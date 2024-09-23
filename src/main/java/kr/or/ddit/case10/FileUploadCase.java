package kr.or.ddit.case10;

import java.io.File;
import java.io.IOException;import java.nio.file.FileVisitOption;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.Case10VO;
import lombok.extern.slf4j.Slf4j;

/**
 	파일 업로드(multipart request 처리)
 	● multipart request 란? 
 	1. method : post
 	2. content-type(enctype) : multipart/form-data
 	 
 	● multipart request 처리구조
 	1. servlet 3.x 이상 : 하나의 part 를 {Part} Api 객체로 캡슐화하여 처리한다.
 	2. servlet 2.x 까지 : 하나의 part 를 {FileItem} api 객체로 캡슐화하여 처리한다.
 	3. (Spring) {MultipartFile}로 캡슐화하여 사용한다.
 		- {FileItem} 객체를 사용하는 구현체가 있다.
 		- {Part} 객체를 사용하는 구현체가 있다.
 		- servlet-context.xml 에서 빈으로 등록했다.
 		original multipart request 
 		-> multipart resolver 
 		-> MultipartHttpServletRequest wrapping(File Map)
 	
 
 */
@Slf4j
@Controller
@RequestMapping("/case10")
public class FileUploadCase {
	
	// 스프링 리소스는 read-only다.
	// set 을하려면 한번은 파일객체로 바꿔야 한다.
	@Value("file:D:/saveFiles")
	private Resource folder; // read-only
	
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
		this.saveFolder = folder.getFile(); // 스프링에서 리소스 주입 후, 파일로 바꾼다.
		log.info("folder : {}", folder);
	}
	
	@PostMapping("upload03")
	public String uploade03(
			Case10VO case10vo, RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {
		
		log.info("uploader : {}", case10vo.getUploader());
		int index = 0;
		
		
		for(MultipartFile singleFile : case10vo.getUploadFile()) {
			singleProcess(index++,singleFile, redirectAttributes);
		}
		
		return "redirect:/case10/result";
	}
	
	private void singleProcess(
			int index
			,MultipartFile singleFile
			,RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {
		
		log.info("uploadFile : {}", singleFile);
		
		Map<String, Object> fileVO = new HashMap<String, Object>();
		redirectAttributes.addFlashAttribute("fileVO_"+index,fileVO);
		
		fileVO.put("contentType", singleFile.getContentType());
		fileVO.put("fileSize", singleFile.getSize());
		fileVO.put("fancySize", FileUtils.byteCountToDisplaySize(singleFile.getSize()));
		fileVO.put("originalFileName", singleFile.getOriginalFilename());
		String filename = UUID.randomUUID().toString(); 
		fileVO.put("saveFileName", filename);
		
		File saveFile = new File(saveFolder, filename);
		singleFile.transferTo(saveFile);
	}
	
	
	
	@PostMapping("upload02")
	public String upload02(
		@RequestParam String[] uploader
		, @RequestPart MultipartFile[] uploadFile	
		, RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {
		
//		if(uploadFile.isEmpty()) {
//			throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "업로드 파일 없음.");
//		}
		
		log.info("uploader : {}", uploader);
		log.info("uploadFile : {}", uploadFile);
		
//		Map<String, Object> fileVO = new HashMap<String, Object>();
//		redirectAttributes.addFlashAttribute("fileVO",fileVO);
//		
//		fileVO.put("contentType", uploadFile.getContentType());
//		fileVO.put("fileSize", uploadFile.getSize());
//		fileVO.put("fancySize", FileUtils.byteCountToDisplaySize(uploadFile.getSize()));
//		fileVO.put("originalFileName", uploadFile.getOriginalFilename());
//		String filename = UUID.randomUUID().toString(); 
//		fileVO.put("saveFileName", filename);
		
//		File saveFile = new File(saveFolder, filename);
//		uploadFile.transferTo(saveFile);
		
		return "redirect:/case10/result";
	}
	
	
	@PostMapping("upload01")
	public String upload01(
			@RequestParam String uploader
			, MultipartHttpServletRequest req
			, RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {
		MultipartFile uploadFile = req.getFile("uploadFile");
		log.info("uploader : {}", uploader);
		log.info("uploadFile : {}", uploadFile);
		Map<String, Object> fileVO = new HashMap<String, Object>();
		redirectAttributes.addFlashAttribute("fileVO",fileVO);
		fileVO.put("contentType", uploadFile.getContentType());
		fileVO.put("fileSize", uploadFile.getSize());
		fileVO.put("fancySize", FileUtils.byteCountToDisplaySize(uploadFile.getSize()));
		fileVO.put("originalFileName", uploadFile.getOriginalFilename());
		String filename = UUID.randomUUID().toString(); 
		fileVO.put("saveFileName", filename);
		File saveFile = new File(saveFolder, filename);
		uploadFile.transferTo(saveFile);
		return "redirect:/case10/result";
	}
	
	@GetMapping({"uploadUI", "result"})
	public void uploadUI() {
		
	}
	
}
