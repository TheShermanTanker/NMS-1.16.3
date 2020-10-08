/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Iterators;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.util.concurrent.ListeningExecutorService;
/*     */ import com.google.common.util.concurrent.MoreExecutors;
/*     */ import com.mojang.datafixers.DSL;
/*     */ import com.mojang.datafixers.DataFixUtils;
/*     */ import com.mojang.datafixers.types.Type;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import it.unimi.dsi.fastutil.Hash;
/*     */ import java.io.File;
/*     */ import java.io.IOException;
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.lang.management.RuntimeMXBean;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.time.Instant;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Random;
/*     */ import java.util.UUID;
/*     */ import java.util.concurrent.CompletableFuture;
/*     */ import java.util.concurrent.Executor;
/*     */ import java.util.concurrent.ExecutorService;
/*     */ import java.util.concurrent.Executors;
/*     */ import java.util.concurrent.LinkedBlockingQueue;
/*     */ import java.util.concurrent.ThreadPoolExecutor;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.function.BooleanSupplier;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.LongSupplier;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collector;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.IntStream;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ public class SystemUtils
/*     */ {
/*  50 */   private static final AtomicInteger c = new AtomicInteger(1);
/*  51 */   private static final ExecutorService d = a("Bootstrap", -2);
/*  52 */   private static final ExecutorService e = a("Main", -1);
/*  53 */   private static final ExecutorService f = n();
/*  54 */   public static LongSupplier a = System::nanoTime;
/*  55 */   public static final UUID b = new UUID(0L, 0L); public static final UUID getNullUUID() { return b; }
/*  56 */    private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   public static <K, V> Collector<Map.Entry<? extends K, ? extends V>, ?, Map<K, V>> a() {
/*  59 */     return Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue);
/*     */   }
/*     */   
/*     */   public static <T extends Comparable<T>> String a(IBlockState<T> iblockstate, T object) {
/*  63 */     return iblockstate.a(object);
/*     */   }
/*     */   
/*     */   public static String a(String s, @Nullable MinecraftKey minecraftkey) {
/*  67 */     return (minecraftkey == null) ? (s + ".unregistered_sadface") : (s + '.' + minecraftkey.getNamespace() + '.' + minecraftkey.getKey().replace('/', '.'));
/*     */   }
/*     */   
/*     */   public static long getMonotonicMillis() {
/*  71 */     return getMonotonicNanos() / 1000000L;
/*     */   }
/*     */   
/*     */   public static long getMonotonicNanos() {
/*  75 */     return System.nanoTime();
/*     */   }
/*     */   
/*     */   public static long getTimeMillis() {
/*  79 */     return Instant.now().toEpochMilli();
/*     */   }
/*     */   
/*     */   private static ExecutorService a(String s, int priorityModifier) {
/*     */     ExecutorService object;
/*  84 */     int i = Math.min(8, Math.max(Runtime.getRuntime().availableProcessors() - 2, 1));
/*  85 */     i = Integer.getInteger("Paper.WorkerThreadCount", i).intValue();
/*     */ 
/*     */     
/*  88 */     if (i <= 0) {
/*  89 */       ListeningExecutorService listeningExecutorService = MoreExecutors.newDirectExecutorService();
/*     */     } else {
/*  91 */       object = new ThreadPoolExecutor(i, i, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<>(), target -> new ServerWorkerThread(target, s, priorityModifier));
/*     */     } 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 111 */     return object;
/*     */   }
/*     */   
/*     */   public static Executor e() {
/* 115 */     return d;
/*     */   }
/*     */   
/*     */   public static Executor f() {
/* 119 */     return e;
/*     */   }
/*     */   
/*     */   public static Executor g() {
/* 123 */     return f;
/*     */   }
/*     */   public static void shutdownServerThreadPool() {
/* 126 */     h();
/*     */   } public static void h() {
/* 128 */     a(e);
/* 129 */     a(f);
/*     */   }
/*     */   private static void a(ExecutorService executorservice) {
/*     */     boolean flag;
/* 133 */     executorservice.shutdown();
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/* 138 */       flag = executorservice.awaitTermination(3L, TimeUnit.SECONDS);
/* 139 */     } catch (InterruptedException interruptedexception) {
/* 140 */       flag = false;
/*     */     } 
/*     */     
/* 143 */     if (!flag) {
/* 144 */       executorservice.shutdownNow();
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   private static ExecutorService n() {
/* 150 */     return Executors.newCachedThreadPool(runnable -> {
/*     */           Thread thread = new Thread(runnable);
/*     */           thread.setName("IO-Worker-" + c.getAndIncrement());
/*     */           thread.setUncaughtExceptionHandler(SystemUtils::a);
/*     */           return thread;
/*     */         });
/*     */   }
/*     */   
/*     */   public static void onThreadError(Thread thread, Throwable throwable) {
/* 159 */     a(thread, throwable);
/*     */   } private static void a(Thread thread, Throwable throwable) {
/* 161 */     c(throwable);
/* 162 */     if (throwable instanceof java.util.concurrent.CompletionException) {
/* 163 */       throwable = throwable.getCause();
/*     */     }
/*     */     
/* 166 */     if (throwable instanceof ReportedException) {
/* 167 */       DispenserRegistry.a(((ReportedException)throwable).a().e());
/* 168 */       System.exit(-1);
/*     */     } 
/*     */     
/* 171 */     LOGGER.error(String.format("Caught exception in thread %s", new Object[] { thread }), throwable);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Type<?> a(DSL.TypeReference typereference, String s) {
/* 176 */     return !SharedConstants.c ? null : b(typereference, s);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static Type<?> b(DSL.TypeReference typereference, String s) {
/* 181 */     Type<?> type = null;
/*     */     
/*     */     try {
/* 184 */       type = DataConverterRegistry.a().getSchema(DataFixUtils.makeKey(SharedConstants.getGameVersion().getWorldVersion())).getChoiceType(typereference, s);
/* 185 */     } catch (IllegalArgumentException illegalargumentexception) {
/* 186 */       LOGGER.error("No data fixer registered for {}", s);
/* 187 */       if (SharedConstants.d) {
/* 188 */         throw illegalargumentexception;
/*     */       }
/*     */     } 
/*     */     
/* 192 */     return type;
/*     */   }
/*     */   
/*     */   public static OS i() {
/* 196 */     String s = System.getProperty("os.name").toLowerCase(Locale.ROOT);
/*     */     
/* 198 */     return s.contains("win") ? OS.WINDOWS : (s.contains("mac") ? OS.OSX : (s.contains("solaris") ? OS.SOLARIS : (s.contains("sunos") ? OS.SOLARIS : (s.contains("linux") ? OS.LINUX : (s.contains("unix") ? OS.LINUX : OS.UNKNOWN)))));
/*     */   }
/*     */   
/*     */   public static Stream<String> j() {
/* 202 */     RuntimeMXBean runtimemxbean = ManagementFactory.getRuntimeMXBean();
/*     */     
/* 204 */     return runtimemxbean.getInputArguments().stream().filter(s -> s.startsWith("-X"));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> T a(List<T> list) {
/* 210 */     return list.get(list.size() - 1);
/*     */   }
/*     */   
/*     */   public static <T> T a(Iterable<T> iterable, @Nullable T t0) {
/* 214 */     Iterator<T> iterator = iterable.iterator();
/* 215 */     T t1 = iterator.next();
/*     */     
/* 217 */     if (t0 != null) {
/* 218 */       Object object = t1;
/*     */       
/* 220 */       while (object != t0) {
/* 221 */         if (iterator.hasNext()) {
/* 222 */           object = iterator.next();
/*     */         }
/*     */       } 
/*     */       
/* 226 */       if (iterator.hasNext()) {
/* 227 */         return iterator.next();
/*     */       }
/*     */     } 
/*     */     
/* 231 */     return t1;
/*     */   }
/*     */   
/*     */   public static <T> T b(Iterable<T> iterable, @Nullable T t0) {
/* 235 */     Iterator<T> iterator = iterable.iterator();
/*     */ 
/*     */     
/*     */     T object1;
/*     */     
/* 240 */     for (object1 = null; iterator.hasNext(); object1 = object) {
/* 241 */       T object = iterator.next();
/* 242 */       if (object == t0) {
/* 243 */         if (object1 == null) {
/* 244 */           object1 = iterator.hasNext() ? (T)Iterators.getLast(iterator) : t0;
/*     */         }
/*     */         
/*     */         break;
/*     */       } 
/*     */     } 
/* 250 */     return object1;
/*     */   }
/*     */   
/*     */   public static <T> T a(Supplier<T> supplier) {
/* 254 */     return supplier.get();
/*     */   }
/*     */   
/*     */   public static <T> T a(T t0, Consumer<T> consumer) {
/* 258 */     consumer.accept(t0);
/* 259 */     return t0;
/*     */   }
/*     */   
/*     */   public static <K> Hash.Strategy<K> k() {
/* 263 */     return IdentityHashingStrategy.INSTANCE;
/*     */   }
/*     */   
/*     */   public static <V> CompletableFuture<List<V>> b(List<? extends CompletableFuture<? extends V>> list) {
/* 267 */     List<V> list1 = Lists.newArrayListWithCapacity(list.size());
/* 268 */     CompletableFuture[] arrayOfCompletableFuture = new CompletableFuture[list.size()];
/* 269 */     CompletableFuture<Void> completablefuture = new CompletableFuture<>();
/*     */     
/* 271 */     list.forEach(completablefuture1 -> {
/*     */           int i = list1.size();
/*     */ 
/*     */ 
/*     */           
/*     */           list1.add(null);
/*     */ 
/*     */ 
/*     */           
/*     */           acompletablefuture[i] = completablefuture1.whenComplete(());
/*     */         });
/*     */ 
/*     */     
/* 284 */     return CompletableFuture.allOf((CompletableFuture<?>[])arrayOfCompletableFuture).applyToEither(completablefuture, ovoid -> list1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static <T> Stream<T> a(Optional<? extends T> optional) {
/* 290 */     return (Stream<T>)DataFixUtils.orElseGet(optional.map(Stream::of), Stream::empty);
/*     */   }
/*     */   
/*     */   public static <T> Optional<T> a(Optional<T> optional, Consumer<T> consumer, Runnable runnable) {
/* 294 */     if (optional.isPresent()) {
/* 295 */       consumer.accept(optional.get());
/*     */     } else {
/* 297 */       runnable.run();
/*     */     } 
/*     */     
/* 300 */     return optional;
/*     */   }
/*     */   
/*     */   public static Runnable a(Runnable runnable, Supplier<String> supplier) {
/* 304 */     return runnable;
/*     */   }
/*     */   
/*     */   public static <T extends Throwable> T c(T t0) {
/* 308 */     if (SharedConstants.d) {
/* 309 */       LOGGER.error("Trying to throw a fatal exception, pausing in IDE", (Throwable)t0);
/*     */       
/*     */       try {
/*     */         while (true)
/* 313 */         { Thread.sleep(1000L);
/* 314 */           LOGGER.error("paused"); } 
/* 315 */       } catch (InterruptedException interruptedexception) {
/* 316 */         return t0;
/*     */       } 
/*     */     } 
/*     */     
/* 320 */     return t0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static String d(Throwable throwable) {
/* 325 */     return (throwable.getCause() != null) ? d(throwable.getCause()) : ((throwable.getMessage() != null) ? throwable.getMessage() : throwable.toString());
/*     */   }
/*     */   
/*     */   public static <T> T a(T[] at, Random random) {
/* 329 */     return at[random.nextInt(at.length)];
/*     */   }
/*     */   
/*     */   public static int a(int[] aint, Random random) {
/* 333 */     return aint[random.nextInt(aint.length)];
/*     */   }
/*     */   
/*     */   private static BooleanSupplier a(final Path java_nio_file_path, final Path java_nio_file_path1) {
/* 337 */     return new BooleanSupplier() {
/*     */         public boolean getAsBoolean() {
/*     */           try {
/* 340 */             Files.move(java_nio_file_path, java_nio_file_path1, new java.nio.file.CopyOption[0]);
/* 341 */             return true;
/* 342 */           } catch (IOException ioexception) {
/* 343 */             SystemUtils.LOGGER.error("Failed to rename", ioexception);
/* 344 */             return false;
/*     */           } 
/*     */         }
/*     */         
/*     */         public String toString() {
/* 349 */           return "rename " + java_nio_file_path + " to " + java_nio_file_path1;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static BooleanSupplier a(final Path java_nio_file_path) {
/* 355 */     return new BooleanSupplier() {
/*     */         public boolean getAsBoolean() {
/*     */           try {
/* 358 */             Files.deleteIfExists(java_nio_file_path);
/* 359 */             return true;
/* 360 */           } catch (IOException ioexception) {
/* 361 */             SystemUtils.LOGGER.warn("Failed to delete", ioexception);
/* 362 */             return false;
/*     */           } 
/*     */         }
/*     */         
/*     */         public String toString() {
/* 367 */           return "delete old " + java_nio_file_path;
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static BooleanSupplier b(final Path java_nio_file_path) {
/* 373 */     return new BooleanSupplier() {
/*     */         public boolean getAsBoolean() {
/* 375 */           return !Files.exists(java_nio_file_path, new java.nio.file.LinkOption[0]);
/*     */         }
/*     */         
/*     */         public String toString() {
/* 379 */           return "verify that " + java_nio_file_path + " is deleted";
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static BooleanSupplier c(final Path java_nio_file_path) {
/* 385 */     return new BooleanSupplier() {
/*     */         public boolean getAsBoolean() {
/* 387 */           return Files.isRegularFile(java_nio_file_path, new java.nio.file.LinkOption[0]);
/*     */         }
/*     */         
/*     */         public String toString() {
/* 391 */           return "verify that " + java_nio_file_path + " is present";
/*     */         }
/*     */       };
/*     */   }
/*     */   
/*     */   private static boolean a(BooleanSupplier... abooleansupplier) {
/* 397 */     BooleanSupplier[] abooleansupplier1 = abooleansupplier;
/* 398 */     int i = abooleansupplier.length;
/*     */     
/* 400 */     for (int j = 0; j < i; j++) {
/* 401 */       BooleanSupplier booleansupplier = abooleansupplier1[j];
/*     */       
/* 403 */       if (!booleansupplier.getAsBoolean()) {
/* 404 */         LOGGER.warn("Failed to execute {}", booleansupplier);
/* 405 */         return false;
/*     */       } 
/*     */     } 
/*     */     
/* 409 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean a(int i, String s, BooleanSupplier... abooleansupplier) {
/* 413 */     for (int j = 0; j < i; j++) {
/* 414 */       if (a(abooleansupplier)) {
/* 415 */         return true;
/*     */       }
/*     */       
/* 418 */       LOGGER.error("Failed to {}, retrying {}/{}", s, Integer.valueOf(j), Integer.valueOf(i));
/*     */     } 
/*     */     
/* 421 */     LOGGER.error("Failed to {}, aborting, progress might be lost", s);
/* 422 */     return false;
/*     */   }
/*     */   
/*     */   public static void a(File file, File file1, File file2) {
/* 426 */     a(file.toPath(), file1.toPath(), file2.toPath());
/*     */   }
/*     */   
/*     */   public static void a(Path java_nio_file_path, Path java_nio_file_path1, Path java_nio_file_path2) {
/* 430 */     boolean flag = true;
/*     */     
/* 432 */     if ((!Files.exists(java_nio_file_path, new java.nio.file.LinkOption[0]) || a(10, "create backup " + java_nio_file_path2, new BooleanSupplier[] { a(java_nio_file_path2), a(java_nio_file_path, java_nio_file_path2), c(java_nio_file_path2)
/* 433 */         })) && a(10, "remove old " + java_nio_file_path, new BooleanSupplier[] { a(java_nio_file_path), b(java_nio_file_path)
/* 434 */         }) && !a(10, "replace " + java_nio_file_path + " with " + java_nio_file_path1, new BooleanSupplier[] { a(java_nio_file_path1, java_nio_file_path), c(java_nio_file_path) })) {
/* 435 */       a(10, "restore " + java_nio_file_path + " from " + java_nio_file_path2, new BooleanSupplier[] { a(java_nio_file_path2, java_nio_file_path), c(java_nio_file_path) });
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Consumer<String> a(String s, Consumer<String> consumer) {
/* 443 */     return s1 -> consumer.accept(s + s1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public static DataResult<int[]> a(IntStream intstream, int i) {
/* 449 */     int[] aint = intstream.limit((i + 1)).toArray();
/*     */     
/* 451 */     if (aint.length != i) {
/* 452 */       String s = "Input is not a list of " + i + " ints";
/*     */       
/* 454 */       return (aint.length >= i) ? DataResult.error(s, Arrays.copyOf(aint, i)) : DataResult.error(s);
/*     */     } 
/* 456 */     return DataResult.success(aint);
/*     */   }
/*     */ 
/*     */   
/*     */   public static void l() {
/* 461 */     Thread thread = new Thread("Timer hack thread") {
/*     */         public void run() {
/*     */           try {
/*     */             while (true)
/* 465 */               Thread.sleep(2147483647L); 
/* 466 */           } catch (InterruptedException interruptedexception) {
/* 467 */             SystemUtils.LOGGER.warn("Timer hack thread interrupted, that really should not happen");
/*     */             
/*     */             return;
/*     */           } 
/*     */         }
/*     */       };
/*     */     
/* 474 */     thread.setDaemon(true);
/* 475 */     thread.setUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler(LOGGER));
/* 476 */     thread.start();
/*     */   }
/*     */   
/*     */   enum IdentityHashingStrategy
/*     */     implements Hash.Strategy<Object> {
/* 481 */     INSTANCE;
/*     */ 
/*     */ 
/*     */     
/*     */     public int hashCode(Object object) {
/* 486 */       return System.identityHashCode(object);
/*     */     }
/*     */     
/*     */     public boolean equals(Object object, Object object1) {
/* 490 */       return (object == object1);
/*     */     }
/*     */   }
/*     */   
/*     */   public enum OS
/*     */   {
/* 496 */     LINUX, SOLARIS, WINDOWS {
/*     */        },
/* 498 */     OSX {
/*     */        },
/* 500 */     UNKNOWN;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SystemUtils.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */