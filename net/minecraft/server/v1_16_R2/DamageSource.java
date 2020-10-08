/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class DamageSource
/*     */ {
/*   7 */   public static final DamageSource FIRE = (new DamageSource("inFire")).setIgnoreArmor().setFire();
/*   8 */   public static final DamageSource LIGHTNING = new DamageSource("lightningBolt");
/*   9 */   public static final DamageSource BURN = (new DamageSource("onFire")).setIgnoreArmor().setFire();
/*  10 */   public static final DamageSource LAVA = (new DamageSource("lava")).setFire();
/*  11 */   public static final DamageSource HOT_FLOOR = (new DamageSource("hotFloor")).setFire();
/*  12 */   public static final DamageSource STUCK = (new DamageSource("inWall")).setIgnoreArmor();
/*  13 */   public static final DamageSource CRAMMING = (new DamageSource("cramming")).setIgnoreArmor();
/*  14 */   public static final DamageSource DROWN = (new DamageSource("drown")).setIgnoreArmor();
/*  15 */   public static final DamageSource STARVE = (new DamageSource("starve")).setIgnoreArmor().setStarvation();
/*  16 */   public static final DamageSource CACTUS = new DamageSource("cactus");
/*  17 */   public static final DamageSource FALL = (new DamageSource("fall")).setIgnoreArmor();
/*  18 */   public static final DamageSource FLY_INTO_WALL = (new DamageSource("flyIntoWall")).setIgnoreArmor();
/*  19 */   public static final DamageSource OUT_OF_WORLD = (new DamageSource("outOfWorld")).setIgnoreArmor().setIgnoresInvulnerability();
/*  20 */   public static final DamageSource GENERIC = (new DamageSource("generic")).setIgnoreArmor();
/*  21 */   public static final DamageSource MAGIC = (new DamageSource("magic")).setIgnoreArmor().setMagic();
/*  22 */   public static final DamageSource WITHER = (new DamageSource("wither")).setIgnoreArmor();
/*  23 */   public static final DamageSource ANVIL = new DamageSource("anvil");
/*  24 */   public static final DamageSource FALLING_BLOCK = new DamageSource("fallingBlock");
/*  25 */   public static final DamageSource DRAGON_BREATH = (new DamageSource("dragonBreath")).setIgnoreArmor();
/*  26 */   public static final DamageSource DRYOUT = new DamageSource("dryout");
/*  27 */   public static final DamageSource SWEET_BERRY_BUSH = new DamageSource("sweetBerryBush");
/*     */   private boolean w;
/*     */   private boolean x;
/*     */   private boolean y;
/*  31 */   private float z = 0.1F;
/*     */   
/*     */   private boolean A;
/*     */   private boolean B;
/*     */   private boolean C;
/*     */   private boolean D;
/*     */   private boolean E;
/*     */   public final String translationIndex;
/*     */   private boolean sweep;
/*     */   
/*     */   public boolean isSweep() {
/*  42 */     return this.sweep;
/*     */   }
/*     */   
/*     */   public DamageSource sweep() {
/*  46 */     this.sweep = true;
/*  47 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public static DamageSource b(EntityLiving entityliving) {
/*  52 */     return new EntityDamageSource("sting", entityliving);
/*     */   }
/*     */   
/*     */   public static DamageSource mobAttack(EntityLiving entityliving) {
/*  56 */     return new EntityDamageSource("mob", entityliving);
/*     */   }
/*     */   
/*     */   public static DamageSource a(Entity entity, EntityLiving entityliving) {
/*  60 */     return new EntityDamageSourceIndirect("mob", entity, entityliving);
/*     */   }
/*     */   
/*     */   public static DamageSource playerAttack(EntityHuman entityhuman) {
/*  64 */     return new EntityDamageSource("player", entityhuman);
/*     */   }
/*     */   
/*     */   public static DamageSource arrow(EntityArrow entityarrow, @Nullable Entity entity) {
/*  68 */     return (new EntityDamageSourceIndirect("arrow", entityarrow, entity)).c();
/*     */   }
/*     */   
/*     */   public static DamageSource a(Entity entity, @Nullable Entity entity1) {
/*  72 */     return (new EntityDamageSourceIndirect("trident", entity, entity1)).c();
/*     */   }
/*     */   
/*     */   public static DamageSource a(EntityFireworks entityfireworks, @Nullable Entity entity) {
/*  76 */     return (new EntityDamageSourceIndirect("fireworks", entityfireworks, entity)).setExplosion();
/*     */   }
/*     */   
/*     */   public static DamageSource fireball(EntityFireballFireball entityfireballfireball, @Nullable Entity entity) {
/*  80 */     return (entity == null) ? (new EntityDamageSourceIndirect("onFire", entityfireballfireball, entityfireballfireball)).setFire().c() : (new EntityDamageSourceIndirect("fireball", entityfireballfireball, entity)).setFire().c();
/*     */   }
/*     */   
/*     */   public static DamageSource a(EntityWitherSkull entitywitherskull, Entity entity) {
/*  84 */     return (new EntityDamageSourceIndirect("witherSkull", entitywitherskull, entity)).c();
/*     */   }
/*     */   
/*     */   public static DamageSource projectile(Entity entity, @Nullable Entity entity1) {
/*  88 */     return (new EntityDamageSourceIndirect("thrown", entity, entity1)).c();
/*     */   }
/*     */   
/*     */   public static DamageSource c(Entity entity, @Nullable Entity entity1) {
/*  92 */     return (new EntityDamageSourceIndirect("indirectMagic", entity, entity1)).setIgnoreArmor().setMagic();
/*     */   }
/*     */   
/*     */   public static DamageSource a(Entity entity) {
/*  96 */     return (new EntityDamageSource("thorns", entity)).x().setMagic();
/*     */   }
/*     */   
/*     */   public static DamageSource explosion(@Nullable Explosion explosion) {
/* 100 */     return d((explosion != null) ? explosion.getSource() : null);
/*     */   }
/*     */   
/*     */   public static DamageSource d(@Nullable EntityLiving entityliving) {
/* 104 */     return (entityliving != null) ? (new EntityDamageSource("explosion.player", entityliving)).r().setExplosion() : (new DamageSource("explosion")).r().setExplosion();
/*     */   }
/*     */   
/*     */   public static DamageSource a() {
/* 108 */     return new DamageSourceNetherBed();
/*     */   }
/*     */   
/*     */   public String toString() {
/* 112 */     return "DamageSource (" + this.translationIndex + ")";
/*     */   }
/*     */   
/*     */   public boolean b() {
/* 116 */     return this.B;
/*     */   }
/*     */   
/*     */   public DamageSource c() {
/* 120 */     this.B = true;
/* 121 */     return this;
/*     */   }
/*     */   
/*     */   public boolean isExplosion() {
/* 125 */     return this.E;
/*     */   }
/*     */   
/*     */   public DamageSource setExplosion() {
/* 129 */     this.E = true;
/* 130 */     return this;
/*     */   }
/*     */   
/*     */   public boolean ignoresArmor() {
/* 134 */     return this.w;
/*     */   }
/*     */   
/*     */   public float getExhaustionCost() {
/* 138 */     return this.z;
/*     */   }
/*     */   
/*     */   public boolean ignoresInvulnerability() {
/* 142 */     return this.x;
/*     */   }
/*     */   
/*     */   public boolean isStarvation() {
/* 146 */     return this.y;
/*     */   }
/*     */   
/*     */   protected DamageSource(String s) {
/* 150 */     this.translationIndex = s;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity j() {
/* 155 */     return getEntity();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Entity getEntity() {
/* 160 */     return null;
/*     */   }
/*     */   
/*     */   protected DamageSource setIgnoreArmor() {
/* 164 */     this.w = true;
/* 165 */     this.z = 0.0F;
/* 166 */     return this;
/*     */   }
/*     */   
/*     */   protected DamageSource setIgnoresInvulnerability() {
/* 170 */     this.x = true;
/* 171 */     return this;
/*     */   }
/*     */   
/*     */   protected DamageSource setStarvation() {
/* 175 */     this.y = true;
/* 176 */     this.z = 0.0F;
/* 177 */     return this;
/*     */   }
/*     */   
/*     */   protected DamageSource setFire() {
/* 181 */     this.A = true;
/* 182 */     return this;
/*     */   }
/*     */   
/*     */   public IChatBaseComponent getLocalizedDeathMessage(EntityLiving entityliving) {
/* 186 */     EntityLiving entityliving1 = entityliving.getKillingEntity();
/* 187 */     String s = "death.attack." + this.translationIndex;
/* 188 */     String s1 = s + ".player";
/*     */     
/* 190 */     return (entityliving1 != null) ? new ChatMessage(s1, new Object[] { entityliving.getScoreboardDisplayName(), entityliving1.getScoreboardDisplayName() }) : new ChatMessage(s, new Object[] { entityliving.getScoreboardDisplayName() });
/*     */   }
/*     */   
/*     */   public boolean isFire() {
/* 194 */     return this.A;
/*     */   }
/*     */   
/*     */   public String q() {
/* 198 */     return this.translationIndex;
/*     */   }
/*     */   
/*     */   public DamageSource r() {
/* 202 */     this.C = true;
/* 203 */     return this;
/*     */   }
/*     */   
/*     */   public boolean s() {
/* 207 */     return this.C;
/*     */   }
/*     */   
/*     */   public boolean isMagic() {
/* 211 */     return this.D;
/*     */   }
/*     */   
/*     */   public DamageSource setMagic() {
/* 215 */     this.D = true;
/* 216 */     return this;
/*     */   }
/*     */   
/*     */   public boolean v() {
/* 220 */     Entity entity = getEntity();
/*     */     
/* 222 */     return (entity instanceof EntityHuman && ((EntityHuman)entity).abilities.canInstantlyBuild);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Vec3D w() {
/* 227 */     return null;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DamageSource.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */