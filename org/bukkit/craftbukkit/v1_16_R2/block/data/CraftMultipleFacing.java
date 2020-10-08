/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import org.bukkit.block.BlockFace;
/*    */ 
/*    */ public abstract class CraftMultipleFacing extends CraftBlockData implements MultipleFacing {
/*  7 */   private static final BlockStateBoolean[] FACES = new BlockStateBoolean[] {
/*  8 */       getBoolean("north", true), getBoolean("east", true), getBoolean("south", true), getBoolean("west", true), getBoolean("up", true), getBoolean("down", true)
/*    */     };
/*    */ 
/*    */   
/*    */   public boolean hasFace(BlockFace face) {
/* 13 */     BlockStateBoolean state = FACES[face.ordinal()];
/* 14 */     if (state == null) {
/* 15 */       throw new IllegalArgumentException("Non-allowed face " + face + ". Check MultipleFacing.getAllowedFaces.");
/*    */     }
/* 17 */     return ((Boolean)get((IBlockState<Boolean>)state)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFace(BlockFace face, boolean has) {
/* 22 */     BlockStateBoolean state = FACES[face.ordinal()];
/* 23 */     if (state == null) {
/* 24 */       throw new IllegalArgumentException("Non-allowed face " + face + ". Check MultipleFacing.getAllowedFaces.");
/*    */     }
/* 26 */     set((IBlockState<Comparable>)state, Boolean.valueOf(has));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 31 */     ImmutableSet.Builder<BlockFace> faces = ImmutableSet.builder();
/*    */     
/* 33 */     for (int i = 0; i < FACES.length; i++) {
/* 34 */       if (FACES[i] != null && ((Boolean)get((IBlockState<Boolean>)FACES[i])).booleanValue()) {
/* 35 */         faces.add(BlockFace.values()[i]);
/*    */       }
/*    */     } 
/*    */     
/* 39 */     return (Set<BlockFace>)faces.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getAllowedFaces() {
/* 44 */     ImmutableSet.Builder<BlockFace> faces = ImmutableSet.builder();
/*    */     
/* 46 */     for (int i = 0; i < FACES.length; i++) {
/* 47 */       if (FACES[i] != null) {
/* 48 */         faces.add(BlockFace.values()[i]);
/*    */       }
/*    */     } 
/*    */     
/* 52 */     return (Set<BlockFace>)faces.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftMultipleFacing.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */