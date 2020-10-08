/*    */ package org.bukkit.craftbukkit.v1_16_R2.inventory;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.objects.Object2ObjectLinkedOpenHashMap;
/*    */ import java.util.Iterator;
/*    */ import java.util.Map;
/*    */ import net.minecraft.server.v1_16_R2.IRecipe;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftKey;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import net.minecraft.server.v1_16_R2.Recipes;
/*    */ import org.bukkit.inventory.Recipe;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RecipeIterator
/*    */   implements Iterator<Recipe>
/*    */ {
/* 17 */   private final Iterator<Map.Entry<Recipes<?>, Object2ObjectLinkedOpenHashMap<MinecraftKey, IRecipe<?>>>> recipes = (MinecraftServer.getServer().getCraftingManager()).recipes.entrySet().iterator();
/*    */   
/*    */   private Iterator<IRecipe<?>> current;
/*    */   
/*    */   public boolean hasNext() {
/* 22 */     if (this.current != null && this.current.hasNext()) {
/* 23 */       return true;
/*    */     }
/*    */     
/* 26 */     if (this.recipes.hasNext()) {
/* 27 */       this.current = (Iterator<IRecipe<?>>)((Object2ObjectLinkedOpenHashMap)((Map.Entry)this.recipes.next()).getValue()).values().iterator();
/* 28 */       return hasNext();
/*    */     } 
/*    */     
/* 31 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public Recipe next() {
/* 36 */     if (this.current == null || !this.current.hasNext()) {
/* 37 */       this.current = (Iterator<IRecipe<?>>)((Object2ObjectLinkedOpenHashMap)((Map.Entry)this.recipes.next()).getValue()).values().iterator();
/* 38 */       return next();
/*    */     } 
/*    */     
/* 41 */     return ((IRecipe)this.current.next()).toBukkitRecipe();
/*    */   }
/*    */ 
/*    */   
/*    */   public void remove() {
/* 46 */     if (this.current == null) {
/* 47 */       throw new IllegalStateException("next() not yet called");
/*    */     }
/*    */     
/* 50 */     this.current.remove();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\inventory\RecipeIterator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */