DROP TABLE recipe_ingredient;
DROP TABLE ingredient;
DROP TABLE recipe;

CREATE TABLE ingredient (
    ingredient_title VARCHAR(256) NOT NULL,
    best_before DATE DEFAULT NULL,
    use_by DATE DEFAULT NULL,
    PRIMARY KEY (ingredient_title)
);

INSERT INTO ingredient (ingredient_title, best_before, use_by) VALUES
    ('Ham','2030-12-31','2030-01-01'),
    ('Cheese','1999-01-01','2030-01-01'),
    ('Bread','2030-12-31','2030-01-01'),
    ('Butter','2030-12-31','2030-01-01'),
    ('Bacon','2030-12-31','2030-01-01'),
    ('Eggs','2030-12-31','2030-01-01'),
    ('Baked Beans','2030-12-31','2030-01-01'),
    ('Mushrooms','2030-12-31','2030-01-01'),
    ('Sausage','2030-12-31','2030-01-01'),
    ('Hotdog Bun','2030-12-31','2030-01-01'),
    ('Ketchup','2030-12-31','2030-01-01'),
    ('Mustard','2030-12-31','2030-01-01'),
    ('Lettuce','2030-12-31','2030-01-01'),
    ('Tomato','2030-12-31','2030-01-01'),
    ('Cucumber','2030-12-31','2030-01-01'),
    ('Beetroot','2030-12-31','2030-01-01'),
    ('Salad Dressing','2030-12-31','1999-01-01'),
    ('Spinach','2030-12-31','1999-01-01'),
    ('Milk','2030-12-31','1999-01-01');

CREATE TABLE recipe (
     recipe_title VARCHAR(256) NOT NULL,
     PRIMARY KEY (recipe_title)
);

INSERT INTO recipe (recipe_title) VALUES
    ('Ham and Cheese Toastie'),
    ('Fry-up'),
    ('Salad'),
    ('Hotdog'),
    ('Omelette')
;

CREATE TABLE recipe_ingredient (
    recipe_title VARCHAR(256) NOT NULL,
    ingredient_title VARCHAR(256) NOT NULL,
    PRIMARY KEY (recipe_title, ingredient_title),
    CONSTRAINT FK_recipe FOREIGN KEY (recipe_title) REFERENCES recipe (recipe_title),
    CONSTRAINT FK_ingredient FOREIGN KEY (ingredient_title) REFERENCES ingredient (ingredient_title)
);

INSERT INTO recipe_ingredient (recipe_title, ingredient_title) VALUES
    ('Ham and Cheese Toastie','Ham'),
    ('Ham and Cheese Toastie','Cheese'),
    ('Ham and Cheese Toastie','Bread'),
    ('Ham and Cheese Toastie','Butter'),

    ('Fry-up','Bacon'),
    ('Fry-up','Eggs'),
    ('Fry-up','Baked Beans'),
    ('Fry-up','Mushrooms'),
    ('Fry-up','Sausage'),
    ('Fry-up','Bread'),

    ('Salad','Lettuce'),
    ('Salad','Tomato'),
    ('Salad','Cucumber'),
    ('Salad','Beetroot'),
    ('Salad','Salad Dressing'),

    ('Hotdog','Hotdog Bun'),
    ('Hotdog','Sausage'),
    ('Hotdog','Ketchup'),
    ('Hotdog','Mustard'),

    ('Omelette','Eggs'),
    ('Omelette','Mushrooms'),
    ('Omelette','Milk'),
    ('Omelette','Spinach')
;

