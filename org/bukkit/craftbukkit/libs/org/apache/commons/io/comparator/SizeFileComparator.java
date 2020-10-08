/*     */ package org.bukkit.craftbukkit.libs.org.apache.commons.io.comparator;
/*     */ 
/*     */ import java.io.File;
/*     */ import java.io.Serializable;
/*     */ import java.util.Comparator;
/*     */ import java.util.List;
/*     */ import org.bukkit.craftbukkit.libs.org.apache.commons.io.FileUtils;
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
/*     */ public class SizeFileComparator
/*     */   extends AbstractFileComparator
/*     */   implements Serializable
/*     */ {
/*     */   private static final long serialVersionUID = -1201561106411416190L;
/*  57 */   public static final Comparator<File> SIZE_COMPARATOR = new SizeFileComparator();
/*     */ 
/*     */   
/*  60 */   public static final Comparator<File> SIZE_REVERSE = new ReverseComparator(SIZE_COMPARATOR);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  66 */   public static final Comparator<File> SIZE_SUMDIR_COMPARATOR = new SizeFileComparator(true);
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*  72 */   public static final Comparator<File> SIZE_SUMDIR_REVERSE = new ReverseComparator(SIZE_SUMDIR_COMPARATOR);
/*     */ 
/*     */ 
/*     */   
/*     */   private final boolean sumDirectoryContents;
/*     */ 
/*     */ 
/*     */   
/*     */   public SizeFileComparator() {
/*  81 */     this.sumDirectoryContents = false;
/*     */   }
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
/*     */   public SizeFileComparator(boolean sumDirectoryContents) {
/*  96 */     this.sumDirectoryContents = sumDirectoryContents;
/*     */   }
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
/*     */   public int compare(File file1, File file2) {
/* 111 */     long size1 = 0L;
/* 112 */     if (file1.isDirectory()) {
/* 113 */       size1 = (this.sumDirectoryContents && file1.exists()) ? FileUtils.sizeOfDirectory(file1) : 0L;
/*     */     } else {
/* 115 */       size1 = file1.length();
/*     */     } 
/* 117 */     long size2 = 0L;
/* 118 */     if (file2.isDirectory()) {
/* 119 */       size2 = (this.sumDirectoryContents && file2.exists()) ? FileUtils.sizeOfDirectory(file2) : 0L;
/*     */     } else {
/* 121 */       size2 = file2.length();
/*     */     } 
/* 123 */     long result = size1 - size2;
/* 124 */     if (result < 0L)
/* 125 */       return -1; 
/* 126 */     if (result > 0L) {
/* 127 */       return 1;
/*     */     }
/* 129 */     return 0;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public String toString() {
/* 140 */     return super.toString() + "[sumDirectoryContents=" + this.sumDirectoryContents + "]";
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\libs\org\apache\commons\io\comparator\SizeFileComparator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */