/*     */ package com.destroystokyo.paper;
/*     */ 
/*     */ import com.google.common.base.MoreObjects;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.BufferedWriter;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.OpenOption;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.nio.file.StandardOpenOption;
/*     */ import java.util.Objects;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ 
/*     */ public enum VersionHistoryManager {
/*  23 */   INSTANCE;
/*     */   
/*  25 */   private final Gson gson = new Gson();
/*     */   
/*  27 */   private final Logger logger = Bukkit.getLogger();
/*     */   
/*  29 */   private VersionData currentData = null;
/*     */   
/*     */   VersionHistoryManager() {
/*  32 */     Path path = Paths.get("version_history.json", new String[0]);
/*     */     
/*  34 */     if (Files.exists(path, new java.nio.file.LinkOption[0])) {
/*     */       
/*  36 */       if (!Files.isRegularFile(path, new java.nio.file.LinkOption[0])) {
/*  37 */         if (Files.isDirectory(path, new java.nio.file.LinkOption[0])) {
/*  38 */           this.logger.severe(path + " is a directory, cannot be used for version history");
/*     */         } else {
/*  40 */           this.logger.severe(path + " is not a regular file, cannot be used for version history");
/*     */         } 
/*     */         
/*     */         return;
/*     */       } 
/*     */       
/*  46 */       try { BufferedReader reader = Files.newBufferedReader(path, StandardCharsets.UTF_8); 
/*  47 */         try { this.currentData = (VersionData)this.gson.fromJson(reader, VersionData.class);
/*  48 */           if (reader != null) reader.close();  } catch (Throwable throwable) { if (reader != null) try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/*  49 */       { this.logger.log(Level.SEVERE, "Failed to read version history file '" + path + "'", e);
/*     */         return; }
/*  51 */       catch (JsonSyntaxException e)
/*  52 */       { this.logger.log(Level.SEVERE, "Invalid json syntax for file '" + path + "'", (Throwable)e);
/*     */         
/*     */         return; }
/*     */       
/*  56 */       String version = Bukkit.getVersion();
/*  57 */       if (version == null) {
/*  58 */         this.logger.severe("Failed to retrieve current version");
/*     */         
/*     */         return;
/*     */       } 
/*  62 */       if (!version.equals(this.currentData.getCurrentVersion())) {
/*     */         
/*  64 */         this.currentData.setOldVersion(this.currentData.getCurrentVersion());
/*  65 */         this.currentData.setCurrentVersion(version);
/*  66 */         writeFile(path);
/*     */       } 
/*     */     } else {
/*     */       
/*  70 */       this.currentData = new VersionData();
/*     */       
/*  72 */       this.currentData.setCurrentVersion(Bukkit.getVersion());
/*  73 */       writeFile(path);
/*     */     } 
/*     */   }
/*     */   private void writeFile(@Nonnull Path path) {
/*     */     
/*  78 */     try { BufferedWriter writer = Files.newBufferedWriter(path, StandardCharsets.UTF_8, new OpenOption[] { StandardOpenOption.WRITE, StandardOpenOption.CREATE, StandardOpenOption.TRUNCATE_EXISTING });
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*  85 */       try { this.gson.toJson(this.currentData, writer);
/*  86 */         if (writer != null) writer.close();  } catch (Throwable throwable) { if (writer != null) try { writer.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (IOException e)
/*  87 */     { this.logger.log(Level.SEVERE, "Failed to write to version history file", e); }
/*     */   
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public VersionData getVersionData() {
/*  93 */     return this.currentData;
/*     */   }
/*     */   
/*     */   public static class VersionData
/*     */   {
/*     */     private String oldVersion;
/*     */     private String currentVersion;
/*     */     
/*     */     @Nullable
/*     */     public String getOldVersion() {
/* 103 */       return this.oldVersion;
/*     */     }
/*     */     
/*     */     public void setOldVersion(@Nullable String oldVersion) {
/* 107 */       this.oldVersion = oldVersion;
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     public String getCurrentVersion() {
/* 112 */       return this.currentVersion;
/*     */     }
/*     */     
/*     */     public void setCurrentVersion(@Nullable String currentVersion) {
/* 116 */       this.currentVersion = currentVersion;
/*     */     }
/*     */ 
/*     */     
/*     */     public String toString() {
/* 121 */       return MoreObjects.toStringHelper(this)
/* 122 */         .add("oldVersion", this.oldVersion)
/* 123 */         .add("currentVersion", this.currentVersion)
/* 124 */         .toString();
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(@Nullable Object o) {
/* 129 */       if (this == o) {
/* 130 */         return true;
/*     */       }
/* 132 */       if (o == null || getClass() != o.getClass()) {
/* 133 */         return false;
/*     */       }
/* 135 */       VersionData versionData = (VersionData)o;
/* 136 */       return (Objects.equals(this.oldVersion, versionData.oldVersion) && 
/* 137 */         Objects.equals(this.currentVersion, versionData.currentVersion));
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 142 */       return Objects.hash(new Object[] { this.oldVersion, this.currentVersion });
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\VersionHistoryManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */