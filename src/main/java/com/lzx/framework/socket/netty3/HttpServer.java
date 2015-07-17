package com.lzx.framework.socket.netty3;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;
import org.jboss.netty.handler.codec.http.HttpRequestDecoder;
import org.jboss.netty.handler.codec.http.HttpResponseEncoder;

import com.lzx.framework.http.server.IHttpHandler;
//import org.jboss.netty.handler.codec.http.HttpChunkAggregator;
import com.lzx.framework.socket.ISocketServer;

//import org.jboss.netty.handler.stream.ChunkedWriteHandler;

public class HttpServer implements ISocketServer{

	private int port;
	private IHttpHandler handler;

	public HttpServer(int port, IHttpHandler handler) {
		this.port = port;
		this.handler = handler;
	}


	private  class ServerPipelineFactory implements ChannelPipelineFactory {
		public ChannelPipeline getPipeline() throws Exception {
			// Create a default pipeline implementation.
			ChannelPipeline pipeline = Channels.pipeline();
			pipeline.addLast("decoder", new HttpRequestDecoder());
			pipeline.addLast("encoder", new HttpResponseEncoder());
			// http处理handler
			pipeline.addLast("handler", new HttpChannelHandler(handler));
			return pipeline;
		}
	}
	@Override
	public void bind() throws InterruptedException {
		// 配置服务器-使用java线程池作为解释线程
		ServerBootstrap bootstrap = new ServerBootstrap(new NioServerSocketChannelFactory(Executors.newCachedThreadPool(),
				Executors.newCachedThreadPool()));
		// 设置 pipeline factory.
		bootstrap.setPipelineFactory(new ServerPipelineFactory());
		// 绑定端口
		bootstrap.bind(new InetSocketAddress(port));
		System.out.println("httpserver start on " + port);
	}
	@Override
	public void close() {
		
	}


}
