/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class Musics
/*    */ {
/* 12 */   public static final Music a = new Music(SoundEffects.MUSIC_MENU, 20, 600, true);
/* 13 */   public static final Music b = new Music(SoundEffects.MUSIC_CREATIVE, 12000, 24000, false);
/* 14 */   public static final Music c = new Music(SoundEffects.MUSIC_CREDITS, 0, 0, true);
/* 15 */   public static final Music d = new Music(SoundEffects.MUSIC_DRAGON, 0, 0, true);
/* 16 */   public static final Music e = new Music(SoundEffects.MUSIC_END, 6000, 24000, true);
/*    */   
/* 18 */   public static final Music f = a(SoundEffects.MUSIC_UNDER_WATER);
/* 19 */   public static final Music g = a(SoundEffects.MUSIC_GAME);
/*    */   
/*    */   public static Music a(SoundEffect var0) {
/* 22 */     return new Music(var0, 12000, 24000, false);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Musics.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */