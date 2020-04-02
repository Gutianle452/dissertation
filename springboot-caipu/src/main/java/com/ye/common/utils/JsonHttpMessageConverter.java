package com.ye.common.utils;

import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;

import org.springframework.http.HttpOutputMessage;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.support.spring.FastJsonHttpMessageConverter;

/**
 * 自定义fastJson日期转换格式
 * 将默认的"yyyy-MM-dd HH:mm:ss"格式改为 "yyyy-MM-dd"格式
 * @author jjs
 *
 */
public class JsonHttpMessageConverter extends FastJsonHttpMessageConverter{
	
	private static SerializeConfig mapping = new SerializeConfig();  
	private static String dateFormat;  
	static {  
	    dateFormat = "yyyy-MM-dd";  
	    mapping.put(Date.class, new SimpleDateFormatSerializer(dateFormat));  
	} 
	
	@Override
	protected void writeInternal(Object obj, HttpOutputMessage outputMessage)
			throws IOException, HttpMessageNotWritableException {
		// TODO Auto-generated method stub
		
		OutputStream out = outputMessage.getBody();
        String text = JSON.toJSONString(obj, mapping, this.getFeatures());
        byte[] bytes = text.getBytes(this.getCharset());
        out.write(bytes);
	}
	
}
