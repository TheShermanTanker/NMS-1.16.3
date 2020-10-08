/*     */ package org.bukkit.material;
/*     */ 
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.TreeSpecies;
/*     */ import org.bukkit.block.BlockFace;
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
/*     */ @Deprecated
/*     */ public class Door
/*     */   extends MaterialData
/*     */   implements Directional, Openable
/*     */ {
/*     */   @Deprecated
/*     */   public Door() {
/*  36 */     super(Material.LEGACY_WOODEN_DOOR);
/*     */   }
/*     */   
/*     */   public Door(Material type) {
/*  40 */     super(type);
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
/*     */ 
/*     */   
/*     */   public Door(Material type, BlockFace face) {
/*  63 */     this(type, face, false);
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
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Door(Material type, BlockFace face, boolean isOpen) {
/*  88 */     super(type);
/*  89 */     setTopHalf(false);
/*  90 */     setFacingDirection(face);
/*  91 */     setOpen(isOpen);
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
/*     */   public Door(Material type, boolean isHingeRight) {
/* 109 */     super(type);
/* 110 */     setTopHalf(true);
/* 111 */     setHinge(isHingeRight);
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
/*     */   public Door(TreeSpecies species, BlockFace face) {
/* 129 */     this(getWoodDoorOfSpecies(species), face, false);
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
/*     */   public Door(TreeSpecies species, BlockFace face, boolean isOpen) {
/* 148 */     this(getWoodDoorOfSpecies(species), face, isOpen);
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
/*     */   public Door(TreeSpecies species, boolean isHingeRight) {
/* 160 */     this(getWoodDoorOfSpecies(species), isHingeRight);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public Door(Material type, byte data) {
/* 170 */     super(type, data);
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
/*     */   public static Material getWoodDoorOfSpecies(TreeSpecies species) {
/* 187 */     switch (species)
/*     */     
/*     */     { default:
/* 190 */         return Material.LEGACY_WOODEN_DOOR;
/*     */       case NORTH:
/* 192 */         return Material.LEGACY_BIRCH_DOOR;
/*     */       case EAST:
/* 194 */         return Material.LEGACY_SPRUCE_DOOR;
/*     */       case SOUTH:
/* 196 */         return Material.LEGACY_JUNGLE_DOOR;
/*     */       case null:
/* 198 */         return Material.LEGACY_ACACIA_DOOR;
/*     */       case null:
/* 200 */         break; }  return Material.LEGACY_DARK_OAK_DOOR;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isOpen() {
/* 209 */     return ((getData() & 0x4) == 4);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOpen(boolean isOpen) {
/* 217 */     setData((byte)(isOpen ? (getData() | 0x4) : (getData() & 0xFFFFFFFB)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTopHalf() {
/* 224 */     return ((getData() & 0x8) == 8);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setTopHalf(boolean isTopHalf) {
/* 233 */     setData((byte)(isTopHalf ? (getData() | 0x8) : (getData() & 0xFFFFFFF7)));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public BlockFace getHingeCorner() {
/* 242 */     return BlockFace.SELF;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 247 */     return (isTopHalf() ? "TOP" : "BOTTOM") + " half of " + super.toString();
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
/*     */   public void setFacingDirection(BlockFace face) {
/* 259 */     byte data = (byte)(getData() & 0xC);
/* 260 */     switch (face) {
/*     */       case WEST:
/* 262 */         data = (byte)(data | 0x0);
/*     */         break;
/*     */       case NORTH:
/* 265 */         data = (byte)(data | 0x1);
/*     */         break;
/*     */       case EAST:
/* 268 */         data = (byte)(data | 0x2);
/*     */         break;
/*     */       case SOUTH:
/* 271 */         data = (byte)(data | 0x3);
/*     */         break;
/*     */     } 
/* 274 */     setData(data);
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
/*     */   public BlockFace getFacing() {
/* 286 */     byte data = (byte)(getData() & 0x3);
/* 287 */     switch (data) {
/*     */       case 0:
/* 289 */         return BlockFace.WEST;
/*     */       case 1:
/* 291 */         return BlockFace.NORTH;
/*     */       case 2:
/* 293 */         return BlockFace.EAST;
/*     */       case 3:
/* 295 */         return BlockFace.SOUTH;
/*     */     } 
/* 297 */     throw new IllegalStateException("Unknown door facing (data: " + data + ")");
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
/*     */   public boolean getHinge() {
/* 309 */     return ((getData() & 0x1) == 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setHinge(boolean isHingeRight) {
/* 320 */     setData((byte)(isHingeRight ? (getData() | 0x1) : (getData() & 0xFFFFFFFE)));
/*     */   }
/*     */ 
/*     */   
/*     */   public Door clone() {
/* 325 */     return (Door)super.clone();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\material\Door.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */