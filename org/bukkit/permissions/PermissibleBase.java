/*     */ package org.bukkit.permissions;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class PermissibleBase
/*     */   implements Permissible
/*     */ {
/*     */   private ServerOperator opable;
/*  20 */   private Permissible parent = this;
/*  21 */   private final List<PermissionAttachment> attachments = new LinkedList<>();
/*  22 */   private final Map<String, PermissionAttachmentInfo> permissions = new HashMap<>();
/*     */   
/*     */   public PermissibleBase(@Nullable ServerOperator opable) {
/*  25 */     this.opable = opable;
/*     */     
/*  27 */     if (opable instanceof Permissible) {
/*  28 */       this.parent = (Permissible)opable;
/*     */     }
/*     */     
/*  31 */     recalculatePermissions();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOp() {
/*  36 */     if (this.opable == null) {
/*  37 */       return false;
/*     */     }
/*  39 */     return this.opable.isOp();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void setOp(boolean value) {
/*  45 */     if (this.opable == null) {
/*  46 */       throw new UnsupportedOperationException("Cannot change op value as no ServerOperator is set");
/*     */     }
/*  48 */     this.opable.setOp(value);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isPermissionSet(@NotNull String name) {
/*  54 */     if (name == null) {
/*  55 */       throw new IllegalArgumentException("Permission name cannot be null");
/*     */     }
/*     */     
/*  58 */     return this.permissions.containsKey(name.toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPermissionSet(@NotNull Permission perm) {
/*  63 */     if (perm == null) {
/*  64 */       throw new IllegalArgumentException("Permission cannot be null");
/*     */     }
/*     */     
/*  67 */     return isPermissionSet(perm.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPermission(@NotNull String inName) {
/*  72 */     if (inName == null) {
/*  73 */       throw new IllegalArgumentException("Permission name cannot be null");
/*     */     }
/*     */     
/*  76 */     String name = inName.toLowerCase(Locale.ENGLISH);
/*     */ 
/*     */     
/*  79 */     PermissionAttachmentInfo info = this.permissions.get(name);
/*  80 */     if (info != null) {
/*  81 */       return info.getValue();
/*     */     }
/*     */     
/*  84 */     Permission perm = Bukkit.getServer().getPluginManager().getPermission(name);
/*     */     
/*  86 */     if (perm != null) {
/*  87 */       return perm.getDefault().getValue(isOp());
/*     */     }
/*  89 */     return Permission.DEFAULT_PERMISSION.getValue(isOp());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean hasPermission(@NotNull Permission perm) {
/*  96 */     if (perm == null) {
/*  97 */       throw new IllegalArgumentException("Permission cannot be null");
/*     */     }
/*     */     
/* 100 */     String name = perm.getName().toLowerCase(Locale.ENGLISH);
/*     */ 
/*     */     
/* 103 */     PermissionAttachmentInfo info = this.permissions.get(name);
/* 104 */     if (info != null) {
/* 105 */       return info.getValue();
/*     */     }
/*     */     
/* 108 */     return perm.getDefault().getValue(isOp());
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public synchronized PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
/* 114 */     if (name == null)
/* 115 */       throw new IllegalArgumentException("Permission name cannot be null"); 
/* 116 */     if (plugin == null)
/* 117 */       throw new IllegalArgumentException("Plugin cannot be null"); 
/* 118 */     if (!plugin.isEnabled()) {
/* 119 */       throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
/*     */     }
/*     */     
/* 122 */     PermissionAttachment result = addAttachment(plugin);
/* 123 */     result.setPermission(name, value);
/*     */     
/* 125 */     recalculatePermissions();
/*     */     
/* 127 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public synchronized PermissionAttachment addAttachment(@NotNull Plugin plugin) {
/* 133 */     if (plugin == null)
/* 134 */       throw new IllegalArgumentException("Plugin cannot be null"); 
/* 135 */     if (!plugin.isEnabled()) {
/* 136 */       throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
/*     */     }
/*     */     
/* 139 */     PermissionAttachment result = new PermissionAttachment(plugin, this.parent);
/*     */     
/* 141 */     this.attachments.add(result);
/* 142 */     recalculatePermissions();
/*     */     
/* 144 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void removeAttachment(@NotNull PermissionAttachment attachment) {
/* 149 */     if (attachment == null) {
/* 150 */       throw new IllegalArgumentException("Attachment cannot be null");
/*     */     }
/*     */     
/* 153 */     if (this.attachments.contains(attachment)) {
/* 154 */       this.attachments.remove(attachment);
/* 155 */       PermissionRemovedExecutor ex = attachment.getRemovalCallback();
/*     */       
/* 157 */       if (ex != null) {
/* 158 */         ex.attachmentRemoved(attachment);
/*     */       }
/*     */       
/* 161 */       recalculatePermissions();
/*     */     } else {
/* 163 */       throw new IllegalArgumentException("Given attachment is not part of Permissible object " + this.parent);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public synchronized void recalculatePermissions() {
/* 169 */     clearPermissions();
/* 170 */     Set<Permission> defaults = Bukkit.getServer().getPluginManager().getDefaultPermissions(isOp());
/* 171 */     Bukkit.getServer().getPluginManager().subscribeToDefaultPerms(isOp(), this.parent);
/*     */     
/* 173 */     for (Permission perm : defaults) {
/* 174 */       String name = perm.getName().toLowerCase(Locale.ENGLISH);
/* 175 */       this.permissions.put(name, new PermissionAttachmentInfo(this.parent, name, null, true));
/* 176 */       Bukkit.getServer().getPluginManager().subscribeToPermission(name, this.parent);
/* 177 */       calculateChildPermissions(perm.getChildren(), false, null);
/*     */     } 
/*     */     
/* 180 */     for (PermissionAttachment attachment : this.attachments) {
/* 181 */       calculateChildPermissions(attachment.getPermissions(), false, attachment);
/*     */     }
/*     */   }
/*     */   
/*     */   public synchronized void clearPermissions() {
/* 186 */     Set<String> perms = this.permissions.keySet();
/*     */     
/* 188 */     for (String name : perms) {
/* 189 */       Bukkit.getServer().getPluginManager().unsubscribeFromPermission(name, this.parent);
/*     */     }
/*     */     
/* 192 */     Bukkit.getServer().getPluginManager().unsubscribeFromDefaultPerms(false, this.parent);
/* 193 */     Bukkit.getServer().getPluginManager().unsubscribeFromDefaultPerms(true, this.parent);
/*     */     
/* 195 */     this.permissions.clear();
/*     */   }
/*     */   
/*     */   private void calculateChildPermissions(@NotNull Map<String, Boolean> children, boolean invert, @Nullable PermissionAttachment attachment) {
/* 199 */     for (Map.Entry<String, Boolean> entry : children.entrySet()) {
/* 200 */       String name = entry.getKey();
/*     */       
/* 202 */       Permission perm = Bukkit.getServer().getPluginManager().getPermission(name);
/* 203 */       boolean value = ((Boolean)entry.getValue()).booleanValue() ^ invert;
/* 204 */       String lname = name.toLowerCase(Locale.ENGLISH);
/*     */       
/* 206 */       this.permissions.put(lname, new PermissionAttachmentInfo(this.parent, lname, attachment, value));
/* 207 */       Bukkit.getServer().getPluginManager().subscribeToPermission(name, this.parent);
/*     */       
/* 209 */       if (perm != null) {
/* 210 */         calculateChildPermissions(perm.getChildren(), !value, attachment);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public synchronized PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
/* 218 */     if (name == null)
/* 219 */       throw new IllegalArgumentException("Permission name cannot be null"); 
/* 220 */     if (plugin == null)
/* 221 */       throw new IllegalArgumentException("Plugin cannot be null"); 
/* 222 */     if (!plugin.isEnabled()) {
/* 223 */       throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
/*     */     }
/*     */     
/* 226 */     PermissionAttachment result = addAttachment(plugin, ticks);
/*     */     
/* 228 */     if (result != null) {
/* 229 */       result.setPermission(name, value);
/*     */     }
/*     */     
/* 232 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public synchronized PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
/* 238 */     if (plugin == null)
/* 239 */       throw new IllegalArgumentException("Plugin cannot be null"); 
/* 240 */     if (!plugin.isEnabled()) {
/* 241 */       throw new IllegalArgumentException("Plugin " + plugin.getDescription().getFullName() + " is disabled");
/*     */     }
/*     */     
/* 244 */     PermissionAttachment result = addAttachment(plugin);
/*     */     
/* 246 */     if (Bukkit.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new RemoveAttachmentRunnable(result), ticks) == -1) {
/* 247 */       Bukkit.getServer().getLogger().log(Level.WARNING, "Could not add PermissionAttachment to " + this.parent + " for plugin " + plugin.getDescription().getFullName() + ": Scheduler returned -1");
/* 248 */       result.remove();
/* 249 */       return null;
/*     */     } 
/* 251 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public synchronized Set<PermissionAttachmentInfo> getEffectivePermissions() {
/* 258 */     return new HashSet<>(this.permissions.values());
/*     */   }
/*     */   
/*     */   private static class RemoveAttachmentRunnable implements Runnable {
/*     */     private PermissionAttachment attachment;
/*     */     
/*     */     public RemoveAttachmentRunnable(@NotNull PermissionAttachment attachment) {
/* 265 */       this.attachment = attachment;
/*     */     }
/*     */ 
/*     */     
/*     */     public void run() {
/* 270 */       this.attachment.remove();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\permissions\PermissibleBase.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */