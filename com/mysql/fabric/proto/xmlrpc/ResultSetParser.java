/*    */ package com.mysql.fabric.proto.xmlrpc;
/*    */ 
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ public class ResultSetParser
/*    */ {
/*    */   public List<Map<String, ?>> parse(Map<String, ?> info, List<List<Object>> rows) {
/* 44 */     List<String> fieldNames = (List<String>)info.get("names");
/* 45 */     Map<String, Integer> fieldNameIndexes = new HashMap<String, Integer>();
/* 46 */     for (int i = 0; i < fieldNames.size(); i++) {
/* 47 */       fieldNameIndexes.put(fieldNames.get(i), Integer.valueOf(i));
/*    */     }
/*    */     
/* 50 */     List<Map<String, ?>> result = new ArrayList<Map<String, ?>>(rows.size());
/* 51 */     for (List<Object> r : rows) {
/* 52 */       Map<String, Object> resultRow = new HashMap<String, Object>();
/* 53 */       for (Map.Entry<String, Integer> f : fieldNameIndexes.entrySet()) {
/* 54 */         resultRow.put(f.getKey(), r.get(((Integer)f.getValue()).intValue()));
/*    */       }
/* 56 */       result.add(resultRow);
/*    */     } 
/* 58 */     return result;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\mysql\fabric\proto\xmlrpc\ResultSetParser.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */