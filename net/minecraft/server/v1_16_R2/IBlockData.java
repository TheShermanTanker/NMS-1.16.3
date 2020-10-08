/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.MapCodec;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*    */ 
/*    */ public class IBlockData extends BlockBase.BlockData {
/*  9 */   public static final Codec<IBlockData> b = a(IRegistry.BLOCK, Block::getBlockData).stable();
/*    */ 
/*    */   
/*    */   Material cachedMaterial;
/*    */ 
/*    */   
/*    */   public final Material getBukkitMaterial() {
/* 16 */     if (this.cachedMaterial == null) {
/* 17 */       this.cachedMaterial = CraftMagicNumbers.getMaterial(getBlock());
/*    */     }
/*    */     
/* 20 */     return this.cachedMaterial;
/*    */   }
/*    */ 
/*    */   
/*    */   public IBlockData(Block block, ImmutableMap<IBlockState<?>, Comparable<?>> immutablemap, MapCodec<IBlockData> mapcodec) {
/* 25 */     super(block, immutablemap, mapcodec);
/*    */   }
/*    */ 
/*    */   
/*    */   protected IBlockData p() {
/* 30 */     return this;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\IBlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */