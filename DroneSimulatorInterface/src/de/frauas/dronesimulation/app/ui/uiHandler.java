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
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

//
import java.awt.Font;

import java.awt.Dimension;
//
import java.awt.GridLayout;

import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
//
import de.frauas.dronesimulation.app.dronelist.DroneList;

public class uiHandler extends JFrame {

	public uiHandler(List<DroneList> listOfDrones) {
		setTitle("Drone Simulation Interface");
		setSize(1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();
		JButton refreshButton = new JButton("Refresh");

		JButton aboutUsButton = new JButton("About Us");

		JLabel labelLastUpdate = new JLabel();

		// labels for drone list
		JLabel labelDroneListLable = new JLabel();

		JLabel labelId = new JLabel();
		JLabel labelSerialnumber = new JLabel();
		JLabel labelCarriageWeight = new JLabel();
		JLabel labelCarriageType = new JLabel();
		JLabel labelCreated = new JLabel();
		// labels for drone type
		JLabel labelDroneTypeLable = new JLabel();
		JLabel labelManufacturer = new JLabel();
		JLabel labelTypeId = new JLabel();
		JLabel labelTypeName = new JLabel();
		JLabel labelWeight = new JLabel();
		JLabel labelMaxSpeed = new JLabel();
		JLabel labelBatteryCapacity = new JLabel();
		JLabel labelControlRange = new JLabel();
		JLabel labelMaxCarriage = new JLabel();
		// labels for drone dynamics
		JLabel labelDroneDynamicLable = new JLabel();
		JLabel labelDDTimeStamp = new JLabel();
		JLabel labelDDSpeed = new JLabel();
		JLabel labelDDAlightRoll = new JLabel();
		JLabel labelDDAlightPitch = new JLabel();
		JLabel labelDDAlightYaw = new JLabel();
		JLabel labelDDLongitute = new JLabel();
		JLabel labelDDLatitude = new JLabel();
		JLabel labelDDBatteryStatus = new JLabel();
		JLabel labelDDLastSeen = new JLabel();
		JLabel labelDDStatus = new JLabel();

		String[] dronesArray = new String[listOfDrones.size()];
		for (DroneList drone : listOfDrones) {
			String droneTableInfos = "# " + drone.getId() + "    " + drone.getDroneType().getManufacturer();
			// System.out.println(droneTableInfos);
			dronesArray[listOfDrones.indexOf(drone)] = droneTableInfos;
			// System.out.println(drone.getSerialnumber().toString());
		}
		JList<String> DroneTable = new JList<>(dronesArray);

		DroneTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		DroneTable.setVisibleRowCount(-1); // make all items visible

		labelDroneListLable.setText("Drone Instance infos");
		labelDroneListLable.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font to Arial, bold, size

		labelDroneTypeLable.setText("Drone Type infos");
		labelDroneTypeLable.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font to Arial, bold, size

		labelDroneDynamicLable.setText("Drone Dynamic infos");
		labelDroneDynamicLable.setFont(new Font("Arial", Font.BOLD, 20)); // Set the font to Arial, bold,

		// to update the label with the selected drone
		DroneTable.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String selectedDrone = DroneTable.getSelectedValue();
					// 20
					String droneId = String.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getId());

					// set text for Drone Type

					String droneSerialnumber = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getSerialnumber());
					labelId.setText("Drone ID: " + droneId);

					labelSerialnumber.setText("Serialnumber: " + droneSerialnumber);

					labelCarriageWeight.setText("Drone Carriage Weight: "
							+ String.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getCarriageWeight()));

					labelCarriageType.setText("Carriage Type: " + listOfDrones.get(DroneTable.getSelectedIndex())
							.getCarriageType().toString());

					labelCreated.setText("Created: "
							+ String.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getCreated()));

					// set text for Drone Type

					labelManufacturer.setText("Manufacturer: "
							+ String.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType()
									.getManufacturer()));

					labelTypeId.setText("Type ID: " + String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeId()));

					labelTypeName.setText("Type Name: " + String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()));

					labelWeight.setText("Weight: " + String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getWeight()));

					labelMaxSpeed.setText("Max Speed: " + String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getMax_speed()));

					labelBatteryCapacity.setText("Battery Capacity: " + String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getBatterycapacity()));

					labelControlRange.setText("Control Range: " + String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getControlRange()));

					labelMaxCarriage.setText("Max Carriage: " + String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getMaxCarriage()));

					// set text for Drone Dynamics
					// size

					labelDDTimeStamp.setText("Timestamp: " + String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getTimestamp()));

					labelDDSpeed.setText("Speed: " + String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getSpeed()));

					labelDDAlightRoll.setText("Alight Roll: " + String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getAlignRoll()));

					labelDDAlightPitch.setText("Alight Pitch: " + String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getAlignPitch()));

					labelDDAlightYaw.setText("Alight Yaw: " + String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getAlignYaw()));

					labelDDLongitute.setText("Longitute: " + String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLongitude()));

					labelDDLatitude.setText("Latitude: " + String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLatitude()));

					labelDDBatteryStatus.setText("Battery Status: " + String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getBatteryStatus()));

					labelDDLastSeen.setText("Last Seen: " + String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLastSeen()));

					labelDDStatus.setText("Status: " + String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getStatus()));

					// System.out.println(listOfDrones.get(DroneTable.getSelectedIndex()).getId());
				}
			}
		});
		// current time as last update in dd/mm/yyyy hh:mm:ss format
		labelLastUpdate.setText("Last Update: " + java.time.LocalDateTime.now().toString());

		JScrollPane DroneTableScroller = new JScrollPane(DroneTable);

		DroneTableScroller.setPreferredSize(new Dimension(250, 300)); // Set your desired width and height

		panel.setLayout(new GridLayout(0, 3)); // Create a grid with 3 columns

		panel.add(DroneTableScroller);
		// // panel.add(refreshButton);
		// // Lable Infos for drone List
		// panel.add(labelId);
		// panel.add(labelSerialnumber);
		// panel.add(labelCarriageWeight);
		// panel.add(labelCarriageType);
		// panel.add(labelCreated);
		// // Lable Infos for drone Type
		// panel.add(labelManufacturer);
		// panel.add(labelTypeId);
		// panel.add(labelTypeName);
		// panel.add(labelWeight);
		// panel.add(labelMaxSpeed);
		// panel.add(labelBatteryCapacity);
		// panel.add(labelControlRange);
		// panel.add(labelMaxCarriage);
		// // Lable Infos for drone Dynamics
		// panel.add(labelDDTimeStamp);
		// panel.add(labelDDSpeed);
		// panel.add(labelDDAlightRoll);
		// panel.add(labelDDAlightPitch);
		// panel.add(labelDDAlightYaw);
		// panel.add(labelDDLongitute);
		// panel.add(labelDDLatitude);
		// panel.add(labelDDBatteryStatus);
		// panel.add(labelDDLastSeen);
		// panel.add(labelDDStatus);

		// Create a new panel for the drone list labels
		JPanel droneListPanel = new JPanel(new GridLayout(0, 1)); // 1 column, as many rows as needed
		droneListPanel.add(labelDroneListLable);
		droneListPanel.add(labelId);
		droneListPanel.add(labelSerialnumber);
		droneListPanel.add(labelCarriageWeight);
		droneListPanel.add(labelCarriageType);
		droneListPanel.add(labelCreated);

		// Create a new panel for the drone type labels
		JPanel droneTypePanel = new JPanel(new GridLayout(0, 1)); // 1 column, as many rows as needed
		droneTypePanel.add(labelDroneTypeLable);
		droneTypePanel.add(labelManufacturer);
		droneTypePanel.add(labelTypeId);
		droneTypePanel.add(labelTypeName);
		droneTypePanel.add(labelWeight);
		droneTypePanel.add(labelMaxSpeed);
		droneTypePanel.add(labelBatteryCapacity);
		droneTypePanel.add(labelControlRange);
		droneTypePanel.add(labelMaxCarriage);

		// Create a new panel for the drone dynamics labels
		JPanel droneDynamicsPanel = new JPanel(new GridLayout(0, 1)); // 1 column, as many rows as needed
		droneDynamicsPanel.add(labelDroneDynamicLable);
		droneDynamicsPanel.add(labelDDTimeStamp);
		droneDynamicsPanel.add(labelDDSpeed);
		droneDynamicsPanel.add(labelDDAlightRoll);
		droneDynamicsPanel.add(labelDDAlightPitch);
		droneDynamicsPanel.add(labelDDAlightYaw);
		droneDynamicsPanel.add(labelDDLongitute);
		droneDynamicsPanel.add(labelDDLatitude);
		droneDynamicsPanel.add(labelDDBatteryStatus);
		droneDynamicsPanel.add(labelDDLastSeen);
		droneDynamicsPanel.add(labelDDStatus);

		JPanel OptionPanel = new JPanel(new GridLayout(4, 3)); // 1 column, as many rows as needed
		OptionPanel.add(refreshButton);
		OptionPanel.add(aboutUsButton);
		OptionPanel.add(labelLastUpdate);
		JPanel OptionPanel2 = new JPanel(new GridLayout(4, 3)); // 1 column, as many rows as needed
		OptionPanel2.add(refreshButton);
		OptionPanel2.add(aboutUsButton);
		OptionPanel2.add(labelLastUpdate);
		OptionPanel.add(OptionPanel2);

		// Add the panels to the main panel
		panel.add(droneListPanel);
		panel.add(droneTypePanel);
		panel.add(droneDynamicsPanel);
		panel.add(OptionPanel);

		add(panel);
	}

}
