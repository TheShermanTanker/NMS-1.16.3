/*    */ package net.minecraft.server.v1_16_R2;
/*    */ 
/*    */ import java.util.Comparator;
/*    */ import org.bukkit.plugin.Plugin;
/*    */ 
/*    */ public class TicketType<T> {
/*    */   private final String i;
/*    */   private final Comparator<T> j;
/*    */   public long loadPeriod;
/* 10 */   public static final TicketType<Unit> START = a("start", (unit, unit1) -> 0);
/*    */ 
/*    */   
/* 13 */   public static final TicketType<Unit> DRAGON = a("dragon", (unit, unit1) -> 0);
/*    */ 
/*    */   
/* 16 */   public static final TicketType<ChunkCoordIntPair> PLAYER = a("player", Comparator.comparingLong(ChunkCoordIntPair::pair));
/* 17 */   public static final TicketType<ChunkCoordIntPair> FORCED = a("forced", Comparator.comparingLong(ChunkCoordIntPair::pair));
/* 18 */   public static final TicketType<ChunkCoordIntPair> LIGHT = a("light", Comparator.comparingLong(ChunkCoordIntPair::pair));
/* 19 */   public static final TicketType<BlockPosition> PORTAL = a("portal", BaseBlockPosition::compareTo, 300);
/* 20 */   public static final TicketType<Long> LOGIN = a("login", Long::compareTo, 100);
/* 21 */   public static final TicketType<Integer> POST_TELEPORT = a("post_teleport", Integer::compareTo, 5);
/* 22 */   public static final TicketType<ChunkCoordIntPair> UNKNOWN = a("unknown", Comparator.comparingLong(ChunkCoordIntPair::pair), 1); public static final TicketType<Plugin> PLUGIN_TICKET;
/* 23 */   public static final TicketType<Unit> PLUGIN = a("plugin", (a, b) -> 0); static {
/* 24 */     PLUGIN_TICKET = a("plugin_ticket", (plugin1, plugin2) -> plugin1.getClass().getName().compareTo(plugin2.getClass().getName()));
/* 25 */   } public static final TicketType<Long> FUTURE_AWAIT = a("future_await", Long::compareTo);
/* 26 */   public static final TicketType<Long> ASYNC_LOAD = a("async_load", Long::compareTo);
/* 27 */   public static final TicketType<ChunkCoordIntPair> PRIORITY = a("priority", Comparator.comparingLong(ChunkCoordIntPair::pair), 300);
/* 28 */   public static final TicketType<ChunkCoordIntPair> URGENT = a("urgent", Comparator.comparingLong(ChunkCoordIntPair::pair), 300);
/* 29 */   public static final TicketType<Long> DELAYED_UNLOAD = a("delayed_unload", Long::compareTo);
/* 30 */   public static final TicketType<Long> REQUIRED_LOAD = a("required_load", Long::compareTo);
/*    */   
/*    */   boolean delayUnloadViable = true;
/*    */   
/*    */   static {
/* 35 */     LIGHT.delayUnloadViable = false;
/* 36 */     PLUGIN.delayUnloadViable = false;
/* 37 */     PRIORITY.delayUnloadViable = false;
/* 38 */     URGENT.delayUnloadViable = false;
/* 39 */     DELAYED_UNLOAD.delayUnloadViable = false;
/*    */   }
/*    */   
/*    */   public static <T> TicketType<T> a(String s, Comparator<T> comparator) {
/* 43 */     return new TicketType<>(s, comparator, 0L);
/*    */   }
/*    */   
/*    */   public static <T> TicketType<T> a(String s, Comparator<T> comparator, int i) {
/* 47 */     return new TicketType<>(s, comparator, i);
/*    */   }
/*    */   
/*    */   protected TicketType(String s, Comparator<T> comparator, long i) {
/* 51 */     this.i = s;
/* 52 */     this.j = comparator;
/* 53 */     this.loadPeriod = i;
/*    */   }
/*    */   
/*    */   public String toString() {
/* 57 */     return this.i;
/*    */   }
/*    */   
/*    */   public Comparator<T> a() {
/* 61 */     return this.j;
/*    */   }
/*    */   
/*    */   public long b() {
/* 65 */     return this.loadPeriod;
/*    */   }
/*    */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\TicketType.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */