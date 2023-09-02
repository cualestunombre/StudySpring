package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.util.Map;
import java.util.Properties;

@Slf4j
public class OsEnv {
    @Test
    public void printOsEnv(){
        Map<String,String> envMap = System.getenv();
        for(String key : envMap.keySet()){
            log.info("env {}={}",key,System.getenv());
        }
    }

    @Test
    public void systemProperties(){
        Properties properties = System.getProperties();
        for (Object key : properties.keySet()){
            log.info("prop {}={}",key,System.getProperty(String.valueOf(key)));
        }
    }

    @Test
    public void printMyProperties(){
        Properties properties = System.getProperties();
        for(Object key : properties.keySet()){
            log.info("prop {}={}",key,System.getProperty(String.valueOf(key)));
        }

        String url = System.getProperty("url");
        String username = System.getProperty("username");
        String password = System.getProperty("password");

        log.info("url={}",url);
        log.info("username={}",username);
        log.info("password={}",password);


    }
}
