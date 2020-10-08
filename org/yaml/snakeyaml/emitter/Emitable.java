package org.yaml.snakeyaml.emitter;

import java.io.IOException;
import org.yaml.snakeyaml.events.Event;

public interface Emitable {
  void emit(Event paramEvent) throws IOException;
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\yaml\snakeyaml\emitter\Emitable.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */