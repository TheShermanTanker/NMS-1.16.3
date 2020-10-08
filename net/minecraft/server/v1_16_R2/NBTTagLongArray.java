/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.LongSet;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
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
/*     */ public class NBTTagLongArray
/*     */   extends NBTList<NBTTagLong>
/*     */ {
/*  24 */   public static final NBTTagType<NBTTagLongArray> a = new NBTTagType<NBTTagLongArray>()
/*     */     {
/*     */       public NBTTagLongArray b(DataInput var0, int var1, NBTReadLimiter var2) throws IOException {
/*  27 */         var2.a(192L);
/*     */         
/*  29 */         int var3 = var0.readInt();
/*  30 */         var2.a(64L * var3);
/*  31 */         long[] var4 = new long[var3];
/*  32 */         for (int var5 = 0; var5 < var3; var5++) {
/*  33 */           var4[var5] = var0.readLong();
/*     */         }
/*  35 */         return new NBTTagLongArray(var4);
/*     */       }
/*     */ 
/*     */       
/*     */       public String a() {
/*  40 */         return "LONG[]";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  45 */         return "TAG_Long_Array";
/*     */       }
/*     */     };
/*     */   
/*     */   private long[] b;
/*     */   
/*     */   public NBTTagLongArray(long[] var0) {
/*  52 */     this.b = var0;
/*     */   }
/*     */   
/*     */   public NBTTagLongArray(LongSet var0) {
/*  56 */     this.b = var0.toLongArray();
/*     */   }
/*     */   
/*     */   public NBTTagLongArray(List<Long> var0) {
/*  60 */     this(a(var0));
/*     */   }
/*     */   
/*     */   private static long[] a(List<Long> var0) {
/*  64 */     long[] var1 = new long[var0.size()];
/*  65 */     for (int var2 = 0; var2 < var0.size(); var2++) {
/*  66 */       Long var3 = var0.get(var2);
/*  67 */       var1[var2] = (var3 == null) ? 0L : var3.longValue();
/*     */     } 
/*     */     
/*  70 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput var0) throws IOException {
/*  75 */     var0.writeInt(this.b.length);
/*  76 */     for (long var4 : this.b) {
/*  77 */       var0.writeLong(var4);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  83 */     return 12;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagLongArray> b() {
/*  88 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  93 */     StringBuilder var0 = new StringBuilder("[L;");
/*  94 */     for (int var1 = 0; var1 < this.b.length; var1++) {
/*  95 */       if (var1 != 0) {
/*  96 */         var0.append(',');
/*     */       }
/*  98 */       var0.append(this.b[var1]).append('L');
/*     */     } 
/* 100 */     return var0.append(']').toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagLongArray clone() {
/* 105 */     long[] var0 = new long[this.b.length];
/* 106 */     System.arraycopy(this.b, 0, var0, 0, this.b.length);
/* 107 */     return new NBTTagLongArray(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/* 112 */     if (this == var0) {
/* 113 */       return true;
/*     */     }
/*     */     
/* 116 */     return (var0 instanceof NBTTagLongArray && Arrays.equals(this.b, ((NBTTagLongArray)var0).b));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 121 */     return Arrays.hashCode(this.b);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String var0, int var1) {
/* 126 */     IChatBaseComponent var2 = (new ChatComponentText("L")).a(g);
/* 127 */     IChatMutableComponent var3 = (new ChatComponentText("[")).addSibling(var2).c(";");
/*     */     
/* 129 */     for (int var4 = 0; var4 < this.b.length; var4++) {
/* 130 */       IChatMutableComponent var5 = (new ChatComponentText(String.valueOf(this.b[var4]))).a(f);
/* 131 */       var3.c(" ").addSibling(var5).addSibling(var2);
/* 132 */       if (var4 != this.b.length - 1) {
/* 133 */         var3.c(",");
/*     */       }
/*     */     } 
/*     */     
/* 137 */     var3.c("]");
/*     */     
/* 139 */     return var3;
/*     */   }
/*     */   
/*     */   public long[] getLongs() {
/* 143 */     return this.b;
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 148 */     return this.b.length;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagLong get(int var0) {
/* 153 */     return NBTTagLong.a(this.b[var0]);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagLong set(int var0, NBTTagLong var1) {
/* 158 */     long var2 = this.b[var0];
/* 159 */     this.b[var0] = var1.asLong();
/* 160 */     return NBTTagLong.a(var2);
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(int var0, NBTTagLong var1) {
/* 165 */     this.b = ArrayUtils.add(this.b, var0, var1.asLong());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(int var0, NBTBase var1) {
/* 170 */     if (var1 instanceof NBTNumber) {
/* 171 */       this.b[var0] = ((NBTNumber)var1).asLong();
/* 172 */       return true;
/*     */     } 
/* 174 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(int var0, NBTBase var1) {
/* 179 */     if (var1 instanceof NBTNumber) {
/* 180 */       this.b = ArrayUtils.add(this.b, var0, ((NBTNumber)var1).asLong());
/* 181 */       return true;
/*     */     } 
/* 183 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagLong remove(int var0) {
/* 188 */     long var1 = this.b[var0];
/* 189 */     this.b = ArrayUtils.remove(this.b, var0);
/* 190 */     return NBTTagLong.a(var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte d_() {
/* 195 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public void clear() {
/* 200 */     this.b = new long[0];
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagLongArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */