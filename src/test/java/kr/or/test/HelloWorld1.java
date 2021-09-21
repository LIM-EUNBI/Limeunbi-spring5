package kr.or.test;

import java.util.Arrays;
import java.util.Scanner;

public class HelloWorld1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int n;
		int[] Numbers = new int[5];
		int prev, next, temp;
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		
		for(int i = 0; i < n; i++) {
			System.out.println("숫자를 입력해주세요.");
			Numbers[i] = sc.nextInt();
		}
		
	}
}
