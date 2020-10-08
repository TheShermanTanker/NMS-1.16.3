/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public class EnchantmentProtection
/*     */   extends Enchantment
/*     */ {
/*     */   public final DamageType a;
/*     */   
/*     */   public enum DamageType
/*     */   {
/*  10 */     ALL("all", 1, 11),
/*  11 */     FIRE("fire", 10, 8),
/*  12 */     FALL("fall", 5, 6),
/*  13 */     EXPLOSION("explosion", 5, 8),
/*  14 */     PROJECTILE("projectile", 3, 6);
/*     */     
/*     */     private final String f;
/*     */     private final int g;
/*     */     private final int h;
/*     */     
/*     */     DamageType(String var2, int var3, int var4) {
/*  21 */       this.f = var2;
/*  22 */       this.g = var3;
/*  23 */       this.h = var4;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int b() {
/*  31 */       return this.g;
/*     */     }
/*     */     
/*     */     public int c() {
/*  35 */       return this.h;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnchantmentProtection(Enchantment.Rarity var0, DamageType var1, EnumItemSlot... var2) {
/*  42 */     super(var0, (var1 == DamageType.FALL) ? EnchantmentSlotType.ARMOR_FEET : EnchantmentSlotType.ARMOR, var2);
/*  43 */     this.a = var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(int var0) {
/*  48 */     return this.a.b() + (var0 - 1) * this.a.c();
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(int var0) {
/*  53 */     return a(var0) + this.a.c();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxLevel() {
/*  58 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(int var0, DamageSource var1) {
/*  63 */     if (var1.ignoresInvulnerability()) {
/*  64 */       return 0;
/*     */     }
/*     */     
/*  67 */     if (this.a == DamageType.ALL) {
/*  68 */       return var0;
/*     */     }
/*  70 */     if (this.a == DamageType.FIRE && var1.isFire()) {
/*  71 */       return var0 * 2;
/*     */     }
/*  73 */     if (this.a == DamageType.FALL && var1 == DamageSource.FALL) {
/*  74 */       return var0 * 3;
/*     */     }
/*  76 */     if (this.a == DamageType.EXPLOSION && var1.isExplosion()) {
/*  77 */       return var0 * 2;
/*     */     }
/*  79 */     if (this.a == DamageType.PROJECTILE && var1.b()) {
/*  80 */       return var0 * 2;
/*     */     }
/*  82 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Enchantment var0) {
/*  87 */     if (var0 instanceof EnchantmentProtection) {
/*  88 */       EnchantmentProtection var1 = (EnchantmentProtection)var0;
/*     */       
/*  90 */       if (this.a == var1.a) {
/*  91 */         return false;
/*     */       }
/*     */       
/*  94 */       return (this.a == DamageType.FALL || var1.a == DamageType.FALL);
/*     */     } 
/*  96 */     return super.a(var0);
/*     */   }
/*     */   
/*     */   public static int a(EntityLiving var0, int var1) {
/* 100 */     int var2 = EnchantmentManager.a(Enchantments.PROTECTION_FIRE, var0);
/*     */     
/* 102 */     if (var2 > 0) {
/* 103 */       var1 -= MathHelper.d(var1 * var2 * 0.15F);
/*     */     }
/*     */     
/* 106 */     return var1;
/*     */   }
/*     */   
/*     */   public static double a(EntityLiving var0, double var1) {
/* 110 */     int var3 = EnchantmentManager.a(Enchantments.PROTECTION_EXPLOSIONS, var0);
/*     */     
/* 112 */     if (var3 > 0) {
/* 113 */       var1 -= MathHelper.floor(var1 * (var3 * 0.15F));
/*     */     }
/*     */     
/* 116 */     return var1;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentProtection.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */