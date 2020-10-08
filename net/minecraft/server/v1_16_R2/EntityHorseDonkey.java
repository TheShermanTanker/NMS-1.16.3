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
/*    */ 
/*    */ public class EntityHorseDonkey
/*    */   extends EntityHorseChestedAbstract
/*    */ {
/*    */   public EntityHorseDonkey(EntityTypes<? extends EntityHorseDonkey> var0, World var1) {
/* 16 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAmbient() {
/* 21 */     super.getSoundAmbient();
/* 22 */     return SoundEffects.ENTITY_DONKEY_AMBIENT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAngry() {
/* 27 */     super.getSoundAngry();
/* 28 */     return SoundEffects.ENTITY_DONKEY_ANGRY;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundDeath() {
/* 33 */     super.getSoundDeath();
/* 34 */     return SoundEffects.ENTITY_DONKEY_DEATH;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   protected SoundEffect fg() {
/* 40 */     return SoundEffects.ENTITY_DONKEY_EAT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 45 */     super.getSoundHurt(var0);
/* 46 */     return SoundEffects.ENTITY_DONKEY_HURT;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean mate(EntityAnimal var0) {
/* 51 */     if (var0 == this) {
/* 52 */       return false;
/*    */     }
/*    */     
/* 55 */     if (var0 instanceof EntityHorseDonkey || var0 instanceof EntityHorse) {
/* 56 */       return (fo() && ((EntityHorseAbstract)var0).fo());
/*    */     }
/*    */     
/* 59 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityAgeable createChild(WorldServer var0, EntityAgeable var1) {
/* 64 */     EntityTypes<? extends EntityHorseAbstract> var2 = (var1 instanceof EntityHorse) ? (EntityTypes)EntityTypes.MULE : (EntityTypes)EntityTypes.DONKEY;
/* 65 */     EntityHorseAbstract var3 = var2.a(var0);
/*    */     
/* 67 */     a(var1, var3);
/*    */     
/* 69 */     return var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityHorseDonkey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */