/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import it.unimi.dsi.fastutil.doubles.DoubleArrayList;
/*    */ import it.unimi.dsi.fastutil.doubles.DoubleList;
/*    */ import it.unimi.dsi.fastutil.ints.IntArrayList;
/*    */ 
/*    */ 
/*    */ public final class VoxelShapeMergerList
/*    */   implements VoxelShapeMerger
/*    */ {
/*    */   private final DoubleList a;
/*    */   private final IntArrayList b;
/*    */   private final IntArrayList c;
/* 14 */   private static final IntArrayList INFINITE_B_1 = new IntArrayList(new int[] { 1, 1 });
/* 15 */   private static final IntArrayList INFINITE_B_0 = new IntArrayList(new int[] { 0, 0 });
/* 16 */   private static final IntArrayList INFINITE_C = new IntArrayList(new int[] { 0, 1 });
/*    */ 
/*    */   
/*    */   protected VoxelShapeMergerList(DoubleList doublelist, DoubleList doublelist1, boolean flag, boolean flag1) {
/* 20 */     int i = 0;
/* 21 */     int j = 0;
/* 22 */     double d0 = Double.NaN;
/* 23 */     int k = doublelist.size();
/* 24 */     int l = doublelist1.size();
/* 25 */     int i1 = k + l;
/*    */ 
/*    */     
/* 28 */     int size = doublelist.size();
/* 29 */     double tail = doublelist.getDouble(size - 1);
/* 30 */     double head = doublelist.getDouble(0);
/* 31 */     if (head == Double.NEGATIVE_INFINITY && tail == Double.POSITIVE_INFINITY && !flag && !flag1 && (size == 2 || size == 4)) {
/* 32 */       this.a = doublelist1;
/* 33 */       if (size == 2) {
/* 34 */         this.b = INFINITE_B_0;
/*    */       } else {
/* 36 */         this.b = INFINITE_B_1;
/*    */       } 
/* 38 */       this.c = INFINITE_C;
/*    */       
/*    */       return;
/*    */     } 
/*    */     
/* 43 */     this.a = (DoubleList)new DoubleArrayList(i1);
/* 44 */     this.b = new IntArrayList(i1);
/* 45 */     this.c = new IntArrayList(i1);
/*    */     
/*    */     while (true) {
/* 48 */       boolean flag2 = (i < k);
/* 49 */       boolean flag3 = (j < l);
/*    */       
/* 51 */       if (!flag2 && !flag3) {
/* 52 */         if (this.a.isEmpty()) {
/* 53 */           this.a.add(Math.min(doublelist.getDouble(k - 1), doublelist1.getDouble(l - 1)));
/*    */         }
/*    */         
/*    */         return;
/*    */       } 
/*    */       
/* 59 */       boolean flag4 = (flag2 && (!flag3 || doublelist.getDouble(i) < doublelist1.getDouble(j) + 1.0E-7D));
/* 60 */       double d1 = flag4 ? doublelist.getDouble(i++) : doublelist1.getDouble(j++);
/*    */       
/* 62 */       if (((i != 0 && flag2) || flag4 || flag1) && ((j != 0 && flag3) || !flag4 || flag)) {
/* 63 */         if (d0 < d1 - 1.0E-7D) {
/* 64 */           this.b.add(i - 1);
/* 65 */           this.c.add(j - 1);
/* 66 */           this.a.add(d1);
/* 67 */           d0 = d1; continue;
/* 68 */         }  if (!this.a.isEmpty()) {
/* 69 */           this.b.set(this.b.size() - 1, i - 1);
/* 70 */           this.c.set(this.c.size() - 1, j - 1);
/*    */         } 
/*    */       } 
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean a(VoxelShapeMerger.a voxelshapemerger_a) {
/* 78 */     for (int i = 0; i < this.a.size() - 1; i++) {
/* 79 */       if (!voxelshapemerger_a.merge(this.b.getInt(i), this.c.getInt(i), i)) {
/* 80 */         return false;
/*    */       }
/*    */     } 
/*    */     
/* 84 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public DoubleList a() {
/* 89 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VoxelShapeMergerList.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */