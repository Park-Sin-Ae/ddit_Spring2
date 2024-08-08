package kr.or.ddit.controller.crud.notice;

import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.ServiceResult;
import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.CustomUser;
import kr.or.ddit.vo.crud.NoticeMemberVO;
import kr.or.ddit.vo.crud.NoticeVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeInsertController {
	
	@Inject
	private INoticeService noticeService;
	
	@RequestMapping(value="/form.do", method = RequestMethod.GET)
	public String noticeForm() {
		log.info("noticeForm() 실행...!");
		return "notice/form";
	}
	
	@RequestMapping(value="/insert.do", method = RequestMethod.POST)
	public String insertNotice(
			HttpServletRequest req, RedirectAttributes ra,
			NoticeVO noticeVO, Model model) {
		String goPage = "";		// 이동할 페이지 정보
		
		// 넘겨받은 데이터 검증 후, 에러가 발생한 데이터에 대한 에러정보를 담을 공간
		Map<String, String> errors = new HashMap<String, String>();
		if(StringUtils.isBlank(noticeVO.getBoTitle())) {	// 제목 데이터가 누락되었을 때
			errors.put("boTitle", "제목을 입력해주세요!");
		}
		if(StringUtils.isBlank(noticeVO.getBoContent())) {		// 내용 데이터가 누락되었을 때
			errors.put("boContent", "내용을 입력해주세요!");
		}
		
		// 기본 데이터의 누락정보에 따른 에러 정보 갯수에 따른 처리
		if(errors.size() > 0) {
			model.addAttribute("errors", errors);
			model.addAttribute("noticeVO", noticeVO);
			goPage = "notice/form";
		}else {		// 에러가 없을 때
			// 첫 번째 방법)
			// 로그인 세션 정보안에서 아이디를 꺼내서 작성자를 설정한다.
//			HttpSession session = req.getSession();
//			NoticeMemberVO memberVO = (NoticeMemberVO) session.getAttribute("SessionInfo");
			
			// 두번째 방법) 시큐리티 인증 시 
			CustomUser user =
					(CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			NoticeMemberVO memberVO = user.getMember();
			
			
			// 등록 기능은 로그인 한 사용자 이외에는 사용 불가
			if(memberVO != null) {
				noticeVO.setBoWriter(memberVO.getMemId());
				ServiceResult result = noticeService.insertNotice(req, noticeVO);
				if(result.equals(ServiceResult.OK)) {		// 등록 성공
					goPage = "redirect:/notice/detail.do?boNo="+noticeVO.getBoNo();
				}else {		// 등록 실패
					model.addAttribute("noticeVO", noticeVO);
					model.addAttribute("message", "서버에러, 다시 시도해주세요!");
					goPage = "notice/form";
				}
			}else {
				ra.addFlashAttribute("message", "로그인 후에 사용 가능합니다.");
				goPage = "redirect:/notice/login.do";
			}
		}
		return goPage;
	}
	
}

