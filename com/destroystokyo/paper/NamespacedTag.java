/*     */ package com.destroystokyo.paper;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.util.Locale;
/*     */ import java.util.UUID;
/*     */ import java.util.regex.Pattern;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ public final class NamespacedTag
/*     */   implements Namespaced
/*     */ {
/*     */   public static final String MINECRAFT = "minecraft";
/*     */   public static final String BUKKIT = "bukkit";
/*  34 */   private static final Pattern VALID_NAMESPACE = Pattern.compile("[a-z0-9._-]+");
/*  35 */   private static final Pattern VALID_KEY = Pattern.compile("[a-z0-9/._-]+");
/*     */ 
/*     */ 
/*     */   
/*     */   private final String namespace;
/*     */ 
/*     */ 
/*     */   
/*     */   private final String key;
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public NamespacedTag(@NotNull String namespace, @NotNull String key) {
/*  49 */     Preconditions.checkArgument((namespace != null && VALID_NAMESPACE.matcher(namespace).matches()), "Invalid namespace. Must be [a-z0-9._-]: %s", namespace);
/*  50 */     Preconditions.checkArgument((key != null && VALID_KEY.matcher(key).matches()), "Invalid key. Must be [a-z0-9/._-]: %s", key);
/*     */     
/*  52 */     this.namespace = namespace;
/*  53 */     this.key = key;
/*     */     
/*  55 */     String string = toString();
/*  56 */     Preconditions.checkArgument((string.length() < 256), "NamespacedTag must be less than 256 characters", string);
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
/*     */ 
/*     */ 
/*     */   
/*     */   public NamespacedTag(@NotNull Plugin plugin, @NotNull String key) {
/*  72 */     Preconditions.checkArgument((plugin != null), "Plugin cannot be null");
/*  73 */     Preconditions.checkArgument((key != null), "Key cannot be null");
/*     */     
/*  75 */     this.namespace = plugin.getName().toLowerCase(Locale.ROOT);
/*  76 */     this.key = key.toLowerCase().toLowerCase(Locale.ROOT);
/*     */ 
/*     */     
/*  79 */     Preconditions.checkArgument(VALID_NAMESPACE.matcher(this.namespace).matches(), "Invalid namespace. Must be [a-z0-9._-]: %s", this.namespace);
/*  80 */     Preconditions.checkArgument(VALID_KEY.matcher(this.key).matches(), "Invalid key. Must be [a-z0-9/._-]: %s", this.key);
/*     */     
/*  82 */     String string = toString();
/*  83 */     Preconditions.checkArgument((string.length() < 256), "NamespacedTag must be less than 256 characters (%s)", string);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getNamespace() {
/*  88 */     return this.namespace;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public String getKey() {
/*  93 */     return this.key;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  98 */     int hash = 7;
/*  99 */     hash = 47 * hash + this.namespace.hashCode();
/* 100 */     hash = 47 * hash + this.key.hashCode();
/* 101 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 106 */     if (obj == null) {
/* 107 */       return false;
/*     */     }
/* 109 */     if (getClass() != obj.getClass()) {
/* 110 */       return false;
/*     */     }
/* 112 */     NamespacedTag other = (NamespacedTag)obj;
/* 113 */     return (this.namespace.equals(other.namespace) && this.key.equals(other.key));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 118 */     return "#" + this.namespace + ":" + this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static NamespacedTag randomKey() {
/* 129 */     return new NamespacedTag("bukkit", UUID.randomUUID().toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NamespacedTag minecraft(@NotNull String key) {
/* 140 */     return new NamespacedTag("minecraft", key);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\NamespacedTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */