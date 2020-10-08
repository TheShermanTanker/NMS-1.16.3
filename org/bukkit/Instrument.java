/*     */ package org.bukkit;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public enum Instrument
/*     */ {
/*  12 */   PIANO(0),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  17 */   BASS_DRUM(1),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  22 */   SNARE_DRUM(2),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  27 */   STICKS(3),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  32 */   BASS_GUITAR(4),
/*     */ 
/*     */ 
/*     */   
/*  36 */   FLUTE(5),
/*     */ 
/*     */ 
/*     */   
/*  40 */   BELL(6),
/*     */ 
/*     */ 
/*     */   
/*  44 */   GUITAR(7),
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  49 */   CHIME(8),
/*     */ 
/*     */ 
/*     */   
/*  53 */   XYLOPHONE(9),
/*     */ 
/*     */ 
/*     */   
/*  57 */   IRON_XYLOPHONE(10),
/*     */ 
/*     */ 
/*     */   
/*  61 */   COW_BELL(11),
/*     */ 
/*     */ 
/*     */   
/*  65 */   DIDGERIDOO(12),
/*     */ 
/*     */ 
/*     */   
/*  69 */   BIT(13),
/*     */ 
/*     */ 
/*     */   
/*  73 */   BANJO(14),
/*     */ 
/*     */ 
/*     */   
/*  77 */   PLING(15);
/*     */   
/*     */   static {
/*  80 */     BY_DATA = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/* 109 */     for (Instrument instrument : values())
/* 110 */       BY_DATA.put(Byte.valueOf(instrument.getType()), instrument); 
/*     */   }
/*     */   
/*     */   private final byte type;
/*     */   private static final Map<Byte, Instrument> BY_DATA;
/*     */   
/*     */   Instrument(int type) {
/*     */     this.type = (byte)type;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   public byte getType() {
/*     */     return this.type;
/*     */   }
/*     */   
/*     */   @Deprecated
/*     */   @Nullable
/*     */   public static Instrument getByType(byte type) {
/*     */     return BY_DATA.get(Byte.valueOf(type));
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Instrument.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */