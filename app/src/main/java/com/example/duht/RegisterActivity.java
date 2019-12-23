package com.example.duht;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class RegisterActivity extends AppCompatActivity {
    String TAG="During Registration";
    private TextView olduser;
    private EditText editTextFullName;
    private EditText editTextEmail;
    private EditText editTextPhoneNumber;
    private EditText editTextPassword;
    private EditText editTextAge;
    private FirebaseAuth mAuth;
    private RadioButton radioButtonMale;
    private RadioButton radioButtonFemale;
    private String gender="";

    private Button btnRegister;
    DatabaseReference databaseReference;
    FirebaseDatabase firebaseDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        olduser = (TextView)findViewById(R.id.textViewBackToLogin);
        firebaseDatabase=FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference("Patient");
        olduser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(RegisterActivity.this,LoginActivity.class);

                startActivity(i);
                finish();
            }
        });

        editTextFullName=(EditText)findViewById(R.id.editTextName);
        editTextEmail=(EditText)findViewById(R.id.editTextEmail);
        editTextPassword=(EditText)findViewById(R.id.editTextPassword);
        editTextAge=(EditText)findViewById(R.id.editTextAge);
        editTextPhoneNumber=(EditText)findViewById(R.id.editTextPhone);
        radioButtonMale=(RadioButton) findViewById(R.id.radioButtonMale);
        radioButtonFemale=(RadioButton)findViewById(R.id.radioButtonFemale);
        btnRegister=(Button)findViewById(R.id.btnRegister);


        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                final String name=editTextFullName.getText().toString();
                final String email=editTextEmail.getText().toString();
                final String password=editTextPassword.getText().toString();
                final String phone=editTextPhoneNumber.getText().toString();
                final String age=editTextAge.getText().toString();
                if(radioButtonFemale.isChecked()){
                    gender="Female";
                }
                else if(radioButtonMale.isChecked()){
                    gender="Male";
                }
                mAuth = FirebaseAuth.getInstance();
                if(TextUtils.isEmpty(name)){
                      Toast.makeText(getApplicationContext(),"Please enter Name",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Please enter email",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Please enter password",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(phone)){
                    Toast.makeText(getApplicationContext(),"Please enter Phone Number",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(age)){
                    Toast.makeText(getApplicationContext(),"Please enter age",Toast.LENGTH_SHORT).show();
                }
                else if(TextUtils.isEmpty(gender)){
                    Toast.makeText(getApplicationContext(),"Please enter Gender",Toast.LENGTH_SHORT).show();
                }

                else {
                    mAuth.createUserWithEmailAndPassword(email, password)
                            .addOnCompleteListener(RegisterActivity.this, new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {
                                    if (task.isSuccessful()) {
                                        // Sign in success, update UI with the signed-in user's information
                                        Log.d(TAG, "createUserWithEmail:success");

                                        Patient p=new Patient(name,age,phone,email,password,gender);
                                        databaseReference.child(mAuth.getCurrentUser().getUid()).setValue(
                                                p
                                        ).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Log.d(TAG, "Registration done");
                                                Intent i=new Intent(RegisterActivity.this,LoginActivity.class);
                                                startActivity(i);
                                                finish();
                                            }
                                        });



                                    } else {
                                        // If sign in fails, display a message to the user.
                                        Log.w(TAG, "createUserWithEmail:failure", task.getException());
                                        Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                                Toast.LENGTH_SHORT).show();

                                    }
                                }
                            });
                }
            }


        });
    }
}
