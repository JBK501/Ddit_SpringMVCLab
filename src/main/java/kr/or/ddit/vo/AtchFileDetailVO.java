package kr.or.ddit.vo;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
	파일 메타데이터 VO
	
*/
@Data
@NoArgsConstructor
public class AtchFileDetailVO {
	
	private MultipartFile uploadFile;
	
	// 클라이언트가 업로드한 파일을 받기위한 생성자
	public AtchFileDetailVO(MultipartFile uploadFile) {
		
		this.uploadFile = uploadFile;
		
		this.orignlFileNm = uploadFile.getOriginalFilename();
		this.contentType = uploadFile.getContentType();
		this.fileSize = uploadFile.getSize();
		this.fancySize = FileUtils.byteCountToDisplaySize(fileSize);
		
		this.streFileNm = UUID.randomUUID().toString(); // 저장파일명을 생성자에서 만든다.
	}
	
	private Integer atchFileId;		// 파일 코드 (db에 생성되는 시점에 초기화)		
	private String fileStreCours; 	// 저장경로  (실제 저장될 때 초기화)
	private String streFileNm;		// 저장파일명
	private String orignlFileNm;	// 원본파일명 (확장자도 같이 처리함)
	private String contentType; 	// 파일 MIME (image / exe )
	private Long fileSize;			// 파일크기
	private String fancySize; 		// 파일크기(ex) 1KB
	
	
	// 저장경로를 입력받아서 파일을 저장한다.
	public void saveTo(File saveFolder) throws IllegalStateException, IOException {
		File saveFile = new File(saveFolder, streFileNm);
		this.fileStreCours = saveFile.getAbsolutePath();
		uploadFile.transferTo(saveFile);
	}
	
}
