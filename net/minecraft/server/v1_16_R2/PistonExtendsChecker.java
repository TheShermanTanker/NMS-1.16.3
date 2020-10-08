/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.List;
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
/*     */ public class PistonExtendsChecker
/*     */ {
/*     */   private final World a;
/*     */   private final BlockPosition b;
/*     */   private final boolean c;
/*     */   private final BlockPosition d;
/*     */   private final EnumDirection e;
/*  22 */   private final List<BlockPosition> f = Lists.newArrayList();
/*  23 */   private final List<BlockPosition> g = Lists.newArrayList();
/*     */   private final EnumDirection h;
/*     */   
/*     */   public PistonExtendsChecker(World var0, BlockPosition var1, EnumDirection var2, boolean var3) {
/*  27 */     this.a = var0;
/*  28 */     this.b = var1;
/*  29 */     this.h = var2;
/*  30 */     this.c = var3;
/*     */     
/*  32 */     if (var3) {
/*  33 */       this.e = var2;
/*  34 */       this.d = var1.shift(var2);
/*     */     } else {
/*  36 */       this.e = var2.opposite();
/*  37 */       this.d = var1.shift(var2, 2);
/*     */     } 
/*     */   }
/*     */   
/*     */   public boolean a() {
/*  42 */     this.f.clear();
/*  43 */     this.g.clear();
/*     */     
/*  45 */     IBlockData var0 = this.a.getType(this.d);
/*     */     
/*  47 */     if (!BlockPiston.a(var0, this.a, this.d, this.e, false, this.h)) {
/*  48 */       if (this.c && var0.getPushReaction() == EnumPistonReaction.DESTROY) {
/*  49 */         this.g.add(this.d);
/*  50 */         return true;
/*     */       } 
/*     */       
/*  53 */       return false;
/*     */     } 
/*     */ 
/*     */     
/*  57 */     if (!a(this.d, this.e))
/*     */     {
/*  59 */       return false;
/*     */     }
/*     */     
/*  62 */     for (int var1 = 0; var1 < this.f.size(); var1++) {
/*  63 */       BlockPosition var2 = this.f.get(var1);
/*     */ 
/*     */       
/*  66 */       if (a(this.a.getType(var2).getBlock()) && 
/*  67 */         !a(var2))
/*     */       {
/*  69 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/*  74 */     return true;
/*     */   }
/*     */   
/*     */   private static boolean a(Block var0) {
/*  78 */     return (var0 == Blocks.SLIME_BLOCK || var0 == Blocks.HONEY_BLOCK);
/*     */   }
/*     */ 
/*     */   
/*     */   private static boolean a(Block var0, Block var1) {
/*  83 */     if (var0 == Blocks.HONEY_BLOCK && var1 == Blocks.SLIME_BLOCK) {
/*  84 */       return false;
/*     */     }
/*  86 */     if (var0 == Blocks.SLIME_BLOCK && var1 == Blocks.HONEY_BLOCK) {
/*  87 */       return false;
/*     */     }
/*  89 */     return (a(var0) || a(var1));
/*     */   }
/*     */   
/*     */   private boolean a(BlockPosition var0, EnumDirection var1) {
/*  93 */     IBlockData var2 = this.a.getType(var0);
/*  94 */     Block var3 = var2.getBlock();
/*  95 */     if (var2.isAir())
/*     */     {
/*  97 */       return true; } 
/*  98 */     if (!BlockPiston.a(var2, this.a, var0, this.e, false, var1))
/*     */     {
/* 100 */       return true; } 
/* 101 */     if (var0.equals(this.b))
/*     */     {
/* 103 */       return true; } 
/* 104 */     if (this.f.contains(var0))
/*     */     {
/* 106 */       return true;
/*     */     }
/*     */     
/* 109 */     int var4 = 1;
/* 110 */     if (var4 + this.f.size() > 12)
/*     */     {
/* 112 */       return false;
/*     */     }
/*     */     
/* 115 */     while (a(var3)) {
/* 116 */       BlockPosition blockPosition = var0.shift(this.e.opposite(), var4);
/* 117 */       Block block = var3;
/* 118 */       var2 = this.a.getType(blockPosition);
/* 119 */       var3 = var2.getBlock();
/*     */       
/* 121 */       if (var2.isAir() || !a(block, var3) || !BlockPiston.a(var2, this.a, blockPosition, this.e, false, this.e.opposite()) || blockPosition.equals(this.b)) {
/*     */         break;
/*     */       }
/* 124 */       var4++;
/* 125 */       if (var4 + this.f.size() > 12) {
/* 126 */         return false;
/*     */       }
/*     */     } 
/*     */ 
/*     */     
/* 131 */     int var5 = 0;
/*     */     
/*     */     int var6;
/* 134 */     for (var6 = var4 - 1; var6 >= 0; var6--) {
/* 135 */       this.f.add(var0.shift(this.e.opposite(), var6));
/* 136 */       var5++;
/*     */     } 
/*     */ 
/*     */     
/* 140 */     for (var6 = 1;; var6++) {
/* 141 */       BlockPosition var7 = var0.shift(this.e, var6);
/*     */       
/* 143 */       int var8 = this.f.indexOf(var7);
/* 144 */       if (var8 > -1) {
/*     */         
/* 146 */         a(var5, var8);
/*     */ 
/*     */ 
/*     */ 
/*     */         
/* 151 */         for (int var9 = 0; var9 <= var8 + var5; var9++) {
/* 152 */           BlockPosition var10 = this.f.get(var9);
/* 153 */           if (a(this.a.getType(var10).getBlock()) && 
/* 154 */             !a(var10)) {
/* 155 */             return false;
/*     */           }
/*     */         } 
/*     */ 
/*     */         
/* 160 */         return true;
/*     */       } 
/*     */       
/* 163 */       var2 = this.a.getType(var7);
/*     */       
/* 165 */       if (var2.isAir())
/*     */       {
/* 167 */         return true;
/*     */       }
/*     */       
/* 170 */       if (!BlockPiston.a(var2, this.a, var7, this.e, true, this.e) || var7.equals(this.b))
/*     */       {
/* 172 */         return false;
/*     */       }
/*     */       
/* 175 */       if (var2.getPushReaction() == EnumPistonReaction.DESTROY) {
/* 176 */         this.g.add(var7);
/* 177 */         return true;
/*     */       } 
/*     */       
/* 180 */       if (this.f.size() >= 12) {
/* 181 */         return false;
/*     */       }
/*     */       
/* 184 */       this.f.add(var7);
/* 185 */       var5++;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void a(int var0, int var1) {
/* 190 */     List<BlockPosition> var2 = Lists.newArrayList();
/* 191 */     List<BlockPosition> var3 = Lists.newArrayList();
/* 192 */     List<BlockPosition> var4 = Lists.newArrayList();
/*     */     
/* 194 */     var2.addAll(this.f.subList(0, var1));
/* 195 */     var3.addAll(this.f.subList(this.f.size() - var0, this.f.size()));
/* 196 */     var4.addAll(this.f.subList(var1, this.f.size() - var0));
/*     */     
/* 198 */     this.f.clear();
/* 199 */     this.f.addAll(var2);
/* 200 */     this.f.addAll(var3);
/* 201 */     this.f.addAll(var4);
/*     */   }
/*     */   
/*     */   private boolean a(BlockPosition var0) {
/* 205 */     IBlockData var1 = this.a.getType(var0);
/* 206 */     for (EnumDirection var5 : EnumDirection.values()) {
/* 207 */       if (var5.n() != this.e.n()) {
/* 208 */         BlockPosition var6 = var0.shift(var5);
/* 209 */         IBlockData var7 = this.a.getType(var6);
/* 210 */         if (a(var7.getBlock(), var1.getBlock()))
/*     */         {
/*     */           
/* 213 */           if (!a(var6, var5)) {
/* 214 */             return false;
/*     */           }
/*     */         }
/*     */       } 
/*     */     } 
/* 219 */     return true;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public List<BlockPosition> getMovedBlocks() {
/* 227 */     return this.f;
/*     */   }
/*     */   
/*     */   public List<BlockPosition> getBrokenBlocks() {
/* 231 */     return this.g;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PistonExtendsChecker.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */