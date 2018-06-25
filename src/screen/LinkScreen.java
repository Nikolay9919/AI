package screen;

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Graph;
import models.Link;
import models.Node;

import javax.swing.JTable;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextPane;
import javax.swing.JRadioButton;

public class LinkScreen extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private static final int ASTAR = 1;
	private static final int COOR = 2;
	private static final int BREATH = 3;
	private static final int WEIGHT = 3;

	private JPanel contentPane;
	private JTable tableFrom;
	private JTable tableTo;
	private JButton btnMakeOneWay;
	private JButton btnMakeTwoWay;

	private NodeTableTM ntm;
	private JTextField tfLength;
	private JLabel lblWeight;

	private JButton btnAddNewNode;

	private Graph graph;
	private NodeAddScreen nodeAddScreen;
	private JButton btnFindPath;

	private JTextPane tpLinks;
	private JTextPane tpResult;

	private int searchAlgorithm = ASTAR;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LinkScreen frame = new LinkScreen();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LinkScreen() {
		setTitle("Graph");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 800, 533);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(2, 2, 2, 2));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		tableFrom = new JTable();
		tableFrom.setBounds(10, 56, 260, 190);
		contentPane.add(tableFrom);

		tableTo = new JTable();
		tableTo.setBounds(400, 56, 260, 190);
		contentPane.add(tableTo);

		btnMakeOneWay = new JButton("Make One Way Link");
		btnMakeOneWay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graph.getNodes().get(tableFrom.getSelectedRow()).getLinks().add(
						new Link(Integer.parseInt(tfLength.getText()), graph.getNodes().get(tableTo.getSelectedRow())));
				tpLinks.setText(tpLinks.getText() + "\n" + graph.getNodes().get(tableFrom.getSelectedRow()).getName()
						+ " => " + graph.getNodes().get(tableTo.getSelectedRow()).getName());
			}
		});
		btnMakeOneWay.setBounds(243, 413, 183, 23);
		contentPane.add(btnMakeOneWay);

		btnMakeTwoWay = new JButton("Make Two Way Link");
		btnMakeTwoWay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				graph.getNodes().get(tableFrom.getSelectedRow()).getLinks().add(
						new Link(Integer.parseInt(tfLength.getText()), graph.getNodes().get(tableTo.getSelectedRow())));
				graph.getNodes().get(tableTo.getSelectedRow()).getLinks().add(new Link(
						Integer.parseInt(tfLength.getText()), graph.getNodes().get(tableFrom.getSelectedRow())));
				tpLinks.setText(tpLinks.getText() + "\n" + graph.getNodes().get(tableFrom.getSelectedRow()).getName()
						+ " <=> " + graph.getNodes().get(tableTo.getSelectedRow()).getName());
			}
		});
		btnMakeTwoWay.setBounds(243, 447, 183, 23);
		contentPane.add(btnMakeTwoWay);

		tfLength = new JTextField();
		tfLength.setBounds(300, 77, 73, 20);
		contentPane.add(tfLength);
		tfLength.setColumns(10);

		lblWeight = new JLabel("Length");
		lblWeight.setBounds(313, 56, 46, 14);
		contentPane.add(lblWeight);

		btnAddNewNode = new JButton("Add new Node");
		btnAddNewNode.setBounds(46, 413, 171, 23);
		contentPane.add(btnAddNewNode);

		btnFindPath = new JButton("Find Path");
		btnFindPath.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String> result = new ArrayList<>();
				tpResult.setText("");
				tpResult.setText(tpResult.getText() + "\nPath:\n");
				Node startNode = graph.getNodes().get(tableFrom.getSelectedRow());
				Node endNode = graph.getNodes().get(tableTo.getSelectedRow());
				if (searchAlgorithm == ASTAR) {
					result = graph.searchA(startNode, endNode);
				}
				if (searchAlgorithm == COOR) {
					result = graph.searchCoor(startNode, endNode);
				}
				if (searchAlgorithm == BREATH) {
					result = graph.breath(startNode, endNode);
				}
				if (searchAlgorithm == WEIGHT) {
					result = graph.weight(startNode, endNode);
				}

				tpResult.setText(result.toString());

			}
		});
		btnFindPath.setBounds(457, 413, 171, 23);
		contentPane.add(btnFindPath);

		tpLinks = new JTextPane();
		tpLinks.setBounds(291, 101, 91, 144);
		contentPane.add(tpLinks);

		JRadioButton rbGreed = new JRadioButton("Greedy search");
		rbGreed.setBounds(457, 447, 109, 23);
		rbGreed.setSelected(false);
		rbGreed.setActionCommand("greed");
		rbGreed.addActionListener(this);
		contentPane.add(rbGreed);

		JRadioButton rbAStar = new JRadioButton("A*");
		rbAStar.setBounds(566, 447, 62, 23);
		rbAStar.setSelected(true);
		rbAStar.setActionCommand("astar");
		rbAStar.addActionListener(this);
		contentPane.add(rbAStar);

		JRadioButton rbBreath = new JRadioButton("Breath");
		rbBreath.setBounds(620, 447, 100, 23);
		rbBreath.setSelected(true);
		rbBreath.setActionCommand("Breath");
		rbBreath.addActionListener(this);
		contentPane.add(rbBreath);

		JRadioButton rbWeight = new JRadioButton("Weight");
		rbWeight.setBounds(700, 447, 100, 23);
		rbWeight.setSelected(true);
		rbWeight.setActionCommand("Weight");
		rbWeight.addActionListener(this);
		contentPane.add(rbWeight);

		ButtonGroup bgSearch = new ButtonGroup();
		bgSearch.add(rbGreed);
		bgSearch.add(rbAStar);
		bgSearch.add(rbBreath);
		bgSearch.add(rbWeight);
		initGraph();

		btnAddNewNode.setActionCommand("add_node");

		tpResult = new JTextPane();
		tpResult.setBounds(20, 263, 629, 116);
		contentPane.add(tpResult);
		;
		btnAddNewNode.addActionListener(this);

	}

	private void initGraph() {
		graph = new Graph();
		updateNodes();
	}

	public void updateNodes() {
		List<String[]> values = new ArrayList<>();

		for (Node n : graph.getNodes()) {
			values.add(new String[] { n.getName(), String.valueOf(n.getX()), String.valueOf(n.getY()),
					String.valueOf(n.getWeight()) });
		}
		ntm = new NodeTableTM(values.toArray(new Object[][] {}));
		tableFrom.setModel(ntm);
		tableTo.setModel(ntm);
	}

	public Graph getGraph() {
		return graph;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "add_node":
			nodeAddScreen = new NodeAddScreen(this);
			nodeAddScreen.setVisible(true);
			break;
		case "greed":
			searchAlgorithm = COOR;
			break;
		case "astar":
			searchAlgorithm = ASTAR;
			break;
		case "breath":
			searchAlgorithm = BREATH;
			break;
		case "weight":
			searchAlgorithm = WEIGHT;
			break;
		default:
			break;
		}

	}
}
