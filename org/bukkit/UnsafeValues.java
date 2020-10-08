/*    */ package org.bukkit;
/*    */ 
/*    */ import com.destroystokyo.paper.util.VersionFetcher;
/*    */ import org.bukkit.advancement.Advancement;
/*    */ import org.bukkit.block.Block;
/*    */ import org.bukkit.block.data.BlockData;
/*    */ import org.bukkit.entity.EntityType;
/*    */ import org.bukkit.inventory.ItemStack;
/*    */ import org.bukkit.material.MaterialData;
/*    */ import org.bukkit.plugin.InvalidPluginException;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginDescriptionFile;
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
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ 
/*    */ @Deprecated
/*    */ public interface UnsafeValues
/*    */ {
/*    */   void reportTimings();
/*    */   
/*    */   Material toLegacy(Material paramMaterial);
/*    */   
/*    */   Material fromLegacy(Material paramMaterial);
/*    */   
/*    */   Material fromLegacy(MaterialData paramMaterialData);
/*    */   
/*    */   Material fromLegacy(MaterialData paramMaterialData, boolean paramBoolean);
/*    */   
/*    */   BlockData fromLegacy(Material paramMaterial, byte paramByte);
/*    */   
/*    */   Material getMaterial(String paramString, int paramInt);
/*    */   
/*    */   int getDataVersion();
/*    */   
/*    */   ItemStack modifyItemStack(ItemStack paramItemStack, String paramString);
/*    */   
/*    */   void checkSupported(PluginDescriptionFile paramPluginDescriptionFile) throws InvalidPluginException;
/*    */   
/*    */   byte[] processClass(PluginDescriptionFile paramPluginDescriptionFile, String paramString, byte[] paramArrayOfbyte);
/*    */   
/*    */   Advancement loadAdvancement(NamespacedKey paramNamespacedKey, String paramString);
/*    */   
/*    */   boolean removeAdvancement(NamespacedKey paramNamespacedKey);
/*    */   
/*    */   String getTimingsServerName();
/*    */   
/*    */   default VersionFetcher getVersionFetcher() {
/* 85 */     return (VersionFetcher)new VersionFetcher.DummyVersionFetcher();
/*    */   }
/*    */   
/*    */   boolean isSupportedApiVersion(String paramString);
/*    */   
/*    */   static boolean isLegacyPlugin(Plugin plugin) {
/* 91 */     return !Bukkit.getUnsafe().isSupportedApiVersion(plugin.getDescription().getAPIVersion());
/*    */   }
/*    */   
/*    */   byte[] serializeItem(ItemStack paramItemStack);
/*    */   
/*    */   ItemStack deserializeItem(byte[] paramArrayOfbyte);
/*    */   
/*    */   String getTranslationKey(Material paramMaterial);
/*    */   
/*    */   String getTranslationKey(Block paramBlock);
/*    */   
/*    */   String getTranslationKey(EntityType paramEntityType);
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\UnsafeValues.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */