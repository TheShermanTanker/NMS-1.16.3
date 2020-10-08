/*    */ package org.bukkit.event.inventory;
/*    */ 
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.event.block.BlockExpEvent;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class FurnaceExtractEvent
/*    */   extends BlockExpEvent
/*    */ {
/*    */   private final Player player;
/*    */   private final Material itemType;
/*    */   private final int itemAmount;
/*    */   
/*    */   public FurnaceExtractEvent(@NotNull Player player, @NotNull Block block, @NotNull Material itemType, int itemAmount, int exp) {
/* 18 */     super(block, exp);
/* 19 */     this.player = player;
/* 20 */     this.itemType = itemType;
/* 21 */     this.itemAmount = itemAmount;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Player getPlayer() {
/* 31 */     return this.player;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Material getItemType() {
/* 41 */     return this.itemType;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int getItemAmount() {
/* 50 */     return this.itemAmount;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\inventory\FurnaceExtractEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */