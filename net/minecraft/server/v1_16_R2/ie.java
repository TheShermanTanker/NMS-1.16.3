/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import com.google.common.collect.Maps;
/*    */ import com.google.common.collect.Multimap;
/*    */ import com.google.common.collect.Sets;
/*    */ import com.google.gson.Gson;
/*    */ import com.google.gson.GsonBuilder;
/*    */ import com.mojang.datafixers.util.Pair;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Path;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.function.BiConsumer;
/*    */ import java.util.function.Consumer;
/*    */ import java.util.function.Supplier;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ie
/*    */   implements DebugReportProvider
/*    */ {
/* 33 */   private static final Logger LOGGER = LogManager.getLogger();
/* 34 */   private static final Gson c = (new GsonBuilder()).setPrettyPrinting().disableHtmlEscaping().create();
/*    */   
/*    */   private final DebugReportGenerator d;
/* 37 */   private final List<Pair<Supplier<Consumer<BiConsumer<MinecraftKey, LootTable.a>>>, LootContextParameterSet>> e = (List<Pair<Supplier<Consumer<BiConsumer<MinecraftKey, LootTable.a>>>, LootContextParameterSet>>)ImmutableList.of(
/* 38 */       Pair.of(ic::new, LootContextParameterSets.FISHING), 
/* 39 */       Pair.of(ia::new, LootContextParameterSets.CHEST), 
/* 40 */       Pair.of(ib::new, LootContextParameterSets.ENTITY), 
/* 41 */       Pair.of(hz::new, LootContextParameterSets.BLOCK), 
/* 42 */       Pair.of(ig::new, LootContextParameterSets.BARTER), 
/* 43 */       Pair.of(id::new, LootContextParameterSets.GIFT));
/*    */ 
/*    */   
/*    */   public ie(DebugReportGenerator var0) {
/* 47 */     this.d = var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(HashCache var0) {
/* 52 */     Path var1 = this.d.b();
/*    */     
/* 54 */     Map<MinecraftKey, LootTable> var2 = Maps.newHashMap();
/*    */     
/* 56 */     this.e.forEach(var1 -> ((Consumer<BiConsumer>)((Supplier<Consumer<BiConsumer>>)var1.getFirst()).get()).accept(()));
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */     
/* 64 */     LootCollector var3 = new LootCollector(LootContextParameterSets.GENERIC, var0 -> null, var2::get);
/*    */     
/* 66 */     Sets.SetView setView = Sets.difference(LootTables.a(), var2.keySet());
/*    */     
/* 68 */     for (MinecraftKey var6 : setView) {
/* 69 */       var3.a("Missing built-in table: " + var6);
/*    */     }
/*    */     
/* 72 */     var2.forEach((var1, var2) -> LootTableRegistry.a(var0, var1, var2));
/*    */     
/* 74 */     Multimap<String, String> var5 = var3.a();
/* 75 */     if (!var5.isEmpty()) {
/* 76 */       var5.forEach((var0, var1) -> LOGGER.warn("Found validation problem in " + var0 + ": " + var1));
/* 77 */       throw new IllegalStateException("Failed to validate loot tables, see logs");
/*    */     } 
/*    */     
/* 80 */     var2.forEach((var2, var3) -> {
/*    */           Path var4 = a(var0, var2);
/*    */           try {
/*    */             DebugReportProvider.a(c, var1, LootTableRegistry.a(var3), var4);
/* 84 */           } catch (IOException var5) {
/*    */             LOGGER.error("Couldn't save loot table {}", var4, var5);
/*    */           } 
/*    */         });
/*    */   }
/*    */   
/*    */   private static Path a(Path var0, MinecraftKey var1) {
/* 91 */     return var0.resolve("data/" + var1.getNamespace() + "/loot_tables/" + var1.getKey() + ".json");
/*    */   }
/*    */ 
/*    */   
/*    */   public String a() {
/* 96 */     return "LootTables";
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ie.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */