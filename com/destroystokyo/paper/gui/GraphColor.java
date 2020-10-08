/*    */ package com.destroystokyo.paper.gui;
/*    */ 
/*    */ import java.awt.Color;
/*    */ 
/*    */ public class GraphColor {
/*  6 */   private static final Color[] colorLine = new Color[101];
/*  7 */   private static final Color[] colorFill = new Color[101];
/*    */   
/*    */   static {
/* 10 */     for (int i = 0; i < 101; i++) {
/* 11 */       Color color = createColor(i);
/* 12 */       colorLine[i] = new Color(color.getRed() / 2, color.getGreen() / 2, color.getBlue() / 2, 255);
/* 13 */       colorFill[i] = new Color(colorLine[i].getRed(), colorLine[i].getGreen(), colorLine[i].getBlue(), 125);
/*    */     } 
/*    */   }
/*    */   
/*    */   public static Color getLineColor(int percent) {
/* 18 */     return colorLine[percent];
/*    */   }
/*    */   
/*    */   public static Color getFillColor(int percent) {
/* 22 */     return colorFill[percent];
/*    */   }
/*    */   private static Color createColor(int percent) {
/*    */     int red, green;
/* 26 */     if (percent <= 50) {
/* 27 */       return new Color(65280);
/*    */     }
/*    */     
/* 30 */     int value = 510 - (int)(Math.min(Math.max(0.0F, (percent - 50) / 50.0F), 1.0F) * 510.0F);
/*    */ 
/*    */     
/* 33 */     if (value < 255) {
/* 34 */       red = 255;
/* 35 */       green = (int)(Math.sqrt(value) * 16.0D);
/*    */     } else {
/* 37 */       green = 255;
/* 38 */       value -= 255;
/* 39 */       red = 255 - value * value / 255;
/*    */     } 
/*    */     
/* 42 */     return new Color(red, green, 0);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\gui\GraphColor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */