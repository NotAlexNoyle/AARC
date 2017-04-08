package notalexnoyle;
//TODO: Add Icon to .jar?

// Import color library for text formatting
import java.awt.Color;
import java.awt.Font;
// Import action classes for button pushes
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// Import UI classes for window/elements
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

// Main class declaration
public class AARC {
	
	// Declare text field to allow user to enter display pixel width
	// Declared as static so that the value entered by the user can be accessed in the calculate button action listener
	static JTextField widthEntry = new JTextField(5);
	
	// Declare text field to allow user to enter display pixel height
	// Declared as static so that the value entered by the user can be accessed in the calculate button action listener
	static JTextField heightEntry = new JTextField(5);
	
	// Declare result display text field
	// Declared as static so that it can be accessed in action listener method
	static JTextField resultDisplay = new JTextField();
			
	// init main
	public static void main(String[] args) {
		
		// Declare panel to hold frame / set layout / set style
		JPanel panel = new JPanel();
		panel.setBackground(Color.DARK_GRAY);
		
		// Declare label for width text field
		JLabel widthLabel = new JLabel("Width: ");
		// Set label text color
		widthLabel.setForeground(Color.white);
		
		// Declare label for height text field
		JLabel heightLabel = new JLabel("Height: ");
		// Set label text color
		heightLabel.setForeground(Color.white);
				
		// Declare "calculate" button
		JButton calculateButton = new JButton("Calculate");
		// Removes border around button text for UI niceness
		calculateButton.setFocusable(false);
		// Calls button action method when pressed
		calculateButtonAction(calculateButton);
		
		// Logic to prevent crash upon startup when text field values are empty
		if(widthEntry.getText().equals("") && heightEntry.getText().equals("")) {
			resultDisplay.setText("Enter Values...");
			resultDisplay.setFont(new Font("Enter Values...", Font.ITALIC, 12));
		}
		
		// Formatting for resultDisplay text field to make it appear as a JLabel but still be selectable for copy/paste
		resultDisplay.setEditable(false);
		resultDisplay.setBorder(null);
		resultDisplay.setForeground(Color.white);
		resultDisplay.setBackground(Color.DARK_GRAY);
		
		// Add UI elements to panel
		panel.add(widthLabel);
		panel.add(widthEntry);
		panel.add(heightLabel);
		panel.add(heightEntry);
		panel.add(resultDisplay);
		panel.add(calculateButton);
				
		// Declare frame
		JFrame frame = new JFrame();
		// Add panel to frame
		frame.add(panel);
		// Set window attributes
		frame.setTitle("     AARC");
		frame.setSize(150, 150);
		frame.setLocation(200, 200);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
		// Set default button (allows "enter" keys to calculate)
		frame.getRootPane().setDefaultButton(calculateButton);
		
	}
	
	// Method to calculate aspect ratio
	public static String aspectRatioMath(int widthWorker, int heightWorker) {
		
		// Call GCF method with width/height values
		int calcPrimary = greatestCommonDivisor(widthWorker, heightWorker);
		// Divide width by GCF
		int calcSecondary = widthWorker / calcPrimary;
		// Divide height by GCF
		int calcTirtiary = heightWorker / calcPrimary;
		
		// Assemble calculations into formatted string (aspect ratio as displayed to user)
		String calcAssembly = String.valueOf(calcSecondary) + ":" + String.valueOf(calcTirtiary);
		// Return completed assembled string
		return calcAssembly;
		
	}
	
	// Method to determine GCF of width and height - generic
	public static int greatestCommonDivisor(int valueX, int valueY) {
		
		// Euclidean Algorithm --> credit to Oleksander Firsov & Euclid of Alexandria
		if (valueY == 0) {
			return valueX;
		}
		else {
			return greatestCommonDivisor(valueY, valueX % valueY);
		}
		
	}
	
	// Method to make calculate button active
	public static void calculateButtonAction(JButton workerButton) {
		
		// Declare action listener function using worker variable
		workerButton.addActionListener(new ActionListener() {
					
		// Nested method for performing action
		public void actionPerformed(ActionEvent event) {

			// Input validation logic
			// Booleans to keep track of input validation errors. Specifically, whether or not the user entered width/height are valid numbers.
			boolean widthIsBad = false;
			boolean heightIsBad = false;

			// Try to convert the String in the width entry field into an Integer
			try {
				
				Integer.valueOf(widthEntry.getText());
				
			}
			// If the conversion to an Integer fails, it means the user entry is invalid
			catch (NumberFormatException workerException) {
				
				// Sets boolean for error tracking
				widthIsBad = true;
				
			}
			
			// Try to convert the String in the height entry field into an Integer
			try {
				
				Integer.valueOf(heightEntry.getText());
				
			}
			// If the conversion to an Integer fails, it means the user entry is invalid
			catch (NumberFormatException workerException) {
				
				// Sets boolean for error tracking
				heightIsBad = true;
				
			}
			
			// Conditionals to display proper error messages based on which fields are not valid
			if(widthIsBad == true && heightIsBad == true) {
				
				// Display error message with formatted title
				JOptionPane.showMessageDialog(null, "Please enter a valid width and height. Both must be whole numbers within a reasonable range of display dimensions.", "Error", JOptionPane.INFORMATION_MESSAGE); 
				
			}
			else if(widthIsBad == true && heightIsBad == false) { 
				
				// Display error message with formatted title
				JOptionPane.showMessageDialog(null, "Please enter a valid width. It must be a whole number within a reasonable range of display dimensions.", "Error", JOptionPane.INFORMATION_MESSAGE);
				
			}
			else if(widthIsBad == false && heightIsBad == true) {
				
				// Display error message with formatted title
				JOptionPane.showMessageDialog(null, "Please enter a valid height. It must be a whole number within a reasonable range of display dimensions", "Error", JOptionPane.INFORMATION_MESSAGE);
				
			}
			// Final valid integer testing conditional: means IF both fields are valid integers: do this
			else {
				
				// Nested conditionals to display proper error messages based on which fields are negative or zero (therefore, invalid)
				
				// If width and height are both negative or zero: do this
				if(Integer.valueOf(widthEntry.getText()) <= 0 && Integer.valueOf(heightEntry.getText()) <= 0) {
					
					// Display error message with formatted title
					JOptionPane.showMessageDialog(null, "Please enter a width and height larger than zero.", "Error", JOptionPane.INFORMATION_MESSAGE); 
					
				}
				// If only width is negative or zero: do this
				else if(Integer.valueOf(widthEntry.getText()) <= 0 && Integer.valueOf(heightEntry.getText()) > 0) { 
					
					// Display error message with formatted title
					JOptionPane.showMessageDialog(null, "Please enter a width larger than zero.", "Error", JOptionPane.INFORMATION_MESSAGE);
					
				}
				// If only height is negative or zero: do this
				else if(Integer.valueOf(heightEntry.getText()) <= 0 && Integer.valueOf(widthEntry.getText()) > 0) {
					
					// Display error message with formatted title
					JOptionPane.showMessageDialog(null, "Please enter a height larger than zero", "Error", JOptionPane.INFORMATION_MESSAGE);
					
				}
				// Final negative or zero number conditional --> If both fields are both valid, positive, non-zero numbers: do this
				else {
					
					// Set display label to result { pass in user entry Integer derived from String to math methods };
					resultDisplay.setText("Ratio = " + aspectRatioMath(Integer.valueOf(widthEntry.getText()), Integer.valueOf(heightEntry.getText())));
					// Formatting to make result label pretty
					resultDisplay.setFont(new Font("Ratio =", Font.BOLD, 12));
					
				}
				
			}
				
		}
		
		// End action listener event call
		});

	}

// %end
}