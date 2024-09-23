package kr.or.ddit.vo;

import org.springframework.http.HttpStatus;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import lombok.Data;

@Data
public class Case10VO {
	private String[] uploader;
	private MultipartFile[] uploadFile;
	
	public void setUploadFile(MultipartFile[] uploadFile) {
		
		for(MultipartFile singleFile : uploadFile) {
			singleFileTest(singleFile);
		}
		
		this.uploadFile = uploadFile;
	}
	
	
	private void singleFileTest(MultipartFile singleFile) {
		if(singleFile.isEmpty()) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "업로드 파일 없음.");
		}
		if(!singleFile.getContentType().startsWith("image/")) {
			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "이미지만 업로드 가능");
		}
//		this.uploadFile = singleFile;
	}
	
}
