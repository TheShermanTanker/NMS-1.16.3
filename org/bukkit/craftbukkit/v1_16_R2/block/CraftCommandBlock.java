/*    */ package org.bukkit.craftbukkit.v1_16_R2.block;
/*    */ 
/*    */ import net.minecraft.server.v1_16_R2.TileEntity;
/*    */ import net.minecraft.server.v1_16_R2.TileEntityCommand;
/*    */ import org.bukkit.Material;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.CommandBlock;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftChatMessage;
/*    */ 
/*    */ public class CraftCommandBlock extends CraftBlockEntityState<TileEntityCommand> implements CommandBlock {
/*    */   private String command;
/*    */   private String name;
/*    */   
/*    */   public CraftCommandBlock(Block block) {
/* 15 */     super(block, TileEntityCommand.class);
/*    */   }
/*    */   
/*    */   public CraftCommandBlock(Material material, TileEntityCommand te) {
/* 19 */     super(material, te);
/*    */   }
/*    */ 
/*    */   
/*    */   public void load(TileEntityCommand commandBlock) {
/* 24 */     super.load(commandBlock);
/*    */     
/* 26 */     this.command = commandBlock.getCommandBlock().getCommand();
/* 27 */     this.name = CraftChatMessage.fromComponent(commandBlock.getCommandBlock().getName());
/*    */   }
/*    */ 
/*    */   
/*    */   public String getCommand() {
/* 32 */     return this.command;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setCommand(String command) {
/* 37 */     this.command = (command != null) ? command : "";
/*    */   }
/*    */ 
/*    */   
/*    */   public String getName() {
/* 42 */     return this.name;
/*    */   }
/*    */ 
/*    */   
/*    */   public void setName(String name) {
/* 47 */     this.name = (name != null) ? name : "@";
/*    */   }
/*    */ 
/*    */   
/*    */   public void applyTo(TileEntityCommand commandBlock) {
/* 52 */     super.applyTo(commandBlock);
/*    */     
/* 54 */     commandBlock.getCommandBlock().setCommand(this.command);
/* 55 */     commandBlock.getCommandBlock().setName(CraftChatMessage.fromStringOrNull(this.name));
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\block\CraftCommandBlock.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */