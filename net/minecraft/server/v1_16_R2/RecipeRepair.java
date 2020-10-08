/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
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
/*     */ public class RecipeRepair
/*     */   extends IRecipeComplex
/*     */ {
/*     */   public RecipeRepair(MinecraftKey var0) {
/*  19 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(InventoryCrafting var0, World var1) {
/*  24 */     List<ItemStack> var2 = Lists.newArrayList();
/*     */     
/*  26 */     for (int var3 = 0; var3 < var0.getSize(); var3++) {
/*  27 */       ItemStack var4 = var0.getItem(var3);
/*     */       
/*  29 */       if (!var4.isEmpty()) {
/*  30 */         var2.add(var4);
/*     */         
/*  32 */         if (var2.size() > 1) {
/*  33 */           ItemStack var5 = var2.get(0);
/*  34 */           if (var4.getItem() != var5.getItem() || var5.getCount() != 1 || var4.getCount() != 1 || !var5.getItem().usesDurability()) {
/*  35 */             return false;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  41 */     return (var2.size() == 2);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(InventoryCrafting var0) {
/*  46 */     List<ItemStack> var1 = Lists.newArrayList();
/*     */     
/*  48 */     for (int var2 = 0; var2 < var0.getSize(); var2++) {
/*  49 */       ItemStack var3 = var0.getItem(var2);
/*     */       
/*  51 */       if (!var3.isEmpty()) {
/*  52 */         var1.add(var3);
/*     */         
/*  54 */         if (var1.size() > 1) {
/*  55 */           ItemStack var4 = var1.get(0);
/*  56 */           if (var3.getItem() != var4.getItem() || var4.getCount() != 1 || var3.getCount() != 1 || !var4.getItem().usesDurability()) {
/*  57 */             return ItemStack.b;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  63 */     if (var1.size() == 2) {
/*  64 */       ItemStack itemStack1 = var1.get(0);
/*  65 */       ItemStack var3 = var1.get(1);
/*     */       
/*  67 */       if (itemStack1.getItem() == var3.getItem() && itemStack1.getCount() == 1 && var3.getCount() == 1 && itemStack1.getItem().usesDurability()) {
/*  68 */         Item var4 = itemStack1.getItem();
/*  69 */         int var5 = var4.getMaxDurability() - itemStack1.getDamage();
/*  70 */         int var6 = var4.getMaxDurability() - var3.getDamage();
/*  71 */         int var7 = var5 + var6 + var4.getMaxDurability() * 5 / 100;
/*  72 */         int var8 = var4.getMaxDurability() - var7;
/*  73 */         if (var8 < 0) {
/*  74 */           var8 = 0;
/*     */         }
/*     */         
/*  77 */         ItemStack var9 = new ItemStack(itemStack1.getItem());
/*  78 */         var9.setDamage(var8);
/*     */         
/*  80 */         Map<Enchantment, Integer> var10 = Maps.newHashMap();
/*  81 */         Map<Enchantment, Integer> var11 = EnchantmentManager.a(itemStack1);
/*  82 */         Map<Enchantment, Integer> var12 = EnchantmentManager.a(var3);
/*  83 */         IRegistry.ENCHANTMENT.g().filter(Enchantment::c).forEach(var3 -> {
/*     */               int var4 = Math.max(((Integer)var0.getOrDefault(var3, Integer.valueOf(0))).intValue(), ((Integer)var1.getOrDefault(var3, Integer.valueOf(0))).intValue());
/*     */               
/*     */               if (var4 > 0) {
/*     */                 var2.put(var3, Integer.valueOf(var4));
/*     */               }
/*     */             });
/*  90 */         if (!var10.isEmpty()) {
/*  91 */           EnchantmentManager.a(var10, var9);
/*     */         }
/*     */         
/*  94 */         return var9;
/*     */       } 
/*     */     } 
/*     */     
/*  98 */     return ItemStack.b;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public RecipeSerializer<?> getRecipeSerializer() {
/* 108 */     return RecipeSerializer.o;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RecipeRepair.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */