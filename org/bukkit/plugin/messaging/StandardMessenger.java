/*     */ package org.bukkit.plugin.messaging;
/*     */ 
/*     */ import com.google.common.collect.ImmutableSet;
/*     */ import java.util.HashMap;
/*     */ import java.util.HashSet;
/*     */ import java.util.Locale;
/*     */ import java.util.Map;
/*     */ import java.util.Set;
/*     */ import java.util.logging.Level;
/*     */ import org.bukkit.entity.Player;
/*     */ import org.bukkit.plugin.Plugin;
/*     */ import org.jetbrains.annotations.NotNull;
/*     */ 
/*     */ 
/*     */ 
/*     */ public class StandardMessenger
/*     */   implements Messenger
/*     */ {
/*  19 */   private final Map<String, Set<PluginMessageListenerRegistration>> incomingByChannel = new HashMap<>();
/*  20 */   private final Map<Plugin, Set<PluginMessageListenerRegistration>> incomingByPlugin = new HashMap<>();
/*  21 */   private final Map<String, Set<Plugin>> outgoingByChannel = new HashMap<>();
/*  22 */   private final Map<Plugin, Set<String>> outgoingByPlugin = (Map<Plugin, Set<String>>)new HashMap<>();
/*  23 */   private final Object incomingLock = new Object();
/*  24 */   private final Object outgoingLock = new Object();
/*     */   
/*     */   private void addToOutgoing(@NotNull Plugin plugin, @NotNull String channel) {
/*  27 */     synchronized (this.outgoingLock) {
/*  28 */       Set<Plugin> plugins = this.outgoingByChannel.get(channel);
/*  29 */       Set<String> channels = this.outgoingByPlugin.get(plugin);
/*     */       
/*  31 */       if (plugins == null) {
/*  32 */         plugins = new HashSet<>();
/*  33 */         this.outgoingByChannel.put(channel, plugins);
/*     */       } 
/*     */       
/*  36 */       if (channels == null) {
/*  37 */         channels = new HashSet<>();
/*  38 */         this.outgoingByPlugin.put(plugin, channels);
/*     */       } 
/*     */       
/*  41 */       plugins.add(plugin);
/*  42 */       channels.add(channel);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeFromOutgoing(@NotNull Plugin plugin, @NotNull String channel) {
/*  47 */     synchronized (this.outgoingLock) {
/*  48 */       Set<Plugin> plugins = this.outgoingByChannel.get(channel);
/*  49 */       Set<String> channels = this.outgoingByPlugin.get(plugin);
/*     */       
/*  51 */       if (plugins != null) {
/*  52 */         plugins.remove(plugin);
/*     */         
/*  54 */         if (plugins.isEmpty()) {
/*  55 */           this.outgoingByChannel.remove(channel);
/*     */         }
/*     */       } 
/*     */       
/*  59 */       if (channels != null) {
/*  60 */         channels.remove(channel);
/*     */         
/*  62 */         if (channels.isEmpty()) {
/*  63 */           this.outgoingByChannel.remove(channel);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeFromOutgoing(@NotNull Plugin plugin) {
/*  70 */     synchronized (this.outgoingLock) {
/*  71 */       Set<String> channels = this.outgoingByPlugin.get(plugin);
/*     */       
/*  73 */       if (channels != null) {
/*  74 */         String[] toRemove = channels.<String>toArray(new String[channels.size()]);
/*     */         
/*  76 */         this.outgoingByPlugin.remove(plugin);
/*     */         
/*  78 */         for (String channel : toRemove) {
/*  79 */           removeFromOutgoing(plugin, channel);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void addToIncoming(@NotNull PluginMessageListenerRegistration registration) {
/*  86 */     synchronized (this.incomingLock) {
/*  87 */       Set<PluginMessageListenerRegistration> registrations = this.incomingByChannel.get(registration.getChannel());
/*     */       
/*  89 */       if (registrations == null) {
/*  90 */         registrations = new HashSet<>();
/*  91 */         this.incomingByChannel.put(registration.getChannel(), registrations);
/*     */       }
/*  93 */       else if (registrations.contains(registration)) {
/*  94 */         throw new IllegalArgumentException("This registration already exists");
/*     */       } 
/*     */ 
/*     */       
/*  98 */       registrations.add(registration);
/*     */       
/* 100 */       registrations = this.incomingByPlugin.get(registration.getPlugin());
/*     */       
/* 102 */       if (registrations == null) {
/* 103 */         registrations = new HashSet<>();
/* 104 */         this.incomingByPlugin.put(registration.getPlugin(), registrations);
/*     */       }
/* 106 */       else if (registrations.contains(registration)) {
/* 107 */         throw new IllegalArgumentException("This registration already exists");
/*     */       } 
/*     */ 
/*     */       
/* 111 */       registrations.add(registration);
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeFromIncoming(@NotNull PluginMessageListenerRegistration registration) {
/* 116 */     synchronized (this.incomingLock) {
/* 117 */       Set<PluginMessageListenerRegistration> registrations = this.incomingByChannel.get(registration.getChannel());
/*     */       
/* 119 */       if (registrations != null) {
/* 120 */         registrations.remove(registration);
/*     */         
/* 122 */         if (registrations.isEmpty()) {
/* 123 */           this.incomingByChannel.remove(registration.getChannel());
/*     */         }
/*     */       } 
/*     */       
/* 127 */       registrations = this.incomingByPlugin.get(registration.getPlugin());
/*     */       
/* 129 */       if (registrations != null) {
/* 130 */         registrations.remove(registration);
/*     */         
/* 132 */         if (registrations.isEmpty()) {
/* 133 */           this.incomingByPlugin.remove(registration.getPlugin());
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeFromIncoming(@NotNull Plugin plugin, @NotNull String channel) {
/* 140 */     synchronized (this.incomingLock) {
/* 141 */       Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
/*     */       
/* 143 */       if (registrations != null) {
/* 144 */         PluginMessageListenerRegistration[] toRemove = registrations.<PluginMessageListenerRegistration>toArray(new PluginMessageListenerRegistration[registrations.size()]);
/*     */         
/* 146 */         for (PluginMessageListenerRegistration registration : toRemove) {
/* 147 */           if (registration.getChannel().equals(channel)) {
/* 148 */             removeFromIncoming(registration);
/*     */           }
/*     */         } 
/*     */       } 
/*     */     } 
/*     */   }
/*     */   
/*     */   private void removeFromIncoming(@NotNull Plugin plugin) {
/* 156 */     synchronized (this.incomingLock) {
/* 157 */       Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
/*     */       
/* 159 */       if (registrations != null) {
/* 160 */         PluginMessageListenerRegistration[] toRemove = registrations.<PluginMessageListenerRegistration>toArray(new PluginMessageListenerRegistration[registrations.size()]);
/*     */         
/* 162 */         this.incomingByPlugin.remove(plugin);
/*     */         
/* 164 */         for (PluginMessageListenerRegistration registration : toRemove) {
/* 165 */           removeFromIncoming(registration);
/*     */         }
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isReservedChannel(@NotNull String channel) {
/* 173 */     channel = validateAndCorrectChannel(channel);
/*     */     
/* 175 */     return (channel.equals("minecraft:register") || channel.equals("minecraft:unregister"));
/*     */   }
/*     */ 
/*     */   
/*     */   public void registerOutgoingPluginChannel(@NotNull Plugin plugin, @NotNull String channel) {
/* 180 */     if (plugin == null) {
/* 181 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/* 183 */     channel = validateAndCorrectChannel(channel);
/* 184 */     if (isReservedChannel(channel)) {
/* 185 */       throw new ReservedChannelException(channel);
/*     */     }
/*     */     
/* 188 */     addToOutgoing(plugin, channel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unregisterOutgoingPluginChannel(@NotNull Plugin plugin, @NotNull String channel) {
/* 193 */     if (plugin == null) {
/* 194 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/* 196 */     channel = validateAndCorrectChannel(channel);
/*     */     
/* 198 */     removeFromOutgoing(plugin, channel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unregisterOutgoingPluginChannel(@NotNull Plugin plugin) {
/* 203 */     if (plugin == null) {
/* 204 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/*     */     
/* 207 */     removeFromOutgoing(plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public PluginMessageListenerRegistration registerIncomingPluginChannel(@NotNull Plugin plugin, @NotNull String channel, @NotNull PluginMessageListener listener) {
/* 213 */     if (plugin == null) {
/* 214 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/* 216 */     channel = validateAndCorrectChannel(channel);
/* 217 */     if (isReservedChannel(channel)) {
/* 218 */       throw new ReservedChannelException(channel);
/*     */     }
/* 220 */     if (listener == null) {
/* 221 */       throw new IllegalArgumentException("Listener cannot be null");
/*     */     }
/*     */     
/* 224 */     PluginMessageListenerRegistration result = new PluginMessageListenerRegistration(this, plugin, channel, listener);
/*     */     
/* 226 */     addToIncoming(result);
/*     */     
/* 228 */     return result;
/*     */   }
/*     */ 
/*     */   
/*     */   public void unregisterIncomingPluginChannel(@NotNull Plugin plugin, @NotNull String channel, @NotNull PluginMessageListener listener) {
/* 233 */     if (plugin == null) {
/* 234 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/* 236 */     if (listener == null) {
/* 237 */       throw new IllegalArgumentException("Listener cannot be null");
/*     */     }
/* 239 */     channel = validateAndCorrectChannel(channel);
/*     */     
/* 241 */     removeFromIncoming(new PluginMessageListenerRegistration(this, plugin, channel, listener));
/*     */   }
/*     */ 
/*     */   
/*     */   public void unregisterIncomingPluginChannel(@NotNull Plugin plugin, @NotNull String channel) {
/* 246 */     if (plugin == null) {
/* 247 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/* 249 */     channel = validateAndCorrectChannel(channel);
/*     */     
/* 251 */     removeFromIncoming(plugin, channel);
/*     */   }
/*     */ 
/*     */   
/*     */   public void unregisterIncomingPluginChannel(@NotNull Plugin plugin) {
/* 256 */     if (plugin == null) {
/* 257 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/*     */     
/* 260 */     removeFromIncoming(plugin);
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<String> getOutgoingChannels() {
/* 266 */     synchronized (this.outgoingLock) {
/* 267 */       Set<String> keys = this.outgoingByChannel.keySet();
/* 268 */       return (Set<String>)ImmutableSet.copyOf(keys);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<String> getOutgoingChannels(@NotNull Plugin plugin) {
/* 275 */     if (plugin == null) {
/* 276 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/*     */     
/* 279 */     synchronized (this.outgoingLock) {
/* 280 */       Set<String> channels = this.outgoingByPlugin.get(plugin);
/*     */       
/* 282 */       if (channels != null) {
/* 283 */         return (Set<String>)ImmutableSet.copyOf(channels);
/*     */       }
/* 285 */       return (Set<String>)ImmutableSet.of();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<String> getIncomingChannels() {
/* 293 */     synchronized (this.incomingLock) {
/* 294 */       Set<String> keys = this.incomingByChannel.keySet();
/* 295 */       return (Set<String>)ImmutableSet.copyOf(keys);
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<String> getIncomingChannels(@NotNull Plugin plugin) {
/* 302 */     if (plugin == null) {
/* 303 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/*     */     
/* 306 */     synchronized (this.incomingLock) {
/* 307 */       Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
/*     */       
/* 309 */       if (registrations != null) {
/* 310 */         ImmutableSet.Builder<String> builder = ImmutableSet.builder();
/*     */         
/* 312 */         for (PluginMessageListenerRegistration registration : registrations) {
/* 313 */           builder.add(registration.getChannel());
/*     */         }
/*     */         
/* 316 */         return (Set<String>)builder.build();
/*     */       } 
/* 318 */       return (Set<String>)ImmutableSet.of();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull Plugin plugin) {
/* 326 */     if (plugin == null) {
/* 327 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/*     */     
/* 330 */     synchronized (this.incomingLock) {
/* 331 */       Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
/*     */       
/* 333 */       if (registrations != null) {
/* 334 */         return (Set<PluginMessageListenerRegistration>)ImmutableSet.copyOf(registrations);
/*     */       }
/* 336 */       return (Set<PluginMessageListenerRegistration>)ImmutableSet.of();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull String channel) {
/* 344 */     channel = validateAndCorrectChannel(channel);
/*     */     
/* 346 */     synchronized (this.incomingLock) {
/* 347 */       Set<PluginMessageListenerRegistration> registrations = this.incomingByChannel.get(channel);
/*     */       
/* 349 */       if (registrations != null) {
/* 350 */         return (Set<PluginMessageListenerRegistration>)ImmutableSet.copyOf(registrations);
/*     */       }
/* 352 */       return (Set<PluginMessageListenerRegistration>)ImmutableSet.of();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   @NotNull
/*     */   public Set<PluginMessageListenerRegistration> getIncomingChannelRegistrations(@NotNull Plugin plugin, @NotNull String channel) {
/* 360 */     if (plugin == null) {
/* 361 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/* 363 */     channel = validateAndCorrectChannel(channel);
/*     */     
/* 365 */     synchronized (this.incomingLock) {
/* 366 */       Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
/*     */       
/* 368 */       if (registrations != null) {
/* 369 */         ImmutableSet.Builder<PluginMessageListenerRegistration> builder = ImmutableSet.builder();
/*     */         
/* 371 */         for (PluginMessageListenerRegistration registration : registrations) {
/* 372 */           if (registration.getChannel().equals(channel)) {
/* 373 */             builder.add(registration);
/*     */           }
/*     */         } 
/*     */         
/* 377 */         return (Set<PluginMessageListenerRegistration>)builder.build();
/*     */       } 
/* 379 */       return (Set<PluginMessageListenerRegistration>)ImmutableSet.of();
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */   
/*     */   public boolean isRegistrationValid(@NotNull PluginMessageListenerRegistration registration) {
/* 386 */     if (registration == null) {
/* 387 */       throw new IllegalArgumentException("Registration cannot be null");
/*     */     }
/*     */     
/* 390 */     synchronized (this.incomingLock) {
/* 391 */       Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(registration.getPlugin());
/*     */       
/* 393 */       if (registrations != null) {
/* 394 */         return registrations.contains(registration);
/*     */       }
/*     */       
/* 397 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isIncomingChannelRegistered(@NotNull Plugin plugin, @NotNull String channel) {
/* 403 */     if (plugin == null) {
/* 404 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/* 406 */     channel = validateAndCorrectChannel(channel);
/*     */     
/* 408 */     synchronized (this.incomingLock) {
/* 409 */       Set<PluginMessageListenerRegistration> registrations = this.incomingByPlugin.get(plugin);
/*     */       
/* 411 */       if (registrations != null) {
/* 412 */         for (PluginMessageListenerRegistration registration : registrations) {
/* 413 */           if (registration.getChannel().equals(channel)) {
/* 414 */             return true;
/*     */           }
/*     */         } 
/*     */       }
/*     */       
/* 419 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public boolean isOutgoingChannelRegistered(@NotNull Plugin plugin, @NotNull String channel) {
/* 425 */     if (plugin == null) {
/* 426 */       throw new IllegalArgumentException("Plugin cannot be null");
/*     */     }
/* 428 */     channel = validateAndCorrectChannel(channel);
/*     */     
/* 430 */     synchronized (this.outgoingLock) {
/* 431 */       Set<String> channels = this.outgoingByPlugin.get(plugin);
/*     */       
/* 433 */       if (channels != null) {
/* 434 */         return channels.contains(channel);
/*     */       }
/*     */       
/* 437 */       return false;
/*     */     } 
/*     */   }
/*     */ 
/*     */   
/*     */   public void dispatchIncomingMessage(@NotNull Player source, @NotNull String channel, @NotNull byte[] message) {
/* 443 */     if (source == null) {
/* 444 */       throw new IllegalArgumentException("Player source cannot be null");
/*     */     }
/* 446 */     if (message == null) {
/* 447 */       throw new IllegalArgumentException("Message cannot be null");
/*     */     }
/* 449 */     channel = validateAndCorrectChannel(channel);
/*     */     
/* 451 */     Set<PluginMessageListenerRegistration> registrations = getIncomingChannelRegistrations(channel);
/*     */     
/* 453 */     for (PluginMessageListenerRegistration registration : registrations) {
/*     */       try {
/* 455 */         registration.getListener().onPluginMessageReceived(channel, source, message);
/* 456 */       } catch (Throwable t) {
/* 457 */         registration.getPlugin().getLogger().log(Level.WARNING, 
/* 458 */             String.format("Plugin %s generated an exception whilst handling plugin message", new Object[] {
/* 459 */                 registration.getPlugin().getDescription().getFullName()
/*     */               }), t);
/*     */       } 
/*     */     } 
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   public static void validateChannel(@NotNull String channel) {
/* 473 */     validateAndCorrectChannel(channel);
/*     */   }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   @Deprecated
/*     */   @NotNull
/*     */   public static String validateAndCorrectChannel(@NotNull String channel) {
/* 486 */     if (channel == null) {
/* 487 */       throw new IllegalArgumentException("Channel cannot be null");
/*     */     }
/*     */ 
/*     */ 
/*     */     
/* 492 */     if (channel.equals("BungeeCord")) {
/* 493 */       return "bungeecord:main";
/*     */     }
/*     */     
/* 496 */     if (channel.equals("bungeecord:main")) {
/* 497 */       return "BungeeCord";
/*     */     }
/* 499 */     if (channel.length() > Messenger.MAX_CHANNEL_SIZE) {
/* 500 */       throw new ChannelNameTooLongException(channel);
/*     */     }
/* 502 */     if (channel.indexOf(':') == -1) {
/* 503 */       throw new IllegalArgumentException("Channel must contain : separator (attempted to use " + channel + ")");
/*     */     }
/* 505 */     if (!channel.toLowerCase(Locale.ROOT).equals(channel))
/*     */     {
/* 507 */       throw new IllegalArgumentException("Channel must be entirely lowercase (attempted to use " + channel + ")");
/*     */     }
/* 509 */     return channel;
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
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */   
/*     */   public static void validatePluginMessage(@NotNull Messenger messenger, @NotNull Plugin source, @NotNull String channel, @NotNull byte[] message) {
/* 531 */     if (messenger == null) {
/* 532 */       throw new IllegalArgumentException("Messenger cannot be null");
/*     */     }
/* 534 */     if (source == null) {
/* 535 */       throw new IllegalArgumentException("Plugin source cannot be null");
/*     */     }
/* 537 */     if (!source.isEnabled()) {
/* 538 */       throw new IllegalArgumentException("Plugin must be enabled to send messages");
/*     */     }
/* 540 */     if (message == null) {
/* 541 */       throw new IllegalArgumentException("Message cannot be null");
/*     */     }
/* 543 */     if (!messenger.isOutgoingChannelRegistered(source, channel)) {
/* 544 */       throw new ChannelNotRegisteredException(channel);
/*     */     }
/* 546 */     if (message.length > 32766) {
/* 547 */       throw new MessageTooLargeException(message);
/*     */     }
/* 549 */     validateChannel(channel);
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\bukkit\plugin\messaging\StandardMessenger.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */