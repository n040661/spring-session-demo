package com.wlf.demo;

import org.springframework.context.annotation.Bean;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

@EnableRedisHttpSession(maxInactiveIntervalInSeconds = 60*60, redisNamespace = "applicationA")
public class HttpSessionConfig {

	@Bean  
    public HttpSessionStrategy httpSessionStrategy() {  
        return new HeaderHttpSessionStrategy();  
    }  
	
}
