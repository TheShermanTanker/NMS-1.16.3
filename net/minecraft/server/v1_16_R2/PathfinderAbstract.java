/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import it.unimi.dsi.fastutil.ints.Int2ObjectMap;
/*    */ 
/*    */ public abstract class PathfinderAbstract {
/*    */   protected ChunkCache a;
/*    */   protected EntityInsentient b;
/*    */   protected int d;
/*    */   protected int e;
/*    */   protected int f;
/* 10 */   protected final Int2ObjectMap<PathPoint> c = (Int2ObjectMap<PathPoint>)new Int2ObjectOpenHashMap(); protected boolean g; protected boolean h;
/*    */   protected boolean i;
/*    */   
/*    */   public boolean shouldPassDoors() {
/* 14 */     return this.g; } public void setShouldPassDoors(boolean b) { this.g = b; }
/* 15 */   public boolean shouldOpenDoors() { return this.h; } public void setShouldOpenDoors(boolean b) { this.h = b; }
/* 16 */   public boolean shouldFloat() { return this.i; } public void setShouldFloat(boolean b) { this.i = b; }
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(ChunkCache chunkcache, EntityInsentient entityinsentient) {
/* 21 */     this.a = chunkcache;
/* 22 */     this.b = entityinsentient;
/* 23 */     this.c.clear();
/* 24 */     this.d = MathHelper.d(entityinsentient.getWidth() + 1.0F);
/* 25 */     this.e = MathHelper.d(entityinsentient.getHeight() + 1.0F);
/* 26 */     this.f = MathHelper.d(entityinsentient.getWidth() + 1.0F);
/*    */   }
/*    */   
/*    */   public void a() {
/* 30 */     this.a = null;
/* 31 */     this.b = null;
/*    */   }
/*    */   
/*    */   protected PathPoint a(BlockPosition blockposition) {
/* 35 */     return a(blockposition.getX(), blockposition.getY(), blockposition.getZ());
/*    */   }
/*    */   
/*    */   protected PathPoint a(int i, int j, int k) {
/* 39 */     return (PathPoint)this.c.computeIfAbsent(PathPoint.b(i, j, k), l -> new PathPoint(i, j, k));
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public void a(boolean flag) {
/* 55 */     this.g = flag;
/*    */   }
/*    */   
/*    */   public void b(boolean flag) {
/* 59 */     this.h = flag;
/*    */   }
/*    */   
/*    */   public void c(boolean flag) {
/* 63 */     this.i = flag;
/*    */   }
/*    */   
/*    */   public boolean c() {
/* 67 */     return this.g;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 71 */     return this.h;
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 75 */     return this.i;
/*    */   }
/*    */   
/*    */   public abstract PathPoint b();
/*    */   
/*    */   public abstract PathDestination a(double paramDouble1, double paramDouble2, double paramDouble3);
/*    */   
/*    */   public abstract int a(PathPoint[] paramArrayOfPathPoint, PathPoint paramPathPoint);
/*    */   
/*    */   public abstract PathType a(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3, EntityInsentient paramEntityInsentient, int paramInt4, int paramInt5, int paramInt6, boolean paramBoolean1, boolean paramBoolean2);
/*    */   
/*    */   public abstract PathType a(IBlockAccess paramIBlockAccess, int paramInt1, int paramInt2, int paramInt3);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PathfinderAbstract.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */