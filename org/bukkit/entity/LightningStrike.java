/*    */ package org.bukkit.entity;
/*    */ 
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public interface LightningStrike
/*    */   extends Entity
/*    */ {
/*    */   boolean isEffect();
/*    */   
/*    */   @NotNull
/*    */   Spigot spigot();
/*    */   
/*    */   public static class Spigot
/*    */     extends Entity.Spigot
/*    */   {
/*    */     public boolean isSilent() {
/* 26 */       throw new UnsupportedOperationException("Not supported yet.");
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\entity\LightningStrike.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */