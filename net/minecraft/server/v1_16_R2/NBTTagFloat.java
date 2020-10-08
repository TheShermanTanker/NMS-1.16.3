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
/*     */ public class NBTTagFloat
/*     */   extends NBTNumber
/*     */ {
/*  19 */   public static final NBTTagFloat a = new NBTTagFloat(0.0F);
/*     */   
/*  21 */   public static final NBTTagType<NBTTagFloat> b = new NBTTagType<NBTTagFloat>()
/*     */     {
/*     */       public NBTTagFloat b(DataInput var0, int var1, NBTReadLimiter var2) throws IOException {
/*  24 */         var2.a(96L);
/*  25 */         return NBTTagFloat.a(var0.readFloat());
/*     */       }
/*     */ 
/*     */       
/*     */       public String a() {
/*  30 */         return "FLOAT";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  35 */         return "TAG_Float";
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean c() {
/*  40 */         return true;
/*     */       }
/*     */     };
/*     */   
/*     */   private final float data;
/*     */   
/*     */   private NBTTagFloat(float var0) {
/*  47 */     this.data = var0;
/*     */   }
/*     */   
/*     */   public static NBTTagFloat a(float var0) {
/*  51 */     if (var0 == 0.0F) {
/*  52 */       return a;
/*     */     }
/*  54 */     return new NBTTagFloat(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput var0) throws IOException {
/*  59 */     var0.writeFloat(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  64 */     return 5;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagFloat> b() {
/*  69 */     return b;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  74 */     return this.data + "f";
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagFloat clone() {
/*  79 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  84 */     if (this == var0) {
/*  85 */       return true;
/*     */     }
/*     */     
/*  88 */     return (var0 instanceof NBTTagFloat && this.data == ((NBTTagFloat)var0).data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  93 */     return Float.floatToIntBits(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String var0, int var1) {
/*  98 */     IChatBaseComponent var2 = (new ChatComponentText("f")).a(g);
/*  99 */     return (new ChatComponentText(String.valueOf(this.data))).addSibling(var2).a(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public long asLong() {
/* 104 */     return (long)this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public int asInt() {
/* 109 */     return MathHelper.d(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public short asShort() {
/* 114 */     return (short)(MathHelper.d(this.data) & 0xFFFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte asByte() {
/* 119 */     return (byte)(MathHelper.d(this.data) & 0xFF);
/*     */   }
/*     */ 
/*     */   
/*     */   public double asDouble() {
/* 124 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public float asFloat() {
/* 129 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public Number k() {
/* 134 */     return Float.valueOf(this.data);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagFloat.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */