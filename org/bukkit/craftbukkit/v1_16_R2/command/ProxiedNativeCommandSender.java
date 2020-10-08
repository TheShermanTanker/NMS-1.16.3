/*     */ package org.bukkit.craftbukkit.v1_16_R2.command;
/*     */ 
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.CommandListenerWrapper;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.command.ProxiedCommandSender;
/*     */ import org.bukkit.permissions.Permission;
/*     */ import org.bukkit.permissions.PermissionAttachment;
/*     */ import org.bukkit.permissions.PermissionAttachmentInfo;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class ProxiedNativeCommandSender
/*     */   implements ProxiedCommandSender
/*     */ {
/*     */   private final CommandListenerWrapper orig;
/*     */   private final CommandSender caller;
/*     */   private final CommandSender callee;
/*     */   
/*     */   public ProxiedNativeCommandSender(CommandListenerWrapper orig, CommandSender caller, CommandSender callee) {
/*  21 */     this.orig = orig;
/*  22 */     this.caller = caller;
/*  23 */     this.callee = callee;
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper getHandle() {
/*  27 */     return this.orig;
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandSender getCaller() {
/*  32 */     return this.caller;
/*     */   }
/*     */ 
/*     */   
/*     */   public CommandSender getCallee() {
/*  37 */     return this.callee;
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessage(String message) {
/*  42 */     getCaller().sendMessage(message);
/*     */   }
/*     */ 
/*     */   
/*     */   public void sendMessage(String[] messages) {
/*  47 */     getCaller().sendMessage(messages);
/*     */   }
/*     */ 
/*     */   
/*     */   public Server getServer() {
/*  52 */     return getCallee().getServer();
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  57 */     return getCallee().getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPermissionSet(String name) {
/*  62 */     return getCaller().isPermissionSet(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPermissionSet(Permission perm) {
/*  67 */     return getCaller().isPermissionSet(perm);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPermission(String name) {
/*  72 */     return getCaller().hasPermission(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPermission(Permission perm) {
/*  77 */     return getCaller().hasPermission(perm);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
/*  82 */     return getCaller().addAttachment(plugin, name, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin) {
/*  87 */     return getCaller().addAttachment(plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
/*  92 */     return getCaller().addAttachment(plugin, name, value, ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
/*  97 */     return getCaller().addAttachment(plugin, ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAttachment(PermissionAttachment attachment) {
/* 102 */     getCaller().removeAttachment(attachment);
/*     */   }
/*     */ 
/*     */   
/*     */   public void recalculatePermissions() {
/* 107 */     getCaller().recalculatePermissions();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<PermissionAttachmentInfo> getEffectivePermissions() {
/* 112 */     return getCaller().getEffectivePermissions();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOp() {
/* 117 */     return getCaller().isOp();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOp(boolean value) {
/* 122 */     getCaller().setOp(value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandSender.Spigot spigot() {
/* 129 */     return getCaller().spigot();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\command\ProxiedNativeCommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */