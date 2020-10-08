/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.HashMap;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BehaviorVillageHeroGift
/*     */   extends Behavior<EntityVillager>
/*     */ {
/*     */   private static final Map<VillagerProfession, MinecraftKey> b;
/*     */   
/*     */   static {
/*  39 */     b = SystemUtils.<Map<VillagerProfession, MinecraftKey>>a(Maps.newHashMap(), var0 -> {
/*     */           var0.put(VillagerProfession.ARMORER, LootTables.al);
/*     */           var0.put(VillagerProfession.BUTCHER, LootTables.am);
/*     */           var0.put(VillagerProfession.CARTOGRAPHER, LootTables.an);
/*     */           var0.put(VillagerProfession.CLERIC, LootTables.ao);
/*     */           var0.put(VillagerProfession.FARMER, LootTables.ap);
/*     */           var0.put(VillagerProfession.FISHERMAN, LootTables.aq);
/*     */           var0.put(VillagerProfession.FLETCHER, LootTables.ar);
/*     */           var0.put(VillagerProfession.LEATHERWORKER, LootTables.as);
/*     */           var0.put(VillagerProfession.LIBRARIAN, LootTables.at);
/*     */           var0.put(VillagerProfession.MASON, LootTables.au);
/*     */           var0.put(VillagerProfession.SHEPHERD, LootTables.av);
/*     */           var0.put(VillagerProfession.TOOLSMITH, LootTables.aw);
/*     */           var0.put(VillagerProfession.WEAPONSMITH, LootTables.ax);
/*     */         });
/*     */   }
/*     */   
/*  56 */   private int c = 600;
/*     */   private boolean d;
/*     */   private long e;
/*     */   
/*     */   public BehaviorVillageHeroGift(int var0) {
/*  61 */     super(
/*  62 */         (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.WALK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.LOOK_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.INTERACTION_TARGET, MemoryStatus.REGISTERED, MemoryModuleType.NEAREST_VISIBLE_PLAYER, MemoryStatus.VALUE_PRESENT), var0);
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
/*     */   protected boolean a(WorldServer var0, EntityVillager var1) {
/*  74 */     if (!b(var1)) {
/*  75 */       return false;
/*     */     }
/*     */     
/*  78 */     if (this.c > 0) {
/*  79 */       this.c--;
/*  80 */       return false;
/*     */     } 
/*     */     
/*  83 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/*  88 */     this.d = false;
/*  89 */     this.e = var2;
/*  90 */     EntityHuman var4 = c(var1).get();
/*  91 */     var1.getBehaviorController().setMemory(MemoryModuleType.INTERACTION_TARGET, var4);
/*  92 */     BehaviorUtil.a(var1, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(WorldServer var0, EntityVillager var1, long var2) {
/*  97 */     return (b(var1) && !this.d);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void d(WorldServer var0, EntityVillager var1, long var2) {
/* 102 */     EntityHuman var4 = c(var1).get();
/* 103 */     BehaviorUtil.a(var1, var4);
/*     */     
/* 105 */     if (a(var1, var4)) {
/* 106 */       if (var2 - this.e > 20L) {
/* 107 */         a(var1, var4);
/* 108 */         this.d = true;
/*     */       } 
/*     */     } else {
/* 111 */       BehaviorUtil.a(var1, var4, 0.5F, 5);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(WorldServer var0, EntityVillager var1, long var2) {
/* 117 */     this.c = a(var0);
/* 118 */     var1.getBehaviorController().removeMemory(MemoryModuleType.INTERACTION_TARGET);
/* 119 */     var1.getBehaviorController().removeMemory(MemoryModuleType.WALK_TARGET);
/* 120 */     var1.getBehaviorController().removeMemory(MemoryModuleType.LOOK_TARGET);
/*     */   }
/*     */   
/*     */   private void a(EntityVillager var0, EntityLiving var1) {
/* 124 */     List<ItemStack> var2 = a(var0);
/* 125 */     for (ItemStack var4 : var2) {
/* 126 */       BehaviorUtil.a(var0, var4, var1.getPositionVector());
/*     */     }
/*     */   }
/*     */   
/*     */   private List<ItemStack> a(EntityVillager var0) {
/* 131 */     if (var0.isBaby()) {
/* 132 */       return (List<ItemStack>)ImmutableList.of(new ItemStack(Items.bi));
/*     */     }
/*     */     
/* 135 */     VillagerProfession var1 = var0.getVillagerData().getProfession();
/* 136 */     if (b.containsKey(var1)) {
/* 137 */       LootTable var2 = var0.world.getMinecraftServer().getLootTableRegistry().getLootTable(b.get(var1));
/*     */ 
/*     */ 
/*     */       
/* 141 */       LootTableInfo.Builder var3 = (new LootTableInfo.Builder((WorldServer)var0.world)).<Vec3D>set(LootContextParameters.ORIGIN, var0.getPositionVector()).<Entity>set(LootContextParameters.THIS_ENTITY, var0).a(var0.getRandom());
/*     */       
/* 143 */       return var2.populateLoot(var3.build(LootContextParameterSets.GIFT));
/*     */     } 
/*     */     
/* 146 */     return (List<ItemStack>)ImmutableList.of(new ItemStack(Items.WHEAT_SEEDS));
/*     */   }
/*     */   
/*     */   private boolean b(EntityVillager var0) {
/* 150 */     return c(var0).isPresent();
/*     */   }
/*     */   
/*     */   private Optional<EntityHuman> c(EntityVillager var0) {
/* 154 */     return var0.getBehaviorController().<EntityHuman>getMemory(MemoryModuleType.NEAREST_VISIBLE_PLAYER)
/* 155 */       .filter(this::a);
/*     */   }
/*     */   
/*     */   private boolean a(EntityHuman var0) {
/* 159 */     return var0.hasEffect(MobEffects.HERO_OF_THE_VILLAGE);
/*     */   }
/*     */   
/*     */   private boolean a(EntityVillager var0, EntityHuman var1) {
/* 163 */     BlockPosition var2 = var1.getChunkCoordinates();
/* 164 */     BlockPosition var3 = var0.getChunkCoordinates();
/* 165 */     return var3.a(var2, 5.0D);
/*     */   }
/*     */   
/*     */   private static int a(WorldServer var0) {
/* 169 */     return 600 + var0.random.nextInt(6001);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorVillageHeroGift.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */