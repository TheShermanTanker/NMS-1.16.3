/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.util.Random;
/*     */ import javax.annotation.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class TileEntityEnchantTable
/*     */   extends TileEntity
/*     */   implements INamableTileEntity, ITickable
/*     */ {
/*     */   public int a;
/*     */   public float b;
/*     */   public float c;
/*     */   public float g;
/*     */   public float h;
/*     */   public float i;
/*     */   public float j;
/*     */   public float k;
/*     */   public float l;
/*     */   public float m;
/*  26 */   private static final Random n = new Random();
/*     */   private IChatBaseComponent o;
/*     */   
/*     */   public TileEntityEnchantTable() {
/*  30 */     super(TileEntityTypes.ENCHANTING_TABLE);
/*     */   }
/*     */ 
/*     */   
/*     */   public NBTTagCompound save(NBTTagCompound var0) {
/*  35 */     super.save(var0);
/*  36 */     if (hasCustomName()) {
/*  37 */       var0.setString("CustomName", IChatBaseComponent.ChatSerializer.a(this.o));
/*     */     }
/*     */     
/*  40 */     return var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void load(IBlockData var0, NBTTagCompound var1) {
/*  45 */     super.load(var0, var1);
/*  46 */     if (var1.hasKeyOfType("CustomName", 8)) {
/*  47 */       this.o = IChatBaseComponent.ChatSerializer.a(var1.getString("CustomName"));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public void tick() {
/*  53 */     this.j = this.i;
/*  54 */     this.l = this.k;
/*     */     
/*  56 */     EntityHuman var0 = this.world.a(this.position.getX() + 0.5D, this.position.getY() + 0.5D, this.position.getZ() + 0.5D, 3.0D, false);
/*  57 */     if (var0 != null) {
/*  58 */       double d1 = var0.locX() - this.position.getX() + 0.5D;
/*  59 */       double d2 = var0.locZ() - this.position.getZ() + 0.5D;
/*     */       
/*  61 */       this.m = (float)MathHelper.d(d2, d1);
/*     */       
/*  63 */       this.i += 0.1F;
/*     */       
/*  65 */       if (this.i < 0.5F || n.nextInt(40) == 0) {
/*  66 */         float var5 = this.g;
/*     */         do {
/*  68 */           this.g += (n.nextInt(4) - n.nextInt(4));
/*  69 */         } while (var5 == this.g);
/*     */       } 
/*     */     } else {
/*  72 */       this.m += 0.02F;
/*  73 */       this.i -= 0.1F;
/*     */     } 
/*     */     
/*  76 */     while (this.k >= 3.1415927F) {
/*  77 */       this.k -= 6.2831855F;
/*     */     }
/*  79 */     while (this.k < -3.1415927F) {
/*  80 */       this.k += 6.2831855F;
/*     */     }
/*  82 */     while (this.m >= 3.1415927F) {
/*  83 */       this.m -= 6.2831855F;
/*     */     }
/*  85 */     while (this.m < -3.1415927F) {
/*  86 */       this.m += 6.2831855F;
/*     */     }
/*  88 */     float var1 = this.m - this.k;
/*  89 */     while (var1 >= 3.1415927F) {
/*  90 */       var1 -= 6.2831855F;
/*     */     }
/*  92 */     while (var1 < -3.1415927F) {
/*  93 */       var1 += 6.2831855F;
/*     */     }
/*     */     
/*  96 */     this.k += var1 * 0.4F;
/*     */     
/*  98 */     this.i = MathHelper.a(this.i, 0.0F, 1.0F);
/*     */     
/* 100 */     this.a++;
/* 101 */     this.c = this.b;
/*     */     
/* 103 */     float var2 = (this.g - this.b) * 0.4F;
/* 104 */     float var3 = 0.2F;
/* 105 */     var2 = MathHelper.a(var2, -0.2F, 0.2F);
/* 106 */     this.h += (var2 - this.h) * 0.9F;
/*     */     
/* 108 */     this.b += this.h;
/*     */   }
/*     */ 
/*     */   
/*     */   public IChatBaseComponent getDisplayName() {
/* 113 */     if (this.o != null) {
/* 114 */       return this.o;
/*     */     }
/* 116 */     return new ChatMessage("container.enchant");
/*     */   }
/*     */   
/*     */   public void setCustomName(@Nullable IChatBaseComponent var0) {
/* 120 */     this.o = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public IChatBaseComponent getCustomName() {
/* 126 */     return this.o;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TileEntityEnchantTable.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */