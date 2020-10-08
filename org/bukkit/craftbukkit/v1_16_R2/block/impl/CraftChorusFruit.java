/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockChorusFruit;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.MultipleFacing;
/*    */ 
/*    */ public final class CraftChorusFruit extends CraftBlockData implements MultipleFacing {
/*    */   public CraftChorusFruit(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftChorusFruit() {}
/*    */   
/* 18 */   private static final BlockStateBoolean[] FACES = new BlockStateBoolean[] {
/* 19 */       getBoolean(BlockChorusFruit.class, "north", true), getBoolean(BlockChorusFruit.class, "east", true), getBoolean(BlockChorusFruit.class, "south", true), getBoolean(BlockChorusFruit.class, "west", true), getBoolean(BlockChorusFruit.class, "up", true), getBoolean(BlockChorusFruit.class, "down", true)
/*    */     };
/*    */ 
/*    */   
/*    */   public boolean hasFace(BlockFace face) {
/* 24 */     BlockStateBoolean state = FACES[face.ordinal()];
/* 25 */     if (state == null) {
/* 26 */       throw new IllegalArgumentException("Non-allowed face " + face + ". Check MultipleFacing.getAllowedFaces.");
/*    */     }
/* 28 */     return ((Boolean)get((IBlockState)state)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFace(BlockFace face, boolean has) {
/* 33 */     BlockStateBoolean state = FACES[face.ordinal()];
/* 34 */     if (state == null) {
/* 35 */       throw new IllegalArgumentException("Non-allowed face " + face + ". Check MultipleFacing.getAllowedFaces.");
/*    */     }
/* 37 */     set((IBlockState)state, Boolean.valueOf(has));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 42 */     ImmutableSet.Builder<BlockFace> faces = ImmutableSet.builder();
/*    */     
/* 44 */     for (int i = 0; i < FACES.length; i++) {
/* 45 */       if (FACES[i] != null && ((Boolean)get((IBlockState)FACES[i])).booleanValue()) {
/* 46 */         faces.add(BlockFace.values()[i]);
/*    */       }
/*    */     } 
/*    */     
/* 50 */     return (Set<BlockFace>)faces.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getAllowedFaces() {
/* 55 */     ImmutableSet.Builder<BlockFace> faces = ImmutableSet.builder();
/*    */     
/* 57 */     for (int i = 0; i < FACES.length; i++) {
/* 58 */       if (FACES[i] != null) {
/* 59 */         faces.add(BlockFace.values()[i]);
/*    */       }
/*    */     } 
/*    */     
/* 63 */     return (Set<BlockFace>)faces.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftChorusFruit.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */