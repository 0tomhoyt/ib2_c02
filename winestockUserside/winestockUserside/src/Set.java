import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Set extends JFrame{
    private JPanel panel1;
    private JPanel setPagePanel;
    private JButton homeButton;
    private JTextField sensorIDTextField;
    private JTextField currentNumberTextField;
    private JLabel setLiqureNameLabel;
    private JLabel setCurrentNumberLabel;
    private JTextField liqureNameTextField;
    private JButton setLiqureNameButton;
    private JButton setPeopleButton;
    private JLabel liqureNameLabel;
    private JLabel sensorIDLabel;
    private JLabel currentNumberLabel;

    public Set(String title)
    {
        super(title);
        setPagePanel.setBackground(Color.WHITE);
        setLiqureNameLabel.setText("   set the liqure type combined to Sensor");
        setCurrentNumberLabel.setText("   correct the current number of people inside");
        setLiqureNameButton.setText("SET");
        setPeopleButton.setText("SET");
        homeButton.setText("home");
        homeButton. setIcon(new ImageIcon(stock.class.getResource("/com/Home_icon.png")));
        sensorIDTextField.setBackground(Color.LIGHT_GRAY);
        currentNumberTextField.setBackground(Color.LIGHT_GRAY);
        liqureNameTextField.setBackground(Color.LIGHT_GRAY);
        setContentPane(setPagePanel);
        setPagePanel.setVisible(true);

        homeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new Homepage("homepage");
                ui.setVisible(true);
                ui.setSize(600,800);
                ui.setLocation(500,0);
                dispose();
            }
        });

        setLiqureNameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase db = new DataBase();
                db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/setLiqureName/"+liqureNameTextField.getText()+"/"+sensorIDTextField.getText());
            }
        });

        setPeopleButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase db = new DataBase();
                db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/updatePeople/"+currentNumberTextField.getText()+"/1");
            }
        });
    }
}
