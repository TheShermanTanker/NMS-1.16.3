package io.netty.channel;

public interface MessageSizeEstimator {
  Handle newHandle();
  
  public static interface Handle {
    int size(Object param1Object);
  }
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\channel\MessageSizeEstimator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */