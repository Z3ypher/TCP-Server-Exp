/*
Need to figure out how to remove the GUI after saving the port number.

*/

import javax.swing.*;

public class PortGeneratorUI {
    private static JFrame frame;
    private static JPanel panel;
    private static JLabel label;
    private static JTextField textField;
    private static JButton button;
    private static int portNumber;
    private static boolean portSaved;

    public static void initialize() {
        frame = new JFrame("Port Generator");
        panel = new JPanel();
        label = new JLabel("Enter Port Number:");
        textField = new JTextField(10);
        button = new JButton("Save");

        button.addActionListener(e -> {
            try {
                portNumber = Integer.parseInt(textField.getText());
                JOptionPane.showMessageDialog(frame, "Port number saved: " + portNumber);
                portSaved = true;
                synchronized (PortGeneratorUI.class) {
                    PortGeneratorUI.class.notifyAll();
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(frame, "Invalid input. Please enter a valid integer.");
            }
        });

        panel.add(label);
        panel.add(textField);
        panel.add(button);
        frame.add(panel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static int getPortNumber() {
        return portNumber;
    }

    public static synchronized void waitForPort() throws InterruptedException {
        while (!portSaved) {
            PortGeneratorUI.class.wait();
        }
    }

    public void main() {
        PortGeneratorUI.initialize();
        try {
            PortGeneratorUI.waitForPort();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int port = PortGeneratorUI.getPortNumber();
        System.out.println("Port number entered: " + port);
    }
}