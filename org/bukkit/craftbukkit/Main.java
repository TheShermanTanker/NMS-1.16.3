/*     */ package org.bukkit.craftbukkit;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.OperatingSystemMXBean;
/*     */ import java.lang.management.RuntimeMXBean;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Arrays;
/*     */ import java.util.Calendar;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import joptsimple.OptionException;
/*     */ import joptsimple.OptionParser;
/*     */ import joptsimple.OptionSet;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ 
/*     */ public class Main
/*     */ {
/*     */   public static void main(String[] args) {
/*  23 */     if (System.getProperty("jdk.nio.maxCachedBufferSize") == null) System.setProperty("jdk.nio.maxCachedBufferSize", "262144"); 
/*  24 */     OptionParser parser = new OptionParser()
/*     */       {
/*     */       
/*     */       };
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
/* 159 */     OptionSet options = null;
/*     */ 
/*     */     
/* 162 */     tryPreloadClass("com.destroystokyo.paper.log.LogFullPolicy");
/* 163 */     tryPreloadClass("org.apache.logging.log4j.core.Core");
/* 164 */     tryPreloadClass("org.apache.logging.log4j.core.Appender");
/* 165 */     tryPreloadClass("org.apache.logging.log4j.core.ContextDataInjector");
/* 166 */     tryPreloadClass("org.apache.logging.log4j.core.Filter");
/* 167 */     tryPreloadClass("org.apache.logging.log4j.core.ErrorHandler");
/* 168 */     tryPreloadClass("org.apache.logging.log4j.core.LogEvent");
/* 169 */     tryPreloadClass("org.apache.logging.log4j.core.Logger");
/* 170 */     tryPreloadClass("org.apache.logging.log4j.core.LoggerContext");
/* 171 */     tryPreloadClass("org.apache.logging.log4j.core.LogEventListener");
/* 172 */     tryPreloadClass("org.apache.logging.log4j.core.AbstractLogEvent");
/* 173 */     tryPreloadClass("org.apache.logging.log4j.message.AsynchronouslyFormattable");
/* 174 */     tryPreloadClass("org.apache.logging.log4j.message.FormattedMessage");
/* 175 */     tryPreloadClass("org.apache.logging.log4j.message.ParameterizedMessage");
/* 176 */     tryPreloadClass("org.apache.logging.log4j.message.Message");
/* 177 */     tryPreloadClass("org.apache.logging.log4j.message.MessageFactory");
/* 178 */     tryPreloadClass("org.apache.logging.log4j.message.TimestampMessage");
/* 179 */     tryPreloadClass("org.apache.logging.log4j.message.SimpleMessage");
/* 180 */     tryPreloadClass("org.apache.logging.log4j.core.async.AsyncLogger");
/* 181 */     tryPreloadClass("org.apache.logging.log4j.core.async.AsyncLoggerContext");
/* 182 */     tryPreloadClass("org.apache.logging.log4j.core.async.AsyncQueueFullPolicy");
/* 183 */     tryPreloadClass("org.apache.logging.log4j.core.async.AsyncLoggerDisruptor");
/* 184 */     tryPreloadClass("org.apache.logging.log4j.core.async.RingBufferLogEvent");
/* 185 */     tryPreloadClass("org.apache.logging.log4j.core.async.DisruptorUtil");
/* 186 */     tryPreloadClass("org.apache.logging.log4j.core.async.RingBufferLogEventHandler");
/* 187 */     tryPreloadClass("org.apache.logging.log4j.core.impl.ThrowableProxy");
/* 188 */     tryPreloadClass("org.apache.logging.log4j.core.impl.ThrowableProxy$CacheEntry");
/* 189 */     tryPreloadClass("org.apache.logging.log4j.core.impl.ExtendedClassInfo");
/* 190 */     tryPreloadClass("org.apache.logging.log4j.core.impl.ExtendedStackTraceElement");
/*     */     
/*     */     try {
/* 193 */       options = parser.parse(args);
/* 194 */     } catch (OptionException ex) {
/* 195 */       Logger.getLogger(Main.class.getName()).log(Level.SEVERE, ex.getLocalizedMessage());
/*     */     } 
/*     */     
/* 198 */     if (options == null || options.has("?")) {
/*     */       try {
/* 200 */         parser.printHelpOn(System.out);
/* 201 */       } catch (IOException ex) {
/* 202 */         Logger.getLogger(Main.class.getName()).log(Level.SEVERE, (String)null, ex);
/*     */       } 
/* 204 */     } else if (options.has("v")) {
/* 205 */       System.out.println(CraftServer.class.getPackage().getImplementationVersion());
/*     */     } else {
/*     */       
/* 208 */       String path = (new File(".")).getAbsolutePath();
/* 209 */       if (path.contains("!") || path.contains("+")) {
/* 210 */         System.err.println("Cannot run server in a directory with ! or + in the pathname. Please rename the affected folders and try again.");
/*     */         
/*     */         return;
/*     */       } 
/* 214 */       float javaVersion = Float.parseFloat(System.getProperty("java.class.version"));
/* 215 */       if (javaVersion > 59.0D) {
/* 216 */         System.err.println("Unsupported Java detected (" + javaVersion + "). Only up to Java 15 is supported.");
/* 217 */         if (!Boolean.getBoolean("Paper.IgnoreJavaVersion")) {
/*     */           return;
/*     */         }
/*     */       } 
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
/*     */       try {
/* 242 */         if (options.has("nojline")) {
/* 243 */           System.setProperty("terminal.jline", "false");
/* 244 */           useJline = false;
/*     */         } 
/*     */ 
/*     */         
/* 248 */         if (options.has("noconsole")) {
/* 249 */           useConsole = false;
/* 250 */           useJline = false;
/* 251 */           System.setProperty("terminal.jline", "false");
/*     */         } 
/*     */         
/* 254 */         if (Main.class.getPackage().getImplementationVendor() != null && System.getProperty("IReallyKnowWhatIAmDoingISwear") == null) {
/* 255 */           Date buildDate = (new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'")).parse(Main.class.getPackage().getImplementationVendor());
/*     */           
/* 257 */           Calendar deadline = Calendar.getInstance();
/* 258 */           deadline.add(6, -7);
/* 259 */           if (buildDate.before(deadline.getTime())) {
/*     */             
/* 261 */             System.err.println("*** Warning, you've not updated in a while! ***");
/* 262 */             System.err.println("*** Please download a new build ***");
/*     */           } 
/*     */         } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 270 */         RuntimeMXBean runtimeMX = ManagementFactory.getRuntimeMXBean();
/* 271 */         OperatingSystemMXBean osMX = ManagementFactory.getOperatingSystemMXBean();
/* 272 */         if (runtimeMX != null && osMX != null) {
/* 273 */           String javaInfo = "Java " + runtimeMX.getSpecVersion() + " (" + runtimeMX.getVmName() + " " + runtimeMX.getVmVersion() + ")";
/* 274 */           String osInfo = "Host: " + osMX.getName() + " " + osMX.getVersion() + " (" + osMX.getArch() + ")";
/*     */           
/* 276 */           System.out.println("System Info: " + javaInfo + " " + osInfo);
/*     */         } else {
/* 278 */           System.out.println("Unable to read system info");
/*     */         } 
/*     */         
/* 281 */         System.setProperty("library.jansi.version", "Paper");
/* 282 */         System.out.println("Loading libraries, please wait...");
/* 283 */         net.minecraft.server.v1_16_R2.Main.main(options);
/* 284 */       } catch (Throwable t) {
/* 285 */         t.printStackTrace();
/*     */       } 
/*     */ 
/*     */ 
/*     */       
/* 290 */       tryPreloadClass("com.destroystokyo.paper.util.SneakyThrow");
/* 291 */       tryPreloadClass("com.google.common.collect.Iterators$PeekingImpl");
/* 292 */       tryPreloadClass("com.google.common.collect.MapMakerInternalMap$Values");
/* 293 */       tryPreloadClass("com.google.common.collect.MapMakerInternalMap$ValueIterator");
/* 294 */       tryPreloadClass("com.google.common.collect.MapMakerInternalMap$WriteThroughEntry");
/* 295 */       tryPreloadClass("com.google.common.collect.Iterables");
/* 296 */       for (int i = 1; i <= 15; i++) {
/* 297 */         tryPreloadClass("com.google.common.collect.Iterables$" + i, false);
/*     */       }
/* 299 */       tryPreloadClass("org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableBoolean");
/* 300 */       tryPreloadClass("org.bukkit.craftbukkit.libs.org.apache.commons.lang3.mutable.MutableInt");
/* 301 */       tryPreloadClass("org.jline.terminal.impl.MouseSupport");
/* 302 */       tryPreloadClass("org.jline.terminal.impl.MouseSupport$1");
/* 303 */       tryPreloadClass("org.jline.terminal.Terminal$MouseTracking");
/* 304 */       tryPreloadClass("co.aikar.timings.TimingHistory");
/* 305 */       tryPreloadClass("co.aikar.timings.TimingHistory$MinuteReport");
/* 306 */       tryPreloadClass("io.netty.channel.AbstractChannelHandlerContext");
/* 307 */       tryPreloadClass("io.netty.channel.AbstractChannelHandlerContext$11");
/* 308 */       tryPreloadClass("io.netty.channel.AbstractChannelHandlerContext$12");
/* 309 */       tryPreloadClass("io.netty.channel.AbstractChannelHandlerContext$13");
/* 310 */       tryPreloadClass("io.netty.channel.AbstractChannel$AbstractUnsafe$8");
/* 311 */       tryPreloadClass("io.netty.util.concurrent.DefaultPromise");
/* 312 */       tryPreloadClass("io.netty.util.concurrent.DefaultPromise$1");
/* 313 */       tryPreloadClass("io.netty.util.internal.PromiseNotificationUtil");
/* 314 */       tryPreloadClass("io.netty.util.internal.SystemPropertyUtil");
/* 315 */       tryPreloadClass("org.bukkit.craftbukkit.v1_16_R2.scheduler.CraftScheduler");
/* 316 */       tryPreloadClass("org.bukkit.craftbukkit.v1_16_R2.scheduler.CraftScheduler$1");
/* 317 */       tryPreloadClass("org.bukkit.craftbukkit.v1_16_R2.scheduler.CraftScheduler$2");
/* 318 */       tryPreloadClass("org.bukkit.craftbukkit.v1_16_R2.scheduler.CraftScheduler$3");
/* 319 */       tryPreloadClass("org.bukkit.craftbukkit.v1_16_R2.scheduler.CraftScheduler$4");
/* 320 */       tryPreloadClass("org.slf4j.helpers.MessageFormatter");
/* 321 */       tryPreloadClass("org.slf4j.helpers.FormattingTuple");
/* 322 */       tryPreloadClass("org.slf4j.helpers.BasicMarker");
/* 323 */       tryPreloadClass("org.slf4j.helpers.Util");
/* 324 */       tryPreloadClass("com.destroystokyo.paper.event.player.PlayerConnectionCloseEvent");
/* 325 */       tryPreloadClass("com.destroystokyo.paper.event.entity.EntityRemoveFromWorldEvent");
/*     */       
/* 327 */       tryPreloadClass("net.minecraft.server.v1_16_R2.LightEngineLayerEventListener$Void");
/* 328 */       tryPreloadClass("net.minecraft.server.v1_16_R2.LightEngineLayerEventListener");
/* 329 */       tryPreloadClass("net.minecraft.server.v1_16_R2.ExceptionSuppressor");
/*     */     } 
/*     */   }
/*     */   public static boolean useJline = true;
/*     */   public static boolean useConsole = true;
/*     */   
/*     */   private static void tryPreloadClass(String className) {
/* 336 */     tryPreloadClass(className, true);
/*     */   }
/*     */   private static void tryPreloadClass(String className, boolean printError) {
/*     */     try {
/* 340 */       Class.forName(className);
/* 341 */     } catch (ClassNotFoundException e) {
/* 342 */       if (printError) System.err.println("An expected class  " + className + " was not found for preloading: " + e.getMessage());
/*     */     
/*     */     } 
/*     */   }
/*     */   
/*     */   private static List<String> asList(String... params) {
/* 348 */     return Arrays.asList(params);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */