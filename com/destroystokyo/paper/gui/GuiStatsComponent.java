/*    */ package com.destroystokyo.paper.gui;
/*    */ 
/*    */ import java.awt.BorderLayout;
/*    */ import java.awt.Dimension;
/*    */ import java.awt.event.ActionEvent;
/*    */ import javax.swing.JPanel;
/*    */ import javax.swing.Timer;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ 
/*    */ public class GuiStatsComponent extends JPanel {
/*    */   private final Timer timer;
/*    */   private final RAMGraph ramGraph;
/*    */   
/*    */   public GuiStatsComponent(MinecraftServer server) {
/* 15 */     super(new BorderLayout());
/*    */     
/* 17 */     setOpaque(false);
/*    */     
/* 19 */     this.ramGraph = new RAMGraph();
/* 20 */     RAMDetails ramDetails = new RAMDetails(server);
/*    */     
/* 22 */     add(this.ramGraph, "North");
/* 23 */     add(ramDetails, "Center");
/*    */     
/* 25 */     this.timer = new Timer(500, event -> {
/*    */           this.ramGraph.update();
/*    */           ramDetails.update();
/*    */         });
/* 29 */     this.timer.start();
/*    */   }
/*    */ 
/*    */   
/*    */   public Dimension getPreferredSize() {
/* 34 */     return new Dimension(350, 200);
/*    */   }
/*    */   
/* 37 */   public void stop() { a(); } public void a() {
/* 38 */     this.timer.stop();
/* 39 */     this.ramGraph.stop();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\gui\GuiStatsComponent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */