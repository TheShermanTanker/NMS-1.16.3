/*     */ package org.bukkit.permissions;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.plugin.PluginManager;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Permission
/*     */ {
/*  20 */   public static final PermissionDefault DEFAULT_PERMISSION = PermissionDefault.OP;
/*     */   
/*     */   private final String name;
/*  23 */   private final Map<String, Boolean> children = new LinkedHashMap<>();
/*  24 */   private PermissionDefault defaultValue = DEFAULT_PERMISSION;
/*     */   private String description;
/*     */   
/*     */   public Permission(@NotNull String name) {
/*  28 */     this(name, null, null, null);
/*     */   }
/*     */   
/*     */   public Permission(@NotNull String name, @Nullable String description) {
/*  32 */     this(name, description, null, null);
/*     */   }
/*     */   
/*     */   public Permission(@NotNull String name, @Nullable PermissionDefault defaultValue) {
/*  36 */     this(name, null, defaultValue, null);
/*     */   }
/*     */   
/*     */   public Permission(@NotNull String name, @Nullable String description, @Nullable PermissionDefault defaultValue) {
/*  40 */     this(name, description, defaultValue, null);
/*     */   }
/*     */   
/*     */   public Permission(@NotNull String name, @Nullable Map<String, Boolean> children) {
/*  44 */     this(name, null, null, children);
/*     */   }
/*     */   
/*     */   public Permission(@NotNull String name, @Nullable String description, @Nullable Map<String, Boolean> children) {
/*  48 */     this(name, description, null, children);
/*     */   }
/*     */   
/*     */   public Permission(@NotNull String name, @Nullable PermissionDefault defaultValue, @Nullable Map<String, Boolean> children) {
/*  52 */     this(name, null, defaultValue, children);
/*     */   }
/*     */   
/*     */   public Permission(@NotNull String name, @Nullable String description, @Nullable PermissionDefault defaultValue, @Nullable Map<String, Boolean> children) {
/*  56 */     Validate.notNull(name, "Name cannot be null");
/*  57 */     this.name = name;
/*  58 */     this.description = (description == null) ? "" : description;
/*     */     
/*  60 */     if (defaultValue != null) {
/*  61 */       this.defaultValue = defaultValue;
/*     */     }
/*     */     
/*  64 */     if (children != null) {
/*  65 */       this.children.putAll(children);
/*     */     }
/*     */     
/*  68 */     recalculatePermissibles();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getName() {
/*  78 */     return this.name;
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
/*     */   @NotNull
/*     */   public Map<String, Boolean> getChildren() {
/*  91 */     return this.children;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PermissionDefault getDefault() {
/* 101 */     return this.defaultValue;
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
/*     */   public void setDefault(@NotNull PermissionDefault value) {
/* 115 */     if (this.defaultValue == null) {
/* 116 */       throw new IllegalArgumentException("Default value cannot be null");
/*     */     }
/*     */     
/* 119 */     this.defaultValue = value;
/* 120 */     recalculatePermissibles();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String getDescription() {
/* 130 */     return this.description;
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
/*     */   public void setDescription(@Nullable String value) {
/* 142 */     if (value == null) {
/* 143 */       this.description = "";
/*     */     } else {
/* 145 */       this.description = value;
/*     */     } 
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
/*     */   @NotNull
/*     */   public Set<Permissible> getPermissibles() {
/* 159 */     return Bukkit.getServer().getPluginManager().getPermissionSubscriptions(this.name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void recalculatePermissibles() {
/* 169 */     Set<Permissible> perms = getPermissibles();
/*     */     
/* 171 */     Bukkit.getServer().getPluginManager().recalculatePermissionDefaults(this);
/*     */     
/* 173 */     for (Permissible p : perms) {
/* 174 */       p.recalculatePermissions();
/*     */     }
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
/*     */   @NotNull
/*     */   public Permission addParent(@NotNull String name, boolean value) {
/* 190 */     PluginManager pm = Bukkit.getServer().getPluginManager();
/* 191 */     String lname = name.toLowerCase(Locale.ENGLISH);
/*     */     
/* 193 */     Permission perm = pm.getPermission(lname);
/*     */     
/* 195 */     if (perm == null) {
/* 196 */       perm = new Permission(lname);
/* 197 */       pm.addPermission(perm);
/*     */     } 
/*     */     
/* 200 */     addParent(perm, value);
/*     */     
/* 202 */     return perm;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void addParent(@NotNull Permission perm, boolean value) {
/* 212 */     perm.getChildren().put(getName(), Boolean.valueOf(value));
/* 213 */     perm.recalculatePermissibles();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static List<Permission> loadPermissions(@NotNull Map<?, ?> data, @NotNull String error, @Nullable PermissionDefault def) {
/* 237 */     List<Permission> result = new ArrayList<>();
/*     */     
/* 239 */     for (Map.Entry<?, ?> entry : data.entrySet()) {
/*     */       try {
/* 241 */         result.add(loadPermission(entry.getKey().toString(), (Map<?, ?>)entry.getValue(), def, result));
/* 242 */       } catch (Throwable ex) {
/* 243 */         Bukkit.getServer().getLogger().log(Level.SEVERE, String.format(error, new Object[] { entry.getKey() }), ex);
/*     */       } 
/*     */     } 
/*     */     
/* 247 */     return result;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Permission loadPermission(@NotNull String name, @NotNull Map<String, Object> data) {
/* 269 */     return loadPermission(name, data, DEFAULT_PERMISSION, null);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Permission loadPermission(@NotNull String name, @NotNull Map<?, ?> data, @Nullable PermissionDefault def, @Nullable List<Permission> output) {
/* 293 */     Validate.notNull(name, "Name cannot be null");
/* 294 */     Validate.notNull(data, "Data cannot be null");
/*     */     
/* 296 */     String desc = null;
/* 297 */     Map<String, Boolean> children = null;
/*     */     
/* 299 */     if (data.get("default") != null) {
/* 300 */       PermissionDefault value = PermissionDefault.getByName(data.get("default").toString());
/* 301 */       if (value != null) {
/* 302 */         def = value;
/*     */       } else {
/* 304 */         throw new IllegalArgumentException("'default' key contained unknown value");
/*     */       } 
/*     */     } 
/*     */     
/* 308 */     if (data.get("children") != null) {
/* 309 */       Object childrenNode = data.get("children");
/* 310 */       if (childrenNode instanceof Iterable) {
/* 311 */         children = new LinkedHashMap<>();
/* 312 */         for (Object child : childrenNode) {
/* 313 */           if (child != null) {
/* 314 */             children.put(child.toString(), Boolean.TRUE);
/*     */           }
/*     */         } 
/* 317 */       } else if (childrenNode instanceof Map) {
/* 318 */         children = extractChildren((Map<?, ?>)childrenNode, name, def, output);
/*     */       } else {
/* 320 */         throw new IllegalArgumentException("'children' key is of wrong type");
/*     */       } 
/*     */     } 
/*     */     
/* 324 */     if (data.get("description") != null) {
/* 325 */       desc = data.get("description").toString();
/*     */     }
/*     */     
/* 328 */     return new Permission(name, desc, def, children);
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private static Map<String, Boolean> extractChildren(@NotNull Map<?, ?> input, @NotNull String name, @Nullable PermissionDefault def, @Nullable List<Permission> output) {
/* 333 */     Map<String, Boolean> children = new LinkedHashMap<>();
/*     */     
/* 335 */     for (Map.Entry<?, ?> entry : input.entrySet()) {
/* 336 */       if (entry.getValue() instanceof Boolean) {
/* 337 */         children.put(entry.getKey().toString(), (Boolean)entry.getValue()); continue;
/* 338 */       }  if (entry.getValue() instanceof Map) {
/*     */         try {
/* 340 */           Permission perm = loadPermission(entry.getKey().toString(), (Map<?, ?>)entry.getValue(), def, output);
/* 341 */           children.put(perm.getName(), Boolean.TRUE);
/*     */           
/* 343 */           if (output != null) {
/* 344 */             output.add(perm);
/*     */           }
/* 346 */         } catch (Throwable ex) {
/* 347 */           throw new IllegalArgumentException("Permission node '" + entry.getKey().toString() + "' in child of " + name + " is invalid", ex);
/*     */         }  continue;
/*     */       } 
/* 350 */       throw new IllegalArgumentException("Child '" + entry.getKey().toString() + "' contains invalid value");
/*     */     } 
/*     */ 
/*     */     
/* 354 */     return children;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\permissions\Permission.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */