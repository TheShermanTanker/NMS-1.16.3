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
/*     */ public class NBTTagInt
/*     */   extends NBTNumber
/*     */ {
/*     */   static class a
/*     */   {
/*  21 */     static final NBTTagInt[] a = new NBTTagInt[1153];
/*     */     
/*     */     static {
/*  24 */       for (int var0 = 0; var0 < a.length; var0++)
/*  25 */         a[var0] = new NBTTagInt(-128 + var0); 
/*     */     }
/*     */   }
/*     */   
/*  29 */   public static final NBTTagType<NBTTagInt> a = new NBTTagType<NBTTagInt>()
/*     */     {
/*     */       public NBTTagInt b(DataInput var0, int var1, NBTReadLimiter var2) throws IOException {
/*  32 */         var2.a(96L);
/*  33 */         return NBTTagInt.a(var0.readInt());
/*     */       }
/*     */ 
/*     */       
/*     */       public String a() {
/*  38 */         return "INT";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  43 */         return "TAG_Int";
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean c() {
/*  48 */         return true;
/*     */       }
/*     */     };
/*     */   
/*     */   private final int data;
/*     */   
/*     */   private NBTTagInt(int var0) {
/*  55 */     this.data = var0;
/*     */   }
/*     */   
/*     */   public static NBTTagInt a(int var0) {
/*  59 */     if (var0 >= -128 && var0 <= 1024)
/*  60 */       return a.a[var0 + 128]; 
/*  61 */     return new NBTTagInt(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput var0) throws IOException {
/*  66 */     var0.writeInt(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  71 */     return 3;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagInt> b() {
/*  76 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  81 */     return String.valueOf(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagInt clone() {
/*  86 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  91 */     if (this == var0) {
/*  92 */       return true;
/*     */     }
/*     */     
/*  95 */     return (var0 instanceof NBTTagInt && this.data == ((NBTTagInt)var0).data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 100 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String var0, int var1) {
/* 105 */     return (new ChatComponentText(String.valueOf(this.data))).a(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public long asLong() {
/* 110 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public int asInt() {
/* 115 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public short asShort() {
/* 120 */     return (short)(this.data & 0xFFFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte asByte() {
/* 125 */     return (byte)(this.data & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public double asDouble() {
/* 130 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public float asFloat() {
/* 135 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public Number k() {
/* 140 */     return Integer.valueOf(this.data);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagInt.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */