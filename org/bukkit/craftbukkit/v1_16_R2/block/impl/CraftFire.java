/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import java.util.Set;
/*    */ import net.minecraft.server.v1_16_R2.BlockFire;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.block.BlockFace;
/*    */ import org.bukkit.block.data.type.Fire;
/*    */ 
/*    */ public final class CraftFire extends CraftBlockData implements Fire, Ageable, MultipleFacing {
/*    */   public CraftFire(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftFire() {}
/*    */   
/* 18 */   private static final BlockStateInteger AGE = getInteger(BlockFire.class, "age");
/*    */ 
/*    */   
/*    */   public int getAge() {
/* 22 */     return ((Integer)get((IBlockState)AGE)).intValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setAge(int age) {
/* 27 */     set((IBlockState)AGE, Integer.valueOf(age));
/*    */   }
/*    */ 
/*    */   
/*    */   public int getMaximumAge() {
/* 32 */     return getMax(AGE);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 37 */   private static final BlockStateBoolean[] FACES = new BlockStateBoolean[] {
/* 38 */       getBoolean(BlockFire.class, "north", true), getBoolean(BlockFire.class, "east", true), getBoolean(BlockFire.class, "south", true), getBoolean(BlockFire.class, "west", true), getBoolean(BlockFire.class, "up", true), getBoolean(BlockFire.class, "down", true)
/*    */     };
/*    */ 
/*    */   
/*    */   public boolean hasFace(BlockFace face) {
/* 43 */     BlockStateBoolean state = FACES[face.ordinal()];
/* 44 */     if (state == null) {
/* 45 */       throw new IllegalArgumentException("Non-allowed face " + face + ". Check MultipleFacing.getAllowedFaces.");
/*    */     }
/* 47 */     return ((Boolean)get((IBlockState)state)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setFace(BlockFace face, boolean has) {
/* 52 */     BlockStateBoolean state = FACES[face.ordinal()];
/* 53 */     if (state == null) {
/* 54 */       throw new IllegalArgumentException("Non-allowed face " + face + ". Check MultipleFacing.getAllowedFaces.");
/*    */     }
/* 56 */     set((IBlockState)state, Boolean.valueOf(has));
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getFaces() {
/* 61 */     ImmutableSet.Builder<BlockFace> faces = ImmutableSet.builder();
/*    */     
/* 63 */     for (int i = 0; i < FACES.length; i++) {
/* 64 */       if (FACES[i] != null && ((Boolean)get((IBlockState)FACES[i])).booleanValue()) {
/* 65 */         faces.add(BlockFace.values()[i]);
/*    */       }
/*    */     } 
/*    */     
/* 69 */     return (Set<BlockFace>)faces.build();
/*    */   }
/*    */ 
/*    */   
/*    */   public Set<BlockFace> getAllowedFaces() {
/* 74 */     ImmutableSet.Builder<BlockFace> faces = ImmutableSet.builder();
/*    */     
/* 76 */     for (int i = 0; i < FACES.length; i++) {
/* 77 */       if (FACES[i] != null) {
/* 78 */         faces.add(BlockFace.values()[i]);
/*    */       }
/*    */     } 
/*    */     
/* 82 */     return (Set<BlockFace>)faces.build();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftFire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */