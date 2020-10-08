package com.destroystokyo.paper.brigadier;

import com.mojang.brigadier.Command;
import com.mojang.brigadier.suggestion.SuggestionProvider;
import java.util.function.Predicate;

public interface BukkitBrigadierCommand<S extends BukkitBrigadierCommandSource> extends Command<S>, Predicate<S>, SuggestionProvider<S> {}


/* Location:              C:\Users\Josep\Downloads\Decompile Minecraft\tuinity-1.16.3.jar!\com\destroystokyo\paper\brigadier\BukkitBrigadierCommand.class
 * Java compiler version: 8 (52.0)
 * JD-Core Version:       1.1.3
 */