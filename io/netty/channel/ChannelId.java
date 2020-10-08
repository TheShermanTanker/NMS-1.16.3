package io.netty.channel;

import java.io.Serializable;

public interface ChannelId extends Serializable, Comparable<ChannelId> {
  String asShortText();
  
  String asLongText();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\channel\ChannelId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */