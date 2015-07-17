package com.lzx.framework.socket.netty3;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.group.ChannelGroup;
import org.jboss.netty.channel.socket.SocketChannelConfig;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelUpstreamHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.ISocketListener;

public class DefaultHandler extends IdleStateAwareChannelUpstreamHandler {

	protected final ISocketListener listener;
	protected ChannelGroup allChannels;

	public DefaultHandler(ISocketListener listener, ChannelGroup channelGroup) {
		this.listener = listener;
		this.allChannels = channelGroup;
	}

	public DefaultHandler(ISocketListener listener) {
		this.listener = listener;
	}

	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		this.listener.connected(new Netty3Channel(ctx.getChannel()));
	}

	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) throws Exception {
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		Object o = e.getMessage();
		if (!(o instanceof IMessage)) {
			ctx.sendUpstream(e);
			return;
		}
		IMessage msg = (IMessage) o;
		listener.messageReceived(ctx.getChannel().getId(), msg);
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, org.jboss.netty.channel.ExceptionEvent e) throws Exception {
		e.getCause().printStackTrace();
	}

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		this.listener.close(ctx.getChannel().getId());

	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		if (this.allChannels != null)
			this.allChannels.add(ctx.getChannel());
		//相对于低延迟更偏好高带宽，而相对于短连接时间更偏好低延迟
		((SocketChannelConfig) ctx.getChannel().getConfig()).setPerformancePreferences(0, 1, 2);
	}

	@Override
	public void channelDisconnected(ChannelHandlerContext ctx, ChannelStateEvent e) throws Exception {
		this.listener.disconnected(ctx.getChannel().getId());
	}
}
