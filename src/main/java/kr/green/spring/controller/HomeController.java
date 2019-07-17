package kr.green.spring.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

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
		System.out.println(mVo);
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
	
	@RequestMapping(value = "/signout")
	public String signout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.removeAttribute("user");
		return "redirect:/";
	}
	
	

	
	
}

