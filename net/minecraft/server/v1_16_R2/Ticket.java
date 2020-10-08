/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ 
/*    */ public final class Ticket<T> implements Comparable<Ticket<?>> {
/*    */   private final TicketType<T> a;
/*    */   private int b;
/*    */   
/*  8 */   public final void setTicketLevel(int value) { this.b = value; } public final T identifier; private long d; public final T getObjectReason() {
/*  9 */     return this.identifier; }
/* 10 */   public final long getCreationTick() { return this.d; } public final void setCreationTick(long value) { this.d = value; }
/* 11 */    public int priority = 0;
/*    */   boolean isCached;
/*    */   
/*    */   protected Ticket(TicketType<T> tickettype, int i, T t0) {
/* 15 */     this.a = tickettype;
/* 16 */     this.b = i;
/* 17 */     this.identifier = t0;
/*    */   }
/*    */ 
/*    */   
/*    */   public int compareTo(Ticket<?> ticket) {
/* 22 */     int i = Integer.compare(this.b, ticket.b);
/*    */     
/* 24 */     if (i != 0) {
/* 25 */       return i;
/*    */     }
/* 27 */     int j = Integer.compare(System.identityHashCode(this.a), System.identityHashCode(ticket.a));
/*    */     
/* 29 */     return (j != 0) ? j : this.a.a().compare(this.identifier, ticket.identifier);
/*    */   }
/*    */ 
/*    */   
/*    */   public boolean equals(Object object) {
/* 34 */     if (this == object)
/* 35 */       return true; 
/* 36 */     if (!(object instanceof Ticket)) {
/* 37 */       return false;
/*    */     }
/* 39 */     Ticket<?> ticket = (Ticket)object;
/*    */     
/* 41 */     return (this.b == ticket.b && Objects.equals(this.a, ticket.a) && Objects.equals(this.identifier, ticket.identifier));
/*    */   }
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 46 */     return Objects.hash(new Object[] { this.a, Integer.valueOf(this.b), this.identifier });
/*    */   }
/*    */   
/*    */   public String toString() {
/* 50 */     return "Ticket[" + this.a + " " + this.b + " (" + this.identifier + ")] at " + this.d;
/*    */   }
/*    */   
/*    */   public TicketType<T> getTicketType() {
/* 54 */     return this.a;
/*    */   }
/*    */   public final int getTicketLevel() {
/* 57 */     return b();
/*    */   } public int b() {
/* 59 */     return this.b;
/*    */   }
/*    */   public final void setCurrentTick(long i) {
/* 62 */     a(i);
/*    */   } protected void a(long i) {
/* 64 */     this.d = i;
/*    */   }
/*    */   protected final boolean isExpired(long time) {
/* 67 */     return b(time);
/*    */   } protected boolean b(long i) {
/* 69 */     long j = this.a.b();
/*    */     
/* 71 */     return (j != 0L && i - this.d > j);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Ticket.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */