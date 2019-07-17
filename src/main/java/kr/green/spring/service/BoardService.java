package kr.green.spring.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;

import kr.green.spring.pagination.Criteria;
import kr.green.spring.vo.BoardVO;

public interface BoardService {

	ArrayList<BoardVO> getBoardList(Criteria cri);

	BoardVO getBoard(Integer num);	

	void updateViews(Integer num);

	void updateBoard(BoardVO bVo, HttpServletRequest r);

	

	void registerBoard(BoardVO boardVo);

	void deleteBoard(Integer num);

	boolean isWriter(Integer num, HttpServletRequest r);

	int getTotalCount(Criteria cri);





}
