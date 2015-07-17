package com.lzx.framework.socket.netty3;

import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.netty3.message.ChatRequest;

public class TestMessage {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChatRequest c=new ChatRequest();
		
		Class cs=c.getClass();
		System.out.println(cs.getGenericSuperclass());
		System.out.println(cs.getGenericInterfaces());
		printArray(cs.getInterfaces());
		System.out.println(cs.getSuperclass().getSuperclass());
		printArray(cs.getSuperclass().getInterfaces());
		System.out.println(cs.isAssignableFrom(IMessage.class));
		System.out.println(IMessage.class.isInstance(c));
	}
	public static void printArray(Object[] arr) {
		System.out.println("arr:");
		for(Object o:arr) {
			System.out.println("    "+o);
		}
	}
	
	public boolean isImplement(Class cl){
		Class[] inters=cl.getInterfaces();
		for(Class onei:inters) {
			
		}
		
		return false;
	}
}
