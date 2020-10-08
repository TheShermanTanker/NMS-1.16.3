/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ public class SoundEffect {
/*    */   public static final Codec<SoundEffect> a;
/*    */   
/*    */   static {
/*  7 */     a = MinecraftKey.a.xmap(SoundEffect::new, var0 -> var0.b);
/*    */   }
/*    */   private final MinecraftKey b;
/*    */   
/*    */   public SoundEffect(MinecraftKey var0) {
/* 12 */     this.b = var0;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SoundEffect.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */