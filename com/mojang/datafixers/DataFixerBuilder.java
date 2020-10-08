/*    */ package com.mojang.datafixers;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import com.mojang.datafixers.schemas.Schema;
/*    */ import com.mojang.datafixers.types.Type;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectAVLTreeMap;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectSortedMap;
/*    */ import it.unimi.dsi.fastutil.ints.IntAVLTreeSet;
/*    */ import it.unimi.dsi.fastutil.ints.IntBidirectionalIterator;
/*    */ import it.unimi.dsi.fastutil.ints.IntSortedSet;
/*    */ import java.util.ArrayList;
/*    */ import java.util.Iterator;
/*    */ import java.util.List;
/*    */ import java.util.concurrent.CompletableFuture;
/*    */ import java.util.concurrent.Executor;
/*    */ import java.util.function.BiFunction;
/*    */ import org.apache.logging.log4j.LogManager;
/*    */ import org.apache.logging.log4j.Logger;
/*    */ 
/*    */ 
/*    */ public class DataFixerBuilder
/*    */ {
/* 23 */   private static final Logger LOGGER = LogManager.getLogger();
/*    */   
/*    */   private final int dataVersion;
/* 26 */   private final Int2ObjectSortedMap<Schema> schemas = (Int2ObjectSortedMap<Schema>)new Int2ObjectAVLTreeMap();
/* 27 */   private final List<DataFix> globalList = Lists.newArrayList();
/* 28 */   private final IntSortedSet fixerVersions = (IntSortedSet)new IntAVLTreeSet();
/*    */   private final int minDataFixPrecacheVersion;
/*    */   
/*    */   public DataFixerBuilder(int dataVersion) {
/* 32 */     this.minDataFixPrecacheVersion = Integer.getInteger("Paper.minPrecachedDatafixVersion", dataVersion + 1).intValue() * 10;
/* 33 */     this.dataVersion = dataVersion;
/*    */   }
/*    */   
/*    */   public Schema addSchema(int version, BiFunction<Integer, Schema, Schema> factory) {
/* 37 */     return addSchema(version, 0, factory);
/*    */   }
/*    */   
/*    */   public Schema addSchema(int version, int subVersion, BiFunction<Integer, Schema, Schema> factory) {
/* 41 */     int key = DataFixUtils.makeKey(version, subVersion);
/* 42 */     Schema parent = this.schemas.isEmpty() ? null : (Schema)this.schemas.get(DataFixerUpper.getLowestSchemaSameVersion(this.schemas, key - 1));
/* 43 */     Schema schema = factory.apply(Integer.valueOf(DataFixUtils.makeKey(version, subVersion)), parent);
/* 44 */     addSchema(schema);
/* 45 */     return schema;
/*    */   }
/*    */   
/*    */   public void addSchema(Schema schema) {
/* 49 */     this.schemas.put(schema.getVersionKey(), schema);
/*    */   }
/*    */   
/*    */   public void addFixer(DataFix fix) {
/* 53 */     int version = DataFixUtils.getVersion(fix.getVersionKey());
/*    */     
/* 55 */     if (version > this.dataVersion) {
/* 56 */       LOGGER.warn("Ignored fix registered for version: {} as the DataVersion of the game is: {}", Integer.valueOf(version), Integer.valueOf(this.dataVersion));
/*    */       
/*    */       return;
/*    */     } 
/* 60 */     this.globalList.add(fix);
/* 61 */     this.fixerVersions.add(fix.getVersionKey());
/*    */   }
/*    */   
/*    */   public DataFixer build(Executor executor) {
/* 65 */     DataFixerUpper fixerUpper = new DataFixerUpper((Int2ObjectSortedMap<Schema>)new Int2ObjectAVLTreeMap(this.schemas), new ArrayList<>(this.globalList), (IntSortedSet)new IntAVLTreeSet(this.fixerVersions));
/*    */     
/* 67 */     IntBidirectionalIterator iterator = fixerUpper.fixerVersions().iterator();
/* 68 */     while (iterator.hasNext()) {
/* 69 */       int versionKey = iterator.nextInt();
/* 70 */       if (versionKey < this.minDataFixPrecacheVersion)
/* 71 */         continue;  Schema schema = (Schema)this.schemas.get(versionKey);
/* 72 */       for (Iterator<String> iterator1 = schema.types().iterator(); iterator1.hasNext(); ) { String typeName = iterator1.next();
/* 73 */         CompletableFuture.runAsync(() -> { Type<?> dataType = schema.getType(()); TypeRewriteRule rule = fixerUpper.getRule(DataFixUtils.getVersion(versionKey), this.dataVersion); dataType.rewrite(rule, DataFixerUpper.OPTIMIZATION_RULE); }executor)
/*    */ 
/*    */ 
/*    */           
/* 77 */           .exceptionally(e -> {
/*    */               LOGGER.error("Unable to build datafixers", e);
/*    */               
/*    */               Runtime.getRuntime().exit(1);
/*    */               return null;
/*    */             }); }
/*    */     
/*    */     } 
/* 85 */     return fixerUpper;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mojang\datafixers\DataFixerBuilder.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */