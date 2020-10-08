/*     */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.List;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.World;
/*     */ import org.bukkit.Chunk;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.data.CraftBlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.material.MaterialData;
/*     */ import org.bukkit.metadata.MetadataValue;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class CraftBlockState implements BlockState {
/*     */   protected final CraftWorld world;
/*     */   private final BlockPosition position;
/*     */   protected IBlockData data;
/*     */   protected int flag;
/*     */   
/*     */   public CraftBlockState(Block block) {
/*  30 */     this.world = (CraftWorld)block.getWorld();
/*  31 */     this.position = ((CraftBlock)block).getPosition();
/*  32 */     this.data = ((CraftBlock)block).getNMS();
/*  33 */     this.flag = 3;
/*     */   }
/*     */   
/*     */   public CraftBlockState(Block block, int flag) {
/*  37 */     this(block);
/*  38 */     this.flag = flag;
/*     */   }
/*     */   
/*     */   public CraftBlockState(Material material) {
/*  42 */     this.world = null;
/*  43 */     this.data = CraftMagicNumbers.getBlock(material).getBlockData();
/*  44 */     this.position = BlockPosition.ZERO;
/*     */   }
/*     */   
/*     */   public static CraftBlockState getBlockState(GeneratorAccess world, BlockPosition pos) {
/*  48 */     return new CraftBlockState(CraftBlock.at(world, pos));
/*     */   }
/*     */   
/*     */   public static CraftBlockState getBlockState(World world, BlockPosition pos, int flag) {
/*  52 */     return new CraftBlockState(world.getWorld().getBlockAt(pos.getX(), pos.getY(), pos.getZ()), flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public World getWorld() {
/*  57 */     requirePlaced();
/*  58 */     return (World)this.world;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getX() {
/*  63 */     return this.position.getX();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getY() {
/*  68 */     return this.position.getY();
/*     */   }
/*     */ 
/*     */   
/*     */   public int getZ() {
/*  73 */     return this.position.getZ();
/*     */   }
/*     */ 
/*     */   
/*     */   public Chunk getChunk() {
/*  78 */     requirePlaced();
/*  79 */     return this.world.getChunkAt(getX() >> 4, getZ() >> 4);
/*     */   }
/*     */   
/*     */   public void setData(IBlockData data) {
/*  83 */     this.data = data;
/*     */   }
/*     */   
/*     */   public BlockPosition getPosition() {
/*  87 */     return this.position;
/*     */   }
/*     */   
/*     */   public IBlockData getHandle() {
/*  91 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockData getBlockData() {
/*  96 */     return (BlockData)CraftBlockData.fromData(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setBlockData(BlockData data) {
/* 101 */     Preconditions.checkArgument((data != null), "BlockData cannot be null");
/* 102 */     this.data = ((CraftBlockData)data).getState();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setData(MaterialData data) {
/* 107 */     Material mat = CraftMagicNumbers.getMaterial(this.data).getItemType();
/*     */     
/* 109 */     if (mat == null || mat.getData() == null) {
/* 110 */       this.data = CraftMagicNumbers.getBlock(data);
/*     */     }
/* 112 */     else if (data.getClass() == mat.getData() || data.getClass() == MaterialData.class) {
/* 113 */       this.data = CraftMagicNumbers.getBlock(data);
/*     */     } else {
/* 115 */       throw new IllegalArgumentException("Provided data is not of type " + mat
/* 116 */           .getData().getName() + ", found " + data.getClass().getName());
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public MaterialData getData() {
/* 123 */     return CraftMagicNumbers.getMaterial(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setType(Material type) {
/* 128 */     Preconditions.checkArgument((type != null), "Material cannot be null");
/* 129 */     Preconditions.checkArgument(type.isBlock(), "Material must be a block!");
/*     */     
/* 131 */     if (getType() != type) {
/* 132 */       this.data = CraftMagicNumbers.getBlock(type).getBlockData();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getType() {
/* 138 */     return this.data.getBukkitMaterial();
/*     */   }
/*     */   
/*     */   public void setFlag(int flag) {
/* 142 */     this.flag = flag;
/*     */   }
/*     */   
/*     */   public int getFlag() {
/* 146 */     return this.flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getLightLevel() {
/* 151 */     return getBlock().getLightLevel();
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftBlock getBlock() {
/* 156 */     requirePlaced();
/* 157 */     return CraftBlock.at((GeneratorAccess)this.world.getHandle(), this.position);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean update() {
/* 162 */     return update(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean update(boolean force) {
/* 167 */     return update(force, true);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean update(boolean force, boolean applyPhysics) {
/* 172 */     if (!isPlaced()) {
/* 173 */       return true;
/*     */     }
/* 175 */     CraftBlock block = getBlock();
/*     */     
/* 177 */     if (block.getType() != getType() && 
/* 178 */       !force) {
/* 179 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 183 */     IBlockData newBlock = this.data;
/* 184 */     block.setTypeAndData(newBlock, applyPhysics);
/* 185 */     this.world.getHandle().notify(this.position, block
/*     */         
/* 187 */         .getNMS(), newBlock, 3);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 197 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getRawData() {
/* 202 */     return CraftMagicNumbers.toLegacyData(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation() {
/* 207 */     return new Location((World)this.world, getX(), getY(), getZ());
/*     */   }
/*     */ 
/*     */   
/*     */   public Location getLocation(Location loc) {
/* 212 */     if (loc != null) {
/* 213 */       loc.setWorld((World)this.world);
/* 214 */       loc.setX(getX());
/* 215 */       loc.setY(getY());
/* 216 */       loc.setZ(getZ());
/* 217 */       loc.setYaw(0.0F);
/* 218 */       loc.setPitch(0.0F);
/*     */     } 
/*     */     
/* 221 */     return loc;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRawData(byte data) {
/* 226 */     this.data = CraftMagicNumbers.getBlock(getType(), data);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 231 */     if (obj == null) {
/* 232 */       return false;
/*     */     }
/* 234 */     if (getClass() != obj.getClass()) {
/* 235 */       return false;
/*     */     }
/* 237 */     CraftBlockState other = (CraftBlockState)obj;
/* 238 */     if (this.world != other.world && (this.world == null || !this.world.equals(other.world))) {
/* 239 */       return false;
/*     */     }
/* 241 */     if (this.position != other.position && (this.position == null || !this.position.equals(other.position))) {
/* 242 */       return false;
/*     */     }
/* 244 */     if (this.data != other.data && (this.data == null || !this.data.equals(other.data))) {
/* 245 */       return false;
/*     */     }
/* 247 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 252 */     int hash = 7;
/* 253 */     hash = 73 * hash + ((this.world != null) ? this.world.hashCode() : 0);
/* 254 */     hash = 73 * hash + ((this.position != null) ? this.position.hashCode() : 0);
/* 255 */     hash = 73 * hash + ((this.data != null) ? this.data.hashCode() : 0);
/* 256 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setMetadata(String metadataKey, MetadataValue newMetadataValue) {
/* 261 */     requirePlaced();
/* 262 */     this.world.getBlockMetadata().setMetadata(getBlock(), metadataKey, newMetadataValue);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<MetadataValue> getMetadata(String metadataKey) {
/* 267 */     requirePlaced();
/* 268 */     return this.world.getBlockMetadata().getMetadata(getBlock(), metadataKey);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasMetadata(String metadataKey) {
/* 273 */     requirePlaced();
/* 274 */     return this.world.getBlockMetadata().hasMetadata(getBlock(), metadataKey);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeMetadata(String metadataKey, Plugin owningPlugin) {
/* 279 */     requirePlaced();
/* 280 */     this.world.getBlockMetadata().removeMetadata(getBlock(), metadataKey, owningPlugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPlaced() {
/* 285 */     return (this.world != null);
/*     */   }
/*     */   
/*     */   protected void requirePlaced() {
/* 289 */     if (!isPlaced())
/* 290 */       throw new IllegalStateException("The blockState must be placed to call this method"); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBlockState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */