package models;

import java.util.ArrayList;
import java.util.List;

public class Graph {
	private List<Node> nodes = new ArrayList<>();
	private List<Node> path = new ArrayList<>();

	public Graph() {
	}

	public Graph(List<Node> nodes) {
		super();
		this.nodes = nodes;
	}

	public List<Node> getNodes() {
		return nodes;
	}

	public void setNodes(List<Node> nodes) {
		this.nodes = nodes;
	}

	public ArrayList<String> searchA(Node startNode, Node endNode) {
		List<Node> closed = new ArrayList<>();
		List<Node> open = new ArrayList<Node>();
		this.cleanGraph();

		open.add(startNode);
		startNode.setG(0);
		startNode.setH(endNode);
		ArrayList<String> result = new ArrayList<>();
		while (!open.isEmpty()) {
			Node currentNode = open.get(0);
			System.out.println(currentNode.getName());

			result.add(currentNode.getName());
			double minF = currentNode.getF();
			boolean tentative_is_better = false;
			// choose node open with minimum F
			if (open.size() > 1) {
				for (int i = 1; i < open.size(); i++) {
					if (open.get(i).getF() < minF) {
						currentNode = open.get(i);
						minF = open.get(i).getF();
					}
				}
			}
			// build path
			if (currentNode.getName() == endNode.getName()) {
				Node prevStep = endNode;
				while (prevStep != null) {
					path.add(prevStep);
					Node nodeWithLink = prevStep.getCameFrom();
					prevStep = nodeWithLink;
				}
				return result;
			}

			open.remove(currentNode);
			closed.add(currentNode);
			linkLoop: for (Link link : currentNode.getLinks()) {
				// if next node in closed list
				for (Node n : closed) {
					if (link.getNodeEnd().getName() == n.getName()) {
						continue linkLoop;
					}
				}

				double tentative_g_score = currentNode.getG() + link.getLength();
				// if next node in open list
				boolean inOpenList = false;
				for (Node n : open) {
					if (link.getNodeEnd().getName() == n.getName()) {
						inOpenList = true;
						if (tentative_g_score < link.getNodeEnd().getG()) {
							tentative_is_better = true;
						} else {
							tentative_is_better = false;
						}
					}
				}
				if (!inOpenList) {
					open.add(link.getNodeEnd());
					tentative_is_better = true;
				}
				if (tentative_is_better) {
					int index = open.size() - 1;
					open.get(index).setCameFrom(currentNode);
					open.get(index).setG(tentative_g_score);
					open.get(index).setH(endNode);
				}
			}
		}
		ArrayList<String> False = new ArrayList<>();
		False.add("FALSE");
		return False;
	}

	public List<Node> getPath() {
		return path;
	}

	public int getNodeIdByName(String name) {
		for (int i = 0; i < nodes.size(); i++) {
			if (nodes.get(i).getName() == name) {
				return i;
			}
		}
		return -1;
	}

	private void cleanGraph() {
		for (Node n : nodes) {
			n.setCameFrom(null);
			n.setG(0.0);
			n.setH(null);
		}
		path = new ArrayList<>();
	}

	int x;
	int y;

	public ArrayList<String> searchCoor(Node startNode, Node endNode) {
		ArrayList<String> FAIL = new ArrayList<>();
		FAIL.add("FAIL");
		if (!nodes.contains(startNode) || !nodes.contains(endNode))
			return FAIL;
		x = endNode.getX();
		y = endNode.getY();

		ArrayList<Node> queue = new ArrayList<>();
		ArrayList<String> result = new ArrayList<>();

		queue.add(startNode);

		while (!queue.isEmpty()) {
			Node temp = queue.get(0);
			queue.remove(0);
			temp.isInPath();

			System.out.println(temp.getName());

			if (temp.equals(endNode))
				return result;

			for (Link l : temp.paths) {

				if (!l.getNodeEnd().isInPath() && !queue.contains(l.getNodeEnd())) {
					addToQueue(l.getNodeEnd(), queue);
				}
			}
		}

		return FAIL;
	}

	private void addToQueue(Node endNode, ArrayList<Node> queue) {
		double distance = Math.sqrt(Math.pow(endNode.getX() - x, 2) + Math.pow(endNode.getY() - y, 2));
		endNode.distance = distance;

		for (int i = 0; i < queue.size(); i++) {
			if (queue.get(i).distance > distance) {
				queue.add(i, endNode);
				return;
			}
		}

		queue.add(endNode);
	}

	public ArrayList<String> breath(Node startNode, Node endNode) {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> FALSE = new ArrayList<>();
		FALSE.add("FALSE");
		if (!nodes.contains(startNode) || !nodes.contains(endNode))
			return FALSE;

		ArrayList<Node> queue = new ArrayList<>();
		queue.removeAll(nodes);
		queue.add(startNode);

		while (queue.size() > 0) {
			Node current = queue.get(0);
			System.out.println(current.getName() + "-");
			result.add(current.getName());
			if (current.getName().contains((CharSequence) endNode))
				return result;

			queue.remove(0);
			current.isInPath();

			for (int i = 0; i < current.paths.size(); i++) {
				Link link = current.paths.get(i);

				if (!link.getNodeEnd().isInPath() && !queue.contains(link.getNodeEnd())) {
					queue.add(0, link.getNodeEnd());
				}
			}

		}

		
		return result;

	}

	public ArrayList<String> weight(Node startNode, Node endNode) {
		ArrayList<String> result = new ArrayList<>();
		ArrayList<String> FALSE = new ArrayList<>();
		FALSE.add("FALSE");
		if (!nodes.contains(startNode) || !nodes.contains(endNode))
			return FALSE;

		ArrayList<Node> queue = new ArrayList<>();
		queue.add(startNode);

		while (queue.size() > 0) {
			Node node = queue.get(0);
			node.isInPath();
			queue.remove(0);

			System.out.println(node.getName());

			if (node.getName().equals(endNode))
				return result;

			for (int i = 0; i < node.paths.size(); i++) {
				Link l = node.paths.get(i);

				if (queue.contains(l.getNodeEnd()) || l.getNodeEnd().isInPath()) {
					continue;
				}

				addNodeToQueue(l.getNodeEnd(), queue);
			}

		}

		return FALSE;
	}

	private void addNodeToQueue(Node endPoint, ArrayList<Node> queue) {
		for (int i = 0; i < queue.size(); i++) {
			if (endPoint.getWeight() < queue.get(i).getWeight()) {
				queue.add(i, endPoint);
				return;
			}
		}
		queue.add(endPoint);
	}
}
