/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Arrays;
/*    */ import java.util.Map;
/*    */ import java.util.function.Function;
/*    */ import java.util.stream.Collectors;
/*    */ 
/*    */ public enum SoundCategory
/*    */ {
/* 10 */   MASTER("master"),
/* 11 */   MUSIC("music"),
/* 12 */   RECORDS("record"),
/* 13 */   WEATHER("weather"),
/* 14 */   BLOCKS("block"),
/* 15 */   HOSTILE("hostile"),
/* 16 */   NEUTRAL("neutral"),
/* 17 */   PLAYERS("player"),
/* 18 */   AMBIENT("ambient"),
/* 19 */   VOICE("voice"); private static final Map<String, SoundCategory> k;
/*    */   
/*    */   static {
/* 22 */     k = (Map<String, SoundCategory>)Arrays.<SoundCategory>stream(values()).collect(Collectors.toMap(SoundCategory::a, Function.identity()));
/*    */   }
/*    */   private final String l;
/*    */   
/*    */   SoundCategory(String var2) {
/* 27 */     this.l = var2;
/*    */   }
/*    */   
/*    */   public String a() {
/* 31 */     return this.l;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\SoundCategory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */