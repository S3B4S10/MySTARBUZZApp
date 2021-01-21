package co.edu.unipiloto.edu.mystarbuzzapp;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class Signup_Form extends AppCompatActivity {

    private DatabaseHelper myDb;
    private TextInputLayout editFullName, editUsuerName, editEmail, editPassword;
    private Button btnAddData,btnViewAll;
    private  int gender;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup_form);
        getSupportActionBar().setTitle("SignUp Form");
        myDb = new DatabaseHelper(this);
        editFullName = (TextInputLayout)findViewById(R.id.editText_fullname);
        editUsuerName = (TextInputLayout)findViewById(R.id.editText_username);
        editEmail = (TextInputLayout)findViewById(R.id.editText_email);
        editPassword = (TextInputLayout)findViewById(R.id.editText_password);
        btnAddData = (Button)findViewById(R.id.button_register);
        btnViewAll = (Button)findViewById(R.id.button_show_register);
        
        addData();
        viewAll();
        
    }

    private void addData() {
        btnAddData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Integer valueInt = new Integer(gender);
                User user = new User();
                user.setFullName(editFullName.getEditText().getText().toString());
                user.setUserName(editUsuerName.getEditText().getText().toString());
                user.setEmail(editEmail.getEditText().getText().toString());
                user.setPassword(editEmail.getEditText().getText().toString());
                user.setPassword(editPassword.getEditText().getText().toString());

                boolean isInserted = myDb.insertData(user);

                if (isInserted){
                    Toast.makeText(Signup_Form.this,"Data Inserted", Toast.LENGTH_SHORT).show();
                }else{
                    Toast.makeText(Signup_Form.this,"Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    private void viewAll() {
        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Cursor cursor = myDb.getAllData();
                if (cursor.getCount()==0){
                    showMessage("Error", "Nothing Found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursor.moveToNext()){
                    buffer.append("Id: " + cursor.getString(0)+"\n");
                    buffer.append("Name: " + cursor.getString(1)+"\n");
                    buffer.append("UserName: " + cursor.getString(2)+"\n");
                    buffer.append("Email: " + cursor.getString(3)+"\n");
                    buffer.append("Password: " + cursor.getString(4)+"\n");
                    buffer.append("Gender: " + cursor.getString(5)+"\n");
                }
                showMessage("Data",buffer.toString());
            }
        });
    }

    public void showMessage (String title, String message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage((message));
        builder.show();
    }
    public void onRadioButtonClicked(View view){
        boolean checked = ((RadioButton) view).isChecked();

        //chequear que radio fue cliqueado
        switch (view.getId()){
            case R.id.radioButton_female:
                if (checked) {
                    gender = 0;
                    break;
                }
            case R.id.radioButton_male:
                if (checked){
                    gender = 1;
                    break;
                }
        }
    }

}
