package com.project.ms_server_connection;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.project.ms_server_connection.Connection.ConnectionClass;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class SignUp extends AppCompatActivity {
    EditText EdName,EdEmail,EdPass;
    AppCompatButton submitBtn;
    TextView statusTxt;
    Connection con;
    Statement stmt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        EdEmail = findViewById(R.id.editText3);
        EdName = findViewById(R.id.editText2);
        EdPass = findViewById(R.id.editText5);
        submitBtn = findViewById(R.id.appCompatButton);
        statusTxt = findViewById(R.id.status_txt);

        //register btn
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new SignUp.registerUser().execute("");

            }
        });

    }
    public class registerUser extends AsyncTask<String, String,String >{
        String z = "";
        Boolean isSuccess = false;

        @Override
        protected void onPreExecute() {
            statusTxt.setText("Sending data to database");
        }

        @Override
        protected void onPostExecute(String s) {
            statusTxt.setText("Registration successful");
            EdName.setText("");
            EdEmail.setText("");
            EdPass.setText("");
            startActivity(new Intent(SignUp.this, SignIn.class));
            finish();
        }

        @Override
        protected String doInBackground(String... strings) {
            try {
                con = connectionClass(ConnectionClass.un,ConnectionClass.pass,ConnectionClass.db,ConnectionClass.ip);
                if (con == null){
                    z= "check your internet connection";
                }else {
                    String sql = "INSERT INTO register (name,email,password) VALUES ('"+EdName.getText()+"','"+EdEmail.getText()+"','"+EdPass.getText()+"')";
                    stmt = con.createStatement();
                    stmt.executeUpdate(sql);

                }

            }catch (Exception e){
                isSuccess = false;
                z=e.getMessage();

            }
            return z;
        }
    }

    @SuppressLint("NewApi")
    public Connection connectionClass(String user, String password, String database,String server){
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        Connection connection = null;
        String connectionUrl = null;
        try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver");
            connectionUrl = "jdbc:jtds:sqlserver://" + server+"/" + database + ";user=" + user + ";password=" + password +"+";
            connection = DriverManager.getConnection(connectionUrl);

        }catch (Exception e){
            Log.e("SQL Connection Error : ", e.getMessage());

        }
        return connection;
    }
    public void sign_In(View view) {
        startActivity(new Intent(SignUp.this, SignIn.class));
        finish();

    }
}