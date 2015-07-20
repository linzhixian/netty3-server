import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;


public class ChannelBufferTest {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		ChannelBuffer buffer=ChannelBuffers.dynamicBuffer();
		System.out.println(buffer.writerIndex());
		buffer.writeShort(0);
		buffer.writeInt(100);
		System.out.println(buffer.writerIndex());
		buffer.setShort(0, buffer.writerIndex());
		System.out.println(buffer.readShort());
	}

}
