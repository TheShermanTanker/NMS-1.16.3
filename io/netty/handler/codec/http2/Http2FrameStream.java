package io.netty.handler.codec.http2;

public interface Http2FrameStream {
  int id();
  
  Http2Stream.State state();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http2\Http2FrameStream.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */