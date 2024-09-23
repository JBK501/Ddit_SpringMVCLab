package kr.or.ddit.vo;

import lombok.Data;

/**
	클라이언트가 전송한 여러개의 파라미터를 한 번에 받는 용도로 사용한다.
	command object
 */

@Data
public class ParametersVO {
	private Integer numParam;
	private String txtParam;
	private String korParam;
}
