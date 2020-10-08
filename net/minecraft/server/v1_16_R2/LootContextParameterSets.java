/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.BiMap;
/*     */ import com.google.common.collect.HashBiMap;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public class LootContextParameterSets
/*     */ {
/*  11 */   private static final BiMap<MinecraftKey, LootContextParameterSet> m = (BiMap<MinecraftKey, LootContextParameterSet>)HashBiMap.create(); public static final LootContextParameterSet CHEST; public static final LootContextParameterSet COMMAND;
/*     */   public static final LootContextParameterSet SELECTOR;
/*  13 */   public static final LootContextParameterSet EMPTY = a("empty", var0 -> {
/*     */       
/*  15 */       }); public static final LootContextParameterSet FISHING; static { CHEST = a("chest", var0 -> var0.addRequired(LootContextParameters.ORIGIN).addOptional(LootContextParameters.THIS_ENTITY));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  20 */     COMMAND = a("command", var0 -> var0.addRequired(LootContextParameters.ORIGIN).addOptional(LootContextParameters.THIS_ENTITY));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  25 */     SELECTOR = a("selector", var0 -> var0.addRequired(LootContextParameters.ORIGIN).addRequired(LootContextParameters.THIS_ENTITY));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  30 */     FISHING = a("fishing", var0 -> var0.addRequired(LootContextParameters.ORIGIN).addRequired(LootContextParameters.TOOL).addOptional(LootContextParameters.THIS_ENTITY));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  36 */     ENTITY = a("entity", var0 -> var0.addRequired(LootContextParameters.THIS_ENTITY).addRequired(LootContextParameters.ORIGIN).addRequired(LootContextParameters.DAMAGE_SOURCE).addOptional(LootContextParameters.KILLER_ENTITY).addOptional(LootContextParameters.DIRECT_KILLER_ENTITY).addOptional(LootContextParameters.LAST_DAMAGE_PLAYER));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     GIFT = a("gift", var0 -> var0.addRequired(LootContextParameters.ORIGIN).addRequired(LootContextParameters.THIS_ENTITY));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  50 */     BARTER = a("barter", var0 -> var0.addRequired(LootContextParameters.THIS_ENTITY));
/*     */ 
/*     */ 
/*     */     
/*  54 */     ADVANCEMENT_REWARD = a("advancement_reward", var0 -> var0.addRequired(LootContextParameters.THIS_ENTITY).addRequired(LootContextParameters.ORIGIN));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  59 */     j = a("advancement_entity", var0 -> var0.addRequired(LootContextParameters.THIS_ENTITY).addRequired(LootContextParameters.ORIGIN));
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  64 */     GENERIC = a("generic", var0 -> var0.addRequired(LootContextParameters.THIS_ENTITY).addRequired(LootContextParameters.LAST_DAMAGE_PLAYER).addRequired(LootContextParameters.DAMAGE_SOURCE).addRequired(LootContextParameters.KILLER_ENTITY).addRequired(LootContextParameters.DIRECT_KILLER_ENTITY).addRequired(LootContextParameters.ORIGIN).addRequired(LootContextParameters.BLOCK_STATE).addRequired(LootContextParameters.BLOCK_ENTITY).addRequired(LootContextParameters.TOOL).addRequired(LootContextParameters.EXPLOSION_RADIUS));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  77 */     BLOCK = a("block", var0 -> var0.addRequired(LootContextParameters.BLOCK_STATE).addRequired(LootContextParameters.ORIGIN).addRequired(LootContextParameters.TOOL).addOptional(LootContextParameters.THIS_ENTITY).addOptional(LootContextParameters.BLOCK_ENTITY).addOptional(LootContextParameters.EXPLOSION_RADIUS)); }
/*     */   
/*     */   public static final LootContextParameterSet ENTITY; public static final LootContextParameterSet GIFT;
/*     */   public static final LootContextParameterSet BARTER;
/*     */   public static final LootContextParameterSet ADVANCEMENT_REWARD;
/*     */   public static final LootContextParameterSet j;
/*     */   public static final LootContextParameterSet GENERIC;
/*     */   public static final LootContextParameterSet BLOCK;
/*     */   
/*     */   private static LootContextParameterSet a(String var0, Consumer<LootContextParameterSet.Builder> var1) {
/*  87 */     LootContextParameterSet.Builder var2 = new LootContextParameterSet.Builder();
/*  88 */     var1.accept(var2);
/*  89 */     LootContextParameterSet var3 = var2.build();
/*  90 */     MinecraftKey var4 = new MinecraftKey(var0);
/*  91 */     LootContextParameterSet var5 = (LootContextParameterSet)m.put(var4, var3);
/*  92 */     if (var5 != null) {
/*  93 */       throw new IllegalStateException("Loot table parameter set " + var4 + " is already registered");
/*     */     }
/*  95 */     return var3;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static LootContextParameterSet a(MinecraftKey var0) {
/* 100 */     return (LootContextParameterSet)m.get(var0);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static MinecraftKey a(LootContextParameterSet var0) {
/* 105 */     return (MinecraftKey)m.inverse().get(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootContextParameterSets.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */