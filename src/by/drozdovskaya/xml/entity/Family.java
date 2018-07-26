package by.drozdovskaya.xml.entity;

import java.util.ArrayList;
import java.util.List;

public class Family {
	
	private Mother mother;
	private Father father;
	private List<Child> children;
	
	public Family() {
		this.children = new ArrayList<>();
	}

	public Family(Mother mother, Father father, List<Child> children) {
		this.mother = mother;
		this.father = father;
		this.children = children;
	}

	public Mother getMother() {
		return mother;
	}

	public void setMother(Mother mother) {
		this.mother = mother;
	}

	public Father getFather() {
		return father;
	}

	public void setFather(Father father) {
		this.father = father;
	}

	public List<Child> getChildren() {
		return children;
	}

	public void setChildren(List<Child> children) {
		this.children = children;
	}
	public void addChild(Child child) {
		this.children.add(child);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((children == null) ? 0 : children.hashCode());
		result = prime * result + ((father == null) ? 0 : father.hashCode());
		result = prime * result + ((mother == null) ? 0 : mother.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Family other = (Family) obj;
		if (children == null) {
			if (other.children != null)
				return false;
		} else if (!children.equals(other.children))
			return false;
		if (father == null) {
			if (other.father != null)
				return false;
		} else if (!father.equals(other.father))
			return false;
		if (mother == null) {
			if (other.mother != null)
				return false;
		} else if (!mother.equals(other.mother))
			return false;
		return true;
	}
	

	@Override
	public String toString() {

		return "\n Family: " + mother + ", " + father + ", " + children;
	}
	
	

}
