/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public class ResourcePackInfo
/*    */ {
/*  6 */   public static final ResourcePackInfoDeserializer a = new ResourcePackInfoDeserializer();
/*    */   
/*    */   private final IChatBaseComponent b;
/*    */   private final int c;
/*    */   
/*    */   public ResourcePackInfo(IChatBaseComponent var0, int var1) {
/* 12 */     this.b = var0;
/* 13 */     this.c = var1;
/*    */   }
/*    */   
/*    */   public IChatBaseComponent a() {
/* 17 */     return this.b;
/*    */   }
/*    */   
/*    */   public int b() {
/* 21 */     return this.c;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ResourcePackInfo.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */