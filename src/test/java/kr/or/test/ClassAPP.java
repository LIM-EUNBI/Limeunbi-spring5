package kr.or.test;
/**
 * 이 클래스는 클래스 상속 extends
 * 오브젝트(객체) 생성에 필요한 new 키워드(예약어) 생성자 메서드
 * 추상클래스(abstract)
 * @author 웅비
 *
 */

// 오브젝트생성에 필요한 new 키워드 생성자 메서드 사용한 실습 클래스
class Circle {
	private int r; // 원의 반지름 변수
	
	// 생성자
	public Circle(int radius) {
		r = radius;
	};
	
	// 원주율 구하는 메서드
	public double getCircle() {
		double result = r * r * 3.14;
		return result;
	}
}

class Employee { // 회사의 직원들 클래스 (부모 클래스)
	int mSalary; // 월급 멤버 변수
	String mDept; // 사원 부서 변수
	
	public void doJob() {
		System.out.println("환영합니다. 직원 여러분~");
	}
}
class Salesman extends Employee {
	public Salesman() { // 자동으로 생성되는데 개발자가 커스터 마이징 할 때 만든다.
		mDept = "판매 부서";
	}
	public void doJob() {
		System.out.println("환영합니다. " + mDept + " 직원 여러분들~");
	}
}
class Development extends Employee {
	public Development() {
		mDept = "개발 부서";
	}
	public void doJob() {
		System.out.println("환영합니다. " + mDept + " 직원 여러분~");
	}
}

// 추상 클래스 
abstract class GraphicObject {
	int x, y;
	abstract void draw(); // 구현 내용이 없고, 메서드 명만 존재. 추상 메서드
	// 추상 메서드를 만드는 이유는 메서드가 수십개 수백개일 때,
	// 개발자가 구현할 때 어려움이 존재	
}
// 추상 클래스 사용
// 스프링에서는 추상클래스보다는 인터페이스를 더 많이 사용한다. 사용의도는 비슷하다.
class Traiangle extends GraphicObject {

	@Override // 부모 클래스의 메서드를 재정의 해서 사용. (오버라이드)
	void draw() {
		// 삼각형
		System.out.println("  *");
		System.out.println(" ***");
		System.out.println("*****");
	}
}
class Rectangle extends GraphicObject {
	@Override
	void draw() {
		// 사각형
		System.out.println("*****");
		System.out.println("*****");
		System.out.println("*****");
	}
}

public class ClassAPP {

	public static void main(String[] args) {
		// 원주율 구하는 오브젝트를 생성
		Circle c = new Circle(5);
		
		System.out.println("원의 넓이는 : " + c.getCircle());
		c = null; // 메모리 반환
		
		Employee e = new Employee();
		Salesman s = new Salesman();
		Development d = new Development();
		
		e.doJob();
		s.doJob();
		d.doJob();
		System.out.println();
		
		GraphicObject t = new Traiangle();
		GraphicObject r = new Rectangle();
		
		t.draw();
		System.out.println();
		r.draw();
		
		
	}
}
