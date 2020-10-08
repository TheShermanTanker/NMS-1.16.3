/*    */ package org.bukkit.command;
/*    */ 
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ public class MultipleCommandAlias
/*    */   extends Command
/*    */ {
/*    */   private Command[] commands;
/*    */   
/*    */   public MultipleCommandAlias(@NotNull String name, @NotNull Command[] commands) {
/* 12 */     super(name);
/* 13 */     this.commands = commands;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   @NotNull
/*    */   public Command[] getCommands() {
/* 23 */     return this.commands;
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean execute(@NotNull CommandSender sender, @NotNull String commandLabel, @NotNull String[] args) {
/* 28 */     boolean result = false;
/*    */     
/* 30 */     for (Command command : this.commands) {
/* 31 */       result |= command.execute(sender, commandLabel, args);
/*    */     }
/*    */     
/* 34 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\MultipleCommandAlias.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */