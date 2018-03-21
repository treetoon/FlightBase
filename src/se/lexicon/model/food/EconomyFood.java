package se.lexicon.model.food;

import se.lexicon.utilities.SectionType;

public class EconomyFood extends Food {

	public EconomyFood(int id, Double price, String name, SectionType sectionType) {
		super(id, price, name, sectionType.ECONOMY);
	}
   
}
