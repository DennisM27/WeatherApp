import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import java.awt.BorderLayout;
import java.awt.Image;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;

public class GUI implements ActionListener {

    private JTextArea weatherInfo;
    private JTextField zipCodeEntryField;
    private Weather weather;
    private String zipCode;

    public GUI()
    {
        weatherInfo = new JTextArea(20, 35);
        zipCodeEntryField = new JTextField();
        zipCode = "11217";
        weather = retrieveWeather();

        setupGui();
    }

    public void setupGui()
    {
        JFrame frame = new JFrame("Weather App");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel titlePanel = new JPanel();
        JLabel titleLabel = new JLabel("Current Weather");
        titleLabel.setFont(new Font("Helvetica", Font.BOLD, 20));
        titleLabel.setForeground(Color.blue);
        titlePanel.add(titleLabel);

        JPanel contentPanel = new JPanel();
        JLabel zipcodeHint = new JLabel("Enter Zip Code:");

        JTextField zipcodeBox;
        JButton submitButton;
        JButton clearButton;
        JCheckBox showCelcius;
        JLabel celciusHint;
        // add them all to the panel

        JPanel imagePanel = new JPanel();
        ImageIcon image = new ImageIcon(weather.getConditionIcon());
        Image imageData = image.getImage();
        Image scaledImage = imageData.getScaledInstance(100, 100, java.awt.Image.SCALE_SMOOTH);
        image = new ImageIcon(scaledImage);
        JLabel pictureLabel = new JLabel(image);
        imagePanel.add(pictureLabel);

        // init
        submitButton.addActionListener(this);
        clearButton.addActionListener(this);

        frame.pack();
        frame.setVisible(true);
    }

    private void updateGui() {

    }

    public Weather retrieveWeather() {
        return Networking.getCurrentWeather(zipCode);
    }

    public void actionPerformed(ActionEvent e) {
        JButton button = (JButton) (e.getSource());
        String text = button.getText();

        if (text.equals("submit")) {
            retrieveWeather();
            updateGui();
        }
    }
}
