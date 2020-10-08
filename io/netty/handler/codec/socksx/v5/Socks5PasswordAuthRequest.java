package io.netty.handler.codec.socksx.v5;

public interface Socks5PasswordAuthRequest extends Socks5Message {
  String username();
  
  String password();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\socksx\v5\Socks5PasswordAuthRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */