package com.lzx.framework.socket.netty3;

import org.jboss.netty.buffer.ChannelBuffers;

import com.lzx.framework.socket.IChannelBuffer;
import com.lzx.framework.socket.ICreateBuffer;

public class CreateBuffer implements ICreateBuffer {

	private static ICreateBuffer INSTANCE=new CreateBuffer();
	
	public static ICreateBuffer getInstance() {
		return INSTANCE;
	}
	@Override
	public IChannelBuffer createChannelBuffer() {
		return new Netty3ChannelBuffer(ChannelBuffers.dynamicBuffer());
	}


}
