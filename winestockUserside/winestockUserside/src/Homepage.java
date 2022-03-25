import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Homepage extends JFrame{
    private JButton button1;
    private JButton orderlist;
    private JButton storageList;
    private JButton button4;
    private JButton button6;
    private JButton co2;
    private JButton customer;
    private JPanel homepagePanel;
    private JLabel bar_image;
    private JButton profile;
    private JLabel BAR_MANAGER;


    public Homepage(String title)
    {
        super(title);
        //homepagePanel.setBackground(Color.white);
        orderlist.setText("Order List");
        //bar. setIcon(new ImageIcon(Homepage.class.getResource("/com/Bar_image.jpg")));
        profile.setLocation(275,150);
        profile.setSize(95, 100);
        BAR_MANAGER.setLocation(200,50);
        BAR_MANAGER.setSize(300, 50);
        profile. setIcon(new ImageIcon(Homepage.class.getResource("/com/profile_icon.png")));

        button4. setIcon(new ImageIcon(Homepage.class.getResource("/com/notification_icon.png")));
        button6. setIcon(new ImageIcon(Homepage.class.getResource("/com/Notes_icon.jpg")));
        button1. setIcon(new ImageIcon(Homepage.class.getResource("/com/settings_icon.png")));

        button1.setBackground(Color.WHITE);
        button4.setBackground(Color.WHITE);
        button6.setBackground(Color.WHITE);
        storageList.setBackground(Color.WHITE);
        orderlist.setBackground(Color.WHITE);
        customer.setBackground(Color.WHITE);
        co2.setBackground(Color.WHITE);

        storageList.setIcon(new ImageIcon(Homepage.class.getResource("/com/list_icon.png")));
        orderlist.setIcon(new ImageIcon(Homepage.class.getResource("/com/list_icon.png")));
        co2.setIcon(new ImageIcon(Homepage.class.getResource("/com/Co2_carbon_dioxide_icon.png")));
        customer.setIcon(new ImageIcon(Homepage.class.getResource("/com/people_icon.png")));
        button4.setSize(80,80);
        storageList.setText("Storage List");
        co2.setText("co2 info");

        bar_image.add(profile);
        bar_image.add(BAR_MANAGER);

        orderlist.setSize(400,400);
        storageList.setSize(400,400);
        setContentPane(homepagePanel);


        //check low stock
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array = new JSONArray(response);
        JSONObject obj = new JSONObject();

        for (int i = 0; i < 3; i++) {
            obj =array.getJSONObject(i);
            if (obj.getInt("number")<=5)
            {
                JFrame lowStock = new LowStock();
                lowStock.setSize(400,200);
                lowStock.setLocationRelativeTo(storageList);
                lowStock.setVisible(true);
                lowStock.setAlwaysOnTop(true);
                break;
            }
        }
        //get number of people inside
        response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getPeople");
        JSONArray peoplearray = new JSONArray(response);
        JSONObject peopleobj = peoplearray.getJSONObject(0);
        int people = peopleobj.getInt("number");
        customer.setText("currently " + people +" people inside");

        orderlist.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame orderlist =  new orderList("orderlist");
                orderlist.setVisible(true);
                orderlist.setSize(600,800);
                orderlist.setLocation(500,0);
                dispose();
            }
        });
        storageList.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame stock = new stock("stock");
                stock.setVisible(true);
                stock.setSize(600,800);
                stock.setLocation(500,100);
                dispose();
            }
        });
    }

    public static void main(String[] args)
    {
        JFrame ui = new Homepage("homepage");
        ui.setSize(600,750);
        ui.setLocation(500,0);
        ui.setVisible(true);
    }

}
