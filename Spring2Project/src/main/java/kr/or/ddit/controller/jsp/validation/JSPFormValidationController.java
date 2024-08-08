package kr.or.ddit.controller.jsp.validation;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/validation")
public class JSPFormValidationController {
	
	/*
	 * 		15. 입력값 검증 에러
	 * 		- 입력값 검증 에러를 출력하려면 <form:errors> 요소를 사용한다.
	 */
	
	@RequestMapping(value="/registerFormValidation01", method = RequestMethod.GET)
	public String registerFormValidation01(Model model) {
		log.info("registerFormValidation01() 실행...!");
		model.addAttribute("member", new Member());
		return "home/formtag/validation/registerFormValidation01";
	}
	
	// 입력값 검증 결과 페이지
	@RequestMapping(value="/result", method = RequestMethod.POST)
	public String registerFormValidationResult(Member member, Model model) {
		log.info("registerFomValidationResult() 실행...!");
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		return "home/formtag/validation/result";
		
	}
}
