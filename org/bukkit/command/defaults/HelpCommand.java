/*     */ package org.bukkit.command.defaults;
/*     */ 
/*     */ import com.google.common.collect.ImmutableList;
/*     */ import java.util.ArrayList;
/*     */ import java.util.Arrays;
/*     */ import java.util.Comparator;
/*     */ import java.util.HashMap;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.TreeSet;
/*     */ import org.apache.commons.lang.ArrayUtils;
/*     */ import org.apache.commons.lang.StringUtils;
/*     */ import org.apache.commons.lang.Validate;
/*     */ import org.apache.commons.lang.math.NumberUtils;
/*     */ import org.bukkit.Bukkit;
/*     */ import org.bukkit.ChatColor;
/*     */ import org.bukkit.command.CommandSender;
/*     */ import org.bukkit.help.HelpMap;
/*     */ import org.bukkit.help.HelpTopic;
/*     */ import org.bukkit.help.HelpTopicComparator;
/*     */ import org.bukkit.help.IndexHelpTopic;
/*     */ import org.bukkit.util.ChatPaginator;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ import org.jetbrains.annotations.Nullable;
/*     */ 
/*     */ public class HelpCommand extends BukkitCommand {
/*     */   public HelpCommand() {
/*  29 */     super("help");
/*  30 */     this.description = "Shows the help menu";
/*  31 */     this.usageMessage = "/help <pageNumber>\n/help <topic>\n/help <topic> <pageNumber>";
/*  32 */     setAliases(Arrays.asList(new String[] { "?" }));
/*  33 */     setPermission("bukkit.command.help");
/*     */   }
/*     */   public boolean execute(@NotNull CommandSender sender, @NotNull String currentAlias, @NotNull String[] args) {
/*     */     String command;
/*     */     int pageNumber, pageHeight, pageWidth;
/*  38 */     if (!testPermission(sender)) return true;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*  45 */     if (args.length == 0) {
/*  46 */       command = "";
/*  47 */       pageNumber = 1;
/*  48 */     } else if (NumberUtils.isDigits(args[args.length - 1])) {
/*  49 */       command = StringUtils.join(ArrayUtils.subarray((Object[])args, 0, args.length - 1), " ");
/*     */       try {
/*  51 */         pageNumber = NumberUtils.createInteger(args[args.length - 1]).intValue();
/*  52 */       } catch (NumberFormatException exception) {
/*  53 */         pageNumber = 1;
/*     */       } 
/*  55 */       if (pageNumber <= 0) {
/*  56 */         pageNumber = 1;
/*     */       }
/*     */     } else {
/*  59 */       command = StringUtils.join((Object[])args, " ");
/*  60 */       pageNumber = 1;
/*     */     } 
/*     */     
/*  63 */     if (sender instanceof org.bukkit.command.ConsoleCommandSender) {
/*  64 */       pageHeight = Integer.MAX_VALUE;
/*  65 */       pageWidth = Integer.MAX_VALUE;
/*     */     } else {
/*  67 */       pageHeight = 9;
/*  68 */       pageWidth = 55;
/*     */     } 
/*     */     
/*  71 */     HelpMap helpMap = Bukkit.getServer().getHelpMap();
/*  72 */     HelpTopic topic = helpMap.getHelpTopic(command);
/*     */     
/*  74 */     if (topic == null) {
/*  75 */       topic = helpMap.getHelpTopic("/" + command);
/*     */     }
/*     */     
/*  78 */     if (topic == null) {
/*  79 */       topic = findPossibleMatches(command);
/*     */     }
/*     */     
/*  82 */     if (topic == null || !topic.canSee(sender)) {
/*  83 */       sender.sendMessage(ChatColor.RED + "No help for " + command);
/*  84 */       return true;
/*     */     } 
/*     */     
/*  87 */     ChatPaginator.ChatPage page = ChatPaginator.paginate(topic.getFullText(sender), pageNumber, pageWidth, pageHeight);
/*     */     
/*  89 */     StringBuilder header = new StringBuilder();
/*  90 */     header.append(ChatColor.YELLOW);
/*  91 */     header.append("--------- ");
/*  92 */     header.append(ChatColor.WHITE);
/*  93 */     header.append("Help: ");
/*  94 */     header.append(topic.getName());
/*  95 */     header.append(" ");
/*  96 */     if (page.getTotalPages() > 1) {
/*  97 */       header.append("(");
/*  98 */       header.append(page.getPageNumber());
/*  99 */       header.append("/");
/* 100 */       header.append(page.getTotalPages());
/* 101 */       header.append(") ");
/*     */     } 
/* 103 */     header.append(ChatColor.YELLOW);
/* 104 */     for (int i = header.length(); i < 55; i++) {
/* 105 */       header.append("-");
/*     */     }
/* 107 */     sender.sendMessage(header.toString());
/*     */     
/* 109 */     sender.sendMessage(page.getLines());
/*     */     
/* 111 */     return true;
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public List<String> tabComplete(@NotNull CommandSender sender, @NotNull String alias, @NotNull String[] args) {
/* 117 */     Validate.notNull(sender, "Sender cannot be null");
/* 118 */     Validate.notNull(args, "Arguments cannot be null");
/* 119 */     Validate.notNull(alias, "Alias cannot be null");
/*     */     
/* 121 */     if (args.length == 1) {
/* 122 */       List<String> matchedTopics = new ArrayList<>();
/* 123 */       String searchString = args[0];
/* 124 */       for (HelpTopic topic : Bukkit.getServer().getHelpMap().getHelpTopics()) {
/* 125 */         String trimmedTopic = topic.getName().startsWith("/") ? topic.getName().substring(1) : topic.getName();
/*     */         
/* 127 */         if (trimmedTopic.startsWith(searchString)) {
/* 128 */           matchedTopics.add(trimmedTopic);
/*     */         }
/*     */       } 
/* 131 */       return matchedTopics;
/*     */     } 
/* 133 */     return (List<String>)ImmutableList.of();
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   protected HelpTopic findPossibleMatches(@NotNull String searchString) {
/* 138 */     int maxDistance = searchString.length() / 5 + 3;
/* 139 */     Set<HelpTopic> possibleMatches = new TreeSet<>((Comparator<? super HelpTopic>)HelpTopicComparator.helpTopicComparatorInstance());
/*     */     
/* 141 */     if (searchString.startsWith("/")) {
/* 142 */       searchString = searchString.substring(1);
/*     */     }
/*     */     
/* 145 */     for (HelpTopic topic : Bukkit.getServer().getHelpMap().getHelpTopics()) {
/* 146 */       String trimmedTopic = topic.getName().startsWith("/") ? topic.getName().substring(1) : topic.getName();
/*     */       
/* 148 */       if (trimmedTopic.length() < searchString.length()) {
/*     */         continue;
/*     */       }
/*     */       
/* 152 */       if (Character.toLowerCase(trimmedTopic.charAt(0)) != Character.toLowerCase(searchString.charAt(0))) {
/*     */         continue;
/*     */       }
/*     */       
/* 156 */       if (damerauLevenshteinDistance(searchString, trimmedTopic.substring(0, searchString.length())) < maxDistance) {
/* 157 */         possibleMatches.add(topic);
/*     */       }
/*     */     } 
/*     */     
/* 161 */     if (possibleMatches.size() > 0) {
/* 162 */       return (HelpTopic)new IndexHelpTopic("Search", null, null, possibleMatches, "Search for: " + searchString);
/*     */     }
/* 164 */     return null;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   protected static int damerauLevenshteinDistance(@Nullable String s1, @Nullable String s2) {
/* 178 */     if (s1 == null && s2 == null) {
/* 179 */       return 0;
/*     */     }
/* 181 */     if (s1 != null && s2 == null) {
/* 182 */       return s1.length();
/*     */     }
/* 184 */     if (s1 == null && s2 != null) {
/* 185 */       return s2.length();
/*     */     }
/*     */     
/* 188 */     int s1Len = s1.length();
/* 189 */     int s2Len = s2.length();
/* 190 */     int[][] H = new int[s1Len + 2][s2Len + 2];
/*     */     
/* 192 */     int INF = s1Len + s2Len;
/* 193 */     H[0][0] = INF;
/* 194 */     for (int i = 0; i <= s1Len; i++) {
/* 195 */       H[i + 1][1] = i;
/* 196 */       H[i + 1][0] = INF;
/*     */     } 
/* 198 */     for (int j = 0; j <= s2Len; j++) {
/* 199 */       H[1][j + 1] = j;
/* 200 */       H[0][j + 1] = INF;
/*     */     } 
/*     */     
/* 203 */     Map<Character, Integer> sd = new HashMap<>();
/* 204 */     for (char Letter : (s1 + s2).toCharArray()) {
/* 205 */       if (!sd.containsKey(Character.valueOf(Letter))) {
/* 206 */         sd.put(Character.valueOf(Letter), Integer.valueOf(0));
/*     */       }
/*     */     } 
/*     */     
/* 210 */     for (int k = 1; k <= s1Len; k++) {
/* 211 */       int DB = 0;
/* 212 */       for (int m = 1; m <= s2Len; m++) {
/* 213 */         int i1 = ((Integer)sd.get(Character.valueOf(s2.charAt(m - 1)))).intValue();
/* 214 */         int j1 = DB;
/*     */         
/* 216 */         if (s1.charAt(k - 1) == s2.charAt(m - 1)) {
/* 217 */           H[k + 1][m + 1] = H[k][m];
/* 218 */           DB = m;
/*     */         } else {
/* 220 */           H[k + 1][m + 1] = Math.min(H[k][m], Math.min(H[k + 1][m], H[k][m + 1])) + 1;
/*     */         } 
/*     */         
/* 223 */         H[k + 1][m + 1] = Math.min(H[k + 1][m + 1], H[i1][j1] + k - i1 - 1 + 1 + m - j1 - 1);
/*     */       } 
/* 225 */       sd.put(Character.valueOf(s1.charAt(k - 1)), Integer.valueOf(k));
/*     */     } 
/*     */     
/* 228 */     return H[s1Len + 1][s2Len + 1];
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\command\defaults\HelpCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */