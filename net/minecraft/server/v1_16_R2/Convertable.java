/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.UnmodifiableIterator;
/*     */ import com.mojang.datafixers.DataFixer;
/*     */ import com.mojang.datafixers.util.Pair;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import com.mojang.serialization.Lifecycle;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.attribute.FileAttribute;
/*     */ import java.time.format.DateTimeFormatter;
/*     */ import java.time.format.DateTimeFormatterBuilder;
/*     */ import java.time.format.SignStyle;
/*     */ import java.time.temporal.ChronoField;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ import java.util.function.BiFunction;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class Convertable {
/*  29 */   private static final Logger LOGGER = LogManager.getLogger();
/*  30 */   private static final DateTimeFormatter b = (new DateTimeFormatterBuilder()).appendValue(ChronoField.YEAR, 4, 10, SignStyle.EXCEEDS_PAD).appendLiteral('-').appendValue(ChronoField.MONTH_OF_YEAR, 2).appendLiteral('-').appendValue(ChronoField.DAY_OF_MONTH, 2).appendLiteral('_').appendValue(ChronoField.HOUR_OF_DAY, 2).appendLiteral('-').appendValue(ChronoField.MINUTE_OF_HOUR, 2).appendLiteral('-').appendValue(ChronoField.SECOND_OF_MINUTE, 2).toFormatter();
/*  31 */   private static final ImmutableList<String> c = ImmutableList.of("RandomSeed", "generatorName", "generatorOptions", "generatorVersion", "legacy_custom_options", "MapFeatures", "BonusChest");
/*     */   public final Path universe;
/*     */   private final Path backupUniverse;
/*     */   private final DataFixer f;
/*     */   
/*     */   public Convertable(Path java_nio_file_path, Path java_nio_file_path1, DataFixer datafixer) {
/*  37 */     this.f = datafixer;
/*     */     
/*     */     try {
/*  40 */       Files.createDirectories(Files.exists(java_nio_file_path, new java.nio.file.LinkOption[0]) ? java_nio_file_path.toRealPath(new java.nio.file.LinkOption[0]) : java_nio_file_path, (FileAttribute<?>[])new FileAttribute[0]);
/*  41 */     } catch (IOException ioexception) {
/*  42 */       throw new RuntimeException(ioexception);
/*     */     } 
/*     */     
/*  45 */     this.universe = java_nio_file_path;
/*  46 */     this.backupUniverse = java_nio_file_path1;
/*     */   }
/*     */   
/*     */   public static Convertable a(Path java_nio_file_path) {
/*  50 */     return new Convertable(java_nio_file_path, java_nio_file_path.resolve("../backups"), DataConverterRegistry.a());
/*     */   }
/*     */   
/*     */   private static <T> Pair<GeneratorSettings, Lifecycle> a(Dynamic<T> dynamic, DataFixer datafixer, int i) {
/*  54 */     Dynamic<T> dynamic1 = dynamic.get("WorldGenSettings").orElseEmptyMap();
/*  55 */     UnmodifiableIterator unmodifiableiterator = c.iterator();
/*     */     
/*  57 */     while (unmodifiableiterator.hasNext()) {
/*  58 */       String s = (String)unmodifiableiterator.next();
/*  59 */       Optional<? extends Dynamic<?>> optional = dynamic.get(s).result();
/*     */       
/*  61 */       if (optional.isPresent()) {
/*  62 */         dynamic1 = dynamic1.set(s, optional.get());
/*     */       }
/*     */     } 
/*     */     
/*  66 */     Dynamic<T> dynamic2 = datafixer.update(DataConverterTypes.WORLD_GEN_SETTINGS, dynamic1, i, SharedConstants.getGameVersion().getWorldVersion());
/*  67 */     DataResult<GeneratorSettings> dataresult = GeneratorSettings.a.parse(dynamic2);
/*  68 */     Logger logger = LOGGER;
/*     */     
/*  70 */     logger.getClass();
/*  71 */     Objects.requireNonNull(logger); return Pair.of(dataresult.resultOrPartial(SystemUtils.a("WorldGenSettings: ", logger::error)).orElseGet(() -> { DataResult dataresult1 = RegistryLookupCodec.<DimensionManager>a(IRegistry.K).codec().parse(dynamic2); Logger logger1 = LOGGER; logger1.getClass(); Objects.requireNonNull(logger1); IRegistry<DimensionManager> iregistry = (IRegistry<DimensionManager>)dataresult1.resultOrPartial(SystemUtils.a("Dimension type registry: ", logger1::error)).orElseThrow(()); dataresult1 = RegistryLookupCodec.<BiomeBase>a(IRegistry.ay).codec().parse(dynamic2); logger1 = LOGGER; logger1.getClass(); Objects.requireNonNull(logger1); IRegistry<BiomeBase> iregistry1 = (IRegistry<BiomeBase>)dataresult1.resultOrPartial(SystemUtils.a("Biome registry: ", logger1::error)).orElseThrow(()); dataresult1 = RegistryLookupCodec.<GeneratorSettingBase>a(IRegistry.ar).codec().parse(dynamic2); logger1 = LOGGER; logger1.getClass(); Objects.requireNonNull(logger1); IRegistry<GeneratorSettingBase> iregistry2 = (IRegistry<GeneratorSettingBase>)dataresult1.resultOrPartial(SystemUtils.a("Noise settings registry: ", logger1::error)).orElseThrow(()); return GeneratorSettings.a(iregistry, iregistry1, iregistry2); }), dataresult
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
/*  95 */         .lifecycle());
/*     */   }
/*     */   
/*     */   private static DataPackConfiguration a(Dynamic<?> dynamic) {
/*  99 */     DataResult dataresult = DataPackConfiguration.b.parse(dynamic);
/* 100 */     Logger logger = LOGGER;
/*     */     
/* 102 */     logger.getClass();
/* 103 */     Objects.requireNonNull(logger); return dataresult.resultOrPartial(logger::error).orElse(DataPackConfiguration.a);
/*     */   }
/*     */   
/*     */   private int g() {
/* 107 */     return 19133;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private <T> T a(File file, BiFunction<File, DataFixer, T> bifunction) {
/* 112 */     if (!file.exists()) {
/* 113 */       return null;
/*     */     }
/* 115 */     File file1 = new File(file, "level.dat");
/*     */     
/* 117 */     if (file1.exists()) {
/* 118 */       T t0 = bifunction.apply(file1, this.f);
/*     */       
/* 120 */       if (t0 != null) {
/* 121 */         return t0;
/*     */       }
/*     */     } 
/*     */     
/* 125 */     file1 = new File(file, "level.dat_old");
/* 126 */     return file1.exists() ? bifunction.apply(file1, this.f) : null;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   private static DataPackConfiguration b(File file, DataFixer datafixer) {
/*     */     try {
/* 133 */       NBTTagCompound nbttagcompound = NBTCompressedStreamTools.a(file);
/* 134 */       NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Data");
/*     */       
/* 136 */       nbttagcompound1.remove("Player");
/* 137 */       int i = nbttagcompound1.hasKeyOfType("DataVersion", 99) ? nbttagcompound1.getInt("DataVersion") : -1;
/* 138 */       Dynamic<NBTBase> dynamic = datafixer.update(DataFixTypes.LEVEL.a(), new Dynamic(DynamicOpsNBT.a, nbttagcompound1), i, SharedConstants.getGameVersion().getWorldVersion());
/*     */       
/* 140 */       return dynamic.get("DataPacks").result().map(Convertable::a).orElse(DataPackConfiguration.a);
/* 141 */     } catch (Exception exception) {
/* 142 */       LOGGER.error("Exception reading {}", file, exception);
/* 143 */       return null;
/*     */     } 
/*     */   }
/*     */   
/*     */   private static BiFunction<File, DataFixer, WorldDataServer> b(DynamicOps<NBTBase> dynamicops, DataPackConfiguration datapackconfiguration) {
/* 148 */     return (file, datafixer) -> {
/*     */         try {
/*     */           NBTTagCompound nbttagcompound = NBTCompressedStreamTools.a(file);
/*     */           
/*     */           NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Data");
/*     */           
/*     */           NBTTagCompound nbttagcompound2 = nbttagcompound1.hasKeyOfType("Player", 10) ? nbttagcompound1.getCompound("Player") : null;
/*     */           nbttagcompound1.remove("Player");
/*     */           int i = nbttagcompound1.hasKeyOfType("DataVersion", 99) ? nbttagcompound1.getInt("DataVersion") : -1;
/*     */           Dynamic<NBTBase> dynamic = datafixer.update(DataFixTypes.LEVEL.a(), new Dynamic(dynamicops, nbttagcompound1), i, SharedConstants.getGameVersion().getWorldVersion());
/*     */           Pair<GeneratorSettings, Lifecycle> pair = a(dynamic, datafixer, i);
/*     */           LevelVersion levelversion = LevelVersion.a(dynamic);
/*     */           WorldSettings worldsettings = WorldSettings.a(dynamic, datapackconfiguration);
/*     */           return WorldDataServer.a(dynamic, datafixer, i, nbttagcompound2, worldsettings, levelversion, (GeneratorSettings)pair.getFirst(), (Lifecycle)pair.getSecond());
/* 162 */         } catch (Exception exception) {
/*     */           LOGGER.error("Exception reading {}", file, exception);
/*     */           return null;
/*     */         } 
/*     */       };
/*     */   }
/*     */   
/*     */   private BiFunction<File, DataFixer, WorldInfo> a(File file, boolean flag) {
/* 170 */     return (file1, datafixer) -> {
/*     */         try {
/*     */           NBTTagCompound nbttagcompound = NBTCompressedStreamTools.a(file1);
/*     */           
/*     */           NBTTagCompound nbttagcompound1 = nbttagcompound.getCompound("Data");
/*     */           
/*     */           nbttagcompound1.remove("Player");
/*     */           
/*     */           int i = nbttagcompound1.hasKeyOfType("DataVersion", 99) ? nbttagcompound1.getInt("DataVersion") : -1;
/*     */           
/*     */           Dynamic<NBTBase> dynamic = datafixer.update(DataFixTypes.LEVEL.a(), new Dynamic(DynamicOpsNBT.a, nbttagcompound1), i, SharedConstants.getGameVersion().getWorldVersion());
/*     */           LevelVersion levelversion = LevelVersion.a(dynamic);
/*     */           int j = levelversion.a();
/*     */           if (j != 19132 && j != 19133) {
/*     */             return null;
/*     */           }
/*     */           boolean flag1 = (j != g());
/*     */           File file2 = new File(file, "icon.png");
/*     */           DataPackConfiguration datapackconfiguration = dynamic.get("DataPacks").result().map(Convertable::a).orElse(DataPackConfiguration.a);
/*     */           WorldSettings worldsettings = WorldSettings.a(dynamic, datapackconfiguration);
/*     */           return new WorldInfo(worldsettings, levelversion, file.getName(), flag1, flag, file2);
/* 191 */         } catch (Exception exception) {
/*     */           LOGGER.error("Exception reading {}", file1, exception);
/*     */           return null;
/*     */         } 
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   public ConversionSession c(String s, ResourceKey<WorldDimension> dimensionType) throws IOException {
/* 200 */     return new ConversionSession(s, dimensionType);
/*     */   }
/*     */   
/*     */   public class ConversionSession
/*     */     implements AutoCloseable
/*     */   {
/*     */     private final SessionLock lock;
/*     */     public final Path folder;
/*     */     private final String levelName;
/* 209 */     private final Map<SavedFile, Path> e = Maps.newHashMap();
/*     */     
/*     */     private final ResourceKey<WorldDimension> dimensionType;
/*     */     
/*     */     public ConversionSession(String s, ResourceKey<WorldDimension> dimensionType) throws IOException {
/* 214 */       this.dimensionType = dimensionType;
/*     */       
/* 216 */       this.levelName = s;
/* 217 */       this.folder = Convertable.this.universe.resolve(s);
/* 218 */       this.lock = SessionLock.a(this.folder);
/*     */     }
/*     */     
/*     */     public String getLevelName() {
/* 222 */       return this.levelName;
/*     */     }
/*     */     
/*     */     public Path getWorldFolder(SavedFile savedfile) {
/* 226 */       return this.e.computeIfAbsent(savedfile, savedfile1 -> this.folder.resolve(savedfile1.a()));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public File a(ResourceKey<World> resourcekey) {
/* 233 */       return getFolder(this.folder.toFile());
/*     */     }
/*     */     
/*     */     private File getFolder(File file) {
/* 237 */       if (this.dimensionType == WorldDimension.OVERWORLD)
/* 238 */         return file; 
/* 239 */       if (this.dimensionType == WorldDimension.THE_NETHER)
/* 240 */         return new File(file, "DIM-1"); 
/* 241 */       if (this.dimensionType == WorldDimension.THE_END) {
/* 242 */         return new File(file, "DIM1");
/*     */       }
/* 244 */       throw new IllegalArgumentException("Unknwon dimension " + this.dimensionType);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     private void checkSession() {
/* 250 */       if (!this.lock.a()) {
/* 251 */         throw new IllegalStateException("Lock is no longer valid");
/*     */       }
/*     */     }
/*     */     
/*     */     public WorldNBTStorage b() {
/* 256 */       checkSession();
/* 257 */       return new WorldNBTStorage(this, Convertable.this.f);
/*     */     }
/*     */     
/*     */     public boolean isConvertable() {
/* 261 */       WorldInfo worldinfo = d();
/*     */       
/* 263 */       return (worldinfo != null && worldinfo.k().a() != Convertable.this.g());
/*     */     }
/*     */     
/*     */     public boolean convert(IProgressUpdate iprogressupdate) {
/* 267 */       checkSession();
/* 268 */       return WorldUpgraderIterator.a(this, iprogressupdate);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public WorldInfo d() {
/* 273 */       checkSession();
/* 274 */       return (WorldInfo)Convertable.this.a(this.folder.toFile(), (BiFunction)Convertable.this.a(this.folder.toFile(), false));
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public SaveData a(DynamicOps<NBTBase> dynamicops, DataPackConfiguration datapackconfiguration) {
/* 279 */       checkSession();
/* 280 */       return (SaveData)Convertable.this.a(this.folder.toFile(), (BiFunction)Convertable.b(dynamicops, datapackconfiguration));
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public DataPackConfiguration e() {
/* 285 */       checkSession();
/* 286 */       return (DataPackConfiguration)Convertable.this.a(this.folder.toFile(), (file, datafixer) -> Convertable.b(file, datafixer));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void a(IRegistryCustom iregistrycustom, SaveData savedata) {
/* 292 */       a(iregistrycustom, savedata, (NBTTagCompound)null);
/*     */     }
/*     */     
/*     */     public void a(IRegistryCustom iregistrycustom, SaveData savedata, @Nullable NBTTagCompound nbttagcompound) {
/* 296 */       File file = this.folder.toFile();
/* 297 */       NBTTagCompound nbttagcompound1 = savedata.a(iregistrycustom, nbttagcompound);
/* 298 */       NBTTagCompound nbttagcompound2 = new NBTTagCompound();
/*     */       
/* 300 */       nbttagcompound2.set("Data", nbttagcompound1);
/*     */       
/*     */       try {
/* 303 */         File file1 = File.createTempFile("level", ".dat", file);
/*     */         
/* 305 */         NBTCompressedStreamTools.a(nbttagcompound2, file1);
/* 306 */         File file2 = new File(file, "level.dat_old");
/* 307 */         File file3 = new File(file, "level.dat");
/*     */         
/* 309 */         SystemUtils.a(file3, file1, file2);
/* 310 */       } catch (Exception exception) {
/* 311 */         Convertable.LOGGER.error("Failed to save level {}", file, exception);
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     public File f() {
/* 317 */       checkSession();
/* 318 */       return this.folder.resolve("icon.png").toFile();
/*     */     }
/*     */     
/*     */     public void close() throws IOException {
/* 322 */       this.lock.close();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Convertable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */