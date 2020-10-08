/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemEnchantedBook
/*    */   extends Item
/*    */ {
/*    */   public ItemEnchantedBook(Item.Info var0) {
/* 21 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean e(ItemStack var0) {
/* 26 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean f_(ItemStack var0) {
/* 31 */     return false;
/*    */   }
/*    */   
/*    */   public static NBTTagList d(ItemStack var0) {
/* 35 */     NBTTagCompound var1 = var0.getTag();
/* 36 */     if (var1 != null) {
/* 37 */       return var1.getList("StoredEnchantments", 10);
/*    */     }
/*    */     
/* 40 */     return new NBTTagList();
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void a(ItemStack var0, WeightedRandomEnchant var1) {
/* 50 */     NBTTagList var2 = d(var0);
/* 51 */     boolean var3 = true;
/*    */     
/* 53 */     MinecraftKey var4 = IRegistry.ENCHANTMENT.getKey(var1.enchantment);
/* 54 */     for (int var5 = 0; var5 < var2.size(); var5++) {
/* 55 */       NBTTagCompound var6 = var2.getCompound(var5);
/*    */       
/* 57 */       MinecraftKey var7 = MinecraftKey.a(var6.getString("id"));
/* 58 */       if (var7 != null && var7.equals(var4)) {
/* 59 */         if (var6.getInt("lvl") < var1.level) {
/* 60 */           var6.setShort("lvl", (short)var1.level);
/*    */         }
/*    */         
/* 63 */         var3 = false;
/*    */         
/*    */         break;
/*    */       } 
/*    */     } 
/* 68 */     if (var3) {
/* 69 */       NBTTagCompound nBTTagCompound = new NBTTagCompound();
/*    */       
/* 71 */       nBTTagCompound.setString("id", String.valueOf(var4));
/* 72 */       nBTTagCompound.setShort("lvl", (short)var1.level);
/*    */       
/* 74 */       var2.add(nBTTagCompound);
/*    */     } 
/*    */     
/* 77 */     var0.getOrCreateTag().set("StoredEnchantments", var2);
/*    */   }
/*    */   
/*    */   public static ItemStack a(WeightedRandomEnchant var0) {
/* 81 */     ItemStack var1 = new ItemStack(Items.ENCHANTED_BOOK);
/* 82 */     a(var1, var0);
/* 83 */     return var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(CreativeModeTab var0, NonNullList<ItemStack> var1) {
/* 88 */     if (var0 == CreativeModeTab.g) {
/* 89 */       for (Enchantment var3 : IRegistry.ENCHANTMENT) {
/* 90 */         if (var3.itemTarget != null) {
/* 91 */           for (int var4 = var3.getStartLevel(); var4 <= var3.getMaxLevel(); var4++) {
/* 92 */             var1.add(a(new WeightedRandomEnchant(var3, var4)));
/*    */           }
/*    */         }
/*    */       } 
/* 96 */     } else if ((var0.n()).length != 0) {
/* 97 */       for (Enchantment var3 : IRegistry.ENCHANTMENT) {
/* 98 */         if (var0.a(var3.itemTarget))
/* 99 */           var1.add(a(new WeightedRandomEnchant(var3, var3.getMaxLevel()))); 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemEnchantedBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */