package kr.or.ddit.case03;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.view.ContentNegotiatingViewResolver;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.tiles3.TilesViewResolver;

@Controller
@RequestMapping("/case03")
public class UserViewResolvers {
	
	// lvn 을 생략하면, 스프링이 매핑주소를 기반으로 lvn을 찾아낸다.
	@GetMapping("view04")
	public void view04() {
		// case03/view04
	}
	
	// 페이지 모듈화 구조를 사용하려면, tiles.xml 파일에서 definition이 반드시 필요하다.
	/**
	 	1. {@link ContentNegotiatingViewResolver}
	 	2. {@link TilesViewResolver}가 lvn이용하여 definition을 검색한다.
	  	@return logical view name
	 */
	@GetMapping("view03")
	public String view03(Model model) {
		model.addAttribute("prop","test");
		return "tiles:case03/view03";
	}
	
	/**
	   1. {@link ContentNegotiatingViewResolver}가 accept 헤더를 이용해서, 컨텐츠 종류를 결정한다.
	   2. {@link InternalResourceViewResolver}가 single jsp 로 응답을 처리하기 위해 
	   										  lvn 앞뒤에 prefix, suffix 를 덧붙여 실제 뷰를 찾는다.
	 * @return logical view name
	 */
	@RequestMapping("view02")
	public String view02() { 
		return "case03/view02";
	}
	
	/**
	 	{@link ContentNegotiatingViewResolver} 
	 	: accept 헤더를 기반으로, 응답 컨텐츠 종류를 결정한다.
	 */
	@GetMapping(value ="view01", produces = MediaType.APPLICATION_JSON_VALUE)
	public void view01() { }
	
}
