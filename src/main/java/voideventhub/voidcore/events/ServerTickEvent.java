package voideventhub.voidcore.events;

import net.fabricmc.fabric.api.event.lifecycle.v1.ServerTickEvents;
import net.minecraft.entity.Entity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.server.world.ServerWorld;
import voideventhub.voidcore.cardinal.MemberComponent;
import voideventhub.voidcore.cardinal.VCComponents;

import java.util.Random;

public class ServerTickEvent {

    private static int tick = 0;

    public static void register() {
        ServerTickEvents.START_SERVER_TICK.register(server -> {
            tick++;

            if (tick % 5 != 0) {
                return;
            }

            server.getPlayerManager()
                    .getPlayerList()
                    .stream()
                    .filter(ServerTickEvent::isPremiumMember)
                    .filter(Entity::isSprinting)
                    .forEach(ServerTickEvent::spawnSprintTrailParticles);
        });
    }

    private static void spawnSprintTrailParticles(ServerPlayerEntity player) {
        Random rand = new Random();

        double yaw = player.getYaw();
        double dx = Math.sin(Math.toRadians(yaw + rand.nextInt(90) - 45));
        double dz = Math.cos(Math.toRadians(yaw + 180 + rand.nextInt(90) - 45));

        ServerWorld world = player.getWorld();
        world.spawnParticles(
                ParticleTypes.CLOUD,
                player.getX(),
                player.getY() + 0.5f + rand.nextDouble() * 0.1 - 0.05,
                player.getZ(),
                0,
                dx + rand.nextDouble() * 0.1 - 0.05,
                rand.nextDouble() * 1f,
                dz + rand.nextDouble() * 0.1 - 0.05,
                0.3f
        );

        world.spawnParticles(
                ParticleTypes.END_ROD,
                player.getX(),
                player.getY() + 0.5f + rand.nextDouble() * 0.1 - 0.05,
                player.getZ(),
                0,
                dx + rand.nextDouble() * 0.1 - 0.05,
                rand.nextDouble() * 1f,
                dz + rand.nextDouble() * 0.1 - 0.05,
                0.3f
        );
    }

    private static boolean isPremiumMember(ServerPlayerEntity player) {
        MemberComponent memberComponent = VCComponents.MEMBER.get(player);
        return memberComponent.isPatron() || memberComponent.isServerBooster();
    }

}
