/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Dynamic;
/*    */ import com.mojang.serialization.OptionalDynamic;
/*    */ 
/*    */ public class LevelVersion
/*    */ {
/*    */   private final int a;
/*    */   private final long b;
/*    */   private final String c;
/*    */   private final int d;
/*    */   private final boolean e;
/*    */   
/*    */   public LevelVersion(int var0, long var1, String var3, int var4, boolean var5) {
/* 15 */     this.a = var0;
/* 16 */     this.b = var1;
/* 17 */     this.c = var3;
/* 18 */     this.d = var4;
/* 19 */     this.e = var5;
/*    */   }
/*    */   
/*    */   public static LevelVersion a(Dynamic<?> var0) {
/* 23 */     int var1 = var0.get("version").asInt(0);
/* 24 */     long var2 = var0.get("LastPlayed").asLong(0L);
/* 25 */     OptionalDynamic<?> var4 = var0.get("Version");
/*    */     
/* 27 */     if (var4.result().isPresent()) {
/* 28 */       return new LevelVersion(var1, var2, var4
/*    */ 
/*    */           
/* 31 */           .get("Name").asString(SharedConstants.getGameVersion().getName()), var4
/* 32 */           .get("Id").asInt(SharedConstants.getGameVersion().getWorldVersion()), var4
/* 33 */           .get("Snapshot").asBoolean(!SharedConstants.getGameVersion().isStable()));
/*    */     }
/*    */     
/* 36 */     return new LevelVersion(var1, var2, "", 0, false);
/*    */   }
/*    */   
/*    */   public int a() {
/* 40 */     return this.a;
/*    */   }
/*    */   
/*    */   public long b() {
/* 44 */     return this.b;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\LevelVersion.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */