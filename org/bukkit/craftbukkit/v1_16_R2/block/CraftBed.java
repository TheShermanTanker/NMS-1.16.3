/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntityBed;
/*    */ import org.bukkit.DyeColor;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Bed;
/*    */ import org.bukkit.block.Block;
/*    */ 
/*    */ public class CraftBed
/*    */   extends CraftBlockEntityState<TileEntityBed> implements Bed {
/*    */   public CraftBed(Block block) {
/* 12 */     super(block, TileEntityBed.class);
/*    */   }
/*    */   
/*    */   public CraftBed(Material material, TileEntityBed te) {
/* 16 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public DyeColor getColor() {
/* 21 */     switch (getType()) {
/*    */       case BLACK_BED:
/* 23 */         return DyeColor.BLACK;
/*    */       case BLUE_BED:
/* 25 */         return DyeColor.BLUE;
/*    */       case BROWN_BED:
/* 27 */         return DyeColor.BROWN;
/*    */       case CYAN_BED:
/* 29 */         return DyeColor.CYAN;
/*    */       case GRAY_BED:
/* 31 */         return DyeColor.GRAY;
/*    */       case GREEN_BED:
/* 33 */         return DyeColor.GREEN;
/*    */       case LIGHT_BLUE_BED:
/* 35 */         return DyeColor.LIGHT_BLUE;
/*    */       case LIGHT_GRAY_BED:
/* 37 */         return DyeColor.LIGHT_GRAY;
/*    */       case LIME_BED:
/* 39 */         return DyeColor.LIME;
/*    */       case MAGENTA_BED:
/* 41 */         return DyeColor.MAGENTA;
/*    */       case ORANGE_BED:
/* 43 */         return DyeColor.ORANGE;
/*    */       case PINK_BED:
/* 45 */         return DyeColor.PINK;
/*    */       case PURPLE_BED:
/* 47 */         return DyeColor.PURPLE;
/*    */       case RED_BED:
/* 49 */         return DyeColor.RED;
/*    */       case WHITE_BED:
/* 51 */         return DyeColor.WHITE;
/*    */       case YELLOW_BED:
/* 53 */         return DyeColor.YELLOW;
/*    */     } 
/* 55 */     throw new IllegalArgumentException("Unknown DyeColor for " + getType());
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public void setColor(DyeColor color) {
/* 61 */     throw new UnsupportedOperationException("Must set block type to appropriate bed colour");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBed.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */