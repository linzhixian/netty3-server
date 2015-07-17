/** Date:2013-1-24下午2:07:37 Copyright (c) 2013, 三三得玖 All Rights Reserved.
 */
package com.lzx.framework.socket.netty3;

import java.util.zip.CRC32;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.frame.LengthFieldBasedFrameDecoder;

import com.lzx.framework.socket.CodecFactory;
import com.lzx.framework.socket.IDecoder;
import com.lzx.framework.socket.IMessageDispatch;
import com.lzx.framework.socket.Length;
import com.lzx.framework.socket.exception.CrcWrongException;


public class DefaultDecoder extends LengthFieldBasedFrameDecoder {

	private IMessageDispatch messageDispath;
	private IDecoder decoder;
	
    public DefaultDecoder( IMessageDispatch messageDispath,CodecFactory codecFactory) {
        this(2400, 0, Length.PACKET_LENGTH, -Length.PACKET_LENGTH, 0);
        this.messageDispath=messageDispath;
        decoder=codecFactory.createDecoder( this.messageDispath);
    }

    public DefaultDecoder(int maxFrameLength, int lengthFieldOffset, int lengthFieldLength, int lengthAdjustment, int initialBytesToStrip) {
        super(maxFrameLength, lengthFieldOffset, lengthFieldLength, lengthAdjustment, initialBytesToStrip);
    }
    public Object decode(ChannelHandlerContext ctx, Channel channel, ChannelBuffer channelBuffer) throws Exception
    {
    	
        ChannelBuffer buffer = (ChannelBuffer) super.decode(ctx, channel, channelBuffer);
        return decoder.decode(new Netty3ChannelBuffer(buffer));
    }
    /**
     * 描述:如方法名. <br/>
     * 
     * @author Administrator
     * @param bodyBytes
     * @param crcValue
     * @throws CrcWrongException
     */
    private void checkCrc32(byte[] bodyBytes, int crcValue) throws CrcWrongException
    {
        // �?��crc32完整校验�?
        CRC32 crc32 = new CRC32();
        crc32.update(bodyBytes);
        int targetValue = (int) crc32.getValue();
        if (targetValue != crcValue) {
            throw new CrcWrongException();
        }
    }
}
