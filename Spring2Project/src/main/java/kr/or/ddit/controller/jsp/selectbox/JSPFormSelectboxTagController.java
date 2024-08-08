package kr.or.ddit.controller.jsp.selectbox;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.CodeLabelValue;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag/selectbox")
public class JSPFormSelectboxTagController {
	/*
	 * 		11. 셀렉트 박스 요소
	 * 		- HTML 셀렉트 박스를 출력하려면 <form:select>요소를 사용한다.
	 */
	
	// 1) 모델에 Map타입의 데이터를 생성하여 추가한 후에 화면으로 전달한다.
	@RequestMapping(value="/registerFormSelectbox01", method = RequestMethod.GET)
	public String registerFormSelectbox01(Model model) {
		log.info("registerFormSelectbox01 실행...!");
		
		Map<String, String> nationalityCodeMap = new HashMap<String, String>();
		nationalityCodeMap.put("01", "Korea");
		nationalityCodeMap.put("02", "Canada");
		nationalityCodeMap.put("03", "Austrailia");
		
		model.addAttribute("nationalityCodeMap", nationalityCodeMap);
		model.addAttribute("member", new Member());
		return "home/formtag/selectbox/registerFormSelectBox01";
	}
	
	// 2) 모델에 List타입의 데이터를 생성하여 추가한 후에 화면으로 전달한다.
	@RequestMapping(value="/registerFormSelectbox02", method = RequestMethod.GET)
	public String registerFormSelectbox02(Model model) {
		log.info("registerFormSelectbox02 실행...!");
		
		List<CodeLabelValue> nationalityCodeList = new ArrayList<CodeLabelValue>();
		nationalityCodeList.add(new CodeLabelValue("01", "Korea"));
		nationalityCodeList.add(new CodeLabelValue("02", "Canada"));
		nationalityCodeList.add(new CodeLabelValue("03", "Austrailia"));
		
		model.addAttribute("nationalityCodeList", nationalityCodeList);
		model.addAttribute("member", new Member());
		return "home/formtag/selectbox/registerFormSelectbox02";
			
	}
	// 3) 
	@RequestMapping(value="/registerFormSelectbox03", method = RequestMethod.GET)
	public String registerFormSelectbox03(Model model) {
		log.info("registerFormSelectbox03 실행...!");
		
		List<CodeLabelValue> carCodeList = new ArrayList<CodeLabelValue>();
		carCodeList.add(new CodeLabelValue("01", "Jeep"));
		carCodeList.add(new CodeLabelValue("02", "Bmw"));
		carCodeList.add(new CodeLabelValue("03", "Audi"));
		
		model.addAttribute("carCodeList", carCodeList);
		model.addAttribute("member", new Member());
		return "home/formtag/selectbox/registerFormSelectbox03";
		
	}
	
	@RequestMapping(value="/result", method = RequestMethod.POST)
	public String registerFormSelectboxResult(Member member, Model model) {
		log.info("registerFormSelectbox03 실행...!");
		
		model.addAttribute("member", member);
		return "home/formtag/selectbox/result";
	}
}
