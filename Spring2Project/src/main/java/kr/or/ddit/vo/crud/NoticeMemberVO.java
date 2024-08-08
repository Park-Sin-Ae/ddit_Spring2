package kr.or.ddit.vo.crud;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeMemberVO {
	private int memNo;
	private String memId;
	private String memPw;
	private String memName;
	private String memGender;
	private String memEmail;
	private String memPhone;
	private String memPostcode;
	private String memAddress1;
	private String memAddress2;
	private String memAgree;
	private String memProfileimg;
	private String memRegdate;
	private String enabled;
	
	private MultipartFile imgFile;
	private List<NoticeMemberAuthVO> authList;
}
