/*     */ package org.bukkit.plugin.messaging;
/*     */ 
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class PluginMessageListenerRegistration
/*     */ {
/*     */   private final Messenger messenger;
/*     */   private final Plugin plugin;
/*     */   private final String channel;
/*     */   private final PluginMessageListener listener;
/*     */   
/*     */   public PluginMessageListenerRegistration(@NotNull Messenger messenger, @NotNull Plugin plugin, @NotNull String channel, @NotNull PluginMessageListener listener) {
/*  17 */     if (messenger == null) {
/*  18 */       throw new IllegalArgumentException("Messenger cannot be null!");
/*     */     }
/*  20 */     if (plugin == null) {
/*  21 */       throw new IllegalArgumentException("Plugin cannot be null!");
/*     */     }
/*  23 */     if (channel == null) {
/*  24 */       throw new IllegalArgumentException("Channel cannot be null!");
/*     */     }
/*  26 */     if (listener == null) {
/*  27 */       throw new IllegalArgumentException("Listener cannot be null!");
/*     */     }
/*     */     
/*  30 */     this.messenger = messenger;
/*  31 */     this.plugin = plugin;
/*  32 */     this.channel = channel;
/*  33 */     this.listener = listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getChannel() {
/*  43 */     return this.channel;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PluginMessageListener getListener() {
/*  53 */     return this.listener;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Plugin getPlugin() {
/*  63 */     return this.plugin;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValid() {
/*  72 */     return this.messenger.isRegistrationValid(this);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  77 */     if (obj == null) {
/*  78 */       return false;
/*     */     }
/*  80 */     if (getClass() != obj.getClass()) {
/*  81 */       return false;
/*     */     }
/*  83 */     PluginMessageListenerRegistration other = (PluginMessageListenerRegistration)obj;
/*  84 */     if (this.messenger != other.messenger && !this.messenger.equals(other.messenger)) {
/*  85 */       return false;
/*     */     }
/*  87 */     if (this.plugin != other.plugin && !this.plugin.equals(other.plugin)) {
/*  88 */       return false;
/*     */     }
/*  90 */     if (!this.channel.equals(other.channel)) {
/*  91 */       return false;
/*     */     }
/*  93 */     if (this.listener != other.listener && !this.listener.equals(other.listener)) {
/*  94 */       return false;
/*     */     }
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 101 */     int hash = 7;
/* 102 */     hash = 53 * hash + this.messenger.hashCode();
/* 103 */     hash = 53 * hash + this.plugin.hashCode();
/* 104 */     hash = 53 * hash + this.channel.hashCode();
/* 105 */     hash = 53 * hash + this.listener.hashCode();
/* 106 */     return hash;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\messaging\PluginMessageListenerRegistration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */