/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*    */ import java.io.IOException;
/*    */ import java.io.InputStream;
/*    */ import java.io.OutputStream;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class RegionFileCompression
/*    */ {
/* 16 */   private static final Int2ObjectMap<RegionFileCompression> d = (Int2ObjectMap<RegionFileCompression>)new Int2ObjectOpenHashMap(); static final Int2ObjectMap<RegionFileCompression> getCompressionTypes() { return d; }
/* 17 */    public static final RegionFileCompression a = a(new RegionFileCompression(1, java.util.zip.GZIPInputStream::new, java.util.zip.GZIPOutputStream::new));
/* 18 */   public static final RegionFileCompression b = a(new RegionFileCompression(2, java.util.zip.InflaterInputStream::new, java.util.zip.DeflaterOutputStream::new)); public static final RegionFileCompression c; static {
/* 19 */     c = a(new RegionFileCompression(3, inputstream -> inputstream, outputstream -> outputstream));
/*    */   }
/*    */ 
/*    */   
/*    */   private final int e;
/*    */   
/*    */   private final a<InputStream> f;
/*    */   private final a<OutputStream> g;
/*    */   
/*    */   private RegionFileCompression(int i, a<InputStream> regionfilecompression_a, a<OutputStream> regionfilecompression_a1) {
/* 29 */     this.e = i;
/* 30 */     this.f = regionfilecompression_a;
/* 31 */     this.g = regionfilecompression_a1;
/*    */   }
/*    */   
/*    */   private static RegionFileCompression a(RegionFileCompression regionfilecompression) {
/* 35 */     d.put(regionfilecompression.e, regionfilecompression);
/* 36 */     return regionfilecompression;
/*    */   }
/*    */   @Nullable
/* 39 */   public static RegionFileCompression getByType(int type) { return a(type); } @Nullable
/*    */   public static RegionFileCompression a(int i) {
/* 41 */     return (RegionFileCompression)d.get(i);
/*    */   }
/*    */   
/*    */   public static boolean b(int i) {
/* 45 */     return d.containsKey(i);
/*    */   }
/*    */   public final int compressionTypeId() {
/* 48 */     return a();
/*    */   } public int a() {
/* 50 */     return this.e;
/*    */   }
/*    */   
/*    */   public OutputStream a(OutputStream outputstream) throws IOException {
/* 54 */     return this.g.wrap(outputstream);
/*    */   }
/*    */   public final InputStream wrap(InputStream inputstream) throws IOException {
/* 57 */     return a(inputstream);
/*    */   } public InputStream a(InputStream inputstream) throws IOException {
/* 59 */     return this.f.wrap(inputstream);
/*    */   }
/*    */   
/*    */   @FunctionalInterface
/*    */   static interface a<O> {
/*    */     O wrap(O param1O) throws IOException;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\RegionFileCompression.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */