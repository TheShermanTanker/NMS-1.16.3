/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.block.BlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlockState;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockFormEvent;
/*     */ 
/*     */ public class BlockConcretePowder
/*     */   extends BlockFalling {
/*     */   public BlockConcretePowder(Block block, BlockBase.Info blockbase_info) {
/*  13 */     super(blockbase_info);
/*  14 */     this.a = block.getBlockData();
/*     */   }
/*     */   private final IBlockData a;
/*     */   
/*     */   public void a(World world, BlockPosition blockposition, IBlockData iblockdata, IBlockData iblockdata1, EntityFallingBlock entityfallingblock) {
/*  19 */     if (canHarden(world, blockposition, iblockdata1)) {
/*  20 */       CraftEventFactory.handleBlockFormEvent(world, blockposition, this.a, 3);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/*  27 */     World world = blockactioncontext.getWorld();
/*  28 */     BlockPosition blockposition = blockactioncontext.getClickPosition();
/*  29 */     IBlockData iblockdata = world.getType(blockposition);
/*     */ 
/*     */     
/*  32 */     if (!canHarden(world, blockposition, iblockdata)) {
/*  33 */       return super.getPlacedState(blockactioncontext);
/*     */     }
/*     */ 
/*     */     
/*  37 */     CraftBlockState blockState = CraftBlockState.getBlockState(world, blockposition);
/*  38 */     blockState.setData(this.a);
/*     */     
/*  40 */     BlockFormEvent event = new BlockFormEvent((Block)blockState.getBlock(), (BlockState)blockState);
/*  41 */     (world.getMinecraftServer()).server.getPluginManager().callEvent((Event)event);
/*     */     
/*  43 */     if (!event.isCancelled()) {
/*  44 */       return blockState.getHandle();
/*     */     }
/*     */     
/*  47 */     return super.getPlacedState(blockactioncontext);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean canHarden(IBlockAccess iblockaccess, BlockPosition blockposition, IBlockData iblockdata) {
/*  52 */     return (l(iblockdata) || a(iblockaccess, blockposition));
/*     */   }
/*     */   
/*     */   private static boolean a(IBlockAccess iblockaccess, BlockPosition blockposition) {
/*  56 */     boolean flag = false;
/*  57 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = blockposition.i();
/*  58 */     EnumDirection[] aenumdirection = EnumDirection.values();
/*  59 */     int i = aenumdirection.length;
/*     */     
/*  61 */     for (int j = 0; j < i; j++) {
/*  62 */       EnumDirection enumdirection = aenumdirection[j];
/*  63 */       IBlockData iblockdata = iblockaccess.getType(blockposition_mutableblockposition);
/*     */       
/*  65 */       if (enumdirection != EnumDirection.DOWN || l(iblockdata)) {
/*  66 */         blockposition_mutableblockposition.a(blockposition, enumdirection);
/*  67 */         iblockdata = iblockaccess.getType(blockposition_mutableblockposition);
/*  68 */         if (l(iblockdata) && !iblockdata.d(iblockaccess, blockposition, enumdirection.opposite())) {
/*  69 */           flag = true;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*     */     } 
/*  75 */     return flag;
/*     */   }
/*     */   
/*     */   private static boolean l(IBlockData iblockdata) {
/*  79 */     return iblockdata.getFluid().a(TagsFluid.WATER);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/*  85 */     if (a(generatoraccess, blockposition)) {
/*     */       
/*  87 */       if (!(generatoraccess instanceof World)) {
/*  88 */         return this.a;
/*     */       }
/*  90 */       CraftBlockState blockState = CraftBlockState.getBlockState(generatoraccess, blockposition);
/*  91 */       blockState.setData(this.a);
/*     */       
/*  93 */       BlockFormEvent event = new BlockFormEvent((Block)blockState.getBlock(), (BlockState)blockState);
/*  94 */       ((World)generatoraccess).getServer().getPluginManager().callEvent((Event)event);
/*     */       
/*  96 */       if (!event.isCancelled()) {
/*  97 */         return blockState.getHandle();
/*     */       }
/*     */     } 
/*     */     
/* 101 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockConcretePowder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */