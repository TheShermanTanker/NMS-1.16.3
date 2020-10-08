/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class WorldMapFrame
/*    */ {
/*    */   private final BlockPosition a;
/*    */   private final int b;
/*    */   private final int c;
/*    */   
/*    */   public WorldMapFrame(BlockPosition var0, int var1, int var2) {
/* 13 */     this.a = var0;
/* 14 */     this.b = var1;
/* 15 */     this.c = var2;
/*    */   }
/*    */   
/*    */   public static WorldMapFrame a(NBTTagCompound var0) {
/* 19 */     BlockPosition var1 = GameProfileSerializer.b(var0.getCompound("Pos"));
/* 20 */     int var2 = var0.getInt("Rotation");
/* 21 */     int var3 = var0.getInt("EntityId");
/* 22 */     return new WorldMapFrame(var1, var2, var3);
/*    */   }
/*    */   
/*    */   public NBTTagCompound a() {
/* 26 */     NBTTagCompound var0 = new NBTTagCompound();
/* 27 */     var0.set("Pos", GameProfileSerializer.a(this.a));
/* 28 */     var0.setInt("Rotation", this.b);
/* 29 */     var0.setInt("EntityId", this.c);
/* 30 */     return var0;
/*    */   }
/*    */   
/*    */   public BlockPosition b() {
/* 34 */     return this.a;
/*    */   }
/*    */   
/*    */   public int c() {
/* 38 */     return this.b;
/*    */   }
/*    */   
/*    */   public int d() {
/* 42 */     return this.c;
/*    */   }
/*    */   
/*    */   public String e() {
/* 46 */     return a(this.a);
/*    */   }
/*    */   
/*    */   public static String a(BlockPosition var0) {
/* 50 */     return "frame-" + var0.getX() + "," + var0.getY() + "," + var0.getZ();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\WorldMapFrame.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */