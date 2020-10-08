/*      */ package org.bukkit.plugin;
/*      */ 
/*      */ import com.google.common.collect.ImmutableList;
/*      */ import com.google.common.collect.ImmutableMap;
/*      */ import com.google.common.collect.ImmutableSet;
/*      */ import java.io.InputStream;
/*      */ import java.io.Reader;
/*      */ import java.io.Writer;
/*      */ import java.util.HashMap;
/*      */ import java.util.HashSet;
/*      */ import java.util.List;
/*      */ import java.util.Locale;
/*      */ import java.util.Map;
/*      */ import java.util.Set;
/*      */ import java.util.regex.Pattern;
/*      */ import org.bukkit.permissions.Permission;
/*      */ import org.bukkit.permissions.PermissionDefault;
/*      */ import org.jetbrains.annotations.NotNull;
/*      */ import org.jetbrains.annotations.Nullable;
/*      */ import org.yaml.snakeyaml.Yaml;
/*      */ import org.yaml.snakeyaml.constructor.AbstractConstruct;
/*      */ import org.yaml.snakeyaml.constructor.BaseConstructor;
/*      */ import org.yaml.snakeyaml.constructor.SafeConstructor;
/*      */ import org.yaml.snakeyaml.nodes.Node;
/*      */ import org.yaml.snakeyaml.nodes.Tag;
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ public final class PluginDescriptionFile
/*      */ {
/*  192 */   private static final Pattern VALID_NAME = Pattern.compile("^[A-Za-z0-9 _.-]+$");
/*  193 */   private static final ThreadLocal<Yaml> YAML = new ThreadLocal<Yaml>()
/*      */     {
/*      */       @NotNull
/*      */       protected Yaml initialValue() {
/*  197 */         return new Yaml((BaseConstructor)new SafeConstructor()
/*      */             {
/*      */             
/*      */             });
/*      */       }
/*      */     };
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*  229 */   String rawName = null;
/*  230 */   private String name = null;
/*  231 */   private List<String> provides = (List<String>)ImmutableList.of();
/*  232 */   private String main = null;
/*  233 */   private String classLoaderOf = null;
/*  234 */   private List<String> depend = (List<String>)ImmutableList.of();
/*  235 */   private List<String> softDepend = (List<String>)ImmutableList.of();
/*  236 */   private List<String> loadBefore = (List<String>)ImmutableList.of();
/*  237 */   private String version = null;
/*  238 */   private Map<String, Map<String, Object>> commands = (Map<String, Map<String, Object>>)ImmutableMap.of();
/*  239 */   private String description = null;
/*  240 */   private List<String> authors = null;
/*  241 */   private List<String> contributors = null;
/*  242 */   private String website = null;
/*  243 */   private String prefix = null;
/*  244 */   private PluginLoadOrder order = PluginLoadOrder.POSTWORLD;
/*  245 */   private List<Permission> permissions = null;
/*  246 */   private Map<?, ?> lazyPermissions = null;
/*  247 */   private PermissionDefault defaultPerm = PermissionDefault.OP;
/*  248 */   private Set<PluginAwareness> awareness = (Set<PluginAwareness>)ImmutableSet.of();
/*  249 */   private String apiVersion = null;
/*      */   
/*      */   public PluginDescriptionFile(@NotNull InputStream stream) throws InvalidDescriptionException {
/*  252 */     loadMap(asMap(((Yaml)YAML.get()).load(stream)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PluginDescriptionFile(@NotNull Reader reader) throws InvalidDescriptionException {
/*  263 */     loadMap(asMap(((Yaml)YAML.get()).load(reader)));
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public PluginDescriptionFile(@NotNull String pluginName, @NotNull String pluginVersion, @NotNull String mainClass) {
/*  274 */     this.name = this.rawName = pluginName;
/*      */     
/*  276 */     if (!VALID_NAME.matcher(this.name).matches()) {
/*  277 */       throw new IllegalArgumentException("name '" + this.name + "' contains invalid characters.");
/*      */     }
/*  279 */     this.name = this.name.replace(' ', '_');
/*  280 */     this.version = pluginVersion;
/*  281 */     this.main = mainClass;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public String getName() {
/*  311 */     return this.name;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public List<String> getProvides() {
/*  342 */     return this.provides;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public String getVersion() {
/*  363 */     return this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public String getMain() {
/*  390 */     return this.main;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public String getDescription() {
/*  410 */     return this.description;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public PluginLoadOrder getLoad() {
/*  434 */     return this.order;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public List<String> getAuthors() {
/*  470 */     return this.authors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public List<String> getContributors() {
/*  494 */     return this.contributors;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public String getWebsite() {
/*  514 */     return this.website;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public List<String> getDepend() {
/*  545 */     return this.depend;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public List<String> getSoftDepend() {
/*  575 */     return this.softDepend;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public List<String> getLoadBefore() {
/*  605 */     return this.loadBefore;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public String getPrefix() {
/*  625 */     return this.prefix;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Map<String, Map<String, Object>> getCommands() {
/*  744 */     return this.commands;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public List<Permission> getPermissions() {
/*  857 */     if (this.permissions == null) {
/*  858 */       if (this.lazyPermissions == null) {
/*  859 */         this.permissions = (List<Permission>)ImmutableList.of();
/*      */       } else {
/*  861 */         this.permissions = (List<Permission>)ImmutableList.copyOf(Permission.loadPermissions(this.lazyPermissions, "Permission node '%s' in plugin description file for " + getFullName() + " is invalid", this.defaultPerm));
/*  862 */         this.lazyPermissions = null;
/*      */       } 
/*      */     }
/*  865 */     return this.permissions;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public PermissionDefault getPermissionDefault() {
/*  887 */     return this.defaultPerm;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public Set<PluginAwareness> getAwareness() {
/*  927 */     return this.awareness;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @NotNull
/*      */   public String getFullName() {
/*  939 */     return this.name + " v" + this.version;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Nullable
/*      */   public String getAPIVersion() {
/*  957 */     return this.apiVersion;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @Nullable
/*      */   public String getClassLoaderOf() {
/*  967 */     return this.classLoaderOf;
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   public void save(@NotNull Writer writer) {
/*  976 */     ((Yaml)YAML.get()).dump(saveMap(), writer);
/*      */   }
/*      */   
/*      */   private void loadMap(@NotNull Map<?, ?> map) throws InvalidDescriptionException {
/*      */     try {
/*  981 */       this.name = this.rawName = map.get("name").toString();
/*      */       
/*  983 */       if (!VALID_NAME.matcher(this.name).matches()) {
/*  984 */         throw new InvalidDescriptionException("name '" + this.name + "' contains invalid characters.");
/*      */       }
/*  986 */       this.name = this.name.replace(' ', '_');
/*  987 */     } catch (NullPointerException ex) {
/*  988 */       throw new InvalidDescriptionException(ex, "name is not defined");
/*  989 */     } catch (ClassCastException ex) {
/*  990 */       throw new InvalidDescriptionException(ex, "name is of wrong type");
/*      */     } 
/*      */     
/*  993 */     this.provides = makePluginNameList(map, "provides");
/*      */     
/*      */     try {
/*  996 */       this.version = map.get("version").toString();
/*  997 */     } catch (NullPointerException ex) {
/*  998 */       throw new InvalidDescriptionException(ex, "version is not defined");
/*  999 */     } catch (ClassCastException ex) {
/* 1000 */       throw new InvalidDescriptionException(ex, "version is of wrong type");
/*      */     } 
/*      */     
/*      */     try {
/* 1004 */       this.main = map.get("main").toString();
/* 1005 */       if (this.main.startsWith("org.bukkit.")) {
/* 1006 */         throw new InvalidDescriptionException("main may not be within the org.bukkit namespace");
/*      */       }
/* 1008 */     } catch (NullPointerException ex) {
/* 1009 */       throw new InvalidDescriptionException(ex, "main is not defined");
/* 1010 */     } catch (ClassCastException ex) {
/* 1011 */       throw new InvalidDescriptionException(ex, "main is of wrong type");
/*      */     } 
/*      */     
/* 1014 */     if (map.get("commands") != null) {
/* 1015 */       ImmutableMap.Builder<String, Map<String, Object>> commandsBuilder = ImmutableMap.builder();
/*      */       try {
/* 1017 */         for (Map.Entry<?, ?> command : (Iterable<Map.Entry<?, ?>>)((Map)map.get("commands")).entrySet()) {
/* 1018 */           ImmutableMap.Builder<String, Object> commandBuilder = ImmutableMap.builder();
/* 1019 */           if (command.getValue() != null) {
/* 1020 */             for (Map.Entry<?, ?> commandEntry : (Iterable<Map.Entry<?, ?>>)((Map)command.getValue()).entrySet()) {
/* 1021 */               if (commandEntry.getValue() instanceof Iterable) {
/*      */                 
/* 1023 */                 ImmutableList.Builder<Object> commandSubList = ImmutableList.builder();
/* 1024 */                 for (Object commandSubListItem : commandEntry.getValue()) {
/* 1025 */                   if (commandSubListItem != null) {
/* 1026 */                     commandSubList.add(commandSubListItem);
/*      */                   }
/*      */                 } 
/* 1029 */                 commandBuilder.put(commandEntry.getKey().toString(), commandSubList.build()); continue;
/* 1030 */               }  if (commandEntry.getValue() != null) {
/* 1031 */                 commandBuilder.put(commandEntry.getKey().toString(), commandEntry.getValue());
/*      */               }
/*      */             } 
/*      */           }
/* 1035 */           commandsBuilder.put(command.getKey().toString(), commandBuilder.build());
/*      */         } 
/* 1037 */       } catch (ClassCastException ex) {
/* 1038 */         throw new InvalidDescriptionException(ex, "commands are of wrong type");
/*      */       } 
/* 1040 */       this.commands = (Map<String, Map<String, Object>>)commandsBuilder.build();
/*      */     } 
/*      */     
/* 1043 */     if (map.get("class-loader-of") != null) {
/* 1044 */       this.classLoaderOf = map.get("class-loader-of").toString();
/*      */     }
/*      */     
/* 1047 */     this.depend = makePluginNameList(map, "depend");
/* 1048 */     this.softDepend = makePluginNameList(map, "softdepend");
/* 1049 */     this.loadBefore = makePluginNameList(map, "loadbefore");
/*      */     
/* 1051 */     if (map.get("website") != null) {
/* 1052 */       this.website = map.get("website").toString();
/*      */     }
/*      */     
/* 1055 */     if (map.get("description") != null) {
/* 1056 */       this.description = map.get("description").toString();
/*      */     }
/*      */     
/* 1059 */     if (map.get("load") != null) {
/*      */       try {
/* 1061 */         this.order = PluginLoadOrder.valueOf(((String)map.get("load")).toUpperCase(Locale.ENGLISH).replaceAll("\\W", ""));
/* 1062 */       } catch (ClassCastException ex) {
/* 1063 */         throw new InvalidDescriptionException(ex, "load is of wrong type");
/* 1064 */       } catch (IllegalArgumentException ex) {
/* 1065 */         throw new InvalidDescriptionException(ex, "load is not a valid choice");
/*      */       } 
/*      */     }
/*      */     
/* 1069 */     if (map.get("authors") != null) {
/* 1070 */       ImmutableList.Builder<String> authorsBuilder = ImmutableList.builder();
/* 1071 */       if (map.get("author") != null) {
/* 1072 */         authorsBuilder.add(map.get("author").toString());
/*      */       }
/*      */       try {
/* 1075 */         for (Object o : map.get("authors")) {
/* 1076 */           authorsBuilder.add(o.toString());
/*      */         }
/* 1078 */       } catch (ClassCastException ex) {
/* 1079 */         throw new InvalidDescriptionException(ex, "authors are of wrong type");
/* 1080 */       } catch (NullPointerException ex) {
/* 1081 */         throw new InvalidDescriptionException(ex, "authors are improperly defined");
/*      */       } 
/* 1083 */       this.authors = (List<String>)authorsBuilder.build();
/* 1084 */     } else if (map.get("author") != null) {
/* 1085 */       this.authors = (List<String>)ImmutableList.of(map.get("author").toString());
/*      */     } else {
/* 1087 */       this.authors = (List<String>)ImmutableList.of();
/*      */     } 
/*      */     
/* 1090 */     if (map.get("contributors") != null) {
/* 1091 */       ImmutableList.Builder<String> contributorsBuilder = ImmutableList.builder();
/*      */       try {
/* 1093 */         for (Object o : map.get("contributors")) {
/* 1094 */           contributorsBuilder.add(o.toString());
/*      */         }
/* 1096 */       } catch (ClassCastException ex) {
/* 1097 */         throw new InvalidDescriptionException(ex, "contributors are of wrong type");
/*      */       } 
/* 1099 */       this.contributors = (List<String>)contributorsBuilder.build();
/*      */     } else {
/* 1101 */       this.contributors = (List<String>)ImmutableList.of();
/*      */     } 
/*      */     
/* 1104 */     if (map.get("default-permission") != null) {
/*      */       try {
/* 1106 */         this.defaultPerm = PermissionDefault.getByName(map.get("default-permission").toString());
/* 1107 */       } catch (ClassCastException ex) {
/* 1108 */         throw new InvalidDescriptionException(ex, "default-permission is of wrong type");
/* 1109 */       } catch (IllegalArgumentException ex) {
/* 1110 */         throw new InvalidDescriptionException(ex, "default-permission is not a valid choice");
/*      */       } 
/*      */     }
/*      */     
/* 1114 */     if (map.get("awareness") instanceof Iterable) {
/* 1115 */       Set<PluginAwareness> awareness = new HashSet<>();
/*      */       try {
/* 1117 */         for (Object o : map.get("awareness")) {
/* 1118 */           awareness.add((PluginAwareness)o);
/*      */         }
/* 1120 */       } catch (ClassCastException ex) {
/* 1121 */         throw new InvalidDescriptionException(ex, "awareness has wrong type");
/*      */       } 
/* 1123 */       this.awareness = (Set<PluginAwareness>)ImmutableSet.copyOf(awareness);
/*      */     } 
/*      */     
/* 1126 */     if (map.get("api-version") != null) {
/* 1127 */       this.apiVersion = map.get("api-version").toString();
/*      */     }
/*      */     
/*      */     try {
/* 1131 */       this.lazyPermissions = (Map<?, ?>)map.get("permissions");
/* 1132 */     } catch (ClassCastException ex) {
/* 1133 */       throw new InvalidDescriptionException(ex, "permissions are of the wrong type");
/*      */     } 
/*      */     
/* 1136 */     if (map.get("prefix") != null) {
/* 1137 */       this.prefix = map.get("prefix").toString();
/*      */     }
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   private static List<String> makePluginNameList(@NotNull Map<?, ?> map, @NotNull String key) throws InvalidDescriptionException {
/* 1143 */     Object value = map.get(key);
/* 1144 */     if (value == null) {
/* 1145 */       return (List<String>)ImmutableList.of();
/*      */     }
/*      */     
/* 1148 */     ImmutableList.Builder<String> builder = ImmutableList.builder();
/*      */     try {
/* 1150 */       for (Object entry : value) {
/* 1151 */         builder.add(entry.toString().replace(' ', '_'));
/*      */       }
/* 1153 */     } catch (ClassCastException ex) {
/* 1154 */       throw new InvalidDescriptionException(ex, key + " is of wrong type");
/* 1155 */     } catch (NullPointerException ex) {
/* 1156 */       throw new InvalidDescriptionException(ex, "invalid " + key + " format");
/*      */     } 
/* 1158 */     return (List<String>)builder.build();
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   private Map<String, Object> saveMap() {
/* 1163 */     Map<String, Object> map = new HashMap<>();
/*      */     
/* 1165 */     map.put("name", this.name);
/* 1166 */     if (this.provides != null) {
/* 1167 */       map.put("provides", this.provides);
/*      */     }
/* 1169 */     map.put("main", this.main);
/* 1170 */     map.put("version", this.version);
/* 1171 */     map.put("order", this.order.toString());
/* 1172 */     map.put("default-permission", this.defaultPerm.toString());
/*      */     
/* 1174 */     if (this.commands != null) {
/* 1175 */       map.put("command", this.commands);
/*      */     }
/* 1177 */     if (this.depend != null) {
/* 1178 */       map.put("depend", this.depend);
/*      */     }
/* 1180 */     if (this.softDepend != null) {
/* 1181 */       map.put("softdepend", this.softDepend);
/*      */     }
/* 1183 */     if (this.website != null) {
/* 1184 */       map.put("website", this.website);
/*      */     }
/* 1186 */     if (this.description != null) {
/* 1187 */       map.put("description", this.description);
/*      */     }
/*      */     
/* 1190 */     if (this.authors.size() == 1) {
/* 1191 */       map.put("author", this.authors.get(0));
/* 1192 */     } else if (this.authors.size() > 1) {
/* 1193 */       map.put("authors", this.authors);
/*      */     } 
/*      */     
/* 1196 */     if (this.contributors != null) {
/* 1197 */       map.put("contributors", this.contributors);
/*      */     }
/*      */     
/* 1200 */     if (this.apiVersion != null) {
/* 1201 */       map.put("api-version", this.apiVersion);
/*      */     }
/*      */     
/* 1204 */     if (this.classLoaderOf != null) {
/* 1205 */       map.put("class-loader-of", this.classLoaderOf);
/*      */     }
/*      */     
/* 1208 */     if (this.prefix != null) {
/* 1209 */       map.put("prefix", this.prefix);
/*      */     }
/*      */     
/* 1212 */     return map;
/*      */   }
/*      */   
/*      */   @NotNull
/*      */   private Map<?, ?> asMap(@NotNull Object object) throws InvalidDescriptionException {
/* 1217 */     if (object instanceof Map) {
/* 1218 */       return (Map<?, ?>)object;
/*      */     }
/* 1220 */     throw new InvalidDescriptionException(object + " is not properly structured.");
/*      */   }
/*      */ 
/*      */ 
/*      */ 
/*      */ 
/*      */   
/*      */   @Deprecated
/*      */   @NotNull
/*      */   public String getRawName() {
/* 1230 */     return this.rawName;
/*      */   }
/*      */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\PluginDescriptionFile.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */