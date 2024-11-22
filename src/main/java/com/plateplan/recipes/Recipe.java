package com.plateplan.recipes;

import com.google.gson.annotations.SerializedName;
import java.util.ArrayList;
import java.util.List;

public class Recipe {
	@SerializedName("idMeal")
	private String mealDbId;

	@SerializedName("strMeal")
	private String name;

	@SerializedName("strMealThumb")
	private String thumbnailUrl;

	private List<Ingredient> ingredients;
	private int calories;
	private int protein;
	private int carbs;
	private int fat;

	private Recipe(RecipeBuilder builder) {
		this.mealDbId = builder.mealDbId;
		this.name = builder.name;
		this.thumbnailUrl = builder.thumbnailUrl;
		this.ingredients = new ArrayList<>(builder.ingredients);
		this.calories = builder.calories;
		this.protein = builder.protein;
		this.carbs = builder.carbs;
		this.fat = builder.fat;
	}

	public String getMealDbId() {
		return mealDbId;
	}

	public void setMealDbId(String mealDbId) {
		this.mealDbId = mealDbId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getThumbnailUrl() {
		return thumbnailUrl;
	}

	public void setThumbnailUrl(String thumbnailUrl) {
		this.thumbnailUrl = thumbnailUrl;
	}

	public List<Ingredient> getIngredients() {
		return new ArrayList<>(ingredients);
	}

	public int getCalories() {
		return calories;
	}

	public int getProtein() {
		return protein;
	}

	public int getCarbs() {
		return carbs;
	}

	public int getFat() {
		return fat;
	}

	// Builder class
	public static class RecipeBuilder {
		private String mealDbId;
		private String name;
		private String thumbnailUrl;
		private List<Ingredient> ingredients = new ArrayList<>();
		private int calories;
		private int protein;
		private int carbs;
		private int fat;

		public RecipeBuilder mealDbId(String mealDbId) {
			this.mealDbId = mealDbId;
			return this;
		}

		public RecipeBuilder name(String name) {
			this.name = name;
			return this;
		}

		public RecipeBuilder thumbnailUrl(String thumbnailUrl) {
			this.thumbnailUrl = thumbnailUrl;
			return this;
		}

		public RecipeBuilder addIngredient(Ingredient ingredient) {
			this.ingredients.add(ingredient);
			return this;
		}

		public RecipeBuilder calories(int calories) {
			this.calories = calories;
			return this;
		}

		public RecipeBuilder protein(int protein) {
			this.protein = protein;
			return this;
		}

		public RecipeBuilder carbs(int carbs) {
			this.carbs = carbs;
			return this;
		}

		public RecipeBuilder fat(int fat) {
			this.fat = fat;
			return this;
		}

		public Recipe build() {
			validateRecipeData();
			return new Recipe(this);
		}

		private void validateRecipeData() {
			if (name == null || name.trim().isEmpty()) {
				throw new IllegalStateException("Recipe name is required");
			}
		}
	}

	@Override
	public String toString() {
		return "Recipe{" +
				"mealDbId='" + mealDbId + '\'' +
				", name='" + name + '\'' +
				", thumbnailUrl='" + thumbnailUrl + '\'' +
				", ingredients=" + ingredients +
				", calories=" + calories +
				", protein=" + protein +
				", carbs=" + carbs +
				", fat=" + fat +
				'}';
	}
}
