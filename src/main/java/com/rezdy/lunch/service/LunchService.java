package com.rezdy.lunch.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LunchService {

	@Autowired
	private EntityManager entityManager;

	private List<Recipe> recipeSorted;

	public List<Recipe> getNonExpiredRecipesOnDate(LocalDate date) {
		List<Recipe> recipes = loadRecipes(date);

		sortRecipes(recipes);

		return recipeSorted;
	}

	private void sortRecipes(List<Recipe> recipes) {
		recipes.stream().forEach(recipe -> {
			Collections.sort(recipe.getIngredientsSet(), new SortByBestBefore());
		});
		recipeSorted = recipes;
	}

	public List<Recipe> loadRecipes(LocalDate date) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteriaQuery = cb.createQuery(Recipe.class);
		Root<Recipe> recipeRoot = criteriaQuery.from(Recipe.class);

		CriteriaQuery<Recipe> query = criteriaQuery.select(recipeRoot);

		Subquery<Recipe> nonExpiredIngredientSubquery = query.subquery(Recipe.class);
		Root<Recipe> nonExpiredIngredient = nonExpiredIngredientSubquery.from(Recipe.class);
		nonExpiredIngredientSubquery.select(nonExpiredIngredient);

		Predicate matchingRecipe = cb.equal(nonExpiredIngredient.get("title"), recipeRoot.get("title"));
		Predicate expiredIngredient = cb.lessThan(nonExpiredIngredient.join("ingredients").get("useBy"), date);

		Predicate allNonExpiredIngredients = cb
				.exists(nonExpiredIngredientSubquery.where(matchingRecipe, expiredIngredient));

		return entityManager.createQuery(query.where(allNonExpiredIngredients)).getResultList();
	}

	public List<Recipe> getRecipesByTitle(String title) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteriaQuery = cb.createQuery(Recipe.class);
		Root<Recipe> recipeRoot = criteriaQuery.from(Recipe.class);

		CriteriaQuery<Recipe> query = criteriaQuery.select(recipeRoot);

		List<Recipe> recipes = entityManager.createQuery(query.where(cb.equal(recipeRoot.get("title"), title)))
				.getResultList();

		if (recipes.isEmpty()) {
			throw new RuntimeException("Bad Request");
		}
		return recipes;
	}

	// Not Completed
	public List<Recipe> excludeRecipesByTitle(List<String> titles) {
		CriteriaBuilder cb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Recipe> criteriaQuery = cb.createQuery(Recipe.class);
		Root<Recipe> recipeRoot = criteriaQuery.from(Recipe.class);
		CriteriaQuery<Recipe> query = criteriaQuery.select(recipeRoot);
		List<Recipe> recipes = entityManager.createQuery(query.where(recipeRoot.get("ingredients"))).getResultList();

		return recipes;
	}
}
