package de.frauas.dronesimulation.app.ui;

import java.time.LocalTime;

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
import de.frauas.dronesimulation.app.dronetype.DroneType;

public class uiHandler extends JFrame {

	public uiHandler(List<DroneList> listOfDrones, List<DroneType> listOfDroneTypes) {
		setTitle("Drone Simulation Interface");
		setSize(1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		// labels for Option Panel
		JButton refreshButton = new JButton("Refresh");
		JButton aboutUsButton = new JButton("About Us");
		JLabel labelLastUpdate = new JLabel();
		JLabel labelDrones = new JLabel();
		JLabel labelDroneTypes = new JLabel();
		JLabel labelOnlineDrones = new JLabel();
		JLabel labelCurrentTime = new JLabel();

		// labels for drone list Panel

		JLabel labelDroneListLable = new JLabel();
		JLabel labelId = new JLabel();
		JLabel labelSerialnumber = new JLabel();
		JLabel labelCarriageWeight = new JLabel();
		JLabel labelCarriageType = new JLabel();
		JLabel labelCreated = new JLabel();
		// labels for drone type panel
		JLabel labelDroneTypeLable = new JLabel();
		JLabel labelManufacturer = new JLabel();
		JLabel labelTypeId = new JLabel();
		JLabel labelTypeName = new JLabel();
		JLabel labelWeight = new JLabel();
		JLabel labelMaxSpeed = new JLabel();
		JLabel labelBatteryCapacity = new JLabel();
		JLabel labelControlRange = new JLabel();
		JLabel labelMaxCarriage = new JLabel();
		// labels for drone dynamics Panel
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
					labelId.setText("Drone ID: " + droneId);

					// set text for Drone Type

					String droneSerialnumber = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getSerialnumber());
					labelSerialnumber.setText("Serialnumber: " + droneSerialnumber);

					int selectedIndex = DroneTable.getSelectedIndex();

					String droneCarriageWeight = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getCarriageWeight());
					labelCarriageWeight.setText("Drone Carriage Weight: " + droneCarriageWeight);

					String droneCarriageType = listOfDrones.get(DroneTable.getSelectedIndex()).getCarriageType();
					labelCarriageType.setText("Carriage Type: " + droneCarriageType);

					String droneCreated = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getCreated());
					labelCreated.setText("Created: " + droneCreated);

					// set text for Drone Type

					String droneManufacturer = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getManufacturer());
					labelManufacturer.setText("Manufacturer: " + droneManufacturer);

					String droneTypeId = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeId());
					labelTypeId.setText("Type ID: " + droneTypeId);

					String droneTypeName = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName());
					labelTypeName.setText("Type Name: " + droneTypeName);

					String droneWeight = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getWeight());
					labelWeight.setText("Weight: " + droneWeight);

					String droneMaxSpeed = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getMax_speed());
					labelMaxSpeed.setText("Max Speed: " + droneMaxSpeed);

					String droneBatteryCapacity = String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getBatterycapacity());
					labelBatteryCapacity.setText("Battery Capacity: " + droneBatteryCapacity);

					String droneControlRange = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getControlRange());
					labelControlRange.setText("Control Range: " + droneControlRange);

					String droneMaxCarriage = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getMaxCarriage());
					labelMaxCarriage.setText("Max Carriage: " + droneMaxCarriage);

					// set text for Drone Dynamics

					String droneTimestamp = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getTimestamp());
					labelDDTimeStamp.setText("Timestamp: " + droneTimestamp);

					String droneSpeed = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getSpeed());
					labelDDSpeed.setText("Speed: " + droneSpeed);

					String droneAlignRoll = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getAlignRoll());
					labelDDAlightRoll.setText("Alight Roll: " + droneAlignRoll);

					String droneAlignPitch = String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getAlignPitch());
					labelDDAlightPitch.setText("Alight Pitch: " + droneAlignPitch);

					String droneAlignYaw = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getAlignYaw());
					labelDDAlightYaw.setText("Alight Yaw: " + droneAlignYaw);

					String droneLongitude = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLongitude());
					labelDDLongitute.setText("Longitute: " + droneLongitude);

					String droneLatitude = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLatitude());
					labelDDLatitude.setText("Latitude: " + droneLatitude);

					String droneBatteryStatus = String.valueOf(
							listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getBatteryStatus());
					labelDDBatteryStatus.setText("Battery Status: " + droneBatteryStatus);

					String droneLastSeen = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLastSeen());
					labelDDLastSeen.setText("Last Seen: " + droneLastSeen);

					String droneStatus = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getStatus());
					labelDDStatus.setText("Status: " + droneStatus);
					// System.out.println(listOfDrones.get(DroneTable.getSelectedIndex()).getId());
				}
			}
		});
		// current time as last update in dd/mm/yyyy hh:mm:ss format
		labelLastUpdate.setText("Last Update: " + java.time.LocalDateTime.now().toString());
		labelDroneTypes.setText("Total Drone Types: " + listOfDroneTypes.size());
		labelDrones.setText("Total Drones: " + listOfDrones.size());
		int OnlineDrones = 0;
		for (DroneList drone : listOfDrones) {
			if (drone.getDroneDynamics().getStatus().equals("ON")) {
				labelOnlineDrones.setText("Online Drones: " + drone.getDroneDynamics().getStatus());
				OnlineDrones++;
			}
		}

		labelOnlineDrones.setText("Online Drones: " + OnlineDrones);
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
		OptionPanel.add(labelDrones);
		OptionPanel.add(labelDroneTypes);
		OptionPanel.add(labelOnlineDrones);

		OptionPanel.add(labelLastUpdate);

		OptionPanel.add(refreshButton);
		OptionPanel.add(aboutUsButton);

		// Add the panels to the main panel
		panel.add(droneListPanel);
		panel.add(droneTypePanel);
		panel.add(droneDynamicsPanel);
		panel.add(OptionPanel);

		add(panel);
	}

	public static void updateCurrentTime(String[] args) {
		while (true) {
			// Get the current time
			LocalTime currentTime = LocalTime.now();

			// Display the current time
			System.out.println("Current Time: " + currentTime);

			try {
				// Pause the loop for one second
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}

		}
	}

}
