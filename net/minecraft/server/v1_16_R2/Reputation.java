/*     */ package net.minecraft.server.v1_16_R2;
/*     */ import com.destroystokyo.paper.entity.villager.ReputationType;
/*     */ import com.google.common.collect.Maps;
/*     */ import com.google.common.collect.Sets;
/*     */ import com.mojang.datafixers.kinds.App;
/*     */ import com.mojang.datafixers.kinds.Applicative;
/*     */ import com.mojang.datafixers.util.Function3;
/*     */ import com.mojang.serialization.DataResult;
/*     */ import com.mojang.serialization.Decoder;
/*     */ import com.mojang.serialization.Dynamic;
/*     */ import com.mojang.serialization.DynamicOps;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntMap;
/*     */ import it.unimi.dsi.fastutil.objects.Object2IntOpenHashMap;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectArrayList;
/*     */ import it.unimi.dsi.fastutil.objects.ObjectIterator;
/*     */ import java.util.Arrays;
/*     */ import java.util.Collection;
/*     */ import java.util.Iterator;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Random;
/*     */ import java.util.Set;
/*     */ import java.util.UUID;
/*     */ import java.util.function.Predicate;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Stream;
/*     */ 
/*     */ public class Reputation {
/*  29 */   private final Map<UUID, a> a = Maps.newHashMap(); public Map<UUID, a> getReputations() { return this.a; }
/*     */ 
/*     */ 
/*     */   
/*     */   public void b() {
/*  34 */     Iterator<a> iterator = this.a.values().iterator();
/*     */     
/*  36 */     while (iterator.hasNext()) {
/*  37 */       a reputation_a = iterator.next();
/*     */       
/*  39 */       reputation_a.a();
/*  40 */       if (reputation_a.b()) {
/*  41 */         iterator.remove();
/*     */       }
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   private Stream<b> c() {
/*  48 */     return this.a.entrySet().stream().flatMap(entry -> ((a)entry.getValue()).a((UUID)entry.getKey()));
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   private List<b> decompress() {
/*  55 */     ObjectArrayList<b> objectArrayList = new ObjectArrayList();
/*  56 */     for (Map.Entry<UUID, a> entry : getReputations().entrySet()) {
/*  57 */       for (b cur : ((a)entry.getValue()).decompress(entry.getKey())) {
/*  58 */         if (cur.a() != 0)
/*  59 */           objectArrayList.add(cur); 
/*     */       } 
/*     */     } 
/*  62 */     return (List<b>)objectArrayList;
/*     */   }
/*     */ 
/*     */   
/*     */   private Collection<b> a(Random random, int i) {
/*  67 */     List<b> list = decompress();
/*     */     
/*  69 */     if (list.isEmpty()) {
/*  70 */       return Collections.emptyList();
/*     */     }
/*  72 */     int[] aint = new int[list.size()];
/*  73 */     int j = 0;
/*     */     
/*  75 */     for (int k = 0; k < list.size(); k++) {
/*  76 */       b reputation_b = list.get(k);
/*     */       
/*  78 */       j += Math.abs(reputation_b.a());
/*  79 */       aint[k] = j - 1;
/*     */     } 
/*     */     
/*  82 */     Set<b> set = Sets.newIdentityHashSet();
/*     */     
/*  84 */     for (int l = 0; l < i; l++) {
/*  85 */       int i1 = random.nextInt(j);
/*  86 */       int j1 = Arrays.binarySearch(aint, i1);
/*     */       
/*  88 */       set.add(list.get((j1 < 0) ? (-j1 - 1) : j1));
/*     */     } 
/*     */     
/*  91 */     return set;
/*     */   }
/*     */ 
/*     */   
/*     */   private a a(UUID uuid) {
/*  96 */     return this.a.computeIfAbsent(uuid, uuid1 -> new a());
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public void a(Reputation reputation, Random random, int i) {
/* 102 */     Collection<b> collection = reputation.a(random, i);
/*     */     
/* 104 */     collection.forEach(reputation_b -> {
/*     */           int j = reputation_b.c - reputation_b.b.j;
/*     */           if (j >= 2) {
/*     */             (a(reputation_b.a)).a.mergeInt(reputation_b.b, j, Reputation::a);
/*     */           }
/*     */         });
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public int a(UUID uuid, Predicate<ReputationType> predicate) {
/* 115 */     a reputation_a = this.a.get(uuid);
/*     */     
/* 117 */     return (reputation_a != null) ? reputation_a.a(predicate) : 0;
/*     */   }
/*     */   
/*     */   public void a(UUID uuid, ReputationType reputationtype, int i) {
/* 121 */     a reputation_a = a(uuid);
/*     */     
/* 123 */     reputation_a.a.mergeInt(reputationtype, i, (integer, integer1) -> Integer.valueOf(a(reputationtype, integer.intValue(), integer1.intValue())));
/*     */ 
/*     */     
/* 126 */     reputation_a.a(reputationtype);
/* 127 */     if (reputation_a.b()) {
/* 128 */       this.a.remove(uuid);
/*     */     }
/*     */   }
/*     */ 
/*     */   
/*     */   public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
/* 134 */     return new Dynamic(dynamicops, dynamicops.createList(decompress().stream().map(reputation_b -> reputation_b.a(dynamicops))
/*     */           
/* 136 */           .map(Dynamic::getValue)));
/*     */   }
/*     */   
/*     */   public void a(Dynamic<?> dynamic) {
/* 140 */     dynamic.asStream().map(b::a).flatMap(dataresult -> SystemUtils.a(dataresult.result()))
/*     */       
/* 142 */       .forEach(reputation_b -> (a(reputation_b.a)).a.put(reputation_b.b, reputation_b.c));
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   private static int a(int i, int j) {
/* 148 */     return Math.max(i, j);
/*     */   }
/*     */   
/*     */   private int a(ReputationType reputationtype, int i, int j) {
/* 152 */     int k = i + j;
/*     */     
/* 154 */     return (k > reputationtype.h) ? Math.max(reputationtype.h, i) : k;
/*     */   }
/*     */   public static class a { private final Object2IntMap<ReputationType> a;
/*     */     
/*     */     private Object2IntMap<ReputationType> getEntries() {
/* 159 */       return this.a;
/*     */     }
/*     */     public a() {
/* 162 */       this.a = (Object2IntMap<ReputationType>)new Object2IntOpenHashMap();
/*     */     }
/*     */ 
/*     */     
/*     */     public int a(Predicate<ReputationType> predicate) {
/* 167 */       int weight = 0;
/* 168 */       for (ObjectIterator<Object2IntMap.Entry<ReputationType>> objectIterator = getEntries().object2IntEntrySet().iterator(); objectIterator.hasNext(); ) { Object2IntMap.Entry<ReputationType> entry = objectIterator.next();
/* 169 */         if (predicate.test((ReputationType)entry.getKey())) {
/* 170 */           weight += entry.getIntValue() * ((ReputationType)entry.getKey()).getWeight();
/*     */         } }
/*     */       
/* 173 */       return weight;
/*     */     }
/*     */     
/*     */     public List<Reputation.b> decompress(UUID uuid) {
/* 177 */       ObjectArrayList<Reputation.b> objectArrayList = new ObjectArrayList();
/* 178 */       for (ObjectIterator<Object2IntMap.Entry<ReputationType>> objectIterator = getEntries().object2IntEntrySet().iterator(); objectIterator.hasNext(); ) { Object2IntMap.Entry<ReputationType> entry = objectIterator.next();
/* 179 */         objectArrayList.add(new Reputation.b(uuid, (ReputationType)entry.getKey(), entry.getIntValue())); }
/*     */       
/* 181 */       return (List<Reputation.b>)objectArrayList;
/*     */     }
/*     */ 
/*     */     
/*     */     public Stream<Reputation.b> a(UUID uuid) {
/* 186 */       return this.a.object2IntEntrySet().stream().map(entry -> new Reputation.b(uuid, (ReputationType)entry.getKey(), entry.getIntValue()));
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public void a() {
/* 192 */       ObjectIterator objectiterator = this.a.object2IntEntrySet().iterator();
/*     */       
/* 194 */       while (objectiterator.hasNext()) {
/* 195 */         Object2IntMap.Entry<ReputationType> entry = (Object2IntMap.Entry<ReputationType>)objectiterator.next();
/* 196 */         int i = entry.getIntValue() - ((ReputationType)entry.getKey()).i;
/*     */         
/* 198 */         if (i < 2) {
/* 199 */           objectiterator.remove(); continue;
/*     */         } 
/* 201 */         entry.setValue(i);
/*     */       } 
/*     */     }
/*     */ 
/*     */ 
/*     */     
/*     */     public boolean b() {
/* 208 */       return this.a.isEmpty();
/*     */     }
/*     */     
/*     */     public void a(ReputationType reputationtype) {
/* 212 */       int i = this.a.getInt(reputationtype);
/*     */       
/* 214 */       if (i > reputationtype.h) {
/* 215 */         this.a.put(reputationtype, reputationtype.h);
/*     */       }
/*     */       
/* 218 */       if (i < 2) {
/* 219 */         b(reputationtype);
/*     */       }
/*     */     }
/*     */ 
/*     */     
/*     */     public void b(ReputationType reputationtype) {
/* 225 */       this.a.removeInt(reputationtype);
/*     */     }
/*     */ 
/*     */     
/* 229 */     private static final ReputationType[] REPUTATION_TYPES = ReputationType.values();
/*     */     public com.destroystokyo.paper.entity.villager.Reputation getPaperReputation() {
/* 231 */       int[] reputation = new int[REPUTATION_TYPES.length];
/* 232 */       reputation[ReputationType.MAJOR_NEGATIVE.ordinal()] = this.a.getOrDefault(ReputationType.MAJOR_NEGATIVE, 0);
/* 233 */       reputation[ReputationType.MAJOR_POSITIVE.ordinal()] = this.a.getOrDefault(ReputationType.MAJOR_POSITIVE, 0);
/* 234 */       reputation[ReputationType.MINOR_NEGATIVE.ordinal()] = this.a.getOrDefault(ReputationType.MINOR_NEGATIVE, 0);
/* 235 */       reputation[ReputationType.MINOR_POSITIVE.ordinal()] = this.a.getOrDefault(ReputationType.MINOR_POSITIVE, 0);
/* 236 */       reputation[ReputationType.TRADING.ordinal()] = this.a.getOrDefault(ReputationType.TRADING, 0);
/* 237 */       return ReputationConstructor.construct(reputation);
/*     */     }
/*     */     
/*     */     public void assignFromPaperReputation(com.destroystokyo.paper.entity.villager.Reputation rep) {
/*     */       int val;
/* 242 */       if ((val = rep.getReputation(ReputationType.MAJOR_NEGATIVE)) != 0) this.a.put(ReputationType.MAJOR_NEGATIVE, val); 
/* 243 */       if ((val = rep.getReputation(ReputationType.MAJOR_POSITIVE)) != 0) this.a.put(ReputationType.MAJOR_POSITIVE, val); 
/* 244 */       if ((val = rep.getReputation(ReputationType.MINOR_NEGATIVE)) != 0) this.a.put(ReputationType.MINOR_NEGATIVE, val); 
/* 245 */       if ((val = rep.getReputation(ReputationType.MINOR_POSITIVE)) != 0) this.a.put(ReputationType.MINOR_POSITIVE, val); 
/* 246 */       if ((val = rep.getReputation(ReputationType.TRADING)) != 0) this.a.put(ReputationType.TRADING, val);
/*     */     
/*     */     } }
/*     */ 
/*     */   
/*     */   static class b
/*     */   {
/*     */     public final UUID a;
/*     */     public final ReputationType b;
/*     */     public final int c;
/*     */     
/*     */     public b(UUID uuid, ReputationType reputationtype, int i) {
/* 258 */       this.a = uuid;
/* 259 */       this.b = reputationtype;
/* 260 */       this.c = i;
/*     */     }
/*     */     
/*     */     public int a() {
/* 264 */       return this.c * this.b.g;
/*     */     }
/*     */     
/*     */     public String toString() {
/* 268 */       return "GossipEntry{target=" + this.a + ", type=" + this.b + ", value=" + this.c + '}';
/*     */     }
/*     */     
/*     */     public <T> Dynamic<T> a(DynamicOps<T> dynamicops) {
/* 272 */       return new Dynamic(dynamicops, dynamicops.createMap((Map)ImmutableMap.of(dynamicops.createString("Target"), MinecraftSerializableUUID.a.encodeStart(dynamicops, this.a).result().orElseThrow(RuntimeException::new), dynamicops.createString("Type"), dynamicops.createString(this.b.f), dynamicops.createString("Value"), dynamicops.createInt(this.c))));
/*     */     }
/*     */     
/*     */     public static DataResult<b> a(Dynamic<?> dynamic) {
/* 276 */       return DataResult.unbox(DataResult.instance().group((App)dynamic.get("Target").read((Decoder)MinecraftSerializableUUID.a), (App)dynamic.get("Type").asString().map(ReputationType::a), (App)dynamic.get("Value").asNumber().map(Number::intValue)).apply((Applicative)DataResult.instance(), b::new));
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\Reputation.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */