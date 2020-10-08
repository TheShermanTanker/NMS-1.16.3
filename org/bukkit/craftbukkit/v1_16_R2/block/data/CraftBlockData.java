/*     */ package org.bukkit.craftbukkit.v1_16_R2.block.data;
/*     */ import com.google.common.base.Function;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.HashBiMap;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.HashMap;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.ArgumentBlock;
/*     */ import net.minecraft.server.v1_16_R2.Block;
/*     */ import net.minecraft.server.v1_16_R2.BlockCampfire;
/*     */ import net.minecraft.server.v1_16_R2.BlockRepeater;
/*     */ import net.minecraft.server.v1_16_R2.BlockRotatable;
/*     */ import net.minecraft.server.v1_16_R2.BlockStateBoolean;
/*     */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*     */ import net.minecraft.server.v1_16_R2.BlockStateInteger;
/*     */ import net.minecraft.server.v1_16_R2.BlockTarget;
/*     */ import net.minecraft.server.v1_16_R2.EnumDirection;
/*     */ import net.minecraft.server.v1_16_R2.IBlockData;
/*     */ import net.minecraft.server.v1_16_R2.IBlockState;
/*     */ import net.minecraft.server.v1_16_R2.IRegistry;
/*     */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*     */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*     */ import org.bukkit.Material;
/*     */ import org.bukkit.block.BlockFace;
/*     */ import org.bukkit.block.data.BlockData;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*     */ 
/*     */ public class CraftBlockData implements BlockData {
/*     */   private IBlockData state;
/*     */   
/*     */   protected CraftBlockData() {
/*  38 */     throw new AssertionError("Template Constructor");
/*     */   }
/*     */   private Map<IBlockState<?>, Comparable<?>> parsedStates;
/*     */   protected CraftBlockData(IBlockData state) {
/*  42 */     this.state = state;
/*     */   }
/*     */ 
/*     */   
/*     */   public Material getMaterial() {
/*  47 */     return this.state.getBukkitMaterial();
/*     */   }
/*     */   
/*     */   public IBlockData getState() {
/*  51 */     return this.state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected <B extends Enum<B>> B get(BlockStateEnum<?> nms, Class<B> bukkit) {
/*  63 */     return toBukkit((Enum)this.state.get((IBlockState)nms), bukkit);
/*     */   }
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
/*     */   protected <B extends Enum<B>> Set<B> getValues(BlockStateEnum<?> nms, Class<B> bukkit) {
/*  77 */     ImmutableSet.Builder<B> values = ImmutableSet.builder();
/*     */     
/*  79 */     for (Enum<?> e : (Iterable<Enum<?>>)nms.getValues()) {
/*  80 */       values.add(toBukkit(e, bukkit));
/*     */     }
/*     */     
/*  83 */     return (Set<B>)values.build();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected <B extends Enum<B>, N extends Enum<N> & net.minecraft.server.v1_16_R2.INamable> void set(BlockStateEnum<N> nms, Enum<B> bukkit) {
/*  95 */     this.parsedStates = null;
/*  96 */     this.state = (IBlockData)this.state.set((IBlockState)nms, toNMS(bukkit, nms.getType()));
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockData merge(BlockData data) {
/* 101 */     CraftBlockData craft = (CraftBlockData)data;
/* 102 */     Preconditions.checkArgument((craft.parsedStates != null), "Data not created via string parsing");
/* 103 */     Preconditions.checkArgument((this.state.getBlock() == craft.state.getBlock()), "States have different types (got %s, expected %s)", data, this);
/*     */     
/* 105 */     CraftBlockData clone = (CraftBlockData)clone();
/* 106 */     clone.parsedStates = null;
/*     */     
/* 108 */     for (IBlockState<?> parsed : craft.parsedStates.keySet()) {
/* 109 */       clone.state = (IBlockData)clone.state.set(parsed, craft.state.get(parsed));
/*     */     }
/*     */     
/* 112 */     return clone;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean matches(BlockData data) {
/* 117 */     if (data == null) {
/* 118 */       return false;
/*     */     }
/* 120 */     if (!(data instanceof CraftBlockData)) {
/* 121 */       return false;
/*     */     }
/*     */     
/* 124 */     CraftBlockData craft = (CraftBlockData)data;
/* 125 */     if (this.state.getBlock() != craft.state.getBlock()) {
/* 126 */       return false;
/*     */     }
/*     */ 
/*     */     
/* 130 */     boolean exactMatch = equals(data);
/*     */ 
/*     */     
/* 133 */     if (!exactMatch && craft.parsedStates != null) {
/* 134 */       return merge(data).equals(this);
/*     */     }
/*     */     
/* 137 */     return exactMatch;
/*     */   }
/*     */   
/* 140 */   private static final Map<Class, BiMap<Enum<?>, Enum<?>>> classMappings = (Map)new HashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <B extends Enum<B>> B toBukkit(Enum<?> nms, Class<B> bukkit) {
/*     */     Enum<?> converted;
/*     */     HashBiMap hashBiMap;
/* 151 */     BiMap<Enum<?>, Enum<?>> nmsToBukkit = classMappings.get(nms.getClass());
/*     */     
/* 153 */     if (nmsToBukkit != null) {
/* 154 */       converted = (Enum)nmsToBukkit.get(nms);
/* 155 */       if (converted != null) {
/* 156 */         return (B)converted;
/*     */       }
/*     */     } 
/*     */     
/* 160 */     if (nms instanceof EnumDirection) {
/* 161 */       BlockFace blockFace = CraftBlock.notchToBlockFace((EnumDirection)nms);
/*     */     } else {
/* 163 */       converted = ((Enum[])bukkit.getEnumConstants())[nms.ordinal()];
/*     */     } 
/*     */     
/* 166 */     Preconditions.checkState((converted != null), "Could not convert enum %s->%s", nms, bukkit);
/*     */     
/* 168 */     if (nmsToBukkit == null) {
/* 169 */       hashBiMap = HashBiMap.create();
/* 170 */       classMappings.put(nms.getClass(), hashBiMap);
/*     */     } 
/*     */     
/* 173 */     hashBiMap.put(nms, converted);
/*     */     
/* 175 */     return (B)converted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static <N extends Enum<N> & net.minecraft.server.v1_16_R2.INamable> N toNMS(Enum<?> bukkit, Class<N> nms) {
/*     */     Enum<?> converted;
/*     */     HashBiMap hashBiMap;
/* 189 */     BiMap<Enum<?>, Enum<?>> nmsToBukkit = classMappings.get(nms);
/*     */     
/* 191 */     if (nmsToBukkit != null) {
/* 192 */       converted = (Enum)nmsToBukkit.inverse().get(bukkit);
/* 193 */       if (converted != null) {
/* 194 */         return (N)converted;
/*     */       }
/*     */     } 
/*     */     
/* 198 */     if (bukkit instanceof BlockFace) {
/* 199 */       EnumDirection enumDirection = CraftBlock.blockFaceToNotch((BlockFace)bukkit);
/*     */     } else {
/* 201 */       converted = ((Enum[])nms.getEnumConstants())[bukkit.ordinal()];
/*     */     } 
/*     */     
/* 204 */     Preconditions.checkState((converted != null), "Could not convert enum %s->%s", nms, bukkit);
/*     */     
/* 206 */     if (nmsToBukkit == null) {
/* 207 */       hashBiMap = HashBiMap.create();
/* 208 */       classMappings.put(nms, hashBiMap);
/*     */     } 
/*     */     
/* 211 */     hashBiMap.put(converted, bukkit);
/*     */     
/* 213 */     return (N)converted;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected <T extends Comparable<T>> T get(IBlockState<T> ibs) {
/* 225 */     return (T)this.state.get(ibs);
/*     */   }
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
/*     */   public <T extends Comparable<T>, V extends T> void set(IBlockState<T> ibs, V v) {
/* 238 */     this.parsedStates = null;
/* 239 */     this.state = (IBlockData)this.state.set(ibs, (Comparable)v);
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAsString() {
/* 244 */     return toString((Map<IBlockState<?>, Comparable<?>>)this.state.getStateMap());
/*     */   }
/*     */ 
/*     */   
/*     */   public String getAsString(boolean hideUnspecified) {
/* 249 */     return (hideUnspecified && this.parsedStates != null) ? toString(this.parsedStates) : getAsString();
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockData clone() {
/*     */     try {
/* 255 */       return (BlockData)super.clone();
/* 256 */     } catch (CloneNotSupportedException ex) {
/* 257 */       throw new AssertionError("Clone not supported", ex);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 263 */     return "CraftBlockData{" + getAsString() + "}";
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString(Map<IBlockState<?>, Comparable<?>> states) {
/* 268 */     StringBuilder stateString = new StringBuilder(IRegistry.BLOCK.getKey(this.state.getBlock()).toString());
/*     */     
/* 270 */     if (!states.isEmpty()) {
/* 271 */       stateString.append('[');
/* 272 */       stateString.append(states.entrySet().stream().map(IBlockDataHolder.STATE_TO_VALUE).collect(Collectors.joining(",")));
/* 273 */       stateString.append(']');
/*     */     } 
/*     */     
/* 276 */     return stateString.toString();
/*     */   }
/*     */   
/*     */   public NBTTagCompound toStates() {
/* 280 */     NBTTagCompound compound = new NBTTagCompound();
/*     */     
/* 282 */     for (UnmodifiableIterator<Map.Entry<IBlockState<?>, Comparable<?>>> unmodifiableIterator = this.state.getStateMap().entrySet().iterator(); unmodifiableIterator.hasNext(); ) { Map.Entry<IBlockState<?>, Comparable<?>> entry = unmodifiableIterator.next();
/* 283 */       IBlockState iblockstate = entry.getKey();
/*     */       
/* 285 */       compound.setString(iblockstate.getName(), iblockstate.a(entry.getValue())); }
/*     */ 
/*     */     
/* 288 */     return compound;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 293 */     return (obj instanceof CraftBlockData && this.state.equals(((CraftBlockData)obj).state));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 298 */     return this.state.hashCode();
/*     */   }
/*     */   
/*     */   protected static BlockStateBoolean getBoolean(String name) {
/* 302 */     throw new AssertionError("Template Method");
/*     */   }
/*     */   
/*     */   protected static BlockStateBoolean getBoolean(String name, boolean optional) {
/* 306 */     throw new AssertionError("Template Method");
/*     */   }
/*     */   
/*     */   protected static BlockStateEnum<?> getEnum(String name) {
/* 310 */     throw new AssertionError("Template Method");
/*     */   }
/*     */   
/*     */   protected static BlockStateInteger getInteger(String name) {
/* 314 */     throw new AssertionError("Template Method");
/*     */   }
/*     */   
/*     */   protected static BlockStateBoolean getBoolean(Class<? extends Block> block, String name) {
/* 318 */     return (BlockStateBoolean)getState(block, name, false);
/*     */   }
/*     */   
/*     */   protected static BlockStateBoolean getBoolean(Class<? extends Block> block, String name, boolean optional) {
/* 322 */     return (BlockStateBoolean)getState(block, name, optional);
/*     */   }
/*     */   
/*     */   protected static BlockStateEnum<?> getEnum(Class<? extends Block> block, String name) {
/* 326 */     return (BlockStateEnum)getState(block, name, false);
/*     */   }
/*     */   
/*     */   protected static BlockStateInteger getInteger(Class<? extends Block> block, String name) {
/* 330 */     return (BlockStateInteger)getState(block, name, false);
/*     */   }
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
/*     */   private static IBlockState<?> getState(Class<? extends Block> block, String name, boolean optional) {
/* 345 */     IBlockState<?> state = null;
/*     */     
/* 347 */     for (Block instance : IRegistry.BLOCK) {
/* 348 */       if (instance.getClass() == block) {
/* 349 */         if (state == null) {
/* 350 */           state = instance.getStates().a(name); continue;
/*     */         } 
/* 352 */         IBlockState<?> newState = instance.getStates().a(name);
/*     */         
/* 354 */         Preconditions.checkState((state == newState), "State mistmatch %s,%s", state, newState);
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/* 359 */     Preconditions.checkState((optional || state != null), "Null state for %s,%s", block, name);
/*     */     
/* 361 */     return state;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int getMin(BlockStateInteger state) {
/* 371 */     return state.min;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int getMax(BlockStateInteger state) {
/* 381 */     return state.max;
/*     */   }
/*     */ 
/*     */   
/* 385 */   private static final Map<Class<? extends Block>, Function<IBlockData, CraftBlockData>> MAP = new HashMap<>();
/*     */ 
/*     */   
/*     */   static {
/* 389 */     register((Class)BlockAnvil.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftAnvil::new);
/* 390 */     register((Class)BlockBamboo.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBamboo::new);
/* 391 */     register((Class)BlockBanner.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBanner::new);
/* 392 */     register((Class)BlockBannerWall.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBannerWall::new);
/* 393 */     register((Class)BlockBarrel.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBarrel::new);
/* 394 */     register((Class)BlockBed.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBed::new);
/* 395 */     register((Class)BlockBeehive.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBeehive::new);
/* 396 */     register((Class)BlockBeetroot.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBeetroot::new);
/* 397 */     register((Class)BlockBell.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBell::new);
/* 398 */     register((Class)BlockBlastFurnace.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBlastFurnace::new);
/* 399 */     register((Class)BlockBrewingStand.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBrewingStand::new);
/* 400 */     register((Class)BlockBubbleColumn.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftBubbleColumn::new);
/* 401 */     register((Class)BlockCactus.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCactus::new);
/* 402 */     register((Class)BlockCake.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCake::new);
/* 403 */     register((Class)BlockCampfire.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCampfire::new);
/* 404 */     register((Class)BlockCarrots.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCarrots::new);
/* 405 */     register((Class)BlockCauldron.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCauldron::new);
/* 406 */     register((Class)BlockChain.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftChain::new);
/* 407 */     register((Class)BlockChest.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftChest::new);
/* 408 */     register((Class)BlockChestTrapped.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftChestTrapped::new);
/* 409 */     register((Class)BlockChorusFlower.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftChorusFlower::new);
/* 410 */     register((Class)BlockChorusFruit.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftChorusFruit::new);
/* 411 */     register((Class)BlockCobbleWall.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCobbleWall::new);
/* 412 */     register((Class)BlockCocoa.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCocoa::new);
/* 413 */     register((Class)BlockCommand.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCommand::new);
/* 414 */     register((Class)BlockComposter.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftComposter::new);
/* 415 */     register((Class)BlockConduit.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftConduit::new);
/* 416 */     register((Class)BlockCoralDead.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCoralDead::new);
/* 417 */     register((Class)BlockCoralFan.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCoralFan::new);
/* 418 */     register((Class)BlockCoralFanAbstract.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCoralFanAbstract::new);
/* 419 */     register((Class)BlockCoralFanWall.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCoralFanWall::new);
/* 420 */     register((Class)BlockCoralFanWallAbstract.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCoralFanWallAbstract::new);
/* 421 */     register((Class)BlockCoralPlant.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCoralPlant::new);
/* 422 */     register((Class)BlockCrops.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftCrops::new);
/* 423 */     register((Class)BlockDaylightDetector.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftDaylightDetector::new);
/* 424 */     register((Class)BlockDirtSnow.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftDirtSnow::new);
/* 425 */     register((Class)BlockDispenser.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftDispenser::new);
/* 426 */     register((Class)BlockDoor.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftDoor::new);
/* 427 */     register((Class)BlockDropper.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftDropper::new);
/* 428 */     register((Class)BlockEndRod.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftEndRod::new);
/* 429 */     register((Class)BlockEnderChest.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftEnderChest::new);
/* 430 */     register((Class)BlockEnderPortalFrame.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftEnderPortalFrame::new);
/* 431 */     register((Class)BlockFence.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftFence::new);
/* 432 */     register((Class)BlockFenceGate.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftFenceGate::new);
/* 433 */     register((Class)BlockFire.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftFire::new);
/* 434 */     register((Class)BlockFloorSign.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftFloorSign::new);
/* 435 */     register((Class)BlockFluids.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftFluids::new);
/* 436 */     register((Class)BlockFurnaceFurace.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftFurnaceFurace::new);
/* 437 */     register((Class)BlockGlazedTerracotta.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftGlazedTerracotta::new);
/* 438 */     register((Class)BlockGrass.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftGrass::new);
/* 439 */     register((Class)BlockGrindstone.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftGrindstone::new);
/* 440 */     register((Class)BlockHay.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftHay::new);
/* 441 */     register((Class)BlockHopper.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftHopper::new);
/* 442 */     register((Class)BlockHugeMushroom.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftHugeMushroom::new);
/* 443 */     register((Class)BlockIceFrost.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftIceFrost::new);
/* 444 */     register((Class)BlockIronBars.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftIronBars::new);
/* 445 */     register((Class)BlockJigsaw.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftJigsaw::new);
/* 446 */     register((Class)BlockJukeBox.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftJukeBox::new);
/* 447 */     register((Class)BlockKelp.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftKelp::new);
/* 448 */     register((Class)BlockLadder.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftLadder::new);
/* 449 */     register((Class)BlockLantern.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftLantern::new);
/* 450 */     register((Class)BlockLeaves.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftLeaves::new);
/* 451 */     register((Class)BlockLectern.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftLectern::new);
/* 452 */     register((Class)BlockLever.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftLever::new);
/* 453 */     register((Class)BlockLoom.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftLoom::new);
/* 454 */     register((Class)BlockMinecartDetector.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftMinecartDetector::new);
/* 455 */     register((Class)BlockMinecartTrack.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftMinecartTrack::new);
/* 456 */     register((Class)BlockMycel.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftMycel::new);
/* 457 */     register((Class)BlockNetherWart.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftNetherWart::new);
/* 458 */     register((Class)BlockNote.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftNote::new);
/* 459 */     register((Class)BlockObserver.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftObserver::new);
/* 460 */     register((Class)BlockPiston.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftPiston::new);
/* 461 */     register((Class)BlockPistonExtension.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftPistonExtension::new);
/* 462 */     register((Class)BlockPistonMoving.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftPistonMoving::new);
/* 463 */     register((Class)BlockPortal.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftPortal::new);
/* 464 */     register((Class)BlockPotatoes.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftPotatoes::new);
/* 465 */     register((Class)BlockPoweredRail.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftPoweredRail::new);
/* 466 */     register((Class)BlockPressurePlateBinary.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftPressurePlateBinary::new);
/* 467 */     register((Class)BlockPressurePlateWeighted.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftPressurePlateWeighted::new);
/* 468 */     register((Class)BlockPumpkinCarved.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftPumpkinCarved::new);
/* 469 */     register((Class)BlockRedstoneComparator.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftRedstoneComparator::new);
/* 470 */     register((Class)BlockRedstoneLamp.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftRedstoneLamp::new);
/* 471 */     register((Class)BlockRedstoneOre.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftRedstoneOre::new);
/* 472 */     register((Class)BlockRedstoneTorch.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftRedstoneTorch::new);
/* 473 */     register((Class)BlockRedstoneTorchWall.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftRedstoneTorchWall::new);
/* 474 */     register((Class)BlockRedstoneWire.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftRedstoneWire::new);
/* 475 */     register((Class)BlockReed.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftReed::new);
/* 476 */     register((Class)BlockRepeater.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftRepeater::new);
/* 477 */     register((Class)BlockRespawnAnchor.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftRespawnAnchor::new);
/* 478 */     register((Class)BlockRotatable.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftRotatable::new);
/* 479 */     register((Class)BlockSapling.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftSapling::new);
/* 480 */     register((Class)BlockScaffolding.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftScaffolding::new);
/* 481 */     register((Class)BlockSeaPickle.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftSeaPickle::new);
/* 482 */     register((Class)BlockShulkerBox.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftShulkerBox::new);
/* 483 */     register((Class)BlockSkull.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftSkull::new);
/* 484 */     register((Class)BlockSkullPlayer.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftSkullPlayer::new);
/* 485 */     register((Class)BlockSkullPlayerWall.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftSkullPlayerWall::new);
/* 486 */     register((Class)BlockSkullWall.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftSkullWall::new);
/* 487 */     register((Class)BlockSmoker.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftSmoker::new);
/* 488 */     register((Class)BlockSnow.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftSnow::new);
/* 489 */     register((Class)BlockSoil.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftSoil::new);
/* 490 */     register((Class)BlockStainedGlassPane.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftStainedGlassPane::new);
/* 491 */     register((Class)BlockStairs.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftStairs::new);
/* 492 */     register((Class)BlockStem.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftStem::new);
/* 493 */     register((Class)BlockStemAttached.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftStemAttached::new);
/* 494 */     register((Class)BlockStepAbstract.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftStepAbstract::new);
/* 495 */     register((Class)BlockStoneButton.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftStoneButton::new);
/* 496 */     register((Class)BlockStonecutter.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftStonecutter::new);
/* 497 */     register((Class)BlockStructure.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftStructure::new);
/* 498 */     register((Class)BlockSweetBerryBush.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftSweetBerryBush::new);
/* 499 */     register((Class)BlockTNT.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTNT::new);
/* 500 */     register((Class)BlockTallPlant.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTallPlant::new);
/* 501 */     register((Class)BlockTallPlantFlower.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTallPlantFlower::new);
/* 502 */     register((Class)BlockTallSeaGrass.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTallSeaGrass::new);
/* 503 */     register((Class)BlockTarget.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTarget::new);
/* 504 */     register((Class)BlockTorchWall.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTorchWall::new);
/* 505 */     register((Class)BlockTrapdoor.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTrapdoor::new);
/* 506 */     register((Class)BlockTripwire.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTripwire::new);
/* 507 */     register((Class)BlockTripwireHook.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTripwireHook::new);
/* 508 */     register((Class)BlockTurtleEgg.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTurtleEgg::new);
/* 509 */     register((Class)BlockTwistingVines.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftTwistingVines::new);
/* 510 */     register((Class)BlockVine.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftVine::new);
/* 511 */     register((Class)BlockWallSign.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftWallSign::new);
/* 512 */     register((Class)BlockWeepingVines.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftWeepingVines::new);
/* 513 */     register((Class)BlockWitherSkull.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftWitherSkull::new);
/* 514 */     register((Class)BlockWitherSkullWall.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftWitherSkullWall::new);
/* 515 */     register((Class)BlockWoodButton.class, org.bukkit.craftbukkit.v1_16_R2.block.impl.CraftWoodButton::new);
/*     */   }
/*     */ 
/*     */   
/*     */   private static void register(Class<? extends Block> nms, Function<IBlockData, CraftBlockData> bukkit) {
/* 520 */     Preconditions.checkState((MAP.put(nms, bukkit) == null), "Duplicate mapping %s->%s", nms, bukkit);
/*     */   }
/*     */ 
/*     */   
/* 524 */   private static Map<String, CraftBlockData> stringDataCache = new HashMap<>();
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/* 529 */     reloadCache();
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
/* 589 */     Block.REGISTRY_ID.iterator().forEachRemaining(BlockBase.BlockData::createCraftBlockData);
/*     */   } public static void reloadCache() { stringDataCache.clear(); Block.REGISTRY_ID.forEach(blockData -> stringDataCache.put(blockData.toString(), blockData.createCraftBlockData())); } public static CraftBlockData newData(Material material, String data) { Preconditions.checkArgument((material == null || material.isBlock()), "Cannot get data for not block %s", material); if (material != null) { Block block = CraftMagicNumbers.getBlock(material); if (block != null) { MinecraftKey key = IRegistry.BLOCK.getKey(block); data = (data == null) ? key.toString() : (key + data); }  }  CraftBlockData cached = stringDataCache.computeIfAbsent(data, s -> createNewData(null, s)); return (CraftBlockData)cached.clone(); }
/*     */   private static CraftBlockData createNewData(Material material, String data) { IBlockData blockData; Block block = CraftMagicNumbers.getBlock(material); Map<IBlockState<?>, Comparable<?>> parsed = null; if (data != null) { try { if (block != null) data = IRegistry.BLOCK.getKey(block) + data;  StringReader reader = new StringReader(data); ArgumentBlock arg = (new ArgumentBlock(reader, false)).a(false); Preconditions.checkArgument(!reader.canRead(), "Spurious trailing data: " + data); blockData = arg.getBlockData(); parsed = arg.getStateMap(); } catch (CommandSyntaxException ex) { throw new IllegalArgumentException("Could not parse data: " + data, ex); }  } else { blockData = block.getBlockData(); }  CraftBlockData craft = fromData(blockData); craft.parsedStates = parsed; return craft; }
/* 592 */   public static CraftBlockData fromData(IBlockData data) { return data.createCraftBlockData(); }
/*     */ 
/*     */ 
/*     */   
/*     */   public static CraftBlockData createData(IBlockData data) {
/* 597 */     return (CraftBlockData)((Function)MAP.getOrDefault(data.getBlock().getClass(), CraftBlockData::new)).apply(data);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\CraftBlockData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */