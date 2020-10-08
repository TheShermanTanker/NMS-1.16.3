/*    */ package org.bukkit.craftbukkit.v1_16_R2.block.impl;
/*    */ import net.minecraft.server.v1_16_R2.BlockNote;
/*    */ import net.minecraft.server.v1_16_R2.BlockStateEnum;
/*    */ import net.minecraft.server.v1_16_R2.IBlockData;
/*    */ import net.minecraft.server.v1_16_R2.IBlockState;
/*    */ import org.bukkit.Instrument;
/*    */ import org.bukkit.Note;
/*    */ import org.bukkit.block.data.Powerable;
/*    */ import org.bukkit.block.data.type.NoteBlock;
/*    */ 
/*    */ public final class CraftNote extends CraftBlockData implements NoteBlock, Powerable {
/*    */   public CraftNote(IBlockData state) {
/* 13 */     super(state);
/*    */   }
/*    */   
/*    */   public CraftNote() {}
/*    */   
/* 18 */   private static final BlockStateEnum<?> INSTRUMENT = getEnum(BlockNote.class, "instrument");
/* 19 */   private static final BlockStateInteger NOTE = getInteger(BlockNote.class, "note");
/*    */ 
/*    */   
/*    */   public Instrument getInstrument() {
/* 23 */     return (Instrument)get(INSTRUMENT, Instrument.class);
/*    */   }
/*    */ 
/*    */   
/*    */   public void setInstrument(Instrument instrument) {
/* 28 */     set(INSTRUMENT, (Enum)instrument);
/*    */   }
/*    */ 
/*    */   
/*    */   public Note getNote() {
/* 33 */     return new Note(((Integer)get((IBlockState)NOTE)).intValue());
/*    */   }
/*    */ 
/*    */   
/*    */   public void setNote(Note note) {
/* 38 */     set((IBlockState)NOTE, Integer.valueOf(note.getId()));
/*    */   }
/*    */ 
/*    */ 
/*    */   
/* 43 */   private static final BlockStateBoolean POWERED = getBoolean(BlockNote.class, "powered");
/*    */ 
/*    */   
/*    */   public boolean isPowered() {
/* 47 */     return ((Boolean)get((IBlockState)POWERED)).booleanValue();
/*    */   }
/*    */ 
/*    */   
/*    */   public void setPowered(boolean powered) {
/* 52 */     set((IBlockState)POWERED, Boolean.valueOf(powered));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\impl\CraftNote.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */