/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.HashBasedTable;
/*     */ import com.google.common.collect.Table;
/*     */ import com.google.common.primitives.UnsignedLong;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import java.util.Collection;
/*     */ import java.util.Collections;
/*     */ import java.util.Comparator;
/*     */ import java.util.PriorityQueue;
/*     */ import java.util.Queue;
/*     */ import java.util.Set;
/*     */ import java.util.stream.Stream;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class CustomFunctionCallbackTimerQueue<T>
/*     */ {
/*  22 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*     */   private final CustomFunctionCallbackTimers<T> b;
/*     */   
/*     */   public static class a<T>
/*     */   {
/*     */     public final long a;
/*     */     public final UnsignedLong b;
/*     */     public final String c;
/*     */     public final CustomFunctionCallbackTimer<T> d;
/*     */     
/*     */     private a(long var0, UnsignedLong var2, String var3, CustomFunctionCallbackTimer<T> var4) {
/*  34 */       this.a = var0;
/*  35 */       this.b = var2;
/*  36 */       this.c = var3;
/*  37 */       this.d = var4;
/*     */     }
/*     */   }
/*     */   
/*     */   private static <T> Comparator<a<T>> c() {
/*  42 */     return Comparator.comparingLong(var0 -> var0.a).thenComparing(var0 -> var0.b);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*  47 */   private final Queue<a<T>> c = new PriorityQueue<>((Comparator)c());
/*     */   
/*  49 */   private UnsignedLong d = UnsignedLong.ZERO;
/*     */   
/*  51 */   private final Table<String, Long, a<T>> e = (Table<String, Long, a<T>>)HashBasedTable.create();
/*     */   
/*     */   public CustomFunctionCallbackTimerQueue(CustomFunctionCallbackTimers<T> var0, Stream<Dynamic<NBTBase>> var1) {
/*  54 */     this(var0);
/*  55 */     this.c.clear();
/*  56 */     this.e.clear();
/*  57 */     this.d = UnsignedLong.ZERO;
/*     */     
/*  59 */     var1.forEach(var0 -> {
/*     */           if (!(var0.getValue() instanceof NBTTagCompound)) {
/*     */             LOGGER.warn("Invalid format of events: {}", var0);
/*     */             return;
/*     */           } 
/*     */           a((NBTTagCompound)var0.getValue());
/*     */         });
/*     */   }
/*     */   
/*     */   public CustomFunctionCallbackTimerQueue(CustomFunctionCallbackTimers<T> var0) {
/*  69 */     this.b = var0;
/*     */   }
/*     */   
/*     */   public void a(T var0, long var1) {
/*     */     while (true) {
/*  74 */       a<T> var3 = this.c.peek();
/*  75 */       if (var3 == null || var3.a > var1) {
/*     */         break;
/*     */       }
/*     */       
/*  79 */       this.c.remove();
/*  80 */       this.e.remove(var3.c, Long.valueOf(var1));
/*     */       
/*  82 */       var3.d.a(var0, this, var1);
/*     */     } 
/*     */   }
/*     */   
/*     */   public void a(String var0, long var1, CustomFunctionCallbackTimer<T> var3) {
/*  87 */     if (this.e.contains(var0, Long.valueOf(var1))) {
/*     */       return;
/*     */     }
/*  90 */     this.d = this.d.plus(UnsignedLong.ONE);
/*  91 */     a<T> var4 = new a<>(var1, this.d, var0, var3);
/*  92 */     this.e.put(var0, Long.valueOf(var1), var4);
/*  93 */     this.c.add(var4);
/*     */   }
/*     */   
/*     */   public int a(String var0) {
/*  97 */     Collection<a<T>> var1 = this.e.row(var0).values();
/*  98 */     var1.forEach(this.c::remove);
/*  99 */     int var2 = var1.size();
/* 100 */     var1.clear();
/* 101 */     return var2;
/*     */   }
/*     */   
/*     */   public Set<String> a() {
/* 105 */     return Collections.unmodifiableSet(this.e.rowKeySet());
/*     */   }
/*     */   
/*     */   private void a(NBTTagCompound var0) {
/* 109 */     NBTTagCompound var1 = var0.getCompound("Callback");
/* 110 */     CustomFunctionCallbackTimer<T> var2 = this.b.a(var1);
/* 111 */     if (var2 != null) {
/* 112 */       String var3 = var0.getString("Name");
/* 113 */       long var4 = var0.getLong("TriggerTime");
/* 114 */       a(var3, var4, var2);
/*     */     } 
/*     */   }
/*     */   
/*     */   private NBTTagCompound a(a<T> var0) {
/* 119 */     NBTTagCompound var1 = new NBTTagCompound();
/* 120 */     var1.setString("Name", var0.c);
/* 121 */     var1.setLong("TriggerTime", var0.a);
/* 122 */     var1.set("Callback", this.b.a(var0.d));
/* 123 */     return var1;
/*     */   }
/*     */   
/*     */   public NBTTagList b() {
/* 127 */     NBTTagList var0 = new NBTTagList();
/* 128 */     this.c.stream().sorted(c()).map(this::a).forEach(var0::add);
/* 129 */     return var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CustomFunctionCallbackTimerQueue.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */