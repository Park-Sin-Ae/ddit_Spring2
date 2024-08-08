package kr.or.ddit.controller.crud.notice;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.crud.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeModifyController {

	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/update.do", method = RequestMethod.GET)
	public String noticeModifyForm(int boNo, Model model) {
		log.info("noticeModifyForm() 실행...!");
		NoticeVO noticeVO = noticeService.selectNotice(boNo);
		model.addAttribute("notice", noticeVO);
		model.addAttribute("status", "u");		// flag
		return "notice/form";
	}
	
	@RequestMapping(value="/update.do", method = RequestMethod.POST)
	public String noticeModify(
			HttpServletRequest req, RedirectAttributes ra,
			NoticeVO noticeVO, Model model) {
		String goPage = "";
		
		ServiceResult result = noticeService.updateNotice(req, noticeVO);
		if(result.equals(ServiceResult.OK)) {		// 수정 성공
			ra.addFlashAttribute("message", "게시글 수정 완료!");
			goPage = "redirect:/notice/detail.do?boNo="+noticeVO.getBoNo();
		}else {		// 수정 실패
			model.addAttribute("status", "u");
			model.addAttribute("notice", noticeVO);
			model.addAttribute("message", "수정에 실패하였습니다!");
			goPage = "notice/form";
		}
		
		return goPage;
	}
	
	  @RequestMapping(value="/delete.do", method = RequestMethod.POST)
	   public String noticeDelete(
			   HttpServletRequest req, RedirectAttributes ra,
			   int boNo, Model model) {
	      String goPage = "";
	      
	      ServiceResult result = noticeService.deleteNotice(req, boNo);
	      if(result.equals(ServiceResult.OK)) {   // 삭제 성공
	         goPage = "redirect:/notice/list.do";
	      }else {
	         goPage = "redirect:/notice/detail.do?boNo=" + boNo;
	      }
	      
	      return goPage;
	   }

}
