/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class ItemBoneMeal
/*     */   extends Item {
/*     */   public ItemBoneMeal(Item.Info item_info) {
/*  10 */     super(item_info);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(ItemActionContext itemactioncontext) {
/*  16 */     return applyBonemeal(itemactioncontext);
/*     */   }
/*     */ 
/*     */   
/*     */   public static EnumInteractionResult applyBonemeal(ItemActionContext itemactioncontext) {
/*  21 */     World world = itemactioncontext.getWorld();
/*  22 */     BlockPosition blockposition = itemactioncontext.getClickPosition();
/*  23 */     BlockPosition blockposition1 = blockposition.shift(itemactioncontext.getClickedFace());
/*     */     
/*  25 */     if (a(itemactioncontext.getItemStack(), world, blockposition)) {
/*  26 */       if (!world.isClientSide) {
/*  27 */         world.triggerEffect(2005, blockposition, 0);
/*     */       }
/*     */       
/*  30 */       return EnumInteractionResult.a(world.isClientSide);
/*     */     } 
/*  32 */     IBlockData iblockdata = world.getType(blockposition);
/*  33 */     boolean flag = iblockdata.d(world, blockposition, itemactioncontext.getClickedFace());
/*     */     
/*  35 */     if (flag && a(itemactioncontext.getItemStack(), world, blockposition1, itemactioncontext.getClickedFace())) {
/*  36 */       if (!world.isClientSide) {
/*  37 */         world.triggerEffect(2005, blockposition1, 0);
/*     */       }
/*     */       
/*  40 */       return EnumInteractionResult.a(world.isClientSide);
/*     */     } 
/*  42 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static boolean a(ItemStack itemstack, World world, BlockPosition blockposition) {
/*  48 */     IBlockData iblockdata = world.getType(blockposition);
/*     */     
/*  50 */     if (iblockdata.getBlock() instanceof IBlockFragilePlantElement) {
/*  51 */       IBlockFragilePlantElement iblockfragileplantelement = (IBlockFragilePlantElement)iblockdata.getBlock();
/*     */       
/*  53 */       if (iblockfragileplantelement.a(world, blockposition, iblockdata, world.isClientSide)) {
/*  54 */         if (world instanceof WorldServer) {
/*  55 */           if (iblockfragileplantelement.a(world, world.random, blockposition, iblockdata)) {
/*  56 */             iblockfragileplantelement.a((WorldServer)world, world.random, blockposition, iblockdata);
/*     */           }
/*     */           
/*  59 */           itemstack.subtract(1);
/*     */         } 
/*     */         
/*  62 */         return true;
/*     */       } 
/*     */     } 
/*     */     
/*  66 */     return false;
/*     */   }
/*     */   
/*     */   public static boolean a(ItemStack itemstack, World world, BlockPosition blockposition, @Nullable EnumDirection enumdirection) {
/*  70 */     if (world.getType(blockposition).a(Blocks.WATER) && world.getFluid(blockposition).e() == 8) {
/*  71 */       if (!(world instanceof WorldServer)) {
/*  72 */         return true;
/*     */       }
/*  74 */       int i = 0;
/*     */       
/*  76 */       label47: while (i < 128) {
/*  77 */         BlockPosition blockposition1 = blockposition;
/*  78 */         IBlockData iblockdata = Blocks.SEAGRASS.getBlockData();
/*  79 */         int j = 0;
/*     */ 
/*     */         
/*  82 */         while (j < i / 16) {
/*  83 */           blockposition1 = blockposition1.b(RANDOM.nextInt(3) - 1, (RANDOM.nextInt(3) - 1) * RANDOM.nextInt(3) / 2, RANDOM.nextInt(3) - 1);
/*  84 */           if (!world.getType(blockposition1).r(world, blockposition1)) {
/*  85 */             j++; continue;
/*     */           } 
/*     */           continue label47;
/*     */         } 
/*  89 */         Optional<ResourceKey<BiomeBase>> optional = world.i(blockposition1);
/*     */         
/*  91 */         if (Objects.equals(optional, Optional.of(Biomes.WARM_OCEAN)) || Objects.equals(optional, Optional.of(Biomes.DEEP_WARM_OCEAN))) {
/*  92 */           if (i == 0 && enumdirection != null && enumdirection.n().d()) {
/*  93 */             iblockdata = ((Block)TagsBlock.WALL_CORALS.a(world.random)).getBlockData().set(BlockCoralFanWallAbstract.a, enumdirection);
/*  94 */           } else if (RANDOM.nextInt(4) == 0) {
/*  95 */             iblockdata = ((Block)TagsBlock.UNDERWATER_BONEMEALS.a(RANDOM)).getBlockData();
/*     */           } 
/*     */         }
/*     */         
/*  99 */         if (iblockdata.getBlock().a(TagsBlock.WALL_CORALS)) {
/* 100 */           for (int k = 0; !iblockdata.canPlace(world, blockposition1) && k < 4; k++) {
/* 101 */             iblockdata = iblockdata.set(BlockCoralFanWallAbstract.a, EnumDirection.EnumDirectionLimit.HORIZONTAL.a(RANDOM));
/*     */           }
/*     */         }
/*     */         
/* 105 */         if (iblockdata.canPlace(world, blockposition1)) {
/* 106 */           IBlockData iblockdata1 = world.getType(blockposition1);
/*     */           
/* 108 */           if (iblockdata1.a(Blocks.WATER) && world.getFluid(blockposition1).e() == 8) {
/* 109 */             world.setTypeAndData(blockposition1, iblockdata, 3);
/* 110 */           } else if (iblockdata1.a(Blocks.SEAGRASS) && RANDOM.nextInt(10) == 0) {
/* 111 */             ((IBlockFragilePlantElement)Blocks.SEAGRASS).a((WorldServer)world, RANDOM, blockposition1, iblockdata1);
/*     */           } 
/*     */         } 
/*     */ 
/*     */         
/* 116 */         i++;
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 121 */       itemstack.subtract(1);
/* 122 */       return true;
/*     */     } 
/*     */     
/* 125 */     return false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemBoneMeal.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */