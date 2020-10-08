/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.event.CraftEventFactory;
/*     */ 
/*     */ 
/*     */ public abstract class FluidTypeLava
/*     */   extends FluidTypeFlowing
/*     */ {
/*     */   public FluidType d() {
/*  11 */     return FluidTypes.FLOWING_LAVA;
/*     */   }
/*     */ 
/*     */   
/*     */   public FluidType e() {
/*  16 */     return FluidTypes.LAVA;
/*     */   }
/*     */ 
/*     */   
/*     */   public Item a() {
/*  21 */     return Items.LAVA_BUCKET;
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(World world, BlockPosition blockposition, Fluid fluid, Random random) {
/*  26 */     if (world.getGameRules().getBoolean(GameRules.DO_FIRE_TICK)) {
/*  27 */       int i = random.nextInt(3);
/*     */       
/*  29 */       if (i > 0) {
/*  30 */         BlockPosition blockposition1 = blockposition;
/*     */         
/*  32 */         for (int j = 0; j < i; j++) {
/*  33 */           blockposition1 = blockposition1.b(random.nextInt(3) - 1, 1, random.nextInt(3) - 1);
/*  34 */           if (!world.p(blockposition1)) {
/*     */             return;
/*     */           }
/*     */           
/*  38 */           IBlockData iblockdata = world.getType(blockposition1);
/*     */           
/*  40 */           if (iblockdata.isAir()) {
/*  41 */             if (a(world, blockposition1))
/*     */             {
/*  43 */               if (world.getType(blockposition1).getBlock() == Blocks.FIRE || 
/*  44 */                 !CraftEventFactory.callBlockIgniteEvent(world, blockposition1, blockposition).isCancelled()) {
/*     */ 
/*     */ 
/*     */ 
/*     */                 
/*  49 */                 world.setTypeUpdate(blockposition1, BlockFireAbstract.a(world, blockposition1));
/*     */                 return;
/*     */               }  } 
/*  52 */           } else if (iblockdata.getMaterial().isSolid()) {
/*     */             return;
/*     */           } 
/*     */         } 
/*     */       } else {
/*  57 */         for (int k = 0; k < 3; k++) {
/*  58 */           BlockPosition blockposition2 = blockposition.b(random.nextInt(3) - 1, 0, random.nextInt(3) - 1);
/*     */           
/*  60 */           if (!world.p(blockposition2)) {
/*     */             return;
/*     */           }
/*     */           
/*  64 */           if (world.isEmpty(blockposition2.up()) && b(world, blockposition2)) {
/*     */             
/*  66 */             BlockPosition up = blockposition2.up();
/*  67 */             if (world.getType(up).getBlock() == Blocks.FIRE || 
/*  68 */               !CraftEventFactory.callBlockIgniteEvent(world, up, blockposition).isCancelled())
/*     */             {
/*     */ 
/*     */ 
/*     */               
/*  73 */               world.setTypeUpdate(blockposition2.up(), BlockFireAbstract.a(world, blockposition2));
/*     */             }
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean a(IWorldReader iworldreader, BlockPosition blockposition) {
/*  82 */     EnumDirection[] aenumdirection = EnumDirection.values();
/*  83 */     int i = aenumdirection.length;
/*     */     
/*  85 */     for (int j = 0; j < i; j++) {
/*  86 */       EnumDirection enumdirection = aenumdirection[j];
/*     */       
/*  88 */       if (b(iworldreader, blockposition.shift(enumdirection))) {
/*  89 */         return true;
/*     */       }
/*     */     } 
/*     */     
/*  93 */     return false;
/*     */   }
/*     */   
/*     */   private boolean b(IWorldReader iworldreader, BlockPosition blockposition) {
/*  97 */     return (blockposition.getY() >= 0 && blockposition.getY() < 256 && !iworldreader.isLoaded(blockposition)) ? false : iworldreader.getType(blockposition).getMaterial().isBurnable();
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata) {
/* 102 */     a(generatoraccess, blockposition);
/*     */   }
/*     */ 
/*     */   
/*     */   public int b(IWorldReader iworldreader) {
/* 107 */     return iworldreader.getDimensionManager().isNether() ? 4 : 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public IBlockData b(Fluid fluid) {
/* 112 */     return Blocks.LAVA.getBlockData().set(BlockFluids.LEVEL, Integer.valueOf(e(fluid)));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(FluidType fluidtype) {
/* 117 */     return (fluidtype == FluidTypes.LAVA || fluidtype == FluidTypes.FLOWING_LAVA);
/*     */   }
/*     */ 
/*     */   
/*     */   public int c(IWorldReader iworldreader) {
/* 122 */     return iworldreader.getDimensionManager().isNether() ? 1 : 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Fluid fluid, IBlockAccess iblockaccess, BlockPosition blockposition, FluidType fluidtype, EnumDirection enumdirection) {
/* 127 */     return (fluid.getHeight(iblockaccess, blockposition) >= 0.44444445F && fluidtype.a(TagsFluid.WATER));
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(IWorldReader iworldreader) {
/* 132 */     return iworldreader.getDimensionManager().isNether() ? 10 : 30;
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(World world, BlockPosition blockposition, Fluid fluid, Fluid fluid1) {
/* 137 */     int i = a(world);
/*     */     
/* 139 */     if (!fluid.isEmpty() && !fluid1.isEmpty() && !((Boolean)fluid.get(FALLING)).booleanValue() && !((Boolean)fluid1.get(FALLING)).booleanValue() && fluid1.getHeight(world, blockposition) > fluid.getHeight(world, blockposition) && world.getRandom().nextInt(4) != 0) {
/* 140 */       i *= 4;
/*     */     }
/*     */     
/* 143 */     return i;
/*     */   }
/*     */   
/*     */   private void a(GeneratorAccess generatoraccess, BlockPosition blockposition) {
/* 147 */     generatoraccess.triggerEffect(1501, blockposition, 0);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean f() {
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   protected void a(GeneratorAccess generatoraccess, BlockPosition blockposition, IBlockData iblockdata, EnumDirection enumdirection, Fluid fluid) {
/* 157 */     if (enumdirection == EnumDirection.DOWN) {
/* 158 */       Fluid fluid1 = generatoraccess.getFluid(blockposition);
/*     */       
/* 160 */       if (a(TagsFluid.LAVA) && fluid1.a(TagsFluid.WATER)) {
/* 161 */         if (iblockdata.getBlock() instanceof BlockFluids)
/*     */         {
/* 163 */           if (!CraftEventFactory.handleBlockFormEvent(generatoraccess.getMinecraftWorld(), blockposition, Blocks.STONE.getBlockData(), 3)) {
/*     */             return;
/*     */           }
/*     */         }
/*     */ 
/*     */         
/* 169 */         a(generatoraccess, blockposition);
/*     */         
/*     */         return;
/*     */       } 
/*     */     } 
/* 174 */     super.a(generatoraccess, blockposition, iblockdata, enumdirection, fluid);
/*     */   }
/*     */ 
/*     */   
/*     */   protected boolean j() {
/* 179 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   protected float c() {
/* 184 */     return 100.0F;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class a
/*     */     extends FluidTypeLava
/*     */   {
/*     */     protected void a(BlockStateList.a<FluidType, Fluid> blockstatelist_a) {
/* 193 */       super.a(blockstatelist_a);
/* 194 */       blockstatelist_a.a((IBlockState<?>[])new IBlockState[] { LEVEL });
/*     */     }
/*     */ 
/*     */     
/*     */     public int d(Fluid fluid) {
/* 199 */       return ((Integer)fluid.get(LEVEL)).intValue();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean c(Fluid fluid) {
/* 204 */       return false;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static class b
/*     */     extends FluidTypeLava
/*     */   {
/*     */     public int d(Fluid fluid) {
/* 214 */       return 8;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean c(Fluid fluid) {
/* 219 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\FluidTypeLava.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */