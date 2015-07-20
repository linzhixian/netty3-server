package com.lzx.framework.socket.netty3;

import java.io.IOException;
import java.io.OutputStream;

import org.jboss.netty.buffer.ChannelBuffer;

import com.lzx.framework.socket.IChannelBuffer;

public class Netty3ChannelBuffer implements IChannelBuffer {

	private ChannelBuffer buffer;

	public Netty3ChannelBuffer(ChannelBuffer buffer) {
		this.buffer = buffer;
	}
	@Override
	public Object innerObj() {
		return buffer;
	}
	@Override
	public void writeShort(int i) {
		buffer.writeShort(i);
	}

	@Override
	public void writeBytes(byte[] bytes) {
		buffer.writeBytes(bytes);
	}

	@Override
	public int writerIndex() {
		return buffer.writerIndex();
	}

	@Override
	public void setShort(int index, int value) {
		buffer.setShort(index, value);
	}

	@Override
	public void writeInt(int value) {
		buffer.writeInt(value);
	}

	@Override
	public int readableBytes() {
		return buffer.readableBytes();
	}

	@Override
	public void markReaderIndex() {
		buffer.markReaderIndex();
	}

	@Override
	public int readUnsignedShort() {
		return buffer.readUnsignedShort();
	}

	@Override
	public void resetReaderIndex() {
		buffer.resetReaderIndex();
	}

	@Override
	public IChannelBuffer readBytes(int length) {
		return new Netty3ChannelBuffer(buffer.readBytes(length));
	}

	@Override
	public byte[] array() {
		return buffer.array();
	}
	@Override
	public void writeByte(int i) {
		buffer.writeByte(i);
	}
	@Override
	public void setByte(int i, int offsetByte) {
		this.buffer.setByte(i, offsetByte);
	}
	@Override
	public int readUnsignedByte() {
		return buffer.readUnsignedByte();
	}
	@Override
	public int readerIndex() {
		return buffer.readerIndex();
	}
	@Override
	public void getBytes(int readerIndex, OutputStream checkSumStream, int readableBytes) throws IOException {
		buffer.getBytes(readerIndex, checkSumStream, readableBytes);
		
	}
	@Override
	public void getBytes(int readerIndex, byte[]  dst) {
		buffer.getBytes(readerIndex, dst);
	}


}
