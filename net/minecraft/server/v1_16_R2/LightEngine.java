/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
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
/*     */ public class LightEngine
/*     */   implements ILightEngine
/*     */ {
/*     */   @Nullable
/*     */   private final LightEngineLayer<?, ?> a;
/*     */   @Nullable
/*     */   private final LightEngineLayer<?, ?> b;
/*     */   
/*     */   public LightEngine(ILightAccess var0, boolean var1, boolean var2) {
/*  22 */     this.a = var1 ? new LightEngineBlock(var0) : null;
/*  23 */     this.b = var2 ? new LightEngineSky(var0) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(BlockPosition var0) {
/*  29 */     if (this.a != null) {
/*  30 */       this.a.a(var0);
/*     */     }
/*  32 */     if (this.b != null) {
/*  33 */       this.b.a(var0);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(BlockPosition var0, int var1) {
/*  40 */     if (this.a != null) {
/*  41 */       this.a.a(var0, var1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean a() {
/*  48 */     if (this.b != null && this.b.a()) {
/*  49 */       return true;
/*     */     }
/*  51 */     return (this.a != null && this.a.a());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(int var0, boolean var1, boolean var2) {
/*  57 */     if (this.a != null && this.b != null) {
/*  58 */       int var3 = var0 / 2;
/*  59 */       int var4 = this.a.a(var3, var1, var2);
/*  60 */       int var5 = var0 - var3 + var4;
/*  61 */       int var6 = this.b.a(var5, var1, var2);
/*  62 */       if (var4 == 0 && var6 > 0) {
/*  63 */         return this.a.a(var6, var1, var2);
/*     */       }
/*  65 */       return var6;
/*     */     } 
/*  67 */     if (this.a != null) {
/*  68 */       return this.a.a(var0, var1, var2);
/*     */     }
/*  70 */     if (this.b != null) {
/*  71 */       return this.b.a(var0, var1, var2);
/*     */     }
/*  73 */     return var0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(SectionPosition var0, boolean var1) {
/*  83 */     if (this.a != null) {
/*  84 */       this.a.a(var0, var1);
/*     */     }
/*  86 */     if (this.b != null) {
/*  87 */       this.b.a(var0, var1);
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(ChunkCoordIntPair var0, boolean var1) {
/*  94 */     if (this.a != null) {
/*  95 */       this.a.a(var0, var1);
/*     */     }
/*  97 */     if (this.b != null) {
/*  98 */       this.b.a(var0, var1);
/*     */     }
/*     */   }
/*     */   
/*     */   public LightEngineLayerEventListener a(EnumSkyBlock var0) {
/* 103 */     if (var0 == EnumSkyBlock.BLOCK) {
/* 104 */       if (this.a == null) {
/* 105 */         return LightEngineLayerEventListener.Void.INSTANCE;
/*     */       }
/* 107 */       return this.a;
/*     */     } 
/* 109 */     if (this.b == null) {
/* 110 */       return LightEngineLayerEventListener.Void.INSTANCE;
/*     */     }
/* 112 */     return this.b;
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
/*     */   public void a(EnumSkyBlock var0, SectionPosition var1, @Nullable NibbleArray var2, boolean var3) {
/* 134 */     if (var0 == EnumSkyBlock.BLOCK) {
/* 135 */       if (this.a != null) {
/* 136 */         this.a.a(var1.s(), var2, var3);
/*     */       }
/*     */     }
/* 139 */     else if (this.b != null) {
/* 140 */       this.b.a(var1.s(), var2, var3);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(ChunkCoordIntPair var0, boolean var1) {
/* 146 */     if (this.a != null) {
/* 147 */       this.a.b(var0, var1);
/*     */     }
/* 149 */     if (this.b != null) {
/* 150 */       this.b.b(var0, var1);
/*     */     }
/*     */   }
/*     */   
/*     */   public int b(BlockPosition var0, int var1) {
/* 155 */     int var2 = (this.b == null) ? 0 : (this.b.b(var0) - var1);
/* 156 */     int var3 = (this.a == null) ? 0 : this.a.b(var0);
/*     */     
/* 158 */     return Math.max(var3, var2);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LightEngine.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */