/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class GenLayerSpecial
/*    */ {
/*    */   public enum Special1
/*    */     implements AreaTransformer7
/*    */   {
/*  9 */     INSTANCE;
/*    */ 
/*    */     
/*    */     public int a(WorldGenContext var0, int var1, int var2, int var3, int var4, int var5) {
/* 13 */       if (var5 == 1 && (var1 == 3 || var2 == 3 || var4 == 3 || var3 == 3 || var1 == 4 || var2 == 4 || var4 == 4 || var3 == 4))
/*    */       {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 23 */         return 2;
/*    */       }
/*    */       
/* 26 */       return var5;
/*    */     }
/*    */   }
/*    */   
/*    */   public enum Special2 implements AreaTransformer7 {
/* 31 */     INSTANCE;
/*    */ 
/*    */     
/*    */     public int a(WorldGenContext var0, int var1, int var2, int var3, int var4, int var5) {
/* 35 */       if (var5 == 4 && (var1 == 1 || var2 == 1 || var4 == 1 || var3 == 1 || var1 == 2 || var2 == 2 || var4 == 2 || var3 == 2))
/*    */       {
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */         
/* 45 */         return 3;
/*    */       }
/*    */       
/* 48 */       return var5;
/*    */     }
/*    */   }
/*    */   
/*    */   public enum Special3 implements AreaTransformer5 {
/* 53 */     INSTANCE;
/*    */ 
/*    */     
/*    */     public int a(WorldGenContext var0, int var1) {
/* 57 */       if (!GenLayers.b(var1) && var0.a(13) == 0) {
/* 58 */         var1 |= 1 + var0.a(15) << 8 & 0xF00;
/*    */       }
/*    */       
/* 61 */       return var1;
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GenLayerSpecial.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */