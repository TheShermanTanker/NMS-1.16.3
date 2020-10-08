package net.minecraft.server.v1_16_R2;

public interface PacketLoginOutListener extends PacketListener {
  void a(PacketLoginOutEncryptionBegin paramPacketLoginOutEncryptionBegin);
  
  void a(PacketLoginOutSuccess paramPacketLoginOutSuccess);
  
  void a(PacketLoginOutDisconnect paramPacketLoginOutDisconnect);
  
  void a(PacketLoginOutSetCompression paramPacketLoginOutSetCompression);
  
  void a(PacketLoginOutCustomPayload paramPacketLoginOutCustomPayload);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketLoginOutListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */