/*    */ package com.destroystokyo.paper.gui;
/*    */ 
/*    */ import java.awt.Dimension;
/*    */ import java.text.DecimalFormat;
/*    */ import java.text.DecimalFormatSymbols;
/*    */ import java.util.Locale;
/*    */ import java.util.Vector;
/*    */ import javax.swing.DefaultListCellRenderer;
/*    */ import javax.swing.DefaultListSelectionModel;
/*    */ import javax.swing.JList;
/*    */ import javax.swing.border.EmptyBorder;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import net.minecraft.server.v1_16_R2.SystemUtils;
/*    */ 
/*    */ public class RAMDetails
/*    */   extends JList<String> {
/*    */   static {
/* 18 */     DECIMAL_FORMAT = (DecimalFormat)SystemUtils.a(new DecimalFormat("########0.000"), format -> format.setDecimalFormatSymbols(DecimalFormatSymbols.getInstance(Locale.ROOT)));
/*    */   }
/*    */   public static final DecimalFormat DECIMAL_FORMAT;
/*    */   private final MinecraftServer server;
/*    */   
/*    */   public RAMDetails(MinecraftServer server) {
/* 24 */     this.server = server;
/*    */     
/* 26 */     setBorder(new EmptyBorder(0, 10, 0, 0));
/* 27 */     setFixedCellHeight(20);
/* 28 */     setOpaque(false);
/*    */     
/* 30 */     DefaultListCellRenderer renderer = new DefaultListCellRenderer();
/* 31 */     renderer.setOpaque(false);
/* 32 */     setCellRenderer(renderer);
/*    */     
/* 34 */     setSelectionModel(new DefaultListSelectionModel()
/*    */         {
/*    */           public void setAnchorSelectionIndex(int anchorIndex) {}
/*    */ 
/*    */ 
/*    */           
/*    */           public void setLeadAnchorNotificationEnabled(boolean flag) {}
/*    */ 
/*    */ 
/*    */           
/*    */           public void setLeadSelectionIndex(int leadIndex) {}
/*    */ 
/*    */ 
/*    */           
/*    */           public void setSelectionInterval(int index0, int index1) {}
/*    */         });
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public Dimension getPreferredSize() {
/* 55 */     return new Dimension(350, 100);
/*    */   }
/*    */   
/*    */   public void update() {
/* 59 */     GraphData data = RAMGraph.DATA.peekLast();
/* 60 */     Vector<String> vector = new Vector<>();
/* 61 */     vector.add("Memory use: " + (data.getUsedMem() / 1024L / 1024L) + " mb (" + (data.getFree() * 100L / data.getMax()) + "% free)");
/* 62 */     vector.add("Heap: " + (data.getTotal() / 1024L / 1024L) + " / " + (data.getMax() / 1024L / 1024L) + " mb");
/* 63 */     vector.add("Avg tick: " + DECIMAL_FORMAT.format(getAverage(this.server.getTickTimes())) + " ms");
/* 64 */     setListData(vector);
/*    */   }
/*    */   
/*    */   public double getAverage(long[] tickTimes) {
/* 68 */     long total = 0L;
/* 69 */     for (long value : tickTimes) {
/* 70 */       total += value;
/*    */     }
/* 72 */     return total / tickTimes.length * 1.0E-6D;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\gui\RAMDetails.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */