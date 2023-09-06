package voideventhub.voidcore.common.components.team;

import dev.onyxstudios.cca.api.v3.component.ComponentV3;
import net.minecraft.util.math.Vec3d;

public interface TeamComponent extends ComponentV3 {

    /**
     * Sets the spawn point of the team
     * @param position the position of the spawn point
     */
    void setSpawn(Vec3d position);

    /**
     * @return the spawn point of the team
     */
    Vec3d getSpawn();

}
