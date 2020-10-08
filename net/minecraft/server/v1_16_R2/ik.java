/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.GsonBuilder;
/*     */ import com.google.gson.JsonElement;
/*     */ import java.nio.file.Path;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.function.BiConsumer;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Consumer;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class ik
/*     */   implements DebugReportProvider
/*     */ {
/*  32 */   private static final Logger LOGGER = LogManager.getLogger();
/*  33 */   private static final Gson c = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
/*     */   
/*     */   private final DebugReportGenerator d;
/*     */   
/*     */   public ik(DebugReportGenerator var0) {
/*  38 */     this.d = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(HashCache var0) {
/*  43 */     Path var1 = this.d.b();
/*     */     
/*  45 */     Map<Block, il> var2 = Maps.newHashMap();
/*  46 */     Consumer<il> var3 = var1 -> {
/*     */         Block var2 = var1.a();
/*     */         
/*     */         il var3 = var0.put(var2, var1);
/*     */         if (var3 != null) {
/*     */           throw new IllegalStateException("Duplicate blockstate definition for " + var2);
/*     */         }
/*     */       };
/*  54 */     Map<MinecraftKey, Supplier<JsonElement>> var4 = Maps.newHashMap();
/*  55 */     Set<Item> var5 = Sets.newHashSet();
/*     */     
/*  57 */     BiConsumer<MinecraftKey, Supplier<JsonElement>> var6 = (var1, var2) -> {
/*     */         Supplier<JsonElement> var3 = var0.put(var1, var2);
/*     */         
/*     */         if (var3 != null) {
/*     */           throw new IllegalStateException("Duplicate model definition for " + var1);
/*     */         }
/*     */       };
/*  64 */     Consumer<Item> var7 = var5::add;
/*     */     
/*  66 */     (new ii(var3, var6, var7)).a();
/*  67 */     (new ij(var6)).a();
/*     */     
/*  69 */     List<Block> var8 = (List<Block>)IRegistry.BLOCK.g().filter(var1 -> !var0.containsKey(var1)).collect(Collectors.toList());
/*  70 */     if (!var8.isEmpty()) {
/*  71 */       throw new IllegalStateException("Missing blockstate definitions for: " + var8);
/*     */     }
/*     */     
/*  74 */     IRegistry.BLOCK.forEach(var2 -> {
/*     */           Item var3 = Item.e.get(var2);
/*     */           
/*     */           if (var3 != null) {
/*     */             if (var0.contains(var3)) {
/*     */               return;
/*     */             }
/*     */             MinecraftKey var4 = iw.a(var3);
/*     */             if (!var1.containsKey(var4)) {
/*     */               var1.put(var4, new iv(iw.a(var2)));
/*     */             }
/*     */           } 
/*     */         });
/*  87 */     a(var0, var1, (Map)var2, ik::a);
/*  88 */     a(var0, var1, var4, ik::a);
/*     */   }
/*     */   
/*     */   private <T> void a(HashCache var0, Path var1, Map<T, ? extends Supplier<JsonElement>> var2, BiFunction<Path, T, Path> var3) {
/*  92 */     var2.forEach((var3, var4) -> {
/*     */           Path var5 = var0.apply(var1, var3);
/*     */           try {
/*     */             DebugReportProvider.a(c, var2, var4.get(), var5);
/*  96 */           } catch (Exception var6) {
/*     */             LOGGER.error("Couldn't save {}", var5, var6);
/*     */           } 
/*     */         });
/*     */   }
/*     */   
/*     */   private static Path a(Path var0, Block var1) {
/* 103 */     MinecraftKey var2 = IRegistry.BLOCK.getKey(var1);
/* 104 */     return var0.resolve("assets/" + var2.getNamespace() + "/blockstates/" + var2.getKey() + ".json");
/*     */   }
/*     */   
/*     */   private static Path a(Path var0, MinecraftKey var1) {
/* 108 */     return var0.resolve("assets/" + var1.getNamespace() + "/models/" + var1.getKey() + ".json");
/*     */   }
/*     */ 
/*     */   
/*     */   public String a() {
/* 113 */     return "Block State Definitions";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ik.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */