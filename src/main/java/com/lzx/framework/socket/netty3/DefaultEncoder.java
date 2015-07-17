package com.lzx.framework.socket.netty3;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.lzx.framework.socket.CodecFactory;
import com.lzx.framework.socket.IEncoder;

/**
 * <pre>
 * 消息结构
 * +-------------------------+-------+-------------+
 * |         Header          | body  |   |
 * |-------------+-----------+---------------------+
 * |PacketLength | CommandID | body  | |
 * +-------------+-----------+-------+-------------+
 * 各个数据项的长度
 * PacketLength=2 bit   为整个消息的长度
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
public class DefaultEncoder extends OneToOneEncoder  {

	private IEncoder defaultEncoder;
	public DefaultEncoder(CodecFactory factory) {
		defaultEncoder=factory.createEncoder(CreateBuffer.getInstance());
	}


	@Override
	public Object encode(ChannelHandlerContext ctx, Channel channel, Object msg) throws Exception {
		//IInnerEnable obj=(IInnerEnable)defaultEncoder.encode(msg);
//		/return obj.innerObj();
		return defaultEncoder.encode(msg);
	}



}
