package kr.or.test;

import java.util.function.IntSupplier;

public class Lambda {
	public static int plus(int x, int y, String lambda) {
		int result = 0;
		// 람다식 적용 전
		if(lambda.equals("nonLambda")){
		IntSupplier intSup = new IntSupplier() {

			@Override
			public int getAsInt() {
				int sum = x + y;
				return sum;
			}
			
		};
		result = intSup.getAsInt();
		} 
		// 람다식 적용 후
		if(lambda.equals("Lambda")){
			IntSupplier intSup = () -> {
				int sum = x + y;
				return sum;
			};
			result = intSup.getAsInt();
		}
		
		return result;
		
		
	}
	public String memberOne() {
		String result = "";
		return result;
	}
	public static void main(String[] args) {
		// plus 메서드 호출
		System.out.println("람다식 적용 전 메서드 결과 : " + plus(3, 4, "nonLambda"));
		System.out.println("람다식 적용 후 메서드 결과 : " + plus(3, 4, "Lambda"));
	}
}
