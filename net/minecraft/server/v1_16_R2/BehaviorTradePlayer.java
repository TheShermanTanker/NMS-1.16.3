/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Map;
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
/*     */ public class BehaviorTradePlayer
/*     */   extends Behavior<EntityVillager>
/*     */ {
/*     */   @Nullable
/*     */   private ItemStack b;
/*  27 */   private final List<ItemStack> c = Lists.newArrayList();
/*     */   private int d;
/*     */   private int e;
/*     */   private int f;
/*     */   
/*     */   public BehaviorTradePlayer(int var0, int var1) {
/*  33 */     super(
/*  34 */         (Map<MemoryModuleType<?>, MemoryStatus>)ImmutableMap.of(MemoryModuleType.INTERACTION_TARGET, MemoryStatus.VALUE_PRESENT), var0, var1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(WorldServer var0, EntityVillager var1) {
/*  44 */     BehaviorController<?> var2 = var1.getBehaviorController();
/*  45 */     if (!var2.<EntityLiving>getMemory(MemoryModuleType.INTERACTION_TARGET).isPresent()) {
/*  46 */       return false;
/*     */     }
/*     */     
/*  49 */     EntityLiving var3 = var2.<EntityLiving>getMemory(MemoryModuleType.INTERACTION_TARGET).get();
/*  50 */     return (var3.getEntityType() == EntityTypes.PLAYER && var1
/*  51 */       .isAlive() && var3
/*  52 */       .isAlive() && 
/*  53 */       !var1.isBaby() && var1
/*  54 */       .h(var3) <= 17.0D);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(WorldServer var0, EntityVillager var1, long var2) {
/*  59 */     return (a(var0, var1) && this.f > 0 && var1
/*     */       
/*  61 */       .getBehaviorController().<EntityLiving>getMemory(MemoryModuleType.INTERACTION_TARGET).isPresent());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(WorldServer var0, EntityVillager var1, long var2) {
/*  66 */     super.a(var0, var1, var2);
/*  67 */     c(var1);
/*     */     
/*  69 */     this.d = 0;
/*  70 */     this.e = 0;
/*  71 */     this.f = 40;
/*     */   }
/*     */ 
/*     */   
/*     */   public void d(WorldServer var0, EntityVillager var1, long var2) {
/*  76 */     EntityLiving var4 = c(var1);
/*     */     
/*  78 */     a(var4, var1);
/*  79 */     if (!this.c.isEmpty()) {
/*  80 */       d(var1);
/*     */     } else {
/*  82 */       var1.setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/*  83 */       this.f = Math.min(this.f, 40);
/*     */     } 
/*     */     
/*  86 */     this.f--;
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(WorldServer var0, EntityVillager var1, long var2) {
/*  91 */     super.c(var0, var1, var2);
/*  92 */     var1.getBehaviorController().removeMemory(MemoryModuleType.INTERACTION_TARGET);
/*     */     
/*  94 */     var1.setSlot(EnumItemSlot.MAINHAND, ItemStack.b);
/*  95 */     this.b = null;
/*     */   }
/*     */   
/*     */   private void a(EntityLiving var0, EntityVillager var1) {
/*  99 */     boolean var2 = false;
/* 100 */     ItemStack var3 = var0.getItemInMainHand();
/* 101 */     if (this.b == null || !ItemStack.c(this.b, var3)) {
/* 102 */       this.b = var3;
/* 103 */       var2 = true;
/* 104 */       this.c.clear();
/*     */     } 
/*     */     
/* 107 */     if (var2 && !this.b.isEmpty()) {
/* 108 */       b(var1);
/* 109 */       if (!this.c.isEmpty()) {
/* 110 */         this.f = 900;
/* 111 */         a(var1);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(EntityVillager var0) {
/* 117 */     var0.setSlot(EnumItemSlot.MAINHAND, this.c.get(0));
/*     */   }
/*     */   
/*     */   private void b(EntityVillager var0) {
/* 121 */     for (MerchantRecipe var2 : var0.getOffers()) {
/* 122 */       if (!var2.isFullyUsed() && a(var2)) {
/* 123 */         this.c.add(var2.getSellingItem());
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean a(MerchantRecipe var0) {
/* 129 */     return (ItemStack.c(this.b, var0.getBuyItem1()) || ItemStack.c(this.b, var0.getBuyItem2()));
/*     */   }
/*     */   
/*     */   private EntityLiving c(EntityVillager var0) {
/* 133 */     BehaviorController<?> var1 = var0.getBehaviorController();
/*     */     
/* 135 */     EntityLiving var2 = var1.<EntityLiving>getMemory(MemoryModuleType.INTERACTION_TARGET).get();
/*     */     
/* 137 */     var1.setMemory(MemoryModuleType.LOOK_TARGET, new BehaviorPositionEntity(var2, true));
/* 138 */     return var2;
/*     */   }
/*     */   
/*     */   private void d(EntityVillager var0) {
/* 142 */     if (this.c.size() >= 2 && ++this.d >= 40) {
/* 143 */       this.e++;
/* 144 */       this.d = 0;
/* 145 */       if (this.e > this.c.size() - 1) {
/* 146 */         this.e = 0;
/*     */       }
/* 148 */       var0.setSlot(EnumItemSlot.MAINHAND, this.c.get(this.e));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BehaviorTradePlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */