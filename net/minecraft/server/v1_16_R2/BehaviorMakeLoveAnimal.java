/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BehaviorMakeLoveAnimal
/*     */   extends Behavior<EntityAnimal>
/*     */ {
/*     */   private final EntityTypes<? extends EntityAnimal> b;
/*     */   private final float c;
/*     */   private long d;
/*     */   
/*     */   public BehaviorMakeLoveAnimal(EntityTypes<? extends EntityAnimal> var0, float var1) {
/*  29 */     super(
/*  30 */         (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT, MemoryModuleType.BREED_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED), 325);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  38 */     this.b = var0;
/*  39 */     this.c = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(WorldServer var0, EntityAnimal var1) {
/*  44 */     return (var1.isInLove() && c(var1).isPresent());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, EntityAnimal var1, long var2) {
/*  49 */     EntityAnimal var4 = c(var1).get();
/*     */     
/*  51 */     var1.getBehaviorController().setMemory(MemoryModuleType.BREED_TARGET, var4);
/*  52 */     var4.getBehaviorController().setMemory(MemoryModuleType.BREED_TARGET, var1);
/*     */     
/*  54 */     BehaviorUtil.a(var1, var4, this.c);
/*     */     
/*  56 */     int var5 = 275 + var1.getRandom().nextInt(50);
/*  57 */     this.d = var2 + var5;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(WorldServer var0, EntityAnimal var1, long var2) {
/*  62 */     if (!b(var1)) {
/*  63 */       return false;
/*     */     }
/*  65 */     EntityAnimal var4 = a(var1);
/*     */     
/*  67 */     return (var4.isAlive() && var1
/*  68 */       .mate(var4) && 
/*  69 */       BehaviorUtil.a(var1.getBehaviorController(), var4) && var2 <= this.d);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void d(WorldServer var0, EntityAnimal var1, long var2) {
/*  75 */     EntityAnimal var4 = a(var1);
/*     */     
/*  77 */     BehaviorUtil.a(var1, var4, this.c);
/*  78 */     if (!var1.a(var4, 3.0D)) {
/*     */       return;
/*     */     }
/*  81 */     if (var2 >= this.d) {
/*  82 */       var1.a(var0, var4);
/*  83 */       var1.getBehaviorController().removeMemory(MemoryModuleType.BREED_TARGET);
/*  84 */       var4.getBehaviorController().removeMemory(MemoryModuleType.BREED_TARGET);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(WorldServer var0, EntityAnimal var1, long var2) {
/*  90 */     var1.getBehaviorController().removeMemory(MemoryModuleType.BREED_TARGET);
/*  91 */     var1.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
/*  92 */     var1.getBehaviorController().removeMemory(MemoryModuleType.LOOK_TARGET);
/*  93 */     this.d = 0L;
/*     */   }
/*     */   
/*     */   private EntityAnimal a(EntityAnimal var0) {
/*  97 */     return var0.getBehaviorController().<EntityAgeable>getMemory(MemoryModuleType.BREED_TARGET).get();
/*     */   }
/*     */   
/*     */   private boolean b(EntityAnimal var0) {
/* 101 */     BehaviorController<?> var1 = var0.getBehaviorController();
/* 102 */     return (var1.hasMemory(MemoryModuleType.BREED_TARGET) && ((EntityAgeable)var1
/* 103 */       .<EntityAgeable>getMemory(MemoryModuleType.BREED_TARGET).get()).getEntityType() == this.b);
/*     */   }
/*     */   
/*     */   private Optional<? extends EntityAnimal> c(EntityAnimal var0) {
/* 107 */     return ((List)var0.getBehaviorController().<List>getMemory((MemoryModuleType)MemoryModuleType.VISIBLE_MOBS).get()).stream()
/* 108 */       .filter(var0 -> (var0.getEntityType() == this.b))
/* 109 */       .map(var0 -> (EntityAnimal)var0)
/* 110 */       .filter(var0::mate)
/* 111 */       .findFirst();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorMakeLoveAnimal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */