/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.tuple.Pair;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ import org.bukkit.event.entity.EntityTransformEvent;
/*     */ 
/*     */ public class EntityMushroomCow
/*     */   extends EntityCow
/*     */   implements IShearable {
/*  14 */   private static final DataWatcherObject<String> bo = DataWatcher.a((Class)EntityMushroomCow.class, DataWatcherRegistry.d);
/*     */   private MobEffectList bp;
/*     */   private int bq;
/*     */   private UUID br;
/*     */   
/*     */   public EntityMushroomCow(EntityTypes<? extends EntityMushroomCow> entitytypes, World world) {
/*  20 */     super((EntityTypes)entitytypes, world);
/*     */   }
/*     */ 
/*     */   
/*     */   public float a(BlockPosition blockposition, IWorldReader iworldreader) {
/*  25 */     return iworldreader.getType(blockposition.down()).a(Blocks.MYCELIUM) ? 10.0F : (iworldreader.y(blockposition) - 0.5F);
/*     */   }
/*     */   
/*     */   public static boolean c(EntityTypes<EntityMushroomCow> entitytypes, GeneratorAccess generatoraccess, EnumMobSpawn enummobspawn, BlockPosition blockposition, Random random) {
/*  29 */     return (generatoraccess.getType(blockposition.down()).a(Blocks.MYCELIUM) && generatoraccess.getLightLevel(blockposition, 0) > 8);
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLightningStrike(WorldServer worldserver, EntityLightning entitylightning) {
/*  34 */     UUID uuid = entitylightning.getUniqueID();
/*     */     
/*  36 */     if (!uuid.equals(this.br)) {
/*  37 */       setVariant((getVariant() == Type.RED) ? Type.BROWN : Type.RED);
/*  38 */       this.br = uuid;
/*  39 */       playSound(SoundEffects.ENTITY_MOOSHROOM_CONVERT, 2.0F, 1.0F);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/*  46 */     super.initDatawatcher();
/*  47 */     this.datawatcher.register(bo, Type.RED.c);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult b(EntityHuman entityhuman, EnumHand enumhand) {
/*  52 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  54 */     if (itemstack.getItem() == Items.BOWL && !isBaby()) {
/*  55 */       ItemStack itemstack1; SoundEffect soundeffect; boolean flag = false;
/*     */ 
/*     */       
/*  58 */       if (this.bp != null) {
/*  59 */         flag = true;
/*  60 */         itemstack1 = new ItemStack(Items.SUSPICIOUS_STEW);
/*  61 */         ItemSuspiciousStew.a(itemstack1, this.bp, this.bq);
/*  62 */         this.bp = null;
/*  63 */         this.bq = 0;
/*     */       } else {
/*  65 */         itemstack1 = new ItemStack(Items.MUSHROOM_STEW);
/*     */       } 
/*     */       
/*  68 */       ItemStack itemstack2 = ItemLiquidUtil.a(itemstack, entityhuman, itemstack1, false);
/*     */       
/*  70 */       entityhuman.a(enumhand, itemstack2);
/*     */ 
/*     */       
/*  73 */       if (flag) {
/*  74 */         soundeffect = SoundEffects.ENTITY_MOOSHROOM_SUSPICIOUS_MILK;
/*     */       } else {
/*  76 */         soundeffect = SoundEffects.ENTITY_MOOSHROOM_MILK;
/*     */       } 
/*     */       
/*  79 */       playSound(soundeffect, 1.0F, 1.0F);
/*  80 */       return EnumInteractionResult.a(this.world.isClientSide);
/*  81 */     }  if (itemstack.getItem() == Items.SHEARS && canShear()) {
/*     */       
/*  83 */       if (!CraftEventFactory.handlePlayerShearEntityEvent(entityhuman, this, itemstack, enumhand)) {
/*  84 */         return EnumInteractionResult.PASS;
/*     */       }
/*     */       
/*  87 */       shear(SoundCategory.PLAYERS);
/*  88 */       if (!this.world.isClientSide) {
/*  89 */         itemstack.damage(1, entityhuman, entityhuman1 -> entityhuman1.broadcastItemBreak(enumhand));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*  94 */       return EnumInteractionResult.a(this.world.isClientSide);
/*  95 */     }  if (getVariant() == Type.BROWN && itemstack.getItem().a(TagsItem.SMALL_FLOWERS)) {
/*  96 */       if (this.bp != null) {
/*  97 */         for (int i = 0; i < 2; i++) {
/*  98 */           this.world.addParticle(Particles.SMOKE, locX() + this.random.nextDouble() / 2.0D, e(0.5D), locZ() + this.random.nextDouble() / 2.0D, 0.0D, this.random.nextDouble() / 5.0D, 0.0D);
/*     */         }
/*     */       } else {
/* 101 */         Optional<Pair<MobEffectList, Integer>> optional = l(itemstack);
/*     */         
/* 103 */         if (!optional.isPresent()) {
/* 104 */           return EnumInteractionResult.PASS;
/*     */         }
/*     */         
/* 107 */         Pair<MobEffectList, Integer> pair = optional.get();
/*     */         
/* 109 */         if (!entityhuman.abilities.canInstantlyBuild) {
/* 110 */           itemstack.subtract(1);
/*     */         }
/*     */         
/* 113 */         for (int j = 0; j < 4; j++) {
/* 114 */           this.world.addParticle(Particles.EFFECT, locX() + this.random.nextDouble() / 2.0D, e(0.5D), locZ() + this.random.nextDouble() / 2.0D, 0.0D, this.random.nextDouble() / 5.0D, 0.0D);
/*     */         }
/*     */         
/* 117 */         this.bp = (MobEffectList)pair.getLeft();
/* 118 */         this.bq = ((Integer)pair.getRight()).intValue();
/* 119 */         playSound(SoundEffects.ENTITY_MOOSHROOM_EAT, 2.0F, 1.0F);
/*     */       } 
/*     */       
/* 122 */       return EnumInteractionResult.a(this.world.isClientSide);
/*     */     } 
/* 124 */     return super.b(entityhuman, enumhand);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void shear(SoundCategory soundcategory) {
/* 130 */     this.world.playSound((EntityHuman)null, this, SoundEffects.ENTITY_MOOSHROOM_SHEAR, soundcategory, 1.0F, 1.0F);
/* 131 */     if (!this.world.s_()) {
/* 132 */       ((WorldServer)this.world).a(Particles.EXPLOSION, locX(), e(0.5D), locZ(), 1, 0.0D, 0.0D, 0.0D, 0.0D);
/*     */       
/* 134 */       EntityCow entitycow = EntityTypes.COW.a(this.world);
/*     */       
/* 136 */       entitycow.setPositionRotation(locX(), locY(), locZ(), this.yaw, this.pitch);
/* 137 */       entitycow.setHealth(getHealth());
/* 138 */       entitycow.aA = this.aA;
/* 139 */       if (hasCustomName()) {
/* 140 */         entitycow.setCustomName(getCustomName());
/* 141 */         entitycow.setCustomNameVisible(getCustomNameVisible());
/*     */       } 
/*     */       
/* 144 */       if (isPersistent()) {
/* 145 */         entitycow.setPersistent();
/*     */       }
/*     */       
/* 148 */       entitycow.setInvulnerable(isInvulnerable());
/*     */       
/* 150 */       if (CraftEventFactory.callEntityTransformEvent(this, entitycow, EntityTransformEvent.TransformReason.SHEARED).isCancelled()) {
/*     */         return;
/*     */       }
/* 153 */       this.world.addEntity(entitycow, CreatureSpawnEvent.SpawnReason.SHEARED);
/*     */       
/* 155 */       die();
/*     */ 
/*     */       
/* 158 */       for (int i = 0; i < 5; i++) {
/* 159 */         this.world.addEntity(new EntityItem(this.world, locX(), e(1.0D), locZ(), new ItemStack((getVariant()).d.getBlock())));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean canShear() {
/* 167 */     return (isAlive() && !isBaby());
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 172 */     super.saveData(nbttagcompound);
/* 173 */     nbttagcompound.setString("Type", (getVariant()).c);
/* 174 */     if (this.bp != null) {
/* 175 */       nbttagcompound.setByte("EffectId", (byte)MobEffectList.getId(this.bp));
/* 176 */       nbttagcompound.setInt("EffectDuration", this.bq);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 183 */     super.loadData(nbttagcompound);
/* 184 */     setVariant(Type.b(nbttagcompound.getString("Type")));
/* 185 */     if (nbttagcompound.hasKeyOfType("EffectId", 1)) {
/* 186 */       this.bp = MobEffectList.fromId(nbttagcompound.getByte("EffectId"));
/*     */     }
/*     */     
/* 189 */     if (nbttagcompound.hasKeyOfType("EffectDuration", 3)) {
/* 190 */       this.bq = nbttagcompound.getInt("EffectDuration");
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private Optional<Pair<MobEffectList, Integer>> l(ItemStack itemstack) {
/* 196 */     Item item = itemstack.getItem();
/*     */     
/* 198 */     if (item instanceof ItemBlock) {
/* 199 */       Block block = ((ItemBlock)item).getBlock();
/*     */       
/* 201 */       if (block instanceof BlockFlowers) {
/* 202 */         BlockFlowers blockflowers = (BlockFlowers)block;
/*     */         
/* 204 */         return Optional.of(Pair.of(blockflowers.c(), Integer.valueOf(blockflowers.d())));
/*     */       } 
/*     */     } 
/*     */     
/* 208 */     return Optional.empty();
/*     */   }
/*     */   
/*     */   public void setVariant(Type entitymushroomcow_type) {
/* 212 */     this.datawatcher.set(bo, entitymushroomcow_type.c);
/*     */   }
/*     */   
/*     */   public Type getVariant() {
/* 216 */     return Type.b(this.datawatcher.<String>get(bo));
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMushroomCow createChild(WorldServer worldserver, EntityAgeable entityageable) {
/* 221 */     EntityMushroomCow entitymushroomcow = EntityTypes.MOOSHROOM.a(worldserver);
/*     */     
/* 223 */     entitymushroomcow.setVariant(a((EntityMushroomCow)entityageable));
/* 224 */     return entitymushroomcow;
/*     */   }
/*     */   
/*     */   private Type a(EntityMushroomCow entitymushroomcow) {
/* 228 */     Type entitymushroomcow_type2, entitymushroomcow_type = getVariant();
/* 229 */     Type entitymushroomcow_type1 = entitymushroomcow.getVariant();
/*     */ 
/*     */     
/* 232 */     if (entitymushroomcow_type == entitymushroomcow_type1 && this.random.nextInt(1024) == 0) {
/* 233 */       entitymushroomcow_type2 = (entitymushroomcow_type == Type.BROWN) ? Type.RED : Type.BROWN;
/*     */     } else {
/* 235 */       entitymushroomcow_type2 = this.random.nextBoolean() ? entitymushroomcow_type : entitymushroomcow_type1;
/*     */     } 
/*     */     
/* 238 */     return entitymushroomcow_type2;
/*     */   }
/*     */   
/*     */   public enum Type
/*     */   {
/* 243 */     RED("red", Blocks.RED_MUSHROOM.getBlockData()), BROWN("brown", Blocks.BROWN_MUSHROOM.getBlockData());
/*     */     
/*     */     private final IBlockData d;
/*     */     private final String c;
/*     */     
/*     */     Type(String s, IBlockData iblockdata) {
/* 249 */       this.c = s;
/* 250 */       this.d = iblockdata;
/*     */     }
/*     */     
/*     */     private static Type b(String s) {
/* 254 */       Type[] aentitymushroomcow_type = values();
/* 255 */       int i = aentitymushroomcow_type.length;
/*     */       
/* 257 */       for (int j = 0; j < i; j++) {
/* 258 */         Type entitymushroomcow_type = aentitymushroomcow_type[j];
/*     */         
/* 260 */         if (entitymushroomcow_type.c.equals(s)) {
/* 261 */           return entitymushroomcow_type;
/*     */         }
/*     */       } 
/*     */       
/* 265 */       return RED;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityMushroomCow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */