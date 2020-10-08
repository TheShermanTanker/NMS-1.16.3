/*     */ package com.destroystokyo.paper.gui;
/*     */ import java.awt.Color;
/*     */ import java.awt.Dimension;
/*     */ import java.awt.Graphics;
/*     */ import java.awt.MouseInfo;
/*     */ import java.awt.Point;
/*     */ import java.awt.PointerInfo;
/*     */ import java.awt.event.ActionEvent;
/*     */ import java.awt.event.MouseAdapter;
/*     */ import java.awt.event.MouseEvent;
/*     */ import java.text.SimpleDateFormat;
/*     */ import java.util.Date;
/*     */ import java.util.LinkedList;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import javax.swing.JComponent;
/*     */ import javax.swing.SwingUtilities;
/*     */ import javax.swing.Timer;
/*     */ import javax.swing.ToolTipManager;
/*     */ 
/*     */ public class RAMGraph extends JComponent {
/*  21 */   public static final LinkedList<GraphData> DATA = new LinkedList<GraphData>()
/*     */     {
/*     */       public boolean add(GraphData data) {
/*  24 */         if (size() >= 348) {
/*  25 */           remove();
/*     */         }
/*  27 */         return super.add(data);
/*     */       }
/*     */     };
/*     */   
/*     */   static {
/*  32 */     GraphData empty = new GraphData(0L, 0L, 0L);
/*  33 */     for (int i = 0; i < 350; i++) {
/*  34 */       DATA.add(empty);
/*     */     }
/*     */   }
/*     */   
/*     */   private final Timer timer;
/*  39 */   private final SimpleDateFormat TIME_FORMAT = new SimpleDateFormat("HH:mm:ss");
/*     */   
/*     */   private int currentTick;
/*     */   
/*     */   public RAMGraph() {
/*  44 */     ToolTipManager.sharedInstance().setInitialDelay(0);
/*     */     
/*  46 */     addMouseListener(new MouseAdapter() {
/*  47 */           final int defaultDismissTimeout = ToolTipManager.sharedInstance().getDismissDelay();
/*  48 */           final int dismissDelayMinutes = (int)TimeUnit.MINUTES.toMillis(10L);
/*     */ 
/*     */           
/*     */           public void mouseEntered(MouseEvent me) {
/*  52 */             ToolTipManager.sharedInstance().setDismissDelay(this.dismissDelayMinutes);
/*     */           }
/*     */ 
/*     */           
/*     */           public void mouseExited(MouseEvent me) {
/*  57 */             ToolTipManager.sharedInstance().setDismissDelay(this.defaultDismissTimeout);
/*     */           }
/*     */         });
/*     */     
/*  61 */     this.timer = new Timer(50, event -> repaint());
/*  62 */     this.timer.start();
/*     */   }
/*     */ 
/*     */   
/*     */   public Dimension getPreferredSize() {
/*  67 */     return new Dimension(350, 110);
/*     */   }
/*     */   
/*     */   public void update() {
/*  71 */     Runtime jvm = Runtime.getRuntime();
/*  72 */     DATA.add(new GraphData(jvm.totalMemory(), jvm.freeMemory(), jvm.maxMemory()));
/*     */     
/*  74 */     PointerInfo pointerInfo = MouseInfo.getPointerInfo();
/*  75 */     if (pointerInfo != null) {
/*  76 */       Point point = pointerInfo.getLocation();
/*  77 */       if (point != null) {
/*  78 */         Point loc = new Point(point);
/*  79 */         SwingUtilities.convertPointFromScreen(loc, this);
/*  80 */         if (contains(loc)) {
/*  81 */           ToolTipManager.sharedInstance().mouseMoved(new MouseEvent(this, -1, 
/*  82 */                 System.currentTimeMillis(), 0, loc.x, loc.y, point.x, point.y, 0, false, 0));
/*     */         }
/*     */       } 
/*     */     } 
/*     */ 
/*     */     
/*  88 */     this.currentTick++;
/*     */   }
/*     */ 
/*     */   
/*     */   public void paint(Graphics graphics) {
/*  93 */     graphics.setColor(new Color(-1));
/*  94 */     graphics.fillRect(0, 0, 350, 100);
/*     */     
/*  96 */     graphics.setColor(new Color(8947848));
/*  97 */     graphics.drawLine(1, 25, 348, 25);
/*  98 */     graphics.drawLine(1, 50, 348, 50);
/*  99 */     graphics.drawLine(1, 75, 348, 75);
/*     */     
/* 101 */     int i = 0;
/* 102 */     for (GraphData data : DATA) {
/* 103 */       i++;
/* 104 */       if ((i + this.currentTick) % 120 == 0) {
/* 105 */         graphics.setColor(new Color(8947848));
/* 106 */         graphics.drawLine(i, 1, i, 99);
/*     */       } 
/* 108 */       int used = data.getUsedPercent();
/* 109 */       if (used > 0) {
/* 110 */         Color color = data.getLineColor();
/* 111 */         graphics.setColor(data.getFillColor());
/* 112 */         graphics.fillRect(i, 100 - used, 1, used);
/* 113 */         graphics.setColor(color);
/* 114 */         graphics.fillRect(i, 100 - used, 1, 1);
/*     */       } 
/*     */     } 
/*     */     
/* 118 */     graphics.setColor(new Color(-16777216));
/* 119 */     graphics.drawRect(0, 0, 348, 100);
/*     */     
/* 121 */     Point m = getMousePosition();
/* 122 */     if (m != null && m.x > 0 && m.x < 348 && m.y > 0 && m.y < 100) {
/* 123 */       GraphData data = DATA.get(m.x);
/* 124 */       int used = data.getUsedPercent();
/* 125 */       graphics.setColor(new Color(0));
/* 126 */       graphics.drawLine(m.x, 1, m.x, 99);
/* 127 */       graphics.drawOval(m.x - 2, 100 - used - 2, 5, 5);
/* 128 */       graphics.setColor(data.getLineColor());
/* 129 */       graphics.fillOval(m.x - 2, 100 - used - 2, 5, 5);
/* 130 */       setToolTipText(String.format("<html><body>Used: %s mb (%s%%)<br/>%s</body></html>", new Object[] {
/* 131 */               Integer.valueOf(Math.round((float)data.getUsedMem() / 1024.0F / 1024.0F)), 
/* 132 */               Integer.valueOf(used), getTime(m.x) }));
/*     */     } 
/*     */   }
/*     */   
/*     */   public String getTime(int halfSeconds) {
/* 137 */     int millis = (348 - halfSeconds) / 2 * 1000;
/* 138 */     return this.TIME_FORMAT.format(new Date(System.currentTimeMillis() - millis));
/*     */   }
/*     */   
/*     */   public void stop() {
/* 142 */     this.timer.stop();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\gui\RAMGraph.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */