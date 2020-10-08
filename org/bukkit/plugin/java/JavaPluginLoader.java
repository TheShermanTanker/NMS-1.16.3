/*     */ package org.bukkit.plugin.java;
/*     */ 
/*     */ import co.aikar.timings.TimedEventExecutor;
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.Arrays;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.CopyOnWriteArrayList;
/*     */ import java.util.concurrent.locks.ReentrantReadWriteLock;
/*     */ import java.util.jar.JarEntry;
/*     */ import java.util.jar.JarFile;
/*     */ import java.util.logging.Level;
/*     */ import java.util.regex.Pattern;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Server;
/*     */ import org.bukkit.UnsafeValues;
/*     */ import org.bukkit.Warning;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*     */ import org.bukkit.configuration.serialization.ConfigurationSerialization;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.EventHandler;
/*     */ import org.bukkit.event.Listener;
/*     */ import org.bukkit.event.server.PluginDisableEvent;
/*     */ import org.bukkit.event.server.PluginEnableEvent;
/*     */ import org.bukkit.plugin.AuthorNagException;
/*     */ import org.bukkit.plugin.EventExecutor;
/*     */ import org.bukkit.plugin.InvalidDescriptionException;
/*     */ import org.bukkit.plugin.InvalidPluginException;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.bukkit.plugin.PluginDescriptionFile;
/*     */ import org.bukkit.plugin.PluginLoader;
/*     */ import org.bukkit.plugin.RegisteredListener;
/*     */ import org.bukkit.plugin.UnknownDependencyException;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ import org.yaml.snakeyaml.error.YAMLException;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class JavaPluginLoader
/*     */   implements PluginLoader
/*     */ {
/*     */   final Server server;
/*  53 */   private static final boolean DISABLE_CLASS_PRIORITIZATION = Boolean.getBoolean("Paper.DisableClassPrioritization");
/*  54 */   private final Pattern[] fileFilters = new Pattern[] { Pattern.compile("\\.jar$") };
/*  55 */   private final Map<String, Class<?>> classes = new ConcurrentHashMap<>();
/*  56 */   private final Map<String, ReentrantReadWriteLock> classLoadLock = new HashMap<>();
/*  57 */   private final Map<String, Integer> classLoadLockCount = new HashMap<>();
/*  58 */   private final List<PluginClassLoader> loaders = new CopyOnWriteArrayList<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public JavaPluginLoader(@NotNull Server instance) {
/*  67 */     Validate.notNull(instance, "Server cannot be null");
/*  68 */     this.server = instance;
/*     */   }
/*     */   @NotNull
/*     */   public Plugin loadPlugin(@NotNull File file) throws InvalidPluginException {
/*     */     PluginDescriptionFile description;
/*     */     PluginClassLoader loader;
/*  74 */     Validate.notNull(file, "File cannot be null");
/*     */     
/*  76 */     if (!file.exists()) {
/*  77 */       throw new InvalidPluginException(new FileNotFoundException(file.getPath() + " does not exist"));
/*     */     }
/*     */ 
/*     */     
/*     */     try {
/*  82 */       description = getPluginDescription(file);
/*  83 */     } catch (InvalidDescriptionException ex) {
/*  84 */       throw new InvalidPluginException(ex);
/*     */     } 
/*     */     
/*  87 */     File parentFile = file.getParentFile();
/*  88 */     File dataFolder = new File(parentFile, description.getName());
/*     */     
/*  90 */     File oldDataFolder = new File(parentFile, description.getRawName());
/*     */ 
/*     */     
/*  93 */     if (!dataFolder.equals(oldDataFolder))
/*     */     {
/*  95 */       if (dataFolder.isDirectory() && oldDataFolder.isDirectory()) {
/*  96 */         this.server.getLogger().warning(String.format("While loading %s (%s) found old-data folder: `%s' next to the new one `%s'", new Object[] { description
/*     */                 
/*  98 */                 .getFullName(), file, oldDataFolder, dataFolder }));
/*     */ 
/*     */ 
/*     */       
/*     */       }
/* 103 */       else if (oldDataFolder.isDirectory() && !dataFolder.exists()) {
/* 104 */         if (!oldDataFolder.renameTo(dataFolder)) {
/* 105 */           throw new InvalidPluginException("Unable to rename old data folder: `" + oldDataFolder + "' to: `" + dataFolder + "'");
/*     */         }
/* 107 */         this.server.getLogger().log(Level.INFO, String.format("While loading %s (%s) renamed data folder: `%s' to `%s'", new Object[] { description
/*     */                 
/* 109 */                 .getFullName(), file, oldDataFolder, dataFolder }));
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 116 */     if (dataFolder.exists() && !dataFolder.isDirectory()) {
/* 117 */       throw new InvalidPluginException(String.format("Projected datafolder: `%s' for %s (%s) exists and is not a directory", new Object[] { dataFolder, description
/*     */ 
/*     */               
/* 120 */               .getFullName(), file }));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 125 */     for (String pluginName : description.getDepend()) {
/* 126 */       Plugin current = this.server.getPluginManager().getPlugin(pluginName);
/*     */       
/* 128 */       if (current == null) {
/* 129 */         throw new UnknownDependencyException("Unknown dependency " + pluginName + ". Please download and install " + pluginName + " to run this plugin.");
/*     */       }
/*     */     } 
/*     */     
/* 133 */     this.server.getUnsafe().checkSupported(description);
/*     */ 
/*     */     
/*     */     try {
/* 137 */       loader = new PluginClassLoader(this, getClass().getClassLoader(), description, dataFolder, file);
/* 138 */     } catch (InvalidPluginException ex) {
/* 139 */       throw ex;
/* 140 */     } catch (Throwable ex) {
/* 141 */       throw new InvalidPluginException(ex);
/*     */     } 
/*     */     
/* 144 */     this.loaders.add(loader);
/*     */     
/* 146 */     return (Plugin)loader.plugin;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PluginDescriptionFile getPluginDescription(@NotNull File file) throws InvalidDescriptionException {
/* 152 */     Validate.notNull(file, "File cannot be null");
/*     */     
/* 154 */     JarFile jar = null;
/* 155 */     InputStream stream = null;
/*     */     
/*     */     try {
/* 158 */       jar = new JarFile(file);
/* 159 */       JarEntry entry = jar.getJarEntry("plugin.yml");
/*     */       
/* 161 */       if (entry == null) {
/* 162 */         throw new InvalidDescriptionException(new FileNotFoundException("Jar does not contain plugin.yml"));
/*     */       }
/*     */       
/* 165 */       stream = jar.getInputStream(entry);
/*     */       
/* 167 */       return new PluginDescriptionFile(stream);
/*     */     }
/* 169 */     catch (IOException ex) {
/* 170 */       throw new InvalidDescriptionException(ex);
/* 171 */     } catch (YAMLException ex) {
/* 172 */       throw new InvalidDescriptionException(ex);
/*     */     } finally {
/* 174 */       if (jar != null) {
/*     */         try {
/* 176 */           jar.close();
/* 177 */         } catch (IOException iOException) {}
/*     */       }
/*     */       
/* 180 */       if (stream != null) {
/*     */         try {
/* 182 */           stream.close();
/* 183 */         } catch (IOException iOException) {}
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Pattern[] getPluginFileFilters() {
/* 192 */     return (Pattern[])this.fileFilters.clone();
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   Class<?> getClassByName(String name) {
/* 198 */     return getClassByName(name, null);
/*     */   }
/*     */   
/*     */   Class<?> getClassByName(String name, PluginClassLoader requester) {
/*     */     ReentrantReadWriteLock lock;
/* 203 */     Class<?> cachedClass = this.classes.get(name);
/* 204 */     if (cachedClass != null) {
/* 205 */       return cachedClass;
/*     */     }
/*     */     
/* 208 */     synchronized (this.classLoadLock) {
/* 209 */       lock = this.classLoadLock.computeIfAbsent(name, x -> new ReentrantReadWriteLock());
/* 210 */       this.classLoadLockCount.compute(name, (x, prev) -> Integer.valueOf((prev != null) ? (prev.intValue() + 1) : 1));
/*     */     } 
/* 212 */     lock.writeLock().lock();
/*     */     try {
/* 214 */       if (!DISABLE_CLASS_PRIORITIZATION && requester != null) {
/*     */         try {
/* 216 */           cachedClass = requester.findClass(name, false);
/* 217 */         } catch (ClassNotFoundException classNotFoundException) {}
/* 218 */         if (cachedClass != null) {
/* 219 */           return cachedClass;
/*     */         }
/*     */       } 
/*     */       
/* 223 */       cachedClass = this.classes.get(name);
/*     */ 
/*     */       
/* 226 */       if (cachedClass != null) {
/* 227 */         return cachedClass;
/*     */       }
/* 229 */       for (PluginClassLoader loader : this.loaders) {
/*     */         try {
/* 231 */           cachedClass = loader.findClass(name, false);
/* 232 */         } catch (ClassNotFoundException classNotFoundException) {}
/* 233 */         if (cachedClass != null) {
/* 234 */           return cachedClass;
/*     */         }
/*     */       }
/*     */     
/*     */     } finally {
/*     */       
/* 240 */       synchronized (this.classLoadLock) {
/* 241 */         lock.writeLock().unlock();
/* 242 */         if (((Integer)this.classLoadLockCount.get(name)).intValue() == 1) {
/* 243 */           this.classLoadLock.remove(name);
/* 244 */           this.classLoadLockCount.remove(name);
/*     */         } else {
/* 246 */           this.classLoadLockCount.compute(name, (x, prev) -> Integer.valueOf(prev.intValue() - 1));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 251 */     return null;
/*     */   }
/*     */   
/*     */   void setClass(@NotNull String name, @NotNull Class<?> clazz) {
/* 255 */     if (!this.classes.containsKey(name)) {
/* 256 */       this.classes.put(name, clazz);
/*     */       
/* 258 */       if (ConfigurationSerializable.class.isAssignableFrom(clazz)) {
/* 259 */         Class<? extends ConfigurationSerializable> serializable = clazz.asSubclass(ConfigurationSerializable.class);
/* 260 */         ConfigurationSerialization.registerClass(serializable);
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeClass(@NotNull String name) {
/* 266 */     Class<?> clazz = this.classes.remove(name);
/*     */     
/*     */     try {
/* 269 */       if (clazz != null && ConfigurationSerializable.class.isAssignableFrom(clazz)) {
/* 270 */         Class<? extends ConfigurationSerializable> serializable = clazz.asSubclass(ConfigurationSerializable.class);
/* 271 */         ConfigurationSerialization.unregisterClass(serializable);
/*     */       } 
/* 273 */     } catch (NullPointerException nullPointerException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(@NotNull Listener listener, @NotNull Plugin plugin) {
/*     */     Set<Method> methods;
/* 282 */     Validate.notNull(plugin, "Plugin can not be null");
/* 283 */     Validate.notNull(listener, "Listener can not be null");
/*     */     
/* 285 */     boolean useTimings = this.server.getPluginManager().useTimings();
/* 286 */     Map<Class<? extends Event>, Set<RegisteredListener>> ret = new HashMap<>();
/*     */     
/*     */     try {
/* 289 */       Method[] publicMethods = listener.getClass().getMethods();
/* 290 */       Method[] privateMethods = listener.getClass().getDeclaredMethods();
/* 291 */       methods = new HashSet<>(publicMethods.length + privateMethods.length, 1.0F);
/* 292 */       for (Method method : publicMethods) {
/* 293 */         methods.add(method);
/*     */       }
/* 295 */       for (Method method : privateMethods) {
/* 296 */         methods.add(method);
/*     */       }
/* 298 */     } catch (NoClassDefFoundError e) {
/* 299 */       plugin.getLogger().severe("Plugin " + plugin.getDescription().getFullName() + " has failed to register events for " + listener.getClass() + " because " + e.getMessage() + " does not exist.");
/* 300 */       return ret;
/*     */     } 
/*     */     
/* 303 */     for (Method method : methods) {
/* 304 */       EventHandler eh = method.<EventHandler>getAnnotation(EventHandler.class);
/* 305 */       if (eh == null) {
/*     */         continue;
/*     */       }
/* 308 */       if (method.isBridge() || method.isSynthetic()) {
/*     */         continue;
/*     */       }
/*     */       Class<?> checkClass;
/* 312 */       if ((method.getParameterTypes()).length != 1 || !Event.class.isAssignableFrom(checkClass = method.getParameterTypes()[0])) {
/* 313 */         plugin.getLogger().severe(plugin.getDescription().getFullName() + " attempted to register an invalid EventHandler method signature \"" + method.toGenericString() + "\" in " + listener.getClass());
/*     */         continue;
/*     */       } 
/* 316 */       Class<? extends Event> eventClass = checkClass.asSubclass(Event.class);
/* 317 */       method.setAccessible(true);
/* 318 */       Set<RegisteredListener> eventSet = ret.get(eventClass);
/* 319 */       if (eventSet == null) {
/* 320 */         eventSet = new HashSet<>();
/* 321 */         ret.put(eventClass, eventSet);
/*     */       } 
/*     */       
/* 324 */       for (Class<?> clazz = eventClass; Event.class.isAssignableFrom(clazz); clazz = clazz.getSuperclass()) {
/*     */         
/* 326 */         if (clazz.getAnnotation(Deprecated.class) != null) {
/* 327 */           Warning warning = clazz.<Warning>getAnnotation(Warning.class);
/* 328 */           Warning.WarningState warningState = this.server.getWarningState();
/* 329 */           if (!warningState.printFor(warning)) {
/*     */             break;
/*     */           }
/* 332 */           plugin.getLogger().log(Level.WARNING, 
/*     */               
/* 334 */               String.format("\"%s\" has registered a listener for %s on method \"%s\", but the event is Deprecated. \"%s\"; please notify the authors %s.", new Object[] {
/*     */                   
/* 336 */                   plugin.getDescription().getFullName(), clazz
/* 337 */                   .getName(), method
/* 338 */                   .toGenericString(), (
/* 339 */                   warning != null && warning.reason().length() != 0) ? warning.reason() : "Server performance will be affected", 
/* 340 */                   Arrays.toString(plugin.getDescription().getAuthors().toArray())
/* 341 */                 }), (warningState == Warning.WarningState.ON) ? (Throwable)new AuthorNagException(null) : null);
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/* 346 */       TimedEventExecutor timedEventExecutor = new TimedEventExecutor(EventExecutor.create(method, eventClass), plugin, method, eventClass);
/*     */ 
/*     */ 
/*     */       
/* 350 */       eventSet.add(new RegisteredListener(listener, (EventExecutor)timedEventExecutor, eh.priority(), plugin, eh.ignoreCancelled()));
/*     */     } 
/*     */     
/* 353 */     return ret;
/*     */   }
/*     */ 
/*     */   
/*     */   public void enablePlugin(@NotNull Plugin plugin) {
/* 358 */     Validate.isTrue(plugin instanceof JavaPlugin, "Plugin is not associated with this PluginLoader");
/*     */     
/* 360 */     if (!plugin.isEnabled()) {
/*     */       
/* 362 */       String enableMsg = "Enabling " + plugin.getDescription().getFullName();
/* 363 */       if (UnsafeValues.isLegacyPlugin(plugin)) {
/* 364 */         enableMsg = enableMsg + "*";
/*     */       }
/*     */       
/* 367 */       plugin.getLogger().info(enableMsg);
/*     */ 
/*     */       
/* 370 */       JavaPlugin jPlugin = (JavaPlugin)plugin;
/*     */       
/* 372 */       PluginClassLoader pluginLoader = (PluginClassLoader)jPlugin.getClassLoader();
/*     */       
/* 374 */       if (!this.loaders.contains(pluginLoader)) {
/* 375 */         this.loaders.add(pluginLoader);
/* 376 */         this.server.getLogger().log(Level.WARNING, "Enabled plugin with unregistered PluginClassLoader " + plugin.getDescription().getFullName());
/*     */       } 
/*     */       
/*     */       try {
/* 380 */         jPlugin.setEnabled(true);
/* 381 */       } catch (Throwable ex) {
/* 382 */         this.server.getLogger().log(Level.SEVERE, "Error occurred while enabling " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
/*     */         
/* 384 */         this.server.getPluginManager().disablePlugin((Plugin)jPlugin, true);
/*     */ 
/*     */         
/*     */         return;
/*     */       } 
/*     */ 
/*     */       
/* 391 */       this.server.getPluginManager().callEvent((Event)new PluginEnableEvent(plugin));
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void disablePlugin(@NotNull Plugin plugin) {
/* 398 */     disablePlugin(plugin, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public void disablePlugin(@NotNull Plugin plugin, boolean closeClassloader) {
/* 403 */     Validate.isTrue(plugin instanceof JavaPlugin, "Plugin is not associated with this PluginLoader");
/*     */     
/* 405 */     if (plugin.isEnabled()) {
/* 406 */       String message = String.format("Disabling %s", new Object[] { plugin.getDescription().getFullName() });
/* 407 */       plugin.getLogger().info(message);
/*     */       
/* 409 */       this.server.getPluginManager().callEvent((Event)new PluginDisableEvent(plugin));
/*     */       
/* 411 */       JavaPlugin jPlugin = (JavaPlugin)plugin;
/* 412 */       ClassLoader cloader = jPlugin.getClassLoader();
/*     */       
/*     */       try {
/* 415 */         jPlugin.setEnabled(false);
/* 416 */       } catch (Throwable ex) {
/* 417 */         this.server.getLogger().log(Level.SEVERE, "Error occurred while disabling " + plugin.getDescription().getFullName() + " (Is it up to date?)", ex);
/*     */       } 
/*     */       
/* 420 */       if (cloader instanceof PluginClassLoader) {
/* 421 */         PluginClassLoader loader = (PluginClassLoader)cloader;
/* 422 */         this.loaders.remove(loader);
/*     */         
/* 424 */         Set<String> names = loader.getClasses();
/*     */         
/* 426 */         for (String name : names) {
/* 427 */           removeClass(name);
/*     */         }
/*     */         
/*     */         try {
/* 431 */           if (closeClassloader) {
/* 432 */             loader.close();
/*     */           }
/* 434 */         } catch (IOException e) {
/* 435 */           this.server.getLogger().log(Level.WARNING, "Error closing the Plugin Class Loader for " + plugin.getDescription().getFullName());
/* 436 */           e.printStackTrace();
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\java\JavaPluginLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */