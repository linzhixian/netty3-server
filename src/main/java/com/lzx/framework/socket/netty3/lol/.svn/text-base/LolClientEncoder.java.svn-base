package com.lzx.framework.socket.netty3.lol;

import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.lzx.framework.socket.IChannelBuffer;
import com.lzx.framework.socket.ICreateBuffer;
import com.lzx.framework.socket.netty3.Netty3ChannelBuffer;


/**
 * <pre>
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
 * 
 * 
 * date: 2013-1-24 下午2:08:01 <br/>
 * 
 * @author lzx
 * @version
 * @since JDK 1.6
 */
public class LolClientEncoder  extends OneToOneEncoder implements ICreateBuffer{

	
	private LolEncoder encoder=new LolEncoder(this);
	
	public LolClientEncoder() {
		System.out.println("new LolClientEncoder----------:"+this);
	}
	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		return encoder.encode(msg);
	}
	
	@Override
	public IChannelBuffer createChannelBuffer() {
		return new Netty3ChannelBuffer(ChannelBuffers.dynamicBuffer());
	}
}
