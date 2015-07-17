package com.lzx.framework.socket.netty3;

import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.handler.execution.ExecutionHandler;
import org.jboss.netty.handler.execution.MemoryAwareThreadPoolExecutor;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

import com.lzx.framework.socket.CodecFactory;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.IMessageDispatch;
import com.lzx.framework.socket.ISocketListener;
import com.lzx.framework.utils.NamingThreadFactory;

public class Netty3Server extends AbstractNetty3Server {

	private IMessageDispatch messageDispatch;
	private ExecutionHandler executionHandler;
	private IdleStateHandler idleStateHandler;
	protected int workThreadCount;
	protected ISocketListener socketListner;
	protected CodecFactory codecFactor;
	protected final HashedWheelTimer hashedWheelTimer = new HashedWheelTimer();
	protected final IMessage pingMessage;

	protected ThreadPoolExecutor threadPoolExecutor;
	public Netty3Server(int port, IMessageDispatch messageDispatch, ISocketListener socketListner, CodecFactory factory,
			int workThreadCount, IMessage pingMessage) {
		super(port);
		this.messageDispatch = messageDispatch;
		this.workThreadCount = workThreadCount;
		this.socketListner = socketListner;
		this.codecFactor = factory;
		this.pingMessage = pingMessage;
		ThreadPoolExecutor threadPoolExecutor = new MemoryAwareThreadPoolExecutor(workThreadCount, 65536, 1048576, 60,
				TimeUnit.SECONDS, new NamingThreadFactory("ThreadPoolExecutor"));
		executionHandler = new ExecutionHandler(threadPoolExecutor);
		idleStateHandler = new IdleStateHandler(hashedWheelTimer, 60*60, 30, 0);
	}

	@Override
	public void addHandler(ChannelPipeline pipeline) {
		
		pipeline.addLast("decoder", new DefaultDecoder(messageDispatch, this.codecFactor));
		pipeline.addLast("poolshandler", executionHandler);
		pipeline.addLast("IdleStateHandler", idleStateHandler);
		pipeline.addLast("pingHandler", new PingHandler(pingMessage));//心跳包
		pipeline.addLast("handler", new DefaultHandler(socketListner, allChannels));
		pipeline.addLast("encoder", new DefaultEncoder(codecFactor));
	}

	@Override
	public void close() {
		super.close();
		if (executionHandler != null)
			executionHandler.releaseExternalResources();
		if (idleStateHandler != null)
			idleStateHandler.releaseExternalResources();
	}

}
