/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.gson.JsonDeserializationContext;
/*     */ import com.google.gson.JsonDeserializer;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonParseException;
/*     */ import com.google.gson.JsonSerializationContext;
/*     */ import com.google.gson.JsonSerializer;
/*     */ import java.lang.reflect.Type;
/*     */ import java.util.Arrays;
/*     */ import java.util.Date;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ public class AdvancementProgress
/*     */   implements Comparable<AdvancementProgress>
/*     */ {
/*  24 */   private final Map<String, CriterionProgress> a = Maps.newHashMap();
/*  25 */   private String[][] b = new String[0][];
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Map<String, Criterion> var0, String[][] var1) {
/*  31 */     Set<String> var2 = var0.keySet();
/*  32 */     this.a.entrySet().removeIf(var1 -> !var0.contains(var1.getKey()));
/*  33 */     for (String var4 : var2) {
/*  34 */       if (!this.a.containsKey(var4)) {
/*  35 */         this.a.put(var4, new CriterionProgress());
/*     */       }
/*     */     } 
/*  38 */     this.b = var1;
/*     */   }
/*     */   
/*     */   public boolean isDone() {
/*  42 */     if (this.b.length == 0) {
/*  43 */       return false;
/*     */     }
/*  45 */     for (String[] var3 : this.b) {
/*  46 */       boolean var4 = false;
/*  47 */       for (String var8 : var3) {
/*  48 */         CriterionProgress var9 = getCriterionProgress(var8);
/*  49 */         if (var9 != null && var9.a()) {
/*  50 */           var4 = true;
/*     */           break;
/*     */         } 
/*     */       } 
/*  54 */       if (!var4) {
/*  55 */         return false;
/*     */       }
/*     */     } 
/*  58 */     return true;
/*     */   }
/*     */   
/*     */   public boolean b() {
/*  62 */     for (CriterionProgress var1 : this.a.values()) {
/*  63 */       if (var1.a()) {
/*  64 */         return true;
/*     */       }
/*     */     } 
/*  67 */     return false;
/*     */   }
/*     */   
/*     */   public boolean a(String var0) {
/*  71 */     CriterionProgress var1 = this.a.get(var0);
/*  72 */     if (var1 != null && !var1.a()) {
/*  73 */       var1.b();
/*  74 */       return true;
/*     */     } 
/*  76 */     return false;
/*     */   }
/*     */   
/*     */   public boolean b(String var0) {
/*  80 */     CriterionProgress var1 = this.a.get(var0);
/*  81 */     if (var1 != null && var1.a()) {
/*  82 */       var1.c();
/*  83 */       return true;
/*     */     } 
/*  85 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  90 */     return "AdvancementProgress{criteria=" + this.a + ", requirements=" + 
/*     */       
/*  92 */       Arrays.deepToString((Object[])this.b) + '}';
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer var0) {
/*  97 */     var0.d(this.a.size());
/*  98 */     for (Map.Entry<String, CriterionProgress> var2 : this.a.entrySet()) {
/*  99 */       var0.a(var2.getKey());
/* 100 */       ((CriterionProgress)var2.getValue()).a(var0);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static AdvancementProgress b(PacketDataSerializer var0) {
/* 105 */     AdvancementProgress var1 = new AdvancementProgress();
/* 106 */     int var2 = var0.i();
/* 107 */     for (int var3 = 0; var3 < var2; var3++) {
/* 108 */       var1.a.put(var0.e(32767), CriterionProgress.b(var0));
/*     */     }
/* 110 */     return var1;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public CriterionProgress getCriterionProgress(String var0) {
/* 115 */     return this.a.get(var0);
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
/*     */   
/*     */   public Iterable<String> getRemainingCriteria() {
/* 161 */     List<String> var0 = Lists.newArrayList();
/* 162 */     for (Map.Entry<String, CriterionProgress> var2 : this.a.entrySet()) {
/* 163 */       if (!((CriterionProgress)var2.getValue()).a()) {
/* 164 */         var0.add(var2.getKey());
/*     */       }
/*     */     } 
/* 167 */     return var0;
/*     */   }
/*     */   
/*     */   public Iterable<String> getAwardedCriteria() {
/* 171 */     List<String> var0 = Lists.newArrayList();
/* 172 */     for (Map.Entry<String, CriterionProgress> var2 : this.a.entrySet()) {
/* 173 */       if (((CriterionProgress)var2.getValue()).a()) {
/* 174 */         var0.add(var2.getKey());
/*     */       }
/*     */     } 
/* 177 */     return var0;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Date g() {
/* 182 */     Date var0 = null;
/*     */     
/* 184 */     for (CriterionProgress var2 : this.a.values()) {
/* 185 */       if (var2.a() && (var0 == null || var2.getDate().before(var0))) {
/* 186 */         var0 = var2.getDate();
/*     */       }
/*     */     } 
/*     */     
/* 190 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int compareTo(AdvancementProgress var0) {
/* 195 */     Date var1 = g();
/* 196 */     Date var2 = var0.g();
/*     */     
/* 198 */     if (var1 == null && var2 != null) {
/* 199 */       return 1;
/*     */     }
/* 201 */     if (var1 != null && var2 == null) {
/* 202 */       return -1;
/*     */     }
/* 204 */     if (var1 == null && var2 == null) {
/* 205 */       return 0;
/*     */     }
/*     */     
/* 208 */     return var1.compareTo(var2);
/*     */   }
/*     */   
/*     */   public static class a
/*     */     implements JsonDeserializer<AdvancementProgress>, JsonSerializer<AdvancementProgress> {
/*     */     public JsonElement serialize(AdvancementProgress var0, Type var1, JsonSerializationContext var2) {
/* 214 */       JsonObject var3 = new JsonObject();
/* 215 */       JsonObject var4 = new JsonObject();
/* 216 */       for (Map.Entry<String, CriterionProgress> var6 : (Iterable<Map.Entry<String, CriterionProgress>>)AdvancementProgress.b(var0).entrySet()) {
/* 217 */         CriterionProgress var7 = var6.getValue();
/* 218 */         if (var7.a()) {
/* 219 */           var4.add(var6.getKey(), var7.e());
/*     */         }
/*     */       } 
/* 222 */       if (!var4.entrySet().isEmpty()) {
/* 223 */         var3.add("criteria", (JsonElement)var4);
/*     */       }
/* 225 */       var3.addProperty("done", Boolean.valueOf(var0.isDone()));
/* 226 */       return (JsonElement)var3;
/*     */     }
/*     */ 
/*     */     
/*     */     public AdvancementProgress deserialize(JsonElement var0, Type var1, JsonDeserializationContext var2) throws JsonParseException {
/* 231 */       JsonObject var3 = ChatDeserializer.m(var0, "advancement");
/* 232 */       JsonObject var4 = ChatDeserializer.a(var3, "criteria", new JsonObject());
/* 233 */       AdvancementProgress var5 = new AdvancementProgress();
/*     */       
/* 235 */       for (Map.Entry<String, JsonElement> var7 : (Iterable<Map.Entry<String, JsonElement>>)var4.entrySet()) {
/* 236 */         String var8 = var7.getKey();
/* 237 */         AdvancementProgress.b(var5).put(var8, CriterionProgress.a(ChatDeserializer.a(var7.getValue(), var8)));
/*     */       } 
/*     */       
/* 240 */       return var5;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AdvancementProgress.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */