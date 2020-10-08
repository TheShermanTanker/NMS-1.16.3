/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.event.entity.EntityTeleportEndGatewayEvent;
/*     */ import com.destroystokyo.paper.event.player.PlayerTeleportEndGatewayEvent;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Location;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.block.EndGateway;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftEndGateway;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftPlayer;
/*     */ import org.bukkit.entity.Entity;
/*     */ import org.bukkit.event.Event;
/*     */ 
/*     */ public class TileEntityEndGateway extends TileEntityEnderPortal implements ITickable {
/*  18 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   public long age;
/*     */   private int c;
/*     */   @Nullable
/*     */   public BlockPosition exitPortal;
/*     */   public boolean exactTeleport;
/*     */   
/*     */   public TileEntityEndGateway() {
/*  26 */     super(TileEntityTypes.END_GATEWAY);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*  31 */     super.save(nbttagcompound);
/*  32 */     nbttagcompound.setLong("Age", this.age);
/*  33 */     if (this.exitPortal != null) {
/*  34 */       nbttagcompound.set("ExitPortal", GameProfileSerializer.a(this.exitPortal));
/*     */     }
/*     */     
/*  37 */     if (this.exactTeleport) {
/*  38 */       nbttagcompound.setBoolean("ExactTeleport", this.exactTeleport);
/*     */     }
/*     */     
/*  41 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/*  46 */     super.load(iblockdata, nbttagcompound);
/*  47 */     this.age = nbttagcompound.getLong("Age");
/*  48 */     if (nbttagcompound.hasKeyOfType("ExitPortal", 10)) {
/*  49 */       this.exitPortal = GameProfileSerializer.b(nbttagcompound.getCompound("ExitPortal"));
/*     */     }
/*     */     
/*  52 */     this.exactTeleport = nbttagcompound.getBoolean("ExactTeleport");
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  57 */     boolean flag = d();
/*  58 */     boolean flag1 = f();
/*     */     
/*  60 */     this.age++;
/*  61 */     if (flag1) {
/*  62 */       this.c--;
/*  63 */     } else if (!this.world.isClientSide) {
/*  64 */       List<Entity> list = this.world.a(Entity.class, new AxisAlignedBB(getPosition()), TileEntityEndGateway::a);
/*     */ 
/*     */       
/*  67 */       for (Entity entity : list) {
/*  68 */         if (entity.canPortal()) {
/*  69 */           b(entity);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */       
/*  75 */       if (this.age % 2400L == 0L) {
/*  76 */         h();
/*     */       }
/*     */     } 
/*     */     
/*  80 */     if (flag != d() || flag1 != f()) {
/*  81 */       update();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(Entity entity) {
/*  87 */     return (IEntitySelector.g.test(entity) && !entity.getRootVehicle().ah());
/*     */   }
/*     */   
/*     */   public boolean d() {
/*  91 */     return (this.age < 200L);
/*     */   }
/*     */   
/*     */   public boolean f() {
/*  95 */     return (this.c > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PacketPlayOutTileEntityData getUpdatePacket() {
/* 101 */     return new PacketPlayOutTileEntityData(this.position, 8, b());
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound b() {
/* 106 */     return save(new NBTTagCompound());
/*     */   }
/*     */   
/*     */   public void h() {
/* 110 */     if (!this.world.isClientSide) {
/* 111 */       this.c = 40;
/* 112 */       this.world.playBlockAction(getPosition(), getBlock().getBlock(), 1, 0);
/* 113 */       update();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean setProperty(int i, int j) {
/* 120 */     if (i == 1) {
/* 121 */       this.c = 40;
/* 122 */       return true;
/*     */     } 
/* 124 */     return super.setProperty(i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(Entity entity) {
/* 129 */     if (this.world instanceof WorldServer && !f()) {
/* 130 */       this.c = 100;
/* 131 */       if (this.exitPortal == null && this.world.getTypeKey() == DimensionManager.THE_END) {
/* 132 */         a((WorldServer)this.world);
/*     */       }
/*     */       
/* 135 */       if (this.exitPortal != null) {
/* 136 */         Entity entity1; BlockPosition blockposition = this.exactTeleport ? this.exitPortal : k();
/*     */ 
/*     */         
/* 139 */         if (entity instanceof EntityEnderPearl) {
/* 140 */           Entity entity2 = ((EntityEnderPearl)entity).getShooter();
/*     */           
/* 142 */           if (entity2 instanceof EntityPlayer) {
/* 143 */             CriterionTriggers.d.a((EntityPlayer)entity2, this.world.getType(getPosition()));
/*     */           }
/*     */           
/* 146 */           if (entity2 != null) {
/* 147 */             entity1 = entity2;
/* 148 */             entity.die();
/*     */           } else {
/* 150 */             entity1 = entity;
/*     */           } 
/*     */         } else {
/* 153 */           entity1 = entity.getRootVehicle();
/*     */         } 
/*     */ 
/*     */         
/* 157 */         if (entity1 instanceof EntityPlayer) {
/* 158 */           CraftPlayer player = (CraftPlayer)entity1.getBukkitEntity();
/* 159 */           Location location1 = new Location((World)this.world.getWorld(), blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D);
/* 160 */           location1.setPitch(player.getLocation().getPitch());
/* 161 */           location1.setYaw(player.getLocation().getYaw());
/*     */           
/* 163 */           PlayerTeleportEndGatewayEvent playerTeleportEndGatewayEvent = new PlayerTeleportEndGatewayEvent((Player)player, player.getLocation(), location1, (EndGateway)new CraftEndGateway(MCUtil.toLocation(this.world, getPosition()).getBlock()));
/* 164 */           Bukkit.getPluginManager().callEvent((Event)playerTeleportEndGatewayEvent);
/* 165 */           if (playerTeleportEndGatewayEvent.isCancelled()) {
/*     */             return;
/*     */           }
/*     */           
/* 169 */           entity1.resetPortalCooldown();
/* 170 */           ((EntityPlayer)entity1).playerConnection.teleport(playerTeleportEndGatewayEvent.getTo());
/* 171 */           h();
/*     */ 
/*     */           
/*     */           return;
/*     */         } 
/*     */         
/* 177 */         CraftEntity bukkitEntity = entity.getBukkitEntity();
/* 178 */         Location location = new Location((World)this.world.getWorld(), blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D);
/* 179 */         location.setPitch(bukkitEntity.getLocation().getPitch());
/* 180 */         location.setYaw(bukkitEntity.getLocation().getYaw());
/*     */         
/* 182 */         EntityTeleportEndGatewayEvent event = new EntityTeleportEndGatewayEvent((Entity)bukkitEntity, bukkitEntity.getLocation(), location, (EndGateway)new CraftEndGateway(MCUtil.toLocation(this.world, getPosition()).getBlock()));
/* 183 */         if (!event.callEvent()) {
/*     */           return;
/*     */         }
/*     */ 
/*     */         
/* 188 */         entity1.resetPortalCooldown();
/* 189 */         entity1.enderTeleportAndLoad(event.getTo().getX(), event.getTo().getY(), event.getTo().getZ());
/*     */       } 
/*     */       
/* 192 */       h();
/*     */     } 
/*     */   }
/*     */   
/*     */   private BlockPosition k() {
/* 197 */     BlockPosition blockposition = a(this.world, this.exitPortal.b(0, 2, 0), 5, false);
/*     */     
/* 199 */     LOGGER.debug("Best exit position for portal at {} is {}", this.exitPortal, blockposition);
/* 200 */     return blockposition.up();
/*     */   }
/*     */   
/*     */   private void a(WorldServer worldserver) {
/* 204 */     Vec3D vec3d = (new Vec3D(getPosition().getX(), 0.0D, getPosition().getZ())).d();
/* 205 */     Vec3D vec3d1 = vec3d.a(1024.0D);
/*     */     
/*     */     int i;
/*     */     
/* 209 */     for (i = 16; a(worldserver, vec3d1).b() > 0 && i-- > 0; vec3d1 = vec3d1.e(vec3d.a(-16.0D))) {
/* 210 */       LOGGER.debug("Skipping backwards past nonempty chunk at {}", vec3d1);
/*     */     }
/*     */     
/* 213 */     for (i = 16; a(worldserver, vec3d1).b() == 0 && i-- > 0; vec3d1 = vec3d1.e(vec3d.a(16.0D))) {
/* 214 */       LOGGER.debug("Skipping forward past empty chunk at {}", vec3d1);
/*     */     }
/*     */     
/* 217 */     LOGGER.debug("Found chunk at {}", vec3d1);
/* 218 */     Chunk chunk = a(worldserver, vec3d1);
/*     */     
/* 220 */     this.exitPortal = a(chunk);
/* 221 */     if (this.exitPortal == null) {
/* 222 */       this.exitPortal = new BlockPosition(vec3d1.x + 0.5D, 75.0D, vec3d1.z + 0.5D);
/* 223 */       LOGGER.debug("Failed to find suitable block, settling on {}", this.exitPortal);
/* 224 */       BiomeDecoratorGroups.END_ISLAND.a(worldserver, worldserver.getChunkProvider().getChunkGenerator(), new Random(this.exitPortal.asLong()), this.exitPortal);
/*     */     } else {
/* 226 */       LOGGER.debug("Found block at {}", this.exitPortal);
/*     */     } 
/*     */     
/* 229 */     this.exitPortal = a(worldserver, this.exitPortal, 16, true);
/* 230 */     LOGGER.debug("Creating portal at {}", this.exitPortal);
/* 231 */     this.exitPortal = this.exitPortal.up(10);
/* 232 */     a(worldserver, this.exitPortal);
/* 233 */     update();
/*     */   }
/*     */   
/*     */   private static BlockPosition a(IBlockAccess iblockaccess, BlockPosition blockposition, int i, boolean flag) {
/* 237 */     BlockPosition blockposition1 = null;
/*     */     
/* 239 */     for (int j = -i; j <= i; j++) {
/* 240 */       for (int k = -i; k <= i; k++) {
/* 241 */         if (j != 0 || k != 0 || flag) {
/* 242 */           for (int l = 255; l > ((blockposition1 == null) ? 0 : blockposition1.getY()); l--) {
/* 243 */             BlockPosition blockposition2 = new BlockPosition(blockposition.getX() + j, l, blockposition.getZ() + k);
/* 244 */             IBlockData iblockdata = iblockaccess.getType(blockposition2);
/*     */             
/* 246 */             if (iblockdata.r(iblockaccess, blockposition2) && (flag || !iblockdata.a(Blocks.BEDROCK))) {
/* 247 */               blockposition1 = blockposition2;
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */         }
/*     */       } 
/*     */     } 
/* 255 */     return (blockposition1 == null) ? blockposition : blockposition1;
/*     */   }
/*     */   
/*     */   private static Chunk a(World world, Vec3D vec3d) {
/* 259 */     return world.getChunkAt(MathHelper.floor(vec3d.x / 16.0D), MathHelper.floor(vec3d.z / 16.0D));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static BlockPosition a(Chunk chunk) {
/* 264 */     ChunkCoordIntPair chunkcoordintpair = chunk.getPos();
/* 265 */     BlockPosition blockposition = new BlockPosition(chunkcoordintpair.d(), 30, chunkcoordintpair.e());
/* 266 */     int i = chunk.b() + 16 - 1;
/* 267 */     BlockPosition blockposition1 = new BlockPosition(chunkcoordintpair.f(), i, chunkcoordintpair.g());
/* 268 */     BlockPosition blockposition2 = null;
/* 269 */     double d0 = 0.0D;
/* 270 */     Iterator<BlockPosition> iterator = BlockPosition.a(blockposition, blockposition1).iterator();
/*     */     
/* 272 */     while (iterator.hasNext()) {
/* 273 */       BlockPosition blockposition3 = iterator.next();
/* 274 */       IBlockData iblockdata = chunk.getType(blockposition3);
/* 275 */       BlockPosition blockposition4 = blockposition3.up();
/* 276 */       BlockPosition blockposition5 = blockposition3.up(2);
/*     */       
/* 278 */       if (iblockdata.a(Blocks.END_STONE) && !chunk.getType(blockposition4).r(chunk, blockposition4) && !chunk.getType(blockposition5).r(chunk, blockposition5)) {
/* 279 */         double d1 = blockposition3.distanceSquared(0.0D, 0.0D, 0.0D, true);
/*     */         
/* 281 */         if (blockposition2 == null || d1 < d0) {
/* 282 */           blockposition2 = blockposition3;
/* 283 */           d0 = d1;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 288 */     return blockposition2;
/*     */   }
/*     */   
/*     */   private void a(WorldServer worldserver, BlockPosition blockposition) {
/* 292 */     WorldGenerator.END_GATEWAY.b(WorldGenEndGatewayConfiguration.a(getPosition(), false)).a(worldserver, worldserver.getChunkProvider().getChunkGenerator(), new Random(), blockposition);
/*     */   }
/*     */   
/*     */   public void a(BlockPosition blockposition, boolean flag) {
/* 296 */     this.exactTeleport = flag;
/* 297 */     this.exitPortal = blockposition;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityEndGateway.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */