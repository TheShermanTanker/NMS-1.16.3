/*    */ package org.bukkit.configuration.file;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.configuration.serialization.ConfigurationSerialization;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ import org.yaml.snakeyaml.constructor.SafeConstructor;
/*    */ import org.yaml.snakeyaml.error.YAMLException;
/*    */ import org.yaml.snakeyaml.nodes.Node;
/*    */ import org.yaml.snakeyaml.nodes.Tag;
/*    */ 
/*    */ public class YamlConstructor
/*    */   extends SafeConstructor {
/*    */   public YamlConstructor() {
/* 16 */     this.yamlConstructors.put(Tag.MAP, new ConstructCustomObject());
/*    */   }
/*    */   private class ConstructCustomObject extends SafeConstructor.ConstructYamlMap { private ConstructCustomObject() {
/* 19 */       super(YamlConstructor.this);
/*    */     }
/*    */     
/*    */     @Nullable
/*    */     public Object construct(@NotNull Node node) {
/* 24 */       if (node.isTwoStepsConstruction()) {
/* 25 */         throw new YAMLException("Unexpected referential mapping structure. Node: " + node);
/*    */       }
/*    */       
/* 28 */       Map<?, ?> raw = (Map<?, ?>)super.construct(node);
/*    */       
/* 30 */       if (raw.containsKey("==")) {
/* 31 */         Map<String, Object> typed = new LinkedHashMap<>(raw.size());
/* 32 */         for (Map.Entry<?, ?> entry : raw.entrySet()) {
/* 33 */           typed.put(entry.getKey().toString(), entry.getValue());
/*    */         }
/*    */         
/*    */         try {
/* 37 */           return ConfigurationSerialization.deserializeObject(typed);
/* 38 */         } catch (IllegalArgumentException ex) {
/* 39 */           throw new YAMLException("Could not deserialize object", ex);
/*    */         } 
/*    */       } 
/*    */       
/* 43 */       return raw;
/*    */     }
/*    */ 
/*    */     
/*    */     public void construct2ndStep(@NotNull Node node, @NotNull Object object) {
/* 48 */       throw new YAMLException("Unexpected referential mapping structure. Node: " + node);
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\file\YamlConstructor.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */