/*     */ package org.jline.utils;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Objects;
/*     */ import org.jline.terminal.Size;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.terminal.impl.AbstractTerminal;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Status
/*     */ {
/*     */   protected final AbstractTerminal terminal;
/*     */   protected final boolean supported;
/*  26 */   protected List<AttributedString> oldLines = Collections.emptyList();
/*  27 */   protected List<AttributedString> linesToRestore = Collections.emptyList();
/*     */   protected int rows;
/*     */   protected int columns;
/*     */   protected boolean force;
/*     */   protected boolean suspended = false;
/*     */   
/*     */   public static Status getStatus(Terminal terminal) {
/*  34 */     return getStatus(terminal, true);
/*     */   }
/*     */   
/*     */   public static Status getStatus(Terminal terminal, boolean create) {
/*  38 */     return (terminal instanceof AbstractTerminal) ? ((AbstractTerminal)terminal)
/*  39 */       .getStatus(create) : null;
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public Status(AbstractTerminal terminal) {
/*  45 */     this.terminal = Objects.<AbstractTerminal>requireNonNull(terminal, "terminal can not be null");
/*  46 */     this
/*     */ 
/*     */       
/*  49 */       .supported = (terminal.getStringCapability(InfoCmp.Capability.change_scroll_region) != null && terminal.getStringCapability(InfoCmp.Capability.save_cursor) != null && terminal.getStringCapability(InfoCmp.Capability.restore_cursor) != null && terminal.getStringCapability(InfoCmp.Capability.cursor_address) != null);
/*  50 */     if (this.supported) {
/*  51 */       resize();
/*     */     }
/*     */   }
/*     */   
/*     */   public void resize() {
/*  56 */     Size size = this.terminal.getSize();
/*  57 */     this.rows = size.getRows();
/*  58 */     this.columns = size.getColumns();
/*  59 */     this.force = true;
/*     */   }
/*     */   
/*     */   public void reset() {
/*  63 */     this.force = true;
/*     */   }
/*     */   
/*     */   public void hardReset() {
/*  67 */     if (this.suspended) {
/*     */       return;
/*     */     }
/*  70 */     List<AttributedString> lines = new ArrayList<>(this.oldLines);
/*  71 */     update(null);
/*  72 */     update(lines);
/*     */   }
/*     */   
/*     */   public void redraw() {
/*  76 */     if (this.suspended) {
/*     */       return;
/*     */     }
/*  79 */     update(this.oldLines);
/*     */   }
/*     */   
/*     */   public void update(List<AttributedString> lines) {
/*  83 */     if (!this.supported) {
/*     */       return;
/*     */     }
/*  86 */     if (lines == null) {
/*  87 */       lines = Collections.emptyList();
/*     */     }
/*  89 */     if (this.suspended) {
/*  90 */       this.linesToRestore = new ArrayList<>(lines);
/*     */       return;
/*     */     } 
/*  93 */     if (this.oldLines.equals(lines) && !this.force) {
/*     */       return;
/*     */     }
/*  96 */     int nb = lines.size() - this.oldLines.size();
/*  97 */     if (nb > 0) {
/*  98 */       int j; for (j = 0; j < nb; j++) {
/*  99 */         this.terminal.puts(InfoCmp.Capability.cursor_down, new Object[0]);
/*     */       }
/* 101 */       for (j = 0; j < nb; j++) {
/* 102 */         this.terminal.puts(InfoCmp.Capability.cursor_up, new Object[0]);
/*     */       }
/*     */     } 
/* 105 */     this.terminal.puts(InfoCmp.Capability.save_cursor, new Object[0]);
/* 106 */     this.terminal.puts(InfoCmp.Capability.cursor_address, new Object[] { Integer.valueOf(this.rows - lines.size()), Integer.valueOf(0) });
/* 107 */     this.terminal.puts(InfoCmp.Capability.clr_eos, new Object[0]);
/* 108 */     for (int i = 0; i < lines.size(); i++) {
/* 109 */       this.terminal.puts(InfoCmp.Capability.cursor_address, new Object[] { Integer.valueOf(this.rows - lines.size() + i), Integer.valueOf(0) });
/* 110 */       ((AttributedString)lines.get(i)).columnSubSequence(0, this.columns).print((Terminal)this.terminal);
/*     */     } 
/* 112 */     this.terminal.puts(InfoCmp.Capability.change_scroll_region, new Object[] { Integer.valueOf(0), Integer.valueOf(this.rows - 1 - lines.size()) });
/* 113 */     this.terminal.puts(InfoCmp.Capability.restore_cursor, new Object[0]);
/* 114 */     this.terminal.flush();
/* 115 */     this.oldLines = new ArrayList<>(lines);
/* 116 */     this.force = false;
/*     */   }
/*     */   
/*     */   public void suspend() {
/* 120 */     if (this.suspended) {
/*     */       return;
/*     */     }
/* 123 */     this.linesToRestore = new ArrayList<>(this.oldLines);
/* 124 */     update(null);
/* 125 */     this.suspended = true;
/*     */   }
/*     */   
/*     */   public void restore() {
/* 129 */     if (!this.suspended) {
/*     */       return;
/*     */     }
/* 132 */     this.suspended = false;
/* 133 */     update(this.linesToRestore);
/* 134 */     this.linesToRestore = Collections.emptyList();
/*     */   }
/*     */   
/*     */   public int size() {
/* 138 */     return this.oldLines.size();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jlin\\utils\Status.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */