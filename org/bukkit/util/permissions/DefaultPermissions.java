/*    */ package org.bukkit.util.permissions;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.permissions.Permission;
/*    */ import org.bukkit.permissions.PermissionDefault;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ public final class DefaultPermissions
/*    */ {
/*    */   private static final String ROOT = "craftbukkit";
/*    */   private static final String LEGACY_PREFIX = "craft";
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermission(@NotNull Permission perm) {
/* 18 */     return registerPermission(perm, true);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermission(@NotNull Permission perm, boolean withLegacy) {
/* 23 */     Permission result = perm;
/*    */     
/*    */     try {
/* 26 */       Bukkit.getPluginManager().addPermission(perm);
/* 27 */     } catch (IllegalArgumentException ex) {
/* 28 */       result = Bukkit.getPluginManager().getPermission(perm.getName());
/* 29 */       assert result != null;
/*    */     } 
/*    */     
/* 32 */     if (withLegacy) {
/* 33 */       Permission legacy = new Permission("craft" + result.getName(), result.getDescription(), PermissionDefault.FALSE);
/* 34 */       legacy.getChildren().put(result.getName(), Boolean.valueOf(true));
/* 35 */       registerPermission(perm, false);
/*    */     } 
/*    */     
/* 38 */     return result;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermission(@NotNull Permission perm, @NotNull Permission parent) {
/* 43 */     parent.getChildren().put(perm.getName(), Boolean.valueOf(true));
/* 44 */     return registerPermission(perm);
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermission(@NotNull String name, @Nullable String desc) {
/* 49 */     Permission perm = registerPermission(new Permission(name, desc));
/* 50 */     return perm;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermission(@NotNull String name, @Nullable String desc, @NotNull Permission parent) {
/* 55 */     Permission perm = registerPermission(name, desc);
/* 56 */     parent.getChildren().put(perm.getName(), Boolean.valueOf(true));
/* 57 */     return perm;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermission(@NotNull String name, @Nullable String desc, @Nullable PermissionDefault def) {
/* 62 */     Permission perm = registerPermission(new Permission(name, desc, def));
/* 63 */     return perm;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermission(@NotNull String name, @Nullable String desc, @Nullable PermissionDefault def, @NotNull Permission parent) {
/* 68 */     Permission perm = registerPermission(name, desc, def);
/* 69 */     parent.getChildren().put(perm.getName(), Boolean.valueOf(true));
/* 70 */     return perm;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermission(@NotNull String name, @Nullable String desc, @Nullable PermissionDefault def, @Nullable Map<String, Boolean> children) {
/* 75 */     Permission perm = registerPermission(new Permission(name, desc, def, children));
/* 76 */     return perm;
/*    */   }
/*    */   
/*    */   @NotNull
/*    */   public static Permission registerPermission(@NotNull String name, @Nullable String desc, @Nullable PermissionDefault def, @Nullable Map<String, Boolean> children, @NotNull Permission parent) {
/* 81 */     Permission perm = registerPermission(name, desc, def, children);
/* 82 */     parent.getChildren().put(perm.getName(), Boolean.valueOf(true));
/* 83 */     return perm;
/*    */   }
/*    */   
/*    */   public static void registerCorePermissions() {
/* 87 */     Permission parent = registerPermission("craftbukkit", "Gives the user the ability to use all CraftBukkit utilities and commands");
/*    */     
/* 89 */     CommandPermissions.registerPermissions(parent);
/* 90 */     BroadcastPermissions.registerPermissions(parent);
/*    */     
/* 92 */     parent.recalculatePermissibles();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukki\\util\permissions\DefaultPermissions.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */