/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import com.google.common.collect.Streams;
/*     */ import com.google.gson.JsonArray;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import java.util.List;
/*     */ import java.util.Set;
/*     */ import java.util.function.UnaryOperator;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ public class LootItemFunctionSetLore
/*     */   extends LootItemFunctionConditional
/*     */ {
/*     */   private final boolean a;
/*     */   private final List<IChatBaseComponent> b;
/*     */   @Nullable
/*     */   private final LootTableInfo.EntityTarget d;
/*     */   
/*     */   public LootItemFunctionSetLore(LootItemCondition[] var0, boolean var1, List<IChatBaseComponent> var2, @Nullable LootTableInfo.EntityTarget var3) {
/*  36 */     super(var0);
/*  37 */     this.a = var1;
/*  38 */     this.b = (List<IChatBaseComponent>)ImmutableList.copyOf(var2);
/*  39 */     this.d = var3;
/*     */   }
/*     */ 
/*     */   
/*     */   public LootItemFunctionType b() {
/*  44 */     return LootItemFunctions.s;
/*     */   }
/*     */ 
/*     */   
/*     */   public Set<LootContextParameter<?>> a() {
/*  49 */     return (this.d != null) ? (Set<LootContextParameter<?>>)ImmutableSet.of(this.d.a()) : (Set<LootContextParameter<?>>)ImmutableSet.of();
/*     */   }
/*     */ 
/*     */   
/*     */   public ItemStack a(ItemStack var0, LootTableInfo var1) {
/*  54 */     NBTTagList var2 = a(var0, !this.b.isEmpty());
/*     */     
/*  56 */     if (var2 != null) {
/*  57 */       if (this.a) {
/*  58 */         var2.clear();
/*     */       }
/*     */       
/*  61 */       UnaryOperator<IChatBaseComponent> var3 = LootItemFunctionSetName.a(var1, this.d);
/*  62 */       this.b.stream().map(var3).map(IChatBaseComponent.ChatSerializer::a).map(NBTTagString::a).forEach(var2::add);
/*     */     } 
/*     */     
/*  65 */     return var0;
/*     */   }
/*     */   @Nullable
/*     */   private NBTTagList a(ItemStack var0, boolean var1) {
/*     */     NBTTagCompound var2;
/*     */     NBTTagCompound var3;
/*  71 */     if (var0.hasTag()) {
/*  72 */       var2 = var0.getTag();
/*  73 */     } else if (var1) {
/*  74 */       var2 = new NBTTagCompound();
/*  75 */       var0.setTag(var2);
/*     */     } else {
/*  77 */       return null;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/*  82 */     if (var2.hasKeyOfType("display", 10)) {
/*  83 */       var3 = var2.getCompound("display");
/*  84 */     } else if (var1) {
/*  85 */       var3 = new NBTTagCompound();
/*  86 */       var2.set("display", var3);
/*     */     } else {
/*  88 */       return null;
/*     */     } 
/*     */     
/*  91 */     if (var3.hasKeyOfType("Lore", 9))
/*  92 */       return var3.getList("Lore", 8); 
/*  93 */     if (var1) {
/*  94 */       NBTTagList var4 = new NBTTagList();
/*  95 */       var3.set("Lore", var4);
/*  96 */       return var4;
/*     */     } 
/*  98 */     return null;
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
/*     */   public static class b
/*     */     extends LootItemFunctionConditional.c<LootItemFunctionSetLore>
/*     */   {
/*     */     public void a(JsonObject var0, LootItemFunctionSetLore var1, JsonSerializationContext var2) {
/* 140 */       super.a(var0, var1, var2);
/*     */       
/* 142 */       var0.addProperty("replace", Boolean.valueOf(LootItemFunctionSetLore.a(var1)));
/*     */       
/* 144 */       JsonArray var3 = new JsonArray();
/* 145 */       for (IChatBaseComponent var5 : LootItemFunctionSetLore.b(var1)) {
/* 146 */         var3.add(IChatBaseComponent.ChatSerializer.b(var5));
/*     */       }
/* 148 */       var0.add("lore", (JsonElement)var3);
/*     */       
/* 150 */       if (LootItemFunctionSetLore.c(var1) != null) {
/* 151 */         var0.add("entity", var2.serialize(LootItemFunctionSetLore.c(var1)));
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public LootItemFunctionSetLore b(JsonObject var0, JsonDeserializationContext var1, LootItemCondition[] var2) {
/* 157 */       boolean var3 = ChatDeserializer.a(var0, "replace", false);
/* 158 */       List<IChatBaseComponent> var4 = (List<IChatBaseComponent>)Streams.stream((Iterable)ChatDeserializer.u(var0, "lore")).map(IChatBaseComponent.ChatSerializer::a).collect(ImmutableList.toImmutableList());
/* 159 */       LootTableInfo.EntityTarget var5 = ChatDeserializer.<LootTableInfo.EntityTarget>a(var0, "entity", null, var1, LootTableInfo.EntityTarget.class);
/* 160 */       return new LootItemFunctionSetLore(var2, var3, var4, var5);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootItemFunctionSetLore.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */