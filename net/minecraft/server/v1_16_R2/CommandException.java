/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ 
/*    */ public class CommandException
/*    */   extends RuntimeException {
/*    */   private final IChatBaseComponent a;
/*    */   
/*    */   public CommandException(IChatBaseComponent var0) {
/* 10 */     super(var0.getString(), null, CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES, CommandSyntaxException.ENABLE_COMMAND_STACK_TRACES);
/* 11 */     this.a = var0;
/*    */   }
/*    */   
/*    */   public IChatBaseComponent a() {
/* 15 */     return this.a;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\CommandException.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */