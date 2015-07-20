package com.lzx.framework.socket.netty3.lol;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

import com.lzx.framework.socket.IMessageDispatch;
import com.lzx.framework.socket.Length;
import com.lzx.framework.socket.netty3.Netty3ChannelBuffer;

public class LolClientDecoder extends LengthFieldBasedFrameDecoder {
	private LolDecoder decoder;

	public LolClientDecoder(IMessageDispatch messageDispath) {
		this(2400, 0, Length.PACKET_LENGTH, -Length.PACKET_LENGTH, 0);
		decoder = new LolDecoder(messageDispath);
	}

	public LolClientDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment,
			int initialBytesToStrip) {
		super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
	}

	public Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer channelBuffer) throws Exception {

		ChannelBuffer buffer = (ChannelBuffer) super.decode(ctx, channel, channelBuffer);
		if(buffer==null) return null;
		return decoder.decode(new Netty3ChannelBuffer(buffer));
	}

}
