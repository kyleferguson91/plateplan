package com.plateplan.recipes;

import com.google.gson.annotations.SerializedName;

public class Recipe {
	@SerializedName("idMeal")
	private String mealDbId;

	@SerializedName("strMeal")
	private String name;

	@SerializedName("strMealThumb")
	private String thumbnailUrl;

	// Getters and setters
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

	@Override
	public String toString() {
		return "Recipe{" +
				"mealDbId='" + mealDbId + '\'' +
				", name='" + name + '\'' +
				", thumbnailUrl='" + thumbnailUrl + '\'' +
				'}';
	}
}
