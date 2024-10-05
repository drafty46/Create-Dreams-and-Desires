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
        int newChance = Mth.clamp(chance, 0, 100);
        return newChance != 0 && RANDOM.nextInt(1,  100) <= newChance;
    }

    public static boolean randomChance(int chance, Level level) {
        int newChance = Mth.clamp(chance, 0, 100);
        return newChance != 0 && level.getRandom().nextInt(1,  100) <= newChance;
    }

    public static boolean randomChance(double chance) {
        int newChance = Mth.clamp(((int) chance * 100), 0, 100);
        return newChance != 0 && RANDOM.nextInt(1,  100) <= newChance;
    }
    public static boolean randomChance(double chance, Level level) {
        int newChance = Mth.clamp(((int) chance * 100), 0, 100);
        return newChance != 0 && level.getRandom().nextInt(1,  100) <= newChance;
    }

    public static boolean tickDelay(int percentage, Level level ) {
        return level.getGameTime() % percentage != 0;
    }

    public static int percentage(int value, int maxValue, boolean invert) {
        int a = (int)(((invert ? (double)maxValue - (double)value : (double)value) / (double)maxValue) * 100);
        return a;
    }

    public static String percentString(int value, int maxValue, boolean invert) {
        return percentage(value, maxValue, invert) + "%";
    }

    public static int radial(int value, int maxValue, boolean invert) {
        int a = (value / maxValue);
        a %= 360;
        return a;
    }

    public static String valueToTime(int ticks, OffsetTime off) {
        boolean bT = off == OffsetTime.TICKS;
        boolean bS = off == OffsetTime.SECONDS || bT;
        boolean bM = off == OffsetTime.MINUTES || bS;
        boolean bH = off == OffsetTime.HOURS || bM;
        boolean bD = off == OffsetTime.DAYS || bH;
        boolean bMTH = off == OffsetTime.MONTHS || bD;
        int t = ticks;
        int s = t / 20;
        int m = s / 60;
        int h = m / 60;
        int d = h / 24;
        int mth = d / 30;
        int y = mth / 12;
        t %= 20;
        s %= 60;
        m %= 60;
        h %= 24;
        mth %= 30;
        String aticks = bT ? conversion(t, "t", d > 0 || mth > 0 || y > 0 || s > 0 || m > 0 || h > 0, off == OffsetTime.TICKS) : "";
        String secs = bS ? conversion(s, "s", d > 0 || mth > 0 || y > 0 || m > 0 || h > 0, off == OffsetTime.SECONDS) : "";
        String mins = bM ? conversion(m, "m", d > 0 || mth > 0 || y > 0 || h > 0, off == OffsetTime.MINUTES) : "";
        String hours = bH ? conversion(h, "h", d > 0 || mth > 0 || y > 0, off == OffsetTime.HOURS) : "";
        String days = bD ? conversion(d, "d", mth > 0 || y > 0, off == OffsetTime.DAYS) : "";
        String months = bMTH ? conversion(mth, "m", y > 0, off == OffsetTime.MONTHS) : "";
        String years = y > 0 ? y + "y" : "";
        return years + months + days + hours + mins + secs + aticks;
    }
    public static String conversion(int value, String inc, boolean above, boolean isEnding) {
        return value > 0 ? above ? value < 10 ? ":0" + value + inc : ":" + value + inc : value + inc : above ? ":00" + inc : isEnding ? "0" + inc : "";
    }
    public static enum OffsetTime {
        TICKS,
        SECONDS,
        MINUTES,
        HOURS,
        DAYS,
        MONTHS,
        YEARS
    }


    public static ResourceLocation asResource(String path) {
        return new ResourceLocation(MOD_ID, path);
    }
}
