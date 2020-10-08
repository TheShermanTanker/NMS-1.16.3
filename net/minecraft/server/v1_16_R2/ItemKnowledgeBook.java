/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import java.util.Optional;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
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
/*    */ public class ItemKnowledgeBook
/*    */   extends Item
/*    */ {
/* 23 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   public ItemKnowledgeBook(Item.Info var0) {
/* 26 */     super(var0);
/*    */   }
/*    */ 
/*    */   
/*    */   public InteractionResultWrapper<ItemStack> a(World var0, EntityHuman var1, EnumHand var2) {
/* 31 */     ItemStack var3 = var1.b(var2);
/* 32 */     NBTTagCompound var4 = var3.getTag();
/*    */     
/* 34 */     if (!var1.abilities.canInstantlyBuild) {
/* 35 */       var1.a(var2, ItemStack.b);
/*    */     }
/*    */     
/* 38 */     if (var4 == null || !var4.hasKeyOfType("Recipes", 9)) {
/* 39 */       LOGGER.error("Tag not valid: {}", var4);
/* 40 */       return InteractionResultWrapper.fail(var3);
/*    */     } 
/*    */     
/* 43 */     if (!var0.isClientSide) {
/* 44 */       NBTTagList var5 = var4.getList("Recipes", 8);
/* 45 */       List<IRecipe<?>> var6 = Lists.newArrayList();
/*    */       
/* 47 */       CraftingManager var7 = var0.getMinecraftServer().getCraftingManager();
/* 48 */       for (int var8 = 0; var8 < var5.size(); var8++) {
/* 49 */         String var9 = var5.getString(var8);
/* 50 */         Optional<? extends IRecipe<?>> var10 = var7.getRecipe(new MinecraftKey(var9));
/* 51 */         if (var10.isPresent()) {
/* 52 */           var6.add(var10.get());
/*    */         } else {
/* 54 */           LOGGER.error("Invalid recipe: {}", var9);
/* 55 */           return InteractionResultWrapper.fail(var3);
/*    */         } 
/*    */       } 
/*    */       
/* 59 */       var1.discoverRecipes(var6);
/* 60 */       var1.b(StatisticList.ITEM_USED.b(this));
/*    */     } 
/*    */     
/* 63 */     return InteractionResultWrapper.a(var3, var0.s_());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemKnowledgeBook.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */