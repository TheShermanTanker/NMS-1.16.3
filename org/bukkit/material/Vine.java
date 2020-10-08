/*     */ package org.bukkit.material;
/*     */ 
/*     */ import java.util.Arrays;
/*     */ import java.util.EnumSet;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class Vine
/*     */   extends MaterialData
/*     */ {
/*     */   private static final int VINE_NORTH = 4;
/*     */   private static final int VINE_EAST = 8;
/*     */   private static final int VINE_WEST = 2;
/*     */   private static final int VINE_SOUTH = 1;
/*  20 */   private static final EnumSet<BlockFace> possibleFaces = EnumSet.of(BlockFace.WEST, BlockFace.NORTH, BlockFace.SOUTH, BlockFace.EAST);
/*     */   
/*     */   public Vine() {
/*  23 */     super(Material.LEGACY_VINE);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Vine(Material type, byte data) {
/*  33 */     super(type, data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Vine(byte data) {
/*  42 */     super(Material.LEGACY_VINE, data);
/*     */   }
/*     */   
/*     */   public Vine(BlockFace... faces) {
/*  46 */     this(EnumSet.copyOf(Arrays.asList(faces)));
/*     */   }
/*     */   
/*     */   public Vine(EnumSet<BlockFace> faces) {
/*  50 */     this((byte)0);
/*  51 */     faces.retainAll(possibleFaces);
/*     */     
/*  53 */     byte data = 0;
/*     */     
/*  55 */     if (faces.contains(BlockFace.WEST)) {
/*  56 */       data = (byte)(data | 0x2);
/*     */     }
/*     */     
/*  59 */     if (faces.contains(BlockFace.NORTH)) {
/*  60 */       data = (byte)(data | 0x4);
/*     */     }
/*     */     
/*  63 */     if (faces.contains(BlockFace.SOUTH)) {
/*  64 */       data = (byte)(data | 0x1);
/*     */     }
/*     */     
/*  67 */     if (faces.contains(BlockFace.EAST)) {
/*  68 */       data = (byte)(data | 0x8);
/*     */     }
/*     */     
/*  71 */     setData(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOnFace(BlockFace face) {
/*  83 */     switch (face) {
/*     */       case WEST:
/*  85 */         return ((getData() & 0x2) == 2);
/*     */       case NORTH:
/*  87 */         return ((getData() & 0x4) == 4);
/*     */       case SOUTH:
/*  89 */         return ((getData() & 0x1) == 1);
/*     */       case EAST:
/*  91 */         return ((getData() & 0x8) == 8);
/*     */       case NORTH_EAST:
/*  93 */         return (isOnFace(BlockFace.EAST) && isOnFace(BlockFace.NORTH));
/*     */       case NORTH_WEST:
/*  95 */         return (isOnFace(BlockFace.WEST) && isOnFace(BlockFace.NORTH));
/*     */       case SOUTH_EAST:
/*  97 */         return (isOnFace(BlockFace.EAST) && isOnFace(BlockFace.SOUTH));
/*     */       case SOUTH_WEST:
/*  99 */         return (isOnFace(BlockFace.WEST) && isOnFace(BlockFace.SOUTH));
/*     */       case UP:
/* 101 */         return true;
/*     */     } 
/* 103 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void putOnFace(BlockFace face) {
/* 113 */     switch (face) {
/*     */       case WEST:
/* 115 */         setData((byte)(getData() | 0x2));
/*     */       
/*     */       case NORTH:
/* 118 */         setData((byte)(getData() | 0x4));
/*     */       
/*     */       case SOUTH:
/* 121 */         setData((byte)(getData() | 0x1));
/*     */       
/*     */       case EAST:
/* 124 */         setData((byte)(getData() | 0x8));
/*     */       
/*     */       case NORTH_WEST:
/* 127 */         putOnFace(BlockFace.WEST);
/* 128 */         putOnFace(BlockFace.NORTH);
/*     */       
/*     */       case SOUTH_WEST:
/* 131 */         putOnFace(BlockFace.WEST);
/* 132 */         putOnFace(BlockFace.SOUTH);
/*     */       
/*     */       case NORTH_EAST:
/* 135 */         putOnFace(BlockFace.EAST);
/* 136 */         putOnFace(BlockFace.NORTH);
/*     */       
/*     */       case SOUTH_EAST:
/* 139 */         putOnFace(BlockFace.EAST);
/* 140 */         putOnFace(BlockFace.SOUTH);
/*     */       
/*     */       case UP:
/*     */         return;
/*     */     } 
/* 145 */     throw new IllegalArgumentException("Vines can't go on face " + face.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void removeFromFace(BlockFace face) {
/* 155 */     switch (face) {
/*     */       case WEST:
/* 157 */         setData((byte)(getData() & 0xFFFFFFFD));
/*     */       
/*     */       case NORTH:
/* 160 */         setData((byte)(getData() & 0xFFFFFFFB));
/*     */       
/*     */       case SOUTH:
/* 163 */         setData((byte)(getData() & 0xFFFFFFFE));
/*     */       
/*     */       case EAST:
/* 166 */         setData((byte)(getData() & 0xFFFFFFF7));
/*     */       
/*     */       case NORTH_WEST:
/* 169 */         removeFromFace(BlockFace.WEST);
/* 170 */         removeFromFace(BlockFace.NORTH);
/*     */       
/*     */       case SOUTH_WEST:
/* 173 */         removeFromFace(BlockFace.WEST);
/* 174 */         removeFromFace(BlockFace.SOUTH);
/*     */       
/*     */       case NORTH_EAST:
/* 177 */         removeFromFace(BlockFace.EAST);
/* 178 */         removeFromFace(BlockFace.NORTH);
/*     */       
/*     */       case SOUTH_EAST:
/* 181 */         removeFromFace(BlockFace.EAST);
/* 182 */         removeFromFace(BlockFace.SOUTH);
/*     */       
/*     */       case UP:
/*     */         return;
/*     */     } 
/* 187 */     throw new IllegalArgumentException("Vines can't go on face " + face.toString());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 193 */     return "VINE";
/*     */   }
/*     */ 
/*     */   
/*     */   public Vine clone() {
/* 198 */     return (Vine)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Vine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */