/*     */ package com.tuinity.tuinity.config;
/*     */ 
/*     */ import com.destroystokyo.paper.util.SneakyThrow;
/*     */ import java.io.File;
/*     */ import java.lang.reflect.Method;
/*     */ import java.lang.reflect.Modifier;
/*     */ import java.util.logging.Level;
/*     */ import net.minecraft.server.v1_16_R2.TicketType;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class TuinityConfig
/*     */ {
/*     */   public static final String CONFIG_HEADER = "Configuration file for Tuinity.";
/*     */   public static final int CURRENT_CONFIG_VERSION = 2;
/*  19 */   private static final Object[] EMPTY = new Object[0]; private static File configFile; public static YamlConfiguration config;
/*     */   private static int configVersion;
/*     */   public static boolean tickWorldsInParallel;
/*     */   public static int tickThreads;
/*     */   public static int delayChunkUnloadsBy;
/*     */   public static boolean lagCompensateBlockBreaking;
/*     */   
/*     */   public static void init(File file) {
/*  27 */     File tuinityConfig = new File(file.getParent(), "tuinity.yml");
/*  28 */     if (!tuinityConfig.exists()) {
/*  29 */       File oldConfig = new File(file.getParent(), "concrete.yml");
/*  30 */       oldConfig.renameTo(tuinityConfig);
/*     */     } 
/*  32 */     configFile = file;
/*  33 */     YamlConfiguration config = new YamlConfiguration();
/*  34 */     config.options().header("Configuration file for Tuinity.");
/*  35 */     config.options().copyDefaults(true);
/*     */     
/*  37 */     if (!file.exists()) {
/*     */       try {
/*  39 */         file.createNewFile();
/*  40 */       } catch (Exception ex) {
/*  41 */         Bukkit.getLogger().log(Level.SEVERE, "Failure to create tuinity config", ex);
/*     */       } 
/*     */     } else {
/*     */       try {
/*  45 */         config.load(file);
/*  46 */       } catch (Exception ex) {
/*  47 */         Bukkit.getLogger().log(Level.SEVERE, "Failure to load tuinity config", ex);
/*  48 */         SneakyThrow.sneaky(ex);
/*  49 */         throw new RuntimeException(ex);
/*     */       } 
/*     */     } 
/*     */     
/*  53 */     load(config);
/*     */   }
/*     */   
/*     */   public static void load(YamlConfiguration config) {
/*  57 */     TuinityConfig.config = config;
/*  58 */     configVersion = getInt("config-version-please-do-not-modify-me", 2);
/*  59 */     set("config-version-please-do-not-modify-me", Integer.valueOf(2));
/*     */     
/*  61 */     for (Method method : TuinityConfig.class.getDeclaredMethods()) {
/*  62 */       if (method.getReturnType() == void.class && method.getParameterCount() == 0 && 
/*  63 */         Modifier.isPrivate(method.getModifiers()) && Modifier.isStatic(method.getModifiers())) {
/*     */         
/*     */         try {
/*     */ 
/*     */           
/*  68 */           method.setAccessible(true);
/*  69 */           method.invoke(null, EMPTY);
/*  70 */         } catch (Exception ex) {
/*  71 */           SneakyThrow.sneaky(ex);
/*  72 */           throw new RuntimeException(ex);
/*     */         } 
/*     */       }
/*     */     } 
/*     */     
/*     */     try {
/*  78 */       config.save(configFile);
/*  79 */     } catch (Exception ex) {
/*  80 */       Bukkit.getLogger().log(Level.SEVERE, "Unable to save tuinity config", ex);
/*     */     } 
/*     */   }
/*     */   
/*     */   static void set(String path, Object value) {
/*  85 */     config.set(path, value);
/*     */   }
/*     */   
/*     */   static boolean getBoolean(String path, boolean dfl) {
/*  89 */     config.addDefault(path, Boolean.valueOf(dfl));
/*  90 */     return config.getBoolean(path, dfl);
/*     */   }
/*     */   
/*     */   static int getInt(String path, int dfl) {
/*  94 */     config.addDefault(path, Integer.valueOf(dfl));
/*  95 */     return config.getInt(path, dfl);
/*     */   }
/*     */   
/*     */   static long getLong(String path, long dfl) {
/*  99 */     config.addDefault(path, Long.valueOf(dfl));
/* 100 */     return config.getLong(path, dfl);
/*     */   }
/*     */   
/*     */   static double getDouble(String path, double dfl) {
/* 104 */     config.addDefault(path, Double.valueOf(dfl));
/* 105 */     return config.getDouble(path, dfl);
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
/*     */   private static void delayChunkUnloadsBy() {
/* 124 */     delayChunkUnloadsBy = getInt("delay-chunkunloads-by", 1) * 20;
/* 125 */     if (delayChunkUnloadsBy >= 0) {
/* 126 */       TicketType.DELAYED_UNLOAD.loadPeriod = delayChunkUnloadsBy;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void lagCompensateBlockBreaking() {
/* 133 */     lagCompensateBlockBreaking = getBoolean("lag-compensate-block-breaking", true);
/*     */   }
/*     */   
/*     */   public static final class WorldConfig {
/*     */     public final String worldName;
/*     */     public ConfigurationSection config;
/*     */     ConfigurationSection worldDefaults;
/*     */     public int threads;
/*     */     
/*     */     public WorldConfig(String worldName) {
/* 143 */       this.worldName = worldName;
/* 144 */       init();
/*     */     }
/*     */     public int spawnLimitMonsters; public int spawnLimitAnimals; public int spawnLimitWaterAmbient; public int spawnLimitWaterAnimals; public int spawnLimitAmbient;
/*     */     public void init() {
/* 148 */       this.worldDefaults = TuinityConfig.config.getConfigurationSection("world-settings.default");
/* 149 */       if (this.worldDefaults == null) {
/* 150 */         this.worldDefaults = TuinityConfig.config.createSection("world-settings.default");
/*     */       }
/*     */       
/* 153 */       String worldSectionPath = (TuinityConfig.configVersion < 1) ? this.worldName : "world-settings.".concat(this.worldName);
/* 154 */       ConfigurationSection section = TuinityConfig.config.getConfigurationSection(worldSectionPath);
/* 155 */       if (section == null) {
/* 156 */         section = TuinityConfig.config.createSection(worldSectionPath);
/*     */       }
/* 158 */       TuinityConfig.config.set(worldSectionPath, section);
/*     */       
/* 160 */       load(section);
/*     */     }
/*     */     
/*     */     public void load(ConfigurationSection config) {
/* 164 */       this.config = config;
/*     */       
/* 166 */       for (Method method : WorldConfig.class.getDeclaredMethods()) {
/* 167 */         if (method.getReturnType() == void.class && method.getParameterCount() == 0 && 
/* 168 */           Modifier.isPrivate(method.getModifiers()) && !Modifier.isStatic(method.getModifiers())) {
/*     */           
/*     */           try {
/*     */ 
/*     */             
/* 173 */             method.setAccessible(true);
/* 174 */             method.invoke(this, TuinityConfig.EMPTY);
/* 175 */           } catch (Exception ex) {
/* 176 */             SneakyThrow.sneaky(ex);
/* 177 */             throw new RuntimeException(ex);
/*     */           } 
/*     */         }
/*     */       } 
/* 181 */       if (TuinityConfig.configVersion < 1) {
/* 182 */         ConfigurationSection oldSection = TuinityConfig.config.getConfigurationSection(this.worldName);
/* 183 */         TuinityConfig.config.set("world-settings.".concat(this.worldName), oldSection);
/* 184 */         TuinityConfig.config.set(this.worldName, null);
/*     */       } 
/*     */ 
/*     */       
/*     */       try {
/* 189 */         TuinityConfig.config.save(TuinityConfig.configFile);
/* 190 */       } catch (Exception ex) {
/* 191 */         Bukkit.getLogger().log(Level.SEVERE, "Unable to save tuinity config", ex);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     void set(String path, Object val) {
/* 200 */       this.worldDefaults.set(path, val);
/* 201 */       if (this.config.get(path) != null) {
/* 202 */         this.config.set(path, val);
/*     */       }
/*     */     }
/*     */     
/*     */     boolean getBoolean(String path, boolean dfl) {
/* 207 */       this.worldDefaults.addDefault(path, Boolean.valueOf(dfl));
/* 208 */       if (TuinityConfig.configVersion < 1 && 
/* 209 */         this.config.getBoolean(path) == dfl) {
/* 210 */         this.config.set(path, null);
/*     */       }
/*     */       
/* 213 */       return this.config.getBoolean(path, this.worldDefaults.getBoolean(path));
/*     */     }
/*     */     
/*     */     int getInt(String path, int dfl) {
/* 217 */       this.worldDefaults.addDefault(path, Integer.valueOf(dfl));
/* 218 */       if (TuinityConfig.configVersion < 1 && 
/* 219 */         this.config.getInt(path) == dfl) {
/* 220 */         this.config.set(path, null);
/*     */       }
/*     */       
/* 223 */       return this.config.getInt(path, this.worldDefaults.getInt(path));
/*     */     }
/*     */     
/*     */     long getLong(String path, long dfl) {
/* 227 */       this.worldDefaults.addDefault(path, Long.valueOf(dfl));
/* 228 */       if (TuinityConfig.configVersion < 1 && 
/* 229 */         this.config.getLong(path) == dfl) {
/* 230 */         this.config.set(path, null);
/*     */       }
/*     */       
/* 233 */       return this.config.getLong(path, this.worldDefaults.getLong(path));
/*     */     }
/*     */     
/*     */     double getDouble(String path, double dfl) {
/* 237 */       this.worldDefaults.addDefault(path, Double.valueOf(dfl));
/* 238 */       if (TuinityConfig.configVersion < 1 && 
/* 239 */         this.config.getDouble(path) == dfl) {
/* 240 */         this.config.set(path, null);
/*     */       }
/*     */       
/* 243 */       return this.config.getDouble(path, this.worldDefaults.getDouble(path));
/*     */     }
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
/*     */     private void perWorldSpawnLimit() {
/* 262 */       String path = "spawn-limits";
/*     */       
/* 264 */       this.spawnLimitMonsters = getInt("spawn-limits.monsters", -1);
/* 265 */       this.spawnLimitAnimals = getInt("spawn-limits.animals", -1);
/* 266 */       this.spawnLimitWaterAmbient = getInt("spawn-limits.water-ambient", -1);
/* 267 */       this.spawnLimitWaterAnimals = getInt("spawn-limits.water-animals", -1);
/* 268 */       this.spawnLimitAmbient = getInt("spawn-limits.ambient", -1);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\tuinity\tuinity\config\TuinityConfig.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */