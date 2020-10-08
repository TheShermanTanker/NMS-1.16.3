/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import java.util.Set;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class TileEntityTypes<T extends TileEntity>
/*     */ {
/*  22 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   @Nullable
/*     */   public static MinecraftKey a(TileEntityTypes<?> var0) {
/*  26 */     return IRegistry.BLOCK_ENTITY_TYPE.getKey(var0);
/*     */   }
/*     */   
/*  29 */   public static final TileEntityTypes<TileEntityFurnaceFurnace> FURNACE = a("furnace", a.a(TileEntityFurnaceFurnace::new, new Block[] { Blocks.FURNACE }));
/*  30 */   public static final TileEntityTypes<TileEntityChest> CHEST = a("chest", a.a(TileEntityChest::new, new Block[] { Blocks.CHEST }));
/*  31 */   public static final TileEntityTypes<TileEntityChestTrapped> TRAPPED_CHEST = a("trapped_chest", a.a(TileEntityChestTrapped::new, new Block[] { Blocks.TRAPPED_CHEST }));
/*  32 */   public static final TileEntityTypes<TileEntityEnderChest> ENDER_CHEST = a("ender_chest", a.a(TileEntityEnderChest::new, new Block[] { Blocks.ENDER_CHEST }));
/*  33 */   public static final TileEntityTypes<TileEntityJukeBox> JUKEBOX = a("jukebox", a.a(TileEntityJukeBox::new, new Block[] { Blocks.JUKEBOX }));
/*  34 */   public static final TileEntityTypes<TileEntityDispenser> DISPENSER = a("dispenser", a.a(TileEntityDispenser::new, new Block[] { Blocks.DISPENSER }));
/*  35 */   public static final TileEntityTypes<TileEntityDropper> DROPPER = a("dropper", a.a(TileEntityDropper::new, new Block[] { Blocks.DROPPER }));
/*  36 */   public static final TileEntityTypes<TileEntitySign> SIGN = a("sign", a.a(TileEntitySign::new, new Block[] { Blocks.OAK_SIGN, Blocks.SPRUCE_SIGN, Blocks.BIRCH_SIGN, Blocks.ACACIA_SIGN, Blocks.JUNGLE_SIGN, Blocks.DARK_OAK_SIGN, Blocks.OAK_WALL_SIGN, Blocks.SPRUCE_WALL_SIGN, Blocks.BIRCH_WALL_SIGN, Blocks.ACACIA_WALL_SIGN, Blocks.JUNGLE_WALL_SIGN, Blocks.DARK_OAK_WALL_SIGN, Blocks.CRIMSON_SIGN, Blocks.CRIMSON_WALL_SIGN, Blocks.WARPED_SIGN, Blocks.WARPED_WALL_SIGN }));
/*  37 */   public static final TileEntityTypes<TileEntityMobSpawner> MOB_SPAWNER = a("mob_spawner", a.a(TileEntityMobSpawner::new, new Block[] { Blocks.SPAWNER }));
/*  38 */   public static final TileEntityTypes<TileEntityPiston> PISTON = a("piston", a.a(TileEntityPiston::new, new Block[] { Blocks.MOVING_PISTON }));
/*  39 */   public static final TileEntityTypes<TileEntityBrewingStand> BREWING_STAND = a("brewing_stand", a.a(TileEntityBrewingStand::new, new Block[] { Blocks.BREWING_STAND }));
/*  40 */   public static final TileEntityTypes<TileEntityEnchantTable> ENCHANTING_TABLE = a("enchanting_table", a.a(TileEntityEnchantTable::new, new Block[] { Blocks.ENCHANTING_TABLE }));
/*  41 */   public static final TileEntityTypes<TileEntityEnderPortal> END_PORTAL = a("end_portal", a.a(TileEntityEnderPortal::new, new Block[] { Blocks.END_PORTAL }));
/*  42 */   public static final TileEntityTypes<TileEntityBeacon> BEACON = a("beacon", a.a(TileEntityBeacon::new, new Block[] { Blocks.BEACON }));
/*  43 */   public static final TileEntityTypes<TileEntitySkull> SKULL = a("skull", a.a(TileEntitySkull::new, new Block[] { Blocks.SKELETON_SKULL, Blocks.SKELETON_WALL_SKULL, Blocks.CREEPER_HEAD, Blocks.CREEPER_WALL_HEAD, Blocks.DRAGON_HEAD, Blocks.DRAGON_WALL_HEAD, Blocks.ZOMBIE_HEAD, Blocks.ZOMBIE_WALL_HEAD, Blocks.WITHER_SKELETON_SKULL, Blocks.WITHER_SKELETON_WALL_SKULL, Blocks.PLAYER_HEAD, Blocks.PLAYER_WALL_HEAD }));
/*  44 */   public static final TileEntityTypes<TileEntityLightDetector> DAYLIGHT_DETECTOR = a("daylight_detector", a.a(TileEntityLightDetector::new, new Block[] { Blocks.DAYLIGHT_DETECTOR }));
/*  45 */   public static final TileEntityTypes<TileEntityHopper> HOPPER = a("hopper", a.a(TileEntityHopper::new, new Block[] { Blocks.HOPPER }));
/*  46 */   public static final TileEntityTypes<TileEntityComparator> COMPARATOR = a("comparator", a.a(TileEntityComparator::new, new Block[] { Blocks.COMPARATOR }));
/*  47 */   public static final TileEntityTypes<TileEntityBanner> BANNER = a("banner", a.a(TileEntityBanner::new, new Block[] { Blocks.WHITE_BANNER, Blocks.ORANGE_BANNER, Blocks.MAGENTA_BANNER, Blocks.LIGHT_BLUE_BANNER, Blocks.YELLOW_BANNER, Blocks.LIME_BANNER, Blocks.PINK_BANNER, Blocks.GRAY_BANNER, Blocks.LIGHT_GRAY_BANNER, Blocks.CYAN_BANNER, Blocks.PURPLE_BANNER, Blocks.BLUE_BANNER, Blocks.BROWN_BANNER, Blocks.GREEN_BANNER, Blocks.RED_BANNER, Blocks.BLACK_BANNER, Blocks.WHITE_WALL_BANNER, Blocks.ORANGE_WALL_BANNER, Blocks.MAGENTA_WALL_BANNER, Blocks.LIGHT_BLUE_WALL_BANNER, Blocks.YELLOW_WALL_BANNER, Blocks.LIME_WALL_BANNER, Blocks.PINK_WALL_BANNER, Blocks.GRAY_WALL_BANNER, Blocks.LIGHT_GRAY_WALL_BANNER, Blocks.CYAN_WALL_BANNER, Blocks.PURPLE_WALL_BANNER, Blocks.BLUE_WALL_BANNER, Blocks.BROWN_WALL_BANNER, Blocks.GREEN_WALL_BANNER, Blocks.RED_WALL_BANNER, Blocks.BLACK_WALL_BANNER }));
/*  48 */   public static final TileEntityTypes<TileEntityStructure> STRUCTURE_BLOCK = a("structure_block", a.a(TileEntityStructure::new, new Block[] { Blocks.STRUCTURE_BLOCK }));
/*  49 */   public static final TileEntityTypes<TileEntityEndGateway> END_GATEWAY = a("end_gateway", a.a(TileEntityEndGateway::new, new Block[] { Blocks.END_GATEWAY }));
/*  50 */   public static final TileEntityTypes<TileEntityCommand> COMMAND_BLOCK = a("command_block", a.a(TileEntityCommand::new, new Block[] { Blocks.COMMAND_BLOCK, Blocks.CHAIN_COMMAND_BLOCK, Blocks.REPEATING_COMMAND_BLOCK }));
/*  51 */   public static final TileEntityTypes<TileEntityShulkerBox> SHULKER_BOX = a("shulker_box", a.a(TileEntityShulkerBox::new, new Block[] { Blocks.SHULKER_BOX, Blocks.BLACK_SHULKER_BOX, Blocks.BLUE_SHULKER_BOX, Blocks.BROWN_SHULKER_BOX, Blocks.CYAN_SHULKER_BOX, Blocks.GRAY_SHULKER_BOX, Blocks.GREEN_SHULKER_BOX, Blocks.LIGHT_BLUE_SHULKER_BOX, Blocks.LIGHT_GRAY_SHULKER_BOX, Blocks.LIME_SHULKER_BOX, Blocks.MAGENTA_SHULKER_BOX, Blocks.ORANGE_SHULKER_BOX, Blocks.PINK_SHULKER_BOX, Blocks.PURPLE_SHULKER_BOX, Blocks.RED_SHULKER_BOX, Blocks.WHITE_SHULKER_BOX, Blocks.YELLOW_SHULKER_BOX }));
/*  52 */   public static final TileEntityTypes<TileEntityBed> BED = a("bed", a.a(TileEntityBed::new, new Block[] { Blocks.RED_BED, Blocks.BLACK_BED, Blocks.BLUE_BED, Blocks.BROWN_BED, Blocks.CYAN_BED, Blocks.GRAY_BED, Blocks.GREEN_BED, Blocks.LIGHT_BLUE_BED, Blocks.LIGHT_GRAY_BED, Blocks.LIME_BED, Blocks.MAGENTA_BED, Blocks.ORANGE_BED, Blocks.PINK_BED, Blocks.PURPLE_BED, Blocks.WHITE_BED, Blocks.YELLOW_BED }));
/*  53 */   public static final TileEntityTypes<TileEntityConduit> CONDUIT = a("conduit", a.a(TileEntityConduit::new, new Block[] { Blocks.CONDUIT }));
/*  54 */   public static final TileEntityTypes<TileEntityBarrel> BARREL = a("barrel", a.a(TileEntityBarrel::new, new Block[] { Blocks.BARREL }));
/*  55 */   public static final TileEntityTypes<TileEntitySmoker> SMOKER = a("smoker", a.a(TileEntitySmoker::new, new Block[] { Blocks.SMOKER }));
/*  56 */   public static final TileEntityTypes<TileEntityBlastFurnace> BLAST_FURNACE = a("blast_furnace", a.a(TileEntityBlastFurnace::new, new Block[] { Blocks.BLAST_FURNACE }));
/*  57 */   public static final TileEntityTypes<TileEntityLectern> LECTERN = a("lectern", a.a(TileEntityLectern::new, new Block[] { Blocks.LECTERN }));
/*  58 */   public static final TileEntityTypes<TileEntityBell> BELL = a("bell", a.a(TileEntityBell::new, new Block[] { Blocks.BELL }));
/*  59 */   public static final TileEntityTypes<TileEntityJigsaw> JIGSAW = a("jigsaw", a.a(TileEntityJigsaw::new, new Block[] { Blocks.JIGSAW }));
/*  60 */   public static final TileEntityTypes<TileEntityCampfire> CAMPFIRE = a("campfire", a.a(TileEntityCampfire::new, new Block[] { Blocks.CAMPFIRE, Blocks.SOUL_CAMPFIRE }));
/*  61 */   public static final TileEntityTypes<TileEntityBeehive> BEEHIVE = a("beehive", a.a(TileEntityBeehive::new, new Block[] { Blocks.BEE_NEST, Blocks.BEEHIVE })); private final Supplier<? extends T> I;
/*     */   
/*     */   private static <T extends TileEntity> TileEntityTypes<T> a(String var0, a<T> var1) {
/*  64 */     if (a.a(var1).isEmpty()) {
/*  65 */       LOGGER.warn("Block entity type {} requires at least one valid block to be defined!", var0);
/*     */     }
/*  67 */     Type<?> var2 = SystemUtils.a(DataConverterTypes.BLOCK_ENTITY, var0);
/*  68 */     return (TileEntityTypes<T>)IRegistry.<TileEntityTypes<?>>a(IRegistry.BLOCK_ENTITY_TYPE, var0, var1.a(var2));
/*     */   }
/*     */ 
/*     */   
/*     */   private final Set<Block> J;
/*     */   private final Type<?> K;
/*     */   
/*     */   public TileEntityTypes(Supplier<? extends T> var0, Set<Block> var1, Type<?> var2) {
/*  76 */     this.I = var0;
/*  77 */     this.J = var1;
/*  78 */     this.K = var2;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public T a() {
/*  83 */     return this.I.get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValidBlock(Block var0) {
/*  90 */     return this.J.contains(var0);
/*     */   }
/*     */   
/*     */   public static final class a<T extends TileEntity> {
/*     */     private final Supplier<? extends T> a;
/*     */     private final Set<Block> b;
/*     */     
/*     */     private a(Supplier<? extends T> var0, Set<Block> var1) {
/*  98 */       this.a = var0;
/*  99 */       this.b = var1;
/*     */     }
/*     */     
/*     */     public static <T extends TileEntity> a<T> a(Supplier<? extends T> var0, Block... var1) {
/* 103 */       return new a<>(var0, (Set<Block>)ImmutableSet.copyOf((Object[])var1));
/*     */     }
/*     */     
/*     */     public TileEntityTypes<T> a(Type<?> var0) {
/* 107 */       return new TileEntityTypes<>(this.a, this.b, var0);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public T a(IBlockAccess var0, BlockPosition var1) {
/* 114 */     TileEntity var2 = var0.getTileEntity(var1);
/* 115 */     if (var2 == null || var2.getTileType() != this) {
/* 116 */       return null;
/*     */     }
/* 118 */     return (T)var2;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityTypes.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */