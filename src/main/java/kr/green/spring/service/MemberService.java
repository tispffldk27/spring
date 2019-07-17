package kr.green.spring.service;

import kr.green.spring.vo.MemberVO;


public interface MemberService {
	public boolean signup(MemberVO mVo);

	public MemberVO signin(MemberVO mVo);

	public boolean modify(MemberVO mVo, String oldPw);
}
