package com.lzx.framework.socket.netty3;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lzx.framework.socket.IMessage;

/**
 * 超时没交互发送心跳包
 * 
 * @author lzx
 * 
 */
public class PingHandler extends IdleStateAwareChannelHandler {
	private static final Logger logger = LoggerFactory.getLogger(PingHandler.class);
	private IMessage pingMessage;

	public PingHandler(IMessage pingMessage) {
		this.pingMessage = pingMessage;
	}

	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e) {
		if (e.getState() == IdleState.READER_IDLE) {
			logger.info("Reader_idle:timeout--close()"+ctx.getChannel().getId());
			e.getChannel().close();
		} else if (e.getState() == IdleState.WRITER_IDLE) {
			System.out.println("send pingMessage:"+ctx.getChannel().getId());
			e.getChannel().write(pingMessage);
		}
	}
}
