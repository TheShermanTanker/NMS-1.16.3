/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySkeleton
/*    */   extends EntitySkeletonAbstract
/*    */ {
/*    */   public EntitySkeleton(EntityTypes<? extends EntitySkeleton> var0, World var1) {
/* 13 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAmbient() {
/* 18 */     return SoundEffects.ENTITY_SKELETON_AMBIENT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 23 */     return SoundEffects.ENTITY_SKELETON_HURT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundDeath() {
/* 28 */     return SoundEffects.ENTITY_SKELETON_DEATH;
/*    */   }
/*    */ 
/*    */   
/*    */   SoundEffect eK() {
/* 33 */     return SoundEffects.ENTITY_SKELETON_STEP;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void dropDeathLoot(DamageSource var0, int var1, boolean var2) {
/* 38 */     super.dropDeathLoot(var0, var1, var2);
/* 39 */     Entity var3 = var0.getEntity();
/* 40 */     if (var3 instanceof EntityCreeper) {
/* 41 */       EntityCreeper var4 = (EntityCreeper)var3;
/* 42 */       if (var4.canCauseHeadDrop()) {
/* 43 */         var4.setCausedHeadDrop();
/* 44 */         a(Items.SKELETON_SKULL);
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySkeleton.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */