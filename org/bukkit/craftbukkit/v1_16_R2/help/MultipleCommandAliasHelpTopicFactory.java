/*    */ package org.bukkit.craftbukkit.v1_16_R2.help;
/*    */ 
/*    */ import org.bukkit.command.Command;
/*    */ import org.bukkit.command.MultipleCommandAlias;
/*    */ import org.bukkit.help.HelpTopic;
/*    */ import org.bukkit.help.HelpTopicFactory;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class MultipleCommandAliasHelpTopicFactory
/*    */   implements HelpTopicFactory<MultipleCommandAlias>
/*    */ {
/*    */   public HelpTopic createTopic(MultipleCommandAlias multipleCommandAlias) {
/* 14 */     return new MultipleCommandAliasHelpTopic(multipleCommandAlias);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\help\MultipleCommandAliasHelpTopicFactory.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */