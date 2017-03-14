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
		// TODO: Revise UI - consider switching layout to something which allows more precise alignment, while still allowing the user to move around window.
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
		// Display result by calling math method
		else {
			// TODO: Insert Github link / make it clickable / make email address clickable to open in mail client or copy if one is not present?
			JOptionPane.showMessageDialog(null, "This is a bug that shouldn't be happening. Contact the developer at alexnoyle@icloud.com, or open an issue on github", "Fatal Error", JOptionPane.INFORMATION_MESSAGE);
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
			
			// Logic to catch blank text in fields and display errors if caught
			// TODO: Catch other potential crashes such as non-number characters
			if (widthEntry.getText().equals("") && heightEntry.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Width and Height are both blank. Please enter them and try again.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (widthEntry.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Width is blank. Please enter it and try again.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else if (heightEntry.getText().equals("")) {
				JOptionPane.showMessageDialog(null, "Height is blank. Please enter it and try again.", "Error", JOptionPane.INFORMATION_MESSAGE);
			}
			else {
				// Passes in user-entered values to label
				resultDisplay.setText("Ratio = " + aspectRatioMath(Integer.valueOf(widthEntry.getText()), Integer.valueOf(heightEntry.getText())));
				resultDisplay.setFont(new Font("Ratio =", Font.BOLD, 12));
			}
				
		}
		
		// End action listener event call
		});

	}

// %end
}