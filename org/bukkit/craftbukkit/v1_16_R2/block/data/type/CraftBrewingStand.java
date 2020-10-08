/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ 
/*    */ public abstract class CraftBrewingStand extends CraftBlockData implements BrewingStand {
/*  8 */   private static final BlockStateBoolean[] HAS_BOTTLE = new BlockStateBoolean[] {
/*  9 */       getBoolean("has_bottle_0"), getBoolean("has_bottle_1"), getBoolean("has_bottle_2")
/*    */     };
/*    */ 
/*    */   
/*    */   public boolean hasBottle(int bottle) {
/* 14 */     return ((Boolean)get((IBlockState)HAS_BOTTLE[bottle])).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBottle(int bottle, boolean has) {
/* 19 */     set((IBlockState)HAS_BOTTLE[bottle], Boolean.valueOf(has));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Integer> getBottles() {
/* 24 */     ImmutableSet.Builder<Integer> bottles = ImmutableSet.builder();
/*    */     
/* 26 */     for (int index = 0; index < getMaximumBottles(); index++) {
/* 27 */       if (hasBottle(index)) {
/* 28 */         bottles.add(Integer.valueOf(index));
/*    */       }
/*    */     } 
/*    */     
/* 32 */     return (Set<Integer>)bottles.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumBottles() {
/* 37 */     return HAS_BOTTLE.length;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */