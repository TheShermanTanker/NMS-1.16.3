/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
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
/*     */ public class NBTTagLong
/*     */   extends NBTNumber
/*     */ {
/*     */   static class a
/*     */   {
/*  21 */     static final NBTTagLong[] a = new NBTTagLong[1153];
/*     */     
/*     */     static {
/*  24 */       for (int var0 = 0; var0 < a.length; var0++)
/*  25 */         a[var0] = new NBTTagLong((-128 + var0)); 
/*     */     }
/*     */   }
/*     */   
/*  29 */   public static final NBTTagType<NBTTagLong> a = new NBTTagType<NBTTagLong>()
/*     */     {
/*     */       public NBTTagLong b(DataInput var0, int var1, NBTReadLimiter var2) throws IOException {
/*  32 */         var2.a(128L);
/*  33 */         return NBTTagLong.a(var0.readLong());
/*     */       }
/*     */ 
/*     */       
/*     */       public String a() {
/*  38 */         return "LONG";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  43 */         return "TAG_Long";
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean c() {
/*  48 */         return true;
/*     */       }
/*     */     };
/*     */   
/*     */   private final long data;
/*     */   
/*     */   private NBTTagLong(long var0) {
/*  55 */     this.data = var0;
/*     */   }
/*     */   
/*     */   public static NBTTagLong a(long var0) {
/*  59 */     if (var0 >= -128L && var0 <= 1024L)
/*  60 */       return a.a[(int)var0 + 128]; 
/*  61 */     return new NBTTagLong(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput var0) throws IOException {
/*  66 */     var0.writeLong(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  71 */     return 4;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagLong> b() {
/*  76 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  81 */     return this.data + "L";
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagLong clone() {
/*  86 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  91 */     if (this == var0) {
/*  92 */       return true;
/*     */     }
/*     */     
/*  95 */     return (var0 instanceof NBTTagLong && this.data == ((NBTTagLong)var0).data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 100 */     return (int)(this.data ^ this.data >>> 32L);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String var0, int var1) {
/* 105 */     IChatBaseComponent var2 = (new ChatComponentText("L")).a(g);
/* 106 */     return (new ChatComponentText(String.valueOf(this.data))).addSibling(var2).a(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public long asLong() {
/* 111 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public int asInt() {
/* 116 */     return (int)(this.data & 0xFFFFFFFFFFFFFFFFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public short asShort() {
/* 121 */     return (short)(int)(this.data & 0xFFFFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte asByte() {
/* 126 */     return (byte)(int)(this.data & 0xFFL);
/*     */   }
/*     */ 
/*     */   
/*     */   public double asDouble() {
/* 131 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public float asFloat() {
/* 136 */     return (float)this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public Number k() {
/* 141 */     return Long.valueOf(this.data);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagLong.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */