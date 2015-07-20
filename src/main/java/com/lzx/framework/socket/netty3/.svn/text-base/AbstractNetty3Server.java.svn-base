package com.lzx.framework.socket.netty3;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

import org.jboss.netty.bootstrap.ServerBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.channel.Channels;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.group.ChannelGroupFuture;
import org.jboss.netty.channel.group.DefaultChannelGroup;
import org.jboss.netty.channel.socket.nio.NioServerSocketChannelFactory;

import com.lzx.framework.socket.ISocketServer;
import com.lzx.framework.utils.NamingThreadFactory;

public abstract class AbstractNetty3Server implements ISocketServer {

	protected int port;


	private static final AtomicInteger channelGroupId = new AtomicInteger();
	private static final AtomicInteger bossId = new AtomicInteger();
	private static final AtomicInteger wrkerId = new AtomicInteger();
	protected final ChannelGroup allChannels;
	private NioServerSocketChannelFactory factory;

	public AbstractNetty3Server(int port) {
		this.port = port;

		allChannels = new DefaultChannelGroup("NettyServer-ChannelGroup-" + channelGroupId.incrementAndGet());
	}

	@Override
	public void bind() throws InterruptedException {
		factory = new NioServerSocketChannelFactory(Executors.newCachedThreadPool(new NamingThreadFactory("Netty-Server-Boss-"
				+ bossId.incrementAndGet())), Executors.newCachedThreadPool(new NamingThreadFactory("Netty-Server-Worker-"
				+ wrkerId.incrementAndGet())));
		ServerBootstrap bootstrap = new ServerBootstrap(factory);

		bootstrap.setPipelineFactory(new ChannelPipelineFactory() {
			public ChannelPipeline getPipeline() {
				ChannelPipeline pipeline = Channels.pipeline();
				addHandler(pipeline);
				return pipeline;
			}
		});
		bootstrap.setOption("child.tcpNoDelay", true);
		bootstrap.setOption("child.keepAlive", true);
		Channel channel = bootstrap.bind(new InetSocketAddress(port));
		this.allChannels.add(channel);
		System.out.println("---------------Netty3Server started at port:"+port);
	}

	public abstract void addHandler(ChannelPipeline pipeline);

	@Override
	public void close() {
		ChannelGroupFuture future = allChannels.close();
		if (future != null)
			future.awaitUninterruptibly();
		if (factory != null)
			factory.releaseExternalResources();

	}
}
