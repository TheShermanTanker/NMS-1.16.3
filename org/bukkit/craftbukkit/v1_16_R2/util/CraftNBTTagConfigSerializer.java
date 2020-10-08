/*    */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*    */ 
/*    */ import com.mojang.brigadier.StringReader;
/*    */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*    */ import java.util.ArrayList;
/*    */ import java.util.HashMap;
/*    */ import java.util.List;
/*    */ import java.util.Map;
/*    */ import java.util.regex.Pattern;
/*    */ import net.minecraft.server.v1_16_R2.MojangsonParser;
/*    */ import net.minecraft.server.v1_16_R2.NBTBase;
/*    */ import net.minecraft.server.v1_16_R2.NBTList;
/*    */ import net.minecraft.server.v1_16_R2.NBTTagCompound;
/*    */ import net.minecraft.server.v1_16_R2.NBTTagDouble;
/*    */ import net.minecraft.server.v1_16_R2.NBTTagInt;
/*    */ import net.minecraft.server.v1_16_R2.NBTTagList;
/*    */ import net.minecraft.server.v1_16_R2.NBTTagString;
/*    */ 
/*    */ public class CraftNBTTagConfigSerializer
/*    */ {
/* 21 */   private static final Pattern ARRAY = Pattern.compile("^\\[.*]");
/* 22 */   private static final Pattern INTEGER = Pattern.compile("[-+]?(?:0|[1-9][0-9]*)i", 2);
/* 23 */   private static final Pattern DOUBLE = Pattern.compile("[-+]?(?:[0-9]+[.]?|[0-9]*[.][0-9]+)(?:e[-+]?[0-9]+)?d", 2);
/* 24 */   private static final MojangsonParser MOJANGSON_PARSER = new MojangsonParser(new StringReader(""));
/*    */   
/*    */   public static Object serialize(NBTBase base) {
/* 27 */     if (base instanceof NBTTagCompound) {
/* 28 */       Map<String, Object> innerMap = new HashMap<>();
/* 29 */       for (String key : ((NBTTagCompound)base).getKeys()) {
/* 30 */         innerMap.put(key, serialize(((NBTTagCompound)base).get(key)));
/*    */       }
/*    */       
/* 33 */       return innerMap;
/* 34 */     }  if (base instanceof NBTTagList) {
/* 35 */       List<Object> baseList = new ArrayList();
/* 36 */       for (int i = 0; i < ((NBTList)base).size(); i++) {
/* 37 */         baseList.add(serialize((NBTBase)((NBTList)base).get(i)));
/*    */       }
/*    */       
/* 40 */       return baseList;
/* 41 */     }  if (base instanceof NBTTagString)
/* 42 */       return base.asString(); 
/* 43 */     if (base instanceof NBTTagInt) {
/* 44 */       return base.toString() + "i";
/*    */     }
/*    */     
/* 47 */     return base.toString();
/*    */   }
/*    */   
/*    */   public static NBTBase deserialize(Object object) {
/* 51 */     if (object instanceof Map) {
/* 52 */       NBTTagCompound compound = new NBTTagCompound();
/* 53 */       for (Map.Entry<String, Object> entry : (Iterable<Map.Entry<String, Object>>)((Map)object).entrySet()) {
/* 54 */         compound.set(entry.getKey(), deserialize(entry.getValue()));
/*    */       }
/*    */       
/* 57 */       return (NBTBase)compound;
/* 58 */     }  if (object instanceof List) {
/* 59 */       List<Object> list = (List<Object>)object;
/* 60 */       if (list.isEmpty()) {
/* 61 */         return (NBTBase)new NBTTagList();
/*    */       }
/*    */       
/* 64 */       NBTTagList tagList = new NBTTagList();
/* 65 */       for (Object tag : list) {
/* 66 */         tagList.add(deserialize(tag));
/*    */       }
/*    */       
/* 69 */       return (NBTBase)tagList;
/* 70 */     }  if (object instanceof String) {
/* 71 */       String string = (String)object;
/*    */       
/* 73 */       if (ARRAY.matcher(string).matches())
/*    */         try {
/* 75 */           return (new MojangsonParser(new StringReader(string))).parseArray();
/* 76 */         } catch (CommandSyntaxException e) {
/* 77 */           throw new RuntimeException("Could not deserialize found list ", e);
/*    */         }  
/* 79 */       if (INTEGER.matcher(string).matches())
/* 80 */         return (NBTBase)NBTTagInt.a(Integer.parseInt(string.substring(0, string.length() - 1))); 
/* 81 */       if (DOUBLE.matcher(string).matches()) {
/* 82 */         return (NBTBase)NBTTagDouble.a(Double.parseDouble(string.substring(0, string.length() - 1)));
/*    */       }
/* 84 */       NBTBase nbtBase = MOJANGSON_PARSER.parseLiteral(string);
/*    */       
/* 86 */       if (nbtBase instanceof NBTTagInt)
/* 87 */         return (NBTBase)NBTTagString.a(nbtBase.asString()); 
/* 88 */       if (nbtBase instanceof NBTTagDouble) {
/* 89 */         return (NBTBase)NBTTagString.a(String.valueOf(((NBTTagDouble)nbtBase).asDouble()));
/*    */       }
/* 91 */       return nbtBase;
/*    */     } 
/*    */ 
/*    */ 
/*    */     
/* 96 */     throw new RuntimeException("Could not deserialize NBTBase");
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\CraftNBTTagConfigSerializer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */