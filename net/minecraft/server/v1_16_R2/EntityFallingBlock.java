/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityFallingBlock extends Entity {
/*     */   private IBlockData block;
/*     */   public int ticksLived;
/*     */   public boolean dropItem;
/*     */   private boolean g;
/*     */   public boolean hurtEntities;
/*     */   private int fallHurtMax;
/*     */   private float fallHurtAmount;
/*     */   public NBTTagCompound tileEntityData;
/*  19 */   protected static final DataWatcherObject<BlockPosition> e = DataWatcher.a((Class)EntityFallingBlock.class, DataWatcherRegistry.l);
/*     */   
/*     */   public EntityFallingBlock(EntityTypes<? extends EntityFallingBlock> entitytypes, World world) {
/*  22 */     super(entitytypes, world);
/*  23 */     this.block = Blocks.SAND.getBlockData();
/*  24 */     this.dropItem = true;
/*  25 */     this.fallHurtMax = 40;
/*  26 */     this.fallHurtAmount = 2.0F;
/*     */   }
/*     */   
/*     */   public EntityFallingBlock(World world, double d0, double d1, double d2, IBlockData iblockdata) {
/*  30 */     this(EntityTypes.FALLING_BLOCK, world);
/*  31 */     this.block = iblockdata;
/*  32 */     this.i = true;
/*  33 */     setPosition(d0, d1 + ((1.0F - getHeight()) / 2.0F), d2);
/*  34 */     setMot(Vec3D.ORIGIN);
/*  35 */     this.lastX = d0;
/*  36 */     this.lastY = d1;
/*  37 */     this.lastZ = d2;
/*  38 */     a(getChunkCoordinates());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bK() {
/*  43 */     return false;
/*     */   }
/*     */   
/*     */   public void a(BlockPosition blockposition) {
/*  47 */     this.datawatcher.set(e, blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  52 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  57 */     this.datawatcher.register(e, BlockPosition.ZERO);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/*  62 */     return !this.dead;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  68 */     if (this.dead) {
/*     */       return;
/*     */     }
/*     */     
/*  72 */     if (this.block.isAir()) {
/*  73 */       die();
/*     */     } else {
/*  75 */       Block block = this.block.getBlock();
/*     */ 
/*     */       
/*  78 */       if (this.ticksLived++ == 0) {
/*  79 */         BlockPosition blockposition = getChunkCoordinates();
/*  80 */         if (this.world.getType(blockposition).a(block) && !CraftEventFactory.callEntityChangeBlockEvent(this, blockposition, Blocks.AIR.getBlockData()).isCancelled()) {
/*  81 */           this.world.a(blockposition, false);
/*  82 */         } else if (!this.world.isClientSide) {
/*  83 */           die();
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/*  88 */       if (!isNoGravity()) {
/*  89 */         setMot(getMot().add(0.0D, -0.04D, 0.0D));
/*     */       }
/*     */       
/*  92 */       move(EnumMoveType.SELF, getMot());
/*     */ 
/*     */       
/*  95 */       if (this.dead) {
/*     */         return;
/*     */       }
/*     */ 
/*     */ 
/*     */       
/* 101 */       if (this.world.paperConfig.fallingBlockHeightNerf != 0 && locY() > this.world.paperConfig.fallingBlockHeightNerf) {
/* 102 */         if (this.dropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/* 103 */           a(block);
/*     */         }
/*     */         
/* 106 */         die();
/*     */         
/*     */         return;
/*     */       } 
/* 110 */       if (!this.world.isClientSide) {
/* 111 */         BlockPosition blockposition = getChunkCoordinates();
/* 112 */         boolean flag = this.block.getBlock() instanceof BlockConcretePowder;
/* 113 */         boolean flag1 = (flag && this.world.getFluid(blockposition).a(TagsFluid.WATER));
/* 114 */         double d0 = getMot().g();
/*     */         
/* 116 */         if (flag && d0 > 1.0D) {
/* 117 */           MovingObjectPositionBlock movingobjectpositionblock = this.world.rayTrace(new RayTrace(new Vec3D(this.lastX, this.lastY, this.lastZ), getPositionVector(), RayTrace.BlockCollisionOption.COLLIDER, RayTrace.FluidCollisionOption.SOURCE_ONLY, this));
/*     */           
/* 119 */           if (movingobjectpositionblock.getType() != MovingObjectPosition.EnumMovingObjectType.MISS && this.world.getFluid(movingobjectpositionblock.getBlockPosition()).a(TagsFluid.WATER)) {
/* 120 */             blockposition = movingobjectpositionblock.getBlockPosition();
/* 121 */             flag1 = true;
/*     */           } 
/*     */         } 
/*     */         
/* 125 */         if (!this.onGround && !flag1) {
/* 126 */           if (!this.world.isClientSide && ((this.ticksLived > 100 && (blockposition.getY() < 1 || blockposition.getY() > 256)) || this.ticksLived > 600)) {
/* 127 */             if (this.dropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/* 128 */               a(block);
/*     */             }
/*     */             
/* 131 */             die();
/*     */           } 
/*     */         } else {
/* 134 */           IBlockData iblockdata = this.world.getType(blockposition);
/*     */           
/* 136 */           setMot(getMot().d(0.7D, -0.5D, 0.7D));
/* 137 */           if (!iblockdata.a(Blocks.MOVING_PISTON)) {
/* 138 */             die();
/* 139 */             if (!this.g) {
/* 140 */               boolean flag2 = iblockdata.a(new BlockActionContextDirectional(this.world, blockposition, EnumDirection.DOWN, ItemStack.b, EnumDirection.UP));
/* 141 */               boolean flag3 = (BlockFalling.canFallThrough(this.world.getType(blockposition.down())) && (!flag || !flag1));
/* 142 */               boolean flag4 = (this.block.canPlace(this.world, blockposition) && !flag3);
/*     */               
/* 144 */               if (flag2 && flag4) {
/* 145 */                 if (this.block.b(BlockProperties.C) && this.world.getFluid(blockposition).getType() == FluidTypes.WATER) {
/* 146 */                   this.block = this.block.set(BlockProperties.C, Boolean.valueOf(true));
/*     */                 }
/*     */ 
/*     */                 
/* 150 */                 if (CraftEventFactory.callEntityChangeBlockEvent(this, blockposition, this.block).isCancelled()) {
/*     */                   return;
/*     */                 }
/*     */                 
/* 154 */                 if (this.world.setTypeAndData(blockposition, this.block, 3)) {
/* 155 */                   if (block instanceof BlockFalling) {
/* 156 */                     ((BlockFalling)block).a(this.world, blockposition, this.block, iblockdata, this);
/*     */                   }
/*     */                   
/* 159 */                   if (this.tileEntityData != null && block instanceof ITileEntity) {
/* 160 */                     TileEntity tileentity = this.world.getTileEntity(blockposition);
/*     */                     
/* 162 */                     if (tileentity != null) {
/* 163 */                       NBTTagCompound nbttagcompound = tileentity.save(new NBTTagCompound());
/* 164 */                       Iterator<String> iterator = this.tileEntityData.getKeys().iterator();
/*     */                       
/* 166 */                       while (iterator.hasNext()) {
/* 167 */                         String s = iterator.next();
/* 168 */                         NBTBase nbtbase = this.tileEntityData.get(s);
/*     */                         
/* 170 */                         if (!"x".equals(s) && !"y".equals(s) && !"z".equals(s)) {
/* 171 */                           nbttagcompound.set(s, nbtbase.clone());
/*     */                         }
/*     */                       } 
/*     */                       
/* 175 */                       tileentity.load(this.block, nbttagcompound);
/* 176 */                       tileentity.update();
/*     */                     } 
/*     */                   } 
/* 179 */                 } else if (this.dropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/* 180 */                   a(block);
/*     */                 } 
/* 182 */               } else if (this.dropItem && this.world.getGameRules().getBoolean(GameRules.DO_ENTITY_DROPS)) {
/* 183 */                 a(block);
/*     */               } 
/* 185 */             } else if (block instanceof BlockFalling) {
/* 186 */               ((BlockFalling)block).a(this.world, blockposition, this);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 192 */       setMot(getMot().a(0.98D));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(float f, float f1) {
/* 198 */     if (this.hurtEntities) {
/* 199 */       int i = MathHelper.f(f - 1.0F);
/*     */       
/* 201 */       if (i > 0) {
/* 202 */         List<Entity> list = Lists.newArrayList(this.world.getEntities(this, getBoundingBox()));
/* 203 */         boolean flag = this.block.a(TagsBlock.ANVIL);
/* 204 */         DamageSource damagesource = flag ? DamageSource.ANVIL : DamageSource.FALLING_BLOCK;
/* 205 */         Iterator<Entity> iterator = list.iterator();
/*     */         
/* 207 */         while (iterator.hasNext()) {
/* 208 */           Entity entity = iterator.next();
/*     */           
/* 210 */           CraftEventFactory.entityDamage = this;
/* 211 */           entity.damageEntity(damagesource, Math.min(MathHelper.d(i * this.fallHurtAmount), this.fallHurtMax));
/* 212 */           CraftEventFactory.entityDamage = null;
/*     */         } 
/*     */         
/* 215 */         if (flag && this.random.nextFloat() < 0.05000000074505806D + i * 0.05D) {
/* 216 */           IBlockData iblockdata = BlockAnvil.c(this.block);
/*     */           
/* 218 */           if (iblockdata == null) {
/* 219 */             this.g = true;
/*     */           } else {
/* 221 */             this.block = iblockdata;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 227 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/* 232 */     nbttagcompound.set("BlockState", GameProfileSerializer.a(this.block));
/* 233 */     nbttagcompound.setInt("Time", this.ticksLived);
/* 234 */     nbttagcompound.setBoolean("DropItem", this.dropItem);
/* 235 */     nbttagcompound.setBoolean("HurtEntities", this.hurtEntities);
/* 236 */     nbttagcompound.setFloat("FallHurtAmount", this.fallHurtAmount);
/* 237 */     nbttagcompound.setInt("FallHurtMax", this.fallHurtMax);
/* 238 */     if (this.tileEntityData != null) {
/* 239 */       nbttagcompound.set("TileEntityData", this.tileEntityData);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/* 246 */     this.block = GameProfileSerializer.c(nbttagcompound.getCompound("BlockState"));
/*     */ 
/*     */     
/* 249 */     Block b = this.block.getBlock();
/* 250 */     if (this.world.paperConfig.filterNBTFromSpawnEgg && (b == Blocks.COMMAND_BLOCK || b == Blocks.REPEATING_COMMAND_BLOCK || b == Blocks.CHAIN_COMMAND_BLOCK)) {
/* 251 */       this.block = Blocks.STONE.getBlockData();
/*     */     }
/*     */     
/* 254 */     this.ticksLived = nbttagcompound.getInt("Time");
/* 255 */     if (nbttagcompound.hasKeyOfType("HurtEntities", 99)) {
/* 256 */       this.hurtEntities = nbttagcompound.getBoolean("HurtEntities");
/* 257 */       this.fallHurtAmount = nbttagcompound.getFloat("FallHurtAmount");
/* 258 */       this.fallHurtMax = nbttagcompound.getInt("FallHurtMax");
/* 259 */     } else if (this.block.a(TagsBlock.ANVIL)) {
/* 260 */       this.hurtEntities = true;
/*     */     } 
/*     */     
/* 263 */     if (nbttagcompound.hasKeyOfType("DropItem", 99)) {
/* 264 */       this.dropItem = nbttagcompound.getBoolean("DropItem");
/*     */     }
/*     */     
/* 267 */     if (nbttagcompound.hasKeyOfType("TileEntityData", 10)) {
/* 268 */       this.tileEntityData = nbttagcompound.getCompound("TileEntityData");
/*     */     }
/*     */     
/* 271 */     if (this.block.isAir()) {
/* 272 */       this.block = Blocks.SAND.getBlockData();
/*     */     }
/*     */ 
/*     */     
/* 276 */     if (nbttagcompound.hasKey("SourceLoc_x")) {
/* 277 */       int srcX = nbttagcompound.getInt("SourceLoc_x");
/* 278 */       int srcY = nbttagcompound.getInt("SourceLoc_y");
/* 279 */       int srcZ = nbttagcompound.getInt("SourceLoc_z");
/* 280 */       this.origin = new Location((World)this.world.getWorld(), srcX, srcY, srcZ);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(boolean flag) {
/* 286 */     this.hurtEntities = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public void appendEntityCrashDetails(CrashReportSystemDetails crashreportsystemdetails) {
/* 291 */     super.appendEntityCrashDetails(crashreportsystemdetails);
/* 292 */     crashreportsystemdetails.a("Immitating BlockState", this.block.toString());
/*     */   }
/*     */   
/*     */   public IBlockData getBlock() {
/* 296 */     return this.block;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ci() {
/* 301 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 306 */     return new PacketPlayOutSpawnEntity(this, Block.getCombinedId(getBlock()));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityFallingBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */