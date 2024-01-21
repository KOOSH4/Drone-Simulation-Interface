package de.frauas.dronesimulation.app.ui;

import javax.swing.JFrame;
import javax.swing.JLabel;

import java.util.List;

import javax.swing.JButton;
import javax.swing.JPanel;
//
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
//
import java.awt.Dimension;

import de.frauas.dronesimulation.app.dronelist.DroneList;

public class uiHandler extends JFrame {

	public uiHandler(List<DroneList> listOfDrones) {
		setTitle("Drone Simulation Interface");
		setSize(1300, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		JLabel label = new JLabel("Hello, Drone!");
		JButton refreshButton = new JButton("Refresh");
		String[] dronesArray = new String[listOfDrones.size()];
		for (DroneList drone : listOfDrones) {

			dronesArray[listOfDrones.indexOf(drone)] = drone.getDroneType().getManufacturer();
			// System.out.println(drone.getSerialnumber().toString());
		}

		JList<String> DroneTable = new JList<>(dronesArray);
		DroneTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DroneTable.setVisibleRowCount(-1); // make all items visible

		JScrollPane DroneTableScroller = new JScrollPane(DroneTable);

		DroneTableScroller.setPreferredSize(new Dimension(300, 200)); // Set your desired width and height
		panel.add(DroneTableScroller);
		panel.add(label);
		panel.add(refreshButton);

		add(panel);
	}

}
