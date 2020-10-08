/*     */ package org.apache.logging.slf4j;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.concurrent.ConcurrentHashMap;
/*     */ import java.util.concurrent.ConcurrentMap;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ import org.apache.logging.log4j.status.StatusLogger;
/*     */ import org.slf4j.IMarkerFactory;
/*     */ import org.slf4j.Marker;
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
/*     */ public class Log4jMarkerFactory
/*     */   implements IMarkerFactory
/*     */ {
/*  36 */   private static final Logger LOGGER = (Logger)StatusLogger.getLogger();
/*     */   
/*  38 */   private final ConcurrentMap<String, Marker> markerMap = new ConcurrentHashMap<>();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Marker getMarker(String name) {
/*  47 */     if (name == null) {
/*  48 */       throw new IllegalArgumentException("Marker name must not be null");
/*     */     }
/*  50 */     Marker marker = this.markerMap.get(name);
/*  51 */     if (marker != null) {
/*  52 */       return marker;
/*     */     }
/*  54 */     Marker log4jMarker = MarkerManager.getMarker(name);
/*  55 */     return addMarkerIfAbsent(name, log4jMarker);
/*     */   }
/*     */   
/*     */   private Marker addMarkerIfAbsent(String name, Marker log4jMarker) {
/*  59 */     Marker marker = new Log4jMarker(log4jMarker);
/*  60 */     Marker existing = this.markerMap.putIfAbsent(name, marker);
/*  61 */     return (existing == null) ? marker : existing;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Marker getMarker(Marker marker) {
/*  71 */     if (marker == null) {
/*  72 */       throw new IllegalArgumentException("Marker must not be null");
/*     */     }
/*  74 */     Marker m = this.markerMap.get(marker.getName());
/*  75 */     if (m != null) {
/*  76 */       return m;
/*     */     }
/*  78 */     return addMarkerIfAbsent(marker.getName(), convertMarker(marker));
/*     */   }
/*     */   
/*     */   private static Marker convertMarker(Marker original) {
/*  82 */     if (original == null) {
/*  83 */       throw new IllegalArgumentException("Marker must not be null");
/*     */     }
/*  85 */     return convertMarker(original, new ArrayList<>());
/*     */   }
/*     */ 
/*     */   
/*     */   private static Marker convertMarker(Marker original, Collection<Marker> visited) {
/*  90 */     Marker marker = MarkerManager.getMarker(original.getName());
/*  91 */     if (original.hasReferences()) {
/*  92 */       Iterator<Marker> it = original.iterator();
/*  93 */       while (it.hasNext()) {
/*  94 */         Marker next = it.next();
/*  95 */         if (visited.contains(next)) {
/*  96 */           LOGGER.warn("Found a cycle in Marker [{}]. Cycle will be broken.", next.getName()); continue;
/*     */         } 
/*  98 */         visited.add(next);
/*  99 */         marker.addParents(new Marker[] { convertMarker(next, visited) });
/*     */       } 
/*     */     } 
/*     */     
/* 103 */     return marker;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean exists(String name) {
/* 113 */     return this.markerMap.containsKey(name);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean detachMarker(String name) {
/* 123 */     return false;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public Marker getDetachedMarker(String name) {
/* 133 */     LOGGER.warn("Log4j does not support detached Markers. Returned Marker [{}] will be unchanged.", name);
/* 134 */     return getMarker(name);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\slf4j\Log4jMarkerFactory.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */