/*    */ package org.bukkit.command;
/*    */ 
/*    */ import net.md_5.bungee.api.chat.BaseComponent;
/*    */ import net.md_5.bungee.api.chat.TextComponent;
/*    */ import org.bukkit.Server;
/*    */ import org.bukkit.permissions.Permissible;
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
/*    */ public interface CommandSender
/*    */   extends Permissible
/*    */ {
/*    */   void sendMessage(@NotNull String paramString);
/*    */   
/*    */   void sendMessage(@NotNull String[] paramArrayOfString);
/*    */   
/*    */   @NotNull
/*    */   Server getServer();
/*    */   
/*    */   @NotNull
/*    */   String getName();
/*    */   
/*    */   @NotNull
/*    */   Spigot spigot();
/*    */   
/*    */   public static class Spigot
/*    */   {
/*    */     public void sendMessage(@NotNull BaseComponent component) {
/* 48 */       throw new UnsupportedOperationException("Not supported yet.");
/*    */     }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/*    */     public void sendMessage(@NotNull BaseComponent... components) {
/* 57 */       throw new UnsupportedOperationException("Not supported yet.");
/*    */     }
/*    */   }
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
/*    */   default void sendMessage(@NotNull BaseComponent component) {
/* 75 */     sendMessage(component.toLegacyText());
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   void sendMessage(@NotNull BaseComponent... components) {
/* 87 */     sendMessage((new TextComponent(components)).toLegacyText());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\CommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */