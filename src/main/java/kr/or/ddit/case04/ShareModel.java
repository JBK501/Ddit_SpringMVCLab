package kr.or.ddit.case04;

import java.time.LocalDateTime;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/case04")
public class ShareModel {
	
	/*
	 *  1. url : /case04/model05
	 *  2. method : POST
	 *  3. resp : html 제한 (tiles)
	 *  4. model : now 이름으로 현재 날짜와 현재 시간 데이터를 전달함. 
	 */
	@PostMapping(value="model05", produces = MediaType.APPLICATION_JSON_VALUE)
	public String model05(Model model) { 
		LocalDateTime now = LocalDateTime.now();
		model.addAttribute("now",now);
		String lvn = "tiles:case04/model05";
		return lvn;
	}
	
	
	// 같은 클래스 안에 모든 핸들러가 실행이 될 때마다 실행된다.
	@ModelAttribute("appendModel")
	public StringBuffer appendModel() {
		return new StringBuffer("메소드에서 추가한 모델");
	}
	
	@RequestMapping("model04")
	public void model04(Model model) {
		String info = "주방에서 만든 모델";
		model.addAttribute("modelInfo", info);
	}
	
	@RequestMapping("model03")
	public ModelAndView model03() {
		ModelAndView mav = new ModelAndView();
		String info = "주방에서 만든 모델";
		mav.addObject("modelInfo", info);
		mav.setViewName("case04/model03");
		
		return mav;
	}
	
	@RequestMapping("model02")
	public void model02(Model model) {
		String info = "주방에서 만든 모델";
		model.addAttribute("modelInfo",info);
	}
	
	@RequestMapping("model01")
	public void model01(HttpServletRequest req) {
		String info = "주방에서 만든 모델";
		req.setAttribute("modelInfo", info);
	}
}
