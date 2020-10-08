/*    */ package org.bukkit.craftbukkit.v1_16_R2.command;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.CommandListenerWrapper;
/*    */ import net.minecraft.server.v1_16_R2.GeneratorAccess;
/*    */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*    */ import net.minecraft.server.v1_16_R2.SystemUtils;
/*    */ import net.minecraft.server.v1_16_R2.TileEntity;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.command.BlockCommandSender;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.block.CraftBlock;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*    */ 
/*    */ public class CraftBlockCommandSender
/*    */   extends ServerCommandSender
/*    */   implements BlockCommandSender
/*    */ {
/*    */   private final CommandListenerWrapper block;
/*    */   private final TileEntity tile;
/*    */   
/*    */   public CraftBlockCommandSender(CommandListenerWrapper commandBlockListenerAbstract, TileEntity tile) {
/* 21 */     this.block = commandBlockListenerAbstract;
/* 22 */     this.tile = tile;
/*    */   }
/*    */ 
/*    */   
/*    */   public Block getBlock() {
/* 27 */     return (Block)CraftBlock.at((GeneratorAccess)this.tile.getWorld(), this.tile.getPosition());
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendMessage(String message) {
/* 32 */     for (IChatBaseComponent component : CraftChatMessage.fromString(message)) {
/* 33 */       this.block.base.sendMessage(component, SystemUtils.b);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendMessage(String[] messages) {
/* 39 */     for (String message : messages) {
/* 40 */       sendMessage(message);
/*    */     }
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 46 */     return this.block.getName();
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean isOp() {
/* 51 */     return true;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setOp(boolean value) {
/* 56 */     throw new UnsupportedOperationException("Cannot change operator status of a block");
/*    */   }
/*    */   
/*    */   public CommandListenerWrapper getWrapper() {
/* 60 */     return this.block;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\command\CraftBlockCommandSender.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */