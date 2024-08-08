package kr.or.ddit.controller.exception;

import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.NoHandlerFoundException;

import lombok.extern.slf4j.Slf4j;

// @ControllerAdvice 스프링 컨트롤러에서 발생하는 예외를 처리하는 핸들러 클래스임을 명시한다.
@ControllerAdvice
@Slf4j
public class CommonExceptionHandler {
	
	// @ExceptionHandler 어노테이션은 괄호안에 설정한 예외 타입을 해당 메소드가 처리한다는 것을 의미한다.
//	@ExceptionHandler(Exception.class)
//	public String handle(Exception e, Model model) {
//		log.info("e : " + e.toString());
//		
//		model.addAttribute("exception", e);
//		return "error/errorCommon";
//	}
//	
//	@ExceptionHandler(NoHandlerFoundException.class)
//	@ResponseStatus(value=HttpStatus.NOT_FOUND)
//	public String handle404(Exception e) {
//		log.info("handle 404 exception : " + e.toString());
//		return "error/custom404";
//	}
}
