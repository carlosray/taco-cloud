package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import tacos.model.Taco;
import tacos.model.resources.TacoRepresentationModel;
import tacos.model.resources.TacoRepresentationModelAssembler;
import tacos.repo.TacoRepository;

@RepositoryRestController
public class RecentTacoController {
    private TacoRepository tacoRepository;

    @Autowired
    public RecentTacoController(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @GetMapping(path = "/tacos/recent", produces = "application/hal+json")
    @ResponseBody
    public CollectionModel<TacoRepresentationModel> recentTacos() {
        PageRequest pageRequest = PageRequest.of(0, 12, Sort.by("createdAt").descending());
        Iterable<Taco> all = tacoRepository.findAll(pageRequest);
        CollectionModel<TacoRepresentationModel> modelTacos = new TacoRepresentationModelAssembler(RecentTacoController.class).toCollectionModel(all);
        Link link = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(RecentTacoController.class).recentTacos())
                .withRel("recents");
        modelTacos.add(link);
        return modelTacos;
    }
}
