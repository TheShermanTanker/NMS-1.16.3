package org.slf4j.spi;

import org.slf4j.IMarkerFactory;

public interface MarkerFactoryBinder {
  IMarkerFactory getMarkerFactory();
  
  String getMarkerFactoryClassStr();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\slf4j\spi\MarkerFactoryBinder.class
 * Java compiler version: 5 (49.0)
 * JD-Core Version:       1.1.3
 */