/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.ImmutableSet;
/*    */ import com.google.common.collect.Maps;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemHoe
/*    */   extends ItemTool
/*    */ {
/* 22 */   private static final Set<Block> c = (Set<Block>)ImmutableSet.of(Blocks.NETHER_WART_BLOCK, Blocks.WARPED_WART_BLOCK, Blocks.HAY_BLOCK, Blocks.DRIED_KELP_BLOCK, Blocks.TARGET, Blocks.SHROOMLIGHT, (Object[])new Block[] { Blocks.SPONGE, Blocks.WET_SPONGE, Blocks.JUNGLE_LEAVES, Blocks.OAK_LEAVES, Blocks.SPRUCE_LEAVES, Blocks.DARK_OAK_LEAVES, Blocks.ACACIA_LEAVES, Blocks.BIRCH_LEAVES });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 39 */   protected static final Map<Block, IBlockData> a = Maps.newHashMap((Map)ImmutableMap.of(Blocks.GRASS_BLOCK, Blocks.FARMLAND
/* 40 */         .getBlockData(), Blocks.GRASS_PATH, Blocks.FARMLAND
/* 41 */         .getBlockData(), Blocks.DIRT, Blocks.FARMLAND
/* 42 */         .getBlockData(), Blocks.COARSE_DIRT, Blocks.DIRT
/* 43 */         .getBlockData()));
/*    */ 
/*    */   
/*    */   protected ItemHoe(ToolMaterial var0, int var1, float var2, Item.Info var3) {
/* 47 */     super(var1, var2, var0, c, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext var0) {
/* 52 */     World var1 = var0.getWorld();
/* 53 */     BlockPosition var2 = var0.getClickPosition();
/*    */     
/* 55 */     if (var0.getClickedFace() != EnumDirection.DOWN && var1.getType(var2.up()).isAir()) {
/* 56 */       IBlockData var3 = a.get(var1.getType(var2).getBlock());
/*    */       
/* 58 */       if (var3 != null) {
/* 59 */         EntityHuman var4 = var0.getEntity();
/* 60 */         var1.playSound(var4, var2, SoundEffects.ITEM_HOE_TILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*    */         
/* 62 */         if (!var1.isClientSide) {
/* 63 */           var1.setTypeAndData(var2, var3, 11);
/* 64 */           if (var4 != null) {
/* 65 */             var0.getItemStack().damage(1, var4, var1 -> var1.broadcastItemBreak(var0.getHand()));
/*    */           }
/*    */         } 
/* 68 */         return EnumInteractionResult.a(var1.isClientSide);
/*    */       } 
/*    */     } 
/*    */     
/* 72 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemHoe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */