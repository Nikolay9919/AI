package models;

public class Link {
	private int length;
	private Node nodeEnd;
	private boolean inPath;

	public Link(int length, Node nodeEnd) {
		super();
		this.length = length;
		this.nodeEnd = nodeEnd;
	}

	public int getLength() {
		return length;
	}

	public Node getNodeEnd() {
		return nodeEnd;
	}

	public boolean isInPath() {
		return inPath;
	}

	public void setInPath(boolean inPath) {
		this.inPath = inPath;
	}

}
