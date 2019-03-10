package cn.qblank.concurrency.example.lock;

import java.util.concurrent.locks.StampedLock;

/**
 * 悲观锁和乐观锁的差别
 * @version 1.0
 * @date 2019/3/9 22:19
 */
public class LockExample4 {
    private double x, y;

    private final StampedLock sl = new StampedLock();
    void move(double deltaX, double deltaY) { // an exclusively locked m
        long stamp = sl.writeLock();

        try {

            x += deltaX;

            y += deltaY;

        } finally {

            sl.unlockWrite(stamp);

        }

    }

    double distanceFromOrigin() { // A read-only method

        long stamp = sl.tryOptimisticRead();

        double currentX = x, currentY = y;

        if (!sl.validate(stamp)) {

            stamp = sl.readLock();

            try {

                currentX = x;

                currentY = y;

            } finally {

                sl.unlockRead(stamp);

            }

        }

        return Math.sqrt(currentX * currentX + currentY * currentY);

    }

    void moveIfAtOrigin(double newX, double newY) { // upgrade

        // Could instead start with optimistic, not read mode

        long stamp = sl.readLock();

        try {

            while (x == 0.0 && y == 0.0) {

                long ws = sl.tryConvertToWriteLock(stamp);

                if (ws != 0L) {

                    stamp = ws;

                    x = newX;

                    y = newY;

                    break;

                } else {

                    sl.unlockRead(stamp);

                    stamp = sl.writeLock();

                }

            }

        }finally {
            sl.unlock(stamp);
        }
    }
}