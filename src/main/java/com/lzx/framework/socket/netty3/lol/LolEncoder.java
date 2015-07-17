package com.lzx.framework.socket.netty3.lol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.lzx.framework.socket.IChannelBuffer;
import com.lzx.framework.socket.ICreateBuffer;
import com.lzx.framework.socket.IEncoder;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.utils.StringUtil;

/**
 *  * <pre>
 * 消息结构
 * +-------------------------+-------+-------------------------------------+
 * |         Header                              | body  |  * Mac       |
 * |-------------+-----------+-----------------------------------------------+
 * |PacketLength | checkcode|OFFSET| CommandID | body  |  * crc32 |
 * +-------------+-----------+-------+---------------------------------------+
 * 各个数据项的长度
 * PacketLength=2 bit   为整个消息的长度
 * checkcode=1 bit
 * OFFSET=1  bit
 * CommandID=2 bit
 * body= change
 * </pre>
 * @author lzx
 *注意：该编码器不支持多线程多session，必须一个session�?��Encoder
 */
public class LolEncoder implements IEncoder {
	private static final Logger logger = LoggerFactory.getLogger(LolEncoder.class);
	private ICreateBuffer createBuffer ;
	
	private int msgOffset=0;
	
	public LolEncoder(ICreateBuffer createBuffer) {
		this.createBuffer=createBuffer;
	}
	
	@Override
	public Object encode(Object msg) throws Exception {
		if (!(msg instanceof IMessage)) {
			return msg;
		}
		IMessage msgObj = (IMessage) msg;
		int commandId = msgObj.getCmd();
		//logger.debug("encode:"+StringUtil.beanToString(msgObj));
		byte[] bodyBytes = msgObj.encode();
		if(bodyBytes==null) bodyBytes=new byte[0];
		IChannelBuffer buffer = createBuffer.createChannelBuffer();
		buffer.writeShort(0);//先占�?
		buffer.writeByte(0);//先占�?
		buffer.writeByte(0);//先占�?
		buffer.writeShort(((msgOffset++ & 7) << 13) | commandId);	
		buffer.writeBytes(bodyBytes);
		buffer.setByte(3, getOffsetByte(msgOffset));//写offsset
		buffer.setByte(2,getBytesCode(buffer.array()));
		buffer.setShort(0, 6+bodyBytes.length);//写消息�?长度
		
/*		// 写消息安全码
		writeMac(buffer, bodyBytes);*/
		return buffer.innerObj();
	}


	private int getBytesCode(byte[] bytes) 
	{
		int result = 0;
		for(int i= bytes.length - 1 ;i >= 0 ;i--)
		{
			result += bytes[i];
		}
		//相与取后8�?000 0000 1111 1111
		return result & 0xff;
	}
	private int getOffsetByte(int offset)
	{
		int  v = offset;
		v ^= v >> 8;
		v ^= v >> 4;
		v &= 0xff;
		return v;
	}
}
