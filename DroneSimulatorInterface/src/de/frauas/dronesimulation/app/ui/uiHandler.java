package de.frauas.dronesimulation.app.ui;

//
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JComboBox;
//
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;
//
import java.awt.Dimension;
//
import de.frauas.dronesimulation.app.apiconnection.ApiHandler;
import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
//
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronetype.DroneType;
import de.frauas.dronesimulation.app.main.Helper;
import de.frauas.dronesimulation.app.main.Main;

public class uiHandler extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public uiHandler(List<DroneList> listOfDrones, List<DroneType> listOfDroneTypes, ApiHandler droneApiHandler,
			int minutesBefore, List<DroneDynamics> listOfDronesDynamicTimeStamp) {
		setTitle("Drone Simulation Interface");
		setSize(1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		JButton refreshButton = new JButton("Refresh");
		JButton aboutUsButton = new JButton("About Us");

		JLabel labelLastUpdate = new JLabel();
		JLabel labelDrones = new JLabel();
		JLabel labelDroneTypes = new JLabel();
		JLabel labelOnlineDrones = new JLabel();

		// labels for drone list Panel

		JLabel labelId = new JLabel();
		JLabel labelSerialnumber = new JLabel();
		JLabel labelCarriageWeight = new JLabel();
		JLabel labelCarriageType = new JLabel();
		JLabel labelCreated = new JLabel();
		// labels for drone type panel
		JLabel labelManufacturer = new JLabel();
		JLabel labelTypeId = new JLabel();
		JLabel labelTypeName = new JLabel();
		JLabel labelWeight = new JLabel();
		JLabel labelMaxSpeed = new JLabel();
		JLabel labelBatteryCapacity = new JLabel();
		JLabel labelControlRange = new JLabel();
		JLabel labelMaxCarriage = new JLabel();
		// labels for drone dynamics Panel
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
		JLabel labelDDBatteryPercentage = new JLabel();

		// set Icons for drone lables

		// String iconPath = "./icons/";
		String iconPath = "./DroneSimulatorInterface/src/resources/icons/";

		// Drone Icons
		ImageIcon iconDrone = new ImageIcon(iconPath + "droneGallery/drone.png");
		ImageIcon iconDroneSenAct = new ImageIcon(getClass().getResource("/icons/droneGallery/droneACT.png"));
		ImageIcon iconDroneSenNot = new ImageIcon(getClass().getResource("/icons/droneGallery/droneNOT.png"));
		ImageIcon iconDroneSenSen = new ImageIcon(getClass().getResource("/icons/droneGallery/droneSEN.png"));

		// Battery Icons
		ImageIcon iconBattery0 = new ImageIcon(getClass().getResource("/icons/battery0.png"));
		ImageIcon iconBattery25 = new ImageIcon(getClass().getResource("/icons/battery25.png"));
		ImageIcon iconBattery50 = new ImageIcon(getClass().getResource("/icons/battery50.png"));
		ImageIcon iconBattery75 = new ImageIcon(getClass().getResource("/icons/battery75.png"));
		ImageIcon iconBattery100 = new ImageIcon(getClass().getResource("/icons/battery100.png"));

		// Switch Icons
		ImageIcon iconSwitchOff = new ImageIcon(getClass().getResource("/icons/switch-off.png"));
		ImageIcon iconSwitchOn = new ImageIcon(getClass().getResource("/icons/switch-on.png"));

		// Empty Icon
		ImageIcon iconEmpty1 = new ImageIcon(getClass().getResource("/icons/empty.png"));

		// Drone Gallery Icons
		ImageIcon iconDroneGalleryAA108 = new ImageIcon(getClass().getResource("/icons/droneGallery/AA108.png"));
		ImageIcon iconDroneGalleryChromaCameraDrone = new ImageIcon(
				getClass().getResource("/icons/droneGallery/Chroma Camera Drone.png"));
		ImageIcon iconDroneGalleryD80 = new ImageIcon(getClass().getResource("/icons/droneGallery/D80.png"));
		ImageIcon iconDroneGalleryEvoII = new ImageIcon(getClass().getResource("/icons/droneGallery/Evo II.png"));
		ImageIcon iconDroneGalleryF24Pro = new ImageIcon(getClass().getResource("/icons/droneGallery/F24 Pro.png"));
		ImageIcon iconDroneGalleryHS100 = new ImageIcon(getClass().getResource("/icons/droneGallery/HS100.png"));
		ImageIcon iconDroneGalleryKarma = new ImageIcon(getClass().getResource("/icons/droneGallery/Karma.png"));
		ImageIcon iconDroneGalleryPowerEggX = new ImageIcon(
				getClass().getResource("/icons/droneGallery/PowerEgg X.png"));
		ImageIcon iconDroneGalleryS5C = new ImageIcon(getClass().getResource("/icons/droneGallery/S5C.png"));
		ImageIcon iconDroneGallerySkydio2 = new ImageIcon(getClass().getResource("/icons/droneGallery/Skydio 2.png"));
		ImageIcon iconDroneGalleryTello = new ImageIcon(getClass().getResource("/icons/droneGallery/Tello.png"));
		ImageIcon iconDroneGalleryTyphoonHPro = new ImageIcon(
				getClass().getResource("/icons/droneGallery/Typhoon H Pro.png"));
		ImageIcon iconDroneGalleryVoyager4 = new ImageIcon(getClass().getResource("/icons/droneGallery/Voyager 4.png"));
		ImageIcon iconDroneGalleryX4H107D = new ImageIcon(getClass().getResource("/icons/droneGallery/X4 H107D.png"));
		ImageIcon iconDroneGalleryX5C = new ImageIcon(getClass().getResource("/icons/droneGallery/X5C.png"));

		// Labels
		JLabel labelDroneIcon = new JLabel(iconDrone);
		JLabel labelDroneBatteryIcon = new JLabel(iconBattery100);
		JLabel labelDroneIconCarriageType = new JLabel(iconDrone);
		JLabel labelDroneSwitchOn = new JLabel(iconSwitchOn);
		JLabel labelDroneEmpty1 = new JLabel(iconEmpty1);
		JLabel labelDroneEmpty2 = new JLabel(iconEmpty1);
		JLabel labelDroneEmpty3 = new JLabel(iconEmpty1);

		// Drones Array
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

		// to update the label with the selected drone
		DroneTable.addListSelectionListener(new ListSelectionListener() {
			@Override
			public void valueChanged(ListSelectionEvent e) {
				if (!e.getValueIsAdjusting()) {
					String droneId = String.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getId());
					labelId.setText("Drone ID: " + droneId);

					// set text for Drone Type

					String droneSerialnumber = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getSerialnumber());
					labelSerialnumber.setText("Serialnumber: " + droneSerialnumber);

					String droneCarriageWeight = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getCarriageWeight());
					labelCarriageWeight.setText("Drone Carriage Weight: " + droneCarriageWeight);

					String droneCarriageType = listOfDrones.get(DroneTable.getSelectedIndex()).getCarriageType();
					labelCarriageType.setText("Carriage Type: " + droneCarriageType);

					String droneCreated = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getCreated().getDayOfMonth()
									+ "/"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getCreated().getMonthValue()
									+ "/"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getCreated().getYear()
									+ " "
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getCreated().getHour()
									+ ":"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getCreated().getMinute()
									+ ":"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getCreated().getSecond());
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
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getTimestamp()
									.getDayOfMonth()
									+ "/"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getTimestamp()
											.getMonthValue()
									+ "/"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getTimestamp()
											.getYear()
									+ " "
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getTimestamp()
											.getHour()
									+ ":"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getTimestamp()
											.getMinute()
									+ ":"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getTimestamp()
											.getSecond());
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
							.valueOf(listOfDrones
									.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLastSeen().getDayOfMonth()
									+ "/"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLastSeen()
											.getMonthValue()
									+ "/"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLastSeen()
											.getYear()
									+ " "
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLastSeen()
											.getHour()
									+ ":"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLastSeen()
											.getMinute()
									+ ":"
									+ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getLastSeen()
											.getSecond());
					labelDDLastSeen.setText("Last Seen: " + droneLastSeen);

					String droneStatus = String
							.valueOf(listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getStatus());
					labelDDStatus.setText("Status: " + droneStatus);

					if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getStatus().equals("ON")) {
						labelDroneSwitchOn.setIcon(iconSwitchOn);
					} else {
						labelDroneSwitchOn.setIcon(iconSwitchOff);
					}

					int batteryPercentage = (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics()
							.getBatteryStatus() * 100)
							/ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getBatterycapacity();

					labelDDBatteryPercentage.setText("Battery Percentage: " + batteryPercentage + "%");
					if (batteryPercentage == 0) {
						labelDroneBatteryIcon.setIcon(iconBattery0);
					} else if (batteryPercentage > 0 && batteryPercentage <= 35) {
						labelDroneBatteryIcon.setIcon(iconBattery25);
					} else if (batteryPercentage > 35 && batteryPercentage <= 60) {
						labelDroneBatteryIcon.setIcon(iconBattery50);
					} else if (batteryPercentage > 60 && batteryPercentage <= 99) {
						labelDroneBatteryIcon.setIcon(iconBattery75);
					} else if (batteryPercentage == 100) {
						labelDroneBatteryIcon.setIcon(iconBattery100);
					}

					if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("AA108")) {
						labelDroneIcon.setIcon(iconDroneGalleryAA108);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("Chroma Camera Drone")) {
						labelDroneIcon.setIcon(iconDroneGalleryChromaCameraDrone);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("D80")) {
						labelDroneIcon.setIcon(iconDroneGalleryD80);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("Evo II")) {
						labelDroneIcon.setIcon(iconDroneGalleryEvoII);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("F24 Pro")) {
						labelDroneIcon.setIcon(iconDroneGalleryF24Pro);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("HS100")) {
						labelDroneIcon.setIcon(iconDroneGalleryHS100);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("Karma")) {
						labelDroneIcon.setIcon(iconDroneGalleryKarma);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("PowerEgg X")) {
						labelDroneIcon.setIcon(iconDroneGalleryPowerEggX);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("S5C")) {
						labelDroneIcon.setIcon(iconDroneGalleryS5C);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("Skydio 2")) {
						labelDroneIcon.setIcon(iconDroneGallerySkydio2);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("Tello")) {
						labelDroneIcon.setIcon(iconDroneGalleryTello);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("Typhoon H Pro")) {
						labelDroneIcon.setIcon(iconDroneGalleryTyphoonHPro);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("Voyager 4")) {
						labelDroneIcon.setIcon(iconDroneGalleryVoyager4);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("X4 H107D")) {
						labelDroneIcon.setIcon(iconDroneGalleryX4H107D);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getTypeName()
							.equals("X5C")) {
						labelDroneIcon.setIcon(iconDroneGalleryX5C);
					}

					if (listOfDrones.get(DroneTable.getSelectedIndex()).getCarriageType().equals("NOT")) {
						labelDroneIconCarriageType.setIcon(iconDroneSenNot);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getCarriageType().equals("ACT")) {
						labelDroneIconCarriageType.setIcon(iconDroneSenAct);
					} else if (listOfDrones.get(DroneTable.getSelectedIndex()).getCarriageType().equals("SEN")) {
						labelDroneIconCarriageType.setIcon(iconDroneSenSen);
					}
					// System.out.println(labelDroneIcon.getIcon().toString());

					// System.out.println(listOfDrones.get(DroneTable.getSelectedIndex()).getId());
				}
			}
		});

		int hour = 00;
		int minute = 00;
		// Create a list to hold all time values
		List<String> times = new ArrayList<>();

		// Generate all possible time values
		for (int i = hour; i < 24; i++) {
			for (int j = minute; j < 60; j++) {
				times.add(String.format("%02d:%02d", i, j));

			}
			minute = 0;
		}

		// Create a JComboBox and add all time values to it
		JComboBox<String> timePicker = new JComboBox<>(times.toArray(new String[0]));

		List<String> dates1 = new ArrayList<>();

		// Iterate over the first two elements of the list
		for (DroneDynamics droneDynamics : listOfDronesDynamicTimeStamp) {
			// Get the timestamp
			LocalDateTime timestamp = droneDynamics.getTimestamp();

			// Format the date
			String date = String.format("%02d/%02d/%d",
					timestamp.getDayOfMonth(),
					timestamp.getMonthValue(),
					timestamp.getYear());

			dates1.add(date);

		}
		JComboBox<String> datePicker = new JComboBox<>(dates1.toArray(new String[0]));
		// Set the selected item to the current time
		timePicker.setSelectedItem(String.format("%02d:%02d", hour, minute));
		JPanel dateTimePanel = new JPanel();
		dateTimePanel.setBorder(new TitledBorder("Select Date and Time"));
		dateTimePanel.add(datePicker);
		dateTimePanel.add(timePicker);

		datePicker
				.addActionListener(e -> offsetSelectedDateTime(datePicker, timePicker, listOfDronesDynamicTimeStamp,
						droneApiHandler, listOfDrones, DroneTable));
		timePicker
				.addActionListener(e -> offsetSelectedDateTime(datePicker, timePicker, listOfDronesDynamicTimeStamp,
						droneApiHandler, listOfDrones, DroneTable));
		timePicker.setSelectedItem(
				String.format("%02d:%02d", listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour(),
						listOfDronesDynamicTimeStamp.get(0).getTimestamp().getMinute()));

		//////////////////////////
		// Add an action listener to the button
		refreshButton.addActionListener(
				e -> callRefreshData(droneApiHandler, listOfDrones, listOfDroneTypes, 12, listOfDronesDynamicTimeStamp,
						DroneTable));
		/////////////////////////////////////////////////////////////////////////////////////////
		// current time as last update in dd/mm/yyyy hh:mm:ss format
		labelLastUpdate.setText("Last Update: Today " + java.time.LocalDateTime.now().getHour() + ":"
				+ java.time.LocalDateTime.now().getMinute() + ":"
				+ java.time.LocalDateTime.now().getSecond());
		labelDroneTypes.setText("Total Drone Types: " + listOfDroneTypes.size());
		labelDrones.setText("Total Drones: " + listOfDrones.size());
		int OnlineDrones = 0;
		for (DroneList drone : listOfDrones) {
			if (drone.getDroneDynamics().getStatus().equals("ON")) {
				labelOnlineDrones.setText("Online Drones: " + drone.getDroneDynamics().getStatus());
				OnlineDrones++;
			}
		}
		// shows Current Time in a label
		labelOnlineDrones.setText("Online Drones: " + OnlineDrones);
		JScrollPane DroneTableScroller = new JScrollPane(DroneTable);
		DroneTableScroller.setBorder(new TitledBorder("Drone List"));
		// to open a dialog box with the about us information
		aboutUsButton.addActionListener(e -> JOptionPane.showMessageDialog(null,
				"Drone Simulation Interface" + "\n" + "Object Oriented Programming with Java" + "\n"
						+ "" + "\n" + "\n" + "Developed by:" + "\n"
						+ "Group 22" + "\n" + "\n" + "Group Members:" + "\n" + "KOOSH4" + "\n" + "Tata-kh" + ""
						+ "\n"));

		DroneTableScroller.setPreferredSize(new Dimension(250, 300)); // Set your desired width and height

		panel.setLayout(new GridLayout(0, 3)); // Create a grid with 3 columns
		DroneTable.setSelectedIndex(0);

		JPanel droneListPanel = new JPanel(new GridLayout(0, 1)); // 1 column, as many rows as needed
		droneListPanel.setBorder(new TitledBorder("Drone List infos"));

		droneListPanel.add(labelId);
		droneListPanel.add(labelSerialnumber);
		droneListPanel.add(labelCarriageWeight);
		droneListPanel.add(labelCarriageType);
		droneListPanel.add(labelCreated);

		// Create a new panel for the drone type labels
		JPanel droneTypePanel = new JPanel(new GridLayout(0, 1)); // 1 column, as many rows as needed
		droneTypePanel.setBorder(new TitledBorder("Drone Type infos"));

		droneTypePanel.add(labelTypeId);
		droneTypePanel.add(labelManufacturer);
		droneTypePanel.add(labelTypeName);
		droneTypePanel.add(labelMaxSpeed);
		droneTypePanel.add(labelMaxCarriage);
		droneTypePanel.add(labelWeight);
		droneTypePanel.add(labelBatteryCapacity);
		droneTypePanel.add(labelControlRange);

		// Create a new panel for the drone dynamics labels
		JPanel droneDynamicsPanel = new JPanel(new GridLayout(0, 1)); // 1 column, as many rows as needed
		droneDynamicsPanel.setBorder(new TitledBorder("Drone Dynamic infos"));

		droneDynamicsPanel.add(labelDDStatus);
		droneDynamicsPanel.add(labelDDBatteryPercentage);
		droneDynamicsPanel.add(labelDDSpeed);
		droneDynamicsPanel.add(labelDDLongitute);
		droneDynamicsPanel.add(labelDDLatitude);
		droneDynamicsPanel.add(labelDDAlightRoll);
		droneDynamicsPanel.add(labelDDAlightPitch);
		droneDynamicsPanel.add(labelDDAlightYaw);
		droneDynamicsPanel.add(labelDDBatteryStatus);
		droneDynamicsPanel.add(labelDDLastSeen);
		droneDynamicsPanel.add(labelDDTimeStamp);
		// a panel for the drone Option labels

		JPanel OptionPanel = new JPanel(new GridLayout(4, 3)); // 1 column, as many rows as needed
		OptionPanel.setBorder(new TitledBorder("Options"));

		panel.add(DroneTableScroller);

		OptionPanel.add(labelDrones);
		OptionPanel.add(labelDroneTypes);
		OptionPanel.add(labelOnlineDrones);

		OptionPanel.add(labelLastUpdate);

		OptionPanel.add(refreshButton);
		OptionPanel.add(aboutUsButton);
		OptionPanel.add(dateTimePanel);
		// a panel for the Visual labels

		JPanel VisualPanel = new JPanel(new GridLayout(4, 3)); // 1 column, as many rows as needed
		VisualPanel.setBorder(new TitledBorder("Visual Panel"));
		VisualPanel.add(labelDroneIcon);
		VisualPanel.add(labelDroneBatteryIcon);
		VisualPanel.add(labelDroneSwitchOn);
		VisualPanel.add(labelDroneIconCarriageType);
		VisualPanel.add(labelDroneEmpty1);
		VisualPanel.add(labelDroneEmpty2);
		VisualPanel.add(labelDroneEmpty3);

		// Add the panels to the main panel
		panel.add(DroneTableScroller);
		panel.add(OptionPanel);
		panel.add(VisualPanel);
		panel.add(droneListPanel);
		panel.add(droneTypePanel);
		panel.add(droneDynamicsPanel);

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

	public int offsetSelectedDateTime(JComboBox<String> datePicker, JComboBox<String> timePicker,
			List<DroneDynamics> listOfDronesDynamicTimeStamp, ApiHandler droneApiHandler,
			List<DroneList> listOfDrones, JList<String> DroneTable) {
		String selectedDate = (String) datePicker.getSelectedItem();
		String selectedTime = (String) timePicker.getSelectedItem();
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
		String dateTimeString = selectedDate + " " + selectedTime;
		LocalDateTime selecteDateTime = LocalDateTime.parse(dateTimeString, formatter);

		if (datePicker.getSelectedIndex() == 0) {
			if ((selecteDateTime.getHour() == listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour()
					&& selecteDateTime.getMinute() >= listOfDronesDynamicTimeStamp.get(0).getTimestamp().getMinute())
					|| selecteDateTime.getHour() > listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour()) {
				System.out.println("Available");
			} else {
				JOptionPane.showMessageDialog(null,
						"There is no Dynamic Data in this Timestamp" + "\n" + "Please choose another time" + "\n"
								+ "Dynamic data on selected day starts from:"
								+ listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour() + ":"
								+ listOfDronesDynamicTimeStamp.get(0).getTimestamp().getMinute() + "\n" + "");

				System.out.println("Not available yet");
			}
		}
		if (datePicker.getSelectedIndex() == 1) {
			System.out.println("Selected date Hour: " + selecteDateTime.getHour());
			System.out.println("Selected date Minute: " + selecteDateTime.getMinute());
			System.out.println("SelectLI date Hour: " + listOfDronesDynamicTimeStamp.get(1).getTimestamp().getHour());
			System.out
					.println("SelectLI date Minute: " + listOfDronesDynamicTimeStamp.get(1).getTimestamp().getMinute());

			if ((selecteDateTime.getHour() == listOfDronesDynamicTimeStamp.get(1).getTimestamp().getHour()
					&& selecteDateTime.getMinute() <= listOfDronesDynamicTimeStamp.get(1).getTimestamp().getMinute())
					|| selecteDateTime.getHour() < listOfDronesDynamicTimeStamp.get(1).getTimestamp().getHour()) {
				System.out.println("Available");

			} else {
				JOptionPane.showMessageDialog(null,
						"There is no Dynamic Data in this Timestamp" + "\n" + "Please choose another time" + "\n"
								+ "Dynamic data on selected day ends at: "
								+ listOfDronesDynamicTimeStamp.get(1).getTimestamp().getHour() + ":"
								+ listOfDronesDynamicTimeStamp.get(1).getTimestamp().getMinute() + "\n" + "");
				System.out.println("Not available yet");
			}
		}

		System.out.println("Selected date: " + selectedDate);
		System.out.println("Selected time: " + selectedTime);
		int hour = selecteDateTime.getHour();
		int minute = selecteDateTime.getMinute();

		// Calculate the total number of minutes
		int totalMinutes = 0;

		if (datePicker.getSelectedIndex() == 0) {
			totalMinutes = (hour - listOfDronesDynamicTimeStamp.get(0).getTimestamp().getHour()) * 60
					+ (minute - listOfDronesDynamicTimeStamp.get(0).getTimestamp().getMinute());
		} else {
			totalMinutes = (hour) * 60
					+ (minute) + 893;
		}

		System.out.println("Total minutes from 00:00: " + totalMinutes);
		if (0 <= totalMinutes && totalMinutes <= 1440) {
			Helper.getDroneDynamics(droneApiHandler, listOfDrones, totalMinutes, listOfDronesDynamicTimeStamp);
			DroneTable.setSelectedIndex(4);
			DroneTable.setSelectedIndex(0);
		}

		return totalMinutes;
	}

	public static void callRefreshData(ApiHandler droneApiHandler, List<DroneList> listOfDrones,
			List<DroneType> listOfDroneTypes, int minutesBefore, List<DroneDynamics> listOfDronesDynamicTimeStamp,
			JList<String> DroneTable) {

		Main.refreshData(droneApiHandler, listOfDrones, listOfDroneTypes,
				minutesBefore, listOfDronesDynamicTimeStamp);
		DroneTable.setSelectedIndex(4);
		DroneTable.setSelectedIndex(0);
	}

}
