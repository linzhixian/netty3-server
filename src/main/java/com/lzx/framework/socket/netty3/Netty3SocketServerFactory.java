package com.lzx.framework.socket.netty3;

import com.lzx.framework.http.server.IHttpHandler;
import com.lzx.framework.socket.ISocketServer;
import com.lzx.framework.socket.SocketServerFactory;
import com.lzx.framework.socket.SocketServerInitializer;

public class Netty3SocketServerFactory extends SocketServerFactory{

	@Override
	public ISocketServer createSocketServer(SocketServerInitializer socketServerInitializer) {
		int port=socketServerInitializer.getPort();
		
		Netty3Server server=new Netty3Server(port,socketServerInitializer.getMessageDispatch(),
				socketServerInitializer.getSocketListener(),
				socketServerInitializer.getCodecFactory(),
				socketServerInitializer.getWorkThreadCount(),
				socketServerInitializer.getPingMessage());
		return server;
		
	}
	@Override
	public ISocketServer createFlashSecurityServer(int port) {
		return new Netty3FlashSecurityServer(port);
	}
	@Override
	public ISocketServer createHttpServer(int port, IHttpHandler handler) {
		return new HttpServer(port,handler);
	}

}
