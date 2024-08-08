package kr.or.ddit.controller.jsp;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class JSPHomeController {
	/*
	 * [ 7장 : JSP ]
	 * 
	 * 		1. 지시자
	 * 		- 지시자(directive)는 JSP페이지에 대한 설정 정보를 지정할 때 사용한다.
	 * 		- JSP가 제공하는 지시자에는 page지시자, taglib지시자, include지시자로 세가지가 있다.
	 * 
	 * 		1) page 지시자
	 * 		- JSP 페이지에 대한 정보를 지정한다.
	 *		
	 *				속성			|			설명 
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		contentType			| JSP가 생성할 문서의 MIME 타입과 문자 코드를 지정한다.
	 *		pageEncoding		| JSP 페이지 자체의 문자코드를 지정한다.
	 *		session				| JSP 페이지가 세션을 사용할지의 여부를 지정한다.
	 *		import				| JSP 페이지에서 사용할 자바 클래스를 지정한다.
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 *		2) taglib 지시자
	 *		- JSP 페이지에서 사용할 태그 라이브러리를 지정한다.
	 *		
	 *				속성			|			설명 
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *			prefix			| 태그 라이브러리를 호출할 때 사용할 접두어를 지정한다.
	 *			uri				| TLD 파일이 위치한 URI 혹은 파일 경로를 지정한다.
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 *		3) include 지시자
	 *		- JSP 페이지의 특정 영역에 다른 문서를 포함한다.
	 *
	 *				속성			|			설명 
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *			file			| 포함할 파일의 경로를 지정한다.
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 *		2. 스크립틀릿
	 *		- 스크립틀릿(Scriptlet)은 JSP페이지에서 자바 코드를 실행할 때 사용하는 코드의 블록이다.
	 *		- 스크립틀릿 코드 블록은 "<%"와 "%>" 사이, 그리고 "<%=" 와 "%>" 사이에 자바 코드를 포함할 수 있다.
	 *		- 현재는 스크립틀릿을 사용하기 보다는 JSTL과 같은 커스텀 태그 라이브러리와 EL을 조합하여 JSP를 구현하는
	 *			방법을 많이 사용한다.
	 *
	 *		3. 커스텀 채그 라이브러리
	 *		- 스크립트 요소가 많아지면 많아질수록 JSP코드는 복잡해집니다.
	 *		이 문제를 해결하는 한 가지 방법은 커스텀 태그를 사용하는 것입니다.
	 *		커스텀 태그를 사용하면 뷰를 표시하기 위한 로직을 공통화하거나, 표현하기 복잡한 로직을 캡슐화 할 수 있어서
	 *		JSP의 구현 코드를 간결하게 만들 수 있습니다.
	 *		그리고, 커스텀 태그를 모아 놓은 것을 커스텀 태그 라이브러리라고 합니다.
	 *
	 *			# 대표적인 태그 라이브러리
	 *			- JSTL(JavaServer Pages Standard Tag Library)
	 *			- spring-form JSP Tag Library
	 *			- spring JSP Tag Library
	 *			- spring Security JSP Tag Library
	 *
	 *		4. 표현언어(EL)
	 *		- JSP는 EL(Expression Language)이라는 표현 언어를 사용해 값의 참조, 출력, 연산을 할 수 있습니다.
	 *		- EL식은 ${...} 또는 #{...} 형식으로 작성합니다.
	 *
	 *		# EL을 사용하여 객체를 조회하는 방법은 다음고ㅜㅏ 같다.
	 *		- 자바빈즈 프로퍼티를 조회하는 경우 "속성명.프로퍼티명"을 지정한다.
	 *		- List나 배열 요소를 조회하는 경우 "속성명[요소위치]"를 지정한다.
	 *		- Map 요소를 조회하는 경우 "속성명.프로퍼티명" 또는 "속성명[프로퍼티명]"을 지정한다.
	 *
	 *		# 사용 가능한 연산자
	 *		- EL에서는 다음과 같은 연산자를 사용할 수 있다.
	 *		
	 *		# 산술 연산자
	 *			+	|	-	|	*	|	/(div)	|	%(mood)
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		   더하기	|  빼기	|  곱하기	|    나누기	|	나머지	
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		
	 *		# 비교 연산자
	 *			연산자	| 			설명 
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		   ==(eq)	| 같은 값인지 비교한다.
	 *		   !=(ne)	| 다른 값인지 비교한다.
	 *		   <=(le)	| 왼쪽이 작거나 같은 값인지 비교한다.
	 *		   >=(ge)	| 왼쪽이 크거나 같은 값인지 비교한다
	 *		   <(lt)	| 왼쪽이 작은 값인지 비교한다.
	 *		   >(gt)	| 왼쪽이 큰 값인지 비교한다
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 *		# empty 연산자
	 *		- null 이거나 공백(문자열의 경우 공백 문자)인지 비교
	 *
	 *			** true 조건 :::
	 *			- null값, 빈 문자열(""), 길이가 0인 배열, 빈 collection
	 *
	 *		# 논리 연산자
	 *			연산자	|			설명
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		&&(and)		| 두 피연산자 모두 true이면 bool true를 반환하고, 그렇지 않으면 false
	 *		||(or)		| 두 피연산자 중 하나 또는 모드 true이면 bool true, 그렇지 않으면 false
	 *		!(not)		| 해당 피연산자의 의미를 반대로 바꾼다.
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――
	 * 
	 */	
	
	// 4) 자바빈즈 프로퍼티를 조회하는 경우 "속성명.프로퍼티명" 또는 "속성명[프로퍼티명]"을 지정한다.
	@RequestMapping(value="/home0104", method = RequestMethod.GET)
	public String home0104(Model model) {
		log.info("home0104() 실행...!");
		Map<String, String> memberMap = new HashMap<String, String>();
		memberMap.put("userId", "hong");
		memberMap.put("password", "4321");
		memberMap.put("email", "123@naver.com");
		memberMap.put("userName", "홍길순");
		
		model.addAttribute("memberMap", memberMap);
		return "home/home0104";
	}
	
	// 산술 연산자 사용
	@RequestMapping(value="/home0201", method = RequestMethod.GET)
	public String home0201(Model model) {
		log.info("home0201() 실행...!");
		int coin = 100;
		model.addAttribute("coin", coin);
		return "home/home0201";
	}
	
	// 비교 연산자 사용
	@RequestMapping(value="/home0202", method = RequestMethod.GET)
	public String home0202(Model model) {
		int coin = 1000;
		String userId = "hongkd";
		
		model.addAttribute("coin", coin);
		model.addAttribute("userId", userId);
		return "home/home0202";
		
	}
	
	// empty 연산자 사용
	@RequestMapping(value="/home0301", method = RequestMethod.GET)
	public String home0301(Model model) {
		log.info("home0301() 실행...!");
		return "home/home0301";
	}
	
	// 논리 연산자 사용
	@RequestMapping(value="/home0401", method = RequestMethod.GET)
	public String home0401(Model model) {
		log.info("home0401() 실행...!");
		Member member = new Member();
		int coin = 1000;
		String userId = "hongkd";
		
		model.addAttribute("coin", coin);
		model.addAttribute("userId", userId);
		model.addAttribute("member", member);
		return "home/home0401";
	}
}
