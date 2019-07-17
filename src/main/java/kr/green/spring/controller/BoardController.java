package kr.green.spring.controller;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import kr.green.spring.pagination.Criteria;
import kr.green.spring.pagination.PageMaker;
import kr.green.spring.service.BoardService;
import kr.green.spring.vo.BoardVO;

@Controller
@RequestMapping(value="/board")
public class BoardController {
	
	@Autowired
	BoardService boardService;
	
	@RequestMapping(value="/list", method=RequestMethod.GET)
	public String boardListGet(Model model, Criteria cri) {
		cri.setPerPageNum(2);
		ArrayList<BoardVO> boardList = boardService.getBoardList(cri);
		PageMaker pm = new PageMaker();
		System.out.println(cri);
		//pm의 현재 페이지 정보 설정
		pm.setCriteria(cri);
		//pm의 displayPageNum 설정
		pm.setDisplayPageNum(5);
		//pm의 총 게시글 수 설정
		int totalCount = boardService.getTotalCount(cri);
		pm.setTotalCount(totalCount);
		model.addAttribute("pageMaker",pm);
		model.addAttribute("list", boardList);
		return "board/list";
	}
	@RequestMapping(value="/display", method=RequestMethod.GET)
	public String boardDisplayGet(Model model,Integer num) {
		//조회수 증가
		boardService.updateViews(num);
		
		BoardVO bVo = boardService.getBoard(num);
		model.addAttribute("board", bVo);
		return "board/display";
	}
	
	@RequestMapping(value ="/modify",method=RequestMethod.GET)
	public String boardModifyGet(Model model,Integer num,HttpServletRequest r) {
		if(!boardService.isWriter(num,r)) {
			return "redirect:/board/list";
		}
		//조회수 증가
		boardService.updateViews(num);
		BoardVO bVo = boardService.getBoard(num);
				
		model.addAttribute("board", bVo);
		return "board/modify";
	}

	@RequestMapping(value ="/modify",method=RequestMethod.POST)
	public String boardModifyPost(Model model,BoardVO bVo,HttpServletRequest r) {
		boardService.updateBoard(bVo,r);
		model.addAttribute("num",bVo.getNum());
		
		return "redirect:/board/display";
	}
	
	
	@RequestMapping(value ="register",method=RequestMethod.GET)
	public String boardRegisterGet() {
		return "board/register";   
	}
	@RequestMapping(value ="register",method=RequestMethod.POST)
	public String boardRegisterGet(BoardVO boardVo) {
		System.out.println(boardVo);
		boardService.registerBoard(boardVo);
		return "redirect:/board/list";   
	}
	
	@RequestMapping(value="delete", method=RequestMethod.GET)
	public String boardDeleteGet(Integer num,HttpServletRequest r) {
		if(boardService.isWriter(num,r)) {
			boardService.deleteBoard(num);
		}
		return "redirect:/board/list";
	}
	
	
	
	

	
	
}
