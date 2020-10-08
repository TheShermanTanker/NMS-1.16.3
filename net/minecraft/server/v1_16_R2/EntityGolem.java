/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public abstract class EntityGolem
/*    */   extends EntityCreature
/*    */ {
/*    */   protected EntityGolem(EntityTypes<? extends EntityGolem> var0, World var1) {
/* 13 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b(float var0, float var1) {
/* 18 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected SoundEffect getSoundAmbient() {
/* 24 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 30 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected SoundEffect getSoundDeath() {
/* 36 */     return null;
/*    */   }
/*    */ 
/*    */   
/*    */   public int D() {
/* 41 */     return 120;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isTypeNotPersistent(double var0) {
/* 46 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityGolem.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */