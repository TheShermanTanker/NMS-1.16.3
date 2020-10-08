/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import java.text.NumberFormat;
/*    */ import java.util.Locale;
/*    */ 
/*    */ public interface Counter {
/*    */   public static final DecimalFormat DECIMAL_FORMAT;
/*    */   
/*    */   static {
/* 11 */     DECIMAL_FORMAT = SystemUtils.<DecimalFormat>a(new DecimalFormat("########0.00"), var0 -> var0.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));
/*    */   }
/* 13 */   public static final Counter DEFAULT = NumberFormat.getIntegerInstance(Locale.US)::format; static {
/* 14 */     DIVIDE_BY_TEN = (var0 -> DECIMAL_FORMAT.format(var0 * 0.1D));
/* 15 */     DISTANCE = (var0 -> {
/*    */         double var1 = var0 / 100.0D;
/*    */ 
/*    */         
/*    */         double var3 = var1 / 1000.0D;
/*    */ 
/*    */         
/*    */         return (var3 > 0.5D) ? (DECIMAL_FORMAT.format(var3) + " km") : ((var1 > 0.5D) ? (DECIMAL_FORMAT.format(var1) + " m") : (var0 + " cm"));
/*    */       });
/*    */ 
/*    */     
/* 26 */     TIME = (var0 -> {
/*    */         double var1 = var0 / 20.0D;
/*    */         double var3 = var1 / 60.0D;
/*    */         double var5 = var3 / 60.0D;
/*    */         double var7 = var5 / 24.0D;
/*    */         double var9 = var7 / 365.0D;
/*    */         return (var9 > 0.5D) ? (DECIMAL_FORMAT.format(var9) + " y") : ((var7 > 0.5D) ? (DECIMAL_FORMAT.format(var7) + " d") : ((var5 > 0.5D) ? (DECIMAL_FORMAT.format(var5) + " h") : ((var3 > 0.5D) ? (DECIMAL_FORMAT.format(var3) + " m") : (var1 + " s"))));
/*    */       });
/*    */   }
/*    */   
/*    */   public static final Counter DIVIDE_BY_TEN;
/*    */   public static final Counter DISTANCE;
/*    */   public static final Counter TIME;
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Counter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */