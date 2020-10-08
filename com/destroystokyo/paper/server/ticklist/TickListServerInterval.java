/*    */ package com.destroystokyo.paper.server.ticklist;
/*    */ 
/*    */ import com.destroystokyo.paper.util.set.LinkedSortedSet;
/*    */ import java.util.Comparator;
/*    */ import net.minecraft.server.v1_16_R2.NextTickListEntry;
/*    */ import net.minecraft.server.v1_16_R2.TickListPriority;
/*    */ 
/*    */ 
/*    */ public final class TickListServerInterval<T>
/*    */ {
/* 11 */   public static final int TOTAL_PRIORITIES = (TickListPriority.values()).length; static {
/* 12 */     ENTRY_COMPARATOR_BY_ID = ((entry1, entry2) -> Long.compare(entry1.getId(), entry2.getId()));
/*    */   }
/*    */   public static final Comparator<NextTickListEntry<?>> ENTRY_COMPARATOR_BY_ID;
/* 15 */   public static final Comparator<NextTickListEntry<?>> ENTRY_COMPARATOR = NextTickListEntry.comparator();
/*    */ 
/*    */ 
/*    */ 
/*    */   
/* 20 */   public final LinkedSortedSet<NextTickListEntry<T>>[] byPriority = (LinkedSortedSet<NextTickListEntry<T>>[])new LinkedSortedSet[TOTAL_PRIORITIES];
/*    */   
/*    */   public TickListServerInterval() {
/* 23 */     for (int i = 0, len = this.byPriority.length; i < len; i++) {
/* 24 */       this.byPriority[i] = new LinkedSortedSet(ENTRY_COMPARATOR_BY_ID);
/*    */     }
/*    */   }
/*    */   
/*    */   public void addEntryLast(NextTickListEntry<T> entry) {
/* 29 */     this.byPriority[entry.getPriority().ordinal()].addLast(entry);
/*    */   }
/*    */   
/*    */   public void addEntryFirst(NextTickListEntry<T> entry) {
/* 33 */     this.byPriority[entry.getPriority().ordinal()].addFirst(entry);
/*    */   }
/*    */   
/*    */   public void clear() {
/* 37 */     for (int i = 0, len = this.byPriority.length; i < len; i++)
/* 38 */       this.byPriority[i].clear(); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\server\ticklist\TickListServerInterval.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */