package by.drozdovskaya.xml.entity;

import java.util.ArrayList;
import java.util.List;

public class Families {
	
	private List<Family> families;
	
	
	
	public Families() {
		this.families = new ArrayList<>();
	}



	public void addFamily(Family family) {
		this.families.add(family);
	}



	@Override
	public String toString() {
		return "Families: families=" + families;
	}
	
	
}
