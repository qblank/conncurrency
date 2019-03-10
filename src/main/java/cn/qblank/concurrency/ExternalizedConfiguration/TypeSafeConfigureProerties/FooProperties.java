package cn.qblank.concurrency.ExternalizedConfiguration.TypeSafeConfigureProerties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.net.InetAddress;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;




/**
 * @date 2018/11/5
 */
@ConfigurationProperties(prefix = "foo")
@Data
public class FooProperties {
    private boolean enabled;

    private InetAddress remoteAddress;

    private final Security security = new Security();

    public static class Security {

        private String username;

        private String password;

        private List<String> roles = new ArrayList<>(Collections.singleton("USER"));

    }


}
