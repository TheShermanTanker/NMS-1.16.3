/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.Set;
/*     */ import org.bukkit.event.entity.EntityPotionEffectEvent;
/*     */ 
/*     */ public class EntityTippedArrow extends EntityArrow {
/*  10 */   private static final DataWatcherObject<Integer> COLOR = DataWatcher.a((Class)EntityTippedArrow.class, DataWatcherRegistry.b);
/*     */   private PotionRegistry potionRegistry;
/*     */   public final Set<MobEffect> effects;
/*     */   private boolean hasColor;
/*     */   
/*     */   public EntityTippedArrow(EntityTypes<? extends EntityTippedArrow> entitytypes, World world) {
/*  16 */     super((EntityTypes)entitytypes, world);
/*  17 */     this.potionRegistry = Potions.EMPTY;
/*  18 */     this.effects = Sets.newHashSet();
/*     */   }
/*     */   
/*     */   public EntityTippedArrow(World world, double d0, double d1, double d2) {
/*  22 */     super((EntityTypes)EntityTypes.ARROW, d0, d1, d2, world);
/*  23 */     this.potionRegistry = Potions.EMPTY;
/*  24 */     this.effects = Sets.newHashSet();
/*     */   }
/*     */   
/*     */   public EntityTippedArrow(World world, EntityLiving entityliving) {
/*  28 */     super((EntityTypes)EntityTypes.ARROW, entityliving, world);
/*  29 */     this.potionRegistry = Potions.EMPTY;
/*  30 */     this.effects = Sets.newHashSet();
/*     */   }
/*     */   
/*     */   public void b(ItemStack itemstack) {
/*  34 */     if (itemstack.getItem() == Items.TIPPED_ARROW) {
/*  35 */       this.potionRegistry = PotionUtil.d(itemstack);
/*  36 */       Collection<MobEffect> collection = PotionUtil.b(itemstack);
/*     */       
/*  38 */       if (!collection.isEmpty()) {
/*  39 */         Iterator<MobEffect> iterator = collection.iterator();
/*     */         
/*  41 */         while (iterator.hasNext()) {
/*  42 */           MobEffect mobeffect = iterator.next();
/*     */           
/*  44 */           this.effects.add(new MobEffect(mobeffect));
/*     */         } 
/*     */       } 
/*     */       
/*  48 */       int i = c(itemstack);
/*     */       
/*  50 */       if (i == -1) {
/*  51 */         z();
/*     */       } else {
/*  53 */         setColor(i);
/*     */       } 
/*  55 */     } else if (itemstack.getItem() == Items.ARROW) {
/*  56 */       this.potionRegistry = Potions.EMPTY;
/*  57 */       this.effects.clear();
/*  58 */       this.datawatcher.set(COLOR, Integer.valueOf(-1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int c(ItemStack itemstack) {
/*  64 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/*  66 */     return (nbttagcompound != null && nbttagcompound.hasKeyOfType("CustomPotionColor", 99)) ? nbttagcompound.getInt("CustomPotionColor") : -1;
/*     */   }
/*     */   
/*     */   private void z() {
/*  70 */     this.hasColor = false;
/*  71 */     if (this.potionRegistry == Potions.EMPTY && this.effects.isEmpty()) {
/*  72 */       this.datawatcher.set(COLOR, Integer.valueOf(-1));
/*     */     } else {
/*  74 */       this.datawatcher.set(COLOR, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void addEffect(MobEffect mobeffect) {
/*  80 */     this.effects.add(mobeffect);
/*  81 */     getDataWatcher().set(COLOR, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  86 */     super.initDatawatcher();
/*  87 */     this.datawatcher.register(COLOR, Integer.valueOf(-1));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  92 */     super.tick();
/*  93 */     if (this.world.isClientSide) {
/*  94 */       if (this.inGround) {
/*  95 */         if (this.c % 5 == 0) {
/*  96 */           b(1);
/*     */         }
/*     */       } else {
/*  99 */         b(2);
/*     */       } 
/* 101 */     } else if (this.inGround && this.c != 0 && !this.effects.isEmpty() && this.c >= 600) {
/* 102 */       this.world.broadcastEntityEffect(this, (byte)0);
/* 103 */       this.potionRegistry = Potions.EMPTY;
/* 104 */       this.effects.clear();
/* 105 */       this.datawatcher.set(COLOR, Integer.valueOf(-1));
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void b(int i) {
/* 111 */     int j = getColor();
/*     */     
/* 113 */     if (j != -1 && i > 0) {
/* 114 */       double d0 = (j >> 16 & 0xFF) / 255.0D;
/* 115 */       double d1 = (j >> 8 & 0xFF) / 255.0D;
/* 116 */       double d2 = (j >> 0 & 0xFF) / 255.0D;
/*     */       
/* 118 */       for (int k = 0; k < i; k++) {
/* 119 */         this.world.addParticle(Particles.ENTITY_EFFECT, d(0.5D), cE(), g(0.5D), d0, d1, d2);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void refreshEffects() {
/* 127 */     getDataWatcher().set(COLOR, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */   }
/*     */   
/*     */   public String getType() {
/* 131 */     return IRegistry.POTION.getKey(this.potionRegistry).toString();
/*     */   }
/*     */   
/*     */   public void setType(String string) {
/* 135 */     this.potionRegistry = IRegistry.POTION.get(new MinecraftKey(string));
/* 136 */     getDataWatcher().set(COLOR, Integer.valueOf(PotionUtil.a(PotionUtil.a(this.potionRegistry, this.effects))));
/*     */   }
/*     */   
/*     */   public boolean isTipped() {
/* 140 */     return (!this.effects.isEmpty() || this.potionRegistry != Potions.EMPTY);
/*     */   }
/*     */ 
/*     */   
/*     */   public int getColor() {
/* 145 */     return ((Integer)this.datawatcher.<Integer>get(COLOR)).intValue();
/*     */   }
/*     */   
/*     */   public void setColor(int i) {
/* 149 */     this.hasColor = true;
/* 150 */     this.datawatcher.set(COLOR, Integer.valueOf(i));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 155 */     super.saveData(nbttagcompound);
/* 156 */     if (this.potionRegistry != Potions.EMPTY && this.potionRegistry != null) {
/* 157 */       nbttagcompound.setString("Potion", IRegistry.POTION.getKey(this.potionRegistry).toString());
/*     */     }
/*     */     
/* 160 */     if (this.hasColor) {
/* 161 */       nbttagcompound.setInt("Color", getColor());
/*     */     }
/*     */     
/* 164 */     if (!this.effects.isEmpty()) {
/* 165 */       NBTTagList nbttaglist = new NBTTagList();
/* 166 */       Iterator<MobEffect> iterator = this.effects.iterator();
/*     */       
/* 168 */       while (iterator.hasNext()) {
/* 169 */         MobEffect mobeffect = iterator.next();
/*     */         
/* 171 */         nbttaglist.add(mobeffect.a(new NBTTagCompound()));
/*     */       } 
/*     */       
/* 174 */       nbttagcompound.set("CustomPotionEffects", nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 181 */     super.loadData(nbttagcompound);
/* 182 */     if (nbttagcompound.hasKeyOfType("Potion", 8)) {
/* 183 */       this.potionRegistry = PotionUtil.c(nbttagcompound);
/*     */     }
/*     */     
/* 186 */     Iterator<MobEffect> iterator = PotionUtil.b(nbttagcompound).iterator();
/*     */     
/* 188 */     while (iterator.hasNext()) {
/* 189 */       MobEffect mobeffect = iterator.next();
/*     */       
/* 191 */       addEffect(mobeffect);
/*     */     } 
/*     */     
/* 194 */     if (nbttagcompound.hasKeyOfType("Color", 99)) {
/* 195 */       setColor(nbttagcompound.getInt("Color"));
/*     */     } else {
/* 197 */       z();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(EntityLiving entityliving) {
/* 204 */     super.a(entityliving);
/* 205 */     Iterator<MobEffect> iterator = this.potionRegistry.a().iterator();
/*     */ 
/*     */ 
/*     */     
/* 209 */     while (iterator.hasNext()) {
/* 210 */       MobEffect mobeffect = iterator.next();
/* 211 */       entityliving.addEffect(new MobEffect(mobeffect.getMobEffect(), Math.max(mobeffect.getDuration() / 8, 1), mobeffect.getAmplifier(), mobeffect.isAmbient(), mobeffect.isShowParticles()), EntityPotionEffectEvent.Cause.ARROW);
/*     */     } 
/*     */     
/* 214 */     if (!this.effects.isEmpty()) {
/* 215 */       iterator = this.effects.iterator();
/*     */       
/* 217 */       while (iterator.hasNext()) {
/* 218 */         MobEffect mobeffect = iterator.next();
/* 219 */         entityliving.addEffect(mobeffect, EntityPotionEffectEvent.Cause.ARROW);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected ItemStack getItemStack() {
/* 227 */     if (this.effects.isEmpty() && this.potionRegistry == Potions.EMPTY) {
/* 228 */       return new ItemStack(Items.ARROW);
/*     */     }
/* 230 */     ItemStack itemstack = new ItemStack(Items.TIPPED_ARROW);
/*     */     
/* 232 */     PotionUtil.a(itemstack, this.potionRegistry);
/* 233 */     PotionUtil.a(itemstack, this.effects);
/* 234 */     if (this.hasColor) {
/* 235 */       itemstack.getOrCreateTag().setInt("CustomPotionColor", getColor());
/*     */     }
/*     */     
/* 238 */     return itemstack;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityTippedArrow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */