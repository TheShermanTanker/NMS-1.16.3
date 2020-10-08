/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ItemHorseArmor
/*    */   extends Item
/*    */ {
/*    */   private final int a;
/*    */   private final String b;
/*    */   
/*    */   public ItemHorseArmor(int var0, String var1, Item.Info var2) {
/* 12 */     super(var2);
/* 13 */     this.a = var0;
/* 14 */     this.b = "textures/entity/horse/armor/horse_armor_" + var1 + ".png";
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int g() {
/* 22 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\ItemHorseArmor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */