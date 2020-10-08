/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PotionBrewer
/*     */ {
/*  17 */   private static final List<PredicatedCombination<PotionRegistry>> a = Lists.newArrayList();
/*  18 */   private static final List<PredicatedCombination<Item>> b = Lists.newArrayList();
/*     */   
/*  20 */   private static final List<RecipeItemStack> c = Lists.newArrayList(); static {
/*  21 */     d = (var0 -> {
/*     */         for (RecipeItemStack var2 : c) {
/*     */           if (var2.test(var0))
/*     */             return true; 
/*     */         } 
/*     */         return false;
/*     */       });
/*     */   }
/*     */   private static final Predicate<ItemStack> d;
/*     */   public static boolean a(ItemStack var0) {
/*  31 */     return (b(var0) || c(var0));
/*     */   }
/*     */   
/*     */   protected static boolean b(ItemStack var0) {
/*  35 */     for (int var1 = 0, var2 = b.size(); var1 < var2; var1++) {
/*  36 */       if (PredicatedCombination.a(b.get(var1)).test(var0)) {
/*  37 */         return true;
/*     */       }
/*     */     } 
/*  40 */     return false;
/*     */   }
/*     */   
/*     */   protected static boolean c(ItemStack var0) {
/*  44 */     for (int var1 = 0, var2 = a.size(); var1 < var2; var1++) {
/*  45 */       if (PredicatedCombination.a(a.get(var1)).test(var0)) {
/*  46 */         return true;
/*     */       }
/*     */     } 
/*  49 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean a(PotionRegistry var0) {
/*  53 */     for (int var1 = 0, var2 = a.size(); var1 < var2; var1++) {
/*  54 */       if (PredicatedCombination.b(a.get(var1)) == var0) {
/*  55 */         return true;
/*     */       }
/*     */     } 
/*  58 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(ItemStack var0, ItemStack var1) {
/*  63 */     if (!d.test(var0)) {
/*  64 */       return false;
/*     */     }
/*     */     
/*  67 */     return (b(var0, var1) || c(var0, var1));
/*     */   }
/*     */   
/*     */   protected static boolean b(ItemStack var0, ItemStack var1) {
/*  71 */     Item var2 = var0.getItem();
/*  72 */     for (int var3 = 0, var4 = b.size(); var3 < var4; var3++) {
/*  73 */       PredicatedCombination<Item> var5 = b.get(var3);
/*  74 */       if (PredicatedCombination.c(var5) == var2 && PredicatedCombination.a(var5).test(var1)) {
/*  75 */         return true;
/*     */       }
/*     */     } 
/*  78 */     return false;
/*     */   }
/*     */   
/*     */   protected static boolean c(ItemStack var0, ItemStack var1) {
/*  82 */     PotionRegistry var2 = PotionUtil.d(var0);
/*  83 */     for (int var3 = 0, var4 = a.size(); var3 < var4; var3++) {
/*  84 */       PredicatedCombination<PotionRegistry> var5 = a.get(var3);
/*  85 */       if (PredicatedCombination.c(var5) == var2 && PredicatedCombination.a(var5).test(var1)) {
/*  86 */         return true;
/*     */       }
/*     */     } 
/*  89 */     return false;
/*     */   }
/*     */   
/*     */   public static ItemStack d(ItemStack var0, ItemStack var1) {
/*  93 */     if (!var1.isEmpty()) {
/*  94 */       PotionRegistry var2 = PotionUtil.d(var1);
/*  95 */       Item var3 = var1.getItem(); int var4, var5;
/*  96 */       for (var4 = 0, var5 = b.size(); var4 < var5; var4++) {
/*  97 */         PredicatedCombination<Item> var6 = b.get(var4);
/*  98 */         if (PredicatedCombination.c(var6) == var3 && PredicatedCombination.a(var6).test(var0)) {
/*  99 */           return PotionUtil.a(new ItemStack((IMaterial)PredicatedCombination.b(var6)), var2);
/*     */         }
/*     */       } 
/*     */       
/* 103 */       for (var4 = 0, var5 = a.size(); var4 < var5; var4++) {
/* 104 */         PredicatedCombination<PotionRegistry> var6 = a.get(var4);
/* 105 */         if (PredicatedCombination.c(var6) == var2 && PredicatedCombination.a(var6).test(var0)) {
/* 106 */           return PotionUtil.a(new ItemStack(var3), (PotionRegistry)PredicatedCombination.b(var6));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 111 */     return var1;
/*     */   }
/*     */   
/*     */   public static void a() {
/* 115 */     a(Items.POTION);
/* 116 */     a(Items.SPLASH_POTION);
/* 117 */     a(Items.LINGERING_POTION);
/*     */     
/* 119 */     a(Items.POTION, Items.GUNPOWDER, Items.SPLASH_POTION);
/* 120 */     a(Items.SPLASH_POTION, Items.DRAGON_BREATH, Items.LINGERING_POTION);
/*     */     
/* 122 */     a(Potions.WATER, Items.GLISTERING_MELON_SLICE, Potions.MUNDANE);
/* 123 */     a(Potions.WATER, Items.GHAST_TEAR, Potions.MUNDANE);
/* 124 */     a(Potions.WATER, Items.RABBIT_FOOT, Potions.MUNDANE);
/* 125 */     a(Potions.WATER, Items.BLAZE_POWDER, Potions.MUNDANE);
/* 126 */     a(Potions.WATER, Items.SPIDER_EYE, Potions.MUNDANE);
/* 127 */     a(Potions.WATER, Items.SUGAR, Potions.MUNDANE);
/* 128 */     a(Potions.WATER, Items.MAGMA_CREAM, Potions.MUNDANE);
/*     */     
/* 130 */     a(Potions.WATER, Items.GLOWSTONE_DUST, Potions.THICK);
/*     */     
/* 132 */     a(Potions.WATER, Items.REDSTONE, Potions.MUNDANE);
/*     */     
/* 134 */     a(Potions.WATER, Items.NETHER_WART, Potions.AWKWARD);
/*     */     
/* 136 */     a(Potions.AWKWARD, Items.GOLDEN_CARROT, Potions.NIGHT_VISION);
/* 137 */     a(Potions.NIGHT_VISION, Items.REDSTONE, Potions.LONG_NIGHT_VISION);
/*     */     
/* 139 */     a(Potions.NIGHT_VISION, Items.FERMENTED_SPIDER_EYE, Potions.INVISIBILITY);
/* 140 */     a(Potions.LONG_NIGHT_VISION, Items.FERMENTED_SPIDER_EYE, Potions.LONG_INVISIBILITY);
/*     */     
/* 142 */     a(Potions.INVISIBILITY, Items.REDSTONE, Potions.LONG_INVISIBILITY);
/*     */     
/* 144 */     a(Potions.AWKWARD, Items.MAGMA_CREAM, Potions.FIRE_RESISTANCE);
/* 145 */     a(Potions.FIRE_RESISTANCE, Items.REDSTONE, Potions.LONG_FIRE_RESISTANCE);
/*     */     
/* 147 */     a(Potions.AWKWARD, Items.RABBIT_FOOT, Potions.LEAPING);
/* 148 */     a(Potions.LEAPING, Items.REDSTONE, Potions.LONG_LEAPING);
/* 149 */     a(Potions.LEAPING, Items.GLOWSTONE_DUST, Potions.STRONG_LEAPING);
/*     */     
/* 151 */     a(Potions.LEAPING, Items.FERMENTED_SPIDER_EYE, Potions.SLOWNESS);
/* 152 */     a(Potions.LONG_LEAPING, Items.FERMENTED_SPIDER_EYE, Potions.LONG_SLOWNESS);
/*     */     
/* 154 */     a(Potions.SLOWNESS, Items.REDSTONE, Potions.LONG_SLOWNESS);
/*     */     
/* 156 */     a(Potions.SLOWNESS, Items.GLOWSTONE_DUST, Potions.STRONG_SLOWNESS);
/* 157 */     a(Potions.AWKWARD, Items.TURTLE_HELMET, Potions.TURTLE_MASTER);
/* 158 */     a(Potions.TURTLE_MASTER, Items.REDSTONE, Potions.LONG_TURTLE_MASTER);
/* 159 */     a(Potions.TURTLE_MASTER, Items.GLOWSTONE_DUST, Potions.STRONG_TURTLE_MASTER);
/*     */     
/* 161 */     a(Potions.SWIFTNESS, Items.FERMENTED_SPIDER_EYE, Potions.SLOWNESS);
/* 162 */     a(Potions.LONG_SWIFTNESS, Items.FERMENTED_SPIDER_EYE, Potions.LONG_SLOWNESS);
/*     */     
/* 164 */     a(Potions.AWKWARD, Items.SUGAR, Potions.SWIFTNESS);
/* 165 */     a(Potions.SWIFTNESS, Items.REDSTONE, Potions.LONG_SWIFTNESS);
/* 166 */     a(Potions.SWIFTNESS, Items.GLOWSTONE_DUST, Potions.STRONG_SWIFTNESS);
/*     */     
/* 168 */     a(Potions.AWKWARD, Items.PUFFERFISH, Potions.WATER_BREATHING);
/* 169 */     a(Potions.WATER_BREATHING, Items.REDSTONE, Potions.LONG_WATER_BREATHING);
/*     */     
/* 171 */     a(Potions.AWKWARD, Items.GLISTERING_MELON_SLICE, Potions.HEALING);
/* 172 */     a(Potions.HEALING, Items.GLOWSTONE_DUST, Potions.STRONG_HEALING);
/*     */     
/* 174 */     a(Potions.HEALING, Items.FERMENTED_SPIDER_EYE, Potions.HARMING);
/* 175 */     a(Potions.STRONG_HEALING, Items.FERMENTED_SPIDER_EYE, Potions.STRONG_HARMING);
/*     */     
/* 177 */     a(Potions.HARMING, Items.GLOWSTONE_DUST, Potions.STRONG_HARMING);
/*     */     
/* 179 */     a(Potions.POISON, Items.FERMENTED_SPIDER_EYE, Potions.HARMING);
/* 180 */     a(Potions.LONG_POISON, Items.FERMENTED_SPIDER_EYE, Potions.HARMING);
/* 181 */     a(Potions.STRONG_POISON, Items.FERMENTED_SPIDER_EYE, Potions.STRONG_HARMING);
/*     */     
/* 183 */     a(Potions.AWKWARD, Items.SPIDER_EYE, Potions.POISON);
/* 184 */     a(Potions.POISON, Items.REDSTONE, Potions.LONG_POISON);
/* 185 */     a(Potions.POISON, Items.GLOWSTONE_DUST, Potions.STRONG_POISON);
/*     */     
/* 187 */     a(Potions.AWKWARD, Items.GHAST_TEAR, Potions.REGENERATION);
/* 188 */     a(Potions.REGENERATION, Items.REDSTONE, Potions.LONG_REGENERATION);
/* 189 */     a(Potions.REGENERATION, Items.GLOWSTONE_DUST, Potions.STRONG_REGENERATION);
/*     */     
/* 191 */     a(Potions.AWKWARD, Items.BLAZE_POWDER, Potions.STRENGTH);
/* 192 */     a(Potions.STRENGTH, Items.REDSTONE, Potions.LONG_STRENGTH);
/* 193 */     a(Potions.STRENGTH, Items.GLOWSTONE_DUST, Potions.STRONG_STRENGTH);
/*     */     
/* 195 */     a(Potions.WATER, Items.FERMENTED_SPIDER_EYE, Potions.WEAKNESS);
/* 196 */     a(Potions.WEAKNESS, Items.REDSTONE, Potions.LONG_WEAKNESS);
/*     */     
/* 198 */     a(Potions.AWKWARD, Items.PHANTOM_MEMBRANE, Potions.SLOW_FALLING);
/* 199 */     a(Potions.SLOW_FALLING, Items.REDSTONE, Potions.LONG_SLOW_FALLING);
/*     */   }
/*     */   
/*     */   private static void a(Item var0, Item var1, Item var2) {
/* 203 */     if (!(var0 instanceof ItemPotion)) {
/* 204 */       throw new IllegalArgumentException("Expected a potion, got: " + IRegistry.ITEM.getKey(var0));
/*     */     }
/* 206 */     if (!(var2 instanceof ItemPotion)) {
/* 207 */       throw new IllegalArgumentException("Expected a potion, got: " + IRegistry.ITEM.getKey(var2));
/*     */     }
/* 209 */     b.add(new PredicatedCombination<>(var0, RecipeItemStack.a(new IMaterial[] { var1 }, ), var2));
/*     */   }
/*     */   
/*     */   private static void a(Item var0) {
/* 213 */     if (!(var0 instanceof ItemPotion)) {
/* 214 */       throw new IllegalArgumentException("Expected a potion, got: " + IRegistry.ITEM.getKey(var0));
/*     */     }
/* 216 */     c.add(RecipeItemStack.a(new IMaterial[] { var0 }));
/*     */   }
/*     */   
/*     */   private static void a(PotionRegistry var0, Item var1, PotionRegistry var2) {
/* 220 */     a.add(new PredicatedCombination<>(var0, RecipeItemStack.a(new IMaterial[] { var1 }, ), var2));
/*     */   }
/*     */   
/*     */   static class PredicatedCombination<T> {
/*     */     private final T a;
/*     */     private final RecipeItemStack b;
/*     */     private final T c;
/*     */     
/*     */     public PredicatedCombination(T var0, RecipeItemStack var1, T var2) {
/* 229 */       this.a = var0;
/* 230 */       this.b = var1;
/* 231 */       this.c = var2;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PotionBrewer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */