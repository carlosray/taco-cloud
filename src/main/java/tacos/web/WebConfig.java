package tacos.web;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.EntityLinks;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import tacos.model.Taco;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Bean
    public RepresentationModelProcessor<PagedModel<EntityModel<Taco>>> tacoProcessor(EntityLinks links) {
        return new RepresentationModelProcessor<PagedModel<EntityModel<Taco>>>() {
            @Override
            public PagedModel<EntityModel<Taco>> process(PagedModel<EntityModel<Taco>> model) {
                model.add(
                        links.linkFor(Taco.class)
                                .slash("recent")
                                .withRel("recents")
                );
                return model;
            }
        };
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login");
    }
}
