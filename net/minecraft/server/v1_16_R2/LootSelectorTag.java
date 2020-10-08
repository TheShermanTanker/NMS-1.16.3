/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonObject;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import java.util.function.Consumer;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class LootSelectorTag
/*    */   extends LootSelectorEntry
/*    */ {
/*    */   private final Tag<Item> g;
/*    */   private final boolean h;
/*    */   
/*    */   private LootSelectorTag(Tag<Item> var0, boolean var1, int var2, int var3, LootItemCondition[] var4, LootItemFunction[] var5) {
/* 24 */     super(var2, var3, var4, var5);
/* 25 */     this.g = var0;
/* 26 */     this.h = var1;
/*    */   }
/*    */ 
/*    */   
/*    */   public LootEntryType a() {
/* 31 */     return LootEntries.e;
/*    */   }
/*    */ 
/*    */   
/*    */   public void a(Consumer<ItemStack> var0, LootTableInfo var1) {
/* 36 */     this.g.getTagged().forEach(var1 -> var0.accept(new ItemStack(var1)));
/*    */   }
/*    */   
/*    */   private boolean a(LootTableInfo var0, Consumer<LootEntry> var1) {
/* 40 */     if (a(var0)) {
/* 41 */       for (Item var3 : this.g.getTagged()) {
/* 42 */         var1.accept(new LootSelectorEntry.c(this, var3)
/*    */             {
/*    */               public void a(Consumer<ItemStack> var0, LootTableInfo var1) {
/* 45 */                 var0.accept(new ItemStack(this.a));
/*    */               }
/*    */             });
/*    */       } 
/* 49 */       return true;
/*    */     } 
/* 51 */     return false;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean expand(LootTableInfo var0, Consumer<LootEntry> var1) {
/* 56 */     if (this.h) {
/* 57 */       return a(var0, var1);
/*    */     }
/* 59 */     return super.expand(var0, var1);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static LootSelectorEntry.a<?> b(Tag<Item> var0) {
/* 68 */     return a((var1, var2, var3, var4) -> new LootSelectorTag(var0, true, var1, var2, var3, var4));
/*    */   }
/*    */   
/*    */   public static class a
/*    */     extends LootSelectorEntry.e<LootSelectorTag> {
/*    */     public void a(JsonObject var0, LootSelectorTag var1, JsonSerializationContext var2) {
/* 74 */       super.a(var0, var1, var2);
/*    */       
/* 76 */       var0.addProperty("name", TagsInstance.a().getItemTags().b(LootSelectorTag.a(var1)).toString());
/* 77 */       var0.addProperty("expand", Boolean.valueOf(LootSelectorTag.b(var1)));
/*    */     }
/*    */ 
/*    */     
/*    */     protected LootSelectorTag b(JsonObject var0, JsonDeserializationContext var1, int var2, int var3, LootItemCondition[] var4, LootItemFunction[] var5) {
/* 82 */       MinecraftKey var6 = new MinecraftKey(ChatDeserializer.h(var0, "name"));
/*    */       
/* 84 */       Tag<Item> var7 = TagsInstance.a().getItemTags().a(var6);
/* 85 */       if (var7 == null) {
/* 86 */         throw new JsonParseException("Can't find tag: " + var6);
/*    */       }
/*    */       
/* 89 */       boolean var8 = ChatDeserializer.j(var0, "expand");
/*    */       
/* 91 */       return new LootSelectorTag(var7, var8, var2, var3, var4, var5);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LootSelectorTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */