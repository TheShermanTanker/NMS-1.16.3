package io.netty.channel.unix;

import io.netty.channel.ServerChannel;

public interface ServerDomainSocketChannel extends ServerChannel, UnixChannel {
  DomainSocketAddress remoteAddress();
  
  DomainSocketAddress localAddress();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\channe\\unix\ServerDomainSocketChannel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */