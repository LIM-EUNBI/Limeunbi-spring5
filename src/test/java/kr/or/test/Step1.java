package kr.or.test;
/**
 * 내부 변수와 배열 사용에 대해서 실습 클래스
 * @author 임은비
 *
 */
public class Step1 {
	// 전역변수는 step1클래스에서 사용할 수 있는 변수
	// 내부변수는 main메서드 내부에서만 사용할 수 있는 변수
//	private String name; 전역변수
	public void Step1() {
		// 클래스명과 같은 이름의 메서드를 생성자 메서드(자동으로 만들어짐)
		// 중요한 이유는 다른 클래스에서 step1을 객체로 만들 때 생성자 메서드가 필요.
		// step1 aaa = new step1();
		// 자바앱에서는 객체를 만들 때 필수
		// 단, 스프링에서는 @Inject로 객체를 만들어서 사용
	}
	public static void main(String[] args) {
		// name, age, phoneNum 내부변수 사용.
		String name;
		int age;
		String phoneNum;
		// 값 넣기
		name = "홍길동";
		age = 10;
		phoneNum = "000-0000-0000";
		
		printMember(name, age, phoneNum);

		name = "임은비";
		age = 29;
		phoneNum = "010-1111-1111";
		printMember(name, age, phoneNum);
		name = "김철수";
		age = 23;
		phoneNum = "010-5488-4442";
		printMember(name, age, phoneNum);
		
		// 배열
		String[] names = {"홍길동", "임은비", "김철수"};
		int[] ages = {10, 29, 23};
		String[] phoneNumbers = {"000-0000-0000", "010-1111-1111", "010-5488-4442"};
		printMember(names, ages, phoneNumbers);
	}

	private static void printMember(String[] names, int[] ages, String[] phoneNumbers) {
		
		for(int i = 0; i < names.length; i++) {
			System.out.println("입력하신 회원의 이름은 " + names[i] + ", 나이는 " + ages[i] + "세, 핸드폰번호는 " + phoneNumbers[i] + " 입니다.");

		}
		
	}

	private static void printMember(String name, int age, String phoneNum) {
		System.out.println("입력하신 회원의 이름은 " + name + ", 나이는 " + age + "세, 핸드폰번호는 " + phoneNum + " 입니다.");
	}

}
