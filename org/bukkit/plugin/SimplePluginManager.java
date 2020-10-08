/*     */ package org.bukkit.plugin;
/*     */ import co.aikar.timings.TimedEventExecutor;
/*     */ import co.aikar.timings.Timings;
/*     */ import com.destroystokyo.paper.event.server.ServerExceptionEvent;
/*     */ import com.destroystokyo.paper.exception.ServerEventException;
/*     */ import com.destroystokyo.paper.exception.ServerException;
/*     */ import com.destroystokyo.paper.exception.ServerPluginEnableDisableException;
/*     */ import com.google.common.base.Preconditions;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.graph.Graph;
/*     */ import com.google.common.graph.GraphBuilder;
/*     */ import com.google.common.graph.Graphs;
/*     */ import com.google.common.graph.MutableGraph;
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Constructor;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Iterator;
/*     */ import java.util.LinkedHashMap;
/*     */ import java.util.LinkedHashSet;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.WeakHashMap;
/*     */ import java.util.logging.Level;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.World;
/*     */ import org.bukkit.command.Command;
/*     */ import org.bukkit.command.PluginCommandYamlParser;
/*     */ import org.bukkit.command.SimpleCommandMap;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventPriority;
/*     */ import org.bukkit.event.HandlerList;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.permissions.Permissible;
/*     */ import org.bukkit.permissions.Permission;
/*     */ import org.bukkit.permissions.PermissionDefault;
/*     */ import org.bukkit.util.FileUtil;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public final class SimplePluginManager implements PluginManager {
/*     */   private final Server server;
/*  52 */   private final Map<Pattern, PluginLoader> fileAssociations = new HashMap<>();
/*  53 */   private final List<Plugin> plugins = new ArrayList<>();
/*  54 */   private final Map<String, Plugin> lookupNames = new HashMap<>();
/*  55 */   private MutableGraph<String> dependencyGraph = GraphBuilder.directed().build();
/*     */   private File updateDirectory;
/*     */   private final SimpleCommandMap commandMap;
/*  58 */   private final Map<String, Permission> permissions = new HashMap<>();
/*  59 */   private final Map<Boolean, Set<Permission>> defaultPerms = new LinkedHashMap<>();
/*  60 */   private final Map<String, Map<Permissible, Boolean>> permSubs = new HashMap<>();
/*  61 */   private final Map<Boolean, Map<Permissible, Boolean>> defSubs = new HashMap<>();
/*     */   private boolean useTimings = false;
/*     */   
/*     */   public SimplePluginManager(@NotNull Server instance, @NotNull SimpleCommandMap commandMap) {
/*  65 */     this.server = instance;
/*  66 */     this.commandMap = commandMap;
/*     */     
/*  68 */     this.defaultPerms.put(Boolean.valueOf(true), new LinkedHashSet<>());
/*  69 */     this.defaultPerms.put(Boolean.valueOf(false), new LinkedHashSet<>());
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
/*     */   public void registerInterface(@NotNull Class<? extends PluginLoader> loader) throws IllegalArgumentException {
/*     */     PluginLoader instance;
/*  83 */     if (PluginLoader.class.isAssignableFrom(loader)) {
/*     */ 
/*     */       
/*     */       try {
/*  87 */         Constructor<? extends PluginLoader> constructor = loader.getConstructor(new Class[] { Server.class });
/*  88 */         instance = constructor.newInstance(new Object[] { this.server });
/*  89 */       } catch (NoSuchMethodException ex) {
/*  90 */         String className = loader.getName();
/*     */         
/*  92 */         throw new IllegalArgumentException(String.format("Class %s does not have a public %s(Server) constructor", new Object[] { className, className }), ex);
/*  93 */       } catch (Exception ex) {
/*  94 */         throw new IllegalArgumentException(String.format("Unexpected exception %s while attempting to construct a new instance of %s", new Object[] { ex.getClass().getName(), loader.getName() }), ex);
/*     */       } 
/*     */     } else {
/*  97 */       throw new IllegalArgumentException(String.format("Class %s does not implement interface PluginLoader", new Object[] { loader.getName() }));
/*     */     } 
/*     */     
/* 100 */     Pattern[] patterns = instance.getPluginFileFilters();
/*     */     
/* 102 */     synchronized (this) {
/* 103 */       for (Pattern pattern : patterns) {
/* 104 */         this.fileAssociations.put(pattern, instance);
/*     */       }
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
/*     */   @NotNull
/*     */   public Plugin[] loadPlugins(@NotNull File directory) {
/* 118 */     Validate.notNull(directory, "Directory cannot be null");
/* 119 */     Validate.isTrue(directory.isDirectory(), "Directory must be a directory");
/*     */     
/* 121 */     List<Plugin> result = new ArrayList<>();
/* 122 */     Set<Pattern> filters = this.fileAssociations.keySet();
/*     */     
/* 124 */     if (!this.server.getUpdateFolder().equals("")) {
/* 125 */       this.updateDirectory = new File(directory, this.server.getUpdateFolder());
/*     */     }
/*     */     
/* 128 */     Map<String, File> plugins = new HashMap<>();
/* 129 */     Set<String> loadedPlugins = new HashSet<>();
/* 130 */     Map<String, String> pluginsProvided = new HashMap<>();
/* 131 */     Map<String, Collection<String>> dependencies = new HashMap<>();
/* 132 */     Map<String, Collection<String>> softDependencies = new HashMap<>();
/*     */ 
/*     */     
/* 135 */     for (File file : directory.listFiles()) {
/* 136 */       PluginLoader loader = null;
/* 137 */       for (Pattern filter : filters) {
/* 138 */         Matcher match = filter.matcher(file.getName());
/* 139 */         if (match.find()) {
/* 140 */           loader = this.fileAssociations.get(filter);
/*     */         }
/*     */       } 
/*     */       
/* 144 */       if (loader != null) {
/*     */         
/* 146 */         PluginDescriptionFile description = null;
/*     */         
/* 148 */         try { description = loader.getPluginDescription(file);
/* 149 */           String name = description.getName();
/* 150 */           if (name.equalsIgnoreCase("bukkit") || name.equalsIgnoreCase("minecraft") || name.equalsIgnoreCase("mojang")) {
/* 151 */             this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "': Restricted Name");
/*     */           }
/* 153 */           else if (description.rawName.indexOf(' ') != -1) {
/* 154 */             this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "': uses the space-character (0x20) in its name");
/*     */ 
/*     */           
/*     */           }
/*     */           else {
/*     */ 
/*     */ 
/*     */             
/* 162 */             File replacedFile = plugins.put(description.getName(), file);
/* 163 */             if (replacedFile != null) {
/* 164 */               this.server.getLogger().severe(String.format("Ambiguous plugin name `%s' for files `%s' and `%s' in `%s'", new Object[] { description
/*     */                       
/* 166 */                       .getName(), file
/* 167 */                       .getPath(), replacedFile
/* 168 */                       .getPath(), directory
/* 169 */                       .getPath() }));
/*     */             }
/*     */ 
/*     */             
/* 173 */             String removedProvided = pluginsProvided.remove(description.getName());
/* 174 */             if (removedProvided != null) {
/* 175 */               this.server.getLogger().warning(String.format("Ambiguous plugin name `%s'. It is also provided by `%s'", new Object[] { description
/*     */                       
/* 177 */                       .getName(), removedProvided }));
/*     */             }
/*     */ 
/*     */ 
/*     */             
/* 182 */             for (String provided : description.getProvides()) {
/* 183 */               File pluginFile = plugins.get(provided);
/* 184 */               if (pluginFile != null) {
/* 185 */                 this.server.getLogger().warning(String.format("`%s provides `%s' while this is also the name of `%s' in `%s'", new Object[] { file
/*     */                         
/* 187 */                         .getPath(), provided, pluginFile
/*     */                         
/* 189 */                         .getPath(), directory
/* 190 */                         .getPath() }));
/*     */                 continue;
/*     */               } 
/* 193 */               String replacedPlugin = pluginsProvided.put(provided, description.getName());
/* 194 */               if (replacedPlugin != null) {
/* 195 */                 this.server.getLogger().warning(String.format("`%s' is provided by both `%s' and `%s'", new Object[] { provided, description
/*     */ 
/*     */                         
/* 198 */                         .getName(), replacedPlugin }));
/*     */               }
/*     */             } 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 205 */             Collection<String> softDependencySet = description.getSoftDepend();
/* 206 */             if (softDependencySet != null && !softDependencySet.isEmpty()) {
/* 207 */               if (softDependencies.containsKey(description.getName())) {
/*     */                 
/* 209 */                 ((Collection<String>)softDependencies.get(description.getName())).addAll(softDependencySet);
/*     */               } else {
/* 211 */                 softDependencies.put(description.getName(), new LinkedList<>(softDependencySet));
/*     */               } 
/*     */               
/* 214 */               for (String depend : softDependencySet) {
/* 215 */                 this.dependencyGraph.putEdge(description.getName(), depend);
/*     */               }
/*     */             } 
/*     */             
/* 219 */             Collection<String> dependencySet = description.getDepend();
/* 220 */             if (dependencySet != null && !dependencySet.isEmpty()) {
/* 221 */               dependencies.put(description.getName(), new LinkedList<>(dependencySet));
/*     */               
/* 223 */               for (String depend : dependencySet) {
/* 224 */                 this.dependencyGraph.putEdge(description.getName(), depend);
/*     */               }
/*     */             } 
/*     */             
/* 228 */             Collection<String> loadBeforeSet = description.getLoadBefore();
/* 229 */             if (loadBeforeSet != null && !loadBeforeSet.isEmpty())
/* 230 */               for (String loadBeforeTarget : loadBeforeSet)
/* 231 */               { if (softDependencies.containsKey(loadBeforeTarget)) {
/* 232 */                   ((Collection<String>)softDependencies.get(loadBeforeTarget)).add(description.getName());
/*     */                 } else {
/*     */                   
/* 235 */                   Collection<String> shortSoftDependency = new LinkedList<>();
/* 236 */                   shortSoftDependency.add(description.getName());
/* 237 */                   softDependencies.put(loadBeforeTarget, shortSoftDependency);
/*     */                 } 
/*     */                 
/* 240 */                 this.dependencyGraph.putEdge(loadBeforeTarget, description.getName()); }  
/*     */           }  }
/*     */         catch (InvalidDescriptionException ex) { this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "'", ex); }
/*     */       
/*     */       } 
/* 245 */     }  while (!plugins.isEmpty()) {
/* 246 */       boolean missingDependency = true;
/* 247 */       Iterator<Map.Entry<String, File>> pluginIterator = plugins.entrySet().iterator();
/*     */       
/* 249 */       while (pluginIterator.hasNext()) {
/* 250 */         Map.Entry<String, File> entry = pluginIterator.next();
/* 251 */         String plugin = entry.getKey();
/*     */         
/* 253 */         if (dependencies.containsKey(plugin)) {
/* 254 */           Iterator<String> dependencyIterator = ((Collection<String>)dependencies.get(plugin)).iterator();
/*     */           
/* 256 */           while (dependencyIterator.hasNext()) {
/* 257 */             String dependency = dependencyIterator.next();
/*     */ 
/*     */             
/* 260 */             if (loadedPlugins.contains(dependency)) {
/* 261 */               dependencyIterator.remove();
/*     */               continue;
/*     */             } 
/* 264 */             if (!plugins.containsKey(dependency) && !pluginsProvided.containsKey(dependency)) {
/* 265 */               missingDependency = false;
/* 266 */               pluginIterator.remove();
/* 267 */               softDependencies.remove(plugin);
/* 268 */               dependencies.remove(plugin);
/*     */               
/* 270 */               this.server.getLogger().log(Level.SEVERE, "Could not load '" + ((File)entry
/*     */                   
/* 272 */                   .getValue()).getPath() + "' in folder '" + directory.getPath() + "'", new UnknownDependencyException("Unknown dependency " + dependency + ". Please download and install " + dependency + " to run this plugin."));
/*     */               
/*     */               break;
/*     */             } 
/*     */           } 
/*     */           
/* 278 */           if (dependencies.containsKey(plugin) && ((Collection)dependencies.get(plugin)).isEmpty()) {
/* 279 */             dependencies.remove(plugin);
/*     */           }
/*     */         } 
/* 282 */         if (softDependencies.containsKey(plugin)) {
/* 283 */           Iterator<String> softDependencyIterator = ((Collection<String>)softDependencies.get(plugin)).iterator();
/*     */           
/* 285 */           while (softDependencyIterator.hasNext()) {
/* 286 */             String softDependency = softDependencyIterator.next();
/*     */ 
/*     */             
/* 289 */             if (!plugins.containsKey(softDependency) && !pluginsProvided.containsKey(softDependency)) {
/* 290 */               softDependencyIterator.remove();
/*     */             }
/*     */           } 
/*     */           
/* 294 */           if (((Collection)softDependencies.get(plugin)).isEmpty()) {
/* 295 */             softDependencies.remove(plugin);
/*     */           }
/*     */         } 
/* 298 */         if (!dependencies.containsKey(plugin) && !softDependencies.containsKey(plugin) && plugins.containsKey(plugin)) {
/*     */           
/* 300 */           File file = plugins.get(plugin);
/* 301 */           pluginIterator.remove();
/* 302 */           missingDependency = false;
/*     */           
/*     */           try {
/* 305 */             Plugin loadedPlugin = loadPlugin(file);
/* 306 */             if (loadedPlugin != null) {
/* 307 */               result.add(loadedPlugin);
/* 308 */               loadedPlugins.add(loadedPlugin.getName());
/* 309 */               loadedPlugins.addAll(loadedPlugin.getDescription().getProvides()); continue;
/*     */             } 
/* 311 */             this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "'");
/*     */           
/*     */           }
/* 314 */           catch (InvalidPluginException ex) {
/* 315 */             this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "'", ex);
/*     */           } 
/*     */         } 
/*     */       } 
/*     */       
/* 320 */       if (missingDependency) {
/*     */ 
/*     */         
/* 323 */         pluginIterator = plugins.entrySet().iterator();
/*     */         
/* 325 */         while (pluginIterator.hasNext()) {
/* 326 */           Map.Entry<String, File> entry = pluginIterator.next();
/* 327 */           String plugin = entry.getKey();
/*     */           
/* 329 */           if (!dependencies.containsKey(plugin)) {
/* 330 */             softDependencies.remove(plugin);
/* 331 */             missingDependency = false;
/* 332 */             File file = entry.getValue();
/* 333 */             pluginIterator.remove();
/*     */             
/*     */             try {
/* 336 */               Plugin loadedPlugin = loadPlugin(file);
/* 337 */               if (loadedPlugin != null) {
/* 338 */                 result.add(loadedPlugin);
/* 339 */                 loadedPlugins.add(loadedPlugin.getName());
/* 340 */                 loadedPlugins.addAll(loadedPlugin.getDescription().getProvides()); break;
/*     */               } 
/* 342 */               this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "'");
/*     */               
/*     */               break;
/* 345 */             } catch (InvalidPluginException ex) {
/* 346 */               this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "'", ex);
/*     */             } 
/*     */           } 
/*     */         } 
/*     */         
/* 351 */         if (missingDependency) {
/* 352 */           softDependencies.clear();
/* 353 */           dependencies.clear();
/* 354 */           Iterator<File> failedPluginIterator = plugins.values().iterator();
/*     */           
/* 356 */           while (failedPluginIterator.hasNext()) {
/* 357 */             File file = failedPluginIterator.next();
/* 358 */             failedPluginIterator.remove();
/* 359 */             this.server.getLogger().log(Level.SEVERE, "Could not load '" + file.getPath() + "' in folder '" + directory.getPath() + "': circular dependency detected");
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 365 */     return result.<Plugin>toArray(new Plugin[result.size()]);
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
/*     */   @Nullable
/*     */   public synchronized Plugin loadPlugin(@NotNull File file) throws InvalidPluginException, UnknownDependencyException {
/* 383 */     Validate.notNull(file, "File cannot be null");
/*     */     
/* 385 */     checkUpdate(file);
/*     */     
/* 387 */     Set<Pattern> filters = this.fileAssociations.keySet();
/* 388 */     Plugin result = null;
/*     */     
/* 390 */     for (Pattern filter : filters) {
/* 391 */       String name = file.getName();
/* 392 */       Matcher match = filter.matcher(name);
/*     */       
/* 394 */       if (match.find()) {
/* 395 */         PluginLoader loader = this.fileAssociations.get(filter);
/*     */         
/* 397 */         result = loader.loadPlugin(file);
/*     */       } 
/*     */     } 
/*     */     
/* 401 */     if (result != null) {
/* 402 */       this.plugins.add(result);
/* 403 */       this.lookupNames.put(result.getDescription().getName().toLowerCase(Locale.ENGLISH), result);
/* 404 */       for (String provided : result.getDescription().getProvides()) {
/* 405 */         this.lookupNames.putIfAbsent(provided.toLowerCase(Locale.ENGLISH), result);
/*     */       }
/*     */     } 
/*     */     
/* 409 */     return result;
/*     */   }
/*     */   
/*     */   private void checkUpdate(@NotNull File file) {
/* 413 */     if (this.updateDirectory == null || !this.updateDirectory.isDirectory()) {
/*     */       return;
/*     */     }
/*     */     
/* 417 */     File updateFile = new File(this.updateDirectory, file.getName());
/* 418 */     if (updateFile.isFile() && FileUtil.copy(updateFile, file)) {
/* 419 */       updateFile.delete();
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
/*     */   @Nullable
/*     */   public synchronized Plugin getPlugin(@NotNull String name) {
/* 434 */     return this.lookupNames.get(name.replace(' ', '_').toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public synchronized Plugin[] getPlugins() {
/* 440 */     return this.plugins.<Plugin>toArray(new Plugin[this.plugins.size()]);
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
/*     */   public boolean isPluginEnabled(@NotNull String name) {
/* 453 */     Plugin plugin = getPlugin(name);
/*     */     
/* 455 */     return isPluginEnabled(plugin);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized boolean isPluginEnabled(@Nullable Plugin plugin) {
/* 466 */     if (plugin != null && this.plugins.contains(plugin)) {
/* 467 */       return plugin.isEnabled();
/*     */     }
/* 469 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void enablePlugin(@NotNull Plugin plugin) {
/* 475 */     if (!plugin.isEnabled()) {
/* 476 */       List<Command> pluginCommands = PluginCommandYamlParser.parse(plugin);
/*     */       
/* 478 */       if (!pluginCommands.isEmpty()) {
/* 479 */         this.commandMap.registerAll(plugin.getDescription().getName(), pluginCommands);
/*     */       }
/*     */       
/*     */       try {
/* 483 */         plugin.getPluginLoader().enablePlugin(plugin);
/* 484 */       } catch (Throwable ex) {
/* 485 */         handlePluginException("Error occurred (in the plugin loader) while enabling " + plugin
/* 486 */             .getDescription().getFullName() + " (Is it up to date?)", ex, plugin);
/*     */       } 
/*     */       
/* 489 */       HandlerList.bakeAll();
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void disablePlugins() {
/* 495 */     disablePlugins(false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void disablePlugins(boolean closeClassloaders) {
/* 500 */     Plugin[] plugins = getPlugins();
/* 501 */     for (int i = plugins.length - 1; i >= 0; i--) {
/* 502 */       disablePlugin(plugins[i], closeClassloaders);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void disablePlugin(@NotNull Plugin plugin) {
/* 508 */     disablePlugin(plugin, false);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void disablePlugin(@NotNull Plugin plugin, boolean closeClassloader) {
/* 514 */     if (plugin.isEnabled()) {
/*     */       try {
/* 516 */         plugin.getPluginLoader().disablePlugin(plugin, closeClassloader);
/* 517 */       } catch (Throwable ex) {
/* 518 */         handlePluginException("Error occurred (in the plugin loader) while disabling " + plugin
/* 519 */             .getDescription().getFullName() + " (Is it up to date?)", ex, plugin);
/*     */       } 
/*     */       
/*     */       try {
/* 523 */         this.server.getScheduler().cancelTasks(plugin);
/* 524 */       } catch (Throwable ex) {
/* 525 */         handlePluginException("Error occurred (in the plugin loader) while cancelling tasks for " + plugin
/* 526 */             .getDescription().getFullName() + " (Is it up to date?)", ex, plugin);
/*     */       } 
/*     */       
/*     */       try {
/* 530 */         this.server.getServicesManager().unregisterAll(plugin);
/* 531 */       } catch (Throwable ex) {
/* 532 */         handlePluginException("Error occurred (in the plugin loader) while unregistering services for " + plugin
/* 533 */             .getDescription().getFullName() + " (Is it up to date?)", ex, plugin);
/*     */       } 
/*     */       
/*     */       try {
/* 537 */         HandlerList.unregisterAll(plugin);
/* 538 */       } catch (Throwable ex) {
/* 539 */         handlePluginException("Error occurred (in the plugin loader) while unregistering events for " + plugin
/* 540 */             .getDescription().getFullName() + " (Is it up to date?)", ex, plugin);
/*     */       } 
/*     */       
/*     */       try {
/* 544 */         this.server.getMessenger().unregisterIncomingPluginChannel(plugin);
/* 545 */         this.server.getMessenger().unregisterOutgoingPluginChannel(plugin);
/* 546 */       } catch (Throwable ex) {
/* 547 */         handlePluginException("Error occurred (in the plugin loader) while unregistering plugin channels for " + plugin
/* 548 */             .getDescription().getFullName() + " (Is it up to date?)", ex, plugin);
/*     */       } 
/*     */       
/*     */       try {
/* 552 */         for (World world : this.server.getWorlds()) {
/* 553 */           world.removePluginChunkTickets(plugin);
/*     */         }
/* 555 */       } catch (Throwable ex) {
/* 556 */         this.server.getLogger().log(Level.SEVERE, "Error occurred (in the plugin loader) while removing chunk tickets for " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void handlePluginException(String msg, Throwable ex, Plugin plugin) {
/* 563 */     this.server.getLogger().log(Level.SEVERE, msg, ex);
/* 564 */     callEvent((Event)new ServerExceptionEvent((ServerException)new ServerPluginEnableDisableException(msg, ex, plugin)));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void clearPlugins() {
/* 570 */     synchronized (this) {
/* 571 */       disablePlugins(true);
/* 572 */       this.plugins.clear();
/* 573 */       this.lookupNames.clear();
/* 574 */       this.dependencyGraph = GraphBuilder.directed().build();
/* 575 */       HandlerList.unregisterAll();
/* 576 */       this.fileAssociations.clear();
/* 577 */       this.permissions.clear();
/* 578 */       ((Set)this.defaultPerms.get(Boolean.valueOf(true))).clear();
/* 579 */       ((Set)this.defaultPerms.get(Boolean.valueOf(false))).clear();
/*     */     } 
/*     */   } private void fireEvent(Event event) {
/* 582 */     callEvent(event);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void callEvent(@NotNull Event event) {
/* 592 */     if (event.isAsynchronous() && this.server.isPrimaryThread())
/* 593 */       throw new IllegalStateException(event.getEventName() + " may only be triggered asynchronously."); 
/* 594 */     if (!event.isAsynchronous() && !this.server.isPrimaryThread() && !this.server.isStopping()) {
/* 595 */       throw new IllegalStateException(event.getEventName() + " may only be triggered synchronously.");
/*     */     }
/*     */     
/* 598 */     HandlerList handlers = event.getHandlers();
/* 599 */     RegisteredListener[] listeners = handlers.getRegisteredListeners();
/*     */     
/* 601 */     for (RegisteredListener registration : listeners) {
/* 602 */       if (registration.getPlugin().isEnabled()) {
/*     */         
/*     */         try {
/*     */ 
/*     */           
/* 607 */           registration.callEvent(event);
/* 608 */         } catch (AuthorNagException ex) {
/* 609 */           Plugin plugin = registration.getPlugin();
/*     */           
/* 611 */           if (plugin.isNaggable()) {
/* 612 */             plugin.setNaggable(false);
/*     */             
/* 614 */             this.server.getLogger().log(Level.SEVERE, String.format("Nag author(s): '%s' of '%s' about the following: %s", new Object[] { plugin
/*     */                     
/* 616 */                     .getDescription().getAuthors(), plugin
/* 617 */                     .getDescription().getFullName(), ex
/* 618 */                     .getMessage() }));
/*     */           }
/*     */         
/* 621 */         } catch (Throwable ex) {
/*     */           
/* 623 */           String msg = "Could not pass event " + event.getEventName() + " to " + registration.getPlugin().getDescription().getFullName();
/* 624 */           this.server.getLogger().log(Level.SEVERE, msg, ex);
/* 625 */           if (!(event instanceof ServerExceptionEvent)) {
/* 626 */             callEvent((Event)new ServerExceptionEvent((ServerException)new ServerEventException(msg, ex, registration.getPlugin(), registration.getListener(), event)));
/*     */           }
/*     */         } 
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerEvents(@NotNull Listener listener, @NotNull Plugin plugin) {
/* 635 */     if (!plugin.isEnabled()) {
/* 636 */       throw new IllegalPluginAccessException("Plugin attempted to register " + listener + " while not enabled");
/*     */     }
/*     */     
/* 639 */     for (Map.Entry<Class<? extends Event>, Set<RegisteredListener>> entry : plugin.getPluginLoader().createRegisteredListeners(listener, plugin).entrySet()) {
/* 640 */       getEventListeners(getRegistrationClass(entry.getKey())).registerAll(entry.getValue());
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor, @NotNull Plugin plugin) {
/* 647 */     registerEvent(event, listener, priority, executor, plugin, false);
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
/*     */   public void registerEvent(@NotNull Class<? extends Event> event, @NotNull Listener listener, @NotNull EventPriority priority, @NotNull EventExecutor executor, @NotNull Plugin plugin, boolean ignoreCancelled) {
/* 664 */     Validate.notNull(listener, "Listener cannot be null");
/* 665 */     Validate.notNull(priority, "Priority cannot be null");
/* 666 */     Validate.notNull(executor, "Executor cannot be null");
/* 667 */     Validate.notNull(plugin, "Plugin cannot be null");
/*     */     
/* 669 */     if (!plugin.isEnabled()) {
/* 670 */       throw new IllegalPluginAccessException("Plugin attempted to register " + event + " while not enabled");
/*     */     }
/*     */     
/* 673 */     TimedEventExecutor timedEventExecutor = new TimedEventExecutor(executor, plugin, null, event);
/*     */ 
/*     */ 
/*     */     
/* 677 */     getEventListeners(event).register(new RegisteredListener(listener, (EventExecutor)timedEventExecutor, priority, plugin, ignoreCancelled));
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   private HandlerList getEventListeners(@NotNull Class<? extends Event> type) {
/*     */     try {
/* 684 */       Method method = getRegistrationClass(type).getDeclaredMethod("getHandlerList", new Class[0]);
/* 685 */       method.setAccessible(true);
/* 686 */       return (HandlerList)method.invoke(null, new Object[0]);
/* 687 */     } catch (Exception e) {
/* 688 */       throw new IllegalPluginAccessException(e.toString());
/*     */     } 
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   private Class<? extends Event> getRegistrationClass(@NotNull Class<? extends Event> clazz) {
/*     */     try {
/* 695 */       clazz.getDeclaredMethod("getHandlerList", new Class[0]);
/* 696 */       return clazz;
/* 697 */     } catch (NoSuchMethodException e) {
/* 698 */       if (clazz.getSuperclass() != null && 
/* 699 */         !clazz.getSuperclass().equals(Event.class) && Event.class
/* 700 */         .isAssignableFrom(clazz.getSuperclass())) {
/* 701 */         return getRegistrationClass(clazz.getSuperclass().asSubclass(Event.class));
/*     */       }
/* 703 */       throw new IllegalPluginAccessException("Unable to find handler list for event " + clazz.getName() + ". Static getHandlerList method required!");
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Permission getPermission(@NotNull String name) {
/* 711 */     return this.permissions.get(name.toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */   
/*     */   public void addPermission(@NotNull Permission perm) {
/* 716 */     addPermission(perm, true);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void addPermission(@NotNull Permission perm, boolean dirty) {
/* 721 */     String name = perm.getName().toLowerCase(Locale.ENGLISH);
/*     */     
/* 723 */     if (this.permissions.containsKey(name)) {
/* 724 */       throw new IllegalArgumentException("The permission " + name + " is already defined!");
/*     */     }
/*     */     
/* 727 */     this.permissions.put(name, perm);
/* 728 */     calculatePermissionDefault(perm, dirty);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<Permission> getDefaultPermissions(boolean op) {
/* 734 */     return (Set<Permission>)ImmutableSet.copyOf(this.defaultPerms.get(Boolean.valueOf(op)));
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePermission(@NotNull Permission perm) {
/* 739 */     removePermission(perm.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public void removePermission(@NotNull String name) {
/* 744 */     this.permissions.remove(name.toLowerCase(Locale.ENGLISH));
/*     */   }
/*     */ 
/*     */   
/*     */   public void recalculatePermissionDefaults(@NotNull Permission perm) {
/* 749 */     if (perm != null && this.permissions.containsKey(perm.getName().toLowerCase(Locale.ENGLISH))) {
/* 750 */       ((Set)this.defaultPerms.get(Boolean.valueOf(true))).remove(perm);
/* 751 */       ((Set)this.defaultPerms.get(Boolean.valueOf(false))).remove(perm);
/*     */       
/* 753 */       calculatePermissionDefault(perm, true);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void calculatePermissionDefault(@NotNull Permission perm, boolean dirty) {
/* 758 */     if (perm.getDefault() == PermissionDefault.OP || perm.getDefault() == PermissionDefault.TRUE) {
/* 759 */       ((Set<Permission>)this.defaultPerms.get(Boolean.valueOf(true))).add(perm);
/* 760 */       if (dirty) {
/* 761 */         dirtyPermissibles(true);
/*     */       }
/*     */     } 
/* 764 */     if (perm.getDefault() == PermissionDefault.NOT_OP || perm.getDefault() == PermissionDefault.TRUE) {
/* 765 */       ((Set<Permission>)this.defaultPerms.get(Boolean.valueOf(false))).add(perm);
/* 766 */       if (dirty) {
/* 767 */         dirtyPermissibles(false);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public void dirtyPermissibles() {
/* 774 */     dirtyPermissibles(true);
/* 775 */     dirtyPermissibles(false);
/*     */   }
/*     */   
/*     */   private void dirtyPermissibles(boolean op) {
/* 779 */     Set<Permissible> permissibles = getDefaultPermSubscriptions(op);
/*     */     
/* 781 */     for (Permissible p : permissibles) {
/* 782 */       p.recalculatePermissions();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void subscribeToPermission(@NotNull String permission, @NotNull Permissible permissible) {
/* 788 */     String name = permission.toLowerCase(Locale.ENGLISH);
/* 789 */     Map<Permissible, Boolean> map = this.permSubs.get(name);
/*     */     
/* 791 */     if (map == null) {
/* 792 */       map = new WeakHashMap<>();
/* 793 */       this.permSubs.put(name, map);
/*     */     } 
/*     */     
/* 796 */     map.put(permissible, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void unsubscribeFromPermission(@NotNull String permission, @NotNull Permissible permissible) {
/* 801 */     String name = permission.toLowerCase(Locale.ENGLISH);
/* 802 */     Map<Permissible, Boolean> map = this.permSubs.get(name);
/*     */     
/* 804 */     if (map != null) {
/* 805 */       map.remove(permissible);
/*     */       
/* 807 */       if (map.isEmpty()) {
/* 808 */         this.permSubs.remove(name);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<Permissible> getPermissionSubscriptions(@NotNull String permission) {
/* 816 */     String name = permission.toLowerCase(Locale.ENGLISH);
/* 817 */     Map<Permissible, Boolean> map = this.permSubs.get(name);
/*     */     
/* 819 */     if (map == null) {
/* 820 */       return (Set<Permissible>)ImmutableSet.of();
/*     */     }
/* 822 */     return (Set<Permissible>)ImmutableSet.copyOf(map.keySet());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void subscribeToDefaultPerms(boolean op, @NotNull Permissible permissible) {
/* 828 */     Map<Permissible, Boolean> map = this.defSubs.get(Boolean.valueOf(op));
/*     */     
/* 830 */     if (map == null) {
/* 831 */       map = new WeakHashMap<>();
/* 832 */       this.defSubs.put(Boolean.valueOf(op), map);
/*     */     } 
/*     */     
/* 835 */     map.put(permissible, Boolean.valueOf(true));
/*     */   }
/*     */ 
/*     */   
/*     */   public void unsubscribeFromDefaultPerms(boolean op, @NotNull Permissible permissible) {
/* 840 */     Map<Permissible, Boolean> map = this.defSubs.get(Boolean.valueOf(op));
/*     */     
/* 842 */     if (map != null) {
/* 843 */       map.remove(permissible);
/*     */       
/* 845 */       if (map.isEmpty()) {
/* 846 */         this.defSubs.remove(Boolean.valueOf(op));
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<Permissible> getDefaultPermSubscriptions(boolean op) {
/* 854 */     Map<Permissible, Boolean> map = this.defSubs.get(Boolean.valueOf(op));
/*     */     
/* 856 */     if (map == null) {
/* 857 */       return (Set<Permissible>)ImmutableSet.of();
/*     */     }
/* 859 */     return (Set<Permissible>)ImmutableSet.copyOf(map.keySet());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<Permission> getPermissions() {
/* 866 */     return new HashSet<>(this.permissions.values());
/*     */   }
/*     */   
/*     */   public boolean isTransitiveDepend(@NotNull PluginDescriptionFile plugin, @NotNull PluginDescriptionFile depend) {
/* 870 */     Preconditions.checkArgument((plugin != null), "plugin");
/* 871 */     Preconditions.checkArgument((depend != null), "depend");
/*     */     
/* 873 */     if (this.dependencyGraph.nodes().contains(plugin.getName())) {
/* 874 */       if (Graphs.reachableNodes((Graph)this.dependencyGraph, plugin.getName()).contains(depend.getName())) {
/* 875 */         return true;
/*     */       }
/* 877 */       for (String provided : depend.getProvides()) {
/* 878 */         if (Graphs.reachableNodes((Graph)this.dependencyGraph, plugin.getName()).contains(provided)) {
/* 879 */           return true;
/*     */         }
/*     */       } 
/*     */     } 
/* 883 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean useTimings() {
/* 888 */     return Timings.isTimingsEnabled();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void useTimings(boolean use) {
/* 897 */     Timings.setTimingsEnabled(use);
/*     */   }
/*     */ 
/*     */   
/*     */   public void clearPermissions() {
/* 902 */     this.permissions.clear();
/* 903 */     ((Set)this.defaultPerms.get(Boolean.valueOf(true))).clear();
/* 904 */     ((Set)this.defaultPerms.get(Boolean.valueOf(false))).clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\SimplePluginManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */