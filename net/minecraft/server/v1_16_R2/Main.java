/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.profile.PaperAuthenticationService;
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.io.Files;
/*     */ import com.mojang.authlib.GameProfileRepository;
/*     */ import com.mojang.authlib.minecraft.MinecraftSessionService;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import java.awt.GraphicsEnvironment;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.net.Proxy;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.Optional;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.function.BooleanSupplier;
/*     */ import joptsimple.OptionSet;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.file.YamlConfiguration;
/*     */ import org.spigotmc.SpigotConfig;
/*     */ 
/*     */ public class Main {
/*  27 */   private static final Logger LOGGER = LogManager.getLogger();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void main(OptionSet optionset) {
/*     */     try {
/*     */       File file;
/*     */       DataPackResources datapackresources;
/*  59 */       CrashReport.h();
/*  60 */       DispenserRegistry.init();
/*  61 */       DispenserRegistry.c();
/*  62 */       SystemUtils.l();
/*  63 */       IRegistryCustom.Dimension iregistrycustom_dimension = IRegistryCustom.b();
/*  64 */       Path java_nio_file_path = Paths.get("server.properties", new String[0]);
/*  65 */       DedicatedServerSettings dedicatedserversettings = new DedicatedServerSettings(iregistrycustom_dimension, optionset);
/*     */       
/*  67 */       dedicatedserversettings.save();
/*     */       
/*  69 */       YamlConfiguration bukkitConfiguration = loadConfigFile((File)optionset.valueOf("bukkit-settings"));
/*  70 */       YamlConfiguration spigotConfiguration = loadConfigFile((File)optionset.valueOf("spigot-settings"));
/*  71 */       YamlConfiguration paperConfiguration = loadConfigFile((File)optionset.valueOf("paper-settings"));
/*     */ 
/*     */       
/*  74 */       Path java_nio_file_path1 = Paths.get("eula.txt", new String[0]);
/*  75 */       EULA eula = new EULA(java_nio_file_path1);
/*     */       
/*  77 */       if (optionset.has("initSettings")) {
/*  78 */         LOGGER.info("Initialized '{}' and '{}'", java_nio_file_path.toAbsolutePath(), java_nio_file_path1.toAbsolutePath());
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  83 */       boolean eulaAgreed = Boolean.getBoolean("com.mojang.eula.agree");
/*  84 */       if (eulaAgreed) {
/*     */         
/*  86 */         System.err.println("You have used the Spigot command line EULA agreement flag.");
/*  87 */         System.err.println("By using this setting you are indicating your agreement to Mojang's EULA (https://account.mojang.com/documents/minecraft_eula).");
/*  88 */         System.err.println("If you do not agree to the above EULA please stop your server and remove this flag immediately.");
/*     */       } 
/*     */       
/*  91 */       if (!eula.a() && !eulaAgreed) {
/*  92 */         LOGGER.info("You need to agree to the EULA in order to run the server. Go to eula.txt for more info.");
/*     */         
/*     */         return;
/*     */       } 
/*  96 */       SpigotConfig.disabledAdvancements = spigotConfiguration.getStringList("advancements.disabled");
/*     */ 
/*     */       
/*  99 */       File userCacheFile = new File("usercache.json");
/* 100 */       if (optionset.has("universe")) {
/* 101 */         file = (File)optionset.valueOf("universe");
/* 102 */         userCacheFile = new File(file, "usercache.json");
/*     */       } else {
/* 104 */         file = new File(bukkitConfiguration.getString("settings.world-container", "."));
/*     */       } 
/*     */       
/* 107 */       PaperAuthenticationService paperAuthenticationService = new PaperAuthenticationService(Proxy.NO_PROXY, UUID.randomUUID().toString());
/* 108 */       MinecraftSessionService minecraftsessionservice = paperAuthenticationService.createMinecraftSessionService();
/* 109 */       GameProfileRepository gameprofilerepository = paperAuthenticationService.createProfileRepository();
/* 110 */       UserCache usercache = new UserCache(gameprofilerepository, userCacheFile);
/*     */       
/* 112 */       String s = Optional.<Object>ofNullable(optionset.valueOf("world")).orElse((dedicatedserversettings.getProperties()).levelName);
/* 113 */       Convertable convertable = Convertable.a(file.toPath());
/* 114 */       Convertable.ConversionSession convertable_conversionsession = convertable.c(s, WorldDimension.OVERWORLD);
/*     */       
/* 116 */       MinecraftServer.convertWorld(convertable_conversionsession);
/* 117 */       DataPackConfiguration datapackconfiguration = convertable_conversionsession.e();
/* 118 */       boolean flag = optionset.has("safeMode");
/*     */       
/* 120 */       if (flag) {
/* 121 */         LOGGER.warn("Safe mode active, only vanilla datapack will be loaded");
/*     */       }
/*     */       
/* 124 */       ResourcePackRepository resourcepackrepository = new ResourcePackRepository(new ResourcePackSource[] { new ResourcePackSourceVanilla(), new ResourcePackSourceFolder(convertable_conversionsession.getWorldFolder(SavedFile.DATAPACKS).toFile(), PackSource.c) });
/*     */       
/* 126 */       File bukkitDataPackFolder = new File(convertable_conversionsession.getWorldFolder(SavedFile.DATAPACKS).toFile(), "bukkit");
/* 127 */       if (!bukkitDataPackFolder.exists()) {
/* 128 */         bukkitDataPackFolder.mkdirs();
/*     */       }
/* 130 */       File mcMeta = new File(bukkitDataPackFolder, "pack.mcmeta");
/*     */       try {
/* 132 */         Files.write("{\n    \"pack\": {\n        \"description\": \"Data pack for resources provided by Bukkit plugins\",\n        \"pack_format\": " + 
/*     */ 
/*     */             
/* 135 */             SharedConstants.getGameVersion().getPackVersion() + "\n    }\n}\n", mcMeta, Charsets.UTF_8);
/*     */       
/*     */       }
/* 138 */       catch (IOException ex) {
/* 139 */         throw new RuntimeException("Could not initialize Bukkit datapack", ex);
/*     */       } 
/*     */       
/* 142 */       DataPackConfiguration datapackconfiguration1 = MinecraftServer.a(resourcepackrepository, (datapackconfiguration == null) ? DataPackConfiguration.a : datapackconfiguration, flag);
/* 143 */       CompletableFuture<DataPackResources> completablefuture = DataPackResources.a(resourcepackrepository.f(), CommandDispatcher.ServerType.DEDICATED, (dedicatedserversettings.getProperties()).functionPermissionLevel, SystemUtils.f(), Runnable::run);
/*     */ 
/*     */ 
/*     */       
/*     */       try {
/* 148 */         datapackresources = completablefuture.get();
/* 149 */       } catch (Exception exception) {
/* 150 */         LOGGER.warn("Failed to load datapacks, can't proceed with server load. You can either fix your datapacks or reset to vanilla with --safeMode", exception);
/* 151 */         resourcepackrepository.close();
/*     */         
/*     */         return;
/*     */       } 
/* 155 */       datapackresources.i();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 185 */       Class.forName("net.minecraft.server.v1_16_R2.VillagerTrades");
/* 186 */       DedicatedServer dedicatedServer = MinecraftServer.<DedicatedServer>a(thread -> {
/*     */             DedicatedServer dedicatedserver1 = new DedicatedServer(optionset, datapackconfiguration1, thread, iregistrycustom_dimension, convertable_conversionsession, resourcepackrepository, datapackresources, null, dedicatedserversettings, DataConverterRegistry.a(), minecraftsessionservice, gameprofilerepository, usercache, WorldLoadListenerLogger::new);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/* 195 */             boolean flag1 = (!optionset.has("nogui") && !optionset.nonOptionArguments().contains("nogui"));
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             if (flag1 && !GraphicsEnvironment.isHeadless()) {
/*     */               dedicatedserver1.bc();
/*     */             }
/*     */ 
/*     */ 
/*     */             
/*     */             if (optionset.has("port")) {
/*     */               int port = ((Integer)optionset.valueOf("port")).intValue();
/*     */ 
/*     */ 
/*     */               
/*     */               if (port > 0) {
/*     */                 dedicatedserver1.setPort(port);
/*     */               }
/*     */             } 
/*     */ 
/*     */ 
/*     */             
/*     */             return dedicatedserver1;
/*     */           });
/* 220 */     } catch (Exception exception1) {
/* 221 */       LOGGER.fatal("Failed to start the minecraft server", exception1);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static YamlConfiguration loadConfigFile(File configFile) throws IOException, InvalidConfigurationException {
/* 228 */     YamlConfiguration config = new YamlConfiguration();
/* 229 */     if (configFile.exists()) {
/* 230 */       config.load(configFile);
/*     */     }
/* 232 */     return config;
/*     */   }
/*     */ 
/*     */   
/*     */   public static void convertWorld(Convertable.ConversionSession convertable_conversionsession, DataFixer datafixer, boolean flag, BooleanSupplier booleansupplier, ImmutableSet<ResourceKey<DimensionManager>> immutableset) {
/* 237 */     LOGGER.info("Forcing world upgrade! {}", convertable_conversionsession.getLevelName());
/* 238 */     WorldUpgrader worldupgrader = new WorldUpgrader(convertable_conversionsession, datafixer, immutableset, flag);
/* 239 */     IChatBaseComponent ichatbasecomponent = null;
/*     */     
/* 241 */     while (!worldupgrader.b()) {
/* 242 */       IChatBaseComponent ichatbasecomponent1 = worldupgrader.h();
/*     */       
/* 244 */       if (ichatbasecomponent != ichatbasecomponent1) {
/* 245 */         ichatbasecomponent = ichatbasecomponent1;
/* 246 */         LOGGER.info(worldupgrader.h().getString());
/*     */       } 
/*     */       
/* 249 */       int i = worldupgrader.e();
/*     */       
/* 251 */       if (i > 0) {
/* 252 */         int j = worldupgrader.f() + worldupgrader.g();
/*     */         
/* 254 */         LOGGER.info("{}% completed ({} / {} chunks)...", Integer.valueOf(MathHelper.d(j / i * 100.0F)), Integer.valueOf(j), Integer.valueOf(i));
/*     */       } 
/*     */       
/* 257 */       if (!booleansupplier.getAsBoolean()) {
/* 258 */         worldupgrader.a(); continue;
/*     */       } 
/*     */       try {
/* 261 */         Thread.sleep(1000L);
/* 262 */       } catch (InterruptedException interruptedException) {}
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */