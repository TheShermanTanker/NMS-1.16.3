/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class DragonControllerCharge
/*    */   extends AbstractDragonController
/*    */ {
/* 11 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private Vec3D c;
/*    */   
/*    */   private int d;
/*    */   
/*    */   public DragonControllerCharge(EntityEnderDragon var0) {
/* 18 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void c() {
/* 23 */     if (this.c == null) {
/* 24 */       LOGGER.warn("Aborting charge player as no target was set.");
/* 25 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.HOLDING_PATTERN);
/*    */       
/*    */       return;
/*    */     } 
/* 29 */     if (this.d > 0 && 
/* 30 */       this.d++ >= 10) {
/* 31 */       this.a.getDragonControllerManager().setControllerPhase(DragonControllerPhase.HOLDING_PATTERN);
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 36 */     double var0 = this.c.c(this.a.locX(), this.a.locY(), this.a.locZ());
/* 37 */     if (var0 < 100.0D || var0 > 22500.0D || this.a.positionChanged || this.a.v) {
/* 38 */       this.d++;
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 44 */     this.c = null;
/* 45 */     this.d = 0;
/*    */   }
/*    */   
/*    */   public void a(Vec3D var0) {
/* 49 */     this.c = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public float f() {
/* 54 */     return 3.0F;
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public Vec3D g() {
/* 60 */     return this.c;
/*    */   }
/*    */ 
/*    */   
/*    */   public DragonControllerPhase<DragonControllerCharge> getControllerPhase() {
/* 65 */     return DragonControllerPhase.CHARGING_PLAYER;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerCharge.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */