/*     */ package org.bukkit;
/*     */ 
/*     */ import com.destroystokyo.paper.Namespaced;
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
/*     */ public final class NamespacedKey
/*     */   implements Namespaced
/*     */ {
/*     */   public static final String MINECRAFT = "minecraft";
/*     */   public static final String BUKKIT = "bukkit";
/*  33 */   private static final Pattern VALID_NAMESPACE = Pattern.compile("[a-z0-9._-]+");
/*  34 */   private static final Pattern VALID_KEY = Pattern.compile("[a-z0-9/._-]+");
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
/*     */   public NamespacedKey(@NotNull String namespace, @NotNull String key) {
/*  48 */     Preconditions.checkArgument((namespace != null && VALID_NAMESPACE.matcher(namespace).matches()), "Invalid namespace. Must be [a-z0-9._-]: %s", namespace);
/*  49 */     Preconditions.checkArgument((key != null && VALID_KEY.matcher(key).matches()), "Invalid key. Must be [a-z0-9/._-]: %s", key);
/*     */     
/*  51 */     this.namespace = namespace;
/*  52 */     this.key = key;
/*     */     
/*  54 */     String string = toString();
/*  55 */     Preconditions.checkArgument((string.length() < 256), "NamespacedKey must be less than 256 characters", string);
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
/*     */   public NamespacedKey(@NotNull Plugin plugin, @NotNull String key) {
/*  71 */     Preconditions.checkArgument((plugin != null), "Plugin cannot be null");
/*  72 */     Preconditions.checkArgument((key != null), "Key cannot be null");
/*     */     
/*  74 */     this.namespace = plugin.getName().toLowerCase(Locale.ROOT);
/*  75 */     this.key = key.toLowerCase(Locale.ROOT);
/*     */ 
/*     */     
/*  78 */     Preconditions.checkArgument(VALID_NAMESPACE.matcher(this.namespace).matches(), "Invalid namespace. Must be [a-z0-9._-]: %s", this.namespace);
/*  79 */     Preconditions.checkArgument(VALID_KEY.matcher(this.key).matches(), "Invalid key. Must be [a-z0-9/._-]: %s", this.key);
/*     */     
/*  81 */     String string = toString();
/*  82 */     Preconditions.checkArgument((string.length() < 256), "NamespacedKey must be less than 256 characters (%s)", string);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getNamespace() {
/*  88 */     return this.namespace;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getKey() {
/*  94 */     return this.key;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  99 */     int hash = 5;
/* 100 */     hash = 47 * hash + this.namespace.hashCode();
/* 101 */     hash = 47 * hash + this.key.hashCode();
/* 102 */     return hash;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 107 */     if (obj == null) {
/* 108 */       return false;
/*     */     }
/* 110 */     if (getClass() != obj.getClass()) {
/* 111 */       return false;
/*     */     }
/* 113 */     NamespacedKey other = (NamespacedKey)obj;
/* 114 */     return (this.namespace.equals(other.namespace) && this.key.equals(other.key));
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 119 */     return this.namespace + ":" + this.key;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public static NamespacedKey randomKey() {
/* 131 */     return new NamespacedKey("bukkit", UUID.randomUUID().toString());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static NamespacedKey minecraft(@NotNull String key) {
/* 142 */     return new NamespacedKey("minecraft", key);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\NamespacedKey.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */