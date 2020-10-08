/*    */ package org.bukkit.craftbukkit.v1_16_R2;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.Block;
/*    */ import net.minecraft.server.v1_16_R2.Item;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Color;
/*    */ import org.bukkit.Effect;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*    */ import org.bukkit.potion.Potion;
/*    */ 
/*    */ public class CraftEffect
/*    */ {
/*    */   public static <T> int getDataValue(Effect effect, T data) {
/* 16 */     switch (effect)
/*    */     { case VILLAGER_PLANT_GROW:
/* 18 */         datavalue = ((Integer)data).intValue();
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
/*    */ 
/*    */         
/* 71 */         return datavalue;case POTION_BREAK: datavalue = ((Potion)data).toDamageValue() & 0x3F; return datavalue;case INSTANT_POTION_BREAK: datavalue = ((Color)data).asRGB(); return datavalue;case RECORD_PLAY: Validate.isTrue((data == Material.AIR || ((Material)data).isRecord()), "Invalid record type!"); datavalue = Item.getId(CraftMagicNumbers.getItem((Material)data)); return datavalue;case SMOKE: switch ((BlockFace)data) { case VILLAGER_PLANT_GROW: datavalue = 0; return datavalue;case POTION_BREAK: datavalue = 1; return datavalue;case INSTANT_POTION_BREAK: datavalue = 2; return datavalue;case RECORD_PLAY: datavalue = 3; return datavalue;case SMOKE: case STEP_SOUND: datavalue = 4; return datavalue;case null: datavalue = 5; return datavalue;case null: datavalue = 6; return datavalue;case null: datavalue = 7; return datavalue;case null: datavalue = 8; return datavalue; }  throw new IllegalArgumentException("Bad smoke direction!");case STEP_SOUND: Validate.isTrue(((Material)data).isBlock(), "Material is not a block!"); datavalue = Block.getCombinedId(CraftMagicNumbers.getBlock((Material)data).getBlockData()); return datavalue; }  int datavalue = 0; return datavalue;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */