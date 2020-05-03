package tacos.model.resources;

import org.springframework.hateoas.RepresentationModel;
import tacos.model.Ingredient;

public class IngredientRepresentationModel extends RepresentationModel<IngredientRepresentationModel> {
    private String name;
    private Ingredient.Type type;

    public IngredientRepresentationModel(Ingredient ingredient) {
        this.name = ingredient.getName();
        this.type = ingredient.getType();
    }

    public String getName() {
        return name;
    }

    public Ingredient.Type getType() {
        return type;
    }
}
