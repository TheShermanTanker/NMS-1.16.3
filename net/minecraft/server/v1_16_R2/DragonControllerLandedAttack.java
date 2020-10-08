/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class DragonControllerLandedAttack
/*    */   extends AbstractDragonControllerLanded
/*    */ {
/*    */   private int b;
/*    */   
/*    */   public DragonControllerLandedAttack(EntityEnderDragon var0) {
/* 13 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void b() {
/* 18 */     this.a.world.a(this.a.locX(), this.a.locY(), this.a.locZ(), SoundEffects.ENTITY_ENDER_DRAGON_GROWL, this.a.getSoundCategory(), 2.5F, 0.8F + this.a.getRandom().nextFloat() * 0.3F, false);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 23 */     if (this.b++ >= 40) {
/* 24 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.SITTING_FLAMING);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 30 */     this.b = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerLandedAttack> getControllerPhase() {
/* 35 */     return DragonControllerPhase.SITTING_ATTACKING;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerLandedAttack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */