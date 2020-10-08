/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.BlockStructure;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import org.bukkit.block.data.type.StructureBlock;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftStructure extends CraftBlockData implements StructureBlock {
/*    */   public CraftStructure() {}
/*    */   
/*    */   public CraftStructure(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 18 */   private static final BlockStateEnum<?> MODE = getEnum(BlockStructure.class, "mode");
/*    */ 
/*    */   
/*    */   public StructureBlock.Mode getMode() {
/* 22 */     return (StructureBlock.Mode)get(MODE, StructureBlock.Mode.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMode(StructureBlock.Mode mode) {
/* 27 */     set(MODE, (Enum)mode);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftStructure.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */