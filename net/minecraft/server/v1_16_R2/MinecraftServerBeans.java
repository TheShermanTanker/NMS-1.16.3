/*     */ package net.minecraft.server.v1_16_R2;
/*     */ 
/*     */ import java.lang.management.ManagementFactory;
/*     */ import java.util.Arrays;
/*     */ import java.util.List;
/*     */ import java.util.Map;
/*     */ import java.util.Objects;
/*     */ import java.util.function.Function;
/*     */ import java.util.function.Supplier;
/*     */ import java.util.stream.Collectors;
/*     */ import java.util.stream.Stream;
/*     */ import javax.annotation.Nullable;
/*     */ import javax.management.Attribute;
/*     */ import javax.management.AttributeList;
/*     */ import javax.management.DynamicMBean;
/*     */ import javax.management.JMException;
/*     */ import javax.management.MBeanAttributeInfo;
/*     */ import javax.management.MBeanInfo;
/*     */ import javax.management.MalformedObjectNameException;
/*     */ import javax.management.ObjectName;
/*     */ import org.apache.logging.log4j.LogManager;
/*     */ import org.apache.logging.log4j.Logger;
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ public final class MinecraftServerBeans
/*     */   implements DynamicMBean
/*     */ {
/*  30 */   private static final Logger LOGGER = LogManager.getLogger();
/*     */   private final MinecraftServer b;
/*     */   
/*     */   private MinecraftServerBeans(MinecraftServer var0) {
/*  34 */     this
/*     */ 
/*     */       
/*  37 */       .d = (Map<String, a>)Stream.<a>of(new a[] { new a("tickTimes", this::b, "Historical tick times (ms)", long[].class), new a("averageTickTime", this::a, "Current average tick time (ms)", long.class) }).collect(Collectors.toMap(var0 -> a.b(var0), Function.identity()));
/*     */ 
/*     */     
/*  40 */     this.b = var0;
/*     */ 
/*     */ 
/*     */     
/*  44 */     MBeanAttributeInfo[] var1 = (MBeanAttributeInfo[])this.d.values().stream().map(var0 -> a.c((a)var0)).toArray(var0 -> new MBeanAttributeInfo[var0]);
/*     */     
/*  46 */     this.c = new MBeanInfo(MinecraftServerBeans.class.getSimpleName(), "metrics for dedicated server", var1, null, null, new javax.management.MBeanNotificationInfo[0]);
/*     */   }
/*     */   private final MBeanInfo c; private final Map<String, a> d;
/*     */   public static void a(MinecraftServer var0) {
/*     */     try {
/*  51 */       ManagementFactory.getPlatformMBeanServer().registerMBean(new MinecraftServerBeans(var0), new ObjectName("net.minecraft.server.v1_16_R2:type=Server"));
/*     */ 
/*     */     
/*     */     }
/*  55 */     catch (MalformedObjectNameException|javax.management.InstanceAlreadyExistsException|javax.management.MBeanRegistrationException|javax.management.NotCompliantMBeanException var1) {
/*  56 */       LOGGER.warn("Failed to initialise server as JMX bean", var1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private float a() {
/*  61 */     return this.b.aN();
/*     */   }
/*     */   
/*     */   private long[] b() {
/*  65 */     return this.b.h;
/*     */   }
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Object getAttribute(String var0) {
/*  71 */     a var1 = this.d.get(var0);
/*  72 */     return (var1 == null) ? null : 
/*     */       
/*  74 */       a.a(var1).get();
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public void setAttribute(Attribute var0) {}
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeList getAttributes(String[] var0) {
/*  88 */     List<Attribute> var1 = (List<Attribute>)Arrays.<String>stream(var0).map(this.d::get).filter(Objects::nonNull).map(var0 -> new Attribute(a.b(var0), a.a(var0).get())).collect(Collectors.toList());
/*  89 */     return new AttributeList(var1);
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public AttributeList setAttributes(AttributeList var0) {
/*  95 */     return new AttributeList();
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @Nullable
/*     */   public Object invoke(String var0, Object[] var1, String[] var2) {
/* 102 */     return null;
/*     */   }
/*     */ 
/*     */   
/*     */   public MBeanInfo getMBeanInfo() {
/* 107 */     return this.c;
/*     */   }
/*     */   
/*     */   static final class a {
/*     */     private final String a;
/*     */     private final Supplier<Object> b;
/*     */     private final String c;
/*     */     private final Class<?> d;
/*     */     
/*     */     private a(String var0, Supplier<Object> var1, String var2, Class<?> var3) {
/* 117 */       this.a = var0;
/* 118 */       this.b = var1;
/* 119 */       this.c = var2;
/* 120 */       this.d = var3;
/*     */     }
/*     */     
/*     */     private MBeanAttributeInfo a() {
/* 124 */       return new MBeanAttributeInfo(this.a, this.d.getSimpleName(), this.c, true, false, false);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\net\minecraft\server\v1_16_R2\MinecraftServerBeans.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */