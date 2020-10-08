/*     */ package org.bukkit.command;
/*     */ 
/*     */ import java.util.Set;
/*     */ import org.apache.commons.lang.NotImplementedException;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.permissions.Permission;
/*     */ import org.bukkit.permissions.PermissionAttachment;
/*     */ import org.bukkit.permissions.PermissionAttachmentInfo;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public interface MessageCommandSender
/*     */   extends CommandSender
/*     */ {
/*     */   default void sendMessage(@NotNull String[] messages) {
/*  21 */     for (String message : messages) {
/*  22 */       sendMessage(message);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default Server getServer() {
/*  29 */     return Bukkit.getServer();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default String getName() {
/*  35 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean isOp() {
/*  40 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   default void setOp(boolean value) {
/*  45 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean isPermissionSet(@NotNull String name) {
/*  50 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean isPermissionSet(@NotNull Permission perm) {
/*  55 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean hasPermission(@NotNull String name) {
/*  60 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   default boolean hasPermission(@NotNull Permission perm) {
/*  65 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value) {
/*  71 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default PermissionAttachment addAttachment(@NotNull Plugin plugin) {
/*  77 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default PermissionAttachment addAttachment(@NotNull Plugin plugin, @NotNull String name, boolean value, int ticks) {
/*  83 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default PermissionAttachment addAttachment(@NotNull Plugin plugin, int ticks) {
/*  89 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   default void removeAttachment(@NotNull PermissionAttachment attachment) {
/*  94 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   default void recalculatePermissions() {
/*  99 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default Set<PermissionAttachmentInfo> getEffectivePermissions() {
/* 105 */     throw new NotImplementedException();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   default CommandSender.Spigot spigot() {
/* 111 */     throw new NotImplementedException();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\MessageCommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */