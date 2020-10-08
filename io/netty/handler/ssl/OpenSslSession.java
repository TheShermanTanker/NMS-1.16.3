package io.netty.handler.ssl;

import javax.net.ssl.SSLException;
import javax.net.ssl.SSLSession;

interface OpenSslSession extends SSLSession {
  void handshakeFinished() throws SSLException;
  
  void tryExpandApplicationBufferSize(int paramInt);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\ssl\OpenSslSession.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */