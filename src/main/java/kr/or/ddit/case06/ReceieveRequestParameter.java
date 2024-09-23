package kr.or.ddit.case06;

import java.util.Map;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import kr.or.ddit.vo.ParametersVO;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequestMapping("/case06")
public class ReceieveRequestParameter {
	
	@RequestMapping("param04")
	public void param04(@RequestParam Map<String, Object> paramsMap) {
		log.info("paramsMap : {}", paramsMap);
	}
	
	@RequestMapping("param03")
	public void param03(@ModelAttribute("paramsVO") ParametersVO paramsVO) {
		log.info("paramsVO : {}", paramsVO);
	}
	
	@PostMapping(value =  "param02", produces = MediaType.APPLICATION_JSON_VALUE)
	public void param02(
			@RequestParam(required = false, defaultValue = "-1") int numParam
			, @RequestParam String txtParam
			, String korParam
	) {
		log.info("numParam : {}", numParam);
		log.info("txtParam : {}", txtParam);
		log.info("korParam : {}", korParam);
	}
	
	// 어노테이션이 없다면 @RequestParam 에서 데이터를 뽑는다.
	// @RequestParam(required = false)
	@GetMapping(value =  "param01", produces = MediaType.APPLICATION_JSON_VALUE)
	public void param01(
			@RequestParam(required = false, defaultValue = "-1") int numParam
			, @RequestParam String txtParam
			, @RequestParam(required = false) String korParam
	) {
		log.info("numParam : {}", numParam);
		log.info("txtParam : {}", txtParam);
		log.info("korParam : {}", korParam);
	}
	
}
