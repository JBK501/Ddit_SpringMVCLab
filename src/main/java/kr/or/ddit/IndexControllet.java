package kr.or.ddit;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IndexControllet {
	
	@RequestMapping(value = "/index.do")
	public String index() {
		return "index";
	}
	
}
