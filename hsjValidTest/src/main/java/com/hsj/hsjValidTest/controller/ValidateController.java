package com.hsj.hsjValidTest.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.hsj.hsjValidTest.pojo.ValBean;
import com.hsj.hsjValidTest.vo.JSONResultWrapper;
/**
 * 博客：https://www.cnblogs.com/leechenxiang/p/5546615.html
 * @Description:TODO
 * @author:hsj qq:2356899074
 * @time:2017年11月14日 上午10:50:13
 */
@RestController
@RequestMapping(value = "/val")
public class ValidateController {
	
	private final static Logger LOGGER = LoggerFactory.getLogger(ValidateController.class);

	/**不优雅的处理方式，不推荐；推荐全局异常处理
	 * post:http://10.88.20.84:8081/hsjValidatorTest/val/val
	 * Content-Type:application/json;charset=UTF-8
	 * body:{
	        "age":"18",
    		"username":"hsjxia",
    		"password":"123hsj678",
    		"phone":"13518705742",
    		"email":"2356899074@qq.com"
			}
	 * 
	 * @Description:TODO
	 * @author:hsj qq:2356899074
	 * @time:2017年11月14日 下午1:33:03
	 * @param b
	 * @param result
	 * @return
	 * @throws Exception
	 */
    @RequestMapping(value = "/val",method={RequestMethod.POST,RequestMethod.GET} )
    public JSONResultWrapper val(@Valid @RequestBody ValBean b, BindingResult result) {
    	
        if(result.hasErrors()){    
            //如果没有通过,跳转提示    
            Map<String, String> map = getErrors(result);
            return JSONResultWrapper.errorWithMap(map,JSONResultWrapper.RESCODE_ERR);
        }else{    
            //继续业务逻辑    
        	LOGGER.info("info {}",JSON.toJSONString(b));
        } 
        
        return JSONResultWrapper.okWithObject(b,JSONResultWrapper.RESCODE_OK);
    }
    
    
    private Map<String, String> getErrors(BindingResult result) {
        Map<String, String> map = new HashMap<String, String>();
        List<FieldError> list = result.getFieldErrors();
        for (FieldError error : list) {
        	LOGGER.info("error.getField(): {}",error.getField());
        	LOGGER.info("error.getDefaultMessage(): {}",error.getDefaultMessage());
            
            map.put(error.getField(), error.getDefaultMessage());
        }
        return map;
    }
    
}