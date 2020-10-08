/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Preconditions;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ArrayUtils;
/*     */ 
/*     */ public class NBTTagByteArray extends NBTList<NBTTagByte> {
/*  12 */   public static final NBTTagType<NBTTagByteArray> a = new NBTTagType<NBTTagByteArray>()
/*     */     {
/*     */       public NBTTagByteArray b(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
/*  15 */         nbtreadlimiter.a(192L);
/*  16 */         int j = datainput.readInt();
/*  17 */         Preconditions.checkArgument((j < 16777216));
/*     */         
/*  19 */         nbtreadlimiter.a(8L * j);
/*  20 */         byte[] abyte = new byte[j];
/*     */         
/*  22 */         datainput.readFully(abyte);
/*  23 */         return new NBTTagByteArray(abyte);
/*     */       }
/*     */ 
/*     */       
/*     */       public String a() {
/*  28 */         return "BYTE[]";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  33 */         return "TAG_Byte_Array";
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public NBTTagByteArray(byte[] abyte) {
/*  39 */     this.data = abyte;
/*     */   }
/*     */   private byte[] data;
/*     */   public NBTTagByteArray(List<Byte> list) {
/*  43 */     this(a(list));
/*     */   }
/*     */   
/*     */   private static byte[] a(List<Byte> list) {
/*  47 */     byte[] abyte = new byte[list.size()];
/*     */     
/*  49 */     for (int i = 0; i < list.size(); i++) {
/*  50 */       Byte obyte = list.get(i);
/*     */       
/*  52 */       abyte[i] = (obyte == null) ? 0 : obyte.byteValue();
/*     */     } 
/*     */     
/*  55 */     return abyte;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput dataoutput) throws IOException {
/*  60 */     dataoutput.writeInt(this.data.length);
/*  61 */     dataoutput.write(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  66 */     return 7;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagByteArray> b() {
/*  71 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  76 */     StringBuilder stringbuilder = new StringBuilder("[B;");
/*     */     
/*  78 */     for (int i = 0; i < this.data.length; i++) {
/*  79 */       if (i != 0) {
/*  80 */         stringbuilder.append(',');
/*     */       }
/*     */       
/*  83 */       stringbuilder.append(this.data[i]).append('B');
/*     */     } 
/*     */     
/*  86 */     return stringbuilder.append(']').toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase clone() {
/*  91 */     byte[] abyte = new byte[this.data.length];
/*     */     
/*  93 */     System.arraycopy(this.data, 0, abyte, 0, this.data.length);
/*  94 */     return new NBTTagByteArray(abyte);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/*  98 */     return (this == object) ? true : ((object instanceof NBTTagByteArray && Arrays.equals(this.data, ((NBTTagByteArray)object).data)));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 102 */     return Arrays.hashCode(this.data);
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String s, int i) {
/* 107 */     IChatMutableComponent ichatmutablecomponent = (new ChatComponentText("B")).a(g);
/* 108 */     IChatMutableComponent ichatmutablecomponent1 = (new ChatComponentText("[")).addSibling(ichatmutablecomponent).c(";");
/*     */     
/* 110 */     for (int j = 0; j < this.data.length; j++) {
/* 111 */       IChatMutableComponent ichatmutablecomponent2 = (new ChatComponentText(String.valueOf(this.data[j]))).a(f);
/*     */       
/* 113 */       ichatmutablecomponent1.c(" ").addSibling(ichatmutablecomponent2).addSibling(ichatmutablecomponent);
/* 114 */       if (j != this.data.length - 1) {
/* 115 */         ichatmutablecomponent1.c(",");
/*     */       }
/*     */     } 
/*     */     
/* 119 */     ichatmutablecomponent1.c("]");
/* 120 */     return ichatmutablecomponent1;
/*     */   }
/*     */   
/*     */   public byte[] getBytes() {
/* 124 */     return this.data;
/*     */   }
/*     */   
/*     */   public int size() {
/* 128 */     return this.data.length;
/*     */   }
/*     */   
/*     */   public NBTTagByte get(int i) {
/* 132 */     return NBTTagByte.a(this.data[i]);
/*     */   }
/*     */   
/*     */   public NBTTagByte set(int i, NBTTagByte nbttagbyte) {
/* 136 */     byte b0 = this.data[i];
/*     */     
/* 138 */     this.data[i] = nbttagbyte.asByte();
/* 139 */     return NBTTagByte.a(b0);
/*     */   }
/*     */   
/*     */   public void add(int i, NBTTagByte nbttagbyte) {
/* 143 */     this.data = ArrayUtils.add(this.data, i, nbttagbyte.asByte());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(int i, NBTBase nbtbase) {
/* 148 */     if (nbtbase instanceof NBTNumber) {
/* 149 */       this.data[i] = ((NBTNumber)nbtbase).asByte();
/* 150 */       return true;
/*     */     } 
/* 152 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(int i, NBTBase nbtbase) {
/* 158 */     if (nbtbase instanceof NBTNumber) {
/* 159 */       this.data = ArrayUtils.add(this.data, i, ((NBTNumber)nbtbase).asByte());
/* 160 */       return true;
/*     */     } 
/* 162 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagByte remove(int i) {
/* 168 */     byte b0 = this.data[i];
/*     */     
/* 170 */     this.data = ArrayUtils.remove(this.data, i);
/* 171 */     return NBTTagByte.a(b0);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte d_() {
/* 176 */     return 1;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 180 */     this.data = new byte[0];
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagByteArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */