/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.mojang.brigadier.StringReader;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.exceptions.SimpleCommandExceptionType;
/*     */ import java.util.function.Function;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CriterionConditionRange
/*     */ {
/*  17 */   public static final CriterionConditionRange a = new CriterionConditionRange(null, null);
/*     */   
/*  19 */   public static final SimpleCommandExceptionType b = new SimpleCommandExceptionType(new ChatMessage("argument.range.ints"));
/*     */   
/*     */   private final Float c;
/*     */   private final Float d;
/*     */   
/*     */   public CriterionConditionRange(@Nullable Float var0, @Nullable Float var1) {
/*  25 */     this.c = var0;
/*  26 */     this.d = var1;
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
/*     */   @Nullable
/*     */   public Float a() {
/*  73 */     return this.c;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public Float b() {
/*  78 */     return this.d;
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
/*     */   public static CriterionConditionRange a(StringReader var0, boolean var1, Function<Float, Float> var2) throws CommandSyntaxException {
/*     */     Float var5;
/* 121 */     if (!var0.canRead()) {
/* 122 */       throw CriterionConditionValue.a.createWithContext(var0);
/*     */     }
/* 124 */     int var3 = var0.getCursor();
/* 125 */     Float var4 = a(b(var0, var1), var2);
/*     */     
/* 127 */     if (var0.canRead(2) && var0.peek() == '.' && var0.peek(1) == '.')
/* 128 */     { var0.skip();
/* 129 */       var0.skip();
/* 130 */       var5 = a(b(var0, var1), var2);
/* 131 */       if (var4 == null && var5 == null) {
/* 132 */         var0.setCursor(var3);
/* 133 */         throw CriterionConditionValue.a.createWithContext(var0);
/*     */       }  }
/* 135 */     else { if (!var1 && var0.canRead() && var0.peek() == '.') {
/* 136 */         var0.setCursor(var3);
/* 137 */         throw b.createWithContext(var0);
/*     */       } 
/* 139 */       var5 = var4; }
/*     */     
/* 141 */     if (var4 == null && var5 == null) {
/* 142 */       var0.setCursor(var3);
/* 143 */       throw CriterionConditionValue.a.createWithContext(var0);
/*     */     } 
/* 145 */     return new CriterionConditionRange(var4, var5);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static Float b(StringReader var0, boolean var1) throws CommandSyntaxException {
/* 150 */     int var2 = var0.getCursor();
/* 151 */     while (var0.canRead() && c(var0, var1)) {
/* 152 */       var0.skip();
/*     */     }
/* 154 */     String var3 = var0.getString().substring(var2, var0.getCursor());
/* 155 */     if (var3.isEmpty()) {
/* 156 */       return null;
/*     */     }
/*     */     try {
/* 159 */       return Float.valueOf(Float.parseFloat(var3));
/* 160 */     } catch (NumberFormatException var4) {
/* 161 */       if (var1) {
/* 162 */         throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.readerInvalidDouble().createWithContext(var0, var3);
/*     */       }
/* 164 */       throw CommandSyntaxException.BUILT_IN_EXCEPTIONS.readerInvalidInt().createWithContext(var0, var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean c(StringReader var0, boolean var1) {
/* 170 */     char var2 = var0.peek();
/* 171 */     if ((var2 >= '0' && var2 <= '9') || var2 == '-') {
/* 172 */       return true;
/*     */     }
/*     */     
/* 175 */     if (var1 && var2 == '.') {
/* 176 */       return (!var0.canRead(2) || var0.peek(1) != '.');
/*     */     }
/*     */     
/* 179 */     return false;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static Float a(@Nullable Float var0, Function<Float, Float> var1) {
/* 184 */     return (var0 == null) ? null : var1.apply(var0);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CriterionConditionRange.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */