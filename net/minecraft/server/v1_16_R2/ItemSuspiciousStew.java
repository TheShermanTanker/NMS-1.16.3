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
/*    */ public class ItemSuspiciousStew
/*    */   extends Item
/*    */ {
/*    */   public ItemSuspiciousStew(Item.Info var0) {
/* 19 */     super(var0);
/*    */   }
/*    */   
/*    */   public static void a(ItemStack var0, MobEffectList var1, int var2) {
/* 23 */     NBTTagCompound var3 = var0.getOrCreateTag();
/* 24 */     NBTTagList var4 = var3.getList("Effects", 9);
/* 25 */     NBTTagCompound var5 = new NBTTagCompound();
/*    */     
/* 27 */     var5.setByte("EffectId", (byte)MobEffectList.getId(var1));
/* 28 */     var5.setInt("EffectDuration", var2);
/* 29 */     var4.add(var5);
/* 30 */     var3.set("Effects", var4);
/*    */   }
/*    */ 
/*    */   
/*    */   public ItemStack a(ItemStack var0, World var1, EntityLiving var2) {
/* 35 */     ItemStack var3 = super.a(var0, var1, var2);
/*    */     
/* 37 */     NBTTagCompound var4 = var0.getTag();
/* 38 */     if (var4 != null && var4.hasKeyOfType("Effects", 9)) {
/* 39 */       NBTTagList var5 = var4.getList("Effects", 10);
/*    */       
/* 41 */       for (int var6 = 0; var6 < var5.size(); var6++) {
/* 42 */         int var7 = 160;
/* 43 */         NBTTagCompound var8 = var5.getCompound(var6);
/* 44 */         if (var8.hasKeyOfType("EffectDuration", 3)) {
/* 45 */           var7 = var8.getInt("EffectDuration");
/*    */         }
/* 47 */         MobEffectList var9 = MobEffectList.fromId(var8.getByte("EffectId"));
/* 48 */         if (var9 != null) {
/* 49 */           var2.addEffect(new MobEffect(var9, var7));
/*    */         }
/*    */       } 
/*    */     } 
/*    */     
/* 54 */     if (var2 instanceof EntityHuman && ((EntityHuman)var2).abilities.canInstantlyBuild) {
/* 55 */       return var3;
/*    */     }
/* 57 */     return new ItemStack(Items.BOWL);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemSuspiciousStew.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */