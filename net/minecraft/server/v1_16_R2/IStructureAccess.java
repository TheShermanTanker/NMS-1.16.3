package net.minecraft.server.v1_16_R2;

import it.unimi.dsi.fastutil.longs.LongSet;
import java.util.Map;
import javax.annotation.Nullable;

public interface IStructureAccess {
  @Nullable
  StructureStart<?> a(StructureGenerator<?> paramStructureGenerator);
  
  void a(StructureGenerator<?> paramStructureGenerator, StructureStart<?> paramStructureStart);
  
  LongSet b(StructureGenerator<?> paramStructureGenerator);
  
  void a(StructureGenerator<?> paramStructureGenerator, long paramLong);
  
  Map<StructureGenerator<?>, LongSet> v();
  
  void b(Map<StructureGenerator<?>, LongSet> paramMap);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IStructureAccess.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */