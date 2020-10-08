/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableMap;
/*    */ import com.google.common.collect.Sets;
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
/*    */ 
/*    */ public class ItemAxe
/*    */   extends ItemTool
/*    */ {
/* 22 */   private static final Set<Material> c = Sets.newHashSet((Object[])new Material[] { Material.WOOD, Material.NETHER_WOOD, Material.PLANT, Material.REPLACEABLE_PLANT, Material.BAMBOO, Material.PUMPKIN });
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 31 */   private static final Set<Block> d = Sets.newHashSet((Object[])new Block[] { Blocks.LADDER, Blocks.SCAFFOLDING, Blocks.OAK_BUTTON, Blocks.SPRUCE_BUTTON, Blocks.BIRCH_BUTTON, Blocks.JUNGLE_BUTTON, Blocks.DARK_OAK_BUTTON, Blocks.ACACIA_BUTTON, Blocks.CRIMSON_BUTTON, Blocks.WARPED_BUTTON });
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
/* 44 */   protected static final Map<Block, Block> a = (Map<Block, Block>)(new ImmutableMap.Builder())
/* 45 */     .put(Blocks.OAK_WOOD, Blocks.STRIPPED_OAK_WOOD)
/* 46 */     .put(Blocks.OAK_LOG, Blocks.STRIPPED_OAK_LOG)
/* 47 */     .put(Blocks.DARK_OAK_WOOD, Blocks.STRIPPED_DARK_OAK_WOOD)
/* 48 */     .put(Blocks.DARK_OAK_LOG, Blocks.STRIPPED_DARK_OAK_LOG)
/* 49 */     .put(Blocks.ACACIA_WOOD, Blocks.STRIPPED_ACACIA_WOOD)
/* 50 */     .put(Blocks.ACACIA_LOG, Blocks.STRIPPED_ACACIA_LOG)
/* 51 */     .put(Blocks.BIRCH_WOOD, Blocks.STRIPPED_BIRCH_WOOD)
/* 52 */     .put(Blocks.BIRCH_LOG, Blocks.STRIPPED_BIRCH_LOG)
/* 53 */     .put(Blocks.JUNGLE_WOOD, Blocks.STRIPPED_JUNGLE_WOOD)
/* 54 */     .put(Blocks.JUNGLE_LOG, Blocks.STRIPPED_JUNGLE_LOG)
/* 55 */     .put(Blocks.SPRUCE_WOOD, Blocks.STRIPPED_SPRUCE_WOOD)
/* 56 */     .put(Blocks.SPRUCE_LOG, Blocks.STRIPPED_SPRUCE_LOG)
/* 57 */     .put(Blocks.WARPED_STEM, Blocks.STRIPPED_WARPED_STEM)
/* 58 */     .put(Blocks.WARPED_HYPHAE, Blocks.STRIPPED_WARPED_HYPHAE)
/* 59 */     .put(Blocks.CRIMSON_STEM, Blocks.STRIPPED_CRIMSON_STEM)
/* 60 */     .put(Blocks.CRIMSON_HYPHAE, Blocks.STRIPPED_CRIMSON_HYPHAE)
/* 61 */     .build();
/*    */   
/*    */   protected ItemAxe(ToolMaterial var0, float var1, float var2, Item.Info var3) {
/* 64 */     super(var1, var2, var0, d, var3);
/*    */   }
/*    */ 
/*    */   
/*    */   public float getDestroySpeed(ItemStack var0, IBlockData var1) {
/* 69 */     Material var2 = var1.getMaterial();
/* 70 */     if (c.contains(var2)) {
/* 71 */       return this.b;
/*    */     }
/* 73 */     return super.getDestroySpeed(var0, var1);
/*    */   }
/*    */ 
/*    */   
/*    */   public EnumInteractionResult a(ItemActionContext var0) {
/* 78 */     World var1 = var0.getWorld();
/* 79 */     BlockPosition var2 = var0.getClickPosition();
/* 80 */     IBlockData var3 = var1.getType(var2);
/* 81 */     Block var4 = a.get(var3.getBlock());
/*    */     
/* 83 */     if (var4 != null) {
/* 84 */       EntityHuman var5 = var0.getEntity();
/* 85 */       var1.playSound(var5, var2, SoundEffects.ITEM_AXE_STRIP, SoundCategory.BLOCKS, 1.0F, 1.0F);
/*    */       
/* 87 */       if (!var1.isClientSide) {
/* 88 */         var1.setTypeAndData(var2, var4.getBlockData().set(BlockRotatable.AXIS, var3.get(BlockRotatable.AXIS)), 11);
/*    */         
/* 90 */         if (var5 != null) {
/* 91 */           var0.getItemStack().damage(1, var5, var1 -> var1.broadcastItemBreak(var0.getHand()));
/*    */         }
/*    */       } 
/* 94 */       return EnumInteractionResult.a(var1.isClientSide);
/*    */     } 
/*    */     
/* 97 */     return EnumInteractionResult.PASS;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemAxe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */