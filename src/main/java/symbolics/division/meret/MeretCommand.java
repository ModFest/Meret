package symbolics.division.meret;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.doublekekse.area_lib.command.argument.AreaArgument;
import net.minecraft.commands.CommandSourceStack;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class MeretCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            literal("meret").then(literal("set_delay").then(argument("area", AreaArgument.area()).then(argument("min_delay", IntegerArgumentType.integer()).then(argument("max_delay", IntegerArgumentType.integer()).executes(ctx -> {
                var server = ctx.getSource().getServer();

                var minDelay = IntegerArgumentType.getInteger(ctx, "min_delay");
                var maxDelay = IntegerArgumentType.getInteger(ctx, "max_delay");
                var area = AreaArgument.getArea(ctx, "area");

                area.put(server, Meret.AREA_MUSIC_DELAY_DATA_COMPONENT, new AreaMusicDelay(minDelay, maxDelay));

                return 1;
            })))))
        );
    }
}
