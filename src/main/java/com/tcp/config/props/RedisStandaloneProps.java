package com.tcp.config.props;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author Xu
 */
@Component
@EnableConfigurationProperties
@ConfigurationProperties(prefix = "redis.standalone")
public class RedisStandaloneProps {

    public RedisStandaloneProps() {
        this.getPool();
    }

    private String ip;
    private Integer port;
    private String password;
    private Map<String,Object> pool;
    //最大分配的对象数
    private Integer maxActive;
    //最大能够保持idel状态的对象数
    private Integer maxIdle;
    //当调用borrow Object方法时，是否进行有效性检查
    private Boolean testOnBorrow;

    private Boolean usePool;

    private Integer timeOut;

    private Integer maxWait;

    private Integer minIdle;


    public Integer getMaxWait() {
        if(maxWait==null){
            if(pool!=null && pool.containsKey("max-wait")){
                this.maxWait = (Integer) pool.get("max-wait");
            }
        }
        return maxWait;
    }

    public void setMaxWait(Integer maxWait) {
        this.maxWait = maxWait;
    }

    public Integer getMinIdle() {
        if(minIdle==null){
            if(pool!=null && pool.containsKey("min-idle")){
                this.minIdle = (Integer) pool.get("min-idle");
            }
        }
        return minIdle;
    }

    public void setMinIdle(Integer minIdle) {
        this.minIdle = minIdle;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Map<String, Object> getPool() {
        return pool;
    }

    public void setPool(Map<String, Object> pool) {
        this.pool = pool;
    }

    public Integer getMaxActive() {
        if(maxActive==null){
            if(pool!=null && pool.containsKey("max-active")){
                this.maxActive = (Integer) pool.get("max-active");
            }
        }
        return maxActive;
    }

    public void setMaxActive(Integer maxActive) {
        this.maxActive = maxActive;
    }

    public Integer getMaxIdle() {
        if(maxIdle==null){
            if(pool!=null && pool.containsKey("max-idle")){
                this.maxIdle = (Integer)pool.get("max-idle");
            }
        }
        return maxIdle;
    }

    public void setMaxIdle(Integer maxIdle) {
        this.maxIdle = maxIdle;
    }

    public Boolean getTestOnBorrow() {
        if(testOnBorrow==null){
            if(pool!=null && pool.containsKey("testOnBorrow")){
                this.testOnBorrow = (Boolean)pool.get("testOnBorrow");
            }
        }
        return testOnBorrow;
    }

    public void setTestOnBorrow(Boolean testOnBorrow) {
        this.testOnBorrow = testOnBorrow;
    }

    public Boolean getUsePool() {
        if(usePool==null){
            if(pool!=null && pool.containsKey("usePool")){
                this.usePool = (Boolean)pool.get("usePool");
            }
        }
        return usePool;
    }

    public void setUsePool(Boolean usePool) {
        this.usePool = usePool;
    }

    public Integer getTimeOut() {
        if(timeOut==null){
            if(pool!=null && pool.containsKey("timeOut")){
                this.timeOut = (Integer)pool.get("timeOut");
            }
        }
        return timeOut;
    }

    public void setTimeOut(Integer timeOut) {
        this.timeOut = timeOut;
    }
}


