/*    */ package org.apache.logging.log4j.core.lookup;
/*    */ 
/*    */ import java.util.Map;
/*    */ import org.apache.logging.log4j.core.LogEvent;
/*    */ import org.apache.logging.log4j.core.config.plugins.Plugin;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Plugin(name = "main", category = "Lookup")
/*    */ public class MainMapLookup
/*    */   extends MapLookup
/*    */ {
/* 37 */   static final MapLookup MAIN_SINGLETON = new MapLookup(MapLookup.newMap(0));
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public MainMapLookup() {}
/*    */ 
/*    */ 
/*    */   
/*    */   public MainMapLookup(Map<String, String> map) {
/* 47 */     super(map);
/*    */   }
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */   
/*    */   public static void setMainArguments(String... args) {
/* 75 */     if (args == null) {
/*    */       return;
/*    */     }
/* 78 */     initMap(args, MAIN_SINGLETON.getMap());
/*    */   }
/*    */ 
/*    */   
/*    */   public String lookup(LogEvent event, String key) {
/* 83 */     return MAIN_SINGLETON.getMap().get(key);
/*    */   }
/*    */ 
/*    */   
/*    */   public String lookup(String key) {
/* 88 */     return MAIN_SINGLETON.getMap().get(key);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\apache\logging\log4j\core\lookup\MainMapLookup.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */