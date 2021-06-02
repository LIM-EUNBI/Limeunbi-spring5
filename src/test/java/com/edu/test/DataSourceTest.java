package com.edu.test;

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
	
	@Test // 테스트할때만 쓴다고 명시
	public void test() {
		// 로거는 조건에 따라서 출력을  조정할 수 있음.
		logger.debug("Junit 테스트 시작입니다.");
	}
}
