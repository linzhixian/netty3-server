package lolclient.message;

import server.util.protobuf.ChatMessageContent;

import com.lzx.framework.socket.AbstractMessage;

public class EnterChatChannelReq  extends AbstractMessage {

    /* ---------   Chat   Start  ------------- */
    
    public static final short C2S_ENTER_CHATCHANNEL = 1720;     //进入聊天频道
    public static final short S2C_ENTER_CHATCHANNEL = 1721;     //进入聊天频道应答
    public static final short C2S_SEND_CHAT=1723;                //客户端发送聊天
    public static final short C2S_ChAT_BROADCAST=1724;          //服务器广播聊天
    public static final short C2S_CHAT_USER_ONLINE_StATUS=1725; //客户端收到用户上线或下线通知
    
    /* ---------   Chat   End  ------------- */
    
	private int channelId;
	
	public EnterChatChannelReq(int channelId) {
		this.setCmd(C2S_ENTER_CHATCHANNEL);
		this.channelId=channelId;
	}
	
	@Override
	public byte[] encode() {
		ChatMessageContent.EnterChatChannelReq.Builder builder=ChatMessageContent.EnterChatChannelReq.newBuilder();
		builder.setChannelID(channelId);
		return builder.build().toByteArray();
	}

	@Override
	public void decode(byte[] content) throws Exception {
		ChatMessageContent.EnterChatChannelReq req=(ChatMessageContent.EnterChatChannelReq)ChatMessageContent.EnterChatChannelReq.parseFrom(content);
		this.channelId=req.getChannelID();
	}



}
