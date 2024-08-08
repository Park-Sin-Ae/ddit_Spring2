package kr.or.ddit.controller.crud;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.inject.Inject;

import org.springframework.aop.support.AopUtils;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/crud/board")
public class CrudBoardController {
	
	@Inject
	private IBoardService service;
	
	// @PostConstruct는 런타임 단계에서 초기화를 수행하는 메소드이다.
	// 런타임 후 빈이 등록되고 초기화하는 과정에서 생성자를 초기화하거나 값을 확인하고자 할 때 사용한다.
	@PostConstruct
	public void init() {
		// AopProxy는 인터페이스 기반의 프록시를 생성한 Dynamic Proxy를 사용하여 위빙을 지정합니다.
		// - 인터페이스를 구현하고 해당 인터페이스를 참조해서 구현한 클래스를 사용하는 형태여야 한다.(Service, Impl)
		// aopProxy 상태 확인(interface 기반의 프록시면 true)
		log.info("### aopProxy 상태(interface 기반) : {}", AopUtils.isAopProxy(service));
		// 인터페이스 기반이 아닌 클래스 기반의 프록시를 생성한 Cglib Proxy를 사용하여 위빙을 지정한다.
		// - 인터페이스 구현 없이 클래스로만 사용되는 형태
		// aopProxy 상태 확인(클래스 상속 기반의 프록시면 true)
		log.info("### aopProxy 상태(클래스 상속 기반) : {}", AopUtils.isCglibProxy(service));
	}
	
	@RequestMapping(value="/register", method = RequestMethod.GET)
	public String crudRegisterForm() {
		log.info("crudRegisterForm() 실행...!");
		return "crud/register";
	}
	
	@RequestMapping(value="/register", method = RequestMethod.POST)
	public String crudRegister(Board board, Model model) {
		log.info("crudRegister() 실행...!");
		service.register(board);
		model.addAttribute("msg", "등록이 완료되었습니다!");
		return "crud/success";
	}
	
	@RequestMapping(value="/list", method = RequestMethod.GET)
	public String crudList(Model model) {
		log.info("crudList() 실행...!");
		List<Board> boardList = service.list();
		model.addAttribute("boardList", boardList);
		return "crud/list";
	}
	
	@RequestMapping(value="/read", method = RequestMethod.GET)
	public String crudRead(int boardNo, Model model) throws Exception {
		log.info("crudRead() 실행...!");
		Board board = service.read(boardNo);
		model.addAttribute("board", board);
		return "crud/read";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.GET)
	public String crudModifyForm(int boardNo, Model model) throws Exception {
		Board board = service.read(boardNo);
		model.addAttribute("board", board);
		model.addAttribute("status", "u");
		return "crud/register";
	}
	
	@RequestMapping(value="/modify", method = RequestMethod.POST)
	public String crudModify(Board board, Model model) {
		log.info("crudModify() 실행...!");
		service.update(board);
		model.addAttribute("msg", "수정이 완료되었습니다.");
		return "crud/success";
	}
	
	@RequestMapping(value="/remove", method = RequestMethod.POST)
	public String crudElete(int boardNo, Model model) {
		log.info("crudModify() 실행...!");
		service.delete(boardNo);
		model.addAttribute("msg", "삭제가 완료되었습니다!");
		return "crud/success";
	}
	
	@RequestMapping(value="/search", method = RequestMethod.POST)
	public String crudSearch(String title, Model model) {
		log.info("crudSearch() 실행...!");
		Board board = new Board();
		board.setTitle(title);
		
		List<Board> boardList = service.search(board);
		model.addAttribute("boardList", boardList);
		model.addAttribute("board", board);
		return "crud/list";
	}
		
}
