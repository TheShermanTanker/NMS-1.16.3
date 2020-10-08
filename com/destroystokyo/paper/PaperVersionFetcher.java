/*     */ package com.destroystokyo.paper;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSyntaxException;
/*     */ import java.io.BufferedReader;
/*     */ import java.io.IOException;
/*     */ import java.net.HttpURLConnection;
/*     */ import java.net.URL;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.annotation.Nonnull;
/*     */ import javax.annotation.Nullable;
/*     */ import org.bukkit.Bukkit;
/*     */ 
/*     */ public class PaperVersionFetcher implements VersionFetcher {
/*  15 */   private static final Pattern VER_PATTERN = Pattern.compile("^([0-9\\.]*)\\-.*R");
/*     */   private static final String GITHUB_BRANCH_NAME = "master";
/*     */   @Nullable
/*     */   private static String mcVer;
/*     */   
/*     */   public long getCacheTime() {
/*  21 */     return 720000L;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nonnull
/*     */   public String getVersionMessage(@Nonnull String serverVersion) {
/*  27 */     String[] parts = serverVersion.substring("git-Tuinity-".length()).split("[-\\s]");
/*  28 */     String updateMessage = getUpdateStatusMessage("Spottedleaf/Tuinity", "master", parts[0]);
/*  29 */     String history = getHistory();
/*     */     
/*  31 */     return (history != null) ? (history + "\n" + updateMessage) : updateMessage;
/*     */   }
/*     */   @Nullable
/*     */   private static String getMinecraftVersion() {
/*  35 */     if (mcVer == null) {
/*  36 */       Matcher matcher = VER_PATTERN.matcher(Bukkit.getBukkitVersion());
/*  37 */       if (matcher.find()) {
/*  38 */         String result = matcher.group();
/*  39 */         mcVer = result.substring(0, result.length() - 2);
/*     */       } else {
/*  41 */         Bukkit.getLogger().warning("Unable to match version to pattern! Report to PaperMC!");
/*  42 */         Bukkit.getLogger().warning("Pattern: " + VER_PATTERN.toString());
/*  43 */         Bukkit.getLogger().warning("Version: " + Bukkit.getBukkitVersion());
/*     */       } 
/*     */     } 
/*     */     
/*  47 */     return mcVer;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static String getUpdateStatusMessage(@Nonnull String repo, @Nonnull String branch, @Nonnull String versionInfo) {
/*  53 */     versionInfo = versionInfo.replace("\"", "");
/*  54 */     int distance = fetchDistanceFromGitHub(repo, branch, versionInfo);
/*     */ 
/*     */     
/*  57 */     switch (distance) {
/*     */       case -1:
/*  59 */         return "Error obtaining version information";
/*     */       case 0:
/*  61 */         return "You are running the latest version";
/*     */       case -2:
/*  63 */         return "Unknown version";
/*     */     } 
/*  65 */     return "You are " + distance + " version(s) behind";
/*     */   }
/*     */ 
/*     */   
/*     */   private static int fetchDistanceFromSiteApi(int jenkinsBuild, @Nullable String siteApiVersion) {
/*  70 */     if (siteApiVersion == null) return -1;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  75 */     try { BufferedReader reader = Resources.asCharSource(new URL("https://papermc.io/api/v1/paper/" + siteApiVersion + "/latest"), Charsets.UTF_8).openBufferedStream(); 
/*  76 */       try { JsonObject json = (JsonObject)(new Gson()).fromJson(reader, JsonObject.class);
/*  77 */         int latest = json.get("build").getAsInt();
/*  78 */         int i = latest - jenkinsBuild;
/*  79 */         if (reader != null) reader.close();  return i; } catch (Throwable throwable) { if (reader != null) try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } catch (JsonSyntaxException ex)
/*  80 */     { ex.printStackTrace();
/*  81 */       return -1; }
/*     */     
/*  83 */     catch (IOException e)
/*  84 */     { e.printStackTrace();
/*  85 */       return -1; }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   private static int fetchDistanceFromGitHub(@Nonnull String repo, @Nonnull String branch, @Nonnull String hash) {
/*     */     try {
/*  92 */       HttpURLConnection connection = (HttpURLConnection)(new URL("https://api.github.com/repos/" + repo + "/compare/" + branch + "..." + hash)).openConnection();
/*  93 */       connection.connect();
/*  94 */       if (connection.getResponseCode() == 404) return -2;  
/*  95 */       try { BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), Charsets.UTF_8)); 
/*  96 */         try { JsonObject obj = (JsonObject)(new Gson()).fromJson(reader, JsonObject.class);
/*  97 */           String status = obj.get("status").getAsString();
/*  98 */           switch (status)
/*     */           { case "identical":
/* 100 */               i = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */               
/* 106 */               reader.close(); return i;case "behind": i = obj.get("behind_by").getAsInt(); reader.close(); return i; }  int i = -1; reader.close(); return i; } catch (Throwable throwable) { try { reader.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }  throw throwable; }  } catch (JsonSyntaxException|NumberFormatException e)
/* 107 */       { e.printStackTrace();
/* 108 */         return -1; }
/*     */     
/* 110 */     } catch (IOException e) {
/* 111 */       e.printStackTrace();
/* 112 */       return -1;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private String getHistory() {
/* 118 */     VersionHistoryManager.VersionData data = VersionHistoryManager.INSTANCE.getVersionData();
/* 119 */     if (data == null) {
/* 120 */       return null;
/*     */     }
/*     */     
/* 123 */     String oldVersion = data.getOldVersion();
/* 124 */     if (oldVersion == null) {
/* 125 */       return null;
/*     */     }
/*     */     
/* 128 */     return "Previous version: " + oldVersion;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\PaperVersionFetcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */