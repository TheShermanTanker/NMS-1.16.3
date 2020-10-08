/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Iterator;
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ public class BlockChorusFlower
/*     */   extends Block
/*     */ {
/*  11 */   public static final BlockStateInteger AGE = BlockProperties.ah;
/*     */   private final BlockChorusFruit b;
/*     */   
/*     */   protected BlockChorusFlower(BlockChorusFruit blockchorusfruit, BlockBase.Info blockbase_info) {
/*  15 */     super(blockbase_info);
/*  16 */     this.b = blockchorusfruit;
/*  17 */     j(((IBlockData)this.blockStateList.getBlockData()).set(AGE, Integer.valueOf(0)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void tickAlways(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  22 */     if (!iblockdata.canPlace(worldserver, blockposition)) {
/*  23 */       worldserver.b(blockposition, true);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isTicking(IBlockData iblockdata) {
/*  30 */     return (((Integer)iblockdata.get(AGE)).intValue() < 5);
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick(IBlockData iblockdata, WorldServer worldserver, BlockPosition blockposition, Random random) {
/*  35 */     BlockPosition blockposition1 = blockposition.up();
/*     */     
/*  37 */     if (worldserver.isEmpty(blockposition1) && blockposition1.getY() < 256) {
/*  38 */       int i = ((Integer)iblockdata.get(AGE)).intValue();
/*     */       
/*  40 */       if (i < 5) {
/*  41 */         boolean flag = false;
/*  42 */         boolean flag1 = false;
/*  43 */         IBlockData iblockdata1 = worldserver.getType(blockposition.down());
/*  44 */         Block block = iblockdata1.getBlock();
/*     */ 
/*     */         
/*  47 */         if (block == Blocks.END_STONE) {
/*  48 */           flag = true;
/*  49 */         } else if (block == this.b) {
/*  50 */           int j = 1;
/*     */           
/*  52 */           for (int k = 0; k < 4; k++) {
/*  53 */             Block block1 = worldserver.getType(blockposition.down(j + 1)).getBlock();
/*     */             
/*  55 */             if (block1 != this.b) {
/*  56 */               if (block1 == Blocks.END_STONE) {
/*  57 */                 flag1 = true;
/*     */               }
/*     */               
/*     */               break;
/*     */             } 
/*  62 */             j++;
/*     */           } 
/*     */           
/*  65 */           if (j < 2 || j <= random.nextInt(flag1 ? 5 : 4)) {
/*  66 */             flag = true;
/*     */           }
/*  68 */         } else if (iblockdata1.isAir()) {
/*  69 */           flag = true;
/*     */         } 
/*     */         
/*  72 */         if (flag && b(worldserver, blockposition1, (EnumDirection)null) && worldserver.isEmpty(blockposition.up(2))) {
/*     */           
/*  74 */           if (CraftEventFactory.handleBlockSpreadEvent(worldserver, blockposition, blockposition1, getBlockData().set(AGE, Integer.valueOf(i)), 2)) {
/*  75 */             worldserver.setTypeAndData(blockposition, this.b.a(worldserver, blockposition), 2);
/*  76 */             a(worldserver, blockposition1, i);
/*     */           }
/*     */         
/*  79 */         } else if (i < 4) {
/*  80 */           int j = random.nextInt(4);
/*  81 */           if (flag1) {
/*  82 */             j++;
/*     */           }
/*     */           
/*  85 */           boolean flag2 = false;
/*     */           
/*  87 */           for (int l = 0; l < j; l++) {
/*  88 */             EnumDirection enumdirection = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random);
/*  89 */             BlockPosition blockposition2 = blockposition.shift(enumdirection);
/*     */             
/*  91 */             if (worldserver.isEmpty(blockposition2) && worldserver.isEmpty(blockposition2.down()) && b(worldserver, blockposition2, enumdirection.opposite()))
/*     */             {
/*  93 */               if (CraftEventFactory.handleBlockSpreadEvent(worldserver, blockposition, blockposition2, getBlockData().set(AGE, Integer.valueOf(i + 1)), 2)) {
/*  94 */                 a(worldserver, blockposition2, i + 1);
/*  95 */                 flag2 = true;
/*     */               } 
/*     */             }
/*     */           } 
/*     */ 
/*     */           
/* 101 */           if (flag2) {
/* 102 */             worldserver.setTypeAndData(blockposition, this.b.a(worldserver, blockposition), 2);
/*     */           
/*     */           }
/* 105 */           else if (CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition, getBlockData().set(AGE, Integer.valueOf(5)), 2)) {
/* 106 */             a(worldserver, blockposition);
/*     */           
/*     */           }
/*     */ 
/*     */         
/*     */         }
/* 112 */         else if (CraftEventFactory.handleBlockGrowEvent(worldserver, blockposition, getBlockData().set(AGE, Integer.valueOf(5)), 2)) {
/* 113 */           a(worldserver, blockposition);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void a(World world, BlockPosition blockposition, int i) {
/* 123 */     world.setTypeAndData(blockposition, getBlockData().set(AGE, Integer.valueOf(i)), 2);
/* 124 */     world.triggerEffect(1033, blockposition, 0);
/*     */   }
/*     */   
/*     */   private void a(World world, BlockPosition blockposition) {
/* 128 */     world.setTypeAndData(blockposition, getBlockData().set(AGE, Integer.valueOf(5)), 2);
/* 129 */     world.triggerEffect(1034, blockposition, 0);
/*     */   }
/*     */   private static boolean b(IWorldReader iworldreader, BlockPosition blockposition, @Nullable EnumDirection enumdirection) {
/*     */     EnumDirection enumdirection1;
/* 133 */     Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */ 
/*     */ 
/*     */     
/*     */     do {
/* 138 */       if (!iterator.hasNext()) {
/* 139 */         return true;
/*     */       }
/*     */       
/* 142 */       enumdirection1 = iterator.next();
/* 143 */     } while (enumdirection1 == enumdirection || iworldreader.isEmpty(blockposition.shift(enumdirection1)));
/*     */     
/* 145 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData updateState(IBlockData iblockdata, EnumDirection enumdirection, IBlockData iblockdata1, GeneratorAccess generatoraccess, BlockPosition blockposition, BlockPosition blockposition1) {
/* 150 */     if (enumdirection != EnumDirection.UP && !iblockdata.canPlace(generatoraccess, blockposition)) {
/* 151 */       generatoraccess.getBlockTickList().a(blockposition, this, 1);
/*     */     }
/*     */     
/* 154 */     return super.updateState(iblockdata, enumdirection, iblockdata1, generatoraccess, blockposition, blockposition1);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canPlace(IBlockData iblockdata, IWorldReader iworldreader, BlockPosition blockposition) {
/* 159 */     IBlockData iblockdata1 = iworldreader.getType(blockposition.down());
/*     */     
/* 161 */     if (iblockdata1.getBlock() != this.b && !iblockdata1.a(Blocks.END_STONE)) {
/* 162 */       if (!iblockdata1.isAir()) {
/* 163 */         return false;
/*     */       }
/* 165 */       boolean flag = false;
/* 166 */       Iterator<EnumDirection> iterator = EnumDirection.EnumDirectionLimit.HORIZONTAL.iterator();
/*     */       
/* 168 */       while (iterator.hasNext()) {
/* 169 */         EnumDirection enumdirection = iterator.next();
/* 170 */         IBlockData iblockdata2 = iworldreader.getType(blockposition.shift(enumdirection));
/*     */         
/* 172 */         if (iblockdata2.a(this.b)) {
/* 173 */           if (flag) {
/* 174 */             return false;
/*     */           }
/*     */           
/* 177 */           flag = true; continue;
/* 178 */         }  if (!iblockdata2.isAir()) {
/* 179 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 183 */       return flag;
/*     */     } 
/*     */     
/* 186 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected void a(BlockStateList.a<Block, IBlockData> blockstatelist_a) {
/* 192 */     blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { AGE });
/*     */   }
/*     */   
/*     */   public static void a(GeneratorAccess generatoraccess, BlockPosition blockposition, Random random, int i) {
/* 196 */     generatoraccess.setTypeAndData(blockposition, ((BlockChorusFruit)Blocks.CHORUS_PLANT).a(generatoraccess, blockposition), 2);
/* 197 */     a(generatoraccess, blockposition, random, blockposition, i, 0);
/*     */   }
/*     */   
/*     */   private static void a(GeneratorAccess generatoraccess, BlockPosition blockposition, Random random, BlockPosition blockposition1, int i, int j) {
/* 201 */     BlockChorusFruit blockchorusfruit = (BlockChorusFruit)Blocks.CHORUS_PLANT;
/* 202 */     int k = random.nextInt(4) + 1;
/*     */     
/* 204 */     if (j == 0) {
/* 205 */       k++;
/*     */     }
/*     */     
/* 208 */     for (int l = 0; l < k; l++) {
/* 209 */       BlockPosition blockposition2 = blockposition.up(l + 1);
/*     */       
/* 211 */       if (!b(generatoraccess, blockposition2, (EnumDirection)null)) {
/*     */         return;
/*     */       }
/*     */       
/* 215 */       generatoraccess.setTypeAndData(blockposition2, blockchorusfruit.a(generatoraccess, blockposition2), 2);
/* 216 */       generatoraccess.setTypeAndData(blockposition2.down(), blockchorusfruit.a(generatoraccess, blockposition2.down()), 2);
/*     */     } 
/*     */     
/* 219 */     boolean flag = false;
/*     */     
/* 221 */     if (j < 4) {
/* 222 */       int i1 = random.nextInt(4);
/*     */       
/* 224 */       if (j == 0) {
/* 225 */         i1++;
/*     */       }
/*     */       
/* 228 */       for (int j1 = 0; j1 < i1; j1++) {
/* 229 */         EnumDirection enumdirection = EnumDirection.EnumDirectionLimit.HORIZONTAL.a(random);
/* 230 */         BlockPosition blockposition3 = blockposition.up(k).shift(enumdirection);
/*     */         
/* 232 */         if (Math.abs(blockposition3.getX() - blockposition1.getX()) < i && Math.abs(blockposition3.getZ() - blockposition1.getZ()) < i && generatoraccess.isEmpty(blockposition3) && generatoraccess.isEmpty(blockposition3.down()) && b(generatoraccess, blockposition3, enumdirection.opposite())) {
/* 233 */           flag = true;
/* 234 */           generatoraccess.setTypeAndData(blockposition3, blockchorusfruit.a(generatoraccess, blockposition3), 2);
/* 235 */           generatoraccess.setTypeAndData(blockposition3.shift(enumdirection.opposite()), blockchorusfruit.a(generatoraccess, blockposition3.shift(enumdirection.opposite())), 2);
/* 236 */           a(generatoraccess, blockposition3, random, blockposition1, i, j + 1);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 241 */     if (!flag) {
/* 242 */       generatoraccess.setTypeAndData(blockposition.up(k), Blocks.CHORUS_FLOWER.getBlockData().set(AGE, Integer.valueOf(5)), 2);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(World world, IBlockData iblockdata, MovingObjectPositionBlock movingobjectpositionblock, IProjectile iprojectile) {
/* 249 */     if (iprojectile.getEntityType().a(TagsEntity.IMPACT_PROJECTILES)) {
/* 250 */       BlockPosition blockposition = movingobjectpositionblock.getBlockPosition();
/*     */       
/* 252 */       world.a(blockposition, true, iprojectile);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockChorusFlower.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */