/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.datafixers.kinds.Applicative;
/*    */ import com.mojang.datafixers.util.Function4;
/*    */ import com.mojang.serialization.Codec;
/*    */ import com.mojang.serialization.codecs.RecordCodecBuilder;
/*    */ 
/*    */ public class CaveSoundSettings {
/*    */   static {
/* 10 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)SoundEffect.a.fieldOf("sound").forGetter(()), (App)Codec.INT.fieldOf("tick_delay").forGetter(()), (App)Codec.INT.fieldOf("block_search_extent").forGetter(()), (App)Codec.DOUBLE.fieldOf("offset").forGetter(())).apply((Applicative)var0, CaveSoundSettings::new));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/*    */   public static final Codec<CaveSoundSettings> a;
/*    */   
/* 17 */   public static final CaveSoundSettings b = new CaveSoundSettings(SoundEffects.AMBIENT_CAVE, 6000, 8, 2.0D);
/*    */ 
/*    */   
/*    */   private SoundEffect c;
/*    */   
/*    */   private int d;
/*    */   
/*    */   private int e;
/*    */   
/*    */   private double f;
/*    */ 
/*    */   
/*    */   public CaveSoundSettings(SoundEffect var0, int var1, int var2, double var3) {
/* 30 */     this.c = var0;
/* 31 */     this.d = var1;
/* 32 */     this.e = var2;
/* 33 */     this.f = var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CaveSoundSettings.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */