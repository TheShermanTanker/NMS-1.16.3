/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEnderDragon;
/*    */ import org.bukkit.entity.EnderDragon;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.entity.EnderDragonChangePhaseEvent;
/*    */ 
/*    */ public class DragonControllerManager
/*    */ {
/* 12 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   private final EntityEnderDragon enderDragon;
/* 14 */   private final IDragonController[] dragonControllers = new IDragonController[DragonControllerPhase.c()];
/*    */   private IDragonController currentDragonController;
/*    */   
/*    */   public DragonControllerManager(EntityEnderDragon entityenderdragon) {
/* 18 */     this.enderDragon = entityenderdragon;
/* 19 */     setControllerPhase(DragonControllerPhase.HOVER);
/*    */   }
/*    */   
/*    */   public void setControllerPhase(DragonControllerPhase<?> dragoncontrollerphase) {
/* 23 */     if (this.currentDragonController == null || dragoncontrollerphase != this.currentDragonController.getControllerPhase()) {
/* 24 */       if (this.currentDragonController != null) {
/* 25 */         this.currentDragonController.e();
/*    */       }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */       
/* 32 */       EnderDragonChangePhaseEvent event = new EnderDragonChangePhaseEvent((EnderDragon)this.enderDragon.getBukkitEntity(), (this.currentDragonController == null) ? null : CraftEnderDragon.getBukkitPhase(this.currentDragonController.getControllerPhase()), CraftEnderDragon.getBukkitPhase(dragoncontrollerphase));
/*    */       
/* 34 */       this.enderDragon.world.getServer().getPluginManager().callEvent((Event)event);
/* 35 */       if (event.isCancelled()) {
/*    */         return;
/*    */       }
/* 38 */       dragoncontrollerphase = CraftEnderDragon.getMinecraftPhase(event.getNewPhase());
/*    */ 
/*    */       
/* 41 */       this.currentDragonController = b(dragoncontrollerphase);
/* 42 */       if (!this.enderDragon.world.isClientSide) {
/* 43 */         this.enderDragon.getDataWatcher().set(EntityEnderDragon.PHASE, Integer.valueOf(dragoncontrollerphase.b()));
/*    */       }
/*    */       
/* 46 */       LOGGER.debug("Dragon is now in phase {} on the {}", dragoncontrollerphase, this.enderDragon.world.isClientSide ? "client" : "server");
/* 47 */       this.currentDragonController.d();
/*    */     } 
/*    */   }
/*    */   
/*    */   public IDragonController a() {
/* 52 */     return this.currentDragonController;
/*    */   }
/*    */   
/*    */   public <T extends IDragonController> T b(DragonControllerPhase<T> dragoncontrollerphase) {
/* 56 */     int i = dragoncontrollerphase.b();
/*    */     
/* 58 */     if (this.dragonControllers[i] == null) {
/* 59 */       this.dragonControllers[i] = dragoncontrollerphase.a(this.enderDragon);
/*    */     }
/*    */     
/* 62 */     return (T)this.dragonControllers[i];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DragonControllerManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */