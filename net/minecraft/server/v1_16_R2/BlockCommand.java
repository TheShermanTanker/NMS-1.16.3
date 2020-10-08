/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.block.Block;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.block.BlockRedstoneEvent;
/*     */ 
/*     */ public class BlockCommand extends BlockTileEntity {
/*  11 */   private static final Logger LOGGER = LogManager.getLogger();
/*  12 */   public static final BlockStateDirection a = BlockDirectional.FACING;
/*  13 */   public static final BlockStateBoolean b = BlockProperties.c;
/*     */   
/*     */   public BlockCommand(BlockBase.Info blockbase_info) {
/*  16 */     super(blockbase_info);
/*  17 */     j(((IBlockData)this.blockStateList.getBlockData()).set(a, EnumDirection.NORTH).set(b, Boolean.valueOf(false)));
/*     */   }
/*     */ 
/*     */   
/*     */   public TileEntity createTile(IBlockAccess iblockaccess) {
/*  22 */     TileEntityCommand tileentitycommand = new TileEntityCommand();
/*     */     
/*  24 */     tileentitycommand.b((this == Blocks.CHAIN_COMMAND_BLOCK));
/*  25 */     return tileentitycommand;
/*     */   }
/*     */ 
/*     */   
/*     */   public void doPhysics(IBlockData iblockdata, World world, BlockPosition blockposition, Block block, BlockPosition blockposition1, boolean flag) {
/*  30 */     if (!world.isClientSide) {
/*  31 */       TileEntity tileentity = world.getTileEntity(blockposition);
/*     */       
/*  33 */       if (tileentity instanceof TileEntityCommand) {
/*  34 */         TileEntityCommand tileentitycommand = (TileEntityCommand)tileentity;
/*  35 */         boolean flag1 = world.isBlockIndirectlyPowered(blockposition);
/*  36 */         boolean flag2 = tileentitycommand.f();
/*     */         
/*  38 */         Block bukkitBlock = world.getWorld().getBlockAt(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*  39 */         int old = flag2 ? 15 : 0;
/*  40 */         int current = flag1 ? 15 : 0;
/*     */         
/*  42 */         BlockRedstoneEvent eventRedstone = new BlockRedstoneEvent(bukkitBlock, old, current);
/*  43 */         world.getServer().getPluginManager().callEvent((Event)eventRedstone);
/*  44 */         flag1 = (eventRedstone.getNewCurrent() > 0);
/*     */ 
/*     */         
/*  47 */         tileentitycommand.a(flag1);
/*  48 */         if (!flag2 && !tileentitycommand.g() && tileentitycommand.m() != TileEntityCommand.Type.SEQUENCE && 
/*  49 */           flag1) {
/*  50 */           tileentitycommand.k();
/*  51 */           world.getBlockTickList().a(blockposition, this, 1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  61 */     TileEntity tileentity = worldserver.getTileEntity(blockposition);
/*     */     
/*  63 */     if (tileentity instanceof TileEntityCommand) {
/*  64 */       TileEntityCommand tileentitycommand = (TileEntityCommand)tileentity;
/*  65 */       CommandBlockListenerAbstract commandblocklistenerabstract = tileentitycommand.getCommandBlock();
/*  66 */       boolean flag = !UtilColor.b(commandblocklistenerabstract.getCommand());
/*  67 */       TileEntityCommand.Type tileentitycommand_type = tileentitycommand.m();
/*  68 */       boolean flag1 = tileentitycommand.j();
/*     */       
/*  70 */       if (tileentitycommand_type == TileEntityCommand.Type.AUTO) {
/*  71 */         tileentitycommand.k();
/*  72 */         if (flag1) {
/*  73 */           a(iblockdata, worldserver, blockposition, commandblocklistenerabstract, flag);
/*  74 */         } else if (tileentitycommand.x()) {
/*  75 */           commandblocklistenerabstract.a(0);
/*     */         } 
/*     */         
/*  78 */         if (tileentitycommand.f() || tileentitycommand.g()) {
/*  79 */           worldserver.getBlockTickList().a(blockposition, this, 1);
/*     */         }
/*  81 */       } else if (tileentitycommand_type == TileEntityCommand.Type.REDSTONE) {
/*  82 */         if (flag1) {
/*  83 */           a(iblockdata, worldserver, blockposition, commandblocklistenerabstract, flag);
/*  84 */         } else if (tileentitycommand.x()) {
/*  85 */           commandblocklistenerabstract.a(0);
/*     */         } 
/*     */       } 
/*     */       
/*  89 */       worldserver.updateAdjacentComparators(blockposition, this);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(IBlockData iblockdata, World world, BlockPosition blockposition, CommandBlockListenerAbstract commandblocklistenerabstract, boolean flag) {
/*  95 */     if (flag) {
/*  96 */       commandblocklistenerabstract.a(world);
/*     */     } else {
/*  98 */       commandblocklistenerabstract.a(0);
/*     */     } 
/*     */     
/* 101 */     a(world, blockposition, (EnumDirection)iblockdata.get(a));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult interact(IBlockData iblockdata, World world, BlockPosition blockposition, EntityHuman entityhuman, EnumHand enumhand, MovingObjectPositionBlock movingobjectpositionblock) {
/* 106 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 108 */     if (tileentity instanceof TileEntityCommand && (entityhuman.isCreativeAndOp() || (entityhuman.isCreative() && entityhuman.getBukkitEntity().hasPermission("minecraft.commandblock")))) {
/* 109 */       entityhuman.a((TileEntityCommand)tileentity);
/* 110 */       return EnumInteractionResult.a(world.isClientSide);
/*     */     } 
/* 112 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isComplexRedstone(IBlockData iblockdata) {
/* 118 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IBlockData iblockdata, World world, BlockPosition blockposition) {
/* 123 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 125 */     return (tileentity instanceof TileEntityCommand) ? ((TileEntityCommand)tileentity).getCommandBlock().i() : 0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void postPlace(World world, BlockPosition blockposition, IBlockData iblockdata, EntityLiving entityliving, ItemStack itemstack) {
/* 130 */     TileEntity tileentity = world.getTileEntity(blockposition);
/*     */     
/* 132 */     if (tileentity instanceof TileEntityCommand) {
/* 133 */       TileEntityCommand tileentitycommand = (TileEntityCommand)tileentity;
/* 134 */       CommandBlockListenerAbstract commandblocklistenerabstract = tileentitycommand.getCommandBlock();
/*     */       
/* 136 */       if (itemstack.hasName()) {
/* 137 */         commandblocklistenerabstract.setName(itemstack.getName());
/*     */       }
/*     */       
/* 140 */       if (!world.isClientSide) {
/* 141 */         if (itemstack.b("BlockEntityTag") == null) {
/* 142 */           commandblocklistenerabstract.a(world.getGameRules().getBoolean(GameRules.SEND_COMMAND_FEEDBACK));
/* 143 */           tileentitycommand.b((this == Blocks.CHAIN_COMMAND_BLOCK));
/*     */         } 
/*     */         
/* 146 */         if (tileentitycommand.m() == TileEntityCommand.Type.SEQUENCE) {
/* 147 */           boolean flag = world.isBlockIndirectlyPowered(blockposition);
/*     */           
/* 149 */           tileentitycommand.a(flag);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumRenderType b(IBlockData iblockdata) {
/* 158 */     return EnumRenderType.MODEL;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 163 */     return iblockdata.set(a, enumblockrotation.a((EnumDirection)iblockdata.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 168 */     return iblockdata.a(enumblockmirror.a((EnumDirection)iblockdata.get(a)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 173 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { a, b });
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData getPlacedState(BlockActionContext blockactioncontext) {
/* 178 */     return getBlockData().set(a, blockactioncontext.d().opposite());
/*     */   }
/*     */   
/*     */   private static void a(World world, BlockPosition blockposition, EnumDirection enumdirection) {
/* 182 */     BlockPosition.MutableBlockPosition blockposition_mutableblockposition = blockposition.i();
/* 183 */     GameRules gamerules = world.getGameRules();
/*     */ 
/*     */     
/*     */     int i;
/*     */     
/* 188 */     for (i = gamerules.getInt(GameRules.MAX_COMMAND_CHAIN_LENGTH); i-- > 0; enumdirection = (EnumDirection)iblockdata.get(a)) {
/* 189 */       blockposition_mutableblockposition.c(enumdirection);
/* 190 */       IBlockData iblockdata = world.getType(blockposition_mutableblockposition);
/* 191 */       Block block = iblockdata.getBlock();
/*     */       
/* 193 */       if (!iblockdata.a(Blocks.CHAIN_COMMAND_BLOCK)) {
/*     */         break;
/*     */       }
/*     */       
/* 197 */       TileEntity tileentity = world.getTileEntity(blockposition_mutableblockposition);
/*     */       
/* 199 */       if (!(tileentity instanceof TileEntityCommand)) {
/*     */         break;
/*     */       }
/*     */       
/* 203 */       TileEntityCommand tileentitycommand = (TileEntityCommand)tileentity;
/*     */       
/* 205 */       if (tileentitycommand.m() != TileEntityCommand.Type.SEQUENCE) {
/*     */         break;
/*     */       }
/*     */       
/* 209 */       if (tileentitycommand.f() || tileentitycommand.g()) {
/* 210 */         CommandBlockListenerAbstract commandblocklistenerabstract = tileentitycommand.getCommandBlock();
/*     */         
/* 212 */         if (tileentitycommand.k()) {
/* 213 */           if (!commandblocklistenerabstract.a(world)) {
/*     */             break;
/*     */           }
/*     */           
/* 217 */           world.updateAdjacentComparators(blockposition_mutableblockposition, block);
/* 218 */         } else if (tileentitycommand.x()) {
/* 219 */           commandblocklistenerabstract.a(0);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 224 */     if (i <= 0) {
/* 225 */       int j = Math.max(gamerules.getInt(GameRules.MAX_COMMAND_CHAIN_LENGTH), 0);
/*     */       
/* 227 */       LOGGER.warn("Command Block chain tried to execute more than {} steps!", Integer.valueOf(j));
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */