package kr.or.ddit.case05;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case05")
public class FlowControl {
	
	// redirect 에서 attribute 저장방법
	// 세션을 통해 전달은 하되, attribute를 꺼낸 후, 알아서 지우는 방식이 필요하다.(flash)
	// 따라서 attribute를 FlashAttribute로 저장한다.
	/**
	 * redirect: 기존 요청이 제거되고(stateless) 새로운 요청이 발생하는 구조.
	 * RedirectAttribute.addFlashAttribute() -> flash map manager 가 동작한다.
	 * -> Model 에다가 flash attribute 데이터를 저장한다.
	 * -> Model.getAttribute()
	 * @param redirectAttributes
	 * @return
	 */
	@PostMapping("start02")
	public String start02(RedirectAttributes redirectAttributes) {
		redirectAttributes.addFlashAttribute("startAttr", "출발점에서 만든 모델");
		return "redirect:/case05/dest02";  
	}
	
	// redirect 이동은 모델 객체를 확인할 수 있다.
	@GetMapping("dest02")
	public void dest02(Model model) {
		log.info("모델 객체 : {}", model);
		log.info("startAttr : {}", model.getAttribute("startAttr"));
		model.addAttribute("destAttr", "도착점에서 만든 모델");
	}
	
	
	/**
	 * forward : 기존 요청을 가지고 분기하는 방식
	 * model.add -> request scope 에 데이터를 저장한다.
	 * -> @RequestAttribute 사용해서 데이터를 확인할 수 있다.
	 * @param model
	 * @return
	 */
	@GetMapping("start01")
	public String start01(Model model) {
		
		model.addAttribute("startAttr", "출발점에서 만든 모델");
		
		// 흐름을 제어하기 위한 도착지 정보
		// lvn이 아니다. (그래서 {/}를 붙여야 한다.)
		return "forward:/case05/dest01";  
	}
	
	// forward 이동은 모델객체를 확인할 수 없다.
	// @requestAttribute 를 통해서 attribute 정보를 확인할 수 있다.
	@RequestMapping("dest01")
	public void dest01(Model model, @RequestAttribute String startAttr) {
		log.info("모델 객체 : {}", model); 
		log.info("startAttr : {}", startAttr); 
		model.addAttribute("destAttr", "도착점에서 만든 모델");
	}
}
