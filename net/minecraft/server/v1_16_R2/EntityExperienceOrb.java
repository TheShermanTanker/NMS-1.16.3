/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.destroystokyo.paper.event.player.PlayerPickupExperienceEvent;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftLivingEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.entity.ExperienceOrb;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.entity.EntityTargetEvent;
/*     */ import org.bukkit.event.entity.EntityTargetLivingEntityEvent;
/*     */ import org.bukkit.event.player.PlayerItemMendEvent;
/*     */ 
/*     */ 
/*     */ public class EntityExperienceOrb
/*     */   extends Entity
/*     */ {
/*     */   public int b;
/*     */   public int c;
/*     */   public int d;
/*     */   private int e;
/*  22 */   public ExperienceOrb.SpawnReason spawnReason = ExperienceOrb.SpawnReason.UNKNOWN; public int value; private EntityHuman targetPlayer; private int targetTime; public UUID sourceEntityId; public UUID triggerEntityId;
/*     */   
/*     */   private void loadPaperNBT(NBTTagCompound nbttagcompound) {
/*  25 */     if (!nbttagcompound.hasKeyOfType("Paper.ExpData", 10)) {
/*     */       return;
/*     */     }
/*  28 */     NBTTagCompound comp = nbttagcompound.getCompound("Paper.ExpData");
/*  29 */     if (comp.hasUUID("source")) {
/*  30 */       this.sourceEntityId = comp.getUUID("source");
/*     */     }
/*  32 */     if (comp.hasUUID("trigger")) {
/*  33 */       this.triggerEntityId = comp.getUUID("trigger");
/*     */     }
/*  35 */     if (comp.hasKey("reason")) {
/*  36 */       String reason = comp.getString("reason");
/*     */       try {
/*  38 */         this.spawnReason = ExperienceOrb.SpawnReason.valueOf(reason);
/*  39 */       } catch (Exception e) {
/*  40 */         this.world.getServer().getLogger().warning("Invalid spawnReason set for experience orb: " + e.getMessage() + " - " + reason);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   private void savePaperNBT(NBTTagCompound nbttagcompound) {
/*  45 */     NBTTagCompound comp = new NBTTagCompound();
/*  46 */     if (this.sourceEntityId != null) {
/*  47 */       comp.setUUID("source", this.sourceEntityId);
/*     */     }
/*  49 */     if (this.triggerEntityId != null) {
/*  50 */       comp.setUUID("trigger", this.triggerEntityId);
/*     */     }
/*  52 */     if (this.spawnReason != null && this.spawnReason != ExperienceOrb.SpawnReason.UNKNOWN) {
/*  53 */       comp.setString("reason", this.spawnReason.name());
/*     */     }
/*  55 */     nbttagcompound.set("Paper.ExpData", comp);
/*     */   }
/*     */   
/*     */   public EntityExperienceOrb(World world, double d0, double d1, double d2, int i) {
/*  59 */     this(world, d0, d1, d2, i, (ExperienceOrb.SpawnReason)null, (Entity)null);
/*     */   }
/*     */   
/*     */   public EntityExperienceOrb(World world, double d0, double d1, double d2, int i, ExperienceOrb.SpawnReason reason, Entity triggerId) {
/*  63 */     this(world, d0, d1, d2, i, reason, triggerId, (Entity)null);
/*     */   }
/*     */   
/*     */   public EntityExperienceOrb(World world, double d0, double d1, double d2, int i, ExperienceOrb.SpawnReason reason, Entity triggerId, Entity sourceId) {
/*  67 */     this(EntityTypes.EXPERIENCE_ORB, world);
/*  68 */     this.sourceEntityId = (sourceId != null) ? sourceId.getUniqueID() : null;
/*  69 */     this.triggerEntityId = (triggerId != null) ? triggerId.getUniqueID() : null;
/*  70 */     this.spawnReason = (reason != null) ? reason : ExperienceOrb.SpawnReason.UNKNOWN;
/*     */     
/*  72 */     setPosition(d0, d1, d2);
/*  73 */     this.yaw = (float)(this.random.nextDouble() * 360.0D);
/*  74 */     setMot((this.random.nextDouble() * 0.20000000298023224D - 0.10000000149011612D) * 2.0D, this.random.nextDouble() * 0.2D * 2.0D, (this.random.nextDouble() * 0.20000000298023224D - 0.10000000149011612D) * 2.0D);
/*  75 */     this.value = i;
/*     */   }
/*     */   
/*     */   public EntityExperienceOrb(EntityTypes<? extends EntityExperienceOrb> entitytypes, World world) {
/*  79 */     super(entitytypes, world);
/*  80 */     this.e = 5;
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean playStepSound() {
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {}
/*     */ 
/*     */   
/*     */   public void tick() {
/*  93 */     super.tick();
/*  94 */     EntityHuman prevTarget = this.targetPlayer;
/*  95 */     if (this.d > 0) {
/*  96 */       this.d--;
/*     */     }
/*     */     
/*  99 */     this.lastX = locX();
/* 100 */     this.lastY = locY();
/* 101 */     this.lastZ = locZ();
/* 102 */     if (a(TagsFluid.WATER)) {
/* 103 */       i();
/* 104 */     } else if (!isNoGravity()) {
/* 105 */       setMot(getMot().add(0.0D, -0.03D, 0.0D));
/*     */     } 
/*     */     
/* 108 */     if (this.world.getFluid(getChunkCoordinates()).a(TagsFluid.LAVA)) {
/* 109 */       setMot(((this.random.nextFloat() - this.random.nextFloat()) * 0.2F), 0.20000000298023224D, ((this.random.nextFloat() - this.random.nextFloat()) * 0.2F));
/* 110 */       playSound(SoundEffects.ENTITY_GENERIC_BURN, 0.4F, 2.0F + this.random.nextFloat() * 0.4F);
/*     */     } 
/*     */     
/* 113 */     if (!this.world.b(getBoundingBox())) {
/* 114 */       l(locX(), ((getBoundingBox()).minY + (getBoundingBox()).maxY) / 2.0D, locZ());
/*     */     }
/*     */     
/* 117 */     double d0 = 8.0D;
/*     */     
/* 119 */     if (this.targetTime < this.b - 20 + getId() % 100) {
/* 120 */       if (this.targetPlayer == null || this.targetPlayer.h(this) > 64.0D) {
/* 121 */         this.targetPlayer = this.world.findNearbyPlayer(this, 8.0D);
/*     */       }
/*     */       
/* 124 */       this.targetTime = this.b;
/*     */     } 
/*     */     
/* 127 */     if (this.targetPlayer != null && this.targetPlayer.isSpectator()) {
/* 128 */       this.targetPlayer = null;
/*     */     }
/*     */ 
/*     */     
/* 132 */     boolean cancelled = false;
/* 133 */     if (this.targetPlayer != prevTarget) {
/* 134 */       EntityTargetLivingEntityEvent event = CraftEventFactory.callEntityTargetLivingEvent(this, this.targetPlayer, (this.targetPlayer != null) ? EntityTargetEvent.TargetReason.CLOSEST_PLAYER : EntityTargetEvent.TargetReason.FORGOT_TARGET);
/* 135 */       EntityLiving target = (event.getTarget() == null) ? null : ((CraftLivingEntity)event.getTarget()).getHandle();
/* 136 */       cancelled = event.isCancelled();
/*     */       
/* 138 */       if (cancelled) {
/* 139 */         this.targetPlayer = prevTarget;
/*     */       } else {
/* 141 */         this.targetPlayer = (target instanceof EntityHuman) ? (EntityHuman)target : null;
/*     */       } 
/*     */     } 
/*     */     
/* 145 */     if (this.targetPlayer != null && !cancelled) {
/*     */       
/* 147 */       Vec3D vec3d = new Vec3D(this.targetPlayer.locX() - locX(), this.targetPlayer.locY() + this.targetPlayer.getHeadHeight() / 2.0D - locY(), this.targetPlayer.locZ() - locZ());
/* 148 */       double d1 = vec3d.g();
/*     */       
/* 150 */       if (d1 < 64.0D) {
/* 151 */         double d2 = 1.0D - Math.sqrt(d1) / 8.0D;
/*     */         
/* 153 */         setMot(getMot().e(vec3d.d().a(d2 * d2 * 0.1D)));
/*     */       } 
/*     */     } 
/*     */     
/* 157 */     move(EnumMoveType.SELF, getMot());
/* 158 */     float f = 0.98F;
/*     */     
/* 160 */     if (this.onGround) {
/* 161 */       f = this.world.getType(new BlockPosition(locX(), locY() - 1.0D, locZ())).getBlock().getFrictionFactor() * 0.98F;
/*     */     }
/*     */     
/* 164 */     setMot(getMot().d(f, 0.98D, f));
/* 165 */     if (this.onGround) {
/* 166 */       setMot(getMot().d(1.0D, -0.9D, 1.0D));
/*     */     }
/*     */     
/* 169 */     this.b++;
/* 170 */     this.c++;
/* 171 */     if (this.c >= 6000) {
/* 172 */       die();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void i() {
/* 178 */     Vec3D vec3d = getMot();
/*     */     
/* 180 */     setMot(vec3d.x * 0.9900000095367432D, Math.min(vec3d.y + 5.000000237487257E-4D, 0.05999999865889549D), vec3d.z * 0.9900000095367432D);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void aL() {}
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 188 */     if (isInvulnerable(damagesource)) {
/* 189 */       return false;
/*     */     }
/* 191 */     velocityChanged();
/* 192 */     this.e = (int)(this.e - f);
/* 193 */     if (this.e <= 0) {
/* 194 */       die();
/*     */     }
/*     */     
/* 197 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 203 */     nbttagcompound.setShort("Health", (short)this.e);
/* 204 */     nbttagcompound.setShort("Age", (short)this.c);
/* 205 */     nbttagcompound.setInt("Value", this.value);
/* 206 */     savePaperNBT(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 211 */     this.e = nbttagcompound.getShort("Health");
/* 212 */     this.c = nbttagcompound.getShort("Age");
/* 213 */     this.value = nbttagcompound.getInt("Value");
/* 214 */     loadPaperNBT(nbttagcompound);
/*     */   }
/*     */ 
/*     */   
/*     */   public void pickup(EntityHuman entityhuman) {
/* 219 */     if (!this.world.isClientSide && 
/* 220 */       this.d == 0 && entityhuman.bu == 0 && (new PlayerPickupExperienceEvent((Player)((EntityPlayer)entityhuman).getBukkitEntity(), (ExperienceOrb)getBukkitEntity())).callEvent()) {
/* 221 */       entityhuman.bu = 2;
/* 222 */       entityhuman.receive(this, 1);
/* 223 */       Map.Entry<EnumItemSlot, ItemStack> entry = EnchantmentManager.a(Enchantments.MENDING, entityhuman, ItemStack::f);
/*     */       
/* 225 */       if (entry != null) {
/* 226 */         ItemStack itemstack = entry.getValue();
/*     */         
/* 228 */         if (!itemstack.isEmpty() && itemstack.f()) {
/* 229 */           int i = Math.min(c(this.value), itemstack.getDamage());
/*     */ 
/*     */           
/* 232 */           PlayerItemMendEvent event = CraftEventFactory.callPlayerItemMendEvent(entityhuman, this, itemstack, i);
/* 233 */           i = event.getRepairAmount();
/* 234 */           if (!event.isCancelled()) {
/* 235 */             this.value -= b(i);
/* 236 */             itemstack.setDamage(itemstack.getDamage() - i);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */ 
/*     */       
/* 242 */       if (this.value > 0) {
/* 243 */         entityhuman.giveExp(CraftEventFactory.callPlayerExpChangeEvent(entityhuman, this).getAmount());
/*     */       }
/*     */       
/* 246 */       die();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public final int durToXp(int i) {
/* 252 */     return b(i);
/*     */   } private int b(int i) {
/* 254 */     return i / 2;
/*     */   }
/*     */   public final int xpToDur(int i) {
/* 257 */     return c(i);
/*     */   } private int c(int i) {
/* 259 */     return i * 2;
/*     */   }
/*     */   
/*     */   public int g() {
/* 263 */     return this.value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int getOrbValue(int i) {
/* 268 */     if (i > 162670129) return i - 100000; 
/* 269 */     if (i > 81335063) return 81335063; 
/* 270 */     if (i > 40667527) return 40667527; 
/* 271 */     if (i > 20333759) return 20333759; 
/* 272 */     if (i > 10166857) return 10166857; 
/* 273 */     if (i > 5083423) return 5083423; 
/* 274 */     if (i > 2541701) return 2541701; 
/* 275 */     if (i > 1270849) return 1270849; 
/* 276 */     if (i > 635413) return 635413; 
/* 277 */     if (i > 317701) return 317701; 
/* 278 */     if (i > 158849) return 158849; 
/* 279 */     if (i > 79423) return 79423; 
/* 280 */     if (i > 39709) return 39709; 
/* 281 */     if (i > 19853) return 19853; 
/* 282 */     if (i > 9923) return 9923; 
/* 283 */     if (i > 4957) return 4957;
/*     */     
/* 285 */     return (i >= 2477) ? 2477 : ((i >= 1237) ? 1237 : ((i >= 617) ? 617 : ((i >= 307) ? 307 : ((i >= 149) ? 149 : ((i >= 73) ? 73 : ((i >= 37) ? 37 : ((i >= 17) ? 17 : ((i >= 7) ? 7 : ((i >= 3) ? 3 : 1)))))))));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean bK() {
/* 290 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 295 */     return new PacketPlayOutSpawnEntityExperienceOrb(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityExperienceOrb.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */