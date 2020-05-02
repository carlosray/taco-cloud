package tacos.model.resources;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.model.Ingredient;

public class IngredientRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Ingredient, IngredientRepresentationModel> {

    public IngredientRepresentationModelAssembler(Class<?> controllerClass) {
        super(controllerClass, IngredientRepresentationModel.class);
    }

    @Override
    protected IngredientRepresentationModel instantiateModel(Ingredient entity) {
        return new IngredientRepresentationModel(entity);
    }

    @Override
    public IngredientRepresentationModel toModel(Ingredient ingredient) {
        return createModelWithId(ingredient.getId(), ingredient);
    }
}
