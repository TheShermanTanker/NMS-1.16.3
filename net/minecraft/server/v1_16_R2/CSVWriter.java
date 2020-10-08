/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.io.IOException;
/*    */ import java.io.Writer;
/*    */ import java.util.List;
/*    */ import java.util.stream.Collectors;
/*    */ import java.util.stream.Stream;
/*    */ import javax.annotation.Nullable;
/*    */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.StringEscapeUtils;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class CSVWriter
/*    */ {
/*    */   private final Writer a;
/*    */   private final int b;
/*    */   
/*    */   private CSVWriter(Writer var0, List<String> var1) throws IOException {
/* 20 */     this.a = var0;
/* 21 */     this.b = var1.size();
/* 22 */     a(var1.stream());
/*    */   }
/*    */   
/*    */   public static a a() {
/* 26 */     return new a();
/*    */   }
/*    */   
/*    */   public void a(Object... var0) throws IOException {
/* 30 */     if (var0.length != this.b) {
/* 31 */       throw new IllegalArgumentException("Invalid number of columns, expected " + this.b + ", but got " + var0.length);
/*    */     }
/*    */     
/* 34 */     a(Stream.of(var0));
/*    */   }
/*    */   
/*    */   private void a(Stream<?> var0) throws IOException {
/* 38 */     this.a.write((String)var0.<CharSequence>map(CSVWriter::a).collect(Collectors.joining(",")) + "\r\n");
/*    */   }
/*    */   
/*    */   private static String a(@Nullable Object var0) {
/* 42 */     return StringEscapeUtils.escapeCsv((var0 != null) ? var0.toString() : "[null]");
/*    */   }
/*    */   
/*    */   public static class a {
/* 46 */     private final List<String> a = Lists.newArrayList();
/*    */     
/*    */     public a a(String var0) {
/* 49 */       this.a.add(var0);
/* 50 */       return this;
/*    */     }
/*    */     
/*    */     public CSVWriter a(Writer var0) throws IOException {
/* 54 */       return new CSVWriter(var0, this.a);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CSVWriter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */