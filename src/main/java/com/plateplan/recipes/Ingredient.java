package com.plateplan.recipes;

public class Ingredient {
	private final String name;
	private final String type;
	private final String goal;
	private final double quantity;
	private final String unit;

	private Ingredient(IngredientBuilder builder) {
		this.name = builder.name;
		this.type = builder.type;
		this.goal = builder.goal;
		this.quantity = builder.quantity;
		this.unit = builder.unit;
	}

	// Getters
	public String getName() {
		return name;
	}

	public String getType() {
		return type;
	}

	public String getGoal() {
		return goal;
	}

	public double getQuantity() {
		return quantity;
	}

	public String getUnit() {
		return unit;
	}

	// Builder class
	public static class IngredientBuilder {
		private String name;
		private String type;
		private String goal;
		private double quantity;
		private String unit;

		public IngredientBuilder name(String name) {
			this.name = name;
			return this;
		}

		public IngredientBuilder type(String type) {
			this.type = type;
			return this;
		}

		public IngredientBuilder goal(String goal) {
			this.goal = goal;
			return this;
		}

		public IngredientBuilder quantity(double quantity) {
			this.quantity = quantity;
			return this;
		}

		public IngredientBuilder unit(String unit) {
			this.unit = unit;
			return this;
		}

		public Ingredient build() {
			validateIngredientData();
			return new Ingredient(this);
		}

		private void validateIngredientData() {
			if (name == null || name.trim().isEmpty()) {
				throw new IllegalStateException("Ingredient name is required");
			}
			if (quantity < 0) {
				throw new IllegalStateException("Quantity cannot be negative");
			}
		}
	}
}