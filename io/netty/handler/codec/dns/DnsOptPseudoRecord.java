package io.netty.handler.codec.dns;

public interface DnsOptPseudoRecord extends DnsRecord {
  int extendedRcode();
  
  int version();
  
  int flags();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\dns\DnsOptPseudoRecord.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */