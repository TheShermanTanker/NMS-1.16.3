/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum BlockPropertyChestType
/*    */   implements INamable {
/*    */   public static final BlockPropertyChestType[] d;
/*  6 */   SINGLE("single", 0),
/*  7 */   LEFT("left", 2),
/*  8 */   RIGHT("right", 1);
/*    */   
/*    */   static {
/* 11 */     d = values();
/*    */   }
/*    */   
/*    */   private final String e;
/*    */   
/*    */   BlockPropertyChestType(String var2, int var3) {
/* 17 */     this.e = var2;
/* 18 */     this.f = var3;
/*    */   }
/*    */   private final int f;
/*    */   
/*    */   public String getName() {
/* 23 */     return this.e;
/*    */   }
/*    */   
/*    */   public BlockPropertyChestType b() {
/* 27 */     return d[this.f];
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyChestType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */