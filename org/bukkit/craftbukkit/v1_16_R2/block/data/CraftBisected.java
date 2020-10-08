/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import org.bukkit.block.data.Bisected;
/*    */ 
/*    */ public class CraftBisected extends CraftBlockData implements Bisected {
/*  7 */   private static final BlockStateEnum<?> HALF = getEnum("half");
/*    */ 
/*    */   
/*    */   public Bisected.Half getHalf() {
/* 11 */     return get(HALF, Bisected.Half.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setHalf(Bisected.Half half) {
/* 16 */     set(HALF, (Enum<Enum>)half);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftBisected.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */