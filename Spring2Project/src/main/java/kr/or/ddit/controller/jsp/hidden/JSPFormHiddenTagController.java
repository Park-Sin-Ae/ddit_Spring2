package kr.or.ddit.controller.jsp.hidden;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/hidden")
public class JSPFormHiddenTagController {
	
	/*
	 * 		12. 숨겨진 필드 요소
	 * 		- HTML 숨겨진 필드를 출력하려면 <form:hidden> 요소를 사용한다.
	 */
	
	@RequestMapping(value="/registerFormHidden01", method = RequestMethod.GET)
	public String registerFormHidden01(Model model) {
		log.info("registerFormHidden01() 실행...!");
		Member member = new Member();
		member.setUserId("hong123");
		member.setUserName("홍길순");
		model.addAttribute("member", member);
		return "home/formtag/hidden/registerFormHidden01";
	}
	
	@RequestMapping(value="/result", method = RequestMethod.POST)
	public String registerFormHiddenResult(Member member ,Model model) {
		log.info("registerFormHiddenResult() 실행...!");
		model.addAttribute("member", member);
		return "home/formtag/hidden/result";
	}
}
