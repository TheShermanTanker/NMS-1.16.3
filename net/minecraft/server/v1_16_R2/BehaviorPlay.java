/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BehaviorPlay
/*     */   extends Behavior<EntityCreature>
/*     */ {
/*     */   public BehaviorPlay() {
/*  37 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.VISIBLE_VILLAGER_BABIES, MemoryStatus.VALUE_PRESENT, MemoryModuleType.WALK_TARGET, MemoryStatus.VALUE_ABSENT, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.INTERACTION_TARGET, MemoryStatus.REGISTERED));
/*     */   }
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
/*     */   protected boolean a(WorldServer var0, EntityCreature var1) {
/*  50 */     return (var0.getRandom().nextInt(10) == 0 && e(var1));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, EntityCreature var1, long var2) {
/*  55 */     EntityLiving var4 = b(var1);
/*  56 */     if (var4 != null) {
/*     */       
/*  58 */       a(var0, var1, var4);
/*     */       
/*     */       return;
/*     */     } 
/*  62 */     Optional<EntityLiving> var5 = b(var1);
/*  63 */     if (var5.isPresent()) {
/*     */       
/*  65 */       a(var1, var5.get());
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  70 */     a(var1).ifPresent(var1 -> a(var0, var1));
/*     */   }
/*     */   
/*     */   private void a(WorldServer var0, EntityCreature var1, EntityLiving var2) {
/*  74 */     for (int var3 = 0; var3 < 10; var3++) {
/*  75 */       Vec3D var4 = RandomPositionGenerator.b(var1, 20, 8);
/*  76 */       if (var4 != null && var0.a_(new BlockPosition(var4))) {
/*  77 */         var1.getBehaviorController().setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(var4, 0.6F, 0));
/*     */         return;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private static void a(EntityCreature var0, EntityLiving var1) {
/*  84 */     BehaviorController<?> var2 = var0.getBehaviorController();
/*  85 */     var2.setMemory(MemoryModuleType.INTERACTION_TARGET, var1);
/*  86 */     var2.setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorPositionEntity(var1, true));
/*  87 */     var2.setMemory(MemoryModuleType.WALK_TARGET, new MemoryTarget(new BehaviorPositionEntity(var1, false), 0.6F, 1));
/*     */   }
/*     */   
/*     */   private Optional<EntityLiving> a(EntityCreature var0) {
/*  91 */     return d(var0).stream().findAny();
/*     */   }
/*     */ 
/*     */   
/*     */   private Optional<EntityLiving> b(EntityCreature var0) {
/*  96 */     Map<EntityLiving, Integer> var1 = c(var0);
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 101 */     return var1.entrySet().stream()
/* 102 */       .sorted(Comparator.comparingInt(Map.Entry::getValue))
/* 103 */       .filter(var0 -> (((Integer)var0.getValue()).intValue() > 0 && ((Integer)var0.getValue()).intValue() <= 5))
/* 104 */       .map(Map.Entry::getKey)
/* 105 */       .findFirst();
/*     */   }
/*     */   
/*     */   private Map<EntityLiving, Integer> c(EntityCreature var0) {
/* 109 */     Map<EntityLiving, Integer> var1 = Maps.newHashMap();
/*     */     
/* 111 */     d(var0).stream()
/* 112 */       .filter(this::c)
/* 113 */       .forEach(var1 -> (Integer)var0.compute(a(var1), ()));
/*     */ 
/*     */ 
/*     */     
/* 117 */     return var1;
/*     */   }
/*     */   
/*     */   private List<EntityLiving> d(EntityCreature var0) {
/* 121 */     return var0.getBehaviorController().<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_VILLAGER_BABIES).get();
/*     */   }
/*     */   
/*     */   private EntityLiving a(EntityLiving var0) {
/* 125 */     return var0.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.INTERACTION_TARGET).get();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private EntityLiving b(EntityLiving var0) {
/* 130 */     return ((List<EntityLiving>)var0.getBehaviorController().<List<EntityLiving>>getMemory(MemoryModuleType.VISIBLE_VILLAGER_BABIES).get()).stream()
/* 131 */       .filter(var1 -> a(var0, var1))
/* 132 */       .findAny()
/* 133 */       .orElse(null);
/*     */   }
/*     */   
/*     */   private boolean c(EntityLiving var0) {
/* 137 */     return var0.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.INTERACTION_TARGET).isPresent();
/*     */   }
/*     */   
/*     */   private boolean a(EntityLiving var0, EntityLiving var1) {
/* 141 */     return var1.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.INTERACTION_TARGET)
/* 142 */       .filter(var1 -> (var1 == var0))
/* 143 */       .isPresent();
/*     */   }
/*     */   
/*     */   private boolean e(EntityCreature var0) {
/* 147 */     return var0.getBehaviorController().hasMemory(MemoryModuleType.VISIBLE_VILLAGER_BABIES);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorPlay.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */