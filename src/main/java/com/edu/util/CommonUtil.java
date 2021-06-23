package com.edu.util;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

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