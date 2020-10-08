/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.base.Strings;
/*     */ import com.google.common.collect.Lists;
/*     */ import com.mojang.serialization.Codec;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import it.unimi.dsi.fastutil.objects.Object2ObjectOpenHashMap;
/*     */ import java.io.DataInput;
/*     */ import java.io.DataOutput;
/*     */ import java.io.IOException;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.regex.Pattern;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ public class NBTTagCompound implements NBTBase {
/*     */   public static final Codec<NBTTagCompound> a;
/*     */   
/*     */   static {
/*  29 */     a = Codec.PASSTHROUGH.comapFlatMap(dynamic -> {
/*     */           NBTBase nbtbase = (NBTBase)dynamic.convert(DynamicOpsNBT.a).getValue();
/*     */           return (nbtbase instanceof NBTTagCompound) ? DataResult.success(nbtbase) : DataResult.error("Not a compound tag: " + nbtbase);
/*     */         }nbttagcompound -> new Dynamic(DynamicOpsNBT.a, nbttagcompound));
/*     */   }
/*     */ 
/*     */   
/*  36 */   private static final Logger LOGGER = LogManager.getLogger();
/*  37 */   private static final Pattern h = Pattern.compile("[A-Za-z0-9._+-]+");
/*  38 */   public static final NBTTagType<NBTTagCompound> b = new NBTTagType<NBTTagCompound>()
/*     */     {
/*     */       public NBTTagCompound b(DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) throws IOException {
/*  41 */         nbtreadlimiter.a(384L);
/*  42 */         if (i > 512) {
/*  43 */           throw new RuntimeException("Tried to read NBT tag with too high complexity, depth > 512");
/*     */         }
/*  45 */         Object2ObjectOpenHashMap<String, NBTBase> hashmap = new Object2ObjectOpenHashMap(8, 0.8F);
/*     */         
/*     */         byte b0;
/*     */         
/*  49 */         while ((b0 = NBTTagCompound.c(datainput, nbtreadlimiter)) != 0) {
/*  50 */           String s = NBTTagCompound.d(datainput, nbtreadlimiter);
/*     */           
/*  52 */           nbtreadlimiter.a((224 + 16 * s.length()));
/*  53 */           NBTBase nbtbase = NBTTagCompound.b(NBTTagTypes.a(b0), s, datainput, i + 1, nbtreadlimiter);
/*     */           
/*  55 */           if (hashmap.put(s, nbtbase) != null) {
/*  56 */             nbtreadlimiter.a(288L);
/*     */           }
/*     */         } 
/*     */         
/*  60 */         return new NBTTagCompound((Map<String, NBTBase>)hashmap);
/*     */       }
/*     */ 
/*     */ 
/*     */       
/*     */       public String a() {
/*  66 */         return "COMPOUND";
/*     */       }
/*     */ 
/*     */       
/*     */       public String b() {
/*  71 */         return "TAG_Compound";
/*     */       }
/*     */     };
/*     */   public final Map<String, NBTBase> map;
/*     */   
/*     */   protected NBTTagCompound(Map<String, NBTBase> map) {
/*  77 */     this.map = map;
/*     */   }
/*     */   
/*     */   public NBTTagCompound() {
/*  81 */     this((Map<String, NBTBase>)new Object2ObjectOpenHashMap(8, 0.8F));
/*     */   }
/*     */ 
/*     */   
/*     */   public void write(DataOutput dataoutput) throws IOException {
/*  86 */     Iterator<String> iterator = this.map.keySet().iterator();
/*     */     
/*  88 */     while (iterator.hasNext()) {
/*  89 */       String s = iterator.next();
/*  90 */       NBTBase nbtbase = this.map.get(s);
/*     */       
/*  92 */       a(s, nbtbase, dataoutput);
/*     */     } 
/*     */     
/*  95 */     dataoutput.writeByte(0);
/*     */   }
/*     */   
/*     */   public Set<String> getKeys() {
/*  99 */     return this.map.keySet();
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getTypeId() {
/* 104 */     return 10;
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagType<NBTTagCompound> b() {
/* 109 */     return b;
/*     */   }
/*     */   
/*     */   public int e() {
/* 113 */     return this.map.size();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NBTBase set(String s, NBTBase nbtbase) {
/* 118 */     return this.map.put(s, nbtbase);
/*     */   }
/*     */   
/*     */   public void setByte(String s, byte b0) {
/* 122 */     this.map.put(s, NBTTagByte.a(b0));
/*     */   }
/*     */   
/*     */   public void setShort(String s, short short0) {
/* 126 */     this.map.put(s, NBTTagShort.a(short0));
/*     */   }
/*     */   
/*     */   public void setInt(String s, int i) {
/* 130 */     this.map.put(s, NBTTagInt.a(i));
/*     */   }
/*     */   
/*     */   public void setLong(String s, long i) {
/* 134 */     this.map.put(s, NBTTagLong.a(i));
/*     */   }
/*     */   public void setUUID(String prefix, UUID uuid) {
/* 137 */     a(prefix, uuid);
/*     */   }
/*     */   public void a(String s, UUID uuid) {
/* 140 */     if (hasKeyOfType(s + "Most", 99) && hasKeyOfType(s + "Least", 99)) {
/* 141 */       this.map.remove(s + "Most");
/* 142 */       this.map.remove(s + "Least");
/*     */     } 
/*     */     
/* 145 */     this.map.put(s, GameProfileSerializer.a(uuid));
/*     */   }
/*     */   @Nullable
/*     */   public UUID getUUID(String prefix) {
/* 149 */     return a(prefix);
/*     */   }
/*     */   @Nullable
/*     */   public UUID a(String s) {
/* 153 */     if (!hasKeyOfType(s, 11) && hasKeyOfType(s + "Most", 99) && hasKeyOfType(s + "Least", 99)) {
/* 154 */       return new UUID(getLong(s + "Most"), getLong(s + "Least"));
/*     */     }
/*     */     
/* 157 */     return GameProfileSerializer.a(get(s));
/*     */   }
/*     */   public final boolean hasUUID(String s) {
/* 160 */     return b(s);
/*     */   }
/*     */   public boolean b(String s) {
/* 163 */     if (hasKeyOfType(s + "Most", 99) && hasKeyOfType(s + "Least", 99)) {
/* 164 */       return true;
/*     */     }
/*     */     
/* 167 */     NBTBase nbtbase = get(s);
/*     */     
/* 169 */     return (nbtbase != null && nbtbase.b() == NBTTagIntArray.a && (((NBTTagIntArray)nbtbase).getInts()).length == 4);
/*     */   }
/*     */   
/*     */   public void setFloat(String s, float f) {
/* 173 */     this.map.put(s, NBTTagFloat.a(f));
/*     */   }
/*     */   
/*     */   public void setDouble(String s, double d0) {
/* 177 */     this.map.put(s, NBTTagDouble.a(d0));
/*     */   }
/*     */   
/*     */   public void setString(String s, String s1) {
/* 181 */     this.map.put(s, NBTTagString.a(s1));
/*     */   }
/*     */   
/*     */   public void setByteArray(String s, byte[] abyte) {
/* 185 */     this.map.put(s, new NBTTagByteArray(abyte));
/*     */   }
/*     */   
/*     */   public void setIntArray(String s, int[] aint) {
/* 189 */     this.map.put(s, new NBTTagIntArray(aint));
/*     */   }
/*     */   
/*     */   public void b(String s, List<Integer> list) {
/* 193 */     this.map.put(s, new NBTTagIntArray(list));
/*     */   }
/*     */   
/*     */   public void a(String s, long[] along) {
/* 197 */     this.map.put(s, new NBTTagLongArray(along));
/*     */   }
/*     */   
/*     */   public void c(String s, List<Long> list) {
/* 201 */     this.map.put(s, new NBTTagLongArray(list));
/*     */   }
/*     */   
/*     */   public void setBoolean(String s, boolean flag) {
/* 205 */     this.map.put(s, NBTTagByte.a(flag));
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public NBTBase get(String s) {
/* 210 */     return this.map.get(s);
/*     */   }
/*     */   
/*     */   public byte d(String s) {
/* 214 */     NBTBase nbtbase = this.map.get(s);
/*     */     
/* 216 */     return (nbtbase == null) ? 0 : nbtbase.getTypeId();
/*     */   }
/*     */   
/*     */   public boolean hasKey(String s) {
/* 220 */     return this.map.containsKey(s);
/*     */   }
/*     */   
/*     */   public boolean hasKeyOfType(String s, int i) {
/* 224 */     byte b0 = d(s);
/*     */     
/* 226 */     return (b0 == i) ? true : ((i != 99) ? false : ((b0 == 1 || b0 == 2 || b0 == 3 || b0 == 4 || b0 == 5 || b0 == 6)));
/*     */   }
/*     */   
/*     */   public byte getByte(String s) {
/*     */     try {
/* 231 */       if (hasKeyOfType(s, 99)) {
/* 232 */         return ((NBTNumber)this.map.get(s)).asByte();
/*     */       }
/* 234 */     } catch (ClassCastException classCastException) {}
/*     */ 
/*     */ 
/*     */     
/* 238 */     return 0;
/*     */   }
/*     */   
/*     */   public short getShort(String s) {
/*     */     try {
/* 243 */       if (hasKeyOfType(s, 99)) {
/* 244 */         return ((NBTNumber)this.map.get(s)).asShort();
/*     */       }
/* 246 */     } catch (ClassCastException classCastException) {}
/*     */ 
/*     */ 
/*     */     
/* 250 */     return 0;
/*     */   }
/*     */   
/*     */   public int getInt(String s) {
/*     */     try {
/* 255 */       if (hasKeyOfType(s, 99)) {
/* 256 */         return ((NBTNumber)this.map.get(s)).asInt();
/*     */       }
/* 258 */     } catch (ClassCastException classCastException) {}
/*     */ 
/*     */ 
/*     */     
/* 262 */     return 0;
/*     */   }
/*     */   
/*     */   public long getLong(String s) {
/*     */     try {
/* 267 */       if (hasKeyOfType(s, 99)) {
/* 268 */         return ((NBTNumber)this.map.get(s)).asLong();
/*     */       }
/* 270 */     } catch (ClassCastException classCastException) {}
/*     */ 
/*     */ 
/*     */     
/* 274 */     return 0L;
/*     */   }
/*     */   
/*     */   public float getFloat(String s) {
/*     */     try {
/* 279 */       if (hasKeyOfType(s, 99)) {
/* 280 */         return ((NBTNumber)this.map.get(s)).asFloat();
/*     */       }
/* 282 */     } catch (ClassCastException classCastException) {}
/*     */ 
/*     */ 
/*     */     
/* 286 */     return 0.0F;
/*     */   }
/*     */   
/*     */   public double getDouble(String s) {
/*     */     try {
/* 291 */       if (hasKeyOfType(s, 99)) {
/* 292 */         return ((NBTNumber)this.map.get(s)).asDouble();
/*     */       }
/* 294 */     } catch (ClassCastException classCastException) {}
/*     */ 
/*     */ 
/*     */     
/* 298 */     return 0.0D;
/*     */   }
/*     */   
/*     */   public String getString(String s) {
/*     */     try {
/* 303 */       if (hasKeyOfType(s, 8)) {
/* 304 */         return ((NBTBase)this.map.get(s)).asString();
/*     */       }
/* 306 */     } catch (ClassCastException classCastException) {}
/*     */ 
/*     */ 
/*     */     
/* 310 */     return "";
/*     */   }
/*     */   
/*     */   public byte[] getByteArray(String s) {
/*     */     try {
/* 315 */       if (hasKeyOfType(s, 7)) {
/* 316 */         return ((NBTTagByteArray)this.map.get(s)).getBytes();
/*     */       }
/* 318 */     } catch (ClassCastException classcastexception) {
/* 319 */       throw new ReportedException(a(s, NBTTagByteArray.a, classcastexception));
/*     */     } 
/*     */     
/* 322 */     return new byte[0];
/*     */   }
/*     */   
/*     */   public int[] getIntArray(String s) {
/*     */     try {
/* 327 */       if (hasKeyOfType(s, 11)) {
/* 328 */         return ((NBTTagIntArray)this.map.get(s)).getInts();
/*     */       }
/* 330 */     } catch (ClassCastException classcastexception) {
/* 331 */       throw new ReportedException(a(s, NBTTagIntArray.a, classcastexception));
/*     */     } 
/*     */     
/* 334 */     return new int[0];
/*     */   }
/*     */   
/*     */   public long[] getLongArray(String s) {
/*     */     try {
/* 339 */       if (hasKeyOfType(s, 12)) {
/* 340 */         return ((NBTTagLongArray)this.map.get(s)).getLongs();
/*     */       }
/* 342 */     } catch (ClassCastException classcastexception) {
/* 343 */       throw new ReportedException(a(s, NBTTagLongArray.a, classcastexception));
/*     */     } 
/*     */     
/* 346 */     return new long[0];
/*     */   }
/*     */   
/*     */   public NBTTagCompound getCompound(String s) {
/*     */     try {
/* 351 */       if (hasKeyOfType(s, 10)) {
/* 352 */         return (NBTTagCompound)this.map.get(s);
/*     */       }
/* 354 */     } catch (ClassCastException classcastexception) {
/* 355 */       throw new ReportedException(a(s, b, classcastexception));
/*     */     } 
/*     */     
/* 358 */     return new NBTTagCompound();
/*     */   }
/*     */   
/*     */   public NBTTagList getList(String s, int i) {
/*     */     try {
/* 363 */       if (d(s) == 9) {
/* 364 */         NBTTagList nbttaglist = (NBTTagList)this.map.get(s);
/*     */         
/* 366 */         if (!nbttaglist.isEmpty() && nbttaglist.d_() != i) {
/* 367 */           return new NBTTagList();
/*     */         }
/*     */         
/* 370 */         return nbttaglist;
/*     */       } 
/* 372 */     } catch (ClassCastException classcastexception) {
/* 373 */       throw new ReportedException(a(s, NBTTagList.a, classcastexception));
/*     */     } 
/*     */     
/* 376 */     return new NBTTagList();
/*     */   }
/*     */   
/*     */   public boolean getBoolean(String s) {
/* 380 */     return (getByte(s) != 0);
/*     */   }
/*     */   
/*     */   public void remove(String s) {
/* 384 */     this.map.remove(s);
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 389 */     StringBuilder stringbuilder = new StringBuilder("{");
/* 390 */     Collection<String> collection = this.map.keySet();
/*     */     
/* 392 */     if (LOGGER.isDebugEnabled()) {
/* 393 */       List<String> list = Lists.newArrayList(this.map.keySet());
/*     */       
/* 395 */       Collections.sort(list);
/* 396 */       collection = list;
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 401 */     for (Iterator<String> iterator = collection.iterator(); iterator.hasNext(); stringbuilder.append(s(s)).append(':').append(this.map.get(s))) {
/* 402 */       String s = iterator.next();
/* 403 */       if (stringbuilder.length() != 1) {
/* 404 */         stringbuilder.append(',');
/*     */       }
/*     */     } 
/*     */     
/* 408 */     return stringbuilder.append('}').toString();
/*     */   }
/*     */   
/*     */   public boolean isEmpty() {
/* 412 */     return this.map.isEmpty();
/*     */   }
/*     */   
/*     */   private CrashReport a(String s, NBTTagType<?> nbttagtype, ClassCastException classcastexception) {
/* 416 */     CrashReport crashreport = CrashReport.a(classcastexception, "Reading NBT data");
/* 417 */     CrashReportSystemDetails crashreportsystemdetails = crashreport.a("Corrupt NBT tag", 1);
/*     */     
/* 419 */     crashreportsystemdetails.a("Tag type found", () -> ((NBTBase)this.map.get(s)).b().a());
/*     */ 
/*     */     
/* 422 */     Objects.requireNonNull(nbttagtype); crashreportsystemdetails.a("Tag type expected", nbttagtype::a);
/* 423 */     crashreportsystemdetails.a("Tag name", s);
/* 424 */     return crashreport;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public NBTTagCompound clone() {
/* 430 */     Object2ObjectOpenHashMap<String, NBTBase> ret = new Object2ObjectOpenHashMap(this.map.size(), 0.8F);
/*     */     
/* 432 */     Iterator<Map.Entry<String, NBTBase>> iterator = (this.map instanceof Object2ObjectOpenHashMap) ? (Iterator<Map.Entry<String, NBTBase>>)((Object2ObjectOpenHashMap)this.map).object2ObjectEntrySet().fastIterator() : this.map.entrySet().iterator();
/* 433 */     while (iterator.hasNext()) {
/* 434 */       Map.Entry<String, NBTBase> entry = iterator.next();
/* 435 */       ret.put(entry.getKey(), ((NBTBase)entry.getValue()).clone());
/*     */     } 
/*     */     
/* 438 */     return new NBTTagCompound((Map<String, NBTBase>)ret);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object object) {
/* 443 */     return (this == object) ? true : ((object instanceof NBTTagCompound && Objects.equals(this.map, ((NBTTagCompound)object).map)));
/*     */   }
/*     */   
/*     */   public int hashCode() {
/* 447 */     return this.map.hashCode();
/*     */   }
/*     */   
/*     */   private static void a(String s, NBTBase nbtbase, DataOutput dataoutput) throws IOException {
/* 451 */     dataoutput.writeByte(nbtbase.getTypeId());
/* 452 */     if (nbtbase.getTypeId() != 0) {
/* 453 */       dataoutput.writeUTF(s);
/* 454 */       nbtbase.write(dataoutput);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static byte c(DataInput datainput, NBTReadLimiter nbtreadlimiter) throws IOException {
/* 459 */     return datainput.readByte();
/*     */   }
/*     */   
/*     */   private static String d(DataInput datainput, NBTReadLimiter nbtreadlimiter) throws IOException {
/* 463 */     return datainput.readUTF();
/*     */   }
/*     */   
/*     */   private static NBTBase b(NBTTagType<?> nbttagtype, String s, DataInput datainput, int i, NBTReadLimiter nbtreadlimiter) {
/*     */     try {
/* 468 */       return (NBTBase)nbttagtype.b(datainput, i, nbtreadlimiter);
/* 469 */     } catch (IOException ioexception) {
/* 470 */       CrashReport crashreport = CrashReport.a(ioexception, "Loading NBT data");
/* 471 */       CrashReportSystemDetails crashreportsystemdetails = crashreport.a("NBT Tag");
/*     */       
/* 473 */       crashreportsystemdetails.a("Tag name", s);
/* 474 */       crashreportsystemdetails.a("Tag type", nbttagtype.a());
/* 475 */       throw new ReportedException(crashreport);
/*     */     } 
/*     */   }
/*     */   
/*     */   public NBTTagCompound a(NBTTagCompound nbttagcompound) {
/* 480 */     Iterator<String> iterator = nbttagcompound.map.keySet().iterator();
/*     */     
/* 482 */     while (iterator.hasNext()) {
/* 483 */       String s = iterator.next();
/* 484 */       NBTBase nbtbase = nbttagcompound.map.get(s);
/*     */       
/* 486 */       if (nbtbase.getTypeId() == 10) {
/* 487 */         if (hasKeyOfType(s, 10)) {
/* 488 */           NBTTagCompound nbttagcompound1 = getCompound(s);
/*     */           
/* 490 */           nbttagcompound1.a((NBTTagCompound)nbtbase); continue;
/*     */         } 
/* 492 */         set(s, nbtbase.clone());
/*     */         continue;
/*     */       } 
/* 495 */       set(s, nbtbase.clone());
/*     */     } 
/*     */ 
/*     */     
/* 499 */     return this;
/*     */   }
/*     */   
/*     */   protected static String s(String s) {
/* 503 */     return h.matcher(s).matches() ? s : NBTTagString.b(s);
/*     */   }
/*     */   
/*     */   protected static IChatBaseComponent t(String s) {
/* 507 */     if (h.matcher(s).matches()) {
/* 508 */       return (new ChatComponentText(s)).a(d);
/*     */     }
/* 510 */     String s1 = NBTTagString.b(s);
/* 511 */     String s2 = s1.substring(0, 1);
/* 512 */     IChatMutableComponent ichatmutablecomponent = (new ChatComponentText(s1.substring(1, s1.length() - 1))).a(d);
/*     */     
/* 514 */     return (new ChatComponentText(s2)).addSibling(ichatmutablecomponent).c(s2);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public IChatBaseComponent a(String s, int i) {
/* 520 */     if (this.map.isEmpty()) {
/* 521 */       return new ChatComponentText("{}");
/*     */     }
/* 523 */     ChatComponentText chatcomponenttext = new ChatComponentText("{");
/* 524 */     Collection<String> collection = this.map.keySet();
/*     */     
/* 526 */     if (LOGGER.isDebugEnabled()) {
/* 527 */       List<String> list = Lists.newArrayList(this.map.keySet());
/*     */       
/* 529 */       Collections.sort(list);
/* 530 */       collection = list;
/*     */     } 
/*     */     
/* 533 */     if (!s.isEmpty()) {
/* 534 */       chatcomponenttext.c("\n");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 539 */     for (Iterator<String> iterator = collection.iterator(); iterator.hasNext(); chatcomponenttext.addSibling(ichatmutablecomponent)) {
/* 540 */       String s1 = iterator.next();
/*     */       
/* 542 */       IChatMutableComponent ichatmutablecomponent = (new ChatComponentText(Strings.repeat(s, i + 1))).addSibling(t(s1)).c(String.valueOf(':')).c(" ").addSibling(((NBTBase)this.map.get(s1)).a(s, i + 1));
/* 543 */       if (iterator.hasNext()) {
/* 544 */         ichatmutablecomponent.c(String.valueOf(',')).c(s.isEmpty() ? " " : "\n");
/*     */       }
/*     */     } 
/*     */     
/* 548 */     if (!s.isEmpty()) {
/* 549 */       chatcomponenttext.c("\n").c(Strings.repeat(s, i));
/*     */     }
/*     */     
/* 552 */     chatcomponenttext.c("}");
/* 553 */     return chatcomponenttext;
/*     */   }
/*     */ 
/*     */   
/*     */   protected Map<String, NBTBase> h() {
/* 558 */     return Collections.unmodifiableMap(this.map);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NBTTagCompound.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */