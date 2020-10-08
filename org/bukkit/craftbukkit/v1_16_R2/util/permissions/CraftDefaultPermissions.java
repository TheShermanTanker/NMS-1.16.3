/*    */ package org.bukkit.craftbukkit.v1_16_R2.util.permissions;
/*    */ 
/*    */ import org.bukkit.permissions.Permission;
/*    */ import org.bukkit.permissions.PermissionDefault;
/*    */ import org.bukkit.util.permissions.DefaultPermissions;
/*    */ 
/*    */ public final class CraftDefaultPermissions
/*    */ {
/*    */   private static final String ROOT = "minecraft";
/*    */   
/*    */   public static void registerCorePermissions() {
/* 12 */     Permission parent = DefaultPermissions.registerPermission("minecraft", "Gives the user the ability to use all vanilla utilities and commands");
/* 13 */     CommandPermissions.registerPermissions(parent);
/*    */     
/* 15 */     DefaultPermissions.registerPermission("minecraft.nbt.place", "Gives the user the ability to place restricted blocks with NBT in creative", PermissionDefault.OP, parent);
/* 16 */     DefaultPermissions.registerPermission("minecraft.nbt.copy", "Gives the user the ability to copy NBT in creative", PermissionDefault.TRUE, parent);
/* 17 */     DefaultPermissions.registerPermission("minecraft.debugstick", "Gives the user the ability to use the debug stick in creative", PermissionDefault.OP, parent);
/* 18 */     DefaultPermissions.registerPermission("minecraft.debugstick.always", "Gives the user the ability to use the debug stick in all game modes", PermissionDefault.FALSE, parent);
/* 19 */     DefaultPermissions.registerPermission("minecraft.commandblock", "Gives the user the ability to use command blocks.", PermissionDefault.OP, parent);
/*    */     
/* 21 */     parent.recalculatePermissibles();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\permissions\CraftDefaultPermissions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */