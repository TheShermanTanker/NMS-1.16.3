/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import co.aikar.timings.Timing;
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import java.util.Optional;
/*     */ 
/*     */ public class CustomFunctionData {
/*  12 */   private static final MinecraftKey a = new MinecraftKey("tick");
/*  13 */   private static final MinecraftKey b = new MinecraftKey("load");
/*     */   private final MinecraftServer server;
/*     */   private boolean d;
/*  16 */   private final ArrayDeque<a> e = new ArrayDeque<>();
/*  17 */   private final List<a> f = Lists.newArrayList();
/*  18 */   private final List<CustomFunction> g = Lists.newArrayList();
/*     */   private boolean h;
/*     */   private CustomFunctionManager i;
/*     */   
/*     */   public CustomFunctionData(MinecraftServer minecraftserver, CustomFunctionManager customfunctionmanager) {
/*  23 */     this.server = minecraftserver;
/*  24 */     this.i = customfunctionmanager;
/*  25 */     b(customfunctionmanager);
/*     */   }
/*     */   
/*     */   public int b() {
/*  29 */     return this.server.getGameRules().getInt(GameRules.MAX_COMMAND_CHAIN_LENGTH);
/*     */   }
/*     */   
/*     */   public CommandDispatcher<CommandListenerWrapper> getCommandDispatcher() {
/*  33 */     return this.server.vanillaCommandDispatcher.a();
/*     */   }
/*     */   
/*     */   public void tick() {
/*  37 */     a(this.g, a);
/*  38 */     if (this.h) {
/*  39 */       this.h = false;
/*  40 */       Collection<CustomFunction> collection = this.i.b().b(b).getTagged();
/*     */       
/*  42 */       a(collection, b);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void a(Collection<CustomFunction> collection, MinecraftKey minecraftkey) {
/*  48 */     Objects.requireNonNull(minecraftkey); this.server.getMethodProfiler().a(minecraftkey::toString);
/*  49 */     Iterator<CustomFunction> iterator = collection.iterator();
/*     */     
/*  51 */     while (iterator.hasNext()) {
/*  52 */       CustomFunction customfunction = iterator.next();
/*     */       
/*  54 */       a(customfunction, e());
/*     */     } 
/*     */     
/*  57 */     this.server.getMethodProfiler().exit();
/*     */   }
/*     */   
/*     */   public int a(CustomFunction customfunction, CommandListenerWrapper commandlistenerwrapper) {
/*  61 */     int j, i = b();
/*     */     
/*  63 */     if (this.d) {
/*  64 */       if (this.e.size() + this.f.size() < i) {
/*  65 */         this.f.add(new a(this, commandlistenerwrapper, new CustomFunction.d(customfunction)));
/*     */       }
/*     */       
/*  68 */       return 0;
/*     */     } 
/*     */ 
/*     */     
/*  72 */     try { Timing timing = customfunction.getTiming().startTiming(); 
/*  73 */       try { this.d = true;
/*  74 */         int k = 0;
/*  75 */         CustomFunction.c[] acustomfunction_c = customfunction.b();
/*     */         
/*  77 */         for (j = acustomfunction_c.length - 1; j >= 0; j--) {
/*  78 */           this.e.push(new a(this, commandlistenerwrapper, acustomfunction_c[j]));
/*     */         }
/*     */ 
/*     */         
/*  82 */         do { if (this.e.isEmpty())
/*  83 */           { j = k;
/*  84 */             int m = j;
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
/*     */             
/* 108 */             if (timing != null) timing.close();  return m; }  try { a customfunctiondata_a = this.e.removeFirst(); Objects.requireNonNull(customfunctiondata_a); this.server.getMethodProfiler().a(customfunctiondata_a::toString); customfunctiondata_a.a(this.e, i); if (!this.f.isEmpty()) { List list = Lists.reverse(this.f); ArrayDeque<a> arraydeque = this.e; this.e.getClass(); Objects.requireNonNull(arraydeque); list.forEach(arraydeque::addFirst); this.f.clear(); }  } finally { this.server.getMethodProfiler().exit(); }  ++k; } while (k < i); j = k; if (timing != null) timing.close();  } catch (Throwable throwable) { if (timing != null)
/* 109 */           try { timing.close(); } catch (Throwable throwable1) { throwable.addSuppressed(throwable1); }   throw throwable; }  } finally { this.e.clear();
/* 110 */       this.f.clear();
/* 111 */       this.d = false; }
/*     */ 
/*     */     
/* 114 */     return j;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(CustomFunctionManager customfunctionmanager) {
/* 119 */     this.i = customfunctionmanager;
/* 120 */     b(customfunctionmanager);
/*     */   }
/*     */   
/*     */   private void b(CustomFunctionManager customfunctionmanager) {
/* 124 */     this.g.clear();
/* 125 */     this.g.addAll(customfunctionmanager.b().b(a).getTagged());
/* 126 */     this.h = true;
/*     */   }
/*     */   
/*     */   public CommandListenerWrapper e() {
/* 130 */     return this.server.getServerCommandListener().a(2).a();
/*     */   }
/*     */   
/*     */   public Optional<CustomFunction> a(MinecraftKey minecraftkey) {
/* 134 */     return this.i.a(minecraftkey);
/*     */   }
/*     */   
/*     */   public Tag<CustomFunction> b(MinecraftKey minecraftkey) {
/* 138 */     return this.i.b(minecraftkey);
/*     */   }
/*     */   
/*     */   public Iterable<MinecraftKey> f() {
/* 142 */     return this.i.a().keySet();
/*     */   }
/*     */   
/*     */   public Iterable<MinecraftKey> g() {
/* 146 */     return this.i.b().b();
/*     */   }
/*     */   
/*     */   public static class a
/*     */   {
/*     */     private final CustomFunctionData a;
/*     */     private final CommandListenerWrapper b;
/*     */     private final CustomFunction.c c;
/*     */     
/*     */     public a(CustomFunctionData customfunctiondata, CommandListenerWrapper commandlistenerwrapper, CustomFunction.c customfunction_c) {
/* 156 */       this.a = customfunctiondata;
/* 157 */       this.b = commandlistenerwrapper;
/* 158 */       this.c = customfunction_c;
/*     */     }
/*     */     
/*     */     public void a(ArrayDeque<a> arraydeque, int i) {
/*     */       try {
/* 163 */         this.c.a(this.a, this.b, arraydeque, i);
/* 164 */       } catch (Throwable throwable) {}
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public String toString() {
/* 171 */       return this.c.toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CustomFunctionData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */