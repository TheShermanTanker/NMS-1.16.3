/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
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
/*     */ public abstract class Enchantment
/*     */ {
/*     */   private final EnumItemSlot[] a;
/*     */   private final Rarity d;
/*     */   public final EnchantmentSlotType itemTarget;
/*     */   @Nullable
/*     */   protected String c;
/*     */   
/*     */   public enum Rarity
/*     */   {
/*  29 */     COMMON(10),
/*  30 */     UNCOMMON(5),
/*  31 */     RARE(2),
/*  32 */     VERY_RARE(1);
/*     */     
/*     */     private final int e;
/*     */     
/*     */     Rarity(int var2) {
/*  37 */       this.e = var2;
/*     */     }
/*     */     
/*     */     public int a() {
/*  41 */       return this.e;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected Enchantment(Rarity var0, EnchantmentSlotType var1, EnumItemSlot[] var2) {
/*  52 */     this.d = var0;
/*  53 */     this.itemTarget = var1;
/*  54 */     this.a = var2;
/*     */   }
/*     */   
/*     */   public Map<EnumItemSlot, ItemStack> a(EntityLiving var0) {
/*  58 */     Map<EnumItemSlot, ItemStack> var1 = Maps.newEnumMap(EnumItemSlot.class);
/*  59 */     for (EnumItemSlot var5 : this.a) {
/*  60 */       ItemStack var6 = var0.getEquipment(var5);
/*  61 */       if (!var6.isEmpty()) {
/*  62 */         var1.put(var5, var6);
/*     */       }
/*     */     } 
/*  65 */     return var1;
/*     */   }
/*     */   
/*     */   public Rarity d() {
/*  69 */     return this.d;
/*     */   }
/*     */   
/*     */   public int getStartLevel() {
/*  73 */     return 1;
/*     */   }
/*     */   
/*     */   public int getMaxLevel() {
/*  77 */     return 1;
/*     */   }
/*     */   
/*     */   public int a(int var0) {
/*  81 */     return 1 + var0 * 10;
/*     */   }
/*     */   
/*     */   public int b(int var0) {
/*  85 */     return a(var0) + 5;
/*     */   }
/*     */   
/*     */   public int a(int var0, DamageSource var1) {
/*  89 */     return 0;
/*     */   }
/*     */   
/*     */   public float a(int var0, EnumMonsterType var1) {
/*  93 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public final boolean isCompatible(Enchantment var0) {
/*  97 */     return (a(var0) && var0.a(this));
/*     */   }
/*     */   
/*     */   protected boolean a(Enchantment var0) {
/* 101 */     return (this != var0);
/*     */   }
/*     */   
/*     */   protected String f() {
/* 105 */     if (this.c == null) {
/* 106 */       this.c = SystemUtils.a("enchantment", IRegistry.ENCHANTMENT.getKey(this));
/*     */     }
/* 108 */     return this.c;
/*     */   }
/*     */   
/*     */   public String g() {
/* 112 */     return f();
/*     */   }
/*     */   
/*     */   public IChatBaseComponent d(int var0) {
/* 116 */     IChatMutableComponent var1 = new ChatMessage(g());
/* 117 */     if (c()) {
/* 118 */       var1.a(EnumChatFormat.RED);
/*     */     } else {
/* 120 */       var1.a(EnumChatFormat.GRAY);
/*     */     } 
/* 122 */     if (var0 != 1 || getMaxLevel() != 1) {
/* 123 */       var1.c(" ").addSibling(new ChatMessage("enchantment.level." + var0));
/*     */     }
/* 125 */     return var1;
/*     */   }
/*     */   
/*     */   public boolean canEnchant(ItemStack var0) {
/* 129 */     return this.itemTarget.canEnchant(var0.getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityLiving var0, Entity var1, int var2) {}
/*     */ 
/*     */   
/*     */   public void b(EntityLiving var0, Entity var1, int var2) {}
/*     */   
/*     */   public boolean isTreasure() {
/* 139 */     return false;
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 143 */     return false;
/*     */   }
/*     */   public boolean h() {
/* 146 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean i() {
/* 154 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Enchantment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */