package net.minecraft.server.v1_16_R2;

public interface PacketStatusOutListener extends PacketListener {
  void a(PacketStatusOutServerInfo paramPacketStatusOutServerInfo);
  
  void a(PacketStatusOutPong paramPacketStatusOutPong);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketStatusOutListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */