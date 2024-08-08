package kr.or.ddit.controller.model;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/redirectattribute")
public class RedirectAttributeMemberController {
	/*
	 * 		4. RedirectAttribute 타입
	 * 		- RedirectAttribute는 일회성으로 데이터를 전달하는 용도로 사용한다.
	 */
	
	// 테스트 페이지
	@RequestMapping(value="/registerForm", method = RequestMethod.GET)
	public String registerForm() {
		log.info("registerForm() 실행...!");
		return "member/registerRedirectAttributeForm";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(Member member, RedirectAttributes ra) {
		log.info("register() 실행...!");
		log.info("userId : " + member.getUserId());
		ra.addFlashAttribute("msg", "success");
		return "redirect:/redirectattribute/result";
	}
	
	// 페이지 이동방식 redirect 후, 이동할 result 페이지
	@RequestMapping(value="/result", method = RequestMethod.GET)
	public String result() {
		log.info("result() 실행...!");
		return "result";
	}
}
