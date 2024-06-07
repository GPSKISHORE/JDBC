package com.tcs.jdbc;

public abstract class inter extends abstra{

	void meth1() {
		System.out.println("HI from meth1 in inner class");
	}
}

class cl extends inter{

	@Override
	void meth11() {
		// TODO Auto-generated method stub
		System.out.println("meth11 from cl");
	}
	
}

class c2{
	public static void main(String[] args) {
		new cl().meth1();
	}
}