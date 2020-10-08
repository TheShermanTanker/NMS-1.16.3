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
/*     */ public class NBTTagIntArray extends NBTList<NBTTagInt> {
/*  12 */   public static final NBTTagType<NBTTagIntArray> a = new NBTTagType<NBTTagIntArray>()
/*     */     {
/*     */       public NBTTagIntArray b(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
/*  15 */         nbtreadlimiter.a(192L);
/*  16 */         int j = datainput.readInt();
/*  17 */         Preconditions.checkArgument((j < 16777216));
/*     */         
/*  19 */         nbtreadlimiter.a(32L * j);
/*  20 */         int[] aint = new int[j];
/*     */         
/*  22 */         for (int k = 0; k < j; k++) {
/*  23 */           aint[k] = datainput.readInt();
/*     */         }
/*     */         
/*  26 */         return new NBTTagIntArray(aint);
/*     */       }
/*     */ 
/*     */       
/*     */       public String a() {
/*  31 */         return "INT[]";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  36 */         return "TAG_Int_Array";
/*     */       }
/*     */     };
/*     */ 
/*     */   
/*     */   public NBTTagIntArray(int[] aint) {
/*  42 */     this.data = aint;
/*     */   }
/*     */   private int[] data;
/*     */   public NBTTagIntArray(List<Integer> list) {
/*  46 */     this(a(list));
/*     */   }
/*     */   
/*     */   private static int[] a(List<Integer> list) {
/*  50 */     int[] aint = new int[list.size()];
/*     */     
/*  52 */     for (int i = 0; i < list.size(); i++) {
/*  53 */       Integer integer = list.get(i);
/*     */       
/*  55 */       aint[i] = (integer == null) ? 0 : integer.intValue();
/*     */     } 
/*     */     
/*  58 */     return aint;
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput dataoutput) throws IOException {
/*  63 */     dataoutput.writeInt(this.data.length);
/*  64 */     int[] aint = this.data;
/*  65 */     int i = aint.length;
/*     */     
/*  67 */     for (int j = 0; j < i; j++) {
/*  68 */       int k = aint[j];
/*     */       
/*  70 */       dataoutput.writeInt(k);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  77 */     return 11;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagIntArray> b() {
/*  82 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  87 */     StringBuilder stringbuilder = new StringBuilder("[I;");
/*     */     
/*  89 */     for (int i = 0; i < this.data.length; i++) {
/*  90 */       if (i != 0) {
/*  91 */         stringbuilder.append(',');
/*     */       }
/*     */       
/*  94 */       stringbuilder.append(this.data[i]);
/*     */     } 
/*     */     
/*  97 */     return stringbuilder.append(']').toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagIntArray clone() {
/* 102 */     int[] aint = new int[this.data.length];
/*     */     
/* 104 */     System.arraycopy(this.data, 0, aint, 0, this.data.length);
/* 105 */     return new NBTTagIntArray(aint);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 109 */     return (this == object) ? true : ((object instanceof NBTTagIntArray && Arrays.equals(this.data, ((NBTTagIntArray)object).data)));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 113 */     return Arrays.hashCode(this.data);
/*     */   }
/*     */   
/*     */   public int[] getInts() {
/* 117 */     return this.data;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String s, int i) {
/* 122 */     IChatMutableComponent ichatmutablecomponent = (new ChatComponentText("I")).a(g);
/* 123 */     IChatMutableComponent ichatmutablecomponent1 = (new ChatComponentText("[")).addSibling(ichatmutablecomponent).c(";");
/*     */     
/* 125 */     for (int j = 0; j < this.data.length; j++) {
/* 126 */       ichatmutablecomponent1.c(" ").addSibling((new ChatComponentText(String.valueOf(this.data[j]))).a(f));
/* 127 */       if (j != this.data.length - 1) {
/* 128 */         ichatmutablecomponent1.c(",");
/*     */       }
/*     */     } 
/*     */     
/* 132 */     ichatmutablecomponent1.c("]");
/* 133 */     return ichatmutablecomponent1;
/*     */   }
/*     */   
/*     */   public int size() {
/* 137 */     return this.data.length;
/*     */   }
/*     */   
/*     */   public NBTTagInt get(int i) {
/* 141 */     return NBTTagInt.a(this.data[i]);
/*     */   }
/*     */   
/*     */   public NBTTagInt set(int i, NBTTagInt nbttagint) {
/* 145 */     int j = this.data[i];
/*     */     
/* 147 */     this.data[i] = nbttagint.asInt();
/* 148 */     return NBTTagInt.a(j);
/*     */   }
/*     */   
/*     */   public void add(int i, NBTTagInt nbttagint) {
/* 152 */     this.data = ArrayUtils.add(this.data, i, nbttagint.asInt());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(int i, NBTBase nbtbase) {
/* 157 */     if (nbtbase instanceof NBTNumber) {
/* 158 */       this.data[i] = ((NBTNumber)nbtbase).asInt();
/* 159 */       return true;
/*     */     } 
/* 161 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(int i, NBTBase nbtbase) {
/* 167 */     if (nbtbase instanceof NBTNumber) {
/* 168 */       this.data = ArrayUtils.add(this.data, i, ((NBTNumber)nbtbase).asInt());
/* 169 */       return true;
/*     */     } 
/* 171 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagInt remove(int i) {
/* 177 */     int j = this.data[i];
/*     */     
/* 179 */     this.data = ArrayUtils.remove(this.data, i);
/* 180 */     return NBTTagInt.a(j);
/*     */   }
/*     */ 
/*     */   
/*     */   public byte d_() {
/* 185 */     return 3;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 189 */     this.data = new int[0];
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagIntArray.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */