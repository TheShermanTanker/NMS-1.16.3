/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import javax.annotation.Nullable;
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
/*    */ public class ItemScaffolding
/*    */   extends ItemBlock
/*    */ {
/*    */   public ItemScaffolding(Block var0, Item.Info var1) {
/* 22 */     super(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   @Nullable
/*    */   public BlockActionContext b(BlockActionContext var0) {
/* 28 */     BlockPosition var1 = var0.getClickPosition();
/* 29 */     World var2 = var0.getWorld();
/*    */     
/* 31 */     IBlockData var3 = var2.getType(var1);
/* 32 */     Block var4 = getBlock();
/* 33 */     if (var3.a(var4)) {
/*    */       EnumDirection var5;
/* 35 */       if (var0.isSneaking()) {
/* 36 */         var5 = var0.l() ? var0.getClickedFace().opposite() : var0.getClickedFace();
/*    */       } else {
/* 38 */         var5 = (var0.getClickedFace() == EnumDirection.UP) ? var0.f() : EnumDirection.UP;
/*    */       } 
/*    */       
/* 41 */       int var6 = 0;
/* 42 */       BlockPosition.MutableBlockPosition var7 = var1.i().c(var5);
/* 43 */       while (var6 < 7) {
/* 44 */         if (!var2.isClientSide && !World.isValidLocation(var7)) {
/*    */           
/* 46 */           EntityHuman var8 = var0.getEntity();
/* 47 */           int var9 = var2.getBuildHeight();
/* 48 */           if (var8 instanceof EntityPlayer && var7.getY() >= var9) {
/* 49 */             PacketPlayOutChat var10 = new PacketPlayOutChat((new ChatMessage("build.tooHigh", new Object[] { Integer.valueOf(var9) })).a(EnumChatFormat.RED), ChatMessageType.GAME_INFO, SystemUtils.b);
/* 50 */             ((EntityPlayer)var8).playerConnection.sendPacket(var10);
/*    */           } 
/*    */           
/*    */           break;
/*    */         } 
/* 55 */         var3 = var2.getType(var7);
/*    */         
/* 57 */         if (!var3.a(getBlock())) {
/* 58 */           if (var3.a(var0)) {
/* 59 */             return BlockActionContext.a(var0, var7, var5);
/*    */           }
/*    */           
/*    */           break;
/*    */         } 
/* 64 */         var7.c(var5);
/* 65 */         if (var5.n().d()) {
/* 66 */           var6++;
/*    */         }
/*    */       } 
/*    */       
/* 70 */       return null;
/*    */     } 
/*    */     
/* 73 */     if (BlockScaffolding.a(var2, var1) == 7) {
/* 74 */       return null;
/*    */     }
/*    */     
/* 77 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   protected boolean isCheckCollisions() {
/* 82 */     return false;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemScaffolding.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */