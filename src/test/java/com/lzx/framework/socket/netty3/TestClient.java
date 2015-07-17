package com.lzx.framework.socket.netty3;


import com.lzx.framework.socket.MessageDispatch;
import com.lzx.framework.socket.netty3.Netty3Client;
import com.lzx.framework.socket.netty3.command.TestRequestAction;
import com.lzx.framework.socket.netty3.message.ChatRequest;
import com.lzx.framework.socket.netty3.message.TestRequest;

public class TestClient {

	/**
	 * @param args
	 * @throws Exception
	 */
	public static void main(String[] args) throws Exception {
		MessageDispatch messageDispath = new MessageDispatch();
		messageDispath.registerMessage(1, TestRequest.class, new TestRequestAction());
		final Netty3Client client = new Netty3Client("127.0.0.1", 7000, messageDispath);
		client.connect();
		System.out.println("");
		
		TestRequest testMessage = new TestRequest();
		testMessage.setCmd((short) 1);
		client.send(testMessage);

		ChatRequest chatRequest=new ChatRequest();
		chatRequest.setCmd(2);
		chatRequest.setMsg("hello world");
		client.send(chatRequest);
		
		Object wait = new Object();
		synchronized (wait) {
			wait.wait();
		}
	}

}
