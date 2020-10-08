/*     */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.BlockPropertyStructureMode;
/*     */ import net.minecraft.server.v1_16_R2.EnumBlockMirror;
/*     */ import net.minecraft.server.v1_16_R2.EnumBlockRotation;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import net.minecraft.server.v1_16_R2.TileEntityStructure;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.Structure;
/*     */ import org.bukkit.block.structure.Mirror;
/*     */ import org.bukkit.block.structure.StructureRotation;
/*     */ import org.bukkit.block.structure.UsageMode;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.Validate;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*     */ import org.bukkit.entity.LivingEntity;
/*     */ import org.bukkit.util.BlockVector;
/*     */ 
/*     */ public class CraftStructureBlock extends CraftBlockEntityState<TileEntityStructure> implements Structure {
/*     */   private static final int MAX_SIZE = 48;
/*     */   
/*     */   public CraftStructureBlock(Block block) {
/*  25 */     super(block, TileEntityStructure.class);
/*     */   }
/*     */   
/*     */   public CraftStructureBlock(Material material, TileEntityStructure structure) {
/*  29 */     super(material, structure);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getStructureName() {
/*  34 */     return getSnapshot().getStructureName();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStructureName(String name) {
/*  39 */     Preconditions.checkArgument((name != null), "Structure Name cannot be null");
/*  40 */     getSnapshot().setStructureName(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAuthor() {
/*  45 */     return (getSnapshot()).author;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAuthor(String author) {
/*  50 */     Preconditions.checkArgument((author != null && !author.isEmpty()), "Author name cannot be null nor empty");
/*  51 */     (getSnapshot()).author = author;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setAuthor(LivingEntity entity) {
/*  56 */     Preconditions.checkArgument((entity != null), "Structure Block author entity cannot be null");
/*  57 */     getSnapshot().setAuthor(((CraftLivingEntity)entity).getHandle());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockVector getRelativePosition() {
/*  62 */     return new BlockVector((getSnapshot()).relativePosition.getX(), (getSnapshot()).relativePosition.getY(), (getSnapshot()).relativePosition.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRelativePosition(BlockVector vector) {
/*  67 */     Validate.isTrue(isBetween(vector.getBlockX(), -48, 48), "Structure Size (X) must be between -48 and 48", new Object[0]);
/*  68 */     Validate.isTrue(isBetween(vector.getBlockY(), -48, 48), "Structure Size (Y) must be between -48 and 48", new Object[0]);
/*  69 */     Validate.isTrue(isBetween(vector.getBlockZ(), -48, 48), "Structure Size (Z) must be between -48 and 48", new Object[0]);
/*  70 */     (getSnapshot()).relativePosition = new BlockPosition(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockVector getStructureSize() {
/*  75 */     return new BlockVector((getSnapshot()).size.getX(), (getSnapshot()).size.getY(), (getSnapshot()).size.getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setStructureSize(BlockVector vector) {
/*  80 */     Validate.isTrue(isBetween(vector.getBlockX(), 0, 48), "Structure Size (X) must be between 0 and 48", new Object[0]);
/*  81 */     Validate.isTrue(isBetween(vector.getBlockY(), 0, 48), "Structure Size (Y) must be between 0 and 48", new Object[0]);
/*  82 */     Validate.isTrue(isBetween(vector.getBlockZ(), 0, 48), "Structure Size (Z) must be between 0 and 48", new Object[0]);
/*  83 */     (getSnapshot()).size = new BlockPosition(vector.getBlockX(), vector.getBlockY(), vector.getBlockZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMirror(Mirror mirror) {
/*  88 */     (getSnapshot()).mirror = EnumBlockMirror.valueOf(mirror.name());
/*     */   }
/*     */ 
/*     */   
/*     */   public Mirror getMirror() {
/*  93 */     return Mirror.valueOf((getSnapshot()).mirror.name());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRotation(StructureRotation rotation) {
/*  98 */     (getSnapshot()).rotation = EnumBlockRotation.valueOf(rotation.name());
/*     */   }
/*     */ 
/*     */   
/*     */   public StructureRotation getRotation() {
/* 103 */     return StructureRotation.valueOf((getSnapshot()).rotation.name());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setUsageMode(UsageMode mode) {
/* 108 */     (getSnapshot()).usageMode = BlockPropertyStructureMode.valueOf(mode.name());
/*     */   }
/*     */ 
/*     */   
/*     */   public UsageMode getUsageMode() {
/* 113 */     return UsageMode.valueOf(getSnapshot().getUsageMode().name());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIgnoreEntities(boolean flag) {
/* 118 */     (getSnapshot()).ignoreEntities = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIgnoreEntities() {
/* 123 */     return (getSnapshot()).ignoreEntities;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setShowAir(boolean showAir) {
/* 128 */     (getSnapshot()).showAir = showAir;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isShowAir() {
/* 133 */     return (getSnapshot()).showAir;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBoundingBoxVisible(boolean showBoundingBox) {
/* 138 */     (getSnapshot()).showBoundingBox = showBoundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBoundingBoxVisible() {
/* 143 */     return (getSnapshot()).showBoundingBox;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setIntegrity(float integrity) {
/* 148 */     Validate.isTrue(isBetween(integrity, 0.0F, 1.0F), "Integrity must be between 0.0f and 1.0f", new Object[0]);
/* 149 */     (getSnapshot()).integrity = integrity;
/*     */   }
/*     */ 
/*     */   
/*     */   public float getIntegrity() {
/* 154 */     return (getSnapshot()).integrity;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setSeed(long seed) {
/* 159 */     (getSnapshot()).seed = seed;
/*     */   }
/*     */ 
/*     */   
/*     */   public long getSeed() {
/* 164 */     return (getSnapshot()).seed;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMetadata(String metadata) {
/* 169 */     Validate.notNull(metadata, "Structure metadata cannot be null", new Object[0]);
/* 170 */     if (getUsageMode() == UsageMode.DATA) {
/* 171 */       (getSnapshot()).metadata = metadata;
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public String getMetadata() {
/* 177 */     return (getSnapshot()).metadata;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTo(TileEntityStructure tileEntity) {
/* 182 */     super.applyTo(tileEntity);
/*     */ 
/*     */     
/* 185 */     tileEntity.setUsageMode(tileEntity.getUsageMode());
/*     */   }
/*     */   
/*     */   private static boolean isBetween(int num, int min, int max) {
/* 189 */     return (num >= min && num <= max);
/*     */   }
/*     */   
/*     */   private static boolean isBetween(float num, float min, float max) {
/* 193 */     return (num >= min && num <= max);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftStructureBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */