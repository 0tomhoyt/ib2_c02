import org.json.JSONArray;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class order2 extends JFrame{
    //private orderList oList;
    private JButton confirmOrderButton;
    private JTextField textField1;
    private JButton cancelButton;
    private JPanel panel1;
    private JLabel name;
    private JLabel amount;
    private JButton homeButton;



    public order2(String title) {

        super(title);
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array = new JSONArray(response);

        panel1.setBackground(Color.GRAY);
        homeButton. setIcon(new ImageIcon(stock.class.getResource("/com/Home_icon.png")));
        confirmOrderButton. setIcon(new ImageIcon(stock.class.getResource("/com/confirm_icon.png")));
        cancelButton. setIcon(new ImageIcon(stock.class.getResource("/com/cancel_icon.png")));




        name.setText(array.getJSONObject(0).getString("winetype"));
        homeButton.setText("home");
        homeButton. setIcon(new ImageIcon(stock.class.getResource("/com/Home_icon.png")));
        //this.oList = new orderList("orderList",0,0,0);
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



        confirmOrderButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                //oList.setNrTequila(Integer.parseInt(amount.getText()));
                //oList.setNumbers();
                int number = Integer.parseInt(textField1.getText());
                String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getorderlist");
                JSONArray array = new JSONArray(response);
                number += array.getJSONObject(0).getInt("ordernum");
                String urlname = "https://studev.groept.be/api/a21ib2c02/updateorderlist/"+number +"/2";
                db.makeGETRequest(urlname);
                JFrame orderlist =  new orderList("orderlist");
                orderlist.setVisible(true);
                orderlist.setSize(600,800);
                orderlist.setLocation(500,0);
                dispose();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {dispose();
                JFrame stock = new stock("stock");
                stock.setVisible(true);
                stock.setSize(600,800);
                stock.setLocation(500,0);
                dispose();}
        });

    }
    public static void main(String[] args)
    {
        JFrame ui = new order("order");
        ui.setVisible(true);
    }
}
