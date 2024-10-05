package uwu.lopyluna.create_dd.content.items.equipment.jetpack;

import com.jozufozu.flywheel.api.Instancer;
import com.jozufozu.flywheel.api.MaterialManager;
import com.simibubi.create.content.kinetics.base.SingleRotatingInstance;
import com.simibubi.create.content.kinetics.base.flwdata.RotatingData;

public class JetpackInstance extends SingleRotatingInstance<JetpackBlockEntity> {

    public JetpackInstance(MaterialManager materialManager, JetpackBlockEntity blockEntity) {
        super(materialManager, blockEntity);
    }

    @Override
    protected Instancer<RotatingData> getModel() {
        return getRotatingMaterial().getModel(JetpackRenderer.getShaftModel(), blockState);
    }

}