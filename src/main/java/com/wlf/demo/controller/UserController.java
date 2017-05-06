package com.wlf.demo.controller;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;

@RestController
public class UserController {
	
	@RequestMapping(value = "/rest/user",
			method = RequestMethod.GET, 
			produces = MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
	public void getUser(HttpServletRequest request,HttpServletResponse response){
		HttpSession session = request.getSession();  
	    String loginname = (String) session.getAttribute("loginname");  
	    writeJson(loginname + " : " + session.getId(),response);  
	}
	
	@RequestMapping(value = "/login/{loginname}/{password}",
			method = RequestMethod.POST, 
			produces = MediaType.TEXT_PLAIN_VALUE+";charset=UTF-8")
	public void getUser(@PathVariable final String loginname,@PathVariable final String password,HttpServletRequest request,HttpServletResponse response){
    	if(loginname.equals("admin") && password.equals("123456")){
    		HttpSession session = request.getSession();    
    		session.setAttribute("loginname", loginname);
    		writeJson("登录成功!",response);  
    	}else{
    		System.out.println("用户信息验证失败!");
    		writeJson("无效的用户信息!",response);  
    	}    
	}
	
	public void writeJson(Object object,HttpServletResponse response)
    {
        try
        {
        	//DisableCircularReferenceDetect避免$ref问题
            String json = JSON.toJSONStringWithDateFormat(object, "yyyy-MM-dd HH:mm:ss",SerializerFeature.DisableCircularReferenceDetect);
            response.setContentType("text/html;charset=utf-8");
            response.getWriter().write(json);
            response.getWriter().flush();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
	
}
