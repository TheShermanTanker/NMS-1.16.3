/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.BlockStepAbstract;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.Waterlogged;
/*    */ import org.bukkit.block.data.type.Slab;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftStepAbstract extends CraftBlockData implements Slab, Waterlogged {
/*    */   public CraftStepAbstract(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftStepAbstract() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> TYPE = getEnum(BlockStepAbstract.class, "type");
/*    */ 
/*    */   
/*    */   public Slab.Type getType() {
/* 22 */     return (Slab.Type)get(TYPE, Slab.Type.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setType(Slab.Type type) {
/* 27 */     set(TYPE, (Enum)type);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 32 */   private static final BlockStateBoolean WATERLOGGED = getBoolean(BlockStepAbstract.class, "waterlogged");
/*    */ 
/*    */   
/*    */   public boolean isWaterlogged() {
/* 36 */     return ((Boolean)get((IBlockState)WATERLOGGED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setWaterlogged(boolean waterlogged) {
/* 41 */     set((IBlockState)WATERLOGGED, Boolean.valueOf(waterlogged));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftStepAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */