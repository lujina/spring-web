package com.demo.spring_web.util;

import java.io.IOException;

import org.hibernate.TypeMismatchException;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.HttpMediaTypeNotAcceptableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
/**
 * 统一异常处理
 * @author Administrator
 *
 */
@ControllerAdvice
public class RestExceptionHandler {
	//运行时异常
    @ExceptionHandler(RuntimeException.class)  
    @ResponseBody  
    public Object runtimeExceptionHandler(RuntimeException runtimeException) {  

        return ResponseFormat.getResult(1000, null);
    }  
    //空指针异常
    @ExceptionHandler(NullPointerException.class)  
    @ResponseBody  
    public Object nullPointerExceptionHandler(NullPointerException ex) {  
        ex.printStackTrace();
        return ResponseFormat.getResult(1001, null);
    }   
    //类型转换异常
    @ExceptionHandler(ClassCastException.class)  
    @ResponseBody  
    public Object classCastExceptionHandler(ClassCastException ex) {  
        ex.printStackTrace();
        return ResponseFormat.getResult(1002, null);  
    } 
    //IO异常
    @ExceptionHandler(IOException.class)  
    @ResponseBody  
    public Object iOExceptionHandler(IOException ex) {  
        ex.printStackTrace();
        return ResponseFormat.getResult(1003, null); 
    }  
    //未知方法异常
    @ExceptionHandler(NoSuchMethodException.class)  
    @ResponseBody  
    public Object noSuchMethodExceptionHandler(NoSuchMethodException ex) {  
        ex.printStackTrace();
        return ResponseFormat.getResult(1004, null);
    }  
    //数组越界异常
    @ExceptionHandler(IndexOutOfBoundsException.class)  
    @ResponseBody  
    public Object indexOutOfBoundsExceptionHandler(IndexOutOfBoundsException ex) {  
        ex.printStackTrace();
        return ResponseFormat.getResult(1005, null);
    }
    //400错误
    @ExceptionHandler({HttpMessageNotReadableException.class})
    @ResponseBody
    public Object requestNotReadable(HttpMessageNotReadableException ex){
        System.out.println("400..requestNotReadable");
        ex.printStackTrace();
        return ResponseFormat.getResult(400, null);
    }
    //400错误
    @ExceptionHandler({TypeMismatchException.class})
    @ResponseBody
    public Object requestTypeMismatch(TypeMismatchException ex){
        System.out.println("400..TypeMismatchException");
        ex.printStackTrace();
        return ResponseFormat.getResult(400, null);
    }
    //400错误
    @ExceptionHandler({MissingServletRequestParameterException.class})
    @ResponseBody
    public Object requestMissingServletRequest(MissingServletRequestParameterException ex){
        System.out.println("400..MissingServletRequest");
        ex.printStackTrace();
        return ResponseFormat.getResult(400, null);
    }
    //405错误
    @ExceptionHandler({HttpRequestMethodNotSupportedException.class})
    @ResponseBody
    public Object request405(){
        System.out.println("405...");
        return ResponseFormat.getResult(405, null);
    }
    //406错误
    @ExceptionHandler({HttpMediaTypeNotAcceptableException.class})
    @ResponseBody
    public Object request406(){
        System.out.println("404...");
        return ResponseFormat.getResult(406, null);
    }
    //500错误
    @ExceptionHandler({ConversionNotSupportedException.class,HttpMessageNotWritableException.class})
    @ResponseBody
    public Object server500(RuntimeException runtimeException){
        System.out.println("500...");
        return ResponseFormat.getResult(406, null);
    }
}
