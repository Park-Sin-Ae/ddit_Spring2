package kr.or.ddit.controller.test.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import kr.or.ddit.controller.test.vo.StudentVO;

@Repository
public class Test03Repository {
	
	// 305호 학생 정보를 담을 리스트
	private List<StudentVO> studentList = new ArrayList<StudentVO>();
	
	public Test03Repository() {
		// StudentVO를 활용하여 305호 학생 5명을 초기화해주세요!
		// # 첫 번쨰 학생
		// 아이디 : a001
		// 비밀번호 : 1234
		// 이름 : 홍길동
		// 핸드폰번호 : 010-1234-1234
		// 이메일 : a001@naver.com 과 같은 데이터로 학생 총 5명의 데이터를 초기화 한 후,
		// 학생 정보를 담을 리스트에 추가해주세요!
		StudentVO student1 = new StudentVO();
		student1.setMemId("a001");
		student1.setMemPw("1234");
		student1.setMemName("홍길동");
		student1.setMemPhone("010-1234-1234");
		student1.setMemEmail("a001@naver.com");
		
		StudentVO student2 = new StudentVO();
		student2.setMemId("a002");
		student2.setMemPw("1234");
		student2.setMemName("조민지");
		student2.setMemPhone("010-2222-2222");
		student2.setMemEmail("a002@gmail.com");
		
		StudentVO student3 = new StudentVO();
		student3.setMemId("a003");
		student3.setMemPw("1234");
		student3.setMemName("조서연");
		student3.setMemPhone("010-3333-3333");
		student3.setMemEmail("a003@naver.com");
		
		StudentVO student4 = new StudentVO();
		student4.setMemId("a004");
		student4.setMemPw("1234");
		student4.setMemName("김현경");
		student4.setMemPhone("010-4444-4444");
		student4.setMemEmail("a004@gmail.com");
		
		StudentVO student5 = new StudentVO();
		student5.setMemId("a005");
		student5.setMemPw("1234");
		student5.setMemName("박신애");
		student5.setMemPhone("010-5555-5555");
		student5.setMemEmail("a005@gmail.com");
		
		studentList.add(student1);
		studentList.add(student2);
		studentList.add(student3);
		studentList.add(student4);
		studentList.add(student5);
		
	}
	
	// 기능 구현
	// 1) 학생 전체 가져오기
	public List<StudentVO> selectAllStudent(){
		return studentList;
	}
	
	// 2) 학생 한명 정보 가져오기
	public StudentVO selectStudent(String memId){
		StudentVO student = new StudentVO();
		
		for (StudentVO std : studentList) {
			if(student.getMemId().equals(memId)) {
				student = std;
			}
		}
		return student;
	}
	
	// 3) 이름, 이메일 정보를 활용해 학생 아이디 가져오기
	public String findId(String name, String email) {
		String id = "일치하는 정보가 없습니다.";
		
		for (StudentVO std : studentList) {
			if(std.getMemName().equals(name) && std.getMemEmail().equals(email)) {
				id = std.getMemId();
			}
		}
		
		return id;
	}
	
	// 4) 아이디, 이름, 이메일 정보를 활용해 학생 비밀번호 가져오기
	public String findPw(String id, String name, String email) {
		String pw = "일치하는 정보가 없습니다.";
		
		for (StudentVO std : studentList) {
			if(std.getMemId().equals(id) && std.getMemName().equals(name) && std.getMemEmail().equals(email)) {
				pw = std.getMemPw();
			}
		}
		
		return pw;
	}
	// 등등 필요에 의한 기능을 구현해주세요!
	// 위 4가지 기능은 필수!
}


