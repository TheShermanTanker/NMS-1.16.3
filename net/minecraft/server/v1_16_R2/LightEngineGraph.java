/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import it.unimi.dsi.fastutil.longs.Long2ByteMap;
/*     */ import it.unimi.dsi.fastutil.longs.Long2ByteOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.longs.LongArrayList;
/*     */ import it.unimi.dsi.fastutil.longs.LongLinkedOpenHashSet;
/*     */ import it.unimi.dsi.fastutil.longs.LongList;
/*     */ import java.util.function.LongPredicate;
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
/*     */ 
/*     */ public abstract class LightEngineGraph
/*     */ {
/*     */   private final int a;
/*     */   private final LongLinkedOpenHashSet[] b;
/*     */   private final Long2ByteMap c;
/*     */   private int d;
/*     */   private volatile boolean e;
/*     */   
/*     */   protected LightEngineGraph(int var0, int var1, int var2) {
/*  42 */     if (var0 >= 254) {
/*  43 */       throw new IllegalArgumentException("Level count must be < 254.");
/*     */     }
/*  45 */     this.a = var0;
/*  46 */     this.b = new LongLinkedOpenHashSet[var0];
/*  47 */     for (int var3 = 0; var3 < var0; var3++) {
/*  48 */       this.b[var3] = new LongLinkedOpenHashSet(this, var1, 0.5F, var1)
/*     */         {
/*     */           protected void rehash(int var0) {
/*  51 */             if (var0 > this.a) {
/*  52 */               super.rehash(var0);
/*     */             }
/*     */           }
/*     */         };
/*     */     } 
/*  57 */     this.c = (Long2ByteMap)new Long2ByteOpenHashMap(this, var2, 0.5F, var2)
/*     */       {
/*     */         protected void rehash(int var0) {
/*  60 */           if (var0 > this.a) {
/*  61 */             super.rehash(var0);
/*     */           }
/*     */         }
/*     */       };
/*  65 */     this.c.defaultReturnValue((byte)-1);
/*  66 */     this.d = var0;
/*     */   }
/*     */   
/*     */   private int a(int var0, int var1) {
/*  70 */     int var2 = var0;
/*  71 */     if (var2 > var1) {
/*  72 */       var2 = var1;
/*     */     }
/*  74 */     if (var2 > this.a - 1) {
/*  75 */       var2 = this.a - 1;
/*     */     }
/*  77 */     return var2;
/*     */   }
/*     */   
/*     */   private void a(int var0) {
/*  81 */     int var1 = this.d;
/*  82 */     this.d = var0;
/*  83 */     for (int var2 = var1 + 1; var2 < var0; var2++) {
/*  84 */       if (!this.b[var2].isEmpty()) {
/*  85 */         this.d = var2;
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void e(long var0) {
/*  92 */     int var2 = this.c.get(var0) & 0xFF;
/*  93 */     if (var2 == 255) {
/*     */       return;
/*     */     }
/*  96 */     int var3 = c(var0);
/*  97 */     int var4 = a(var3, var2);
/*  98 */     a(var0, var4, this.a, true);
/*  99 */     this.e = (this.d < this.a);
/*     */   }
/*     */   
/*     */   public void a(LongPredicate var0) {
/* 103 */     LongArrayList longArrayList = new LongArrayList();
/*     */     
/* 105 */     this.c.keySet().forEach(var2 -> {
/*     */           if (var0.test(var2)) {
/*     */             var1.add(var2);
/*     */           }
/*     */         });
/*     */     
/* 111 */     longArrayList.forEach(this::e);
/*     */   }
/*     */   
/*     */   private void a(long var0, int var2, int var3, boolean var4) {
/* 115 */     if (var4) {
/* 116 */       this.c.remove(var0);
/*     */     }
/* 118 */     this.b[var2].remove(var0);
/* 119 */     if (this.b[var2].isEmpty() && this.d == var2) {
/* 120 */       a(var3);
/*     */     }
/*     */   }
/*     */   
/*     */   private void a(long var0, int var2, int var3) {
/* 125 */     this.c.put(var0, (byte)var2);
/* 126 */     this.b[var3].add(var0);
/* 127 */     if (this.d > var3) {
/* 128 */       this.d = var3;
/*     */     }
/*     */   }
/*     */   
/*     */   protected void f(long var0) {
/* 133 */     a(var0, var0, this.a - 1, false);
/*     */   }
/*     */   
/*     */   protected void a(long var0, long var2, int var4, boolean var5) {
/* 137 */     a(var0, var2, var4, c(var2), this.c.get(var2) & 0xFF, var5);
/* 138 */     this.e = (this.d < this.a);
/*     */   } private void a(long var0, long var2, int var4, int var5, int var6, boolean var7) {
/*     */     boolean var8;
/*     */     int var9;
/* 142 */     if (a(var2)) {
/*     */       return;
/*     */     }
/* 145 */     var4 = MathHelper.clamp(var4, 0, this.a - 1);
/* 146 */     var5 = MathHelper.clamp(var5, 0, this.a - 1);
/*     */     
/* 148 */     if (var6 == 255) {
/* 149 */       var8 = true;
/* 150 */       var6 = var5;
/*     */     } else {
/* 152 */       var8 = false;
/*     */     } 
/*     */     
/* 155 */     if (var7) {
/*     */       
/* 157 */       var9 = Math.min(var6, var4);
/*     */     } else {
/* 159 */       var9 = MathHelper.clamp(a(var2, var0, var4), 0, this.a - 1);
/*     */     } 
/* 161 */     int var10 = a(var5, var6);
/* 162 */     if (var5 != var9) {
/* 163 */       int var11 = a(var5, var9);
/* 164 */       if (var10 != var11 && !var8) {
/* 165 */         a(var2, var10, var11, false);
/*     */       }
/* 167 */       a(var2, var9, var11);
/* 168 */     } else if (!var8) {
/* 169 */       a(var2, var10, this.a, true);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected final void b(long var0, long var2, int var4, boolean var5) {
/* 174 */     int var6 = this.c.get(var2) & 0xFF;
/* 175 */     int var7 = MathHelper.clamp(b(var0, var2, var4), 0, this.a - 1);
/* 176 */     if (var5) {
/* 177 */       a(var0, var2, var7, c(var2), var6, true);
/*     */     } else {
/*     */       int var8;
/*     */       boolean var9;
/* 181 */       if (var6 == 255) {
/* 182 */         var9 = true;
/* 183 */         var8 = MathHelper.clamp(c(var2), 0, this.a - 1);
/*     */       } else {
/* 185 */         var8 = var6;
/* 186 */         var9 = false;
/*     */       } 
/*     */       
/* 189 */       if (var7 == var8)
/*     */       {
/* 191 */         a(var0, var2, this.a - 1, var9 ? var8 : c(var2), var6, false);
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   protected final boolean b() {
/* 197 */     return this.e;
/*     */   }
/*     */   
/*     */   protected final int b(int var0) {
/* 201 */     if (this.d >= this.a) {
/* 202 */       return var0;
/*     */     }
/* 204 */     while (this.d < this.a && var0 > 0) {
/* 205 */       var0--;
/* 206 */       LongLinkedOpenHashSet var1 = this.b[this.d];
/* 207 */       long var2 = var1.removeFirstLong();
/* 208 */       int var4 = MathHelper.clamp(c(var2), 0, this.a - 1);
/* 209 */       if (var1.isEmpty()) {
/* 210 */         a(this.a);
/*     */       }
/* 212 */       int var5 = this.c.remove(var2) & 0xFF;
/* 213 */       if (var5 < var4) {
/*     */         
/* 215 */         a(var2, var5);
/* 216 */         a(var2, var5, true); continue;
/* 217 */       }  if (var5 > var4) {
/*     */         
/* 219 */         a(var2, var5, a(this.a - 1, var5));
/* 220 */         a(var2, this.a - 1);
/* 221 */         a(var2, var4, false);
/*     */       } 
/*     */     } 
/* 224 */     this.e = (this.d < this.a);
/* 225 */     return var0;
/*     */   }
/*     */   
/*     */   public int c() {
/* 229 */     return this.c.size();
/*     */   }
/*     */   
/*     */   protected abstract boolean a(long paramLong);
/*     */   
/*     */   protected abstract int a(long paramLong1, long paramLong2, int paramInt);
/*     */   
/*     */   protected abstract void a(long paramLong, int paramInt, boolean paramBoolean);
/*     */   
/*     */   protected abstract int c(long paramLong);
/*     */   
/*     */   protected abstract void a(long paramLong, int paramInt);
/*     */   
/*     */   protected abstract int b(long paramLong1, long paramLong2, int paramInt);
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngineGraph.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */