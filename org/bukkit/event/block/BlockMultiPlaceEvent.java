/*    */ package org.bukkit.event.block;
/*    */ 
/*    */ import com.google.common.collect.ImmutableList;
/*    */ import java.util.List;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.BlockState;
/*    */ import org.bukkit.entity.Player;
/*    */ import org.bukkit.inventory.EquipmentSlot;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class BlockMultiPlaceEvent
/*    */   extends BlockPlaceEvent
/*    */ {
/*    */   private final List<BlockState> states;
/*    */   
/*    */   @Deprecated
/*    */   public BlockMultiPlaceEvent(@NotNull List<BlockState> states, @NotNull Block clicked, @NotNull ItemStack itemInHand, @NotNull Player thePlayer, boolean canBuild) {
/* 24 */     this(states, clicked, itemInHand, thePlayer, canBuild, EquipmentSlot.HAND);
/*    */   }
/*    */ 
/*    */   
/*    */   public BlockMultiPlaceEvent(@NotNull List<BlockState> states, @NotNull Block clicked, @NotNull ItemStack itemInHand, @NotNull Player thePlayer, boolean canBuild, @NotNull EquipmentSlot hand) {
/* 29 */     super(((BlockState)states.get(0)).getBlock(), states.get(0), clicked, itemInHand, thePlayer, canBuild, hand);
/* 30 */     this.states = (List<BlockState>)ImmutableList.copyOf(states);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public List<BlockState> getReplacedBlockStates() {
/* 43 */     return this.states;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\event\block\BlockMultiPlaceEvent.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */