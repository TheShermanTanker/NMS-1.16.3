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
/*     */ public class NBTTagShort
/*     */   extends NBTNumber
/*     */ {
/*     */   static class a
/*     */   {
/*  21 */     static final NBTTagShort[] a = new NBTTagShort[1153];
/*     */     
/*     */     static {
/*  24 */       for (int var0 = 0; var0 < a.length; var0++)
/*  25 */         a[var0] = new NBTTagShort((short)(-128 + var0)); 
/*     */     }
/*     */   }
/*     */   
/*  29 */   public static final NBTTagType<NBTTagShort> a = new NBTTagType<NBTTagShort>()
/*     */     {
/*     */       public NBTTagShort b(DataInput var0, int var1, NBTReadLimiter var2) throws IOException {
/*  32 */         var2.a(80L);
/*  33 */         return NBTTagShort.a(var0.readShort());
/*     */       }
/*     */ 
/*     */       
/*     */       public String a() {
/*  38 */         return "SHORT";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  43 */         return "TAG_Short";
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean c() {
/*  48 */         return true;
/*     */       }
/*     */     };
/*     */   
/*     */   private final short data;
/*     */   
/*     */   private NBTTagShort(short var0) {
/*  55 */     this.data = var0;
/*     */   }
/*     */   
/*     */   public static NBTTagShort a(short var0) {
/*  59 */     if (var0 >= -128 && var0 <= 1024)
/*  60 */       return a.a[var0 + 128]; 
/*  61 */     return new NBTTagShort(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput var0) throws IOException {
/*  66 */     var0.writeShort(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  71 */     return 2;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagShort> b() {
/*  76 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  81 */     return this.data + "s";
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagShort clone() {
/*  86 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  91 */     if (this == var0) {
/*  92 */       return true;
/*     */     }
/*     */     
/*  95 */     return (var0 instanceof NBTTagShort && this.data == ((NBTTagShort)var0).data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 100 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String var0, int var1) {
/* 105 */     IChatBaseComponent var2 = (new ChatComponentText("s")).a(g);
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
/* 116 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public short asShort() {
/* 121 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte asByte() {
/* 126 */     return (byte)(this.data & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public double asDouble() {
/* 131 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public float asFloat() {
/* 136 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public Number k() {
/* 141 */     return Short.valueOf(this.data);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagShort.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */