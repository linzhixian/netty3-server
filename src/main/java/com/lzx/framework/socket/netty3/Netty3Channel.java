package com.lzx.framework.socket.netty3;

import java.net.SocketAddress;
import java.util.concurrent.TimeUnit;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFutureListener;

import com.lzx.framework.socket.IChannel;
import com.lzx.framework.socket.IChannelBuffer;
import com.lzx.framework.socket.IMessage;

public class Netty3Channel implements IChannel {

	private Channel channel;
	
	public Netty3Channel(Channel channel) {
		this.channel=channel;
	}
	@Override
	public boolean isConnected() {
		return channel.isConnected();
	}

	@Override
	public void disconnect() {
		channel.disconnect();
	}

	@Override
	public SocketAddress getLocalAddress() {
		return channel.getLocalAddress();
	}

	@Override
	public SocketAddress getRemoteAddress() {
		return channel.getRemoteAddress();
	}

	@Override
	public void write(IMessage message) {
		if(channel.isOpen())
		channel.write(message);
	}
	@Override
	public boolean writeAwaitUninterruptibly(IMessage message,long timeout,TimeUnit timeUnit) {
		 return this.channel.write(message).awaitUninterruptibly(timeout,timeUnit);
	}
	@Override
	public Integer getId() {
		return this.channel.getId();
	}
	@Override
	public boolean isOpen() {
		return channel.isOpen();
	}

	public void writeThenClose(IChannelBuffer msg) {
		channel.write(msg.innerObj()).addListener(ChannelFutureListener.CLOSE);
	}

	@Override
	public void close() {
		this.channel.close();
	}
	@Override
	public boolean closeAwaitUninterruptibly(int waitTime,TimeUnit timeUnit) {
		return this.channel.close().awaitUninterruptibly(waitTime, TimeUnit.SECONDS);
	}
	  
	
	public String toString() {
		return this.channel.toString();
	}
	@Override
	public void writeThenClose(IMessage message) {
		channel.write(message).addListener(ChannelFutureListener.CLOSE);
	}
	
}
