/*    */ package org.bukkit.craftbukkit.v1_16_R2.scoreboard;
/*    */ 
/*    */ import com.google.common.collect.ImmutableBiMap;
/*    */ import net.minecraft.server.v1_16_R2.IScoreboardCriteria;
/*    */ import net.minecraft.server.v1_16_R2.Scoreboard;
/*    */ import org.bukkit.scoreboard.DisplaySlot;
/*    */ import org.bukkit.scoreboard.RenderType;
/*    */ 
/*    */ final class CraftScoreboardTranslations {
/*    */   static final int MAX_DISPLAY_SLOT = 3;
/* 11 */   static ImmutableBiMap<DisplaySlot, String> SLOTS = ImmutableBiMap.of(DisplaySlot.BELOW_NAME, "belowName", DisplaySlot.PLAYER_LIST, "list", DisplaySlot.SIDEBAR, "sidebar");
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   static DisplaySlot toBukkitSlot(int i) {
/* 19 */     return (DisplaySlot)SLOTS.inverse().get(Scoreboard.getSlotName(i));
/*    */   }
/*    */   
/*    */   static int fromBukkitSlot(DisplaySlot slot) {
/* 23 */     return Scoreboard.getSlotForName((String)SLOTS.get(slot));
/*    */   }
/*    */   
/*    */   static RenderType toBukkitRender(IScoreboardCriteria.EnumScoreboardHealthDisplay display) {
/* 27 */     return RenderType.valueOf(display.name());
/*    */   }
/*    */   
/*    */   static IScoreboardCriteria.EnumScoreboardHealthDisplay fromBukkitRender(RenderType render) {
/* 31 */     return IScoreboardCriteria.EnumScoreboardHealthDisplay.valueOf(render.name());
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\scoreboard\CraftScoreboardTranslations.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */