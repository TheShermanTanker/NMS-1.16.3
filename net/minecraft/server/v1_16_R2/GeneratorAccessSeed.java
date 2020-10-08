package net.minecraft.server.v1_16_R2;

import java.util.stream.Stream;

public interface GeneratorAccessSeed extends WorldAccess {
  long getSeed();
  
  Stream<? extends StructureStart<?>> a(SectionPosition paramSectionPosition, StructureGenerator<?> paramStructureGenerator);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GeneratorAccessSeed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */