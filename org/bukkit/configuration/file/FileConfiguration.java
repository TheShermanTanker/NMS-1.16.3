/*     */ package org.bukkit.configuration.file;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.common.io.Files;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileNotFoundException;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.io.OutputStreamWriter;
/*     */ import java.io.Reader;
/*     */ import java.io.Writer;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.bukkit.configuration.Configuration;
/*     */ import org.bukkit.configuration.ConfigurationOptions;
/*     */ import org.bukkit.configuration.InvalidConfigurationException;
/*     */ import org.bukkit.configuration.MemoryConfiguration;
/*     */ import org.bukkit.configuration.MemoryConfigurationOptions;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public abstract class FileConfiguration
/*     */   extends MemoryConfiguration
/*     */ {
/*     */   public FileConfiguration() {}
/*     */   
/*     */   public FileConfiguration(@Nullable Configuration defaults) {
/*  42 */     super(defaults);
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
/*     */   public void save(@NotNull File file) throws IOException {
/*  61 */     Validate.notNull(file, "File cannot be null");
/*     */     
/*  63 */     Files.createParentDirs(file);
/*     */     
/*  65 */     String data = saveToString();
/*     */     
/*  67 */     Writer writer = new OutputStreamWriter(new FileOutputStream(file), Charsets.UTF_8);
/*     */     
/*     */     try {
/*  70 */       writer.write(data);
/*     */     } finally {
/*  72 */       writer.close();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void save(@NotNull String file) throws IOException {
/*  92 */     Validate.notNull(file, "File cannot be null");
/*     */     
/*  94 */     save(new File(file));
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void load(@NotNull File file) throws FileNotFoundException, IOException, InvalidConfigurationException {
/* 124 */     Validate.notNull(file, "File cannot be null");
/*     */     
/* 126 */     FileInputStream stream = new FileInputStream(file);
/*     */     
/* 128 */     load(new InputStreamReader(stream, Charsets.UTF_8));
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
/*     */   public void load(@NotNull Reader reader) throws IOException, InvalidConfigurationException {
/* 145 */     BufferedReader input = (reader instanceof BufferedReader) ? (BufferedReader)reader : new BufferedReader(reader);
/*     */     
/* 147 */     StringBuilder builder = new StringBuilder();
/*     */     
/*     */     try {
/*     */       String line;
/*     */       
/* 152 */       while ((line = input.readLine()) != null) {
/* 153 */         builder.append(line);
/* 154 */         builder.append('\n');
/*     */       } 
/*     */     } finally {
/* 157 */       input.close();
/*     */     } 
/*     */     
/* 160 */     loadFromString(builder.toString());
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
/*     */   public void load(@NotNull String file) throws FileNotFoundException, IOException, InvalidConfigurationException {
/* 182 */     Validate.notNull(file, "File cannot be null");
/*     */     
/* 184 */     load(new File(file));
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
/*     */   public FileConfigurationOptions options() {
/* 220 */     if (this.options == null) {
/* 221 */       this.options = new FileConfigurationOptions(this);
/*     */     }
/*     */     
/* 224 */     return (FileConfigurationOptions)this.options;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public abstract String saveToString();
/*     */   
/*     */   public abstract void loadFromString(@NotNull String paramString) throws InvalidConfigurationException;
/*     */   
/*     */   @NotNull
/*     */   protected abstract String buildHeader();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\file\FileConfiguration.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */