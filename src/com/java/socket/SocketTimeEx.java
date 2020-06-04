package com.java.socket;

import java.net.Socket;
import java.net.SocketException;

public class SocketTimeEx {
	public static void main(String[] args) throws SocketException {
		Socket sock = new Socket() ;
		System.out.println(sock.getSoTimeout());
	}
}
