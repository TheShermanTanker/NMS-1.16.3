/*     */ package org.bukkit.material;
/*     */ 
/*     */ import java.util.EnumSet;
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.material.types.MushroomBlockTexture;
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
/*     */ @Deprecated
/*     */ public class Mushroom
/*     */   extends MaterialData
/*     */ {
/*     */   private static final byte NORTH_LIMIT = 4;
/*     */   private static final byte SOUTH_LIMIT = 6;
/*     */   private static final byte EAST_WEST_LIMIT = 3;
/*     */   private static final byte EAST_REMAINDER = 0;
/*     */   private static final byte WEST_REMAINDER = 1;
/*     */   private static final byte NORTH_SOUTH_MOD = 3;
/*     */   private static final byte EAST_WEST_MOD = 1;
/*     */   
/*     */   public Mushroom(Material shroom) {
/*  39 */     super(shroom);
/*  40 */     Validate.isTrue((shroom == Material.LEGACY_HUGE_MUSHROOM_1 || shroom == Material.LEGACY_HUGE_MUSHROOM_2), "Not a mushroom!");
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Mushroom(Material shroom, BlockFace capFace) {
/*  61 */     this(shroom, MushroomBlockTexture.getCapByFace(capFace));
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
/*     */   
/*     */   public Mushroom(Material shroom, MushroomBlockTexture texture) {
/*  74 */     this(shroom, texture.getData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Mushroom(Material shroom, byte data) {
/*  84 */     super(shroom, data);
/*  85 */     Validate.isTrue((shroom == Material.LEGACY_HUGE_MUSHROOM_1 || shroom == Material.LEGACY_HUGE_MUSHROOM_2), "Not a mushroom!");
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isStem() {
/*  92 */     return (getData() == MushroomBlockTexture.STEM_SIDES.getData() || getData() == MushroomBlockTexture.ALL_STEM.getData());
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
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setStem() {
/* 108 */     setData(MushroomBlockTexture.STEM_SIDES.getData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MushroomBlockTexture getBlockTexture() {
/* 117 */     return MushroomBlockTexture.getByData(getData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setBlockTexture(MushroomBlockTexture texture) {
/* 126 */     setData(texture.getData());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isFacePainted(BlockFace face) {
/* 136 */     byte data = getData();
/*     */     
/* 138 */     if (data == MushroomBlockTexture.ALL_PORES.getData() || data == MushroomBlockTexture.STEM_SIDES.getData() || data == MushroomBlockTexture.ALL_STEM
/* 139 */       .getData()) {
/* 140 */       return false;
/*     */     }
/*     */     
/* 143 */     switch (face) {
/*     */       case WEST:
/* 145 */         return (data < 4);
/*     */       case EAST:
/* 147 */         return (data > 6);
/*     */       case NORTH:
/* 149 */         return (data % 3 == 0);
/*     */       case SOUTH:
/* 151 */         return (data % 3 == 1);
/*     */       case UP:
/* 153 */         return true;
/*     */       case DOWN:
/*     */       case SELF:
/* 156 */         return (data == MushroomBlockTexture.ALL_CAP.getData());
/*     */     } 
/* 158 */     return false;
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setFacePainted(BlockFace face, boolean painted) {
/* 175 */     if (painted == isFacePainted(face)) {
/*     */       return;
/*     */     }
/*     */     
/* 179 */     byte data = getData();
/*     */     
/* 181 */     if (data == MushroomBlockTexture.ALL_PORES.getData() || isStem()) {
/* 182 */       data = MushroomBlockTexture.CAP_TOP.getData();
/*     */     }
/* 184 */     if (data == MushroomBlockTexture.ALL_CAP.getData() && !painted) {
/* 185 */       data = MushroomBlockTexture.CAP_TOP.getData();
/* 186 */       face = face.getOppositeFace();
/* 187 */       painted = true;
/*     */     } 
/*     */     
/* 190 */     switch (face) {
/*     */       case WEST:
/* 192 */         if (painted) {
/* 193 */           data = (byte)(data - 3); break;
/*     */         } 
/* 195 */         data = (byte)(data + 3);
/*     */         break;
/*     */ 
/*     */       
/*     */       case EAST:
/* 200 */         if (painted) {
/* 201 */           data = (byte)(data + 3); break;
/*     */         } 
/* 203 */         data = (byte)(data - 3);
/*     */         break;
/*     */ 
/*     */       
/*     */       case NORTH:
/* 208 */         if (painted) {
/* 209 */           data = (byte)(data + 1); break;
/*     */         } 
/* 211 */         data = (byte)(data - 1);
/*     */         break;
/*     */ 
/*     */       
/*     */       case SOUTH:
/* 216 */         if (painted) {
/* 217 */           data = (byte)(data - 1); break;
/*     */         } 
/* 219 */         data = (byte)(data + 1);
/*     */         break;
/*     */ 
/*     */       
/*     */       case UP:
/* 224 */         if (!painted) {
/* 225 */           data = MushroomBlockTexture.ALL_PORES.getData();
/*     */         }
/*     */         break;
/*     */       case DOWN:
/*     */       case SELF:
/* 230 */         if (painted) {
/* 231 */           data = MushroomBlockTexture.ALL_CAP.getData(); break;
/*     */         } 
/* 233 */         data = MushroomBlockTexture.ALL_PORES.getData();
/*     */         break;
/*     */       
/*     */       default:
/* 237 */         throw new IllegalArgumentException("Can't paint that face of a mushroom!");
/*     */     } 
/*     */     
/* 240 */     setData(data);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Set<BlockFace> getPaintedFaces() {
/* 248 */     EnumSet<BlockFace> faces = EnumSet.noneOf(BlockFace.class);
/*     */     
/* 250 */     if (isFacePainted(BlockFace.WEST)) {
/* 251 */       faces.add(BlockFace.WEST);
/*     */     }
/*     */     
/* 254 */     if (isFacePainted(BlockFace.NORTH)) {
/* 255 */       faces.add(BlockFace.NORTH);
/*     */     }
/*     */     
/* 258 */     if (isFacePainted(BlockFace.SOUTH)) {
/* 259 */       faces.add(BlockFace.SOUTH);
/*     */     }
/*     */     
/* 262 */     if (isFacePainted(BlockFace.EAST)) {
/* 263 */       faces.add(BlockFace.EAST);
/*     */     }
/*     */     
/* 266 */     if (isFacePainted(BlockFace.UP)) {
/* 267 */       faces.add(BlockFace.UP);
/*     */     }
/*     */     
/* 270 */     if (isFacePainted(BlockFace.DOWN)) {
/* 271 */       faces.add(BlockFace.DOWN);
/*     */     }
/*     */     
/* 274 */     return faces;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 279 */     return getItemType() + (isStem() ? " STEM " : " CAP ") + getPaintedFaces();
/*     */   }
/*     */ 
/*     */   
/*     */   public Mushroom clone() {
/* 284 */     return (Mushroom)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Mushroom.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */