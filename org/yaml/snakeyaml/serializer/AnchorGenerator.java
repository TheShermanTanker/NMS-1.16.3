package org.yaml.snakeyaml.serializer;

import org.yaml.snakeyaml.nodes.Node;

public interface AnchorGenerator {
  String nextAnchor(Node paramNode);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\yaml\snakeyaml\serializer\AnchorGenerator.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */