/*    */ package org.bukkit.craftbukkit.v1_16_R2.command;
/*    */ 
/*    */ import com.destroystokyo.paper.PaperConfig;
/*    */ import net.minecraft.server.v1_16_R2.ChatComponentText;
/*    */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*    */ import net.minecraft.server.v1_16_R2.RemoteControlCommandListener;
/*    */ import net.minecraft.server.v1_16_R2.SystemUtils;
/*    */ import org.bukkit.command.RemoteConsoleCommandSender;
/*    */ import org.bukkit.permissions.Permission;
/*    */ 
/*    */ public class CraftRemoteConsoleCommandSender extends ServerCommandSender implements RemoteConsoleCommandSender {
/*    */   public CraftRemoteConsoleCommandSender(RemoteControlCommandListener listener) {
/* 13 */     this.listener = listener;
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendMessage(String message) {
/* 18 */     this.listener.sendMessage((IChatBaseComponent)new ChatComponentText(message + "\n"), SystemUtils.b);
/*    */   }
/*    */   private final RemoteControlCommandListener listener;
/*    */   
/*    */   public void sendMessage(String[] messages) {
/* 23 */     for (String message : messages) {
/* 24 */       sendMessage(message);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 30 */     return "Rcon";
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOp() {
/* 35 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOp(boolean value) {
/* 40 */     throw new UnsupportedOperationException("Cannot change operator status of remote controller.");
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public boolean hasPermission(String name) {
/* 46 */     return (PaperConfig.consoleHasAllPermissions || super.hasPermission(name));
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean hasPermission(Permission perm) {
/* 51 */     return (PaperConfig.consoleHasAllPermissions || super.hasPermission(perm));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\command\CraftRemoteConsoleCommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */