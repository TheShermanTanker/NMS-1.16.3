/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ItemSpade
/*     */   extends ItemTool
/*     */ {
/*  24 */   private static final Set<Block> c = Sets.newHashSet((Object[])new Block[] { Blocks.CLAY, Blocks.DIRT, Blocks.COARSE_DIRT, Blocks.PODZOL, Blocks.FARMLAND, Blocks.GRASS_BLOCK, Blocks.GRAVEL, Blocks.MYCELIUM, Blocks.SAND, Blocks.RED_SAND, Blocks.SNOW_BLOCK, Blocks.SNOW, Blocks.SOUL_SAND, Blocks.GRASS_PATH, Blocks.WHITE_CONCRETE_POWDER, Blocks.ORANGE_CONCRETE_POWDER, Blocks.MAGENTA_CONCRETE_POWDER, Blocks.LIGHT_BLUE_CONCRETE_POWDER, Blocks.YELLOW_CONCRETE_POWDER, Blocks.LIME_CONCRETE_POWDER, Blocks.PINK_CONCRETE_POWDER, Blocks.GRAY_CONCRETE_POWDER, Blocks.LIGHT_GRAY_CONCRETE_POWDER, Blocks.CYAN_CONCRETE_POWDER, Blocks.PURPLE_CONCRETE_POWDER, Blocks.BLUE_CONCRETE_POWDER, Blocks.BROWN_CONCRETE_POWDER, Blocks.GREEN_CONCRETE_POWDER, Blocks.RED_CONCRETE_POWDER, Blocks.BLACK_CONCRETE_POWDER, Blocks.SOUL_SOIL });
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
/*     */ 
/*     */ 
/*     */   
/*  58 */   protected static final Map<Block, IBlockData> a = Maps.newHashMap((Map)ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.GRASS_PATH
/*  59 */         .getBlockData()));
/*     */ 
/*     */   
/*     */   public ItemSpade(ToolMaterial var0, float var1, float var2, Item.Info var3) {
/*  63 */     super(var1, var2, var0, c, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean canDestroySpecialBlock(IBlockData var0) {
/*  68 */     return (var0.a(Blocks.SNOW) || var0.a(Blocks.SNOW_BLOCK));
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(ItemActionContext var0) {
/*  73 */     World var1 = var0.getWorld();
/*  74 */     BlockPosition var2 = var0.getClickPosition();
/*     */     
/*  76 */     IBlockData var3 = var1.getType(var2);
/*  77 */     if (var0.getClickedFace() != EnumDirection.DOWN) {
/*  78 */       EntityHuman var4 = var0.getEntity();
/*  79 */       IBlockData var5 = a.get(var3.getBlock());
/*  80 */       IBlockData var6 = null;
/*     */       
/*  82 */       if (var5 != null && var1.getType(var2.up()).isAir()) {
/*  83 */         var1.playSound(var4, var2, SoundEffects.ITEM_SHOVEL_FLATTEN, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*  84 */         var6 = var5;
/*  85 */       } else if (var3.getBlock() instanceof BlockCampfire && ((Boolean)var3.get(BlockCampfire.b)).booleanValue()) {
/*  86 */         if (!var1.s_()) {
/*  87 */           var1.a((EntityHuman)null, 1009, var2, 0);
/*     */         }
/*  89 */         BlockCampfire.c(var1, var2, var3);
/*  90 */         var6 = var3.set(BlockCampfire.b, Boolean.valueOf(false));
/*     */       } 
/*     */       
/*  93 */       if (var6 != null) {
/*  94 */         if (!var1.isClientSide) {
/*  95 */           var1.setTypeAndData(var2, var6, 11);
/*  96 */           if (var4 != null) {
/*  97 */             var0.getItemStack().damage(1, var4, var1 -> var1.broadcastItemBreak(var0.getHand()));
/*     */           }
/*     */         } 
/* 100 */         return EnumInteractionResult.a(var1.isClientSide);
/*     */       } 
/* 102 */       return EnumInteractionResult.PASS;
/*     */     } 
/*     */     
/* 105 */     return EnumInteractionResult.PASS;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemSpade.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */