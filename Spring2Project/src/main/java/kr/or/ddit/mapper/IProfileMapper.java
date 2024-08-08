package kr.or.ddit.mapper;

import kr.or.ddit.vo.crud.NoticeMemberVO;

public interface IProfileMapper {
	public NoticeMemberVO selectMember(String memId);

	public int profileUpdate(NoticeMemberVO memberVO);
}
