/*     */ package org.bukkit.map;
/*     */ 
/*     */ import java.util.HashMap;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapFont
/*     */ {
/*  13 */   private final HashMap<Character, CharacterSprite> chars = new HashMap<>();
/*  14 */   private int height = 0;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean malleable = true;
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setChar(char ch, @NotNull CharacterSprite sprite) {
/*  25 */     if (!this.malleable) {
/*  26 */       throw new IllegalStateException("this font is not malleable");
/*     */     }
/*     */     
/*  29 */     this.chars.put(Character.valueOf(ch), sprite);
/*  30 */     if (sprite.getHeight() > this.height) {
/*  31 */       this.height = sprite.getHeight();
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public CharacterSprite getChar(char ch) {
/*  44 */     return this.chars.get(Character.valueOf(ch));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getWidth(@NotNull String text) {
/*  55 */     if (!isValid(text)) {
/*  56 */       throw new IllegalArgumentException("text contains invalid characters");
/*     */     }
/*     */     
/*  59 */     if (text.length() == 0) {
/*  60 */       return 0;
/*     */     }
/*     */     
/*  63 */     int result = 0;
/*  64 */     for (int i = 0; i < text.length(); i++) {
/*  65 */       char ch = text.charAt(i);
/*  66 */       if (ch != '§')
/*  67 */         result += ((CharacterSprite)this.chars.get(Character.valueOf(ch))).getWidth(); 
/*     */     } 
/*  69 */     result += text.length() - 1;
/*     */     
/*  71 */     return result;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getHeight() {
/*  80 */     return this.height;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isValid(@NotNull String text) {
/*  91 */     for (int i = 0; i < text.length(); i++) {
/*  92 */       char ch = text.charAt(i);
/*  93 */       if (ch != '§' && ch != '\n' && 
/*  94 */         this.chars.get(Character.valueOf(ch)) == null) return false; 
/*     */     } 
/*  96 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public static class CharacterSprite
/*     */   {
/*     */     private final int width;
/*     */     
/*     */     private final int height;
/*     */     
/*     */     private final boolean[] data;
/*     */     
/*     */     public CharacterSprite(int width, int height, @NotNull boolean[] data) {
/* 109 */       this.width = width;
/* 110 */       this.height = height;
/* 111 */       this.data = data;
/*     */       
/* 113 */       if (data.length != width * height) {
/* 114 */         throw new IllegalArgumentException("size of data does not match dimensions");
/*     */       }
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean get(int row, int col) {
/* 126 */       if (row < 0 || col < 0 || row >= this.height || col >= this.width) return false; 
/* 127 */       return this.data[row * this.width + col];
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getWidth() {
/* 136 */       return this.width;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public int getHeight() {
/* 145 */       return this.height;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\map\MapFont.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */