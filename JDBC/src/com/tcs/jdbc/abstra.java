package com.tcs.jdbc;

public abstract class abstra {
	
	abstract void meth1();
	abstract void meth11();
	
	void meth29() {
		System.out.println("Abstarct class metth2");
	}
	 static void meth3()
	{
		System.out.println("Meth3");
	}
	 static {
		 System.out.println("block");
	 }
		public static void main(String[] args) {
	
		 abstra.meth3();
	}
}
