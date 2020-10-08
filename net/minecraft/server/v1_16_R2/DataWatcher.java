/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Maps;
/*     */ import io.netty.handler.codec.DecoderException;
/*     */ import io.netty.handler.codec.EncoderException;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.io.IOException;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.lang3.ObjectUtils;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataWatcher
/*     */ {
/*  22 */   private static final Logger LOGGER = LogManager.getLogger();
/*  23 */   private static final Map<Class<? extends Entity>, Integer> b = Maps.newHashMap();
/*     */   private final Entity entity;
/*  25 */   private final Int2ObjectOpenHashMap<Item<?>> entries = new Int2ObjectOpenHashMap();
/*     */   private boolean f = true;
/*     */   private boolean g;
/*     */   boolean registrationLocked;
/*     */   
/*     */   public DataWatcher(Entity entity) {
/*  31 */     this.entity = entity;
/*     */   }
/*     */   public static <T> DataWatcherObject<T> a(Class<? extends Entity> oclass, DataWatcherSerializer<T> datawatcherserializer) {
/*     */     int i;
/*  35 */     if (LOGGER.isDebugEnabled()) {
/*     */       try {
/*  37 */         Class<?> oclass1 = Class.forName(Thread.currentThread().getStackTrace()[2].getClassName());
/*     */         
/*  39 */         if (!oclass1.equals(oclass)) {
/*  40 */           LOGGER.debug("defineId called for: {} from {}", oclass, oclass1, new RuntimeException());
/*     */         }
/*  42 */       } catch (ClassNotFoundException classNotFoundException) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  49 */     if (b.containsKey(oclass)) {
/*  50 */       i = ((Integer)b.get(oclass)).intValue() + 1;
/*     */     } else {
/*  52 */       int j = 0;
/*  53 */       Class<? extends Entity> oclass2 = oclass;
/*     */       
/*  55 */       while (oclass2 != Entity.class) {
/*  56 */         oclass2 = (Class)oclass2.getSuperclass();
/*  57 */         if (b.containsKey(oclass2)) {
/*  58 */           j = ((Integer)b.get(oclass2)).intValue() + 1;
/*     */           
/*     */           break;
/*     */         } 
/*     */       } 
/*  63 */       i = j;
/*     */     } 
/*     */     
/*  66 */     if (i > 254) {
/*  67 */       throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 'þ' + ")");
/*     */     }
/*  69 */     b.put(oclass, Integer.valueOf(i));
/*  70 */     return datawatcherserializer.a(i);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> void register(DataWatcherObject<T> datawatcherobject, T t0) {
/*  76 */     if (this.registrationLocked) throw new IllegalStateException("Registering datawatcher object after entity initialization"); 
/*  77 */     int i = datawatcherobject.a();
/*     */     
/*  79 */     if (i > 254)
/*  80 */       throw new IllegalArgumentException("Data value id is too big with " + i + "! (Max is " + 'þ' + ")"); 
/*  81 */     if (this.entries.containsKey(i))
/*  82 */       throw new IllegalArgumentException("Duplicate id value for " + i + "!"); 
/*  83 */     if (DataWatcherRegistry.b(datawatcherobject.b()) < 0) {
/*  84 */       throw new IllegalArgumentException("Unregistered serializer " + datawatcherobject.b() + " for " + i + "!");
/*     */     }
/*  86 */     registerObject(datawatcherobject, t0);
/*     */   }
/*     */ 
/*     */   
/*     */   private <T> void registerObject(DataWatcherObject<T> datawatcherobject, T t0) {
/*  91 */     Item<T> datawatcher_item = new Item<>(datawatcherobject, t0);
/*     */ 
/*     */     
/*  94 */     this.entries.put(datawatcherobject.a(), datawatcher_item);
/*  95 */     this.f = false;
/*     */   }
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
/*     */ 
/*     */   
/*     */   private <T> Item<T> b(DataWatcherObject<T> datawatcherobject) {
/* 120 */     return (Item<T>)this.entries.get(datawatcherobject.a());
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> T get(DataWatcherObject<T> datawatcherobject) {
/* 125 */     return b(datawatcherobject).b();
/*     */   }
/*     */   
/*     */   public <T> void set(DataWatcherObject<T> datawatcherobject, T t0) {
/* 129 */     Item<T> datawatcher_item = b(datawatcherobject);
/*     */     
/* 131 */     if (ObjectUtils.notEqual(t0, datawatcher_item.b())) {
/* 132 */       datawatcher_item.a(t0);
/* 133 */       this.entity.a(datawatcherobject);
/* 134 */       datawatcher_item.a(true);
/* 135 */       this.g = true;
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public <T> void markDirty(DataWatcherObject<T> datawatcherobject) {
/* 142 */     b(datawatcherobject).a(true);
/* 143 */     this.g = true;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a() {
/* 148 */     return this.g;
/*     */   }
/*     */   
/*     */   public static void a(List<Item<?>> list, PacketDataSerializer packetdataserializer) throws IOException {
/* 152 */     if (list != null) {
/* 153 */       int i = 0;
/*     */       
/* 155 */       for (int j = list.size(); i < j; i++) {
/* 156 */         a(packetdataserializer, list.get(i));
/*     */       }
/*     */     } 
/*     */     
/* 160 */     packetdataserializer.writeByte(255);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<Item<?>> b() {
/* 165 */     List<Item<?>> list = null;
/*     */     
/* 167 */     if (this.g) {
/*     */       
/* 169 */       ObjectIterator<Item> objectIterator = this.entries.values().iterator();
/*     */       
/* 171 */       while (objectIterator.hasNext()) {
/* 172 */         Item<?> datawatcher_item = objectIterator.next();
/*     */         
/* 174 */         if (datawatcher_item.c()) {
/* 175 */           datawatcher_item.a(false);
/* 176 */           if (list == null) {
/* 177 */             list = Lists.newArrayList();
/*     */           }
/*     */           
/* 180 */           list.add(datawatcher_item.d());
/*     */         } 
/*     */       } 
/*     */     } 
/*     */ 
/*     */ 
/*     */     
/* 187 */     this.g = false;
/* 188 */     return list;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public List<Item<?>> c() {
/* 193 */     List<Item<?>> list = null;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 199 */     for (ObjectIterator<Item> objectIterator = this.entries.values().iterator(); objectIterator.hasNext(); list.add(datawatcher_item.d())) {
/* 200 */       Item<?> datawatcher_item = objectIterator.next();
/* 201 */       if (list == null) {
/* 202 */         list = Lists.newArrayList();
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 207 */     return list;
/*     */   }
/*     */   
/*     */   private static <T> void a(PacketDataSerializer packetdataserializer, Item<T> datawatcher_item) throws IOException {
/* 211 */     DataWatcherObject<T> datawatcherobject = datawatcher_item.a();
/* 212 */     int i = DataWatcherRegistry.b(datawatcherobject.b());
/*     */     
/* 214 */     if (i < 0) {
/* 215 */       throw new EncoderException("Unknown serializer type " + datawatcherobject.b());
/*     */     }
/* 217 */     packetdataserializer.writeByte(datawatcherobject.a());
/* 218 */     packetdataserializer.d(i);
/* 219 */     datawatcherobject.b().a(packetdataserializer, datawatcher_item.b());
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public static List<Item<?>> a(PacketDataSerializer packetdataserializer) throws IOException {
/* 225 */     ArrayList<Item<?>> arraylist = null;
/*     */     
/*     */     short short0;
/*     */     
/* 229 */     while ((short0 = packetdataserializer.readUnsignedByte()) != 255) {
/* 230 */       if (arraylist == null) {
/* 231 */         arraylist = Lists.newArrayList();
/*     */       }
/*     */       
/* 234 */       int i = packetdataserializer.i();
/* 235 */       DataWatcherSerializer<?> datawatcherserializer = DataWatcherRegistry.a(i);
/*     */       
/* 237 */       if (datawatcherserializer == null) {
/* 238 */         throw new DecoderException("Unknown serializer type " + i);
/*     */       }
/*     */       
/* 241 */       arraylist.add(a(packetdataserializer, short0, datawatcherserializer));
/*     */     } 
/*     */     
/* 244 */     return arraylist;
/*     */   }
/*     */   
/*     */   private static <T> Item<T> a(PacketDataSerializer packetdataserializer, int i, DataWatcherSerializer<T> datawatcherserializer) {
/* 248 */     return new Item<>(datawatcherserializer.a(i), datawatcherserializer.a(packetdataserializer));
/*     */   }
/*     */   
/*     */   public boolean d() {
/* 252 */     return this.f;
/*     */   }
/*     */   
/*     */   public void e() {
/* 256 */     this.g = false;
/*     */     
/* 258 */     ObjectIterator<Item> objectIterator = this.entries.values().iterator();
/*     */     
/* 260 */     while (objectIterator.hasNext()) {
/* 261 */       Item<?> datawatcher_item = objectIterator.next();
/*     */       
/* 263 */       datawatcher_item.a(false);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public static class Item<T>
/*     */   {
/*     */     private final DataWatcherObject<T> a;
/*     */     
/*     */     private T b;
/*     */     private boolean c;
/*     */     
/*     */     public Item(DataWatcherObject<T> datawatcherobject, T t0) {
/* 276 */       this.a = datawatcherobject;
/* 277 */       this.b = t0;
/* 278 */       this.c = true;
/*     */     }
/*     */     
/*     */     public DataWatcherObject<T> a() {
/* 282 */       return this.a;
/*     */     }
/*     */     
/*     */     public void a(T t0) {
/* 286 */       this.b = t0;
/*     */     }
/*     */     
/*     */     public T b() {
/* 290 */       return this.b;
/*     */     }
/*     */     
/*     */     public boolean c() {
/* 294 */       return this.c;
/*     */     }
/*     */     
/*     */     public void a(boolean flag) {
/* 298 */       this.c = flag;
/*     */     }
/*     */     
/*     */     public Item<T> d() {
/* 302 */       return new Item(this.a, this.a.b().a(this.b));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataWatcher.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */