/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import com.google.common.collect.Lists;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.function.Function;
/*     */ import java.util.stream.Collectors;
/*     */ 
/*     */ public class TickListChunk<T>
/*     */   implements TickList<T> {
/*     */   private final List<a<T>> a;
/*     */   private final Function<T, MinecraftKey> b;
/*     */   
/*     */   public TickListChunk(Function<T, MinecraftKey> function, List<NextTickListEntry<T>> list, long i) {
/*  15 */     this(function, (List<a<T>>)list.stream().map(nextticklistentry -> new a(nextticklistentry.b(), nextticklistentry.a, (int)(nextticklistentry.b - i), nextticklistentry.c))
/*     */         
/*  17 */         .collect(Collectors.toList()));
/*     */   }
/*     */   
/*     */   private TickListChunk(Function<T, MinecraftKey> function, List<a<T>> list) {
/*  21 */     this.a = list;
/*  22 */     this.b = function;
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean a(BlockPosition blockposition, T t0) {
/*  27 */     return false;
/*     */   }
/*     */ 
/*     */   
/*     */   public void a(BlockPosition blockposition, T t0, int i, TickListPriority ticklistpriority) {
/*  32 */     this.a.add(new a<>(t0, blockposition, i, ticklistpriority));
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean b(BlockPosition blockposition, T t0) {
/*  37 */     return false;
/*     */   }
/*     */   
/*     */   public NBTTagList b() {
/*  41 */     NBTTagList nbttaglist = new NBTTagList();
/*  42 */     Iterator<a<T>> iterator = this.a.iterator();
/*     */     
/*  44 */     while (iterator.hasNext()) {
/*  45 */       a<T> ticklistchunk_a = iterator.next();
/*  46 */       NBTTagCompound nbttagcompound = new NBTTagCompound();
/*     */       
/*  48 */       nbttagcompound.setString("i", ((MinecraftKey)this.b.apply(ticklistchunk_a.d)).toString());
/*  49 */       nbttagcompound.setInt("x", ticklistchunk_a.a.getX());
/*  50 */       nbttagcompound.setInt("y", ticklistchunk_a.a.getY());
/*  51 */       nbttagcompound.setInt("z", ticklistchunk_a.a.getZ());
/*  52 */       nbttagcompound.setInt("t", ticklistchunk_a.b);
/*  53 */       nbttagcompound.setInt("p", ticklistchunk_a.c.a());
/*  54 */       nbttaglist.add(nbttagcompound);
/*     */     } 
/*     */     
/*  57 */     return nbttaglist;
/*     */   }
/*     */   
/*  60 */   private static final int MAX_TICK_DELAY = Integer.getInteger("paper.ticklist-max-tick-delay", -1).intValue();
/*     */   
/*     */   public static <T> TickListChunk<T> a(NBTTagList nbttaglist, Function<T, MinecraftKey> function, Function<MinecraftKey, T> function1) {
/*  63 */     List<a<T>> list = Lists.newArrayList();
/*     */     
/*  65 */     for (int i = 0; i < nbttaglist.size(); i++) {
/*  66 */       NBTTagCompound nbttagcompound = nbttaglist.getCompound(i);
/*  67 */       T t0 = function1.apply(new MinecraftKey(nbttagcompound.getString("i")));
/*     */       
/*  69 */       if (t0 != null) {
/*  70 */         BlockPosition blockposition = new BlockPosition(nbttagcompound.getInt("x"), nbttagcompound.getInt("y"), nbttagcompound.getInt("z"));
/*     */ 
/*     */         
/*  73 */         int delay = nbttagcompound.getInt("t");
/*  74 */         if (MAX_TICK_DELAY > 0 && delay > MAX_TICK_DELAY) {
/*  75 */           MinecraftServer.LOGGER.warn("Dropping tick for pos " + blockposition + ", tick delay " + delay);
/*     */         } else {
/*     */           
/*  78 */           list.add(new a<>(t0, blockposition, delay, TickListPriority.a(nbttagcompound.getInt("p"))));
/*     */         } 
/*     */       } 
/*     */     } 
/*     */     
/*  83 */     return new TickListChunk<>(function, list);
/*     */   }
/*     */   
/*     */   public void a(TickList<T> ticklist) {
/*  87 */     this.a.forEach(ticklistchunk_a -> ticklist.a(ticklistchunk_a.a, ticklistchunk_a.d, ticklistchunk_a.b, ticklistchunk_a.c));
/*     */   }
/*     */ 
/*     */   
/*     */   static class a<T>
/*     */   {
/*     */     private final T d;
/*     */     
/*     */     public final BlockPosition a;
/*     */     public final int b;
/*     */     public final TickListPriority c;
/*     */     
/*     */     private a(T t0, BlockPosition blockposition, int i, TickListPriority ticklistpriority) {
/* 100 */       this.d = t0;
/* 101 */       this.a = blockposition;
/* 102 */       this.b = i;
/* 103 */       this.c = ticklistpriority;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 107 */       return (new StringBuilder()).append(this.d).append(": ").append(this.a).append(", ").append(this.b).append(", ").append(this.c).toString();
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TickListChunk.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */