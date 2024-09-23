package kr.or.ddit.case02;

import java.util.Map;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case02")
public class ReceieveRequestHeader {
	
	
	// Optional: 들어올수도 있고, 안들어올수도 있다.
	// ifPresent : 존재한다면 괄호 실행 
	@GetMapping("header08")
	public String header08(@RequestHeader("num-header") Optional<Double> numHeader) {
		numHeader.ifPresent((nh)->log.info("num-header : {}",nh));
		return "case02/dummy";
	}
	
	// request header(optional) : num-header 수신 (숫자 : 정수, 실수) log 출력
	// json 응답만 전송 가능함.
	@GetMapping("header07")
	public String header07(@RequestHeader(name = "num-header", required = false, 
		defaultValue = "-1") double numHeader) {
		log.info("num-header : {}",numHeader);
		return "case02/dummy";
	}
	
	// HttpHeaders 사용
	@PostMapping("header06")
	public String header06(@RequestHeader HttpHeaders allHeaders) {
		allHeaders.forEach((n,v)->log.info("{} : {}",n,v));
		log.info("accept header : {}",allHeaders.getAccept());
		return "case02/dummy";
	}
	
	// @RequestHeader 모든 헤더를 다 받겠어. 
	// Map 으로 받기 (POJO 관점에서 적합)
	@PostMapping("header05")
	public String header05(@RequestHeader Map<String, String> allHeaders) {
		allHeaders.forEach((n,v)->log.info("{} : {}",n,v));
		log.info("accept header : {}",allHeaders.get("accept"));
		return "case02/dummy";
	}
	
 
	// 파라미터가 널값을 받을 수 없는경우, defaultValue를 사용한다. (required가 false일 때 사용)
	// 널 값을 받을 수 있는 래퍼클래스로 지정해도 된다.
	@PostMapping("header04")
	public String header04(@RequestHeader(name="my-header", required = false, defaultValue = "-1") int myHeader
			,@RequestHeader(name="my-header", required = false) Integer myHeader2) {
		// request accept 헤더의 값 수신.
		log.info("수신한 my-header : {}", myHeader);
		log.info("수신한 my-header : {}", myHeader2);
		return "case02/dummy";
	}
	
	// name = "" | 파라미터 명과 매핑됨. (생략불가능 | 자바는 - 변수 불가)
	@PostMapping("header03")
	public String header03(@RequestHeader("content-type") String contentType) {
		// request accept 헤더의 값 수신.
		log.info("수신한 content-type : {}",contentType);
		return "case02/dummy";
	}
	
	
	@GetMapping("header02")
	// @RequestHeader : 헤더정보
	// required = true |  꼭 있어야 하는 헤더정보임. (기본값으로 지정되어 있음)
	// name = "" | 파라미터 명과 매핑됨. (생략가능)
	public String header02(@RequestHeader String accept) {
		// request accept 헤더의 값 수신.
		log.info("수신한 accept : {}",accept);
		return "case02/dummy";
	}
	
	@GetMapping("header01")
	public String header01(HttpServletRequest req) {
		// request accept 헤더의 값 수신.
		log.info("수신한 accept : {}",req.getHeader("accept"));
		return "case02/dummy";
	}
}
