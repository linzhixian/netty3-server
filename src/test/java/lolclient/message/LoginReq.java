package lolclient.message;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.lzx.framework.socket.AbstractMessage;
import com.lzx.framework.socket.netty3.ChannelBufferUtil;

public class LoginReq extends AbstractMessage {

	private String userName;
	
	public LoginReq(String id) {
		this.setCmd(1);
		this.userName=id;
	}
	
	@Override
	public byte[] encode() {
		ChannelBuffer cb = ChannelBuffers.dynamicBuffer(4);
		ChannelBufferUtil.writeUTF(cb, userName);
		return cb.array();
	}

	@Override
	public void decode(byte[] content) throws Exception {
		
	}



}
