package uwu.lopyluna.create_dd.registry;

import static uwu.lopyluna.create_dd.registry.helper.woodtype.WoodEntry.*;

import uwu.lopyluna.create_dd.registry.helper.woodtype.WoodEntry;
import uwu.lopyluna.create_dd.registry.helper.woodtype.WoodTypes;

@SuppressWarnings("unused")
public class DesiresWoodType {

    public static final WoodEntry ROSE = create("Rose", WoodTypes.WOOD, false, false, false, false, true);
    public static final WoodEntry SPIRIT = create("Spirit", WoodTypes.ALL, false, false, false, false, false);


    public static void register() {}
}
