package com.edu.dao;
/**
 * sqlSession 템플릿을 이용해서 쿼리를 실행하는 클래스
 * @author 은비
 */

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.edu.vo.PageVO;
import com.edu.vo.ReplyVO;

@Repository
public class ReplyDAO implements IF_ReplyDAO{
	@Inject
	private SqlSession sqlSession;

	@Override
	public List<ReplyVO> selectReply(PageVO pageVO, Integer bno) throws Exception {
		// 댓글 읽어오기
		Map<String,Object> paramMap = new HashMap<String,Object>();
//		paramMap.put("pageVO", pageVO);
		paramMap.put("queryStartNo", pageVO.getQueryStartNo());
		paramMap.put("queryPerPageNum", pageVO.getQueryPerPageNum());
		paramMap.put("bno", bno);
		return sqlSession.selectList("replyMapper.selectReply", paramMap);
	}

	@Override
	public int countReply(Integer bno) throws Exception {
		// 댓글수
		return sqlSession.selectOne("replyMapper.countReply", bno);
	}

	@Override
	public void insertReply(ReplyVO replyVO) throws Exception {
		// 댓글 생성
		sqlSession.insert("replyMapper.insertReply", replyVO);
		
	}

	@Override
	public void updateReply(ReplyVO replyVO) throws Exception {
		// 댓글 수정 (매개변수 두개일 때)
		
		sqlSession.update("replyMapper.updateReply", replyVO);
		
	}

	@Override
	public void replyCountUpdate(Integer bno, int count) throws Exception {
		// 댓글수 수정
		Map<String,Object> paramMap = new HashMap<String,Object>();
		paramMap.put("bno", bno);
		paramMap.put("count", count);
		sqlSession.update("replyMapper.replyCountUpdate", paramMap);
	}

	@Override
	public void deleteReply(ReplyVO replyVO) throws Exception {
		// 하나만 delete
		sqlSession.delete("replyMapper.deleteReply", replyVO);
	}

	@Override
	public void deleteReplyAll(Integer bno) throws Exception {
		// 게시글에 대한 댓글 모두 지우기
		sqlSession.delete("replyMapper.deleteReplyAll", bno);
	}

}
