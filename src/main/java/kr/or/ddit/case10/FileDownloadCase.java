package kr.or.ddit.case10;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.ContentDisposition;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.or.ddit.vo.AtchFileDetailVO;
import lombok.extern.slf4j.Slf4j;


// 파일의 확장명을 없애야 하는 이유
// 백도어 프로그램에 당하지 않기 위해서
@Slf4j
@Controller
@RequestMapping("/case10/und")
public class FileDownloadCase {
	
	// DB 역할을 하는 맵
	private Map<Integer, AtchFileDetailVO> fileDetailTable = new HashMap<Integer, AtchFileDetailVO>();
	
	// <미션>
	// 파일 업로드를 받는다.
	// d:/saveFiles 폴더에 저장한다.
	// 업로드 완료후 singleUI 컨트롤러로 리다이렉트 한다.
	// 업로드 완료된 파일 정보를 출력한다. (파일 저장명 - uploadedFile)
//	@Value("file:d:/saveFiles")
//	@Value("/resources/images")
	@Value("classpath:kr/or/ddit/saveFiles")
	private Resource folder;
	private File saveFolder;
	
	@PostConstruct
	public void init() throws IOException {
		saveFolder = folder.getFile();
		log.info("saveFolder 위치 : {}", saveFolder.getAbsolutePath());
		
		// 실제 사용경로
//		D:\B_Util\7.Egov\eGovFrameDev-4.0.0-64bit\workspace\.metadata\.plugins\org.eclipse.wst.server.core\tmp0\wtpwebapps\SpringMVCLab\WEB-INF\classes\kr\or\ddit\saveFiles

		// 실행환경에서 사라지는 경로
//		D:\B_Util\7.Egov\eGovFrameDev-4.0.0-64bit\workspace\SpringMVCLab\src\main\resources\kr\or\ddit\saveFiles
	}
	
	// MVC 사용하지 않고 처리
	@GetMapping("download02")
	public ResponseEntity<Resource>  download02(@RequestParam int what) {
		
		// 클라이언트가 요청한 파일의 메타정보를 DB에서 가져온다.
		AtchFileDetailVO fileDetail = fileDetailTable.get(what);
		
		// 요청한 파일이 없다면
		if(fileDetail == null) {
			// 404 에러를 발생한다.
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		String saveName = fileDetail.getStreFileNm();
		File saveFile = new File(saveFolder, saveName);
		
		FileSystemResource resource = new FileSystemResource(saveFile);
		
		HttpHeaders headers = new HttpHeaders();
		ContentDisposition disposition = 
							ContentDisposition.attachment()
											  .filename(fileDetail.getOrignlFileNm(), Charset.forName("UTF-8"))
											  .build();
		
		headers.setContentDisposition(disposition);
		return ResponseEntity.ok()
							.headers(headers)
							.contentType(MediaType.APPLICATION_OCTET_STREAM)
							.contentLength(fileDetail.getFileSize())
							.body(resource);
	}
	
	@GetMapping("download01")
	public String download01(@RequestParam int what, Model model) {
		
		// 클라이언트가 요청한 파일의 메타정보를 DB에서 가져온다.
		AtchFileDetailVO fileDetail = fileDetailTable.get(what);
		
		// 요청한 파일이 없다면
		if(fileDetail == null) {
			// 404 에러를 발생한다.
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		}
		
		model.addAttribute("fileDetail",fileDetail);
		
		return "downloadView";
	}
	
	
	
	// @RequestPart : "multipart/form-data" 요청을 처리하는 어노테이션
	// {MultipartFile} : multipart 요청으로 받은 업로드 파일정보 (원본파일명, 파일타입, 파일크기)
	
	@PostMapping("upload")
	public String upload(
			@RequestPart MultipartFile uploadFile
			, RedirectAttributes redirectAttributes
	) throws IllegalStateException, IOException {
		
		if(!uploadFile.isEmpty()) {
			
			// 파일 메타데이터를 저장할 VO를 생성한다.
			AtchFileDetailVO fileDetail = new AtchFileDetailVO(uploadFile);
			
			// 2진 데이터를 저장한다.
			fileDetail.saveTo(saveFolder);
			
			// DB에 저장할 PK 값을 생성한다.
			Integer newId = fileDetailTable.size() + 1;
			fileDetail.setAtchFileId(newId);
			
			// DB에 메타 데이터를 저장한다.
			fileDetailTable.put(newId, fileDetail);
			
			redirectAttributes.addFlashAttribute("uploadedFile",fileDetail);
		}
		
		return "redirect:/case10/und/singleUI";
	}
	
	@GetMapping("singleUI")
	public String uploadUI() {
		return "case10/und/singleUI";
	}
	
}
