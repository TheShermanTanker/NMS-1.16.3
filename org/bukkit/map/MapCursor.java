/*     */ package org.bukkit.map;
/*     */ 
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
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
/*     */ public final class MapCursor
/*     */ {
/*     */   private byte x;
/*     */   private byte y;
/*     */   private byte direction;
/*     */   private byte type;
/*     */   private boolean visible;
/*     */   private String caption;
/*     */   
/*     */   @Deprecated
/*     */   public MapCursor(byte x, byte y, byte direction, byte type, boolean visible) {
/*  27 */     this(x, y, direction, type, visible, (String)null);
/*     */   }
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
/*     */   public MapCursor(byte x, byte y, byte direction, @NotNull Type type, boolean visible) {
/*  40 */     this(x, y, direction, type, visible, (String)null);
/*     */   }
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
/*     */   @Deprecated
/*     */   public MapCursor(byte x, byte y, byte direction, byte type, boolean visible, @Nullable String caption) {
/*  56 */     this.x = x;
/*  57 */     this.y = y;
/*  58 */     setDirection(direction);
/*  59 */     setRawType(type);
/*  60 */     this.visible = visible;
/*  61 */     this.caption = caption;
/*     */   }
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
/*     */   public MapCursor(byte x, byte y, byte direction, @NotNull Type type, boolean visible, @Nullable String caption) {
/*  75 */     this.x = x;
/*  76 */     this.y = y;
/*  77 */     setDirection(direction);
/*  78 */     setType(type);
/*  79 */     this.visible = visible;
/*  80 */     this.caption = caption;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getX() {
/*  89 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getY() {
/*  98 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getDirection() {
/* 107 */     return this.direction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Type getType() {
/* 118 */     return Type.byValue(this.type);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public byte getRawType() {
/* 129 */     return this.type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isVisible() {
/* 138 */     return this.visible;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setX(byte x) {
/* 147 */     this.x = x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setY(byte y) {
/* 156 */     this.y = y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setDirection(byte direction) {
/* 165 */     if (direction < 0 || direction > 15) {
/* 166 */       throw new IllegalArgumentException("Direction must be in the range 0-15");
/*     */     }
/* 168 */     this.direction = direction;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setType(@NotNull Type type) {
/* 177 */     setRawType(type.value);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public void setRawType(byte type) {
/* 188 */     if (type < 0 || type > 26) {
/* 189 */       throw new IllegalArgumentException("Type must be in the range 0-26");
/*     */     }
/* 191 */     this.type = type;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setVisible(boolean visible) {
/* 200 */     this.visible = visible;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public String getCaption() {
/* 210 */     return this.caption;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setCaption(@Nullable String caption) {
/* 219 */     this.caption = caption;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public enum Type
/*     */   {
/* 229 */     WHITE_POINTER(0),
/* 230 */     GREEN_POINTER(1),
/* 231 */     RED_POINTER(2),
/* 232 */     BLUE_POINTER(3),
/* 233 */     WHITE_CROSS(4),
/* 234 */     RED_MARKER(5),
/* 235 */     WHITE_CIRCLE(6),
/* 236 */     SMALL_WHITE_CIRCLE(7),
/* 237 */     MANSION(8),
/* 238 */     TEMPLE(9),
/* 239 */     BANNER_WHITE(10),
/* 240 */     BANNER_ORANGE(11),
/* 241 */     BANNER_MAGENTA(12),
/* 242 */     BANNER_LIGHT_BLUE(13),
/* 243 */     BANNER_YELLOW(14),
/* 244 */     BANNER_LIME(15),
/* 245 */     BANNER_PINK(16),
/* 246 */     BANNER_GRAY(17),
/* 247 */     BANNER_LIGHT_GRAY(18),
/* 248 */     BANNER_CYAN(19),
/* 249 */     BANNER_PURPLE(20),
/* 250 */     BANNER_BLUE(21),
/* 251 */     BANNER_BROWN(22),
/* 252 */     BANNER_GREEN(23),
/* 253 */     BANNER_RED(24),
/* 254 */     BANNER_BLACK(25),
/* 255 */     RED_X(26);
/*     */     
/*     */     private byte value;
/*     */     
/*     */     Type(int value) {
/* 260 */       this.value = (byte)value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public byte getValue() {
/* 271 */       return this.value;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     @Nullable
/*     */     public static Type byValue(byte value) {
/* 284 */       for (Type t : values()) {
/* 285 */         if (t.value == value) return t; 
/*     */       } 
/* 287 */       return null;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\map\MapCursor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */