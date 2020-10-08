/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.BlockStateListPopulator;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockPumpkinCarved
/*     */   extends BlockFacingHorizontal
/*     */   implements ItemWearable
/*     */ {
/*  15 */   public static final BlockStateDirection a = BlockFacingHorizontal.FACING;
/*     */   
/*     */   @Nullable
/*     */   private ShapeDetector b;
/*     */   
/*     */   @Nullable
/*     */   private ShapeDetector c;
/*     */   
/*     */   static {
/*  24 */     f = (iblockdata -> 
/*  25 */       (iblockdata != null && (iblockdata.a(Blocks.CARVED_PUMPKIN) || iblockdata.a(Blocks.JACK_O_LANTERN))));
/*     */   } @Nullable
/*     */   private ShapeDetector d; @Nullable
/*     */   private ShapeDetector e; private static final Predicate<IBlockData> f; protected BlockPumpkinCarved(BlockBase.Info blockbase_info) {
/*  29 */     super(blockbase_info);
/*  30 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH));
/*     */   }
/*     */ 
/*     */   
/*     */   public void onPlace(IBlockData iblockdata, World world, BlockPosition blockposition, IBlockData iblockdata1, boolean flag) {
/*  35 */     if (!iblockdata1.a(iblockdata.getBlock())) {
/*  36 */       a(world, blockposition);
/*     */     }
/*     */   }
/*     */   
/*     */   public boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
/*  41 */     return (c().a(iworldreader, blockposition) != null || e().a(iworldreader, blockposition) != null);
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition) {
/*  45 */     ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = getSnowmanShape().a(world, blockposition);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  51 */     BlockStateListPopulator blockList = new BlockStateListPopulator(world);
/*  52 */     if (shapedetector_shapedetectorcollection != null) {
/*  53 */       for (int i = 0; i < getSnowmanShape().b(); i++) {
/*  54 */         ShapeDetectorBlock shapedetectorblock = shapedetector_shapedetectorcollection.a(0, i, 0);
/*     */         
/*  56 */         blockList.setTypeAndData(shapedetectorblock.getPosition(), Blocks.AIR.getBlockData(), 2);
/*     */       } 
/*     */ 
/*     */       
/*  60 */       EntitySnowman entitysnowman = EntityTypes.SNOW_GOLEM.a(world);
/*  61 */       BlockPosition blockposition1 = shapedetector_shapedetectorcollection.a(0, 2, 0).getPosition();
/*     */       
/*  63 */       entitysnowman.setPositionRotation(blockposition1.getX() + 0.5D, blockposition1.getY() + 0.05D, blockposition1.getZ() + 0.5D, 0.0F, 0.0F);
/*     */       
/*  65 */       if (!world.addEntity(entitysnowman, CreatureSpawnEvent.SpawnReason.BUILD_SNOWMAN)) {
/*     */         return;
/*     */       }
/*  68 */       for (BlockPosition pos : blockList.getBlocks()) {
/*  69 */         world.triggerEffect(2001, pos, Block.getCombinedId(world.getType(pos)));
/*     */       }
/*  71 */       blockList.updateList();
/*     */       
/*  73 */       Iterator<EntityPlayer> iterator = world.<EntityPlayer>a(EntityPlayer.class, entitysnowman.getBoundingBox().g(5.0D)).iterator();
/*     */       
/*  75 */       while (iterator.hasNext()) {
/*  76 */         EntityPlayer entityplayer = iterator.next();
/*  77 */         CriterionTriggers.n.a(entityplayer, entitysnowman);
/*     */       } 
/*     */       
/*  80 */       for (int j = 0; j < getSnowmanShape().b(); j++) {
/*  81 */         ShapeDetectorBlock shapedetectorblock1 = shapedetector_shapedetectorcollection.a(0, j, 0);
/*     */         
/*  83 */         world.update(shapedetectorblock1.getPosition(), Blocks.AIR);
/*     */       } 
/*     */     } else {
/*  86 */       shapedetector_shapedetectorcollection = getIronGolemShape().a(world, blockposition);
/*  87 */       if (shapedetector_shapedetectorcollection != null) {
/*  88 */         for (int i = 0; i < getIronGolemShape().c(); i++) {
/*  89 */           for (int k = 0; k < getIronGolemShape().b(); k++) {
/*  90 */             ShapeDetectorBlock shapedetectorblock2 = shapedetector_shapedetectorcollection.a(i, k, 0);
/*     */             
/*  92 */             blockList.setTypeAndData(shapedetectorblock2.getPosition(), Blocks.AIR.getBlockData(), 2);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/*  97 */         BlockPosition blockposition2 = shapedetector_shapedetectorcollection.a(1, 2, 0).getPosition();
/*  98 */         EntityIronGolem entityirongolem = EntityTypes.IRON_GOLEM.a(world);
/*     */         
/* 100 */         entityirongolem.setPlayerCreated(true);
/* 101 */         entityirongolem.setPositionRotation(blockposition2.getX() + 0.5D, blockposition2.getY() + 0.05D, blockposition2.getZ() + 0.5D, 0.0F, 0.0F);
/*     */         
/* 103 */         if (!world.addEntity(entityirongolem, CreatureSpawnEvent.SpawnReason.BUILD_IRONGOLEM)) {
/*     */           return;
/*     */         }
/* 106 */         for (BlockPosition pos : blockList.getBlocks()) {
/* 107 */           world.triggerEffect(2001, pos, Block.getCombinedId(world.getType(pos)));
/*     */         }
/* 109 */         blockList.updateList();
/*     */         
/* 111 */         Iterator<EntityPlayer> iterator = world.<EntityPlayer>a(EntityPlayer.class, entityirongolem.getBoundingBox().g(5.0D)).iterator();
/*     */         
/* 113 */         while (iterator.hasNext()) {
/* 114 */           EntityPlayer entityplayer = iterator.next();
/* 115 */           CriterionTriggers.n.a(entityplayer, entityirongolem);
/*     */         } 
/*     */         
/* 118 */         for (int j = 0; j < getIronGolemShape().c(); j++) {
/* 119 */           for (int l = 0; l < getIronGolemShape().b(); l++) {
/* 120 */             ShapeDetectorBlock shapedetectorblock3 = shapedetector_shapedetectorcollection.a(j, l, 0);
/*     */             
/* 122 */             world.update(shapedetectorblock3.getPosition(), Blocks.AIR);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 132 */     return getBlockData().set(a, blockactioncontext.f().opposite());
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 137 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a });
/*     */   }
/*     */   
/*     */   private ShapeDetector c() {
/* 141 */     if (this.b == null) {
/* 142 */       this.b = ShapeDetectorBuilder.a().a(new String[] { " ", "#", "#" }).a('#', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.SNOW_BLOCK))).b();
/*     */     }
/*     */     
/* 145 */     return this.b;
/*     */   }
/*     */   
/*     */   private ShapeDetector getSnowmanShape() {
/* 149 */     if (this.c == null) {
/* 150 */       this.c = ShapeDetectorBuilder.a().a(new String[] { "^", "#", "#" }).a('^', ShapeDetectorBlock.a(f)).a('#', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.SNOW_BLOCK))).b();
/*     */     }
/*     */     
/* 153 */     return this.c;
/*     */   }
/*     */   
/*     */   private ShapeDetector e() {
/* 157 */     if (this.d == null) {
/* 158 */       this.d = ShapeDetectorBuilder.a().a(new String[] { "~ ~", "###", "~#~" }).a('#', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.IRON_BLOCK))).a('~', ShapeDetectorBlock.a(MaterialPredicate.a(Material.AIR))).b();
/*     */     }
/*     */     
/* 161 */     return this.d;
/*     */   }
/*     */   
/*     */   private ShapeDetector getIronGolemShape() {
/* 165 */     if (this.e == null) {
/* 166 */       this.e = ShapeDetectorBuilder.a().a(new String[] { "~^~", "###", "~#~" }).a('^', ShapeDetectorBlock.a(f)).a('#', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.IRON_BLOCK))).a('~', ShapeDetectorBlock.a(MaterialPredicate.a(Material.AIR))).b();
/*     */     }
/*     */     
/* 169 */     return this.e;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPumpkinCarved.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */