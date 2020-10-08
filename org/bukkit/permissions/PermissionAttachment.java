/*     */ package org.bukkit.permissions;
/*     */ 
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class PermissionAttachment
/*     */ {
/*     */   private PermissionRemovedExecutor removed;
/*  15 */   private final Map<String, Boolean> permissions = new LinkedHashMap<>();
/*     */   private final Permissible permissible;
/*     */   private final Plugin plugin;
/*     */   
/*     */   public PermissionAttachment(@NotNull Plugin plugin, @NotNull Permissible permissible) {
/*  20 */     if (plugin == null)
/*  21 */       throw new IllegalArgumentException("Plugin cannot be null"); 
/*  22 */     if (!plugin.isEnabled()) {
/*  23 */       throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
/*     */     }
/*     */     
/*  26 */     this.permissible = permissible;
/*  27 */     this.plugin = plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Plugin getPlugin() {
/*  37 */     return this.plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setRemovalCallback(@Nullable PermissionRemovedExecutor ex) {
/*  47 */     this.removed = ex;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public PermissionRemovedExecutor getRemovalCallback() {
/*  58 */     return this.removed;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Permissible getPermissible() {
/*  68 */     return this.permissible;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<String, Boolean> getPermissions() {
/*  82 */     return new LinkedHashMap<>(this.permissions);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPermission(@NotNull String name, boolean value) {
/*  92 */     this.permissions.put(name.toLowerCase(Locale.ENGLISH), Boolean.valueOf(value));
/*  93 */     this.permissible.recalculatePermissions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setPermission(@NotNull Permission perm, boolean value) {
/* 103 */     setPermission(perm.getName(), value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetPermission(@NotNull String name) {
/* 115 */     this.permissions.remove(name.toLowerCase(Locale.ENGLISH));
/* 116 */     this.permissible.recalculatePermissions();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void unsetPermission(@NotNull Permission perm) {
/* 128 */     unsetPermission(perm.getName());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean remove() {
/*     */     try {
/* 139 */       this.permissible.removeAttachment(this);
/* 140 */       return true;
/* 141 */     } catch (IllegalArgumentException ex) {
/* 142 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\permissions\PermissionAttachment.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */