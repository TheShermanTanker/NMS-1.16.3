/*    */ package net.minecraft.server.v1_16_R2;
/*    */ import com.mojang.datafixers.kinds.App;
/*    */ import com.mojang.serialization.Codec;
/*    */ 
/*    */ public class Music {
/*    */   static {
/*  7 */     a = RecordCodecBuilder.create(var0 -> var0.group((App)SoundEffect.a.fieldOf("sound").forGetter(()), (App)Codec.INT.fieldOf("min_delay").forGetter(()), (App)Codec.INT.fieldOf("max_delay").forGetter(()), (App)Codec.BOOL.fieldOf("replace_current_music").forGetter(())).apply((Applicative)var0, Music::new));
/*    */   }
/*    */ 
/*    */   
/*    */   public static final Codec<Music> a;
/*    */   
/*    */   private final SoundEffect b;
/*    */   
/*    */   private final int c;
/*    */   private final int d;
/*    */   private final boolean e;
/*    */   
/*    */   public Music(SoundEffect var0, int var1, int var2, boolean var3) {
/* 20 */     this.b = var0;
/* 21 */     this.c = var1;
/* 22 */     this.d = var2;
/* 23 */     this.e = var3;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Music.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */