package org.jline.reader;

import java.util.List;

public interface Completer {
  void complete(LineReader paramLineReader, ParsedLine paramParsedLine, List<Candidate> paramList);
}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\org\jline\reader\Completer.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */