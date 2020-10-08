/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.tuinity.tuinity.util.TickThread;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.player.PlayerVelocityEvent;
/*     */ import org.bukkit.util.Vector;
/*     */ 
/*     */ public class EntityTrackerEntry {
/*     */   private final WorldServer b;
/*     */   private final Entity tracker;
/*  22 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final int d;
/*     */   private final boolean e;
/*     */   
/*     */   private Consumer<Packet<?>> getPacketConsumer() {
/*  27 */     return this.f;
/*     */   }
/*     */   private final Consumer<Packet<?>> f;
/*     */   private long xLoc;
/*     */   private long yLoc;
/*     */   private long zLoc;
/*     */   private int yRot;
/*     */   private int xRot;
/*     */   private int headYaw;
/*     */   private Vec3D m;
/*     */   private int tickCounter;
/*     */   private int o;
/*     */   private List<Entity> p;
/*     */   private boolean q;
/*     */   private boolean r;
/*     */   final Set<EntityPlayer> trackedPlayers;
/*  43 */   private Map<EntityPlayer, Boolean> trackedPlayerMap = null;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendPlayerPacket(EntityPlayer player, Packet<?> packet) {
/*  49 */     player.playerConnection.sendPacket(packet);
/*     */   }
/*     */   
/*     */   public EntityTrackerEntry(WorldServer worldserver, Entity entity, int i, boolean flag, Consumer<Packet<?>> consumer, Map<EntityPlayer, Boolean> trackedPlayers) {
/*  53 */     this(worldserver, entity, i, flag, consumer, trackedPlayers.keySet());
/*  54 */     this.trackedPlayerMap = trackedPlayers;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityTrackerEntry(WorldServer worldserver, Entity entity, int i, boolean flag, Consumer<Packet<?>> consumer, Set<EntityPlayer> trackedPlayers) {
/*  59 */     this.trackedPlayers = trackedPlayers;
/*     */     
/*  61 */     this.m = Vec3D.ORIGIN;
/*  62 */     this.p = Collections.emptyList();
/*  63 */     this.b = worldserver;
/*  64 */     this.f = consumer;
/*  65 */     this.tracker = entity;
/*  66 */     this.d = i;
/*  67 */     this.e = flag;
/*  68 */     d();
/*  69 */     this.yRot = MathHelper.d(entity.yaw * 256.0F / 360.0F);
/*  70 */     this.xRot = MathHelper.d(entity.pitch * 256.0F / 360.0F);
/*  71 */     this.headYaw = MathHelper.d(entity.getHeadRotation() * 256.0F / 360.0F);
/*  72 */     this.r = entity.isOnGround();
/*     */   }
/*     */   public final void tick() {
/*  75 */     a();
/*     */   } public void a() {
/*  77 */     TickThread.softEnsureTickThread("Tracker update");
/*  78 */     List<Entity> list = this.tracker.getPassengers();
/*     */     
/*  80 */     if (!list.equals(this.p)) {
/*  81 */       this.p = list;
/*  82 */       broadcastIncludingSelf(new PacketPlayOutMount(this.tracker));
/*     */     } 
/*     */ 
/*     */     
/*  86 */     if (this.tracker instanceof EntityItemFrame) {
/*  87 */       EntityItemFrame entityitemframe = (EntityItemFrame)this.tracker;
/*  88 */       ItemStack itemstack = entityitemframe.getItem();
/*     */       
/*  90 */       if (this.tickCounter % 10 == 0 && itemstack.getItem() instanceof ItemWorldMap) {
/*  91 */         WorldMap worldmap = ItemWorldMap.getSavedMap(itemstack, this.b);
/*  92 */         Iterator<EntityPlayer> iterator = this.trackedPlayers.iterator();
/*     */         
/*  94 */         while (iterator.hasNext()) {
/*  95 */           EntityPlayer entityplayer = iterator.next();
/*     */           
/*  97 */           worldmap.a(entityplayer, itemstack);
/*  98 */           Packet<?> packet = ((ItemWorldMap)itemstack.getItem()).a(itemstack, this.b, entityplayer);
/*     */           
/* 100 */           if (packet != null) {
/* 101 */             entityplayer.playerConnection.sendPacket(packet);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 106 */       c();
/*     */     } 
/*     */     
/* 109 */     if (this.tickCounter % this.d == 0 || this.tracker.impulse || this.tracker.getDataWatcher().a()) {
/*     */ 
/*     */ 
/*     */       
/* 113 */       if (this.tracker.isPassenger()) {
/* 114 */         int k = MathHelper.d(this.tracker.yaw * 256.0F / 360.0F);
/* 115 */         int j = MathHelper.d(this.tracker.pitch * 256.0F / 360.0F);
/* 116 */         boolean flag = (Math.abs(k - this.yRot) >= 1 || Math.abs(j - this.xRot) >= 1);
/*     */         
/* 118 */         if (flag) {
/* 119 */           this.f.accept(new PacketPlayOutEntity.PacketPlayOutEntityLook(this.tracker.getId(), (byte)k, (byte)j, this.tracker.isOnGround()));
/* 120 */           this.yRot = k;
/* 121 */           this.xRot = j;
/*     */         } 
/*     */         
/* 124 */         d();
/* 125 */         c();
/* 126 */         this.q = true;
/*     */       } else {
/* 128 */         this.o++;
/* 129 */         int k = MathHelper.d(this.tracker.yaw * 256.0F / 360.0F);
/* 130 */         int j = MathHelper.d(this.tracker.pitch * 256.0F / 360.0F);
/*     */         
/* 132 */         double vec3d_dx = this.tracker.locX() - 2.44140625E-4D * this.xLoc;
/* 133 */         double vec3d_dy = this.tracker.locY() - 2.44140625E-4D * this.yLoc;
/* 134 */         double vec3d_dz = this.tracker.locZ() - 2.44140625E-4D * this.zLoc;
/* 135 */         boolean flag1 = (vec3d_dx * vec3d_dx + vec3d_dy * vec3d_dy + vec3d_dz * vec3d_dz >= 7.62939453125E-6D);
/*     */         
/* 137 */         Packet<?> packet1 = null;
/* 138 */         boolean flag2 = (flag1 || this.tickCounter % 60 == 0);
/* 139 */         boolean flag3 = (Math.abs(k - this.yRot) >= 1 || Math.abs(j - this.xRot) >= 1);
/*     */ 
/*     */         
/* 142 */         if (flag2) {
/* 143 */           d();
/*     */         }
/*     */         
/* 146 */         if (flag3) {
/* 147 */           this.yRot = k;
/* 148 */           this.xRot = j;
/*     */         } 
/*     */ 
/*     */         
/* 152 */         if (this.tickCounter > 0 || this.tracker instanceof EntityArrow) {
/*     */           
/* 154 */           long l1 = PacketPlayOutEntity.a(vec3d_dx);
/* 155 */           long l = PacketPlayOutEntity.a(vec3d_dy);
/* 156 */           long i1 = PacketPlayOutEntity.a(vec3d_dz);
/*     */           
/* 158 */           boolean flag4 = (l1 < -32768L || l1 > 32767L || l < -32768L || l > 32767L || i1 < -32768L || i1 > 32767L);
/*     */           
/* 160 */           if (!flag4 && this.o <= 400 && !this.q && this.r == this.tracker.isOnGround()) {
/* 161 */             if ((!flag2 || !flag3) && !(this.tracker instanceof EntityArrow)) {
/* 162 */               if (flag2) {
/* 163 */                 packet1 = new PacketPlayOutEntity.PacketPlayOutRelEntityMove(this.tracker.getId(), (short)(int)l1, (short)(int)l, (short)(int)i1, this.tracker.isOnGround());
/* 164 */               } else if (flag3) {
/* 165 */                 packet1 = new PacketPlayOutEntity.PacketPlayOutEntityLook(this.tracker.getId(), (byte)k, (byte)j, this.tracker.isOnGround());
/*     */               } 
/*     */             } else {
/* 168 */               packet1 = new PacketPlayOutEntity.PacketPlayOutRelEntityMoveLook(this.tracker.getId(), (short)(int)l1, (short)(int)l, (short)(int)i1, (byte)k, (byte)j, this.tracker.isOnGround());
/*     */             } 
/*     */           } else {
/* 171 */             this.r = this.tracker.isOnGround();
/* 172 */             this.o = 0;
/* 173 */             packet1 = new PacketPlayOutEntityTeleport(this.tracker);
/*     */           } 
/*     */         } 
/*     */         
/* 177 */         if ((this.e || this.tracker.impulse || (this.tracker instanceof EntityLiving && ((EntityLiving)this.tracker).isGliding())) && this.tickCounter > 0) {
/* 178 */           Vec3D vec3d1 = this.tracker.getMot();
/* 179 */           double d0 = vec3d1.distanceSquared(this.m);
/*     */           
/* 181 */           if (d0 > 1.0E-7D || (d0 > 0.0D && vec3d1.g() == 0.0D)) {
/* 182 */             this.m = vec3d1;
/* 183 */             this.f.accept(new PacketPlayOutEntityVelocity(this.tracker.getId(), this.m));
/*     */           } 
/*     */         } 
/*     */         
/* 187 */         if (packet1 != null)
/*     */         {
/* 189 */           if (this.trackedPlayerMap == null || packet1 instanceof PacketPlayOutEntityTeleport) {
/* 190 */             this.f.accept(packet1);
/*     */           } else {
/* 192 */             PacketPlayOutEntityTeleport teleportPacket = null;
/*     */             
/* 194 */             for (Map.Entry<EntityPlayer, Boolean> viewer : this.trackedPlayerMap.entrySet()) {
/* 195 */               if (((Boolean)viewer.getValue()).booleanValue()) {
/* 196 */                 viewer.setValue(Boolean.valueOf(false));
/* 197 */                 if (teleportPacket == null) {
/* 198 */                   teleportPacket = new PacketPlayOutEntityTeleport(this.tracker);
/*     */                 }
/* 200 */                 sendPlayerPacket(viewer.getKey(), teleportPacket); continue;
/*     */               } 
/* 202 */               sendPlayerPacket(viewer.getKey(), packet1);
/*     */             } 
/*     */           } 
/*     */         }
/*     */ 
/*     */ 
/*     */         
/* 209 */         c();
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
/* 221 */         this.q = false;
/*     */       } 
/*     */       
/* 224 */       int i = MathHelper.d(this.tracker.getHeadRotation() * 256.0F / 360.0F);
/* 225 */       if (Math.abs(i - this.headYaw) >= 1) {
/* 226 */         this.f.accept(new PacketPlayOutEntityHeadRotation(this.tracker, (byte)i));
/* 227 */         this.headYaw = i;
/*     */       } 
/*     */       
/* 230 */       this.tracker.impulse = false;
/*     */     } 
/*     */     
/* 233 */     this.tickCounter++;
/* 234 */     if (this.tracker.velocityChanged) {
/*     */       
/* 236 */       boolean cancelled = false;
/*     */       
/* 238 */       if (this.tracker instanceof EntityPlayer) {
/* 239 */         Player player = (Player)this.tracker.getBukkitEntity();
/* 240 */         Vector velocity = player.getVelocity();
/*     */         
/* 242 */         PlayerVelocityEvent event = new PlayerVelocityEvent(player, velocity.clone());
/* 243 */         this.tracker.world.getServer().getPluginManager().callEvent((Event)event);
/*     */         
/* 245 */         if (event.isCancelled()) {
/* 246 */           cancelled = true;
/* 247 */         } else if (!velocity.equals(event.getVelocity())) {
/* 248 */           player.setVelocity(event.getVelocity());
/*     */         } 
/*     */       } 
/*     */       
/* 252 */       if (!cancelled) {
/* 253 */         broadcastIncludingSelf(new PacketPlayOutEntityVelocity(this.tracker));
/*     */       }
/*     */       
/* 256 */       this.tracker.velocityChanged = false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(EntityPlayer entityplayer) {
/* 262 */     this.tracker.c(entityplayer);
/* 263 */     entityplayer.c(this.tracker);
/*     */   }
/*     */   
/*     */   public void b(EntityPlayer entityplayer) {
/* 267 */     PlayerConnection playerconnection = entityplayer.playerConnection;
/*     */     
/* 269 */     entityplayer.playerConnection.getClass();
/* 270 */     Objects.requireNonNull(playerconnection); a(playerconnection::sendPacket, entityplayer);
/* 271 */     this.tracker.b(entityplayer);
/* 272 */     entityplayer.d(this.tracker);
/*     */   }
/*     */   
/*     */   public void a(Consumer<Packet<?>> consumer, EntityPlayer entityplayer) {
/* 276 */     if (this.tracker.dead) {
/*     */       return;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 283 */     Packet<?> packet = this.tracker.P();
/*     */     
/* 285 */     this.headYaw = MathHelper.d(this.tracker.getHeadRotation() * 256.0F / 360.0F);
/* 286 */     consumer.accept(packet);
/* 287 */     if (!this.tracker.getDataWatcher().d()) {
/* 288 */       consumer.accept(new PacketPlayOutEntityMetadata(this.tracker.getId(), this.tracker.getDataWatcher(), true));
/*     */     }
/*     */     
/* 291 */     boolean flag = this.e;
/*     */     
/* 293 */     if (this.tracker instanceof EntityLiving) {
/* 294 */       Collection<AttributeModifiable> collection = ((EntityLiving)this.tracker).getAttributeMap().b();
/*     */ 
/*     */       
/* 297 */       if (this.tracker.getId() == entityplayer.getId()) {
/* 298 */         ((EntityPlayer)this.tracker).getBukkitEntity().injectScaledMaxHealth(collection, false);
/*     */       }
/*     */ 
/*     */       
/* 302 */       if (!collection.isEmpty()) {
/* 303 */         consumer.accept(new PacketPlayOutUpdateAttributes(this.tracker.getId(), collection));
/*     */       }
/*     */       
/* 306 */       if (((EntityLiving)this.tracker).isGliding()) {
/* 307 */         flag = true;
/*     */       }
/*     */     } 
/*     */     
/* 311 */     this.m = this.tracker.getMot();
/* 312 */     if (flag && !(packet instanceof PacketPlayOutSpawnEntityLiving)) {
/* 313 */       consumer.accept(new PacketPlayOutEntityVelocity(this.tracker.getId(), this.m));
/*     */     }
/*     */     
/* 316 */     if (this.tracker instanceof EntityLiving) {
/* 317 */       List<Pair<EnumItemSlot, ItemStack>> list = Lists.newArrayList();
/* 318 */       EnumItemSlot[] aenumitemslot = EnumItemSlot.values();
/* 319 */       int i = aenumitemslot.length;
/*     */       
/* 321 */       for (int j = 0; j < i; j++) {
/* 322 */         EnumItemSlot enumitemslot = aenumitemslot[j];
/* 323 */         ItemStack itemstack = ((EntityLiving)this.tracker).getEquipment(enumitemslot);
/*     */         
/* 325 */         if (!itemstack.isEmpty()) {
/* 326 */           list.add(Pair.of(enumitemslot, itemstack.cloneItemStack()));
/*     */         }
/*     */       } 
/*     */       
/* 330 */       if (!list.isEmpty()) {
/* 331 */         consumer.accept(new PacketPlayOutEntityEquipment(this.tracker.getId(), list));
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 336 */     this.headYaw = MathHelper.d(this.tracker.getHeadRotation() * 256.0F / 360.0F);
/* 337 */     consumer.accept(new PacketPlayOutEntityHeadRotation(this.tracker, (byte)this.headYaw));
/*     */ 
/*     */     
/* 340 */     if (this.tracker instanceof EntityLiving) {
/* 341 */       EntityLiving entityliving = (EntityLiving)this.tracker;
/* 342 */       Iterator<MobEffect> iterator = entityliving.getEffects().iterator();
/*     */       
/* 344 */       while (iterator.hasNext()) {
/* 345 */         MobEffect mobeffect = iterator.next();
/*     */         
/* 347 */         consumer.accept(new PacketPlayOutEntityEffect(this.tracker.getId(), mobeffect));
/*     */       } 
/*     */     } 
/*     */     
/* 351 */     if (!this.tracker.getPassengers().isEmpty()) {
/* 352 */       consumer.accept(new PacketPlayOutMount(this.tracker));
/*     */     }
/*     */     
/* 355 */     if (this.tracker.isPassenger()) {
/* 356 */       consumer.accept(new PacketPlayOutMount(this.tracker.getVehicle()));
/*     */     }
/*     */     
/* 359 */     if (this.tracker instanceof EntityInsentient) {
/* 360 */       EntityInsentient entityinsentient = (EntityInsentient)this.tracker;
/*     */       
/* 362 */       if (entityinsentient.isLeashed()) {
/* 363 */         consumer.accept(new PacketPlayOutAttachEntity(entityinsentient, entityinsentient.getLeashHolder()));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void c() {
/* 370 */     DataWatcher datawatcher = this.tracker.getDataWatcher();
/*     */     
/* 372 */     if (datawatcher.a()) {
/* 373 */       broadcastIncludingSelf(new PacketPlayOutEntityMetadata(this.tracker.getId(), datawatcher, false));
/*     */     }
/*     */     
/* 376 */     if (this.tracker instanceof EntityLiving) {
/* 377 */       Set<AttributeModifiable> set = ((EntityLiving)this.tracker).getAttributeMap().getAttributes();
/*     */       
/* 379 */       if (!set.isEmpty()) {
/*     */         
/* 381 */         if (this.tracker instanceof EntityPlayer) {
/* 382 */           ((EntityPlayer)this.tracker).getBukkitEntity().injectScaledMaxHealth(set, false);
/*     */         }
/*     */         
/* 385 */         broadcastIncludingSelf(new PacketPlayOutUpdateAttributes(this.tracker.getId(), set));
/*     */       } 
/*     */       
/* 388 */       set.clear();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void d() {
/* 394 */     this.xLoc = PacketPlayOutEntity.a(this.tracker.locX());
/* 395 */     this.yLoc = PacketPlayOutEntity.a(this.tracker.locY());
/* 396 */     this.zLoc = PacketPlayOutEntity.a(this.tracker.locZ());
/*     */   }
/*     */   
/*     */   public Vec3D b() {
/* 400 */     return PacketPlayOutEntity.a(this.xLoc, this.yLoc, this.zLoc);
/*     */   }
/*     */ 
/*     */   
/*     */   void broadcast(Packet<?> packet) {
/* 405 */     getPacketConsumer().accept(packet);
/*     */   }
/*     */ 
/*     */   
/*     */   private void broadcastIncludingSelf(Packet<?> packet) {
/* 410 */     this.f.accept(packet);
/* 411 */     if (this.tracker instanceof EntityPlayer)
/* 412 */       ((EntityPlayer)this.tracker).playerConnection.sendPacket(packet); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityTrackerEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */