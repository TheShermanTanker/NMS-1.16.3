/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
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
/*     */ public class SensorPiglinSpecific
/*     */   extends Sensor<EntityLiving>
/*     */ {
/*     */   public Set<MemoryModuleType<?>> a() {
/*  36 */     return (Set<MemoryModuleType<?>>)ImmutableSet.of(MemoryModuleType.VISIBLE_MOBS, MemoryModuleType.MOBS, MemoryModuleType.NEAREST_VISIBLE_NEMSIS, MemoryModuleType.NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD, MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM, MemoryModuleType.NEAREST_VISIBLE_HUNTABLE_HOGLIN, (Object[])new MemoryModuleType[] { MemoryModuleType.NEAREST_VISIBLE_BABY_HOGLIN, MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS, MemoryModuleType.NEARBY_ADULT_PIGLINS, MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT, MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT, MemoryModuleType.NEAREST_REPELLENT });
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, EntityLiving var1) {
/*  57 */     BehaviorController<?> var2 = var1.getBehaviorController();
/*     */     
/*  59 */     var2.setMemory(MemoryModuleType.NEAREST_REPELLENT, c(var0, var1));
/*     */     
/*  61 */     Optional<EntityInsentient> var3 = Optional.empty();
/*  62 */     Optional<EntityHoglin> var4 = Optional.empty();
/*  63 */     Optional<EntityHoglin> var5 = Optional.empty();
/*  64 */     Optional<EntityPiglin> var6 = Optional.empty();
/*  65 */     Optional<EntityLiving> var7 = Optional.empty();
/*  66 */     Optional<EntityHuman> var8 = Optional.empty();
/*  67 */     Optional<EntityHuman> var9 = Optional.empty();
/*  68 */     int var10 = 0;
/*     */     
/*  70 */     List<EntityPiglinAbstract> var11 = Lists.newArrayList();
/*  71 */     List<EntityPiglinAbstract> var12 = Lists.newArrayList();
/*     */ 
/*     */     
/*  74 */     List<EntityLiving> var13 = (List<EntityLiving>)var2.getMemory(MemoryModuleType.VISIBLE_MOBS).orElse(ImmutableList.of());
/*  75 */     for (EntityLiving var15 : var13) {
/*     */       
/*  77 */       if (var15 instanceof EntityHoglin) {
/*  78 */         EntityHoglin var16 = (EntityHoglin)var15;
/*  79 */         if (var16.isBaby() && !var5.isPresent()) {
/*  80 */           var5 = Optional.of(var16); continue;
/*  81 */         }  if (var16.eL()) {
/*  82 */           var10++;
/*  83 */           if (!var4.isPresent() && var16.eO())
/*  84 */             var4 = Optional.of(var16); 
/*     */         } 
/*     */         continue;
/*     */       } 
/*  88 */       if (var15 instanceof EntityPiglinBrute) {
/*  89 */         var11.add((EntityPiglinBrute)var15); continue;
/*     */       } 
/*  91 */       if (var15 instanceof EntityPiglin) {
/*  92 */         EntityPiglin var16 = (EntityPiglin)var15;
/*  93 */         if (var16.isBaby() && !var6.isPresent()) {
/*  94 */           var6 = Optional.of(var16); continue;
/*  95 */         }  if (var16.eM())
/*  96 */           var11.add(var16); 
/*     */         continue;
/*     */       } 
/*  99 */       if (var15 instanceof EntityHuman) {
/* 100 */         EntityHuman var16 = (EntityHuman)var15;
/* 101 */         if (!var8.isPresent() && IEntitySelector.f.test(var15) && !PiglinAI.a(var16)) {
/* 102 */           var8 = Optional.of(var16);
/*     */         }
/* 104 */         if (!var9.isPresent() && !var16.isSpectator() && PiglinAI.b(var16))
/* 105 */           var9 = Optional.of(var16); 
/*     */         continue;
/*     */       } 
/* 108 */       if (!var3.isPresent() && (var15 instanceof EntitySkeletonWither || var15 instanceof EntityWither)) {
/* 109 */         var3 = Optional.of((EntityInsentient)var15); continue;
/*     */       } 
/* 111 */       if (!var7.isPresent() && PiglinAI.a(var15.getEntityType())) {
/* 112 */         var7 = Optional.of(var15);
/*     */       }
/*     */     } 
/*     */     
/* 116 */     List<EntityLiving> var14 = (List<EntityLiving>)var2.getMemory(MemoryModuleType.MOBS).orElse(ImmutableList.of());
/* 117 */     for (EntityLiving var16 : var14) {
/* 118 */       if (var16 instanceof EntityPiglinAbstract && ((EntityPiglinAbstract)var16).eM()) {
/* 119 */         var12.add((EntityPiglinAbstract)var16);
/*     */       }
/*     */     } 
/*     */     
/* 123 */     var2.setMemory(MemoryModuleType.NEAREST_VISIBLE_NEMSIS, var3);
/* 124 */     var2.setMemory(MemoryModuleType.NEAREST_VISIBLE_HUNTABLE_HOGLIN, var4);
/* 125 */     var2.setMemory(MemoryModuleType.NEAREST_VISIBLE_BABY_HOGLIN, var5);
/* 126 */     var2.setMemory(MemoryModuleType.NEAREST_VISIBLE_ZOMBIFIED, var7);
/* 127 */     var2.setMemory(MemoryModuleType.NEAREST_TARGETABLE_PLAYER_NOT_WEARING_GOLD, var8);
/* 128 */     var2.setMemory(MemoryModuleType.NEAREST_PLAYER_HOLDING_WANTED_ITEM, var9);
/* 129 */     var2.setMemory(MemoryModuleType.NEARBY_ADULT_PIGLINS, var12);
/* 130 */     var2.setMemory(MemoryModuleType.NEAREST_VISIBLE_ADULT_PIGLINS, var11);
/* 131 */     var2.setMemory(MemoryModuleType.VISIBLE_ADULT_PIGLIN_COUNT, Integer.valueOf(var11.size()));
/* 132 */     var2.setMemory(MemoryModuleType.VISIBLE_ADULT_HOGLIN_COUNT, Integer.valueOf(var10));
/*     */   }
/*     */   
/*     */   private static Optional<BlockPosition> c(WorldServer var0, EntityLiving var1) {
/* 136 */     return BlockPosition.a(var1
/* 137 */         .getChunkCoordinates(), 8, 4, var1 -> a(var0, var1));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean a(WorldServer var0, BlockPosition var1) {
/* 145 */     IBlockData var2 = var0.getType(var1);
/* 146 */     boolean var3 = var2.a(TagsBlock.PIGLIN_REPELLENTS);
/* 147 */     if (var3 && var2.a(Blocks.SOUL_CAMPFIRE)) {
/* 148 */       return BlockCampfire.g(var2);
/*     */     }
/* 150 */     return var3;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SensorPiglinSpecific.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */