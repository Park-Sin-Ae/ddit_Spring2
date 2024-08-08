package kr.or.ddit.controller.crud;

// 사용자 정의 에러 출력 컨트롤러
public class BoardRecordNotFoundException extends Exception {

	public BoardRecordNotFoundException(String msg) {
		// 부모인 Exception으로 사용자가 정의한 메세지를 전달한다.
		super(msg);
	}
}
