/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.List;
/*    */ import net.minecraft.server.v1_16_R2.ItemStack;
/*    */ import net.minecraft.server.v1_16_R2.RecipeItemStack;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.inventory.Recipe;
/*    */ import org.bukkit.inventory.RecipeChoice;
/*    */ 
/*    */ public interface CraftRecipe
/*    */   extends Recipe
/*    */ {
/*    */   default RecipeItemStack toNMS(RecipeChoice bukkit, boolean requireNotEmpty) {
/*    */     RecipeItemStack stack;
/* 18 */     if (bukkit == null) {
/* 19 */       stack = RecipeItemStack.a;
/* 20 */     } else if (bukkit instanceof RecipeChoice.MaterialChoice) {
/* 21 */       stack = new RecipeItemStack(((RecipeChoice.MaterialChoice)bukkit).getChoices().stream().map(mat -> new RecipeItemStack.StackProvider(CraftItemStack.asNMSCopy(new ItemStack(mat)))));
/* 22 */     } else if (bukkit instanceof RecipeChoice.ExactChoice) {
/* 23 */       stack = new RecipeItemStack(((RecipeChoice.ExactChoice)bukkit).getChoices().stream().map(mat -> new RecipeItemStack.StackProvider(CraftItemStack.asNMSCopy(mat))));
/* 24 */       stack.exact = true;
/*    */     } else {
/* 26 */       throw new IllegalArgumentException("Unknown recipe stack instance " + bukkit);
/*    */     } 
/*    */     
/* 29 */     stack.buildChoices();
/* 30 */     if (requireNotEmpty && stack.choices.length == 0) {
/* 31 */       throw new IllegalArgumentException("Recipe requires at least one non-air choice!");
/*    */     }
/*    */     
/* 34 */     return stack;
/*    */   }
/*    */   
/*    */   static RecipeChoice toBukkit(RecipeItemStack list) {
/* 38 */     list.buildChoices();
/*    */     
/* 40 */     if (list.choices.length == 0) {
/* 41 */       return null;
/*    */     }
/*    */     
/* 44 */     if (list.exact) {
/* 45 */       List<ItemStack> list1 = new ArrayList<>(list.choices.length);
/* 46 */       for (ItemStack i : list.choices) {
/* 47 */         list1.add(CraftItemStack.asBukkitCopy(i));
/*    */       }
/*    */       
/* 50 */       return (RecipeChoice)new RecipeChoice.ExactChoice(list1);
/*    */     } 
/*    */     
/* 53 */     List<Material> choices = new ArrayList<>(list.choices.length);
/* 54 */     for (ItemStack i : list.choices) {
/* 55 */       choices.add(CraftMagicNumbers.getMaterial(i.getItem()));
/*    */     }
/*    */     
/* 58 */     return (RecipeChoice)new RecipeChoice.MaterialChoice(choices);
/*    */   }
/*    */   
/*    */   void addToCraftingManager();
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\CraftRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */