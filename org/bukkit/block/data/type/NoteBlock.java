package org.bukkit.block.data.type;

import org.bukkit.Instrument;
import org.bukkit.Note;
import org.bukkit.block.data.Powerable;
import org.jetbrains.annotations.NotNull;

public interface NoteBlock extends Powerable {
  @NotNull
  Instrument getInstrument();
  
  void setInstrument(@NotNull Instrument paramInstrument);
  
  @NotNull
  Note getNote();
  
  void setNote(@NotNull Note paramNote);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\block\data\type\NoteBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */