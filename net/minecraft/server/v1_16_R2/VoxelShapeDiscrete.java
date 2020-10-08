/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ 
/*     */ 
/*     */ public abstract class VoxelShapeDiscrete
/*     */ {
/*   7 */   private static final EnumDirection.EnumAxis[] d = EnumDirection.EnumAxis.values();
/*     */   
/*     */   protected final int a;
/*     */   protected final int b;
/*     */   protected final int c;
/*     */   
/*     */   protected VoxelShapeDiscrete(int var0, int var1, int var2) {
/*  14 */     this.a = var0;
/*  15 */     this.b = var1;
/*  16 */     this.c = var2;
/*     */   }
/*     */   
/*     */   public boolean a(EnumAxisCycle var0, int var1, int var2, int var3) {
/*  20 */     return c(var0
/*  21 */         .a(var1, var2, var3, EnumDirection.EnumAxis.X), var0
/*  22 */         .a(var1, var2, var3, EnumDirection.EnumAxis.Y), var0
/*  23 */         .a(var1, var2, var3, EnumDirection.EnumAxis.Z));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean c(int var0, int var1, int var2) {
/*  28 */     if (var0 < 0 || var1 < 0 || var2 < 0) {
/*  29 */       return false;
/*     */     }
/*  31 */     if (var0 >= this.a || var1 >= this.b || var2 >= this.c) {
/*  32 */       return false;
/*     */     }
/*  34 */     return b(var0, var1, var2);
/*     */   }
/*     */   
/*     */   public boolean b(EnumAxisCycle var0, int var1, int var2, int var3) {
/*  38 */     return b(var0
/*  39 */         .a(var1, var2, var3, EnumDirection.EnumAxis.X), var0
/*  40 */         .a(var1, var2, var3, EnumDirection.EnumAxis.Y), var0
/*  41 */         .a(var1, var2, var3, EnumDirection.EnumAxis.Z));
/*     */   }
/*     */ 
/*     */   
/*     */   public abstract boolean b(int paramInt1, int paramInt2, int paramInt3);
/*     */   
/*     */   public abstract void a(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2);
/*     */   
/*     */   public boolean a() {
/*  50 */     for (EnumDirection.EnumAxis var3 : d) {
/*  51 */       if (a(var3) >= b(var3)) {
/*  52 */         return true;
/*     */       }
/*     */     } 
/*  55 */     return false;
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
/*     */   public abstract int a(EnumDirection.EnumAxis paramEnumAxis);
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
/*     */   public abstract int b(EnumDirection.EnumAxis paramEnumAxis);
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
/*     */   public int c(EnumDirection.EnumAxis var0) {
/* 102 */     return var0.a(this.a, this.b, this.c);
/*     */   }
/*     */   
/*     */   public int b() {
/* 106 */     return c(EnumDirection.EnumAxis.X);
/*     */   }
/*     */   
/*     */   public int c() {
/* 110 */     return c(EnumDirection.EnumAxis.Y);
/*     */   }
/*     */   
/*     */   public int d() {
/* 114 */     return c(EnumDirection.EnumAxis.Z);
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean a(int var0, int var1, int var2, int var3) {
/* 195 */     for (int var4 = var0; var4 < var1; var4++) {
/* 196 */       if (!c(var2, var3, var4)) {
/* 197 */         return false;
/*     */       }
/*     */     } 
/* 200 */     return true;
/*     */   }
/*     */   
/*     */   protected void a(int var0, int var1, int var2, int var3, boolean var4) {
/* 204 */     for (int var5 = var0; var5 < var1; var5++) {
/* 205 */       a(var2, var3, var5, false, var4);
/*     */     }
/*     */   }
/*     */   
/*     */   protected boolean a(int var0, int var1, int var2, int var3, int var4) {
/* 210 */     for (int var5 = var0; var5 < var1; var5++) {
/* 211 */       if (!a(var2, var3, var5, var4)) {
/* 212 */         return false;
/*     */       }
/*     */     } 
/* 215 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void b(b var0, boolean var1) {
/* 223 */     VoxelShapeDiscrete var2 = new VoxelShapeBitSet(this);
/* 224 */     for (int var3 = 0; var3 <= this.a; var3++) {
/* 225 */       for (int var4 = 0; var4 <= this.b; var4++) {
/* 226 */         int var5 = -1;
/* 227 */         for (int var6 = 0; var6 <= this.c; var6++) {
/* 228 */           if (var2.c(var3, var4, var6)) {
/* 229 */             if (var1) {
/*     */               
/* 231 */               if (var5 == -1) {
/* 232 */                 var5 = var6;
/*     */               }
/*     */             } else {
/* 235 */               var0.consume(var3, var4, var6, var3 + 1, var4 + 1, var6 + 1);
/*     */             } 
/* 237 */           } else if (var5 != -1) {
/*     */ 
/*     */             
/* 240 */             int var7 = var3;
/* 241 */             int var8 = var3;
/* 242 */             int var9 = var4;
/* 243 */             int var10 = var4;
/*     */ 
/*     */             
/* 246 */             var2.a(var5, var6, var3, var4, false);
/*     */ 
/*     */             
/* 249 */             while (var2.a(var5, var6, var7 - 1, var9)) {
/* 250 */               var2.a(var5, var6, var7 - 1, var9, false);
/* 251 */               var7--;
/*     */             } 
/* 253 */             while (var2.a(var5, var6, var8 + 1, var9)) {
/* 254 */               var2.a(var5, var6, var8 + 1, var9, false);
/* 255 */               var8++;
/*     */             } 
/*     */ 
/*     */             
/* 259 */             while (var2.a(var7, var8 + 1, var5, var6, var9 - 1)) {
/* 260 */               for (int var11 = var7; var11 <= var8; var11++) {
/* 261 */                 var2.a(var5, var6, var11, var9 - 1, false);
/*     */               }
/* 263 */               var9--;
/*     */             } 
/* 265 */             while (var2.a(var7, var8 + 1, var5, var6, var10 + 1)) {
/* 266 */               for (int var11 = var7; var11 <= var8; var11++) {
/* 267 */                 var2.a(var5, var6, var11, var10 + 1, false);
/*     */               }
/* 269 */               var10++;
/*     */             } 
/*     */             
/* 272 */             var0.consume(var7, var9, var5, var8 + 1, var10 + 1, var6);
/* 273 */             var5 = -1;
/*     */           } 
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(a var0) {
/* 282 */     a(var0, EnumAxisCycle.NONE);
/* 283 */     a(var0, EnumAxisCycle.FORWARD);
/* 284 */     a(var0, EnumAxisCycle.BACKWARD);
/*     */   }
/*     */   
/*     */   private void a(a var0, EnumAxisCycle var1) {
/* 288 */     EnumAxisCycle var2 = var1.a();
/*     */     
/* 290 */     EnumDirection.EnumAxis var3 = var2.a(EnumDirection.EnumAxis.Z);
/*     */     
/* 292 */     int var4 = c(var2.a(EnumDirection.EnumAxis.X));
/* 293 */     int var5 = c(var2.a(EnumDirection.EnumAxis.Y));
/* 294 */     int var6 = c(var3);
/*     */     
/* 296 */     EnumDirection var7 = EnumDirection.a(var3, EnumDirection.EnumAxisDirection.NEGATIVE);
/* 297 */     EnumDirection var8 = EnumDirection.a(var3, EnumDirection.EnumAxisDirection.POSITIVE);
/*     */     
/* 299 */     for (int var9 = 0; var9 < var4; var9++) {
/* 300 */       for (int var10 = 0; var10 < var5; var10++) {
/* 301 */         boolean var11 = false;
/* 302 */         for (int var12 = 0; var12 <= var6; var12++) {
/* 303 */           boolean var13 = (var12 != var6 && b(var2, var9, var10, var12));
/* 304 */           if (!var11 && var13) {
/* 305 */             var0.consume(var7, var2
/*     */                 
/* 307 */                 .a(var9, var10, var12, EnumDirection.EnumAxis.X), var2
/* 308 */                 .a(var9, var10, var12, EnumDirection.EnumAxis.Y), var2
/* 309 */                 .a(var9, var10, var12, EnumDirection.EnumAxis.Z));
/*     */           }
/*     */           
/* 312 */           if (var11 && !var13) {
/* 313 */             var0.consume(var8, var2
/*     */                 
/* 315 */                 .a(var9, var10, var12 - 1, EnumDirection.EnumAxis.X), var2
/* 316 */                 .a(var9, var10, var12 - 1, EnumDirection.EnumAxis.Y), var2
/* 317 */                 .a(var9, var10, var12 - 1, EnumDirection.EnumAxis.Z));
/*     */           }
/*     */           
/* 320 */           var11 = var13;
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static interface a {
/*     */     void consume(EnumDirection param1EnumDirection, int param1Int1, int param1Int2, int param1Int3);
/*     */   }
/*     */   
/*     */   public static interface b {
/*     */     void consume(int param1Int1, int param1Int2, int param1Int3, int param1Int4, int param1Int5, int param1Int6);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeDiscrete.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */