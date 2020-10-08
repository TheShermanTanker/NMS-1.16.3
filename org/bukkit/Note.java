/*     */ package org.bukkit;
/*     */ 
/*     */ import com.google.common.collect.Maps;
/*     */ import java.util.Map;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public class Note
/*     */ {
/*     */   private final byte note;
/*     */   
/*     */   public enum Tone
/*     */   {
/*  18 */     G(1, true),
/*  19 */     A(3, true),
/*  20 */     B(5, false),
/*  21 */     C(6, true),
/*  22 */     D(8, true),
/*  23 */     E(10, false),
/*  24 */     F(11, true);
/*     */     
/*     */     private final boolean sharpable;
/*     */     
/*     */     private final byte id;
/*  29 */     private static final Map<Byte, Tone> BY_DATA = Maps.newHashMap();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public static final byte TONES_COUNT = 12;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     Tone(int id, boolean sharpable) {
/*     */       this.id = (byte)(id % 12);
/*     */       this.sharpable = sharpable;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public byte getId() {
/*     */       return getId(false);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public byte getId(boolean sharped) {
/*     */       byte id = (byte)((sharped && this.sharpable) ? (this.id + 1) : this.id);
/*     */       return (byte)(id % 12);
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean isSharpable() {
/*     */       return this.sharpable;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     public boolean isSharped(byte id) {
/*     */       if (id == getId(false)) {
/*     */         return false;
/*     */       }
/*     */       if (id == getId(true)) {
/*     */         return true;
/*     */       }
/*     */       throw new IllegalArgumentException("The id isn't matching to the tone.");
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     static {
/* 109 */       for (Tone tone : values()) {
/* 110 */         int id = tone.id % 12;
/* 111 */         BY_DATA.put(Byte.valueOf((byte)id), tone);
/*     */         
/* 113 */         if (tone.isSharpable()) {
/* 114 */           id = (id + 1) % 12;
/* 115 */           BY_DATA.put(Byte.valueOf((byte)id), tone);
/*     */         } 
/*     */       } 
/*     */     }
/*     */ 
/*     */     
/*     */     @Deprecated
/*     */     @Nullable
/*     */     public static Tone getById(byte id) {
/*     */       return BY_DATA.get(Byte.valueOf(id));
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public Note(int note) {
/* 130 */     Validate.isTrue((note >= 0 && note <= 24), "The note value has to be between 0 and 24.");
/*     */     
/* 132 */     this.note = (byte)note;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Note(int octave, @NotNull Tone tone, boolean sharped) {
/* 144 */     if (sharped && !tone.isSharpable()) {
/* 145 */       tone = Tone.values()[tone.ordinal() + 1];
/* 146 */       sharped = false;
/*     */     } 
/* 148 */     if (octave < 0 || octave > 2 || (octave == 2 && (tone != Tone.F || !sharped))) {
/* 149 */       throw new IllegalArgumentException("Tone and octave have to be between F#0 and F#2");
/*     */     }
/*     */     
/* 152 */     this.note = (byte)(octave * 12 + tone.getId(sharped));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Note flat(int octave, @NotNull Tone tone) {
/* 164 */     Validate.isTrue((octave != 2), "Octave cannot be 2 for flats");
/* 165 */     tone = (tone == Tone.G) ? Tone.F : Tone.values()[tone.ordinal() - 1];
/* 166 */     return new Note(octave, tone, tone.isSharpable());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Note sharp(int octave, @NotNull Tone tone) {
/* 179 */     return new Note(octave, tone, true);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public static Note natural(int octave, @NotNull Tone tone) {
/* 191 */     Validate.isTrue((octave != 2), "Octave cannot be 2 for naturals");
/* 192 */     return new Note(octave, tone, false);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Note sharped() {
/* 200 */     Validate.isTrue((this.note < 24), "This note cannot be sharped because it is the highest known note!");
/* 201 */     return new Note(this.note + 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Note flattened() {
/* 209 */     Validate.isTrue((this.note > 0), "This note cannot be flattened because it is the lowest known note!");
/* 210 */     return new Note(this.note - 1);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public byte getId() {
/* 221 */     return this.note;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public int getOctave() {
/* 230 */     return this.note / 12;
/*     */   }
/*     */   
/*     */   private byte getToneByte() {
/* 234 */     return (byte)(this.note % 12);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Tone getTone() {
/* 244 */     return Tone.getById(getToneByte());
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isSharped() {
/* 253 */     byte note = getToneByte();
/* 254 */     return Tone.getById(note).isSharped(note);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 259 */     int prime = 31;
/* 260 */     int result = 1;
/* 261 */     result = 31 * result + this.note;
/* 262 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/* 267 */     if (this == obj)
/* 268 */       return true; 
/* 269 */     if (obj == null)
/* 270 */       return false; 
/* 271 */     if (getClass() != obj.getClass())
/* 272 */       return false; 
/* 273 */     Note other = (Note)obj;
/* 274 */     if (this.note != other.note)
/* 275 */       return false; 
/* 276 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 281 */     return "Note{" + getTone().toString() + (isSharped() ? "#" : "") + "}";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\Note.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */