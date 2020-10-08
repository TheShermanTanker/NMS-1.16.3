/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class RecipeFireworksStar
/*     */   extends IRecipeComplex
/*     */ {
/*  20 */   private static final RecipeItemStack a = RecipeItemStack.a(new IMaterial[] { Items.FIRE_CHARGE, Items.FEATHER, Items.GOLD_NUGGET, Items.SKELETON_SKULL, Items.WITHER_SKELETON_SKULL, Items.CREEPER_HEAD, Items.PLAYER_HEAD, Items.DRAGON_HEAD, Items.ZOMBIE_HEAD });
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
/*  31 */   private static final RecipeItemStack b = RecipeItemStack.a(new IMaterial[] { Items.DIAMOND }); private static final Map<Item, ItemFireworks.EffectType> d;
/*  32 */   private static final RecipeItemStack c = RecipeItemStack.a(new IMaterial[] { Items.GLOWSTONE_DUST });
/*     */   static {
/*  34 */     d = SystemUtils.<Map<Item, ItemFireworks.EffectType>>a(Maps.newHashMap(), var0 -> {
/*     */           var0.put(Items.FIRE_CHARGE, ItemFireworks.EffectType.LARGE_BALL);
/*     */           var0.put(Items.FEATHER, ItemFireworks.EffectType.BURST);
/*     */           var0.put(Items.GOLD_NUGGET, ItemFireworks.EffectType.STAR);
/*     */           var0.put(Items.SKELETON_SKULL, ItemFireworks.EffectType.CREEPER);
/*     */           var0.put(Items.WITHER_SKELETON_SKULL, ItemFireworks.EffectType.CREEPER);
/*     */           var0.put(Items.CREEPER_HEAD, ItemFireworks.EffectType.CREEPER);
/*     */           var0.put(Items.PLAYER_HEAD, ItemFireworks.EffectType.CREEPER);
/*     */           var0.put(Items.DRAGON_HEAD, ItemFireworks.EffectType.CREEPER);
/*     */           var0.put(Items.ZOMBIE_HEAD, ItemFireworks.EffectType.CREEPER);
/*     */         });
/*     */   }
/*  46 */   private static final RecipeItemStack e = RecipeItemStack.a(new IMaterial[] { Items.GUNPOWDER });
/*     */   
/*     */   public RecipeFireworksStar(MinecraftKey var0) {
/*  49 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(InventoryCrafting var0, World var1) {
/*  54 */     boolean var2 = false;
/*  55 */     boolean var3 = false;
/*  56 */     boolean var4 = false;
/*  57 */     boolean var5 = false;
/*  58 */     boolean var6 = false;
/*     */     
/*  60 */     for (int var7 = 0; var7 < var0.getSize(); var7++) {
/*  61 */       ItemStack var8 = var0.getItem(var7);
/*  62 */       if (!var8.isEmpty())
/*     */       {
/*     */ 
/*     */         
/*  66 */         if (a.test(var8)) {
/*  67 */           if (var4) {
/*  68 */             return false;
/*     */           }
/*  70 */           var4 = true;
/*  71 */         } else if (c.test(var8)) {
/*  72 */           if (var6) {
/*  73 */             return false;
/*     */           }
/*  75 */           var6 = true;
/*  76 */         } else if (b.test(var8)) {
/*  77 */           if (var5) {
/*  78 */             return false;
/*     */           }
/*  80 */           var5 = true;
/*  81 */         } else if (e.test(var8)) {
/*  82 */           if (var2) {
/*  83 */             return false;
/*     */           }
/*  85 */           var2 = true;
/*  86 */         } else if (var8.getItem() instanceof ItemDye) {
/*  87 */           var3 = true;
/*     */         } else {
/*  89 */           return false;
/*     */         } 
/*     */       }
/*     */     } 
/*  93 */     return (var2 && var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(InventoryCrafting var0) {
/*  98 */     ItemStack var1 = new ItemStack(Items.FIREWORK_STAR);
/*  99 */     NBTTagCompound var2 = var1.a("Explosion");
/*     */     
/* 101 */     ItemFireworks.EffectType var3 = ItemFireworks.EffectType.SMALL_BALL;
/* 102 */     List<Integer> var4 = Lists.newArrayList();
/*     */     
/* 104 */     for (int var5 = 0; var5 < var0.getSize(); var5++) {
/* 105 */       ItemStack var6 = var0.getItem(var5);
/* 106 */       if (!var6.isEmpty())
/*     */       {
/*     */ 
/*     */         
/* 110 */         if (a.test(var6)) {
/* 111 */           var3 = d.get(var6.getItem());
/* 112 */         } else if (c.test(var6)) {
/* 113 */           var2.setBoolean("Flicker", true);
/* 114 */         } else if (b.test(var6)) {
/* 115 */           var2.setBoolean("Trail", true);
/* 116 */         } else if (var6.getItem() instanceof ItemDye) {
/* 117 */           var4.add(Integer.valueOf(((ItemDye)var6.getItem()).d().getFireworksColor()));
/*     */         } 
/*     */       }
/*     */     } 
/* 121 */     var2.b("Colors", var4);
/* 122 */     var2.setByte("Type", (byte)var3.a());
/*     */     
/* 124 */     return var1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public ItemStack getResult() {
/* 134 */     return new ItemStack(Items.FIREWORK_STAR);
/*     */   }
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> getRecipeSerializer() {
/* 139 */     return RecipeSerializer.h;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeFireworksStar.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */