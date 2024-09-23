package kr.or.ddit.case02;

import javax.servlet.http.Cookie;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case02")
public class ReceieveCookieeValue {
	
	// 우리는 쿠키의 value만 알면됨.
	@RequestMapping("cookie02")
	public String cookie02(@CookieValue(name = "JSESSIONID") String jsessionId) {
		log.info("jsessionid 쿠키 : {}", jsessionId);
		return "case02/dummy";
	}
	
	@RequestMapping("cookie01")
	public String cookie01(@CookieValue(name = "JSESSIONID") Cookie jsessionId) {
		log.info("jsessionid 쿠키 : {}", jsessionId.getValue());
		return "case02/dummy";
	}
}
