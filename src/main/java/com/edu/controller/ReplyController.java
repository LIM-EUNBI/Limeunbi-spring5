package com.edu.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.edu.service.IF_ReplyService;
import com.edu.vo.PageVO;
import com.edu.vo.ReplyVO;

/**
 * RestFull 서비스의 Endpoint(게이트웨이,URL 매핑)를 만들어주는 클래스
 * @author 은비	
 */


@RestController
public class ReplyController {
	private Logger logger = LoggerFactory.getLogger(ReplyController.class);
	@Inject
	private IF_ReplyService replyService;
	
	//댓글 삭제 
	@RequestMapping(value="/reply/reply_delete/{bno}/{rno}", method=RequestMethod.DELETE)
	public ResponseEntity<String> reply_delete(@PathVariable("bno")Integer bno, @PathVariable("rno")Integer rno){
		ResponseEntity<String> result = null;
		ReplyVO replyVO = new ReplyVO();
		replyVO.setBno(bno);
		replyVO.setRno(rno);
		try {
			replyService.deleteReply(replyVO);
			result = new ResponseEntity<String>("success",HttpStatus.OK);
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result;
	}
	
	//댓글 수정
	@RequestMapping(value="/reply/reply_update", method=RequestMethod.PATCH)
	public ResponseEntity<String> reply_update(@RequestBody ReplyVO replyVO) {
		// @RequestBody는 jsp에서 ajax로 보내는 데이터값.
		ResponseEntity<String> result = null;
		try {
			replyService.updateReply(replyVO);
			result = new ResponseEntity<String>("success", HttpStatus.OK);
			
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result; //Restful방식은 항상 반환값(Body-String, Hasmap-json)이 존재한다.
	}
	// 댓글은 select로 가져온 값을 ajax로 처리하기 때문에 read는 따로 구현을 하지 않는다.
	//댓글 등록 @RequestBody는 jsp에서 ajax메서드로 보내온 값을 받을 때 사용하는 애노테이션
	@RequestMapping(value="/reply/reply_insert", method=RequestMethod.POST)
	public ResponseEntity<String> reply_write(@RequestBody ReplyVO replyVO) {
		ResponseEntity<String> result = null;
		try {
			replyService.insertReply(replyVO);
			result = new ResponseEntity<String>("success", HttpStatus.OK);
			
		} catch (Exception e) {
			result = new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return result;
	}
	
	@RequestMapping(value="/reply/reply_list/{bno}/{page}", method=RequestMethod.POST) //현재 도메인에서만 사용 가능하도록 하기 위해서 POST
	public ResponseEntity<Map<String,Object>> reply_list(@PathVariable("bno") Integer bno,@PathVariable("page")Integer page) {
		//ResponseEntity는 일반 Controller클래스에서 사용했던 ResponseBody와 같은 역할.
		PageVO pageVO = new PageVO();
		//Json데이터형으로 자료를 반환. ResponseEntity형태는 대괄호로 자료를 묶어서 반환
		//[{"rno":1, "reply_text":"댓글테스트", "replyer":"admin", "bno":59}], Map<String,Object> 1개의 레코드
//		Map<String,Object> dummy1 = new HashMap<String,Object>();
//		Map<String,Object> dummy2 = new HashMap<String,Object>();
//		Map<String,Object> dummy3 = new HashMap<String,Object>();
		
//		dummy1.put("rno", 1);
//		dummy1.put("teply_text", "댓글테스트1");
//		dummy1.put("replyer", "admin");
//		dummy1.put("bno", bno);
//		
//		dummy2.put("rno", 2);
//		dummy2.put("teply_text", "댓글테스트1");
//		dummy2.put("replyer", "admin");
//		dummy2.put("bno", bno);
//		
//		dummy3.put("rno", 3);
//		dummy3.put("teply_text", "댓글테스트1");
//		dummy3.put("replyer", "admin");
//		dummy3.put("bno", bno);

//		Map<String,Object> resultMap = new HashMap<String,Object>();
//		List<Object> dummyMapList = new ArrayList<Object>();
//		dummyMapList.add(0, dummy1);
//		dummyMapList.add(1, dummy2);
//		dummyMapList.add(2, dummy3);
		
		ResponseEntity<Map<String,Object>> result = null;
		try {
		//model을 사용해서 ("변수명", 객체내용) 전송한 방식과 동일
		pageVO.setPage(page);
		pageVO.setPerPageNum(5);
		pageVO.setQueryPerPageNum(5);
		pageVO.setTotalCount(replyService.countReply(bno));
		
		if(pageVO.getTotalCount() > 0) {
			//아래 resultMap을 만든 목적은: 위 List객체를 ResponseEntity객체의 매개변수로 사용.
			Map<String,Object> resultMap = new HashMap<String,Object>();
			resultMap.put("replyList", replyService.selectReply(pageVO, bno));
			resultMap.put("pageVO", pageVO);
			logger.info("여기까지");
			//객체를 2개 이상 보내게 되는 상황일때, Json데이터형태(key:value)로 만들어서 보냅니다. 
			//--------------------------------------------------------
			//result객체를 만든목적:RestApi클라이언트(jsp쪽)으로 resultMap객체를 보낼때 상태값을 위해서
			result = new ResponseEntity<Map<String,Object>>(resultMap,HttpStatus.OK);
		} else {
			result = new ResponseEntity<Map<String,Object>>(HttpStatus.NO_CONTENT);
		}
		} catch(Exception e){
			result = new ResponseEntity<Map<String,Object>>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		return result; 
		
		
	}
}
