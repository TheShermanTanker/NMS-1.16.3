/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LootItemFunctionCopyNBT
/*     */   extends LootItemFunctionConditional
/*     */ {
/*     */   private final Source a;
/*     */   private final List<b> b;
/*     */   
/*     */   static class b
/*     */   {
/*     */     private final String a;
/*     */     private final ArgumentNBTKey.h b;
/*     */     private final String c;
/*     */     private final ArgumentNBTKey.h d;
/*     */     private final LootItemFunctionCopyNBT.Action e;
/*     */     
/*     */     private b(String var0, String var1, LootItemFunctionCopyNBT.Action var2) {
/*  42 */       this.a = var0;
/*  43 */       this.b = LootItemFunctionCopyNBT.a(var0);
/*  44 */       this.c = var1;
/*  45 */       this.d = LootItemFunctionCopyNBT.a(var1);
/*  46 */       this.e = var2;
/*     */     }
/*     */     
/*     */     public void a(Supplier<NBTBase> var0, NBTBase var1) {
/*     */       try {
/*  51 */         List<NBTBase> var2 = this.b.a(var1);
/*  52 */         if (!var2.isEmpty()) {
/*  53 */           this.e.a(var0.get(), this.d, var2);
/*     */         }
/*  55 */       } catch (CommandSyntaxException commandSyntaxException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public JsonObject a() {
/*  61 */       JsonObject var0 = new JsonObject();
/*  62 */       var0.addProperty("source", this.a);
/*  63 */       var0.addProperty("target", this.c);
/*  64 */       var0.addProperty("op", LootItemFunctionCopyNBT.Action.a(this.e));
/*  65 */       return var0;
/*     */     }
/*     */     
/*     */     public static b a(JsonObject var0) {
/*  69 */       String var1 = ChatDeserializer.h(var0, "source");
/*  70 */       String var2 = ChatDeserializer.h(var0, "target");
/*  71 */       LootItemFunctionCopyNBT.Action var3 = LootItemFunctionCopyNBT.Action.a(ChatDeserializer.h(var0, "op"));
/*  72 */       return new b(var1, var2, var3);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private LootItemFunctionCopyNBT(LootItemCondition[] var0, Source var1, List<b> var2) {
/*  80 */     super(var0);
/*  81 */     this.a = var1;
/*  82 */     this.b = (List<b>)ImmutableList.copyOf(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemFunctionType b() {
/*  87 */     return LootItemFunctions.u;
/*     */   }
/*     */   
/*     */   private static ArgumentNBTKey.h b(String var0) {
/*     */     try {
/*  92 */       return (new ArgumentNBTKey()).parse(new StringReader(var0));
/*  93 */     } catch (CommandSyntaxException var1) {
/*  94 */       throw new IllegalArgumentException("Failed to parse path " + var0, var1);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<LootContextParameter<?>> a() {
/* 100 */     return (Set<LootContextParameter<?>>)ImmutableSet.of(this.a.f);
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/* 105 */     NBTBase var2 = this.a.g.apply(var1);
/* 106 */     if (var2 != null) {
/* 107 */       this.b.forEach(var2 -> var2.a(var0::getOrCreateTag, var1));
/*     */     }
/*     */     
/* 110 */     return var0;
/*     */   }
/*     */   
/*     */   public static class a extends LootItemFunctionConditional.a<a> {
/*     */     private final LootItemFunctionCopyNBT.Source a;
/* 115 */     private final List<LootItemFunctionCopyNBT.b> b = Lists.newArrayList();
/*     */     
/*     */     private a(LootItemFunctionCopyNBT.Source var0) {
/* 118 */       this.a = var0;
/*     */     }
/*     */     
/*     */     public a a(String var0, String var1, LootItemFunctionCopyNBT.Action var2) {
/* 122 */       this.b.add(new LootItemFunctionCopyNBT.b(var0, var1, var2));
/* 123 */       return this;
/*     */     }
/*     */     
/*     */     public a a(String var0, String var1) {
/* 127 */       return a(var0, var1, LootItemFunctionCopyNBT.Action.REPLACE);
/*     */     }
/*     */ 
/*     */     
/*     */     protected a d() {
/* 132 */       return this;
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunction b() {
/* 137 */       return new LootItemFunctionCopyNBT(g(), this.a, this.b);
/*     */     }
/*     */   }
/*     */   
/*     */   public static a a(Source var0) {
/* 142 */     return new a(var0);
/*     */   }
/*     */   private static final Function<TileEntity, NBTBase> e;
/* 145 */   private static final Function<Entity, NBTBase> d = CriterionConditionNBT::b; static {
/* 146 */     e = (var0 -> var0.save(new NBTTagCompound()));
/*     */   }
/*     */   
/* 149 */   public enum Action { REPLACE("replace")
/*     */     {
/*     */       public void a(NBTBase var0, ArgumentNBTKey.h var1, List<NBTBase> var2) throws CommandSyntaxException {
/* 152 */         var1.b(var0, (NBTBase)Iterables.getLast(var2)::clone);
/*     */       }
/*     */     },
/* 155 */     APPEND("append")
/*     */     {
/*     */       public void a(NBTBase var0, ArgumentNBTKey.h var1, List<NBTBase> var2) throws CommandSyntaxException {
/* 158 */         List<NBTBase> var3 = var1.a(var0, NBTTagList::new);
/* 159 */         var3.forEach(var1 -> {
/*     */               
/*     */               if (var1 instanceof NBTTagList) {
/*     */                 var0.forEach(());
/*     */               }
/*     */             });
/*     */       }
/*     */     },
/* 167 */     MERGE("merge")
/*     */     {
/*     */       public void a(NBTBase var0, ArgumentNBTKey.h var1, List<NBTBase> var2) throws CommandSyntaxException {
/* 170 */         List<NBTBase> var3 = var1.a(var0, NBTTagCompound::new);
/* 171 */         var3.forEach(var1 -> {
/*     */               if (var1 instanceof NBTTagCompound) {
/*     */                 var0.forEach(());
/*     */               }
/*     */             });
/*     */       }
/*     */     };
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private final String d;
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Action(String var2) {
/* 189 */       this.d = var2;
/*     */     }
/*     */     
/*     */     public static Action a(String var0) {
/* 193 */       for (Action var4 : values()) {
/* 194 */         if (var4.d.equals(var0)) {
/* 195 */           return var4;
/*     */         }
/*     */       } 
/* 198 */       throw new IllegalArgumentException("Invalid merge strategy" + var0);
/*     */     }
/*     */     
/*     */     public abstract void a(NBTBase param1NBTBase, ArgumentNBTKey.h param1h, List<NBTBase> param1List) throws CommandSyntaxException; }
/*     */   
/* 203 */   public enum Source { THIS("this", LootContextParameters.THIS_ENTITY, (String)LootItemFunctionCopyNBT.c()),
/* 204 */     KILLER("killer", LootContextParameters.KILLER_ENTITY, (String)LootItemFunctionCopyNBT.c()),
/* 205 */     KILLER_PLAYER("killer_player", LootContextParameters.LAST_DAMAGE_PLAYER, (String)LootItemFunctionCopyNBT.c()),
/* 206 */     BLOCK_ENTITY("block_entity", LootContextParameters.BLOCK_ENTITY, (String)LootItemFunctionCopyNBT.d());
/*     */     
/*     */     public final String e;
/*     */     public final LootContextParameter<?> f;
/*     */     public final Function<LootTableInfo, NBTBase> g;
/*     */     
/*     */     <T> Source(String var2, LootContextParameter<T> var3, Function<? super T, NBTBase> var4) {
/* 213 */       this.e = var2;
/* 214 */       this.f = var3;
/* 215 */       this.g = (var2 -> {
/*     */           T var3 = var2.getContextParameter(var0);
/*     */           return (var3 != null) ? var1.apply(var3) : null;
/*     */         });
/*     */     }
/*     */     
/*     */     public static Source a(String var0) {
/* 222 */       for (Source var4 : values()) {
/* 223 */         if (var4.e.equals(var0)) {
/* 224 */           return var4;
/*     */         }
/*     */       } 
/* 227 */       throw new IllegalArgumentException("Invalid tag source " + var0);
/*     */     } }
/*     */ 
/*     */   
/*     */   public static class e
/*     */     extends LootItemFunctionConditional.c<LootItemFunctionCopyNBT> {
/*     */     public void a(JsonObject var0, LootItemFunctionCopyNBT var1, JsonSerializationContext var2) {
/* 234 */       super.a(var0, var1, var2);
/* 235 */       var0.addProperty("source", (LootItemFunctionCopyNBT.a(var1)).e);
/* 236 */       JsonArray var3 = new JsonArray();
/* 237 */       LootItemFunctionCopyNBT.b(var1).stream().map(LootItemFunctionCopyNBT.b::a).forEach(var3::add);
/* 238 */       var0.add("ops", (JsonElement)var3);
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunctionCopyNBT b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 243 */       LootItemFunctionCopyNBT.Source var3 = LootItemFunctionCopyNBT.Source.a(ChatDeserializer.h(var0, "source"));
/* 244 */       List<LootItemFunctionCopyNBT.b> var4 = Lists.newArrayList();
/* 245 */       JsonArray var5 = ChatDeserializer.u(var0, "ops");
/* 246 */       for (JsonElement var7 : var5) {
/* 247 */         JsonObject var8 = ChatDeserializer.m(var7, "op");
/* 248 */         var4.add(LootItemFunctionCopyNBT.b.a(var8));
/*     */       } 
/* 250 */       return new LootItemFunctionCopyNBT(var2, var3, var4);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionCopyNBT.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */