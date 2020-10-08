/*     */ package org.fusesource.hawtjni.runtime;
/*     */ 
/*     */ import java.io.Closeable;
/*     */ import java.io.File;
/*     */ import java.io.FileInputStream;
/*     */ import java.io.FileOutputStream;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStream;
/*     */ import java.lang.reflect.Method;
/*     */ import java.net.URL;
/*     */ import java.security.MessageDigest;
/*     */ import java.security.NoSuchAlgorithmException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Set;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Library
/*     */ {
/*     */   public static final String STRATEGY_PROPERTY = "hawtjni.strategy";
/*     */   public static final String STRATEGY_SHA1 = "sha1";
/*     */   public static final String STRATEGY_TEMP = "temp";
/*  89 */   static final String SLASH = System.getProperty("file.separator");
/*     */   
/*  91 */   static final String STRATEGY = System.getProperty("hawtjni.strategy", 
/*  92 */       "windows".equals(getOperatingSystem()) ? "sha1" : "temp");
/*     */   
/*     */   private final String name;
/*     */   private final String version;
/*     */   private final ClassLoader classLoader;
/*     */   private boolean loaded;
/*     */   private String nativeLibraryPath;
/*     */   private URL nativeLibrarySourceUrl;
/*     */   
/*     */   public Library(String name) {
/* 102 */     this(name, null, null);
/*     */   }
/*     */   
/*     */   public Library(String name, Class<?> clazz) {
/* 106 */     this(name, version(clazz), clazz.getClassLoader());
/*     */   }
/*     */   
/*     */   public Library(String name, String version) {
/* 110 */     this(name, version, null);
/*     */   }
/*     */   
/*     */   public Library(String name, String version, ClassLoader classLoader) {
/* 114 */     if (name == null) {
/* 115 */       throw new IllegalArgumentException("name cannot be null");
/*     */     }
/* 117 */     this.name = name;
/* 118 */     this.version = version;
/* 119 */     this.classLoader = classLoader;
/*     */   }
/*     */   
/*     */   private static String version(Class<?> clazz) {
/*     */     try {
/* 124 */       return clazz.getPackage().getImplementationVersion();
/* 125 */     } catch (Throwable throwable) {
/*     */       
/* 127 */       return null;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String getNativeLibraryPath() {
/* 136 */     return this.nativeLibraryPath;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public URL getNativeLibrarySourceUrl() {
/* 145 */     return this.nativeLibrarySourceUrl;
/*     */   }
/*     */   
/*     */   public static String getOperatingSystem() {
/* 149 */     String name = System.getProperty("os.name").toLowerCase().trim();
/* 150 */     if (name.startsWith("linux")) {
/* 151 */       return "linux";
/*     */     }
/* 153 */     if (name.startsWith("mac os x")) {
/* 154 */       return "osx";
/*     */     }
/* 156 */     if (name.startsWith("win")) {
/* 157 */       return "windows";
/*     */     }
/* 159 */     return name.replaceAll("\\W+", "_");
/*     */   }
/*     */ 
/*     */   
/*     */   public static String getPlatform() {
/* 164 */     return getOperatingSystem() + getBitModel();
/*     */   }
/*     */   
/*     */   public static int getBitModel() {
/* 168 */     String prop = System.getProperty("sun.arch.data.model");
/* 169 */     if (prop == null) {
/* 170 */       prop = System.getProperty("com.ibm.vm.bitmode");
/*     */     }
/* 172 */     if (prop != null) {
/* 173 */       return Integer.parseInt(prop);
/*     */     }
/* 175 */     return -1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public synchronized void load() {
/* 182 */     if (this.loaded) {
/*     */       return;
/*     */     }
/* 185 */     doLoad();
/* 186 */     this.loaded = true;
/*     */   }
/*     */ 
/*     */   
/*     */   private void doLoad() {
/* 191 */     String version = System.getProperty("library." + this.name + ".version");
/* 192 */     if (version == null) {
/* 193 */       version = this.version;
/*     */     }
/* 195 */     ArrayList<Throwable> errors = new ArrayList<>();
/*     */     
/* 197 */     String[] specificDirs = getSpecificSearchDirs();
/* 198 */     String libFilename = map(this.name);
/* 199 */     String versionlibFilename = (version == null) ? null : map(this.name + "-" + version);
/*     */ 
/*     */     
/* 202 */     String customPath = System.getProperty("library." + this.name + ".path");
/* 203 */     if (customPath != null) {
/* 204 */       for (String dir : specificDirs) {
/* 205 */         if (version != null && load(errors, file(new String[] { customPath, dir, versionlibFilename })))
/*     */           return; 
/* 207 */         if (load(errors, file(new String[] { customPath, dir, libFilename }))) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     }
/*     */     
/* 213 */     if (version != null && loadLibrary(errors, this.name + getBitModel() + "-" + version))
/*     */       return; 
/* 215 */     if (version != null && loadLibrary(errors, this.name + "-" + version))
/*     */       return; 
/* 217 */     if (loadLibrary(errors, this.name)) {
/*     */       return;
/*     */     }
/*     */ 
/*     */     
/* 222 */     if (this.classLoader != null) {
/* 223 */       String targetLibName = (version != null) ? versionlibFilename : libFilename;
/* 224 */       for (String dir : specificDirs) {
/* 225 */         if (version != null && extractAndLoad(errors, customPath, dir, versionlibFilename, targetLibName))
/*     */           return; 
/* 227 */         if (extractAndLoad(errors, customPath, dir, libFilename, targetLibName)) {
/*     */           return;
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 233 */     UnsatisfiedLinkError e = new UnsatisfiedLinkError("Could not load library. Reasons: " + errors.toString());
/*     */     try {
/* 235 */       Method method = Throwable.class.getMethod("addSuppressed", new Class[] { Throwable.class });
/* 236 */       for (Throwable t : errors) {
/* 237 */         method.invoke(e, new Object[] { t });
/*     */       } 
/* 239 */     } catch (Throwable throwable) {}
/*     */     
/* 241 */     throw e;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public final String getArchSpecifcResourcePath() {
/* 246 */     return getArchSpecificResourcePath();
/*     */   }
/*     */   public final String getArchSpecificResourcePath() {
/* 249 */     return "META-INF/native/" + getPlatform() + "/" + System.getProperty("os.arch") + "/" + map(this.name);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public final String getOperatingSystemSpecifcResourcePath() {
/* 254 */     return getOperatingSystemSpecificResourcePath();
/*     */   }
/*     */   public final String getOperatingSystemSpecificResourcePath() {
/* 257 */     return getPlatformSpecificResourcePath(getOperatingSystem());
/*     */   }
/*     */   @Deprecated
/*     */   public final String getPlatformSpecifcResourcePath() {
/* 261 */     return getPlatformSpecificResourcePath();
/*     */   }
/*     */   public final String getPlatformSpecificResourcePath() {
/* 264 */     return getPlatformSpecificResourcePath(getPlatform());
/*     */   }
/*     */   @Deprecated
/*     */   public final String getPlatformSpecifcResourcePath(String platform) {
/* 268 */     return getPlatformSpecificResourcePath(platform);
/*     */   }
/*     */   public final String getPlatformSpecificResourcePath(String platform) {
/* 271 */     return "META-INF/native/" + platform + "/" + map(this.name);
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public final String getResorucePath() {
/* 276 */     return getResourcePath();
/*     */   }
/*     */   public final String getResourcePath() {
/* 279 */     return "META-INF/native/" + map(this.name);
/*     */   }
/*     */   
/*     */   public final String getLibraryFileName() {
/* 283 */     return map(this.name);
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
/*     */   public final String[] getSpecificSearchDirs() {
/* 297 */     return new String[] {
/* 298 */         getPlatform() + "/" + System.getProperty("os.arch"), 
/* 299 */         getPlatform(), 
/* 300 */         getOperatingSystem(), "."
/*     */       };
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean extractAndLoad(ArrayList<Throwable> errors, String customPath, String dir, String libName, String targetLibName) {
/* 306 */     String resourcePath = "META-INF/native/" + ((dir == null) ? "" : (dir + '/')) + libName;
/* 307 */     URL resource = this.classLoader.getResource(resourcePath);
/* 308 */     if (resource != null) {
/*     */       
/* 310 */       int idx = targetLibName.lastIndexOf('.');
/* 311 */       String prefix = targetLibName.substring(0, idx) + "-";
/* 312 */       String suffix = targetLibName.substring(idx);
/*     */ 
/*     */ 
/*     */ 
/*     */       
/* 317 */       for (File path : Arrays.<File>asList(new File[] { (customPath != null) ? 
/* 318 */             file(new String[] { customPath }) : null, 
/* 319 */             file(new String[] { System.getProperty("java.io.tmpdir")
/* 320 */               }), file(new String[] { System.getProperty("user.home"), ".hawtjni", this.name }) })) {
/* 321 */         if (path != null) {
/*     */           File target;
/*     */           
/* 324 */           if ("sha1".equals(STRATEGY)) {
/* 325 */             target = extractSha1(errors, resource, prefix, suffix, path);
/*     */           } else {
/* 327 */             target = extractTemp(errors, resource, prefix, suffix, path);
/*     */           } 
/* 329 */           if (target != null && 
/* 330 */             load(errors, target)) {
/* 331 */             this.nativeLibrarySourceUrl = resource;
/* 332 */             return true;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 338 */     return false;
/*     */   }
/*     */   
/*     */   private File file(String... paths) {
/* 342 */     File rc = null;
/* 343 */     for (String path : paths) {
/* 344 */       if (rc == null) {
/* 345 */         rc = new File(path);
/* 346 */       } else if (path != null) {
/* 347 */         rc = new File(rc, path);
/*     */       } 
/*     */     } 
/* 350 */     return rc;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private String map(String libName) {
/* 358 */     libName = System.mapLibraryName(libName);
/* 359 */     String ext = ".dylib";
/* 360 */     if (libName.endsWith(ext)) {
/* 361 */       libName = libName.substring(0, libName.length() - ext.length()) + ".jnilib";
/*     */     }
/* 363 */     return libName;
/*     */   }
/*     */   
/*     */   private File extractSha1(ArrayList<Throwable> errors, URL source, String prefix, String suffix, File directory) {
/* 367 */     File target = null;
/* 368 */     directory = directory.getAbsoluteFile();
/* 369 */     if (!directory.exists() && 
/* 370 */       !directory.mkdirs()) {
/* 371 */       errors.add(new IOException("Unable to create directory: " + directory));
/* 372 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 376 */       String sha1 = computeSha1(source.openStream());
/* 377 */       String sha1f = "";
/* 378 */       target = new File(directory, prefix + sha1 + suffix);
/*     */       
/* 380 */       if (target.isFile() && target.canRead()) {
/* 381 */         sha1f = computeSha1(new FileInputStream(target));
/*     */       }
/* 383 */       if (sha1f.equals(sha1)) {
/* 384 */         return target;
/*     */       }
/*     */       
/* 387 */       FileOutputStream os = null;
/* 388 */       InputStream is = null;
/*     */       try {
/* 390 */         is = source.openStream();
/* 391 */         if (is != null) {
/* 392 */           byte[] buffer = new byte[4096];
/* 393 */           os = new FileOutputStream(target);
/*     */           int read;
/* 395 */           while ((read = is.read(buffer)) != -1) {
/* 396 */             os.write(buffer, 0, read);
/*     */           }
/* 398 */           chmod755(target);
/*     */         } 
/* 400 */         return target;
/*     */       } finally {
/* 402 */         close(os);
/* 403 */         close(is);
/*     */       } 
/* 405 */     } catch (Throwable e) {
/*     */       IOException io;
/* 407 */       if (target != null) {
/* 408 */         target.delete();
/* 409 */         io = new IOException("Unable to extract library from " + source + " to " + target);
/*     */       } else {
/* 411 */         io = new IOException("Unable to create temporary file in " + directory);
/*     */       } 
/* 413 */       io.initCause(e);
/* 414 */       errors.add(io);
/*     */       
/* 416 */       return null;
/*     */     } 
/*     */   }
/*     */   private String computeSha1(InputStream is) throws NoSuchAlgorithmException, IOException {
/*     */     String sha1;
/*     */     try {
/* 422 */       MessageDigest mDigest = MessageDigest.getInstance("SHA1");
/*     */       
/* 424 */       byte[] buffer = new byte[4096]; int read;
/* 425 */       while ((read = is.read(buffer)) != -1) {
/* 426 */         mDigest.update(buffer, 0, read);
/*     */       }
/* 428 */       byte[] result = mDigest.digest();
/* 429 */       StringBuilder sb = new StringBuilder();
/* 430 */       for (byte b : result) {
/* 431 */         sb.append(Integer.toString((b & 0xFF) + 256, 16).substring(1));
/*     */       }
/* 433 */       sha1 = sb.toString();
/*     */     } finally {
/* 435 */       close(is);
/*     */     } 
/* 437 */     return sha1;
/*     */   }
/*     */   
/*     */   private File extractTemp(ArrayList<Throwable> errors, URL source, String prefix, String suffix, File directory) {
/* 441 */     File target = null;
/* 442 */     directory = directory.getAbsoluteFile();
/* 443 */     if (!directory.exists() && 
/* 444 */       !directory.mkdirs()) {
/* 445 */       errors.add(new IOException("Unable to create directory: " + directory));
/* 446 */       return null;
/*     */     } 
/*     */     
/*     */     try {
/* 450 */       FileOutputStream os = null;
/* 451 */       InputStream is = null;
/*     */       try {
/* 453 */         target = File.createTempFile(prefix, suffix, directory);
/* 454 */         is = source.openStream();
/* 455 */         if (is != null) {
/* 456 */           byte[] buffer = new byte[4096];
/* 457 */           os = new FileOutputStream(target);
/*     */           int read;
/* 459 */           while ((read = is.read(buffer)) != -1) {
/* 460 */             os.write(buffer, 0, read);
/*     */           }
/* 462 */           chmod755(target);
/*     */         } 
/* 464 */         target.deleteOnExit();
/* 465 */         return target;
/*     */       } finally {
/* 467 */         close(os);
/* 468 */         close(is);
/*     */       } 
/* 470 */     } catch (Throwable e) {
/*     */       IOException io;
/* 472 */       if (target != null) {
/* 473 */         target.delete();
/* 474 */         io = new IOException("Unable to extract library from " + source + " to " + target);
/*     */       } else {
/* 476 */         io = new IOException("Unable to create temporary file in " + directory);
/*     */       } 
/* 478 */       io.initCause(e);
/* 479 */       errors.add(io);
/*     */       
/* 481 */       return null;
/*     */     } 
/*     */   }
/*     */   private static void close(Closeable file) {
/* 485 */     if (file != null) {
/*     */       try {
/* 487 */         file.close();
/* 488 */       } catch (Exception exception) {}
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private void chmod755(File file) {
/* 494 */     if (getPlatform().startsWith("windows")) {
/*     */       return;
/*     */     }
/*     */     try {
/* 498 */       ClassLoader classLoader = getClass().getClassLoader();
/*     */       
/* 500 */       Class<?> posixFilePermissionsClass = classLoader.loadClass("java.nio.file.attribute.PosixFilePermissions");
/*     */       
/* 502 */       Method fromStringMethod = posixFilePermissionsClass.getMethod("fromString", new Class[] { String.class });
/* 503 */       Object permissionSet = fromStringMethod.invoke(null, new Object[] { "rwxr-xr-x" });
/*     */       
/* 505 */       Object path = file.getClass().getMethod("toPath", new Class[0]).invoke(file, new Object[0]);
/*     */       
/* 507 */       Class<?> pathClass = classLoader.loadClass("java.nio.file.Path");
/* 508 */       Class<?> filesClass = classLoader.loadClass("java.nio.file.Files");
/* 509 */       Method setPosixFilePermissionsMethod = filesClass.getMethod("setPosixFilePermissions", new Class[] { pathClass, Set.class });
/* 510 */       setPosixFilePermissionsMethod.invoke(null, new Object[] { path, permissionSet });
/* 511 */     } catch (Throwable ignored) {
/*     */       
/*     */       try {
/* 514 */         Runtime.getRuntime().exec(new String[] { "chmod", "755", file.getCanonicalPath() }).waitFor();
/* 515 */       } catch (Throwable throwable) {}
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean load(ArrayList<Throwable> errors, File lib) {
/*     */     try {
/* 522 */       System.load(lib.getPath());
/* 523 */       this.nativeLibraryPath = lib.getPath();
/* 524 */       return true;
/* 525 */     } catch (UnsatisfiedLinkError e) {
/* 526 */       LinkageError le = new LinkageError("Unable to load library from " + lib);
/* 527 */       le.initCause(e);
/* 528 */       errors.add(le);
/*     */       
/* 530 */       return false;
/*     */     } 
/*     */   }
/*     */   private boolean loadLibrary(ArrayList<Throwable> errors, String lib) {
/*     */     try {
/* 535 */       System.loadLibrary(lib);
/* 536 */       this.nativeLibraryPath = "java.library.path,sun.boot.library.pathlib:" + lib;
/* 537 */       return true;
/* 538 */     } catch (UnsatisfiedLinkError e) {
/* 539 */       LinkageError le = new LinkageError("Unable to load library " + lib);
/* 540 */       le.initCause(e);
/* 541 */       errors.add(le);
/*     */       
/* 543 */       return false;
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\fusesource\hawtjni\runtime\Library.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */