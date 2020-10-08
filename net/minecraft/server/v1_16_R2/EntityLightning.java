/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ 
/*     */ public class EntityLightning
/*     */   extends Entity
/*     */ {
/*     */   private int lifeTicks;
/*     */   public long b;
/*     */   private int d;
/*     */   public boolean isEffect;
/*     */   @Nullable
/*     */   private EntityPlayer f;
/*     */   public boolean isSilent = false;
/*     */   
/*     */   public EntityLightning(EntityTypes<? extends EntityLightning> entitytypes, World world) {
/*  21 */     super(entitytypes, world);
/*  22 */     this.Y = true;
/*  23 */     this.lifeTicks = 2;
/*  24 */     this.b = this.random.nextLong();
/*  25 */     this.d = this.random.nextInt(3) + 1;
/*     */   }
/*     */   
/*     */   public void setEffect(boolean flag) {
/*  29 */     this.isEffect = flag;
/*     */   }
/*     */ 
/*     */   
/*     */   public SoundCategory getSoundCategory() {
/*  34 */     return SoundCategory.WEATHER;
/*     */   }
/*     */   
/*     */   public void d(@Nullable EntityPlayer entityplayer) {
/*  38 */     this.f = entityplayer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  43 */     super.tick();
/*  44 */     if (!this.isSilent && this.lifeTicks == 2) {
/*  45 */       EnumDifficulty enumdifficulty = this.world.getDifficulty();
/*     */       
/*  47 */       if (enumdifficulty == EnumDifficulty.NORMAL || enumdifficulty == EnumDifficulty.HARD) {
/*  48 */         a(4);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  53 */       float pitch = 0.8F + this.random.nextFloat() * 0.2F;
/*  54 */       int viewDistance = ((WorldServer)this.world).getServer().getViewDistance() * 16;
/*  55 */       for (EntityPlayer player : this.world.getPlayers()) {
/*  56 */         double deltaX = locX() - player.locX();
/*  57 */         double deltaZ = locZ() - player.locZ();
/*  58 */         double distanceSquared = deltaX * deltaX + deltaZ * deltaZ;
/*     */         
/*  60 */         if (distanceSquared <= this.world.paperConfig.sqrMaxLightningImpactSoundDistance) {
/*  61 */           player.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(SoundEffects.ENTITY_LIGHTNING_BOLT_IMPACT, SoundCategory.WEATHER, 
/*  62 */                 locX(), locY(), locZ(), 2.0F, 0.5F + this.random.nextFloat() * 0.2F));
/*     */         }
/*     */         
/*  65 */         if (this.world.paperConfig.sqrMaxThunderDistance != -1.0D && distanceSquared >= this.world.paperConfig.sqrMaxThunderDistance) {
/*     */           continue;
/*     */         }
/*     */ 
/*     */         
/*  70 */         if (distanceSquared > (viewDistance * viewDistance)) {
/*  71 */           double deltaLength = Math.sqrt(distanceSquared);
/*  72 */           double relativeX = player.locX() + deltaX / deltaLength * viewDistance;
/*  73 */           double relativeZ = player.locZ() + deltaZ / deltaLength * viewDistance;
/*  74 */           player.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(SoundEffects.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, relativeX, locY(), relativeZ, 10000.0F, pitch)); continue;
/*     */         } 
/*  76 */         player.playerConnection.sendPacket(new PacketPlayOutNamedSoundEffect(SoundEffects.ENTITY_LIGHTNING_BOLT_THUNDER, SoundCategory.WEATHER, locX(), locY(), locZ(), 10000.0F, pitch));
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  83 */     this.lifeTicks--;
/*  84 */     if (this.lifeTicks < 0) {
/*  85 */       if (this.d == 0) {
/*  86 */         die();
/*  87 */       } else if (this.lifeTicks < -this.random.nextInt(10)) {
/*  88 */         this.d--;
/*  89 */         this.lifeTicks = 1;
/*  90 */         this.b = this.random.nextLong();
/*  91 */         a(0);
/*     */       } 
/*     */     }
/*     */     
/*  95 */     if (this.lifeTicks >= 0 && !this.isEffect) {
/*  96 */       if (!(this.world instanceof WorldServer)) {
/*  97 */         this.world.c(2);
/*  98 */       } else if (!this.isEffect) {
/*  99 */         double d0 = 3.0D;
/* 100 */         List<Entity> list = this.world.getEntities(this, new AxisAlignedBB(locX() - 3.0D, locY() - 3.0D, locZ() - 3.0D, locX() + 3.0D, locY() + 6.0D + 3.0D, locZ() + 3.0D), Entity::isAlive);
/* 101 */         Iterator<Entity> iterator = list.iterator();
/*     */         
/* 103 */         while (iterator.hasNext()) {
/* 104 */           Entity entity = iterator.next();
/*     */           
/* 106 */           entity.onLightningStrike((WorldServer)this.world, this);
/*     */         } 
/*     */         
/* 109 */         if (this.f != null) {
/* 110 */           CriterionTriggers.E.a(this.f, list);
/*     */         }
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(int i) {
/* 118 */     if (!this.isEffect && !this.world.isClientSide && this.world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
/* 119 */       BlockPosition blockposition = getChunkCoordinates();
/* 120 */       IBlockData iblockdata = BlockFireAbstract.a(this.world, blockposition);
/*     */       
/* 122 */       if (this.world.getType(blockposition).isAir() && iblockdata.canPlace(this.world, blockposition))
/*     */       {
/* 124 */         if (!this.isEffect && !CraftEventFactory.callBlockIgniteEvent(this.world, blockposition, this).isCancelled()) {
/* 125 */           this.world.setTypeUpdate(blockposition, iblockdata);
/*     */         }
/*     */       }
/*     */ 
/*     */       
/* 130 */       for (int j = 0; j < i; j++) {
/* 131 */         BlockPosition blockposition1 = blockposition.b(this.random.nextInt(3) - 1, this.random.nextInt(3) - 1, this.random.nextInt(3) - 1);
/*     */         
/* 133 */         iblockdata = BlockFireAbstract.a(this.world, blockposition1);
/* 134 */         if (this.world.getType(blockposition1).isAir() && iblockdata.canPlace(this.world, blockposition1))
/*     */         {
/* 136 */           if (!this.isEffect && !CraftEventFactory.callBlockIgniteEvent(this.world, blockposition1, this).isCancelled()) {
/* 137 */             this.world.setTypeUpdate(blockposition1, iblockdata);
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {}
/*     */ 
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {}
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {}
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 157 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityLightning.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */