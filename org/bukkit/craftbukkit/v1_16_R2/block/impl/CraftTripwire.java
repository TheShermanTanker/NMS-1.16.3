/*     */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*     */ import net.minecraft.server.v1_16_R2.BlockTripwire;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IBlockState;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.data.type.Tripwire;
/*     */ 
/*     */ public final class CraftTripwire extends CraftBlockData implements Tripwire, Attachable, MultipleFacing, Powerable {
/*     */   public CraftTripwire(IBlockData state) {
/*  13 */     super(state);
/*     */   }
/*     */   
/*     */   public CraftTripwire() {}
/*     */   
/*  18 */   private static final BlockStateBoolean DISARMED = getBoolean(BlockTripwire.class, "disarmed");
/*     */ 
/*     */   
/*     */   public boolean isDisarmed() {
/*  22 */     return ((Boolean)get((IBlockState)DISARMED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setDisarmed(boolean disarmed) {
/*  27 */     set((IBlockState)DISARMED, Boolean.valueOf(disarmed));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  32 */   private static final BlockStateBoolean ATTACHED = getBoolean(BlockTripwire.class, "attached");
/*     */ 
/*     */   
/*     */   public boolean isAttached() {
/*  36 */     return ((Boolean)get((IBlockState)ATTACHED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAttached(boolean attached) {
/*  41 */     set((IBlockState)ATTACHED, Boolean.valueOf(attached));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  46 */   private static final BlockStateBoolean[] FACES = new BlockStateBoolean[] {
/*  47 */       getBoolean(BlockTripwire.class, "north", true), getBoolean(BlockTripwire.class, "east", true), getBoolean(BlockTripwire.class, "south", true), getBoolean(BlockTripwire.class, "west", true), getBoolean(BlockTripwire.class, "up", true), getBoolean(BlockTripwire.class, "down", true)
/*     */     };
/*     */ 
/*     */   
/*     */   public boolean hasFace(BlockFace face) {
/*  52 */     BlockStateBoolean state = FACES[face.ordinal()];
/*  53 */     if (state == null) {
/*  54 */       throw new IllegalArgumentException("Non-allowed face " + face + ". Check MultipleFacing.getAllowedFaces.");
/*     */     }
/*  56 */     return ((Boolean)get((IBlockState)state)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setFace(BlockFace face, boolean has) {
/*  61 */     BlockStateBoolean state = FACES[face.ordinal()];
/*  62 */     if (state == null) {
/*  63 */       throw new IllegalArgumentException("Non-allowed face " + face + ". Check MultipleFacing.getAllowedFaces.");
/*     */     }
/*  65 */     set((IBlockState)state, Boolean.valueOf(has));
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockFace> getFaces() {
/*  70 */     ImmutableSet.Builder<BlockFace> faces = ImmutableSet.builder();
/*     */     
/*  72 */     for (int i = 0; i < FACES.length; i++) {
/*  73 */       if (FACES[i] != null && ((Boolean)get((IBlockState)FACES[i])).booleanValue()) {
/*  74 */         faces.add(BlockFace.values()[i]);
/*     */       }
/*     */     } 
/*     */     
/*  78 */     return (Set<BlockFace>)faces.build();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<BlockFace> getAllowedFaces() {
/*  83 */     ImmutableSet.Builder<BlockFace> faces = ImmutableSet.builder();
/*     */     
/*  85 */     for (int i = 0; i < FACES.length; i++) {
/*  86 */       if (FACES[i] != null) {
/*  87 */         faces.add(BlockFace.values()[i]);
/*     */       }
/*     */     } 
/*     */     
/*  91 */     return (Set<BlockFace>)faces.build();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  96 */   private static final BlockStateBoolean POWERED = getBoolean(BlockTripwire.class, "powered");
/*     */ 
/*     */   
/*     */   public boolean isPowered() {
/* 100 */     return ((Boolean)get((IBlockState)POWERED)).booleanValue();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPowered(boolean powered) {
/* 105 */     set((IBlockState)POWERED, Boolean.valueOf(powered));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftTripwire.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */