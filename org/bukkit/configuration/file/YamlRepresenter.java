/*    */ package org.bukkit.configuration.file;
/*    */ 
/*    */ import java.util.LinkedHashMap;
/*    */ import java.util.Map;
/*    */ import org.bukkit.configuration.ConfigurationSection;
/*    */ import org.bukkit.configuration.serialization.ConfigurationSerializable;
/*    */ import org.bukkit.configuration.serialization.ConfigurationSerialization;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.yaml.snakeyaml.nodes.Node;
/*    */ import org.yaml.snakeyaml.representer.Representer;
/*    */ import org.yaml.snakeyaml.representer.SafeRepresenter;
/*    */ 
/*    */ public class YamlRepresenter extends Representer {
/*    */   public YamlRepresenter() {
/* 15 */     this.multiRepresenters.put(ConfigurationSection.class, new RepresentConfigurationSection());
/* 16 */     this.multiRepresenters.put(ConfigurationSerializable.class, new RepresentConfigurationSerializable());
/*    */   }
/*    */   private class RepresentConfigurationSection extends SafeRepresenter.RepresentMap { private RepresentConfigurationSection() {
/* 19 */       super((SafeRepresenter)YamlRepresenter.this);
/*    */     }
/*    */     
/*    */     @NotNull
/*    */     public Node representData(@NotNull Object data) {
/* 24 */       return super.representData(((ConfigurationSection)data).getValues(false));
/*    */     } }
/*    */   
/*    */   private class RepresentConfigurationSerializable extends SafeRepresenter.RepresentMap { private RepresentConfigurationSerializable() {
/* 28 */       super((SafeRepresenter)YamlRepresenter.this);
/*    */     }
/*    */     
/*    */     @NotNull
/*    */     public Node representData(@NotNull Object data) {
/* 33 */       ConfigurationSerializable serializable = (ConfigurationSerializable)data;
/* 34 */       Map<String, Object> values = new LinkedHashMap<>();
/* 35 */       values.put("==", ConfigurationSerialization.getAlias(serializable.getClass()));
/* 36 */       values.putAll(serializable.serialize());
/*    */       
/* 38 */       return super.representData(values);
/*    */     } }
/*    */ 
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\configuration\file\YamlRepresenter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */