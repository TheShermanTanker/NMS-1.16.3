/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class is {
/*    */   public static final it<a> a;
/*    */   public static final it<a> b;
/*    */   public static final it<MinecraftKey> c;
/*    */   
/*  8 */   public enum a { a(0),
/*  9 */     b(90),
/* 10 */     c(180),
/* 11 */     d(270);
/*    */     
/*    */     private final int e;
/*    */     
/*    */     a(int var2) {
/* 16 */       this.e = var2;
/*    */     } }
/*    */   
/*    */   static {
/* 20 */     a = new it<>("x", var0 -> new JsonPrimitive(Integer.valueOf(a.a(var0))));
/* 21 */     b = new it<>("y", var0 -> new JsonPrimitive(Integer.valueOf(a.a(var0))));
/* 22 */     c = new it<>("model", var0 -> new JsonPrimitive(var0.toString()));
/* 23 */   } public static final it<Boolean> d = new it<>("uvlock", JsonPrimitive::new);
/* 24 */   public static final it<Integer> e = new it<>("weight", JsonPrimitive::new);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\is.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */