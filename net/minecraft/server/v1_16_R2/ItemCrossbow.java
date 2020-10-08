/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.entity.EntityShootBowEvent;
/*     */ 
/*     */ public class ItemCrossbow extends ItemProjectileWeapon implements ItemVanishable {
/*     */   private boolean c = false;
/*     */   
/*     */   public ItemCrossbow(Item.Info item_info) {
/*  14 */     super(item_info);
/*     */   }
/*     */   private boolean d = false;
/*     */   
/*     */   public Predicate<ItemStack> e() {
/*  19 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public Predicate<ItemStack> b() {
/*  24 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/*  29 */     ItemStack itemstack = entityhuman.b(enumhand);
/*     */     
/*  31 */     if (d(itemstack)) {
/*  32 */       a(world, entityhuman, enumhand, itemstack, m(itemstack), 1.0F);
/*  33 */       a(itemstack, false);
/*  34 */       return InteractionResultWrapper.consume(itemstack);
/*  35 */     }  if (!entityhuman.f(itemstack).isEmpty()) {
/*  36 */       if (!d(itemstack)) {
/*  37 */         this.c = false;
/*  38 */         this.d = false;
/*  39 */         entityhuman.c(enumhand);
/*     */       } 
/*     */       
/*  42 */       return InteractionResultWrapper.consume(itemstack);
/*     */     } 
/*  44 */     return InteractionResultWrapper.fail(itemstack);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(ItemStack itemstack, World world, EntityLiving entityliving, int i) {
/*  50 */     int j = e_(itemstack) - i;
/*  51 */     float f = a(j, itemstack);
/*     */     
/*  53 */     if (f >= 1.0F && !d(itemstack) && a(entityliving, itemstack)) {
/*  54 */       a(itemstack, true);
/*  55 */       SoundCategory soundcategory = (entityliving instanceof EntityHuman) ? SoundCategory.PLAYERS : SoundCategory.HOSTILE;
/*     */       
/*  57 */       world.playSound((EntityHuman)null, entityliving.locX(), entityliving.locY(), entityliving.locZ(), SoundEffects.ITEM_CROSSBOW_LOADING_END, soundcategory, 1.0F, 1.0F / (RANDOM.nextFloat() * 0.5F + 1.0F) + 0.2F);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(EntityLiving entityliving, ItemStack itemstack) {
/*  63 */     int i = EnchantmentManager.getEnchantmentLevel(Enchantments.MULTISHOT, itemstack);
/*  64 */     int j = (i == 0) ? 1 : 3;
/*  65 */     boolean flag = (entityliving instanceof EntityHuman && ((EntityHuman)entityliving).abilities.canInstantlyBuild);
/*  66 */     ItemStack itemstack1 = entityliving.f(itemstack);
/*  67 */     ItemStack itemstack2 = itemstack1.cloneItemStack();
/*     */     
/*  69 */     for (int k = 0; k < j; k++) {
/*  70 */       if (k > 0) {
/*  71 */         itemstack1 = itemstack2.cloneItemStack();
/*     */       }
/*     */       
/*  74 */       if (itemstack1.isEmpty() && flag) {
/*  75 */         itemstack1 = new ItemStack(Items.ARROW);
/*  76 */         itemstack2 = itemstack1.cloneItemStack();
/*     */       
/*     */       }
/*  79 */       else if (itemstack1.isEmpty()) {
/*  80 */         return false;
/*     */       } 
/*     */ 
/*     */       
/*  84 */       if (!a(entityliving, itemstack, itemstack1, (k > 0), flag)) {
/*  85 */         return false;
/*     */       }
/*     */     } 
/*     */     
/*  89 */     return true;
/*     */   }
/*     */   private static boolean a(EntityLiving entityliving, ItemStack itemstack, ItemStack itemstack1, boolean flag, boolean flag1) {
/*     */     ItemStack itemstack2;
/*  93 */     if (itemstack1.isEmpty()) {
/*  94 */       return false;
/*     */     }
/*  96 */     boolean flag2 = (flag1 && itemstack1.getItem() instanceof ItemArrow);
/*     */ 
/*     */     
/*  99 */     if (!flag2 && !flag1 && !flag) {
/* 100 */       itemstack2 = itemstack1.cloneAndSubtract(1);
/* 101 */       if (itemstack1.isEmpty() && entityliving instanceof EntityHuman) {
/* 102 */         ((EntityHuman)entityliving).inventory.f(itemstack1);
/*     */       }
/*     */     } else {
/* 105 */       itemstack2 = itemstack1.cloneItemStack();
/*     */     } 
/*     */     
/* 108 */     b(itemstack, itemstack2);
/* 109 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean d(ItemStack itemstack) {
/* 114 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 116 */     return (nbttagcompound != null && nbttagcompound.getBoolean("Charged"));
/*     */   }
/*     */   
/*     */   public static void a(ItemStack itemstack, boolean flag) {
/* 120 */     NBTTagCompound nbttagcompound = itemstack.getOrCreateTag();
/*     */     
/* 122 */     nbttagcompound.setBoolean("Charged", flag);
/*     */   }
/*     */   private static void b(ItemStack itemstack, ItemStack itemstack1) {
/*     */     NBTTagList nbttaglist;
/* 126 */     NBTTagCompound nbttagcompound = itemstack.getOrCreateTag();
/*     */ 
/*     */     
/* 129 */     if (nbttagcompound.hasKeyOfType("ChargedProjectiles", 9)) {
/* 130 */       nbttaglist = nbttagcompound.getList("ChargedProjectiles", 10);
/*     */     } else {
/* 132 */       nbttaglist = new NBTTagList();
/*     */     } 
/*     */     
/* 135 */     NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */     
/* 137 */     itemstack1.save(nbttagcompound1);
/* 138 */     nbttaglist.add(nbttagcompound1);
/* 139 */     nbttagcompound.set("ChargedProjectiles", nbttaglist);
/*     */   }
/*     */   
/*     */   private static List<ItemStack> k(ItemStack itemstack) {
/* 143 */     List<ItemStack> list = Lists.newArrayList();
/* 144 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 146 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("ChargedProjectiles", 9)) {
/* 147 */       NBTTagList nbttaglist = nbttagcompound.getList("ChargedProjectiles", 10);
/*     */       
/* 149 */       if (nbttaglist != null) {
/* 150 */         for (int i = 0; i < nbttaglist.size(); i++) {
/* 151 */           NBTTagCompound nbttagcompound1 = nbttaglist.getCompound(i);
/*     */           
/* 153 */           list.add(ItemStack.a(nbttagcompound1));
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 158 */     return list;
/*     */   }
/*     */   
/*     */   private static void l(ItemStack itemstack) {
/* 162 */     NBTTagCompound nbttagcompound = itemstack.getTag();
/*     */     
/* 164 */     if (nbttagcompound != null) {
/* 165 */       NBTTagList nbttaglist = nbttagcompound.getList("ChargedProjectiles", 9);
/*     */       
/* 167 */       nbttaglist.clear();
/* 168 */       nbttagcompound.set("ChargedProjectiles", nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(ItemStack itemstack, Item item) {
/* 174 */     return k(itemstack).stream().anyMatch(itemstack1 -> (itemstack1.getItem() == item));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(World world, EntityLiving entityliving, EnumHand enumhand, ItemStack itemstack, ItemStack itemstack1, float f, boolean flag, float f1, float f2, float f3) {
/* 180 */     if (!world.isClientSide) {
/* 181 */       Object object; boolean flag1 = (itemstack1.getItem() == Items.FIREWORK_ROCKET);
/*     */ 
/*     */       
/* 184 */       if (flag1) {
/* 185 */         object = new EntityFireworks(world, itemstack1, entityliving, entityliving.locX(), entityliving.getHeadY() - 0.15000000596046448D, entityliving.locZ(), true);
/* 186 */         ((EntityFireworks)object).spawningEntity = entityliving.getUniqueID();
/*     */       } else {
/* 188 */         object = a(world, entityliving, itemstack, itemstack1);
/* 189 */         if (flag || f3 != 0.0F) {
/* 190 */           ((EntityArrow)object).fromPlayer = EntityArrow.PickupStatus.CREATIVE_ONLY;
/*     */         }
/*     */       } 
/*     */       
/* 194 */       if (entityliving instanceof ICrossbow) {
/* 195 */         ICrossbow icrossbow = (ICrossbow)entityliving;
/*     */         
/* 197 */         icrossbow.a(icrossbow.getGoalTarget(), itemstack, (IProjectile)object, f3);
/*     */       } else {
/* 199 */         Vec3D vec3d = entityliving.i(1.0F);
/* 200 */         Quaternion quaternion = new Quaternion(new Vector3fa(vec3d), f3, true);
/* 201 */         Vec3D vec3d1 = entityliving.f(1.0F);
/* 202 */         Vector3fa vector3fa = new Vector3fa(vec3d1);
/*     */         
/* 204 */         vector3fa.a(quaternion);
/* 205 */         ((IProjectile)object).shoot(vector3fa.a(), vector3fa.b(), vector3fa.c(), f1, f2);
/*     */       } 
/*     */       
/* 208 */       EntityShootBowEvent event = CraftEventFactory.callEntityShootBowEvent(entityliving, itemstack, itemstack1, (Entity)object, entityliving.getRaisedHand(), f, true);
/* 209 */       if (event.isCancelled()) {
/* 210 */         event.getProjectile().remove();
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 215 */       itemstack.damage(flag1 ? 3 : 1, entityliving, entityliving1 -> entityliving1.broadcastItemBreak(enumhand));
/*     */ 
/*     */ 
/*     */       
/* 219 */       if (event.getProjectile() == ((Entity)object).getBukkitEntity() && 
/* 220 */         !world.addEntity((Entity)object)) {
/* 221 */         if (entityliving instanceof EntityPlayer) {
/* 222 */           ((EntityPlayer)entityliving).getBukkitEntity().updateInventory();
/*     */         }
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/* 228 */       world.playSound((EntityHuman)null, entityliving.locX(), entityliving.locY(), entityliving.locZ(), SoundEffects.ITEM_CROSSBOW_SHOOT, SoundCategory.PLAYERS, 1.0F, f);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static EntityArrow a(World world, EntityLiving entityliving, ItemStack itemstack, ItemStack itemstack1) {
/* 233 */     ItemArrow itemarrow = (itemstack1.getItem() instanceof ItemArrow) ? (ItemArrow)itemstack1.getItem() : (ItemArrow)Items.ARROW;
/* 234 */     EntityArrow entityarrow = itemarrow.a(world, itemstack1, entityliving);
/*     */     
/* 236 */     if (entityliving instanceof EntityHuman) {
/* 237 */       entityarrow.setCritical(true);
/*     */     }
/*     */     
/* 240 */     entityarrow.a(SoundEffects.ITEM_CROSSBOW_HIT);
/* 241 */     entityarrow.setShotFromCrossbow(true);
/* 242 */     int i = EnchantmentManager.getEnchantmentLevel(Enchantments.PIERCING, itemstack);
/*     */     
/* 244 */     if (i > 0) {
/* 245 */       entityarrow.setPierceLevel((byte)i);
/*     */     }
/*     */     
/* 248 */     return entityarrow;
/*     */   }
/*     */   
/*     */   public static void a(World world, EntityLiving entityliving, EnumHand enumhand, ItemStack itemstack, float f, float f1) {
/* 252 */     List<ItemStack> list = k(itemstack);
/* 253 */     float[] afloat = a(entityliving.getRandom());
/*     */     
/* 255 */     for (int i = 0; i < list.size(); i++) {
/* 256 */       ItemStack itemstack1 = list.get(i);
/* 257 */       boolean flag = (entityliving instanceof EntityHuman && ((EntityHuman)entityliving).abilities.canInstantlyBuild);
/*     */       
/* 259 */       if (!itemstack1.isEmpty()) {
/* 260 */         if (i == 0) {
/* 261 */           a(world, entityliving, enumhand, itemstack, itemstack1, afloat[i], flag, f, f1, 0.0F);
/* 262 */         } else if (i == 1) {
/* 263 */           a(world, entityliving, enumhand, itemstack, itemstack1, afloat[i], flag, f, f1, -10.0F);
/* 264 */         } else if (i == 2) {
/* 265 */           a(world, entityliving, enumhand, itemstack, itemstack1, afloat[i], flag, f, f1, 10.0F);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/* 270 */     a(world, entityliving, itemstack);
/*     */   }
/*     */   
/*     */   private static float[] a(Random random) {
/* 274 */     boolean flag = random.nextBoolean();
/*     */     
/* 276 */     return new float[] { 1.0F, a(flag), a(!flag) };
/*     */   }
/*     */   
/*     */   private static float a(boolean flag) {
/* 280 */     float f = flag ? 0.63F : 0.43F;
/*     */     
/* 282 */     return 1.0F / (RANDOM.nextFloat() * 0.5F + 1.8F) + f;
/*     */   }
/*     */   
/*     */   private static void a(World world, EntityLiving entityliving, ItemStack itemstack) {
/* 286 */     if (entityliving instanceof EntityPlayer) {
/* 287 */       EntityPlayer entityplayer = (EntityPlayer)entityliving;
/*     */       
/* 289 */       if (!world.isClientSide) {
/* 290 */         CriterionTriggers.F.a(entityplayer, itemstack);
/*     */       }
/*     */       
/* 293 */       entityplayer.b(StatisticList.ITEM_USED.b(itemstack.getItem()));
/*     */     } 
/*     */     
/* 296 */     l(itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(World world, EntityLiving entityliving, ItemStack itemstack, int i) {
/* 301 */     if (!world.isClientSide) {
/* 302 */       int j = EnchantmentManager.getEnchantmentLevel(Enchantments.QUICK_CHARGE, itemstack);
/* 303 */       SoundEffect soundeffect = a(j);
/* 304 */       SoundEffect soundeffect1 = (j == 0) ? SoundEffects.ITEM_CROSSBOW_LOADING_MIDDLE : null;
/* 305 */       float f = (itemstack.k() - i) / g(itemstack);
/*     */       
/* 307 */       if (f < 0.2F) {
/* 308 */         this.c = false;
/* 309 */         this.d = false;
/*     */       } 
/*     */       
/* 312 */       if (f >= 0.2F && !this.c) {
/* 313 */         this.c = true;
/* 314 */         world.playSound((EntityHuman)null, entityliving.locX(), entityliving.locY(), entityliving.locZ(), soundeffect, SoundCategory.PLAYERS, 0.5F, 1.0F);
/*     */       } 
/*     */       
/* 317 */       if (f >= 0.5F && soundeffect1 != null && !this.d) {
/* 318 */         this.d = true;
/* 319 */         world.playSound((EntityHuman)null, entityliving.locX(), entityliving.locY(), entityliving.locZ(), soundeffect1, SoundCategory.PLAYERS, 0.5F, 1.0F);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int e_(ItemStack itemstack) {
/* 327 */     return g(itemstack) + 3;
/*     */   }
/*     */   
/*     */   public static int g(ItemStack itemstack) {
/* 331 */     int i = EnchantmentManager.getEnchantmentLevel(Enchantments.QUICK_CHARGE, itemstack);
/*     */     
/* 333 */     return (i == 0) ? 25 : (25 - 5 * i);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumAnimation d_(ItemStack itemstack) {
/* 338 */     return EnumAnimation.CROSSBOW;
/*     */   }
/*     */   
/*     */   private SoundEffect a(int i) {
/* 342 */     switch (i) {
/*     */       case 1:
/* 344 */         return SoundEffects.ITEM_CROSSBOW_QUICK_CHARGE_1;
/*     */       case 2:
/* 346 */         return SoundEffects.ITEM_CROSSBOW_QUICK_CHARGE_2;
/*     */       case 3:
/* 348 */         return SoundEffects.ITEM_CROSSBOW_QUICK_CHARGE_3;
/*     */     } 
/* 350 */     return SoundEffects.ITEM_CROSSBOW_LOADING_START;
/*     */   }
/*     */ 
/*     */   
/*     */   private static float a(int i, ItemStack itemstack) {
/* 355 */     float f = i / g(itemstack);
/*     */     
/* 357 */     if (f > 1.0F) {
/* 358 */       f = 1.0F;
/*     */     }
/*     */     
/* 361 */     return f;
/*     */   }
/*     */   
/*     */   private static float m(ItemStack itemstack) {
/* 365 */     return (itemstack.getItem() == Items.CROSSBOW && a(itemstack, Items.FIREWORK_ROCKET)) ? 1.6F : 3.15F;
/*     */   }
/*     */ 
/*     */   
/*     */   public int d() {
/* 370 */     return 8;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemCrossbow.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */