/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.type.Bamboo;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public abstract class CraftBamboo extends CraftBlockData implements Bamboo {
/*  8 */   private static final BlockStateEnum<?> LEAVES = getEnum("leaves");
/*    */ 
/*    */   
/*    */   public Bamboo.Leaves getLeaves() {
/* 12 */     return (Bamboo.Leaves)get(LEAVES, Bamboo.Leaves.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setLeaves(Bamboo.Leaves leaves) {
/* 17 */     set(LEAVES, (Enum)leaves);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftBamboo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */