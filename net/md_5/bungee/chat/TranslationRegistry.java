/*     */ package net.md_5.bungee.chat;
/*     */ 
/*     */ import com.google.common.base.Charsets;
/*     */ import com.google.gson.Gson;
/*     */ import com.google.gson.JsonElement;
/*     */ import com.google.gson.JsonObject;
/*     */ import java.io.IOException;
/*     */ import java.io.InputStreamReader;
/*     */ import java.util.HashMap;
/*     */ import java.util.LinkedList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.ResourceBundle;
/*     */ 
/*     */ 
/*     */ public final class TranslationRegistry
/*     */ {
/*     */   public boolean equals(Object o) {
/*  19 */     if (o == this) return true;  if (!(o instanceof TranslationRegistry)) return false;  TranslationRegistry other = (TranslationRegistry)o; Object<TranslationProvider> this$providers = (Object<TranslationProvider>)getProviders(), other$providers = (Object<TranslationProvider>)other.getProviders(); return !((this$providers == null) ? (other$providers != null) : !this$providers.equals(other$providers)); } public int hashCode() { int PRIME = 59; result = 1; Object<TranslationProvider> $providers = (Object<TranslationProvider>)getProviders(); return result * 59 + (($providers == null) ? 43 : $providers.hashCode()); } public String toString() { return "TranslationRegistry(providers=" + getProviders() + ")"; }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private TranslationRegistry()
/*     */   {
/*  26 */     this.providers = new LinkedList<>(); } public static final TranslationRegistry INSTANCE = new TranslationRegistry(); public List<TranslationProvider> getProviders() { return this.providers; }
/*     */   
/*     */   private final List<TranslationProvider> providers;
/*     */   
/*     */   static {
/*     */     try {
/*  32 */       INSTANCE.addProvider(new JsonProvider("/assets/minecraft/lang/en_us.json"));
/*  33 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  39 */       INSTANCE.addProvider(new JsonProvider("/mojang-translations/en_us.json"));
/*  40 */     } catch (Exception exception) {}
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     try {
/*  46 */       INSTANCE.addProvider(new ResourceBundleProvider("mojang-translations/en_US"));
/*  47 */     } catch (Exception exception) {}
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private void addProvider(TranslationProvider provider) {
/*  54 */     this.providers.add(provider);
/*     */   }
/*     */ 
/*     */   
/*     */   public String translate(String s) {
/*  59 */     for (TranslationProvider provider : this.providers) {
/*     */       
/*  61 */       String translation = provider.translate(s);
/*     */       
/*  63 */       if (translation != null)
/*     */       {
/*  65 */         return translation;
/*     */       }
/*     */     } 
/*     */     
/*  69 */     return s;
/*     */   }
/*     */   
/*     */   private static interface TranslationProvider {
/*     */     String translate(String param1String); }
/*     */   
/*     */   private static class ResourceBundleProvider implements TranslationProvider {
/*     */     private final ResourceBundle bundle;
/*     */     
/*  78 */     public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof ResourceBundleProvider)) return false;  ResourceBundleProvider other = (ResourceBundleProvider)o; if (!other.canEqual(this)) return false;  Object this$bundle = getBundle(), other$bundle = other.getBundle(); return !((this$bundle == null) ? (other$bundle != null) : !this$bundle.equals(other$bundle)); } protected boolean canEqual(Object other) { return other instanceof ResourceBundleProvider; } public int hashCode() { int PRIME = 59; result = 1; Object $bundle = getBundle(); return result * 59 + (($bundle == null) ? 43 : $bundle.hashCode()); } public String toString() { return "TranslationRegistry.ResourceBundleProvider(bundle=" + getBundle() + ")"; }
/*     */ 
/*     */     
/*     */     public ResourceBundle getBundle() {
/*  82 */       return this.bundle;
/*     */     }
/*     */     
/*     */     public ResourceBundleProvider(String bundlePath) {
/*  86 */       this.bundle = ResourceBundle.getBundle(bundlePath);
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String translate(String s) {
/*  92 */       return this.bundle.containsKey(s) ? this.bundle.getString(s) : null;
/*     */     }
/*     */   }
/*     */   
/*  96 */   private static class JsonProvider implements TranslationProvider { public boolean equals(Object o) { if (o == this) return true;  if (!(o instanceof JsonProvider)) return false;  JsonProvider other = (JsonProvider)o; if (!other.canEqual(this)) return false;  Object<String, String> this$translations = (Object<String, String>)getTranslations(), other$translations = (Object<String, String>)other.getTranslations(); return !((this$translations == null) ? (other$translations != null) : !this$translations.equals(other$translations)); } protected boolean canEqual(Object other) { return other instanceof JsonProvider; } public int hashCode() { int PRIME = 59; result = 1; Object<String, String> $translations = (Object<String, String>)getTranslations(); return result * 59 + (($translations == null) ? 43 : $translations.hashCode()); } public String toString() {
/*  97 */       return "TranslationRegistry.JsonProvider()";
/*     */     }
/*     */ 
/*     */     
/* 101 */     private final Map<String, String> translations = new HashMap<>(); public Map<String, String> getTranslations() { return this.translations; }
/*     */ 
/*     */     
/*     */     public JsonProvider(String resourcePath) throws IOException {
/* 105 */       try (InputStreamReader rd = new InputStreamReader(JsonProvider.class.getResourceAsStream(resourcePath), Charsets.UTF_8)) {
/*     */         
/* 107 */         JsonObject obj = (JsonObject)(new Gson()).fromJson(rd, JsonObject.class);
/* 108 */         for (Map.Entry<String, JsonElement> entries : (Iterable<Map.Entry<String, JsonElement>>)obj.entrySet())
/*     */         {
/* 110 */           this.translations.put(entries.getKey(), ((JsonElement)entries.getValue()).getAsString());
/*     */         }
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public String translate(String s) {
/* 118 */       return this.translations.get(s);
/*     */     } }
/*     */ 
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\md_5\bungee\chat\TranslationRegistry.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */