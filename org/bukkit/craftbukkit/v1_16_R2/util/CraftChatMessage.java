/*     */ package org.bukkit.craftbukkit.v1_16_R2.util;
/*     */ 
/*     */ import com.google.common.collect.ImmutableMap;
/*     */ import java.util.ArrayList;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Optional;
/*     */ import java.util.regex.Matcher;
/*     */ import java.util.regex.Pattern;
/*     */ import net.minecraft.server.v1_16_R2.ChatClickable;
/*     */ import net.minecraft.server.v1_16_R2.ChatComponentText;
/*     */ import net.minecraft.server.v1_16_R2.ChatHexColor;
/*     */ import net.minecraft.server.v1_16_R2.ChatMessage;
/*     */ import net.minecraft.server.v1_16_R2.ChatModifier;
/*     */ import net.minecraft.server.v1_16_R2.EnumChatFormat;
/*     */ import net.minecraft.server.v1_16_R2.IChatBaseComponent;
/*     */ import net.minecraft.server.v1_16_R2.IChatMutableComponent;
/*     */ import org.bukkit.ChatColor;
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class CraftChatMessage
/*     */ {
/*  24 */   private static final Pattern LINK_PATTERN = Pattern.compile("((?:(?:https?):\\/\\/)?(?:[-\\w_\\.]{2,}\\.[a-z]{2,4}.*?(?=[\\.\\?!,;:]?(?:[" + String.valueOf('§') + " \\n]|$))))");
/*     */   private static final Map<Character, EnumChatFormat> formatMap;
/*     */   
/*     */   static {
/*  28 */     ImmutableMap.Builder<Character, EnumChatFormat> builder = ImmutableMap.builder();
/*  29 */     for (EnumChatFormat format : EnumChatFormat.values()) {
/*  30 */       builder.put(Character.valueOf(Character.toLowerCase(format.toString().charAt(1))), format);
/*     */     }
/*  32 */     formatMap = (Map<Character, EnumChatFormat>)builder.build();
/*     */   }
/*     */   
/*     */   public static EnumChatFormat getColor(ChatColor color) {
/*  36 */     return formatMap.get(Character.valueOf(color.getChar()));
/*     */   }
/*     */   
/*     */   public static ChatColor getColor(EnumChatFormat format) {
/*  40 */     return ChatColor.getByChar(format.character);
/*     */   }
/*     */   
/*     */   private static final class StringMessage {
/*  44 */     private static final Pattern INCREMENTAL_PATTERN = Pattern.compile("(" + String.valueOf('§') + "[0-9a-fk-orx])|((?:(?:https?):\\/\\/)?(?:[-\\w_\\.]{2,}\\.[a-z]{2,4}.*?(?=[\\.\\?!,;:]?(?:[" + String.valueOf('§') + " \\n]|$))))|(\\n)", 2);
/*     */     
/*  46 */     private static final Pattern INCREMENTAL_PATTERN_KEEP_NEWLINES = Pattern.compile("(" + String.valueOf('§') + "[0-9a-fk-orx])|((?:(?:https?):\\/\\/)?(?:[-\\w_\\.]{2,}\\.[a-z]{2,4}.*?(?=[\\.\\?!,;:]?(?:[" + String.valueOf('§') + " ]|$))))", 2);
/*     */     
/*  48 */     private static final ChatModifier EMPTY = ChatModifier.a.setItalic(Boolean.valueOf(false));
/*  49 */     private static final ChatModifier RESET = ChatModifier.a.setBold(Boolean.valueOf(false)).setItalic(Boolean.valueOf(false)).setUnderline(Boolean.valueOf(false)).setStrikethrough(Boolean.valueOf(false)).setRandom(Boolean.valueOf(false));
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
/*     */     
/*     */     private final List<IChatBaseComponent> list;
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
/*     */     
/*     */     private IChatMutableComponent currentChatComponent;
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
/*     */     
/*     */     private ChatModifier modifier;
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
/*     */     private final IChatBaseComponent[] output;
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
/*     */     private int currentIndex;
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
/*     */     private StringBuilder hex;
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
/*     */     private final String message;
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
/*     */     
/*     */     private StringMessage(String message, boolean keepNewlines) {
/*     */       // Byte code:
/*     */       //   0: aload_0
/*     */       //   1: invokespecial <init> : ()V
/*     */       //   4: aload_0
/*     */       //   5: new java/util/ArrayList
/*     */       //   8: dup
/*     */       //   9: invokespecial <init> : ()V
/*     */       //   12: putfield list : Ljava/util/List;
/*     */       //   15: aload_0
/*     */       //   16: new net/minecraft/server/v1_16_R2/ChatComponentText
/*     */       //   19: dup
/*     */       //   20: ldc ''
/*     */       //   22: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   25: putfield currentChatComponent : Lnet/minecraft/server/v1_16_R2/IChatMutableComponent;
/*     */       //   28: aload_0
/*     */       //   29: getstatic net/minecraft/server/v1_16_R2/ChatModifier.a : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   32: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   35: aload_0
/*     */       //   36: aload_1
/*     */       //   37: putfield message : Ljava/lang/String;
/*     */       //   40: aload_1
/*     */       //   41: ifnonnull -> 60
/*     */       //   44: aload_0
/*     */       //   45: iconst_1
/*     */       //   46: anewarray net/minecraft/server/v1_16_R2/IChatBaseComponent
/*     */       //   49: dup
/*     */       //   50: iconst_0
/*     */       //   51: aload_0
/*     */       //   52: getfield currentChatComponent : Lnet/minecraft/server/v1_16_R2/IChatMutableComponent;
/*     */       //   55: aastore
/*     */       //   56: putfield output : [Lnet/minecraft/server/v1_16_R2/IChatBaseComponent;
/*     */       //   59: return
/*     */       //   60: aload_0
/*     */       //   61: getfield list : Ljava/util/List;
/*     */       //   64: aload_0
/*     */       //   65: getfield currentChatComponent : Lnet/minecraft/server/v1_16_R2/IChatMutableComponent;
/*     */       //   68: invokeinterface add : (Ljava/lang/Object;)Z
/*     */       //   73: pop
/*     */       //   74: iload_2
/*     */       //   75: ifeq -> 84
/*     */       //   78: getstatic org/bukkit/craftbukkit/v1_16_R2/util/CraftChatMessage$StringMessage.INCREMENTAL_PATTERN_KEEP_NEWLINES : Ljava/util/regex/Pattern;
/*     */       //   81: goto -> 87
/*     */       //   84: getstatic org/bukkit/craftbukkit/v1_16_R2/util/CraftChatMessage$StringMessage.INCREMENTAL_PATTERN : Ljava/util/regex/Pattern;
/*     */       //   87: aload_1
/*     */       //   88: invokevirtual matcher : (Ljava/lang/CharSequence;)Ljava/util/regex/Matcher;
/*     */       //   91: astore_3
/*     */       //   92: aconst_null
/*     */       //   93: astore #4
/*     */       //   95: iconst_0
/*     */       //   96: istore #5
/*     */       //   98: iconst_0
/*     */       //   99: istore #6
/*     */       //   101: aload_3
/*     */       //   102: invokevirtual find : ()Z
/*     */       //   105: ifeq -> 726
/*     */       //   108: iconst_0
/*     */       //   109: istore #7
/*     */       //   111: aload_3
/*     */       //   112: iinc #7, 1
/*     */       //   115: iload #7
/*     */       //   117: invokevirtual group : (I)Ljava/lang/String;
/*     */       //   120: dup
/*     */       //   121: astore #4
/*     */       //   123: ifnonnull -> 129
/*     */       //   126: goto -> 111
/*     */       //   129: aload_3
/*     */       //   130: iload #7
/*     */       //   132: invokevirtual start : (I)I
/*     */       //   135: istore #8
/*     */       //   137: iload #8
/*     */       //   139: aload_0
/*     */       //   140: getfield currentIndex : I
/*     */       //   143: if_icmple -> 155
/*     */       //   146: iconst_0
/*     */       //   147: istore #5
/*     */       //   149: aload_0
/*     */       //   150: iload #8
/*     */       //   152: invokespecial appendNewComponent : (I)V
/*     */       //   155: iload #7
/*     */       //   157: tableswitch default -> 713, 1 -> 184, 2 -> 604, 3 -> 697
/*     */       //   184: aload #4
/*     */       //   186: getstatic java/util/Locale.ENGLISH : Ljava/util/Locale;
/*     */       //   189: invokevirtual toLowerCase : (Ljava/util/Locale;)Ljava/lang/String;
/*     */       //   192: iconst_1
/*     */       //   193: invokevirtual charAt : (I)C
/*     */       //   196: istore #9
/*     */       //   198: invokestatic access$000 : ()Ljava/util/Map;
/*     */       //   201: iload #9
/*     */       //   203: invokestatic valueOf : (C)Ljava/lang/Character;
/*     */       //   206: invokeinterface get : (Ljava/lang/Object;)Ljava/lang/Object;
/*     */       //   211: checkcast net/minecraft/server/v1_16_R2/EnumChatFormat
/*     */       //   214: astore #10
/*     */       //   216: iload #9
/*     */       //   218: bipush #120
/*     */       //   220: if_icmpne -> 239
/*     */       //   223: aload_0
/*     */       //   224: new java/lang/StringBuilder
/*     */       //   227: dup
/*     */       //   228: ldc '#'
/*     */       //   230: invokespecial <init> : (Ljava/lang/String;)V
/*     */       //   233: putfield hex : Ljava/lang/StringBuilder;
/*     */       //   236: goto -> 598
/*     */       //   239: aload_0
/*     */       //   240: getfield hex : Ljava/lang/StringBuilder;
/*     */       //   243: ifnull -> 296
/*     */       //   246: aload_0
/*     */       //   247: getfield hex : Ljava/lang/StringBuilder;
/*     */       //   250: iload #9
/*     */       //   252: invokevirtual append : (C)Ljava/lang/StringBuilder;
/*     */       //   255: pop
/*     */       //   256: aload_0
/*     */       //   257: getfield hex : Ljava/lang/StringBuilder;
/*     */       //   260: invokevirtual length : ()I
/*     */       //   263: bipush #7
/*     */       //   265: if_icmpne -> 598
/*     */       //   268: aload_0
/*     */       //   269: getstatic org/bukkit/craftbukkit/v1_16_R2/util/CraftChatMessage$StringMessage.RESET : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   272: aload_0
/*     */       //   273: getfield hex : Ljava/lang/StringBuilder;
/*     */       //   276: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   279: invokestatic a : (Ljava/lang/String;)Lnet/minecraft/server/v1_16_R2/ChatHexColor;
/*     */       //   282: invokevirtual setColor : (Lnet/minecraft/server/v1_16_R2/ChatHexColor;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   285: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   288: aload_0
/*     */       //   289: aconst_null
/*     */       //   290: putfield hex : Ljava/lang/StringBuilder;
/*     */       //   293: goto -> 598
/*     */       //   296: aload #10
/*     */       //   298: invokevirtual isFormat : ()Z
/*     */       //   301: ifeq -> 451
/*     */       //   304: aload #10
/*     */       //   306: getstatic net/minecraft/server/v1_16_R2/EnumChatFormat.RESET : Lnet/minecraft/server/v1_16_R2/EnumChatFormat;
/*     */       //   309: if_acmpeq -> 451
/*     */       //   312: getstatic org/bukkit/craftbukkit/v1_16_R2/util/CraftChatMessage$1.$SwitchMap$net$minecraft$server$EnumChatFormat : [I
/*     */       //   315: aload #10
/*     */       //   317: invokevirtual ordinal : ()I
/*     */       //   320: iaload
/*     */       //   321: tableswitch default -> 441, 1 -> 356, 2 -> 373, 3 -> 390, 4 -> 407, 5 -> 424
/*     */       //   356: aload_0
/*     */       //   357: aload_0
/*     */       //   358: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   361: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
/*     */       //   364: invokevirtual setBold : (Ljava/lang/Boolean;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   367: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   370: goto -> 598
/*     */       //   373: aload_0
/*     */       //   374: aload_0
/*     */       //   375: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   378: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
/*     */       //   381: invokevirtual setItalic : (Ljava/lang/Boolean;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   384: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   387: goto -> 598
/*     */       //   390: aload_0
/*     */       //   391: aload_0
/*     */       //   392: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   395: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
/*     */       //   398: invokevirtual setStrikethrough : (Ljava/lang/Boolean;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   401: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   404: goto -> 598
/*     */       //   407: aload_0
/*     */       //   408: aload_0
/*     */       //   409: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   412: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
/*     */       //   415: invokevirtual setUnderline : (Ljava/lang/Boolean;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   418: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   421: goto -> 598
/*     */       //   424: aload_0
/*     */       //   425: aload_0
/*     */       //   426: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   429: getstatic java/lang/Boolean.TRUE : Ljava/lang/Boolean;
/*     */       //   432: invokevirtual setRandom : (Ljava/lang/Boolean;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   435: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   438: goto -> 598
/*     */       //   441: new java/lang/AssertionError
/*     */       //   444: dup
/*     */       //   445: ldc 'Unexpected message format'
/*     */       //   447: invokespecial <init> : (Ljava/lang/Object;)V
/*     */       //   450: athrow
/*     */       //   451: aload_0
/*     */       //   452: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   455: astore #11
/*     */       //   457: aload_0
/*     */       //   458: iload #6
/*     */       //   460: ifne -> 469
/*     */       //   463: getstatic org/bukkit/craftbukkit/v1_16_R2/util/CraftChatMessage$StringMessage.RESET : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   466: goto -> 472
/*     */       //   469: getstatic org/bukkit/craftbukkit/v1_16_R2/util/CraftChatMessage$StringMessage.EMPTY : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   472: aload #10
/*     */       //   474: invokevirtual setColor : (Lnet/minecraft/server/v1_16_R2/EnumChatFormat;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   477: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   480: iconst_1
/*     */       //   481: istore #6
/*     */       //   483: aload #11
/*     */       //   485: invokevirtual isBold : ()Z
/*     */       //   488: ifeq -> 506
/*     */       //   491: aload_0
/*     */       //   492: aload_0
/*     */       //   493: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   496: iconst_0
/*     */       //   497: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*     */       //   500: invokevirtual setBold : (Ljava/lang/Boolean;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   503: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   506: aload #11
/*     */       //   508: invokevirtual isItalic : ()Z
/*     */       //   511: ifeq -> 529
/*     */       //   514: aload_0
/*     */       //   515: aload_0
/*     */       //   516: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   519: iconst_0
/*     */       //   520: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*     */       //   523: invokevirtual setItalic : (Ljava/lang/Boolean;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   526: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   529: aload #11
/*     */       //   531: invokevirtual isRandom : ()Z
/*     */       //   534: ifeq -> 552
/*     */       //   537: aload_0
/*     */       //   538: aload_0
/*     */       //   539: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   542: iconst_0
/*     */       //   543: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*     */       //   546: invokevirtual setRandom : (Ljava/lang/Boolean;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   549: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   552: aload #11
/*     */       //   554: invokevirtual isStrikethrough : ()Z
/*     */       //   557: ifeq -> 575
/*     */       //   560: aload_0
/*     */       //   561: aload_0
/*     */       //   562: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   565: iconst_0
/*     */       //   566: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*     */       //   569: invokevirtual setStrikethrough : (Ljava/lang/Boolean;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   572: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   575: aload #11
/*     */       //   577: invokevirtual isUnderlined : ()Z
/*     */       //   580: ifeq -> 598
/*     */       //   583: aload_0
/*     */       //   584: aload_0
/*     */       //   585: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   588: iconst_0
/*     */       //   589: invokestatic valueOf : (Z)Ljava/lang/Boolean;
/*     */       //   592: invokevirtual setUnderline : (Ljava/lang/Boolean;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   595: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   598: iconst_1
/*     */       //   599: istore #5
/*     */       //   601: goto -> 713
/*     */       //   604: aload #4
/*     */       //   606: ldc 'http://'
/*     */       //   608: invokevirtual startsWith : (Ljava/lang/String;)Z
/*     */       //   611: ifne -> 646
/*     */       //   614: aload #4
/*     */       //   616: ldc 'https://'
/*     */       //   618: invokevirtual startsWith : (Ljava/lang/String;)Z
/*     */       //   621: ifne -> 646
/*     */       //   624: new java/lang/StringBuilder
/*     */       //   627: dup
/*     */       //   628: invokespecial <init> : ()V
/*     */       //   631: ldc 'http://'
/*     */       //   633: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   636: aload #4
/*     */       //   638: invokevirtual append : (Ljava/lang/String;)Ljava/lang/StringBuilder;
/*     */       //   641: invokevirtual toString : ()Ljava/lang/String;
/*     */       //   644: astore #4
/*     */       //   646: aload_0
/*     */       //   647: aload_0
/*     */       //   648: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   651: new net/minecraft/server/v1_16_R2/ChatClickable
/*     */       //   654: dup
/*     */       //   655: getstatic net/minecraft/server/v1_16_R2/ChatClickable$EnumClickAction.OPEN_URL : Lnet/minecraft/server/v1_16_R2/ChatClickable$EnumClickAction;
/*     */       //   658: aload #4
/*     */       //   660: invokespecial <init> : (Lnet/minecraft/server/v1_16_R2/ChatClickable$EnumClickAction;Ljava/lang/String;)V
/*     */       //   663: invokevirtual setChatClickable : (Lnet/minecraft/server/v1_16_R2/ChatClickable;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   666: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   669: aload_0
/*     */       //   670: aload_3
/*     */       //   671: iload #7
/*     */       //   673: invokevirtual end : (I)I
/*     */       //   676: invokespecial appendNewComponent : (I)V
/*     */       //   679: aload_0
/*     */       //   680: aload_0
/*     */       //   681: getfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   684: aconst_null
/*     */       //   685: checkcast net/minecraft/server/v1_16_R2/ChatClickable
/*     */       //   688: invokevirtual setChatClickable : (Lnet/minecraft/server/v1_16_R2/ChatClickable;)Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   691: putfield modifier : Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   694: goto -> 713
/*     */       //   697: iload #5
/*     */       //   699: ifeq -> 708
/*     */       //   702: aload_0
/*     */       //   703: iload #8
/*     */       //   705: invokespecial appendNewComponent : (I)V
/*     */       //   708: aload_0
/*     */       //   709: aconst_null
/*     */       //   710: putfield currentChatComponent : Lnet/minecraft/server/v1_16_R2/IChatMutableComponent;
/*     */       //   713: aload_0
/*     */       //   714: aload_3
/*     */       //   715: iload #7
/*     */       //   717: invokevirtual end : (I)I
/*     */       //   720: putfield currentIndex : I
/*     */       //   723: goto -> 101
/*     */       //   726: aload_0
/*     */       //   727: getfield currentIndex : I
/*     */       //   730: aload_1
/*     */       //   731: invokevirtual length : ()I
/*     */       //   734: if_icmplt -> 742
/*     */       //   737: iload #5
/*     */       //   739: ifeq -> 750
/*     */       //   742: aload_0
/*     */       //   743: aload_1
/*     */       //   744: invokevirtual length : ()I
/*     */       //   747: invokespecial appendNewComponent : (I)V
/*     */       //   750: aload_0
/*     */       //   751: aload_0
/*     */       //   752: getfield list : Ljava/util/List;
/*     */       //   755: aload_0
/*     */       //   756: getfield list : Ljava/util/List;
/*     */       //   759: invokeinterface size : ()I
/*     */       //   764: anewarray net/minecraft/server/v1_16_R2/IChatBaseComponent
/*     */       //   767: invokeinterface toArray : ([Ljava/lang/Object;)[Ljava/lang/Object;
/*     */       //   772: checkcast [Lnet/minecraft/server/v1_16_R2/IChatBaseComponent;
/*     */       //   775: putfield output : [Lnet/minecraft/server/v1_16_R2/IChatBaseComponent;
/*     */       //   778: return
/*     */       // Line number table:
/*     */       //   Java source line number -> byte code offset
/*     */       //   #59	-> 0
/*     */       //   #51	-> 4
/*     */       //   #52	-> 15
/*     */       //   #53	-> 28
/*     */       //   #60	-> 35
/*     */       //   #61	-> 40
/*     */       //   #62	-> 44
/*     */       //   #63	-> 59
/*     */       //   #65	-> 60
/*     */       //   #67	-> 74
/*     */       //   #68	-> 92
/*     */       //   #69	-> 95
/*     */       //   #70	-> 98
/*     */       //   #71	-> 101
/*     */       //   #72	-> 108
/*     */       //   #73	-> 111
/*     */       //   #76	-> 129
/*     */       //   #77	-> 137
/*     */       //   #78	-> 146
/*     */       //   #79	-> 149
/*     */       //   #81	-> 155
/*     */       //   #83	-> 184
/*     */       //   #84	-> 198
/*     */       //   #86	-> 216
/*     */       //   #87	-> 223
/*     */       //   #88	-> 239
/*     */       //   #89	-> 246
/*     */       //   #91	-> 256
/*     */       //   #92	-> 268
/*     */       //   #93	-> 288
/*     */       //   #95	-> 296
/*     */       //   #96	-> 312
/*     */       //   #98	-> 356
/*     */       //   #99	-> 370
/*     */       //   #101	-> 373
/*     */       //   #102	-> 387
/*     */       //   #104	-> 390
/*     */       //   #105	-> 404
/*     */       //   #107	-> 407
/*     */       //   #108	-> 421
/*     */       //   #110	-> 424
/*     */       //   #111	-> 438
/*     */       //   #113	-> 441
/*     */       //   #117	-> 451
/*     */       //   #118	-> 457
/*     */       //   #119	-> 480
/*     */       //   #120	-> 483
/*     */       //   #121	-> 491
/*     */       //   #123	-> 506
/*     */       //   #124	-> 514
/*     */       //   #126	-> 529
/*     */       //   #127	-> 537
/*     */       //   #129	-> 552
/*     */       //   #130	-> 560
/*     */       //   #132	-> 575
/*     */       //   #133	-> 583
/*     */       //   #137	-> 598
/*     */       //   #138	-> 601
/*     */       //   #140	-> 604
/*     */       //   #141	-> 624
/*     */       //   #143	-> 646
/*     */       //   #144	-> 669
/*     */       //   #145	-> 679
/*     */       //   #146	-> 694
/*     */       //   #148	-> 697
/*     */       //   #149	-> 702
/*     */       //   #151	-> 708
/*     */       //   #154	-> 713
/*     */       //   #155	-> 723
/*     */       //   #157	-> 726
/*     */       //   #158	-> 742
/*     */       //   #161	-> 750
/*     */       //   #162	-> 778
/*     */       // Local variable table:
/*     */       //   start	length	slot	name	descriptor
/*     */       //   457	141	11	previous	Lnet/minecraft/server/v1_16_R2/ChatModifier;
/*     */       //   198	406	9	c	C
/*     */       //   216	388	10	format	Lnet/minecraft/server/v1_16_R2/EnumChatFormat;
/*     */       //   111	612	7	groupId	I
/*     */       //   137	586	8	index	I
/*     */       //   0	779	0	this	Lorg/bukkit/craftbukkit/v1_16_R2/util/CraftChatMessage$StringMessage;
/*     */       //   0	779	1	message	Ljava/lang/String;
/*     */       //   0	779	2	keepNewlines	Z
/*     */       //   92	687	3	matcher	Ljava/util/regex/Matcher;
/*     */       //   95	684	4	match	Ljava/lang/String;
/*     */       //   98	681	5	needsAdd	Z
/*     */       //   101	678	6	hasReset	Z
/*     */     }
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
/*     */     
/*     */     private void appendNewComponent(int index) {
/* 165 */       IChatMutableComponent iChatMutableComponent = (new ChatComponentText(this.message.substring(this.currentIndex, index))).setChatModifier(this.modifier);
/* 166 */       this.currentIndex = index;
/* 167 */       if (this.currentChatComponent == null) {
/* 168 */         this.currentChatComponent = (IChatMutableComponent)new ChatComponentText("");
/* 169 */         this.list.add(this.currentChatComponent);
/*     */       } 
/* 171 */       this.currentChatComponent.addSibling((IChatBaseComponent)iChatMutableComponent);
/*     */     }
/*     */     
/*     */     private IChatBaseComponent[] getOutput() {
/* 175 */       return this.output;
/*     */     }
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent fromStringOrNull(String message) {
/* 180 */     return fromStringOrNull(message, false);
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent fromStringOrNull(String message, boolean keepNewlines) {
/* 184 */     return (message == null || message.isEmpty()) ? null : fromString(message, keepNewlines)[0];
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent[] fromString(String message) {
/* 188 */     return fromString(message, false);
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent[] fromString(String message, boolean keepNewlines) {
/* 192 */     return (new StringMessage(message, keepNewlines)).getOutput();
/*     */   }
/*     */   
/*     */   public static String toJSON(IChatBaseComponent component) {
/* 196 */     return IChatBaseComponent.ChatSerializer.a(component);
/*     */   }
/*     */   
/*     */   public static String fromComponent(IChatBaseComponent component) {
/* 200 */     if (component == null) return ""; 
/* 201 */     StringBuilder out = new StringBuilder();
/*     */     
/* 203 */     boolean hadFormat = false;
/* 204 */     for (IChatBaseComponent c : component) {
/* 205 */       ChatModifier modi = c.getChatModifier();
/* 206 */       ChatHexColor color = modi.getColor();
/* 207 */       if (!c.getText().isEmpty() || color != null) {
/* 208 */         if (color != null) {
/* 209 */           if (color.format != null) {
/* 210 */             out.append(color.format);
/*     */           } else {
/* 212 */             out.append('§').append("x");
/* 213 */             for (char magic : color.b().substring(1).toCharArray()) {
/* 214 */               out.append('§').append(magic);
/*     */             }
/*     */           } 
/* 217 */           hadFormat = true;
/* 218 */         } else if (hadFormat) {
/* 219 */           out.append(ChatColor.RESET);
/* 220 */           hadFormat = false;
/*     */         } 
/*     */       }
/* 223 */       if (modi.isBold()) {
/* 224 */         out.append(EnumChatFormat.BOLD);
/* 225 */         hadFormat = true;
/*     */       } 
/* 227 */       if (modi.isItalic()) {
/* 228 */         out.append(EnumChatFormat.ITALIC);
/* 229 */         hadFormat = true;
/*     */       } 
/* 231 */       if (modi.isUnderlined()) {
/* 232 */         out.append(EnumChatFormat.UNDERLINE);
/* 233 */         hadFormat = true;
/*     */       } 
/* 235 */       if (modi.isStrikethrough()) {
/* 236 */         out.append(EnumChatFormat.STRIKETHROUGH);
/* 237 */         hadFormat = true;
/*     */       } 
/* 239 */       if (modi.isRandom()) {
/* 240 */         out.append(EnumChatFormat.OBFUSCATED);
/* 241 */         hadFormat = true;
/*     */       } 
/* 243 */       c.b(x -> {
/*     */             out.append(x);
/*     */             return Optional.empty();
/*     */           });
/*     */     } 
/* 248 */     return out.toString();
/*     */   }
/*     */   
/*     */   public static IChatBaseComponent fixComponent(IChatBaseComponent component) {
/* 252 */     Matcher matcher = LINK_PATTERN.matcher("");
/* 253 */     return fixComponent(component, matcher);
/*     */   }
/*     */   private static IChatBaseComponent fixComponent(IChatBaseComponent component, Matcher matcher) {
/*     */     ChatComponentText chatComponentText;
/* 257 */     if (component instanceof ChatComponentText) {
/* 258 */       ChatComponentText text = (ChatComponentText)component;
/* 259 */       String msg = text.getText();
/* 260 */       if (matcher.reset(msg).find()) {
/* 261 */         matcher.reset();
/*     */         
/* 263 */         ChatModifier modifier = text.getChatModifier();
/* 264 */         List<IChatBaseComponent> list1 = new ArrayList<>();
/* 265 */         List<IChatBaseComponent> extrasOld = new ArrayList<>(text.getSiblings());
/* 266 */         chatComponentText = text = new ChatComponentText("");
/*     */         
/* 268 */         int pos = 0;
/* 269 */         while (matcher.find()) {
/* 270 */           String match = matcher.group();
/*     */           
/* 272 */           if (!match.startsWith("http://") && !match.startsWith("https://")) {
/* 273 */             match = "http://" + match;
/*     */           }
/*     */           
/* 276 */           ChatComponentText chatComponentText1 = new ChatComponentText(msg.substring(pos, matcher.start()));
/* 277 */           chatComponentText1.setChatModifier(modifier);
/* 278 */           list1.add(chatComponentText1);
/*     */           
/* 280 */           ChatComponentText link = new ChatComponentText(matcher.group());
/* 281 */           ChatModifier linkModi = modifier.setChatClickable(new ChatClickable(ChatClickable.EnumClickAction.OPEN_URL, match));
/* 282 */           link.setChatModifier(linkModi);
/* 283 */           list1.add(link);
/*     */           
/* 285 */           pos = matcher.end();
/*     */         } 
/*     */         
/* 288 */         ChatComponentText prev = new ChatComponentText(msg.substring(pos));
/* 289 */         prev.setChatModifier(modifier);
/* 290 */         list1.add(prev);
/* 291 */         list1.addAll(extrasOld);
/*     */         
/* 293 */         for (IChatBaseComponent c : list1) {
/* 294 */           text.addSibling(c);
/*     */         }
/*     */       } 
/*     */     } 
/*     */     
/* 299 */     List<IChatBaseComponent> extras = chatComponentText.getSiblings();
/* 300 */     for (int i = 0; i < extras.size(); i++) {
/* 301 */       IChatBaseComponent comp = extras.get(i);
/* 302 */       if (comp.getChatModifier() != null && comp.getChatModifier().getClickEvent() == null) {
/* 303 */         extras.set(i, fixComponent(comp, matcher));
/*     */       }
/*     */     } 
/*     */     
/* 307 */     if (chatComponentText instanceof ChatMessage) {
/* 308 */       Object[] subs = ((ChatMessage)chatComponentText).getArgs();
/* 309 */       for (int j = 0; j < subs.length; j++) {
/* 310 */         Object comp = subs[j];
/* 311 */         if (comp instanceof IChatBaseComponent) {
/* 312 */           IChatBaseComponent c = (IChatBaseComponent)comp;
/* 313 */           if (c.getChatModifier() != null && c.getChatModifier().getClickEvent() == null) {
/* 314 */             subs[j] = fixComponent(c, matcher);
/*     */           }
/* 316 */         } else if (comp instanceof String && matcher.reset((String)comp).find()) {
/* 317 */           subs[j] = fixComponent((IChatBaseComponent)new ChatComponentText((String)comp), matcher);
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/* 322 */     return (IChatBaseComponent)chatComponentText;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\craftbukkit\v1_16_R\\util\CraftChatMessage.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */