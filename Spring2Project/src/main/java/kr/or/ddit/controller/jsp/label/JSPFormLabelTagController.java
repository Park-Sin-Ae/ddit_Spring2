package kr.or.ddit.controller.jsp.label;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/label")
public class JSPFormLabelTagController {
	
	/*
	 * 		13. 라벨 요소
	 * 		- HTML 라벨을 출력하려면 <form:label> 요소를 사용한다.
	 */
	@RequestMapping(value="/registerFormLabel01", method = RequestMethod.GET)
	public String registerFormLabel(Model model) {
		model.addAttribute("member", new Member());
		return "home/formtag/label/registerFormLabel01";
	}
}
