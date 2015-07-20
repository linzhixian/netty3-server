package com.lzx.framework.socket.netty3.message;

import org.springframework.stereotype.Service;

import com.lzx.framework.socket.AbstractMessage;

@Service("testRequest_MessageLZX")
public class TestRequest extends AbstractMessage {
	
	public TestRequest() {
		this.setCmd(1);
	}
	private String name="lzx";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}


	
	@Override
	public byte[] encode() {
		return name.getBytes();
	}

	@Override
	public void decode(byte[] content) {
		name=new String(content);
	}



}
