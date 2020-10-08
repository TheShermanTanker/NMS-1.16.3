/*    */ package org.bukkit.plugin;
/*    */ 
/*    */ import java.io.File;
/*    */ import java.util.Map;
/*    */ import java.util.Set;
/*    */ import java.util.regex.Pattern;
/*    */ import org.bukkit.event.Event;
/*    */ import org.bukkit.event.Listener;
/*    */ import org.jetbrains.annotations.NotNull;
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
/*    */ public interface PluginLoader
/*    */ {
/*    */   @NotNull
/*    */   Plugin loadPlugin(@NotNull File paramFile) throws InvalidPluginException, UnknownDependencyException;
/*    */   
/*    */   @NotNull
/*    */   PluginDescriptionFile getPluginDescription(@NotNull File paramFile) throws InvalidDescriptionException;
/*    */   
/*    */   @NotNull
/*    */   Pattern[] getPluginFileFilters();
/*    */   
/*    */   @NotNull
/*    */   Map<Class<? extends Event>, Set<RegisteredListener>> createRegisteredListeners(@NotNull Listener paramListener, @NotNull Plugin paramPlugin);
/*    */   
/*    */   void enablePlugin(@NotNull Plugin paramPlugin);
/*    */   
/*    */   void disablePlugin(@NotNull Plugin paramPlugin);
/*    */   
/*    */   default void disablePlugin(@NotNull Plugin plugin, boolean closeClassloader) {
/* 91 */     disablePlugin(plugin);
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\PluginLoader.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */