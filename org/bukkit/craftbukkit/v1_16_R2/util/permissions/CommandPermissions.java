/*    */ package org.bukkit.craftbukkit.v1_16_R2.util.permissions;
/*    */ 
/*    */ import org.bukkit.permissions.Permission;
/*    */ import org.bukkit.permissions.PermissionDefault;
/*    */ import org.bukkit.util.permissions.DefaultPermissions;
/*    */ 
/*    */ 
/*    */ public final class CommandPermissions
/*    */ {
/*    */   private static final String ROOT = "minecraft.command";
/*    */   private static final String PREFIX = "minecraft.command.";
/*    */   
/*    */   public static Permission registerPermissions(Permission parent) {
/* 14 */     Permission commands = DefaultPermissions.registerPermission("minecraft.command", "Gives the user the ability to use all vanilla minecraft commands", parent);
/*    */     
/* 16 */     DefaultPermissions.registerPermission("minecraft.command.kill", "Allows the user to commit suicide", PermissionDefault.OP, commands);
/* 17 */     DefaultPermissions.registerPermission("minecraft.command.me", "Allows the user to perform a chat action", PermissionDefault.TRUE, commands);
/* 18 */     DefaultPermissions.registerPermission("minecraft.command.msg", "Allows the user to privately message another player", PermissionDefault.TRUE, commands);
/* 19 */     DefaultPermissions.registerPermission("minecraft.command.help", "Allows the user to access Vanilla command help", PermissionDefault.TRUE, commands);
/* 20 */     DefaultPermissions.registerPermission("minecraft.command.say", "Allows the user to talk as the console", PermissionDefault.OP, commands);
/* 21 */     DefaultPermissions.registerPermission("minecraft.command.give", "Allows the user to give items to players", PermissionDefault.OP, commands);
/* 22 */     DefaultPermissions.registerPermission("minecraft.command.teleport", "Allows the user to teleport players", PermissionDefault.OP, commands);
/* 23 */     DefaultPermissions.registerPermission("minecraft.command.kick", "Allows the user to kick players", PermissionDefault.OP, commands);
/* 24 */     DefaultPermissions.registerPermission("minecraft.command.stop", "Allows the user to stop the server", PermissionDefault.OP, commands);
/* 25 */     DefaultPermissions.registerPermission("minecraft.command.list", "Allows the user to list all online players", PermissionDefault.OP, commands);
/* 26 */     DefaultPermissions.registerPermission("minecraft.command.gamemode", "Allows the user to change the gamemode of another player", PermissionDefault.OP, commands);
/* 27 */     DefaultPermissions.registerPermission("minecraft.command.xp", "Allows the user to give themselves or others arbitrary values of experience", PermissionDefault.OP, commands);
/* 28 */     DefaultPermissions.registerPermission("minecraft.command.toggledownfall", "Allows the user to toggle rain on/off for a given world", PermissionDefault.OP, commands);
/* 29 */     DefaultPermissions.registerPermission("minecraft.command.defaultgamemode", "Allows the user to change the default gamemode of the server", PermissionDefault.OP, commands);
/* 30 */     DefaultPermissions.registerPermission("minecraft.command.seed", "Allows the user to view the seed of the world", PermissionDefault.OP, commands);
/* 31 */     DefaultPermissions.registerPermission("minecraft.command.effect", "Allows the user to add/remove effects on players", PermissionDefault.OP, commands);
/* 32 */     DefaultPermissions.registerPermission("minecraft.command.selector", "Allows the use of selectors", PermissionDefault.OP, commands);
/* 33 */     DefaultPermissions.registerPermission("minecraft.command.trigger", "Allows the use of the trigger command", PermissionDefault.TRUE, commands);
/*    */     
/* 35 */     DefaultPermissions.registerPermission("minecraft.admin.command_feedback", "Receive command broadcasts when sendCommandFeedback is true", PermissionDefault.OP, commands);
/*    */     
/* 37 */     commands.recalculatePermissibles();
/* 38 */     return commands;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\permissions\CommandPermissions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */