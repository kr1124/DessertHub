package com.desserthub.error;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.naming.ServiceUnavailableException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    // 404 에러 처리 (Page Not Found)
    @ExceptionHandler(org.springframework.web.servlet.NoHandlerFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND) // 404 오류 처리
    public String handle404Error(Model model) {
        model.addAttribute("errorCode", 404);
        model.addAttribute("errorMessage", "Page not found!");
        return "error/404";  // 404.html 페이지로 이동
    }

    // 500 에러 처리 (Internal Server Error)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR) // 500 오류 처리
    public String handle500Error(Exception ex, Model model) {
        model.addAttribute("errorCode", 500);
        model.addAttribute("errorMessage", "Internal Server Error!");
        model.addAttribute("exception", ex.getMessage());  // 예외 메시지 전달
        return "error/500";  // 500.html 페이지로 이동
    }

    // 503 에러 처리 (Service Unavailable)
    @ExceptionHandler(ServiceUnavailableException.class)
    @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE) // 503 오류 처리
    public String handle503Error(ServiceUnavailableException ex, Model model) {
        model.addAttribute("errorCode", 503);
        model.addAttribute("errorMessage", "Service is temporarily unavailable. Please try again later.");
        model.addAttribute("exception", ex.getMessage());  // 예외 메시지 전달
        return "error/503";  // 503.html 페이지로 이동
    }

    // 다른 예외 처리 (사용자 정의 예외 등)
    // @ExceptionHandler(CustomException.class)
    // @ResponseStatus(HttpStatus.BAD_REQUEST) // 400 오류 처리
    // public String handleCustomError(CustomException ex, Model model) {
    //     model.addAttribute("errorCode", 400);
    //     model.addAttribute("errorMessage", ex.getMessage());
    //     return "error/400";  // custom_error.html 페이지로 이동
    // }

    // 모든 뷰에서 공통적으로 사용될 모델 속성 추가
    @ModelAttribute
    public void addCommonAttributes(Model model) {
        model.addAttribute("appName", "My Application");
    }
}

