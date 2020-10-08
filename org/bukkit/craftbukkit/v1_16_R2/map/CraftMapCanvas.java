/*     */ package org.bukkit.craftbukkit.v1_16_R2.map;
/*     */ 
/*     */ import java.awt.Image;
/*     */ import java.util.Arrays;
/*     */ import org.bukkit.map.MapCanvas;
/*     */ import org.bukkit.map.MapCursorCollection;
/*     */ import org.bukkit.map.MapFont;
/*     */ import org.bukkit.map.MapPalette;
/*     */ import org.bukkit.map.MapView;
/*     */ 
/*     */ public class CraftMapCanvas
/*     */   implements MapCanvas {
/*  13 */   private final byte[] buffer = new byte[16384];
/*     */   private final CraftMapView mapView;
/*     */   private byte[] base;
/*  16 */   private MapCursorCollection cursors = new MapCursorCollection();
/*     */   
/*     */   protected CraftMapCanvas(CraftMapView mapView) {
/*  19 */     this.mapView = mapView;
/*  20 */     Arrays.fill(this.buffer, (byte)-1);
/*     */   }
/*     */ 
/*     */   
/*     */   public CraftMapView getMapView() {
/*  25 */     return this.mapView;
/*     */   }
/*     */ 
/*     */   
/*     */   public MapCursorCollection getCursors() {
/*  30 */     return this.cursors;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setCursors(MapCursorCollection cursors) {
/*  35 */     this.cursors = cursors;
/*     */   }
/*     */ 
/*     */   
/*     */   public void setPixel(int x, int y, byte color) {
/*  40 */     if (x < 0 || y < 0 || x >= 128 || y >= 128)
/*     */       return; 
/*  42 */     if (this.buffer[y * 128 + x] != color) {
/*  43 */       this.buffer[y * 128 + x] = color;
/*  44 */       this.mapView.worldMap.flagDirty(x, y);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getPixel(int x, int y) {
/*  50 */     if (x < 0 || y < 0 || x >= 128 || y >= 128)
/*  51 */       return 0; 
/*  52 */     return this.buffer[y * 128 + x];
/*     */   }
/*     */ 
/*     */   
/*     */   public byte getBasePixel(int x, int y) {
/*  57 */     if (x < 0 || y < 0 || x >= 128 || y >= 128)
/*  58 */       return 0; 
/*  59 */     return this.base[y * 128 + x];
/*     */   }
/*     */   
/*     */   protected void setBase(byte[] base) {
/*  63 */     this.base = base;
/*     */   }
/*     */   
/*     */   protected byte[] getBuffer() {
/*  67 */     return this.buffer;
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawImage(int x, int y, Image image) {
/*  72 */     byte[] bytes = MapPalette.imageToBytes(image);
/*  73 */     for (int x2 = 0; x2 < image.getWidth(null); x2++) {
/*  74 */       for (int y2 = 0; y2 < image.getHeight(null); y2++) {
/*  75 */         setPixel(x + x2, y + y2, bytes[y2 * image.getWidth(null) + x2]);
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void drawText(int x, int y, MapFont font, String text) {
/*  82 */     int xStart = x;
/*  83 */     byte color = 44;
/*  84 */     if (!font.isValid(text)) {
/*  85 */       throw new IllegalArgumentException("text contains invalid characters");
/*     */     }
/*     */     
/*  88 */     for (int i = 0; i < text.length(); i++) {
/*  89 */       char ch = text.charAt(i);
/*  90 */       if (ch == '\n') {
/*  91 */         x = xStart;
/*  92 */         y += font.getHeight() + 1;
/*     */       }
/*  94 */       else if (ch == '§') {
/*  95 */         int j = text.indexOf(';', i);
/*  96 */         if (j >= 0)
/*     */         { 
/*  98 */           try { color = Byte.parseByte(text.substring(i + 1, j));
/*  99 */             i = j; }
/*     */           
/* 101 */           catch (NumberFormatException numberFormatException)
/*     */           
/*     */           { 
/* 104 */             throw new IllegalArgumentException("Text contains unterminated color string"); }  } else { throw new IllegalArgumentException("Text contains unterminated color string"); }
/*     */       
/*     */       } else {
/* 107 */         MapFont.CharacterSprite sprite = font.getChar(text.charAt(i));
/* 108 */         for (int r = 0; r < font.getHeight(); r++) {
/* 109 */           for (int c = 0; c < sprite.getWidth(); c++) {
/* 110 */             if (sprite.get(r, c)) {
/* 111 */               setPixel(x + c, y + r, color);
/*     */             }
/*     */           } 
/*     */         } 
/* 115 */         x += sprite.getWidth() + 1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\map\CraftMapCanvas.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */