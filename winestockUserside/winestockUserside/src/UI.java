import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class UI extends JFrame{
    private JPanel myPanel;
    private JButton goToStockPage;
    private JButton goToAddPage;
    public UI(String title){
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,200);
        setLocation(400,400);
        setLayout(null);


        //initialize basic
        myPanel = new JPanel();
        myPanel.setSize(500,200);
        goToAddPage = new JButton("add new stock");
        goToStockPage = new JButton("view current stock");


        myPanel.add(goToAddPage);
        myPanel.add(goToStockPage);

        this.setContentPane(myPanel);

        //change the page
        // the page changing button's logic should be changed later. currently i only have 2 pages. but it should be more comlicated in the future
        goToAddPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageAddStock();
            }
        });
        goToStockPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageShowStock();
            }
        });
    }


    public void pageAddStock()
    {
        myPanel.removeAll();
        JLabel winetypelable = new JLabel();
        JTextField winetype = new JTextField(10);
        JLabel numberlable = new JLabel();
        JTextField number = new JTextField(10);
        JLabel idstocklable = new JLabel();
        JTextField idstock = new JTextField(10);
        JButton upload = new JButton("upload");


        winetypelable.setText("winetype:");
        numberlable.setText("number:");
        idstocklable.setText("stock id:");
        winetype.setEditable(true);
        number.setEditable(true);
        idstock.setEditable(true);

        myPanel.add(goToStockPage);
        myPanel.add(winetypelable);
        myPanel.add(winetype);
        myPanel.add(numberlable);
        myPanel.add(number);
        myPanel.add(idstocklable);
        myPanel.add(idstock);
        myPanel.add(upload);
        this.setContentPane(myPanel);

        upload.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DataBase db = new DataBase();
                //the api is INSERT INTO winestock (idstock, winetype, number) VALUES (:l,:m,:n);
                db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/insertwine/"+idstock.getText()+"/"+winetype.getText()+"/"+number.getText());
            }
        });

        goToStockPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageShowStock();
            }
        });

    }


    public void pageShowStock()
    {
        myPanel.removeAll();
        JLabel myLabel = new JLabel();
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array = new JSONArray(response);

        myPanel.add(goToAddPage);

        for (int i = 0; i < array.length(); i++){
            JSONObject currentObject = array.getJSONObject(i);
            myLabel = new JLabel();
            myLabel.setText(currentObject.getInt("idstock")+"   "+currentObject.getInt("number")+"    " +currentObject.getString("winetype"));
            myPanel.add(myLabel);
        }

        goToAddPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                pageAddStock();
            }
        });
        this.setContentPane(myPanel);
    }
    public static void main(String[] args){
        UI ui = new UI("test");
        ui.setVisible(true);
        ui.pack();
    }
}
