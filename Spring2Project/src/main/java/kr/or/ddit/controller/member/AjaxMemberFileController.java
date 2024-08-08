package kr.or.ddit.controller.member;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/ajax")
public class AjaxMemberFileController {
	/*
	 *		10. 파일 업로드 Ajax 방식 요청 처리 
	 */
	@RequestMapping(value="/registerFileForm", method = RequestMethod.GET)
	public String ajaxRegisterFileForm() {
		log.info("ajaxRegisterFileForm() 실행...!");
		return "member/ajaxRegisterFile";
	}

	@ResponseBody
	@RequestMapping(value="/uploadAjax", method = RequestMethod.POST)
	public ResponseEntity<String> uploadAjax(MultipartFile file){
		// formData로 넘어오는 데이터를 객체로 받을 때는 @RequestBody를 붙이지 않고 객체만 선언한다.
		// 단일 파일 데이터를 받을 떄에는 MultiapartFile file 또는 @RequestBody MultipartFile file로도
		// 데이터를 받을 수 있다.
		log.info("uploadAjax() 실행...!");
		log.info("originalFileName : " + file.getOriginalFilename());
		return new ResponseEntity<String>("UPLOAD SUCCESS", HttpStatus.OK); 
	}
}
