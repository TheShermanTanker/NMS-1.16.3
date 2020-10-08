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
/*     */ public class NBTTagByte
/*     */   extends NBTNumber
/*     */ {
/*     */   static class a
/*     */   {
/*  19 */     private static final NBTTagByte[] a = new NBTTagByte[256];
/*     */     
/*     */     static {
/*  22 */       for (int var0 = 0; var0 < a.length; var0++)
/*  23 */         a[var0] = new NBTTagByte((byte)(var0 - 128)); 
/*     */     }
/*     */   }
/*     */   
/*  27 */   public static final NBTTagType<NBTTagByte> a = new NBTTagType<NBTTagByte>()
/*     */     {
/*     */       public NBTTagByte b(DataInput var0, int var1, NBTReadLimiter var2) throws IOException {
/*  30 */         var2.a(72L);
/*  31 */         return NBTTagByte.a(var0.readByte());
/*     */       }
/*     */ 
/*     */       
/*     */       public String a() {
/*  36 */         return "BYTE";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  41 */         return "TAG_Byte";
/*     */       }
/*     */ 
/*     */       
/*     */       public boolean c() {
/*  46 */         return true;
/*     */       }
/*     */     };
/*     */   
/*  50 */   public static final NBTTagByte b = a((byte)0);
/*  51 */   public static final NBTTagByte c = a((byte)1);
/*     */   
/*     */   private final byte data;
/*     */   
/*     */   private NBTTagByte(byte var0) {
/*  56 */     this.data = var0;
/*     */   }
/*     */   
/*     */   public static NBTTagByte a(byte var0) {
/*  60 */     return a.a()[128 + var0];
/*     */   }
/*     */   
/*     */   public static NBTTagByte a(boolean var0) {
/*  64 */     return var0 ? c : b;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput var0) throws IOException {
/*  69 */     var0.writeByte(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  74 */     return 1;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagByte> b() {
/*  79 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  84 */     return this.data + "b";
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagByte clone() {
/*  89 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/*  94 */     if (this == var0) {
/*  95 */       return true;
/*     */     }
/*     */     
/*  98 */     return (var0 instanceof NBTTagByte && this.data == ((NBTTagByte)var0).data);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 103 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String var0, int var1) {
/* 108 */     IChatBaseComponent var2 = (new ChatComponentText("b")).a(g);
/* 109 */     return (new ChatComponentText(String.valueOf(this.data))).addSibling(var2).a(f);
/*     */   }
/*     */ 
/*     */   
/*     */   public long asLong() {
/* 114 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public int asInt() {
/* 119 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public short asShort() {
/* 124 */     return (short)this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public byte asByte() {
/* 129 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public double asDouble() {
/* 134 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public float asFloat() {
/* 139 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public Number k() {
/* 144 */     return Byte.valueOf(this.data);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagByte.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */