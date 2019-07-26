package kr.green.spring.controller;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import javax.mail.internet.MimeMessage;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.green.spring.service.MemberService;
import kr.green.spring.vo.MemberVO;

/**
 * Handles requests for the application home page.
 */
//컨트롤러 어노테이션으로 @Controller가 붙으면 컨트롤러로 인식
@Controller
public class HomeController {
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	private JavaMailSender mailSender;
	
	//서버부분을 제외한 URL이 /이고, 방식이 get이면 home 메소드를 실행
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model) {
		logger.info("메인페이지 실행");
	
		return "home";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.GET)
	public String signupGet(Model model) {
		logger.info("회원가입페이지 실행");
		
		return "signup";
	}
	@RequestMapping(value = "/signup", method = RequestMethod.POST)
	public String signupPost(MemberVO mVo) {
		logger.info("회원가입 진행중");
		
		if(memberService.signup(mVo))
			return "redirect:/";
		else
			return "redirect:/signup";
	}
	@RequestMapping(value = "/signin", method = RequestMethod.GET)
	public String signinGet(Model model) {
		logger.info("로그인페이지 실행");
		return "signin";
	}
	@RequestMapping(value = "/signin", method = RequestMethod.POST)
	public String signinPost(Model model, MemberVO mVo) {
		logger.info("로그인 진행중");
		MemberVO user = memberService.signin(mVo);
		if(user != null) {
			model.addAttribute("user", user);
			return "redirect:/";
		}
		return "redirect:/signin";
	}
	@RequestMapping(value = "/member/modify", method = RequestMethod.GET)
	public String memberModifyGet() {
		logger.info("회원정보수정페이지 실행");
		return "member/modify";
	}
	@RequestMapping(value = "/member/modify", method = RequestMethod.POST)
	public String memberModifyPost(MemberVO mVo, String oldPw) {
		logger.info("회원정보수정 진행 중");
		if(memberService.modify(mVo,oldPw)) {
			return "redirect:/";
		}
		return "redirect:/member/modify";
	}
	@RequestMapping(value="/signout")
	public String signout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/";
	}
	
	@RequestMapping(value ="/dup")
	@ResponseBody
	public Map<Object, Object> idcheck(@RequestBody String id){

	    Map<Object, Object> map = new HashMap<Object, Object>();
	    //변수 id에 저장된 아이디가 회원 아이디인지 아닌지 확인하여 isMember변수에 담아보낸다.
	    
	    boolean isMember = memberService.isMember(id);
	    map.put("isMember", isMember);
	    return map;
	}
	@RequestMapping(value = "/mail/mailForm")
	public String mailForm() {

	    return "mail";
	}  

	// mailSending 코드
	@RequestMapping(value = "/mail/mailSending")
	public String mailSending(HttpServletRequest request) {

	    String setfrom = "stajun@naver.com";         
	    String tomail  = request.getParameter("tomail");     // 받는 사람 이메일
	    String title   = request.getParameter("title");      // 제목
	    String content = request.getParameter("content");    // 내용

	    try {
	        MimeMessage message = mailSender.createMimeMessage();
	        MimeMessageHelper messageHelper 
	            = new MimeMessageHelper(message, true, "UTF-8");

	        messageHelper.setFrom(setfrom);  // 보내는사람 생략하거나 하면 정상작동을 안함
	        messageHelper.setTo(tomail);     // 받는사람 이메일
	        messageHelper.setSubject(title); // 메일제목은 생략이 가능하다
	        messageHelper.setText(content);  // 메일 내용

	        mailSender.send(message);
	    } catch(Exception e){
	        System.out.println(e);
	    }

	    return "redirect:/mail/mailForm";
	}
	@RequestMapping(value = "/password/find")
	public String passwordFind() {
		
	    return "member/find";
	}  
	
	@RequestMapping(value ="/checkemail")
	@ResponseBody
	public Map<Object, Object> emailcheck(@RequestBody String str){

	    Map<Object, Object> map = new HashMap<Object, Object>();
	   
	    String [] arr = new String [2];
	    arr = str.split("&");
	    String id = arr[0];
	    String email = "";
	    try {
			email=URLDecoder.decode(arr[1],"UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    id= memberService.getVal(id);
	    email = memberService.getVal(email);
	    
	    boolean isOK = memberService.checkMember(id,email);
	    map.put("isOK",isOK);
	    return map;
	}
	@RequestMapping(value = "/password/send")
	public String passwordSend(String id,String email) {
	Object memeberService;
		//비밀번호 생성
		String newPw = memberService.createPw();
		//생성된 비밃번호 디비에 저장
		memberService.modify(id,email,newPw);
		//이메일 발송
		String title= "변경된 비밀번호입니다.";
		String contents = "변경된 비밀번호입니다.\n"+newPw+"\n";
		memberService.sendMail(email,title,contents);
	    return "send";
	}  
	
	
}

