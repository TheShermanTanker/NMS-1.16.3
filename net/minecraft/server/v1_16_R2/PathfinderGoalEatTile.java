/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.EnumSet;
/*    */ import java.util.function.Predicate;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class PathfinderGoalEatTile
/*    */   extends PathfinderGoal
/*    */ {
/* 12 */   private static final Predicate<IBlockData> a = BlockStatePredicate.a(Blocks.GRASS);
/*    */   private final EntityInsentient b;
/*    */   private final World c;
/*    */   private int d;
/*    */   
/*    */   public PathfinderGoalEatTile(EntityInsentient entityinsentient) {
/* 18 */     this.b = entityinsentient;
/* 19 */     this.c = entityinsentient.world;
/* 20 */     a(EnumSet.of(PathfinderGoal.Type.MOVE, PathfinderGoal.Type.LOOK, PathfinderGoal.Type.JUMP));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a() {
/* 25 */     if (this.b.getRandom().nextInt(this.b.isBaby() ? 50 : 1000) != 0) {
/* 26 */       return false;
/*    */     }
/* 28 */     BlockPosition blockposition = this.b.getChunkCoordinates();
/*    */     
/* 30 */     return a.test(this.c.getType(blockposition)) ? true : this.c.getType(blockposition.down()).a(Blocks.GRASS_BLOCK);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void c() {
/* 36 */     this.d = 40;
/* 37 */     this.c.broadcastEntityEffect(this.b, (byte)10);
/* 38 */     this.b.getNavigation().o();
/*    */   }
/*    */ 
/*    */   
/*    */   public void d() {
/* 43 */     this.d = 0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean b() {
/* 48 */     return (this.d > 0);
/*    */   }
/*    */   
/*    */   public int g() {
/* 52 */     return this.d;
/*    */   }
/*    */ 
/*    */   
/*    */   public void e() {
/* 57 */     this.d = Math.max(0, this.d - 1);
/* 58 */     if (this.d == 4) {
/* 59 */       BlockPosition blockposition = this.b.getChunkCoordinates();
/*    */       
/* 61 */       if (a.test(this.c.getType(blockposition))) {
/*    */         
/* 63 */         if (!CraftEventFactory.callEntityChangeBlockEvent(this.b, blockposition, Blocks.AIR.getBlockData(), !this.c.getGameRules().getBoolean(GameRules.MOB_GRIEFING)).isCancelled()) {
/* 64 */           this.c.b(blockposition, false);
/*    */         }
/*    */         
/* 67 */         this.b.blockEaten();
/*    */       } else {
/* 69 */         BlockPosition blockposition1 = blockposition.down();
/*    */         
/* 71 */         if (this.c.getType(blockposition1).a(Blocks.GRASS_BLOCK)) {
/*    */           
/* 73 */           if (!CraftEventFactory.callEntityChangeBlockEvent(this.b, blockposition, Blocks.AIR.getBlockData(), !this.c.getGameRules().getBoolean(GameRules.MOB_GRIEFING)).isCancelled()) {
/* 74 */             this.c.triggerEffect(2001, blockposition1, Block.getCombinedId(Blocks.GRASS_BLOCK.getBlockData()));
/* 75 */             this.c.setTypeAndData(blockposition1, Blocks.DIRT.getBlockData(), 2);
/*    */           } 
/*    */           
/* 78 */           this.b.blockEaten();
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderGoalEatTile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */