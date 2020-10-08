/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Random;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntitySkeletonStray
/*    */   extends EntitySkeletonAbstract
/*    */ {
/*    */   public EntitySkeletonStray(EntityTypes<? extends EntitySkeletonStray> var0, World var1) {
/* 22 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */   
/*    */   public static boolean a(EntityTypes<EntitySkeletonStray> var0, WorldAccess var1, EnumMobSpawn var2, BlockPosition var3, Random var4) {
/* 26 */     return (b((EntityTypes)var0, var1, var2, var3, var4) && (var2 == EnumMobSpawn.SPAWNER || var1
/* 27 */       .e(var3)));
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundAmbient() {
/* 32 */     return SoundEffects.ENTITY_STRAY_AMBIENT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 37 */     return SoundEffects.ENTITY_STRAY_HURT;
/*    */   }
/*    */ 
/*    */   
/*    */   protected SoundEffect getSoundDeath() {
/* 42 */     return SoundEffects.ENTITY_STRAY_DEATH;
/*    */   }
/*    */ 
/*    */   
/*    */   SoundEffect eK() {
/* 47 */     return SoundEffects.ENTITY_STRAY_STEP;
/*    */   }
/*    */ 
/*    */   
/*    */   protected EntityArrow b(ItemStack var0, float var1) {
/* 52 */     EntityArrow var2 = super.b(var0, var1);
/* 53 */     if (var2 instanceof EntityTippedArrow) {
/* 54 */       ((EntityTippedArrow)var2).addEffect(new MobEffect(MobEffects.SLOWER_MOVEMENT, 600));
/*    */     }
/* 56 */     return var2;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntitySkeletonStray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */