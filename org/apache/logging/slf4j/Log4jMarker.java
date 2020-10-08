/*     */ package org.apache.logging.slf4j;
/*     */ 
/*     */ import java.util.ArrayList;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import org.apache.logging.log4j.Marker;
/*     */ import org.apache.logging.log4j.MarkerManager;
/*     */ import org.slf4j.IMarkerFactory;
/*     */ import org.slf4j.Marker;
/*     */ import org.slf4j.impl.StaticMarkerBinder;
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
/*     */ public class Log4jMarker
/*     */   implements Marker
/*     */ {
/*     */   public static final long serialVersionUID = 1590472L;
/*  35 */   private final IMarkerFactory factory = StaticMarkerBinder.SINGLETON.getMarkerFactory();
/*     */ 
/*     */ 
/*     */   
/*     */   private final Marker marker;
/*     */ 
/*     */ 
/*     */   
/*     */   public Log4jMarker(Marker marker) {
/*  44 */     this.marker = marker;
/*     */   }
/*     */ 
/*     */   
/*     */   public void add(Marker marker) {
/*  49 */     if (marker == null) {
/*  50 */       throw new IllegalArgumentException();
/*     */     }
/*  52 */     Marker m = this.factory.getMarker(marker.getName());
/*  53 */     this.marker.addParents(new Marker[] { ((Log4jMarker)m).getLog4jMarker() });
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(Marker marker) {
/*  58 */     if (marker == null) {
/*  59 */       throw new IllegalArgumentException();
/*     */     }
/*  61 */     return this.marker.isInstanceOf(marker.getName());
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean contains(String s) {
/*  66 */     return (s != null) ? this.marker.isInstanceOf(s) : false;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean equals(Object obj) {
/*  71 */     if (this == obj) {
/*  72 */       return true;
/*     */     }
/*  74 */     if (obj == null) {
/*  75 */       return false;
/*     */     }
/*  77 */     if (!(obj instanceof Log4jMarker)) {
/*  78 */       return false;
/*     */     }
/*  80 */     Log4jMarker other = (Log4jMarker)obj;
/*  81 */     if (this.marker == null) {
/*  82 */       if (other.marker != null) {
/*  83 */         return false;
/*     */       }
/*  85 */     } else if (!this.marker.equals(other.marker)) {
/*  86 */       return false;
/*     */     } 
/*  88 */     return true;
/*     */   }
/*     */   
/*     */   public Marker getLog4jMarker() {
/*  92 */     return this.marker;
/*     */   }
/*     */ 
/*     */   
/*     */   public String getName() {
/*  97 */     return this.marker.getName();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasChildren() {
/* 102 */     return this.marker.hasParents();
/*     */   }
/*     */ 
/*     */   
/*     */   public int hashCode() {
/* 107 */     int prime = 31;
/* 108 */     int result = 1;
/* 109 */     result = 31 * result + ((this.marker == null) ? 0 : this.marker.hashCode());
/* 110 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean hasReferences() {
/* 115 */     return this.marker.hasParents();
/*     */   }
/*     */ 
/*     */   
/*     */   public Iterator<Marker> iterator() {
/* 120 */     Marker[] log4jParents = this.marker.getParents();
/* 121 */     List<Marker> parents = new ArrayList<>(log4jParents.length);
/* 122 */     for (Marker m : log4jParents) {
/* 123 */       parents.add(this.factory.getMarker(m.getName()));
/*     */     }
/* 125 */     return parents.iterator();
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean remove(Marker marker) {
/* 130 */     return (marker != null) ? this.marker.remove(MarkerManager.getMarker(marker.getName())) : false;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\slf4j\Log4jMarker.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */