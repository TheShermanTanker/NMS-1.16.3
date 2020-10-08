/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.serialization.Codec;
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public enum EnumCreatureType
/*    */   implements INamable
/*    */ {
/* 11 */   MONSTER("monster", 70, false, false, 128),
/* 12 */   CREATURE("creature", 10, true, true, 128),
/* 13 */   AMBIENT("ambient", 15, true, false, 128),
/* 14 */   WATER_CREATURE("water_creature", 5, true, false, 128),
/* 15 */   WATER_AMBIENT("water_ambient", 20, true, false, 64),
/* 16 */   MISC("misc", -1, true, true, 128);
/*    */   
/*    */   static {
/* 19 */     g = INamable.a(EnumCreatureType::values, EnumCreatureType::a);
/*    */     
/* 21 */     h = (Map<String, EnumCreatureType>)Arrays.<EnumCreatureType>stream(values()).collect(Collectors.toMap(EnumCreatureType::b, var0 -> var0));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 26 */   private final int m = 32; public static final Codec<EnumCreatureType> g; private static final Map<String, EnumCreatureType> h;
/*    */   private final int i;
/*    */   
/*    */   EnumCreatureType(String var2, int var3, boolean var4, boolean var5, int var6) {
/* 30 */     this.l = var2;
/* 31 */     this.i = var3;
/* 32 */     this.j = var4;
/* 33 */     this.k = var5;
/* 34 */     this.n = var6;
/*    */   }
/*    */   private final boolean j; private final boolean k; private final String l; private final int n;
/*    */   public String b() {
/* 38 */     return this.l;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 43 */     return this.l;
/*    */   }
/*    */   
/*    */   public static EnumCreatureType a(String var0) {
/* 47 */     return h.get(var0);
/*    */   }
/*    */   
/*    */   public int c() {
/* 51 */     return this.i;
/*    */   }
/*    */   
/*    */   public boolean d() {
/* 55 */     return this.j;
/*    */   }
/*    */   
/*    */   public boolean e() {
/* 59 */     return this.k;
/*    */   }
/*    */   
/*    */   public int f() {
/* 63 */     return this.n;
/*    */   }
/*    */   
/*    */   public int g() {
/* 67 */     return 32;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\EnumCreatureType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */