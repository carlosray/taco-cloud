package tacos;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;
import tacos.model.Ingredient;
import tacos.model.Order;
import tacos.model.Taco;
import tacos.model.User;
import tacos.repo.IngredientRepository;
import tacos.repo.OrderRepository;
import tacos.repo.TacoRepository;
import tacos.repo.UserRepository;

import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Configuration
@Profile("!prod")
public class DevelopmentConfig {

    @Bean
    public CommandLineRunner dataLoader(TacoRepository tacoRepository, IngredientRepository ingredientRepository, UserRepository userRepository, OrderRepository orderRepository, PasswordEncoder encoder) {
        return args -> {
            List<Ingredient> ingredients = Arrays.asList(
                    new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                    new Ingredient("COTO", "Corn Tortilla", Ingredient.Type.WRAP),
                    new Ingredient("GRBF", "Ground Beef", Ingredient.Type.PROTEIN),
                    new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                    new Ingredient("TMTO", "Diced Tomatoes", Ingredient.Type.VEGGIES),
                    new Ingredient("LETC", "Lettuce", Ingredient.Type.VEGGIES),
                    new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                    new Ingredient("JACK", "Monterrey Jack", Ingredient.Type.CHEESE),
                    new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE),
                    new Ingredient("SRCR", "Sour Cream", Ingredient.Type.SAUCE)
            );
            User testUser = new User("test",
                    encoder.encode("test"),
                    "fullNameTest",
                    "streetTest",
                    "cityTest",
                    "stateTest",
                    "zipTest",
                    "+79991234567");
            Taco taco = new Taco();
            taco.setName("testTaco");
            taco.setIngredients(Arrays.asList(
                    new Ingredient("FLTO", "Flour Tortilla", Ingredient.Type.WRAP),
                    new Ingredient("CARN", "Carnitas", Ingredient.Type.PROTEIN),
                    new Ingredient("CHED", "Cheddar", Ingredient.Type.CHEESE),
                    new Ingredient("SLSA", "Salsa", Ingredient.Type.SAUCE)
            ));

            Order order = new Order(
                    1L,
                    Collections.singletonList(taco),
                    new Date(),
                    "nameOrder",
                    "streetOrder",
                    "cityOrder",
                    "stateOrder",
                    "zipOrder",
                    "5500000000000004",
                    "12/22",
                    "345",
                    testUser);
            //сохранение в репозитории
            ingredients.forEach(ingredientRepository::save);
            tacoRepository.save(taco);
            userRepository.save(testUser);
            orderRepository.save(order);
        };
    }
}
