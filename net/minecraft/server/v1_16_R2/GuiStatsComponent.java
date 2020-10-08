/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import java.awt.Color;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.Graphics;
/*    */ import java.awt.event.ActionEvent;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import java.util.Locale;
/*    */ import javax.swing.JComponent;
/*    */ import javax.swing.Timer;
/*    */ 
/*    */ public class GuiStatsComponent extends JComponent {
/*    */   static {
/* 14 */     a = SystemUtils.<DecimalFormat>a(new DecimalFormat("########0.000"), decimalformat -> decimalformat.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));
/*    */   }
/*    */   private static final DecimalFormat a;
/* 17 */   private final int[] b = new int[256];
/*    */   private int c;
/* 19 */   private final String[] d = new String[11];
/*    */   private final MinecraftServer e;
/*    */   private final Timer f;
/*    */   
/*    */   public GuiStatsComponent(MinecraftServer minecraftserver) {
/* 24 */     this.e = minecraftserver;
/* 25 */     setPreferredSize(new Dimension(456, 246));
/* 26 */     setMinimumSize(new Dimension(456, 246));
/* 27 */     setMaximumSize(new Dimension(456, 246));
/* 28 */     this.f = new Timer(500, actionevent -> b());
/*    */ 
/*    */     
/* 31 */     this.f.start();
/* 32 */     setBackground(Color.BLACK);
/*    */   }
/*    */   
/*    */   private void b() {
/* 36 */     long i = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
/*    */     
/* 38 */     this.d[0] = "Memory use: " + (i / 1024L / 1024L) + " mb (" + (Runtime.getRuntime().freeMemory() * 100L / Runtime.getRuntime().maxMemory()) + "% free)";
/* 39 */     this.d[1] = "Avg tick: " + a.format(a(this.e.h) * 1.0E-6D) + " ms";
/* 40 */     this.b[this.c++ & 0xFF] = (int)(i * 100L / Runtime.getRuntime().maxMemory());
/* 41 */     repaint();
/*    */   }
/*    */   
/*    */   private double a(long[] along) {
/* 45 */     long i = 0L;
/* 46 */     long[] along1 = along;
/* 47 */     int j = along.length;
/*    */     
/* 49 */     for (int k = 0; k < j; k++) {
/* 50 */       long l = along1[k];
/*    */       
/* 52 */       i += l;
/*    */     } 
/*    */     
/* 55 */     return i / along.length;
/*    */   }
/*    */   
/*    */   public void paint(Graphics graphics) {
/* 59 */     graphics.setColor(new Color(16777215));
/* 60 */     graphics.fillRect(0, 0, 456, 246);
/*    */     
/*    */     int i;
/*    */     
/* 64 */     for (i = 0; i < 256; i++) {
/* 65 */       int j = this.b[i + this.c & 0xFF];
/*    */       
/* 67 */       graphics.setColor(new Color(j + 28 << 16));
/* 68 */       graphics.fillRect(i, 100 - j, 1, j);
/*    */     } 
/*    */     
/* 71 */     graphics.setColor(Color.BLACK);
/*    */     
/* 73 */     for (i = 0; i < this.d.length; i++) {
/* 74 */       String s = this.d[i];
/*    */       
/* 76 */       if (s != null) {
/* 77 */         graphics.drawString(s, 32, 116 + i * 16);
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void a() {
/* 84 */     this.f.stop();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\GuiStatsComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */