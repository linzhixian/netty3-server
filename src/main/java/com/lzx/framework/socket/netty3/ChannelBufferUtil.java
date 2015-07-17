package com.lzx.framework.socket.netty3;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.BigEndianHeapChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffer;

public class ChannelBufferUtil {

	public static final Charset UTF_8 = Charset.forName("UTF-8");

	public static final String readUTF(ChannelBuffer buffer) {
		int bufferSize = buffer.readUnsignedShort();
		if (bufferSize == 0) {
			return "";
		}
		byte[] s = new byte[bufferSize];
		buffer.readBytes(s);
		return new String(s, UTF_8);
	}

	public static final String readUTF(ChannelBuffer buffer, int limit) {

		int bufferSize = buffer.readUnsignedShort();
		if (bufferSize == 0) {
			return "";
		}

		if (bufferSize > limit) {
			buffer.skipBytes(bufferSize); // 跳过这个utf占用的空间
			throw new IllegalArgumentException("UTF length exceed limit");
		}

		byte[] s = new byte[bufferSize];
		buffer.readBytes(s);

		return new String(s, UTF_8);
	}

	public static final boolean readBoolean(ChannelBuffer buffer) {
		return buffer.readByte() != 0;
	}

	public static final void writeUTF(ChannelBuffer buffer, String toWrite) {
		if (toWrite == null)
			buffer.writeShort(0);
		else {
			byte[] b = encode(toWrite);
			buffer.writeShort(b.length);
			buffer.writeBytes(b);
		}
	}

	public static byte[] encode(String toDo) {
		return toDo.getBytes(UTF_8);
	}

	public static final void writeUTF(ChannelBuffer buffer, byte[] toWrite) {
		if (toWrite == null)
			buffer.writeShort(0);
		else {
			buffer.writeShort(toWrite.length);
			buffer.writeBytes(toWrite);
		}
	}

	public static final void writeBoolean(ChannelBuffer buffer, boolean toWrite) {
		buffer.writeByte(toWrite ? 1 : 0);
	}

	public static final void writeTrue(ChannelBuffer buffer) {
		buffer.writeByte(1);
	}

	public static final void writeFalse(ChannelBuffer buffer) {
		buffer.writeByte(0);
	}

	public static final void writeShortAnsiString(ChannelBuffer buffer, String toWrite) {
		if (toWrite == null)
			buffer.writeByte(0);
		else {
			byte[] b = toWrite.getBytes();
			buffer.writeByte(b.length);
			buffer.writeBytes(b);
		}
	}

	/**
	 * 注意：length是byte
	 * 
	 * @param buffer
	 * @param toWrite
	 */
	public static final void writeIntArray(ChannelBuffer buffer, int[] toWrite) {
		if (toWrite == null)
			buffer.writeByte(0);
		else {
			buffer.writeByte(toWrite.length);
			for (int i : toWrite) {
				buffer.writeInt(i);
			}
		}
	}

	public static final ChannelBuffer trimBuffer(ChannelBuffer buffer) {
		ChannelBuffer result = new BigEndianHeapChannelBuffer(buffer.writerIndex());
		result.writeBytes(buffer);
		return result;
	}
}
