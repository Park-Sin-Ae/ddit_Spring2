package kr.or.ddit.service.impl;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import kr.or.ddit.controller.crud.BoardRecordNotFoundException;
import kr.or.ddit.mapper.IBoardMapper;
import kr.or.ddit.service.IBoardService;
import kr.or.ddit.vo.Board;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BoardServiceImpl implements IBoardService {

	@Inject
	private IBoardMapper mapper;
	
	@Override
	public void register(Board board) {
		log.info("service register() 실행...!");
		mapper.create(board);

	}

	@Override
	public List<Board> list() {
		log.info("service list() 실행...!");
		return mapper.list();
	}

	@Override
	public Board read(int boardNo) throws Exception {
		log.info("service read() 실행...!");
		
		Board board = mapper.read(boardNo);
		
		// 게시판의 글이 존재하지 않으면 사용자가 정의한 예외를 발생시킨다.
		if(board == null) {
			throw new BoardRecordNotFoundException("Not Found boardNo=" + boardNo);
		}
		
		return board;
	}

	@Override
	public void update(Board board) {
		log.info("service update() 실행...!");
		mapper.update(board);
		
	}

	@Override
	public void delete(int boardNo) {
		log.info("service delete() 실행...!");
		mapper.delete(boardNo);
	}

	@Override
	public List<Board> search(Board board) {
		log.info("service search() 실행...!");
		return mapper.search(board);
	}

}
