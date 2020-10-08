/*     */ package co.aikar.timings;
/*     */ 
/*     */ import co.aikar.util.LoadingMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.atomic.AtomicInteger;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ final class TimingIdentifier
/*     */ {
/*     */   static final Map<String, TimingGroup> GROUP_MAP;
/*     */   
/*     */   static {
/*  47 */     GROUP_MAP = LoadingMap.of(new ConcurrentHashMap<>(64, 0.5F), x$0 -> new TimingGroup(x$0));
/*  48 */   } private static final TimingGroup DEFAULT_GROUP = getGroup("Minecraft");
/*     */   final String group;
/*     */   final String name;
/*     */   final TimingHandler groupHandler;
/*     */   private final int hashCode;
/*     */   
/*     */   TimingIdentifier(@Nullable String group, @NotNull String name, @Nullable Timing groupHandler) {
/*  55 */     this.group = (group != null) ? group : DEFAULT_GROUP.name;
/*  56 */     this.name = name;
/*  57 */     this.groupHandler = (groupHandler != null) ? groupHandler.getTimingHandler() : null;
/*  58 */     this.hashCode = 31 * this.group.hashCode() + this.name.hashCode();
/*     */   }
/*     */   
/*     */   @NotNull
/*     */   static TimingGroup getGroup(@Nullable String groupName) {
/*  63 */     if (groupName == null)
/*     */     {
/*  65 */       return DEFAULT_GROUP;
/*     */     }
/*     */     
/*  68 */     return GROUP_MAP.get(groupName);
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object o) {
/*  73 */     if (o == null) {
/*  74 */       return false;
/*     */     }
/*     */     
/*  77 */     TimingIdentifier that = (TimingIdentifier)o;
/*  78 */     return (Objects.equals(this.group, that.group) && Objects.equals(this.name, that.name));
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/*  83 */     return this.hashCode;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/*  88 */     return "TimingIdentifier{id=" + this.group + ":" + this.name + '}';
/*     */   }
/*     */   
/*     */   static class TimingGroup
/*     */   {
/*  93 */     private static AtomicInteger idPool = new AtomicInteger(1);
/*  94 */     final int id = idPool.getAndIncrement();
/*     */     
/*     */     final String name;
/*  97 */     final List<TimingHandler> handlers = Collections.synchronizedList(new ArrayList<>(64));
/*     */     
/*     */     private TimingGroup(String name) {
/* 100 */       this.name = name;
/*     */     }
/*     */ 
/*     */     
/*     */     public boolean equals(Object o) {
/* 105 */       if (this == o) return true; 
/* 106 */       if (o == null || getClass() != o.getClass()) return false; 
/* 107 */       TimingGroup that = (TimingGroup)o;
/* 108 */       return (this.id == that.id);
/*     */     }
/*     */ 
/*     */     
/*     */     public int hashCode() {
/* 113 */       return this.id;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\TimingIdentifier.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */