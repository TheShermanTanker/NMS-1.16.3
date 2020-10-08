/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
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
/*     */ public class LootItemFunctionCopyState
/*     */   extends LootItemFunctionConditional
/*     */ {
/*     */   private final Block a;
/*     */   private final Set<IBlockState<?>> b;
/*     */   
/*     */   private LootItemFunctionCopyState(LootItemCondition[] var0, Block var1, Set<IBlockState<?>> var2) {
/*  32 */     super(var0);
/*  33 */     this.a = var1;
/*  34 */     this.b = var2;
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemFunctionType b() {
/*  39 */     return LootItemFunctions.v;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<LootContextParameter<?>> a() {
/*  44 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(LootContextParameters.BLOCK_STATE);
/*     */   }
/*     */ 
/*     */   
/*     */   protected ItemStack a(ItemStack var0, LootTableInfo var1) {
/*  49 */     IBlockData var2 = var1.<IBlockData>getContextParameter(LootContextParameters.BLOCK_STATE);
/*  50 */     if (var2 != null) {
/*  51 */       NBTTagCompound var4, var3 = var0.getOrCreateTag();
/*     */       
/*  53 */       if (var3.hasKeyOfType("BlockStateTag", 10)) {
/*  54 */         var4 = var3.getCompound("BlockStateTag");
/*     */       } else {
/*  56 */         var4 = new NBTTagCompound();
/*  57 */         var3.set("BlockStateTag", var4);
/*     */       } 
/*     */       
/*  60 */       this.b.stream().filter(var2::b).forEach(var2 -> var0.setString(var2.getName(), a(var1, var2)));
/*     */     } 
/*     */     
/*  63 */     return var0;
/*     */   }
/*     */   
/*     */   public static class a extends LootItemFunctionConditional.a<a> {
/*     */     private final Block a;
/*  68 */     private final Set<IBlockState<?>> b = Sets.newHashSet();
/*     */     
/*     */     private a(Block var0) {
/*  71 */       this.a = var0;
/*     */     }
/*     */     
/*     */     public a a(IBlockState<?> var0) {
/*  75 */       if (!this.a.getStates().d().contains(var0)) {
/*  76 */         throw new IllegalStateException("Property " + var0 + " is not present on block " + this.a);
/*     */       }
/*  78 */       this.b.add(var0);
/*  79 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     protected a d() {
/*  84 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunction b() {
/*  89 */       return new LootItemFunctionCopyState(g(), this.a, this.b);
/*     */     }
/*     */   }
/*     */   
/*     */   public static a a(Block var0) {
/*  94 */     return new a(var0);
/*     */   }
/*     */   
/*     */   private static <T extends Comparable<T>> String a(IBlockData var0, IBlockState<T> var1) {
/*  98 */     T var2 = (T)var0.get(var1);
/*  99 */     return var1.a(var2);
/*     */   }
/*     */   
/*     */   public static class b
/*     */     extends LootItemFunctionConditional.c<LootItemFunctionCopyState> {
/*     */     public void a(JsonObject var0, LootItemFunctionCopyState var1, JsonSerializationContext var2) {
/* 105 */       super.a(var0, var1, var2);
/* 106 */       var0.addProperty("block", IRegistry.BLOCK.getKey(LootItemFunctionCopyState.a(var1)).toString());
/* 107 */       JsonArray var3 = new JsonArray();
/* 108 */       LootItemFunctionCopyState.b(var1).forEach(var1 -> var0.add(var1.getName()));
/* 109 */       var0.add("properties", (JsonElement)var3);
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunctionCopyState b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 114 */       MinecraftKey var3 = new MinecraftKey(ChatDeserializer.h(var0, "block"));
/*     */       
/* 116 */       Block var4 = (Block)IRegistry.BLOCK.getOptional(var3).orElseThrow(() -> new IllegalArgumentException("Can't find block " + var0));
/* 117 */       BlockStateList<Block, IBlockData> var5 = var4.getStates();
/* 118 */       Set<IBlockState<?>> var6 = Sets.newHashSet();
/*     */       
/* 120 */       JsonArray var7 = ChatDeserializer.a(var0, "properties", (JsonArray)null);
/* 121 */       if (var7 != null) {
/* 122 */         var7.forEach(var2 -> var0.add(var1.a(ChatDeserializer.a(var2, "property"))));
/*     */       }
/* 124 */       return new LootItemFunctionCopyState(var2, var4, var6);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionCopyState.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */