/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import it.unimi.dsi.fastutil.ints.IntArrayList;
/*     */ import it.unimi.dsi.fastutil.ints.IntList;
/*     */ import it.unimi.dsi.fastutil.ints.IntListIterator;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import javax.annotation.Nullable;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
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
/*     */ public class AutoRecipe<C extends IInventory>
/*     */   implements AutoRecipeAbstract<Integer>
/*     */ {
/*  25 */   protected static final Logger LOGGER = LogManager.getLogger();
/*     */   
/*  27 */   protected final AutoRecipeStackManager b = new AutoRecipeStackManager();
/*     */   
/*     */   protected PlayerInventory c;
/*     */   protected ContainerRecipeBook<C> d;
/*     */   
/*     */   public AutoRecipe(ContainerRecipeBook<C> var0) {
/*  33 */     this.d = var0;
/*     */   }
/*     */   
/*     */   public void a(EntityPlayer var0, @Nullable IRecipe<C> var1, boolean var2) {
/*  37 */     if (var1 == null || !var0.getRecipeBook().b(var1)) {
/*     */       return;
/*     */     }
/*     */     
/*  41 */     this.c = var0.inventory;
/*     */ 
/*     */     
/*  44 */     if (!b() && !var0.isCreative()) {
/*     */       return;
/*     */     }
/*     */     
/*  48 */     this.b.a();
/*  49 */     var0.inventory.a(this.b);
/*  50 */     this.d.a(this.b);
/*     */     
/*  52 */     if (this.b.a(var1, (IntList)null)) {
/*  53 */       a(var1, var2);
/*     */     } else {
/*  55 */       a();
/*  56 */       var0.playerConnection.sendPacket(new PacketPlayOutAutoRecipe(var0.activeContainer.windowId, var1));
/*     */     } 
/*     */     
/*  59 */     var0.inventory.update();
/*     */   }
/*     */   
/*     */   protected void a() {
/*  63 */     for (int var0 = 0; var0 < this.d.g() * this.d.h() + 1; var0++) {
/*  64 */       if (var0 != this.d.f() || (!(this.d instanceof ContainerWorkbench) && !(this.d instanceof ContainerPlayer)))
/*     */       {
/*     */         
/*  67 */         a(var0);
/*     */       }
/*     */     } 
/*  70 */     this.d.e();
/*     */   }
/*     */   
/*     */   protected void a(int var0) {
/*  74 */     ItemStack var1 = this.d.getSlot(var0).getItem();
/*  75 */     if (var1.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/*  79 */     while (var1.getCount() > 0) {
/*  80 */       int var2 = this.c.firstPartial(var1);
/*  81 */       if (var2 == -1) {
/*  82 */         var2 = this.c.getFirstEmptySlotIndex();
/*     */       }
/*  84 */       ItemStack var3 = var1.cloneItemStack();
/*  85 */       var3.setCount(1);
/*     */       
/*  87 */       if (!this.c.c(var2, var3)) {
/*  88 */         LOGGER.error("Can't find any space for item in the inventory");
/*     */       }
/*     */       
/*  91 */       this.d.getSlot(var0).a(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   protected void a(IRecipe<C> var0, boolean var1) {
/*  96 */     boolean var2 = this.d.a(var0);
/*  97 */     int var3 = this.b.b(var0, (IntList)null);
/*     */ 
/*     */     
/* 100 */     if (var2) {
/* 101 */       for (int i = 0; i < this.d.h() * this.d.g() + 1; i++) {
/* 102 */         if (i != this.d.f()) {
/*     */ 
/*     */           
/* 105 */           ItemStack var5 = this.d.getSlot(i).getItem();
/* 106 */           if (!var5.isEmpty() && Math.min(var3, var5.getMaxStackSize()) < var5.getCount() + 1) {
/*     */             return;
/*     */           }
/*     */         } 
/*     */       } 
/*     */     }
/* 112 */     int var4 = a(var1, var3, var2);
/* 113 */     IntArrayList intArrayList = new IntArrayList();
/* 114 */     if (this.b.a(var0, (IntList)intArrayList, var4)) {
/*     */       
/* 116 */       int var6 = var4;
/* 117 */       for (IntListIterator<Integer> intListIterator = intArrayList.iterator(); intListIterator.hasNext(); ) { int var8 = ((Integer)intListIterator.next()).intValue();
/* 118 */         int var9 = AutoRecipeStackManager.a(var8).getMaxStackSize();
/* 119 */         if (var9 < var6) {
/* 120 */           var6 = var9;
/*     */         } }
/*     */       
/* 123 */       var4 = var6;
/*     */ 
/*     */       
/* 126 */       if (this.b.a(var0, (IntList)intArrayList, var4)) {
/* 127 */         a();
/* 128 */         a(this.d.g(), this.d.h(), this.d.f(), var0, (Iterator<Integer>)intArrayList.iterator(), var4);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(Iterator<Integer> var0, int var1, int var2, int var3, int var4) {
/* 135 */     Slot var5 = this.d.getSlot(var1);
/* 136 */     ItemStack var6 = AutoRecipeStackManager.a(((Integer)var0.next()).intValue());
/* 137 */     if (!var6.isEmpty()) {
/* 138 */       for (int var7 = 0; var7 < var2; var7++) {
/* 139 */         a(var5, var6);
/*     */       }
/*     */     }
/*     */   }
/*     */   
/*     */   protected int a(boolean var0, int var1, boolean var2) {
/* 145 */     int var3 = 1;
/* 146 */     if (var0) {
/* 147 */       var3 = var1;
/* 148 */     } else if (var2) {
/* 149 */       var3 = 64;
/* 150 */       for (int var4 = 0; var4 < this.d.g() * this.d.h() + 1; var4++) {
/* 151 */         if (var4 != this.d.f()) {
/*     */ 
/*     */ 
/*     */           
/* 155 */           ItemStack var5 = this.d.getSlot(var4).getItem();
/* 156 */           if (!var5.isEmpty() && var3 > var5.getCount()) {
/* 157 */             var3 = var5.getCount();
/*     */           }
/*     */         } 
/*     */       } 
/* 161 */       if (var3 < 64) {
/* 162 */         var3++;
/*     */       }
/*     */     } 
/*     */     
/* 166 */     return var3;
/*     */   }
/*     */   
/*     */   protected void a(Slot var0, ItemStack var1) {
/* 170 */     int var2 = this.c.c(var1);
/* 171 */     if (var2 == -1) {
/*     */       return;
/*     */     }
/* 174 */     ItemStack var3 = this.c.getItem(var2).cloneItemStack();
/*     */     
/* 176 */     if (var3.isEmpty()) {
/*     */       return;
/*     */     }
/*     */     
/* 180 */     if (var3.getCount() > 1) {
/* 181 */       this.c.splitStack(var2, 1);
/*     */     } else {
/* 183 */       this.c.splitWithoutUpdate(var2);
/*     */     } 
/* 185 */     var3.setCount(1);
/*     */     
/* 187 */     if (var0.getItem().isEmpty()) {
/* 188 */       var0.set(var3);
/*     */     } else {
/* 190 */       var0.getItem().add(1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private boolean b() {
/* 195 */     List<ItemStack> var0 = Lists.newArrayList();
/* 196 */     int var1 = c();
/*     */     
/* 198 */     for (int var2 = 0; var2 < this.d.g() * this.d.h() + 1; var2++) {
/* 199 */       if (var2 != this.d.f()) {
/*     */ 
/*     */ 
/*     */         
/* 203 */         ItemStack var3 = this.d.getSlot(var2).getItem().cloneItemStack();
/* 204 */         if (!var3.isEmpty()) {
/*     */ 
/*     */ 
/*     */           
/* 208 */           int var4 = this.c.firstPartial(var3);
/* 209 */           if (var4 == -1 && var0.size() <= var1) {
/* 210 */             for (ItemStack var6 : var0) {
/* 211 */               if (var6.doMaterialsMatch(var3) && var6.getCount() != var6.getMaxStackSize() && var6.getCount() + var3.getCount() <= var6.getMaxStackSize()) {
/* 212 */                 var6.add(var3.getCount());
/* 213 */                 var3.setCount(0);
/*     */                 
/*     */                 break;
/*     */               } 
/*     */             } 
/* 218 */             if (!var3.isEmpty()) {
/* 219 */               if (var0.size() < var1) {
/* 220 */                 var0.add(var3);
/*     */               } else {
/* 222 */                 return false;
/*     */               
/*     */               }
/*     */ 
/*     */             
/*     */             }
/*     */           }
/* 229 */           else if (var4 == -1) {
/* 230 */             return false;
/*     */           } 
/*     */         } 
/*     */       } 
/* 234 */     }  return true;
/*     */   }
/*     */   
/*     */   private int c() {
/* 238 */     int var0 = 0;
/* 239 */     for (ItemStack var2 : this.c.items) {
/* 240 */       if (var2.isEmpty()) {
/* 241 */         var0++;
/*     */       }
/*     */     } 
/* 244 */     return var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\AutoRecipe.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */