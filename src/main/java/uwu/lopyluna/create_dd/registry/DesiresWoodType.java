package uwu.lopyluna.create_dd.registry;

import static uwu.lopyluna.create_dd.DesiresCreate.REGISTRATE;
import static uwu.lopyluna.create_dd.registry.helper.woodtype.WoodEntry.*;

import com.simibubi.create.content.decoration.slidingDoor.SlidingDoorRenderer;
import com.tterrag.registrate.util.entry.BlockEntityEntry;
import uwu.lopyluna.create_dd.content.blocks.functional.sliding_door.WoodenSlidingDoorBlockEntity;
import uwu.lopyluna.create_dd.registry.helper.woodtype.WoodEntry;
import uwu.lopyluna.create_dd.registry.helper.woodtype.WoodTypes;

@SuppressWarnings("unused")
public class DesiresWoodType {

    public static final WoodEntry ROSE = create("Rose", WoodTypes.WOOD, true, true, true, true, false, false, false);
    public static final WoodEntry SMOKED = create("Smoked", WoodTypes.WOOD, true, true, true, true, false, false, false);
    public static final WoodEntry RUBBER = create("Rubber", WoodTypes.WOOD, true, false, false, true, false, false, false);
    public static final WoodEntry SPIRIT = create("Spirit", WoodTypes.WOOD, false, true, true, true, false, false, false);
    //add recipes


    public static final BlockEntityEntry<WoodenSlidingDoorBlockEntity> SLIDING_DOOR =
            REGISTRATE.blockEntity("sliding_door", WoodenSlidingDoorBlockEntity::new)
                    .renderer(() -> SlidingDoorRenderer::new)
                    .validBlocks(ROSE.slidingDoor, SMOKED.slidingDoor, SPIRIT.slidingDoor)
                    .register();

    public static void register() {}
}
