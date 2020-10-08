/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.PaperConfig;
/*     */ import com.destroystokyo.paper.exception.ServerInternalException;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.io.Files;
/*     */ import com.mojang.authlib.Agent;
/*     */ import com.mojang.authlib.GameProfile;
/*     */ import com.mojang.authlib.ProfileLookupCallback;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.text.ParseException;
/*     */ import java.util.Collection;
/*     */ import java.util.Date;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.UUID;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class NameReferencingFileConverter {
/*  28 */   private static final Logger LOGGER = LogManager.getLogger();
/*  29 */   public static final File a = new File("banned-ips.txt");
/*  30 */   public static final File b = new File("banned-players.txt");
/*  31 */   public static final File c = new File("ops.txt");
/*  32 */   public static final File d = new File("white-list.txt");
/*     */   
/*     */   static List<String> a(File file, Map<String, String[]> map) throws IOException {
/*  35 */     List<String> list = Files.readLines(file, StandardCharsets.UTF_8);
/*  36 */     Iterator<String> iterator = list.iterator();
/*     */     
/*  38 */     while (iterator.hasNext()) {
/*  39 */       String s = iterator.next();
/*     */       
/*  41 */       s = s.trim();
/*  42 */       if (!s.startsWith("#") && s.length() >= 1) {
/*  43 */         String[] astring = s.split("\\|");
/*     */         
/*  45 */         map.put(astring[0].toLowerCase(Locale.ROOT), astring);
/*     */       } 
/*     */     } 
/*     */     
/*  49 */     return list;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static void a(MinecraftServer minecraftserver, Collection<String> collection, ProfileLookupCallback profilelookupcallback) {
/*  55 */     String[] astring = (String[])collection.stream().filter(s -> !UtilColor.b(s)).toArray(i -> new String[i]);
/*     */ 
/*     */ 
/*     */     
/*  59 */     if (minecraftserver.getOnlineMode() || 
/*  60 */       PaperConfig.isProxyOnlineMode()) {
/*  61 */       minecraftserver.getGameProfileRepository().findProfilesByNames(astring, Agent.MINECRAFT, profilelookupcallback);
/*     */     } else {
/*  63 */       String[] astring1 = astring;
/*  64 */       int i = astring.length;
/*     */       
/*  66 */       for (int j = 0; j < i; j++) {
/*  67 */         String s = astring1[j];
/*  68 */         UUID uuid = EntityHuman.a(new GameProfile((UUID)null, s));
/*  69 */         GameProfile gameprofile = new GameProfile(uuid, s);
/*     */         
/*  71 */         profilelookupcallback.onProfileLookupSucceeded(gameprofile);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(final MinecraftServer minecraftserver) {
/*  78 */     final GameProfileBanList gameprofilebanlist = new GameProfileBanList(PlayerList.b);
/*     */     
/*  80 */     if (b.exists() && b.isFile()) {
/*  81 */       if (gameprofilebanlist.b().exists()) {
/*     */         try {
/*  83 */           gameprofilebanlist.load();
/*  84 */         } catch (IOException ioexception) {
/*  85 */           LOGGER.warn("Could not load existing file {}", gameprofilebanlist.b().getName());
/*     */         } 
/*     */       }
/*     */       
/*     */       try {
/*  90 */         final Map<String, String[]> map = Maps.newHashMap();
/*     */         
/*  92 */         a(b, map);
/*  93 */         ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */             public void onProfileLookupSucceeded(GameProfile gameprofile) {
/*  95 */               minecraftserver.getUserCache().a(gameprofile);
/*  96 */               String[] astring = (String[])map.get(gameprofile.getName().toLowerCase(Locale.ROOT));
/*     */               
/*  98 */               if (astring == null) {
/*  99 */                 NameReferencingFileConverter.LOGGER.warn("Could not convert user banlist entry for {}", gameprofile.getName());
/* 100 */                 throw new NameReferencingFileConverter.FileConversionException("Profile not in the conversionlist");
/*     */               } 
/* 102 */               Date date = (astring.length > 1) ? NameReferencingFileConverter.b(astring[1], (Date)null) : null;
/* 103 */               String s = (astring.length > 2) ? astring[2] : null;
/* 104 */               Date date1 = (astring.length > 3) ? NameReferencingFileConverter.b(astring[3], (Date)null) : null;
/* 105 */               String s1 = (astring.length > 4) ? astring[4] : null;
/*     */               
/* 107 */               gameprofilebanlist.add(new GameProfileBanEntry(gameprofile, date, s, date1, s1));
/*     */             }
/*     */ 
/*     */             
/*     */             public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
/* 112 */               NameReferencingFileConverter.LOGGER.warn("Could not lookup user banlist entry for {}", gameprofile.getName(), exception);
/* 113 */               if (!(exception instanceof com.mojang.authlib.yggdrasil.ProfileNotFoundException)) {
/* 114 */                 throw new NameReferencingFileConverter.FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception);
/*     */               }
/*     */             }
/*     */           };
/*     */         
/* 119 */         a(minecraftserver, map.keySet(), profilelookupcallback);
/* 120 */         gameprofilebanlist.save();
/* 121 */         c(b);
/* 122 */         return true;
/* 123 */       } catch (IOException ioexception1) {
/* 124 */         LOGGER.warn("Could not read old user banlist to convert it!", ioexception1);
/* 125 */         return false;
/* 126 */       } catch (FileConversionException namereferencingfileconverter_fileconversionexception) {
/* 127 */         LOGGER.error("Conversion failed, please try again later", namereferencingfileconverter_fileconversionexception);
/* 128 */         return false;
/*     */       } 
/*     */     } 
/* 131 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean b(MinecraftServer minecraftserver) {
/* 136 */     IpBanList ipbanlist = new IpBanList(PlayerList.c);
/*     */     
/* 138 */     if (a.exists() && a.isFile()) {
/* 139 */       if (ipbanlist.b().exists()) {
/*     */         try {
/* 141 */           ipbanlist.load();
/* 142 */         } catch (IOException ioexception) {
/* 143 */           LOGGER.warn("Could not load existing file {}", ipbanlist.b().getName());
/*     */         } 
/*     */       }
/*     */       
/*     */       try {
/* 148 */         Map<String, String[]> map = Maps.newHashMap();
/*     */         
/* 150 */         a(a, map);
/* 151 */         Iterator<String> iterator = map.keySet().iterator();
/*     */         
/* 153 */         while (iterator.hasNext()) {
/* 154 */           String s = iterator.next();
/* 155 */           String[] astring = map.get(s);
/* 156 */           Date date = (astring.length > 1) ? b(astring[1], (Date)null) : null;
/* 157 */           String s1 = (astring.length > 2) ? astring[2] : null;
/* 158 */           Date date1 = (astring.length > 3) ? b(astring[3], (Date)null) : null;
/* 159 */           String s2 = (astring.length > 4) ? astring[4] : null;
/*     */           
/* 161 */           ipbanlist.add(new IpBanEntry(s, date, s1, date1, s2));
/*     */         } 
/*     */         
/* 164 */         ipbanlist.save();
/* 165 */         c(a);
/* 166 */         return true;
/* 167 */       } catch (IOException ioexception1) {
/* 168 */         LOGGER.warn("Could not parse old ip banlist to convert it!", ioexception1);
/* 169 */         return false;
/*     */       } 
/*     */     } 
/* 172 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean c(final MinecraftServer minecraftserver) {
/* 177 */     final OpList oplist = new OpList(PlayerList.d);
/*     */     
/* 179 */     if (c.exists() && c.isFile()) {
/* 180 */       if (oplist.b().exists()) {
/*     */         try {
/* 182 */           oplist.load();
/* 183 */         } catch (IOException ioexception) {
/* 184 */           LOGGER.warn("Could not load existing file {}", oplist.b().getName());
/*     */         } 
/*     */       }
/*     */       
/*     */       try {
/* 189 */         List<String> list = Files.readLines(c, StandardCharsets.UTF_8);
/* 190 */         ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */             public void onProfileLookupSucceeded(GameProfile gameprofile) {
/* 192 */               minecraftserver.getUserCache().a(gameprofile);
/* 193 */               oplist.add(new OpListEntry(gameprofile, minecraftserver.g(), false));
/*     */             }
/*     */             
/*     */             public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
/* 197 */               NameReferencingFileConverter.LOGGER.warn("Could not lookup oplist entry for {}", gameprofile.getName(), exception);
/* 198 */               if (!(exception instanceof com.mojang.authlib.yggdrasil.ProfileNotFoundException)) {
/* 199 */                 throw new NameReferencingFileConverter.FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception);
/*     */               }
/*     */             }
/*     */           };
/*     */         
/* 204 */         a(minecraftserver, list, profilelookupcallback);
/* 205 */         oplist.save();
/* 206 */         c(c);
/* 207 */         return true;
/* 208 */       } catch (IOException ioexception1) {
/* 209 */         LOGGER.warn("Could not read old oplist to convert it!", ioexception1);
/* 210 */         return false;
/* 211 */       } catch (FileConversionException namereferencingfileconverter_fileconversionexception) {
/* 212 */         LOGGER.error("Conversion failed, please try again later", namereferencingfileconverter_fileconversionexception);
/* 213 */         return false;
/*     */       } 
/*     */     } 
/* 216 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean d(final MinecraftServer minecraftserver) {
/* 221 */     final WhiteList whitelist = new WhiteList(PlayerList.e);
/*     */     
/* 223 */     if (d.exists() && d.isFile()) {
/* 224 */       if (whitelist.b().exists()) {
/*     */         try {
/* 226 */           whitelist.load();
/* 227 */         } catch (IOException ioexception) {
/* 228 */           LOGGER.warn("Could not load existing file {}", whitelist.b().getName());
/*     */         } 
/*     */       }
/*     */       
/*     */       try {
/* 233 */         List<String> list = Files.readLines(d, StandardCharsets.UTF_8);
/* 234 */         ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */             public void onProfileLookupSucceeded(GameProfile gameprofile) {
/* 236 */               minecraftserver.getUserCache().a(gameprofile);
/* 237 */               whitelist.add(new WhiteListEntry(gameprofile));
/*     */             }
/*     */             
/*     */             public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
/* 241 */               NameReferencingFileConverter.LOGGER.warn("Could not lookup user whitelist entry for {}", gameprofile.getName(), exception);
/* 242 */               if (!(exception instanceof com.mojang.authlib.yggdrasil.ProfileNotFoundException)) {
/* 243 */                 throw new NameReferencingFileConverter.FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception);
/*     */               }
/*     */             }
/*     */           };
/*     */         
/* 248 */         a(minecraftserver, list, profilelookupcallback);
/* 249 */         whitelist.save();
/* 250 */         c(d);
/* 251 */         return true;
/* 252 */       } catch (IOException ioexception1) {
/* 253 */         LOGGER.warn("Could not read old whitelist to convert it!", ioexception1);
/* 254 */         return false;
/* 255 */       } catch (FileConversionException namereferencingfileconverter_fileconversionexception) {
/* 256 */         LOGGER.error("Conversion failed, please try again later", namereferencingfileconverter_fileconversionexception);
/* 257 */         return false;
/*     */       } 
/*     */     } 
/* 260 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static UUID a(final MinecraftServer minecraftserver, String s) {
/* 266 */     if (!UtilColor.b(s) && s.length() <= 16) {
/* 267 */       GameProfile gameprofile = minecraftserver.getUserCache().getProfile(s);
/*     */       
/* 269 */       if (gameprofile != null && gameprofile.getId() != null)
/* 270 */         return gameprofile.getId(); 
/* 271 */       if (!minecraftserver.isEmbeddedServer() && minecraftserver.getOnlineMode()) {
/* 272 */         final List<GameProfile> list = Lists.newArrayList();
/* 273 */         ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */             public void onProfileLookupSucceeded(GameProfile gameprofile1) {
/* 275 */               minecraftserver.getUserCache().a(gameprofile1);
/* 276 */               list.add(gameprofile1);
/*     */             }
/*     */             
/*     */             public void onProfileLookupFailed(GameProfile gameprofile1, Exception exception) {
/* 280 */               NameReferencingFileConverter.LOGGER.warn("Could not lookup user whitelist entry for {}", gameprofile1.getName(), exception);
/*     */             }
/*     */           };
/*     */         
/* 284 */         a(minecraftserver, Lists.newArrayList((Object[])new String[] { s }, ), profilelookupcallback);
/* 285 */         return (!list.isEmpty() && ((GameProfile)list.get(0)).getId() != null) ? ((GameProfile)list.get(0)).getId() : null;
/*     */       } 
/* 287 */       return EntityHuman.a(new GameProfile((UUID)null, s));
/*     */     } 
/*     */     
/*     */     try {
/* 291 */       return UUID.fromString(s);
/* 292 */     } catch (IllegalArgumentException illegalargumentexception) {
/* 293 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static boolean a(final DedicatedServer dedicatedserver) {
/* 299 */     final File file = getPlayersFolder(dedicatedserver);
/* 300 */     final File file1 = new File(file.getParentFile(), "playerdata");
/* 301 */     final File file2 = new File(file.getParentFile(), "unknownplayers");
/*     */     
/* 303 */     if (file.exists() && file.isDirectory()) {
/* 304 */       File[] afile = file.listFiles();
/* 305 */       List<String> list = Lists.newArrayList();
/* 306 */       File[] afile1 = afile;
/* 307 */       int i = afile.length;
/*     */       
/* 309 */       for (int j = 0; j < i; j++) {
/* 310 */         File file3 = afile1[j];
/* 311 */         String s = file3.getName();
/*     */         
/* 313 */         if (s.toLowerCase(Locale.ROOT).endsWith(".dat")) {
/* 314 */           String s1 = s.substring(0, s.length() - ".dat".length());
/*     */           
/* 316 */           if (!s1.isEmpty()) {
/* 317 */             list.add(s1);
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/*     */       try {
/* 323 */         final String[] astring = list.<String>toArray(new String[list.size()]);
/* 324 */         ProfileLookupCallback profilelookupcallback = new ProfileLookupCallback() {
/*     */             public void onProfileLookupSucceeded(GameProfile gameprofile) {
/* 326 */               dedicatedserver.getUserCache().a(gameprofile);
/* 327 */               UUID uuid = gameprofile.getId();
/*     */               
/* 329 */               if (uuid == null) {
/* 330 */                 throw new NameReferencingFileConverter.FileConversionException("Missing UUID for user profile " + gameprofile.getName());
/*     */               }
/* 332 */               a(file1, a(gameprofile), uuid.toString());
/*     */             }
/*     */ 
/*     */             
/*     */             public void onProfileLookupFailed(GameProfile gameprofile, Exception exception) {
/* 337 */               NameReferencingFileConverter.LOGGER.warn("Could not lookup user uuid for {}", gameprofile.getName(), exception);
/* 338 */               if (exception instanceof com.mojang.authlib.yggdrasil.ProfileNotFoundException) {
/* 339 */                 String s2 = a(gameprofile);
/*     */                 
/* 341 */                 a(file2, s2, s2);
/*     */               } else {
/* 343 */                 throw new NameReferencingFileConverter.FileConversionException("Could not request user " + gameprofile.getName() + " from backend systems", exception);
/*     */               } 
/*     */             }
/*     */             
/*     */             private void a(File file4, String s2, String s3) {
/* 348 */               File file5 = new File(file, s2 + ".dat");
/* 349 */               File file6 = new File(file4, s3 + ".dat");
/*     */ 
/*     */               
/* 352 */               NBTTagCompound root = null;
/*     */               
/*     */               try {
/* 355 */                 root = NBTCompressedStreamTools.a(new FileInputStream(file5));
/* 356 */               } catch (Exception exception) {
/* 357 */                 exception.printStackTrace();
/* 358 */                 ServerInternalException.reportInternalException(exception);
/*     */               } 
/*     */               
/* 361 */               if (root != null) {
/* 362 */                 if (!root.hasKey("bukkit")) {
/* 363 */                   root.set("bukkit", new NBTTagCompound());
/*     */                 }
/* 365 */                 NBTTagCompound data = root.getCompound("bukkit");
/* 366 */                 data.setString("lastKnownName", s2);
/*     */                 
/*     */                 try {
/* 369 */                   NBTCompressedStreamTools.a(root, new FileOutputStream(file2));
/* 370 */                 } catch (Exception exception) {
/* 371 */                   exception.printStackTrace();
/* 372 */                   ServerInternalException.reportInternalException(exception);
/*     */                 } 
/*     */               } 
/*     */ 
/*     */               
/* 377 */               NameReferencingFileConverter.b(file4);
/* 378 */               if (!file5.renameTo(file6)) {
/* 379 */                 throw new NameReferencingFileConverter.FileConversionException("Could not convert file for " + s2);
/*     */               }
/*     */             }
/*     */             
/*     */             private String a(GameProfile gameprofile) {
/* 384 */               String s2 = null;
/* 385 */               String[] astring1 = astring;
/* 386 */               int k = astring1.length;
/*     */               
/* 388 */               for (int l = 0; l < k; l++) {
/* 389 */                 String s3 = astring1[l];
/*     */                 
/* 391 */                 if (s3 != null && s3.equalsIgnoreCase(gameprofile.getName())) {
/* 392 */                   s2 = s3;
/*     */                   
/*     */                   break;
/*     */                 } 
/*     */               } 
/* 397 */               if (s2 == null) {
/* 398 */                 throw new NameReferencingFileConverter.FileConversionException("Could not find the filename for " + gameprofile.getName() + " anymore");
/*     */               }
/* 400 */               return s2;
/*     */             }
/*     */           };
/*     */ 
/*     */         
/* 405 */         a(dedicatedserver, Lists.newArrayList((Object[])astring), profilelookupcallback);
/* 406 */         return true;
/* 407 */       } catch (FileConversionException namereferencingfileconverter_fileconversionexception) {
/* 408 */         LOGGER.error("Conversion failed, please try again later", namereferencingfileconverter_fileconversionexception);
/* 409 */         return false;
/*     */       } 
/*     */     } 
/* 412 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void b(File file) {
/* 417 */     if (file.exists()) {
/* 418 */       if (!file.isDirectory()) {
/* 419 */         throw new FileConversionException("Can't create directory " + file.getName() + " in world save directory.");
/*     */       }
/* 421 */     } else if (!file.mkdirs()) {
/* 422 */       throw new FileConversionException("Can't create directory " + file.getName() + " in world save directory.");
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean e(MinecraftServer minecraftserver) {
/* 427 */     boolean flag = b();
/*     */     
/* 429 */     flag = (flag && f(minecraftserver));
/* 430 */     return flag;
/*     */   }
/*     */   
/*     */   private static boolean b() {
/* 434 */     boolean flag = false;
/*     */     
/* 436 */     if (b.exists() && b.isFile()) {
/* 437 */       flag = true;
/*     */     }
/*     */     
/* 440 */     boolean flag1 = false;
/*     */     
/* 442 */     if (a.exists() && a.isFile()) {
/* 443 */       flag1 = true;
/*     */     }
/*     */     
/* 446 */     boolean flag2 = false;
/*     */     
/* 448 */     if (c.exists() && c.isFile()) {
/* 449 */       flag2 = true;
/*     */     }
/*     */     
/* 452 */     boolean flag3 = false;
/*     */     
/* 454 */     if (d.exists() && d.isFile()) {
/* 455 */       flag3 = true;
/*     */     }
/*     */     
/* 458 */     if (!flag && !flag1 && !flag2 && !flag3) {
/* 459 */       return true;
/*     */     }
/* 461 */     LOGGER.warn("**** FAILED TO START THE SERVER AFTER ACCOUNT CONVERSION!");
/* 462 */     LOGGER.warn("** please remove the following files and restart the server:");
/* 463 */     if (flag) {
/* 464 */       LOGGER.warn("* {}", b.getName());
/*     */     }
/*     */     
/* 467 */     if (flag1) {
/* 468 */       LOGGER.warn("* {}", a.getName());
/*     */     }
/*     */     
/* 471 */     if (flag2) {
/* 472 */       LOGGER.warn("* {}", c.getName());
/*     */     }
/*     */     
/* 475 */     if (flag3) {
/* 476 */       LOGGER.warn("* {}", d.getName());
/*     */     }
/*     */     
/* 479 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean f(MinecraftServer minecraftserver) {
/* 484 */     File file = getPlayersFolder(minecraftserver);
/*     */     
/* 486 */     if (file.exists() && file.isDirectory() && ((file.list()).length > 0 || !file.delete())) {
/* 487 */       LOGGER.warn("**** DETECTED OLD PLAYER DIRECTORY IN THE WORLD SAVE");
/* 488 */       LOGGER.warn("**** THIS USUALLY HAPPENS WHEN THE AUTOMATIC CONVERSION FAILED IN SOME WAY");
/* 489 */       LOGGER.warn("** please restart the server and if the problem persists, remove the directory '{}'", file.getPath());
/* 490 */       return false;
/*     */     } 
/* 492 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   private static File getPlayersFolder(MinecraftServer minecraftserver) {
/* 497 */     return minecraftserver.a(SavedFile.PLAYERS).toFile();
/*     */   }
/*     */   
/*     */   private static void c(File file) {
/* 501 */     File file1 = new File(file.getName() + ".converted");
/*     */     
/* 503 */     file.renameTo(file1);
/*     */   }
/*     */ 
/*     */   
/*     */   private static Date b(String s, Date date) {
/*     */     Date date1;
/*     */     try {
/* 510 */       date1 = ExpirableListEntry.a.parse(s);
/* 511 */     } catch (ParseException parseexception) {
/* 512 */       date1 = date;
/*     */     } 
/*     */     
/* 515 */     return date1;
/*     */   }
/*     */   
/*     */   static class FileConversionException
/*     */     extends RuntimeException {
/*     */     private FileConversionException(String s, Throwable throwable) {
/* 521 */       super(s, throwable);
/*     */     }
/*     */     
/*     */     private FileConversionException(String s) {
/* 525 */       super(s);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NameReferencingFileConverter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */