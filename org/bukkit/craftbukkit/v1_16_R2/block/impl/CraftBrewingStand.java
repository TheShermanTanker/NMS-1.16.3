/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockBrewingStand;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.data.type.BrewingStand;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*    */ 
/*    */ public final class CraftBrewingStand extends CraftBlockData implements BrewingStand {
/*    */   public CraftBrewingStand(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftBrewingStand() {}
/*    */   
/* 18 */   private static final BlockStateBoolean[] HAS_BOTTLE = new BlockStateBoolean[] {
/* 19 */       getBoolean(BlockBrewingStand.class, "has_bottle_0"), getBoolean(BlockBrewingStand.class, "has_bottle_1"), getBoolean(BlockBrewingStand.class, "has_bottle_2")
/*    */     };
/*    */ 
/*    */   
/*    */   public boolean hasBottle(int bottle) {
/* 24 */     return ((Boolean)get((IBlockState)HAS_BOTTLE[bottle])).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setBottle(int bottle, boolean has) {
/* 29 */     set((IBlockState)HAS_BOTTLE[bottle], Boolean.valueOf(has));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<Integer> getBottles() {
/* 34 */     ImmutableSet.Builder<Integer> bottles = ImmutableSet.builder();
/*    */     
/* 36 */     for (int index = 0; index < getMaximumBottles(); index++) {
/* 37 */       if (hasBottle(index)) {
/* 38 */         bottles.add(Integer.valueOf(index));
/*    */       }
/*    */     } 
/*    */     
/* 42 */     return (Set<Integer>)bottles.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumBottles() {
/* 47 */     return HAS_BOTTLE.length;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftBrewingStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */