package kr.or.ddit.controller.crud.notice;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.View;

import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.crud.NoticeFileVO;

@Controller
@RequestMapping("/notice")
public class NoticeDownloadController {
	
	@Inject
	private INoticeService noticeService;
	
	// View타입은 String 타입과 같다.
	// 파일 다운로드
	@RequestMapping(value="/download.do", method = RequestMethod.GET)
	public View NoticeDownload(int fileNo, ModelMap model) {
		// 선택한 파일을 다운로드 하기 위한 정보로 파일번호에 해당하는 파일 정보를 얻어온다.
		NoticeFileVO noticeFileVO = noticeService.noticeDownload(fileNo);
		
		// 데이터 전달자를 통해서 파일정보를 전달하기 위한 Map 선언
		Map<String, Object> noticeFileMap = new HashMap<String, Object>();
		noticeFileMap.put("fileName", noticeFileVO.getFileName());		// 파일명 추가
		noticeFileMap.put("fileSize", noticeFileVO.getFileSize());		// 파일 크기 추가
		noticeFileMap.put("fileSavepath", noticeFileVO.getFileSavepath());	// 파일 저장경로 추가
		model.addAttribute("noticeFileMap", noticeFileMap);		// 데이터 전달자로 전달
		
		// 리턴되는 noticeDownloadView는 jsp페이지로 존재하는 페이지 Name을 요청하는게 아니라,
		// 클래스를 요청하는 것인데 해당 클래스가 스프링에서 제공하는 AbstractView 클래스를 상속받은 클래스인데
		// 그 클래스는 AbstractView를 상속받아 renderMergeOutputModel 함수를 재정의 할 때 View 로 취굽될 수 있게함
		// 헤이헤이!! 이거 클래스 아니고 뷰임 뷰!!
		return new NoticeDownloadView();
	}
}
