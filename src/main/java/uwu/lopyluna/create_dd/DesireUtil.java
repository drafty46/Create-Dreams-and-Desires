package uwu.lopyluna.create_dd;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.util.Mth;
import net.minecraft.world.level.Level;

import static uwu.lopyluna.create_dd.DesiresCreate.*;

@SuppressWarnings({"unused", "all"})
public class DesireUtil {

    public static boolean RANDOM_B = RANDOM.nextBoolean();
    public static int RANDOM_I = RANDOM.nextInt();
    public static double RANDOM_D = RANDOM.nextDouble();
    public static float RANDOM_F = RANDOM.nextFloat();
    public static long RANDOM_L = RANDOM.nextLong();

    public static double RANDOM_G = RANDOM.nextGaussian();
    public static double RANDOM_E = RANDOM.nextExponential();



    public static boolean randomChance(int chance) {
        int newChance = 100 - Mth.clamp(chance, 1, 100);
        return (newChance + 1 == 1) ? true : RANDOM.nextInt(1, newChance + 2) == 1;
    }

    public static boolean randomChance(int chance, Level level) {
        int newChance = 100 - Mth.clamp(chance, 1, 100);
        return (newChance + 1 == 1) ? true : level.getRandom().nextInt(1, newChance + 1) == 1;
    }

    public static boolean randomChance(double chance) {
        int newChance = 100 - Mth.clamp(((int) chance * 100), 1, 100);
        return (newChance + 1 == 1) ? true : RANDOM.nextInt(1, newChance + 2) == 1;
    }
    public static boolean randomChance(double chance, Level level) {
        int newChance = 100 - Mth.clamp(((int) chance * 100), 1, 100);
        return (newChance + 1 == 1) ? true : level.getRandom().nextInt(1, newChance + 2) == 1;
    }



    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
