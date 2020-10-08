package net.minecraft.server.v1_16_R2;

import com.mojang.brigadier.StringReader;
import com.mojang.brigadier.exceptions.CommandSyntaxException;

public interface ParticleParam {
  Particle<?> getParticle();
  
  void a(PacketDataSerializer paramPacketDataSerializer);
  
  String a();
  
  @Deprecated
  public static interface a<T extends ParticleParam> {
    T b(Particle<T> param1Particle, StringReader param1StringReader) throws CommandSyntaxException;
    
    T b(Particle<T> param1Particle, PacketDataSerializer param1PacketDataSerializer);
  }
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ParticleParam.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */