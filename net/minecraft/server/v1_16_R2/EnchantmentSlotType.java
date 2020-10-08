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
/*    */ public enum EnchantmentSlotType
/*    */ {
/* 17 */   ARMOR
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 20 */       return var0 instanceof ItemArmor;
/*    */     }
/*    */   },
/* 23 */   ARMOR_FEET
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 26 */       return (var0 instanceof ItemArmor && ((ItemArmor)var0).b() == EnumItemSlot.FEET);
/*    */     }
/*    */   },
/* 29 */   ARMOR_LEGS
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 32 */       return (var0 instanceof ItemArmor && ((ItemArmor)var0).b() == EnumItemSlot.LEGS);
/*    */     }
/*    */   },
/* 35 */   ARMOR_CHEST
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 38 */       return (var0 instanceof ItemArmor && ((ItemArmor)var0).b() == EnumItemSlot.CHEST);
/*    */     }
/*    */   },
/* 41 */   ARMOR_HEAD
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 44 */       return (var0 instanceof ItemArmor && ((ItemArmor)var0).b() == EnumItemSlot.HEAD);
/*    */     }
/*    */   },
/* 47 */   WEAPON
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 50 */       return var0 instanceof ItemSword;
/*    */     }
/*    */   },
/* 53 */   DIGGER
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 56 */       return var0 instanceof ItemTool;
/*    */     }
/*    */   },
/* 59 */   FISHING_ROD
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 62 */       return var0 instanceof ItemFishingRod;
/*    */     }
/*    */   },
/* 65 */   TRIDENT
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 68 */       return var0 instanceof ItemTrident;
/*    */     }
/*    */   },
/* 71 */   BREAKABLE
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 74 */       return var0.usesDurability();
/*    */     }
/*    */   },
/* 77 */   BOW
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 80 */       return var0 instanceof ItemBow;
/*    */     }
/*    */   },
/* 83 */   WEARABLE
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 86 */       return (var0 instanceof ItemWearable || Block.asBlock(var0) instanceof ItemWearable);
/*    */     }
/*    */   },
/* 89 */   CROSSBOW
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 92 */       return var0 instanceof ItemCrossbow;
/*    */     }
/*    */   },
/* 95 */   VANISHABLE
/*    */   {
/*    */     public boolean canEnchant(Item var0) {
/* 98 */       return (var0 instanceof ItemVanishable || Block.asBlock(var0) instanceof ItemVanishable || BREAKABLE.canEnchant(var0));
/*    */     }
/*    */   };
/*    */   
/*    */   public abstract boolean canEnchant(Item paramItem);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentSlotType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */