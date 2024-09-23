package kr.or.ddit.case10.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.servlet.view.AbstractView;

import kr.or.ddit.vo.AtchFileDetailVO;

public class DownloadView extends AbstractView {

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 모델을 꺼낸다.
		AtchFileDetailVO fileDetail = (AtchFileDetailVO) model.get("fileDetail");
		
		// 저장경로를 받는다. (경로 + 저장파일정보) 
		String saveFilePath = fileDetail.getFileStreCours();
		
		// 파일객체를 만든다.
		File saveFile = new File(saveFilePath);
		
		try (
			// 파일을 읽기위한 스트림을 개방한다.
			FileInputStream fis = new FileInputStream(saveFile); // 1차스트림을 개압할 때는 미디어를 연결해주어야 한다.
			
			// 파일을 전달하기위한 스트림을 개방한다.
			OutputStream os = response.getOutputStream()
		) {
//			Content-Disposition: inline[attatchment];  filename="파일명"
//			Content-Type: application/octet-stream
			
			// <개념>
			// 브라우저가 바로 열어서 처리 : inline (ex : png 파일을 바로 열어버림)
			// 첨부된 형태로 처리 : attatchment (다운로드 처리함)
			// filename : 다운로드하는 파일명
			
			
			// 한글처리를 하기위해 인코딩 한다.
			String filename = 
					URLEncoder.encode(fileDetail.getOrignlFileNm(), "UTF-8")
					.replace("+", " ");
			
			
			// 원본 파일명으로 다운로드를 하기위해 response를 설정한다.
			// 1. 파일정보를 표현한다.
			// 	1.1. 저장방법을 지정한다.
			// 	1.2. 파일명을 지정한다.
			String contentDisposition = 
					String.format("attatchment;  filename=\"%s\"", filename);
			response.setHeader("Content-Disposition", contentDisposition);
			
			// 2. inline이면 원본 파일의 mime을 설정한다. (fileDetail.getContentType())
			//	  attatchment면 첨부 형태로 mime을 설정한다.
			response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
			
			// 3. 청크가 쪼개져야하는 경우 download %로 환산하기위해서 사용한다.
			// 	  10% -> 20% -> 30%...
			response.setContentLengthLong(fileDetail.getFileSize());
			
			
			// 파일을 다운로드한다.
			IOUtils.copy(fis, os);
		};
	}

}
