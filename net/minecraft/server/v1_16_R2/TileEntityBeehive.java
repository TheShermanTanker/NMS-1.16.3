/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.entity.EntityEnterBlockEvent;
/*     */ 
/*     */ public class TileEntityBeehive extends TileEntity implements ITickable {
/*  10 */   private final List<HiveBee> bees = Lists.newArrayList(); @Nullable
/*  11 */   public BlockPosition flowerPos = null;
/*     */   
/*  13 */   public int maxBees = 3;
/*     */   
/*     */   public TileEntityBeehive() {
/*  16 */     super(TileEntityTypes.BEEHIVE);
/*     */   }
/*     */ 
/*     */   
/*     */   public void update() {
/*  21 */     if (d()) {
/*  22 */       a((EntityHuman)null, this.world.getType(getPosition()), ReleaseStatus.EMERGENCY);
/*     */     }
/*     */     
/*  25 */     super.update();
/*     */   }
/*     */   public boolean d() {
/*     */     BlockPosition blockposition;
/*  29 */     if (this.world == null) {
/*  30 */       return false;
/*     */     }
/*  32 */     Iterator<BlockPosition> iterator = BlockPosition.a(this.position.b(-1, -1, -1), this.position.b(1, 1, 1)).iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/*  37 */       if (!iterator.hasNext()) {
/*  38 */         return false;
/*     */       }
/*     */       
/*  41 */       blockposition = iterator.next();
/*  42 */     } while (!(this.world.getType(blockposition).getBlock() instanceof BlockFire));
/*     */     
/*  44 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isEmpty() {
/*  49 */     return this.bees.isEmpty();
/*     */   }
/*     */   
/*     */   public boolean isFull() {
/*  53 */     return (this.bees.size() == this.maxBees);
/*     */   }
/*     */   
/*     */   public void a(@Nullable EntityHuman entityhuman, IBlockData iblockdata, ReleaseStatus tileentitybeehive_releasestatus) {
/*  57 */     List<Entity> list = releaseBees(iblockdata, tileentitybeehive_releasestatus);
/*     */     
/*  59 */     if (entityhuman != null) {
/*  60 */       Iterator<Entity> iterator = list.iterator();
/*     */       
/*  62 */       while (iterator.hasNext()) {
/*  63 */         Entity entity = iterator.next();
/*     */         
/*  65 */         if (entity instanceof EntityBee) {
/*  66 */           EntityBee entitybee = (EntityBee)entity;
/*     */           
/*  68 */           if (entityhuman.getPositionVector().distanceSquared(entity.getPositionVector()) <= 16.0D) {
/*  69 */             if (!isSedated()) {
/*  70 */               entitybee.setGoalTarget(entityhuman, EntityTargetEvent.TargetReason.CLOSEST_PLAYER, true); continue;
/*     */             } 
/*  72 */             entitybee.setCannotEnterHiveTicks(400);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<Entity> releaseBees(IBlockData iblockdata, ReleaseStatus tileentitybeehive_releasestatus) {
/*  83 */     return releaseBees(iblockdata, tileentitybeehive_releasestatus, false);
/*     */   }
/*     */   
/*     */   public List<Entity> releaseBees(IBlockData iblockdata, ReleaseStatus tileentitybeehive_releasestatus, boolean force) {
/*  87 */     List<Entity> list = Lists.newArrayList();
/*     */     
/*  89 */     this.bees.removeIf(tileentitybeehive_hivebee -> releaseBee(iblockdata, tileentitybeehive_hivebee, list, tileentitybeehive_releasestatus, force));
/*     */ 
/*     */ 
/*     */     
/*  93 */     return list;
/*     */   }
/*     */   
/*     */   public void addBee(Entity entity, boolean flag) {
/*  97 */     a(entity, flag, 0);
/*     */   }
/*     */   
/*     */   public int getBeeCount() {
/* 101 */     return this.bees.size();
/*     */   }
/*     */   
/*     */   public static int a(IBlockData iblockdata) {
/* 105 */     return ((Integer)iblockdata.get(BlockBeehive.b)).intValue();
/*     */   }
/*     */   
/*     */   public boolean isSedated() {
/* 109 */     return BlockCampfire.a(this.world, getPosition());
/*     */   }
/*     */   
/*     */   protected void l() {
/* 113 */     PacketDebug.a(this);
/*     */   }
/*     */   
/*     */   public void a(Entity entity, boolean flag, int i) {
/* 117 */     if (this.bees.size() < this.maxBees) {
/*     */       
/* 119 */       if (this.world != null) {
/* 120 */         EntityEnterBlockEvent event = new EntityEnterBlockEvent((Entity)entity.getBukkitEntity(), (Block)CraftBlock.at(this.world, getPosition()));
/* 121 */         Bukkit.getPluginManager().callEvent((Event)event);
/* 122 */         if (event.isCancelled()) {
/* 123 */           if (entity instanceof EntityBee) {
/* 124 */             ((EntityBee)entity).setCannotEnterHiveTicks(400);
/*     */           }
/*     */           
/*     */           return;
/*     */         } 
/*     */       } 
/* 130 */       entity.stopRiding();
/* 131 */       entity.ejectPassengers();
/* 132 */       NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */       
/* 134 */       entity.d(nbttagcompound);
/* 135 */       this.bees.add(new HiveBee(nbttagcompound, i, flag ? 2400 : 600));
/* 136 */       if (this.world != null) {
/* 137 */         if (entity instanceof EntityBee) {
/* 138 */           EntityBee entitybee = (EntityBee)entity;
/*     */           
/* 140 */           if (entitybee.hasFlowerPos() && (!x() || this.world.random.nextBoolean())) {
/* 141 */             this.flowerPos = entitybee.getFlowerPos();
/*     */           }
/*     */         } 
/*     */         
/* 145 */         BlockPosition blockposition = getPosition();
/*     */         
/* 147 */         this.world.playSound((EntityHuman)null, blockposition.getX(), blockposition.getY(), blockposition.getZ(), SoundEffects.BLOCK_BEEHIVE_ENTER, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       } 
/*     */       
/* 150 */       entity.die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean releaseBee(IBlockData iblockdata, HiveBee tileentitybeehive_hivebee, @Nullable List<Entity> list, ReleaseStatus tileentitybeehive_releasestatus) {
/* 156 */     return releaseBee(iblockdata, tileentitybeehive_hivebee, list, tileentitybeehive_releasestatus, false);
/*     */   }
/*     */   
/*     */   private boolean releaseBee(IBlockData iblockdata, HiveBee tileentitybeehive_hivebee, @Nullable List<Entity> list, ReleaseStatus tileentitybeehive_releasestatus, boolean force) {
/* 160 */     if (!force && (this.world.isNight() || this.world.isRaining()) && tileentitybeehive_releasestatus != ReleaseStatus.EMERGENCY)
/*     */     {
/* 162 */       return false;
/*     */     }
/* 164 */     BlockPosition blockposition = getPosition();
/* 165 */     NBTTagCompound nbttagcompound = tileentitybeehive_hivebee.entityData;
/*     */     
/* 167 */     nbttagcompound.remove("Passengers");
/* 168 */     nbttagcompound.remove("Leash");
/* 169 */     nbttagcompound.remove("UUID");
/* 170 */     EnumDirection enumdirection = (EnumDirection)iblockdata.get(BlockBeehive.a);
/* 171 */     BlockPosition blockposition1 = blockposition.shift(enumdirection);
/* 172 */     boolean flag = !this.world.getType(blockposition1).getCollisionShape(this.world, blockposition1).isEmpty();
/*     */     
/* 174 */     if (flag && tileentitybeehive_releasestatus != ReleaseStatus.EMERGENCY) {
/* 175 */       return false;
/*     */     }
/* 177 */     Entity entity = EntityTypes.a(nbttagcompound, this.world, entity1 -> entity1);
/*     */ 
/*     */ 
/*     */     
/* 181 */     if (entity != null) {
/* 182 */       if (!entity.getEntityType().a(TagsEntity.BEEHIVE_INHABITORS)) {
/* 183 */         return false;
/*     */       }
/*     */       
/* 186 */       if (entity instanceof EntityBee) {
/* 187 */         float f = entity.getWidth();
/* 188 */         double d0 = flag ? 0.0D : (0.55D + (f / 2.0F));
/* 189 */         double d1 = blockposition.getX() + 0.5D + d0 * enumdirection.getAdjacentX();
/* 190 */         double d2 = blockposition.getY() + 0.5D - (entity.getHeight() / 2.0F);
/* 191 */         double d3 = blockposition.getZ() + 0.5D + d0 * enumdirection.getAdjacentZ();
/*     */         
/* 193 */         entity.setPositionRotation(d1, d2, d3, entity.yaw, entity.pitch);
/*     */       } 
/* 195 */       if (!this.world.addEntity(entity, CreatureSpawnEvent.SpawnReason.BEEHIVE)) return false;
/*     */       
/* 197 */       if (entity instanceof EntityBee) {
/* 198 */         EntityBee entitybee = (EntityBee)entity;
/*     */         
/* 200 */         if (x() && !entitybee.hasFlowerPos() && this.world.random.nextFloat() < 0.9F) {
/* 201 */           entitybee.setFlowerPos(this.flowerPos);
/*     */         }
/*     */         
/* 204 */         if (tileentitybeehive_releasestatus == ReleaseStatus.HONEY_DELIVERED) {
/* 205 */           entitybee.fb();
/* 206 */           if (iblockdata.getBlock().a(TagsBlock.BEEHIVES)) {
/* 207 */             int i = a(iblockdata);
/*     */             
/* 209 */             if (i < 5) {
/* 210 */               int j = (this.world.random.nextInt(100) == 0) ? 2 : 1;
/*     */               
/* 212 */               if (i + j > 5) {
/* 213 */                 j--;
/*     */               }
/*     */               
/* 216 */               this.world.setTypeUpdate(getPosition(), iblockdata.set(BlockBeehive.b, Integer.valueOf(i + j)));
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 221 */         a(tileentitybeehive_hivebee.ticksInHive, entitybee);
/* 222 */         if (list != null) {
/* 223 */           list.add(entitybee);
/*     */         }
/*     */       } 
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
/* 237 */       this.world.playSound((EntityHuman)null, blockposition, SoundEffects.BLOCK_BEEHIVE_EXIT, SoundCategory.BLOCKS, 1.0F, 1.0F);
/* 238 */       return true;
/*     */     } 
/*     */     
/* 241 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(int i, EntityBee entitybee) {
/* 248 */     int j = entitybee.getAge();
/*     */     
/* 250 */     if (j < 0) {
/* 251 */       entitybee.setAgeRaw(Math.min(0, j + i));
/* 252 */     } else if (j > 0) {
/* 253 */       entitybee.setAgeRaw(Math.max(0, j - i));
/*     */     } 
/*     */     
/* 256 */     entitybee.setLoveTicks(Math.max(0, entitybee.eQ() - i));
/* 257 */     entitybee.eO();
/*     */   }
/*     */   
/*     */   private boolean x() {
/* 261 */     return (this.flowerPos != null);
/*     */   }
/*     */   
/*     */   private void y() {
/* 265 */     Iterator<HiveBee> iterator = this.bees.iterator();
/*     */ 
/*     */ 
/*     */     
/* 269 */     for (IBlockData iblockdata = getBlock(); iterator.hasNext(); tileentitybeehive_hivebee.ticksInHive++) {
/* 270 */       HiveBee tileentitybeehive_hivebee = iterator.next();
/* 271 */       if (tileentitybeehive_hivebee.ticksInHive > tileentitybeehive_hivebee.minOccupationTicks) {
/* 272 */         ReleaseStatus tileentitybeehive_releasestatus = tileentitybeehive_hivebee.entityData.getBoolean("HasNectar") ? ReleaseStatus.HONEY_DELIVERED : ReleaseStatus.BEE_RELEASED;
/*     */         
/* 274 */         if (releaseBee(iblockdata, tileentitybeehive_hivebee, (List<Entity>)null, tileentitybeehive_releasestatus)) {
/* 275 */           iterator.remove();
/*     */         }
/*     */         else {
/*     */           
/* 279 */           tileentitybeehive_hivebee.ticksInHive = tileentitybeehive_hivebee.minOccupationTicks / 2;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 289 */     if (!this.world.isClientSide) {
/* 290 */       y();
/* 291 */       BlockPosition blockposition = getPosition();
/*     */       
/* 293 */       if (this.bees.size() > 0 && this.world.getRandom().nextDouble() < 0.005D) {
/* 294 */         double d0 = blockposition.getX() + 0.5D;
/* 295 */         double d1 = blockposition.getY();
/* 296 */         double d2 = blockposition.getZ() + 0.5D;
/*     */         
/* 298 */         this.world.playSound((EntityHuman)null, d0, d1, d2, SoundEffects.BLOCK_BEEHIVE_WORK, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*     */       } 
/*     */       
/* 301 */       l();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 307 */     super.load(iblockdata, nbttagcompound);
/* 308 */     this.bees.clear();
/* 309 */     NBTTagList nbttaglist = nbttagcompound.getList("Bees", 10);
/*     */     
/* 311 */     for (int i = 0; i < nbttaglist.size(); i++) {
/* 312 */       NBTTagCompound nbttagcompound1 = nbttaglist.getCompound(i);
/* 313 */       HiveBee tileentitybeehive_hivebee = new HiveBee(nbttagcompound1.getCompound("EntityData"), nbttagcompound1.getInt("TicksInHive"), nbttagcompound1.getInt("MinOccupationTicks"));
/*     */       
/* 315 */       this.bees.add(tileentitybeehive_hivebee);
/*     */     } 
/*     */     
/* 318 */     this.flowerPos = null;
/* 319 */     if (nbttagcompound.hasKey("FlowerPos")) {
/* 320 */       this.flowerPos = GameProfileSerializer.b(nbttagcompound.getCompound("FlowerPos"));
/*     */     }
/*     */ 
/*     */     
/* 324 */     if (nbttagcompound.hasKey("Bukkit.MaxEntities")) {
/* 325 */       this.maxBees = nbttagcompound.getInt("Bukkit.MaxEntities");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 332 */     super.save(nbttagcompound);
/* 333 */     nbttagcompound.set("Bees", m());
/* 334 */     if (x()) {
/* 335 */       nbttagcompound.set("FlowerPos", GameProfileSerializer.a(this.flowerPos));
/*     */     }
/* 337 */     nbttagcompound.setInt("Bukkit.MaxEntities", this.maxBees);
/*     */     
/* 339 */     return nbttagcompound;
/*     */   }
/*     */   
/*     */   public NBTTagList m() {
/* 343 */     NBTTagList nbttaglist = new NBTTagList();
/* 344 */     Iterator<HiveBee> iterator = this.bees.iterator();
/*     */     
/* 346 */     while (iterator.hasNext()) {
/* 347 */       HiveBee tileentitybeehive_hivebee = iterator.next();
/*     */       
/* 349 */       tileentitybeehive_hivebee.entityData.remove("UUID");
/* 350 */       NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */       
/* 352 */       nbttagcompound.set("EntityData", tileentitybeehive_hivebee.entityData);
/* 353 */       nbttagcompound.setInt("TicksInHive", tileentitybeehive_hivebee.ticksInHive);
/* 354 */       nbttagcompound.setInt("MinOccupationTicks", tileentitybeehive_hivebee.minOccupationTicks);
/* 355 */       nbttaglist.add(nbttagcompound);
/*     */     } 
/*     */     
/* 358 */     return nbttaglist;
/*     */   }
/*     */   
/*     */   static class HiveBee
/*     */   {
/*     */     private final NBTTagCompound entityData;
/*     */     private int ticksInHive;
/*     */     private final int minOccupationTicks;
/*     */     
/*     */     private HiveBee(NBTTagCompound nbttagcompound, int i, int j) {
/* 368 */       nbttagcompound.remove("UUID");
/* 369 */       this.entityData = nbttagcompound;
/* 370 */       this.ticksInHive = i;
/* 371 */       this.minOccupationTicks = j;
/*     */     }
/*     */   }
/*     */   
/*     */   public enum ReleaseStatus
/*     */   {
/* 377 */     HONEY_DELIVERED, BEE_RELEASED, EMERGENCY;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityBeehive.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */