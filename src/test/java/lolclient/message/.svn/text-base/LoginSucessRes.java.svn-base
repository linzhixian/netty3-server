package lolclient.message;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;

import com.lzx.framework.socket.AbstractMessage;

public class LoginSucessRes extends AbstractMessage {

	private int id;
	
	public LoginSucessRes() {
		this.setCmd(2);
	}

	@Override
	public byte[] encode() {
		return null;
	}

	@Override
	public void decode(byte[] content) throws Exception {
		ChannelBuffer cb=ChannelBuffers.wrappedBuffer(content);
		id=cb.readInt();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
