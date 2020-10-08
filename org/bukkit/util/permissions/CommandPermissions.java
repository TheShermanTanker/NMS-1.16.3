/*    */ package org.bukkit.util.permissions;
/*    */ 
/*    */ import org.bukkit.permissions.Permission;
/*    */ import org.bukkit.permissions.PermissionDefault;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public final class CommandPermissions
/*    */ {
/*    */   private static final String ROOT = "bukkit.command";
/*    */   private static final String PREFIX = "bukkit.command.";
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermissions(@NotNull Permission parent) {
/* 15 */     Permission commands = DefaultPermissions.registerPermission("bukkit.command", "Gives the user the ability to use all CraftBukkit commands", parent);
/*    */     
/* 17 */     DefaultPermissions.registerPermission("bukkit.command.help", "Allows the user to view the vanilla help menu", PermissionDefault.TRUE, commands);
/* 18 */     DefaultPermissions.registerPermission("bukkit.command.plugins", "Allows the user to view the list of plugins running on this server", PermissionDefault.TRUE, commands);
/* 19 */     DefaultPermissions.registerPermission("bukkit.command.reload", "Allows the user to reload the server settings", PermissionDefault.OP, commands);
/* 20 */     DefaultPermissions.registerPermission("bukkit.command.version", "Allows the user to view the version of the server", PermissionDefault.TRUE, commands);
/*    */     
/* 22 */     commands.recalculatePermissibles();
/* 23 */     return commands;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\permissions\CommandPermissions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */