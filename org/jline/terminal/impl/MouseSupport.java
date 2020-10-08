/*     */ package org.jline.terminal.impl;
/*     */ 
/*     */ import java.io.EOFException;
/*     */ import java.io.IOError;
/*     */ import java.io.IOException;
/*     */ import java.nio.charset.StandardCharsets;
/*     */ import java.util.EnumSet;
/*     */ import java.util.function.IntSupplier;
/*     */ import org.jline.terminal.MouseEvent;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.utils.InfoCmp;
/*     */ import org.jline.utils.InputStreamReader;
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
/*     */ public class MouseSupport
/*     */ {
/*     */   public static boolean hasMouseSupport(Terminal terminal) {
/*  26 */     return (terminal.getStringCapability(InfoCmp.Capability.key_mouse) != null);
/*     */   }
/*     */   
/*     */   public static boolean trackMouse(Terminal terminal, Terminal.MouseTracking tracking) {
/*  30 */     if (hasMouseSupport(terminal)) {
/*  31 */       switch (tracking) {
/*     */         case Off:
/*  33 */           terminal.writer().write("\033[?1000l");
/*     */           break;
/*     */         case Normal:
/*  36 */           terminal.writer().write("\033[?1005h\033[?1000h");
/*     */           break;
/*     */         case Button:
/*  39 */           terminal.writer().write("\033[?1005h\033[?1002h");
/*     */           break;
/*     */         case Any:
/*  42 */           terminal.writer().write("\033[?1005h\033[?1003h");
/*     */           break;
/*     */       } 
/*  45 */       terminal.flush();
/*  46 */       return true;
/*     */     } 
/*  48 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public static MouseEvent readMouse(Terminal terminal, MouseEvent last) {
/*  53 */     return readMouse(() -> readExt(terminal), last);
/*     */   } public static MouseEvent readMouse(IntSupplier reader, MouseEvent last) {
/*     */     MouseEvent.Type type;
/*     */     MouseEvent.Button button;
/*  57 */     int cb = reader.getAsInt() - 32;
/*  58 */     int cx = reader.getAsInt() - 32 - 1;
/*  59 */     int cy = reader.getAsInt() - 32 - 1;
/*     */ 
/*     */     
/*  62 */     EnumSet<MouseEvent.Modifier> modifiers = EnumSet.noneOf(MouseEvent.Modifier.class);
/*  63 */     if ((cb & 0x4) == 4) {
/*  64 */       modifiers.add(MouseEvent.Modifier.Shift);
/*     */     }
/*  66 */     if ((cb & 0x8) == 8) {
/*  67 */       modifiers.add(MouseEvent.Modifier.Alt);
/*     */     }
/*  69 */     if ((cb & 0x10) == 16) {
/*  70 */       modifiers.add(MouseEvent.Modifier.Control);
/*     */     }
/*  72 */     if ((cb & 0x40) == 64)
/*  73 */     { type = MouseEvent.Type.Wheel;
/*  74 */       button = ((cb & 0x1) == 1) ? MouseEvent.Button.WheelDown : MouseEvent.Button.WheelUp; }
/*     */     else
/*  76 */     { int b = cb & 0x3;
/*  77 */       switch (b)
/*     */       { case 0:
/*  79 */           button = MouseEvent.Button.Button1;
/*  80 */           if (last.getButton() == button && (last
/*  81 */             .getType() == MouseEvent.Type.Pressed || last.getType() == MouseEvent.Type.Dragged)) {
/*  82 */             type = MouseEvent.Type.Dragged;
/*     */           } else {
/*  84 */             type = MouseEvent.Type.Pressed;
/*     */           } 
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */           
/* 116 */           return new MouseEvent(type, button, modifiers, cx, cy);case 1: button = MouseEvent.Button.Button2; if (last.getButton() == button && (last.getType() == MouseEvent.Type.Pressed || last.getType() == MouseEvent.Type.Dragged)) { type = MouseEvent.Type.Dragged; } else { type = MouseEvent.Type.Pressed; }  return new MouseEvent(type, button, modifiers, cx, cy);case 2: button = MouseEvent.Button.Button3; if (last.getButton() == button && (last.getType() == MouseEvent.Type.Pressed || last.getType() == MouseEvent.Type.Dragged)) { type = MouseEvent.Type.Dragged; } else { type = MouseEvent.Type.Pressed; }  return new MouseEvent(type, button, modifiers, cx, cy); }  if (last.getType() == MouseEvent.Type.Pressed || last.getType() == MouseEvent.Type.Dragged) { button = last.getButton(); type = MouseEvent.Type.Released; } else { button = MouseEvent.Button.NoButton; type = MouseEvent.Type.Moved; }  }  return new MouseEvent(type, button, modifiers, cx, cy);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int readExt(Terminal terminal) {
/*     */     try {
/*     */       int c;
/* 124 */       if (terminal.encoding() != StandardCharsets.UTF_8) {
/* 125 */         c = (new InputStreamReader(terminal.input(), StandardCharsets.UTF_8)).read();
/*     */       } else {
/* 127 */         c = terminal.reader().read();
/*     */       } 
/* 129 */       if (c < 0) {
/* 130 */         throw new EOFException();
/*     */       }
/* 132 */       return c;
/* 133 */     } catch (IOException e) {
/* 134 */       throw new IOError(e);
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\terminal\impl\MouseSupport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */