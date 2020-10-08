/*   */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*   */ 
/*   */ import java.io.File;
/*   */ import java.io.FilenameFilter;
/*   */ 
/*   */ public class DatFileFilter
/*   */   implements FilenameFilter {
/*   */   public boolean accept(File dir, String name) {
/* 9 */     return name.endsWith(".dat");
/*   */   }
/*   */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\DatFileFilter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */