package kr.or.test;
/*
 * 이 클래스는 메서드 기반의 Step1클래스를 객체 기반의 클래스로 변경한 클래스
 * 
 * @author 임은비
 * 
 * */
class MemberVO {
	// 회원정보를 저장하는 클래스(자료)
	private String name, phoneNum;
	private int age;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
	// 구현 메서드
	
}

class MemberService {
	public void printMember(MemberVO[] members) {
		// 향상된 for문을 사용.
		for(MemberVO member : members) {
			System.out.println("회원의 이름은 " + member.getName()
			+ " 나이는 " + member.getAge() + "세, " +
			"폰 번호는 " + member.getPhoneNum() + " 입니다.");
		}
	}
}

public class Step2 {

	public static void main(String[] args) {
		// MemberVO클래스 자료형 객체 생성.
		MemberVO member1 = new MemberVO();
		
		member1.setAge(10);
		member1.setName("임은비");
		member1.setPhoneNum("000-1111-2222");
		
		MemberVO member2 = new MemberVO();
		member2.setName("김길동");
		member2.setAge(15);
		member2.setPhoneNum("111-2222-3333");
		
		MemberVO member3 = new MemberVO();
		member3.setName("김별");
		member3.setAge(26);
		member3.setPhoneNum("222-3333-4444");
		
		// 배열
		MemberVO[] members = new MemberVO[3];
		members[0] = member1;
		members[1] = member2;
		members[2] = member3;
		
		// 객체로 만들면 호출 시 메모리에 로딩 -> 실행 끝나면 메모리 반환
		MemberService memberService = new MemberService();
		memberService.printMember(members);
		// 외부클래스는 아래처럼 직접 접근해서 메서드나 변수를 사용할 수 없음.
		// 외부 클래스로 접근하려면, 객체(실행가능한=메모리로딩)
		// But, 메서드를 static으로 변경하면 아래로 직접 접근 가능함.
		// 대신 static은 메모리 할당이 되기 때문에, 많이 쓰면 실행이 느려져서
		// 현업에서는 많이 쓰이지 않음.
		// MemberService.printMember(members);
		
	}
}
