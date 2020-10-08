/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class EntityHorse
/*     */   extends EntityHorseAbstract
/*     */ {
/*  37 */   private static final UUID bw = UUID.fromString("556E1665-8B10-40C8-8F9D-CF9B1667F295");
/*     */   
/*  39 */   private static final DataWatcherObject<Integer> bx = DataWatcher.a((Class)EntityHorse.class, DataWatcherRegistry.b);
/*     */   
/*     */   public EntityHorse(EntityTypes<? extends EntityHorse> var0, World var1) {
/*  42 */     super((EntityTypes)var0, var1);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void eK() {
/*  47 */     getAttributeInstance(GenericAttributes.MAX_HEALTH).setValue(fp());
/*  48 */     getAttributeInstance(GenericAttributes.MOVEMENT_SPEED).setValue(fr());
/*  49 */     getAttributeInstance(GenericAttributes.JUMP_STRENGTH).setValue(fq());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  54 */     super.initDatawatcher();
/*     */     
/*  56 */     this.datawatcher.register(bx, Integer.valueOf(0));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound var0) {
/*  61 */     super.saveData(var0);
/*     */     
/*  63 */     var0.setInt("Variant", getVariantRaw());
/*     */     
/*  65 */     if (!this.inventoryChest.getItem(1).isEmpty()) {
/*  66 */       var0.set("ArmorItem", this.inventoryChest.getItem(1).save(new NBTTagCompound()));
/*     */     }
/*     */   }
/*     */   
/*     */   public ItemStack eL() {
/*  71 */     return getEquipment(EnumItemSlot.CHEST);
/*     */   }
/*     */   
/*     */   private void m(ItemStack var0) {
/*  75 */     setSlot(EnumItemSlot.CHEST, var0);
/*  76 */     a(EnumItemSlot.CHEST, 0.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound var0) {
/*  81 */     super.loadData(var0);
/*     */     
/*  83 */     setVariantRaw(var0.getInt("Variant"));
/*     */     
/*  85 */     if (var0.hasKeyOfType("ArmorItem", 10)) {
/*  86 */       ItemStack var1 = ItemStack.a(var0.getCompound("ArmorItem"));
/*  87 */       if (!var1.isEmpty() && l(var1)) {
/*  88 */         this.inventoryChest.setItem(1, var1);
/*     */       }
/*     */     } 
/*     */     
/*  92 */     fe();
/*     */   }
/*     */   
/*     */   private void setVariantRaw(int var0) {
/*  96 */     this.datawatcher.set(bx, Integer.valueOf(var0));
/*     */   }
/*     */   
/*     */   private int getVariantRaw() {
/* 100 */     return ((Integer)this.datawatcher.<Integer>get(bx)).intValue();
/*     */   }
/*     */   
/*     */   public void setVariant(HorseColor var0, HorseStyle var1) {
/* 104 */     setVariantRaw(var0.a() & 0xFF | var1.a() << 8 & 0xFF00);
/*     */   }
/*     */   
/*     */   public HorseColor getColor() {
/* 108 */     return HorseColor.a(getVariantRaw() & 0xFF);
/*     */   }
/*     */   
/*     */   public HorseStyle getStyle() {
/* 112 */     return HorseStyle.a((getVariantRaw() & 0xFF00) >> 8);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void fe() {
/* 117 */     if (this.world.isClientSide) {
/*     */       return;
/*     */     }
/*     */     
/* 121 */     super.fe();
/*     */     
/* 123 */     n(this.inventoryChest.getItem(1));
/*     */     
/* 125 */     a(EnumItemSlot.CHEST, 0.0F);
/*     */   }
/*     */   
/*     */   private void n(ItemStack var0) {
/* 129 */     m(var0);
/*     */     
/* 131 */     if (!this.world.isClientSide) {
/* 132 */       getAttributeInstance(GenericAttributes.ARMOR).b(bw);
/* 133 */       if (l(var0)) {
/* 134 */         int var1 = ((ItemHorseArmor)var0.getItem()).g();
/* 135 */         if (var1 != 0) {
/* 136 */           getAttributeInstance(GenericAttributes.ARMOR).b(new AttributeModifier(bw, "Horse armor bonus", var1, AttributeModifier.Operation.ADDITION));
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(IInventory var0) {
/* 144 */     ItemStack var1 = eL();
/*     */     
/* 146 */     super.a(var0);
/*     */     
/* 148 */     ItemStack var2 = eL();
/* 149 */     if (this.ticksLived > 20 && l(var2) && var1 != var2) {
/* 150 */       playSound(SoundEffects.ENTITY_HORSE_ARMOR, 0.5F, 1.0F);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(SoundEffectType var0) {
/* 156 */     super.a(var0);
/* 157 */     if (this.random.nextInt(10) == 0) {
/* 158 */       playSound(SoundEffects.ENTITY_HORSE_BREATHE, var0.a() * 0.6F, var0.b());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAmbient() {
/* 164 */     super.getSoundAmbient();
/* 165 */     return SoundEffects.ENTITY_HORSE_AMBIENT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundDeath() {
/* 170 */     super.getSoundDeath();
/* 171 */     return SoundEffects.ENTITY_HORSE_DEATH;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect fg() {
/* 177 */     return SoundEffects.ENTITY_HORSE_EAT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundHurt(DamageSource var0) {
/* 182 */     super.getSoundHurt(var0);
/* 183 */     return SoundEffects.ENTITY_HORSE_HURT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundAngry() {
/* 188 */     super.getSoundAngry();
/* 189 */     return SoundEffects.ENTITY_HORSE_ANGRY;
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman var0, EnumHand var1) {
/* 194 */     ItemStack var2 = var0.b(var1);
/*     */     
/* 196 */     if (!isBaby()) {
/* 197 */       if (isTamed() && var0.ep()) {
/* 198 */         f(var0);
/* 199 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/*     */       
/* 202 */       if (isVehicle()) {
/* 203 */         return super.b(var0, var1);
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 208 */     if (!var2.isEmpty()) {
/* 209 */       if (k(var2)) {
/* 210 */         return b(var0, var2);
/*     */       }
/*     */       
/* 213 */       EnumInteractionResult var3 = var2.a(var0, this, var1);
/* 214 */       if (var3.a()) {
/* 215 */         return var3;
/*     */       }
/*     */       
/* 218 */       if (!isTamed()) {
/* 219 */         fm();
/* 220 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/*     */       
/* 223 */       boolean var4 = (!isBaby() && !hasSaddle() && var2.getItem() == Items.SADDLE);
/* 224 */       if (l(var2) || var4) {
/* 225 */         f(var0);
/* 226 */         return EnumInteractionResult.a(this.world.isClientSide);
/*     */       } 
/*     */     } 
/* 229 */     if (isBaby()) {
/* 230 */       return super.b(var0, var1);
/*     */     }
/*     */     
/* 233 */     h(var0);
/*     */     
/* 235 */     return EnumInteractionResult.a(this.world.isClientSide);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean mate(EntityAnimal var0) {
/* 240 */     if (var0 == this) {
/* 241 */       return false;
/*     */     }
/*     */     
/* 244 */     if (var0 instanceof EntityHorseDonkey || var0 instanceof EntityHorse) {
/* 245 */       return (fo() && ((EntityHorseAbstract)var0).fo());
/*     */     }
/*     */     
/* 248 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityAgeable createChild(WorldServer var0, EntityAgeable var1) {
/*     */     EntityHorseAbstract var2;
/* 254 */     if (var1 instanceof EntityHorseDonkey) {
/* 255 */       var2 = EntityTypes.MULE.a(var0);
/*     */     } else {
/* 257 */       HorseColor var4; HorseStyle var6; EntityHorse var3 = (EntityHorse)var1;
/*     */       
/* 259 */       var2 = EntityTypes.HORSE.a(var0);
/*     */       
/* 261 */       int var5 = this.random.nextInt(9);
/* 262 */       if (var5 < 4) {
/* 263 */         var4 = getColor();
/* 264 */       } else if (var5 < 8) {
/* 265 */         var4 = var3.getColor();
/*     */       } else {
/* 267 */         var4 = SystemUtils.<HorseColor>a(HorseColor.values(), this.random);
/*     */       } 
/*     */ 
/*     */       
/* 271 */       int var7 = this.random.nextInt(5);
/* 272 */       if (var7 < 2) {
/* 273 */         var6 = getStyle();
/* 274 */       } else if (var7 < 4) {
/* 275 */         var6 = var3.getStyle();
/*     */       } else {
/* 277 */         var6 = SystemUtils.<HorseStyle>a(HorseStyle.values(), this.random);
/*     */       } 
/*     */       
/* 280 */       ((EntityHorse)var2).setVariant(var4, var6);
/*     */     } 
/*     */     
/* 283 */     a(var1, var2);
/*     */     
/* 285 */     return var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean fs() {
/* 290 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean l(ItemStack var0) {
/* 295 */     return var0.getItem() instanceof ItemHorseArmor;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public GroupDataEntity prepare(WorldAccess var0, DifficultyDamageScaler var1, EnumMobSpawn var2, @Nullable GroupDataEntity var3, @Nullable NBTTagCompound var4) {
/*     */     HorseColor var5;
/* 302 */     if (var3 instanceof a) {
/* 303 */       var5 = ((a)var3).a;
/*     */     } else {
/* 305 */       var5 = SystemUtils.<HorseColor>a(HorseColor.values(), this.random);
/* 306 */       var3 = new a(var5);
/*     */     } 
/* 308 */     setVariant(var5, SystemUtils.<HorseStyle>a(HorseStyle.values(), this.random));
/*     */     
/* 310 */     return super.prepare(var0, var1, var2, var3, var4);
/*     */   }
/*     */   
/*     */   public static class a extends EntityAgeable.a {
/*     */     public final HorseColor a;
/*     */     
/*     */     public a(HorseColor var0) {
/* 317 */       super(true);
/* 318 */       this.a = var0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityHorse.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */