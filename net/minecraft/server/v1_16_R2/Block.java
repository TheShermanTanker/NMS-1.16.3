/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import co.aikar.timings.Timing;
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.google.common.cache.CacheLoader;
/*     */ import com.google.common.cache.LoadingCache;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ByteLinkedOpenHashMap;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.entity.ExperienceOrb;
/*     */ 
/*     */ public class Block extends BlockBase implements IMaterial {
/*  16 */   protected static final Logger LOGGER = LogManager.getLogger();
/*  17 */   public static final RegistryBlockID<IBlockData> REGISTRY_ID = new RegistryBlockID<>();
/*  18 */   private static final LoadingCache<VoxelShape, Boolean> a = CacheBuilder.newBuilder().maximumSize(512L).weakKeys().build(new CacheLoader<VoxelShape, Boolean>() {
/*     */         public Boolean load(VoxelShape voxelshape) {
/*  20 */           return Boolean.valueOf(!VoxelShapes.c(VoxelShapes.b(), voxelshape, OperatorBoolean.NOT_SAME));
/*     */         }
/*     */       });
/*     */   protected final BlockStateList<Block, IBlockData> blockStateList; private IBlockData blockData;
/*     */   public Timing timing;
/*     */   
/*     */   public final boolean isDestroyable() {
/*  27 */     return (PaperConfig.allowBlockPermanentBreakingExploits || (this != Blocks.BEDROCK && this != Blocks.END_PORTAL_FRAME && this != Blocks.END_PORTAL && this != Blocks.END_GATEWAY && this != Blocks.COMMAND_BLOCK && this != Blocks.REPEATING_COMMAND_BLOCK && this != Blocks.CHAIN_COMMAND_BLOCK && this != Blocks.BARRIER && this != Blocks.STRUCTURE_BLOCK && this != Blocks.JIGSAW));
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private String name;
/*     */   
/*     */   @Nullable
/*     */   private Item d;
/*     */   
/*     */   private static final ThreadLocal<Object2ByteLinkedOpenHashMap<a>> e;
/*     */ 
/*     */   
/*     */   public Timing getTiming() {
/*  41 */     if (this.timing == null) {
/*  42 */       this.timing = MinecraftTimings.getBlockTiming(this);
/*     */     }
/*  44 */     return this.timing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   static {
/*  51 */     e = ThreadLocal.withInitial(() -> {
/*     */           Object2ByteLinkedOpenHashMap<a> object2bytelinkedopenhashmap = new Object2ByteLinkedOpenHashMap<a>(2048, 0.25F) {
/*     */               protected void rehash(int i) {}
/*     */             };
/*     */           object2bytelinkedopenhashmap.defaultReturnValue(127);
/*     */           return object2bytelinkedopenhashmap;
/*     */         });
/*     */   }
/*     */   
/*     */   public static int getCombinedId(@Nullable IBlockData iblockdata) {
/*  61 */     if (iblockdata == null) {
/*  62 */       return 0;
/*     */     }
/*  64 */     int i = REGISTRY_ID.getId(iblockdata);
/*     */     
/*  66 */     return (i == -1) ? 0 : i;
/*     */   }
/*     */ 
/*     */   
/*     */   public static IBlockData getByCombinedId(int i) {
/*  71 */     IBlockData iblockdata = REGISTRY_ID.fromId(i);
/*     */     
/*  73 */     return (iblockdata == null) ? Blocks.AIR.getBlockData() : iblockdata;
/*     */   }
/*     */   
/*     */   public static Block asBlock(@Nullable Item item) {
/*  77 */     return (item instanceof ItemBlock) ? ((ItemBlock)item).getBlock() : Blocks.AIR;
/*     */   }
/*     */   
/*     */   public static IBlockData a(IBlockData iblockdata, IBlockData iblockdata1, World world, BlockPosition blockposition) {
/*  81 */     VoxelShape voxelshape = VoxelShapes.b(iblockdata.getCollisionShape(world, blockposition), iblockdata1.getCollisionShape(world, blockposition), OperatorBoolean.ONLY_SECOND).a(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  82 */     List<Entity> list = world.getEntities((Entity)null, voxelshape.getBoundingBox());
/*  83 */     Iterator<Entity> iterator = list.iterator();
/*     */     
/*  85 */     while (iterator.hasNext()) {
/*  86 */       Entity entity = iterator.next();
/*  87 */       double d0 = VoxelShapes.a(EnumDirection.EnumAxis.Y, entity.getBoundingBox().d(0.0D, 1.0D, 0.0D), Stream.of(voxelshape), -1.0D);
/*     */       
/*  89 */       entity.enderTeleportTo(entity.locX(), entity.locY() + 1.0D + d0, entity.locZ());
/*     */     } 
/*     */     
/*  92 */     return iblockdata1;
/*     */   }
/*     */   
/*     */   public static VoxelShape a(double d0, double d1, double d2, double d3, double d4, double d5) {
/*  96 */     return VoxelShapes.create(d0 / 16.0D, d1 / 16.0D, d2 / 16.0D, d3 / 16.0D, d4 / 16.0D, d5 / 16.0D);
/*     */   }
/*     */   
/*     */   public boolean a(Tag<Block> tag) {
/* 100 */     return tag.isTagged(this);
/*     */   }
/*     */   
/*     */   public boolean a(Block block) {
/* 104 */     return (this == block);
/*     */   }
/*     */   
/*     */   public static IBlockData b(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 108 */     IBlockData iblockdata1 = iblockdata;
/* 109 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = new BlockPosition.MutableBlockPosition();
/* 110 */     EnumDirection[] aenumdirection = ar;
/* 111 */     int i = aenumdirection.length;
/*     */     
/* 113 */     for (int j = 0; j < i; j++) {
/* 114 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/* 116 */       blockposition_mutableblockposition.a(blockposition, enumdirection);
/* 117 */       iblockdata1 = iblockdata1.updateState(enumdirection, generatoraccess.getType(blockposition_mutableblockposition), generatoraccess, blockposition, blockposition_mutableblockposition);
/*     */     } 
/*     */     
/* 120 */     return iblockdata1;
/*     */   }
/*     */   
/*     */   public static void a(IBlockData iblockdata, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, int i) {
/* 124 */     a(iblockdata, iblockdata1, generatoraccess, blockposition, i, 512);
/*     */   }
/*     */   
/*     */   public static void a(IBlockData iblockdata, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, int i, int j) {
/* 128 */     if (iblockdata1 != iblockdata) {
/* 129 */       if (iblockdata1.isAir()) {
/* 130 */         if (!generatoraccess.s_()) {
/* 131 */           generatoraccess.a(blockposition, ((i & 0x20) == 0), (Entity)null, j);
/*     */         }
/*     */       } else {
/* 134 */         generatoraccess.a(blockposition, iblockdata1, i & 0xFFFFFFDF, j);
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Block(BlockBase.Info blockbase_info) {
/* 141 */     super(blockbase_info);
/* 142 */     BlockStateList.a<Block, IBlockData> blockstatelist_a = new BlockStateList.a<>(this);
/*     */     
/* 144 */     a(blockstatelist_a);
/* 145 */     this.blockStateList = blockstatelist_a.a(Block::getBlockData, IBlockData::new);
/* 146 */     j(this.blockStateList.getBlockData());
/*     */   }
/*     */   
/*     */   public static boolean b(Block block) {
/* 150 */     return (block instanceof BlockLeaves || block == Blocks.BARRIER || block == Blocks.CARVED_PUMPKIN || block == Blocks.JACK_O_LANTERN || block == Blocks.MELON || block == Blocks.PUMPKIN || block.a(TagsBlock.SHULKER_BOXES));
/*     */   }
/*     */   
/*     */   public boolean isTicking(IBlockData iblockdata) {
/* 154 */     return this.av;
/*     */   }
/*     */   
/*     */   public static boolean c(IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 158 */     return iblockaccess.getType(blockposition).a(iblockaccess, blockposition, EnumDirection.UP, EnumBlockSupport.RIGID);
/*     */   }
/*     */   
/*     */   public static boolean a(IWorldReader iworldreader, BlockPosition blockposition, EnumDirection enumdirection) {
/* 162 */     IBlockData iblockdata = iworldreader.getType(blockposition);
/*     */     
/* 164 */     return (enumdirection == EnumDirection.DOWN && iblockdata.a(TagsBlock.aC)) ? false : iblockdata.a(iworldreader, blockposition, enumdirection, EnumBlockSupport.CENTER);
/*     */   }
/*     */   
/*     */   public static boolean a(VoxelShape voxelshape, EnumDirection enumdirection) {
/* 168 */     VoxelShape voxelshape1 = voxelshape.a(enumdirection);
/*     */     
/* 170 */     return a(voxelshape1);
/*     */   }
/*     */   
/*     */   public static boolean a(VoxelShape voxelshape) {
/* 174 */     return ((Boolean)a.getUnchecked(voxelshape)).booleanValue();
/*     */   }
/*     */   
/*     */   public boolean b(IBlockData iblockdata, IBlockAccess iblockaccess, BlockPosition blockposition) {
/* 178 */     return (!a(iblockdata.getShape(iblockaccess, blockposition)) && iblockdata.getFluid().isEmpty());
/*     */   }
/*     */   
/*     */   public void postBreak(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata) {}
/*     */   
/*     */   public static List<ItemStack> a(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, @Nullable TileEntity tileentity) {
/* 184 */     LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder(worldserver)).a(worldserver.random).<Vec3D>set(LootContextParameters.ORIGIN, Vec3D.a(blockposition)).<ItemStack>set(LootContextParameters.TOOL, ItemStack.b).setOptional(LootContextParameters.BLOCK_ENTITY, tileentity);
/*     */     
/* 186 */     return iblockdata.a(loottableinfo_builder);
/*     */   }
/*     */   
/*     */   public static List<ItemStack> getDrops(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, @Nullable TileEntity tileentity, @Nullable Entity entity, ItemStack itemstack) {
/* 190 */     LootTableInfo.Builder loottableinfo_builder = (new LootTableInfo.Builder(worldserver)).a(worldserver.random).<Vec3D>set(LootContextParameters.ORIGIN, Vec3D.a(blockposition)).<ItemStack>set(LootContextParameters.TOOL, itemstack).<Entity>setOptional(LootContextParameters.THIS_ENTITY, entity).setOptional(LootContextParameters.BLOCK_ENTITY, tileentity);
/*     */     
/* 192 */     return iblockdata.a(loottableinfo_builder);
/*     */   }
/*     */   
/*     */   public static void c(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 196 */     if (world instanceof WorldServer) {
/* 197 */       a(iblockdata, (WorldServer)world, blockposition, (TileEntity)null).forEach(itemstack -> a(world, blockposition, itemstack));
/*     */ 
/*     */       
/* 200 */       iblockdata.dropNaturally((WorldServer)world, blockposition, ItemStack.b);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static void dropNaturally(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, @Nullable TileEntity tileentity) {
/* 205 */     a(iblockdata, generatoraccess, blockposition, tileentity);
/*     */   } public static void a(IBlockData iblockdata, GeneratorAccess generatoraccess, BlockPosition blockposition, @Nullable TileEntity tileentity) {
/* 207 */     if (generatoraccess instanceof WorldServer) {
/* 208 */       a(iblockdata, (WorldServer)generatoraccess, blockposition, tileentity).forEach(itemstack -> a((WorldServer)generatoraccess, blockposition, itemstack));
/*     */ 
/*     */       
/* 211 */       iblockdata.dropNaturally((WorldServer)generatoraccess, blockposition, ItemStack.b);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void dropItems(IBlockData iblockdata, World world, BlockPosition blockposition, @Nullable TileEntity tileentity, Entity entity, ItemStack itemstack) {
/* 217 */     if (world instanceof WorldServer) {
/* 218 */       getDrops(iblockdata, (WorldServer)world, blockposition, tileentity, entity, itemstack).forEach(itemstack1 -> a(world, blockposition, itemstack1));
/*     */ 
/*     */       
/* 221 */       iblockdata.dropNaturally((WorldServer)world, blockposition, itemstack);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(World world, BlockPosition blockposition, ItemStack itemstack) {
/* 227 */     if (!world.isClientSide && !itemstack.isEmpty() && world.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
/* 228 */       float f = 0.5F;
/* 229 */       double d0 = (world.random.nextFloat() * 0.5F) + 0.25D;
/* 230 */       double d1 = (world.random.nextFloat() * 0.5F) + 0.25D;
/* 231 */       double d2 = (world.random.nextFloat() * 0.5F) + 0.25D;
/* 232 */       EntityItem entityitem = new EntityItem(world, blockposition.getX() + d0, blockposition.getY() + d1, blockposition.getZ() + d2, itemstack);
/*     */       
/* 234 */       entityitem.defaultPickupDelay();
/*     */       
/* 236 */       if (world.captureDrops != null) {
/* 237 */         world.captureDrops.add(entityitem);
/*     */       } else {
/* 239 */         world.addEntity(entityitem);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   protected void dropExperience(WorldServer worldserver, BlockPosition blockposition, int i, EntityPlayer player) {
/* 246 */     if (worldserver.getGameRules().getBoolean(GameRules.DO_TILE_DROPS)) {
/* 247 */       while (i > 0) {
/* 248 */         int j = EntityExperienceOrb.getOrbValue(i);
/*     */         
/* 250 */         i -= j;
/* 251 */         worldserver.addEntity(new EntityExperienceOrb(worldserver, blockposition.getX() + 0.5D, blockposition.getY() + 0.5D, blockposition.getZ() + 0.5D, j, ExperienceOrb.SpawnReason.BLOCK_BREAK, player));
/*     */       } 
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public float getDurability() {
/* 258 */     return this.durability;
/*     */   }
/*     */   
/*     */   public void wasExploded(World world, BlockPosition blockposition, Explosion explosion) {}
/*     */   
/*     */   public void stepOn(World world, BlockPosition blockposition, Entity entity) {}
/*     */   
/*     */   @Nullable
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 267 */     return getBlockData();
/*     */   }
/*     */   
/*     */   public void a(World world, EntityHuman entityhuman, BlockPosition blockposition, IBlockData iblockdata, @Nullable TileEntity tileentity, ItemStack itemstack) {
/* 271 */     entityhuman.b(StatisticList.BLOCK_MINED.b(this));
/* 272 */     entityhuman.applyExhaustion(0.005F);
/* 273 */     dropItems(iblockdata, world, blockposition, tileentity, entityhuman, itemstack);
/*     */   }
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, @Nullable EntityLiving entityliving, ItemStack itemstack) {}
/*     */   
/*     */   public boolean ai_() {
/* 279 */     return (!this.material.isBuildable() && !this.material.isLiquid());
/*     */   }
/*     */   public String getDescriptionId() {
/* 282 */     return i();
/*     */   } public String i() {
/* 284 */     if (this.name == null) {
/* 285 */       this.name = SystemUtils.a("block", IRegistry.BLOCK.getKey(this));
/*     */     }
/*     */     
/* 288 */     return this.name;
/*     */   }
/*     */   
/*     */   public void fallOn(World world, BlockPosition blockposition, Entity entity, float f) {
/* 292 */     entity.b(f, 1.0F);
/*     */   }
/*     */   
/*     */   public void a(IBlockAccess iblockaccess, Entity entity) {
/* 296 */     entity.setMot(entity.getMot().d(1.0D, 0.0D, 1.0D));
/*     */   }
/*     */   
/*     */   public void a(CreativeModeTab creativemodetab, NonNullList<ItemStack> nonnulllist) {
/* 300 */     nonnulllist.add(new ItemStack(this));
/*     */   }
/*     */   
/*     */   public float getFrictionFactor() {
/* 304 */     return this.frictionFactor;
/*     */   }
/*     */   
/*     */   public float getSpeedFactor() {
/* 308 */     return this.speedFactor;
/*     */   }
/*     */   
/*     */   public float getJumpFactor() {
/* 312 */     return this.jumpFactor;
/*     */   }
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, EntityHuman entityhuman) {
/* 316 */     world.a(entityhuman, 2001, blockposition, getCombinedId(iblockdata));
/* 317 */     if (a(TagsBlock.GUARDED_BY_PIGLINS)) {
/* 318 */       PiglinAI.a(entityhuman, false);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void c(World world, BlockPosition blockposition) {}
/*     */   
/*     */   public boolean a(Explosion explosion) {
/* 326 */     return true;
/*     */   }
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {}
/*     */   
/*     */   public BlockStateList<Block, IBlockData> getStates() {
/* 332 */     return this.blockStateList;
/*     */   }
/*     */   
/*     */   protected final void j(IBlockData iblockdata) {
/* 336 */     this.blockData = iblockdata;
/*     */   }
/*     */   
/*     */   public final IBlockData getBlockData() {
/* 340 */     return this.blockData;
/*     */   }
/*     */   
/*     */   public SoundEffectType getStepSound(IBlockData iblockdata) {
/* 344 */     return this.stepSound;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item getItem() {
/* 349 */     if (this.d == null) {
/* 350 */       this.d = Item.getItemOf(this);
/*     */     }
/*     */     
/* 353 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean o() {
/* 357 */     return this.aA;
/*     */   }
/*     */   
/*     */   public String toString() {
/* 361 */     return "Block{" + IRegistry.BLOCK.getKey(this) + "}";
/*     */   }
/*     */ 
/*     */   
/*     */   protected Block p() {
/* 366 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public int getExpDrop(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, ItemStack itemstack) {
/* 371 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static float range(float min, float value, float max) {
/* 377 */     if (value < min) {
/* 378 */       return min;
/*     */     }
/* 380 */     if (value > max) {
/* 381 */       return max;
/*     */     }
/* 383 */     return value;
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class a
/*     */   {
/*     */     private final IBlockData a;
/*     */     private final IBlockData b;
/*     */     private final EnumDirection c;
/*     */     
/*     */     public a(IBlockData iblockdata, IBlockData iblockdata1, EnumDirection enumdirection) {
/* 394 */       this.a = iblockdata;
/* 395 */       this.b = iblockdata1;
/* 396 */       this.c = enumdirection;
/*     */     }
/*     */     
/*     */     public boolean equals(Object object) {
/* 400 */       if (this == object)
/* 401 */         return true; 
/* 402 */       if (!(object instanceof a)) {
/* 403 */         return false;
/*     */       }
/* 405 */       a block_a = (a)object;
/*     */       
/* 407 */       return (this.a == block_a.a && this.b == block_a.b && this.c == block_a.c);
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 412 */       int i = this.a.hashCode();
/*     */       
/* 414 */       i = 31 * i + this.b.hashCode();
/* 415 */       i = 31 * i + this.c.hashCode();
/* 416 */       return i;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Block.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */