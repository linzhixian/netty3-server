package com.lzx.framework.socket.netty3;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.ChannelDownstreamHandler;
import org.jboss.netty.channel.ChannelEvent;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.lzx.framework.socket.flash.FlashSecurityHandler;

public class Netty3FlashSecurityHandler extends SimpleChannelUpstreamHandler implements ChannelDownstreamHandler{

	private FlashSecurityHandler flashSecurityHandler=new FlashSecurityHandler(CreateBuffer.getInstance());
	
	public Netty3FlashSecurityHandler() {
	}
	
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e) throws Exception {
		ChannelBuffer buffer = (ChannelBuffer) e.getMessage();
		flashSecurityHandler.messageReceived(new Netty3Channel(ctx.getChannel()), new Netty3ChannelBuffer(buffer));
	}

	@Override
	public void handleDownstream(ChannelHandlerContext ctx, ChannelEvent e) throws Exception {
        ctx.sendDownstream(e);
	}
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e) {
        if (e.getCause() instanceof java.io.IOException) {

        } else
            e.getCause().printStackTrace();
    }
}
