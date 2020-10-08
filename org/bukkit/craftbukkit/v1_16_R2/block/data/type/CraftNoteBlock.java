/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.data.type;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.Instrument;
/*    */ import org.bukkit.Note;
/*    */ 
/*    */ public abstract class CraftNoteBlock extends CraftBlockData implements NoteBlock {
/*  8 */   private static final BlockStateEnum<?> INSTRUMENT = getEnum("instrument");
/*  9 */   private static final BlockStateInteger NOTE = getInteger("note");
/*    */ 
/*    */   
/*    */   public Instrument getInstrument() {
/* 13 */     return (Instrument)get(INSTRUMENT, Instrument.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInstrument(Instrument instrument) {
/* 18 */     set(INSTRUMENT, (Enum)instrument);
/*    */   }
/*    */ 
/*    */   
/*    */   public Note getNote() {
/* 23 */     return new Note(((Integer)get((IBlockState)NOTE)).intValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNote(Note note) {
/* 28 */     set((IBlockState)NOTE, Integer.valueOf(note.getId()));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\data\type\CraftNoteBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */