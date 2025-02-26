package symbolics.division.meret;

import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import dev.doublekekse.area_lib.command.argument.AreaArgument;
import net.minecraft.commands.CommandSourceStack;
import net.minecraft.commands.arguments.ResourceLocationArgument;
import net.minecraft.commands.synchronization.SuggestionProviders;
import net.minecraft.core.Holder;
import net.minecraft.sounds.Music;
import net.minecraft.sounds.SoundEvent;

import static net.minecraft.commands.Commands.argument;
import static net.minecraft.commands.Commands.literal;

public class MeretCommand {
    public static void register(CommandDispatcher<CommandSourceStack> dispatcher) {
        dispatcher.register(
            literal("meret")
                .then(literal("set_music").then(argument("area", AreaArgument.area()).then(argument("sound", ResourceLocationArgument.id()).suggests(SuggestionProviders.AVAILABLE_SOUNDS).then(argument("min_delay", IntegerArgumentType.integer()).then(argument("max_delay", IntegerArgumentType.integer()).then(argument("replace_current_music", BoolArgumentType.bool()).executes(ctx -> {
                    var server = ctx.getSource().getServer();

                    var area = AreaArgument.getArea(ctx, "area");
                    var sound = ResourceLocationArgument.getId(ctx, "sound");
                    var minDelay = IntegerArgumentType.getInteger(ctx, "min_delay");
                    var maxDelay = IntegerArgumentType.getInteger(ctx, "max_delay");
                    var replaceCurrentMusic = BoolArgumentType.getBool(ctx, "replace_current_music");

                    var soundEvent = SoundEvent.createVariableRangeEvent((sound));

                    var music = new Music(Holder.direct(soundEvent), minDelay, maxDelay, replaceCurrentMusic);

                    area.put(server, Meret.AREA_MUSIC_DATA_COMPONENT, new AreaMusicComponent(music));

                    return 1;
                }))))))).then(literal("clear").then(argument("area", AreaArgument.area()).executes(ctx -> {
                    var server = ctx.getSource().getServer();
                    var area = AreaArgument.getArea(ctx, "area");

                    area.remove(server, Meret.AREA_MUSIC_DATA_COMPONENT);

                    return 1;
                })))
        );
    }
}
