package kr.or.ddit.controller.member;

import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ajax")
public class AjaxMemberController {
	
	/*
	 * 		9. Ajax 방식 요청 처리
	 */
	
	// ajax 방식 요청 처리 페이지
	@RequestMapping(value="/registerForm", method = RequestMethod.GET)
	public String ajaxRegisterForm() {
		log.info("ajaxRegisterForm() 실행...!");
		
		return "member/ajaxRegisterForm";
	}
	 
	// 3) 객체 타입의 JSON 요청 데이터 @RequestBody를 지정하여 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/register03", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister03(
			@RequestBody Member member){
		log.info("ajaxRegister03() 실행...!");
		
		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 4) 객체 타입의 JSON 요청 데이터는 문자열 매개변수로 처리할 수 없다.
	// 비동기 일때는 @RequestBody
	// 동기 일때는 @RequestParam
	@ResponseBody
	@RequestMapping(value="/register04", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister04(
			@RequestBody Map<String, String> map){
		// @RequestBody String userId와 같이 기본 데이터 타입으로는 데이터를 받을 수 없다!!
		// @RequestBody Map<String, String> map의 형태를 통해 단일 데이터를 받는다!
		log.info("ajaxRegister04() 실행...!");
		
		log.info("userId : " + map.get("userId"));
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 5) 요청 URL에 쿼리파라미터를 붙여서 전달하면 문자열 매개변수로 처리한다.
	@ResponseBody
	@RequestMapping(value="/register05", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister05(String userId, String password){
		log.info("ajaxRegister05() 실행...!");
		
		log.info("userId : " + userId);
		log.info("password : " + password);
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 7) 객체 배열 타입의 JSON 요청 데이터를 자바빈즈 요소를 가진 리스트 컬렉션 매개변수에 @RequestBody
	// 어노테이션을 지정하여 처리한다.
	@ResponseBody
	@RequestMapping(value="/register07", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister07(
			@RequestBody List<Member> memberList
		){
		log.info("ajaxRegister07() 실행...!");
		
		for (Member member : memberList) {
			log.info("userId : " + member.getUserId());
			log.info("password : " + member.getPassword());
		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 8) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody를 지정하여 중첩된 자바빈즈 매개변수로
	//	처리한다.
	@ResponseBody
	@RequestMapping(value="/register08", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister08(
			@RequestBody Member member
		){
		log.info("ajaxRegister08() 실행...!");
		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		
		Address address = member.getAddress();
		if(address != null) {
			log.info("postCode : " + address.getPostCode());
			log.info("location : " + address.getLocation());
		}else {
			log.info("address is null");
		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
	
	// 9) 중첩된 객체 타입의 JSON 요청 데이터를 @RequestBody를 지정하여 중첩된 자바빈즈 매개변수로
	//	처리한다.(리스트 데이터를 받는다.)
	@ResponseBody
	@RequestMapping(value="/register09", method = RequestMethod.POST)
	public ResponseEntity<String> ajaxRegister09(
			@RequestBody Member member
		){
		log.info("ajaxRegister09() 실행...!");
		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		
		List<Card> cardList = member.getCardList();
		if(cardList != null) {
			log.info("cardList.size() : " + cardList.size());
			
			for (Card card : cardList) {
				log.info("no : " + card.getNo());
				log.info("validMonth : " + card.getValidMonth());
			}
		}else {
			log.info("cardList is null");
		}
		return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
	}
}
