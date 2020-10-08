/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ public class MapIcon {
/*     */   private final Type type;
/*     */   private byte x;
/*     */   private byte y;
/*     */   private byte rotation;
/*     */   private final IChatBaseComponent name;
/*     */   
/*     */   public enum Type {
/*  11 */     PLAYER(false),
/*  12 */     FRAME(true),
/*  13 */     RED_MARKER(false),
/*  14 */     BLUE_MARKER(false),
/*  15 */     TARGET_X(true),
/*  16 */     TARGET_POINT(true),
/*  17 */     PLAYER_OFF_MAP(false),
/*  18 */     PLAYER_OFF_LIMITS(false),
/*  19 */     MANSION(true, 5393476),
/*  20 */     MONUMENT(true, 3830373),
/*  21 */     BANNER_WHITE(true),
/*  22 */     BANNER_ORANGE(true),
/*  23 */     BANNER_MAGENTA(true),
/*  24 */     BANNER_LIGHT_BLUE(true),
/*  25 */     BANNER_YELLOW(true),
/*  26 */     BANNER_LIME(true),
/*  27 */     BANNER_PINK(true),
/*  28 */     BANNER_GRAY(true),
/*  29 */     BANNER_LIGHT_GRAY(true),
/*  30 */     BANNER_CYAN(true),
/*  31 */     BANNER_PURPLE(true),
/*  32 */     BANNER_BLUE(true),
/*  33 */     BANNER_BROWN(true),
/*  34 */     BANNER_GREEN(true),
/*  35 */     BANNER_RED(true),
/*  36 */     BANNER_BLACK(true),
/*  37 */     RED_X(true);
/*     */ 
/*     */     
/*     */     private final byte B;
/*     */ 
/*     */     
/*     */     private final boolean C;
/*     */     
/*     */     private final int D;
/*     */ 
/*     */     
/*     */     Type(boolean var2, int var3) {
/*  49 */       this.B = (byte)ordinal();
/*  50 */       this.C = var2;
/*  51 */       this.D = var3;
/*     */     }
/*     */     
/*     */     public byte a() {
/*  55 */       return this.B;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean c() {
/*  63 */       return (this.D >= 0);
/*     */     }
/*     */     
/*     */     public int d() {
/*  67 */       return this.D;
/*     */     }
/*     */     
/*     */     public static Type a(byte var0) {
/*  71 */       return values()[MathHelper.clamp(var0, 0, (values()).length - 1)];
/*     */     }
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapIcon(Type var0, byte var1, byte var2, byte var3, @Nullable IChatBaseComponent var4) {
/*  82 */     this.type = var0;
/*  83 */     this.x = var1;
/*  84 */     this.y = var2;
/*  85 */     this.rotation = var3;
/*  86 */     this.name = var4;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Type getType() {
/*  94 */     return this.type;
/*     */   }
/*     */   
/*     */   public byte getX() {
/*  98 */     return this.x;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getY() {
/* 106 */     return this.y;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public byte getRotation() {
/* 114 */     return this.rotation;
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
/*     */   @Nullable
/*     */   public IChatBaseComponent getName() {
/* 127 */     return this.name;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/* 132 */     if (this == var0) {
/* 133 */       return true;
/*     */     }
/* 135 */     if (!(var0 instanceof MapIcon)) {
/* 136 */       return false;
/*     */     }
/*     */     
/* 139 */     MapIcon var1 = (MapIcon)var0;
/*     */     
/* 141 */     if (this.type != var1.type) {
/* 142 */       return false;
/*     */     }
/* 144 */     if (this.rotation != var1.rotation) {
/* 145 */       return false;
/*     */     }
/* 147 */     if (this.x != var1.x) {
/* 148 */       return false;
/*     */     }
/* 150 */     if (this.y != var1.y) {
/* 151 */       return false;
/*     */     }
/* 153 */     if (!Objects.equals(this.name, var1.name)) {
/* 154 */       return false;
/*     */     }
/*     */     
/* 157 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 162 */     int var0 = this.type.a();
/* 163 */     var0 = 31 * var0 + this.x;
/* 164 */     var0 = 31 * var0 + this.y;
/* 165 */     var0 = 31 * var0 + this.rotation;
/* 166 */     var0 = 31 * var0 + Objects.hashCode(this.name);
/* 167 */     return var0;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MapIcon.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */