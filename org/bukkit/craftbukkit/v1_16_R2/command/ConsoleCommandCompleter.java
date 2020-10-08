/*     */ package org.bukkit.craftbukkit.v1_16_R2.command;
/*     */ import com.destroystokyo.paper.event.server.AsyncTabCompleteEvent;
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Collections;
/*     */ import java.util.List;
/*     */ import java.util.concurrent.ExecutionException;
/*     */ import java.util.function.Function;
/*     */ import java.util.logging.Level;
/*     */ import net.minecraft.server.v1_16_R2.DedicatedServer;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.CraftServer;
/*     */ import org.bukkit.craftbukkit.v1_16_R2.util.Waitable;
/*     */ import org.bukkit.event.Event;
/*     */ import org.bukkit.event.server.TabCompleteEvent;
/*     */ import org.jline.reader.Candidate;
/*     */ import org.jline.reader.Completer;
/*     */ import org.jline.reader.LineReader;
/*     */ import org.jline.reader.ParsedLine;
/*     */ 
/*     */ public class ConsoleCommandCompleter implements Completer {
/*     */   public ConsoleCommandCompleter(DedicatedServer server) {
/*  23 */     this.server = server;
/*     */   }
/*     */   
/*     */   private final DedicatedServer server;
/*     */   
/*     */   public void complete(LineReader reader, ParsedLine line, List<Candidate> candidates) {
/*  29 */     final CraftServer server = this.server.server;
/*  30 */     final String buffer = line.line();
/*     */ 
/*     */     
/*  33 */     List<String> completions = new ArrayList<>();
/*  34 */     AsyncTabCompleteEvent event = new AsyncTabCompleteEvent((CommandSender)server.getConsoleSender(), completions, buffer, true, null);
/*     */     
/*  36 */     event.callEvent();
/*  37 */     completions = event.isCancelled() ? (List<String>)ImmutableList.of() : event.getCompletions();
/*     */     
/*  39 */     if (event.isCancelled() || event.isHandled()) {
/*     */       
/*  41 */       if (!event.isCancelled() && (TabCompleteEvent.getHandlerList().getRegisteredListeners()).length > 0) {
/*  42 */         final List<String> finalCompletions = completions;
/*  43 */         Waitable<List<String>> syncCompletions = new Waitable<List<String>>()
/*     */           {
/*     */             protected List<String> evaluate() {
/*  46 */               TabCompleteEvent syncEvent = new TabCompleteEvent((CommandSender)server.getConsoleSender(), buffer, finalCompletions);
/*  47 */               return syncEvent.callEvent() ? syncEvent.getCompletions() : (List<String>)ImmutableList.of();
/*     */             }
/*     */           };
/*  50 */         (server.getServer()).processQueue.add(syncCompletions);
/*     */         try {
/*  52 */           completions = (List<String>)syncCompletions.get();
/*  53 */         } catch (InterruptedException|ExecutionException e1) {
/*  54 */           e1.printStackTrace();
/*     */         } 
/*     */       } 
/*     */       
/*  58 */       if (!completions.isEmpty()) {
/*  59 */         candidates.addAll((Collection<? extends Candidate>)completions.stream().map(Candidate::new).collect(Collectors.toList()));
/*     */       }
/*     */       
/*     */       return;
/*     */     } 
/*     */     
/*  65 */     Waitable<List<String>> waitable = new Waitable<List<String>>()
/*     */       {
/*     */         protected List<String> evaluate() {
/*  68 */           List<String> offers = server.getCommandMap().tabComplete((CommandSender)server.getConsoleSender(), buffer);
/*     */           
/*  70 */           TabCompleteEvent tabEvent = new TabCompleteEvent((CommandSender)server.getConsoleSender(), buffer, (offers == null) ? Collections.EMPTY_LIST : offers);
/*  71 */           server.getPluginManager().callEvent((Event)tabEvent);
/*     */           
/*  73 */           return tabEvent.isCancelled() ? Collections.EMPTY_LIST : tabEvent.getCompletions();
/*     */         }
/*     */       };
/*  76 */     (server.getServer()).processQueue.add(waitable);
/*     */     try {
/*  78 */       List<String> offers = (List<String>)waitable.get();
/*  79 */       if (offers == null) {
/*     */         return;
/*     */       }
/*     */ 
/*     */       
/*  84 */       for (String completion : offers) {
/*  85 */         if (completion.isEmpty()) {
/*     */           continue;
/*     */         }
/*     */         
/*  89 */         candidates.add(new Candidate(completion));
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */       
/*     */       }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     }
/* 103 */     catch (ExecutionException e) {
/* 104 */       server.getLogger().log(Level.WARNING, "Unhandled exception when tab completing", e);
/* 105 */     } catch (InterruptedException e) {
/* 106 */       Thread.currentThread().interrupt();
/*     */     } 
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R2\command\ConsoleCommandCompleter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */