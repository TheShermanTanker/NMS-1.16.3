/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ 
/*     */ public class EntityPufferFish extends EntityFish {
/*   9 */   private static final DataWatcherObject<Integer> b = DataWatcher.a((Class)EntityPufferFish.class, DataWatcherRegistry.b); private int c;
/*     */   
/*     */   static {
/*  12 */     bo = (entityliving -> (entityliving == null) ? false : (
/*  13 */       (entityliving instanceof EntityHuman && (entityliving.isSpectator() || ((EntityHuman)entityliving).isCreative())) ? false : ((entityliving.getMonsterType() != EnumMonsterType.WATER_MOB))));
/*     */   }
/*     */   private int d; private static final Predicate<EntityLiving> bo;
/*     */   public EntityPufferFish(EntityTypes<? extends EntityPufferFish> entitytypes, World world) {
/*  17 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  22 */     super.initDatawatcher();
/*  23 */     this.datawatcher.register(b, Integer.valueOf(0));
/*     */   }
/*     */   
/*     */   public int getPuffState() {
/*  27 */     return ((Integer)this.datawatcher.<Integer>get(b)).intValue();
/*     */   }
/*     */   
/*     */   public void setPuffState(int i) {
/*  31 */     this.datawatcher.set(b, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/*  36 */     if (b.equals(datawatcherobject)) {
/*  37 */       updateSize();
/*     */     }
/*     */     
/*  40 */     super.a(datawatcherobject);
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/*  45 */     super.saveData(nbttagcompound);
/*  46 */     nbttagcompound.setInt("PuffState", getPuffState());
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/*  51 */     super.loadData(nbttagcompound);
/*  52 */     setPuffState(nbttagcompound.getInt("PuffState"));
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack eK() {
/*  57 */     return new ItemStack(Items.PUFFERFISH_BUCKET);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initPathfinder() {
/*  62 */     super.initPathfinder();
/*  63 */     this.goalSelector.a(1, new a(this));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  68 */     if (!this.world.isClientSide && isAlive() && doAITick()) {
/*  69 */       if (this.c > 0) {
/*  70 */         if (getPuffState() == 0) {
/*  71 */           playSound(SoundEffects.ENTITY_PUFFER_FISH_BLOW_UP, getSoundVolume(), dG());
/*  72 */           setPuffState(1);
/*  73 */         } else if (this.c > 40 && getPuffState() == 1) {
/*  74 */           playSound(SoundEffects.ENTITY_PUFFER_FISH_BLOW_UP, getSoundVolume(), dG());
/*  75 */           setPuffState(2);
/*     */         } 
/*     */         
/*  78 */         this.c++;
/*  79 */       } else if (getPuffState() != 0) {
/*  80 */         if (this.d > 60 && getPuffState() == 2) {
/*  81 */           playSound(SoundEffects.ENTITY_PUFFER_FISH_BLOW_OUT, getSoundVolume(), dG());
/*  82 */           setPuffState(1);
/*  83 */         } else if (this.d > 100 && getPuffState() == 1) {
/*  84 */           playSound(SoundEffects.ENTITY_PUFFER_FISH_BLOW_OUT, getSoundVolume(), dG());
/*  85 */           setPuffState(0);
/*     */         } 
/*     */         
/*  88 */         this.d++;
/*     */       } 
/*     */     }
/*     */     
/*  92 */     super.tick();
/*     */   }
/*     */ 
/*     */   
/*     */   public void movementTick() {
/*  97 */     super.movementTick();
/*  98 */     if (isAlive() && getPuffState() > 0) {
/*  99 */       List<EntityInsentient> list = (List)this.world.a((Class)EntityInsentient.class, getBoundingBox().g(0.3D), bo);
/* 100 */       Iterator<EntityInsentient> iterator = list.iterator();
/*     */       
/* 102 */       while (iterator.hasNext()) {
/* 103 */         EntityInsentient entityinsentient = iterator.next();
/*     */         
/* 105 */         if (entityinsentient.isAlive()) {
/* 106 */           a(entityinsentient);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(EntityInsentient entityinsentient) {
/* 114 */     int i = getPuffState();
/*     */     
/* 116 */     if (entityinsentient.damageEntity(DamageSource.mobAttack(this), (1 + i))) {
/* 117 */       entityinsentient.addEffect(new MobEffect(MobEffects.POISON, 60 * i, 0), EntityPotionEffectEvent.Cause.ATTACK);
/* 118 */       playSound(SoundEffects.ENTITY_PUFFER_FISH_STING, 1.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void pickup(EntityHuman entityhuman) {
/* 125 */     int i = getPuffState();
/*     */     
/* 127 */     if (entityhuman instanceof EntityPlayer && i > 0 && entityhuman.damageEntity(DamageSource.mobAttack(this), (1 + i))) {
/* 128 */       if (!isSilent()) {
/* 129 */         ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.j, 0.0F));
/*     */       }
/*     */       
/* 132 */       entityhuman.addEffect(new MobEffect(MobEffects.POISON, 60 * i, 0), EntityPotionEffectEvent.Cause.ATTACK);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 139 */     return SoundEffects.ENTITY_PUFFER_FISH_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 144 */     return SoundEffects.ENTITY_PUFFER_FISH_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 149 */     return SoundEffects.ENTITY_PUFFER_FISH_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundFlop() {
/* 154 */     return SoundEffects.ENTITY_PUFFER_FISH_FLOP;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize a(EntityPose entitypose) {
/* 159 */     return super.a(entitypose).a(s(getPuffState()));
/*     */   }
/*     */   
/*     */   private static float s(int i) {
/* 163 */     switch (i) {
/*     */       case 0:
/* 165 */         return 0.5F;
/*     */       case 1:
/* 167 */         return 0.7F;
/*     */     } 
/* 169 */     return 1.0F;
/*     */   }
/*     */   
/*     */   static class a
/*     */     extends PathfinderGoal
/*     */   {
/*     */     private final EntityPufferFish a;
/*     */     
/*     */     public a(EntityPufferFish entitypufferfish) {
/* 178 */       this.a = entitypufferfish;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean a() {
/* 183 */       List<EntityLiving> list = this.a.world.a(EntityLiving.class, this.a.getBoundingBox().g(2.0D), EntityPufferFish.bo);
/*     */       
/* 185 */       return !list.isEmpty();
/*     */     }
/*     */ 
/*     */     
/*     */     public void c() {
/* 190 */       this.a.c = 1;
/* 191 */       this.a.d = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public void d() {
/* 196 */       this.a.c = 0;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 201 */       List<EntityLiving> list = this.a.world.a(EntityLiving.class, this.a.getBoundingBox().g(2.0D), EntityPufferFish.bo);
/*     */       
/* 203 */       return !list.isEmpty();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityPufferFish.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */