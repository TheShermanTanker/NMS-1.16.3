/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Comparator;
/*    */ import javax.annotation.Nullable;
/*    */ 
/*    */ 
/*    */ public enum EnumDifficulty
/*    */ {
/*    */   private static final EnumDifficulty[] e;
/* 11 */   PEACEFUL(0, "peaceful"),
/* 12 */   EASY(1, "easy"),
/* 13 */   NORMAL(2, "normal"),
/* 14 */   HARD(3, "hard");
/*    */   
/*    */   static {
/* 17 */     e = (EnumDifficulty[])Arrays.<EnumDifficulty>stream(values()).sorted(Comparator.comparingInt(EnumDifficulty::a)).toArray(var0 -> new EnumDifficulty[var0]);
/*    */   }
/*    */   
/*    */   private final int f;
/*    */   
/*    */   EnumDifficulty(int var2, String var3) {
/* 23 */     this.f = var2;
/* 24 */     this.g = var3;
/*    */   }
/*    */   private final String g;
/*    */   public int a() {
/* 28 */     return this.f;
/*    */   }
/*    */   
/*    */   public IChatBaseComponent b() {
/* 32 */     return new ChatMessage("options.difficulty." + this.g);
/*    */   }
/*    */   
/*    */   public static EnumDifficulty getById(int var0) {
/* 36 */     return e[var0 % e.length];
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public static EnumDifficulty a(String var0) {
/* 41 */     for (EnumDifficulty var4 : values()) {
/* 42 */       if (var4.g.equals(var0)) {
/* 43 */         return var4;
/*    */       }
/*    */     } 
/* 46 */     return null;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String c() {
/* 53 */     return this.g;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumDifficulty.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */