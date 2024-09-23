package kr.or.ddit.case01;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case01") // /case1로 기본 경로(depth) 지정 
public class ReceieveRequest {
	
	
	@PostMapping(value = "request08", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE, 
				produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE})
	public String request08(HttpServletRequest req) {
		log.info("request08 요청 접수, method : {}", req.getMethod());
		return "case01/dummy";
	}
	
	
	// 1. url : /case01/request07
	// 2. method : GET
	// 3. request parameter : who
	// 4. 응답은 무조건 JSON 형태로만 전송할 예정이다. Accept : JSON 이어야 함.
	@GetMapping(value = "request07", params = "who", produces = MediaType.APPLICATION_JSON_VALUE)
	public String request07(HttpServletRequest req) {
		log.info("request07 요청 접수, method : {}", req.getMethod());
		return "case01/dummy";
	}
	
	
	// consumes : Content-Type 헤더 사용
	// Content-Type 이 다르면 UNSUPPORTED_MEDIA_TYPE 에러 발생(415)
	@PostMapping(value = "request06", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
	public String request06(HttpServletRequest req) {
		log.info("request06 요청 접수, method : {}", req.getMethod());
		return "case01/dummy";
	}
	
	// produces : 해당 미디어 타입만 받겠다. 
	// accept에 미디어 타입이 다르면, NOT ACCEPTABLE 에러 발생(406) 
	// accept를 배제시키면, 디폴트로 prduces 값으로 설정된 미디어 파일을 반환한다.
	@PutMapping(value = "request05", produces = "application/json")
	public String request05(HttpServletRequest req) {
		log.info("request05 요청 접수, method : {}", req.getMethod());
		return "case01/dummy";
	}
	
	// params 가 없으면 처리할 수 없다. (필수파라미터를 의미함)
	// !what : what이 없을 때 사용한다.
	@PostMapping(value = "request04", params ="what")
	public String request04(HttpServletRequest req) {
		log.info("request04 요청 접수, method : {}", req.getMethod());
		return "case01/dummy";
	}
	
	// body가 있는 post요청만 받겠다.
	@PostMapping("request03")
	public String request03(HttpServletRequest req) {
		log.info("request03 요청 접수, method : {}", req.getMethod());
		return "case01/dummy";
	}
	
	@GetMapping("request02")
	public String request02(HttpServletRequest req) {
		log.info("request02 요청 접수, method : {}", req.getMethod());
		return "case01/dummy";
	}
	
	@RequestMapping("request01")
	public String request01(HttpServletRequest req) {
		log.info("request01 요청 접수, method : {}", req.getMethod());
		return "case01/dummy";
	}
}
