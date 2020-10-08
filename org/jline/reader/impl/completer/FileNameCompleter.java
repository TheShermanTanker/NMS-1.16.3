/*     */ package org.jline.reader.impl.completer;
/*     */ 
/*     */ import java.io.IOException;
/*     */ import java.nio.file.Files;
/*     */ import java.nio.file.Path;
/*     */ import java.nio.file.Paths;
/*     */ import java.util.List;
/*     */ import org.jline.reader.Candidate;
/*     */ import org.jline.reader.Completer;
/*     */ import org.jline.reader.LineReader;
/*     */ import org.jline.reader.ParsedLine;
/*     */ import org.jline.terminal.Terminal;
/*     */ import org.jline.utils.AttributedStringBuilder;
/*     */ import org.jline.utils.AttributedStyle;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ @Deprecated
/*     */ public class FileNameCompleter
/*     */   implements Completer
/*     */ {
/*     */   public void complete(LineReader reader, ParsedLine commandLine, List<Candidate> candidates) {
/*     */     Path current;
/*     */     String curBuf;
/*  51 */     assert commandLine != null;
/*  52 */     assert candidates != null;
/*     */     
/*  54 */     String buffer = commandLine.word().substring(0, commandLine.wordCursor());
/*     */ 
/*     */ 
/*     */     
/*  58 */     String sep = getUserDir().getFileSystem().getSeparator();
/*  59 */     int lastSep = buffer.lastIndexOf(sep);
/*  60 */     if (lastSep >= 0) {
/*  61 */       curBuf = buffer.substring(0, lastSep + 1);
/*  62 */       if (curBuf.startsWith("~")) {
/*  63 */         if (curBuf.startsWith("~" + sep)) {
/*  64 */           current = getUserHome().resolve(curBuf.substring(2));
/*     */         } else {
/*  66 */           current = getUserHome().getParent().resolve(curBuf.substring(1));
/*     */         } 
/*     */       } else {
/*  69 */         current = getUserDir().resolve(curBuf);
/*     */       } 
/*     */     } else {
/*  72 */       curBuf = "";
/*  73 */       current = getUserDir();
/*     */     } 
/*     */     try {
/*  76 */       Files.newDirectoryStream(current, this::accept).forEach(p -> {
/*     */             String value = curBuf + p.getFileName().toString();
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */             
/*     */             if (Files.isDirectory(p, new java.nio.file.LinkOption[0])) {
/*     */               candidates.add(new Candidate(value + (reader.isSet(LineReader.Option.AUTO_PARAM_SLASH) ? sep : ""), getDisplay(reader.getTerminal(), p), null, null, reader.isSet(LineReader.Option.AUTO_REMOVE_SLASH) ? sep : null, null, false));
/*     */             } else {
/*     */               candidates.add(new Candidate(value, getDisplay(reader.getTerminal(), p), null, null, null, null, true));
/*     */             } 
/*     */           });
/*  91 */     } catch (IOException iOException) {}
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   protected boolean accept(Path path) {
/*     */     try {
/*  98 */       return !Files.isHidden(path);
/*  99 */     } catch (IOException e) {
/* 100 */       return false;
/*     */     } 
/*     */   }
/*     */   
/*     */   protected Path getUserDir() {
/* 105 */     return Paths.get(System.getProperty("user.dir"), new String[0]);
/*     */   }
/*     */   
/*     */   protected Path getUserHome() {
/* 109 */     return Paths.get(System.getProperty("user.home"), new String[0]);
/*     */   }
/*     */ 
/*     */   
/*     */   protected String getDisplay(Terminal terminal, Path p) {
/* 114 */     String name = p.getFileName().toString();
/* 115 */     if (Files.isDirectory(p, new java.nio.file.LinkOption[0])) {
/* 116 */       AttributedStringBuilder sb = new AttributedStringBuilder();
/* 117 */       sb.styled(AttributedStyle.BOLD.foreground(1), name);
/* 118 */       sb.append("/");
/* 119 */       name = sb.toAnsi(terminal);
/* 120 */     } else if (Files.isSymbolicLink(p)) {
/* 121 */       AttributedStringBuilder sb = new AttributedStringBuilder();
/* 122 */       sb.styled(AttributedStyle.BOLD.foreground(1), name);
/* 123 */       sb.append("@");
/* 124 */       name = sb.toAnsi(terminal);
/*     */     } 
/* 126 */     return name;
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\impl\completer\FileNameCompleter.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */