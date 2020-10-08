/*     */ package org.bukkit.craftbukkit.v1_16_R2.entity;
/*     */ import java.util.Set;
/*     */ import net.minecraft.server.v1_16_R2.Entity;
/*     */ import net.minecraft.server.v1_16_R2.EntityMinecartAbstract;
/*     */ import net.minecraft.server.v1_16_R2.EntityMinecartCommandBlock;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*     */ import org.bukkit.entity.EntityType;
/*     */ import org.bukkit.permissions.PermissibleBase;
/*     */ import org.bukkit.permissions.Permission;
/*     */ import org.bukkit.permissions.PermissionAttachment;
/*     */ import org.bukkit.permissions.PermissionAttachmentInfo;
/*     */ import org.bukkit.permissions.ServerOperator;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ 
/*     */ public class CraftMinecartCommand extends CraftMinecart implements CommandMinecart {
/*  18 */   private final PermissibleBase perm = new PermissibleBase((ServerOperator)this);
/*     */   
/*     */   public CraftMinecartCommand(CraftServer server, EntityMinecartCommandBlock entity) {
/*  21 */     super(server, (EntityMinecartAbstract)entity);
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityMinecartCommandBlock getHandle() {
/*  26 */     return (EntityMinecartCommandBlock)this.entity;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getCommand() {
/*  31 */     return getHandle().getCommandBlock().getCommand();
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCommand(String command) {
/*  36 */     getHandle().getCommandBlock().setCommand((command != null) ? command : "");
/*  37 */     getHandle().getDataWatcher().set(EntityMinecartCommandBlock.COMMAND, getHandle().getCommandBlock().getCommand());
/*     */   }
/*     */ 
/*     */   
/*     */   public void setName(String name) {
/*  42 */     getHandle().getCommandBlock().setName(CraftChatMessage.fromStringOrNull(name));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  47 */     return "CraftMinecartCommand";
/*     */   }
/*     */ 
/*     */   
/*     */   public EntityType getType() {
/*  52 */     return EntityType.MINECART_COMMAND;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(String message) {}
/*     */ 
/*     */ 
/*     */   
/*     */   public void sendMessage(String[] messages) {}
/*     */ 
/*     */   
/*     */   public String getName() {
/*  65 */     return CraftChatMessage.fromComponent(getHandle().getCommandBlock().getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOp() {
/*  70 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setOp(boolean value) {
/*  75 */     throw new UnsupportedOperationException("Cannot change operator status of a minecart");
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPermissionSet(String name) {
/*  80 */     return this.perm.isPermissionSet(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isPermissionSet(Permission perm) {
/*  85 */     return this.perm.isPermissionSet(perm);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPermission(String name) {
/*  90 */     return this.perm.hasPermission(name);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasPermission(Permission perm) {
/*  95 */     return this.perm.hasPermission(perm);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value) {
/* 100 */     return this.perm.addAttachment(plugin, name, value);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin) {
/* 105 */     return this.perm.addAttachment(plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, String name, boolean value, int ticks) {
/* 110 */     return this.perm.addAttachment(plugin, name, value, ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public PermissionAttachment addAttachment(Plugin plugin, int ticks) {
/* 115 */     return this.perm.addAttachment(plugin, ticks);
/*     */   }
/*     */ 
/*     */   
/*     */   public void removeAttachment(PermissionAttachment attachment) {
/* 120 */     this.perm.removeAttachment(attachment);
/*     */   }
/*     */ 
/*     */   
/*     */   public void recalculatePermissions() {
/* 125 */     this.perm.recalculatePermissions();
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<PermissionAttachmentInfo> getEffectivePermissions() {
/* 130 */     return this.perm.getEffectivePermissions();
/*     */   }
/*     */ 
/*     */   
/*     */   public Server getServer() {
/* 135 */     return Bukkit.getServer();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\entity\CraftMinecartCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */