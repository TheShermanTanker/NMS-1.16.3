/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public enum BlockPropertyInstrument
/*    */   implements INamable
/*    */ {
/* 12 */   HARP("harp", SoundEffects.BLOCK_NOTE_BLOCK_HARP),
/* 13 */   BASEDRUM("basedrum", SoundEffects.BLOCK_NOTE_BLOCK_BASEDRUM),
/* 14 */   SNARE("snare", SoundEffects.BLOCK_NOTE_BLOCK_SNARE),
/* 15 */   HAT("hat", SoundEffects.BLOCK_NOTE_BLOCK_HAT),
/* 16 */   BASS("bass", SoundEffects.BLOCK_NOTE_BLOCK_BASS),
/* 17 */   FLUTE("flute", SoundEffects.BLOCK_NOTE_BLOCK_FLUTE),
/* 18 */   BELL("bell", SoundEffects.BLOCK_NOTE_BLOCK_BELL),
/* 19 */   GUITAR("guitar", SoundEffects.BLOCK_NOTE_BLOCK_GUITAR),
/* 20 */   CHIME("chime", SoundEffects.BLOCK_NOTE_BLOCK_CHIME),
/* 21 */   XYLOPHONE("xylophone", SoundEffects.BLOCK_NOTE_BLOCK_XYLOPHONE),
/* 22 */   IRON_XYLOPHONE("iron_xylophone", SoundEffects.BLOCK_NOTE_BLOCK_IRON_XYLOPHONE),
/* 23 */   COW_BELL("cow_bell", SoundEffects.BLOCK_NOTE_BLOCK_COW_BELL),
/* 24 */   DIDGERIDOO("didgeridoo", SoundEffects.BLOCK_NOTE_BLOCK_DIDGERIDOO),
/* 25 */   BIT("bit", SoundEffects.BLOCK_NOTE_BLOCK_BIT),
/* 26 */   BANJO("banjo", SoundEffects.BLOCK_NOTE_BLOCK_BANJO),
/* 27 */   PLING("pling", SoundEffects.BLOCK_NOTE_BLOCK_PLING);
/*    */   
/*    */   private final String q;
/*    */   
/*    */   private final SoundEffect r;
/*    */   
/*    */   BlockPropertyInstrument(String var2, SoundEffect var3) {
/* 34 */     this.q = var2;
/* 35 */     this.r = var3;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 40 */     return this.q;
/*    */   }
/*    */   
/*    */   public SoundEffect b() {
/* 44 */     return this.r;
/*    */   }
/*    */   
/*    */   public static BlockPropertyInstrument a(IBlockData var0) {
/* 48 */     if (var0.a(Blocks.CLAY)) {
/* 49 */       return FLUTE;
/*    */     }
/* 51 */     if (var0.a(Blocks.GOLD_BLOCK)) {
/* 52 */       return BELL;
/*    */     }
/* 54 */     if (var0.a(TagsBlock.WOOL)) {
/* 55 */       return GUITAR;
/*    */     }
/* 57 */     if (var0.a(Blocks.PACKED_ICE)) {
/* 58 */       return CHIME;
/*    */     }
/* 60 */     if (var0.a(Blocks.BONE_BLOCK)) {
/* 61 */       return XYLOPHONE;
/*    */     }
/* 63 */     if (var0.a(Blocks.IRON_BLOCK)) {
/* 64 */       return IRON_XYLOPHONE;
/*    */     }
/* 66 */     if (var0.a(Blocks.SOUL_SAND)) {
/* 67 */       return COW_BELL;
/*    */     }
/* 69 */     if (var0.a(Blocks.PUMPKIN)) {
/* 70 */       return DIDGERIDOO;
/*    */     }
/* 72 */     if (var0.a(Blocks.EMERALD_BLOCK)) {
/* 73 */       return BIT;
/*    */     }
/* 75 */     if (var0.a(Blocks.HAY_BLOCK)) {
/* 76 */       return BANJO;
/*    */     }
/* 78 */     if (var0.a(Blocks.GLOWSTONE)) {
/* 79 */       return PLING;
/*    */     }
/*    */     
/* 82 */     Material var1 = var0.getMaterial();
/* 83 */     if (var1 == Material.STONE) {
/* 84 */       return BASEDRUM;
/*    */     }
/* 86 */     if (var1 == Material.SAND) {
/* 87 */       return SNARE;
/*    */     }
/* 89 */     if (var1 == Material.SHATTERABLE) {
/* 90 */       return HAT;
/*    */     }
/* 92 */     if (var1 == Material.WOOD || var1 == Material.NETHER_WOOD) {
/* 93 */       return BASS;
/*    */     }
/*    */     
/* 96 */     return HARP;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\BlockPropertyInstrument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */