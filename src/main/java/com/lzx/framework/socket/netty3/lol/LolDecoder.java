package com.lzx.framework.socket.netty3.lol;

import com.lzx.framework.socket.IChannelBuffer;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.IMessageDispatch;
import com.lzx.framework.socket.Length;
import com.lzx.framework.socket.codec.AbstractDecoder;

public class LolDecoder extends AbstractDecoder {

	public LolDecoder(IMessageDispatch messageDispath) {
		super(messageDispath);
	}

	@Override
	public IMessage decode(IChannelBuffer buffer) throws Exception {
		int packetLength = buffer.readUnsignedShort();
		if (packetLength < Length.HEADER_LENGTH) {
			throw new RuntimeException("Invalid packet length:[" + packetLength + "]");
		}
		if (buffer.readableBytes() < packetLength - Length.SHORT2) {
			buffer.resetReaderIndex();
			return null;
		}
		int commandId = buffer.readUnsignedShort();
		byte[] bodyBytes=new byte[ buffer.readableBytes()];
		 buffer.getBytes(buffer.readerIndex(),bodyBytes );
		/* byte[] desBytes = buffer.readBytes(Length.DES_LENGTH).array(); */
		// int crcValue = buffer.readInt();
		// crc32校验
		// checkCrc32(bodyBytes, crcValue);
		// des校验
		// checkDes(bodyBytes,desBytes);
		// 解压�?
		// bodyBytes = appContext.uncompress(commandId, bodyBytes);

		// RequestId
		
		IMessage message = extractMessage(bodyBytes, commandId);
		//System.out.println("decoder:" + message);
		return message;
	}

}
