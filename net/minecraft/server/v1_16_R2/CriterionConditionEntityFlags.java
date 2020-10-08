/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionConditionEntityFlags
/*     */ {
/*  13 */   public static final CriterionConditionEntityFlags a = (new a()).b();
/*     */   
/*     */   @Nullable
/*     */   private final Boolean b;
/*     */   
/*     */   @Nullable
/*     */   private final Boolean c;
/*     */   
/*     */   @Nullable
/*     */   private final Boolean d;
/*     */   
/*     */   @Nullable
/*     */   private final Boolean e;
/*     */   
/*     */   @Nullable
/*     */   private final Boolean f;
/*     */   
/*     */   public CriterionConditionEntityFlags(@Nullable Boolean var0, @Nullable Boolean var1, @Nullable Boolean var2, @Nullable Boolean var3, @Nullable Boolean var4) {
/*  31 */     this.b = var0;
/*  32 */     this.c = var1;
/*  33 */     this.d = var2;
/*  34 */     this.e = var3;
/*  35 */     this.f = var4;
/*     */   }
/*     */   
/*     */   public boolean a(Entity var0) {
/*  39 */     if (this.b != null && var0.isBurning() != this.b.booleanValue()) {
/*  40 */       return false;
/*     */     }
/*     */     
/*  43 */     if (this.c != null && var0.by() != this.c.booleanValue()) {
/*  44 */       return false;
/*     */     }
/*     */     
/*  47 */     if (this.d != null && var0.isSprinting() != this.d.booleanValue()) {
/*  48 */       return false;
/*     */     }
/*     */     
/*  51 */     if (this.e != null && var0.isSwimming() != this.e.booleanValue()) {
/*  52 */       return false;
/*     */     }
/*     */     
/*  55 */     if (this.f != null && var0 instanceof EntityLiving && ((EntityLiving)var0).isBaby() != this.f.booleanValue()) {
/*  56 */       return false;
/*     */     }
/*     */     
/*  59 */     return true;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static Boolean a(JsonObject var0, String var1) {
/*  64 */     return var0.has(var1) ? Boolean.valueOf(ChatDeserializer.j(var0, var1)) : null;
/*     */   }
/*     */   
/*     */   public static CriterionConditionEntityFlags a(@Nullable JsonElement var0) {
/*  68 */     if (var0 == null || var0.isJsonNull()) {
/*  69 */       return a;
/*     */     }
/*     */     
/*  72 */     JsonObject var1 = ChatDeserializer.m(var0, "entity flags");
/*  73 */     Boolean var2 = a(var1, "is_on_fire");
/*     */     
/*  75 */     Boolean var3 = a(var1, "is_sneaking");
/*  76 */     Boolean var4 = a(var1, "is_sprinting");
/*  77 */     Boolean var5 = a(var1, "is_swimming");
/*  78 */     Boolean var6 = a(var1, "is_baby");
/*     */     
/*  80 */     return new CriterionConditionEntityFlags(var2, var3, var4, var5, var6);
/*     */   }
/*     */   
/*     */   private void a(JsonObject var0, String var1, @Nullable Boolean var2) {
/*  84 */     if (var2 != null) {
/*  85 */       var0.addProperty(var1, var2);
/*     */     }
/*     */   }
/*     */   
/*     */   public JsonElement a() {
/*  90 */     if (this == a) {
/*  91 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/*  94 */     JsonObject var0 = new JsonObject();
/*  95 */     a(var0, "is_on_fire", this.b);
/*     */     
/*  97 */     a(var0, "is_sneaking", this.c);
/*  98 */     a(var0, "is_sprinting", this.d);
/*  99 */     a(var0, "is_swimming", this.e);
/* 100 */     a(var0, "is_baby", this.f);
/* 101 */     return (JsonElement)var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class a
/*     */   {
/*     */     @Nullable
/*     */     private Boolean a;
/*     */     
/*     */     @Nullable
/*     */     private Boolean b;
/*     */     
/*     */     @Nullable
/*     */     private Boolean c;
/*     */     @Nullable
/*     */     private Boolean d;
/*     */     @Nullable
/*     */     private Boolean e;
/*     */     
/*     */     public static a a() {
/* 121 */       return new a();
/*     */     }
/*     */     
/*     */     public a a(@Nullable Boolean var0) {
/* 125 */       this.a = var0;
/* 126 */       return this;
/*     */     }
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
/*     */     public a e(@Nullable Boolean var0) {
/* 145 */       this.e = var0;
/* 146 */       return this;
/*     */     }
/*     */     
/*     */     public CriterionConditionEntityFlags b() {
/* 150 */       return new CriterionConditionEntityFlags(this.a, this.b, this.c, this.d, this.e);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionEntityFlags.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */