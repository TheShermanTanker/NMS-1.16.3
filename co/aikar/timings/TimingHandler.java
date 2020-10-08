/*     */ package co.aikar.timings;
/*     */ 
/*     */ import co.aikar.util.LoadingIntMap;
/*     */ import com.google.common.base.Function;
/*     */ import it.unimi.dsi.fastutil.ints.Int2ObjectOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.ArrayDeque;
/*     */ import java.util.Deque;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
/*     */ import java.util.logging.Level;
/*     */ import java.util.logging.Logger;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ class TimingHandler
/*     */   implements Timing
/*     */ {
/*  41 */   private static AtomicInteger idPool = new AtomicInteger(1);
/*  42 */   private static Deque<TimingHandler> TIMING_STACK = new ArrayDeque<>();
/*  43 */   final int id = idPool.getAndIncrement();
/*     */   
/*     */   final TimingIdentifier identifier;
/*     */   
/*     */   private final boolean verbose;
/*  48 */   private final Int2ObjectOpenHashMap<TimingData> children = (Int2ObjectOpenHashMap<TimingData>)new LoadingIntMap(TimingData::new);
/*     */   
/*     */   final TimingData record;
/*     */   
/*     */   private TimingHandler startParent;
/*     */   private final TimingHandler groupHandler;
/*  54 */   private long start = 0L;
/*  55 */   private int timingDepth = 0;
/*     */   private boolean added;
/*     */   private boolean timed;
/*     */   private boolean enabled;
/*     */   
/*     */   TimingHandler(@NotNull TimingIdentifier id) {
/*  61 */     this.identifier = id;
/*  62 */     this.verbose = id.name.startsWith("##");
/*  63 */     this.record = new TimingData(this.id);
/*  64 */     this.groupHandler = id.groupHandler;
/*     */     
/*  66 */     (TimingIdentifier.getGroup(id.group)).handlers.add(this);
/*  67 */     checkEnabled();
/*     */   }
/*     */   
/*     */   final void checkEnabled() {
/*  71 */     this.enabled = (Timings.timingsEnabled && (!this.verbose || Timings.verboseEnabled));
/*     */   }
/*     */   
/*     */   void processTick(boolean violated) {
/*  75 */     if (this.timingDepth != 0 || this.record.getCurTickCount() == 0) {
/*  76 */       this.timingDepth = 0;
/*  77 */       this.start = 0L;
/*     */       
/*     */       return;
/*     */     } 
/*  81 */     this.record.processTick(violated);
/*  82 */     for (ObjectIterator<TimingData> objectIterator = this.children.values().iterator(); objectIterator.hasNext(); ) { TimingData handler = objectIterator.next();
/*  83 */       handler.processTick(violated); }
/*     */   
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Timing startTimingIfSync() {
/*  90 */     startTiming();
/*  91 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public void stopTimingIfSync() {
/*  96 */     stopTiming();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   public Timing startTiming() {
/* 101 */     if (!this.enabled || !Bukkit.isPrimaryThread()) {
/* 102 */       return this;
/*     */     }
/* 104 */     if (++this.timingDepth == 1) {
/* 105 */       this.startParent = TIMING_STACK.peekLast();
/* 106 */       this.start = System.nanoTime();
/*     */     } 
/* 108 */     TIMING_STACK.addLast(this);
/* 109 */     return this;
/*     */   }
/*     */   
/*     */   public void stopTiming() {
/* 113 */     if (!this.enabled || this.timingDepth <= 0 || this.start == 0L || !Bukkit.isPrimaryThread()) {
/*     */       return;
/*     */     }
/*     */     
/* 117 */     popTimingStack();
/* 118 */     if (--this.timingDepth == 0) {
/* 119 */       addDiff(System.nanoTime() - this.start, this.startParent);
/* 120 */       this.startParent = null;
/* 121 */       this.start = 0L;
/*     */     } 
/*     */   }
/*     */   
/*     */   private void popTimingStack() {
/*     */     TimingHandler last;
/* 127 */     while ((last = TIMING_STACK.removeLast()) != this) {
/* 128 */       last.timingDepth = 0;
/* 129 */       if ("Minecraft".equalsIgnoreCase(last.identifier.group)) {
/* 130 */         Logger.getGlobal().log(Level.SEVERE, "TIMING_STACK_CORRUPTION - Look above this for any errors and report this to Paper unless it has a plugin in the stack trace (" + last.identifier + " did not stopTiming)");
/*     */       } else {
/* 132 */         Logger.getGlobal().log(Level.SEVERE, "TIMING_STACK_CORRUPTION - Report this to the plugin " + last.identifier.group + " (Look for errors above this in the logs) (" + last.identifier + " did not stopTiming)", new Throwable());
/*     */       } 
/*     */       
/* 135 */       boolean found = TIMING_STACK.contains(this);
/* 136 */       if (!found) {
/*     */         
/* 138 */         TIMING_STACK.addLast(last);
/*     */         break;
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public final void abort() {}
/*     */ 
/*     */   
/*     */   void addDiff(long diff, @Nullable TimingHandler parent) {
/* 150 */     if (parent != null) {
/* 151 */       ((TimingData)parent.children.get(this.id)).add(diff);
/*     */     }
/*     */     
/* 154 */     this.record.add(diff);
/* 155 */     if (!this.added) {
/* 156 */       this.added = true;
/* 157 */       this.timed = true;
/* 158 */       TimingsManager.HANDLERS.add(this);
/*     */     } 
/* 160 */     if (this.groupHandler != null) {
/* 161 */       this.groupHandler.addDiff(diff, parent);
/* 162 */       ((TimingData)this.groupHandler.children.get(this.id)).add(diff);
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   void reset(boolean full) {
/* 170 */     this.record.reset();
/* 171 */     if (full) {
/* 172 */       this.timed = false;
/*     */     }
/* 174 */     this.start = 0L;
/* 175 */     this.timingDepth = 0;
/* 176 */     this.added = false;
/* 177 */     this.children.clear();
/* 178 */     checkEnabled();
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public TimingHandler getTimingHandler() {
/* 184 */     return this;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/* 189 */     return (this == o);
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 194 */     return this.id;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void close() {
/* 202 */     stopTimingIfSync();
/*     */   }
/*     */   
/*     */   public boolean isSpecial() {
/* 206 */     return (this == TimingsManager.FULL_SERVER_TICK || this == TimingsManager.TIMINGS_TICK);
/*     */   }
/*     */   
/*     */   boolean isTimed() {
/* 210 */     return this.timed;
/*     */   }
/*     */   
/*     */   public boolean isEnabled() {
/* 214 */     return this.enabled;
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   TimingData[] cloneChildren() {
/* 219 */     TimingData[] clonedChildren = new TimingData[this.children.size()];
/* 220 */     int i = 0;
/* 221 */     for (ObjectIterator<TimingData> objectIterator = this.children.values().iterator(); objectIterator.hasNext(); ) { TimingData child = objectIterator.next();
/* 222 */       clonedChildren[i++] = child.clone(); }
/*     */     
/* 224 */     return clonedChildren;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\TimingHandler.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */