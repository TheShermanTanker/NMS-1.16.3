/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import co.aikar.timings.Timing;
/*     */ import java.lang.ref.WeakReference;
/*     */ import java.util.logging.Level;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.util.Supplier;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.persistence.CraftPersistentDataContainer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.persistence.CraftPersistentDataTypeRegistry;
/*     */ import org.bukkit.inventory.InventoryHolder;
/*     */ 
/*     */ public abstract class TileEntity implements KeyedObject {
/*  17 */   public Timing tickTimer = MinecraftTimings.getTileEntityTimings(this);
/*     */   
/*  19 */   private static final CraftPersistentDataTypeRegistry DATA_TYPE_REGISTRY = new CraftPersistentDataTypeRegistry();
/*     */   
/*     */   public CraftPersistentDataContainer persistentDataContainer;
/*  22 */   private static final Logger LOGGER = LogManager.getLogger(); boolean isLoadingStructure = false; private final TileEntityTypes<?> tileType; @Nullable
/*     */   protected World world; protected BlockPosition position; protected boolean f; public TileEntityTypes getTileEntityType() {
/*  24 */     return this.tileType;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private IBlockData c; private boolean g; private String tileEntityKeyString;
/*     */   private MinecraftKey tileEntityKey;
/*     */   private WeakReference<Chunk> currentChunk;
/*     */   
/*     */   public MinecraftKey getMinecraftKey() {
/*     */     if (this.tileEntityKey == null) {
/*     */       this.tileEntityKey = TileEntityTypes.a(getTileEntityType());
/*     */       this.tileEntityKeyString = (this.tileEntityKey != null) ? this.tileEntityKey.toString() : null;
/*     */     } 
/*     */     return this.tileEntityKey;
/*     */   }
/*     */   
/*  40 */   public TileEntity(TileEntityTypes<?> tileentitytypes) { this.tileEntityKeyString = null;
/*  41 */     this.tileEntityKey = null;
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
/*  58 */     this.currentChunk = null; this.position = BlockPosition.ZERO;
/*     */     this.tileType = tileentitytypes;
/*  60 */     this.persistentDataContainer = new CraftPersistentDataContainer(DATA_TYPE_REGISTRY); } public Chunk getCurrentChunk() { Chunk chunk = (this.currentChunk != null) ? this.currentChunk.get() : null;
/*  61 */     return (chunk != null && chunk.loaded) ? chunk : null; }
/*     */   public String getMinecraftKeyString() { getMinecraftKey();
/*     */     return this.tileEntityKeyString; } public void setCurrentChunk(Chunk chunk) {
/*  64 */     this.currentChunk = (chunk != null) ? new WeakReference<>(chunk) : null;
/*     */   }
/*     */   
/*     */   static boolean IGNORE_TILE_UPDATES = false;
/*     */   
/*     */   @Nullable
/*     */   public World getWorld() {
/*  71 */     return this.world;
/*     */   }
/*     */   
/*     */   public void setLocation(World world, BlockPosition blockposition) {
/*  75 */     this.world = world;
/*  76 */     this.position = blockposition.immutableCopy();
/*     */   }
/*     */   
/*     */   public boolean hasWorld() {
/*  80 */     return (this.world != null);
/*     */   }
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/*  84 */     this.position = new BlockPosition(nbttagcompound.getInt("x"), nbttagcompound.getInt("y"), nbttagcompound.getInt("z"));
/*     */     
/*  86 */     this.persistentDataContainer.clear();
/*     */     
/*  88 */     NBTTagCompound persistentDataTag = nbttagcompound.getCompound("PublicBukkitValues");
/*  89 */     if (persistentDataTag != null) {
/*  90 */       this.persistentDataContainer.putAll(persistentDataTag);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*  96 */     return b(nbttagcompound);
/*     */   }
/*     */   
/*     */   private NBTTagCompound b(NBTTagCompound nbttagcompound) {
/* 100 */     MinecraftKey minecraftkey = TileEntityTypes.a(getTileType());
/*     */     
/* 102 */     if (minecraftkey == null) {
/* 103 */       throw new RuntimeException(getClass() + " is missing a mapping! This is a bug!");
/*     */     }
/* 105 */     nbttagcompound.setString("id", minecraftkey.toString());
/* 106 */     nbttagcompound.setInt("x", this.position.getX());
/* 107 */     nbttagcompound.setInt("y", this.position.getY());
/* 108 */     nbttagcompound.setInt("z", this.position.getZ());
/*     */     
/* 110 */     if (this.persistentDataContainer != null && !this.persistentDataContainer.isEmpty()) {
/* 111 */       nbttagcompound.set("PublicBukkitValues", this.persistentDataContainer.toTagCompound());
/*     */     }
/*     */     
/* 114 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static TileEntity create(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 120 */     String s = nbttagcompound.getString("id");
/*     */     
/* 122 */     return IRegistry.BLOCK_ENTITY_TYPE.getOptional(new MinecraftKey(s)).map(tileentitytypes -> {
/*     */           try {
/*     */             return tileentitytypes.a();
/* 125 */           } catch (Throwable throwable) {
/*     */             LOGGER.error("Failed to create block entity {}", s, throwable);
/*     */             return null;
/*     */           } 
/* 129 */         }).map(tileentity -> {
/*     */           try {
/*     */             tileentity.load(iblockdata, nbttagcompound);
/*     */             return tileentity;
/* 133 */           } catch (Throwable throwable) {
/*     */             LOGGER.error("Failed to load data for block entity {}", s, throwable);
/*     */             return null;
/*     */           } 
/* 137 */         }).orElseGet(() -> {
/*     */           LOGGER.warn("Skipping BlockEntity with id {}", s);
/*     */           return null;
/*     */         });
/*     */   }
/*     */   
/*     */   public void update() {
/* 144 */     if (this.world != null) {
/* 145 */       if (IGNORE_TILE_UPDATES)
/* 146 */         return;  this.c = this.world.getType(this.position);
/* 147 */       this.world.b(this.position, this);
/* 148 */       if (!this.c.isAir()) {
/* 149 */         this.world.updateAdjacentComparators(this.position, this.c.getBlock());
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition getPosition() {
/* 156 */     return this.position;
/*     */   }
/*     */   
/*     */   public IBlockData getBlock() {
/* 160 */     if (this.c == null) {
/* 161 */       this.c = this.world.getType(this.position);
/*     */     }
/*     */     
/* 164 */     return this.c;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/* 169 */     return null;
/*     */   }
/*     */   
/*     */   public NBTTagCompound b() {
/* 173 */     return b(new NBTTagCompound());
/*     */   }
/*     */   
/*     */   public boolean isRemoved() {
/* 177 */     return this.f;
/*     */   }
/*     */   
/*     */   public void al_() {
/* 181 */     this.f = true;
/*     */   }
/*     */   
/*     */   public void r() {
/* 185 */     this.f = false;
/*     */   }
/*     */   
/*     */   public boolean setProperty(int i, int j) {
/* 189 */     return false;
/*     */   }
/*     */   
/*     */   public void invalidateBlockCache() {
/* 193 */     this.c = null;
/*     */   }
/*     */   
/*     */   public void a(CrashReportSystemDetails crashreportsystemdetails) {
/* 197 */     crashreportsystemdetails.a("Name", () -> IRegistry.BLOCK_ENTITY_TYPE.getKey(getTileType()) + " // " + getClass().getCanonicalName());
/*     */ 
/*     */     
/* 200 */     if (this.world != null) {
/*     */       
/* 202 */       IBlockData block = getBlock();
/* 203 */       if (block != null) {
/* 204 */         CrashReportSystemDetails.a(crashreportsystemdetails, this.position, block);
/*     */       }
/*     */       
/* 207 */       CrashReportSystemDetails.a(crashreportsystemdetails, this.position, this.world.getType(this.position));
/*     */     } 
/*     */   }
/*     */   
/*     */   public void setPosition(BlockPosition blockposition) {
/* 212 */     this.position = blockposition.immutableCopy();
/*     */   }
/*     */   
/*     */   public boolean isFilteredNBT() {
/* 216 */     return false;
/*     */   }
/*     */   
/*     */   public void a(EnumBlockRotation enumblockrotation) {}
/*     */   
/*     */   public void a(EnumBlockMirror enumblockmirror) {}
/*     */   
/*     */   public TileEntityTypes<?> getTileType() {
/* 224 */     return this.tileType;
/*     */   }
/*     */   
/*     */   public void w() {
/* 228 */     if (!this.g) {
/* 229 */       this.g = true;
/* 230 */       LOGGER.warn("Block entity invalid: {} @ {}", new Supplier[] { () -> IRegistry.BLOCK_ENTITY_TYPE.getKey(getTileType()), this::getPosition });
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public InventoryHolder getOwner() {
/* 239 */     return getOwner(true);
/*     */   }
/*     */   
/*     */   public InventoryHolder getOwner(boolean useSnapshot) {
/* 243 */     if (this.world == null) return null;
/*     */     
/* 245 */     Block block = this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ());
/* 246 */     if (block == null) {
/* 247 */       Bukkit.getLogger().log(Level.WARNING, "No block for owner at %s %d %d %d", new Object[] { this.world.getWorld(), Integer.valueOf(this.position.getX()), Integer.valueOf(this.position.getY()), Integer.valueOf(this.position.getZ()) });
/* 248 */       return null;
/*     */     } 
/*     */     
/* 251 */     BlockState state = block.getState(useSnapshot);
/* 252 */     if (state instanceof InventoryHolder) return (InventoryHolder)state; 
/* 253 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntity.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */