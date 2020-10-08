/*     */ package net.minecraft.server.v1_16_R2;
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
/*     */ public class DemoPlayerInteractManager
/*     */   extends PlayerInteractManager
/*     */ {
/*     */   private boolean c;
/*     */   private boolean d;
/*     */   private int e;
/*     */   private int f;
/*     */   
/*     */   public DemoPlayerInteractManager(WorldServer var0) {
/*  26 */     super(var0);
/*     */   }
/*     */ 
/*     */   
/*     */   public void a() {
/*  31 */     super.a();
/*  32 */     this.f++;
/*     */     
/*  34 */     long var0 = this.world.getTime();
/*  35 */     long var2 = var0 / 24000L + 1L;
/*     */     
/*  37 */     if (!this.c && this.f > 20) {
/*  38 */       this.c = true;
/*  39 */       this.player.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.f, 0.0F));
/*     */     } 
/*     */     
/*  42 */     this.d = (var0 > 120500L);
/*  43 */     if (this.d) {
/*  44 */       this.e++;
/*     */     }
/*     */     
/*  47 */     if (var0 % 24000L == 500L) {
/*  48 */       if (var2 <= 6L) {
/*  49 */         if (var2 == 6L) {
/*  50 */           this.player.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.f, 104.0F));
/*     */         } else {
/*  52 */           this.player.sendMessage(new ChatMessage("demo.day." + var2), SystemUtils.b);
/*     */         } 
/*     */       }
/*  55 */     } else if (var2 == 1L) {
/*  56 */       if (var0 == 100L) {
/*  57 */         this.player.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.f, 101.0F));
/*  58 */       } else if (var0 == 175L) {
/*  59 */         this.player.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.f, 102.0F));
/*  60 */       } else if (var0 == 250L) {
/*  61 */         this.player.playerConnection.sendPacket(new PacketPlayOutGameStateChange(PacketPlayOutGameStateChange.f, 103.0F));
/*     */       } 
/*  63 */     } else if (var2 == 5L && 
/*  64 */       var0 % 24000L == 22000L) {
/*  65 */       this.player.sendMessage(new ChatMessage("demo.day.warning"), SystemUtils.b);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private void f() {
/*  71 */     if (this.e > 100) {
/*  72 */       this.player.sendMessage(new ChatMessage("demo.reminder"), SystemUtils.b);
/*  73 */       this.e = 0;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BlockPosition var0, PacketPlayInBlockDig.EnumPlayerDigType var1, EnumDirection var2, int var3) {
/*  79 */     if (this.d) {
/*  80 */       f();
/*     */       return;
/*     */     } 
/*  83 */     super.a(var0, var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityPlayer var0, World var1, ItemStack var2, EnumHand var3) {
/*  88 */     if (this.d) {
/*  89 */       f();
/*  90 */       return EnumInteractionResult.PASS;
/*     */     } 
/*  92 */     return super.a(var0, var1, var2, var3);
/*     */   }
/*     */ 
/*     */   
/*     */   public EnumInteractionResult a(EntityPlayer var0, World var1, ItemStack var2, EnumHand var3, MovingObjectPositionBlock var4) {
/*  97 */     if (this.d) {
/*  98 */       f();
/*  99 */       return EnumInteractionResult.PASS;
/*     */     } 
/* 101 */     return super.a(var0, var1, var2, var3, var4);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\DemoPlayerInteractManager.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */