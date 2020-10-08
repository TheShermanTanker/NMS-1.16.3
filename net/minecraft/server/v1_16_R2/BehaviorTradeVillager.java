/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Collectors;
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
/*     */ public class BehaviorTradeVillager
/*     */   extends Behavior<EntityVillager>
/*     */ {
/*  26 */   private Set<Item> b = (Set<Item>)ImmutableSet.of();
/*     */   
/*     */   public BehaviorTradeVillager() {
/*  29 */     super((Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.INTERACTION_TARGET, MemoryStatus.VALUE_PRESENT, MemoryModuleType.VISIBLE_MOBS, MemoryStatus.VALUE_PRESENT));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(WorldServer var0, EntityVillager var1) {
/*  37 */     return BehaviorUtil.a(var1.getBehaviorController(), MemoryModuleType.INTERACTION_TARGET, EntityTypes.VILLAGER);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean b(WorldServer var0, EntityVillager var1, long var2) {
/*  42 */     return a(var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(WorldServer var0, EntityVillager var1, long var2) {
/*  47 */     EntityVillager var4 = var1.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.INTERACTION_TARGET).get();
/*  48 */     BehaviorUtil.a(var1, var4, 0.5F);
/*     */     
/*  50 */     this.b = a(var1, var4);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void d(WorldServer var0, EntityVillager var1, long var2) {
/*  55 */     EntityVillager var4 = var1.getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.INTERACTION_TARGET).get();
/*     */     
/*  57 */     if (var1.h(var4) > 5.0D) {
/*     */       return;
/*     */     }
/*     */     
/*  61 */     BehaviorUtil.a(var1, var4, 0.5F);
/*     */     
/*  63 */     var1.a(var0, var4, var2);
/*     */     
/*  65 */     if (var1.fg() && (var1.getVillagerData().getProfession() == VillagerProfession.FARMER || var4.fh())) {
/*  66 */       a(var1, EntityVillager.bp.keySet(), var4);
/*     */     }
/*     */     
/*  69 */     if (var4.getVillagerData().getProfession() == VillagerProfession.FARMER && var1.getInventory().a(Items.WHEAT) > Items.WHEAT.getMaxStackSize() / 2) {
/*  70 */       a(var1, (Set<Item>)ImmutableSet.of(Items.WHEAT), var4);
/*     */     }
/*     */     
/*  73 */     if (!this.b.isEmpty() && var1.getInventory().a(this.b)) {
/*  74 */       a(var1, this.b, var4);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void c(WorldServer var0, EntityVillager var1, long var2) {
/*  80 */     var1.getBehaviorController().removeMemory(MemoryModuleType.INTERACTION_TARGET);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static Set<Item> a(EntityVillager var0, EntityVillager var1) {
/*  86 */     ImmutableSet<Item> var2 = var1.getVillagerData().getProfession().c();
/*  87 */     ImmutableSet<Item> var3 = var0.getVillagerData().getProfession().c();
/*  88 */     return (Set<Item>)var2.stream().filter(var1 -> !var0.contains(var1)).collect(Collectors.toSet());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(EntityVillager var0, Set<Item> var1, EntityLiving var2) {
/*  95 */     InventorySubcontainer var3 = var0.getInventory();
/*     */     
/*  97 */     ItemStack var4 = ItemStack.b;
/*  98 */     for (int var5 = 0; var5 < var3.getSize(); var5++) {
/*  99 */       ItemStack var6 = var3.getItem(var5);
/* 100 */       if (!var6.isEmpty()) {
/* 101 */         Item var7 = var6.getItem();
/* 102 */         if (var1.contains(var7)) {
/*     */           int var8;
/* 104 */           if (var6.getCount() > var6.getMaxStackSize() / 2) {
/* 105 */             var8 = var6.getCount() / 2;
/* 106 */           } else if (var6.getCount() > 24) {
/* 107 */             var8 = var6.getCount() - 24;
/*     */           } else {
/*     */             continue;
/*     */           } 
/* 111 */           var6.subtract(var8);
/* 112 */           var4 = new ItemStack(var7, var8);
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       continue;
/*     */     } 
/* 118 */     if (!var4.isEmpty())
/* 119 */       BehaviorUtil.a(var0, var4, var2.getPositionVector()); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorTradeVillager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */