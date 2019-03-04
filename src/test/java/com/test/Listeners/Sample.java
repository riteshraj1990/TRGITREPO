package com.test.Listeners;

public class Sample extends Parent{
	
public static void main(String[] args) {
	Sample smp=new Sample();
	Object s2=smp.m1();
	System.out.println(s2);
	
	Object s3=smp.getS();
	System.out.println(s3);
	smp.m2();
}



	public void m2() {
		System.out.println(s);
	}
}
