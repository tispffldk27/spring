package kr.green.spring.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import kr.green.spring.dao.BoardDAO;
import kr.green.spring.pagination.Criteria;
import kr.green.spring.vo.BoardVO;
import kr.green.spring.vo.MemberVO;

@Service
public class BoardServiceImp implements BoardService {

	@Autowired
	BoardDAO boardDao;
	
	@Override
	public ArrayList<BoardVO> getBoardList(Criteria cri) {
		return boardDao.getBoardList(cri);
	}

	@Override
	public BoardVO getBoard(Integer num) {
		if(num == null)
			return null;
		return boardDao.getBoard(num);
	}

	@Override
	public void updateViews(Integer num) {
		//boardDao.updateViews(num);
		BoardVO tmp = boardDao.getBoard(num);
		if(tmp != null) {
			int oldViews = tmp.getViews();
			tmp.setViews(oldViews+1);
			boardDao.updateBoard(tmp);
		}
	}

	@Override
	public void updateBoard(BoardVO bVo, HttpServletRequest r) {
		MemberVO user = (MemberVO)r.getSession().getAttribute("user");
		if(user == null || bVo == null)	return ;
		if(bVo.getWriter() != null && bVo.getWriter().equals(user.getId())) {
			boardDao.updateBoard(bVo);
		}
	}

	@Override
	public void registerBoard(BoardVO boardVo) {
		boardDao.registerBoard(boardVo);
	}

	@Override
	public void deleteBoard(Integer num) {
		if(num == null || num <= 0)	return;
		boardDao.deleteBoard(num);
	}

	@Override
	public boolean isWriter(Integer num, HttpServletRequest r) {
		BoardVO board = boardDao.getBoard(num);
		MemberVO user = (MemberVO)r.getSession().getAttribute("user");
		if(board != null && user != null 
				&& board.getWriter().equals(user.getId())) {
			return true;
		}
		return false;
	}

	@Override
	public int getTotalCount(Criteria cri) {
		return boardDao.getTotalCount(cri);
	}

}
