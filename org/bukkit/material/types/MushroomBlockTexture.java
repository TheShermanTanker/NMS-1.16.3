/*     */ package org.bukkit.material.types;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum MushroomBlockTexture
/*     */ {
/*  16 */   ALL_PORES(0, null),
/*     */ 
/*     */ 
/*     */   
/*  20 */   CAP_NORTH_WEST(1, BlockFace.NORTH_WEST),
/*     */ 
/*     */ 
/*     */   
/*  24 */   CAP_NORTH(2, BlockFace.NORTH),
/*     */ 
/*     */ 
/*     */   
/*  28 */   CAP_NORTH_EAST(3, BlockFace.NORTH_EAST),
/*     */ 
/*     */ 
/*     */   
/*  32 */   CAP_WEST(4, BlockFace.WEST),
/*     */ 
/*     */ 
/*     */   
/*  36 */   CAP_TOP(5, BlockFace.UP),
/*     */ 
/*     */ 
/*     */   
/*  40 */   CAP_EAST(6, BlockFace.EAST),
/*     */ 
/*     */ 
/*     */   
/*  44 */   CAP_SOUTH_WEST(7, BlockFace.SOUTH_WEST),
/*     */ 
/*     */ 
/*     */   
/*  48 */   CAP_SOUTH(8, BlockFace.SOUTH),
/*     */ 
/*     */ 
/*     */   
/*  52 */   CAP_SOUTH_EAST(9, BlockFace.SOUTH_EAST),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  57 */   STEM_SIDES(10, null),
/*     */ 
/*     */ 
/*     */   
/*  61 */   ALL_CAP(14, BlockFace.SELF),
/*     */ 
/*     */ 
/*     */   
/*  65 */   ALL_STEM(15, null); static {
/*  66 */     BY_DATA = Maps.newHashMap();
/*  67 */     BY_BLOCKFACE = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 127 */     for (MushroomBlockTexture type : values()) {
/* 128 */       BY_DATA.put(type.data, type);
/* 129 */       BY_BLOCKFACE.put(type.capFace, type);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static final Map<Byte, MushroomBlockTexture> BY_DATA;
/*     */   private static final Map<BlockFace, MushroomBlockTexture> BY_BLOCKFACE;
/*     */   private final Byte data;
/*     */   private final BlockFace capFace;
/*     */   
/*     */   MushroomBlockTexture(int data, BlockFace capFace) {
/*     */     this.data = Byte.valueOf((byte)data);
/*     */     this.capFace = capFace;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public byte getData() {
/*     */     return this.data.byteValue();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public BlockFace getCapFace() {
/*     */     return this.capFace;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public static MushroomBlockTexture getByData(byte data) {
/*     */     return BY_DATA.get(Byte.valueOf(data));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static MushroomBlockTexture getCapByFace(@Nullable BlockFace face) {
/*     */     return BY_BLOCKFACE.get(face);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\types\MushroomBlockTexture.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */