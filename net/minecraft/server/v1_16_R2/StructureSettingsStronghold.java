/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class StructureSettingsStronghold {
/*    */   static {
/*  7 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)Codec.intRange(0, 1023).fieldOf("distance").forGetter(StructureSettingsStronghold::a), (App)Codec.intRange(0, 1023).fieldOf("spread").forGetter(StructureSettingsStronghold::b), (App)Codec.intRange(1, 4095).fieldOf("count").forGetter(StructureSettingsStronghold::c)).apply((Applicative)var0, StructureSettingsStronghold::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<StructureSettingsStronghold> a;
/*    */   
/*    */   private final int b;
/*    */   private final int c;
/*    */   private final int d;
/*    */   
/*    */   public StructureSettingsStronghold(int var0, int var1, int var2) {
/* 18 */     this.b = var0;
/* 19 */     this.c = var1;
/* 20 */     this.d = var2;
/*    */   }
/*    */   
/*    */   public int a() {
/* 24 */     return this.b;
/*    */   }
/*    */   
/*    */   public int b() {
/* 28 */     return this.c;
/*    */   }
/*    */   
/*    */   public int c() {
/* 32 */     return this.d;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\StructureSettingsStronghold.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */