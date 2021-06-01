package kr.or.test;

public class Collatz {

	public static void main(String[] args) {
		Collatz c = new Collatz();
		int ex = 626331;
		System.out.println(c.collatz(ex));

	}
	
	public int collatz(int num) {
		Double answer = (double)num;
		int n = 0;
		while(true) {
			if(answer%2 == 0) {
				answer = answer/2;
			}
			else {
				answer = (answer*3) + 1;
			}
			n++;
			if(answer == 1) return n;
			if(n>=500) return -1;
		}		
	}
}