/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Vector3f
/*    */ {
/*    */   protected final float x;
/*    */   protected final float y;
/*    */   protected final float z;
/*    */   
/*    */   public Vector3f(float var0, float var1, float var2) {
/* 13 */     this.x = (Float.isInfinite(var0) || Float.isNaN(var0)) ? 0.0F : (var0 % 360.0F);
/* 14 */     this.y = (Float.isInfinite(var1) || Float.isNaN(var1)) ? 0.0F : (var1 % 360.0F);
/* 15 */     this.z = (Float.isInfinite(var2) || Float.isNaN(var2)) ? 0.0F : (var2 % 360.0F);
/*    */   }
/*    */   
/*    */   public Vector3f(NBTTagList var0) {
/* 19 */     this(var0.i(0), var0.i(1), var0.i(2));
/*    */   }
/*    */   
/*    */   public NBTTagList a() {
/* 23 */     NBTTagList var0 = new NBTTagList();
/* 24 */     var0.add(NBTTagFloat.a(this.x));
/* 25 */     var0.add(NBTTagFloat.a(this.y));
/* 26 */     var0.add(NBTTagFloat.a(this.z));
/* 27 */     return var0;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object var0) {
/* 32 */     if (!(var0 instanceof Vector3f)) {
/* 33 */       return false;
/*    */     }
/* 35 */     Vector3f var1 = (Vector3f)var0;
/* 36 */     return (this.x == var1.x && this.y == var1.y && this.z == var1.z);
/*    */   }
/*    */   
/*    */   public float getX() {
/* 40 */     return this.x;
/*    */   }
/*    */   
/*    */   public float getY() {
/* 44 */     return this.y;
/*    */   }
/*    */   
/*    */   public float getZ() {
/* 48 */     return this.z;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Vector3f.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */