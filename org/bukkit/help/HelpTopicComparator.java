/*    */ package org.bukkit.help;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class HelpTopicComparator
/*    */   implements Comparator<HelpTopic>
/*    */ {
/* 15 */   private static final TopicNameComparator tnc = new TopicNameComparator();
/*    */   @NotNull
/*    */   public static TopicNameComparator topicNameComparatorInstance() {
/* 18 */     return tnc;
/*    */   }
/*    */   
/* 21 */   private static final HelpTopicComparator htc = new HelpTopicComparator();
/*    */   @NotNull
/*    */   public static HelpTopicComparator helpTopicComparatorInstance() {
/* 24 */     return htc;
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int compare(@NotNull HelpTopic lhs, @NotNull HelpTopic rhs) {
/* 31 */     return tnc.compare(lhs.getName(), rhs.getName());
/*    */   }
/*    */   
/*    */   public static final class TopicNameComparator
/*    */     implements Comparator<String> {
/*    */     private TopicNameComparator() {}
/*    */     
/*    */     public int compare(@NotNull String lhs, @NotNull String rhs) {
/* 39 */       boolean lhsStartSlash = lhs.startsWith("/");
/* 40 */       boolean rhsStartSlash = rhs.startsWith("/");
/*    */       
/* 42 */       if (lhsStartSlash && !rhsStartSlash)
/* 43 */         return 1; 
/* 44 */       if (!lhsStartSlash && rhsStartSlash) {
/* 45 */         return -1;
/*    */       }
/* 47 */       return lhs.compareToIgnoreCase(rhs);
/*    */     }
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\help\HelpTopicComparator.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */