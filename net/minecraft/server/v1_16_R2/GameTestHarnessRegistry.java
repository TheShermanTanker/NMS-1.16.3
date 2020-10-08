/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import java.util.Collection;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.Set;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.stream.Collectors;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class GameTestHarnessRegistry
/*     */ {
/*  24 */   private static final Collection<GameTestHarnessTestFunction> a = Lists.newArrayList();
/*  25 */   private static final Set<String> b = Sets.newHashSet();
/*  26 */   private static final Map<String, Consumer<WorldServer>> c = Maps.newHashMap();
/*  27 */   private static final Collection<GameTestHarnessTestFunction> d = Sets.newHashSet();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static Collection<GameTestHarnessTestFunction> a(String var0) {
/*  59 */     return (Collection<GameTestHarnessTestFunction>)a.stream()
/*  60 */       .filter(var1 -> a(var1, var0))
/*  61 */       .collect(Collectors.toList());
/*     */   }
/*     */   
/*     */   public static Collection<GameTestHarnessTestFunction> a() {
/*  65 */     return a;
/*     */   }
/*     */   
/*     */   public static Collection<String> b() {
/*  69 */     return b;
/*     */   }
/*     */   
/*     */   public static boolean b(String var0) {
/*  73 */     return b.contains(var0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static Consumer<WorldServer> c(String var0) {
/*  78 */     return c.get(var0);
/*     */   }
/*     */   
/*     */   public static Optional<GameTestHarnessTestFunction> d(String var0) {
/*  82 */     return a().stream()
/*  83 */       .filter(var1 -> var1.a().equalsIgnoreCase(var0))
/*  84 */       .findFirst();
/*     */   }
/*     */ 
/*     */   
/*     */   public static GameTestHarnessTestFunction e(String var0) {
/*  89 */     Optional<GameTestHarnessTestFunction> var1 = d(var0);
/*  90 */     if (!var1.isPresent()) {
/*  91 */       throw new IllegalArgumentException("Can't find the test function for " + var0);
/*     */     }
/*  93 */     return var1.get();
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private static boolean a(GameTestHarnessTestFunction var0, String var1) {
/* 138 */     return var0.a().toLowerCase().startsWith(var1.toLowerCase() + ".");
/*     */   }
/*     */   
/*     */   public static Collection<GameTestHarnessTestFunction> c() {
/* 142 */     return d;
/*     */   }
/*     */   
/*     */   public static void a(GameTestHarnessTestFunction var0) {
/* 146 */     d.add(var0);
/*     */   }
/*     */   
/*     */   public static void d() {
/* 150 */     d.clear();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */