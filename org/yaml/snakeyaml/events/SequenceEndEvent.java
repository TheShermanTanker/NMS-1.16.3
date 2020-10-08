/*    */ package org.yaml.snakeyaml.events;
/*    */ 
/*    */ import org.yaml.snakeyaml.error.Mark;
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public final class SequenceEndEvent
/*    */   extends CollectionEndEvent
/*    */ {
/*    */   public SequenceEndEvent(Mark startMark, Mark endMark) {
/* 28 */     super(startMark, endMark);
/*    */   }
/*    */ 
/*    */   
/*    */   public Event.ID getEventId() {
/* 33 */     return Event.ID.SequenceEnd;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\yaml\snakeyaml\events\SequenceEndEvent.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */