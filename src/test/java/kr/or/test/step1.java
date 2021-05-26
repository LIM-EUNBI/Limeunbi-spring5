package kr.or.test;
/**
 * 내부 변수와 배열 사용에 대해서 실습 클래스
 * @author 임은비
 *
 */
public class step1 {
	// 전역변수는 step1클래스에서 사용할 수 있는 변수
	// 내부변수는 main메서드 내부에서만 사용할 수 있는 변수
//	private String name; 전역변수
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
