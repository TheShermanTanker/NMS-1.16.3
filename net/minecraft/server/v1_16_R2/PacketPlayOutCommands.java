/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import com.google.common.collect.Queues;
/*     */ import com.mojang.brigadier.arguments.ArgumentType;
/*     */ import com.mojang.brigadier.builder.ArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.LiteralArgumentBuilder;
/*     */ import com.mojang.brigadier.builder.RequiredArgumentBuilder;
/*     */ import com.mojang.brigadier.context.CommandContext;
/*     */ import com.mojang.brigadier.exceptions.CommandSyntaxException;
/*     */ import com.mojang.brigadier.tree.ArgumentCommandNode;
/*     */ import com.mojang.brigadier.tree.CommandNode;
/*     */ import com.mojang.brigadier.tree.LiteralCommandNode;
/*     */ import com.mojang.brigadier.tree.RootCommandNode;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMaps;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.io.IOException;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Queue;
/*     */ import javax.annotation.Nullable;
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
/*     */ public class PacketPlayOutCommands
/*     */   implements Packet<PacketListenerPlayOut>
/*     */ {
/*     */   private RootCommandNode<ICompletionProvider> a;
/*     */   
/*     */   public PacketPlayOutCommands() {}
/*     */   
/*     */   public PacketPlayOutCommands(RootCommandNode<ICompletionProvider> var0) {
/*  46 */     this.a = var0;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketDataSerializer var0) throws IOException {
/*  51 */     a[] var1 = new a[var0.i()];
/*  52 */     for (int var2 = 0; var2 < var1.length; var2++) {
/*  53 */       var1[var2] = c(var0);
/*     */     }
/*     */     
/*  56 */     a(var1);
/*  57 */     this.a = (RootCommandNode<ICompletionProvider>)a.a(var1[var0.i()]);
/*     */   }
/*     */ 
/*     */   
/*     */   public void b(PacketDataSerializer var0) throws IOException {
/*  62 */     Object2IntMap<CommandNode<ICompletionProvider>> var1 = a(this.a);
/*  63 */     CommandNode[] arrayOfCommandNode = (CommandNode[])a(var1);
/*     */     
/*  65 */     var0.d(arrayOfCommandNode.length);
/*  66 */     for (CommandNode<ICompletionProvider> var6 : arrayOfCommandNode) {
/*  67 */       a(var0, var6, (Map)var1);
/*     */     }
/*  69 */     var0.d(var1.get(this.a).intValue());
/*     */   }
/*     */   
/*     */   private static void a(a[] var0) {
/*  73 */     List<a> var1 = Lists.newArrayList((Object[])var0);
/*  74 */     while (!var1.isEmpty()) {
/*  75 */       boolean var2 = var1.removeIf(var1 -> var1.a(var0));
/*  76 */       if (!var2) {
/*  77 */         throw new IllegalStateException("Server sent an impossible command tree");
/*     */       }
/*     */     } 
/*     */   }
/*     */   
/*     */   private static Object2IntMap<CommandNode<ICompletionProvider>> a(RootCommandNode<ICompletionProvider> var0) {
/*  83 */     Object2IntOpenHashMap object2IntOpenHashMap = new Object2IntOpenHashMap();
/*  84 */     Queue<CommandNode<ICompletionProvider>> var2 = Queues.newArrayDeque();
/*  85 */     var2.add(var0);
/*     */     
/*     */     CommandNode<ICompletionProvider> var3;
/*  88 */     while ((var3 = var2.poll()) != null) {
/*  89 */       if (object2IntOpenHashMap.containsKey(var3)) {
/*     */         continue;
/*     */       }
/*  92 */       int var4 = object2IntOpenHashMap.size();
/*  93 */       object2IntOpenHashMap.put(var3, var4);
/*  94 */       var2.addAll(var3.getChildren());
/*  95 */       if (var3.getRedirect() != null) {
/*  96 */         var2.add(var3.getRedirect());
/*     */       }
/*     */     } 
/*  99 */     return (Object2IntMap<CommandNode<ICompletionProvider>>)object2IntOpenHashMap;
/*     */   }
/*     */ 
/*     */   
/*     */   private static CommandNode<ICompletionProvider>[] a(Object2IntMap<CommandNode<ICompletionProvider>> var0) {
/* 104 */     CommandNode[] arrayOfCommandNode = new CommandNode[var0.size()];
/* 105 */     for (ObjectIterator<Object2IntMap.Entry<CommandNode<ICompletionProvider>>> objectIterator = Object2IntMaps.fastIterable(var0).iterator(); objectIterator.hasNext(); ) { Object2IntMap.Entry<CommandNode<ICompletionProvider>> var3 = objectIterator.next();
/* 106 */       arrayOfCommandNode[var3.getIntValue()] = (CommandNode)var3.getKey(); }
/*     */     
/* 108 */     return (CommandNode<ICompletionProvider>[])arrayOfCommandNode;
/*     */   }
/*     */   
/*     */   private static a c(PacketDataSerializer var0) {
/* 112 */     byte var1 = var0.readByte();
/* 113 */     int[] var2 = var0.b();
/* 114 */     int var3 = ((var1 & 0x8) != 0) ? var0.i() : 0;
/* 115 */     ArgumentBuilder<ICompletionProvider, ?> var4 = a(var0, var1);
/* 116 */     return new a(var4, var1, var3, var2);
/*     */   }
/*     */   
/*     */   @Nullable
/*     */   private static ArgumentBuilder<ICompletionProvider, ?> a(PacketDataSerializer var0, byte var1) {
/* 121 */     int var2 = var1 & 0x3;
/* 122 */     if (var2 == 2) {
/* 123 */       String var3 = var0.e(32767);
/* 124 */       ArgumentType<?> var4 = ArgumentRegistry.a(var0);
/* 125 */       if (var4 == null) {
/* 126 */         return null;
/*     */       }
/* 128 */       RequiredArgumentBuilder<ICompletionProvider, ?> var5 = RequiredArgumentBuilder.argument(var3, var4);
/* 129 */       if ((var1 & 0x10) != 0) {
/* 130 */         var5.suggests(CompletionProviders.a(var0.p()));
/*     */       }
/* 132 */       return (ArgumentBuilder<ICompletionProvider, ?>)var5;
/*     */     } 
/* 134 */     if (var2 == 1) {
/* 135 */       return (ArgumentBuilder<ICompletionProvider, ?>)LiteralArgumentBuilder.literal(var0.e(32767));
/*     */     }
/* 137 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   private static void a(PacketDataSerializer var0, CommandNode<ICompletionProvider> var1, Map<CommandNode<ICompletionProvider>, Integer> var2) {
/* 142 */     byte var3 = 0;
/* 143 */     if (var1.getRedirect() != null) {
/* 144 */       var3 = (byte)(var3 | 0x8);
/*     */     }
/* 146 */     if (var1.getCommand() != null) {
/* 147 */       var3 = (byte)(var3 | 0x4);
/*     */     }
/*     */     
/* 150 */     if (var1 instanceof RootCommandNode) {
/* 151 */       var3 = (byte)(var3 | 0x0);
/* 152 */     } else if (var1 instanceof ArgumentCommandNode) {
/* 153 */       var3 = (byte)(var3 | 0x2);
/* 154 */       if (((ArgumentCommandNode)var1).getCustomSuggestions() != null) {
/* 155 */         var3 = (byte)(var3 | 0x10);
/*     */       }
/* 157 */     } else if (var1 instanceof LiteralCommandNode) {
/* 158 */       var3 = (byte)(var3 | 0x1);
/*     */     } else {
/* 160 */       throw new UnsupportedOperationException("Unknown node type " + var1);
/*     */     } 
/*     */     
/* 163 */     var0.writeByte(var3);
/*     */     
/* 165 */     var0.d(var1.getChildren().size());
/* 166 */     for (CommandNode<ICompletionProvider> var5 : (Iterable<CommandNode<ICompletionProvider>>)var1.getChildren()) {
/* 167 */       var0.d(((Integer)var2.get(var5)).intValue());
/*     */     }
/*     */     
/* 170 */     if (var1.getRedirect() != null) {
/* 171 */       var0.d(((Integer)var2.get(var1.getRedirect())).intValue());
/*     */     }
/*     */     
/* 174 */     if (var1 instanceof ArgumentCommandNode) {
/* 175 */       ArgumentCommandNode<ICompletionProvider, ?> var4 = (ArgumentCommandNode)var1;
/* 176 */       var0.a(var4.getName());
/* 177 */       ArgumentRegistry.a(var0, var4.getType());
/* 178 */       if (var4.getCustomSuggestions() != null) {
/* 179 */         var0.a(CompletionProviders.a(var4.getCustomSuggestions()));
/*     */       }
/* 181 */     } else if (var1 instanceof LiteralCommandNode) {
/* 182 */       var0.a(((LiteralCommandNode)var1).getLiteral());
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(PacketListenerPlayOut var0) {
/* 188 */     var0.a(this);
/*     */   }
/*     */ 
/*     */   
/*     */   static class a
/*     */   {
/*     */     @Nullable
/*     */     private final ArgumentBuilder<ICompletionProvider, ?> a;
/*     */     
/*     */     private final byte b;
/*     */     
/*     */     private final int c;
/*     */     private final int[] d;
/*     */     @Nullable
/*     */     private CommandNode<ICompletionProvider> e;
/*     */     
/*     */     private a(@Nullable ArgumentBuilder<ICompletionProvider, ?> var0, byte var1, int var2, int[] var3) {
/* 205 */       this.a = var0;
/* 206 */       this.b = var1;
/* 207 */       this.c = var2;
/* 208 */       this.d = var3;
/*     */     }
/*     */     
/*     */     public boolean a(a[] var0) {
/* 212 */       if (this.e == null) {
/* 213 */         if (this.a == null) {
/* 214 */           this.e = (CommandNode<ICompletionProvider>)new RootCommandNode();
/*     */         } else {
/* 216 */           if ((this.b & 0x8) != 0) {
/* 217 */             if ((var0[this.c]).e == null) {
/* 218 */               return false;
/*     */             }
/* 220 */             this.a.redirect((var0[this.c]).e);
/*     */           } 
/*     */           
/* 223 */           if ((this.b & 0x4) != 0) {
/* 224 */             this.a.executes(var0 -> 0);
/*     */           }
/*     */           
/* 227 */           this.e = this.a.build();
/*     */         } 
/*     */       }
/*     */       
/* 231 */       for (int var4 : this.d) {
/* 232 */         if ((var0[var4]).e == null) {
/* 233 */           return false;
/*     */         }
/*     */       } 
/*     */       
/* 237 */       for (int var4 : this.d) {
/* 238 */         CommandNode<ICompletionProvider> var5 = (var0[var4]).e;
/* 239 */         if (!(var5 instanceof RootCommandNode)) {
/* 240 */           this.e.addChild(var5);
/*     */         }
/*     */       } 
/*     */       
/* 244 */       return true;
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\PacketPlayOutCommands.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */