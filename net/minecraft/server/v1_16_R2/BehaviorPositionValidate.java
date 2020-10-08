/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.Map;
/*    */ import java.util.function.Predicate;
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
/*    */ 
/*    */ 
/*    */ public class BehaviorPositionValidate
/*    */   extends Behavior<EntityLiving>
/*    */ {
/*    */   private final MemoryModuleType<GlobalPos> b;
/*    */   private final Predicate<VillagePlaceType> c;
/*    */   
/*    */   public BehaviorPositionValidate(VillagePlaceType var0, MemoryModuleType<GlobalPos> var1) {
/* 29 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(var1, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */     
/* 33 */     this.c = var0.c();
/* 34 */     this.b = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityLiving var1) {
/* 39 */     GlobalPos var2 = var1.getBehaviorController().<GlobalPos>getMemory(this.b).get();
/* 40 */     return (var0.getDimensionKey() == var2.getDimensionManager() && var2
/* 41 */       .getBlockPosition().a(var1.getPositionVector(), 16.0D));
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityLiving var1, long var2) {
/* 46 */     BehaviorController<?> var4 = var1.getBehaviorController();
/* 47 */     GlobalPos var5 = var4.<GlobalPos>getMemory(this.b).get();
/* 48 */     BlockPosition var6 = var5.getBlockPosition();
/* 49 */     WorldServer var7 = var0.getMinecraftServer().getWorldServer(var5.getDimensionManager());
/* 50 */     if (var7 == null || a(var7, var6)) {
/* 51 */       var4.removeMemory(this.b);
/* 52 */     } else if (a(var7, var6, var1)) {
/* 53 */       var4.removeMemory(this.b);
/* 54 */       var0.y().b(var6);
/* 55 */       PacketDebug.c(var0, var6);
/*    */     } 
/*    */   }
/*    */   
/*    */   private boolean a(WorldServer var0, BlockPosition var1, EntityLiving var2) {
/* 60 */     IBlockData var3 = var0.getType(var1);
/* 61 */     return (var3.getBlock().a(TagsBlock.BEDS) && ((Boolean)var3.get(BlockBed.OCCUPIED)).booleanValue() && !var2.isSleeping());
/*    */   }
/*    */   
/*    */   private boolean a(WorldServer var0, BlockPosition var1) {
/* 65 */     return !var0.y().a(var1, this.c);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorPositionValidate.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */