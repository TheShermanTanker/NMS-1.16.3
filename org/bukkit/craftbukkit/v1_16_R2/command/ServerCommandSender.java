/*     */ package org.bukkit.craftbukkit.v1_16_R2.command;
/*     */ import java.util.Set;
/*     */ import net.md_5.bungee.api.chat.BaseComponent;
/*     */ import net.md_5.bungee.api.chat.TextComponent;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.permissions.PermissibleBase;
/*     */ import org.bukkit.permissions.Permission;
/*     */ import org.bukkit.permissions.PermissionAttachment;
/*     */ import org.bukkit.permissions.PermissionAttachmentInfo;
/*     */ import org.bukkit.permissions.ServerOperator;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public abstract class ServerCommandSender implements CommandSender {
/*     */   private static PermissibleBase blockPermInst;
/*     */   
/*     */   public ServerCommandSender() {
/*  18 */     if (this instanceof CraftBlockCommandSender) {
/*  19 */       if (blockPermInst == null) {
/*  20 */         blockPermInst = new PermissibleBase((ServerOperator)this);
/*     */       }
/*  22 */       this.perm = blockPermInst;
/*     */     } else {
/*  24 */       this.perm = new PermissibleBase((ServerOperator)this);
/*     */     } 
/*     */   }
/*     */   private final PermissibleBase perm;
/*     */   
/*     */   public boolean isPermissionSet(String name) {
/*  30 */     return this.perm.isPermissionSet(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPermissionSet(Permission perm) {
/*  35 */     return this.perm.isPermissionSet(perm);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPermission(String name) {
/*  40 */     return this.perm.hasPermission(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPermission(Permission perm) {
/*  45 */     return this.perm.hasPermission(perm);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
/*  50 */     return this.perm.addAttachment(plugin, name, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin) {
/*  55 */     return this.perm.addAttachment(plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
/*  60 */     return this.perm.addAttachment(plugin, name, value, ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
/*  65 */     return this.perm.addAttachment(plugin, ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAttachment(PermissionAttachment attachment) {
/*  70 */     this.perm.removeAttachment(attachment);
/*     */   }
/*     */ 
/*     */   
/*     */   public void recalculatePermissions() {
/*  75 */     this.perm.recalculatePermissions();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<PermissionAttachmentInfo> getEffectivePermissions() {
/*  80 */     return this.perm.getEffectivePermissions();
/*     */   }
/*     */   
/*     */   public boolean isPlayer() {
/*  84 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public Server getServer() {
/*  89 */     return Bukkit.getServer();
/*     */   }
/*     */ 
/*     */   
/*  93 */   private final CommandSender.Spigot spigot = new CommandSender.Spigot()
/*     */     {
/*     */       
/*     */       public void sendMessage(BaseComponent component)
/*     */       {
/*  98 */         ServerCommandSender.this.sendMessage(TextComponent.toLegacyText(new BaseComponent[] { component }));
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public void sendMessage(BaseComponent... components) {
/* 104 */         ServerCommandSender.this.sendMessage(TextComponent.toLegacyText(components));
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */   
/*     */   public CommandSender.Spigot spigot() {
/* 111 */     return this.spigot;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\command\ServerCommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */