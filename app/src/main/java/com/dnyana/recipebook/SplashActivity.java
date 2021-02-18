package com.dnyana.recipebook;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class SplashActivity extends AppCompatActivity {

    private static final int REQUEST_TO_WRITE = 1;
    private DatabaseAdapter databaseAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseAdapter = DatabaseAdapter.getInstance(this);

        if (UserPreferences.isFirstRun(this)) {
            UserPreferences.setIsFirstRun(this, false);

            databaseAdapter.addNewUser(new User("testrun",
                    "test runner", "testrun@recipeapp.com", "password"));

            if (ContextCompat.checkSelfPermission(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        REQUEST_TO_WRITE);
            } else {
                loadDefaultRecipes();
                navigateToLogin();
            }
        } else {
            if (UserPreferences.isUserLoggedIn(this))
                navigateToMainPage();
            else
                navigateToLogin();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case REQUEST_TO_WRITE:
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    loadDefaultRecipes();
                else
                    Toast.makeText(this, "Permission denied to write default recipes.", Toast.LENGTH_LONG)
                            .show();

                navigateToLogin();
                break;
        }
    }

    private void loadDefaultRecipes() {
        try {
            loadStreetFoodRecipes();
            loadChineseRecipes();
            loadFastFoodRecipes();
            loadBakerySnacksRecipes();
            loadSouthRecipes();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadSouthRecipes() throws IOException {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.idli);
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();

        File file = new File(extStorageDirectory, "idli.jpg");
        FileOutputStream outStream = new FileOutputStream(file);
        bm.compress(Bitmap.CompressFormat.PNG, 100, outStream);
        outStream.flush();
        outStream.close();

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("1 cup regular rice, 190 to 200 grams (sona masoori rice or parmal rice or basmati rice)"));
        ingredients.add(new Ingredient("1 cup parboiled rice or idli rice, 200 grams"));
        ingredients.add(new Ingredient("½ cup whole or split urad dal/black gram, husked, 120 grams"));
        ingredients.add(new Ingredient("¼ cup thick poha/flattened rice, 20 grams"));
        ingredients.add(new Ingredient("¼ tsp methi seeds/fenugreek seeds"));
        ingredients.add(new Ingredient("2 cups water for soaking rice"));
        ingredients.add(new Ingredient("1 cup water for soaking urad dal"));
        ingredients.add(new Ingredient("½ cup water for grinding urad dal or add as required"));
        ingredients.add(new Ingredient("¾ cup water for grinding rice or add as required"));
        ingredients.add(new Ingredient("1 tsp rock salt"));
        ingredients.add(new Ingredient("oil as required to apply to the idli moulds"));
        ingredients.add(new Ingredient("2.5 cups water for steaming idlis"));


        List<Direction> directions = new ArrayList<>();
        directions.add(new Direction("pick and rinse both the regular rice and parboiled rice."));
        directions.add(new Direction("rinse the poha and add to the rice."));
        directions.add(new Direction("add water. mix well. cover and keep the rice + poha to soak for 4 to 5 hours."));
        directions.add(new Direction("in a separate bowl, rinse the urad dal and methi seeds a couple of times."));
        directions.add(new Direction("soak the urad dal with methi seeds separately in water for 4-5 hours."));
        directions.add(new Direction("drain the soaked urad dal. reserve the water."));
        directions.add(new Direction("grind the urad dal, methi seed with ¼ cup of the reserved water for some seconds. then add remaining ¼ cup water. grind till you get a smooth and fluffy batter."));
        directions.add(new Direction("remove the urad dal batter in a bowl and keep aside."));
        directions.add(new Direction("grind the rice in batches to make a smooth batter."));
        directions.add(new Direction("mix both the batters together in a large bowl or pan. add salt and mix well."));
        directions.add(new Direction("cover and let the batter ferment for 8-9 hours or more if required. after the fermentation process is over, the batter will become double in size and rise."));
        directions.add(new Direction("grease the idli moulds. pour the batter in the moulds and steam the idlis in a pressure cooker or steamer."));
        directions.add(new Direction("if using pressure cooker remove the vent weight.whistle. steam for 10-12 mins or until the idlis are done."));
        directions.add(new Direction("serve the steaming hot idlis with coconut chutney and sambar. remaining batter can be stored in the refrigerator for a couple of days."));
        databaseAdapter.addNewRecipe(new Recipe("Idli",
                "South Indian Breakfast", "Idli or idly are a type of savoury rice cake, originating from the Indian subcontinent, popular as breakfast foods in Southern India and in Sri Lanka. The cakes are made by steaming a batter consisting of fermented black lentils (de-husked) and rice.", ingredients, directions, file.getAbsolutePath()));

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.medu_vada);
        extStorageDirectory = Environment.getExternalStorageDirectory().toString();

        file = new File(extStorageDirectory, "medu_vada.jpg");
        outStream = new FileOutputStream(file);
        bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();

        List<Ingredient> ingredients1 = new ArrayList<>();
        ingredients1.add(new Ingredient("1 cup urad dal"));
        ingredients1.add(new Ingredient("1 inch ginger, finely chopped"));
        ingredients1.add(new Ingredient("2 green chilli, finely chopped"));
        ingredients1.add(new Ingredient("½ tsp salt"));
        ingredients1.add(new Ingredient("pinch hing / asafoetida"));
        ingredients1.add(new Ingredient("1 tbsp dry coconut (chopped)"));
        ingredients1.add(new Ingredient("as needed few curry leaves, finely chopped"));
        ingredients1.add(new Ingredient("2 tbsp coriander, finely chopped"));
        ingredients1.add(new Ingredient("1 medium Onion or 2-3 shallots, finely chopped (optional)"));
        ingredients1.add(new Ingredient("1 tsp Cumin Seeds"));
        ingredients1.add(new Ingredient("½ tsp crushed Black Peppercorns"));
        ingredients1.add(new Ingredient("as needed oil for deep frying"));

        List<Direction> directions1 = new ArrayList<>();
        directions1.add(new Direction("Soak 1 cup urad dal in enough water for 3 hours. do not over soak, as the vadas will absorb oil. Drain off the water and grind to fine fluffy paste adding water as required, to make smooth thick paste. Transfer this paste into large mixing bowl. beat and mix the batter in circular motion till they turn light. This helps to incorporate air into batter and make medu vada soft and fluffy."));
        directions1.add(new Direction("Now add finely chopped ginger, chopped onion, cumin seeds, crushedpepper, few chopped curry leaves, chopped coriander, chopped dry coconut, pinch of hing and ½ tsp salt to the thick batter."));
        directions1.add(new Direction("Mix the batter well with the help of spoon."));
        directions1.add(new Direction("Heat the oil in large kadai. Wet your hand with enough water and take a small ball size dough and make it round. now slowly shape the edges and make a hole at the centre."));
        directions1.add(new Direction("Drop the vada in hot oil and deep fry the medu vada on medium flame. Fry on both sides till they turn golden brown and crisp."));
        directions1.add(new Direction("Finally, serve hot medu vada with coconut chutney or sambar of your choice."));

        databaseAdapter.addNewRecipe(new Recipe("Medu vada",
                "South Indian Breakfast", "Medu vada is a South Indian fritter made from Vigna mungo (black lentil or urad dal). It is usually made in a doughnut shape, with a crispy exterior and soft interior.", ingredients1, directions1, file.getAbsolutePath()));
    }

    private void loadBakerySnacksRecipes() throws IOException {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.veg_puffs);
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();

        File file = new File(extStorageDirectory, "veg_puffs.jpg");
        FileOutputStream outStream = new FileOutputStream(file);
        bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();

        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("3 cups maida / all-purpose flour / plain flour"));
        ingredients.add(new Ingredient("salt to taste"));
        ingredients.add(new Ingredient("2 tbsp butter, unsalted"));
        ingredients.add(new Ingredient("cold water, as required"));
        ingredients.add(new Ingredient("400 grams butter, cold"));
        ingredients.add(new Ingredient("2 tsp lemon juice / vinegar"));
        ingredients.add(new Ingredient("2 tsp oil"));
        ingredients.add(new Ingredient("½ tsp cumin seeds / jeera"));
        ingredients.add(new Ingredient("½ medium sized onion, finely chopped"));
        ingredients.add(new Ingredient("1 green chilli, finely chopped"));
        ingredients.add(new Ingredient("1 tsp ginger-garlic paste"));
        ingredients.add(new Ingredient("½ cup peas / matar"));
        ingredients.add(new Ingredient("½ cup beetroot, finely chopped"));
        ingredients.add(new Ingredient("1 carrot, finely chopped"));
        ingredients.add(new Ingredient("½ tsp turmeric / haldi"));
        ingredients.add(new Ingredient("½ tsp kashmiri red chili powder / lal mirch powder"));
        ingredients.add(new Ingredient("½ tsp garam masala"));
        ingredients.add(new Ingredient("½ tsp aamchur powder / dry mango powder"));
        ingredients.add(new Ingredient("2 medium sized potato, boiled, peeled & mashed"));
        ingredients.add(new Ingredient("2 tbsp coriander leaves, finely chopped"));
        ingredients.add(new Ingredient("2 tbsp butter"));

        List<Direction> directions = new ArrayList<>();
        directions.add(new Direction("firstly, take the pastry sheet and roll to thin and uniform sheet."));
        directions.add(new Direction("cut the sides and cut to rectangle pieces."));
        directions.add(new Direction("place the prepared stuffing in the centre."));
        directions.add(new Direction("and fold half, press and seal the sides."));
        directions.add(new Direction("now place in the baking tray."));
        directions.add(new Direction("brush the puff with butter for more golden colour."));
        directions.add(new Direction("bake the puffs in preheated oven at 140 degrees for 25 minutes."));
        directions.add(new Direction("finally, veg puffs or veg patties is ready to serve with tomato sauce."));

        databaseAdapter.addNewRecipe(new Recipe("Veg Puff",
                "Bakery Snacks", "puff or patties is one of the common snack recipe across all the south east asian countries, but the filling may differ from region to region. in india there are several varieties, including veg puff, egg puff, paneer puff and chicken puff recipe which is typically consumed as evening snack and even sometime as breakfast.", ingredients, directions, file.getAbsolutePath()));
    }

    private void loadFastFoodRecipes() throws IOException {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.tawa_burger);
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();

        File file = new File(extStorageDirectory, "tawa_burger.jpg");
        FileOutputStream outStream = new FileOutputStream(file);
        bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();


        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("1/2 Cup Onion (Roughly Chopped)"));
        ingredients.add(new Ingredient("1/2 Cup Capsicum (Roughly Chopped)"));
        ingredients.add(new Ingredient("/4 Cup Cabbage (Finely Chopped)"));
        ingredients.add(new Ingredient("1/2 tbsp Red Chilli Powder"));
        ingredients.add(new Ingredient("1/4 tbsp Turmeric Powder"));
        ingredients.add(new Ingredient("1/4 tbsp Coriander Powder"));
        ingredients.add(new Ingredient("1/4 tbsp Garam Masala"));
        ingredients.add(new Ingredient("Salt as per Taste"));
        ingredients.add(new Ingredient("1/2 tbsp Schezwan Sauce (Schezwan Chutney)"));
        ingredients.add(new Ingredient("1 tbsp Tomato Ketchup"));
        ingredients.add(new Ingredient("1/4 Cup Paneer (Cottage Cheese) (Roughly Chopped)"));
        ingredients.add(new Ingredient("Process Cheese"));
        ingredients.add(new Ingredient("1/2 Cup Mayonnaise Spread"));


        List<Direction> directions = new ArrayList<>();
        directions.add(new Direction("Firstly, take tawa and add roughly chopped onion and saute for 2 minutes."));
        directions.add(new Direction("Secondly, add roughly chopped capsicum and again saute for 2 minutes."));
        directions.add(new Direction("Moreover, add finely chopped cabbage in it and mix for 1 minute."));
        directions.add(new Direction("Now add dry spices like red chilli powder, turmeric powder, coriander powder, garam masala and salt as per taste. Then mix well."));
        directions.add(new Direction("Furthermore, gather all ingredients and cover with normal any bowl and cook for 4-5 minutes on medium flame."));
        directions.add(new Direction("Make sure ingredients don’t start to burn. And now add roughly chopped paneer and mix again."));
        directions.add(new Direction("Now add all sauces like red chilli sauce, tomato ketchup and schezwan sauce and mix on low flame."));
        directions.add(new Direction("Tawa burger stuffing is ready."));
        directions.add(new Direction("Furthermore, cut the burger bun from middle and now on the same tawa, roast burger bun from both the side with few drops of oil or butter."));
        directions.add(new Direction("Spread 1-2 tbsp of mayonnaise on burger bun on one side."));
        directions.add(new Direction("Now on the same side spread prepared stuffing."));
        directions.add(new Direction("Then grate some process cheese on it. And cover the burger with second one bun."));
        directions.add(new Direction("Now roast burger on the same tawa for just 1 minute from both sides for 1 minute."));
        directions.add(new Direction("Our Tawa Burger is ready to serve. You can also serve with tomato ketchup."));

        databaseAdapter.addNewRecipe(new Recipe("Tawa burger",
                "Fast Food", "Tawa Burger is popular street food in Mumbai. The burger is commonly prepared with vegetable or meat patties, onion, tomato slices, pickled vegetables, and cheese slices. ", ingredients, directions, file.getAbsolutePath()));

    }

    private void loadChineseRecipes() throws IOException {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.fried_rice);
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();

        File file = new File(extStorageDirectory, "fried_rice.jpg");
        FileOutputStream outStream = new FileOutputStream(file);
        bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();



        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("1 ½ teaspoons + 2 tablespoons avocado oil or safflower oil, divided"));
        ingredients.add(new Ingredient("2 eggs, whisked together"));
        ingredients.add(new Ingredient("1 small white onion, finely chopped (about 1 cup)"));
        ingredients.add(new Ingredient("¼ teaspoon salt, more to taste"));
        ingredients.add(new Ingredient("1 tablespoon grated or finely minced fresh ginger"));
        ingredients.add(new Ingredient("2 large cloves garlic, pressed or minced"));
        ingredients.add(new Ingredient("inch of red pepper flakes"));
        ingredients.add(new Ingredient("2 cups cooked basmati rice"));
        ingredients.add(new Ingredient("1 cup greens (optional), such as spinach, baby kale or tatsoi"));
        ingredients.add(new Ingredient("3 green onions, chopped"));
        ingredients.add(new Ingredient("1 tablespoon reduced-sodium tamari or soy sauce"));
        ingredients.add(new Ingredient("Chili-garlic sauce or sriracha, for serving "));

        List<Direction> directions = new ArrayList<>();
        directions.add(new Direction("This recipe comes together quickly. Before you get started, make sure that all of your ingredients are prepped and within an arm’s reach from the stove. Also have an empty bowl nearby for holding the cooked eggs and veggies. I’m suggesting that you start over medium-high heat, but if at any point you catch a whiff of oil or food burning, reduce the heat to medium."));
        directions.add(new Direction("Warm a large cast iron or stainless steel skillet over medium-high heat until a few drops of water evaporate within a couple of seconds. Immediately add 1 ½ teaspoons of oil and swirl the pan to coat the bottom. Add the scrambled eggs and swirl the pan so they cover the bottom. Cook until they are just lightly set, flipping or stirring along the way. Transfer the eggs to a bowl and wipe out the pan with a heat-proof spatula."));
        directions.add(new Direction("Return the pan to heat and add 1 tablespoon of oil. Add the onion and carrots and cook, stirring often, until the onions are translucent and the carrots are tender, about 3 to 5 minutes."));
        directions.add(new Direction("Add the remaining veggies and salt. Continue cooking, stirring occasionally (don’t stir too often, or the veggies won’t have a chance to turn golden on the edges), until the veggies are cooked through and turning golden, about 3 to 5 more minutes. In the meantime, use the edge of your spatula or a spoon to break up the scrambled eggs into smaller pieces."));
        directions.add(new Direction("Add the greens (if using) and green onions, and stir to combine. Add the cooked veggies and eggs and stir to combine. Remove the pan from the heat and stir in the tamari and sesame oil. Taste, and add a little more tamari if you’d like more soy flavor (don’t overdo it or it will drown out the other flavors) or salt, if the dish needs an extra boost of overall flavor."));
        directions.add(new Direction("Divide into bowls and serve immediately. I usually serve mine with chili-garlic sauce or sriracha on the side. Leftovers store well in the refrigerator, covered, for 3 to 4 days (if you used purple cabbage, it might stain your scrambled eggs a funny blue color, but it’s fine to eat)."));
        directions.add(new Direction("This recipe comes together quickly. Before you get started, make sure that all of your ingredients are prepped and within an arm’s reach from the stove. Also have an empty bowl nearby for holding the cooked eggs and veggies. I’m suggesting that you start over medium-high heat, but if at any point you catch a whiff of oil or food burning, reduce the heat to medium."));
        databaseAdapter.addNewRecipe(new Recipe("Fried Rice",
                "Chinese", "Fried rice is a dish of cooked rice that has been stir-fried in a wok or a frying pan and is usually mixed with other ingredients such as eggs, vegetables, seafood, or meat"
                , ingredients, directions, file.getAbsolutePath()));


        bm = BitmapFactory.decodeResource(getResources(), R.drawable.hakka_noodles);
        extStorageDirectory = Environment.getExternalStorageDirectory().toString();

        file = new File(extStorageDirectory, "hakka_noodles.jpg");
        outStream = new FileOutputStream(file);
        bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();

        List<Ingredient> ingredients1 = new ArrayList<>();
        ingredients1.add(new Ingredient("300 grams noodles"));
        ingredients1.add(new Ingredient("1 tablespoon sesame oil"));
        ingredients1.add(new Ingredient("1 tablespoon vegetable oil"));
        ingredients1.add(new Ingredient("1 teaspoons minced garlic"));
        ingredients1.add(new Ingredient("1 teaspoon minced ginger"));
        ingredients1.add(new Ingredient("1 green chili sliced"));
        ingredients1.add(new Ingredient("1 stalk celery chopped"));
        ingredients1.add(new Ingredient(" medium red onion 100 grams, sliced"));
        ingredients1.add(new Ingredient("1 large carrot 100 grams, sliced"));
        ingredients1.add(new Ingredient("1 large red pepper 200 grams, sliced, can use mix of green and red"));
        ingredients1.add(new Ingredient("3 stalks green onion chopped + more to garnish"));
        ingredients1.add(new Ingredient("2.5 tablespoons soy sauce"));
        ingredients1.add(new Ingredient("1 tablespoon rice vinegar or white vinegar"));
        ingredients1.add(new Ingredient("1 teapoon hot sauce like sriracha"));
        ingredients1.add(new Ingredient("1/4 teaspoon black pepper powder"));
        ingredients1.add(new Ingredient("/2 teaspoon salt or to taste"));
        ingredients1.add(new Ingredient("pinch white pepper powder"));
        ingredients1.add(new Ingredient("1 teaspoon chili oil optional"));

        List<Direction> directions1 = new ArrayList<>();
        directions1.add(new Direction("Chop all the veggies before you start making the noodles."));
        directions1.add(new Direction("Boil the noodles according to instructions on the package. I was supposed to cook my noodles for 2 minutes only."));
        directions1.add(new Direction("Drain the noodles and wash under cold running water, this stops the cooking process. Add 1/2 tablespoon of vegetable oil to the noodles and toss so that the noodles get lightly coated with the oil and don’t stick to each other. Set it aside."));
        directions1.add(new Direction("In a wok heat 1 tablespoon vegetable oil and 1 tablespoon sesame oil on medium-high heat. Once the oil is hot add the minced garlic and ginger, sliced green chili and chopped celery."));
        directions1.add(new Direction("Saute for few seconds until the ginger garlic starts to change color."));
        directions1.add(new Direction("Add sliced onions and saute for a minute or two until the sides of the onion starts turning light golden brown."));
        directions1.add(new Direction("Add in the sliced carrots, bell pepper, green onion and cook for 1 minute on high heat. The veggies should remain crunchy."));
        directions1.add(new Direction("Push veggies to the side, lower the heat and add soy sauce, rice vinegar, hot sauce (like sriracha)."));
        directions1.add(new Direction("Toss the veggies to combine well with the sauce. Add in the black pepper, salt and white pepper."));
        directions1.add(new Direction("Stir in the boiled noodles. Using a pair of tongs, mix it well so that the noodles are well coated with the sauce."));
        directions1.add(new Direction("As a final touch, stir in a teaspoon of chili oil. This is totally optional. Toss the noodles well and garnish with more spring onion greens."));
        directions1.add(new Direction("Serve hakka noodles as such or with your favorite Indo chinese dishes! I love mine with chili paneer."));

        databaseAdapter.addNewRecipe(new Recipe("Hakka noodles",
                "Chinese", "Hakka noodles is a popular Indo-Chinese dish of stir fried noodles, veggies and sauces. It is hugely popular in Indian restaurants and street stalls. It is usually eaten on its own or with a side of Manchurian or chilli dishes like gobi manchurian or chilli paneer.", ingredients1, directions1, file.getAbsolutePath()));
    }

    private void loadStreetFoodRecipes() throws IOException {
        Bitmap bm = BitmapFactory.decodeResource(getResources(), R.drawable.vada_pav);
        String extStorageDirectory = Environment.getExternalStorageDirectory().toString();

        File file = new File(extStorageDirectory, "vada_pav.jpg");
        FileOutputStream outStream = new FileOutputStream(file);
        bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();


        List<Ingredient> ingredients = new ArrayList<>();
        ingredients.add(new Ingredient("2 tsp oil"));
        ingredients.add(new Ingredient("½ tsp mustard"));
        ingredients.add(new Ingredient("few curry leaves"));
        ingredients.add(new Ingredient("1 inch ginger"));
        ingredients.add(new Ingredient("2 clove garlic"));
        ingredients.add(new Ingredient("1 chilli"));
        ingredients.add(new Ingredient("2 tbsp coriander"));
        ingredients.add(new Ingredient("¼ tsp turmeric / haldi"));
        ingredients.add(new Ingredient("2 potato / aloo"));
        ingredients.add(new Ingredient("½ tsp salt"));
        ingredients.add(new Ingredient("¾ cup besan / gram flour"));
        ingredients.add(new Ingredient("¼ tsp kashmiri red chilli powder / lal mirch powder"));
        ingredients.add(new Ingredient("½ cup water"));

        List<Direction> directions = new ArrayList<>();
        directions.add(new Direction("firstly, take ball sized aloo mixture and dip in besan batter and coat well."));
        directions.add(new Direction("deep fry in hot oil stirring occasionally."));
        directions.add(new Direction("now slit the ladi pav and spread 1 tsp green chutney, ½ tsp tamarind chutney and ½ tsp dry garlic chutney."));
        directions.add(new Direction("place the prepared vada in center of pav, chura and fried chilli."));
        directions.add(new Direction("finally, press the vada pav and serve immediately."));


        databaseAdapter.addNewRecipe(new Recipe("Vada pav",
                "Street Food", "Vada pav is the common man’s food in Mumbai and is a popular street food snack across whole Maharashtra."
                , ingredients, directions, file.getAbsolutePath()));

        bm = BitmapFactory.decodeResource(getResources(), R.drawable.samosa);
        extStorageDirectory = Environment.getExternalStorageDirectory().toString();

        file = new File(extStorageDirectory, "samosa.jpg");
        outStream = new FileOutputStream(file);
        bm.compress(Bitmap.CompressFormat.JPEG, 100, outStream);
        outStream.flush();
        outStream.close();

        List<Ingredient> ingredients1 = new ArrayList<>();
        ingredients1.add(new Ingredient("2 cup maida / plain flour"));
        ingredients1.add(new Ingredient("¼ tsp ajwain / carom seeds"));
        ingredients1.add(new Ingredient("½ tsp salt"));
        ingredients1.add(new Ingredient("¼ cup oil"));
        ingredients1.add(new Ingredient("½ cup water"));
        ingredients1.add(new Ingredient("1 tsp cumin / jeera"));
        ingredients1.add(new Ingredient("½ tsp coriander seeds, crushed"));
        ingredients1.add(new Ingredient("½ tsp fennel / saunf"));
        ingredients1.add(new Ingredient("pinch hing / asafoetida"));
        ingredients1.add(new Ingredient("1 inch ginger, finely chopped"));
        ingredients1.add(new Ingredient("1 chilli, finely chopped"));
        ingredients1.add(new Ingredient("½ cup peas / matar"));
        ingredients1.add(new Ingredient("½ tsp kashmiri red chilli powder"));
        ingredients1.add(new Ingredient("½ tsp coriander powder"));
        ingredients1.add(new Ingredient("¼ tsp cumin powder / jeera powder"));
        ingredients1.add(new Ingredient("½ tsp aamchur / dry mango powder"));
        ingredients1.add(new Ingredient("½ tsp garam masala"));
        ingredients1.add(new Ingredient("¼ tsp pepper, crushed"));
        ingredients1.add(new Ingredient("¾ tsp salt"));
        ingredients1.add(new Ingredient("4 potato / aloo, boiled & mashed"));

        List<Direction> directions1 = new ArrayList<>();
        directions1.add(new Direction("pinch a ball sized dough and grease with oil."));
        directions1.add(new Direction("roll the dough into oval shape."));
        directions1.add(new Direction("now cut it horizontally, diving into 2 equal portions using a knife."));
        directions1.add(new Direction("grease with water and make cone."));
        directions1.add(new Direction("stuff 2 tbsp of prepared samosa masala into the cone."));
        directions1.add(new Direction("grease little water on the edges."));
        directions1.add(new Direction("close and seal tightly by pressing firmly."));
        directions1.add(new Direction("deep-fry the samosa on a low flame. alternatively bake at 180 degree celcius for 40 minutes."));
        directions1.add(new Direction("stir occasionally, frying the samosa on low flame for atleast 15 minutes."));
        directions1.add(new Direction("once the aloo samosa turns golden and crisp, drain off over kitchen paper."));
        directions1.add(new Direction("finally, enjoy aloo samosa with green chutney and tamarind chutney."));


        databaseAdapter.addNewRecipe(new Recipe("Samosa",
                "Street Food", "Samosas are a popular snack in the local cuisines of South Asia, Western Asia, Southeast Asia, the Mediterranean, and Africa.", ingredients1, directions1, file.getAbsolutePath()));
    }

    private void navigateToLogin() {
        Intent startIntent = new Intent(this, LoginActivity.class);
        startActivity(startIntent);
        finish();
    }

    private void navigateToMainPage() {
        Intent startIntent = new Intent(this, MainActivity.class);
        startActivity(startIntent);
        finish();
    }
}
