/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Collection;
/*     */ import java.util.function.Consumer;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class GameTestHarnessCollector
/*     */ {
/*  20 */   private final Collection<GameTestHarnessInfo> a = Lists.newArrayList();
/*     */   
/*     */   @Nullable
/*  23 */   private Collection<GameTestHarnessListener> b = Lists.newArrayList();
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public GameTestHarnessCollector(Collection<GameTestHarnessInfo> var0) {
/*  29 */     this.a.addAll(var0);
/*     */   }
/*     */   
/*     */   public void a(GameTestHarnessInfo var0) {
/*  33 */     this.a.add(var0);
/*  34 */     this.b.forEach(var0::a);
/*     */   }
/*     */   
/*     */   public void a(GameTestHarnessListener var0) {
/*  38 */     this.b.add(var0);
/*  39 */     this.a.forEach(var1 -> var1.a(var0));
/*     */   }
/*     */   
/*     */   public void a(Consumer<GameTestHarnessInfo> var0) {
/*  43 */     a(new GameTestHarnessListener(this, var0)
/*     */         {
/*     */           public void a(GameTestHarnessInfo var0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/*     */           public void c(GameTestHarnessInfo var0) {
/*  55 */             this.a.accept(var0);
/*     */           }
/*     */         });
/*     */   }
/*     */   
/*     */   public int a() {
/*  61 */     return (int)this.a.stream().filter(GameTestHarnessInfo::i).filter(GameTestHarnessInfo::q).count();
/*     */   }
/*     */   
/*     */   public int b() {
/*  65 */     return (int)this.a.stream().filter(GameTestHarnessInfo::i).filter(GameTestHarnessInfo::r).count();
/*     */   }
/*     */   
/*     */   public int c() {
/*  69 */     return (int)this.a.stream().filter(GameTestHarnessInfo::k).count();
/*     */   }
/*     */   
/*     */   public boolean d() {
/*  73 */     return (a() > 0);
/*     */   }
/*     */   
/*     */   public boolean e() {
/*  77 */     return (b() > 0);
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
/*     */   public int h() {
/*  89 */     return this.a.size();
/*     */   }
/*     */   
/*     */   public boolean i() {
/*  93 */     return (c() == h());
/*     */   }
/*     */   
/*     */   public String j() {
/*  97 */     StringBuffer var0 = new StringBuffer();
/*  98 */     var0.append('[');
/*  99 */     this.a.forEach(var1 -> {
/*     */           if (!var1.j()) {
/*     */             var0.append(' ');
/*     */           } else if (var1.h()) {
/*     */             var0.append('+');
/*     */           } else if (var1.i()) {
/*     */             var0.append(var1.q() ? 88 : 120);
/*     */           } else {
/*     */             var0.append('_');
/*     */           } 
/*     */         });
/* 110 */     var0.append(']');
/* 111 */     return var0.toString();
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 116 */     return j();
/*     */   }
/*     */   
/*     */   public GameTestHarnessCollector() {}
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GameTestHarnessCollector.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */