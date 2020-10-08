/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityHorseMule
/*    */   extends EntityHorseChestedAbstract
/*    */ {
/*    */   public EntityHorseMule(EntityTypes<? extends EntityHorseMule> var0, World var1) {
/* 15 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAmbient() {
/* 20 */     super.getSoundAmbient();
/* 21 */     return SoundEffects.ENTITY_MULE_AMBIENT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAngry() {
/* 26 */     super.getSoundAngry();
/* 27 */     return SoundEffects.ENTITY_MULE_ANGRY;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundDeath() {
/* 32 */     super.getSoundDeath();
/* 33 */     return SoundEffects.ENTITY_MULE_DEATH;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected SoundEffect fg() {
/* 39 */     return SoundEffects.ENTITY_MULE_EAT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 44 */     super.getSoundHurt(var0);
/* 45 */     return SoundEffects.ENTITY_MULE_HURT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void eO() {
/* 50 */     playSound(SoundEffects.ENTITY_MULE_CHEST, 1.0F, (this.random.nextFloat() - this.random.nextFloat()) * 0.2F + 1.0F);
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityAgeable createChild(WorldServer var0, EntityAgeable var1) {
/* 55 */     return EntityTypes.MULE.a(var0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityHorseMule.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */