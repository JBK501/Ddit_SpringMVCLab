package kr.or.ddit.vo;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import lombok.Data;

@Data
public class JdbcBoardVO implements Serializable {
	@NotNull
	private Integer boardNo;
	@NotBlank
	private String boardTitle;
	@NotBlank
	private String boardWriter;
	@DateTimeFormat(iso = ISO.DATE_TIME) // string --> time api 로 변환
	private LocalDateTime boardDate;
	@Size(min = 5)
	private String boardContent;
}
