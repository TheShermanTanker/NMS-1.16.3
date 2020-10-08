/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public enum EnumItemSlot
/*    */ {
/*  5 */   MAINHAND(Function.HAND, 0, 0, "mainhand"), OFFHAND(Function.HAND, 1, 5, "offhand"), FEET(Function.ARMOR, 0, 1, "feet"), LEGS(Function.ARMOR, 1, 2, "legs"), CHEST(Function.ARMOR, 2, 3, "chest"), HEAD(Function.ARMOR, 3, 4, "head");
/*    */   
/*    */   private final String j;
/*    */   private final int i;
/*    */   private final int h;
/*    */   private final Function g;
/*    */   
/*    */   EnumItemSlot(Function enumitemslot_function, int i, int j, String s) {
/* 13 */     this.g = enumitemslot_function;
/* 14 */     this.h = i;
/* 15 */     this.i = j;
/* 16 */     this.j = s;
/*    */   }
/*    */   public Function getType() {
/* 19 */     return a();
/*    */   } public Function a() {
/* 21 */     return this.g;
/*    */   }
/*    */   
/*    */   public int b() {
/* 25 */     return this.h;
/*    */   }
/*    */   
/*    */   public int getSlotFlag() {
/* 29 */     return this.i;
/*    */   }
/*    */   
/*    */   public String getSlotName() {
/* 33 */     return this.j;
/*    */   }
/*    */   
/*    */   public static EnumItemSlot fromName(String s) {
/* 37 */     EnumItemSlot[] aenumitemslot = values();
/* 38 */     int i = aenumitemslot.length;
/*    */     
/* 40 */     for (int j = 0; j < i; j++) {
/* 41 */       EnumItemSlot enumitemslot = aenumitemslot[j];
/*    */       
/* 43 */       if (enumitemslot.getSlotName().equals(s)) {
/* 44 */         return enumitemslot;
/*    */       }
/*    */     } 
/*    */     
/* 48 */     throw new IllegalArgumentException("Invalid slot '" + s + "'");
/*    */   }
/*    */   
/*    */   public static EnumItemSlot a(Function enumitemslot_function, int i) {
/* 52 */     EnumItemSlot[] aenumitemslot = values();
/* 53 */     int j = aenumitemslot.length;
/*    */     
/* 55 */     for (int k = 0; k < j; k++) {
/* 56 */       EnumItemSlot enumitemslot = aenumitemslot[k];
/*    */       
/* 58 */       if (enumitemslot.a() == enumitemslot_function && enumitemslot.b() == i) {
/* 59 */         return enumitemslot;
/*    */       }
/*    */     } 
/*    */     
/* 63 */     throw new IllegalArgumentException("Invalid slot '" + enumitemslot_function + "': " + i);
/*    */   }
/*    */   
/*    */   public enum Function
/*    */   {
/* 68 */     HAND, ARMOR;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumItemSlot.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */