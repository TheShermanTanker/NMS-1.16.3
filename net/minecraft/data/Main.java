/*    */ package net.minecraft.data;
/*    */ import java.io.IOException;
/*    */ import java.nio.file.Path;
/*    */ import java.nio.file.Paths;
/*    */ import java.util.Collection;
/*    */ import java.util.stream.Collectors;
/*    */ import joptsimple.AbstractOptionSpec;
/*    */ import joptsimple.ArgumentAcceptingOptionSpec;
/*    */ import joptsimple.OptionParser;
/*    */ import joptsimple.OptionSet;
/*    */ import joptsimple.OptionSpec;
/*    */ import joptsimple.OptionSpecBuilder;
/*    */ import net.minecraft.server.v1_16_R2.DebugReportGenerator;
/*    */ import net.minecraft.server.v1_16_R2.DebugReportNBT;
/*    */ import net.minecraft.server.v1_16_R2.DebugReportProvider;
/*    */ import net.minecraft.server.v1_16_R2.ho;
/*    */ import net.minecraft.server.v1_16_R2.hw;
/*    */ import net.minecraft.server.v1_16_R2.hx;
/*    */ import net.minecraft.server.v1_16_R2.ie;
/*    */ import net.minecraft.server.v1_16_R2.ik;
/*    */ import net.minecraft.server.v1_16_R2.jg;
/*    */ import net.minecraft.server.v1_16_R2.jp;
/*    */ import net.minecraft.server.v1_16_R2.jq;
/*    */ import net.minecraft.server.v1_16_R2.js;
/*    */ import net.minecraft.server.v1_16_R2.jt;
/*    */ import net.minecraft.server.v1_16_R2.ju;
/*    */ import net.minecraft.server.v1_16_R2.jv;
/*    */ import net.minecraft.server.v1_16_R2.ks;
/*    */ 
/*    */ public class Main {
/*    */   public static void main(String[] var0) throws IOException {
/* 32 */     OptionParser var1 = new OptionParser();
/* 33 */     AbstractOptionSpec abstractOptionSpec = var1.accepts("help", "Show the help menu").forHelp();
/* 34 */     OptionSpecBuilder optionSpecBuilder1 = var1.accepts("server", "Include server generators");
/* 35 */     OptionSpecBuilder optionSpecBuilder2 = var1.accepts("client", "Include client generators");
/* 36 */     OptionSpecBuilder optionSpecBuilder3 = var1.accepts("dev", "Include development tools");
/* 37 */     OptionSpecBuilder optionSpecBuilder4 = var1.accepts("reports", "Include data reports");
/* 38 */     OptionSpecBuilder optionSpecBuilder5 = var1.accepts("validate", "Validate inputs");
/* 39 */     OptionSpecBuilder optionSpecBuilder6 = var1.accepts("all", "Include all generators");
/* 40 */     ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec1 = var1.accepts("output", "Output folder").withRequiredArg().defaultsTo("generated", (Object[])new String[0]);
/* 41 */     ArgumentAcceptingOptionSpec argumentAcceptingOptionSpec2 = var1.accepts("input", "Input folder").withRequiredArg();
/* 42 */     OptionSet var11 = var1.parse(var0);
/*    */     
/* 44 */     if (var11.has((OptionSpec)abstractOptionSpec) || !var11.hasOptions()) {
/* 45 */       var1.printHelpOn(System.out);
/*    */       
/*    */       return;
/*    */     } 
/* 49 */     Path var12 = Paths.get((String)argumentAcceptingOptionSpec1.value(var11), new String[0]);
/* 50 */     boolean var13 = var11.has((OptionSpec)optionSpecBuilder6);
/* 51 */     boolean var14 = (var13 || var11.has((OptionSpec)optionSpecBuilder2));
/* 52 */     boolean var15 = (var13 || var11.has((OptionSpec)optionSpecBuilder1));
/* 53 */     boolean var16 = (var13 || var11.has((OptionSpec)optionSpecBuilder3));
/* 54 */     boolean var17 = (var13 || var11.has((OptionSpec)optionSpecBuilder4));
/* 55 */     boolean var18 = (var13 || var11.has((OptionSpec)optionSpecBuilder5));
/* 56 */     DebugReportGenerator var19 = a(var12, (Collection<Path>)var11.valuesOf((OptionSpec)argumentAcceptingOptionSpec2).stream().map(var0 -> Paths.get(var0, new String[0])).collect(Collectors.toList()), var14, var15, var16, var17, var18);
/* 57 */     var19.c();
/*    */   }
/*    */   
/*    */   public static DebugReportGenerator a(Path var0, Collection<Path> var1, boolean var2, boolean var3, boolean var4, boolean var5, boolean var6) {
/* 61 */     DebugReportGenerator var7 = new DebugReportGenerator(var0, var1);
/* 62 */     if (var2 || var3) {
/* 63 */       var7.a((DebugReportProvider)(new jp(var7))
/* 64 */           .a((jp.a)new jq()));
/*    */     }
/*    */ 
/*    */     
/* 68 */     if (var2) {
/* 69 */       var7.a((DebugReportProvider)new ik(var7));
/*    */     }
/* 71 */     if (var3) {
/* 72 */       var7.a((DebugReportProvider)new ju(var7));
/* 73 */       js var8 = new js(var7);
/* 74 */       var7.a((DebugReportProvider)var8);
/* 75 */       var7.a((DebugReportProvider)new jv(var7, var8));
/* 76 */       var7.a((DebugReportProvider)new jt(var7));
/* 77 */       var7.a((DebugReportProvider)new jg(var7));
/* 78 */       var7.a((DebugReportProvider)new ho(var7));
/* 79 */       var7.a((DebugReportProvider)new ie(var7));
/*    */     } 
/* 81 */     if (var4) {
/* 82 */       var7.a((DebugReportProvider)new DebugReportNBT(var7));
/*    */     }
/* 84 */     if (var5) {
/* 85 */       var7.a((DebugReportProvider)new hv(var7));
/* 86 */       var7.a((DebugReportProvider)new hx(var7));
/* 87 */       var7.a((DebugReportProvider)new hw(var7));
/* 88 */       var7.a((DebugReportProvider)new ks(var7));
/*    */     } 
/* 90 */     return var7;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\data\Main.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */