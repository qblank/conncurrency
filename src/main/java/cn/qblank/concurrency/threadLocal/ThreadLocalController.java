package cn.qblank.concurrency.threadLocal;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @date 2018/10/31
 */
@Controller
@RequestMapping("/threadLocal")
public class ThreadLocalController {
    @RequestMapping("/test")
    @ResponseBody
    public Long test(){
        return RequestHolder.getId();
    }
}
