package kr.or.ddit.vo.test;

import java.util.Date;
import java.util.List;

import kr.or.ddit.vo.Address;
import kr.or.ddit.vo.Card;
import lombok.Data;

@Data
public class RegisterMemberVO {
	private String userId;
	private String password;
	private String name;
	private String email;
	private	Date birth;
	private String gender;
	private String developer;
	private String foreigner;
	private String[] national;
	private String[] cars;
	private String[] hobby;
	private String introduction;
	
	
	private Address address;
	private List<Card> cardList;
}
