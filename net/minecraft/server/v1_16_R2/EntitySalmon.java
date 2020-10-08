/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySalmon
/*    */   extends EntityFishSchool
/*    */ {
/*    */   public EntitySalmon(EntityTypes<? extends EntitySalmon> var0, World var1) {
/* 13 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int eN() {
/* 20 */     return 5;
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack eK() {
/* 25 */     return new ItemStack(Items.SALMON_BUCKET);
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAmbient() {
/* 30 */     return SoundEffects.ENTITY_SALMON_AMBIENT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundDeath() {
/* 35 */     return SoundEffects.ENTITY_SALMON_DEATH;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 40 */     return SoundEffects.ENTITY_SALMON_HURT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundFlop() {
/* 45 */     return SoundEffects.ENTITY_SALMON_FLOP;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySalmon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */