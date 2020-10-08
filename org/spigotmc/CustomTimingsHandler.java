/*     */ package org.spigotmc;
/*     */ 
/*     */ import co.aikar.timings.Timing;
/*     */ import co.aikar.timings.Timings;
/*     */ import co.aikar.timings.TimingsManager;
/*     */ import java.lang.reflect.Method;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.plugin.AuthorNagException;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
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
/*     */ @Deprecated
/*     */ public final class CustomTimingsHandler
/*     */ {
/*     */   private final Timing handler;
/*     */   private static Boolean sunReflectAvailable;
/*     */   private static Method getCallerClass;
/*     */   
/*     */   public CustomTimingsHandler(@NotNull String name) {
/*     */     Timing timing;
/*  58 */     if (sunReflectAvailable == null) {
/*  59 */       String javaVer = System.getProperty("java.version");
/*  60 */       String[] elements = javaVer.split("\\.");
/*     */       
/*  62 */       int major = Integer.parseInt((elements.length >= 2) ? elements[1] : javaVer);
/*  63 */       if (major <= 8) {
/*  64 */         sunReflectAvailable = Boolean.valueOf(true);
/*     */         
/*     */         try {
/*  67 */           Class<?> reflection = Class.forName("sun.reflect.Reflection");
/*  68 */           getCallerClass = reflection.getMethod("getCallerClass", new Class[] { int.class });
/*  69 */         } catch (ClassNotFoundException|NoSuchMethodException classNotFoundException) {}
/*     */       } else {
/*     */         
/*  72 */         sunReflectAvailable = Boolean.valueOf(false);
/*     */       } 
/*     */     } 
/*     */     
/*  76 */     Class calling = null;
/*  77 */     if (sunReflectAvailable.booleanValue()) {
/*     */       try {
/*  79 */         calling = (Class)getCallerClass.invoke(null, new Object[] { Integer.valueOf(2) });
/*  80 */       } catch (IllegalAccessException|java.lang.reflect.InvocationTargetException illegalAccessException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  86 */     Plugin plugin = null;
/*     */     try {
/*  88 */       plugin = TimingsManager.getPluginByClassloader(calling);
/*  89 */     } catch (Exception exception) {}
/*     */     
/*  91 */     (new AuthorNagException("Deprecated use of CustomTimingsHandler. Please Switch to Timings.of ASAP")).printStackTrace();
/*  92 */     if (plugin != null) {
/*  93 */       timing = Timings.of(plugin, "(Deprecated API) " + name);
/*     */     } else {
/*     */       try {
/*  96 */         Method ofSafe = TimingsManager.class.getDeclaredMethod("getHandler", new Class[] { String.class, String.class, Timing.class });
/*  97 */         ofSafe.setAccessible(true);
/*  98 */         timing = (Timing)ofSafe.invoke(null, new Object[] { "Minecraft", "(Deprecated API) " + name, null });
/*  99 */       } catch (Exception e) {
/* 100 */         e.printStackTrace();
/* 101 */         Bukkit.getLogger().log(Level.SEVERE, "This handler could not be registered");
/* 102 */         timing = Timings.NULL_HANDLER;
/*     */       } 
/*     */     } 
/* 105 */     this.handler = timing;
/*     */   }
/*     */   
/* 108 */   public void startTiming() { this.handler.startTiming(); } public void stopTiming() {
/* 109 */     this.handler.stopTiming();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\spigotmc\CustomTimingsHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */