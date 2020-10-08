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
/*     */ public class NBTTagDouble
/*     */   extends NBTNumber
/*     */ {
/*  19 */   public static final NBTTagDouble a = new NBTTagDouble(0.0D);
/*     */   
/*  21 */   public static final NBTTagType<NBTTagDouble> b = new NBTTagType<NBTTagDouble>()
/*     */     {
/*     */       public NBTTagDouble b(DataInput var0, int var1, NBTReadLimiter var2) throws IOException {
/*  24 */         var2.a(128L);
/*  25 */         return NBTTagDouble.a(var0.readDouble());
/*     */       }
/*     */ 
/*     */       
/*     */       public String a() {
/*  30 */         return "DOUBLE";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  35 */         return "TAG_Double";
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean c() {
/*  40 */         return true;
/*     */       }
/*     */     };
/*     */   
/*     */   private final double data;
/*     */   
/*     */   private NBTTagDouble(double var0) {
/*  47 */     this.data = var0;
/*     */   }
/*     */   
/*     */   public static NBTTagDouble a(double var0) {
/*  51 */     if (var0 == 0.0D) {
/*  52 */       return a;
/*     */     }
/*  54 */     return new NBTTagDouble(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput var0) throws IOException {
/*  59 */     var0.writeDouble(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  64 */     return 6;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagDouble> b() {
/*  69 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  74 */     return this.data + "d";
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagDouble clone() {
/*  79 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  84 */     if (this == var0) {
/*  85 */       return true;
/*     */     }
/*     */     
/*  88 */     return (var0 instanceof NBTTagDouble && this.data == ((NBTTagDouble)var0).data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  93 */     long var0 = Double.doubleToLongBits(this.data);
/*  94 */     return (int)(var0 ^ var0 >>> 32L);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String var0, int var1) {
/*  99 */     IChatBaseComponent var2 = (new ChatComponentText("d")).a(g);
/* 100 */     return (new ChatComponentText(String.valueOf(this.data))).addSibling(var2).a(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public long asLong() {
/* 105 */     return (long)Math.floor(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int asInt() {
/* 110 */     return MathHelper.floor(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public short asShort() {
/* 115 */     return (short)(MathHelper.floor(this.data) & 0xFFFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte asByte() {
/* 120 */     return (byte)(MathHelper.floor(this.data) & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public double asDouble() {
/* 125 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public float asFloat() {
/* 130 */     return (float)this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public Number k() {
/* 135 */     return Double.valueOf(this.data);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagDouble.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */