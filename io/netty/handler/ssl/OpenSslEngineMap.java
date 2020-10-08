package io.netty.handler.ssl;

interface OpenSslEngineMap {
  ReferenceCountedOpenSslEngine remove(long paramLong);
  
  void add(ReferenceCountedOpenSslEngine paramReferenceCountedOpenSslEngine);
  
  ReferenceCountedOpenSslEngine get(long paramLong);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\io\netty\handler\ssl\OpenSslEngineMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       1.1.3
 */