package voideventhub.voidcore.common.components;

import dev.onyxstudios.cca.api.v3.component.ComponentKey;
import dev.onyxstudios.cca.api.v3.component.ComponentRegistryV3;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.entity.EntityComponentInitializer;
import dev.onyxstudios.cca.api.v3.entity.RespawnCopyStrategy;
import dev.onyxstudios.cca.api.v3.scoreboard.ScoreboardComponentFactoryRegistry;
import dev.onyxstudios.cca.api.v3.scoreboard.ScoreboardComponentInitializer;
import net.minecraft.util.Identifier;
import voideventhub.voidcore.common.VoidCore;
import voideventhub.voidcore.common.components.entity.*;
import voideventhub.voidcore.common.components.team.SyncedTeamComponent;
import voideventhub.voidcore.common.components.team.TeamComponent;

public class VCComponents implements EntityComponentInitializer {

    public static final ComponentKey<CosmeticComponent> COSMETIC =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(VoidCore.MOD_ID, "cosmetic"), CosmeticComponent.class);

    public static final ComponentKey<MemberComponent> MEMBER =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(VoidCore.MOD_ID, "member"), MemberComponent.class);

    public static final ComponentKey<DeathComponent> DEATH =
            ComponentRegistryV3.INSTANCE.getOrCreate(new Identifier(VoidCore.MOD_ID, "death"), DeathComponent.class);

    @Override
    public void registerEntityComponentFactories(EntityComponentFactoryRegistry registry) {
        registry.registerForPlayers(COSMETIC, SyncedCosmeticComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(MEMBER, SyncedMemberComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
        registry.registerForPlayers(DEATH, SyncedDeathComponent::new, RespawnCopyStrategy.ALWAYS_COPY);
    }

}
