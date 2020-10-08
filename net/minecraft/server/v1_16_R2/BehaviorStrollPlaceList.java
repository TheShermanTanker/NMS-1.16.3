/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.Optional;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BehaviorStrollPlaceList
/*    */   extends Behavior<EntityVillager>
/*    */ {
/*    */   private final MemoryModuleType<List<GlobalPos>> b;
/*    */   private final MemoryModuleType<GlobalPos> c;
/*    */   private final float d;
/*    */   private final int e;
/*    */   private final int f;
/*    */   private long g;
/*    */   @Nullable
/*    */   private GlobalPos h;
/*    */   
/*    */   public BehaviorStrollPlaceList(MemoryModuleType<List<GlobalPos>> var0, float var1, int var2, int var3, MemoryModuleType<GlobalPos> var4) {
/* 27 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, var0, MemoryStatus.VALUE_PRESENT, var4, MemoryStatus.VALUE_PRESENT));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 33 */     this.b = var0;
/* 34 */     this.d = var1;
/* 35 */     this.e = var2;
/* 36 */     this.f = var3;
/* 37 */     this.c = var4;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean a(WorldServer var0, EntityVillager var1) {
/* 42 */     Optional<List<GlobalPos>> var2 = var1.getBehaviorController().getMemory(this.b);
/* 43 */     Optional<GlobalPos> var3 = var1.getBehaviorController().getMemory(this.c);
/* 44 */     if (var2.isPresent() && var3.isPresent()) {
/* 45 */       List<GlobalPos> var4 = var2.get();
/* 46 */       if (!var4.isEmpty()) {
/* 47 */         this.h = var4.get(var0.getRandom().nextInt(var4.size()));
/* 48 */         return (this.h != null && var0.getDimensionKey() == this.h.getDimensionManager() && ((GlobalPos)var3.get()).getBlockPosition().a(var1.getPositionVector(), this.f));
/*    */       } 
/*    */     } 
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/* 56 */     if (var2 > this.g && this.h != null) {
/* 57 */       var1.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(this.h.getBlockPosition(), this.d, this.e));
/* 58 */       this.g = var2 + 100L;
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorStrollPlaceList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */