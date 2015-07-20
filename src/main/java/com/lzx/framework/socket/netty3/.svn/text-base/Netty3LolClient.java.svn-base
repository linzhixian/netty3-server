package com.lzx.framework.socket.netty3;

import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

import com.lzx.framework.socket.DefaultClientSocketListener;
import com.lzx.framework.socket.DefaultSession;
import com.lzx.framework.socket.DefaultSocketListener;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.IMessageDispatch;
import com.lzx.framework.socket.ISession;
import com.lzx.framework.socket.netty3.lol.LolClientDecoder;
import com.lzx.framework.socket.netty3.lol.LolClientEncoder;
import com.lzx.framework.utils.NamingThreadFactory;

public class Netty3LolClient {

	private static final AtomicInteger bossId = new AtomicInteger();
	private static final AtomicInteger wrkerId = new AtomicInteger();

	String host;
	int port;
	IMessageDispatch messageDispatch;

	NioClientSocketChannelFactory factory;
	ChannelFuture future;
	DefaultSocketListener socketListner;
	ClientBootstrap bootstrap;
	private ISession session;

	public Netty3LolClient(String host, int port, IMessageDispatch messageDispatch) {
		this.host = host;
		this.port = port;
		this.messageDispatch = messageDispatch;
		socketListner = new DefaultClientSocketListener(messageDispatch);
	}

	public void send(IMessage message) {
		
		this.session.send(message);
	}

	public boolean connect() {
		factory = new NioClientSocketChannelFactory(Executors.newCachedThreadPool(new NamingThreadFactory("NettyClient-Boss-"
				+ bossId.incrementAndGet())), Executors.newCachedThreadPool(new NamingThreadFactory("NettyClient-Worker"
				+ wrkerId.incrementAndGet())));
		bootstrap = new ClientBootstrap(factory

		);
		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				ChannelPipeline pipeline = Channels.pipeline();

				pipeline.addLast("decoder", new LolClientDecoder(messageDispatch));
				pipeline.addLast("handler", new DefaultHandler(socketListner));
				pipeline.addLast("encoder", new LolClientEncoder());

				return pipeline;
			}
		});
		SocketAddress remoteAddress = new InetSocketAddress(host, port);
		future = bootstrap.connect(remoteAddress);
		boolean successful = future.awaitUninterruptibly(15, TimeUnit.SECONDS);
		if (successful) {
			Channel channel = future.getChannel();
			if (getSession() == null) {

				session = new DefaultSession(new Netty3Channel(channel));
			} else {
				this.getSession().setChannel(new Netty3Channel(channel));
			}
			socketListner.connected(new Netty3Channel(channel));
			System.out.println("connect sucess");
			return true;
		} else {
			return false;
		}
	}

	private ISession getSession() {
		return session;
	}

	public void close() {
		session.close();
		bootstrap.releaseExternalResources();
		future.getChannel().getCloseFuture().awaitUninterruptibly();
		factory.releaseExternalResources();
	}
}
