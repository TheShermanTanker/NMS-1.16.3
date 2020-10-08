/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftEquipmentSlot;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.entity.ArmorStand;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.entity.EntityDeathEvent;
/*     */ import org.bukkit.event.player.PlayerArmorStandManipulateEvent;
/*     */ import org.bukkit.inventory.EquipmentSlot;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public class EntityArmorStand extends EntityLiving {
/*  19 */   private static final Vector3f bj = new Vector3f(0.0F, 0.0F, 0.0F);
/*  20 */   private static final Vector3f bk = new Vector3f(0.0F, 0.0F, 0.0F);
/*  21 */   private static final Vector3f bl = new Vector3f(-10.0F, 0.0F, -10.0F);
/*  22 */   private static final Vector3f bm = new Vector3f(-15.0F, 0.0F, 10.0F);
/*  23 */   private static final Vector3f bn = new Vector3f(-1.0F, 0.0F, -1.0F);
/*  24 */   private static final Vector3f bo = new Vector3f(1.0F, 0.0F, 1.0F);
/*  25 */   private static final EntitySize bp = new EntitySize(0.0F, 0.0F, true);
/*  26 */   private static final EntitySize bq = EntityTypes.ARMOR_STAND.l().a(0.5F);
/*  27 */   public static final DataWatcherObject<Byte> b = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.a);
/*  28 */   public static final DataWatcherObject<Vector3f> c = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.k);
/*  29 */   public static final DataWatcherObject<Vector3f> d = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.k);
/*  30 */   public static final DataWatcherObject<Vector3f> e = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.k);
/*  31 */   public static final DataWatcherObject<Vector3f> f = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.k);
/*  32 */   public static final DataWatcherObject<Vector3f> g = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.k);
/*  33 */   public static final DataWatcherObject<Vector3f> bh = DataWatcher.a((Class)EntityArmorStand.class, DataWatcherRegistry.k); static {
/*  34 */     br = (entity -> 
/*  35 */       (entity instanceof EntityMinecartAbstract && ((EntityMinecartAbstract)entity).getMinecartType() == EntityMinecartAbstract.EnumMinecartType.RIDEABLE));
/*     */   }
/*     */   
/*     */   private static final Predicate<Entity> br;
/*     */   private final NonNullList<ItemStack> handItems;
/*     */   private final NonNullList<ItemStack> armorItems;
/*     */   private boolean armorStandInvisible;
/*     */   public long bi;
/*     */   public int disabledSlots;
/*     */   public Vector3f headPose;
/*     */   public Vector3f bodyPose;
/*     */   public Vector3f leftArmPose;
/*     */   public Vector3f rightArmPose;
/*     */   public Vector3f leftLegPose;
/*     */   public Vector3f rightLegPose;
/*     */   public boolean canMove = true;
/*     */   public boolean canTick = true;
/*     */   public boolean canTickSetByAPI = false;
/*     */   private boolean noTickPoseDirty = false;
/*     */   private boolean noTickEquipmentDirty = false;
/*     */   
/*     */   public EntityArmorStand(EntityTypes<? extends EntityArmorStand> entitytypes, World world) {
/*  57 */     super((EntityTypes)entitytypes, world);
/*  58 */     if (world != null) this.canTick = world.paperConfig.armorStandTick; 
/*  59 */     this.handItems = NonNullList.a(2, ItemStack.b);
/*  60 */     this.armorItems = NonNullList.a(4, ItemStack.b);
/*  61 */     this.headPose = bj;
/*  62 */     this.bodyPose = bk;
/*  63 */     this.leftArmPose = bl;
/*  64 */     this.rightArmPose = bm;
/*  65 */     this.leftLegPose = bn;
/*  66 */     this.rightLegPose = bo;
/*  67 */     this.G = 0.0F;
/*     */   }
/*     */   
/*     */   public EntityArmorStand(World world, double d0, double d1, double d2) {
/*  71 */     this(EntityTypes.ARMOR_STAND, world);
/*  72 */     setPosition(d0, d1, d2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public float getBukkitYaw() {
/*  78 */     return this.yaw;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void updateSize() {
/*  84 */     double d0 = locX();
/*  85 */     double d1 = locY();
/*  86 */     double d2 = locZ();
/*     */     
/*  88 */     super.updateSize();
/*  89 */     setPosition(d0, d1, d2);
/*     */   }
/*     */   
/*     */   private boolean A() {
/*  93 */     return (!isMarker() && !isNoGravity());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean doAITick() {
/*  98 */     return (super.doAITick() && A());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void initDatawatcher() {
/* 103 */     super.initDatawatcher();
/* 104 */     this.datawatcher.register(b, Byte.valueOf((byte)0));
/* 105 */     this.datawatcher.register(c, bj);
/* 106 */     this.datawatcher.register(d, bk);
/* 107 */     this.datawatcher.register(e, bl);
/* 108 */     this.datawatcher.register(f, bm);
/* 109 */     this.datawatcher.register(g, bn);
/* 110 */     this.datawatcher.register(bh, bo);
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterable<ItemStack> bm() {
/* 115 */     return this.handItems;
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterable<ItemStack> getArmorItems() {
/* 120 */     return this.armorItems;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getEquipment(EnumItemSlot enumitemslot) {
/* 125 */     switch (enumitemslot.a()) {
/*     */       case HAND:
/* 127 */         return this.handItems.get(enumitemslot.b());
/*     */       case ARMOR:
/* 129 */         return this.armorItems.get(enumitemslot.b());
/*     */     } 
/* 131 */     return ItemStack.b;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setSlot(EnumItemSlot enumitemslot, ItemStack itemstack) {
/* 137 */     switch (enumitemslot.a()) {
/*     */       case HAND:
/* 139 */         b(itemstack);
/* 140 */         this.handItems.set(enumitemslot.b(), itemstack);
/*     */         break;
/*     */       case ARMOR:
/* 143 */         b(itemstack);
/* 144 */         this.armorItems.set(enumitemslot.b(), itemstack);
/*     */         break;
/*     */     } 
/* 147 */     this.noTickEquipmentDirty = true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a_(int i, ItemStack itemstack) {
/*     */     EnumItemSlot enumitemslot;
/* 154 */     if (i == 98) {
/* 155 */       enumitemslot = EnumItemSlot.MAINHAND;
/* 156 */     } else if (i == 99) {
/* 157 */       enumitemslot = EnumItemSlot.OFFHAND;
/* 158 */     } else if (i == 100 + EnumItemSlot.HEAD.b()) {
/* 159 */       enumitemslot = EnumItemSlot.HEAD;
/* 160 */     } else if (i == 100 + EnumItemSlot.CHEST.b()) {
/* 161 */       enumitemslot = EnumItemSlot.CHEST;
/* 162 */     } else if (i == 100 + EnumItemSlot.LEGS.b()) {
/* 163 */       enumitemslot = EnumItemSlot.LEGS;
/*     */     } else {
/* 165 */       if (i != 100 + EnumItemSlot.FEET.b()) {
/* 166 */         return false;
/*     */       }
/*     */       
/* 169 */       enumitemslot = EnumItemSlot.FEET;
/*     */     } 
/*     */     
/* 172 */     if (!itemstack.isEmpty() && !EntityInsentient.c(enumitemslot, itemstack) && enumitemslot != EnumItemSlot.HEAD) {
/* 173 */       return false;
/*     */     }
/* 175 */     setSlot(enumitemslot, itemstack);
/* 176 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean e(ItemStack itemstack) {
/* 182 */     EnumItemSlot enumitemslot = EntityInsentient.j(itemstack);
/*     */     
/* 184 */     return (getEquipment(enumitemslot).isEmpty() && !d(enumitemslot));
/*     */   }
/*     */ 
/*     */   
/*     */   public void saveData(NBTTagCompound nbttagcompound) {
/* 189 */     super.saveData(nbttagcompound);
/* 190 */     NBTTagList nbttaglist = new NBTTagList();
/*     */ 
/*     */ 
/*     */     
/* 194 */     for (Iterator<ItemStack> iterator = this.armorItems.iterator(); iterator.hasNext(); nbttaglist.add(nbttagcompound1)) {
/* 195 */       ItemStack itemstack = iterator.next();
/*     */       
/* 197 */       NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/* 198 */       if (!itemstack.isEmpty()) {
/* 199 */         itemstack.save(nbttagcompound1);
/*     */       }
/*     */     } 
/*     */     
/* 203 */     nbttagcompound.set("ArmorItems", nbttaglist);
/* 204 */     NBTTagList nbttaglist1 = new NBTTagList();
/*     */ 
/*     */ 
/*     */     
/* 208 */     for (Iterator<ItemStack> iterator1 = this.handItems.iterator(); iterator1.hasNext(); nbttaglist1.add(nbttagcompound2)) {
/* 209 */       ItemStack itemstack1 = iterator1.next();
/*     */       
/* 211 */       NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/* 212 */       if (!itemstack1.isEmpty()) {
/* 213 */         itemstack1.save(nbttagcompound2);
/*     */       }
/*     */     } 
/*     */     
/* 217 */     nbttagcompound.set("HandItems", nbttaglist1);
/* 218 */     nbttagcompound.setBoolean("Invisible", isInvisible());
/* 219 */     nbttagcompound.setBoolean("Small", isSmall());
/* 220 */     nbttagcompound.setBoolean("ShowArms", hasArms());
/* 221 */     nbttagcompound.setInt("DisabledSlots", this.disabledSlots);
/* 222 */     nbttagcompound.setBoolean("NoBasePlate", hasBasePlate());
/* 223 */     if (isMarker()) {
/* 224 */       nbttagcompound.setBoolean("Marker", isMarker());
/*     */     }
/*     */     
/* 227 */     nbttagcompound.set("Pose", B());
/* 228 */     if (this.canTickSetByAPI) nbttagcompound.setBoolean("Paper.CanTickOverride", this.canTick);
/*     */   
/*     */   }
/*     */   
/*     */   public void loadData(NBTTagCompound nbttagcompound) {
/* 233 */     super.loadData(nbttagcompound);
/*     */ 
/*     */ 
/*     */     
/* 237 */     if (nbttagcompound.hasKeyOfType("ArmorItems", 9)) {
/* 238 */       NBTTagList nbttaglist = nbttagcompound.getList("ArmorItems", 10);
/*     */       
/* 240 */       for (int i = 0; i < this.armorItems.size(); i++) {
/* 241 */         this.armorItems.set(i, ItemStack.a(nbttaglist.getCompound(i)));
/*     */       }
/*     */     } 
/*     */     
/* 245 */     if (nbttagcompound.hasKeyOfType("HandItems", 9)) {
/* 246 */       NBTTagList nbttaglist = nbttagcompound.getList("HandItems", 10);
/*     */       
/* 248 */       for (int i = 0; i < this.handItems.size(); i++) {
/* 249 */         this.handItems.set(i, ItemStack.a(nbttaglist.getCompound(i)));
/*     */       }
/*     */     } 
/*     */     
/* 253 */     setInvisible(nbttagcompound.getBoolean("Invisible"));
/* 254 */     setSmall(nbttagcompound.getBoolean("Small"));
/* 255 */     setArms(nbttagcompound.getBoolean("ShowArms"));
/* 256 */     this.disabledSlots = nbttagcompound.getInt("DisabledSlots");
/* 257 */     setBasePlate(nbttagcompound.getBoolean("NoBasePlate"));
/* 258 */     setMarker(nbttagcompound.getBoolean("Marker"));
/* 259 */     this.noclip = !A();
/*     */     
/* 261 */     if (nbttagcompound.hasKey("Paper.CanTickOverride")) {
/* 262 */       this.canTick = nbttagcompound.getBoolean("Paper.CanTickOverride");
/* 263 */       this.canTickSetByAPI = true;
/*     */     } 
/*     */     
/* 266 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Pose");
/*     */     
/* 268 */     g(nbttagcompound1);
/*     */   }
/*     */   
/*     */   private void g(NBTTagCompound nbttagcompound) {
/* 272 */     NBTTagList nbttaglist = nbttagcompound.getList("Head", 5);
/*     */     
/* 274 */     setHeadPose(nbttaglist.isEmpty() ? bj : new Vector3f(nbttaglist));
/* 275 */     NBTTagList nbttaglist1 = nbttagcompound.getList("Body", 5);
/*     */     
/* 277 */     setBodyPose(nbttaglist1.isEmpty() ? bk : new Vector3f(nbttaglist1));
/* 278 */     NBTTagList nbttaglist2 = nbttagcompound.getList("LeftArm", 5);
/*     */     
/* 280 */     setLeftArmPose(nbttaglist2.isEmpty() ? bl : new Vector3f(nbttaglist2));
/* 281 */     NBTTagList nbttaglist3 = nbttagcompound.getList("RightArm", 5);
/*     */     
/* 283 */     setRightArmPose(nbttaglist3.isEmpty() ? bm : new Vector3f(nbttaglist3));
/* 284 */     NBTTagList nbttaglist4 = nbttagcompound.getList("LeftLeg", 5);
/*     */     
/* 286 */     setLeftLegPose(nbttaglist4.isEmpty() ? bn : new Vector3f(nbttaglist4));
/* 287 */     NBTTagList nbttaglist5 = nbttagcompound.getList("RightLeg", 5);
/*     */     
/* 289 */     setRightLegPose(nbttaglist5.isEmpty() ? bo : new Vector3f(nbttaglist5));
/*     */   }
/*     */   
/*     */   private NBTTagCompound B() {
/* 293 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */     
/* 295 */     if (!bj.equals(this.headPose)) {
/* 296 */       nbttagcompound.set("Head", this.headPose.a());
/*     */     }
/*     */     
/* 299 */     if (!bk.equals(this.bodyPose)) {
/* 300 */       nbttagcompound.set("Body", this.bodyPose.a());
/*     */     }
/*     */     
/* 303 */     if (!bl.equals(this.leftArmPose)) {
/* 304 */       nbttagcompound.set("LeftArm", this.leftArmPose.a());
/*     */     }
/*     */     
/* 307 */     if (!bm.equals(this.rightArmPose)) {
/* 308 */       nbttagcompound.set("RightArm", this.rightArmPose.a());
/*     */     }
/*     */     
/* 311 */     if (!bn.equals(this.leftLegPose)) {
/* 312 */       nbttagcompound.set("LeftLeg", this.leftLegPose.a());
/*     */     }
/*     */     
/* 315 */     if (!bo.equals(this.rightLegPose)) {
/* 316 */       nbttagcompound.set("RightLeg", this.rightLegPose.a());
/*     */     }
/*     */     
/* 319 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isCollidable() {
/* 324 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void C(Entity entity) {}
/*     */ 
/*     */   
/*     */   protected void collideNearby() {
/* 332 */     List<Entity> list = this.world.getEntities(this, getBoundingBox(), br);
/*     */     
/* 334 */     for (int i = 0; i < list.size(); i++) {
/* 335 */       Entity entity = list.get(i);
/*     */       
/* 337 */       if (h(entity) <= 0.2D) {
/* 338 */         entity.collide(this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityHuman entityhuman, Vec3D vec3d, EnumHand enumhand) {
/* 346 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/* 348 */     if (!isMarker() && itemstack.getItem() != Items.NAME_TAG) {
/* 349 */       if (entityhuman.isSpectator())
/* 350 */         return EnumInteractionResult.SUCCESS; 
/* 351 */       if (entityhuman.world.isClientSide) {
/* 352 */         return EnumInteractionResult.CONSUME;
/*     */       }
/* 354 */       EnumItemSlot enumitemslot = EntityInsentient.j(itemstack);
/*     */       
/* 356 */       if (itemstack.isEmpty()) {
/* 357 */         EnumItemSlot enumitemslot1 = i(vec3d);
/* 358 */         EnumItemSlot enumitemslot2 = d(enumitemslot1) ? enumitemslot : enumitemslot1;
/*     */         
/* 360 */         if (a(enumitemslot2) && a(entityhuman, enumitemslot2, itemstack, enumhand)) {
/* 361 */           return EnumInteractionResult.SUCCESS;
/*     */         }
/*     */       } else {
/* 364 */         if (d(enumitemslot)) {
/* 365 */           return EnumInteractionResult.FAIL;
/*     */         }
/*     */         
/* 368 */         if (enumitemslot.a() == EnumItemSlot.Function.HAND && !hasArms()) {
/* 369 */           return EnumInteractionResult.FAIL;
/*     */         }
/*     */         
/* 372 */         if (a(entityhuman, enumitemslot, itemstack, enumhand)) {
/* 373 */           return EnumInteractionResult.SUCCESS;
/*     */         }
/*     */       } 
/*     */       
/* 377 */       return EnumInteractionResult.PASS;
/*     */     } 
/*     */     
/* 380 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */   
/*     */   private EnumItemSlot i(Vec3D vec3d) {
/* 385 */     EnumItemSlot enumitemslot = EnumItemSlot.MAINHAND;
/* 386 */     boolean flag = isSmall();
/* 387 */     double d0 = flag ? (vec3d.y * 2.0D) : vec3d.y;
/* 388 */     EnumItemSlot enumitemslot1 = EnumItemSlot.FEET;
/*     */     
/* 390 */     if (d0 >= 0.1D && d0 < 0.1D + (flag ? 0.8D : 0.45D) && a(enumitemslot1)) {
/* 391 */       enumitemslot = EnumItemSlot.FEET;
/* 392 */     } else if (d0 >= 0.9D + (flag ? 0.3D : 0.0D) && d0 < 0.9D + (flag ? 1.0D : 0.7D) && a(EnumItemSlot.CHEST)) {
/* 393 */       enumitemslot = EnumItemSlot.CHEST;
/* 394 */     } else if (d0 >= 0.4D && d0 < 0.4D + (flag ? 1.0D : 0.8D) && a(EnumItemSlot.LEGS)) {
/* 395 */       enumitemslot = EnumItemSlot.LEGS;
/* 396 */     } else if (d0 >= 1.6D && a(EnumItemSlot.HEAD)) {
/* 397 */       enumitemslot = EnumItemSlot.HEAD;
/* 398 */     } else if (!a(EnumItemSlot.MAINHAND) && a(EnumItemSlot.OFFHAND)) {
/* 399 */       enumitemslot = EnumItemSlot.OFFHAND;
/*     */     } 
/*     */     
/* 402 */     return enumitemslot;
/*     */   }
/*     */   public final boolean isSlotDisabled(EnumItemSlot slot) {
/* 405 */     return d(slot);
/*     */   } private boolean d(EnumItemSlot enumitemslot) {
/* 407 */     return ((this.disabledSlots & 1 << enumitemslot.getSlotFlag()) != 0 || (enumitemslot.a() == EnumItemSlot.Function.HAND && !hasArms()));
/*     */   }
/*     */   
/*     */   private boolean a(EntityHuman entityhuman, EnumItemSlot enumitemslot, ItemStack itemstack, EnumHand enumhand) {
/* 411 */     ItemStack itemstack1 = getEquipment(enumitemslot);
/*     */     
/* 413 */     if (!itemstack1.isEmpty() && (this.disabledSlots & 1 << enumitemslot.getSlotFlag() + 8) != 0)
/* 414 */       return false; 
/* 415 */     if (itemstack1.isEmpty() && (this.disabledSlots & 1 << enumitemslot.getSlotFlag() + 16) != 0) {
/* 416 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 420 */     CraftItemStack craftItemStack1 = CraftItemStack.asCraftMirror(itemstack1);
/* 421 */     CraftItemStack craftItemStack2 = CraftItemStack.asCraftMirror(itemstack);
/*     */     
/* 423 */     Player player = (Player)entityhuman.getBukkitEntity();
/* 424 */     ArmorStand self = (ArmorStand)getBukkitEntity();
/*     */     
/* 426 */     EquipmentSlot slot = CraftEquipmentSlot.getSlot(enumitemslot);
/* 427 */     PlayerArmorStandManipulateEvent armorStandManipulateEvent = new PlayerArmorStandManipulateEvent(player, self, (ItemStack)craftItemStack2, (ItemStack)craftItemStack1, slot);
/* 428 */     this.world.getServer().getPluginManager().callEvent((Event)armorStandManipulateEvent);
/*     */     
/* 430 */     if (armorStandManipulateEvent.isCancelled()) {
/* 431 */       return true;
/*     */     }
/*     */ 
/*     */     
/* 435 */     if (entityhuman.abilities.canInstantlyBuild && itemstack1.isEmpty() && !itemstack.isEmpty()) {
/* 436 */       ItemStack itemstack2 = itemstack.cloneItemStack();
/* 437 */       itemstack2.setCount(1);
/* 438 */       setSlot(enumitemslot, itemstack2);
/* 439 */       return true;
/* 440 */     }  if (!itemstack.isEmpty() && itemstack.getCount() > 1) {
/* 441 */       if (!itemstack1.isEmpty()) {
/* 442 */         return false;
/*     */       }
/* 444 */       ItemStack itemstack2 = itemstack.cloneItemStack();
/* 445 */       itemstack2.setCount(1);
/* 446 */       setSlot(enumitemslot, itemstack2);
/* 447 */       itemstack.subtract(1);
/* 448 */       return true;
/*     */     } 
/*     */     
/* 451 */     setSlot(enumitemslot, itemstack);
/* 452 */     entityhuman.a(enumhand, itemstack1);
/* 453 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean damageEntity(DamageSource damagesource, float f) {
/* 460 */     if (!this.world.isClientSide && !this.dead) {
/* 461 */       if (DamageSource.OUT_OF_WORLD.equals(damagesource)) {
/*     */         
/* 463 */         if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f)) {
/* 464 */           return false;
/*     */         }
/*     */         
/* 467 */         killEntity();
/* 468 */         return false;
/* 469 */       }  if (!isInvulnerable(damagesource) && !isMarker()) {
/*     */         
/* 471 */         if (CraftEventFactory.handleNonLivingEntityDamageEvent(this, damagesource, f, true, this.armorStandInvisible)) {
/* 472 */           return false;
/*     */         }
/*     */         
/* 475 */         if (damagesource.isExplosion()) {
/* 476 */           g(damagesource);
/* 477 */           killEntity();
/* 478 */           return false;
/* 479 */         }  if (DamageSource.FIRE.equals(damagesource)) {
/* 480 */           if (isBurning()) {
/* 481 */             f(damagesource, 0.15F);
/*     */           } else {
/* 483 */             setOnFire(5);
/*     */           } 
/*     */           
/* 486 */           return false;
/* 487 */         }  if (DamageSource.BURN.equals(damagesource) && getHealth() > 0.5F) {
/* 488 */           f(damagesource, 4.0F);
/* 489 */           return false;
/*     */         } 
/* 491 */         boolean flag = damagesource.j() instanceof EntityArrow;
/* 492 */         boolean flag1 = (flag && ((EntityArrow)damagesource.j()).getPierceLevel() > 0);
/* 493 */         boolean flag2 = "player".equals(damagesource.q());
/*     */         
/* 495 */         if (!flag2 && !flag)
/* 496 */           return false; 
/* 497 */         if (damagesource.getEntity() instanceof EntityHuman && !((EntityHuman)damagesource.getEntity()).abilities.mayBuild)
/* 498 */           return false; 
/* 499 */         if (damagesource.v()) {
/* 500 */           F();
/* 501 */           D();
/* 502 */           killEntity();
/* 503 */           return flag1;
/*     */         } 
/* 505 */         long i = this.world.getTime();
/*     */         
/* 507 */         if (i - this.bi > 5L && !flag) {
/* 508 */           this.world.broadcastEntityEffect(this, (byte)32);
/* 509 */           this.bi = i;
/*     */         } else {
/* 511 */           f(damagesource);
/* 512 */           D();
/* 513 */           die();
/*     */         } 
/*     */         
/* 516 */         return true;
/*     */       } 
/*     */ 
/*     */       
/* 520 */       return false;
/*     */     } 
/*     */     
/* 523 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void D() {
/* 528 */     if (this.world instanceof WorldServer) {
/* 529 */       ((WorldServer)this.world).a(new ParticleParamBlock(Particles.BLOCK, Blocks.OAK_PLANKS.getBlockData()), locX(), e(0.6666666666666666D), locZ(), 10, (getWidth() / 4.0F), (getHeight() / 4.0F), (getWidth() / 4.0F), 0.05D);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void f(DamageSource damagesource, float f) {
/* 535 */     float f1 = getHealth();
/*     */     
/* 537 */     f1 -= f;
/* 538 */     if (f1 <= 0.5F) {
/* 539 */       g(damagesource);
/* 540 */       killEntity();
/*     */     } else {
/* 542 */       setHealth(f1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void f(DamageSource damagesource) {
/* 548 */     this.drops.add(CraftItemStack.asBukkitCopy(new ItemStack(Items.ARMOR_STAND)));
/* 549 */     g(damagesource);
/*     */   }
/*     */   
/*     */   private void g(DamageSource damagesource) {
/* 553 */     F();
/*     */ 
/*     */     
/*     */     int i;
/*     */ 
/*     */     
/* 559 */     for (i = 0; i < this.handItems.size(); i++) {
/* 560 */       ItemStack itemstack = this.handItems.get(i);
/* 561 */       if (!itemstack.isEmpty()) {
/* 562 */         this.drops.add(CraftItemStack.asCraftMirror(itemstack));
/* 563 */         this.handItems.set(i, ItemStack.b);
/*     */       } 
/*     */     } 
/*     */     
/* 567 */     for (i = 0; i < this.armorItems.size(); i++) {
/* 568 */       ItemStack itemstack = this.armorItems.get(i);
/* 569 */       if (!itemstack.isEmpty()) {
/* 570 */         this.drops.add(CraftItemStack.asCraftMirror(itemstack));
/* 571 */         this.armorItems.set(i, ItemStack.b);
/*     */       } 
/*     */     } 
/* 574 */     d(damagesource);
/*     */   }
/*     */ 
/*     */   
/*     */   private void F() {
/* 579 */     this.world.playSound((EntityHuman)null, locX(), locY(), locZ(), SoundEffects.ENTITY_ARMOR_STAND_BREAK, getSoundCategory(), 1.0F, 1.0F);
/*     */   }
/*     */ 
/*     */   
/*     */   protected float f(float f, float f1) {
/* 584 */     this.aB = this.lastYaw;
/* 585 */     this.aA = this.yaw;
/* 586 */     return 0.0F;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float b(EntityPose entitypose, EntitySize entitysize) {
/* 591 */     return entitysize.height * (isBaby() ? 0.5F : 0.9F);
/*     */   }
/*     */ 
/*     */   
/*     */   public double ba() {
/* 596 */     return isMarker() ? 0.0D : 0.10000000149011612D;
/*     */   }
/*     */ 
/*     */   
/*     */   public void g(Vec3D vec3d) {
/* 601 */     if (A()) {
/* 602 */       super.g(vec3d);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void n(float f) {
/* 608 */     this.aB = this.lastYaw = f;
/* 609 */     this.aD = this.aC = f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setHeadRotation(float f) {
/* 614 */     this.aB = this.lastYaw = f;
/* 615 */     this.aD = this.aC = f;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void tick() {
/* 621 */     if (!this.canTick) {
/* 622 */       if (this.noTickPoseDirty) {
/* 623 */         this.noTickPoseDirty = false;
/* 624 */         updatePose();
/*     */       } 
/*     */       
/* 627 */       if (this.noTickEquipmentDirty) {
/* 628 */         this.noTickEquipmentDirty = false;
/* 629 */         updateEntityEquipment();
/*     */       } 
/*     */ 
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/* 636 */     super.tick();
/*     */     
/* 638 */     updatePose();
/*     */   }
/*     */ 
/*     */   
/*     */   public void updatePose() {
/* 643 */     Vector3f vector3f = this.datawatcher.<Vector3f>get(c);
/*     */     
/* 645 */     if (!this.headPose.equals(vector3f)) {
/* 646 */       setHeadPose(vector3f);
/*     */     }
/*     */     
/* 649 */     Vector3f vector3f1 = this.datawatcher.<Vector3f>get(d);
/*     */     
/* 651 */     if (!this.bodyPose.equals(vector3f1)) {
/* 652 */       setBodyPose(vector3f1);
/*     */     }
/*     */     
/* 655 */     Vector3f vector3f2 = this.datawatcher.<Vector3f>get(e);
/*     */     
/* 657 */     if (!this.leftArmPose.equals(vector3f2)) {
/* 658 */       setLeftArmPose(vector3f2);
/*     */     }
/*     */     
/* 661 */     Vector3f vector3f3 = this.datawatcher.<Vector3f>get(f);
/*     */     
/* 663 */     if (!this.rightArmPose.equals(vector3f3)) {
/* 664 */       setRightArmPose(vector3f3);
/*     */     }
/*     */     
/* 667 */     Vector3f vector3f4 = this.datawatcher.<Vector3f>get(g);
/*     */     
/* 669 */     if (!this.leftLegPose.equals(vector3f4)) {
/* 670 */       setLeftLegPose(vector3f4);
/*     */     }
/*     */     
/* 673 */     Vector3f vector3f5 = this.datawatcher.<Vector3f>get(bh);
/*     */     
/* 675 */     if (!this.rightLegPose.equals(vector3f5)) {
/* 676 */       setRightLegPose(vector3f5);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void C() {
/* 683 */     setInvisible(this.armorStandInvisible);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setInvisible(boolean flag) {
/* 688 */     this.armorStandInvisible = flag;
/* 689 */     super.setInvisible(flag);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isBaby() {
/* 694 */     return isSmall();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean isDropExperience() {
/* 700 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void killEntity() {
/* 706 */     EntityDeathEvent event = CraftEventFactory.callEntityDeathEvent(this, this.drops);
/* 707 */     if (event.isCancelled())
/* 708 */       return;  die();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean ch() {
/* 713 */     return isInvisible();
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumPistonReaction getPushReaction() {
/* 718 */     return isMarker() ? EnumPistonReaction.IGNORE : super.getPushReaction();
/*     */   }
/*     */   
/*     */   public void setSmall(boolean flag) {
/* 722 */     this.datawatcher.set(b, Byte.valueOf(a(((Byte)this.datawatcher.<Byte>get(b)).byteValue(), 1, flag)));
/*     */   }
/*     */   
/*     */   public boolean isSmall() {
/* 726 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x1) != 0);
/*     */   }
/*     */   
/*     */   public void setArms(boolean flag) {
/* 730 */     this.datawatcher.set(b, Byte.valueOf(a(((Byte)this.datawatcher.<Byte>get(b)).byteValue(), 4, flag)));
/*     */   }
/*     */   
/*     */   public boolean hasArms() {
/* 734 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x4) != 0);
/*     */   }
/*     */   
/*     */   public void setBasePlate(boolean flag) {
/* 738 */     this.datawatcher.set(b, Byte.valueOf(a(((Byte)this.datawatcher.<Byte>get(b)).byteValue(), 8, flag)));
/*     */   }
/*     */   
/*     */   public boolean hasBasePlate() {
/* 742 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x8) != 0);
/*     */   }
/*     */   
/*     */   public void setMarker(boolean flag) {
/* 746 */     this.datawatcher.set(b, Byte.valueOf(a(((Byte)this.datawatcher.<Byte>get(b)).byteValue(), 16, flag)));
/*     */   }
/*     */   
/*     */   public boolean isMarker() {
/* 750 */     return ((((Byte)this.datawatcher.<Byte>get(b)).byteValue() & 0x10) != 0);
/*     */   }
/*     */   
/*     */   private byte a(byte b0, int i, boolean flag) {
/* 754 */     if (flag) {
/* 755 */       b0 = (byte)(b0 | i);
/*     */     } else {
/* 757 */       b0 = (byte)(b0 & (i ^ 0xFFFFFFFF));
/*     */     } 
/*     */     
/* 760 */     return b0;
/*     */   }
/*     */   
/*     */   public void setHeadPose(Vector3f vector3f) {
/* 764 */     this.headPose = vector3f;
/* 765 */     this.datawatcher.set(c, vector3f);
/* 766 */     this.noTickPoseDirty = true;
/*     */   }
/*     */   
/*     */   public void setBodyPose(Vector3f vector3f) {
/* 770 */     this.bodyPose = vector3f;
/* 771 */     this.datawatcher.set(d, vector3f);
/* 772 */     this.noTickPoseDirty = true;
/*     */   }
/*     */   
/*     */   public void setLeftArmPose(Vector3f vector3f) {
/* 776 */     this.leftArmPose = vector3f;
/* 777 */     this.datawatcher.set(e, vector3f);
/* 778 */     this.noTickPoseDirty = true;
/*     */   }
/*     */   
/*     */   public void setRightArmPose(Vector3f vector3f) {
/* 782 */     this.rightArmPose = vector3f;
/* 783 */     this.datawatcher.set(f, vector3f);
/* 784 */     this.noTickPoseDirty = true;
/*     */   }
/*     */   
/*     */   public void setLeftLegPose(Vector3f vector3f) {
/* 788 */     this.noTickPoseDirty = true;
/* 789 */     this.leftLegPose = vector3f;
/* 790 */     this.datawatcher.set(g, vector3f);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setRightLegPose(Vector3f vector3f) {
/* 795 */     this.noTickPoseDirty = true;
/* 796 */     this.rightLegPose = vector3f;
/* 797 */     this.datawatcher.set(bh, vector3f);
/*     */   }
/*     */   
/*     */   public Vector3f r() {
/* 801 */     return this.headPose;
/*     */   }
/*     */   
/*     */   public Vector3f t() {
/* 805 */     return this.bodyPose;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isInteractable() {
/* 810 */     return (super.isInteractable() && !isMarker());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean t(Entity entity) {
/* 815 */     return (entity instanceof EntityHuman && !this.world.a((EntityHuman)entity, getChunkCoordinates()));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumMainHand getMainHand() {
/* 820 */     return EnumMainHand.RIGHT;
/*     */   }
/*     */ 
/*     */   
/*     */   protected SoundEffect getSoundFall(int i) {
/* 825 */     return SoundEffects.ENTITY_ARMOR_STAND_FALL;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundHurt(DamageSource damagesource) {
/* 831 */     return SoundEffects.ENTITY_ARMOR_STAND_HIT;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   protected SoundEffect getSoundDeath() {
/* 837 */     return SoundEffects.ENTITY_ARMOR_STAND_BREAK;
/*     */   }
/*     */ 
/*     */   
/*     */   public void onLightningStrike(WorldServer worldserver, EntityLightning entitylightning) {}
/*     */ 
/*     */   
/*     */   public boolean eg() {
/* 845 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(DataWatcherObject<?> datawatcherobject) {
/* 850 */     if (b.equals(datawatcherobject)) {
/* 851 */       updateSize();
/* 852 */       this.i = !isMarker();
/*     */     } 
/*     */     
/* 855 */     super.a(datawatcherobject);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean eh() {
/* 860 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public EntitySize a(EntityPose entitypose) {
/* 865 */     return s(isMarker());
/*     */   }
/*     */   
/*     */   private EntitySize s(boolean flag) {
/* 869 */     return flag ? bp : (isBaby() ? bq : getEntityType().l());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void move(EnumMoveType moveType, Vec3D vec3d) {
/* 875 */     if (this.canMove) {
/* 876 */       super.move(moveType, vec3d);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canBreatheUnderwater() {
/* 882 */     return true;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EntityArmorStand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */