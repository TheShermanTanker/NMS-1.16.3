/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.io.File;
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
/*    */ public class WorldInfo
/*    */   implements Comparable<WorldInfo>
/*    */ {
/*    */   private final WorldSettings a;
/*    */   private final LevelVersion b;
/*    */   private final String c;
/*    */   private final boolean d;
/*    */   private final boolean e;
/*    */   private final File f;
/*    */   
/*    */   public WorldInfo(WorldSettings var0, LevelVersion var1, String var2, boolean var3, boolean var4, File var5) {
/* 28 */     this.a = var0;
/* 29 */     this.b = var1;
/* 30 */     this.c = var2;
/* 31 */     this.e = var4;
/* 32 */     this.f = var5;
/* 33 */     this.d = var3;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int compareTo(WorldInfo var0) {
/* 58 */     if (this.b.b() < var0.b.b()) {
/* 59 */       return 1;
/*    */     }
/* 61 */     if (this.b.b() > var0.b.b()) {
/* 62 */       return -1;
/*    */     }
/* 64 */     return this.c.compareTo(var0.c);
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
/*    */   public LevelVersion k() {
/* 91 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */