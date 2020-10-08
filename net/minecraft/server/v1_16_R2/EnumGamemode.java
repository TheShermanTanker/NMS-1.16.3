/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum EnumGamemode
/*    */ {
/*  8 */   NOT_SET(-1, ""),
/*  9 */   SURVIVAL(0, "survival"),
/* 10 */   CREATIVE(1, "creative"),
/* 11 */   ADVENTURE(2, "adventure"),
/* 12 */   SPECTATOR(3, "spectator");
/*    */   
/*    */   private final int f;
/*    */   
/*    */   private final String g;
/*    */   
/*    */   EnumGamemode(int var2, String var3) {
/* 19 */     this.f = var2;
/* 20 */     this.g = var3;
/*    */   }
/*    */   
/*    */   public int getId() {
/* 24 */     return this.f;
/*    */   }
/*    */   
/*    */   public String b() {
/* 28 */     return this.g;
/*    */   }
/*    */   
/*    */   public IChatBaseComponent c() {
/* 32 */     return new ChatMessage("gameMode." + this.g);
/*    */   }
/*    */   
/*    */   public void a(PlayerAbilities var0) {
/* 36 */     if (this == CREATIVE) {
/* 37 */       var0.canFly = true;
/* 38 */       var0.canInstantlyBuild = true;
/* 39 */       var0.isInvulnerable = true;
/* 40 */     } else if (this == SPECTATOR) {
/* 41 */       var0.canFly = true;
/* 42 */       var0.canInstantlyBuild = false;
/* 43 */       var0.isInvulnerable = true;
/* 44 */       var0.isFlying = true;
/*    */     } else {
/* 46 */       var0.canFly = false;
/* 47 */       var0.canInstantlyBuild = false;
/* 48 */       var0.isInvulnerable = false;
/* 49 */       var0.isFlying = false;
/*    */     } 
/* 51 */     var0.mayBuild = !d();
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 55 */     return (this == ADVENTURE || this == SPECTATOR);
/*    */   }
/*    */   
/*    */   public boolean isCreative() {
/* 59 */     return (this == CREATIVE);
/*    */   }
/*    */   
/*    */   public boolean f() {
/* 63 */     return (this == SURVIVAL || this == ADVENTURE);
/*    */   }
/*    */   
/*    */   public static EnumGamemode getById(int var0) {
/* 67 */     return a(var0, SURVIVAL);
/*    */   }
/*    */   
/*    */   public static EnumGamemode a(int var0, EnumGamemode var1) {
/* 71 */     for (EnumGamemode var5 : values()) {
/* 72 */       if (var5.f == var0) {
/* 73 */         return var5;
/*    */       }
/*    */     } 
/* 76 */     return var1;
/*    */   }
/*    */   
/*    */   public static EnumGamemode a(String var0) {
/* 80 */     return a(var0, SURVIVAL);
/*    */   }
/*    */   
/*    */   public static EnumGamemode a(String var0, EnumGamemode var1) {
/* 84 */     for (EnumGamemode var5 : values()) {
/* 85 */       if (var5.g.equals(var0)) {
/* 86 */         return var5;
/*    */       }
/*    */     } 
/* 89 */     return var1;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumGamemode.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */