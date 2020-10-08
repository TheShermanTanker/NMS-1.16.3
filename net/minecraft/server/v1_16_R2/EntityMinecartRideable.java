/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class EntityMinecartRideable
/*    */   extends EntityMinecartAbstract
/*    */ {
/*    */   public EntityMinecartRideable(EntityTypes<?> var0, World var1) {
/* 11 */     super(var0, var1);
/*    */   }
/*    */   
/*    */   public EntityMinecartRideable(World var0, double var1, double var3, double var5) {
/* 15 */     super(EntityTypes.MINECART, var0, var1, var3, var5);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(EntityHuman var0, EnumHand var1) {
/* 20 */     if (var0.ep()) {
/* 21 */       return EnumInteractionResult.PASS;
/*    */     }
/*    */     
/* 24 */     if (isVehicle()) {
/* 25 */       return EnumInteractionResult.PASS;
/*    */     }
/* 27 */     if (!this.world.isClientSide) {
/* 28 */       return var0.startRiding(this) ? EnumInteractionResult.CONSUME : EnumInteractionResult.PASS;
/*    */     }
/*    */     
/* 31 */     return EnumInteractionResult.SUCCESS;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(int var0, int var1, int var2, boolean var3) {
/* 36 */     if (var3) {
/* 37 */       if (isVehicle()) {
/* 38 */         ejectPassengers();
/*    */       }
/* 40 */       if (getType() == 0) {
/* 41 */         d(-n());
/* 42 */         c(10);
/* 43 */         setDamage(50.0F);
/* 44 */         velocityChanged();
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public EntityMinecartAbstract.EnumMinecartType getMinecartType() {
/* 51 */     return EntityMinecartAbstract.EnumMinecartType.RIDEABLE;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMinecartRideable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */