/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import com.google.common.collect.Iterables;
/*     */ import com.google.common.collect.Lists;
/*     */ import it.unimi.dsi.fastutil.bytes.ByteOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.bytes.ByteSet;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Arrays;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ 
/*     */ public class NBTTagList
/*     */   extends NBTList<NBTBase> {
/*  18 */   public static final NBTTagType<NBTTagList> a = new NBTTagType<NBTTagList>()
/*     */     {
/*     */       public NBTTagList b(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
/*  21 */         nbtreadlimiter.a(296L);
/*  22 */         if (i > 512) {
/*  23 */           throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
/*     */         }
/*  25 */         byte b0 = datainput.readByte();
/*  26 */         int j = datainput.readInt();
/*     */         
/*  28 */         if (b0 == 0 && j > 0) {
/*  29 */           throw new RuntimeException("Missing type on ListTag");
/*     */         }
/*  31 */         nbtreadlimiter.a(32L * j);
/*  32 */         NBTTagType<?> nbttagtype = NBTTagTypes.a(b0);
/*  33 */         List<NBTBase> list = Lists.newArrayListWithCapacity(j);
/*     */         
/*  35 */         for (int k = 0; k < j; k++) {
/*  36 */           list.add((NBTBase)nbttagtype.b(datainput, i + 1, nbtreadlimiter));
/*     */         }
/*     */         
/*  39 */         return new NBTTagList(list, b0);
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       public String a() {
/*  46 */         return "LIST";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  51 */         return "TAG_List";
/*     */       }
/*     */     };
/*  54 */   private static final ByteSet b = (ByteSet)new ByteOpenHashSet(Arrays.asList(new Byte[] { Byte.valueOf((byte)1), Byte.valueOf((byte)2), Byte.valueOf((byte)3), Byte.valueOf((byte)4), Byte.valueOf((byte)5), Byte.valueOf((byte)6) }));
/*     */   private final List<NBTBase> list;
/*     */   private byte type;
/*     */   
/*     */   private NBTTagList(List<NBTBase> list, byte b0) {
/*  59 */     this.list = list;
/*  60 */     this.type = b0;
/*     */   }
/*     */   
/*     */   public NBTTagList() {
/*  64 */     this(Lists.newArrayList(), (byte)0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput dataoutput) throws IOException {
/*  69 */     if (this.list.isEmpty()) {
/*  70 */       this.type = 0;
/*     */     } else {
/*  72 */       this.type = ((NBTBase)this.list.get(0)).getTypeId();
/*     */     } 
/*     */     
/*  75 */     dataoutput.writeByte(this.type);
/*  76 */     dataoutput.writeInt(this.list.size());
/*  77 */     Iterator<NBTBase> iterator = this.list.iterator();
/*     */     
/*  79 */     while (iterator.hasNext()) {
/*  80 */       NBTBase nbtbase = iterator.next();
/*     */       
/*  82 */       nbtbase.write(dataoutput);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/*  89 */     return 9;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagList> b() {
/*  94 */     return a;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  99 */     StringBuilder stringbuilder = new StringBuilder("[");
/*     */     
/* 101 */     for (int i = 0; i < this.list.size(); i++) {
/* 102 */       if (i != 0) {
/* 103 */         stringbuilder.append(',');
/*     */       }
/*     */       
/* 106 */       stringbuilder.append(this.list.get(i));
/*     */     } 
/*     */     
/* 109 */     return stringbuilder.append(']').toString();
/*     */   }
/*     */   
/*     */   private void g() {
/* 113 */     if (this.list.isEmpty()) {
/* 114 */       this.type = 0;
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTBase remove(int i) {
/* 121 */     NBTBase nbtbase = this.list.remove(i);
/*     */     
/* 123 */     g();
/* 124 */     return nbtbase;
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 128 */     return this.list.isEmpty();
/*     */   }
/*     */   
/*     */   public NBTTagCompound getCompound(int i) {
/* 132 */     if (i >= 0 && i < this.list.size()) {
/* 133 */       NBTBase nbtbase = this.list.get(i);
/*     */       
/* 135 */       if (nbtbase.getTypeId() == 10) {
/* 136 */         return (NBTTagCompound)nbtbase;
/*     */       }
/*     */     } 
/*     */     
/* 140 */     return new NBTTagCompound();
/*     */   }
/*     */   
/*     */   public NBTTagList b(int i) {
/* 144 */     if (i >= 0 && i < this.list.size()) {
/* 145 */       NBTBase nbtbase = this.list.get(i);
/*     */       
/* 147 */       if (nbtbase.getTypeId() == 9) {
/* 148 */         return (NBTTagList)nbtbase;
/*     */       }
/*     */     } 
/*     */     
/* 152 */     return new NBTTagList();
/*     */   }
/*     */   
/*     */   public short d(int i) {
/* 156 */     if (i >= 0 && i < this.list.size()) {
/* 157 */       NBTBase nbtbase = this.list.get(i);
/*     */       
/* 159 */       if (nbtbase.getTypeId() == 2) {
/* 160 */         return ((NBTTagShort)nbtbase).asShort();
/*     */       }
/*     */     } 
/*     */     
/* 164 */     return 0;
/*     */   }
/*     */   
/*     */   public int e(int i) {
/* 168 */     if (i >= 0 && i < this.list.size()) {
/* 169 */       NBTBase nbtbase = this.list.get(i);
/*     */       
/* 171 */       if (nbtbase.getTypeId() == 3) {
/* 172 */         return ((NBTTagInt)nbtbase).asInt();
/*     */       }
/*     */     } 
/*     */     
/* 176 */     return 0;
/*     */   }
/*     */   
/*     */   public int[] f(int i) {
/* 180 */     if (i >= 0 && i < this.list.size()) {
/* 181 */       NBTBase nbtbase = this.list.get(i);
/*     */       
/* 183 */       if (nbtbase.getTypeId() == 11) {
/* 184 */         return ((NBTTagIntArray)nbtbase).getInts();
/*     */       }
/*     */     } 
/*     */     
/* 188 */     return new int[0];
/*     */   }
/*     */   public final double getDoubleAt(int i) {
/* 191 */     return h(i);
/*     */   } public double h(int i) {
/* 193 */     if (i >= 0 && i < this.list.size()) {
/* 194 */       NBTBase nbtbase = this.list.get(i);
/*     */       
/* 196 */       if (nbtbase.getTypeId() == 6) {
/* 197 */         return ((NBTTagDouble)nbtbase).asDouble();
/*     */       }
/*     */     } 
/*     */     
/* 201 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public float i(int i) {
/* 205 */     if (i >= 0 && i < this.list.size()) {
/* 206 */       NBTBase nbtbase = this.list.get(i);
/*     */       
/* 208 */       if (nbtbase.getTypeId() == 5) {
/* 209 */         return ((NBTTagFloat)nbtbase).asFloat();
/*     */       }
/*     */     } 
/*     */     
/* 213 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public String getString(int i) {
/* 217 */     if (i >= 0 && i < this.list.size()) {
/* 218 */       NBTBase nbtbase = this.list.get(i);
/*     */       
/* 220 */       return (nbtbase.getTypeId() == 8) ? nbtbase.asString() : nbtbase.toString();
/*     */     } 
/* 222 */     return "";
/*     */   }
/*     */ 
/*     */   
/*     */   public int size() {
/* 227 */     return this.list.size();
/*     */   }
/*     */   
/*     */   public NBTBase get(int i) {
/* 231 */     return this.list.get(i);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTBase set(int i, NBTBase nbtbase) {
/* 236 */     NBTBase nbtbase1 = get(i);
/*     */     
/* 238 */     if (!a(i, nbtbase)) {
/* 239 */       throw new UnsupportedOperationException(String.format("Trying to add tag of type %d to list of %d", new Object[] { Byte.valueOf(nbtbase.getTypeId()), Byte.valueOf(this.type) }));
/*     */     }
/* 241 */     return nbtbase1;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void add(int i, NBTBase nbtbase) {
/* 247 */     if (!b(i, nbtbase)) {
/* 248 */       throw new UnsupportedOperationException(String.format("Trying to add tag of type %d to list of %d", new Object[] { Byte.valueOf(nbtbase.getTypeId()), Byte.valueOf(this.type) }));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(int i, NBTBase nbtbase) {
/* 254 */     if (a(nbtbase)) {
/* 255 */       this.list.set(i, nbtbase);
/* 256 */       return true;
/*     */     } 
/* 258 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean b(int i, NBTBase nbtbase) {
/* 264 */     if (a(nbtbase)) {
/* 265 */       this.list.add(i, nbtbase);
/* 266 */       return true;
/*     */     } 
/* 268 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   private boolean a(NBTBase nbtbase) {
/* 273 */     if (nbtbase.getTypeId() == 0)
/* 274 */       return false; 
/* 275 */     if (this.type == 0) {
/* 276 */       this.type = nbtbase.getTypeId();
/* 277 */       return true;
/*     */     } 
/* 279 */     return (this.type == nbtbase.getTypeId());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagList clone() {
/* 285 */     Iterable<NBTBase> iterable = NBTTagTypes.a(this.type).c() ? this.list : Iterables.transform(this.list, NBTBase::clone);
/* 286 */     List<NBTBase> list = Lists.newArrayList(iterable);
/*     */     
/* 288 */     return new NBTTagList(list, this.type);
/*     */   }
/*     */   
/*     */   public boolean equals(Object object) {
/* 292 */     return (this == object) ? true : ((object instanceof NBTTagList && Objects.equals(this.list, ((NBTTagList)object).list)));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 296 */     return this.list.hashCode();
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String s, int i) {
/* 301 */     if (isEmpty()) {
/* 302 */       return new ChatComponentText("[]");
/*     */     }
/*     */ 
/*     */     
/* 306 */     if (b.contains(this.type) && size() <= 8) {
/* 307 */       String s1 = ", ";
/* 308 */       ChatComponentText chatcomponenttext = new ChatComponentText("[");
/*     */       
/* 310 */       for (int k = 0; k < this.list.size(); k++) {
/* 311 */         if (k != 0) {
/* 312 */           chatcomponenttext.c(", ");
/*     */         }
/*     */         
/* 315 */         chatcomponenttext.addSibling(((NBTBase)this.list.get(k)).l());
/*     */       } 
/*     */       
/* 318 */       chatcomponenttext.c("]");
/* 319 */       return chatcomponenttext;
/*     */     } 
/* 321 */     ChatComponentText chatcomponenttext1 = new ChatComponentText("[");
/*     */     
/* 323 */     if (!s.isEmpty()) {
/* 324 */       chatcomponenttext1.c("\n");
/*     */     }
/*     */     
/* 327 */     String s2 = String.valueOf(',');
/*     */     
/* 329 */     for (int j = 0; j < this.list.size(); j++) {
/* 330 */       ChatComponentText chatcomponenttext2 = new ChatComponentText(Strings.repeat(s, i + 1));
/*     */       
/* 332 */       chatcomponenttext2.addSibling(((NBTBase)this.list.get(j)).a(s, i + 1));
/* 333 */       if (j != this.list.size() - 1) {
/* 334 */         chatcomponenttext2.c(s2).c(s.isEmpty() ? " " : "\n");
/*     */       }
/*     */       
/* 337 */       chatcomponenttext1.addSibling(chatcomponenttext2);
/*     */     } 
/*     */     
/* 340 */     if (!s.isEmpty()) {
/* 341 */       chatcomponenttext1.c("\n").c(Strings.repeat(s, i));
/*     */     }
/*     */     
/* 344 */     chatcomponenttext1.c("]");
/* 345 */     return chatcomponenttext1;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte d_() {
/* 352 */     return this.type;
/*     */   }
/*     */   
/*     */   public void clear() {
/* 356 */     this.list.clear();
/* 357 */     this.type = 0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */