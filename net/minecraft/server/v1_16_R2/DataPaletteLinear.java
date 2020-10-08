/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Predicate;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class DataPaletteLinear<T>
/*     */   implements DataPalette<T>
/*     */ {
/*     */   private final RegistryBlockID<T> a;
/*     */   private final T[] b;
/*     */   private final DataPaletteExpandable<T> c;
/*     */   private final Function<NBTTagCompound, T> d;
/*     */   private final int e;
/*     */   private int f;
/*     */   
/*     */   public DataPaletteLinear(RegistryBlockID<T> var0, int var1, DataPaletteExpandable<T> var2, Function<NBTTagCompound, T> var3) {
/*  22 */     this.a = var0;
/*  23 */     this.b = (T[])new Object[1 << var1];
/*  24 */     this.e = var1;
/*  25 */     this.c = var2;
/*  26 */     this.d = var3;
/*     */   }
/*     */   
/*     */   public int a(T var0) {
/*     */     int var1;
/*  31 */     for (var1 = 0; var1 < this.f; var1++) {
/*  32 */       if (this.b[var1] == var0) {
/*  33 */         return var1;
/*     */       }
/*     */     } 
/*     */     
/*  37 */     var1 = this.f;
/*  38 */     if (var1 < this.b.length) {
/*  39 */       this.b[var1] = var0;
/*  40 */       this.f++;
/*  41 */       return var1;
/*     */     } 
/*     */     
/*  44 */     return this.c.onResize(this.e + 1, var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Predicate<T> var0) {
/*  49 */     for (int var1 = 0; var1 < this.f; var1++) {
/*  50 */       if (var0.test(this.b[var1])) {
/*  51 */         return true;
/*     */       }
/*     */     } 
/*  54 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public T a(int var0) {
/*  60 */     if (var0 >= 0 && var0 < this.f) {
/*  61 */       return this.b[var0];
/*     */     }
/*  63 */     return null;
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
/*     */   public void b(PacketDataSerializer var0) {
/*  76 */     var0.d(this.f);
/*  77 */     for (int var1 = 0; var1 < this.f; var1++) {
/*  78 */       var0.d(this.a.getId(this.b[var1]));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  84 */     int var0 = PacketDataSerializer.a(b());
/*     */     
/*  86 */     for (int var1 = 0; var1 < b(); var1++) {
/*  87 */       var0 += PacketDataSerializer.a(this.a.getId(this.b[var1]));
/*     */     }
/*     */     
/*  90 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b() {
/*  95 */     return this.f;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagList var0) {
/* 100 */     for (int var1 = 0; var1 < var0.size(); var1++) {
/* 101 */       this.b[var1] = this.d.apply(var0.getCompound(var1));
/*     */     }
/* 103 */     this.f = var0.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataPaletteLinear.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */