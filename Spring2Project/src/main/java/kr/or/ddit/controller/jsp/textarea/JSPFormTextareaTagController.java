package kr.or.ddit.controller.jsp.textarea;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/textarea")
public class JSPFormTextareaTagController {
	/*
	 * 		6. 텍스트 영역 요소(textarea)
	 * 		- HTML 텍스트 영역을 출력하려면 <form:textarea> 요소를 사용한다.
	 * 
	 */
	
	@RequestMapping(value="/registerForm01", method = RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01() 실행...!");
		model.addAttribute("member", new Member());
		return "home/formtag/textarea/registerForm01";
	}
	
	@RequestMapping(value="/registerForm02", method = RequestMethod.GET)
	public String registerForm02(Model model) {
		log.info("registerForm02() 실행...!");
		Member member = new Member();
		member.setIntroduction("반갑습니다!\n저는 305호 학생입니다!\n 잘 부탁드려요");
		model.addAttribute("member", member);
		return "home/formtag/textarea/registerForm01";
	}
}
