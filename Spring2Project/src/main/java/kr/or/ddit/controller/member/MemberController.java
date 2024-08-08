package kr.or.ddit.controller.member;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import kr.or.ddit.vo.FileMember;
import kr.or.ddit.vo.Member;
import kr.or.ddit.vo.MultiFileMember;
import kr.or.ddit.vo.test.RegisterMemberVO;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {
	/*
	 * [ 5장 : 컨트롤러 요청 처리]
	 * 
	 * 		1. 컨트롤러 메소드 매개변수
	 * 		- Model : 이동 대상에 전달할 데이터를 가지고 있는 인터페이스
	 * 		- RedirectAttributes : 리다이렉트 대상에 전달할 데이터를 가지고 있는 인터페이스
	 * 		- 자바빈즈 클래스 : 요청 파라미터를 가지고 있는 자바빈즈 클래스
	 * 		- MultipartFile : 멀티파트 요청을 사용해 업로드된 파일 정보를 가지오 있는 인터페이스
	 * 		- BindingResult : 도메인 클래스의 입력 값 검증 결과를 가지고 있는 인터페이스
	 * 		- Locale : 클라이언트 Locale
	 * 		- Principal : 클라이언트 인증을 위한 사용자 정보를 가지고 있는 인터페이스
	 */
	// 요청 처리 페이지
	@RequestMapping(value="/registerForm", method = RequestMethod.GET)
	public String registerForm() {
		 log.info("registerForm() 실행...!");
		 return "member/registerForm";
	}
	
	// 1) URL 경로 상의 쿼리 파라미터 정보로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String registerByParameter(String userId, String password) {
		log.info("registerByParameter() 실행...!");
		log.info("userId : " + userId);
		log.info("password : " + password);
		return "success";
	}
	
	// 2) URL 경로 상의 경로 변수로부터 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register/{userId}", method = RequestMethod.GET)
	public String registerByPath(@PathVariable String userId) {
		log.info("registerByPath() 실행...!");
		log.info("userId : " + userId);
		return "success";
	}
	// 3) HTML Form 필드명과 컨트롤러 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.
	// 그리고, 매개변수의 위치는 순서에 상관없고 오직 매개변수명이 일치하면 요청 데이터를 취득할 수 있다.
	@RequestMapping(value="/register01", method = RequestMethod.POST)
	public String register01(String userId, String password) {
		log.info("register01() 실행...!");
		log.info("userId" + userId);
		log.info("password" + password);
		return "success";
	}
	// 4) HTML Form 필드값이 숫자일 경우에는 숫자로 타입변화하여 데이터를 취득할 수 있다.
	@RequestMapping(value="/register02", method = RequestMethod.POST)
	public String register02(String userId, String password, int coin) {
		log.info("register02() 실행...!");
		log.info("userId" + userId);
		log.info("password" + password);
		log.info("coin" + coin);
		return "success";
	}
	
	/*
	 * 		3. 요청 데이터 처리 어노테이션
	 * 		- @PathVariables : URL에서 경로 변수 값을 가져오기 위한 어노테이션
	 * 		- @RequestParam : 요청 파라미터 값을 가져오기 위한 어노테이션
	 * 		- @RequestHeader : 요청 헤더 값을 가져오기 위한 어노테이션
	 * 		- @RequestBody : 요청 본문 내용을 가져오기 위한 어노테이션
	 * 		- @CookieValue : 쿠키 값을 가져오기 위한 어노테이션
	 */
	// 1) URL 경로 상의 경로 변수가 여러개일때 @PathVariable 어노테이션을 사용하여
	// 특정한 경로 변수 값을 지정해준다.
	@RequestMapping(value="/register/{userId}/{coin}",
			method = RequestMethod.GET)
	public String registerByPath(
			@PathVariable("userId") String userId,
			@PathVariable("coin") int coin) {
		log.info("registerByPath() 실행...!");
		log.info("userId : " + userId);
		log.info("coin : " + coin);
		return "success";
	}
	
	// 2) @RequestParam 어노테이션을 사용하여 특정한 HTML Form의 필드명을 지정하여
	// 요청을 처리한다. 데이터를 받는 필드명은 동일하게 userId로 하되, 사용하는 변수명은 
	// username으로 달리 설정할 수 있다.
	// 우리가 페이징을 구현할 때 page를 넘기는 방법을 이녀석을 채택해서 구현함.
	@RequestMapping(value="/register0202",
			method = RequestMethod.POST)
	public String register0202(
			@RequestParam("userId") String username
		) {
		log.info("register0202() 실행...!");
		log.info("username" + username);
		return "success";
	}
	
	/*
	 * 		4. 요청 처리 자바빈즈
	 */
	// 1) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/beans/register01", method = RequestMethod.POST)
	public String registerJavaBeans01(Member member) {
		log.info("registerJavaBeans01() 실행...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		log.info("member.coin : " + member.getCoin());
		return "success";
	}
	
	// 2) 폼 텍스트 필드 요소값을 자바빈즈 매개변수와 기본 데이터 타입인 정수 매개변수로 처리한다.
	@RequestMapping(value="/beans/register02", method = RequestMethod.POST)
	public String registerJavaBeans02(Member member, int coin) {
		log.info("registerJavaBeans02() 실행...!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.password : " + member.getPassword());
		log.info("member.coin : " + member.getCoin());
		log.info("coin : " + coin);
		return "success";
	}
	
	/*
	 * 		5. Date 타입 처리
	 * 		- 스프링 MVC는 Date 타입의 데이터를 처리하는 여러 방법을 제공한다.
	 */
	// 1) - 4) 처리
	@RequestMapping(value="/registerByGet01",
			method = RequestMethod.GET)
	public String registerByGet01(
			String userId, Date dateOfBirth) {
		log.info("registerByGet01() 실행...!");
		log.info("userId : " + userId);
		log.info("dateOfBirth : " + dateOfBirth);
		return "success";
	}
	
	@RequestMapping(value="/registerByGet02", 
			method = RequestMethod.GET)
	public String registerByGet02(Member member) {
		log.info("registerByGet02() 실행..!");
		log.info("member.userId : " + member.getUserId());
		log.info("member.dateOfBirth : " + member.getDateOfBirth());
		return "success";
	}
	
	/*
	 * 		6. @DateTimeFormat 어노테이션
	 * 		- @DateTimeFormat 어노테이션의 pattern 속성값에 원하는
	 * 		날짜 형식을 지정할 수 있다.
	 */
	@RequestMapping(value="/registerByGet03",
			method = RequestMethod.POST)
	public String registerByGet03(
			String userId,
			@DateTimeFormat(pattern = "yyyyMMdd") Date dateOfBirth
		) {
		log.info("registerByGet03() 실행...!");
		log.info("userId : " + userId);
		log.info("dateOfBirth : " + dateOfBirth);	
		return "success";
	}
	
	/*
	 * 		7. 폼 방식 요청 정리
	 * 
	 */
	// 1) 폼 텍스트 필드 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다
	@RequestMapping(value="/registerUserId", method = RequestMethod.POST)
	public String registerUserId(String userId) {
		log.info("registerUserId() 실행...!");
		log.info("userId : " + userId);
		return "success";
		
	}
	
	// 2) 폼 아이디 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerMemberUserId", method = RequestMethod.POST)
	public String registerMemberUserId(Member member) {
		log.info("registerMemberUserId() 실행...!");
		log.info("member.userId : " + member.getUserId());
		return "success";
		
	}
	
	
	// 3) 폼 비밀번호 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerPassword", method = RequestMethod.POST)
	public String registerPassword(Member member) {
		log.info("registerPassword() 실행...!");
		log.info("member.password : " + member.getPassword());
		return "success";
		
	}
	
	// 4) 폼 라디오버튼 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerRadio", method = RequestMethod.POST)
	public String registerRadio(String gender) {
		log.info("registerRadio() 실행...!");
		log.info("gender : " + gender);
		return "success";
		
	}
	
	// 5) 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerSelect", method = RequestMethod.POST)
	public String registerSelect(String nationality) {
		log.info("registerSelect() 실행...!");
		log.info("nationality : " + nationality);
		return "success";
		
	}
	// 6) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultiSelect01", method = RequestMethod.POST)
	public String registerMultiSelect01(String cars) {
		log.info("registerMultiSelect01() 실행...!");
		log.info("cars : " + cars);
		return "success";
		
	}
	
	// 7) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultiSelect02", method = RequestMethod.POST)
	public String registerMultiSelect02(String[] carArray) {
		log.info("registerMultiSelect02() 실행...!");
		log.info("carArray.length : " + carArray.length);
		
		if(carArray != null) {
			for(int i = 0; i < carArray.length; i++) {
				log.info("carArray["+i+"] : " + carArray[i]);
			}
		}else {
			log.info("carArray is null");
		}
		return "success";
	}
	
	// 8) 복수 선택이 가능한 폼 셀렉트 박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerMultiSelect03", method = RequestMethod.POST)
	public String registerMultiSelect03(ArrayList<String> carList) {
		log.info("registerMultiSelect03() 실행...!");
		
		// 리스트로는 셀렉트박스 값을 가져올 수 없습니다.
		// 가져오려면 배열 형태를 이용하거나 객체를 이용하여 데이터를 얻어온다.
		
		if(carList != null && carList.size() > 0) {
			log.info("carList.size() : " + carList.size());
			for(int i = 0; i < carList.size(); i++) {
				log.info("carList["+i+"] : " + carList.get(i));
			}
		}else {
			log.info("carList is null");
		}
		return "success";
	}
	
	// 9) 폼 체크박스 요소 값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox01", method = RequestMethod.POST)
	public String registerCheckbox01(String hobby) {
		log.info("registerCheckbox01() 실행...!");
		log.info("hobby : " + hobby);
		return "success";
		
	}
	// 10) 폼 체크박스 요소값을 문자열 배열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox02", method = RequestMethod.POST)
	public String registerCheckbox02(String[] hobbyArray) {
		log.info("registerCheckbox02() 실행...!");
		if(hobbyArray != null){
			log.info("hobbyArray.length : " + hobbyArray.length);
			for (int i = 0; i < hobbyArray.length; i++) {
				log.info("hobbyArray["+i+"] : " + hobbyArray[i]);
			}
		} else {
			log.info("hobbyArray is null");
		}
		return "success";
		
	}
	
	// 11) 폼 체크박스 요소값을 문자열 요소를 가진 리스트 컬렉션 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox03", method = RequestMethod.POST)
	public String registerCheckbox03(ArrayList<String> hobbyList) {
		log.info("registerCheckbox03() 실행...!");
		
		// 받는 타입을 List로 하게 되면 No primary or default constructor found for
		// interface java.util.List 에러가 발생한다.
		// 스프링에서는 List타입으로 데이터를 받는데에 문제가 있다. (데이터 바인딩이 안됨)
		// 리스트와 같은 형태의 값을 받으려면 String[]로 여러 데이터를 받아서 List에 담는 방법이나,
		// 객체를 이용하여 데이터를 바인딩하는 방법이 있다.
		
		if(hobbyList != null){
			log.info("hobbyList.size() : " + hobbyList.size());
			for (int i = 0; i < hobbyList.size(); i++) {
				log.info("hobbyList["+i+"] : " + hobbyList.get(i));
			}
		} else {
			log.info("hobbyList is null");
		}
		return "success";
		
	}
	
	// 12) 폼 체크박스 요소값을 기본 데이터 타입인 문자열 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox04", method = RequestMethod.POST)
	public String registerCheckbox04(String developer) {
		log.info("registerCheckbox04() 실행...!");
		// 값이 존재하면 value 속성 안에 들어있는 값이 넘어오고
		// 값이 존재하지 않으면 null이 넘어온다.
		log.info("developer : " + developer);
		return "success";
		
	}
	
	// 13) 폼 체크박스 요소값을 기본 데이터 타입인 불리언 타입 매개변수로 처리한다.
	@RequestMapping(value="/registerCheckbox05", method = RequestMethod.POST)
	public String registerCheckbox05(boolean foreigner) {
		log.info("registerCheckbox05() 실행...!");
		// 개인정보동의와 같은 기능을 만들 때 사용
		// 체크된 값이 존재하면 value 속성에 설정된 true 값이 넘어오고,
		// 체크된 값이 존재하지 않으면 false가 넘어온다.
		log.info("foreigner : " + foreigner);
		return "success";
		
	}
	
	// 14) 폼 텍스트 필드 요소값을 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerAddress", method = RequestMethod.POST)
	public String registerAddress(Address address) {
		log.info("registerAddress() 실행...!");
		// 개인정보동의와 같은 기능을 만들 때 사용
		// 체크된 값이 존재하면 value 속성에 설정된 true 값이 넘어오고,
		// 체크된 값이 존재하지 않으면 false가 넘어온다.
		log.info("address.postCode : " + address.getPostCode());
		log.info("address.location : " + address.getLocation());
		return "success";
		
	}
	// 15) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerUserAddress", method = RequestMethod.POST)
	public String registerUserAddress(Member member) {
		log.info("registerUserAddress() 실행...!");
		// 개인정보동의와 같은 기능을 만들 때 사용
		// 체크된 값이 존재하면 value 속성에 설정된 true 값이 넘어오고,
		// 체크된 값이 존재하지 않으면 false가 넘어온다.
		
		Address address = member.getAddress();
		
		log.info("address.postCode : " + address.getPostCode());
		log.info("address.location : " + address.getLocation());
		return "success";
		
	}
	
	// 16) 폼 텍스트 필드 요소값을 중첩된 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerUserCardList", method = RequestMethod.POST)
	public String registerUserCardList(Member member) {
		log.info("registerUserCardList() 실행...!");
		
		List<Card> cardList = member.getCardList();
		
		if(cardList != null) {
			log.info("cardList.size() : " + cardList.size());
			
			for (int i = 0; i < cardList.size(); i++) {
				Card card = cardList.get(i);
				log.info("card.no : " + card.getNo());
				log.info("card.validMonth : " + card.getValidMonth());
			}
		}else {
			log.info("cardList is null");
		}
		return "success";
		
	}
	
	// 문제) 회원가입에 필요한 전체 폼 페이지 요청 양식
	@RequestMapping(value="/registerAllForm", method = RequestMethod.GET)
	public String registerAllForm() {
		log.info("registerAllForm() 실행...!");
		
		return "member/registerAllForm";
	}
	
	// 문제) 회원가입 전체 폼 페이지에서 입력받은 데이터를 받아서 결과를 출력해주세요.
	@RequestMapping(value="/registerUser", method = RequestMethod.POST)
	public String registerUser(RegisterMemberVO user, Model model) {
		// 전체 폼페이지에서 넘겨받은 데이터 모두를 콘솔에 출력 후,
		// member 폴더 아래에 있는 result 페이지로 전달 후 넘겨 받은 모든 데이터를 출력해주세요.
		// result 페이지에서 넘긴 모든 데이터는 영문으로 된 값 그대로 를 출력하는게 아니라
		// 한글로 바꿔서 출력해주세요.
		// - input 요소에 value로 설정되어 있는 값은 한글이 아닌 영문으로 설정되어 있어야합니다.
		// - ex) 성별 : 남자
		//		  국적 : 대한민국
		//       개발자 여부 : 개발자 / 일반인
		//       외국인 여부 : 내국인 / 외국인
		//       소유차량 : BMW, VOLVO
		//       취미 : 운동, 영화, 음악
		//       그 외 모든 데이터~~
		//
		// 콘솔에서도 출력해주고, result 결과페이지에서도 출력해주세요!
		// member/result 페이지로 이동해서 출력하는데 JSTL을 활용하여 EL표현문으로 데이터를 출력!
		// table 태그를 활용하던, div를 활용하던 형식은 자유롭게
		
		log.info("유저 ID : " + user.getUserId());
		log.info("패스워드 : " + user.getPassword());
		log.info("이름 : " + user.getName());
		log.info("E-Mail : " + user.getEmail());
		log.info("생년월일 : " + user.getBirth());
		log.info("성별" + user.getGender());
		log.info("개발자 여부" + user.getDeveloper());
		log.info("외국인 여부" + user.getForeigner());
		log.info("국적" + user.getNational());
		log.info("소유차량 : " + user.getCars());
		log.info("취미 : " + user.getHobby());
		log.info("우편번호 : " + user.getAddress().getPostCode());
		log.info("주소 : " + user.getAddress().getLocation());
		List<Card> cardList = user.getCardList();
		if(cardList != null) {
			for (int i = 0; i < cardList.size(); i++) {
				Card card = cardList.get(i);
				log.info("카드 "+ (i+1) +"번호"+card.getNo());
				log.info("카드 "+ (i+1) +"유효년월" + card.getValidMonth());
			} 
		} else {
			log.info("cardList is NULL");
		}
		log.info("소개 : " + user.getIntroduction());
		
		model.addAttribute("user", user);
		return "member/registerAllResult";
	}
	
	/*
	 * 		8. 파일업로드 폼 방식 요청 처리
	 * 		- 파일 업로드 폼 방식 요청 처리를 위한 의존 라이브러리 추가
	 * 		- pom.xml 내, commons-fileupload, commons-io 라이브러리 의존관계 등록
	 * 		- web.xml에 모든 경로에 대한 MultipartFilter를 등록
	 */
	// 3) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 매개변수와 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile03", method = RequestMethod.POST)
	public String registerFile03(Member member, MultipartFile picture) {
		log.info("registerFile03 실행...!");
		log.info("userId : " + member.getUserId());
		log.info("password : " + member.getPassword());
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "success";
	}
	
	// 4) 파일 업로드 폼 파일 요소값과 텍스트 필드 요소값을 FileMember 타입의 자바빈즈 매개변수로 처리한다.
	@RequestMapping(value="/registerFile04", method = RequestMethod.POST)
	public String registerFile04(FileMember fileMember) {
		log.info("registerFile04 실행...!");
		log.info("userId : " + fileMember.getUserId());
		log.info("password : " + fileMember.getPassword());
		
		MultipartFile picture = fileMember.getPicture();
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		return "success";
	}
	
	// 5) 여러개의 파일업로드를 폼 파일 요소값을 여러 개의 MultipartFile 매개변수로 처리한다.
	@RequestMapping(value="/registerFile05", method = RequestMethod.POST)
	public String registerFile05(MultipartFile picture, MultipartFile picture2) {
		log.info("registerFile05 실행...!");
		
		log.info("originalName : " + picture.getOriginalFilename());
		log.info("size : " + picture.getSize());
		log.info("contentType : " + picture.getContentType());
		
		log.info("originalName2 : " + picture2.getOriginalFilename());
		log.info("size2 : " + picture2.getSize());
		log.info("contentType2 : " + picture2.getContentType());
		return "success";
	}
	
	// 6) 여러개의 파일업로드를 폼 파일 요소값을 MultipartFile 타입의 요소를 가진 리스트 컬렉션 타입
	//	매개변수로 처리한다.
	// ::: 기본 타입 컬렉션 리스트로는 파일 데이터를 받을 수 없다. XXXXXXXXXXX
	@RequestMapping(value="/registerFile06", method = RequestMethod.POST)
	public String registerFile06(ArrayList<MultipartFile> pictureList) {
		log.info("registerFile06 실행...!");
		
		for(int i = 0; i < pictureList.size(); i++) {
			MultipartFile picture = pictureList.get(i);
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
		}
		
		return "success";
	}
	
	// 7) 여러개의 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultiFileMember 타입의 자바빈즈
	//	매개변수로 처리한다.(리스트 형태 안됨 무조건 객체형태로 가져올것)
	@RequestMapping(value="/registerFile07", method = RequestMethod.POST)
	public String registerFile07(MultiFileMember multiFileMember) {
		log.info("registerFile07 실행...!");
		
		List<MultipartFile> pictureList = multiFileMember.getPictureList();
		
		for(int i = 0; i < pictureList.size(); i++) {
			MultipartFile picture = pictureList.get(i);
			log.info("originalName : " + picture.getOriginalFilename());
			log.info("size : " + picture.getSize());
			log.info("contentType : " + picture.getContentType());
		}
		
		return "success";
	}
	
	// 8) 파일업로드 폼 파일 요소값과 텍스트 필드 요소값을 MultipartFile 타입의 배열 매개변수로 처리한다.
	@RequestMapping(value="/registerFile08", method = RequestMethod.POST)
	public String registerFile08(MultipartFile[] pictureArray) {
		log.info("registerFile08 실행...!");
		
		for(int i = 0; i < pictureArray.length; i++) {
			log.info("originalName : " + pictureArray[i].getOriginalFilename());
			log.info("size : " + pictureArray[i].getSize());
			log.info("contentType : " + pictureArray[i].getContentType());
		}
		
		return "success";
	}
	
}	

