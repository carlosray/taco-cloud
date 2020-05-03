package tacos.model.resources;

import org.springframework.hateoas.server.mvc.RepresentationModelAssemblerSupport;
import tacos.model.Taco;

public class TacoRepresentationModelAssembler extends RepresentationModelAssemblerSupport<Taco, TacoRepresentationModel> {

    public TacoRepresentationModelAssembler(Class<?> controllerClass) {
        super(controllerClass, TacoRepresentationModel.class);
    }

    @Override
    protected TacoRepresentationModel instantiateModel(Taco taco) {
        return new TacoRepresentationModel(taco);
    }

    @Override
    public TacoRepresentationModel toModel(Taco taco) {
        return createModelWithId(taco.getId(), taco);
    }
}
