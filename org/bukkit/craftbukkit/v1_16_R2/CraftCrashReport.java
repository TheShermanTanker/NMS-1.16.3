/*    */ package org.bukkit.craftbukkit.v1_16_R2;
/*    */ 
/*    */ import java.io.PrintWriter;
/*    */ import java.io.StringWriter;
/*    */ import java.util.Arrays;
/*    */ import java.util.Collection;
/*    */ import java.util.Map;
/*    */ import net.minecraft.server.v1_16_R2.CrashReportCallable;
/*    */ import net.minecraft.server.v1_16_R2.MinecraftServer;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.Chunk;
/*    */ import org.bukkit.World;
/*    */ import org.bukkit.craftbukkit.v1_16_R2.util.CraftMagicNumbers;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ import org.bukkit.plugin.PluginDescriptionFile;
/*    */ 
/*    */ public class CraftCrashReport
/*    */   implements CrashReportCallable<Object>
/*    */ {
/*    */   public Object call() throws Exception {
/* 21 */     StringWriter value = new StringWriter();
/*    */     try {
/* 23 */       value.append("\n   Running: ").append(Bukkit.getName()).append(" version ").append(Bukkit.getVersion()).append(" (Implementing API version ").append(Bukkit.getBukkitVersion()).append(") ").append(String.valueOf(MinecraftServer.getServer().getOnlineMode()));
/* 24 */       value.append("\n   Plugins: {");
/* 25 */       for (Plugin plugin : Bukkit.getPluginManager().getPlugins()) {
/* 26 */         PluginDescriptionFile description = plugin.getDescription();
/* 27 */         boolean legacy = CraftMagicNumbers.isLegacy(description);
/* 28 */         value.append(' ').append(description.getFullName()).append(legacy ? "*" : "").append(' ').append(description.getMain()).append(' ').append(Arrays.toString(description.getAuthors().toArray())).append(',');
/*    */       } 
/* 30 */       value.append("}\n   Warnings: ").append(Bukkit.getWarningState().name());
/* 31 */       value.append("\n   Reload Count: ").append(String.valueOf((MinecraftServer.getServer()).server.reloadCount));
/* 32 */       value.append("\n   Threads: {");
/* 33 */       for (Map.Entry<Thread, ? extends Object[]> entry : Thread.getAllStackTraces().entrySet()) {
/* 34 */         value.append(' ').append(((Thread)entry.getKey()).getState().name()).append(' ').append(((Thread)entry.getKey()).getName()).append(": ").append(Arrays.toString(entry.getValue())).append(',');
/*    */       }
/* 36 */       value.append("}\n   ").append(Bukkit.getScheduler().toString());
/* 37 */       value.append("\n   Force Loaded Chunks: {");
/* 38 */       for (World world : Bukkit.getWorlds()) {
/* 39 */         value.append(' ').append(world.getName()).append(": {");
/* 40 */         for (Map.Entry<Plugin, Collection<Chunk>> entry : (Iterable<Map.Entry<Plugin, Collection<Chunk>>>)world.getPluginChunkTickets().entrySet()) {
/* 41 */           value.append(' ').append(((Plugin)entry.getKey()).getDescription().getFullName()).append(": ").append(Integer.toString(((Collection)entry.getValue()).size())).append(',');
/*    */         }
/* 43 */         value.append("},");
/*    */       } 
/* 45 */       value.append("}");
/* 46 */     } catch (Throwable t) {
/* 47 */       value.append("\n   Failed to handle CraftCrashReport:\n");
/* 48 */       PrintWriter writer = new PrintWriter(value);
/* 49 */       t.printStackTrace(writer);
/* 50 */       writer.flush();
/*    */     } 
/* 52 */     return value.toString();
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\CraftCrashReport.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */