/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockPoweredRail
/*     */   extends BlockMinecartTrackAbstract {
/*   7 */   public static final BlockStateEnum<BlockPropertyTrackPosition> SHAPE = BlockProperties.ad;
/*   8 */   public static final BlockStateBoolean POWERED = BlockProperties.w;
/*     */   
/*     */   protected BlockPoweredRail(BlockBase.Info blockbase_info) {
/*  11 */     super(true, blockbase_info);
/*  12 */     j(((IBlockData)this.blockStateList.getBlockData()).set(SHAPE, BlockPropertyTrackPosition.NORTH_SOUTH).set(POWERED, Boolean.valueOf(false)));
/*     */   }
/*     */   
/*     */   protected boolean a(World world, BlockPosition blockposition, IBlockData iblockdata, boolean flag, int i) {
/*  16 */     if (i >= 8) {
/*  17 */       return false;
/*     */     }
/*  19 */     int j = blockposition.getX();
/*  20 */     int k = blockposition.getY();
/*  21 */     int l = blockposition.getZ();
/*  22 */     boolean flag1 = true;
/*  23 */     BlockPropertyTrackPosition blockpropertytrackposition = (BlockPropertyTrackPosition)iblockdata.get(SHAPE);
/*     */     
/*  25 */     switch (blockpropertytrackposition) {
/*     */       case LEFT_RIGHT:
/*  27 */         if (flag) {
/*  28 */           l++; break;
/*     */         } 
/*  30 */         l--;
/*     */         break;
/*     */       
/*     */       case FRONT_BACK:
/*  34 */         if (flag) {
/*  35 */           j--; break;
/*     */         } 
/*  37 */         j++;
/*     */         break;
/*     */       
/*     */       case null:
/*  41 */         if (flag) {
/*  42 */           j--;
/*     */         } else {
/*  44 */           j++;
/*  45 */           k++;
/*  46 */           flag1 = false;
/*     */         } 
/*     */         
/*  49 */         blockpropertytrackposition = BlockPropertyTrackPosition.EAST_WEST;
/*     */         break;
/*     */       case null:
/*  52 */         if (flag) {
/*  53 */           j--;
/*  54 */           k++;
/*  55 */           flag1 = false;
/*     */         } else {
/*  57 */           j++;
/*     */         } 
/*     */         
/*  60 */         blockpropertytrackposition = BlockPropertyTrackPosition.EAST_WEST;
/*     */         break;
/*     */       case null:
/*  63 */         if (flag) {
/*  64 */           l++;
/*     */         } else {
/*  66 */           l--;
/*  67 */           k++;
/*  68 */           flag1 = false;
/*     */         } 
/*     */         
/*  71 */         blockpropertytrackposition = BlockPropertyTrackPosition.NORTH_SOUTH;
/*     */         break;
/*     */       case null:
/*  74 */         if (flag) {
/*  75 */           l++;
/*  76 */           k++;
/*  77 */           flag1 = false;
/*     */         } else {
/*  79 */           l--;
/*     */         } 
/*     */         
/*  82 */         blockpropertytrackposition = BlockPropertyTrackPosition.NORTH_SOUTH;
/*     */         break;
/*     */     } 
/*  85 */     return a(world, new BlockPosition(j, k, l), flag, i, blockpropertytrackposition) ? true : ((flag1 && a(world, new BlockPosition(j, k - 1, l), flag, i, blockpropertytrackposition)));
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean a(World world, BlockPosition blockposition, boolean flag, int i, BlockPropertyTrackPosition blockpropertytrackposition) {
/*  90 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/*  92 */     if (!iblockdata.a(this)) {
/*  93 */       return false;
/*     */     }
/*  95 */     BlockPropertyTrackPosition blockpropertytrackposition1 = (BlockPropertyTrackPosition)iblockdata.get(SHAPE);
/*     */     
/*  97 */     return (blockpropertytrackposition == BlockPropertyTrackPosition.EAST_WEST && (blockpropertytrackposition1 == BlockPropertyTrackPosition.NORTH_SOUTH || blockpropertytrackposition1 == BlockPropertyTrackPosition.ASCENDING_NORTH || blockpropertytrackposition1 == BlockPropertyTrackPosition.ASCENDING_SOUTH)) ? false : ((blockpropertytrackposition == BlockPropertyTrackPosition.NORTH_SOUTH && (blockpropertytrackposition1 == BlockPropertyTrackPosition.EAST_WEST || blockpropertytrackposition1 == BlockPropertyTrackPosition.ASCENDING_EAST || blockpropertytrackposition1 == BlockPropertyTrackPosition.ASCENDING_WEST)) ? false : (((Boolean)iblockdata.get(POWERED)).booleanValue() ? (world.isBlockIndirectlyPowered(blockposition) ? true : a(world, blockposition, iblockdata, flag, i + 1)) : false));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(IBlockData iblockdata, World world, BlockPosition blockposition, Block block) {
/* 103 */     boolean flag = ((Boolean)iblockdata.get(POWERED)).booleanValue();
/* 104 */     boolean flag1 = (world.isBlockIndirectlyPowered(blockposition) || a(world, blockposition, iblockdata, true, 0) || a(world, blockposition, iblockdata, false, 0));
/*     */     
/* 106 */     if (flag1 != flag) {
/*     */       
/* 108 */       int power = flag ? 15 : 0;
/* 109 */       int newPower = CraftEventFactory.callRedstoneChange(world, blockposition, power, 15 - power).getNewCurrent();
/* 110 */       if (newPower == power) {
/*     */         return;
/*     */       }
/*     */       
/* 114 */       world.setTypeAndData(blockposition, iblockdata.set(POWERED, Boolean.valueOf(flag1)), 3);
/* 115 */       world.applyPhysics(blockposition.down(), this);
/* 116 */       if (((BlockPropertyTrackPosition)iblockdata.get(SHAPE)).c()) {
/* 117 */         world.applyPhysics(blockposition.up(), this);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockState<BlockPropertyTrackPosition> d() {
/* 125 */     return SHAPE;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockRotation enumblockrotation) {
/* 130 */     switch (enumblockrotation) {
/*     */       case LEFT_RIGHT:
/* 132 */         switch ((BlockPropertyTrackPosition)iblockdata.get(SHAPE)) {
/*     */           case null:
/* 134 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case null:
/* 136 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           case null:
/* 138 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case null:
/* 140 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case null:
/* 142 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */           case null:
/* 144 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case null:
/* 146 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */           case null:
/* 148 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */         } 
/*     */       case FRONT_BACK:
/* 151 */         switch ((BlockPropertyTrackPosition)iblockdata.get(SHAPE)) {
/*     */           case LEFT_RIGHT:
/* 153 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.EAST_WEST);
/*     */           case FRONT_BACK:
/* 155 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_SOUTH);
/*     */           case null:
/* 157 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case null:
/* 159 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case null:
/* 161 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case null:
/* 163 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           case null:
/* 165 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case null:
/* 167 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */           case null:
/* 169 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case null:
/* 171 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */         } 
/*     */       case null:
/* 174 */         switch ((BlockPropertyTrackPosition)iblockdata.get(SHAPE)) {
/*     */           case LEFT_RIGHT:
/* 176 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.EAST_WEST);
/*     */           case FRONT_BACK:
/* 178 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_SOUTH);
/*     */           case null:
/* 180 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case null:
/* 182 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case null:
/* 184 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           case null:
/* 186 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case null:
/* 188 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case null:
/* 190 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */           case null:
/* 192 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case null:
/* 194 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */         }  break;
/*     */     } 
/* 197 */     return iblockdata;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IBlockData a(IBlockData iblockdata, EnumBlockMirror enumblockmirror) {
/* 203 */     BlockPropertyTrackPosition blockpropertytrackposition = (BlockPropertyTrackPosition)iblockdata.get(SHAPE);
/*     */     
/* 205 */     switch (enumblockmirror) {
/*     */       case LEFT_RIGHT:
/* 207 */         switch (blockpropertytrackposition) {
/*     */           case null:
/* 209 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_SOUTH);
/*     */           case null:
/* 211 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_NORTH);
/*     */           case null:
/* 213 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case null:
/* 215 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */           case null:
/* 217 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case null:
/* 219 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */         } 
/* 221 */         return super.a(iblockdata, enumblockmirror);
/*     */       
/*     */       case FRONT_BACK:
/* 224 */         switch (blockpropertytrackposition)
/*     */         { case null:
/* 226 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_WEST);
/*     */           case null:
/* 228 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.ASCENDING_EAST);
/*     */           
/*     */           default:
/*     */             break;
/*     */           
/*     */           case null:
/* 234 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_WEST);
/*     */           case null:
/* 236 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.SOUTH_EAST);
/*     */           case null:
/* 238 */             return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_EAST);
/*     */           case null:
/* 240 */             break; }  return iblockdata.set(SHAPE, BlockPropertyTrackPosition.NORTH_WEST);
/*     */     } 
/*     */ 
/*     */     
/* 244 */     return super.a(iblockdata, enumblockmirror);
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 249 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { SHAPE, POWERED });
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPoweredRail.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */