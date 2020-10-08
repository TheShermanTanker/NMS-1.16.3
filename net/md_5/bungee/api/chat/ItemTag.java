/*    */ package net.md_5.bungee.api.chat;
/*    */ 
/*    */ import com.google.gson.JsonDeserializationContext;
/*    */ import com.google.gson.JsonDeserializer;
/*    */ import com.google.gson.JsonElement;
/*    */ import com.google.gson.JsonParseException;
/*    */ import com.google.gson.JsonSerializationContext;
/*    */ import com.google.gson.JsonSerializer;
/*    */ import java.lang.reflect.Type;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class ItemTag
/*    */ {
/*    */   private final String nbt;
/*    */   
/*    */   private static Builder builder() {
/* 20 */     return new Builder(); } private static class Builder { private Builder nbt(String nbt) { this.nbt = nbt; return this; } private String nbt; private ItemTag build() { return new ItemTag(this.nbt); } public String toString() { return "ItemTag.Builder(nbt=" + this.nbt + ")"; } }
/* 21 */   public String toString() { return "ItemTag(nbt=" + getNbt() + ")"; }
/* 22 */   public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof ItemTag)) return false;  ItemTag other = (ItemTag)o; Object this$nbt = getNbt(), other$nbt = other.getNbt(); return !((this$nbt == null) ? (other$nbt != null) : !this$nbt.equals(other$nbt)); } public int hashCode() { int PRIME = 59; result = 1; Object $nbt = getNbt(); return result * 59 + (($nbt == null) ? 43 : $nbt.hashCode()); }
/*    */ 
/*    */ 
/*    */   
/*    */   public String getNbt() {
/* 27 */     return this.nbt;
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   private ItemTag(String nbt) {
/* 50 */     this.nbt = nbt;
/*    */   }
/*    */ 
/*    */   
/*    */   public static ItemTag ofNbt(String nbt) {
/* 55 */     return new ItemTag(nbt);
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static class Serializer
/*    */     implements JsonSerializer<ItemTag>, JsonDeserializer<ItemTag>
/*    */   {
/*    */     public ItemTag deserialize(JsonElement element, Type type, JsonDeserializationContext context) throws JsonParseException {
/* 64 */       return ItemTag.ofNbt(element.getAsJsonPrimitive().getAsString());
/*    */     }
/*    */ 
/*    */ 
/*    */     
/*    */     public JsonElement serialize(ItemTag itemTag, Type type, JsonSerializationContext context) {
/* 70 */       return context.serialize(itemTag.getNbt());
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\api\chat\ItemTag.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */