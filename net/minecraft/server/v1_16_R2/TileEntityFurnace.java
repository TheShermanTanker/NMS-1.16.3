/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.Maps;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.entity.CraftHumanEntity;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ import org.bukkit.entity.ExperienceOrb;
/*     */ import org.bukkit.entity.HumanEntity;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.inventory.FurnaceBurnEvent;
/*     */ import org.bukkit.event.inventory.FurnaceExtractEvent;
/*     */ import org.bukkit.event.inventory.FurnaceSmeltEvent;
/*     */ import org.bukkit.inventory.ItemStack;
/*     */ 
/*     */ public abstract class TileEntityFurnace extends TileEntityContainer implements IWorldInventory, RecipeHolder, AutoRecipeOutput, ITickable {
/*  26 */   private static final int[] g = new int[] { 0 };
/*  27 */   private static final int[] h = new int[] { 2, 1 };
/*  28 */   private static final int[] i = new int[] { 1 };
/*     */   protected NonNullList<ItemStack> items;
/*     */   public int burnTime;
/*     */   private int ticksForCurrentFuel;
/*  32 */   public double cookSpeedMultiplier = 1.0D; public int cookTime;
/*     */   public int cookTimeTotal;
/*     */   protected final IContainerProperties b;
/*     */   private final Object2IntOpenHashMap<MinecraftKey> n;
/*     */   protected final Recipes<? extends RecipeCooking> c;
/*     */   private int maxStack;
/*     */   public List<HumanEntity> transaction;
/*     */   
/*  40 */   protected TileEntityFurnace(TileEntityTypes<?> tileentitytypes, Recipes<? extends RecipeCooking> recipes) { super(tileentitytypes);
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
/* 153 */     this.maxStack = 64;
/* 154 */     this.transaction = new ArrayList<>(); this.items = NonNullList.a(3, ItemStack.b); this.b = new IContainerProperties() {
/*     */         public int getProperty(int i) { switch (i) { case 0: return TileEntityFurnace.this.burnTime;case 1: return TileEntityFurnace.this.ticksForCurrentFuel;case 2: return TileEntityFurnace.this.cookTime;case 3: return TileEntityFurnace.this.cookTimeTotal; }  return 0; }
/*     */         public void setProperty(int i, int j) { switch (i) { case 0: TileEntityFurnace.this.burnTime = j; break;case 1: TileEntityFurnace.this.ticksForCurrentFuel = j; break;case 2: TileEntityFurnace.this.cookTime = j; break;case 3: TileEntityFurnace.this.cookTimeTotal = j; break; }  } public int a() { return 4; }
/* 157 */       }; this.n = new Object2IntOpenHashMap(); this.c = recipes; } public List<ItemStack> getContents() { return this.items; }
/*     */   public static Map<Item, Integer> f() { Map<Item, Integer> map = Maps.newLinkedHashMap(); a(map, Items.LAVA_BUCKET, 20000); a(map, Blocks.COAL_BLOCK, 16000); a(map, Items.BLAZE_ROD, 2400); a(map, Items.COAL, 1600); a(map, Items.CHARCOAL, 1600); a(map, TagsItem.LOGS, 300); a(map, TagsItem.PLANKS, 300); a(map, TagsItem.WOODEN_STAIRS, 300); a(map, TagsItem.WOODEN_SLABS, 150); a(map, TagsItem.WOODEN_TRAPDOORS, 300); a(map, TagsItem.WOODEN_PRESSURE_PLATES, 300); a(map, Blocks.OAK_FENCE, 300); a(map, Blocks.BIRCH_FENCE, 300); a(map, Blocks.SPRUCE_FENCE, 300); a(map, Blocks.JUNGLE_FENCE, 300); a(map, Blocks.DARK_OAK_FENCE, 300); a(map, Blocks.ACACIA_FENCE, 300); a(map, Blocks.OAK_FENCE_GATE, 300); a(map, Blocks.BIRCH_FENCE_GATE, 300); a(map, Blocks.SPRUCE_FENCE_GATE, 300); a(map, Blocks.JUNGLE_FENCE_GATE, 300); a(map, Blocks.DARK_OAK_FENCE_GATE, 300); a(map, Blocks.ACACIA_FENCE_GATE, 300); a(map, Blocks.NOTE_BLOCK, 300); a(map, Blocks.BOOKSHELF, 300); a(map, Blocks.LECTERN, 300); a(map, Blocks.JUKEBOX, 300); a(map, Blocks.CHEST, 300); a(map, Blocks.TRAPPED_CHEST, 300); a(map, Blocks.CRAFTING_TABLE, 300); a(map, Blocks.DAYLIGHT_DETECTOR, 300); a(map, TagsItem.BANNERS, 300); a(map, Items.BOW, 300); a(map, Items.FISHING_ROD, 300); a(map, Blocks.LADDER, 300); a(map, TagsItem.SIGNS, 200); a(map, Items.WOODEN_SHOVEL, 200); a(map, Items.WOODEN_SWORD, 200); a(map, Items.WOODEN_HOE, 200); a(map, Items.WOODEN_AXE, 200); a(map, Items.WOODEN_PICKAXE, 200); a(map, TagsItem.WOODEN_DOORS, 200); a(map, TagsItem.BOATS, 1200); a(map, TagsItem.WOOL, 100); a(map, TagsItem.WOODEN_BUTTONS, 100); a(map, Items.STICK, 100); a(map, TagsItem.SAPLINGS, 100); a(map, Items.BOWL, 100); a(map, TagsItem.CARPETS, 67); a(map, Blocks.DRIED_KELP_BLOCK, 4001); a(map, Items.CROSSBOW, 300); a(map, Blocks.BAMBOO, 50); a(map, Blocks.DEAD_BUSH, 100); a(map, Blocks.SCAFFOLDING, 400); a(map, Blocks.LOOM, 300); a(map, Blocks.BARREL, 300); a(map, Blocks.CARTOGRAPHY_TABLE, 300); a(map, Blocks.FLETCHING_TABLE, 300);
/*     */     a(map, Blocks.SMITHING_TABLE, 300);
/*     */     a(map, Blocks.COMPOSTER, 300);
/* 161 */     return map; } public void onOpen(CraftHumanEntity who) { this.transaction.add(who); }
/*     */ 
/*     */   
/*     */   public void onClose(CraftHumanEntity who) {
/* 165 */     this.transaction.remove(who);
/*     */   }
/*     */   
/*     */   public List<HumanEntity> getViewers() {
/* 169 */     return this.transaction;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getMaxStackSize() {
/* 174 */     return this.maxStack;
/*     */   }
/*     */   
/*     */   public void setMaxStackSize(int size) {
/* 178 */     this.maxStack = size;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean b(Item item) {
/* 183 */     return TagsItem.NON_FLAMMABLE_WOOD.isTagged(item);
/*     */   }
/*     */   
/*     */   private static void a(Map<Item, Integer> map, Tag<Item> tag, int i) {
/* 187 */     Iterator<Item> iterator = tag.getTagged().iterator();
/*     */     
/* 189 */     while (iterator.hasNext()) {
/* 190 */       Item item = iterator.next();
/*     */       
/* 192 */       if (!b(item)) {
/* 193 */         map.put(item, Integer.valueOf(i));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static void a(Map<Item, Integer> map, IMaterial imaterial, int i) {
/* 200 */     Item item = imaterial.getItem();
/*     */     
/* 202 */     if (b(item)) {
/* 203 */       if (SharedConstants.d) {
/* 204 */         throw (IllegalStateException)SystemUtils.c(new IllegalStateException("A developer tried to explicitly make fire resistant item " + item.h((ItemStack)null).getString() + " a furnace fuel. That will not work!"));
/*     */       }
/*     */     } else {
/* 207 */       map.put(item, Integer.valueOf(i));
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean isBurning() {
/* 212 */     return (this.burnTime > 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData iblockdata, NBTTagCompound nbttagcompound) {
/* 217 */     super.load(iblockdata, nbttagcompound);
/* 218 */     this.items = NonNullList.a(getSize(), ItemStack.b);
/* 219 */     ContainerUtil.b(nbttagcompound, this.items);
/* 220 */     this.burnTime = nbttagcompound.getShort("BurnTime");
/* 221 */     this.cookTime = nbttagcompound.getShort("CookTime");
/* 222 */     this.cookTimeTotal = nbttagcompound.getShort("CookTimeTotal");
/* 223 */     this.ticksForCurrentFuel = fuelTime(this.items.get(1));
/* 224 */     NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("RecipesUsed");
/* 225 */     Iterator<String> iterator = nbttagcompound1.getKeys().iterator();
/*     */     
/* 227 */     while (iterator.hasNext()) {
/* 228 */       String s = iterator.next();
/*     */       
/* 230 */       this.n.put(new MinecraftKey(s), nbttagcompound1.getInt(s));
/*     */     } 
/*     */ 
/*     */     
/* 234 */     if (nbttagcompound.hasKey("Paper.CookSpeedMultiplier")) {
/* 235 */       this.cookSpeedMultiplier = nbttagcompound.getDouble("Paper.CookSpeedMultiplier");
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/* 242 */     super.save(nbttagcompound);
/* 243 */     nbttagcompound.setShort("BurnTime", (short)this.burnTime);
/* 244 */     nbttagcompound.setShort("CookTime", (short)this.cookTime);
/* 245 */     nbttagcompound.setShort("CookTimeTotal", (short)this.cookTimeTotal);
/* 246 */     nbttagcompound.setDouble("Paper.CookSpeedMultiplier", this.cookSpeedMultiplier);
/* 247 */     ContainerUtil.a(nbttagcompound, this.items);
/* 248 */     NBTTagCompound nbttagcompound1 = new NBTTagCompound();
/*     */     
/* 250 */     this.n.forEach((minecraftkey, integer) -> nbttagcompound1.setInt(minecraftkey.toString(), integer.intValue()));
/*     */ 
/*     */     
/* 253 */     nbttagcompound.set("RecipesUsed", nbttagcompound1);
/* 254 */     return nbttagcompound;
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/* 259 */     boolean flag = isBurning();
/* 260 */     boolean flag1 = false;
/*     */     
/* 262 */     if (isBurning()) {
/* 263 */       this.burnTime--;
/*     */     }
/*     */     
/* 266 */     if (!this.world.isClientSide) {
/* 267 */       ItemStack itemstack = this.items.get(1);
/*     */       
/* 269 */       if (!isBurning() && (itemstack.isEmpty() || ((ItemStack)this.items.get(0)).isEmpty())) {
/* 270 */         if (!isBurning() && this.cookTime > 0) {
/* 271 */           this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
/*     */         }
/*     */       } else {
/* 274 */         IRecipe<?> irecipe = this.world.getCraftingManager().<TileEntityFurnace, IRecipe>craft((Recipes)this.c, this, this.world).orElse(null);
/*     */         
/* 276 */         if (!isBurning() && canBurn(irecipe)) {
/*     */           
/* 278 */           CraftItemStack fuel = CraftItemStack.asCraftMirror(itemstack);
/*     */           
/* 280 */           FurnaceBurnEvent furnaceBurnEvent = new FurnaceBurnEvent((Block)CraftBlock.at(this.world, this.position), (ItemStack)fuel, fuelTime(itemstack));
/* 281 */           this.world.getServer().getPluginManager().callEvent((Event)furnaceBurnEvent);
/*     */           
/* 283 */           if (furnaceBurnEvent.isCancelled()) {
/*     */             return;
/*     */           }
/*     */           
/* 287 */           this.burnTime = furnaceBurnEvent.getBurnTime();
/* 288 */           this.ticksForCurrentFuel = this.burnTime;
/* 289 */           if (isBurning() && furnaceBurnEvent.isBurning()) {
/*     */             
/* 291 */             flag1 = true;
/* 292 */             if (!itemstack.isEmpty()) {
/* 293 */               Item item = itemstack.getItem();
/*     */               
/* 295 */               itemstack.subtract(1);
/* 296 */               if (itemstack.isEmpty()) {
/* 297 */                 Item item1 = item.getCraftingRemainingItem();
/*     */                 
/* 299 */                 this.items.set(1, (item1 == null) ? ItemStack.b : new ItemStack(item1));
/*     */               } 
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 305 */         if (isBurning() && canBurn(irecipe)) {
/* 306 */           this.cookTime = (int)(this.cookTime + this.cookSpeedMultiplier);
/* 307 */           if (this.cookTime >= this.cookTimeTotal) {
/* 308 */             this.cookTime = 0;
/* 309 */             this.cookTimeTotal = getRecipeCookingTime();
/* 310 */             burn(irecipe);
/* 311 */             flag1 = true;
/*     */           } 
/*     */         } else {
/* 314 */           this.cookTime = 0;
/*     */         } 
/*     */       } 
/*     */       
/* 318 */       if (flag != isBurning()) {
/* 319 */         flag1 = true;
/* 320 */         this.world.setTypeAndData(this.position, this.world.getType(this.position).set(BlockFurnace.LIT, Boolean.valueOf(isBurning())), 3);
/*     */       } 
/*     */     } 
/*     */     
/* 324 */     if (flag1) {
/* 325 */       update();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean canBurn(@Nullable IRecipe<?> irecipe) {
/* 331 */     if (!((ItemStack)this.items.get(0)).isEmpty() && irecipe != null) {
/* 332 */       ItemStack itemstack = irecipe.getResult();
/*     */       
/* 334 */       if (itemstack.isEmpty()) {
/* 335 */         return false;
/*     */       }
/* 337 */       ItemStack itemstack1 = this.items.get(2);
/*     */       
/* 339 */       return itemstack1.isEmpty() ? true : (!itemstack1.doMaterialsMatch(itemstack) ? false : ((itemstack1.getCount() < getMaxStackSize() && itemstack1.getCount() < itemstack1.getMaxStackSize()) ? true : ((itemstack1.getCount() < itemstack.getMaxStackSize()))));
/*     */     } 
/*     */     
/* 342 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private void burn(@Nullable IRecipe<?> irecipe) {
/* 347 */     if (irecipe != null && canBurn(irecipe)) {
/* 348 */       ItemStack itemstack = this.items.get(0);
/* 349 */       ItemStack itemstack1 = irecipe.getResult();
/* 350 */       ItemStack itemstack2 = this.items.get(2);
/*     */ 
/*     */       
/* 353 */       CraftItemStack source = CraftItemStack.asCraftMirror(itemstack);
/* 354 */       ItemStack result = CraftItemStack.asBukkitCopy(itemstack1);
/*     */       
/* 356 */       FurnaceSmeltEvent furnaceSmeltEvent = new FurnaceSmeltEvent(this.world.getWorld().getBlockAt(this.position.getX(), this.position.getY(), this.position.getZ()), (ItemStack)source, result);
/* 357 */       this.world.getServer().getPluginManager().callEvent((Event)furnaceSmeltEvent);
/*     */       
/* 359 */       if (furnaceSmeltEvent.isCancelled()) {
/*     */         return;
/*     */       }
/*     */       
/* 363 */       result = furnaceSmeltEvent.getResult();
/* 364 */       itemstack1 = CraftItemStack.asNMSCopy(result);
/*     */       
/* 366 */       if (!itemstack1.isEmpty()) {
/* 367 */         if (itemstack2.isEmpty()) {
/* 368 */           this.items.set(2, itemstack1.cloneItemStack());
/* 369 */         } else if (CraftItemStack.asCraftMirror(itemstack2).isSimilar(result)) {
/* 370 */           itemstack2.add(itemstack1.getCount());
/*     */         } else {
/*     */           return;
/*     */         } 
/*     */       }
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
/* 385 */       if (!this.world.isClientSide) {
/* 386 */         a(irecipe);
/*     */       }
/*     */       
/* 389 */       if (itemstack.getItem() == Blocks.WET_SPONGE.getItem() && !((ItemStack)this.items.get(1)).isEmpty() && ((ItemStack)this.items.get(1)).getItem() == Items.BUCKET) {
/* 390 */         this.items.set(1, new ItemStack(Items.WATER_BUCKET));
/*     */       }
/*     */       
/* 393 */       itemstack.subtract(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected int fuelTime(ItemStack itemstack) {
/* 398 */     if (itemstack.isEmpty()) {
/* 399 */       return 0;
/*     */     }
/* 401 */     Item item = itemstack.getItem();
/*     */     
/* 403 */     return ((Integer)f().getOrDefault(item, Integer.valueOf(0))).intValue();
/*     */   }
/*     */ 
/*     */   
/*     */   protected int getRecipeCookingTime() {
/* 408 */     return hasWorld() ? ((Integer)this.world.getCraftingManager().<TileEntityFurnace, RecipeCooking>craft(this.c, this, this.world).map(RecipeCooking::getCookingTime).orElse(Integer.valueOf(200))).intValue() : 200;
/*     */   }
/*     */   
/*     */   public static boolean isFuel(ItemStack itemstack) {
/* 412 */     return f().containsKey(itemstack.getItem());
/*     */   }
/*     */ 
/*     */   
/*     */   public int[] getSlotsForFace(EnumDirection enumdirection) {
/* 417 */     return (enumdirection == EnumDirection.DOWN) ? h : ((enumdirection == EnumDirection.UP) ? g : i);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlaceItemThroughFace(int i, ItemStack itemstack, @Nullable EnumDirection enumdirection) {
/* 422 */     return b(i, itemstack);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canTakeItemThroughFace(int i, ItemStack itemstack, EnumDirection enumdirection) {
/* 427 */     if (enumdirection == EnumDirection.DOWN && i == 1) {
/* 428 */       Item item = itemstack.getItem();
/*     */       
/* 430 */       if (item != Items.WATER_BUCKET && item != Items.BUCKET) {
/* 431 */         return false;
/*     */       }
/*     */     } 
/*     */     
/* 435 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getSize() {
/* 440 */     return this.items.size();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/*     */     ItemStack itemstack;
/* 445 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 450 */       if (!iterator.hasNext()) {
/* 451 */         return true;
/*     */       }
/*     */       
/* 454 */       itemstack = iterator.next();
/* 455 */     } while (itemstack.isEmpty());
/*     */     
/* 457 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack getItem(int i) {
/* 462 */     return this.items.get(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitStack(int i, int j) {
/* 467 */     return ContainerUtil.a(this.items, i, j);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack splitWithoutUpdate(int i) {
/* 472 */     return ContainerUtil.a(this.items, i);
/*     */   }
/*     */ 
/*     */   
/*     */   public void setItem(int i, ItemStack itemstack) {
/* 477 */     ItemStack itemstack1 = this.items.get(i);
/* 478 */     boolean flag = (!itemstack.isEmpty() && itemstack.doMaterialsMatch(itemstack1) && ItemStack.equals(itemstack, itemstack1));
/*     */     
/* 480 */     this.items.set(i, itemstack);
/* 481 */     if (itemstack.getCount() > getMaxStackSize()) {
/* 482 */       itemstack.setCount(getMaxStackSize());
/*     */     }
/*     */     
/* 485 */     if (i == 0 && !flag) {
/* 486 */       this.cookTimeTotal = getRecipeCookingTime();
/* 487 */       this.cookTime = 0;
/* 488 */       update();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a(EntityHuman entityhuman) {
/* 495 */     return (this.world.getTileEntity(this.position) != this) ? false : ((entityhuman.h(this.position.getX() + 0.5D, this.position.getY() + 0.5D, this.position.getZ() + 0.5D) <= 64.0D));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(int i, ItemStack itemstack) {
/* 500 */     if (i == 2)
/* 501 */       return false; 
/* 502 */     if (i != 1) {
/* 503 */       return true;
/*     */     }
/* 505 */     ItemStack itemstack1 = this.items.get(1);
/*     */     
/* 507 */     return (isFuel(itemstack) || (itemstack.getItem() == Items.BUCKET && itemstack1.getItem() != Items.BUCKET));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clear() {
/* 513 */     this.items.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(@Nullable IRecipe<?> irecipe) {
/* 518 */     if (irecipe != null) {
/* 519 */       MinecraftKey minecraftkey = irecipe.getKey();
/*     */       
/* 521 */       this.n.addTo(minecraftkey, 1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IRecipe<?> ak_() {
/* 529 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(EntityHuman entityhuman) {}
/*     */   
/*     */   public void d(EntityHuman entityhuman, ItemStack itemstack, int amount) {
/* 536 */     List<IRecipe<?>> list = a(entityhuman.world, entityhuman.getPositionVector(), entityhuman, itemstack, amount);
/*     */     
/* 538 */     entityhuman.discoverRecipes(list);
/* 539 */     this.n.clear();
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IRecipe<?>> a(World world, Vec3D vec3d) {
/* 544 */     return a(world, vec3d, (EntityHuman)null, (ItemStack)null, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   public List<IRecipe<?>> a(World world, Vec3D vec3d, EntityHuman entityhuman, ItemStack itemstack, int amount) {
/* 549 */     List<IRecipe<?>> list = Lists.newArrayList();
/* 550 */     ObjectIterator objectiterator = this.n.object2IntEntrySet().iterator();
/*     */     
/* 552 */     while (objectiterator.hasNext()) {
/* 553 */       Object2IntMap.Entry<MinecraftKey> entry = (Object2IntMap.Entry<MinecraftKey>)objectiterator.next();
/*     */       
/* 555 */       world.getCraftingManager().getRecipe((MinecraftKey)entry.getKey()).ifPresent(irecipe -> {
/*     */             list.add(irecipe);
/*     */             
/*     */             a(world, vec3d, entry.getIntValue(), ((RecipeCooking)irecipe).getExperience(), entityhuman, itemstack, amount);
/*     */           });
/*     */     } 
/* 561 */     return list;
/*     */   }
/*     */   
/*     */   private void a(World world, Vec3D vec3d, int i, float f, EntityHuman entityhuman, ItemStack itemstack, int amount) {
/* 565 */     int j = MathHelper.d(i * f);
/* 566 */     float f1 = MathHelper.h(i * f);
/*     */     
/* 568 */     if (f1 != 0.0F && Math.random() < f1) {
/* 569 */       j++;
/*     */     }
/*     */ 
/*     */     
/* 573 */     if (amount != 0) {
/* 574 */       FurnaceExtractEvent event = new FurnaceExtractEvent((Player)entityhuman.getBukkitEntity(), (Block)CraftBlock.at(world, this.position), CraftMagicNumbers.getMaterial(itemstack.getItem()), amount, j);
/* 575 */       world.getServer().getPluginManager().callEvent((Event)event);
/* 576 */       j = event.getExpToDrop();
/*     */     } 
/*     */ 
/*     */     
/* 580 */     while (j > 0) {
/* 581 */       int k = EntityExperienceOrb.getOrbValue(j);
/*     */       
/* 583 */       j -= k;
/* 584 */       world.addEntity(new EntityExperienceOrb(world, vec3d.x, vec3d.y, vec3d.z, k, ExperienceOrb.SpawnReason.FURNACE, entityhuman));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(AutoRecipeStackManager autorecipestackmanager) {
/* 591 */     Iterator<ItemStack> iterator = this.items.iterator();
/*     */     
/* 593 */     while (iterator.hasNext()) {
/* 594 */       ItemStack itemstack = iterator.next();
/*     */       
/* 596 */       autorecipestackmanager.b(itemstack);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityFurnace.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */