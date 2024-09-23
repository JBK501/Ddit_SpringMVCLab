package kr.or.ddit.case08_09;

import java.util.ArrayList;
import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kr.or.ddit.vo.JdbcBoardVO;

@Controller
@RequestMapping("/case08_09/board")
public class JsonMessageController {
	
	@PostMapping()
	@ResponseBody
	public List<JdbcBoardVO> receieveJson(@RequestBody JdbcBoardVO newBoard) {
		List<JdbcBoardVO> list = new ArrayList<JdbcBoardVO>();
		
		JdbcBoardVO board1 = new JdbcBoardVO();
		board1.setBoardNo(1);
		board1.setBoardTitle("1번 글의 제목");
		list.add(board1);
		
		JdbcBoardVO board2 = new JdbcBoardVO();
		board2.setBoardNo(2);
		board2.setBoardTitle("2번 글의 제목");
		list.add(board2);
		
		list.add(newBoard);
		
		return list;
	}
	
	
	@DeleteMapping("{boNo}")
	@ResponseBody
	public List<JdbcBoardVO> deleteBoard(@PathVariable int boNo) {
		List<JdbcBoardVO> list = new ArrayList<JdbcBoardVO>();
		
		JdbcBoardVO board1 = new JdbcBoardVO();
		board1.setBoardNo(1);
		board1.setBoardTitle("1번 글의 제목");
		list.add(board1);
		
		JdbcBoardVO board2 = new JdbcBoardVO();
		board2.setBoardNo(2);
		board2.setBoardTitle("2번 글의 제목");
		list.add(board2);
		
		list.remove(boNo-1); 
		
		return list;
	}
	
	
	// Detail 로직
	@GetMapping("{boNo}") // PK를 경로변수로 받는다. /board/1(<-요놈)
	@ResponseBody
	public JdbcBoardVO sendJson01Detail(@PathVariable int boNo) {
		JdbcBoardVO board1 = new JdbcBoardVO();
		board1.setBoardNo(boNo);
		board1.setBoardTitle(boNo + "번 글의 제목");
		
		return board1;
	}

	
	@GetMapping() 
	@ResponseBody // 해당 메소드의 리턴값을 가지고, 응답데이터의 body를 결정한다. (json 응답)
	public List<JdbcBoardVO> sendJson01() {
		List<JdbcBoardVO> list = new ArrayList<JdbcBoardVO>();
		
		JdbcBoardVO board1 = new JdbcBoardVO();
		board1.setBoardNo(1);
		board1.setBoardTitle("1번 글의 제목");
		list.add(board1);
		
		JdbcBoardVO board2 = new JdbcBoardVO();
		board2.setBoardNo(2);
		board2.setBoardTitle("2번 글의 제목");
		list.add(board2);
		
		return list;
	}
}
