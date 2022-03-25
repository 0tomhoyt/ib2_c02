import org.json.JSONArray;
import org.json.JSONObject;

import javax.swing.*;
import java.awt.*;

public class LowStock extends JFrame{
    private JPanel panel1;
    private JLabel label1;
    private JLabel label2;
    private JLabel label3;

    public LowStock()
    {
        String a = "",b = "",c = "";
        DataBase db = new DataBase();
        String response = db.makeGETRequest("https://studev.groept.be/api/a21ib2c02/getstock");
        JSONArray array = new JSONArray(response);
        JSONObject obj = new JSONObject();

        for (int i = 0; i < 3; i++) {
            obj =array.getJSONObject(i);
            if (obj.getInt("number")<=5)
            {
                if (a.isBlank())
                {
                    a = a+"currently  " + obj.getString("winetype")  +  "  only have " +obj.getString("number")+    "  left";
                }
                else if(b.isBlank())
                {
                    b = b+"currently  " + obj.getString("winetype")  +  "  only have " +obj.getString("number")+    "  left";
                }
                else
                {
                    c = c+"currently  " + obj.getString("winetype")  +  "  only have " +obj.getString("number")+    "  left";
                }
            }
        }

        label1.setText(a);
        label2.setText(b);
        label3.setText(c);

        panel1.setBackground(Color.red);
        setContentPane(panel1);
        panel1.setVisible(true);
    }
}
