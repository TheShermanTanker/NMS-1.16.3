/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ public class AdvancementTree
/*     */ {
/*     */   private final Advancement a;
/*     */   private final AdvancementTree b;
/*     */   private final AdvancementTree c;
/*     */   private final int d;
/*  13 */   private final List<AdvancementTree> e = Lists.newArrayList();
/*     */   private AdvancementTree f;
/*     */   private AdvancementTree g;
/*     */   private int h;
/*     */   private float i;
/*     */   private float j;
/*     */   private float k;
/*     */   private float l;
/*     */   
/*     */   public AdvancementTree(Advancement var0, @Nullable AdvancementTree var1, @Nullable AdvancementTree var2, int var3, int var4) {
/*  23 */     if (var0.c() == null) {
/*  24 */       throw new IllegalArgumentException("Can't position an invisible advancement!");
/*     */     }
/*  26 */     this.a = var0;
/*  27 */     this.b = var1;
/*  28 */     this.c = var2;
/*  29 */     this.d = var3;
/*  30 */     this.f = this;
/*  31 */     this.h = var4;
/*  32 */     this.i = -1.0F;
/*     */     
/*  34 */     AdvancementTree var5 = null;
/*  35 */     for (Advancement var7 : var0.e()) {
/*  36 */       var5 = a(var7, var5);
/*     */     }
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private AdvancementTree a(Advancement var0, @Nullable AdvancementTree var1) {
/*  42 */     if (var0.c() != null) {
/*  43 */       var1 = new AdvancementTree(var0, this, var1, this.e.size() + 1, this.h + 1);
/*  44 */       this.e.add(var1);
/*     */     } else {
/*  46 */       for (Advancement var3 : var0.e()) {
/*  47 */         var1 = a(var3, var1);
/*     */       }
/*     */     } 
/*  50 */     return var1;
/*     */   }
/*     */   
/*     */   private void a() {
/*  54 */     if (this.e.isEmpty()) {
/*  55 */       if (this.c != null) {
/*  56 */         this.c.i++;
/*     */       } else {
/*  58 */         this.i = 0.0F;
/*     */       } 
/*     */       
/*     */       return;
/*     */     } 
/*  63 */     AdvancementTree var0 = null;
/*  64 */     for (AdvancementTree var2 : this.e) {
/*  65 */       var2.a();
/*  66 */       var0 = var2.a((var0 == null) ? var2 : var0);
/*     */     } 
/*  68 */     b();
/*     */     
/*  70 */     float var1 = (((AdvancementTree)this.e.get(0)).i + ((AdvancementTree)this.e.get(this.e.size() - 1)).i) / 2.0F;
/*  71 */     if (this.c != null) {
/*  72 */       this.c.i++;
/*  73 */       this.j = this.i - var1;
/*     */     } else {
/*  75 */       this.i = var1;
/*     */     } 
/*     */   }
/*     */   
/*     */   private float a(float var0, int var1, float var2) {
/*  80 */     this.i += var0;
/*  81 */     this.h = var1;
/*     */     
/*  83 */     if (this.i < var2) {
/*  84 */       var2 = this.i;
/*     */     }
/*     */     
/*  87 */     for (AdvancementTree var4 : this.e) {
/*  88 */       var2 = var4.a(var0 + this.j, var1 + 1, var2);
/*     */     }
/*     */     
/*  91 */     return var2;
/*     */   }
/*     */   
/*     */   private void a(float var0) {
/*  95 */     this.i += var0;
/*  96 */     for (AdvancementTree var2 : this.e) {
/*  97 */       var2.a(var0);
/*     */     }
/*     */   }
/*     */   
/*     */   private void b() {
/* 102 */     float var0 = 0.0F;
/* 103 */     float var1 = 0.0F;
/* 104 */     for (int var2 = this.e.size() - 1; var2 >= 0; var2--) {
/* 105 */       AdvancementTree var3 = this.e.get(var2);
/* 106 */       var3.i += var0;
/* 107 */       var3.j += var0;
/* 108 */       var1 += var3.k;
/* 109 */       var0 += var3.l + var1;
/*     */     } 
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private AdvancementTree c() {
/* 115 */     if (this.g != null) {
/* 116 */       return this.g;
/*     */     }
/* 118 */     if (!this.e.isEmpty()) {
/* 119 */       return this.e.get(0);
/*     */     }
/* 121 */     return null;
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private AdvancementTree d() {
/* 126 */     if (this.g != null) {
/* 127 */       return this.g;
/*     */     }
/* 129 */     if (!this.e.isEmpty()) {
/* 130 */       return this.e.get(this.e.size() - 1);
/*     */     }
/* 132 */     return null;
/*     */   }
/*     */   
/*     */   private AdvancementTree a(AdvancementTree var0) {
/* 136 */     if (this.c == null) {
/* 137 */       return var0;
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 142 */     AdvancementTree var1 = this;
/* 143 */     AdvancementTree var2 = this;
/* 144 */     AdvancementTree var3 = this.c;
/* 145 */     AdvancementTree var4 = this.b.e.get(0);
/* 146 */     float var5 = this.j;
/* 147 */     float var6 = this.j;
/* 148 */     float var7 = var3.j;
/* 149 */     float var8 = var4.j;
/*     */     
/* 151 */     while (var3.d() != null && var1.c() != null) {
/* 152 */       var3 = var3.d();
/* 153 */       var1 = var1.c();
/* 154 */       var4 = var4.c();
/* 155 */       var2 = var2.d();
/* 156 */       var2.f = this;
/* 157 */       float var9 = var3.i + var7 - var1.i + var5 + 1.0F;
/* 158 */       if (var9 > 0.0F) {
/* 159 */         var3.a(this, var0).a(this, var9);
/* 160 */         var5 += var9;
/* 161 */         var6 += var9;
/*     */       } 
/* 163 */       var7 += var3.j;
/* 164 */       var5 += var1.j;
/* 165 */       var8 += var4.j;
/* 166 */       var6 += var2.j;
/*     */     } 
/* 168 */     if (var3.d() != null && var2.d() == null) {
/* 169 */       var2.g = var3.d();
/* 170 */       var2.j += var7 - var6;
/*     */     } else {
/* 172 */       if (var1.c() != null && var4.c() == null) {
/* 173 */         var4.g = var1.c();
/* 174 */         var4.j += var5 - var8;
/*     */       } 
/* 176 */       var0 = this;
/*     */     } 
/*     */     
/* 179 */     return var0;
/*     */   }
/*     */   
/*     */   private void a(AdvancementTree var0, float var1) {
/* 183 */     float var2 = (var0.d - this.d);
/* 184 */     if (var2 != 0.0F) {
/* 185 */       var0.k -= var1 / var2;
/* 186 */       this.k += var1 / var2;
/*     */     } 
/* 188 */     var0.l += var1;
/* 189 */     var0.i += var1;
/* 190 */     var0.j += var1;
/*     */   }
/*     */   
/*     */   private AdvancementTree a(AdvancementTree var0, AdvancementTree var1) {
/* 194 */     if (this.f != null && var0.b.e.contains(this.f)) {
/* 195 */       return this.f;
/*     */     }
/* 197 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   private void e() {
/* 202 */     if (this.a.c() != null) {
/* 203 */       this.a.c().a(this.h, this.i);
/*     */     }
/*     */     
/* 206 */     if (!this.e.isEmpty()) {
/* 207 */       for (AdvancementTree var1 : this.e) {
/* 208 */         var1.e();
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   public static void a(Advancement var0) {
/* 214 */     if (var0.c() == null) {
/* 215 */       throw new IllegalArgumentException("Can't position children of an invisible root!");
/*     */     }
/* 217 */     AdvancementTree var1 = new AdvancementTree(var0, null, null, 1, 0);
/* 218 */     var1.a();
/* 219 */     float var2 = var1.a(0.0F, 0, var1.i);
/* 220 */     if (var2 < 0.0F) {
/* 221 */       var1.a(-var2);
/*     */     }
/* 223 */     var1.e();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AdvancementTree.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */