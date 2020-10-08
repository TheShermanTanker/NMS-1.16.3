/*     */ package io.netty.handler.codec.http.websocketx;
/*     */ 
/*     */ import io.netty.handler.codec.http.EmptyHttpHeaders;
/*     */ import io.netty.handler.codec.http.HttpHeaders;
/*     */ import io.netty.util.internal.ObjectUtil;
/*     */ import java.net.URI;
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
/*     */ public final class WebSocketClientProtocolConfig
/*     */ {
/*     */   static final boolean DEFAULT_PERFORM_MASKING = true;
/*     */   static final boolean DEFAULT_ALLOW_MASK_MISMATCH = false;
/*     */   static final boolean DEFAULT_HANDLE_CLOSE_FRAMES = true;
/*     */   static final boolean DEFAULT_DROP_PONG_FRAMES = true;
/*     */   private final URI webSocketUri;
/*     */   private final String subprotocol;
/*     */   private final WebSocketVersion version;
/*     */   private final boolean allowExtensions;
/*     */   private final HttpHeaders customHeaders;
/*     */   private final int maxFramePayloadLength;
/*     */   private final boolean performMasking;
/*     */   private final boolean allowMaskMismatch;
/*     */   private final boolean handleCloseFrames;
/*     */   private final WebSocketCloseStatus sendCloseFrame;
/*     */   private final boolean dropPongFrames;
/*     */   private final long handshakeTimeoutMillis;
/*     */   private final long forceCloseTimeoutMillis;
/*     */   private final boolean absoluteUpgradeUrl;
/*     */   
/*     */   private WebSocketClientProtocolConfig(URI webSocketUri, String subprotocol, WebSocketVersion version, boolean allowExtensions, HttpHeaders customHeaders, int maxFramePayloadLength, boolean performMasking, boolean allowMaskMismatch, boolean handleCloseFrames, WebSocketCloseStatus sendCloseFrame, boolean dropPongFrames, long handshakeTimeoutMillis, long forceCloseTimeoutMillis, boolean absoluteUpgradeUrl) {
/*  69 */     this.webSocketUri = webSocketUri;
/*  70 */     this.subprotocol = subprotocol;
/*  71 */     this.version = version;
/*  72 */     this.allowExtensions = allowExtensions;
/*  73 */     this.customHeaders = customHeaders;
/*  74 */     this.maxFramePayloadLength = maxFramePayloadLength;
/*  75 */     this.performMasking = performMasking;
/*  76 */     this.allowMaskMismatch = allowMaskMismatch;
/*  77 */     this.forceCloseTimeoutMillis = forceCloseTimeoutMillis;
/*  78 */     this.handleCloseFrames = handleCloseFrames;
/*  79 */     this.sendCloseFrame = sendCloseFrame;
/*  80 */     this.dropPongFrames = dropPongFrames;
/*  81 */     this.handshakeTimeoutMillis = ObjectUtil.checkPositive(handshakeTimeoutMillis, "handshakeTimeoutMillis");
/*  82 */     this.absoluteUpgradeUrl = absoluteUpgradeUrl;
/*     */   }
/*     */   
/*     */   public URI webSocketUri() {
/*  86 */     return this.webSocketUri;
/*     */   }
/*     */   
/*     */   public String subprotocol() {
/*  90 */     return this.subprotocol;
/*     */   }
/*     */   
/*     */   public WebSocketVersion version() {
/*  94 */     return this.version;
/*     */   }
/*     */   
/*     */   public boolean allowExtensions() {
/*  98 */     return this.allowExtensions;
/*     */   }
/*     */   
/*     */   public HttpHeaders customHeaders() {
/* 102 */     return this.customHeaders;
/*     */   }
/*     */   
/*     */   public int maxFramePayloadLength() {
/* 106 */     return this.maxFramePayloadLength;
/*     */   }
/*     */   
/*     */   public boolean performMasking() {
/* 110 */     return this.performMasking;
/*     */   }
/*     */   
/*     */   public boolean allowMaskMismatch() {
/* 114 */     return this.allowMaskMismatch;
/*     */   }
/*     */   
/*     */   public boolean handleCloseFrames() {
/* 118 */     return this.handleCloseFrames;
/*     */   }
/*     */   
/*     */   public WebSocketCloseStatus sendCloseFrame() {
/* 122 */     return this.sendCloseFrame;
/*     */   }
/*     */   
/*     */   public boolean dropPongFrames() {
/* 126 */     return this.dropPongFrames;
/*     */   }
/*     */   
/*     */   public long handshakeTimeoutMillis() {
/* 130 */     return this.handshakeTimeoutMillis;
/*     */   }
/*     */   
/*     */   public long forceCloseTimeoutMillis() {
/* 134 */     return this.forceCloseTimeoutMillis;
/*     */   }
/*     */   
/*     */   public boolean absoluteUpgradeUrl() {
/* 138 */     return this.absoluteUpgradeUrl;
/*     */   }
/*     */ 
/*     */   
/*     */   public String toString() {
/* 143 */     return "WebSocketClientProtocolConfig {webSocketUri=" + this.webSocketUri + ", subprotocol=" + this.subprotocol + ", version=" + this.version + ", allowExtensions=" + this.allowExtensions + ", customHeaders=" + this.customHeaders + ", maxFramePayloadLength=" + this.maxFramePayloadLength + ", performMasking=" + this.performMasking + ", allowMaskMismatch=" + this.allowMaskMismatch + ", handleCloseFrames=" + this.handleCloseFrames + ", sendCloseFrame=" + this.sendCloseFrame + ", dropPongFrames=" + this.dropPongFrames + ", handshakeTimeoutMillis=" + this.handshakeTimeoutMillis + ", forceCloseTimeoutMillis=" + this.forceCloseTimeoutMillis + ", absoluteUpgradeUrl=" + this.absoluteUpgradeUrl + "}";
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
/*     */   public Builder toBuilder() {
/* 162 */     return new Builder(this);
/*     */   }
/*     */   
/*     */   public static Builder newBuilder() {
/* 166 */     return new Builder(
/* 167 */         URI.create("https://localhost/"), null, WebSocketVersion.V13, false, (HttpHeaders)EmptyHttpHeaders.INSTANCE, 65536, true, false, true, WebSocketCloseStatus.NORMAL_CLOSURE, true, 10000L, -1L, false);
/*     */   }
/*     */ 
/*     */   
/*     */   public static final class Builder
/*     */   {
/*     */     private URI webSocketUri;
/*     */     
/*     */     private String subprotocol;
/*     */     
/*     */     private WebSocketVersion version;
/*     */     
/*     */     private boolean allowExtensions;
/*     */     
/*     */     private HttpHeaders customHeaders;
/*     */     
/*     */     private int maxFramePayloadLength;
/*     */     
/*     */     private boolean performMasking;
/*     */     
/*     */     private boolean allowMaskMismatch;
/*     */     
/*     */     private boolean handleCloseFrames;
/*     */     
/*     */     private WebSocketCloseStatus sendCloseFrame;
/*     */     
/*     */     private boolean dropPongFrames;
/*     */     
/*     */     private long handshakeTimeoutMillis;
/*     */     private long forceCloseTimeoutMillis;
/*     */     private boolean absoluteUpgradeUrl;
/*     */     
/*     */     private Builder(WebSocketClientProtocolConfig clientConfig) {
/* 200 */       this(((WebSocketClientProtocolConfig)ObjectUtil.checkNotNull(clientConfig, "clientConfig")).webSocketUri(), clientConfig
/* 201 */           .subprotocol(), clientConfig
/* 202 */           .version(), clientConfig
/* 203 */           .allowExtensions(), clientConfig
/* 204 */           .customHeaders(), clientConfig
/* 205 */           .maxFramePayloadLength(), clientConfig
/* 206 */           .performMasking(), clientConfig
/* 207 */           .allowMaskMismatch(), clientConfig
/* 208 */           .handleCloseFrames(), clientConfig
/* 209 */           .sendCloseFrame(), clientConfig
/* 210 */           .dropPongFrames(), clientConfig
/* 211 */           .handshakeTimeoutMillis(), clientConfig
/* 212 */           .forceCloseTimeoutMillis(), clientConfig
/* 213 */           .absoluteUpgradeUrl());
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
/*     */ 
/*     */     
/*     */     private Builder(URI webSocketUri, String subprotocol, WebSocketVersion version, boolean allowExtensions, HttpHeaders customHeaders, int maxFramePayloadLength, boolean performMasking, boolean allowMaskMismatch, boolean handleCloseFrames, WebSocketCloseStatus sendCloseFrame, boolean dropPongFrames, long handshakeTimeoutMillis, long forceCloseTimeoutMillis, boolean absoluteUpgradeUrl) {
/* 230 */       this.webSocketUri = webSocketUri;
/* 231 */       this.subprotocol = subprotocol;
/* 232 */       this.version = version;
/* 233 */       this.allowExtensions = allowExtensions;
/* 234 */       this.customHeaders = customHeaders;
/* 235 */       this.maxFramePayloadLength = maxFramePayloadLength;
/* 236 */       this.performMasking = performMasking;
/* 237 */       this.allowMaskMismatch = allowMaskMismatch;
/* 238 */       this.handleCloseFrames = handleCloseFrames;
/* 239 */       this.sendCloseFrame = sendCloseFrame;
/* 240 */       this.dropPongFrames = dropPongFrames;
/* 241 */       this.handshakeTimeoutMillis = handshakeTimeoutMillis;
/* 242 */       this.forceCloseTimeoutMillis = forceCloseTimeoutMillis;
/* 243 */       this.absoluteUpgradeUrl = absoluteUpgradeUrl;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder webSocketUri(String webSocketUri) {
/* 251 */       return webSocketUri(URI.create(webSocketUri));
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder webSocketUri(URI webSocketUri) {
/* 259 */       this.webSocketUri = webSocketUri;
/* 260 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder subprotocol(String subprotocol) {
/* 267 */       this.subprotocol = subprotocol;
/* 268 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder version(WebSocketVersion version) {
/* 275 */       this.version = version;
/* 276 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder allowExtensions(boolean allowExtensions) {
/* 283 */       this.allowExtensions = allowExtensions;
/* 284 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder customHeaders(HttpHeaders customHeaders) {
/* 291 */       this.customHeaders = customHeaders;
/* 292 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder maxFramePayloadLength(int maxFramePayloadLength) {
/* 299 */       this.maxFramePayloadLength = maxFramePayloadLength;
/* 300 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder performMasking(boolean performMasking) {
/* 309 */       this.performMasking = performMasking;
/* 310 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder allowMaskMismatch(boolean allowMaskMismatch) {
/* 317 */       this.allowMaskMismatch = allowMaskMismatch;
/* 318 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder handleCloseFrames(boolean handleCloseFrames) {
/* 325 */       this.handleCloseFrames = handleCloseFrames;
/* 326 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder sendCloseFrame(WebSocketCloseStatus sendCloseFrame) {
/* 333 */       this.sendCloseFrame = sendCloseFrame;
/* 334 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder dropPongFrames(boolean dropPongFrames) {
/* 341 */       this.dropPongFrames = dropPongFrames;
/* 342 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder handshakeTimeoutMillis(long handshakeTimeoutMillis) {
/* 350 */       this.handshakeTimeoutMillis = handshakeTimeoutMillis;
/* 351 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder forceCloseTimeoutMillis(long forceCloseTimeoutMillis) {
/* 358 */       this.forceCloseTimeoutMillis = forceCloseTimeoutMillis;
/* 359 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public Builder absoluteUpgradeUrl(boolean absoluteUpgradeUrl) {
/* 366 */       this.absoluteUpgradeUrl = absoluteUpgradeUrl;
/* 367 */       return this;
/*     */     }
/*     */ 
/*     */ 
/*     */ 
/*     */     
/*     */     public WebSocketClientProtocolConfig build() {
/* 374 */       return new WebSocketClientProtocolConfig(this.webSocketUri, this.subprotocol, this.version, this.allowExtensions, this.customHeaders, this.maxFramePayloadLength, this.performMasking, this.allowMaskMismatch, this.handleCloseFrames, this.sendCloseFrame, this.dropPongFrames, this.handshakeTimeoutMillis, this.forceCloseTimeoutMillis, this.absoluteUpgradeUrl);
/*     */     }
/*     */   }
/*     */ }


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\codec\http\websocketx\WebSocketClientProtocolConfig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */