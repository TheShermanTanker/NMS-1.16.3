/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.type.Comparator;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftComparator extends CraftBlockData implements Comparator {
/*  8 */   private static final BlockStateEnum<?> MODE = getEnum("mode");
/*    */ 
/*    */   
/*    */   public Comparator.Mode getMode() {
/* 12 */     return (Comparator.Mode)get(MODE, Comparator.Mode.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setMode(Comparator.Mode mode) {
/* 17 */     set(MODE, (Enum)mode);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */