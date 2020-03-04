package com.etc;

public class KbEaiListener extends Thread  implements EMSListener{
	
	static AppListener appListener=null;
	
	public KbEaiListener() {
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public byte[] onMsg(byte[] arg0) {
		// TODO Auto-generated method stub
		return null;
	}
	
	public void run() {
		//종료 코드
		appListener.destroyListener();
	}
	public static void main(String[] args) throws ClassNotFoundException {
		appListener=AppListener.getInstance();
		appListener.initListener(Class.forName("").getName(),"" );
		Runtime.getRuntime().addShutdownHook(new KbEaiListener());
	}
}
