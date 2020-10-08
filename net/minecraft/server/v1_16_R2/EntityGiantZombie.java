/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityGiantZombie
/*    */   extends EntityMonster
/*    */ {
/*    */   public EntityGiantZombie(EntityTypes<? extends EntityGiantZombie> var0, World var1) {
/* 14 */     super((EntityTypes)var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   protected float b(EntityPose var0, EntitySize var1) {
/* 19 */     return 10.440001F;
/*    */   }
/*    */   
/*    */   public static AttributeProvider.Builder m() {
/* 23 */     return EntityMonster.eR()
/* 24 */       .a(GenericAttributes.MAX_HEALTH, 100.0D)
/* 25 */       .a(GenericAttributes.MOVEMENT_SPEED, 0.5D)
/* 26 */       .a(GenericAttributes.ATTACK_DAMAGE, 50.0D);
/*    */   }
/*    */ 
/*    */   
/*    */   public float a(BlockPosition var0, IWorldReader var1) {
/* 31 */     return var1.y(var0) - 0.5F;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityGiantZombie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */