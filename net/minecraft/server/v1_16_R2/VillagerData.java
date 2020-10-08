/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function3;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class VillagerData {
/* 10 */   private static final int[] b = new int[] { 0, 10, 70, 150, 250 };
/*    */   static {
/* 12 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)IRegistry.VILLAGER_TYPE.fieldOf("type").orElseGet(()).forGetter(()), (App)IRegistry.VILLAGER_PROFESSION.fieldOf("profession").orElseGet(()).forGetter(()), (App)Codec.INT.fieldOf("level").orElse(Integer.valueOf(1)).forGetter(())).apply((Applicative)var0, VillagerData::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<VillagerData> a;
/*    */   
/*    */   private final VillagerType c;
/*    */   private final VillagerProfession d;
/*    */   private final int e;
/*    */   
/*    */   public VillagerData(VillagerType var0, VillagerProfession var1, int var2) {
/* 23 */     this.c = var0;
/* 24 */     this.d = var1;
/* 25 */     this.e = Math.max(1, var2);
/*    */   }
/*    */   
/*    */   public VillagerType getType() {
/* 29 */     return this.c;
/*    */   }
/*    */   
/*    */   public VillagerProfession getProfession() {
/* 33 */     return this.d;
/*    */   }
/*    */   
/*    */   public int getLevel() {
/* 37 */     return this.e;
/*    */   }
/*    */   
/*    */   public VillagerData withType(VillagerType var0) {
/* 41 */     return new VillagerData(var0, this.d, this.e);
/*    */   }
/*    */   
/*    */   public VillagerData withProfession(VillagerProfession var0) {
/* 45 */     return new VillagerData(this.c, var0, this.e);
/*    */   }
/*    */   
/*    */   public VillagerData withLevel(int var0) {
/* 49 */     return new VillagerData(this.c, this.d, var0);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static int c(int var0) {
/* 57 */     return d(var0) ? b[var0] : 0;
/*    */   }
/*    */   
/*    */   public static boolean d(int var0) {
/* 61 */     return (var0 >= 1 && var0 < 5);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\VillagerData.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */