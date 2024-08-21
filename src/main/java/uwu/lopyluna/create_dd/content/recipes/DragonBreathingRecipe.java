package uwu.lopyluna.create_dd.content.recipes;

import com.simibubi.create.content.processing.recipe.ProcessingRecipe;
import com.simibubi.create.content.processing.recipe.ProcessingRecipeBuilder.ProcessingRecipeParams;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.wrapper.RecipeWrapper;
import uwu.lopyluna.create_dd.registry.DesiresRecipeTypes;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
public class DragonBreathingRecipe extends ProcessingRecipe<DragonBreathingRecipe.DragonBreathingWrapper> {

	public DragonBreathingRecipe(ProcessingRecipeParams params) {
		super(DesiresRecipeTypes.DRAGON_BREATHING, params);
	}

	@Override
	public boolean matches(DragonBreathingWrapper inv, Level worldIn) {
		if (inv.isEmpty())
			return false;
		return ingredients.get(0)
			.test(inv.getItem(0));
	}

	@Override
	protected int getMaxInputCount() {
		return 1;
	}

	@Override
	protected int getMaxOutputCount() {
		return 12;
	}

	public static class DragonBreathingWrapper extends RecipeWrapper {
		public DragonBreathingWrapper() {
			super(new ItemStackHandler(1));
		}
	}

}
