/*     */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import net.minecraft.server.v1_16_R2.BlockPosition;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import net.minecraft.server.v1_16_R2.TileEntity;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.TileState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftWorld;
/*     */ import org.bukkit.persistence.PersistentDataContainer;
/*     */ 
/*     */ public class CraftBlockEntityState<T extends TileEntity> extends CraftBlockState implements TileState {
/*     */   private final Class<T> tileEntityClass;
/*     */   private final T tileEntity;
/*     */   private final T snapshot;
/*     */   public final boolean snapshotDisabled;
/*     */   
/*     */   public CraftBlockEntityState(Block block, Class<T> tileEntityClass) {
/*  20 */     super(block);
/*     */ 
/*     */     
/*     */     try {
/*  24 */       this.tileEntityClass = tileEntityClass;
/*     */ 
/*     */       
/*  27 */       CraftWorld world = (CraftWorld)getWorld();
/*  28 */       this.tileEntity = tileEntityClass.cast(world.getHandle().getTileEntity(getPosition()));
/*  29 */       if (this.tileEntity == null) Preconditions.checkState(false, "Tile is null, asynchronous access? " + block);
/*     */ 
/*     */       
/*  32 */       this.snapshotDisabled = DISABLE_SNAPSHOT;
/*  33 */       if (DISABLE_SNAPSHOT) {
/*  34 */         this.snapshot = this.tileEntity;
/*     */       } else {
/*  36 */         this.snapshot = createSnapshot(this.tileEntity);
/*     */       } 
/*     */       
/*  39 */       if (this.snapshot != null) {
/*  40 */         load(this.snapshot);
/*     */       
/*     */       }
/*     */     }
/*  44 */     catch (Throwable thr) {
/*  45 */       if (thr instanceof ThreadDeath) {
/*  46 */         throw (ThreadDeath)thr;
/*     */       }
/*  48 */       throw new RuntimeException("Failed to read BlockState at: world: " + block.getWorld().getName() + " location: (" + block.getX() + ", " + block.getY() + ", " + block.getZ() + ")", thr);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean DISABLE_SNAPSHOT = false;
/*     */ 
/*     */   
/*     */   public CraftBlockEntityState(Material material, T tileEntity) {
/*  57 */     super(material);
/*     */     
/*  59 */     this.tileEntityClass = (Class)tileEntity.getClass();
/*  60 */     this.tileEntity = tileEntity;
/*     */     
/*  62 */     this.snapshotDisabled = DISABLE_SNAPSHOT;
/*  63 */     if (DISABLE_SNAPSHOT) {
/*  64 */       this.snapshot = this.tileEntity;
/*     */     } else {
/*  66 */       this.snapshot = createSnapshot(this.tileEntity);
/*     */     } 
/*     */     
/*  69 */     if (this.snapshot != null) {
/*  70 */       load(this.snapshot);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private T createSnapshot(T tileEntity) {
/*  76 */     if (tileEntity == null) {
/*  77 */       return null;
/*     */     }
/*     */     
/*  80 */     NBTTagCompound nbtTagCompound = tileEntity.save(new NBTTagCompound());
/*  81 */     return (T)TileEntity.create(getHandle(), nbtTagCompound);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void copyData(T from, T to) {
/*  88 */     BlockPosition pos = to.getPosition();
/*  89 */     NBTTagCompound nbtTagCompound = from.save(new NBTTagCompound());
/*  90 */     to.load(getHandle(), nbtTagCompound);
/*     */ 
/*     */     
/*  93 */     to.setPosition(pos);
/*     */   }
/*     */ 
/*     */   
/*     */   public T getTileEntity() {
/*  98 */     return this.tileEntity;
/*     */   }
/*     */ 
/*     */   
/*     */   protected T getSnapshot() {
/* 103 */     return this.snapshot;
/*     */   }
/*     */ 
/*     */   
/*     */   protected TileEntity getTileEntityFromWorld() {
/* 108 */     requirePlaced();
/*     */     
/* 110 */     return ((CraftWorld)getWorld()).getHandle().getTileEntity(getPosition());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound getSnapshotNBT() {
/* 116 */     applyTo(this.snapshot);
/*     */     
/* 118 */     return this.snapshot.save(new NBTTagCompound());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void load(T tileEntity) {
/* 123 */     if (tileEntity != null && tileEntity != this.snapshot) {
/* 124 */       copyData(tileEntity, this.snapshot);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void applyTo(T tileEntity) {
/* 130 */     if (tileEntity != null && tileEntity != this.snapshot) {
/* 131 */       copyData(this.snapshot, tileEntity);
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean isApplicable(TileEntity tileEntity) {
/* 136 */     return this.tileEntityClass.isInstance(tileEntity);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean update(boolean force, boolean applyPhysics) {
/* 141 */     boolean result = super.update(force, applyPhysics);
/*     */     
/* 143 */     if (result && isPlaced()) {
/* 144 */       TileEntity tile = getTileEntityFromWorld();
/*     */       
/* 146 */       if (isApplicable(tile)) {
/* 147 */         applyTo(this.tileEntityClass.cast(tile));
/* 148 */         tile.update();
/*     */       } 
/*     */     } 
/*     */     
/* 152 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public PersistentDataContainer getPersistentDataContainer() {
/* 157 */     return (PersistentDataContainer)((TileEntity)getSnapshot()).persistentDataContainer;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftBlockEntityState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */