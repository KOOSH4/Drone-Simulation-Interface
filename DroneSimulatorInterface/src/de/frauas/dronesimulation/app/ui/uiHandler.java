package de.frauas.dronesimulation.app.ui;

//
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import java.time.LocalTime;
import java.util.List;
import javax.swing.JButton;
//
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.JOptionPane;
//
import java.awt.Font;
import java.awt.Dimension;
//
import de.frauas.dronesimulation.app.apiconnection.ApiHandler;
import de.frauas.dronesimulation.app.dronedynamics.DroneDynamics;
//
import de.frauas.dronesimulation.app.dronelist.DroneList;
import de.frauas.dronesimulation.app.dronetype.DroneType;

public class uiHandler extends JFrame {

	public uiHandler(List<DroneList> listOfDrones, List<DroneType> listOfDroneTypes, ApiHandler droneApiHandler,
			int minutesBefore, List<DroneDynamics> listOfDronesDynamicTimeStamp) {
		setTitle("Drone Simulation Interface");
		setSize(1024, 768);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JPanel panel = new JPanel();

		// labels for Option Panel
		JLabel labelDroneOptionLable = new JLabel();

		JButton refreshButton = new JButton("Refresh");
		JButton aboutUsButton = new JButton("About Us");
		JLabel labelLastUpdate = new JLabel();
		JLabel labelDrones = new JLabel();
		JLabel labelDroneTypes = new JLabel();
		JLabel labelOnlineDrones = new JLabel();

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
		JLabel labelDDBatteryPercentage = new JLabel();

		// set Icons for drone lables

		String iconPath = "DroneSimulatorInterface/Resource/icons/";
		ImageIcon iconDrone = new ImageIcon(
				iconPath + "droneGallery/drone.png");
		JLabel labelDroneIcon = new JLabel(iconDrone);
		ImageIcon iconBattery0 = new ImageIcon(
				iconPath + "battery0.png");
		ImageIcon iconBattery25 = new ImageIcon(
				iconPath + "battery25.png");
		ImageIcon iconBattery50 = new ImageIcon(
				iconPath + "battery50.png");
		ImageIcon iconBattery75 = new ImageIcon(
				iconPath + "battery75.png");
		ImageIcon iconBattery100 = new ImageIcon(
				iconPath + "battery100.png");
		JLabel labelDroneBatteryIcon = new JLabel(iconBattery100);
		ImageIcon iconSwitchOff = new ImageIcon(iconPath +
				"switch-off.png");
		ImageIcon iconSwitchOn = new ImageIcon(
				iconPath + "switch-on.png");
		JLabel labelDroneIconCarriageType = new JLabel(iconDrone);
		ImageIcon iconDroneSenAct = new ImageIcon(
				iconPath + "droneGallery/droneACT.png");
		ImageIcon iconDroneSenNot = new ImageIcon(
				iconPath + "droneGallery/droneNOT.png");
		ImageIcon iconDroneSenSen = new ImageIcon(
				iconPath + "droneGallery/droneSEN.png");

		JLabel labelDroneSwitchOn = new JLabel(iconSwitchOn);
		ImageIcon iconEmpty1 = new ImageIcon(
				iconPath + "empty.png");
		JLabel labelDroneEmpty1 = new JLabel(iconEmpty1);
		JLabel labelDroneEmpty2 = new JLabel(iconEmpty1);
		JLabel labelDroneEmpty3 = new JLabel(iconEmpty1);

		// Drone Gallery Icons

		ImageIcon iconDroneGalleryAA108 = new ImageIcon(
				iconPath + "droneGallery/AA108.png");
		ImageIcon iconDroneGalleryChromaCameraDrone = new ImageIcon(
				iconPath + "droneGallery/Chroma Camera Drone.png");
		ImageIcon iconDroneGalleryD80 = new ImageIcon(
				iconPath + "droneGallery/D80.png");
		ImageIcon iconDroneGalleryEvoII = new ImageIcon(
				iconPath + "droneGallery/Evo II.png");
		ImageIcon iconDroneGalleryF24Pro = new ImageIcon(
				iconPath + "droneGallery/F24 Pro.png");
		ImageIcon iconDroneGalleryHS100 = new ImageIcon(
				iconPath + "droneGallery/HS100.png");
		ImageIcon iconDroneGalleryKarma = new ImageIcon(
				iconPath + "droneGallery/Karma.png");
		ImageIcon iconDroneGalleryPowerEggX = new ImageIcon(
				iconPath + "droneGallery/PowerEgg X.png");
		ImageIcon iconDroneGalleryS5C = new ImageIcon(
				iconPath + "droneGallery/S5C.png");
		ImageIcon iconDroneGallerySkydio2 = new ImageIcon(
				iconPath + "droneGallery/Skydio 2.png");
		ImageIcon iconDroneGalleryTello = new ImageIcon(
				iconPath + "droneGallery/Tello.png");
		ImageIcon iconDroneGalleryTyphoonHPro = new ImageIcon(
				iconPath + "droneGallery/Typhoon H Pro.png");
		ImageIcon iconDroneGalleryVoyager4 = new ImageIcon(
				iconPath + "droneGallery/Voyager 4.png");
		ImageIcon iconDroneGalleryX4H107D = new ImageIcon(
				iconPath + "droneGallery/X4 H107D.png");
		ImageIcon iconDroneGalleryX5C = new ImageIcon(
				iconPath + "droneGallery/X5C.png");
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

					if (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics().getStatus().equals("ON")) {
						labelDroneSwitchOn.setIcon(iconSwitchOn);
					} else {
						labelDroneSwitchOn.setIcon(iconSwitchOff);
					}

					int batteryPercentage = (listOfDrones.get(DroneTable.getSelectedIndex()).getDroneDynamics()
							.getBatteryStatus() * 100)
							/ listOfDrones.get(DroneTable.getSelectedIndex()).getDroneType().getBatterycapacity();

					labelDDBatteryPercentage.setText("Battery Percentage: " + batteryPercentage + "%");
					if (batteryPercentage >= 0 && batteryPercentage <= 25) {
						labelDroneBatteryIcon.setIcon(iconBattery0);
					} else if (batteryPercentage > 25 && batteryPercentage <= 50) {
						labelDroneBatteryIcon.setIcon(iconBattery25);
					} else if (batteryPercentage > 50 && batteryPercentage <= 75) {
						labelDroneBatteryIcon.setIcon(iconBattery50);
					} else if (batteryPercentage > 75 && batteryPercentage <= 100) {
						labelDroneBatteryIcon.setIcon(iconBattery75);
					} else if (batteryPercentage > 100) {
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
		// shows Current Time in a label
		labelOnlineDrones.setText("Online Drones: " + OnlineDrones);
		JScrollPane DroneTableScroller = new JScrollPane(DroneTable);
		DroneTableScroller.setBorder(new TitledBorder("Drone List"));
		// to open a dialog box with the about us information
		aboutUsButton.addActionListener(e -> JOptionPane.showMessageDialog(null,
				"Drone Simulation Interface" + "\n" + "Version 1.0" + "\n" + "Developed by:" + "\n"
						+ "Koosha and Tara" + "\n" + ""));

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

}
