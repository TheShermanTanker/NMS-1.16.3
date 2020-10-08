/*    */ package co.aikar.timings;
/*    */ 
/*    */ import com.google.common.collect.Lists;
/*    */ import java.util.List;
/*    */ import org.apache.commons.lang.Validate;
/*    */ import org.bukkit.Bukkit;
/*    */ import org.bukkit.command.CommandSender;
/*    */ import org.bukkit.command.MessageCommandSender;
/*    */ import org.jetbrains.annotations.NotNull;
/*    */ import org.jetbrains.annotations.Nullable;
/*    */ 
/*    */ 
/*    */ 
/*    */ public class TimingsReportListener
/*    */   implements MessageCommandSender
/*    */ {
/*    */   private final List<CommandSender> senders;
/*    */   private final Runnable onDone;
/*    */   private String timingsURL;
/*    */   
/*    */   public TimingsReportListener(@NotNull CommandSender senders) {
/* 22 */     this(senders, (Runnable)null);
/*    */   }
/*    */   public TimingsReportListener(@NotNull CommandSender sender, @Nullable Runnable onDone) {
/* 25 */     this(Lists.newArrayList((Object[])new CommandSender[] { sender }, ), onDone);
/*    */   }
/*    */   public TimingsReportListener(@NotNull List<CommandSender> senders) {
/* 28 */     this(senders, (Runnable)null);
/*    */   }
/*    */   public TimingsReportListener(@NotNull List<CommandSender> senders, @Nullable Runnable onDone) {
/* 31 */     Validate.notNull(senders);
/* 32 */     Validate.notEmpty(senders);
/*    */     
/* 34 */     this.senders = Lists.newArrayList(senders);
/* 35 */     this.onDone = onDone;
/*    */   }
/*    */   
/*    */   @Nullable
/*    */   public String getTimingsURL() {
/* 40 */     return this.timingsURL;
/*    */   }
/*    */   
/*    */   public void done() {
/* 44 */     done(null);
/*    */   }
/*    */   
/*    */   public void done(@Nullable String url) {
/* 48 */     this.timingsURL = url;
/* 49 */     if (this.onDone != null) {
/* 50 */       this.onDone.run();
/*    */     }
/* 52 */     for (CommandSender sender : this.senders) {
/* 53 */       if (sender instanceof TimingsReportListener) {
/* 54 */         ((TimingsReportListener)sender).done();
/*    */       }
/*    */     } 
/*    */   }
/*    */ 
/*    */   
/*    */   public void sendMessage(@NotNull String message) {
/* 61 */     this.senders.forEach(sender -> sender.sendMessage(message));
/*    */   }
/*    */   
/*    */   public void addConsoleIfNeeded() {
/* 65 */     boolean hasConsole = false;
/* 66 */     for (CommandSender sender : this.senders) {
/* 67 */       if (sender instanceof org.bukkit.command.ConsoleCommandSender || sender instanceof org.bukkit.command.RemoteConsoleCommandSender) {
/* 68 */         hasConsole = true;
/*    */       }
/*    */     } 
/* 71 */     if (!hasConsole)
/* 72 */       this.senders.add(Bukkit.getConsoleSender()); 
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\co\aikar\timings\TimingsReportListener.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */