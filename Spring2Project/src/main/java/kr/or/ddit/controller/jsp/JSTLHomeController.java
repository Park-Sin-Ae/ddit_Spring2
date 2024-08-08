package kr.or.ddit.controller.jsp;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/jstl")
public class JSTLHomeController {
	/*
	 * 		5. 표준 태그 라이브러리(JSTL)
	 * 		- 많은 개발자들이 JSP에서 코드를 깔끔하게 작성하기 위해서 커스텀 태그를 만들어왔는데,
	 * 		이런 중복되는 노력을 없애기 위해서 나온 것이 바로 JSTL이다.
	 * 
	 * 		1) core 태그 라이브러리
	 * 
	 * 			요소			|			설명 
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 * 		<c:out>			| JSPWriter에 값을 적절하게 처리한 후에 출력한다.
	 * 		<c:set>			| JSP에서 사용할 변수를 설정한다(setter)
	 * 		<c:remove>		| 설정한 변수를 제거한다.
	 * 		<c:catch>		| 예외를 처리한다.
	 * 		<c:if>			| 조건을 지정하고 지정한 조건과 일치하는 처리 내용을 구현한다.
	 * 		<c:choose>		| 여려 조건을 처리할 때 사용한다. (when, otherwise)
	 * 		<c:when>		| 여러 조건을 지정하고 지정한 조건과 일치하는 처리 내용을 구현한다.
	 * 						| <c:choose> 요소에서 사용한다.
	 * 		<c:otherwise>	| <c:when> 요소에서 지정한 조건에 모두 일치하지 않을 때 처리할 내용을 구현
	 * 						| <c:choose> 요소에서 사용한다.
	 * 		<c:forEach>		| 컬렉션이나 배열의 각 항목을 처리할 때 사용한다.
	 * 		<c:forTokens>	| 구분자로 구분된 각각의 토큰을 처리할 때 사용한다.
	 * 		<c:import>		| URL을 사용하여 다른 자원을 삽입한다.
	 * 		<c:url>			| URL을 재작성한다.
	 * 		<c:redirect>	| 지정한 URL에 리다이렉트한다.
	 * 		<c:param>		| 파라미터를 지정한다.	
	 * 		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 * 		[
	 * 			 taglib 지시자 사용 : <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
	 * 		]
	 * 
	 * 		2) fmt태그 라이브러리
	 * 		
	 * 				요소			|			설명 
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 * 		<fmt:formatNumber>	| 숫자를 형식화 한다.
	 * 		<fmt:parseNumber>	| 문자열을 숫자로 변환한다.
	 * 		<fmt:fomatDate>		| Date 객체를 문자열로 변환한다.
	 * 		<fmt:parseDate>		| 문자열을 Date 객체로 변환한다.
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *  	[
	 * 			 taglib 지시자 사용 : <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
	 * 		]
	 * 
	 * 		3) function 태그 라이브러리
	 * 
	 * 					요소			|			설명 
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		<fn:contains>			| 지정한 문자열이 포함되 있는지 판단한다.
	 *		<fn:containsIgnorecase>	| 지정한 문자열이 대문자/소문자를 구분하지 않고  포함돼 있는지 판단한다.
	 *		<fn:startWith>			| 지정한 문자열로 시작하는지 판단한다.
	 *		<fn:endsWith>			| 지정한 문자열로 끝나는지 판단한다.
	 *		<fn:indexOf>			| 지정한 문자열이 처음으로 나왔을 때의 인덱스를 구한다.
	 *		<fn:length>				| 컬렉션 또는 배열의 요소 개수, 문자열 길이를 구한다.
	 *		<fn:escapeXml>			| 지정한 문자열을 XML 구문으로 해석되지 않도록 이스케이프한다.
	 *		<fn:replace>			| 문자열을 치환한다.
	 *		<fn:toLowerCase>		| 문자열을 소문자로 변환한다.
	 *		<fn:toUpperCase>		| 문자열을 대문자로 변환한다.
	 *		<fn:trim>				| 문자열을 trim 한다.
	 *		<fn:substring>			| 지정한 범위에 해당하는 문자열을 잘라낸다.	
	 *		<fn:substringAfter>		| 지정한 문자열에 일치하는 이후의 문자열을 잘라낸다.
	 *		<fn:substringBefore>	| 지정한 문자열에 일치하는 이전의 문자열을 잘라낸다.
	 *		<fn:join>				| 문자열 배열을 결합해서 하나의 문자열을 만든다.
	 *		<fn:split>				| 문자열을 구분자로 분할해서 문자열 배열을 만든다.
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 * 		[
	 * 			 taglib 지시자 사용 : <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
	 * 		]
	 * 
	 * 		6. 코어 태그
	 * 		- 조건분기나 반복처리 그리고 변수의 지정 등과 같이 논리적인 처리를 위해 사용되는 스크립트 코드를
	 * 		대체하기 위한 태그를 제공합니다.
	 * 
	 * 		1) <c:out>
	 * 		- JSPWriter에 값읋 적절하게 처리하한 후에 출력한다.
	 * 
	 * 			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		value		| Object	| 출력할 값
	 *		escapeXml	| boolean	| 특수 문자를 변환할지의 여부
	 *		default		| Object	| value의 결과 값이 null인 경우 출력할 값(대신 출력됨)
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 *		2) <c:set>
	 *		- JSP에서 사용할 변수를 설정한다.
	 *
	 *			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		var			| String	| EL 변수 이름
	 *		value		| Object	| 변수에 할당할 값	
	 *		scope		| String	| 변수를 생성할 영역, 기본값은 page
	 *		target		| Object	| 프로퍼티 값을 설정할 객체 지정
	 *		property	| String	| 프로퍼티 이름		
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		
	 *		3) <c:remove>
	 *		- 설정한 변수를 제거한다.
	 *
	 *			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		var			| String	| 삭제할 EL 변수 이름
	 *		scope		| String	| 삭제할 변수가 포함된 영역
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 *		4) <c:catch>
	 *		- 예외를 처리한다.
	 *		
	 *			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		var			| String	| 예외를 저장할 EL 변수 이름
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 * 		5) <c:if>
	 * 		- 조건을 지정하고 지정된 조건과 일치하는 처리 내용을 구현한다.
	 *		
	 *			속성		|	타입		|				설명
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― 
	 *		var			| String	| 검사 조건의 계산 결과값을 지정할  EL 변수
	 *		test		| boolean	| 검사 조건
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 * 		6) <c:choose>
	 * 		- 여러 조건을 처리할 때 사용한다.
	 * 
	 * 		7) <c:when>
	 * 		- 여러 조건을 지정하고 지정한 조건과 일치하는 처리 내용을 구현한다.
	 * 		<c:choose> 요소에서 사용한다.
	 * 		
	 * 			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		test		| boolean	| 출력한 값 
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 * 		8) <c:otherwise>
	 * 		- <c:when> 요소에서 지정한 조건에 모두 일치하지 않을 때 처리할 내용을 구현한다.
	 * 		  <c:choose> 요소에서 사용한다.
	 * 
	 * 		9) <c:forEach>
	 * 		- 컬렉션이나 배열의 각 항목을 처리할 때 사용한다.
	 * 		
	 * 			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		var			| String	| 몸체에서 사용할 EL 변수 이름
	 *		items		| Object	| 반복 처리할 데이터
	 *		varStatus	| String	| 루프 상태를 저장할 EL 변수 이름
	 *		begin		| int		| 시작 인덱스 값
	 *		end			| int		| 끝 인덱스 값
	 *		step		| int		| 인덱스 증분 값
	 *		―――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――― 
	 *
	 *		10) <c:forTokens>
	 *		- 구분자로 구분된 각각의 토큰을 처리할 때 사용한다.
	 *			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		var			| String	| 몸체에서 사용할 EL 변수 이름
	 *		items		| Object	| 반복 처리할 데이터
	 *		delims		| String	| 구분자
	 *		varStatus	| String	| 루프 상태를 저장할 EL 변수 이름
	 *		begin		| int		| 시작 인덱스 값
	 *		end			| int		| 끝 인덱스 값
	 *		step		| int		| 인덱스 증분 값
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 * 		11) <c:url>
	 * 		- URL을 재작성한다.
	 * 
	 * 			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		value		| Object	| 읽어올 URL
	 *		var			| String	| 읽어올 결과를 저장할 변수 이름
	 *		scope		| String	| 변수를 저장할 영역
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		value 속성값의 두 가지 타입
	 *		- 절대 URL : 완전한 URL이다.
	 *		- 상대 URL :
	 *			> 웹 애플리케이션 내에서의 절대 경로  : '/'로 시작하는 경로(ex_/board/list.jsp)
	 *			> 현재 JSP에 대한 상대 경로 : '/'로 시작하지 않는다.(ex_ ../board/list.jsp)
	 *		웹 애플리케이션 내에서의 절대 경로를 사용할 경우 실제로 생성되는 URL은 컨텍스트 경로를 포함한다.
	 * 	
	 *		12) <c:import>
	 *		- URL을 사용하여 다른 자원을 삽입한다.
	 *
	 *			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		url			| String	| 읽어올 URL
	 *		var			| String	| 읽어온 결과를 저장할 변수 이름
	 *		scope		| String	| 변수를 저장할 영역
	 *		charEncoding| String	| 결과를 읽어 올 때 사용할 캐릭터 인코딩
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 *		13) <c:redirect>
	 *		
	 *			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		url			| String	| 리다이렉트 할 URL
	 *		context		| String	| 컨텍스트 경로
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 *		14) <c:param>
	 *		
	 *			속성		|	타입		|				설명
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *		name		| String	| 파라미터 이름
	 *		value		| Object	| 파라미터 값
	 *		――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――――
	 *
	 */
	// c:out escapeXml 속성의 기본값은 true이다.
	// c:out default 속성은 null 값을 대체한다.
	@RequestMapping(value="/home0102", method = RequestMethod.GET)
	public String home0102(Model model) {
		Member member  = new Member();
		member.setUserId("<p>hongkd<>&%0101</p>");
		member.setPassword(null);
		model.addAttribute("member", member);
		return "home/jstl/home0102";
	}
	
	// c:set을 통해 값을 출력한다.
	// c:set의 몸체를 값으로 사용한다.
	@RequestMapping(value="/home0201", method = RequestMethod.GET)
	public String home0201(Model model) {
		Member member  = new Member();
		member.setUserId("hongkd0201");
		model.addAttribute("member", member);
		return "home/jstl/home0201";
	}
	
	// c:remove
	// c:set 태그로 지정한 변수  memberId를 삭제한다.
	@RequestMapping(value="/home0301", method = RequestMethod.GET)
	public String home0301(Model model) {
		Member member  = new Member();
		member.setUserId("hongkd0301");
		model.addAttribute("member", member);
		return "home/jstl/home0301";
	}
	
	// c:catch
	// EL안에서 발생하는 에러 정보는 EL안에서 처리하므로 var 속성에 설정된 변수로 에러정보를 확인할 수 없다.
	@RequestMapping(value="/home0401", method = RequestMethod.GET)
	public String home0401(Model model) {
		Member member = new Member();
		String[] hobbyArray = {"Music", "Movie"};
		
		member.setHobbyArray(hobbyArray);
		model.addAttribute("member", member);
		return "home/jstl/home0401";
	}
	
	// c:catch
	// EL에서 발생한 에러가 아니르 자바식에서 발생한 에러 정보기 때문에 var 속성에 담긴 에러 정보를 확인할 수 있다.
	@RequestMapping(value="/home0402", method = RequestMethod.GET)
	public String home0402(Model model) {
		return "home/jstl/home0402";
	}
	
	// c:if
	@RequestMapping(value="/home0501", method = RequestMethod.GET)
	public String home0501(Model model) {
		Member member = new Member();
		member.setForeigner(true);
		model.addAttribute("member", member);
		return "home/jstl/home0501";
	}
	
	// c:when, c:otherwise
	@RequestMapping(value="/home0601", method = RequestMethod.GET)
	public String home0601(Model model) {
		Member member = new Member();
		member.setGender("M");
		model.addAttribute("member", member);
		return "home/jstl/home0601";
	}
	
	// c:forEach, c:forTokens
	@RequestMapping(value="/home0701", method = RequestMethod.GET)
	public String home0701(Model model) {
		Member member = new Member();
		String[] hobbyArray = {"Music", "Movie"};
		member.setHobbyArray(hobbyArray);
		
		String hobby = "Music,Movie";
		member.setHobby(hobby);
		model.addAttribute("member", member);
		return "home/jstl/home0601";
	}
	
	// c:import
	@RequestMapping(value="/home0901", method = RequestMethod.GET)
	public String home0901(Model model) {
		return "home/jstl/home0901";
	}
	
	// c:redirect
	@RequestMapping(value="/home1001", method = RequestMethod.GET)
	public String home1001(Model model) {
		return "home/jstl/home1001";
	}
}
