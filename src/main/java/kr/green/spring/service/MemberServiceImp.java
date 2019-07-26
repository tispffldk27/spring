package kr.green.spring.service;

import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.green.spring.dao.MemberDAO;
import kr.green.spring.vo.MemberVO;

@Service
public class MemberServiceImp implements MemberService {

	@Autowired
	MemberDAO memberDao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;
	@Autowired
	private JavaMailSender mailSender;
	
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
		//회원 가입창에서 입력받은 암호를 암호화 시킴
		String encodePw = passwordEncoder.encode(mVo.getPw());
		//회원 정보의 비밀번호를 암호화된 비밀번호로 변경
		mVo.setPw(encodePw);
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
		//수정
		if(passwordEncoder.matches(mVo.getPw(), oVo.getPw()))
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

	@Override
	public boolean isMember(String id) {
		if(memberDao.getMember(id)== null) {
			return false;
		}
		return true;
	}

	@Override
	public String getVal(String id) {
		String [] arr = id.split("=");
		if(arr.length == 2)
		return arr[1];
		else 
			return "";
	}

	@Override
	public boolean checkMember(String id, String email) {
		MemberVO user = memberDao.getMember(id);
		if(user != null && user.getEmail().equals(email)) {
			return true;
		}
		return false;
	}

	@Override
	public String createPw() {
		String str= "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
		String pw="";
		for(int i=0; i< 8 ;i++) {
			int r= (int)(Math.random()*62);
			pw += str.charAt(r);
		}
		return pw;
	}

	@Override
	public void modify(String id, String email, String newPw) {
		MemberVO user = memberDao.getMember(id);
		if(user == null)	return;
		if(!user.getEmail().equals(email))	return;
		String encodePw = passwordEncoder.encode(newPw);
		user.setPw(encodePw);
		memberDao.modify(user);
	}

	@Override
	public void sendMail(String email, String title, String contents) {
		 String setfrom = "tispffldk26@gmail.com";         
		    
		  
		    try {
		        MimeMessage message = mailSender.createMimeMessage();
		        MimeMessageHelper messageHelper 
		            = new MimeMessageHelper(message, true, "UTF-8");

		        messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
		        messageHelper.setTo(email);     // 받는사람 이메일
		        messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
		        messageHelper.setText(contents);  // 메일 내용

		        mailSender.send(message);
		    } catch(Exception e){
		        System.out.println(e);
		    }
		
	}
}
