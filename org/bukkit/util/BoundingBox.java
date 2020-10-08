/*      */ package org.bukkit.util;
/*      */ 
/*      */ import java.util.LinkedHashMap;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import org.apache.commons.lang.Validate;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.block.Block;
/*      */ import org.bukkit.block.BlockFace;
/*      */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*      */ import org.bukkit.configuration.serialization.SerializableAs;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ @SerializableAs("BoundingBox")
/*      */ public class BoundingBox
/*      */   implements Cloneable, ConfigurationSerializable
/*      */ {
/*      */   private double minX;
/*      */   private double minY;
/*      */   private double minZ;
/*      */   private double maxX;
/*      */   private double maxY;
/*      */   private double maxZ;
/*      */   
/*      */   @NotNull
/*      */   public static BoundingBox of(@NotNull Vector corner1, @NotNull Vector corner2) {
/*   42 */     Validate.notNull(corner1, "Corner1 is null!");
/*   43 */     Validate.notNull(corner2, "Corner2 is null!");
/*   44 */     return new BoundingBox(corner1.getX(), corner1.getY(), corner1.getZ(), corner2.getX(), corner2.getY(), corner2.getZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static BoundingBox of(@NotNull Location corner1, @NotNull Location corner2) {
/*   57 */     Validate.notNull(corner1, "Corner1 is null!");
/*   58 */     Validate.notNull(corner2, "Corner2 is null!");
/*   59 */     Validate.isTrue(Objects.equals(corner1.getWorld(), corner2.getWorld()), "Locations from different worlds!");
/*   60 */     return new BoundingBox(corner1.getX(), corner1.getY(), corner1.getZ(), corner2.getX(), corner2.getY(), corner2.getZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static BoundingBox of(@NotNull Block corner1, @NotNull Block corner2) {
/*   75 */     Validate.notNull(corner1, "Corner1 is null!");
/*   76 */     Validate.notNull(corner2, "Corner2 is null!");
/*   77 */     Validate.isTrue(Objects.equals(corner1.getWorld(), corner2.getWorld()), "Blocks from different worlds!");
/*      */     
/*   79 */     int x1 = corner1.getX();
/*   80 */     int y1 = corner1.getY();
/*   81 */     int z1 = corner1.getZ();
/*   82 */     int x2 = corner2.getX();
/*   83 */     int y2 = corner2.getY();
/*   84 */     int z2 = corner2.getZ();
/*      */     
/*   86 */     int minX = Math.min(x1, x2);
/*   87 */     int minY = Math.min(y1, y2);
/*   88 */     int minZ = Math.min(z1, z2);
/*   89 */     int maxX = Math.max(x1, x2) + 1;
/*   90 */     int maxY = Math.max(y1, y2) + 1;
/*   91 */     int maxZ = Math.max(z1, z2) + 1;
/*      */     
/*   93 */     return new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static BoundingBox of(@NotNull Block block) {
/*  104 */     Validate.notNull(block, "Block is null!");
/*  105 */     return new BoundingBox(block.getX(), block.getY(), block.getZ(), (block.getX() + 1), (block.getY() + 1), (block.getZ() + 1));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static BoundingBox of(@NotNull Vector center, double x, double y, double z) {
/*  119 */     Validate.notNull(center, "Center is null!");
/*  120 */     return new BoundingBox(center.getX() - x, center.getY() - y, center.getZ() - z, center.getX() + x, center.getY() + y, center.getZ() + z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public static BoundingBox of(@NotNull Location center, double x, double y, double z) {
/*  134 */     Validate.notNull(center, "Center is null!");
/*  135 */     return new BoundingBox(center.getX() - x, center.getY() - y, center.getZ() - z, center.getX() + x, center.getY() + y, center.getZ() + z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BoundingBox() {
/*  150 */     resize(0.0D, 0.0D, 0.0D, 0.0D, 0.0D, 0.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public BoundingBox(double x1, double y1, double z1, double x2, double y2, double z2) {
/*  164 */     resize(x1, y1, z1, x2, y2, z2);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox resize(double x1, double y1, double z1, double x2, double y2, double z2) {
/*  180 */     NumberConversions.checkFinite(x1, "x1 not finite");
/*  181 */     NumberConversions.checkFinite(y1, "y1 not finite");
/*  182 */     NumberConversions.checkFinite(z1, "z1 not finite");
/*  183 */     NumberConversions.checkFinite(x2, "x2 not finite");
/*  184 */     NumberConversions.checkFinite(y2, "y2 not finite");
/*  185 */     NumberConversions.checkFinite(z2, "z2 not finite");
/*      */     
/*  187 */     this.minX = Math.min(x1, x2);
/*  188 */     this.minY = Math.min(y1, y2);
/*  189 */     this.minZ = Math.min(z1, z2);
/*  190 */     this.maxX = Math.max(x1, x2);
/*  191 */     this.maxY = Math.max(y1, y2);
/*  192 */     this.maxZ = Math.max(z1, z2);
/*  193 */     return this;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMinX() {
/*  202 */     return this.minX;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMinY() {
/*  211 */     return this.minY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMinZ() {
/*  220 */     return this.minZ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Vector getMin() {
/*  230 */     return new Vector(this.minX, this.minY, this.minZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMaxX() {
/*  239 */     return this.maxX;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMaxY() {
/*  248 */     return this.maxY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getMaxZ() {
/*  257 */     return this.maxZ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Vector getMax() {
/*  267 */     return new Vector(this.maxX, this.maxY, this.maxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getWidthX() {
/*  276 */     return this.maxX - this.minX;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getWidthZ() {
/*  285 */     return this.maxZ - this.minZ;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getHeight() {
/*  294 */     return this.maxY - this.minY;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getVolume() {
/*  303 */     return getHeight() * getWidthX() * getWidthZ();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getCenterX() {
/*  312 */     return this.minX + getWidthX() * 0.5D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getCenterY() {
/*  321 */     return this.minY + getHeight() * 0.5D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public double getCenterZ() {
/*  330 */     return this.minZ + getWidthZ() * 0.5D;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Vector getCenter() {
/*  340 */     return new Vector(getCenterX(), getCenterY(), getCenterZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox copy(@NotNull BoundingBox other) {
/*  351 */     Validate.notNull(other, "Other bounding box is null!");
/*  352 */     return resize(other.getMinX(), other.getMinY(), other.getMinZ(), other.getMaxX(), other.getMaxY(), other.getMaxZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox expand(double negativeX, double negativeY, double negativeZ, double positiveX, double positiveY, double positiveZ) {
/*  373 */     if (negativeX == 0.0D && negativeY == 0.0D && negativeZ == 0.0D && positiveX == 0.0D && positiveY == 0.0D && positiveZ == 0.0D) {
/*  374 */       return this;
/*      */     }
/*  376 */     double newMinX = this.minX - negativeX;
/*  377 */     double newMinY = this.minY - negativeY;
/*  378 */     double newMinZ = this.minZ - negativeZ;
/*  379 */     double newMaxX = this.maxX + positiveX;
/*  380 */     double newMaxY = this.maxY + positiveY;
/*  381 */     double newMaxZ = this.maxZ + positiveZ;
/*      */ 
/*      */     
/*  384 */     if (newMinX > newMaxX) {
/*  385 */       double centerX = getCenterX();
/*  386 */       if (newMaxX >= centerX) {
/*  387 */         newMinX = newMaxX;
/*  388 */       } else if (newMinX <= centerX) {
/*  389 */         newMaxX = newMinX;
/*      */       } else {
/*  391 */         newMinX = centerX;
/*  392 */         newMaxX = centerX;
/*      */       } 
/*      */     } 
/*  395 */     if (newMinY > newMaxY) {
/*  396 */       double centerY = getCenterY();
/*  397 */       if (newMaxY >= centerY) {
/*  398 */         newMinY = newMaxY;
/*  399 */       } else if (newMinY <= centerY) {
/*  400 */         newMaxY = newMinY;
/*      */       } else {
/*  402 */         newMinY = centerY;
/*  403 */         newMaxY = centerY;
/*      */       } 
/*      */     } 
/*  406 */     if (newMinZ > newMaxZ) {
/*  407 */       double centerZ = getCenterZ();
/*  408 */       if (newMaxZ >= centerZ) {
/*  409 */         newMinZ = newMaxZ;
/*  410 */       } else if (newMinZ <= centerZ) {
/*  411 */         newMaxZ = newMinZ;
/*      */       } else {
/*  413 */         newMinZ = centerZ;
/*  414 */         newMaxZ = centerZ;
/*      */       } 
/*      */     } 
/*  417 */     return resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox expand(double x, double y, double z) {
/*  437 */     return expand(x, y, z, x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox expand(@NotNull Vector expansion) {
/*  452 */     Validate.notNull(expansion, "Expansion is null!");
/*  453 */     double x = expansion.getX();
/*  454 */     double y = expansion.getY();
/*  455 */     double z = expansion.getZ();
/*  456 */     return expand(x, y, z, x, y, z);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox expand(double expansion) {
/*  470 */     return expand(expansion, expansion, expansion, expansion, expansion, expansion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox expand(double dirX, double dirY, double dirZ, double expansion) {
/*  488 */     if (expansion == 0.0D) return this; 
/*  489 */     if (dirX == 0.0D && dirY == 0.0D && dirZ == 0.0D) return this;
/*      */     
/*  491 */     double negativeX = (dirX < 0.0D) ? (-dirX * expansion) : 0.0D;
/*  492 */     double negativeY = (dirY < 0.0D) ? (-dirY * expansion) : 0.0D;
/*  493 */     double negativeZ = (dirZ < 0.0D) ? (-dirZ * expansion) : 0.0D;
/*  494 */     double positiveX = (dirX > 0.0D) ? (dirX * expansion) : 0.0D;
/*  495 */     double positiveY = (dirY > 0.0D) ? (dirY * expansion) : 0.0D;
/*  496 */     double positiveZ = (dirZ > 0.0D) ? (dirZ * expansion) : 0.0D;
/*  497 */     return expand(negativeX, negativeY, negativeZ, positiveX, positiveY, positiveZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox expand(@NotNull Vector direction, double expansion) {
/*  513 */     Validate.notNull(direction, "Direction is null!");
/*  514 */     return expand(direction.getX(), direction.getY(), direction.getZ(), expansion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox expand(@NotNull BlockFace blockFace, double expansion) {
/*  530 */     Validate.notNull(blockFace, "Block face is null!");
/*  531 */     if (blockFace == BlockFace.SELF) return this;
/*      */     
/*  533 */     return expand(blockFace.getDirection(), expansion);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox expandDirectional(double dirX, double dirY, double dirZ) {
/*  551 */     return expand(dirX, dirY, dirZ, 1.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox expandDirectional(@NotNull Vector direction) {
/*  566 */     Validate.notNull(direction, "Expansion is null!");
/*  567 */     return expand(direction.getX(), direction.getY(), direction.getZ(), 1.0D);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox union(double posX, double posY, double posZ) {
/*  581 */     double newMinX = Math.min(this.minX, posX);
/*  582 */     double newMinY = Math.min(this.minY, posY);
/*  583 */     double newMinZ = Math.min(this.minZ, posZ);
/*  584 */     double newMaxX = Math.max(this.maxX, posX);
/*  585 */     double newMaxY = Math.max(this.maxY, posY);
/*  586 */     double newMaxZ = Math.max(this.maxZ, posZ);
/*  587 */     if (newMinX == this.minX && newMinY == this.minY && newMinZ == this.minZ && newMaxX == this.maxX && newMaxY == this.maxY && newMaxZ == this.maxZ) {
/*  588 */       return this;
/*      */     }
/*  590 */     return resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox union(@NotNull Vector position) {
/*  602 */     Validate.notNull(position, "Position is null!");
/*  603 */     return union(position.getX(), position.getY(), position.getZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox union(@NotNull Location position) {
/*  615 */     Validate.notNull(position, "Position is null!");
/*  616 */     return union(position.getX(), position.getY(), position.getZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox union(@NotNull BoundingBox other) {
/*  628 */     Validate.notNull(other, "Other bounding box is null!");
/*  629 */     if (contains(other)) return this; 
/*  630 */     double newMinX = Math.min(this.minX, other.minX);
/*  631 */     double newMinY = Math.min(this.minY, other.minY);
/*  632 */     double newMinZ = Math.min(this.minZ, other.minZ);
/*  633 */     double newMaxX = Math.max(this.maxX, other.maxX);
/*  634 */     double newMaxY = Math.max(this.maxY, other.maxY);
/*  635 */     double newMaxZ = Math.max(this.maxZ, other.maxZ);
/*  636 */     return resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox intersection(@NotNull BoundingBox other) {
/*  649 */     Validate.notNull(other, "Other bounding box is null!");
/*  650 */     Validate.isTrue(overlaps(other), "The bounding boxes do not overlap!");
/*  651 */     double newMinX = Math.max(this.minX, other.minX);
/*  652 */     double newMinY = Math.max(this.minY, other.minY);
/*  653 */     double newMinZ = Math.max(this.minZ, other.minZ);
/*  654 */     double newMaxX = Math.min(this.maxX, other.maxX);
/*  655 */     double newMaxY = Math.min(this.maxY, other.maxY);
/*  656 */     double newMaxZ = Math.min(this.maxZ, other.maxZ);
/*  657 */     return resize(newMinX, newMinY, newMinZ, newMaxX, newMaxY, newMaxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox shift(double shiftX, double shiftY, double shiftZ) {
/*  670 */     if (shiftX == 0.0D && shiftY == 0.0D && shiftZ == 0.0D) return this; 
/*  671 */     return resize(this.minX + shiftX, this.minY + shiftY, this.minZ + shiftZ, this.maxX + shiftX, this.maxY + shiftY, this.maxZ + shiftZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox shift(@NotNull Vector shift) {
/*  683 */     Validate.notNull(shift, "Shift is null!");
/*  684 */     return shift(shift.getX(), shift.getY(), shift.getZ());
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox shift(@NotNull Location shift) {
/*  695 */     Validate.notNull(shift, "Shift is null!");
/*  696 */     return shift(shift.getX(), shift.getY(), shift.getZ());
/*      */   }
/*      */   
/*      */   private boolean overlaps(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
/*  700 */     return (this.minX < maxX && this.maxX > minX && this.minY < maxY && this.maxY > minY && this.minZ < maxZ && this.maxZ > minZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean overlaps(@NotNull BoundingBox other) {
/*  715 */     Validate.notNull(other, "Other bounding box is null!");
/*  716 */     return overlaps(other.minX, other.minY, other.minZ, other.maxX, other.maxY, other.maxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean overlaps(@NotNull Vector min, @NotNull Vector max) {
/*  731 */     Validate.notNull(min, "Min is null!");
/*  732 */     Validate.notNull(max, "Max is null!");
/*  733 */     double x1 = min.getX();
/*  734 */     double y1 = min.getY();
/*  735 */     double z1 = min.getZ();
/*  736 */     double x2 = max.getX();
/*  737 */     double y2 = max.getY();
/*  738 */     double z2 = max.getZ();
/*  739 */     return overlaps(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), 
/*  740 */         Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean contains(double x, double y, double z) {
/*  758 */     return (x >= this.minX && x < this.maxX && y >= this.minY && y < this.maxY && z >= this.minZ && z < this.maxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean contains(@NotNull Vector position) {
/*  776 */     Validate.notNull(position, "Position is null!");
/*  777 */     return contains(position.getX(), position.getY(), position.getZ());
/*      */   }
/*      */   
/*      */   private boolean contains(double minX, double minY, double minZ, double maxX, double maxY, double maxZ) {
/*  781 */     return (this.minX <= minX && this.maxX >= maxX && this.minY <= minY && this.maxY >= maxY && this.minZ <= minZ && this.maxZ >= maxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean contains(@NotNull BoundingBox other) {
/*  794 */     Validate.notNull(other, "Other bounding box is null!");
/*  795 */     return contains(other.minX, other.minY, other.minZ, other.maxX, other.maxY, other.maxZ);
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public boolean contains(@NotNull Vector min, @NotNull Vector max) {
/*  808 */     Validate.notNull(min, "Min is null!");
/*  809 */     Validate.notNull(max, "Max is null!");
/*  810 */     double x1 = min.getX();
/*  811 */     double y1 = min.getY();
/*  812 */     double z1 = min.getZ();
/*  813 */     double x2 = max.getX();
/*  814 */     double y2 = max.getY();
/*  815 */     double z2 = max.getZ();
/*  816 */     return contains(Math.min(x1, x2), Math.min(y1, y2), Math.min(z1, z2), 
/*  817 */         Math.max(x1, x2), Math.max(y1, y2), Math.max(z1, z2));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public RayTraceResult rayTrace(@NotNull Vector start, @NotNull Vector direction, double maxDistance) {
/*      */     double tMin, tMax;
/*      */     BlockFace blockFace1, blockFace2;
/*      */     double tyMin, tyMax;
/*      */     BlockFace hitBlockFaceYMin, hitBlockFaceYMax;
/*      */     double tzMin, tzMax;
/*      */     BlockFace hitBlockFaceZMin, hitBlockFaceZMax;
/*      */     double t;
/*      */     BlockFace hitBlockFace;
/*  834 */     Validate.notNull(start, "Start is null!");
/*  835 */     start.checkFinite();
/*  836 */     Validate.notNull(direction, "Direction is null!");
/*  837 */     direction.checkFinite();
/*  838 */     Validate.isTrue((direction.lengthSquared() > 0.0D), "Direction's magnitude is 0!");
/*  839 */     if (maxDistance < 0.0D) return null;
/*      */ 
/*      */     
/*  842 */     double startX = start.getX();
/*  843 */     double startY = start.getY();
/*  844 */     double startZ = start.getZ();
/*      */ 
/*      */     
/*  847 */     Vector dir = direction.clone().normalizeZeros().normalize();
/*  848 */     double dirX = dir.getX();
/*  849 */     double dirY = dir.getY();
/*  850 */     double dirZ = dir.getZ();
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  855 */     double divX = 1.0D / dirX;
/*  856 */     double divY = 1.0D / dirY;
/*  857 */     double divZ = 1.0D / dirZ;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  865 */     if (dirX >= 0.0D) {
/*  866 */       tMin = (this.minX - startX) * divX;
/*  867 */       tMax = (this.maxX - startX) * divX;
/*  868 */       blockFace1 = BlockFace.WEST;
/*  869 */       blockFace2 = BlockFace.EAST;
/*      */     } else {
/*  871 */       tMin = (this.maxX - startX) * divX;
/*  872 */       tMax = (this.minX - startX) * divX;
/*  873 */       blockFace1 = BlockFace.EAST;
/*  874 */       blockFace2 = BlockFace.WEST;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  882 */     if (dirY >= 0.0D) {
/*  883 */       tyMin = (this.minY - startY) * divY;
/*  884 */       tyMax = (this.maxY - startY) * divY;
/*  885 */       hitBlockFaceYMin = BlockFace.DOWN;
/*  886 */       hitBlockFaceYMax = BlockFace.UP;
/*      */     } else {
/*  888 */       tyMin = (this.maxY - startY) * divY;
/*  889 */       tyMax = (this.minY - startY) * divY;
/*  890 */       hitBlockFaceYMin = BlockFace.UP;
/*  891 */       hitBlockFaceYMax = BlockFace.DOWN;
/*      */     } 
/*  893 */     if (tMin > tyMax || tMax < tyMin) {
/*  894 */       return null;
/*      */     }
/*  896 */     if (tyMin > tMin) {
/*  897 */       tMin = tyMin;
/*  898 */       blockFace1 = hitBlockFaceYMin;
/*      */     } 
/*  900 */     if (tyMax < tMax) {
/*  901 */       tMax = tyMax;
/*  902 */       blockFace2 = hitBlockFaceYMax;
/*      */     } 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  910 */     if (dirZ >= 0.0D) {
/*  911 */       tzMin = (this.minZ - startZ) * divZ;
/*  912 */       tzMax = (this.maxZ - startZ) * divZ;
/*  913 */       hitBlockFaceZMin = BlockFace.NORTH;
/*  914 */       hitBlockFaceZMax = BlockFace.SOUTH;
/*      */     } else {
/*  916 */       tzMin = (this.maxZ - startZ) * divZ;
/*  917 */       tzMax = (this.minZ - startZ) * divZ;
/*  918 */       hitBlockFaceZMin = BlockFace.SOUTH;
/*  919 */       hitBlockFaceZMax = BlockFace.NORTH;
/*      */     } 
/*  921 */     if (tMin > tzMax || tMax < tzMin) {
/*  922 */       return null;
/*      */     }
/*  924 */     if (tzMin > tMin) {
/*  925 */       tMin = tzMin;
/*  926 */       blockFace1 = hitBlockFaceZMin;
/*      */     } 
/*  928 */     if (tzMax < tMax) {
/*  929 */       tMax = tzMax;
/*  930 */       blockFace2 = hitBlockFaceZMax;
/*      */     } 
/*      */ 
/*      */     
/*  934 */     if (tMax < 0.0D) return null;
/*      */     
/*  936 */     if (tMin > maxDistance) {
/*  937 */       return null;
/*      */     }
/*      */ 
/*      */ 
/*      */ 
/*      */     
/*  943 */     if (tMin < 0.0D) {
/*  944 */       t = tMax;
/*  945 */       hitBlockFace = blockFace2;
/*      */     } else {
/*  947 */       t = tMin;
/*  948 */       hitBlockFace = blockFace1;
/*      */     } 
/*      */     
/*  951 */     Vector hitPosition = dir.multiply(t).add(start);
/*  952 */     return new RayTraceResult(hitPosition, hitBlockFace);
/*      */   }
/*      */ 
/*      */   
/*      */   public int hashCode() {
/*  957 */     int prime = 31;
/*  958 */     int result = 1;
/*      */     
/*  960 */     long temp = Double.doubleToLongBits(this.maxX);
/*  961 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/*  962 */     temp = Double.doubleToLongBits(this.maxY);
/*  963 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/*  964 */     temp = Double.doubleToLongBits(this.maxZ);
/*  965 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/*  966 */     temp = Double.doubleToLongBits(this.minX);
/*  967 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/*  968 */     temp = Double.doubleToLongBits(this.minY);
/*  969 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/*  970 */     temp = Double.doubleToLongBits(this.minZ);
/*  971 */     result = 31 * result + (int)(temp ^ temp >>> 32L);
/*  972 */     return result;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean equals(Object obj) {
/*  977 */     if (this == obj) return true; 
/*  978 */     if (!(obj instanceof BoundingBox)) return false; 
/*  979 */     BoundingBox other = (BoundingBox)obj;
/*  980 */     if (Double.doubleToLongBits(this.maxX) != Double.doubleToLongBits(other.maxX)) return false; 
/*  981 */     if (Double.doubleToLongBits(this.maxY) != Double.doubleToLongBits(other.maxY)) return false; 
/*  982 */     if (Double.doubleToLongBits(this.maxZ) != Double.doubleToLongBits(other.maxZ)) return false; 
/*  983 */     if (Double.doubleToLongBits(this.minX) != Double.doubleToLongBits(other.minX)) return false; 
/*  984 */     if (Double.doubleToLongBits(this.minY) != Double.doubleToLongBits(other.minY)) return false; 
/*  985 */     if (Double.doubleToLongBits(this.minZ) != Double.doubleToLongBits(other.minZ)) return false; 
/*  986 */     return true;
/*      */   }
/*      */ 
/*      */   
/*      */   public String toString() {
/*  991 */     StringBuilder builder = new StringBuilder();
/*  992 */     builder.append("BoundingBox [minX=");
/*  993 */     builder.append(this.minX);
/*  994 */     builder.append(", minY=");
/*  995 */     builder.append(this.minY);
/*  996 */     builder.append(", minZ=");
/*  997 */     builder.append(this.minZ);
/*  998 */     builder.append(", maxX=");
/*  999 */     builder.append(this.maxX);
/* 1000 */     builder.append(", maxY=");
/* 1001 */     builder.append(this.maxY);
/* 1002 */     builder.append(", maxZ=");
/* 1003 */     builder.append(this.maxZ);
/* 1004 */     builder.append("]");
/* 1005 */     return builder.toString();
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public BoundingBox clone() {
/*      */     try {
/* 1017 */       return (BoundingBox)super.clone();
/* 1018 */     } catch (CloneNotSupportedException e) {
/* 1019 */       throw new Error(e);
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Map<String, Object> serialize() {
/* 1026 */     Map<String, Object> result = new LinkedHashMap<>();
/* 1027 */     result.put("minX", Double.valueOf(this.minX));
/* 1028 */     result.put("minY", Double.valueOf(this.minY));
/* 1029 */     result.put("minZ", Double.valueOf(this.minZ));
/* 1030 */     result.put("maxX", Double.valueOf(this.maxX));
/* 1031 */     result.put("maxY", Double.valueOf(this.maxY));
/* 1032 */     result.put("maxZ", Double.valueOf(this.maxZ));
/* 1033 */     return result;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   public static BoundingBox deserialize(@NotNull Map<String, Object> args) {
/* 1038 */     double minX = 0.0D;
/* 1039 */     double minY = 0.0D;
/* 1040 */     double minZ = 0.0D;
/* 1041 */     double maxX = 0.0D;
/* 1042 */     double maxY = 0.0D;
/* 1043 */     double maxZ = 0.0D;
/*      */     
/* 1045 */     if (args.containsKey("minX")) {
/* 1046 */       minX = ((Number)args.get("minX")).doubleValue();
/*      */     }
/* 1048 */     if (args.containsKey("minY")) {
/* 1049 */       minY = ((Number)args.get("minY")).doubleValue();
/*      */     }
/* 1051 */     if (args.containsKey("minZ")) {
/* 1052 */       minZ = ((Number)args.get("minZ")).doubleValue();
/*      */     }
/* 1054 */     if (args.containsKey("maxX")) {
/* 1055 */       maxX = ((Number)args.get("maxX")).doubleValue();
/*      */     }
/* 1057 */     if (args.containsKey("maxY")) {
/* 1058 */       maxY = ((Number)args.get("maxY")).doubleValue();
/*      */     }
/* 1060 */     if (args.containsKey("maxZ")) {
/* 1061 */       maxZ = ((Number)args.get("maxZ")).doubleValue();
/*      */     }
/*      */     
/* 1064 */     return new BoundingBox(minX, minY, minZ, maxX, maxY, maxZ);
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\BoundingBox.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */