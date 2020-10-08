/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemWrittenBook
/*     */   extends Item
/*     */ {
/*     */   public ItemWrittenBook(Item.Info var0) {
/*  43 */     super(var0);
/*     */   }
/*     */   
/*     */   public static boolean a(@Nullable NBTTagCompound var0) {
/*  47 */     if (!ItemBookAndQuill.a(var0)) {
/*  48 */       return false;
/*     */     }
/*     */     
/*  51 */     if (!var0.hasKeyOfType("title", 8)) {
/*  52 */       return false;
/*     */     }
/*  54 */     String var1 = var0.getString("title");
/*  55 */     if (var1.length() > 32) {
/*  56 */       return false;
/*     */     }
/*     */     
/*  59 */     return var0.hasKeyOfType("author", 8);
/*     */   }
/*     */   
/*     */   public static int d(ItemStack var0) {
/*  63 */     return var0.getTag().getInt("generation");
/*     */   }
/*     */   
/*     */   public static int g(ItemStack var0) {
/*  67 */     NBTTagCompound var1 = var0.getTag();
/*  68 */     return (var1 != null) ? var1.getList("pages", 8).size() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent h(ItemStack var0) {
/*  73 */     if (var0.hasTag()) {
/*  74 */       NBTTagCompound var1 = var0.getTag();
/*     */       
/*  76 */       String var2 = var1.getString("title");
/*  77 */       if (!UtilColor.b(var2)) {
/*  78 */         return new ChatComponentText(var2);
/*     */       }
/*     */     } 
/*  81 */     return super.h(var0);
/*     */   }
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
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(ItemActionContext var0) {
/* 100 */     World var1 = var0.getWorld();
/* 101 */     BlockPosition var2 = var0.getClickPosition();
/* 102 */     IBlockData var3 = var1.getType(var2);
/*     */     
/* 104 */     if (var3.a(Blocks.LECTERN)) {
/* 105 */       return BlockLectern.a(var1, var2, var3, var0.getItemStack()) ? EnumInteractionResult.a(var1.isClientSide) : EnumInteractionResult.PASS;
/*     */     }
/*     */     
/* 108 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 113 */     ItemStack var3 = var1.b(var2);
/* 114 */     var1.openBook(var3, var2);
/* 115 */     var1.b(StatisticList.ITEM_USED.b(this));
/* 116 */     return InteractionResultWrapper.a(var3, var0.s_());
/*     */   }
/*     */   
/*     */   public static boolean a(ItemStack var0, @Nullable CommandListenerWrapper var1, @Nullable EntityHuman var2) {
/* 120 */     NBTTagCompound var3 = var0.getTag();
/* 121 */     if (var3 == null || var3.getBoolean("resolved")) {
/* 122 */       return false;
/*     */     }
/* 124 */     var3.setBoolean("resolved", true);
/* 125 */     if (!a(var3)) {
/* 126 */       return false;
/*     */     }
/* 128 */     NBTTagList var4 = var3.getList("pages", 8);
/* 129 */     for (int var5 = 0; var5 < var4.size(); var5++) {
/* 130 */       IChatBaseComponent var7; String var6 = var4.getString(var5);
/*     */       
/*     */       try {
/* 133 */         var7 = IChatBaseComponent.ChatSerializer.b(var6);
/* 134 */         var7 = ChatComponentUtils.filterForDisplay(var1, var7, var2, 0);
/* 135 */       } catch (Exception var8) {
/* 136 */         var7 = new ChatComponentText(var6);
/*     */       } 
/* 138 */       var4.set(var5, NBTTagString.a(IChatBaseComponent.ChatSerializer.a(var7)));
/*     */     } 
/* 140 */     var3.set("pages", var4);
/* 141 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean e(ItemStack var0) {
/* 146 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemWrittenBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */