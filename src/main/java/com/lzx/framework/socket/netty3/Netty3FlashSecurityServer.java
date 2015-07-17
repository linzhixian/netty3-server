package com.lzx.framework.socket.netty3;

import org.jboss.netty.channel.ChannelPipeline;

public class Netty3FlashSecurityServer  extends AbstractNetty3Server {


	public Netty3FlashSecurityServer(int port) {
		super(port);
	}

	public   void addHandler(ChannelPipeline pipeline)  {
		pipeline.addLast("handler",new Netty3FlashSecurityHandler());
	}

}
