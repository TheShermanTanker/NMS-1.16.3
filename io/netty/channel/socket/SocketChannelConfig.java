package io.netty.channel.socket;

import io.netty.buffer.ByteBufAllocator;
import io.netty.channel.ChannelConfig;
import io.netty.channel.MessageSizeEstimator;
import io.netty.channel.RecvByteBufAllocator;
import io.netty.channel.WriteBufferWaterMark;

public interface SocketChannelConfig extends ChannelConfig {
  boolean isTcpNoDelay();
  
  SocketChannelConfig setTcpNoDelay(boolean paramBoolean);
  
  int getSoLinger();
  
  SocketChannelConfig setSoLinger(int paramInt);
  
  int getSendBufferSize();
  
  SocketChannelConfig setSendBufferSize(int paramInt);
  
  int getReceiveBufferSize();
  
  SocketChannelConfig setReceiveBufferSize(int paramInt);
  
  boolean isKeepAlive();
  
  SocketChannelConfig setKeepAlive(boolean paramBoolean);
  
  int getTrafficClass();
  
  SocketChannelConfig setTrafficClass(int paramInt);
  
  boolean isReuseAddress();
  
  SocketChannelConfig setReuseAddress(boolean paramBoolean);
  
  SocketChannelConfig setPerformancePreferences(int paramInt1, int paramInt2, int paramInt3);
  
  boolean isAllowHalfClosure();
  
  SocketChannelConfig setAllowHalfClosure(boolean paramBoolean);
  
  SocketChannelConfig setConnectTimeoutMillis(int paramInt);
  
  @Deprecated
  SocketChannelConfig setMaxMessagesPerRead(int paramInt);
  
  SocketChannelConfig setWriteSpinCount(int paramInt);
  
  SocketChannelConfig setAllocator(ByteBufAllocator paramByteBufAllocator);
  
  SocketChannelConfig setRecvByteBufAllocator(RecvByteBufAllocator paramRecvByteBufAllocator);
  
  SocketChannelConfig setAutoRead(boolean paramBoolean);
  
  SocketChannelConfig setAutoClose(boolean paramBoolean);
  
  SocketChannelConfig setMessageSizeEstimator(MessageSizeEstimator paramMessageSizeEstimator);
  
  SocketChannelConfig setWriteBufferWaterMark(WriteBufferWaterMark paramWriteBufferWaterMark);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\channel\socket\SocketChannelConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */