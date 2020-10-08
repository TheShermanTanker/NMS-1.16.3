/*      */ package net.minecraft.server.v1_16_R2;
/*      */ import com.google.common.collect.Multimap;
/*      */ import com.google.gson.JsonParseException;
/*      */ import com.mojang.brigadier.StringReader;
/*      */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*      */ import com.mojang.datafixers.kinds.App;
/*      */ import com.mojang.datafixers.kinds.Applicative;
/*      */ import com.mojang.datafixers.util.Function3;
/*      */ import com.mojang.serialization.Codec;
/*      */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*      */ import java.text.DecimalFormat;
/*      */ import java.text.DecimalFormatSymbols;
/*      */ import java.util.ArrayList;
/*      */ import java.util.Collection;
/*      */ import java.util.Comparator;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Objects;
/*      */ import java.util.Optional;
/*      */ import java.util.Random;
/*      */ import java.util.function.Consumer;
/*      */ import java.util.function.Predicate;
/*      */ import javax.annotation.Nullable;
/*      */ import org.bukkit.Bukkit;
/*      */ import org.bukkit.Location;
/*      */ import org.bukkit.TreeType;
/*      */ import org.bukkit.block.BlockState;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.inventory.CraftItemStack;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*      */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*      */ import org.bukkit.entity.Player;
/*      */ import org.bukkit.event.Event;
/*      */ import org.bukkit.event.block.BlockFertilizeEvent;
/*      */ import org.bukkit.event.block.BlockPlaceEvent;
/*      */ import org.bukkit.event.player.PlayerItemDamageEvent;
/*      */ import org.bukkit.event.world.StructureGrowEvent;
/*      */ 
/*      */ public final class ItemStack {
/*      */   static {
/*   44 */     a = RecordCodecBuilder.create(instance -> instance.group((App)IRegistry.ITEM.fieldOf("id").forGetter(()), (App)Codec.INT.fieldOf("Count").forGetter(()), (App)NBTTagCompound.a.optionalFieldOf("tag").forGetter(())).apply((Applicative)instance, ItemStack::new));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public static final Codec<ItemStack> a;
/*      */ 
/*      */   
/*   53 */   private static final Logger LOGGER = LogManager.getLogger(); public static final ItemStack b;
/*   54 */   public static final ItemStack NULL_ITEM = b = new ItemStack((Item)null); public static final DecimalFormat c; static {
/*   55 */     c = SystemUtils.<DecimalFormat>a(new DecimalFormat("#.##"), decimalformat -> decimalformat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));
/*      */   }
/*      */   
/*   58 */   private static final ChatModifier e = ChatModifier.a.setColor(EnumChatFormat.DARK_PURPLE).setItalic(Boolean.valueOf(true)); private int count; private int g;
/*      */   @Deprecated
/*      */   private Item item;
/*      */   NBTTagCompound tag;
/*      */   private boolean j;
/*      */   private Entity k;
/*      */   private ShapeDetectorBlock l;
/*      */   private boolean m;
/*      */   private ShapeDetectorBlock n;
/*      */   private boolean o;
/*      */   private static final Comparator<? super NBTTagCompound> enchantSorter;
/*      */   private CraftItemStack bukkitStack;
/*      */   
/*      */   static {
/*   72 */     enchantSorter = Comparator.comparing(o -> o.getString("id"));
/*      */   } private void processEnchantOrder(NBTTagCompound tag) {
/*   74 */     if (tag == null || !tag.hasKeyOfType("Enchantments", 9)) {
/*      */       return;
/*      */     }
/*   77 */     NBTTagList list = tag.getList("Enchantments", 10);
/*   78 */     if (list.size() < 2) {
/*      */       return;
/*      */     }
/*      */     
/*      */     try {
/*   83 */       list.sort((Comparator)enchantSorter);
/*   84 */     } catch (Exception exception) {}
/*      */   }
/*      */   
/*      */   private void processText() {
/*   88 */     NBTTagCompound display = getSubTag("display");
/*   89 */     if (display != null) {
/*   90 */       if (display.hasKeyOfType("Name", 8)) {
/*   91 */         String json = display.getString("Name");
/*   92 */         if (json != null && json.contains("§")) {
/*      */           try {
/*   94 */             display.set("Name", convert(json));
/*   95 */           } catch (JsonParseException jsonparseexception) {
/*   96 */             display.remove("Name");
/*      */           } 
/*      */         }
/*      */       } 
/*  100 */       if (display.hasKeyOfType("Lore", 9)) {
/*  101 */         NBTTagList list = display.getList("Lore", 8);
/*  102 */         for (int index = 0; index < list.size(); index++) {
/*  103 */           String json = list.getString(index);
/*  104 */           if (json != null && json.contains("§")) {
/*      */             try {
/*  106 */               list.set(index, convert(json));
/*  107 */             } catch (JsonParseException e) {
/*  108 */               list.set(index, NBTTagString.create(CraftChatMessage.toJSON(new ChatComponentText(""))));
/*      */             } 
/*      */           }
/*      */         } 
/*      */       } 
/*      */     } 
/*      */   }
/*      */   
/*      */   private NBTTagString convert(String json) {
/*  117 */     IChatBaseComponent component = IChatBaseComponent.ChatSerializer.jsonToComponent(json);
/*  118 */     if (component instanceof ChatComponentText && component.getText().contains("§") && component.getSiblings().isEmpty())
/*      */     {
/*  120 */       component = CraftChatMessage.fromString(component.getText())[0];
/*      */     }
/*  122 */     return NBTTagString.create(CraftChatMessage.toJSON(component));
/*      */   }
/*      */ 
/*      */   
/*      */   public ItemStack(IMaterial imaterial) {
/*  127 */     this(imaterial, 1);
/*      */   }
/*      */   
/*      */   private ItemStack(IMaterial imaterial, int i, Optional<NBTTagCompound> optional) {
/*  131 */     this(imaterial, i);
/*  132 */     optional.ifPresent(this::setTag);
/*      */   }
/*      */   
/*      */   public ItemStack(IMaterial imaterial, int i) {
/*  136 */     this.item = (imaterial == null) ? null : imaterial.getItem();
/*  137 */     this.count = i;
/*  138 */     if (this.item != null && this.item.usesDurability()) {
/*  139 */       setDamage(getDamage());
/*      */     }
/*      */     
/*  142 */     checkEmpty();
/*      */   }
/*      */ 
/*      */   
/*      */   public void convertStack(int version) {
/*  147 */     if (0 < version && version < CraftMagicNumbers.INSTANCE.getDataVersion()) {
/*  148 */       NBTTagCompound savedStack = new NBTTagCompound();
/*  149 */       save(savedStack);
/*  150 */       savedStack = (NBTTagCompound)(MinecraftServer.getServer()).dataConverterManager.update(DataConverterTypes.ITEM_STACK, new Dynamic(DynamicOpsNBT.a, savedStack), version, CraftMagicNumbers.INSTANCE.getDataVersion()).getValue();
/*  151 */       load(savedStack);
/*      */     } 
/*      */   }
/*      */   
/*      */   private void checkEmpty() {
/*  156 */     if (this.j && this == b) throw new AssertionError("TRAP"); 
/*  157 */     this.j = false;
/*  158 */     this.j = isEmpty();
/*      */   }
/*      */ 
/*      */   
/*      */   private void load(NBTTagCompound nbttagcompound) {
/*  163 */     this.item = IRegistry.ITEM.get(new MinecraftKey(nbttagcompound.getString("id")));
/*  164 */     this.count = nbttagcompound.getByte("Count");
/*  165 */     if (nbttagcompound.hasKeyOfType("tag", 10)) {
/*      */       
/*  167 */       this.tag = nbttagcompound.getCompound("tag").clone();
/*  168 */       processEnchantOrder(this.tag);
/*  169 */       processText();
/*  170 */       getItem().b(this.tag);
/*      */     } 
/*      */ 
/*      */     
/*  174 */     if (getItem().usesDurability()) {
/*  175 */       setDamage(getDamage());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   private ItemStack(NBTTagCompound nbttagcompound) {
/*  181 */     load(nbttagcompound);
/*      */     
/*  183 */     checkEmpty();
/*      */   }
/*      */   public static ItemStack fromCompound(NBTTagCompound nbttagcompound) {
/*  186 */     return a(nbttagcompound);
/*      */   } public static ItemStack a(NBTTagCompound nbttagcompound) {
/*      */     try {
/*  189 */       return new ItemStack(nbttagcompound);
/*  190 */     } catch (RuntimeException runtimeexception) {
/*  191 */       LOGGER.debug("Tried to load invalid item: {}", nbttagcompound, runtimeexception);
/*  192 */       return b;
/*      */     } 
/*      */   }
/*      */   
/*      */   public boolean isEmpty() {
/*  197 */     return (this == NULL_ITEM || this.item == null || this.item == Items.AIR || this.count <= 0);
/*      */   }
/*      */   
/*      */   public ItemStack cloneAndSubtract(int i) {
/*  201 */     int j = Math.min(i, this.count);
/*  202 */     ItemStack itemstack = cloneItemStack();
/*      */     
/*  204 */     itemstack.setCount(j);
/*  205 */     subtract(j);
/*  206 */     return itemstack;
/*      */   }
/*      */   
/*      */   public Item getItem() {
/*  210 */     return this.j ? Items.AIR : this.item;
/*      */   }
/*      */   
/*      */   public EnumInteractionResult placeItem(ItemActionContext itemactioncontext, EnumHand enumhand) {
/*  214 */     EntityHuman entityhuman = itemactioncontext.getEntity();
/*  215 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/*  216 */     ShapeDetectorBlock shapedetectorblock = new ShapeDetectorBlock(itemactioncontext.getWorld(), blockposition, false);
/*      */     
/*  218 */     if (entityhuman != null && !entityhuman.abilities.mayBuild && !b(itemactioncontext.getWorld().p(), shapedetectorblock)) {
/*  219 */       return EnumInteractionResult.PASS;
/*      */     }
/*      */     
/*  222 */     NBTTagCompound oldData = getTagClone();
/*  223 */     int oldCount = getCount();
/*  224 */     WorldServer world = (WorldServer)itemactioncontext.getWorld();
/*      */     
/*  226 */     if (!(getItem() instanceof ItemBucket)) {
/*  227 */       world.captureBlockStates = true;
/*      */       
/*  229 */       if (getItem() == Items.BONE_MEAL) {
/*  230 */         world.captureTreeGeneration = true;
/*      */       }
/*      */     } 
/*  233 */     Item item = getItem();
/*  234 */     EnumInteractionResult enuminteractionresult = item.a(itemactioncontext);
/*  235 */     NBTTagCompound newData = getTagClone();
/*  236 */     int newCount = getCount();
/*  237 */     setCount(oldCount);
/*  238 */     setTagClone(oldData);
/*  239 */     world.captureBlockStates = false;
/*  240 */     if (enuminteractionresult.a() && world.captureTreeGeneration && world.capturedBlockStates.size() > 0) {
/*  241 */       world.captureTreeGeneration = false;
/*  242 */       Location location = new Location((World)world.getWorld(), blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  243 */       TreeType treeType = BlockSapling.treeType;
/*  244 */       BlockSapling.treeType = null;
/*  245 */       List<BlockState> blocks = new ArrayList<>((Collection)world.capturedBlockStates.values());
/*  246 */       world.capturedBlockStates.clear();
/*  247 */       StructureGrowEvent structureEvent = null;
/*  248 */       if (treeType != null) {
/*  249 */         boolean isBonemeal = (getItem() == Items.BONE_MEAL);
/*  250 */         structureEvent = new StructureGrowEvent(location, treeType, isBonemeal, (Player)entityhuman.getBukkitEntity(), blocks);
/*  251 */         Bukkit.getPluginManager().callEvent((Event)structureEvent);
/*      */       } 
/*      */       
/*  254 */       BlockFertilizeEvent fertilizeEvent = new BlockFertilizeEvent((Block)CraftBlock.at(world, blockposition), (Player)entityhuman.getBukkitEntity(), blocks);
/*  255 */       fertilizeEvent.setCancelled((structureEvent != null && structureEvent.isCancelled()));
/*  256 */       Bukkit.getPluginManager().callEvent((Event)fertilizeEvent);
/*      */       
/*  258 */       if (!fertilizeEvent.isCancelled()) {
/*      */         
/*  260 */         if (getCount() == oldCount && Objects.equals(this.tag, oldData)) {
/*  261 */           setTag(newData);
/*  262 */           setCount(newCount);
/*      */         } 
/*  264 */         for (BlockState blockstate : blocks) {
/*  265 */           blockstate.update(true);
/*      */         }
/*      */       } 
/*      */       
/*  269 */       return enuminteractionresult;
/*      */     } 
/*  271 */     world.captureTreeGeneration = false;
/*      */     
/*  273 */     if (entityhuman != null && enuminteractionresult.a()) {
/*  274 */       BlockPlaceEvent placeEvent = null;
/*  275 */       List<BlockState> blocks = new ArrayList<>((Collection)world.capturedBlockStates.values());
/*  276 */       world.capturedBlockStates.clear();
/*  277 */       if (blocks.size() > 1) {
/*  278 */         BlockMultiPlaceEvent blockMultiPlaceEvent = CraftEventFactory.callBlockMultiPlaceEvent(world, entityhuman, enumhand, blocks, blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  279 */       } else if (blocks.size() == 1) {
/*  280 */         placeEvent = CraftEventFactory.callBlockPlaceEvent(world, entityhuman, enumhand, blocks.get(0), blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*      */       } 
/*      */       
/*  283 */       if (placeEvent != null && (placeEvent.isCancelled() || !placeEvent.canBuild())) {
/*  284 */         enuminteractionresult = EnumInteractionResult.FAIL;
/*      */         
/*  286 */         placeEvent.getPlayer().updateInventory();
/*  287 */         world.capturedTileEntities.clear();
/*      */         
/*  289 */         for (BlockState blockstate : blocks) {
/*  290 */           blockstate.update(true, false);
/*      */         }
/*      */ 
/*      */         
/*  294 */         BlockPosition placedPos = ((CraftBlock)placeEvent.getBlock()).getPosition();
/*  295 */         for (EnumDirection dir : EnumDirection.values()) {
/*  296 */           ((EntityPlayer)entityhuman).playerConnection.sendPacket(new PacketPlayOutBlockChange(world, placedPos.shift(dir)));
/*      */         }
/*      */       } else {
/*      */         
/*  300 */         if (getCount() == oldCount && Objects.equals(this.tag, oldData)) {
/*  301 */           setTag(newData);
/*  302 */           setCount(newCount);
/*      */         } 
/*      */         
/*  305 */         for (Map.Entry<BlockPosition, TileEntity> e : world.capturedTileEntities.entrySet()) {
/*  306 */           world.setTileEntity(e.getKey(), e.getValue());
/*      */         }
/*      */         
/*  309 */         for (BlockState blockstate : blocks) {
/*  310 */           int updateFlag = ((CraftBlockState)blockstate).getFlag();
/*  311 */           IBlockData oldBlock = ((CraftBlockState)blockstate).getHandle();
/*  312 */           BlockPosition newblockposition = ((CraftBlockState)blockstate).getPosition();
/*  313 */           IBlockData block = world.getType(newblockposition);
/*      */           
/*  315 */           if (!(block.getBlock() instanceof BlockTileEntity)) {
/*  316 */             block.getBlock().onPlace(block, world, newblockposition, oldBlock, true, itemactioncontext);
/*      */           }
/*      */           
/*  319 */           world.notifyAndUpdatePhysics(newblockposition, null, oldBlock, block, world.getType(newblockposition), updateFlag, 512);
/*      */         } 
/*      */ 
/*      */ 
/*      */         
/*  324 */         if (this.item instanceof ItemRecord) {
/*  325 */           ((BlockJukeBox)Blocks.JUKEBOX).a(world, blockposition, world.getType(blockposition), this);
/*  326 */           world.a((EntityHuman)null, 1010, blockposition, Item.getId(this.item));
/*  327 */           subtract(1);
/*  328 */           entityhuman.a(StatisticList.PLAY_RECORD);
/*      */         } 
/*      */         
/*  331 */         if (this.item == Items.WITHER_SKELETON_SKULL) {
/*  332 */           BlockPosition bp = blockposition;
/*  333 */           if (!world.getType(blockposition).getMaterial().isReplaceable()) {
/*  334 */             if (!world.getType(blockposition).getMaterial().isBuildable()) {
/*  335 */               bp = null;
/*      */             } else {
/*  337 */               bp = bp.shift(itemactioncontext.getClickedFace());
/*      */             } 
/*      */           }
/*  340 */           if (bp != null) {
/*  341 */             TileEntity te = world.getTileEntity(bp);
/*  342 */             if (te instanceof TileEntitySkull) {
/*  343 */               BlockWitherSkull.a(world, bp, (TileEntitySkull)te);
/*      */             }
/*      */           } 
/*      */         } 
/*      */ 
/*      */         
/*  349 */         if (this.item instanceof ItemSign && ItemSign.openSign != null) {
/*      */           try {
/*  351 */             entityhuman.openSign((TileEntitySign)world.getTileEntity(ItemSign.openSign));
/*      */           } finally {
/*  353 */             ItemSign.openSign = null;
/*      */           } 
/*      */         }
/*      */ 
/*      */         
/*  358 */         if (this.item instanceof ItemBlock) {
/*  359 */           SoundEffectType soundeffecttype = (((ItemBlock)this.item).getBlock()).stepSound;
/*  360 */           world.playSound(entityhuman, blockposition, soundeffecttype.e(), SoundCategory.BLOCKS, (soundeffecttype.a() + 1.0F) / 2.0F, soundeffecttype.b() * 0.8F);
/*      */         } 
/*      */         
/*  363 */         entityhuman.b(StatisticList.ITEM_USED.b(item));
/*      */       } 
/*      */     } 
/*  366 */     world.capturedTileEntities.clear();
/*  367 */     world.capturedBlockStates.clear();
/*      */ 
/*      */     
/*  370 */     return enuminteractionresult;
/*      */   }
/*      */ 
/*      */   
/*      */   public float a(IBlockData iblockdata) {
/*  375 */     return getItem().getDestroySpeed(this, iblockdata);
/*      */   }
/*      */   
/*      */   public InteractionResultWrapper<ItemStack> a(World world, EntityHuman entityhuman, EnumHand enumhand) {
/*  379 */     return getItem().a(world, entityhuman, enumhand);
/*      */   }
/*      */   
/*      */   public ItemStack a(World world, EntityLiving entityliving) {
/*  383 */     return getItem().a(this, world, entityliving);
/*      */   }
/*      */   
/*      */   public NBTTagCompound save(NBTTagCompound nbttagcompound) {
/*  387 */     MinecraftKey minecraftkey = IRegistry.ITEM.getKey(getItem());
/*      */     
/*  389 */     nbttagcompound.setString("id", (minecraftkey == null) ? "minecraft:air" : minecraftkey.toString());
/*  390 */     nbttagcompound.setByte("Count", (byte)this.count);
/*  391 */     if (this.tag != null) {
/*  392 */       nbttagcompound.set("tag", this.tag.clone());
/*      */     }
/*      */     
/*  395 */     return nbttagcompound;
/*      */   }
/*      */   
/*      */   public int getMaxStackSize() {
/*  399 */     return getItem().getMaxStackSize();
/*      */   }
/*      */   
/*      */   public boolean isStackable() {
/*  403 */     return (getMaxStackSize() > 1 && (!e() || !f()));
/*      */   }
/*      */   
/*      */   public boolean e() {
/*  407 */     if (!this.j && getItem().getMaxDurability() > 0) {
/*  408 */       NBTTagCompound nbttagcompound = getTag();
/*      */       
/*  410 */       return (nbttagcompound == null || !nbttagcompound.getBoolean("Unbreakable"));
/*      */     } 
/*  412 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean f() {
/*  417 */     return (e() && getDamage() > 0);
/*      */   }
/*      */   
/*      */   public int getDamage() {
/*  421 */     return (this.tag == null) ? 0 : this.tag.getInt("Damage");
/*      */   }
/*      */   
/*      */   public void setDamage(int i) {
/*  425 */     getOrCreateTag().setInt("Damage", Math.max(0, i));
/*      */   }
/*      */   
/*      */   public int h() {
/*  429 */     return getItem().getMaxDurability();
/*      */   }
/*      */   
/*      */   public boolean isDamaged(int i, Random random, @Nullable EntityPlayer entityplayer) {
/*  433 */     if (!e()) {
/*  434 */       return false;
/*      */     }
/*      */ 
/*      */     
/*  438 */     if (i > 0) {
/*  439 */       int m = EnchantmentManager.getEnchantmentLevel(Enchantments.DURABILITY, this);
/*  440 */       int k = 0;
/*      */       
/*  442 */       for (int l = 0; m > 0 && l < i; l++) {
/*  443 */         if (EnchantmentDurability.a(this, m, random)) {
/*  444 */           k++;
/*      */         }
/*      */       } 
/*      */       
/*  448 */       i -= k;
/*      */       
/*  450 */       if (entityplayer != null) {
/*  451 */         PlayerItemDamageEvent event = new PlayerItemDamageEvent((Player)entityplayer.getBukkitEntity(), (org.bukkit.inventory.ItemStack)CraftItemStack.asCraftMirror(this), i);
/*  452 */         event.getPlayer().getServer().getPluginManager().callEvent((Event)event);
/*      */         
/*  454 */         if (i != event.getDamage() || event.isCancelled()) {
/*  455 */           event.getPlayer().updateInventory();
/*      */         }
/*  457 */         if (event.isCancelled()) {
/*  458 */           return false;
/*      */         }
/*      */         
/*  461 */         i = event.getDamage();
/*      */       } 
/*      */       
/*  464 */       if (i <= 0) {
/*  465 */         return false;
/*      */       }
/*      */     } 
/*      */     
/*  469 */     if (entityplayer != null && i != 0) {
/*  470 */       CriterionTriggers.t.a(entityplayer, this, getDamage() + i);
/*      */     }
/*      */     
/*  473 */     int j = getDamage() + i;
/*  474 */     setDamage(j);
/*  475 */     return (j >= h());
/*      */   }
/*      */ 
/*      */   
/*      */   public <T extends EntityLiving> void damage(int i, T t0, Consumer<T> consumer) {
/*  480 */     if (!((EntityLiving)t0).world.isClientSide && (!(t0 instanceof EntityHuman) || !((EntityHuman)t0).abilities.canInstantlyBuild) && 
/*  481 */       e() && 
/*  482 */       isDamaged(i, t0.getRandom(), (t0 instanceof EntityPlayer) ? (EntityPlayer)t0 : null)) {
/*  483 */       consumer.accept(t0);
/*  484 */       Item item = getItem();
/*      */       
/*  486 */       if (this.count == 1 && t0 instanceof EntityHuman) {
/*  487 */         CraftEventFactory.callPlayerItemBreakEvent((EntityHuman)t0, this);
/*      */       }
/*      */ 
/*      */       
/*  491 */       subtract(1);
/*  492 */       if (t0 instanceof EntityHuman) {
/*  493 */         ((EntityHuman)t0).b(StatisticList.ITEM_BROKEN.b(item));
/*      */       }
/*      */       
/*  496 */       setDamage(0);
/*      */     } 
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void a(EntityLiving entityliving, EntityHuman entityhuman) {
/*  504 */     Item item = getItem();
/*      */     
/*  506 */     if (item.a(this, entityliving, entityhuman)) {
/*  507 */       entityhuman.b(StatisticList.ITEM_USED.b(item));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(World world, IBlockData iblockdata, BlockPosition blockposition, EntityHuman entityhuman) {
/*  513 */     Item item = getItem();
/*      */     
/*  515 */     if (item.a(this, world, iblockdata, blockposition, entityhuman)) {
/*  516 */       entityhuman.b(StatisticList.ITEM_USED.b(item));
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean canDestroySpecialBlock(IBlockData iblockdata) {
/*  522 */     return getItem().canDestroySpecialBlock(iblockdata);
/*      */   }
/*      */   
/*      */   public EnumInteractionResult a(EntityHuman entityhuman, EntityLiving entityliving, EnumHand enumhand) {
/*  526 */     return getItem().a(this, entityhuman, entityliving, enumhand);
/*      */   }
/*      */   public ItemStack cloneItemStack() {
/*  529 */     return cloneItemStack(false);
/*      */   } public ItemStack cloneItemStack(boolean origItem) {
/*  531 */     if (!origItem && isEmpty()) {
/*  532 */       return b;
/*      */     }
/*  534 */     ItemStack itemstack = new ItemStack(origItem ? this.item : getItem(), this.count);
/*      */     
/*  536 */     itemstack.d(D());
/*  537 */     if (this.tag != null) {
/*  538 */       itemstack.tag = this.tag.clone();
/*      */     }
/*      */     
/*  541 */     return itemstack;
/*      */   }
/*      */ 
/*      */   
/*      */   public static boolean equals(ItemStack itemstack, ItemStack itemstack1) {
/*  546 */     return (itemstack.isEmpty() && itemstack1.isEmpty()) ? true : ((!itemstack.isEmpty() && !itemstack1.isEmpty()) ? ((itemstack.tag == null && itemstack1.tag != null) ? false : ((itemstack.tag == null || itemstack.tag.equals(itemstack1.tag)))) : false);
/*      */   }
/*      */   
/*      */   public static boolean matches(ItemStack itemstack, ItemStack itemstack1) {
/*  550 */     return (itemstack.isEmpty() && itemstack1.isEmpty()) ? true : ((!itemstack.isEmpty() && !itemstack1.isEmpty()) ? itemstack.c(itemstack1) : false);
/*      */   }
/*      */   
/*      */   private boolean c(ItemStack itemstack) {
/*  554 */     return (this.count != itemstack.count) ? false : ((getItem() != itemstack.getItem()) ? false : ((this.tag == null && itemstack.tag != null) ? false : ((this.tag == null || this.tag.equals(itemstack.tag)))));
/*      */   }
/*      */   
/*      */   public static boolean c(ItemStack itemstack, ItemStack itemstack1) {
/*  558 */     return (itemstack == itemstack1) ? true : ((!itemstack.isEmpty() && !itemstack1.isEmpty()) ? itemstack.doMaterialsMatch(itemstack1) : false);
/*      */   }
/*      */   
/*      */   public static boolean d(ItemStack itemstack, ItemStack itemstack1) {
/*  562 */     return (itemstack == itemstack1) ? true : ((!itemstack.isEmpty() && !itemstack1.isEmpty()) ? itemstack.b(itemstack1) : false);
/*      */   }
/*      */   
/*      */   public boolean doMaterialsMatch(ItemStack itemstack) {
/*  566 */     return (!itemstack.isEmpty() && getItem() == itemstack.getItem());
/*      */   }
/*      */   
/*      */   public boolean b(ItemStack itemstack) {
/*  570 */     return !e() ? doMaterialsMatch(itemstack) : ((!itemstack.isEmpty() && getItem() == itemstack.getItem()));
/*      */   }
/*      */   
/*      */   public String j() {
/*  574 */     return getItem().f(this);
/*      */   }
/*      */   
/*      */   public String toString() {
/*  578 */     return this.count + " " + getItem();
/*      */   }
/*      */   
/*      */   public void a(World world, Entity entity, int i, boolean flag) {
/*  582 */     if (this.g > 0) {
/*  583 */       this.g--;
/*      */     }
/*      */     
/*  586 */     if (getItem() != null) {
/*  587 */       getItem().a(this, world, entity, i, flag);
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public void a(World world, EntityHuman entityhuman, int i) {
/*  593 */     entityhuman.a(StatisticList.ITEM_CRAFTED.b(getItem()), i);
/*  594 */     getItem().b(this, world, entityhuman);
/*      */   }
/*      */   public int getItemUseMaxDuration() {
/*  597 */     return k();
/*      */   } public int k() {
/*  599 */     return getItem().e_(this);
/*      */   }
/*      */   
/*      */   public EnumAnimation l() {
/*  603 */     return getItem().d_(this);
/*      */   }
/*      */   
/*      */   public void a(World world, EntityLiving entityliving, int i) {
/*  607 */     getItem().a(this, world, entityliving, i);
/*      */   }
/*      */   
/*      */   public boolean m() {
/*  611 */     return getItem().j(this);
/*      */   }
/*      */   
/*      */   public boolean hasTag() {
/*  615 */     return (!this.j && this.tag != null && !this.tag.isEmpty());
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public NBTTagCompound getTag() {
/*  620 */     return this.tag;
/*      */   }
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   private NBTTagCompound getTagClone() {
/*  626 */     return (this.tag == null) ? null : this.tag.clone();
/*      */   }
/*      */   
/*      */   private void setTagClone(@Nullable NBTTagCompound nbtttagcompound) {
/*  630 */     setTag((nbtttagcompound == null) ? null : nbtttagcompound.clone());
/*      */   }
/*      */ 
/*      */   
/*      */   public NBTTagCompound getOrCreateTag() {
/*  635 */     if (this.tag == null) {
/*  636 */       setTag(new NBTTagCompound());
/*      */     }
/*      */     
/*  639 */     return this.tag;
/*      */   }
/*      */   
/*      */   public NBTTagCompound a(String s) {
/*  643 */     if (this.tag != null && this.tag.hasKeyOfType(s, 10)) {
/*  644 */       return this.tag.getCompound(s);
/*      */     }
/*  646 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*      */     
/*  648 */     a(s, nbttagcompound);
/*  649 */     return nbttagcompound;
/*      */   }
/*      */   @Nullable
/*      */   public NBTTagCompound getSubTag(String s) {
/*  653 */     return b(s);
/*      */   } @Nullable
/*      */   public NBTTagCompound b(String s) {
/*  656 */     return (this.tag != null && this.tag.hasKeyOfType(s, 10)) ? this.tag.getCompound(s) : null;
/*      */   }
/*      */   
/*      */   public void removeTag(String s) {
/*  660 */     if (this.tag != null && this.tag.hasKey(s)) {
/*  661 */       this.tag.remove(s);
/*  662 */       if (this.tag.isEmpty()) {
/*  663 */         this.tag = null;
/*      */       }
/*      */     } 
/*      */   }
/*      */ 
/*      */   
/*      */   public NBTTagList getEnchantments() {
/*  670 */     return (this.tag != null) ? this.tag.getList("Enchantments", 10) : new NBTTagList();
/*      */   }
/*      */ 
/*      */   
/*      */   public org.bukkit.inventory.ItemStack asBukkitMirror() {
/*  675 */     return (org.bukkit.inventory.ItemStack)CraftItemStack.asCraftMirror(this);
/*      */   }
/*      */   public org.bukkit.inventory.ItemStack asBukkitCopy() {
/*  678 */     return (org.bukkit.inventory.ItemStack)CraftItemStack.asCraftMirror(cloneItemStack());
/*      */   }
/*      */   public static ItemStack fromBukkitCopy(org.bukkit.inventory.ItemStack itemstack) {
/*  681 */     return CraftItemStack.asNMSCopy(itemstack);
/*      */   }
/*      */   
/*      */   public org.bukkit.inventory.ItemStack getBukkitStack() {
/*  685 */     if (this.bukkitStack == null || this.bukkitStack.getHandle() != this) {
/*  686 */       this.bukkitStack = CraftItemStack.asCraftMirror(this);
/*      */     }
/*  688 */     return (org.bukkit.inventory.ItemStack)this.bukkitStack;
/*      */   }
/*      */   
/*      */   public void setTag(@Nullable NBTTagCompound nbttagcompound) {
/*  692 */     this.tag = nbttagcompound;
/*  693 */     processEnchantOrder(this.tag);
/*  694 */     if (getItem().usesDurability()) {
/*  695 */       setDamage(getDamage());
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public IChatBaseComponent getName() {
/*  701 */     NBTTagCompound nbttagcompound = b("display");
/*      */     
/*  703 */     if (nbttagcompound != null && nbttagcompound.hasKeyOfType("Name", 8)) {
/*      */       try {
/*  705 */         IChatMutableComponent ichatmutablecomponent = IChatBaseComponent.ChatSerializer.a(nbttagcompound.getString("Name"));
/*      */         
/*  707 */         if (ichatmutablecomponent != null) {
/*  708 */           return ichatmutablecomponent;
/*      */         }
/*      */         
/*  711 */         nbttagcompound.remove("Name");
/*  712 */       } catch (JsonParseException jsonparseexception) {
/*  713 */         nbttagcompound.remove("Name");
/*      */       } 
/*      */     }
/*      */     
/*  717 */     return getItem().h(this);
/*      */   }
/*      */   
/*      */   public ItemStack a(@Nullable IChatBaseComponent ichatbasecomponent) {
/*  721 */     NBTTagCompound nbttagcompound = a("display");
/*      */     
/*  723 */     if (ichatbasecomponent != null) {
/*  724 */       nbttagcompound.setString("Name", IChatBaseComponent.ChatSerializer.a(ichatbasecomponent));
/*      */     } else {
/*  726 */       nbttagcompound.remove("Name");
/*      */     } 
/*      */     
/*  729 */     return this;
/*      */   }
/*      */   
/*      */   public void s() {
/*  733 */     NBTTagCompound nbttagcompound = b("display");
/*      */     
/*  735 */     if (nbttagcompound != null) {
/*  736 */       nbttagcompound.remove("Name");
/*  737 */       if (nbttagcompound.isEmpty()) {
/*  738 */         removeTag("display");
/*      */       }
/*      */     } 
/*      */     
/*  742 */     if (this.tag != null && this.tag.isEmpty()) {
/*  743 */       this.tag = null;
/*      */     }
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean hasName() {
/*  749 */     NBTTagCompound nbttagcompound = b("display");
/*      */     
/*  751 */     return (nbttagcompound != null && nbttagcompound.hasKeyOfType("Name", 8));
/*      */   }
/*      */   
/*      */   public void a(HideFlags itemstack_hideflags) {
/*  755 */     NBTTagCompound nbttagcompound = getOrCreateTag();
/*      */     
/*  757 */     nbttagcompound.setInt("HideFlags", nbttagcompound.getInt("HideFlags") | itemstack_hideflags.a());
/*      */   }
/*      */   
/*      */   public boolean u() {
/*  761 */     return getItem().e(this);
/*      */   }
/*      */   
/*      */   public EnumItemRarity v() {
/*  765 */     return getItem().i(this);
/*      */   }
/*      */   
/*      */   public boolean canEnchant() {
/*  769 */     return !getItem().f_(this) ? false : (!hasEnchantments());
/*      */   }
/*      */   
/*      */   public void addEnchantment(Enchantment enchantment, int i) {
/*  773 */     getOrCreateTag();
/*  774 */     if (!this.tag.hasKeyOfType("Enchantments", 9)) {
/*  775 */       this.tag.set("Enchantments", new NBTTagList());
/*      */     }
/*      */     
/*  778 */     NBTTagList nbttaglist = this.tag.getList("Enchantments", 10);
/*  779 */     NBTTagCompound nbttagcompound = new NBTTagCompound();
/*      */     
/*  781 */     nbttagcompound.setString("id", String.valueOf(IRegistry.ENCHANTMENT.getKey(enchantment)));
/*  782 */     nbttagcompound.setShort("lvl", (short)(byte)i);
/*  783 */     nbttaglist.add(nbttagcompound);
/*  784 */     processEnchantOrder(nbttagcompound);
/*      */   }
/*      */   
/*      */   public boolean hasEnchantments() {
/*  788 */     return (this.tag != null && this.tag.hasKeyOfType("Enchantments", 9)) ? (!this.tag.getList("Enchantments", 10).isEmpty()) : false;
/*      */   }
/*      */   public void getOrCreateTagAndSet(String s, NBTBase nbtbase) {
/*  791 */     a(s, nbtbase);
/*      */   } public void a(String s, NBTBase nbtbase) {
/*  793 */     getOrCreateTag().set(s, nbtbase);
/*      */   }
/*      */   
/*      */   public boolean y() {
/*  797 */     return this.k instanceof EntityItemFrame;
/*      */   }
/*      */   
/*      */   public void a(@Nullable Entity entity) {
/*  801 */     this.k = entity;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public EntityItemFrame z() {
/*  806 */     return (this.k instanceof EntityItemFrame) ? (EntityItemFrame)A() : null;
/*      */   }
/*      */   
/*      */   @Nullable
/*      */   public Entity A() {
/*  811 */     return !this.j ? this.k : null;
/*      */   }
/*      */   
/*      */   public int getRepairCost() {
/*  815 */     return (hasTag() && this.tag.hasKeyOfType("RepairCost", 3)) ? this.tag.getInt("RepairCost") : 0;
/*      */   }
/*      */ 
/*      */   
/*      */   public void setRepairCost(int i) {
/*  820 */     if (i == 0) {
/*  821 */       removeTag("RepairCost");
/*      */       
/*      */       return;
/*      */     } 
/*  825 */     getOrCreateTag().setInt("RepairCost", i);
/*      */   }
/*      */ 
/*      */   
/*      */   public Multimap<AttributeBase, AttributeModifier> a(EnumItemSlot enumitemslot) {
/*      */     Object<AttributeBase, AttributeModifier> object;
/*  831 */     if (hasTag() && this.tag.hasKeyOfType("AttributeModifiers", 9)) {
/*  832 */       object = (Object<AttributeBase, AttributeModifier>)HashMultimap.create();
/*  833 */       NBTTagList nbttaglist = this.tag.getList("AttributeModifiers", 10);
/*      */       
/*  835 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  836 */         NBTTagCompound nbttagcompound = nbttaglist.getCompound(i);
/*      */         
/*  838 */         if (!nbttagcompound.hasKeyOfType("Slot", 8) || nbttagcompound.getString("Slot").equals(enumitemslot.getSlotName())) {
/*  839 */           Optional<AttributeBase> optional = IRegistry.ATTRIBUTE.getOptional(MinecraftKey.a(nbttagcompound.getString("AttributeName")));
/*      */           
/*  841 */           if (optional.isPresent()) {
/*  842 */             AttributeModifier attributemodifier = AttributeModifier.a(nbttagcompound);
/*      */             
/*  844 */             if (attributemodifier != null && attributemodifier.getUniqueId().getLeastSignificantBits() != 0L && attributemodifier.getUniqueId().getMostSignificantBits() != 0L) {
/*  845 */               ((Multimap)object).put(optional.get(), attributemodifier);
/*      */             }
/*      */           } 
/*      */         } 
/*      */       } 
/*      */     } else {
/*  851 */       object = (Object<AttributeBase, AttributeModifier>)getItem().a(enumitemslot);
/*      */     } 
/*      */     
/*  854 */     return (Multimap<AttributeBase, AttributeModifier>)object;
/*      */   }
/*      */   
/*      */   public void a(AttributeBase attributebase, AttributeModifier attributemodifier, @Nullable EnumItemSlot enumitemslot) {
/*  858 */     getOrCreateTag();
/*  859 */     if (!this.tag.hasKeyOfType("AttributeModifiers", 9)) {
/*  860 */       this.tag.set("AttributeModifiers", new NBTTagList());
/*      */     }
/*      */     
/*  863 */     NBTTagList nbttaglist = this.tag.getList("AttributeModifiers", 10);
/*  864 */     NBTTagCompound nbttagcompound = attributemodifier.save();
/*      */     
/*  866 */     nbttagcompound.setString("AttributeName", IRegistry.ATTRIBUTE.getKey(attributebase).toString());
/*  867 */     if (enumitemslot != null) {
/*  868 */       nbttagcompound.setString("Slot", enumitemslot.getSlotName());
/*      */     }
/*      */     
/*  871 */     nbttaglist.add(nbttagcompound);
/*      */   }
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   public void setItem(Item item) {
/*  877 */     this.bukkitStack = null;
/*  878 */     this.item = item;
/*      */   }
/*      */ 
/*      */   
/*      */   public IChatBaseComponent C() {
/*  883 */     IChatMutableComponent ichatmutablecomponent = (new ChatComponentText("")).addSibling(getName());
/*      */     
/*  885 */     if (hasName()) {
/*  886 */       ichatmutablecomponent.a(EnumChatFormat.ITALIC);
/*      */     }
/*      */     
/*  889 */     IChatMutableComponent ichatmutablecomponent1 = ChatComponentUtils.a(ichatmutablecomponent);
/*      */     
/*  891 */     if (!this.j) {
/*  892 */       ichatmutablecomponent1.a((v()).e).format(chatmodifier -> chatmodifier.setChatHoverable(new ChatHoverable((ChatHoverable.EnumHoverAction)ChatHoverable.EnumHoverAction.SHOW_ITEM, (T)new ChatHoverable.c(this))));
/*      */     }
/*      */ 
/*      */ 
/*      */     
/*  897 */     return ichatmutablecomponent1;
/*      */   }
/*      */   
/*      */   private static boolean a(ShapeDetectorBlock shapedetectorblock, @Nullable ShapeDetectorBlock shapedetectorblock1) {
/*  901 */     return (shapedetectorblock1 != null && shapedetectorblock.a() == shapedetectorblock1.a()) ? ((shapedetectorblock.b() == null && shapedetectorblock1.b() == null) ? true : ((shapedetectorblock.b() != null && shapedetectorblock1.b() != null) ? Objects.equals(shapedetectorblock.b().save(new NBTTagCompound()), shapedetectorblock1.b().save(new NBTTagCompound())) : false)) : false;
/*      */   }
/*      */   
/*      */   public boolean a(ITagRegistry itagregistry, ShapeDetectorBlock shapedetectorblock) {
/*  905 */     if (a(shapedetectorblock, this.l)) {
/*  906 */       return this.m;
/*      */     }
/*  908 */     this.l = shapedetectorblock;
/*  909 */     if (hasTag() && this.tag.hasKeyOfType("CanDestroy", 9)) {
/*  910 */       NBTTagList nbttaglist = this.tag.getList("CanDestroy", 8);
/*      */       
/*  912 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  913 */         String s = nbttaglist.getString(i);
/*      */         
/*      */         try {
/*  916 */           Predicate<ShapeDetectorBlock> predicate = ArgumentBlockPredicate.a().parse(new StringReader(s)).create(itagregistry);
/*      */           
/*  918 */           if (predicate.test(shapedetectorblock)) {
/*  919 */             this.m = true;
/*  920 */             return true;
/*      */           } 
/*  922 */         } catch (CommandSyntaxException commandSyntaxException) {}
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  928 */     this.m = false;
/*  929 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public boolean b(ITagRegistry itagregistry, ShapeDetectorBlock shapedetectorblock) {
/*  934 */     if (a(shapedetectorblock, this.n)) {
/*  935 */       return this.o;
/*      */     }
/*  937 */     this.n = shapedetectorblock;
/*  938 */     if (hasTag() && this.tag.hasKeyOfType("CanPlaceOn", 9)) {
/*  939 */       NBTTagList nbttaglist = this.tag.getList("CanPlaceOn", 8);
/*      */       
/*  941 */       for (int i = 0; i < nbttaglist.size(); i++) {
/*  942 */         String s = nbttaglist.getString(i);
/*      */         
/*      */         try {
/*  945 */           Predicate<ShapeDetectorBlock> predicate = ArgumentBlockPredicate.a().parse(new StringReader(s)).create(itagregistry);
/*      */           
/*  947 */           if (predicate.test(shapedetectorblock)) {
/*  948 */             this.o = true;
/*  949 */             return true;
/*      */           } 
/*  951 */         } catch (CommandSyntaxException commandSyntaxException) {}
/*      */       } 
/*      */     } 
/*      */ 
/*      */ 
/*      */     
/*  957 */     this.o = false;
/*  958 */     return false;
/*      */   }
/*      */ 
/*      */   
/*      */   public int D() {
/*  963 */     return this.g;
/*      */   }
/*      */   
/*      */   public void d(int i) {
/*  967 */     this.g = i;
/*      */   }
/*      */   
/*      */   public int getCount() {
/*  971 */     return this.j ? 0 : this.count;
/*      */   }
/*      */   
/*      */   public void setCount(int i) {
/*  975 */     this.count = i;
/*  976 */     checkEmpty();
/*      */   }
/*      */   
/*      */   public void add(int i) {
/*  980 */     setCount(this.count + i);
/*      */   }
/*      */   
/*      */   public void subtract(int i) {
/*  984 */     add(-i);
/*      */   }
/*      */   
/*      */   public void b(World world, EntityLiving entityliving, int i) {
/*  988 */     getItem().a(world, entityliving, this, i);
/*      */   }
/*      */   
/*      */   public boolean F() {
/*  992 */     return getItem().isFood();
/*      */   }
/*      */   
/*      */   public SoundEffect G() {
/*  996 */     return getItem().ae_();
/*      */   }
/*      */   
/*      */   public SoundEffect H() {
/* 1000 */     return getItem().ad_();
/*      */   }
/*      */   
/*      */   public enum HideFlags
/*      */   {
/* 1005 */     ENCHANTMENTS, MODIFIERS, UNBREAKABLE, CAN_DESTROY, CAN_PLACE, ADDITIONAL, DYE;
/*      */     
/* 1007 */     private int h = 1 << ordinal();
/*      */ 
/*      */ 
/*      */     
/*      */     public int a() {
/* 1012 */       return this.h;
/*      */     }
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemStack.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */