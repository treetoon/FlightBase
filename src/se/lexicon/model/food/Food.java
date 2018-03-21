package se.lexicon.model.food;

import se.lexicon.utilities.SectionType;

public abstract class Food {
	static private int id =0;
	private Double price;
    private String name;
    private SectionType sectionType;
    
    public Food(int id, Double price, String name, SectionType sectionType) {
		id++;
		this.price = price;
		this.name = name;
	}
    
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SectionType getSectionType() {
		return sectionType;
	}
	public void setSectionType(SectionType sectionType) {
		this.sectionType = sectionType;
	}

	@Override
	public String toString() {
		return getName() + " " + getPrice();
	}
   
}
