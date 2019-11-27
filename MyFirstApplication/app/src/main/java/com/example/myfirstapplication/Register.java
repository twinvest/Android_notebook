package com.example.myfirstapplication;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.myfirstapplication.Model.User;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Register extends Activity {
    final FirebaseDatabase database = FirebaseDatabase.getInstance();
    final DatabaseReference table_user = database.getReference("User");

    RadioGroup rg1, rg2, rg3, rg4, rg5;
    RadioButton rd1, rd2, rd3, rd4, rd5;
    Button bt_upload;

    EditText edtId, edtPassword, edtPassword2, edtName, edtRoomsize, edtFloor, edtManagecost, edtSpecialmemo;
    String id, password, name, roomsize, floor, managecost, specialmemo, sYear, sMonth, sDay, eYear, eMonth, eDay;
    String str_Qtype1, str_Qtype2, str_Qtype3, str_Qtype4, str_Qtype5;


    DatePicker startDate, endDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //rg1은 라디오그룹1의 약자로 방의 형태를 의미한다.
        rg1 = (RadioGroup) findViewById(R.id.room_type);
        //rg2은 라디오그룹2의 약자로 난방 형태를 의미한다.
        rg2 = (RadioGroup) findViewById(R.id.register_heating);
        //rg3은 라디오그룹3의 약자로 엘레베이터의 유무를 의미한다.
        rg3 = (RadioGroup) findViewById(R.id.register_elevator);
        //rg4은 라디오그룹4의 약자로 베란다의 유무를 의미한다.
        rg4 = (RadioGroup) findViewById(R.id.register_veranda);
        //rg5은 라디오그룹5의 약자로 전세대출 가능한지 불가능한지 여부를 의미한다.
        rg5 = (RadioGroup) findViewById(R.id.register_loan);



        //아래는 EditText
        edtId = (EditText)findViewById(R.id.register_id);
        edtName = (EditText)findViewById(R.id.register_name);
        edtPassword = (EditText)findViewById(R.id.register_pwd);
        edtPassword2 = (EditText)findViewById(R.id.register_pwd2);
        edtRoomsize = (EditText)findViewById(R.id.register_roomsize);
        edtFloor = (EditText)findViewById(R.id.register_floor);
        edtManagecost = (EditText)findViewById(R.id.register_manageCost);
        edtSpecialmemo = (EditText)findViewById(R.id.register_specialmemo);

        //아래는 DatePicker
        startDate = (DatePicker)findViewById(R.id.start_date);
        endDate = (DatePicker)findViewById(R.id.end_date);


        //업로드버튼
        bt_upload = (Button) findViewById(R.id.bt_upload);
        //업로드버튼 클릭 시 동작할 리스너
        bt_upload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final ProgressDialog mDialog = new ProgressDialog(Register.this);
                mDialog.setMessage("잠시만 기다리세요....");
                mDialog.show();

                //Datepicker값 가져오기
                sYear = startDate.getYear() + "년";
                sMonth = startDate.getMonth() + "월";
                sDay = startDate.getDayOfMonth() + "일";
                eYear = endDate.getYear() + "년";
                eMonth = endDate.getMonth() + "월";
                eDay = endDate.getDayOfMonth() + "일";

                //RadioButton 및 선택된 값 가져오기
                rd1 = (RadioButton) findViewById(rg1.getCheckedRadioButtonId());
                rd2 = (RadioButton) findViewById(rg2.getCheckedRadioButtonId());
                rd3 = (RadioButton) findViewById(rg3.getCheckedRadioButtonId());
                rd4 = (RadioButton) findViewById(rg4.getCheckedRadioButtonId());
                rd5 = (RadioButton) findViewById(rg5.getCheckedRadioButtonId());

                //EditText값 가져오기
                id = edtId.getText().toString();
                password = edtPassword.getText().toString();
                name = edtName.getText().toString();
                roomsize = edtRoomsize.getText().toString();
                floor = edtFloor.getText().toString();
                managecost = edtManagecost.getText().toString();
                specialmemo = edtSpecialmemo.getText().toString();

                //라디오 버튼에 획득 된 실제 값 가져오기
                Log.d("tw", rd1.getText().toString());
                str_Qtype1 = rd1.getText().toString();//방의 형태
                str_Qtype2 = rd2.getText().toString();//난방 형태
                str_Qtype3 = rd3.getText().toString();//엘레베이터 유무
                str_Qtype4 = rd4.getText().toString();//베란다 유무
                str_Qtype5 = rd5.getText().toString();//전세대출 가능 여부

                if (id.equals("") || name.equals("") ||  password.equals("") || edtPassword2.getText().equals("") ||
                        roomsize.equals("") || floor.equals("") || managecost.equals("") || str_Qtype1.equals("") ||
                        str_Qtype2.equals("") || str_Qtype3.equals("") || str_Qtype4.equals("") || str_Qtype5.equals("")){
                    Toast.makeText(Register.this, "모든 정보를 다 입력하지 않았습니다.", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
                else if(!password.equals(edtPassword2.getText().toString())){
                    Toast.makeText(Register.this, "비밀번호가 맞지 않습니다.", Toast.LENGTH_SHORT).show();
                    mDialog.dismiss();
                }
                else {
                    // 아이디, 이름, 번호 다 입력했는지 체크
                    table_user.child(edtId.getText().toString()).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                            //Check if already user phone
                            Log.d("LOG", dataSnapshot.toString());
                            Log.d("LOG", Boolean.toString(dataSnapshot.exists()));
                            if (dataSnapshot.exists()) {
                                mDialog.dismiss();
                                Toast.makeText(Register.this, "중복된 ID가 있습니다.", Toast.LENGTH_SHORT).show();
                            } else {
                                mDialog.dismiss();
                                User user = new User(id, name, password, roomsize, floor, managecost, specialmemo, str_Qtype1,
                                        str_Qtype2, str_Qtype3, str_Qtype4, str_Qtype5);

                                table_user.child(id).setValue(user);
                                Toast.makeText(Register.this, "회원가입이 완료되었습니다!", Toast.LENGTH_SHORT).show();
                                //Intent intent = new Intent(SignUp.this, SignActivity.class);
                                //startActivity(intent);
                            }
                        }
                        @Override
                        public void onCancelled(@NonNull DatabaseError databaseError) {

                        }
                    });
                }
                //이 토스트부분을 DO에 저장하게끔 해. 업로드버튼 눌르면 출력됨.
//                Toast.makeText(getApplicationContext(), str_Qtype1+" 선택됨",
//                        Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), str_Qtype2+" 선택됨",
//                        Toast.LENGTH_SHORT).show();
//                Toast.makeText(getApplicationContext(), sYear + sMonth + sDay + " 선택됨",
//                        Toast.LENGTH_SHORT).show();
            }
        });
    }
}
