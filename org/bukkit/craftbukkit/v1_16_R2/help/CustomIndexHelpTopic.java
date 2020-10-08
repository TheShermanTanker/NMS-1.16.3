/*    */ package org.bukkit.craftbukkit.v1_16_R2.help;
/*    */ 
/*    */ import java.util.HashSet;
/*    */ import java.util.LinkedList;
/*    */ import java.util.List;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.help.HelpMap;
/*    */ import org.bukkit.help.HelpTopic;
/*    */ import org.bukkit.help.IndexHelpTopic;
/*    */ 
/*    */ public class CustomIndexHelpTopic
/*    */   extends IndexHelpTopic
/*    */ {
/*    */   private List<String> futureTopics;
/*    */   private final HelpMap helpMap;
/*    */   
/*    */   public CustomIndexHelpTopic(HelpMap helpMap, String name, String shortText, String permission, List<String> futureTopics, String preamble) {
/* 18 */     super(name, shortText, permission, new HashSet(), preamble);
/* 19 */     this.helpMap = helpMap;
/* 20 */     this.futureTopics = futureTopics;
/*    */   }
/*    */ 
/*    */   
/*    */   public String getFullText(CommandSender sender) {
/* 25 */     if (this.futureTopics != null) {
/* 26 */       List<HelpTopic> topics = new LinkedList<>();
/* 27 */       for (String futureTopic : this.futureTopics) {
/* 28 */         HelpTopic topic = this.helpMap.getHelpTopic(futureTopic);
/* 29 */         if (topic != null) {
/* 30 */           topics.add(topic);
/*    */         }
/*    */       } 
/* 33 */       setTopicsCollection(topics);
/* 34 */       this.futureTopics = null;
/*    */     } 
/*    */     
/* 37 */     return super.getFullText(sender);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\help\CustomIndexHelpTopic.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */