/*    */ package net.minecraft.server.v1_16_R2;
/*    */ public class NextTickListEntry<T> {
/*    */   private final T e;
/*    */   public final BlockPosition a;
/*    */   public final long b;
/*    */   public final TickListPriority c;
/*  7 */   private static final AtomicLong COUNTER = new AtomicLong(); private final long f; private final int hash; public int tickState; public final T getData() {
/*  8 */     return this.e; }
/*  9 */   public final BlockPosition getPosition() { return this.a; }
/* 10 */   public final long getTargetTick() { return this.b; }
/* 11 */   public final TickListPriority getPriority() { return this.c; } public final long getId() {
/* 12 */     return this.f;
/*    */   }
/*    */ 
/*    */   
/*    */   public NextTickListEntry(BlockPosition blockposition, T t0) {
/* 17 */     this(blockposition, t0, 0L, TickListPriority.NORMAL);
/*    */   }
/*    */   
/*    */   public NextTickListEntry(BlockPosition blockposition, T t0, long i, TickListPriority ticklistpriority) {
/* 21 */     this.f = COUNTER.getAndIncrement();
/* 22 */     this.a = blockposition.immutableCopy();
/* 23 */     this.e = t0;
/* 24 */     this.b = i;
/* 25 */     this.c = ticklistpriority;
/* 26 */     this.hash = computeHash();
/*    */   }
/*    */   
/*    */   public boolean equals(Object object) {
/* 30 */     if (!(object instanceof NextTickListEntry)) {
/* 31 */       return false;
/*    */     }
/* 33 */     NextTickListEntry<?> nextticklistentry = (NextTickListEntry)object;
/*    */     
/* 35 */     return (this.a.equals(nextticklistentry.a) && this.e == nextticklistentry.e);
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public int hashCode() {
/* 42 */     return this.hash;
/*    */   }
/*    */   
/*    */   public final int computeHash() {
/* 46 */     return this.a.hashCode();
/*    */   }
/*    */   
/*    */   public static <T> Comparator<Object> comparator() {
/* 50 */     return a();
/*    */   } public static <T> Comparator<Object> a() {
/* 52 */     return (nextticklistentry, nextticklistentry1) -> {
/*    */         int i = Long.compare(nextticklistentry.getTargetTick(), nextticklistentry1.getTargetTick());
/*    */         if (i != 0) {
/*    */           return i;
/*    */         }
/*    */         i = nextticklistentry.getPriority().compareTo(nextticklistentry1.getPriority());
/*    */         return (i != 0) ? i : Long.compare(nextticklistentry.getId(), nextticklistentry1.getId());
/*    */       };
/*    */   }
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public String toString() {
/* 66 */     return (new StringBuilder()).append(this.e).append(": ").append(this.a).append(", ").append(this.b).append(", ").append(this.c).append(", ").append(this.f).toString();
/*    */   }
/*    */   
/*    */   public T b() {
/* 70 */     return this.e;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\NextTickListEntry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */