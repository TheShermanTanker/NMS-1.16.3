/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.type.StructureBlock;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftStructureBlock extends CraftBlockData implements StructureBlock {
/*  8 */   private static final BlockStateEnum<?> MODE = getEnum("mode");
/*    */ 
/*    */   
/*    */   public StructureBlock.Mode getMode() {
/* 12 */     return (StructureBlock.Mode)get(MODE, StructureBlock.Mode.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMode(StructureBlock.Mode mode) {
/* 17 */     set(MODE, (Enum)mode);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftStructureBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */