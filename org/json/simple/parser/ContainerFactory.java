package org.json.simple.parser;

import java.util.List;
import java.util.Map;

public interface ContainerFactory {
  Map createObjectContainer();
  
  List creatArrayContainer();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\json\simple\parser\ContainerFactory.class
 * Java compiler version: 2 (46.0)
 * JD-Core Version:       1.1.3
 */