package io.netty.handler.codec.spdy;

public interface SpdyGoAwayFrame extends SpdyFrame {
  int lastGoodStreamId();
  
  SpdyGoAwayFrame setLastGoodStreamId(int paramInt);
  
  SpdySessionStatus status();
  
  SpdyGoAwayFrame setStatus(SpdySessionStatus paramSpdySessionStatus);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\spdy\SpdyGoAwayFrame.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */