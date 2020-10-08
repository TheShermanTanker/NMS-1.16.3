/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.authlib.GameProfile;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringUtils;
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
/*    */ public class BlockSkullPlayer
/*    */   extends BlockSkull
/*    */ {
/*    */   protected BlockSkullPlayer(BlockBase.Info var0) {
/* 20 */     super(BlockSkull.Type.PLAYER, var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public void postPlace(World var0, BlockPosition var1, IBlockData var2, @Nullable EntityLiving var3, ItemStack var4) {
/* 25 */     super.postPlace(var0, var1, var2, var3, var4);
/*    */     
/* 27 */     TileEntity var5 = var0.getTileEntity(var1);
/*    */     
/* 29 */     if (var5 instanceof TileEntitySkull) {
/* 30 */       TileEntitySkull var6 = (TileEntitySkull)var5;
/* 31 */       GameProfile var7 = null;
/* 32 */       if (var4.hasTag()) {
/* 33 */         NBTTagCompound var8 = var4.getTag();
/*    */ 
/*    */         
/* 36 */         if (var8.hasKeyOfType("SkullOwner", 10)) {
/* 37 */           var7 = GameProfileSerializer.deserialize(var8.getCompound("SkullOwner"));
/* 38 */         } else if (var8.hasKeyOfType("SkullOwner", 8) && !StringUtils.isBlank(var8.getString("SkullOwner"))) {
/* 39 */           var7 = new GameProfile(null, var8.getString("SkullOwner"));
/*    */         } 
/*    */       } 
/*    */       
/* 43 */       var6.setGameProfile(var7);
/*    */     } 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockSkullPlayer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */