package org.yaml.snakeyaml.constructor;

import org.yaml.snakeyaml.nodes.Node;

public interface Construct {
  Object construct(Node paramNode);
  
  void construct2ndStep(Node paramNode, Object paramObject);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\yaml\snakeyaml\constructor\Construct.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */