/*     */ package io.netty.resolver.dns.macos;
/*     */ 
/*     */ import io.netty.resolver.dns.DnsServerAddressStream;
/*     */ import io.netty.resolver.dns.DnsServerAddressStreamProvider;
/*     */ import io.netty.resolver.dns.DnsServerAddressStreamProviders;
/*     */ import io.netty.resolver.dns.DnsServerAddresses;
/*     */ import io.netty.util.internal.NativeLibraryLoader;
/*     */ import io.netty.util.internal.PlatformDependent;
/*     */ import io.netty.util.internal.SystemPropertyUtil;
/*     */ import io.netty.util.internal.ThrowableUtil;
/*     */ import io.netty.util.internal.logging.InternalLogger;
/*     */ import io.netty.util.internal.logging.InternalLoggerFactory;
/*     */ import java.net.InetSocketAddress;
/*     */ import java.util.Collections;
/*     */ import java.util.HashMap;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.concurrent.TimeUnit;
/*     */ import java.util.concurrent.atomic.AtomicLong;
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
/*     */ public final class MacOSDnsServerAddressStreamProvider
/*     */   implements DnsServerAddressStreamProvider
/*     */ {
/*     */   private static final Throwable UNAVAILABILITY_CAUSE;
/*  48 */   private static final InternalLogger logger = InternalLoggerFactory.getInstance(MacOSDnsServerAddressStreamProvider.class);
/*     */ 
/*     */   
/*  51 */   private static final long REFRESH_INTERVAL = TimeUnit.SECONDS.toNanos(10L); private volatile Map<String, DnsServerAddresses> currentMappings;
/*     */   
/*     */   static {
/*  54 */     Throwable cause = null;
/*     */     try {
/*  56 */       loadNativeLibrary();
/*  57 */     } catch (Throwable error) {
/*  58 */       cause = error;
/*     */     } 
/*  60 */     UNAVAILABILITY_CAUSE = cause;
/*     */   }
/*     */   private final AtomicLong lastRefresh;
/*     */   private static void loadNativeLibrary() {
/*  64 */     String name = SystemPropertyUtil.get("os.name").toLowerCase(Locale.UK).trim();
/*  65 */     if (!name.startsWith("mac")) {
/*  66 */       throw new IllegalStateException("Only supported on MacOS");
/*     */     }
/*  68 */     String staticLibName = "netty_resolver_dns_native_macos";
/*  69 */     String sharedLibName = staticLibName + '_' + PlatformDependent.normalizedArch();
/*  70 */     ClassLoader cl = PlatformDependent.getClassLoader(MacOSDnsServerAddressStreamProvider.class);
/*     */     try {
/*  72 */       NativeLibraryLoader.load(sharedLibName, cl);
/*  73 */     } catch (UnsatisfiedLinkError e1) {
/*     */       try {
/*  75 */         NativeLibraryLoader.load(staticLibName, cl);
/*  76 */         logger.debug("Failed to load {}", sharedLibName, e1);
/*  77 */       } catch (UnsatisfiedLinkError e2) {
/*  78 */         ThrowableUtil.addSuppressed(e1, e2);
/*  79 */         throw e1;
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   public static boolean isAvailable() {
/*  85 */     return (UNAVAILABILITY_CAUSE == null);
/*     */   }
/*     */   
/*     */   public static void ensureAvailability() {
/*  89 */     if (UNAVAILABILITY_CAUSE != null) {
/*  90 */       throw (Error)(new UnsatisfiedLinkError("failed to load the required native library"))
/*  91 */         .initCause(UNAVAILABILITY_CAUSE);
/*     */     }
/*     */   }
/*     */   
/*     */   public static Throwable unavailabilityCause() {
/*  96 */     return UNAVAILABILITY_CAUSE;
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public MacOSDnsServerAddressStreamProvider() {
/* 103 */     this.currentMappings = retrieveCurrentMappings();
/* 104 */     this.lastRefresh = new AtomicLong(System.nanoTime());
/*     */     ensureAvailability();
/*     */   } private static Map<String, DnsServerAddresses> retrieveCurrentMappings() {
/* 107 */     DnsResolver[] resolvers = resolvers();
/*     */     
/* 109 */     if (resolvers == null || resolvers.length == 0) {
/* 110 */       return Collections.emptyMap();
/*     */     }
/* 112 */     Map<String, DnsServerAddresses> resolverMap = new HashMap<String, DnsServerAddresses>(resolvers.length);
/* 113 */     for (DnsResolver resolver : resolvers) {
/*     */       
/* 115 */       if (!"mdns".equalsIgnoreCase(resolver.options())) {
/*     */ 
/*     */         
/* 118 */         InetSocketAddress[] nameservers = resolver.nameservers();
/* 119 */         if (nameservers != null && nameservers.length != 0)
/*     */         
/*     */         { 
/* 122 */           String domain = resolver.domain();
/* 123 */           if (domain == null)
/*     */           {
/* 125 */             domain = "";
/*     */           }
/* 127 */           InetSocketAddress[] servers = resolver.nameservers();
/* 128 */           for (int a = 0; a < servers.length; a++) {
/* 129 */             InetSocketAddress address = servers[a];
/*     */             
/* 131 */             if (address.getPort() == 0) {
/* 132 */               int port = resolver.port();
/* 133 */               if (port == 0) {
/* 134 */                 port = 53;
/*     */               }
/* 136 */               servers[a] = new InetSocketAddress(address.getAddress(), port);
/*     */             } 
/*     */           } 
/*     */           
/* 140 */           resolverMap.put(domain, DnsServerAddresses.sequential(servers)); } 
/*     */       } 
/* 142 */     }  return resolverMap;
/*     */   }
/*     */ 
/*     */   
/*     */   public DnsServerAddressStream nameServerAddressStream(String hostname) {
/* 147 */     long last = this.lastRefresh.get();
/* 148 */     Map<String, DnsServerAddresses> resolverMap = this.currentMappings;
/* 149 */     if (System.nanoTime() - last > REFRESH_INTERVAL)
/*     */     {
/*     */       
/* 152 */       if (this.lastRefresh.compareAndSet(last, System.nanoTime())) {
/* 153 */         resolverMap = this.currentMappings = retrieveCurrentMappings();
/*     */       }
/*     */     }
/*     */     
/* 157 */     String originalHostname = hostname;
/*     */     while (true) {
/* 159 */       int i = hostname.indexOf('.', 1);
/* 160 */       if (i < 0 || i == hostname.length() - 1) {
/*     */         
/* 162 */         DnsServerAddresses dnsServerAddresses = resolverMap.get("");
/* 163 */         if (dnsServerAddresses != null) {
/* 164 */           return dnsServerAddresses.stream();
/*     */         }
/* 166 */         return DnsServerAddressStreamProviders.unixDefault().nameServerAddressStream(originalHostname);
/*     */       } 
/*     */       
/* 169 */       DnsServerAddresses addresses = resolverMap.get(hostname);
/* 170 */       if (addresses != null) {
/* 171 */         return addresses.stream();
/*     */       }
/*     */       
/* 174 */       hostname = hostname.substring(i + 1);
/*     */     } 
/*     */   }
/*     */   
/*     */   private static native DnsResolver[] resolvers();
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\resolver\dns\macos\MacOSDnsServerAddressStreamProvider.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */