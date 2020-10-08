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
/*     */ public class DataPaletteHash<T>
/*     */   implements DataPalette<T>
/*     */ {
/*     */   private final RegistryBlockID<T> a;
/*     */   private final RegistryID<T> b;
/*     */   private final DataPaletteExpandable<T> c;
/*     */   private final Function<NBTTagCompound, T> d;
/*     */   private final Function<T, NBTTagCompound> e;
/*     */   private final int f;
/*     */   
/*     */   public DataPaletteHash(RegistryBlockID<T> var0, int var1, DataPaletteExpandable<T> var2, Function<NBTTagCompound, T> var3, Function<T, NBTTagCompound> var4) {
/*  22 */     this.a = var0;
/*  23 */     this.f = var1;
/*  24 */     this.c = var2;
/*  25 */     this.d = var3;
/*  26 */     this.e = var4;
/*  27 */     this.b = new RegistryID<>(1 << var1);
/*     */   }
/*     */ 
/*     */   
/*     */   public int a(T var0) {
/*  32 */     int var1 = this.b.getId(var0);
/*  33 */     if (var1 == -1) {
/*  34 */       var1 = this.b.c(var0);
/*     */       
/*  36 */       if (var1 >= 1 << this.f) {
/*  37 */         var1 = this.c.onResize(this.f + 1, var0);
/*     */       }
/*     */     } 
/*  40 */     return var1;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(Predicate<T> var0) {
/*  45 */     for (int var1 = 0; var1 < b(); var1++) {
/*  46 */       if (var0.test(this.b.fromId(var1))) {
/*  47 */         return true;
/*     */       }
/*     */     } 
/*  50 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public T a(int var0) {
/*  56 */     return this.b.fromId(var0);
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
/*     */   public void b(PacketDataSerializer var0) {
/*  70 */     int var1 = b();
/*  71 */     var0.d(var1);
/*     */     
/*  73 */     for (int var2 = 0; var2 < var1; var2++) {
/*  74 */       var0.d(this.a.getId(this.b.fromId(var2)));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public int a() {
/*  80 */     int var0 = PacketDataSerializer.a(b());
/*     */     
/*  82 */     for (int var1 = 0; var1 < b(); var1++) {
/*  83 */       var0 += PacketDataSerializer.a(this.a.getId(this.b.fromId(var1)));
/*     */     }
/*     */     
/*  86 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public int b() {
/*  91 */     return this.b.b();
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(NBTTagList var0) {
/*  96 */     this.b.a();
/*  97 */     for (int var1 = 0; var1 < var0.size(); var1++) {
/*  98 */       this.b.c(this.d.apply(var0.getCompound(var1)));
/*     */     }
/*     */   }
/*     */   
/*     */   public void b(NBTTagList var0) {
/* 103 */     for (int var1 = 0; var1 < b(); var1++)
/* 104 */       var0.add(this.e.apply(this.b.fromId(var1))); 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DataPaletteHash.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */