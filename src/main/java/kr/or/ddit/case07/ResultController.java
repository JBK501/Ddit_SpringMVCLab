package kr.or.ddit.case07;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/case07/result")
public class ResultController {

	@GetMapping
	public void result() {
		
	}
}
