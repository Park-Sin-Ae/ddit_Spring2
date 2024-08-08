package kr.or.ddit.controller.test;

import java.util.List;

import javax.inject.Inject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.controller.test.dao.Test03Repository;
import kr.or.ddit.controller.test.vo.StudentVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/test03")
public class TestController03 {
	
	@Inject
	Test03Repository dao;
	
	// 로그인 페이지
	@RequestMapping(value="/login.do", method = RequestMethod.GET)
	public String loginPage() {
		return "test/test03/login";
	}
	
	// 로그인 성공여부
	@RequestMapping(value="/loginChk.do", method = RequestMethod.POST)
	public String loginChk(String memId, String memPw, Model model) {
		log.info("memId : " + memId);
		log.info("memPw : " + memId);
		List<StudentVO> list = dao.selectAllStudent();
		
		for (StudentVO student : list) {
			if(student.getMemId().equals(memId) && student.getMemPw().equals(memPw)) {
				model.addAttribute("memId", memId);
				model.addAttribute("msg", "Y");
				model.addAttribute("list", list);
				log.info("로그인 성공!!!!!!");
				return "test/test03/info";
			}
		}
		
		model.addAttribute("msg", "N");
		log.info("실패@@@@@@@@@########");
		return "test/test03/login";
	}
	
	@RequestMapping(value = "/findInfo.do", method = RequestMethod.GET)
	public String findInfo() {
		return "test/test03/findInfo";
	}
	
	
	// 아이디/비밀번호 찾기 페이지
	@RequestMapping(value = "/findId.do", method = RequestMethod.POST)
	public ResponseEntity<String> findId(@RequestBody StudentVO vo) {
		String result = "";
		List<StudentVO> studentList = dao.selectAllStudent();
		log.info("@@ vo : " + vo);
		for (StudentVO std : studentList) {
			if (std.getMemName().equals(vo.getMemName()) && std.getMemEmail().equals(vo.getMemEmail())) {
				result = std.getMemId();
			}
		}
		
		return new ResponseEntity<String>(result , HttpStatus.OK);
	}
	
	@RequestMapping(value="/findPw.do", method = RequestMethod.POST)
	public ResponseEntity<String> findPw(@RequestBody StudentVO vo) {
		String result = "";
		log.info("@@ vo : " + vo);
		List<StudentVO> studentList = dao.selectAllStudent();
		
		for(StudentVO std : studentList) {
			if(std.getMemId().equals(vo.getMemId()) 
					&& std.getMemName().equals(vo.getMemName())
					&& std.getMemEmail().equals(vo.getMemEmail())) {
				result = std.getMemPw();
			}
		}
		
		return new ResponseEntity<String>(result , HttpStatus.OK);
	}
	
//	// 로그인 후 정보 페이지
//	@RequestMapping(value="/info.do", method = RequestMethod.GET)
//	public String info(StudentVO std, Model model, String memId, String memPw) {
//		
//		model.addAttribute("std", std);
//		return "test/test03/info";
//	}
	
	
}
