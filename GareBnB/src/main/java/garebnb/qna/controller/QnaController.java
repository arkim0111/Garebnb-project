package garebnb.qna.controller;

import java.util.List;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import garebnb.common.common.CommandMap;
import garebnb.qna.service.QnaService;

@Controller
public class QnaController {

	Logger log = Logger.getLogger(this.getClass());

	@Resource(name="qnaService")
	private QnaService qnaService;
	
	
	//1. 일반회원 + 호스트회원
	//Qna 리스트 + 입력폼 - 일반회원 --- 페이징기능 추가?
	@ResponseBody 
	@RequestMapping(value="/mypage/memQna.do")
	public Map<String, Object> memQna(CommandMap commandMap, HttpServletRequest request) throws Exception{
		Map<String, Object> Json = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("MEM_ID"); //세션에서 아이디
		commandMap.put("USER_MEM_ID", id);
		//String id = (String) session.getAttribute("USERID");
		//commandMap.put("MEM_USERID", id);
		
        List<Map<String, Object>> resultMap = qnaService.selectOneQnaList(commandMap.getMap());
        Json.put("map", resultMap);

        return Json;
	}
	
	
	//Qna 리스트 + 입력폼 - 호스트회원
	@RequestMapping(value="host/mypage/hostQna.do")
	@ResponseBody 
	public Map<String, Object> hostQna(CommandMap commandMap, HttpServletRequest request) throws Exception{
		Map<String, Object> Json = new HashMap<String, Object>();
		
		HttpSession session = request.getSession();
		String id = (String) session.getAttribute("MEM_ID"); //세션에서 아이디
		commandMap.put("USER_MEM_ID", id);
		
        List<Map<String, Object>> resultMap = qnaService.selectOneQnaList(commandMap.getMap());
        Json.put("map", resultMap);

        return Json;
	}
	
	
	//문의하기입력 - 일반회원 입력값  - 세션 추가하기
	@RequestMapping(value="/mypage/memInsertQna.do")
	@ResponseBody
	public Map<String, Object> memInsertQna(CommandMap commandMap) throws Exception{
		Map<String, Object> Json = new HashMap<String, Object>();
		
		qnaService.insertOneQna(commandMap.getMap());
        
        return Json;
	}
	
	
	//문의하기입력 - 호스트회원 입력값 - 세션 추가하기
	@RequestMapping(value="/host/mypage/hostInsertQna.do")
	@ResponseBody 
	public Map<String, Object> hostInsertQna(CommandMap commandMap) throws Exception{
		Map<String, Object> Json = new HashMap<String, Object>();
        qnaService.insertOneQna(commandMap.getMap());
        
        return Json;
	}
	
	
	//문의하기 삭제 - 일반회원 매핑
	@RequestMapping(value="/mypage/deleteQna.do")
	@ResponseBody 
	public Map<String, Object> memDeleteQna(CommandMap commandMap) throws Exception{
		Map<String, Object> Json = new HashMap<String, Object>();
		qnaService.deleteQna(commandMap.getMap());
		
        return Json;
	}
	
	
	
	//문의하기 삭제 - 호스트회원 매핑
		@RequestMapping(value="host/mypage/deleteQna.do")
		@ResponseBody 
		public Map<String, Object> hostDeleteQna(CommandMap commandMap) throws Exception{
			Map<String, Object> Json = new HashMap<String, Object>();
			
			qnaService.deleteQna(commandMap.getMap());
			
	        return Json;
		}
	
	
	
	
	//2. 관리자
	// 관리자 문의 리스트보기	
	@ResponseBody 
	@RequestMapping(value="/Admin/qnaList.do")
	public Map<String, Object> qnaList(CommandMap commandMap) throws Exception{
		Map<String, Object> Json = new HashMap<String, Object>();
		
        List<Map<String, Object>> resultMap = qnaService.selectQNAList(commandMap.getMap());
        Json.put("map", resultMap);

        return Json;
	}
	
	
	// 관리자 문의 상세페이지
	@RequestMapping(value="/Admin/qnaDetail.do")
	public Map<String, Object> qnaDetail(CommandMap commandMap) throws Exception {
		Map<String, Object> Json = new HashMap<String, Object>();
		
        Map<String, Object> resultMap = qnaService.selectAdminOneQNA(commandMap.getMap());
        Json.put("map", resultMap);

        return Json;
	}
	
	
	// 관리자 문의 답변
	@RequestMapping(value="/Admin/qnaComment.do")
	@ResponseBody 
	public Map<String, Object> qnaComment(CommandMap commandMap) throws Exception{
		Map<String, Object> Json = new HashMap<String, Object>();
        qnaService.updateQNAComment(commandMap.getMap());
        
        return Json;
	}

	
}
