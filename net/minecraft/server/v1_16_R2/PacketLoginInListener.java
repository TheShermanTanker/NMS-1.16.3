package net.minecraft.server.v1_16_R2;

public interface PacketLoginInListener extends PacketListener {
  void a(PacketLoginInStart paramPacketLoginInStart);
  
  void a(PacketLoginInEncryptionBegin paramPacketLoginInEncryptionBegin);
  
  void a(PacketLoginInCustomPayload paramPacketLoginInCustomPayload);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketLoginInListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */