/*     */ package org.bukkit.configuration.file;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.IOException;
/*     */ import java.io.Reader;
/*     */ import java.util.Map;
/*     */ import java.util.logging.Level;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.configuration.Configuration;
/*     */ import org.bukkit.configuration.ConfigurationOptions;
/*     */ import org.bukkit.configuration.ConfigurationSection;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.MemoryConfigurationOptions;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.yaml.snakeyaml.DumperOptions;
/*     */ import org.yaml.snakeyaml.LoaderOptions;
/*     */ import org.yaml.snakeyaml.Yaml;
/*     */ import org.yaml.snakeyaml.constructor.BaseConstructor;
/*     */ import org.yaml.snakeyaml.error.YAMLException;
/*     */ import org.yaml.snakeyaml.representer.Representer;
/*     */ 
/*     */ public class YamlConfiguration
/*     */   extends FileConfiguration {
/*     */   protected static final String COMMENT_PREFIX = "# ";
/*     */   protected static final String BLANK_CONFIG = "{}\n";
/*  28 */   private final DumperOptions yamlOptions = new DumperOptions();
/*  29 */   private final LoaderOptions loaderOptions = new LoaderOptions();
/*  30 */   private final Representer yamlRepresenter = new YamlRepresenter();
/*  31 */   private final Yaml yaml = new Yaml((BaseConstructor)new YamlConstructor(), this.yamlRepresenter, this.yamlOptions, this.loaderOptions);
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public String saveToString() {
/*  36 */     this.yamlOptions.setIndent(options().indent());
/*  37 */     this.yamlOptions.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
/*  38 */     this.yamlRepresenter.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
/*     */     
/*  40 */     String header = buildHeader();
/*  41 */     String dump = this.yaml.dump(getValues(false));
/*     */     
/*  43 */     if (dump.equals("{}\n")) {
/*  44 */       dump = "";
/*     */     }
/*     */     
/*  47 */     return header + dump;
/*     */   }
/*     */   
/*     */   public void loadFromString(@NotNull String contents) throws InvalidConfigurationException {
/*     */     Map<?, ?> input;
/*  52 */     Validate.notNull(contents, "Contents cannot be null");
/*     */ 
/*     */     
/*     */     try {
/*  56 */       this.loaderOptions.setMaxAliasesForCollections(2147483647);
/*  57 */       input = (Map<?, ?>)this.yaml.load(contents);
/*  58 */     } catch (YAMLException e) {
/*  59 */       throw new InvalidConfigurationException(e);
/*  60 */     } catch (ClassCastException e) {
/*  61 */       throw new InvalidConfigurationException("Top level is not a Map.");
/*     */     } 
/*     */     
/*  64 */     String header = parseHeader(contents);
/*  65 */     if (header.length() > 0) {
/*  66 */       options().header(header);
/*     */     }
/*     */     
/*  69 */     if (input != null) {
/*  70 */       convertMapsToSections(input, (ConfigurationSection)this);
/*     */     }
/*     */   }
/*     */   
/*     */   protected void convertMapsToSections(@NotNull Map<?, ?> input, @NotNull ConfigurationSection section) {
/*  75 */     for (Map.Entry<?, ?> entry : input.entrySet()) {
/*  76 */       String key = entry.getKey().toString();
/*  77 */       Object value = entry.getValue();
/*     */       
/*  79 */       if (value instanceof Map) {
/*  80 */         convertMapsToSections((Map<?, ?>)value, section.createSection(key)); continue;
/*     */       } 
/*  82 */       section.set(key, value);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected String parseHeader(@NotNull String input) {
/*  89 */     String[] lines = input.split("\r?\n", -1);
/*  90 */     StringBuilder result = new StringBuilder();
/*  91 */     boolean readingHeader = true;
/*  92 */     boolean foundHeader = false;
/*     */     
/*  94 */     for (int i = 0; i < lines.length && readingHeader; i++) {
/*  95 */       String line = lines[i];
/*     */       
/*  97 */       if (line.startsWith("# ")) {
/*  98 */         if (i > 0) {
/*  99 */           result.append("\n");
/*     */         }
/*     */         
/* 102 */         if (line.length() > "# ".length()) {
/* 103 */           result.append(line.substring("# ".length()));
/*     */         }
/*     */         
/* 106 */         foundHeader = true;
/* 107 */       } else if (foundHeader && line.length() == 0) {
/* 108 */         result.append("\n");
/* 109 */       } else if (foundHeader) {
/* 110 */         readingHeader = false;
/*     */       } 
/*     */     } 
/*     */     
/* 114 */     return result.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   protected String buildHeader() {
/* 120 */     String header = options().header();
/*     */     
/* 122 */     if (options().copyHeader()) {
/* 123 */       Configuration def = getDefaults();
/*     */       
/* 125 */       if (def != null && def instanceof FileConfiguration) {
/* 126 */         FileConfiguration filedefaults = (FileConfiguration)def;
/* 127 */         String defaultsHeader = filedefaults.buildHeader();
/*     */         
/* 129 */         if (defaultsHeader != null && defaultsHeader.length() > 0) {
/* 130 */           return defaultsHeader;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 135 */     if (header == null) {
/* 136 */       return "";
/*     */     }
/*     */     
/* 139 */     StringBuilder builder = new StringBuilder();
/* 140 */     String[] lines = header.split("\r?\n", -1);
/* 141 */     boolean startedHeader = false;
/*     */     
/* 143 */     for (int i = lines.length - 1; i >= 0; i--) {
/* 144 */       builder.insert(0, "\n");
/*     */       
/* 146 */       if (startedHeader || lines[i].length() != 0) {
/* 147 */         builder.insert(0, lines[i]);
/* 148 */         builder.insert(0, "# ");
/* 149 */         startedHeader = true;
/*     */       } 
/*     */     } 
/*     */     
/* 153 */     return builder.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public YamlConfigurationOptions options() {
/* 159 */     if (this.options == null) {
/* 160 */       this.options = new YamlConfigurationOptions(this);
/*     */     }
/*     */     
/* 163 */     return (YamlConfigurationOptions)this.options;
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
/*     */   @NotNull
/*     */   public static YamlConfiguration loadConfiguration(@NotNull File file) {
/* 181 */     Validate.notNull(file, "File cannot be null");
/*     */     
/* 183 */     YamlConfiguration config = new YamlConfiguration();
/*     */ 
/*     */     
/* 186 */     try { config.load(file); }
/* 187 */     catch (FileNotFoundException fileNotFoundException) {  }
/* 188 */     catch (IOException ex)
/* 189 */     { Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, ex); }
/* 190 */     catch (InvalidConfigurationException ex)
/* 191 */     { Bukkit.getLogger().log(Level.SEVERE, "Cannot load " + file, (Throwable)ex); }
/*     */ 
/*     */     
/* 194 */     return config;
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
/*     */   @NotNull
/*     */   public static YamlConfiguration loadConfiguration(@NotNull Reader reader) {
/* 210 */     Validate.notNull(reader, "Stream cannot be null");
/*     */     
/* 212 */     YamlConfiguration config = new YamlConfiguration();
/*     */     
/*     */     try {
/* 215 */       config.load(reader);
/* 216 */     } catch (IOException ex) {
/* 217 */       Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration from stream", ex);
/* 218 */     } catch (InvalidConfigurationException ex) {
/* 219 */       Bukkit.getLogger().log(Level.SEVERE, "Cannot load configuration from stream", (Throwable)ex);
/*     */     } 
/*     */     
/* 222 */     return config;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\file\YamlConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */