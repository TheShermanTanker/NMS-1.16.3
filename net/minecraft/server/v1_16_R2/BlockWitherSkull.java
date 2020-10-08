/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.BlockStateListPopulator;
/*     */ import org.bukkit.event.entity.CreatureSpawnEvent;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class BlockWitherSkull
/*     */   extends BlockSkull
/*     */ {
/*     */   @Nullable
/*     */   private static ShapeDetector c;
/*     */   @Nullable
/*     */   private static ShapeDetector d;
/*     */   
/*     */   protected BlockWitherSkull(BlockBase.Info blockbase_info) {
/*  19 */     super(BlockSkull.Type.WITHER_SKELETON, blockbase_info);
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, @Nullable EntityLiving entityliving, ItemStack itemstack) {
/*  24 */     super.postPlace(world, blockposition, iblockdata, entityliving, itemstack);
/*  25 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/*  27 */     if (tileentity instanceof TileEntitySkull) {
/*  28 */       a(world, blockposition, (TileEntitySkull)tileentity);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public static void a(World world, BlockPosition blockposition, TileEntitySkull tileentityskull) {
/*  34 */     if (world.captureBlockStates)
/*  35 */       return;  if (!world.isClientSide) {
/*  36 */       IBlockData iblockdata = tileentityskull.getBlock();
/*  37 */       boolean flag = (iblockdata.a(Blocks.WITHER_SKELETON_SKULL) || iblockdata.a(Blocks.WITHER_SKELETON_WALL_SKULL));
/*     */       
/*  39 */       if (flag && blockposition.getY() >= 0 && world.getDifficulty() != EnumDifficulty.PEACEFUL) {
/*  40 */         ShapeDetector shapedetector = c();
/*  41 */         ShapeDetector.ShapeDetectorCollection shapedetector_shapedetectorcollection = shapedetector.a(world, blockposition);
/*     */         
/*  43 */         if (shapedetector_shapedetectorcollection != null) {
/*     */           
/*  45 */           BlockStateListPopulator blockList = new BlockStateListPopulator(world);
/*  46 */           for (int i = 0; i < shapedetector.c(); i++) {
/*  47 */             for (int j = 0; j < shapedetector.b(); j++) {
/*  48 */               ShapeDetectorBlock shapedetectorblock = shapedetector_shapedetectorcollection.a(i, j, 0);
/*     */               
/*  50 */               blockList.setTypeAndData(shapedetectorblock.getPosition(), Blocks.AIR.getBlockData(), 2);
/*     */             } 
/*     */           } 
/*     */ 
/*     */           
/*  55 */           EntityWither entitywither = EntityTypes.WITHER.a(world);
/*  56 */           BlockPosition blockposition1 = shapedetector_shapedetectorcollection.a(1, 2, 0).getPosition();
/*     */           
/*  58 */           entitywither.setPositionRotation(blockposition1.getX() + 0.5D, blockposition1.getY() + 0.55D, blockposition1.getZ() + 0.5D, (shapedetector_shapedetectorcollection.getFacing().n() == EnumDirection.EnumAxis.X) ? 0.0F : 90.0F, 0.0F);
/*  59 */           entitywither.aA = (shapedetector_shapedetectorcollection.getFacing().n() == EnumDirection.EnumAxis.X) ? 0.0F : 90.0F;
/*  60 */           entitywither.beginSpawnSequence();
/*     */           
/*  62 */           if (!world.addEntity(entitywither, CreatureSpawnEvent.SpawnReason.BUILD_WITHER)) {
/*     */             return;
/*     */           }
/*  65 */           for (BlockPosition pos : blockList.getBlocks()) {
/*  66 */             world.triggerEffect(2001, pos, Block.getCombinedId(world.getType(pos)));
/*     */           }
/*  68 */           blockList.updateList();
/*     */           
/*  70 */           Iterator<EntityPlayer> iterator = world.<EntityPlayer>a(EntityPlayer.class, entitywither.getBoundingBox().g(50.0D)).iterator();
/*     */           
/*  72 */           while (iterator.hasNext()) {
/*  73 */             EntityPlayer entityplayer = iterator.next();
/*     */             
/*  75 */             CriterionTriggers.n.a(entityplayer, entitywither);
/*     */           } 
/*     */ 
/*     */ 
/*     */           
/*  80 */           for (int k = 0; k < shapedetector.c(); k++) {
/*  81 */             for (int l = 0; l < shapedetector.b(); l++) {
/*  82 */               world.update(shapedetector_shapedetectorcollection.a(k, l, 0).getPosition(), Blocks.AIR);
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean b(World world, BlockPosition blockposition, ItemStack itemstack) {
/*  92 */     return (itemstack.getItem() == Items.WITHER_SKELETON_SKULL && blockposition.getY() >= 2 && world.getDifficulty() != EnumDifficulty.PEACEFUL && !world.isClientSide) ? ((d().a(world, blockposition) != null)) : false;
/*     */   }
/*     */   
/*     */   private static ShapeDetector c() {
/*  96 */     if (c == null)
/*     */     {
/*     */       
/*  99 */       c = ShapeDetectorBuilder.a().a(new String[] { "^^^", "###", "~#~" }).a('#', shapedetectorblock -> shapedetectorblock.a().a(TagsBlock.WITHER_SUMMON_BASE_BLOCKS)).a('^', ShapeDetectorBlock.a(BlockStatePredicate.a(Blocks.WITHER_SKELETON_SKULL).or(BlockStatePredicate.a(Blocks.WITHER_SKELETON_WALL_SKULL)))).a('~', ShapeDetectorBlock.a(MaterialPredicate.a(Material.AIR))).b();
/*     */     }
/*     */     
/* 102 */     return c;
/*     */   }
/*     */   
/*     */   private static ShapeDetector d() {
/* 106 */     if (d == null)
/*     */     {
/*     */       
/* 109 */       d = ShapeDetectorBuilder.a().a(new String[] { "   ", "###", "~#~" }).a('#', shapedetectorblock -> shapedetectorblock.a().a(TagsBlock.WITHER_SUMMON_BASE_BLOCKS)).a('~', ShapeDetectorBlock.a(MaterialPredicate.a(Material.AIR))).b();
/*     */     }
/*     */     
/* 112 */     return d;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockWitherSkull.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */