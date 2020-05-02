package tacos.model.resources;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.RepresentationModel;
import tacos.model.Taco;
import tacos.web.DesignTacoController;

import java.util.Date;

public class TacoRepresentationModel extends RepresentationModel<TacoRepresentationModel> {
    private final IngredientRepresentationModelAssembler ingredientModel = new IngredientRepresentationModelAssembler(DesignTacoController.class);
    private final Date createdAt;
    private final String name;
    private final CollectionModel<IngredientRepresentationModel> ingredients;

    public TacoRepresentationModel(Taco taco) {
        this.createdAt = taco.getCreatedAt();
        this.name = taco.getName();
        this.ingredients = ingredientModel.toCollectionModel(taco.getIngredients());
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public String getName() {
        return name;
    }

    public CollectionModel<IngredientRepresentationModel> getIngredients() {
        return ingredients;
    }
}
