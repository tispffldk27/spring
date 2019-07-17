package kr.green.spring.dao;

import kr.green.spring.vo.MemberVO;

public interface MemberDAO {

	MemberVO getMember(String id);

	void signup(MemberVO mVo);

	void modify(MemberVO mVo);
	
}
