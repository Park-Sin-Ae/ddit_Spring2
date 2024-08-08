package kr.or.ddit.controller.crud.notice;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.service.INoticeService;
import kr.or.ddit.vo.crud.NoticeVO;
import kr.or.ddit.vo.crud.PaginationInfoVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/notice")
public class NoticeRetrieveController {

	@Inject
	private INoticeService noticeService;
	
	@Inject
	PasswordEncoder pw;
	
	@PostConstruct
	public void init() {
		log.info("### : " + pw.encode("1234"));
	}
	
	// 공지사항 목록
	// 스프링 시큐리티 적용(ROLE_MEMBER, ROLE_ADMIN 역할명만 접근 가능)
	@PreAuthorize("hasAnyRole('ROLE_MEMBER', 'ROLE_ADMIN')")
    @RequestMapping(value="/list.do", method = RequestMethod.GET)
    public String noticeList(
          @RequestParam(name="page", required = false, defaultValue ="1") int currentPage,
          @RequestParam(required = false, defaultValue = "title") String searchType,
          @RequestParam(required = false) String searchWord,
          Model model
          ) {
       log.info("noticeList() 실행...!");

       PaginationInfoVO<NoticeVO> pagingVO = new PaginationInfoVO<NoticeVO>();
       
       // 검색 기능 추가
       if(StringUtils.isNotBlank(searchWord)) {
    	   pagingVO.setSearchType(searchType);
    	   pagingVO.setSearchWord(searchWord);
    	   
    	   // 검색 후, 목록 페이지로 이동 할 때 검색된 내용을 적용시키기 위한 데이터 전달
    	   model.addAttribute("searchType", searchType);
    	   model.addAttribute("searchWord", searchWord);
       }
       // 총 4가지의 필드를 설정하기 위해  currentPage 셋팅
       // 현재 페이지를 전달 후, start/endRow, start/endPage 설정
       pagingVO.setCurrentPage(currentPage);
       // 총 게시글 수를 얻어온다.
       int totalRecord = noticeService.selectNoticeCount(pagingVO);
       // 총 게시글 수를 전달 후, 총 페이지 수를 설정
       pagingVO.setTotalRecord(totalRecord);
       // 총 페이지 수가 결정 되었다면
       // 총 게시글 수가 포함된 PaginationInfoVO객체를 넘겨주고 
       // 1페이지에 해당하는 10개(screenSize)만큼의 게시글 데이터를 갖고 온다.(dataList)
       List<NoticeVO> dataList = noticeService.selectNoticeList(pagingVO);
       pagingVO.setDataList(dataList);
       
       model.addAttribute("pagingVO", pagingVO);
       
       return "notice/list";
    }

 
	 @RequestMapping(value = "/detail.do", method = RequestMethod.GET)
	 public String noticeDetail(int boNo, Model model) {
	    log.info("noticeDetail() 실행...");
	    NoticeVO noticeVO = noticeService.selectNotice(boNo);
	    model.addAttribute("notice", noticeVO);
	    return "notice/detail";
	    
	 }

}
