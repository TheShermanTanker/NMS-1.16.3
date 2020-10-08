package com.destroystokyo.paper.network;

import java.net.InetSocketAddress;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public interface NetworkClient {
  @NotNull
  InetSocketAddress getAddress();
  
  int getProtocolVersion();
  
  @Nullable
  InetSocketAddress getVirtualHost();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\network\NetworkClient.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */