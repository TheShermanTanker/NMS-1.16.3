/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonNull;
/*     */ import com.google.gson.JsonObject;
/*     */ import com.google.gson.JsonPrimitive;
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.DynamicCommandExceptionType;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.util.function.BiFunction;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public abstract class CriterionConditionValue<T extends Number>
/*     */ {
/*     */   public static class IntegerRange
/*     */     extends CriterionConditionValue<Integer>
/*     */   {
/*  21 */     public static final IntegerRange e = new IntegerRange(null, null); private final Long f;
/*     */     
/*     */     private static IntegerRange a(StringReader var0, @Nullable Integer var1, @Nullable Integer var2) throws CommandSyntaxException {
/*  24 */       if (var1 != null && var2 != null && var1.intValue() > var2.intValue()) {
/*  25 */         throw b.createWithContext(var0);
/*     */       }
/*     */       
/*  28 */       return new IntegerRange(var1, var2);
/*     */     }
/*     */     private final Long g;
/*     */     @Nullable
/*     */     private static Long a(@Nullable Integer var0) {
/*  33 */       return (var0 == null) ? null : Long.valueOf(var0.longValue() * var0.longValue());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private IntegerRange(@Nullable Integer var0, @Nullable Integer var1) {
/*  40 */       super(var0, var1);
/*  41 */       this.f = a(var0);
/*  42 */       this.g = a(var1);
/*     */     }
/*     */     
/*     */     public static IntegerRange a(int var0) {
/*  46 */       return new IntegerRange(Integer.valueOf(var0), Integer.valueOf(var0));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static IntegerRange b(int var0) {
/*  54 */       return new IntegerRange(Integer.valueOf(var0), null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean d(int var0) {
/*  62 */       if (this.c != null && this.c.intValue() > var0) {
/*  63 */         return false;
/*     */       }
/*  65 */       if (this.d != null && this.d.intValue() < var0) {
/*  66 */         return false;
/*     */       }
/*  68 */       return true;
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
/*     */     public static IntegerRange a(@Nullable JsonElement var0) {
/*  82 */       return (IntegerRange)a(var0, e, ChatDeserializer::g, IntegerRange::new);
/*     */     }
/*     */     
/*     */     public static IntegerRange a(StringReader var0) throws CommandSyntaxException {
/*  86 */       return a(var0, var0 -> var0);
/*     */     }
/*     */     
/*     */     public static IntegerRange a(StringReader var0, Function<Integer, Integer> var1) throws CommandSyntaxException {
/*  90 */       return (IntegerRange)a(var0, IntegerRange::a, Integer::parseInt, CommandSyntaxException.BUILT_IN_EXCEPTIONS::readerInvalidInt, var1);
/*     */     }
/*     */   }
/*     */   
/*     */   public static class FloatRange extends CriterionConditionValue<Float> {
/*  95 */     public static final FloatRange e = new FloatRange(null, null); private final Double f; private final Double g;
/*     */     
/*     */     private static FloatRange a(StringReader var0, @Nullable Float var1, @Nullable Float var2) throws CommandSyntaxException {
/*  98 */       if (var1 != null && var2 != null && var1.floatValue() > var2.floatValue()) {
/*  99 */         throw b.createWithContext(var0);
/*     */       }
/*     */       
/* 102 */       return new FloatRange(var1, var2);
/*     */     }
/*     */     
/*     */     @Nullable
/*     */     private static Double a(@Nullable Float var0) {
/* 107 */       return (var0 == null) ? null : Double.valueOf(var0.doubleValue() * var0.doubleValue());
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     private FloatRange(@Nullable Float var0, @Nullable Float var1) {
/* 114 */       super(var0, var1);
/* 115 */       this.f = a(var0);
/* 116 */       this.g = a(var1);
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
/*     */     public static FloatRange b(float var0) {
/* 128 */       return new FloatRange(Float.valueOf(var0), null);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean d(float var0) {
/* 136 */       if (this.c != null && this.c.floatValue() > var0) {
/* 137 */         return false;
/*     */       }
/* 139 */       if (this.d != null && this.d.floatValue() < var0) {
/* 140 */         return false;
/*     */       }
/* 142 */       return true;
/*     */     }
/*     */     
/*     */     public boolean a(double var0) {
/* 146 */       if (this.f != null && this.f.doubleValue() > var0) {
/* 147 */         return false;
/*     */       }
/* 149 */       if (this.g != null && this.g.doubleValue() < var0) {
/* 150 */         return false;
/*     */       }
/* 152 */       return true;
/*     */     }
/*     */     
/*     */     public static FloatRange a(@Nullable JsonElement var0) {
/* 156 */       return (FloatRange)a(var0, e, ChatDeserializer::e, FloatRange::new);
/*     */     }
/*     */     
/*     */     public static FloatRange a(StringReader var0) throws CommandSyntaxException {
/* 160 */       return a(var0, var0 -> var0);
/*     */     }
/*     */     
/*     */     public static FloatRange a(StringReader var0, Function<Float, Float> var1) throws CommandSyntaxException {
/* 164 */       return (FloatRange)a(var0, FloatRange::a, Float::parseFloat, CommandSyntaxException.BUILT_IN_EXCEPTIONS::readerInvalidFloat, var1);
/*     */     }
/*     */   }
/*     */   
/* 168 */   public static final SimpleCommandExceptionType a = new SimpleCommandExceptionType(new ChatMessage("argument.range.empty"));
/* 169 */   public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.range.swapped"));
/*     */   
/*     */   protected final T c;
/*     */   protected final T d;
/*     */   
/*     */   protected CriterionConditionValue(@Nullable T var0, @Nullable T var1) {
/* 175 */     this.c = var0;
/* 176 */     this.d = var1;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public T a() {
/* 181 */     return this.c;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public T b() {
/* 186 */     return this.d;
/*     */   }
/*     */   
/*     */   public boolean c() {
/* 190 */     return (this.c == null && this.d == null);
/*     */   }
/*     */   
/*     */   public JsonElement d() {
/* 194 */     if (c()) {
/* 195 */       return (JsonElement)JsonNull.INSTANCE;
/*     */     }
/*     */     
/* 198 */     if (this.c != null && this.c.equals(this.d)) {
/* 199 */       return (JsonElement)new JsonPrimitive((Number)this.c);
/*     */     }
/*     */     
/* 202 */     JsonObject var0 = new JsonObject();
/* 203 */     if (this.c != null) {
/* 204 */       var0.addProperty("min", (Number)this.c);
/*     */     }
/* 206 */     if (this.d != null) {
/* 207 */       var0.addProperty("max", (Number)this.d);
/*     */     }
/* 209 */     return (JsonElement)var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static <T extends Number, R extends CriterionConditionValue<T>> R a(@Nullable JsonElement var0, R var1, BiFunction<JsonElement, String, T> var2, a<T, R> var3) {
/* 218 */     if (var0 == null || var0.isJsonNull()) {
/* 219 */       return var1;
/*     */     }
/*     */     
/* 222 */     if (ChatDeserializer.b(var0)) {
/* 223 */       Number number = (Number)var2.apply(var0, "value");
/* 224 */       return var3.create((T)number, (T)number);
/*     */     } 
/* 226 */     JsonObject var4 = ChatDeserializer.m(var0, "value");
/* 227 */     Number number1 = var4.has("min") ? (Number)var2.apply(var4.get("min"), "min") : null;
/* 228 */     Number number2 = var4.has("max") ? (Number)var2.apply(var4.get("max"), "max") : null;
/* 229 */     return var3.create((T)number1, (T)number2);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static <T extends Number, R extends CriterionConditionValue<T>> R a(StringReader var0, b<T, R> var1, Function<String, T> var2, Supplier<DynamicCommandExceptionType> var3, Function<T, T> var4) throws CommandSyntaxException {
/* 239 */     if (!var0.canRead()) {
/* 240 */       throw a.createWithContext(var0);
/*     */     }
/*     */     
/* 243 */     int var5 = var0.getCursor();
/*     */     
/*     */     try {
/* 246 */       Number number2, number1 = a(a(var0, var2, var3), var4);
/*     */       
/* 248 */       if (var0.canRead(2) && var0.peek() == '.' && var0.peek(1) == '.') {
/* 249 */         var0.skip();
/* 250 */         var0.skip();
/* 251 */         number2 = a(a(var0, var2, var3), var4);
/* 252 */         if (number1 == null && number2 == null) {
/* 253 */           throw a.createWithContext(var0);
/*     */         }
/*     */       } else {
/* 256 */         number2 = number1;
/*     */       } 
/*     */       
/* 259 */       if (number1 == null && number2 == null) {
/* 260 */         throw a.createWithContext(var0);
/*     */       }
/* 262 */       return var1.create(var0, (T)number1, (T)number2);
/* 263 */     } catch (CommandSyntaxException var6) {
/* 264 */       var0.setCursor(var5);
/* 265 */       throw new CommandSyntaxException(var6.getType(), var6.getRawMessage(), var6.getInput(), var5);
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static <T extends Number> T a(StringReader var0, Function<String, T> var1, Supplier<DynamicCommandExceptionType> var2) throws CommandSyntaxException {
/* 271 */     int var3 = var0.getCursor();
/* 272 */     while (var0.canRead() && a(var0)) {
/* 273 */       var0.skip();
/*     */     }
/* 275 */     String var4 = var0.getString().substring(var3, var0.getCursor());
/* 276 */     if (var4.isEmpty()) {
/* 277 */       return null;
/*     */     }
/*     */     try {
/* 280 */       return var1.apply(var4);
/* 281 */     } catch (NumberFormatException var5) {
/* 282 */       throw ((DynamicCommandExceptionType)var2.get()).createWithContext(var0, var4);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static boolean a(StringReader var0) {
/* 287 */     char var1 = var0.peek();
/* 288 */     if ((var1 >= '0' && var1 <= '9') || var1 == '-') {
/* 289 */       return true;
/*     */     }
/*     */     
/* 292 */     if (var1 == '.') {
/* 293 */       return (!var0.canRead(2) || var0.peek(1) != '.');
/*     */     }
/*     */     
/* 296 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static <T> T a(@Nullable T var0, Function<T, T> var1) {
/* 301 */     return (var0 == null) ? null : var1.apply(var0);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface a<T extends Number, R extends CriterionConditionValue<T>> {
/*     */     R create(@Nullable T param1T1, @Nullable T param1T2);
/*     */   }
/*     */   
/*     */   @FunctionalInterface
/*     */   public static interface b<T extends Number, R extends CriterionConditionValue<T>> {
/*     */     R create(StringReader param1StringReader, @Nullable T param1T1, @Nullable T param1T2) throws CommandSyntaxException;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionValue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */