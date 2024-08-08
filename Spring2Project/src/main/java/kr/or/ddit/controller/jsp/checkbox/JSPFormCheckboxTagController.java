package kr.or.ddit.controller.jsp.checkbox;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/checkbox")
public class JSPFormCheckboxTagController {
	/*
	 * 		8. 체크박스 요소
	 * 		- HTML 체크박스를 출력하려면 <form:checkbox> 요소를 사용한다.
	 */
	
	// 1) 모델에 기본생성자로 생성한 폼 객체를 추가한 후에 화면에 전달한다.
	@RequestMapping(value="/registerForm01", method = RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01() 실행...! ");
		
		model.addAttribute("member", new Member());
		return "home/formtag/checkbox/registerForm01";
	}
	
	@RequestMapping(value="/registerForm02", method = RequestMethod.GET)
	public String registerForm02(Model model) {
		log.info("registerForm02() 실행...! ");
		
		Member member = new Member();
		member.setDeveloper("Y");
		member.setForeigner(true);
		member.setHobby("Movie");
		
		String[] hobbyArray = {"Music", "Movie"};
		member.setHobbyArray(hobbyArray);
		
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add("Music");
		hobbyList.add("Movie");
		member.setHobbyList(hobbyList);
		
		model.addAttribute("member", member);
		return "home/formtag/checkbox/registerForm01";
	}
	
	// 넘겨받은 데이터를 결과로 출력
	@RequestMapping(value="/result", method = RequestMethod.POST)
	public String registerResult(Member member, Model model) {
		log.info("registerForm02() 실행...! ");
		log.info("member.developer : " + member.getDeveloper());
		log.info("member.foreigner : " + member.isForeigner());
		log.info("member.hobby : " + member.getHobby());
		
		String[] hobbyArray = member.getHobbyArray();
		if(hobbyArray != null) {
			for (int i = 0; i < hobbyArray.length; i++) {
				log.info("hobbyArray["+i+"] : " + hobbyArray[i]);
			}
		}else {
			log.info("hobbyArray is null");
		}
		List<String> hobbyList = member.getHobbyList();
		if(hobbyList != null) {
			for (int i = 0; i < hobbyList.size(); i++) {
				log.info("hobbyList["+i+"] : " + hobbyList.get(i));
			}
		}else {
			log.info("hobbyList is null");
		}
		model.addAttribute("member", member);
		return "home/formtag/checkbox/result";
	}
}
