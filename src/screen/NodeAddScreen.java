package screen;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import models.Graph;
import models.Node;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;

import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.awt.event.ActionEvent;

public class NodeAddScreen extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField tfNodeName;
	private JFormattedTextField tfX;
	private JFormattedTextField tfY;
	private JFormattedTextField tfWeight;
	private LinkScreen ls;

	public NodeAddScreen(LinkScreen linkScreen) {

		ls = linkScreen;
		setTitle("Add node");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 293, 341);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JLabel lblNewLabel = new JLabel("Name");
		lblNewLabel.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel.setBounds(32, 38, 46, 14);
		contentPane.add(lblNewLabel);

		JLabel lblNewLabel_1 = new JLabel("X");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNewLabel_1.setBounds(32, 89, 46, 14);
		contentPane.add(lblNewLabel_1);

		JLabel lblY = new JLabel("Y");
		lblY.setHorizontalAlignment(SwingConstants.RIGHT);
		lblY.setBounds(32, 129, 46, 14);
		contentPane.add(lblY);

		JLabel lblWeight = new JLabel("Weight");
		lblWeight.setHorizontalAlignment(SwingConstants.RIGHT);
		lblWeight.setBounds(32, 183, 46, 14);
		contentPane.add(lblWeight);

		tfNodeName = new JTextField();
		tfNodeName.setBounds(109, 35, 86, 20);
		contentPane.add(tfNodeName);
		tfNodeName.setColumns(10);

		NumberFormat xyFormat = NumberFormat.getNumberInstance();
		tfX = new JFormattedTextField(xyFormat);
		tfX.setBounds(109, 86, 86, 20);
		contentPane.add(tfX);
		tfX.setColumns(10);

		tfY = new JFormattedTextField(xyFormat);
		tfY.setBounds(109, 126, 86, 20);
		contentPane.add(tfY);
		tfY.setColumns(10);

		tfWeight = new JFormattedTextField(xyFormat);
		tfWeight.setBounds(109, 180, 86, 20);
		contentPane.add(tfWeight);
		tfWeight.setColumns(10);

		JButton btnSave = new JButton("Save");
		btnSave.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				ls.getGraph().getNodes().add(new Node(tfNodeName.getText(), Integer.parseInt(tfWeight.getText()),
						Integer.parseInt(tfX.getText()), Integer.parseInt(tfY.getText())));
				ls.updateNodes();
				NodeAddScreen.this.dispose();
			}
		});
		btnSave.setBounds(94, 246, 89, 23);
		contentPane.add(btnSave);

	}
}
