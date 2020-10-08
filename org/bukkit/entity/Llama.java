/*    */ package org.bukkit.entity;
/*    */ import org.bukkit.inventory.AbstractHorseInventory;
/*    */ import org.bukkit.inventory.Inventory;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ public interface Llama extends ChestedHorse, RangedEntity {
/*    */   @NotNull
/*    */   Color getColor();
/*    */   
/*    */   void setColor(@NotNull Color paramColor);
/*    */   
/*    */   int getStrength();
/*    */   
/*    */   void setStrength(int paramInt);
/*    */   
/*    */   @NotNull
/*    */   LlamaInventory getInventory();
/*    */   
/*    */   public enum Color {
/* 20 */     CREAMY,
/*    */ 
/*    */ 
/*    */     
/* 24 */     WHITE,
/*    */ 
/*    */ 
/*    */     
/* 28 */     BROWN,
/*    */ 
/*    */ 
/*    */     
/* 32 */     GRAY;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\Llama.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */