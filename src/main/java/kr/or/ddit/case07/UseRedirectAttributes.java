package kr.or.ddit.case07;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.JdbcBoardVO;
import lombok.extern.slf4j.Slf4j;

/**
 	spring form 커스텀 태그 활용방법
 	1. model attribute 가 반드시 전달되어야 한다. ex) board
 	2. <form:form modelAttribute="board">
 	3. model 의 낱개 프로퍼티에 접근
 		<form:input path="boardNo"/> : <input type="text" name="boardNo" value="${board.boardNo}" />
 	4. 각 프로퍼티에 대한 검증 결과 메시지
 		<form:errors path="boardNo" />
 		
 	HandlerAdapter 를 이용한 커맨드 오브젝트 검증 단계
 	1. 검증 대상인 커맨드 오브젝트에 @Valid 나 @Valided(group 적용) 사용
 	2. 검증 대상 커맨드 오브젝트의 다음 파라미터로 Errors 나 BindingResult 를 통해 검증 결과 수집한다.
 	   검증 결과 객체와 검증 결과는 연이어서 파라미터로 받아야 한다.
 	3. errors 객체로부터 검증 통과 여불를 확인한다. ex) errors.hasErrors
 */
@Slf4j
@Controller
@RequestMapping("/case07/insert02")
public class UseRedirectAttributes {
	
	@ModelAttribute("board")
	public JdbcBoardVO board() {
		return new JdbcBoardVO();
	}
	
	@GetMapping
	public String formUI() {
		return "case07/insert02";
	}
	
	@PostMapping
	public String formDataProcess(
			@Validated() @ModelAttribute("board") JdbcBoardVO board
			, Errors errors
			, RedirectAttributes redirectAttributes
	) {
		if(errors.hasErrors()) {
			redirectAttributes.addFlashAttribute("board",board);
			redirectAttributes.addFlashAttribute(BindingResult.MODEL_KEY_PREFIX + "board",errors);
			log.info("errors 속성 명 : {}", BindingResult.MODEL_KEY_PREFIX + "board");
			return "redirect:/case07/insert02"; 
		} else {
			log.info("등록 완료 : {}", board);
			return "redirect:/case07/result";
			
		}
	}
	
}
