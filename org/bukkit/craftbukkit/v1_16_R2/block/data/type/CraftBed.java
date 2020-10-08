/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.Bed;
/*    */ 
/*    */ public abstract class CraftBed extends CraftBlockData implements Bed {
/*  8 */   private static final BlockStateEnum<?> PART = getEnum("part");
/*  9 */   private static final BlockStateBoolean OCCUPIED = getBoolean("occupied");
/*    */ 
/*    */   
/*    */   public Bed.Part getPart() {
/* 13 */     return (Bed.Part)get(PART, Bed.Part.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPart(Bed.Part part) {
/* 18 */     set(PART, (Enum)part);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOccupied() {
/* 23 */     return ((Boolean)get((IBlockState)OCCUPIED)).booleanValue();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */