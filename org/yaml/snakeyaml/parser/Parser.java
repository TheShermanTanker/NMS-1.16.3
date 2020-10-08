package org.yaml.snakeyaml.parser;

import org.yaml.snakeyaml.events.Event;

public interface Parser {
  boolean checkEvent(Event.ID paramID);
  
  Event peekEvent();
  
  Event getEvent();
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\yaml\snakeyaml\parser\Parser.class
 * Java compiler version: 7 (51.0)
 * JD-Core Version:       1.1.3
 */