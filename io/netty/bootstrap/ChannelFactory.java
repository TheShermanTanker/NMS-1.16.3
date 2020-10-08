package io.netty.bootstrap;

@Deprecated
public interface ChannelFactory<T extends io.netty.channel.Channel> {
  T newChannel();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\bootstrap\ChannelFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */