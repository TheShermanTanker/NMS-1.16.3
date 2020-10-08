/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Foods
/*    */ {
/*  9 */   public static final FoodInfo a = (new FoodInfo.a()).a(4).a(0.3F).d();
/* 10 */   public static final FoodInfo b = (new FoodInfo.a()).a(5).a(0.6F).d();
/* 11 */   public static final FoodInfo c = (new FoodInfo.a()).a(3).a(0.3F).a().d();
/* 12 */   public static final FoodInfo d = (new FoodInfo.a()).a(1).a(0.6F).d();
/* 13 */   public static final FoodInfo e = a(6);
/* 14 */   public static final FoodInfo f = (new FoodInfo.a()).a(5).a(0.6F).d();
/* 15 */   public static final FoodInfo g = (new FoodInfo.a()).a(3).a(0.6F).d();
/* 16 */   public static final FoodInfo h = (new FoodInfo.a()).a(2).a(0.3F)
/* 17 */     .a(new MobEffect(MobEffects.HUNGER, 600, 0), 0.3F)
/* 18 */     .a().d();
/* 19 */   public static final FoodInfo i = (new FoodInfo.a()).a(4).a(0.3F).b().d();
/* 20 */   public static final FoodInfo j = (new FoodInfo.a()).a(2).a(0.1F).d();
/* 21 */   public static final FoodInfo k = (new FoodInfo.a()).a(8).a(0.8F).a().d();
/* 22 */   public static final FoodInfo l = (new FoodInfo.a()).a(6).a(0.6F).a().d();
/* 23 */   public static final FoodInfo m = (new FoodInfo.a()).a(5).a(0.6F).d();
/* 24 */   public static final FoodInfo n = (new FoodInfo.a()).a(6).a(0.8F).a().d();
/* 25 */   public static final FoodInfo o = (new FoodInfo.a()).a(8).a(0.8F).a().d();
/* 26 */   public static final FoodInfo p = (new FoodInfo.a()).a(5).a(0.6F).a().d();
/* 27 */   public static final FoodInfo q = (new FoodInfo.a()).a(6).a(0.8F).d();
/* 28 */   public static final FoodInfo r = (new FoodInfo.a()).a(2).a(0.1F).d();
/* 29 */   public static final FoodInfo s = (new FoodInfo.a()).a(1).a(0.3F).c().d();
/* 30 */   public static final FoodInfo t = (new FoodInfo.a()).a(4).a(1.2F)
/* 31 */     .a(new MobEffect(MobEffects.REGENERATION, 400, 1), 1.0F)
/* 32 */     .a(new MobEffect(MobEffects.RESISTANCE, 6000, 0), 1.0F)
/* 33 */     .a(new MobEffect(MobEffects.FIRE_RESISTANCE, 6000, 0), 1.0F)
/* 34 */     .a(new MobEffect(MobEffects.ABSORBTION, 2400, 3), 1.0F)
/* 35 */     .b().d();
/* 36 */   public static final FoodInfo u = (new FoodInfo.a()).a(4).a(1.2F)
/* 37 */     .a(new MobEffect(MobEffects.REGENERATION, 100, 1), 1.0F)
/* 38 */     .a(new MobEffect(MobEffects.ABSORBTION, 2400, 0), 1.0F)
/* 39 */     .b().d();
/* 40 */   public static final FoodInfo v = (new FoodInfo.a()).a(6).a(1.2F).d();
/* 41 */   public static final FoodInfo w = (new FoodInfo.a()).a(6).a(0.1F).d();
/* 42 */   public static final FoodInfo x = (new FoodInfo.a()).a(2).a(0.3F).d();
/* 43 */   public static final FoodInfo y = a(6);
/* 44 */   public static final FoodInfo z = (new FoodInfo.a()).a(2).a(0.3F).a().d();
/* 45 */   public static final FoodInfo A = (new FoodInfo.a()).a(2).a(0.3F)
/* 46 */     .a(new MobEffect(MobEffects.POISON, 100, 0), 0.6F)
/* 47 */     .d();
/* 48 */   public static final FoodInfo B = (new FoodInfo.a()).a(3).a(0.3F).a().d();
/* 49 */   public static final FoodInfo C = (new FoodInfo.a()).a(1).a(0.3F).d();
/* 50 */   public static final FoodInfo D = (new FoodInfo.a()).a(1).a(0.1F)
/* 51 */     .a(new MobEffect(MobEffects.POISON, 1200, 3), 1.0F)
/* 52 */     .a(new MobEffect(MobEffects.HUNGER, 300, 2), 1.0F)
/* 53 */     .a(new MobEffect(MobEffects.CONFUSION, 300, 0), 1.0F)
/* 54 */     .d();
/* 55 */   public static final FoodInfo E = (new FoodInfo.a()).a(8).a(0.3F).d();
/* 56 */   public static final FoodInfo F = (new FoodInfo.a()).a(3).a(0.3F).a().d();
/* 57 */   public static final FoodInfo G = a(10);
/* 58 */   public static final FoodInfo H = (new FoodInfo.a()).a(4).a(0.1F)
/* 59 */     .a(new MobEffect(MobEffects.HUNGER, 600, 0), 0.8F)
/* 60 */     .a().d();
/* 61 */   public static final FoodInfo I = (new FoodInfo.a()).a(2).a(0.1F).d();
/* 62 */   public static final FoodInfo J = (new FoodInfo.a()).a(2).a(0.8F)
/* 63 */     .a(new MobEffect(MobEffects.POISON, 100, 0), 1.0F)
/* 64 */     .d();
/* 65 */   public static final FoodInfo K = a(6);
/* 66 */   public static final FoodInfo L = (new FoodInfo.a()).a(2).a(0.1F).d();
/* 67 */   public static final FoodInfo M = (new FoodInfo.a()).a(1).a(0.1F).d();
/*    */   
/*    */   private static FoodInfo a(int var0) {
/* 70 */     return (new FoodInfo.a()).a(var0).a(0.6F).d();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Foods.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */