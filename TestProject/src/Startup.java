  import java.awt.Color;
import java.awt.Graphics;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;
import javax.swing.text.NumberFormatter;

import java.math.*;
import java.net.URL;
import java.text.NumberFormat;

public class Startup extends JPanel {
    private static final long serialVersionUID = 1L;

    // create an array of strings to populate our dropdown list
    public static String[] droneMovementOptions = {"Select Movement Type", "Random", "Grid"};
    // create a variable to hold the selected option
    public static String droneSelectedOption = "";
    
    //paintComponent paints all the drones in the array list "drones"	  
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }

    //main
    public static void main(String[] args) {
        //make the startup frame
        JFrame startup = new JFrame();
        startup.setSize(1514, 838);//sets the JFrame's size to 1514x838 for 15mix8mi plus some for edges and app header
        startup.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Startup startupFrame = new Startup();
        startupFrame.setLayout(null);//sets the JFrame to have no layout manager (automatically moves widgets for some reason

        //create the combo box for movement selection
        JComboBox<String> movementDropdownBox = new JComboBox<String>(droneMovementOptions);
        movementDropdownBox.setBounds((1514/2)-125/2, (838/2), 125, 30);
        startupFrame.add(movementDropdownBox);
        // create an ActionListener to update the variable when the combo box selection changes
        movementDropdownBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
            	droneSelectedOption = (String) movementDropdownBox.getSelectedItem();
//                System.out.println("Selected option: " + selectedOption[0]);
            }
        });
        
        //create the Y Input for the starting position of the drones
        NumberFormat screenYFormat = NumberFormat.getIntegerInstance();
        NumberFormatter screenYFormatter = new NumberFormatter(screenYFormat);
        screenYFormatter.setMinimum(0);
        screenYFormatter.setMaximum(DroneFleet.screenY);
        JFormattedTextField startPosDronesYInput = new JFormattedTextField(screenYFormatter);
        startPosDronesYInput.setValue(0);
        startPosDronesYInput.setBounds(100,130,230,30);
        startupFrame.add(startPosDronesYInput);
//        startPosDronesYInput.addFocusListener(new FocusAdapter() {
//            @Override
//            public void focusLost(FocusEvent e) {
//               // Get the current value of the text field
//               Object valueY = startPosDronesYInput.getValue();
//
//               // Check if the value is within the bounds
//               if (valueY instanceof Integer) {
//                  int intValue = (Integer) valueY;
//                  if (intValue < 0 || intValue > DroneFleet.screenY) {
//                     // Value is outside the bounds, display error message and return focus to text field
//                     JOptionPane.showMessageDialog(startupFrame, "Value must be between " + 0 + " and " + DroneFleet.screenY);
//                     startPosDronesYInput.requestFocus();
//                  }else {
//                	  DroneFleet.startingy = (int) startPosDronesYInput.getValue();
//                  }
//                	  
//               }
//            }
//         });
        
        JLabel YInputText = new JLabel("Input Drone Starting Y Position (0 - "+ DroneFleet.screenY + ") ");
        YInputText.setBounds(100,100,300,30);
        startupFrame.add(YInputText);
        
        //create the X Input for the starting position of the drones
        NumberFormat screenXFormat = NumberFormat.getIntegerInstance();
        NumberFormatter screenXFormatter = new NumberFormatter(screenXFormat);
        screenXFormatter.setMinimum(0);
        screenXFormatter.setMaximum(DroneFleet.screenX);
        JFormattedTextField startPosDronesXInput = new JFormattedTextField(screenXFormatter);
        startPosDronesXInput.setValue(0);
        startPosDronesXInput.setBounds(100,230,230,30);
        startupFrame.add(startPosDronesXInput);

        JLabel XInputText = new JLabel("Input Drone Starting X Position (0 - "+ DroneFleet.screenX + ") ");
        XInputText.setBounds(100,200,300,30);
        startupFrame.add(XInputText);
        
        //create/add the button to start the simulation and switch to that screen
        JButton startButton = new JButton("Start Simulation");
        startButton.setBounds((1514/2)-125/2, 25, 125, 30);
        startupFrame.add(startButton);
        startButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                DroneFleet.currentFrame = 1;
                System.out.println("start button pressed: " + DroneFleet.currentFrame);
                startup.setVisible(false);
                DroneFleet.main(args);
            }
        });
        startButton.addActionListener(new ActionListener() {
     	   @Override
     	   public void actionPerformed(ActionEvent e) {
     	      // Get the current value of the text field
     	      Object valueX = startPosDronesXInput.getValue();
     	      // Check if the value is within the bounds
     	      if (valueX instanceof Integer) {
     	         int intXValue = (Integer) valueX;
     	         if (intXValue < 0 || intXValue > DroneFleet.screenX) {
     	            // Value is outside the bounds, display error message
     	            JOptionPane.showMessageDialog(startupFrame, "Value must be between " + 0 + " and " + DroneFleet.screenX);
     	         } else {
     	            DroneFleet.startingx = intXValue;
     	         }
     	      }
     	   }
     	});
        startButton.addActionListener(new ActionListener() {
      	   @Override
      	   public void actionPerformed(ActionEvent e) {
      	      // Get the current value of the text field
      	      Object valueY = startPosDronesYInput.getValue();
      	      // Check if the value is within the bounds
      	      if (valueY instanceof Integer) {
      	         int intYValue = (Integer) valueY;
      	         if (intYValue < 0 || intYValue > DroneFleet.screenY) {
      	            // Value is outside the bounds, display error message
      	            JOptionPane.showMessageDialog(startupFrame, "Value must be between " + 0 + " and " + DroneFleet.screenY);
      	         } else {
      	            DroneFleet.startingy = intYValue;
      	         }
      	      }
      	   }
      	});

        startup.add(startupFrame);
        if (DroneFleet.currentFrame == 0) {
            startup.setVisible(true);
        }
    }
}

