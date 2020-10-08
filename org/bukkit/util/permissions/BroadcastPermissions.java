/*    */ package org.bukkit.util.permissions;
/*    */ 
/*    */ import org.bukkit.permissions.Permission;
/*    */ import org.bukkit.permissions.PermissionDefault;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public final class BroadcastPermissions
/*    */ {
/*    */   private static final String ROOT = "bukkit.broadcast";
/*    */   private static final String PREFIX = "bukkit.broadcast.";
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermissions(@NotNull Permission parent) {
/* 15 */     Permission broadcasts = DefaultPermissions.registerPermission("bukkit.broadcast", "Allows the user to receive all broadcast messages", parent);
/*    */     
/* 17 */     DefaultPermissions.registerPermission("bukkit.broadcast.admin", "Allows the user to receive administrative broadcasts", PermissionDefault.OP, broadcasts);
/* 18 */     DefaultPermissions.registerPermission("bukkit.broadcast.user", "Allows the user to receive user broadcasts", PermissionDefault.TRUE, broadcasts);
/*    */     
/* 20 */     broadcasts.recalculatePermissibles();
/*    */     
/* 22 */     return broadcasts;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\permissions\BroadcastPermissions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */