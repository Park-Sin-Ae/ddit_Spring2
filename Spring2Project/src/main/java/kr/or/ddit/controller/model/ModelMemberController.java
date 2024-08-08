package kr.or.ddit.controller.model;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.Member;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ModelMemberController {
	/*
	 * [ 6장. 데이터 전달자 모델 ]
	 * 
	 * 		1. 모델 객체
	 * 		- Model 객체는 뷰(View)에 컨트롤러에서 생성된 데이터를 담아서 전달하는 역할을 한다.
	 * 
	 * 		2. 모델을 통한 데이터 전달
	 * 		- Model 객체를 통해서 다양한 데이터를 뷰(View)에 전달할 수 있다.
	 */
	
	// 1) 매개변수에 선언된 Model 객체의 addAttribute() 메서드를 호출하여 데이터 뷰(View)에 전달한다.
	@RequestMapping(value="/read01", method = RequestMethod.GET)
	public String read01(Model model) {
		log.info("read01() 실행...!");
		
		model.addAttribute("userId", "hongkd");
		model.addAttribute("password", "1234");
		model.addAttribute("email", "aaa@ccc.com");
		model.addAttribute("userName", "홍길동");
		model.addAttribute("birthDay", "2024-05-21");
		return "member/read01";
	}
	
	// 2) Model 객체에 자바빈즈 클래스 객체를 값으로만 전달할 때는 뷰에서 참조할 이름을 클래스명의 앞 글자를
	// 소문자로 변환하여 처리한다.
	@RequestMapping(value="/read02", method = RequestMethod.GET)
	public String read02(Model model) {
		log.info("read02() 실행...!");
		
		Member member = new Member();
		member.setUserId("hongkildong");
		member.setPassword("1122");
		member.setEmail("aaa@ccc.com");
		member.setUserName("홍길순");
		member.setBirthDay("2024-05-21");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		
		member.setDateOfBirth(cal.getTime());
		model.addAttribute(member);
		return "member/read02";
	}
	
	// 3) Model 객체에 자바빈즈 클래스 객체를 특정한 이름을 지정하여 전달할 수 있다.
	@RequestMapping(value="/read03", method = RequestMethod.GET)
	public String read03(Model model) {
		log.info("read03() 실행...!");
		
		Member member = new Member();
		member.setUserId("hongkildong");
		member.setPassword("1122");
		member.setEmail("aaa@ccc.com");
		member.setUserName("홍길순");
		member.setBirthDay("2024-05-21");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 4);
		cal.set(Calendar.DAY_OF_MONTH, 21);
		
		member.setDateOfBirth(cal.getTime());
		model.addAttribute("user", member);
		return "member/read02";
	}
	
	// 4) Model 객체를 통해 배열과 컬렉션 객체를 전달할 수 있다.
	@RequestMapping(value="/read04", method = RequestMethod.GET)
	public String read04(Model model) {
		log.info("read04 실행...!");
		
		String[] carArray = {"jeep", "bmw"};
		
		List<String> carList = new ArrayList<String>();
		carList.add("jeep");
		carList.add("audi");
		
		String[] hobbyArray = {"Music", "Movie"};
		
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add("Sports");
		hobbyList.add("Music");
		
		model.addAttribute("carArray", carArray);
		model.addAttribute("carList", carList);
		model.addAttribute("hobbyArray", hobbyArray);
		model.addAttribute("hobbyList", hobbyList);
		return "member/read04";
	}
	
	// 6) Model 객체를 통해 다양한 타입의 값을 전달할 수 있다.
	@RequestMapping(value="/read06", method = RequestMethod.GET)
	public String read06(Model model) {
		log.info("read06() 실행...!");
		
		Member member = new Member();
		member.setUserId("hongkd1");
		member.setPassword("1234");
		member.setEmail("aaa@ccc.com");
		member.setUserId("홍길동이");
		member.setBirthDay("2024-05-21");
		member.setGender("남자");
		member.setDeveloper("Y");
		member.setForeigner(true);
		member.setNationality("korea");
		member.setCars("jeep");
		
		String[] carArray = {"jeep", "bmw"};
		member.setCarArray(carArray);
		
		List<String> carList = new ArrayList<String>();
		carList.add("bmw");
		carList.add("audi");
		member.setCarList(carList);
		member.setHobby("Music, Movie");
		
		String[] hobbyArray = {"Music","Movie"};
		member.setHobbyArray(hobbyArray);
		
		List<String> hobbyList = new ArrayList<String>();
		hobbyList.add("Music");
		hobbyList.add("Sports");
		member.setHobbyList(hobbyList);
		
		Address address = new Address();
		address.setPostCode("090908");
		address.setLocation("Daejeon");
		member.setAddress(address);
		
		List<Card> cardList = new ArrayList<Card>();
		Card card1 = new Card();
		card1.setNo("123456");
		
		Calendar cal = Calendar.getInstance();
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 9);
		cal.set(Calendar.DAY_OF_MONTH, 10);
		card1.setValidMonth(cal.getTime());
		cardList.add(card1);
		
		Card card2 = new Card();
		card2.setNo("56789");
		
		cal.set(Calendar.YEAR, 2024);
		cal.set(Calendar.MONTH, 10);
		cal.set(Calendar.DAY_OF_MONTH, 11);
		card2.setValidMonth(cal.getTime());
		cardList.add(card2);
		
		member.setCardList(cardList);
		member.setDateOfBirth(cal.getTime());
		member.setIntroduction("안녕하세요\n반갑습니다!");
		model.addAttribute("user", member);
		return "member/read06";
		
	}
}
