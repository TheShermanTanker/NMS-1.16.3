/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorPositionEntity
/*    */   implements BehaviorPosition
/*    */ {
/*    */   private final Entity a;
/*    */   private final boolean b;
/*    */   
/*    */   public BehaviorPositionEntity(Entity var0, boolean var1) {
/* 17 */     this.a = var0;
/* 18 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public Vec3D a() {
/* 23 */     return this.b ? this.a.getPositionVector().add(0.0D, this.a.getHeadHeight(), 0.0D) : this.a.getPositionVector();
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockPosition b() {
/* 28 */     return this.a.getChunkCoordinates();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(EntityLiving var0) {
/* 33 */     if (this.a instanceof EntityLiving) {
/* 34 */       Optional<List<EntityLiving>> var1 = var0.getBehaviorController().getMemory(MemoryModuleType.VISIBLE_MOBS);
/* 35 */       return (this.a.isAlive() && var1.isPresent() && ((List)var1.get()).contains(this.a));
/*    */     } 
/* 37 */     return true;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 46 */     return "EntityTracker for " + this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorPositionEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */