/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityCod
/*    */   extends EntityFishSchool
/*    */ {
/*    */   public EntityCod(EntityTypes<? extends EntityCod> var0, World var1) {
/* 13 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected ItemStack eK() {
/* 18 */     return new ItemStack(Items.COD_BUCKET);
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAmbient() {
/* 23 */     return SoundEffects.ENTITY_COD_AMBIENT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundDeath() {
/* 28 */     return SoundEffects.ENTITY_COD_DEATH;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 33 */     return SoundEffects.ENTITY_COD_HURT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundFlop() {
/* 38 */     return SoundEffects.ENTITY_COD_FLOP;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityCod.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */