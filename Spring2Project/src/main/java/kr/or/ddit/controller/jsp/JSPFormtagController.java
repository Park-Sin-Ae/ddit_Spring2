package kr.or.ddit.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/formtag")
public class JSPFormtagController {
   /*
    *    [ 8장. 스프링 폼 태그 ]
    *     
    *     1. 스프링 폼 태그 라이브러리
    *     - 스프링 폼은 HTML 폼을 표시하기 위한 태그 라이브러리입니다.
    *     - 스프링 폼을 이용하면 HTML 폼과 자바 객체를 쉽게 바인딩 할 수 있습니다.
    *     
    *        # 스프링 폼 커스텀 태그 목록
    *  
    *         <form : form>
    *         - 폼 요소를 생성한다.
    *         <form : input>
    *         - 텍스트 필드 요소를 생성한다.
    *         <form : password>
    *         - 패스워드 필드 요소를 생성한다.
    *         <form : textarea>
    *         - 텍스트 영역 요소를 생성한다.
    *         <form : checkboxes>
    *         - 여러개의 체크박스 요소를 생성한다.
    *         <form : checkbox>
    *         - 체크박스  요소를 생성한다.
    *         <form : radiobuttons>
    *         - 여러개의 라디오 버튼 요소를 생성한다.
    *         <form : radiobutton>
    *         - 라디오 버튼 요소를 생성한다.
    *         <form : select>
    *         - 셀렉트 박스 요소를 생성한다.
    *         <form : hidden>
    *         - 숨겨진 필드 요소를 생성한다.
    *         <form : label>
    *         - 라벨 요소를 생성한다.
    *         <form : button>
    *         - 버튼 요소를 생성한다.
    *         <form : errors>
    *         - 입력값 검증 오류를 표시한다.
    *         스프링 폼 태그 라이브러리 선언 방법
    *         [<%@taglib uri="http://www.springframework.org/tags/form" %>"]
    *   
    *   2. 폼 요소
    *   - HTML 폼을 출력하려면 <form : form> 요소를 사용하여 생성합니다.
    *   ** 생성 시, 사용하게 될 속성들이 어떤 것이 있고 설정 정보에는 어떤 것이 있는 지 체크해봅시다!
    *   
    *   3. 폼 항목의 공통 속성
    *   - HTML 폼 항목을 출력하기 위한 태그 라이브러리에는 몇 가지 공통 속성이 있습니다.
    *      path
    *      - 폼 항목에 바인딩되는 폼 객체는 프로퍼티를 저장한다.
    *      disabled
    *      - 폼 항목을 비활성화할지 여부를 지정합니다. 기본값은 false
    *      readonly
    *      - 폼 항목을 읽기 전용으로 만들지 여부를 지정한다. 기본값은 false
    *         
    */
	@RequestMapping(value="/registerForm01", method = RequestMethod.GET)
	public String registerForm01(Model model) {
		log.info("registerForm01() 실행...!");
		model.addAttribute("member", new Member());
		return "home/formtag/registerForm01";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String register(Member member, Model model) {
		log.info("register() 실행...!");
		log.info("userId : " + member.getUserId());
		log.info("userName : " + member.getUserName());
		
		model.addAttribute("member", member);
		return "home/formtag/result";
	}
}
