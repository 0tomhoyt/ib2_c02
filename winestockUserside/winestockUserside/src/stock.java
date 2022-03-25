import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class stock extends JFrame {
    private JButton orderButton1;
    private JButton orderButton2;
    private JButton orderButton3;
    private JPanel panel1;
    private JLabel nameLabel1;
    private JLabel nameLabel2;
    private JLabel nameLabel3;
    private JLabel numberLabel1;
    private JLabel numberLabel2;
    private JLabel numberLabel3;
    private JButton homeButton;


    public stock(String title) {

        super(title);
        panel1.setBackground(Color.LIGHT_GRAY);
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array = new JSONArray(response);
        JSONObject obj1 = array.getJSONObject(0);
        JSONObject obj2 = array.getJSONObject(1);
        JSONObject obj3 = array.getJSONObject(2);
        nameLabel1.setText(obj1.getString("winetype"));
        numberLabel1.setText(obj1.getString("number"));
        orderButton1.setText("order ");
        nameLabel2.setText(obj2.getString("winetype"));
        numberLabel2.setText(obj2.getString("number"));
        orderButton2.setText("order");
        nameLabel3.setText(obj3.getString("winetype"));
        numberLabel3.setText(obj3.getString("number"));
        orderButton3.setText("order");
        homeButton.setText("home");
        homeButton. setIcon(new ImageIcon(stock.class.getResource("/com/Home_icon.png")));



        setContentPane(panel1);

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
        orderButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new order(obj1.getString("winetype"));
                ui.setVisible(true);
                ui.setSize(600,800);
                ui.setLocation(500,0);
                dispose();
            }
        });
        orderButton2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new order2(obj2.getString("winetype"));
                ui.setVisible(true);
                ui.setSize(600,800);
                ui.setLocation(500,0);
                dispose();
            }
        });

        orderButton3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame ui = new order3(obj3.getString("winetype"));
                ui.setVisible(true);
                ui.setSize(600,800);
                ui.setLocation(500,0);
                dispose();
            }
        });


    }


    public static void main(String[] args)
    {
        JFrame ui = new stock("stock");
        ui.setVisible(true);
    }


}

