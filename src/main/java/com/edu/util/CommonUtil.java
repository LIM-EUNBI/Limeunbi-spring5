package com.edu.util;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FilenameUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.edu.dao.BoardDAO;
import com.edu.service.MemberService;
import com.edu.vo.MemberVO;

/**
 * 이 프로젝트에서 공통으로 사용하는 유틸리티기능을 모아놓은 클래스
 * @author 은비
 *
 */
@Controller
public class CommonUtil {
	//멤버변수생성(아래)
	private Logger logger = LoggerFactory.getLogger(CommonUtil.class);
	@Inject
	private MemberService memberService;//스프링빈을 주입받아서(DI) 객체준비
	@Inject
	private BoardDAO boardDAO;
	
	// 첨부파일 개별삭제 ajax @ResponseBody 사용
	@RequestMapping(value="/file_delete", method=RequestMethod.POST)
	@ResponseBody
	public String file_delete(@RequestParam("save_file_name")String save_file_name){
		String result = ""; // ajax로 보내는 변수
		try {
			boardDAO.deleteAttach(save_file_name); //DB에서 삭제
			File target = new File(uploadPath + "/" + save_file_name);
			if(target.exists()) {
				target.delete();
			}
			result = "success";
		} catch (Exception e) {
			result = "fail: " + e.toString();
		} 
		return result;
	}
	// 다운로드 처리도 같은 페이지에서 결과값만 반환받는 @ResponseBody 사용
	@RequestMapping(value="/download", method=RequestMethod.GET)
	@ResponseBody
	public FileSystemResource download(@RequestParam("save_file_name")String save_file_name, @RequestParam("real_file_name")String real_file_name, HttpServletResponse response) throws Exception {
		//FileSys.... 스프링 코어에서 제공하는 전용 파일처리 클래스
		File file = new File(uploadPath + "/" + save_file_name);
		response.setContentType("application/download; utf-8"); // 한글이 깨지는것을 방지
		real_file_name = URLEncoder.encode(real_file_name);
		response.setHeader("Content-Disposition", "attachment; filename="+real_file_name);
		return new FileSystemResource(file);
	}
	// 페이지 이동이 아닌 같은 페이지에 결과값만 반환하는 @ResponseBody 
	@RequestMapping(value="/image_preview", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<byte[]> imagePreview(@RequestParam("save_file_name") String save_file_name, HttpServletResponse response) throws Exception {
		// 파일 입출력할때는 파일을 byte형식으로 입출력할 때 발생되는 통로(스트림)
		FileInputStream fis = null; //입력통로 
		ByteArrayOutputStream baos = new ByteArrayOutputStream(); //출력통로
		fis = new FileInputStream(uploadPath + "/" + save_file_name);
		int readCount = 0;
		byte[] buffer = new byte[1024];//임시 저장소 크기 지정 1키로
		byte[] fileArray = null;
		// fis 입력받은 save_file_name 바이트값이(배열) -1일때까지 반복
		while((readCount = fis.read(buffer)) != -1) {
			// 입력통로 fis에서 출력통로로 baos보낸다. 파일 입출력은 byte 단위로만 가능
			baos.write(buffer, 0, readCount); // (rawData, 종료조건, 길이)
			// 결과는 baos에 누적된 결과가 들어갑니다.
		}
		fileArray = baos.toByteArray(); // baos 클래스를 byte[] 배열로 형변환.
		fis.close();
		baos.close();
		// fileArray값을 jsp로 보내주는 준비작업 
		final HttpHeaders headers = new HttpHeaders();
		// 크롬 개발자 도구>네트워크>image_preview클릭>헤더탭확인
		String ext = FilenameUtils.getExtension(save_file_name);
		// 이미지 확장자에 따라서 매칭되는 헤더값이 변해야 이미지 미리보기가 정상으로 보인다.
		switch(ext.toLowerCase()) { // 확장자를 소문자로 바꿔서 비교
		case "png":
			headers.setContentType(MediaType.IMAGE_PNG);
			break;
		case "jpg":
			headers.setContentType(MediaType.IMAGE_JPEG);
			break;
		case "gif":
			headers.setContentType(MediaType.IMAGE_GIF);
			break;
		case "jpeg":
			headers.setContentType(MediaType.IMAGE_JPEG);
			break;
		case "bmp":
			headers.setContentType(MediaType.parseMediaType("image/bmp"));
		default:break;
		}
		return new ResponseEntity<byte[]>(fileArray,headers,HttpStatus.CREATED); // 초기값(rawData,헤더정보,HTTP상태값)
	}
	// XSS 크로스사이트 스크립트 방지용 코드를 파싱하는 메서드
	public String unscript(String data) {
		if(data.isEmpty()) {
			return "";
		}
		String ret = data;
		ret = ret.replaceAll("<(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;script");
        ret = ret.replaceAll("</(S|s)(C|c)(R|r)(I|i)(P|p)(T|t)", "&lt;/script");
        ret = ret.replaceAll("<(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;object");
        ret = ret.replaceAll("</(O|o)(B|b)(J|j)(E|e)(C|c)(T|t)", "&lt;/object");
        ret = ret.replaceAll("<(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;applet");
        ret = ret.replaceAll("</(A|a)(P|p)(P|p)(L|l)(E|e)(T|t)", "&lt;/applet");

		return ret;
	}
	// 첨부파일 업로드/다운로드/삭제/인서트/수정에 모두 사용될 저장경로를 지정해서 전역으로 사용
	@Resource(name="uploadPath") // root-context에 있는 uploadPath 가져오기
	private String uploadPath;

	public String getUploadPath() {
		return uploadPath;
	}


	public void setLogger(Logger logger) {
		this.logger = logger;
	}

	public void setMemberService(MemberService memberService) {
		this.memberService = memberService;
	}

	public void setCheckImgArray(ArrayList<String> checkImgArray) {
		this.checkImgArray = checkImgArray;
	}

	//첨부파일이 이미지인지 체크하는 데이터
	private ArrayList<String> checkImgArray = new ArrayList<String>() {
		{
			add("gif");
			add("jpg");
			add("jpeg");
			add("png");
			add("bmp");
		}
	};
	
	public ArrayList<String> getCheckImgArray() {
		return checkImgArray;
	}

	//RestAPI서버 맛보기ID중복체크(제대로 만들면 @RestController 사용)
	@RequestMapping(value="/id_check", method=RequestMethod.GET)
	@ResponseBody //반환받은 값의 헤더값을 제외하고, 내용(body)만 반환하겠다는 명시
	public String id_check(@RequestParam("user_id")String user_id) throws Exception {
		//중복아이디를 체크로지(아래)
		String memberCnt = "1";//중복ID가 있을때, 기본값 1
		if(!user_id.isEmpty()) {//!주의 user_id가 공백이 아니라면,
			MemberVO memberVO = memberService.readMember(user_id);
			logger.info("디버그: " + memberVO);//user_id를 공백을 전송해도 null이기때문에 조건 추가필요
			if(memberVO == null) {//중복아이디가 존재하지 않으면 {}안을 실행
				memberCnt = "0";
			} 			
		}
		return memberCnt;//0.jsp 이렇게 작동하지 않습니다. 이유는 @ResponseBody때문이고, RestAPI는 값만 반환
	}
	//사용자단에서 사용 : JsonView방식으로 RestAPI 구현 
	@RequestMapping(value="/id_check_old", method=RequestMethod.GET)
	public String id_check_old(@RequestParam("user_id")String user_id, Model model) throws Exception{
		String memberCnt = "1"; //중복 아이디가 있는것을 기본값으로 지정
		if(!user_id.isEmpty()) {
			MemberVO memberVO = memberService.readMember(user_id);
			if(memberVO == null) { //중복ID가 없다면
				memberCnt = "0";
			}
		}
		model.addAttribute("memberCnt",memberCnt);
		return "jsonView";//jsp파일명 대신 servlet에서 정의한 스프링빈 ID명을 적으면, json객체로 결과를 반환.
	}

	// 파일 업로드 공통 메서드(Admin Controller에서 사용, homeController에서도 똑같이 사용예정)
	public String fileUpload(MultipartFile file) throws IOException {
		// TODO UUID클래스로 저장될 고유식별 파일명을 생성 후 물리적으로 저장
		String realFileName = file.getOriginalFilename();
		// 폴더에 저장할 PK파일명을 생성
		UUID uid = UUID.randomUUID(); // 유니크ID값 생성
		String saveFileName = uid.toString() + "." + StringUtils.getFilenameExtension(realFileName);
		byte[] fileData = file.getBytes();
		File target = new File(uploadPath, saveFileName);
		FileCopyUtils.copy(fileData, target); // 물리적으로 폴더에 저장
		return saveFileName;
	}
}