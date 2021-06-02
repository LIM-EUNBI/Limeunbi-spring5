package kr.or.test;
/**
 * 이 클래스는 자바앱에서 개발자가 예외를 처리하는 방법을 실습.
 * @author 웅비
 *
 */

public class ExceptionTest {

	public static void main(String[] args) {
		// 외부 클래스 형 변수에 값을 저장해서 출력하는 프로그램
		MemberVO memberVO = new MemberVO(); // 동일 패키지 안의 클래스는 import 없이 사용 가능.
		memberVO.setName("홍길동");
		memberVO.setAge(10);
		memberVO.setPhoneNum("000-0000-0000");
		System.out.println(memberVO.toString());

		// 배열 변수 선언
		String[] arr = {"10", "2a", "30"};
		int indexValue = 0;
		
		for(int i=0; i<=2; i++) {
			//try {} catch(){} finally{}
			try {
			indexValue = Integer.parseInt(arr[i]);
			}catch(NumberFormatException e) {
				System.out.println(e.toString());
			} finally {
				System.out.println(i + "번째 프로그램이 종료되었습니다.");
			}
		}
	}
}
