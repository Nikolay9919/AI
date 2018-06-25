package models;

import java.util.ArrayList;
import java.util.List;

public class Node {
	private String name;
	private int weight;
	private int x;
	private int y;
	List<Link> links = new ArrayList<>();
	private double g;
	private double h;
	private double f;
	private Node cameFrom;
	private boolean inPath;
	public double distance;
	public ArrayList<Link> paths = new ArrayList<>();
	public Node(String name, int weight, int x, int y) {
		super();
		this.name = name;
		this.weight = weight;
		this.x = x;
		this.y = y;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public double getG() {
		return g;
	}

	public void setG(double g) {
		this.g = g;
	}

	public double getF(Node targetNode) {
		return f;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getWeight() {
		return weight;
	}

	public void setWeight(int weight) {
		this.weight = weight;
	}

	public Node getCameFrom() {
		return cameFrom;
	}

	public void setCameFrom(Node cameFrom) {
		this.cameFrom = cameFrom;
	}

	public double getH() {
		return h;
	}

	public void setH(Node targetNode) {
		if (targetNode == null) {
			h = 0.0;
		} else {
			h = Math.sqrt((targetNode.getX() - this.x) * (targetNode.getX() - this.x)
					+ (targetNode.getY() - this.y) * (targetNode.getY() - this.y));
		}
	}

	public double getF() {
		return h + g;
	}

	public boolean isInPath() {
		return inPath;
	}

	public void setInPath(boolean inPath) {
		this.inPath = inPath;
	}

}
