package kr.green.spring.service;

import kr.green.spring.vo.MemberVO;


public interface MemberService {
	public boolean signup(MemberVO mVo);

	public MemberVO signin(MemberVO mVo);

	public boolean modify(MemberVO mVo, String oldPw);

	public boolean isMember(String id);

	public String getVal(String id);

	public boolean checkMember(String id, String email);

	public String createPw();

	public void modify(String id, String email, String newPw);

	public void sendMail(String email, String title, String contents);
	
}
