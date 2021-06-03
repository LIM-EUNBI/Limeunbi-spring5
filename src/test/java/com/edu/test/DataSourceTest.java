package com.edu.test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

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
	
	//dataSource 객체는 데이터베이스 객체를 pool로 저장해서 사용할 때 DataSource객체를 사용.
	@Inject // 스프링에서 객체를 만드는 방법, 이전 자바에서는 new 키워드로 객체를 만듬.
	// 이전 자바7에서는 @Autowired로 객체를 만든다.
	DataSource dataSource; // 메모리 관리를 스프링에서 알아서 해준다.
	
	@Test
	public void oldQueryTest() throws Exception {
		// 스프링빈을 사용하지 않았을 때 방식 : 코딩 테스트에서는 스프링 설정을 안쓰고, 직접 DB 아이디/암호 입력 
		Connection con = null;
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521/XE", "XE", "apmsetup");
		logger.debug("데이터베이스 직접 접근에 성공했습니다.");
		logger.debug("DB종류는 " + con.getMetaData().getDatabaseProductName());

				
		con = null;
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
