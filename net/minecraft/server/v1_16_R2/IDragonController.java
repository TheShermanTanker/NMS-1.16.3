package net.minecraft.server.v1_16_R2;

import javax.annotation.Nullable;

public interface IDragonController {
  boolean a();
  
  void b();
  
  void c();
  
  void a(EntityEnderCrystal paramEntityEnderCrystal, BlockPosition paramBlockPosition, DamageSource paramDamageSource, @Nullable EntityHuman paramEntityHuman);
  
  void d();
  
  void e();
  
  float f();
  
  float h();
  
  DragonControllerPhase<? extends IDragonController> getControllerPhase();
  
  @Nullable
  Vec3D g();
  
  float a(DamageSource paramDamageSource, float paramFloat);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IDragonController.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */