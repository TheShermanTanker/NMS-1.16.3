package io.netty.handler.codec.http;

public interface HttpResponse extends HttpMessage {
  @Deprecated
  HttpResponseStatus getStatus();
  
  HttpResponseStatus status();
  
  HttpResponse setStatus(HttpResponseStatus paramHttpResponseStatus);
  
  HttpResponse setProtocolVersion(HttpVersion paramHttpVersion);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http\HttpResponse.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */