/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Objects;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class MapIconBanner
/*     */ {
/*     */   private final BlockPosition a;
/*     */   private final EnumColor b;
/*     */   @Nullable
/*     */   private final IChatBaseComponent c;
/*     */   
/*     */   public MapIconBanner(BlockPosition var0, EnumColor var1, @Nullable IChatBaseComponent var2) {
/*  22 */     this.a = var0;
/*  23 */     this.b = var1;
/*  24 */     this.c = var2;
/*     */   }
/*     */   
/*     */   public static MapIconBanner a(NBTTagCompound var0) {
/*  28 */     BlockPosition var1 = GameProfileSerializer.b(var0.getCompound("Pos"));
/*  29 */     EnumColor var2 = EnumColor.a(var0.getString("Color"), EnumColor.WHITE);
/*  30 */     IChatBaseComponent var3 = var0.hasKey("Name") ? IChatBaseComponent.ChatSerializer.a(var0.getString("Name")) : null;
/*  31 */     return new MapIconBanner(var1, var2, var3);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   public static MapIconBanner a(IBlockAccess var0, BlockPosition var1) {
/*  36 */     TileEntity var2 = var0.getTileEntity(var1);
/*  37 */     if (var2 instanceof TileEntityBanner) {
/*  38 */       TileEntityBanner var3 = (TileEntityBanner)var2;
/*  39 */       EnumColor var4 = var3.a(() -> var0.getType(var1));
/*  40 */       IChatBaseComponent var5 = var3.hasCustomName() ? var3.getCustomName() : null;
/*  41 */       return new MapIconBanner(var1, var4, var5);
/*     */     } 
/*  43 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public BlockPosition a() {
/*  48 */     return this.a;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MapIcon.Type c() {
/*  56 */     switch (null.a[this.b.ordinal()]) {
/*     */       case 1:
/*  58 */         return MapIcon.Type.BANNER_WHITE;
/*     */       case 2:
/*  60 */         return MapIcon.Type.BANNER_ORANGE;
/*     */       case 3:
/*  62 */         return MapIcon.Type.BANNER_MAGENTA;
/*     */       case 4:
/*  64 */         return MapIcon.Type.BANNER_LIGHT_BLUE;
/*     */       case 5:
/*  66 */         return MapIcon.Type.BANNER_YELLOW;
/*     */       case 6:
/*  68 */         return MapIcon.Type.BANNER_LIME;
/*     */       case 7:
/*  70 */         return MapIcon.Type.BANNER_PINK;
/*     */       case 8:
/*  72 */         return MapIcon.Type.BANNER_GRAY;
/*     */       case 9:
/*  74 */         return MapIcon.Type.BANNER_LIGHT_GRAY;
/*     */       case 10:
/*  76 */         return MapIcon.Type.BANNER_CYAN;
/*     */       case 11:
/*  78 */         return MapIcon.Type.BANNER_PURPLE;
/*     */       case 12:
/*  80 */         return MapIcon.Type.BANNER_BLUE;
/*     */       case 13:
/*  82 */         return MapIcon.Type.BANNER_BROWN;
/*     */       case 14:
/*  84 */         return MapIcon.Type.BANNER_GREEN;
/*     */       case 15:
/*  86 */         return MapIcon.Type.BANNER_RED;
/*     */     } 
/*     */     
/*  89 */     return MapIcon.Type.BANNER_BLACK;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IChatBaseComponent d() {
/*  95 */     return this.c;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object var0) {
/* 100 */     if (this == var0) {
/* 101 */       return true;
/*     */     }
/* 103 */     if (var0 == null || getClass() != var0.getClass()) {
/* 104 */       return false;
/*     */     }
/* 106 */     MapIconBanner var1 = (MapIconBanner)var0;
/* 107 */     return (Objects.equals(this.a, var1.a) && this.b == var1.b && Objects.equals(this.c, var1.c));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 112 */     return Objects.hash(new Object[] { this.a, this.b, this.c });
/*     */   }
/*     */   
/*     */   public NBTTagCompound e() {
/* 116 */     NBTTagCompound var0 = new NBTTagCompound();
/*     */     
/* 118 */     var0.set("Pos", GameProfileSerializer.a(this.a));
/* 119 */     var0.setString("Color", this.b.c());
/*     */     
/* 121 */     if (this.c != null) {
/* 122 */       var0.setString("Name", IChatBaseComponent.ChatSerializer.a(this.c));
/*     */     }
/*     */     
/* 125 */     return var0;
/*     */   }
/*     */   
/*     */   public String f() {
/* 129 */     return "banner-" + this.a.getX() + "," + this.a.getY() + "," + this.a.getZ();
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MapIconBanner.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */