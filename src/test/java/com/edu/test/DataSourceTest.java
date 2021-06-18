package com.edu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.edu.service.IF_BoardService;
import com.edu.service.IF_MemberService;
import com.edu.vo.BoardVO;
import com.edu.vo.MemberVO;
import com.edu.vo.PageVO;

/**
 * 이 클래스는 오라클과 연동해서 CRUD를 테스트해보는 클래스
 * @author 웅비
 *
 */
// RunWith 인터페이스는 현재 클래스가 JUnit 실행 클래스라는 것을 명시
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/spring/**/*.xml"})
@WebAppConfiguration
public class DataSourceTest {
	// 디버그용 로그 객체(변수) 생성
	private Logger logger = Logger.getLogger(DataSourceTest.class);
	@Inject
	private IF_BoardService boardService;

	//dataSource 객체는 데이터베이스 객체를 pool로 저장해서 사용할 때 DataSource객체를 사용.
	@Inject // 스프링에서 객체를 만드는 방법, 이전 자바에서는 new 키워드로 객체를 만듬.
	// 이전 자바7에서는 @Autowired로 객체를 만든다.
	DataSource dataSource; // 메모리 관리를 스프링에서 알아서 해준다.
	@Inject
	private IF_MemberService memberService;
	// M-V-C 사이에 데이터를 입출력하는 임시저장 공간(VO클래스-멤버변수+Get/Set메서드) 생성
	// ValueObject 클래스는 DB테이블과 1:1
	// MemberVO.java VO클래스를 생성.
	
	@Test
	public void insertBoard() throws Exception{
		BoardVO boardVO = new BoardVO();
		boardVO.setTitle("반환값 테스트");
		boardVO.setContent("J유닛 입력 테스트");
		boardVO.setWriter("admin");
		boardVO.setBoard_type("gallery");
		boardService.insertBoard(boardVO);
	}
	@Test
	public void updateMember() throws Exception{
		// 회원정보 수정.
		MemberVO memberVO = new MemberVO();
		memberVO.setEmail("admin@test.com");
		memberVO.setEnabled(true);
		memberVO.setLevels("ROLE_ADMIN");
		memberVO.setPoint(100);
		memberVO.setUser_name("최고관리자");
		memberVO.setUser_pw("");
		// 메서드 내 적용된 객체변수 생성.
		BCryptPasswordEncoder pwe = new BCryptPasswordEncoder();
		if((memberVO.getUser_pw()).length() < 0) {
			String userPwe = pwe.encode(memberVO.getUser_pw()); // encode(rawdata)
			memberVO.setUser_pw(userPwe); // 암호화된 해시데이터가 membervo객체에 입력.
			}
		//스프링5 시큐리티 암호화 적용 로직.
		memberVO.setUser_id("admin"); //수정 조회조건에 사용.where
		memberService.updateMember(memberVO);
		// 1명의 회원만 수정할 때 사용하는 로직.(위)
		// 굳이 회원정보 수정일 때 시큐리티를 적용하는 이유, insert할때도 적용하게될 수 있는지.
		// update와 시큐리티 암호를 따로 구현하면 되는게 아닌지 물어보기.
		
		//반복(시큐리티 암호화가 되지않는 사용자만 암호 업데이트)
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);//기본값으로 1페이지
		pageVO.setPerPageNum(10);
		pageVO.setQueryPerPageNum(1000);
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		for(MemberVO memberOne : listMember) {
			// 중복암호화 방지
			String rawPw = memberOne.getUser_pw();
			if(rawPw.length() < 50) {
			String onePwe = pwe.encode(rawPw);
			memberOne.setUser_pw(onePwe);
			memberService.updateMember(memberOne);
			}
		}
		selectMember();
	}
	
	@Test
	public void readMember() throws Exception{
		// 회원 상세보기(1개 레코드) 
		MemberVO memberVO = new MemberVO();	
		String user_id = "admin";
		memberVO = memberService.readMember(user_id);
	}
	
	
	@Test
	public void deleteMember() throws Exception{
		memberService.deleteMember("user_delete");
		selectMember();
	}
	
	
   @Test
   public void insertMember() throws Exception {
      MemberVO memberVO = new MemberVO();
      //insert쿼리에 저장할 객체
      memberVO.setUser_id("user_delete");
      memberVO.setUser_pw("12341");//스프링시큐리티5버전으로 암호화로 처리예정
      memberVO.setEmail("user@test.com");
      memberVO.setPoint(10);
      memberVO.setEnabled(true);
      memberVO.setLevels("ROLE_USER");
      memberVO.setUser_name("삭제할사용자2");
      memberService.insertMember(memberVO);
      selectMember();
   }

	
	@Test
	public void selectMember() throws Exception{
		// 회원관리 테이블에서 더미로 입력한 100개의 레코드를 출력하는 메서드 -> 회원관리 목록 출력
		// 검색, 페이징 기능 구현(1페이지 10명씩)
		// 현재 몇페이지, 검색어 임시저장 공간 필요. -> DB에 페이징 조건문, 검색 조건문
		// PageVO.java 클래스 만들어서 페이징 처리변수와 검색어 변수 선언.
		PageVO pageVO = new PageVO();
		pageVO.setPage(1);//기본값으로 1페이지
		pageVO.setPerPageNum(10);
		pageVO.setQueryPerPageNum(1000);
		pageVO.setTotalCount(memberService.countMember(pageVO));//테스트 100명
//		pageVO.setSearch_type("user_id");
//		pageVO.setSearch_keyword("user_del");
		
		// pageVO객체에 어떤값이 들어있는지 확인
		logger.info("pageVO 저장된 값 확인 " + pageVO);
		List<MemberVO> listMember = memberService.selectMember(pageVO);
		listMember.toString();
	}
	
	@Test
	public void oldQueryTest() throws Exception {
		// 스프링빈을 사용하지 않았을 때 방식 : 코딩 테스트에서는 스프링 설정을 안쓰고, 직접 DB 아이디/암호 입력 
		Connection con = null;
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "XE", "apmsetup");
		logger.debug("데이터베이스 직접 접근에 성공했습니다.");
		logger.debug("DB종류는 " + con.getMetaData().getDatabaseProductName());

		// 직접 쿼리 날리기
		Statement stmt = con.createStatement();
		
		// insert 쿼리
//		for(int i=0; i<100; i++) {
//		stmt.executeQuery("insert into dept02 values ("+ i +", '판매부서', '서울')");
//		}
		// 테이블에 입력되어 있는 레코드를 select 쿼리를 stmt로 가져오기
		ResultSet rs = stmt.executeQuery("select * from dept");
		
		while(rs.next()) {
			logger.debug(rs.getString("deptno") + " " + rs.getString("dname") + " " + rs.getString("loc"));
		}
				
		con.close();
		stmt.close();
		rs.close();
	}
	
		@Test
	public void dbconnectionTest(){
		// 데이터베이스 커넥션 테스트 : 설정은 root-context에서 생성한 빈을 이용
		try {
			Connection con = dataSource.getConnection();
			logger.debug("데이터베이스 접속에 성공했습니다.");
			logger.debug("DB종류는 " + con.getMetaData().getDatabaseProductName());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.debug("데이터베이스 접속에 실패했습니다.");
			e.printStackTrace();
		}
	}
	
	@Test // 테스트할때만 쓴다고 명시
	public void test() {
		// 로거는 조건에 따라서 출력을  조정할 수 있음.
		logger.debug("Junit 테스트 시작입니다.");
	}
}
