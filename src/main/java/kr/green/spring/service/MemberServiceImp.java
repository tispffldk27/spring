package kr.green.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.spring.dao.MemberDAO;
import kr.green.spring.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	MemberDAO memberDao;
	
	@Override
	public boolean signup(MemberVO mVo) {
		// 기존에 해당 아이디기 있는지 체크
		// 있으면 false 반환하고 종료
		// 없으면 회원가입 진행
		if(mVo == null)
			return false;
		mVo.setName("");
		if(memberDao.getMember(mVo.getId()) != null)
			return false;
		memberDao.signup(mVo);
		return true;
	}

	@Override
	public MemberVO signin(MemberVO mVo) {
		if(mVo == null)//예외처리
			return null;
		MemberVO oVo = memberDao.getMember(mVo.getId());
		if(oVo == null)
			return null;
		if(oVo.getPw().equals(mVo.getPw()))
			return oVo;
		return null;
	}

	@Override
	public boolean modify(MemberVO mVo, String oldPw) {
		if(mVo == null)	
			return false;
		if(memberDao.getMember(mVo.getId()).getPw().equals(oldPw)) {
			memberDao.modify(mVo);
			return true;
		}
		return false;
	}
}
