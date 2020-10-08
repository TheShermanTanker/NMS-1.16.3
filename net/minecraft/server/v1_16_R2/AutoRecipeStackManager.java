/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntMap;
/*     */ import it.unimi.dsi.fastutil.ints.Int2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.ints.IntAVLTreeSet;
/*     */ import it.unimi.dsi.fastutil.ints.IntArrayList;
/*     */ import it.unimi.dsi.fastutil.ints.IntCollection;
/*     */ import it.unimi.dsi.fastutil.ints.IntIterator;
/*     */ import it.unimi.dsi.fastutil.ints.IntList;
/*     */ import it.unimi.dsi.fastutil.ints.IntListIterator;
/*     */ import java.util.BitSet;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class AutoRecipeStackManager
/*     */ {
/*  23 */   public final Int2IntMap a = (Int2IntMap)new Int2IntOpenHashMap();
/*     */   
/*     */   public void a(ItemStack var0) {
/*  26 */     if (!var0.f() && !var0.hasEnchantments() && !var0.hasName()) {
/*  27 */       b(var0);
/*     */     }
/*     */   }
/*     */   
/*     */   public void b(ItemStack var0) {
/*  32 */     a(var0, 64);
/*     */   }
/*     */   
/*     */   public void a(ItemStack var0, int var1) {
/*  36 */     if (!var0.isEmpty()) {
/*  37 */       int var2 = c(var0);
/*  38 */       int var3 = Math.min(var1, var0.getCount());
/*  39 */       b(var2, var3);
/*     */     } 
/*     */   }
/*     */   
/*     */   public static int c(ItemStack var0) {
/*  44 */     return IRegistry.ITEM.a(var0.getItem());
/*     */   }
/*     */   
/*     */   private boolean b(int var0) {
/*  48 */     return (this.a.get(var0) > 0);
/*     */   }
/*     */   
/*     */   private int a(int var0, int var1) {
/*  52 */     int var2 = this.a.get(var0);
/*  53 */     if (var2 >= var1) {
/*  54 */       this.a.put(var0, var2 - var1);
/*  55 */       return var0;
/*     */     } 
/*     */     
/*  58 */     return 0;
/*     */   }
/*     */   
/*     */   private void b(int var0, int var1) {
/*  62 */     this.a.put(var0, this.a.get(var0) + var1);
/*     */   }
/*     */   
/*     */   public boolean a(IRecipe<?> var0, @Nullable IntList var1) {
/*  66 */     return a(var0, var1, 1);
/*     */   }
/*     */   
/*     */   public boolean a(IRecipe<?> var0, @Nullable IntList var1, int var2) {
/*  70 */     return (new a(this, var0)).a(var2, var1);
/*     */   }
/*     */   
/*     */   public int b(IRecipe<?> var0, @Nullable IntList var1) {
/*  74 */     return a(var0, 2147483647, var1);
/*     */   }
/*     */   
/*     */   public int a(IRecipe<?> var0, int var1, @Nullable IntList var2) {
/*  78 */     return (new a(this, var0)).b(var1, var2);
/*     */   }
/*     */   
/*     */   public static ItemStack a(int var0) {
/*  82 */     if (var0 == 0) {
/*  83 */       return ItemStack.b;
/*     */     }
/*  85 */     return new ItemStack(Item.getById(var0));
/*     */   }
/*     */   
/*     */   public void a() {
/*  89 */     this.a.clear();
/*     */   }
/*     */   
/*     */   class a {
/*     */     private final IRecipe<?> b;
/*  94 */     private final List<RecipeItemStack> c = Lists.newArrayList();
/*     */     private final int d;
/*     */     private final int[] e;
/*     */     private final int f;
/*     */     private final BitSet g;
/*  99 */     private final IntList h = (IntList)new IntArrayList();
/*     */     
/*     */     public a(AutoRecipeStackManager this$0, IRecipe<?> var1) {
/* 102 */       this.b = var1;
/* 103 */       this.c.addAll(var1.a());
/* 104 */       this.c.removeIf(RecipeItemStack::d);
/*     */       
/* 106 */       this.d = this.c.size();
/* 107 */       this.e = a();
/* 108 */       this.f = this.e.length;
/*     */ 
/*     */       
/* 111 */       this.g = new BitSet(this.d + this.f + this.d + this.d * this.f);
/* 112 */       for (int var2 = 0; var2 < this.c.size(); var2++) {
/* 113 */         IntList var3 = ((RecipeItemStack)this.c.get(var2)).b();
/* 114 */         for (int var4 = 0; var4 < this.f; var4++) {
/* 115 */           if (var3.contains(this.e[var4])) {
/* 116 */             this.g.set(d(true, var4, var2));
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/*     */     
/*     */     public boolean a(int var0, @Nullable IntList var1) {
/* 123 */       if (var0 <= 0) {
/* 124 */         return true;
/*     */       }
/*     */       
/* 127 */       int var2 = 0;
/* 128 */       while (a(var0)) {
/* 129 */         AutoRecipeStackManager.a(this.a, this.e[this.h.getInt(0)], var0);
/*     */         
/* 131 */         int i = this.h.size() - 1;
/* 132 */         c(this.h.getInt(i));
/*     */         
/* 134 */         for (int j = 0; j < i; j++) {
/* 135 */           c(((j & 0x1) == 0), this.h.get(j).intValue(), this.h.get(j + 1).intValue());
/*     */         }
/*     */         
/* 138 */         this.h.clear();
/* 139 */         this.g.clear(0, this.d + this.f);
/*     */         
/* 141 */         var2++;
/*     */       } 
/*     */       
/* 144 */       boolean var3 = (var2 == this.d);
/* 145 */       boolean var4 = (var3 && var1 != null);
/* 146 */       if (var4) {
/* 147 */         var1.clear();
/*     */       }
/*     */ 
/*     */       
/* 151 */       this.g.clear(0, this.d + this.f + this.d);
/*     */       
/* 153 */       int var5 = 0;
/* 154 */       List<RecipeItemStack> var6 = this.b.a();
/* 155 */       for (int var7 = 0; var7 < var6.size(); var7++) {
/* 156 */         if (var4 && ((RecipeItemStack)var6.get(var7)).d()) {
/* 157 */           var1.add(0);
/*     */         } else {
/* 159 */           for (int var8 = 0; var8 < this.f; var8++) {
/* 160 */             if (b(false, var5, var8)) {
/* 161 */               c(true, var8, var5);
/* 162 */               AutoRecipeStackManager.b(this.a, this.e[var8], var0);
/*     */               
/* 164 */               if (var4) {
/* 165 */                 var1.add(this.e[var8]);
/*     */               }
/*     */             } 
/*     */           } 
/* 169 */           var5++;
/*     */         } 
/*     */       } 
/*     */       
/* 173 */       return var3;
/*     */     }
/*     */     
/*     */     private int[] a() {
/* 177 */       IntAVLTreeSet intAVLTreeSet = new IntAVLTreeSet();
/* 178 */       for (RecipeItemStack var2 : this.c) {
/* 179 */         intAVLTreeSet.addAll((IntCollection)var2.b());
/*     */       }
/*     */       
/* 182 */       IntIterator var1 = intAVLTreeSet.iterator();
/* 183 */       while (var1.hasNext()) {
/* 184 */         if (!AutoRecipeStackManager.a(this.a, var1.nextInt())) {
/* 185 */           var1.remove();
/*     */         }
/*     */       } 
/* 188 */       return intAVLTreeSet.toIntArray();
/*     */     }
/*     */     
/*     */     private boolean a(int var0) {
/* 192 */       int var1 = this.f;
/* 193 */       for (int var2 = 0; var2 < var1; var2++) {
/* 194 */         if (this.a.a.get(this.e[var2]) >= var0) {
/* 195 */           a(false, var2);
/*     */           
/* 197 */           while (!this.h.isEmpty()) {
/* 198 */             int var3 = this.h.size();
/* 199 */             boolean var4 = ((var3 & 0x1) == 1);
/*     */             
/* 201 */             int var5 = this.h.getInt(var3 - 1);
/* 202 */             if (!var4 && !b(var5)) {
/*     */               break;
/*     */             }
/*     */             
/* 206 */             int var6 = var4 ? this.d : var1; int var7;
/* 207 */             for (var7 = 0; var7 < var6; var7++) {
/* 208 */               if (!b(var4, var7) && a(var4, var5, var7) && b(var4, var5, var7)) {
/* 209 */                 a(var4, var7);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 214 */             var7 = this.h.size();
/* 215 */             if (var7 == var3) {
/* 216 */               this.h.removeInt(var7 - 1);
/*     */             }
/*     */           } 
/* 219 */           if (!this.h.isEmpty()) {
/* 220 */             return true;
/*     */           }
/*     */         } 
/*     */       } 
/*     */       
/* 225 */       return false;
/*     */     }
/*     */     
/*     */     private boolean b(int var0) {
/* 229 */       return this.g.get(d(var0));
/*     */     }
/*     */     
/*     */     private void c(int var0) {
/* 233 */       this.g.set(d(var0));
/*     */     }
/*     */     
/*     */     private int d(int var0) {
/* 237 */       return this.d + this.f + var0;
/*     */     }
/*     */     
/*     */     private boolean a(boolean var0, int var1, int var2) {
/* 241 */       return this.g.get(d(var0, var1, var2));
/*     */     }
/*     */     
/*     */     private boolean b(boolean var0, int var1, int var2) {
/* 245 */       return (var0 != this.g.get(1 + d(var0, var1, var2)));
/*     */     }
/*     */     
/*     */     private void c(boolean var0, int var1, int var2) {
/* 249 */       this.g.flip(1 + d(var0, var1, var2));
/*     */     }
/*     */     
/*     */     private int d(boolean var0, int var1, int var2) {
/* 253 */       int var3 = var0 ? (var1 * this.d + var2) : (var2 * this.d + var1);
/* 254 */       return this.d + this.f + this.d + 2 * var3;
/*     */     }
/*     */     
/*     */     private void a(boolean var0, int var1) {
/* 258 */       this.g.set(c(var0, var1));
/* 259 */       this.h.add(var1);
/*     */     }
/*     */     
/*     */     private boolean b(boolean var0, int var1) {
/* 263 */       return this.g.get(c(var0, var1));
/*     */     }
/*     */     
/*     */     private int c(boolean var0, int var1) {
/* 267 */       return (var0 ? 0 : this.d) + var1;
/*     */     }
/*     */     
/*     */     public int b(int var0, @Nullable IntList var1) {
/* 271 */       int var4, var2 = 0;
/* 272 */       int var3 = Math.min(var0, b()) + 1;
/*     */ 
/*     */       
/*     */       while (true) {
/* 276 */         var4 = (var2 + var3) / 2;
/*     */         
/* 278 */         if (a(var4, (IntList)null)) {
/* 279 */           if (var3 - var2 <= 1) {
/*     */             break;
/*     */           }
/* 282 */           var2 = var4; continue;
/*     */         } 
/* 284 */         var3 = var4;
/*     */       } 
/*     */ 
/*     */       
/* 288 */       if (var4 > 0) {
/* 289 */         a(var4, var1);
/*     */       }
/*     */       
/* 292 */       return var4;
/*     */     }
/*     */     
/*     */     private int b() {
/* 296 */       int var0 = Integer.MAX_VALUE;
/* 297 */       for (RecipeItemStack var2 : this.c) {
/* 298 */         int var3 = 0;
/* 299 */         for (IntListIterator<Integer> intListIterator = var2.b().iterator(); intListIterator.hasNext(); ) { int var5 = ((Integer)intListIterator.next()).intValue();
/* 300 */           var3 = Math.max(var3, this.a.a.get(var5)); }
/*     */         
/* 302 */         if (var0 > 0) {
/* 303 */           var0 = Math.min(var0, var3);
/*     */         }
/*     */       } 
/* 306 */       return var0;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AutoRecipeStackManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */