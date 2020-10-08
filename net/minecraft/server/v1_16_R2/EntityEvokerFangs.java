/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class EntityEvokerFangs extends Entity {
/*     */   private int b;
/*     */   private boolean c;
/*     */   private int d;
/*     */   private boolean e;
/*     */   private EntityLiving f;
/*     */   private UUID g;
/*     */   
/*     */   public EntityEvokerFangs(EntityTypes<? extends EntityEvokerFangs> entitytypes, World world) {
/*  18 */     super(entitytypes, world);
/*  19 */     this.d = 22;
/*     */   }
/*     */   
/*     */   public EntityEvokerFangs(World world, double d0, double d1, double d2, float f, int i, EntityLiving entityliving) {
/*  23 */     this(EntityTypes.EVOKER_FANGS, world);
/*  24 */     this.b = i;
/*  25 */     a(entityliving);
/*  26 */     this.yaw = f * 57.295776F;
/*  27 */     setPosition(d0, d1, d2);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {}
/*     */   
/*     */   public void a(@Nullable EntityLiving entityliving) {
/*  34 */     this.f = entityliving;
/*  35 */     this.g = (entityliving == null) ? null : entityliving.getUniqueID();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public EntityLiving getOwner() {
/*  40 */     if (this.f == null && this.g != null && this.world instanceof WorldServer) {
/*  41 */       Entity entity = ((WorldServer)this.world).getEntity(this.g);
/*     */       
/*  43 */       if (entity instanceof EntityLiving) {
/*  44 */         this.f = (EntityLiving)entity;
/*     */       }
/*     */     } 
/*     */     
/*  48 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void loadData(NBTTagCompound nbttagcompound) {
/*  53 */     this.b = nbttagcompound.getInt("Warmup");
/*  54 */     if (nbttagcompound.b("Owner")) {
/*  55 */       this.g = nbttagcompound.a("Owner");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void saveData(NBTTagCompound nbttagcompound) {
/*  62 */     nbttagcompound.setInt("Warmup", this.b);
/*  63 */     if (this.g != null) {
/*  64 */       nbttagcompound.a("Owner", this.g);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/*  71 */     super.tick();
/*  72 */     if (this.world.isClientSide) {
/*  73 */       if (this.e) {
/*  74 */         this.d--;
/*  75 */         if (this.d == 14) {
/*  76 */           for (int i = 0; i < 12; i++) {
/*  77 */             double d0 = locX() + (this.random.nextDouble() * 2.0D - 1.0D) * getWidth() * 0.5D;
/*  78 */             double d1 = locY() + 0.05D + this.random.nextDouble();
/*  79 */             double d2 = locZ() + (this.random.nextDouble() * 2.0D - 1.0D) * getWidth() * 0.5D;
/*  80 */             double d3 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
/*  81 */             double d4 = 0.3D + this.random.nextDouble() * 0.3D;
/*  82 */             double d5 = (this.random.nextDouble() * 2.0D - 1.0D) * 0.3D;
/*     */             
/*  84 */             this.world.addParticle(Particles.CRIT, d0, d1 + 1.0D, d2, d3, d4, d5);
/*     */           } 
/*     */         }
/*     */       } 
/*  88 */     } else if (--this.b < 0) {
/*  89 */       if (this.b == -8) {
/*  90 */         List<EntityLiving> list = this.world.a(EntityLiving.class, getBoundingBox().grow(0.2D, 0.0D, 0.2D));
/*  91 */         Iterator<EntityLiving> iterator = list.iterator();
/*     */         
/*  93 */         while (iterator.hasNext()) {
/*  94 */           EntityLiving entityliving = iterator.next();
/*     */           
/*  96 */           c(entityliving);
/*     */         } 
/*     */       } 
/*     */       
/* 100 */       if (!this.c) {
/* 101 */         this.world.broadcastEntityEffect(this, (byte)4);
/* 102 */         this.c = true;
/*     */       } 
/*     */       
/* 105 */       if (--this.d < 0) {
/* 106 */         die();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void c(EntityLiving entityliving) {
/* 113 */     EntityLiving entityliving1 = getOwner();
/*     */     
/* 115 */     if (entityliving.isAlive() && !entityliving.isInvulnerable() && entityliving != entityliving1) {
/* 116 */       if (entityliving1 == null) {
/* 117 */         CraftEventFactory.entityDamage = this;
/* 118 */         entityliving.damageEntity(DamageSource.MAGIC, 6.0F);
/* 119 */         CraftEventFactory.entityDamage = null;
/*     */       } else {
/* 121 */         if (entityliving1.r(entityliving)) {
/*     */           return;
/*     */         }
/*     */         
/* 125 */         entityliving.damageEntity(DamageSource.c(this, entityliving1), 6.0F);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Packet<?> P() {
/* 133 */     return new PacketPlayOutSpawnEntity(this);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityEvokerFangs.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */