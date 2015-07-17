package com.lzx.framework.socket.netty3.message;

import java.lang.annotation.Annotation;

import org.springframework.stereotype.Service;

import com.google.protobuf.InvalidProtocolBufferException;
import com.lzx.framework.socket.AbstractMessage;
import com.lzx.framework.socket.MessageID;
import com.lzx.framework.socket.netty3.proto.ChatRequestProto;



@Service("chatRequest_MessageLZX")
@MessageID(id = 2)
public class ChatRequest extends AbstractMessage {

	private String msg;

	public ChatRequest() {
		
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	@Override
	public byte[] encode() {
		ChatRequestProto.ChatRequest.Builder builder = ChatRequestProto.ChatRequest.newBuilder();
		builder.setMsg(msg);
		return builder.build().toByteArray();
	}

	@Override
	public void decode(byte[] content) throws InvalidProtocolBufferException {
		com.lzx.framework.socket.netty3.proto.ChatRequestProto.ChatRequest chatRequest = ChatRequestProto.ChatRequest
				.parseFrom(content);
		msg = chatRequest.getMsg();
	}

	public static void main(String[] args) {
		ChatRequest c = new ChatRequest();
		Annotation[] as=c.getClass().getAnnotations();
		for(int i=0;i<as.length;i++)
			System.out.println(as[i]);
		
		MessageID msgid=c.getClass().getAnnotation(MessageID.class);
		System.out.println(msgid.id());
	}
}
