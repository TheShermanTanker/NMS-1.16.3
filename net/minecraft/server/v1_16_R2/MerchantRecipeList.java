/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MerchantRecipeList
/*     */   extends ArrayList<MerchantRecipe>
/*     */ {
/*     */   public MerchantRecipeList() {}
/*     */   
/*     */   public MerchantRecipeList(NBTTagCompound var0) {
/*  17 */     NBTTagList var1 = var0.getList("Recipes", 10);
/*     */     
/*  19 */     for (int var2 = 0; var2 < var1.size(); var2++) {
/*  20 */       add(new MerchantRecipe(var1.getCompound(var2)));
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public MerchantRecipe a(ItemStack var0, ItemStack var1, int var2) {
/*  26 */     if (var2 > 0 && var2 < size()) {
/*     */       
/*  28 */       MerchantRecipe merchantRecipe = get(var2);
/*  29 */       if (merchantRecipe.a(var0, var1)) {
/*  30 */         return merchantRecipe;
/*     */       }
/*  32 */       return null;
/*     */     } 
/*     */     
/*  35 */     for (int var3 = 0; var3 < size(); var3++) {
/*  36 */       MerchantRecipe var4 = get(var3);
/*  37 */       if (var4.a(var0, var1)) {
/*  38 */         return var4;
/*     */       }
/*     */     } 
/*  41 */     return null;
/*     */   }
/*     */   
/*     */   public void a(PacketDataSerializer var0) {
/*  45 */     var0.writeByte((byte)(size() & 0xFF));
/*  46 */     for (int var1 = 0; var1 < size(); var1++) {
/*  47 */       MerchantRecipe var2 = get(var1);
/*  48 */       var0.a(var2.a());
/*  49 */       var0.a(var2.getSellingItem());
/*     */       
/*  51 */       ItemStack var3 = var2.getBuyItem2();
/*  52 */       var0.writeBoolean(!var3.isEmpty());
/*  53 */       if (!var3.isEmpty()) {
/*  54 */         var0.a(var3);
/*     */       }
/*  56 */       var0.writeBoolean(var2.isFullyUsed());
/*  57 */       var0.writeInt(var2.getUses());
/*  58 */       var0.writeInt(var2.getMaxUses());
/*  59 */       var0.writeInt(var2.getXp());
/*  60 */       var0.writeInt(var2.getSpecialPrice());
/*  61 */       var0.writeFloat(var2.getPriceMultiplier());
/*  62 */       var0.writeInt(var2.getDemand());
/*     */     } 
/*     */   }
/*     */   
/*     */   public static MerchantRecipeList b(PacketDataSerializer var0) {
/*  67 */     MerchantRecipeList var1 = new MerchantRecipeList();
/*     */     
/*  69 */     int var2 = var0.readByte() & 0xFF;
/*  70 */     for (int var3 = 0; var3 < var2; var3++) {
/*  71 */       ItemStack var4 = var0.n();
/*  72 */       ItemStack var5 = var0.n();
/*     */       
/*  74 */       ItemStack var6 = ItemStack.b;
/*  75 */       if (var0.readBoolean()) {
/*  76 */         var6 = var0.n();
/*     */       }
/*  78 */       boolean var7 = var0.readBoolean();
/*  79 */       int var8 = var0.readInt();
/*  80 */       int var9 = var0.readInt();
/*  81 */       int var10 = var0.readInt();
/*  82 */       int var11 = var0.readInt();
/*  83 */       float var12 = var0.readFloat();
/*  84 */       int var13 = var0.readInt();
/*     */       
/*  86 */       MerchantRecipe var14 = new MerchantRecipe(var4, var6, var5, var8, var9, var10, var12, var13);
/*  87 */       if (var7) {
/*  88 */         var14.q();
/*     */       }
/*  90 */       var14.setSpecialPrice(var11);
/*     */       
/*  92 */       var1.add(var14);
/*     */     } 
/*  94 */     return var1;
/*     */   }
/*     */   
/*     */   public NBTTagCompound a() {
/*  98 */     NBTTagCompound var0 = new NBTTagCompound();
/*     */     
/* 100 */     NBTTagList var1 = new NBTTagList();
/* 101 */     for (int var2 = 0; var2 < size(); var2++) {
/* 102 */       MerchantRecipe var3 = get(var2);
/* 103 */       var1.add(var3.t());
/*     */     } 
/* 105 */     var0.set("Recipes", var1);
/* 106 */     return var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MerchantRecipeList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */