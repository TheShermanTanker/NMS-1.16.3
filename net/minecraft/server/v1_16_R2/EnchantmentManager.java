/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableFloat;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt;
/*     */ 
/*     */ public class EnchantmentManager
/*     */ {
/*     */   public static int getEnchantmentLevel(Enchantment enchantment, ItemStack itemstack) {
/*  19 */     if (itemstack.isEmpty()) {
/*  20 */       return 0;
/*     */     }
/*  22 */     MinecraftKey minecraftkey = IRegistry.ENCHANTMENT.getKey(enchantment);
/*  23 */     NBTTagList nbttaglist = itemstack.getEnchantments();
/*     */     
/*  25 */     for (int i = 0; i < nbttaglist.size(); i++) {
/*  26 */       NBTTagCompound nbttagcompound = nbttaglist.getCompound(i);
/*  27 */       MinecraftKey minecraftkey1 = MinecraftKey.a(nbttagcompound.getString("id"));
/*     */       
/*  29 */       if (minecraftkey1 != null && minecraftkey1.equals(minecraftkey)) {
/*  30 */         return MathHelper.clamp(nbttagcompound.getInt("lvl"), 0, 255);
/*     */       }
/*     */     } 
/*     */     
/*  34 */     return 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static Map<Enchantment, Integer> a(ItemStack itemstack) {
/*  39 */     NBTTagList nbttaglist = (itemstack.getItem() == Items.ENCHANTED_BOOK) ? ItemEnchantedBook.d(itemstack) : itemstack.getEnchantments();
/*     */     
/*  41 */     return a(nbttaglist);
/*     */   }
/*     */   
/*     */   public static Map<Enchantment, Integer> a(NBTTagList nbttaglist) {
/*  45 */     Map<Enchantment, Integer> map = Maps.newLinkedHashMap();
/*     */     
/*  47 */     for (int i = 0; i < nbttaglist.size(); i++) {
/*  48 */       NBTTagCompound nbttagcompound = nbttaglist.getCompound(i);
/*     */       
/*  50 */       IRegistry.ENCHANTMENT.getOptional(MinecraftKey.a(nbttagcompound.getString("id"))).ifPresent(enchantment -> {
/*     */             Integer integer = map.put(enchantment, Integer.valueOf(nbttagcompound.getInt("lvl")));
/*     */           });
/*     */     } 
/*     */     
/*  55 */     return map;
/*     */   }
/*     */   
/*     */   public static void a(Map<Enchantment, Integer> map, ItemStack itemstack) {
/*  59 */     NBTTagList nbttaglist = new NBTTagList();
/*  60 */     Iterator<Map.Entry<Enchantment, Integer>> iterator = map.entrySet().iterator();
/*     */     
/*  62 */     while (iterator.hasNext()) {
/*  63 */       Map.Entry<Enchantment, Integer> entry = iterator.next();
/*  64 */       Enchantment enchantment = entry.getKey();
/*     */       
/*  66 */       if (enchantment != null) {
/*  67 */         int i = ((Integer)entry.getValue()).intValue();
/*  68 */         NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */         
/*  70 */         nbttagcompound.setString("id", String.valueOf(IRegistry.ENCHANTMENT.getKey(enchantment)));
/*  71 */         nbttagcompound.setShort("lvl", (short)i);
/*  72 */         nbttaglist.add(nbttagcompound);
/*  73 */         if (itemstack.getItem() == Items.ENCHANTED_BOOK) {
/*  74 */           ItemEnchantedBook.a(itemstack, new WeightedRandomEnchant(enchantment, i));
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/*  79 */     if (nbttaglist.isEmpty()) {
/*  80 */       itemstack.removeTag("Enchantments");
/*  81 */     } else if (itemstack.getItem() != Items.ENCHANTED_BOOK) {
/*  82 */       itemstack.a("Enchantments", nbttaglist);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void a(a enchantmentmanager_a, ItemStack itemstack) {
/*  88 */     if (!itemstack.isEmpty()) {
/*  89 */       NBTTagList nbttaglist = itemstack.getEnchantments();
/*     */       
/*  91 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  92 */         String s = nbttaglist.getCompound(i).getString("id");
/*  93 */         int j = nbttaglist.getCompound(i).getInt("lvl");
/*     */         
/*  95 */         IRegistry.ENCHANTMENT.getOptional(MinecraftKey.a(s)).ifPresent(enchantment -> enchantmentmanager_a.accept(enchantment, j));
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(a enchantmentmanager_a, Iterable<ItemStack> iterable) {
/* 104 */     Iterator<ItemStack> iterator = iterable.iterator();
/*     */     
/* 106 */     while (iterator.hasNext()) {
/* 107 */       ItemStack itemstack = iterator.next();
/*     */       
/* 109 */       a(enchantmentmanager_a, itemstack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static int a(Iterable<ItemStack> iterable, DamageSource damagesource) {
/* 115 */     MutableInt mutableint = new MutableInt();
/*     */     
/* 117 */     a((enchantment, i) -> mutableint.add(enchantment.a(i, damagesource)), iterable);
/*     */ 
/*     */     
/* 120 */     return mutableint.intValue();
/*     */   }
/*     */   
/*     */   public static float a(ItemStack itemstack, EnumMonsterType enummonstertype) {
/* 124 */     MutableFloat mutablefloat = new MutableFloat();
/*     */     
/* 126 */     a((enchantment, i) -> mutablefloat.add(enchantment.a(i, enummonstertype)), itemstack);
/*     */ 
/*     */     
/* 129 */     return mutablefloat.floatValue();
/*     */   }
/*     */   
/*     */   public static float a(EntityLiving entityliving) {
/* 133 */     int i = a(Enchantments.SWEEPING, entityliving);
/*     */     
/* 135 */     return (i > 0) ? EnchantmentSweeping.e(i) : 0.0F;
/*     */   }
/*     */   
/*     */   public static void a(EntityLiving entityliving, Entity entity) {
/* 139 */     a enchantmentmanager_a = (enchantment, i) -> enchantment.b(entityliving, entity, i);
/*     */ 
/*     */ 
/*     */     
/* 143 */     if (entityliving != null) {
/* 144 */       a(enchantmentmanager_a, entityliving.bo());
/*     */     }
/*     */     
/* 147 */     if (entity instanceof EntityHuman) {
/* 148 */       a(enchantmentmanager_a, entityliving.getItemInMainHand());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void b(EntityLiving entityliving, Entity entity) {
/* 154 */     a enchantmentmanager_a = (enchantment, i) -> enchantment.a(entityliving, entity, i);
/*     */ 
/*     */ 
/*     */     
/* 158 */     if (entityliving != null) {
/* 159 */       a(enchantmentmanager_a, entityliving.bo());
/*     */     }
/*     */     
/* 162 */     if (entityliving instanceof EntityHuman) {
/* 163 */       a(enchantmentmanager_a, entityliving.getItemInMainHand());
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static int a(Enchantment enchantment, EntityLiving entityliving) {
/* 169 */     Iterable<ItemStack> iterable = enchantment.a(entityliving).values();
/*     */     
/* 171 */     if (iterable == null) {
/* 172 */       return 0;
/*     */     }
/* 174 */     int i = 0;
/* 175 */     Iterator<ItemStack> iterator = iterable.iterator();
/*     */     
/* 177 */     while (iterator.hasNext()) {
/* 178 */       ItemStack itemstack = iterator.next();
/* 179 */       int j = getEnchantmentLevel(enchantment, itemstack);
/*     */       
/* 181 */       if (j > i) {
/* 182 */         i = j;
/*     */       }
/*     */     } 
/*     */     
/* 186 */     return i;
/*     */   }
/*     */ 
/*     */   
/*     */   public static int b(EntityLiving entityliving) {
/* 191 */     return a(Enchantments.KNOCKBACK, entityliving);
/*     */   }
/*     */   
/*     */   public static int getFireAspectEnchantmentLevel(EntityLiving entityliving) {
/* 195 */     return a(Enchantments.FIRE_ASPECT, entityliving);
/*     */   }
/*     */   
/*     */   public static int getOxygenEnchantmentLevel(EntityLiving entityliving) {
/* 199 */     return a(Enchantments.OXYGEN, entityliving);
/*     */   }
/*     */   
/*     */   public static int e(EntityLiving entityliving) {
/* 203 */     return a(Enchantments.DEPTH_STRIDER, entityliving);
/*     */   }
/*     */   
/*     */   public static int getDigSpeedEnchantmentLevel(EntityLiving entityliving) {
/* 207 */     return a(Enchantments.DIG_SPEED, entityliving);
/*     */   }
/*     */   
/*     */   public static int b(ItemStack itemstack) {
/* 211 */     return getEnchantmentLevel(Enchantments.LUCK, itemstack);
/*     */   }
/*     */   
/*     */   public static int c(ItemStack itemstack) {
/* 215 */     return getEnchantmentLevel(Enchantments.LURE, itemstack);
/*     */   }
/*     */   
/*     */   public static int g(EntityLiving entityliving) {
/* 219 */     return a(Enchantments.LOOT_BONUS_MOBS, entityliving);
/*     */   }
/*     */   
/*     */   public static boolean h(EntityLiving entityliving) {
/* 223 */     return (a(Enchantments.WATER_WORKER, entityliving) > 0);
/*     */   }
/*     */   
/*     */   public static boolean i(EntityLiving entityliving) {
/* 227 */     return (a(Enchantments.FROST_WALKER, entityliving) > 0);
/*     */   }
/*     */   
/*     */   public static boolean j(EntityLiving entityliving) {
/* 231 */     return (a(Enchantments.SOUL_SPEED, entityliving) > 0);
/*     */   }
/*     */   
/*     */   public static boolean d(ItemStack itemstack) {
/* 235 */     return (getEnchantmentLevel(Enchantments.BINDING_CURSE, itemstack) > 0);
/*     */   }
/*     */   
/*     */   public static boolean shouldNotDrop(ItemStack itemstack) {
/* 239 */     return (getEnchantmentLevel(Enchantments.VANISHING_CURSE, itemstack) > 0);
/*     */   }
/*     */   
/*     */   public static int f(ItemStack itemstack) {
/* 243 */     return getEnchantmentLevel(Enchantments.LOYALTY, itemstack);
/*     */   }
/*     */   
/*     */   public static int g(ItemStack itemstack) {
/* 247 */     return getEnchantmentLevel(Enchantments.RIPTIDE, itemstack);
/*     */   }
/*     */   
/*     */   public static boolean h(ItemStack itemstack) {
/* 251 */     return (getEnchantmentLevel(Enchantments.CHANNELING, itemstack) > 0);
/*     */   }
/*     */   @Nonnull
/* 254 */   public static ItemStack getRandomEquippedItemWithEnchant(Enchantment enchantment, EntityLiving entityliving) { Map.Entry<EnumItemSlot, ItemStack> entry = b(enchantment, entityliving); return (entry != null) ? entry.getValue() : ItemStack.NULL_ITEM; } @Nullable
/*     */   public static Map.Entry<EnumItemSlot, ItemStack> b(Enchantment enchantment, EntityLiving entityliving) {
/* 256 */     return a(enchantment, entityliving, itemstack -> true);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static Map.Entry<EnumItemSlot, ItemStack> a(Enchantment enchantment, EntityLiving entityliving, Predicate<ItemStack> predicate) {
/* 263 */     Map<EnumItemSlot, ItemStack> map = enchantment.a(entityliving);
/*     */     
/* 265 */     if (map.isEmpty()) {
/* 266 */       return null;
/*     */     }
/* 268 */     List<Map.Entry<EnumItemSlot, ItemStack>> list = Lists.newArrayList();
/* 269 */     Iterator<Map.Entry<EnumItemSlot, ItemStack>> iterator = map.entrySet().iterator();
/*     */     
/* 271 */     while (iterator.hasNext()) {
/* 272 */       Map.Entry<EnumItemSlot, ItemStack> entry = iterator.next();
/* 273 */       ItemStack itemstack = entry.getValue();
/*     */       
/* 275 */       if (!itemstack.isEmpty() && getEnchantmentLevel(enchantment, itemstack) > 0 && predicate.test(itemstack)) {
/* 276 */         list.add(entry);
/*     */       }
/*     */     } 
/*     */     
/* 280 */     return list.isEmpty() ? null : list.get(entityliving.getRandom().nextInt(list.size()));
/*     */   }
/*     */ 
/*     */   
/*     */   public static int a(Random random, int i, int j, ItemStack itemstack) {
/* 285 */     Item item = itemstack.getItem();
/* 286 */     int k = item.c();
/*     */     
/* 288 */     if (k <= 0) {
/* 289 */       return 0;
/*     */     }
/* 291 */     if (j > 15) {
/* 292 */       j = 15;
/*     */     }
/*     */     
/* 295 */     int l = random.nextInt(8) + 1 + (j >> 1) + random.nextInt(j + 1);
/*     */     
/* 297 */     return (i == 0) ? Math.max(l / 3, 1) : ((i == 1) ? (l * 2 / 3 + 1) : Math.max(l, j * 2));
/*     */   }
/*     */ 
/*     */   
/*     */   public static ItemStack a(Random random, ItemStack itemstack, int i, boolean flag) {
/* 302 */     List<WeightedRandomEnchant> list = b(random, itemstack, i, flag);
/* 303 */     boolean flag1 = (itemstack.getItem() == Items.BOOK);
/*     */     
/* 305 */     if (flag1) {
/* 306 */       itemstack = new ItemStack(Items.ENCHANTED_BOOK);
/*     */     }
/*     */     
/* 309 */     Iterator<WeightedRandomEnchant> iterator = list.iterator();
/*     */     
/* 311 */     while (iterator.hasNext()) {
/* 312 */       WeightedRandomEnchant weightedrandomenchant = iterator.next();
/*     */       
/* 314 */       if (flag1) {
/* 315 */         ItemEnchantedBook.a(itemstack, weightedrandomenchant); continue;
/*     */       } 
/* 317 */       itemstack.addEnchantment(weightedrandomenchant.enchantment, weightedrandomenchant.level);
/*     */     } 
/*     */ 
/*     */     
/* 321 */     return itemstack;
/*     */   }
/*     */   
/*     */   public static List<WeightedRandomEnchant> b(Random random, ItemStack itemstack, int i, boolean flag) {
/* 325 */     List<WeightedRandomEnchant> list = Lists.newArrayList();
/* 326 */     Item item = itemstack.getItem();
/* 327 */     int j = item.c();
/*     */     
/* 329 */     if (j <= 0) {
/* 330 */       return list;
/*     */     }
/* 332 */     i += 1 + random.nextInt(j / 4 + 1) + random.nextInt(j / 4 + 1);
/* 333 */     float f = (random.nextFloat() + random.nextFloat() - 1.0F) * 0.15F;
/*     */     
/* 335 */     i = MathHelper.clamp(Math.round(i + i * f), 1, 2147483647);
/* 336 */     List<WeightedRandomEnchant> list1 = a(i, itemstack, flag);
/*     */     
/* 338 */     if (!list1.isEmpty()) {
/* 339 */       list.add(WeightedRandom.<WeightedRandomEnchant>a(random, list1));
/*     */       
/* 341 */       while (random.nextInt(50) <= i) {
/* 342 */         a(list1, SystemUtils.<WeightedRandomEnchant>a(list));
/* 343 */         if (list1.isEmpty()) {
/*     */           break;
/*     */         }
/*     */         
/* 347 */         list.add(WeightedRandom.<WeightedRandomEnchant>a(random, list1));
/* 348 */         i /= 2;
/*     */       } 
/*     */     } 
/*     */     
/* 352 */     return list;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(List<WeightedRandomEnchant> list, WeightedRandomEnchant weightedrandomenchant) {
/* 357 */     Iterator<WeightedRandomEnchant> iterator = list.iterator();
/*     */     
/* 359 */     while (iterator.hasNext()) {
/* 360 */       if (!weightedrandomenchant.enchantment.isCompatible(((WeightedRandomEnchant)iterator.next()).enchantment)) {
/* 361 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean a(Collection<Enchantment> collection, Enchantment enchantment) {
/*     */     Enchantment enchantment1;
/* 368 */     Iterator<Enchantment> iterator = collection.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 373 */       if (!iterator.hasNext()) {
/* 374 */         return true;
/*     */       }
/*     */       
/* 377 */       enchantment1 = iterator.next();
/* 378 */     } while (enchantment1.isCompatible(enchantment));
/*     */     
/* 380 */     return false;
/*     */   }
/*     */   
/*     */   public static List<WeightedRandomEnchant> a(int i, ItemStack itemstack, boolean flag) {
/* 384 */     List<WeightedRandomEnchant> list = Lists.newArrayList();
/* 385 */     Item item = itemstack.getItem();
/* 386 */     boolean flag1 = (itemstack.getItem() == Items.BOOK);
/* 387 */     Iterator<Enchantment> iterator = IRegistry.ENCHANTMENT.iterator();
/*     */     
/* 389 */     while (iterator.hasNext()) {
/* 390 */       Enchantment enchantment = iterator.next();
/*     */       
/* 392 */       if ((!enchantment.isTreasure() || flag) && enchantment.i() && (enchantment.itemTarget.canEnchant(item) || flag1)) {
/* 393 */         for (int j = enchantment.getMaxLevel(); j > enchantment.getStartLevel() - 1; j--) {
/* 394 */           if (i >= enchantment.a(j) && i <= enchantment.b(j)) {
/* 395 */             list.add(new WeightedRandomEnchant(enchantment, j));
/*     */             
/*     */             break;
/*     */           } 
/*     */         } 
/*     */       }
/*     */     } 
/* 402 */     return list;
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   static interface a {
/*     */     void accept(Enchantment param1Enchantment, int param1Int);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnchantmentManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */