package com.schoolmanagement.Student.Fragment;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.util.Log;
import android.view.ContextThemeWrapper;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.schoolmanagement.Other.CustomHeaderWithRelative;
import com.schoolmanagement.Other.MyProgressDialog;
import com.schoolmanagement.R;
import com.schoolmanagement.Student.HomeActivity;
import com.schoolmanagement.Student.Model.Self_Study_Gks_Object;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import pl.droidsonroids.gif.GifImageView;


public class Selection_Exam_Fragment extends Fragment {
    int qnsNo = 0;
    MyProgressDialog myProgressDialog;
    String yourAns = "";
    String rightAns = "";
    Vibrator vibrator;
    Animation mAnim, mAnim2;
    TextView msgCountTime, msgAlarmMsg, msgQnsPaperNumber;
    TextView msgQnsPaperQns, msgQnsPaperOp1, msgQnsPaperOp2, msgQnsPaperOp3, msgQnsPaperOp4, msgQnsPaperOp5;
    TextView msgQnsPaperNext, msgQnsPaperSkip;
    RelativeLayout relativeAlarmMsg;
    CountDownTimer countDownTimer;
    ArrayList<Self_Study_Gks_Object> items;
    Self_Study_Gks_Object item;
    int click = 0, totalqns = 0;
    int r_ans = 0, w_ans = 0, skip = 0, not_att = 0;
    //SimpleDateFormat format = new SimpleDateFormat("mm:ss");
    SimpleDateFormat format = new SimpleDateFormat("hh:mm");
    String id, sub_id, package_id, t_id, type, exam_name;
    GifImageView gifget, gifwrong;
    int[] array_set_up_text;
    Random rand_set_up_text;
    int rand_text_set_up;
    int duration, timec;
    String per, status;
    Dialog dialog;
    TextView txt_subject_named, id_txt_subject_marks;
    String sub, mark, code_type, examtime;
    ImageView back,home;

 //    int qnsNo = 0;
//    int click = 0, totalqns = 0;
//    @RequiresApi(api = Build.VERSION_CODES.N)
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
//        setContentView(R.layout.student_exam_select_activity);

    public Activity activity;
    public FragmentManager fragmentManager;
    RelativeLayout header;
    DrawerLayout drawer;

    public Selection_Exam_Fragment(Activity context, FragmentManager fragmentManager, RelativeLayout lin_header, DrawerLayout drawer, String Select_subject, String sub_code_type, String marksvalue) {
        this.activity = context;
        this.fragmentManager = fragmentManager;
        this.header = lin_header;
        this.drawer = drawer;
        this.sub=Select_subject;
        this.mark=marksvalue;
        this.code_type=sub_code_type;

    }

    @Override
    public View onCreateView(LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {
        View   rootView = inflater.inflate(R.layout.student_exam_select_activity, container, false);
        CustomHeaderWithRelative.setInnerToolbar(getActivity(), header, "Select Exam");

        txt_subject_named = (TextView)rootView.findViewById(R.id.txt_subject_named);
        id_txt_subject_marks = (TextView)rootView.findViewById(R.id.id_txt_subject_marks);

//        Bundle extras = getIntent().getExtras();
//        sub = extras.getString("select_subjects");
//        mark = extras.getString("spinner");
//        code_type = extras.getString("sub_code_type");
//        Toast.makeText(this, "" +mark+ code_type, Toast.LENGTH_SHORT).show();

        txt_subject_named.setText(sub);
        id_txt_subject_marks.setText("(" + mark + " Marks)");
//        msgQnsPaperName = (TextView) this.findViewById(R.id.msgQnsPaperName);
        msgCountTime = (TextView) rootView.findViewById(R.id.msgCountTime);
        msgQnsPaperNext = (TextView) rootView.findViewById(R.id.msgQnsPaperNext);
        msgQnsPaperNumber = (TextView) rootView.findViewById(R.id.msgQnsPaperNumber);
        msgQnsPaperSkip = (TextView) rootView.findViewById(R.id.msgQnsPaperSkip);
        msgAlarmMsg = (TextView) rootView.findViewById(R.id.msgAlarmMsg);

        relativeAlarmMsg = (RelativeLayout) rootView.findViewById(R.id.relativeAlarmMsg);
        msgQnsPaperQns = (TextView) rootView.findViewById(R.id.msgQnsPaperQns);
        msgQnsPaperOp1 = (TextView) rootView.findViewById(R.id.msgQnsPaperOp1);
        msgQnsPaperOp2 = (TextView) rootView.findViewById(R.id.msgQnsPaperOp2);
        msgQnsPaperOp3 = (TextView) rootView.findViewById(R.id.msgQnsPaperOp3);
        msgQnsPaperOp4 = (TextView) rootView.findViewById(R.id.msgQnsPaperOp4);
        msgQnsPaperOp5 = (TextView) rootView.findViewById(R.id.msgQnsPaperOp5);
        gifget = (GifImageView) rootView.findViewById(R.id.gifget);
        gifwrong = (GifImageView) rootView.findViewById(R.id.gifwrong);
        vibrator = (Vibrator) activity.getSystemService(Context.VIBRATOR_SERVICE);
        vibrator.cancel();
        mAnim = AnimationUtils.loadAnimation(activity, R.anim.blink);
        mAnim2 = AnimationUtils.loadAnimation(activity, R.anim.blink2);
        items = new ArrayList<Self_Study_Gks_Object>();

        fetchTestFromServer();

        msgQnsPaperSkip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gifget.setVisibility(View.GONE);
                gifwrong.setVisibility(View.GONE);
                if(items != null){
                    click = 0;
                    msgQnsPaperOp1.clearAnimation();
                    msgQnsPaperOp2.clearAnimation();
                    msgQnsPaperOp3.clearAnimation();
                    msgQnsPaperOp4.clearAnimation();
                    items.get(qnsNo - 1).setSelectans("Skip");
                    if (items != null) {
                        if (qnsNo < totalqns) {
                            if (yourAns.equals("")) {
                                skip = skip + 1;
                                String qns = items.get(qnsNo).getQns().toString().trim();
                                String op1 = items.get(qnsNo).getOp1().toString().trim();
                                String op2 = items.get(qnsNo).getOp2().toString().trim();
                                String op3 = items.get(qnsNo).getOp3().toString().trim();
                                String op4 = items.get(qnsNo).getOp4().toString().trim();
                                String op5 = items.get(qnsNo).getOp5().toString().trim();
                                String r_ans = items.get(qnsNo).getRingtans().toString().trim();
                                if (r_ans.equalsIgnoreCase(op1)) {
                                    rightAns = "1";
                                } else if (r_ans.equalsIgnoreCase(op2)) {
                                    rightAns = "2";
                                } else if (r_ans.equalsIgnoreCase(op3)) {
                                    rightAns = "3";
                                } else if (r_ans.equalsIgnoreCase(op4)) {
                                    rightAns = "4";
                                }

                                msgQnsPaperQns.setText(qns);
                                msgQnsPaperOp1.setText(op1);
                                msgQnsPaperOp1.setTextColor(getResources().getColor(R.color.black_color));

                                msgQnsPaperOp2.setText(op2);
                                msgQnsPaperOp2.setTextColor(getResources().getColor(R.color.black_color));

                                msgQnsPaperOp3.setText(op3);
                                msgQnsPaperOp3.setTextColor(getResources().getColor(R.color.black_color));

                                msgQnsPaperOp4.setText(op4);
                                msgQnsPaperOp4.setTextColor(getResources().getColor(R.color.black_color));

                                msgQnsPaperOp5.setText(op5);
                                msgQnsPaperOp5.setTextColor(getResources().getColor(R.color.black_color));

                                qnsNo = qnsNo + 1;
                                msgQnsPaperNumber.setText(qnsNo + "/" + totalqns);
                            } else {
                                Toast.makeText(activity, "Your answer selected so click on next button for next qns", Toast.LENGTH_SHORT).show();
                            }
                        } else {
                            fetchTestFromServer();
                            /*showResult();*/
                            //                            uploadResult();
                        }
                    }
                }
            }
        });

        msgQnsPaperNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gifget.setVisibility(View.GONE);
                gifwrong.setVisibility(View.GONE);
                if (items != null) {
                    if (qnsNo < totalqns) {
                        if (!yourAns.equals("")) {

                            String qns = items.get(qnsNo).getQns().toString().trim();
                            String op1 = items.get(qnsNo).getOp1().toString().trim();
                            String op2 = items.get(qnsNo).getOp2().toString().trim();
                            String op3 = items.get(qnsNo).getOp3().toString().trim();
                            String op4 = items.get(qnsNo).getOp4().toString().trim();
                            String op5 = items.get(qnsNo).getOp5().toString().trim();
                            String r_ans = items.get(qnsNo).getRingtans().toString().trim();
                            if(r_ans.equalsIgnoreCase(op1)) {
                                rightAns = "1";
                            } else if (r_ans.equalsIgnoreCase(op2)) {
                                rightAns = "2";
                            } else if (r_ans.equalsIgnoreCase(op3)) {
                                rightAns = "3";
                            } else if (r_ans.equalsIgnoreCase(op4)) {
                                rightAns = "4";
                            }

                            click = 0;
                            msgQnsPaperOp1.clearAnimation();
                            msgQnsPaperOp2.clearAnimation();
                            msgQnsPaperOp3.clearAnimation();
                            msgQnsPaperOp4.clearAnimation();
                            msgQnsPaperQns.setText(qns);

                            msgQnsPaperOp1.setText(op1);
                            msgQnsPaperOp1.setTextColor(getResources().getColor(R.color.black_color));

                            msgQnsPaperOp2.setText(op2);
                            msgQnsPaperOp2.setTextColor(getResources().getColor(R.color.black_color));

                            msgQnsPaperOp3.setText(op3);
                            msgQnsPaperOp3.setTextColor(getResources().getColor(R.color.black_color));

                            msgQnsPaperOp4.setText(op4);
                            msgQnsPaperOp4.setTextColor(getResources().getColor(R.color.black_color));

                            msgQnsPaperOp5.setText(op5);
                            msgQnsPaperOp4.setTextColor(getResources().getColor(R.color.black_color));

                            qnsNo = qnsNo + 1;
                            msgQnsPaperNumber.setText(qnsNo + "/" + totalqns);
                            yourAns = "";
                        } else {
                            Toast.makeText(activity, "Please select any one answer, after click on next button", Toast.LENGTH_SHORT).show();
                        }
                    } else {

                        countDownTimer.cancel();
                      //  Toast.makeText(Selection_Exam_Activity.this, "Practice Over", Toast.LENGTH_SHORT).show();
                        //fetchTestFromServer();
                        Log.d("Chetana", sub);
                        Log.d("Chetana", mark);
                        Log.d("Chetana", msgCountTime.getText().toString());
                        Log.d("Chetana", "" + w_ans);
                        Log.d("Chetana", "" + r_ans);
                        double pracentagee = (100 * r_ans) / Double.parseDouble(mark);
                        //Toast.makeText(Selection_Exam_Activity.this, "" + pracentagee, Toast.LENGTH_SHORT).show();
                        per = (String.valueOf(new DecimalFormat("##.##").format(pracentagee) + "%"));
                       // Toast.makeText(Selection_Exam_Activity.this, "" + per, Toast.LENGTH_SHORT).show();
                        Log.d("Chetana", per);
                        if (per.length() <= 50) {
                            status = "Good";
                        } else if (per.length() <= 75) {
                            status = "Better";
                        } else if (per.length() <= 100) {
                            status = "Best";
                        }
                        Log.d("Chetana", status);

                       // UploadResultDetails();

                        ShowResult();
                    }
                }
            }
        });

        msgQnsPaperOp1.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (click == 0) {
                    String op1 = items.get(qnsNo - 1).getOp1().toString().trim();
                    yourAns = op1;
                    if (rightAns.equals("1")) {
                        msgQnsPaperOp1.setText(op1);
                        msgQnsPaperOp1.setTextColor(getResources().getColor(R.color.green_color));
                        r_ans = r_ans + 1;
                        gifget.setVisibility(View.VISIBLE);
                        gifwrong.setVisibility(View.GONE);
                        //                        rand_set_up_text = new Random();
                        //                        array_set_up_text = new int[] { R.drawable.happy,R.drawable.right_answer_one};
                        //                        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                        //                        gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                        //                        gifget.setVisibility(View.VISIBLE);
                        //                        gifwrong.setVisibility(View.GONE);

                    } else {
                        msgQnsPaperOp1.setText(op1);
                        msgQnsPaperOp1.setTextColor(getResources().getColor(R.color.red_new));
                        vibrator.vibrate(300);
                        w_ans = w_ans + 1;
                        gifwrong.setVisibility(View.VISIBLE);
                        gifget.setVisibility(View.GONE);
                        //                        Toast.makeText(Fill_In_TheBlanks_Options.this, "right", Toast.LENGTH_SHORT).show();
                        //                        rand_set_up_text = new Random();
                        //                        array_set_up_text = new int[] {R.drawable.wrong,R.drawable.no_image7};
                        //                        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                        //                        gifwrong.setImageResource(array_set_up_text[rand_text_set_up]);
                        //                        gifwrong.setVisibility(View.VISIBLE);
                        //                        gifget.setVisibility(View.GONE);
                        if (rightAns.equals("2")) {
                            String op = items.get(qnsNo - 1).getOp2().toString().trim();
                            msgQnsPaperOp2.setText(op);
                            msgQnsPaperOp2.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp2.startAnimation(mAnim);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                            //                            Toast.makeText(Fill_In_TheBlanks_Options.this, "Wrong", Toast.LENGTH_SHORT).show();
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);

                        } else if (rightAns.equals("3")) {
                            String op = items.get(qnsNo - 1).getOp3().toString().trim();
                            msgQnsPaperOp3.setText(op);
                            msgQnsPaperOp3.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp3.startAnimation(mAnim);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);

                        } else if (rightAns.equals("4")) {
                            String op = items.get(qnsNo - 1).getOp4().toString().trim();
                            msgQnsPaperOp4.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp4.setText(op);
                            msgQnsPaperOp4.startAnimation(mAnim);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                        }
                    }
                    click = 1;
                    items.get(qnsNo - 1).setSelectans("1");
                }
                return false;
            }
        });

        msgQnsPaperOp2.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (click == 0) {
                    String op1 = items.get(qnsNo - 1).getOp2().toString().trim();
                    yourAns = op1;
                    if (rightAns.equalsIgnoreCase("2")) {
                        msgQnsPaperOp2.setText(op1);
                        msgQnsPaperOp2.setTextColor(getResources().getColor(R.color.green_color));
                        r_ans = r_ans + 1;
                        gifget.setVisibility(View.VISIBLE);
                        gifwrong.setVisibility(View.GONE);
                        //                        rand_set_up_text = new Random();
                        //                        array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                        //                        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                        //                        gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                        //                        gifget.setVisibility(View.VISIBLE);
                        //                        gifwrong.setVisibility(View.GONE);
                    } else {
                        msgQnsPaperOp2.setText(op1);
                        msgQnsPaperOp2.setTextColor(getResources().getColor(R.color.red_new));
                        vibrator.vibrate(300);
                        w_ans = w_ans + 1;
                        gifwrong.setVisibility(View.VISIBLE);
                        gifget.setVisibility(View.GONE);
                        if (rightAns.equalsIgnoreCase("1")) {
                            String op = items.get(qnsNo - 1).getOp1().toString().trim();
                            msgQnsPaperOp1.setText(op);
                            msgQnsPaperOp1.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp1.startAnimation(mAnim);
                            gifget.setVisibility(View.GONE);
                            gifwrong.setVisibility(View.VISIBLE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //            ``                gifwrong.setVisibility(View.GONE);

                        } else if (rightAns.equalsIgnoreCase("3")) {
                            String op = items.get(qnsNo - 1).getOp3().toString().trim();
                            msgQnsPaperOp3.setText(op);
                            msgQnsPaperOp3.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp3.startAnimation(mAnim);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);

                        } else if (rightAns.equalsIgnoreCase("4")) {
                            String op = items.get(qnsNo - 1).getOp4().toString().trim();
                            msgQnsPaperOp4.setText(op);
                            msgQnsPaperOp4.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp4.startAnimation(mAnim);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                        }
                    }
                    //                    rand_set_up_text = new Random();
                    //                    array_set_up_text = new int[] { R.drawable.wrong_answer,  R.drawable.wrong, R.drawable.ni_imag1, R.drawable.no_image4,R.drawable.no_image5,R.drawable.no_imgae3,R.drawable.no_image7};
                    //                    rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                    //                    gifwrong.setImageResource(array_set_up_text[rand_text_set_up]);
                    //                    gifwrong.setVisibility(View.VISIBLE);
                    //                    gifwrong.setVisibility(View.GONE);
                    //                    gifget.setVisibility(View.GONE);
                    click = 1;
                    items.get(qnsNo - 1).setSelectans("2");
                }
                return false;
            }
        });

        msgQnsPaperOp3.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (click == 0) {
                    String op1 = items.get(qnsNo - 1).getOp3().toString().trim();
                    yourAns = op1;
                    if (rightAns.equalsIgnoreCase("3")) {
                        msgQnsPaperOp3.setText(op1);
                        msgQnsPaperOp3.setTextColor(getResources().getColor(R.color.green_color));
                        r_ans = r_ans + 1;
                        gifget.setVisibility(View.VISIBLE);
                        gifwrong.setVisibility(View.GONE);
                        //                        rand_set_up_text = new Random();
                        //                        array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                        //                        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                        //                        gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                        //                        gifget.setVisibility(View.VISIBLE);
                        //                        gifwrong.setVisibility(View.GONE);
                    } else {
                        msgQnsPaperOp3.setText(op1);
                        msgQnsPaperOp3.setTextColor(getResources().getColor(R.color.red_new));
                        vibrator.vibrate(300);
                        w_ans = w_ans + 1;
                        gifwrong.setVisibility(View.VISIBLE);
                        gifget.setVisibility(View.GONE);
                        if (rightAns.equalsIgnoreCase("2")) {
                            String op = items.get(qnsNo - 1).getOp2().toString().trim();
                            msgQnsPaperOp2.setText(op);
                            msgQnsPaperOp2.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp2.startAnimation(mAnim);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);

                        } else if (rightAns.equalsIgnoreCase("1")) {
                            String op = items.get(qnsNo - 1).getOp1().toString().trim();
                            msgQnsPaperOp1.setText(op);
                            msgQnsPaperOp1.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp1.startAnimation(mAnim);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);

                        } else if (rightAns.equalsIgnoreCase("4")){

                            String op = items.get(qnsNo - 1).getOp4().toString().trim();
                            msgQnsPaperOp4.setText(op);
                            msgQnsPaperOp4.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp4.startAnimation(mAnim);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                        }
                    }
                    //                    rand_set_up_text = new Random();
                    //                    array_set_up_text = new int[] { R.drawable.wrong_answer,  R.drawable.wrong, R.drawable.ni_imag1, R.drawable.no_image4,R.drawable.no_image5,R.drawable.no_imgae3,R.drawable.no_image7};
                    //                    rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                    //                    gifwrong.setImageResource(array_set_up_text[rand_text_set_up]);
                    //                    gifwrong.setVisibility(View.VISIBLE);
                    //                    gifwrong.setVisibility(View.GONE);
                    //                    gifget.setVisibility(View.GONE);
                    click = 1;
                    items.get(qnsNo - 1).setSelectans("3");
                }
                return false;
            }
        });

        msgQnsPaperOp4.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (click == 0) {
                    String op1 = items.get(qnsNo - 1).getOp4().toString().trim();

                    yourAns = op1;

                    if (rightAns.equalsIgnoreCase("4")) {
                        msgQnsPaperOp4.setText(op1);
                        msgQnsPaperOp4.setTextColor(getResources().getColor(R.color.green_color));
                        r_ans = r_ans + 1;
                        gifget.setVisibility(View.VISIBLE);
                        gifwrong.setVisibility(View.GONE);
                        //                        rand_set_up_text = new Random();
                        //                        array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                        //                        rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                        //                        gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                        //                        gifget.setVisibility(View.VISIBLE);
                        //                        gifwrong.setVisibility(View.GONE);
                        //items.get(qnsNo - 1).setSelectans("4");
                    } else {
                        msgQnsPaperOp4.setText(op1);
                        msgQnsPaperOp4.setTextColor(getResources().getColor(R.color.red_new));
                        vibrator.vibrate(300);
                        w_ans = w_ans + 1;
                        gifwrong.setVisibility(View.VISIBLE);
                        gifget.setVisibility(View.GONE);
                        if (rightAns.equalsIgnoreCase("2")) {
                            String op = items.get(qnsNo - 1).getOp2().toString().trim();
                            msgQnsPaperOp2.setText(op);
                            msgQnsPaperOp2.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp2.startAnimation(mAnim);
                            gifget.setVisibility(View.GONE);
                            gifwrong.setVisibility(View.VISIBLE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);

                        } else if (rightAns.equalsIgnoreCase("3")) {
                            String op = items.get(qnsNo - 1).getOp3().toString().trim();
                            msgQnsPaperOp3.setText(op);
                            msgQnsPaperOp3.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp3.startAnimation(mAnim);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                        } else if (rightAns.equalsIgnoreCase("1")) {
                            String op = items.get(qnsNo - 1).getOp1().toString().trim();
                            msgQnsPaperOp1.setText(op);
                            msgQnsPaperOp1.setTextColor(getResources().getColor(R.color.green_color));
                            msgQnsPaperOp1.startAnimation(mAnim);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                            //                            rand_set_up_text = new Random();
                            //                            array_set_up_text = new int[] { R.drawable.happy,  R.drawable.right_answer, R.drawable.right_answer_one, R.drawable.right_answer_two};
                            //                            rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                            //                            gifget.setImageResource(array_set_up_text[rand_text_set_up]);
                            //                            gifget.setVisibility(View.VISIBLE);
                            //                            gifwrong.setVisibility(View.GONE);
                        }
                    }
                    //                    rand_set_up_text = new Random();
                    //                    array_set_up_text = new int[] { R.drawable.wrong_answer,  R.drawable.wrong, R.drawable.ni_imag1, R.drawable.no_image4,R.drawable.no_image5,R.drawable.no_imgae3,R.drawable.no_image7};
                    //                    rand_text_set_up = rand_set_up_text.nextInt(array_set_up_text.length - 1);
                    //                    gifwrong.setImageResource(array_set_up_text[rand_text_set_up]);
                    //                    gifwrong.setVisibility(View.VISIBLE);
                    //                    gifget.setVisibility(View.GONE);
                    //                    gifget.setVisibility(View.GONE);
                    click = 1;
                    items.get(qnsNo - 1).setSelectans("4");
                }
                return false;
            }
        });

        msgQnsPaperOp5.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (click == 0) {
                    String op1 = items.get(qnsNo - 1).getOp5().toString().trim();
                    yourAns = op1;
                    not_att = not_att + 1;
                    msgQnsPaperOp5.setText(op1);
                    msgQnsPaperOp5.setTextColor(getResources().getColor(R.color.red_new));
                    //                    gifget.setVisibility(View.VISIBLE);
                    //                    gifwrong.setVisibility(View.VISIBLE);
                    click = 1;
                    items.get(qnsNo - 1).setSelectans("5");
                    //items.get(qnsNo - 1).setSelectans("Not Attempt");
                }
                return false;
            }
        });
        return  rootView;
    }



    public void fetchTestFromServer(){
        duration=10;
        startCounter();
        items= new ArrayList<>();
        totalqns = 3;
        item = new Self_Study_Gks_Object("1",
               "what is Android",
                "",
               "Operating System",
                "Test",
                "about",
                "rest",
                "Not Attempt",
               "Operating System",
                "",
                false
        );
        items.add(item);
        item = new Self_Study_Gks_Object("2",
                "what is Object",
                "",
                "Operating System",
                "Entity",
                "about",
                "rest",
                "Not Attempt",
                "Entity",
                "",
                false
        );
        items.add(item);

        item = new Self_Study_Gks_Object("3",
                "what is Interface",
                "",
                "Operating System",
                "Entity",
                "Internal Communication",
                "rest",
                "Not Attempt",
                "Internal Communication",
                "",
                false
        );
        items.add(item);

        item =  new Self_Study_Gks_Object("4",
                "what is test",
                " ",
                "oprating system",
                "Entity",
                "Internal Communication",
                "rest",
                "Not Attempt",
                "Internal Communication",
                "",
                false);
        items.add(item);

        //msgQnsPaperName.setText("" + exam_name + " Exam");
        txt_subject_named.startAnimation(AnimationUtils.loadAnimation(activity, R.anim.zoom_animation));

        if (items != null) {
            qnsNo = 0;
            String qns = items.get(qnsNo).getQns().toString().trim();
            String op1 = items.get(qnsNo).getOp1().toString().trim();
            String op2 = items.get(qnsNo).getOp2().toString().trim();
            String op3 = items.get(qnsNo).getOp3().toString().trim();
            String op4 = items.get(qnsNo).getOp4().toString().trim();
            String op5 = items.get(qnsNo).getOp5().toString().trim();
            String r_ans = items.get(qnsNo).getRingtans().toString().trim();
            if (r_ans.equals(op1)) {
                rightAns = "1";
            } else if (r_ans.equals(op2)) {
                rightAns = "2";
            } else if (r_ans.equals(op3)) {
                rightAns = "3";
            } else if (r_ans.equals(op4)) {
                rightAns = "4";
            }
            click = 0;
            msgQnsPaperQns.setText(qns);
            msgQnsPaperOp1.setText(op1);
            msgQnsPaperOp2.setText(op2);
            msgQnsPaperOp3.setText(op3);
            msgQnsPaperOp4.setText(op4);
            msgQnsPaperOp5.setText(op5);
            qnsNo = qnsNo + 1;
            msgQnsPaperNumber.setText(qnsNo + "/" + totalqns);
        }
    }
//    private void fetchTestFromServer() {
//        myProgressDialog = MyProgressDialog.show(Selection_Exam_Activity.this, "", "", true, false, null);
//        String url = Utills.BaseUrl + "exam.php";
//        StringRequest list = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//                myProgressDialog.dismiss();
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    //Toast.makeText(Selection_Exam_Activity.this, ""+response, Toast.LENGTH_SHORT).show();
//                    if (obj.getString("status").equals("Fail")) {
//                        messgaeshow();
//                       // countDownTimer.cancel();
//                        //msgCountTime.setText("done!");
//                    } else {
//                        JSONObject data = new JSONObject();
//                        examtime = obj.getString("time");
//                        duration = Integer.parseInt(examtime);
//                        startCounter();
////                        Toast.makeText(Selection_Exam_Activity.this, "" + duration, Toast.LENGTH_SHORT).show();
//                        JSONArray array = obj.getJSONArray("all_quation");
//                        for (int i = 0; i < array.length(); i++) {
//                            data = array.getJSONObject(i);
//                            totalqns = array.length();
//                            item = new Self_Study_Gks_Object(data.getString("ex_id"),
//                                    data.getString("question"),
//                                    data.getString("url_img"),
//                                    data.getString("option_a"),
//                                    data.getString("option_b"),
//                                    data.getString("option_c"),
//                                    data.getString("option_d"),
//                                    "Not Attempt",
//                                    data.getString("answer"),
//                                    "",
//                                    false
//                            );
//                            items.add(item);
//                            //msgQnsPaperName.setText("" + exam_name + " Exam");
//                            txt_subject_named.startAnimation(AnimationUtils.loadAnimation(Selection_Exam_Activity.this, R.anim.zoom_animation));
//
//                            if (items != null) {
//                                qnsNo = 0;
//                                String qns = items.get(qnsNo).getQns().toString().trim();
//                                String op1 = items.get(qnsNo).getOp1().toString().trim();
//                                String op2 = items.get(qnsNo).getOp2().toString().trim();
//                                String op3 = items.get(qnsNo).getOp3().toString().trim();
//                                String op4 = items.get(qnsNo).getOp4().toString().trim();
//                                String op5 = items.get(qnsNo).getOp5().toString().trim();
//                                String r_ans = items.get(qnsNo).getRingtans().toString().trim();
//                                if (r_ans.equals(op1)) {
//                                    rightAns = "1";
//                                } else if (r_ans.equals(op2)) {
//                                    rightAns = "2";
//                                } else if (r_ans.equals(op3)) {
//                                    rightAns = "3";
//                                } else if (r_ans.equals(op4)) {
//                                    rightAns = "4";
//                                }
//                                click = 0;
//                                msgQnsPaperQns.setText(qns);
//                                msgQnsPaperOp1.setText(op1);
//                                msgQnsPaperOp2.setText(op2);
//                                msgQnsPaperOp3.setText(op3);
//                                msgQnsPaperOp4.setText(op4);
//                                msgQnsPaperOp5.setText(op5);
//                                qnsNo = qnsNo + 1;
//                                msgQnsPaperNumber.setText(qnsNo + "/" + totalqns);
//                            }
//                        }
//                    }
//                } catch (JSONException e) {
//                    //countDownTimer.cancel();
//                    // msgCountTime.setText("done!");
//                    Toast.makeText(Selection_Exam_Activity.this, "Oops something wrong", Toast.LENGTH_SHORT).show();
//                    myProgressDialog.dismiss();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.d("pvnError", "" + error);
//                Toast.makeText(Selection_Exam_Activity.this, "Server Down or Network problem", Toast.LENGTH_SHORT).show();
//               // countDownTimer.cancel();
//                msgCountTime.setText("done!");
//                myProgressDialog.dismiss();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//                Map<String, String> map = new HashMap<String, String>();
//                map.put("user_id", "" + Datastorage.ReadFromPreference("payment_id", Datastorage.STRING_KEY, Selection_Exam_Activity.this));
//                map.put("q_type", code_type);
//                map.put("q_mark", mark);
//                return map;
//            }
//        };
//        list.setShouldCache(false);
//        list.setRetryPolicy(new DefaultRetryPolicy(
//                60000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        AppController.getInstance().addToRequestQueue(list);
//        //AppController.getInstance().addToRequestQueue(list);
//    }

    private void messgaeshow() {

        dialog = new Dialog(new ContextThemeWrapper(activity, R.style.DialogSlideAnim));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.exam_details_dailog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow()
                .setLayout(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );
        TextView details_examname, details_ok;
        details_examname = (TextView) dialog.findViewById(R.id.details_examname);
        details_examname.setText(sub);
        details_ok = (TextView) dialog.findViewById(R.id.details_ok);
        details_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(activity, HomeActivity.class);
                startActivity(i);
                dialog.dismiss();
            }
        });
        dialog.show();
    }
//    private void UploadResultDetails(){
////        myProgressDialog = MyProgressDialog.show(Selection_Exam_Activity.this, "Count your percentage", "Please wait for few second we will give you result", true, false, null);
//        String url = Utills.BaseUrl + "upload_result.php";
//
//        StringRequest result = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
//            @Override
//            public void onResponse(String response) {
//
//               // myProgressDialog.dismiss();
//
//                // Toast.makeText(QnsActivity2.this, "***** "+response, Toast.LENGTH_SHORT).show();
//                try {
//                    JSONObject obj = new JSONObject(response);
//                    String rsp = obj.getString("code");
//
//                    if (rsp.equals("1") == true) {
//                        Toast.makeText(Selection_Exam_Activity.this, "Show Your Result in Report module", Toast.LENGTH_SHORT).show();
//                        // showResult();
//                    }
//                    if (rsp.equals("0")) {
//                        Toast.makeText(Selection_Exam_Activity.this, "Something wrong, please try again", Toast.LENGTH_SHORT).show();
//                    }
//                } catch (JSONException e) {
//                    Toast.makeText(Selection_Exam_Activity.this, "Oops something wrong, Please try again." + e, Toast.LENGTH_SHORT).show();
//                    //myProgressDialog.dismiss();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//
//                Toast.makeText(Selection_Exam_Activity.this, "Server Down or Network problem" + error, Toast.LENGTH_SHORT).show();
//                //myProgressDialog.dismiss();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() throws AuthFailureError {
//
//                Map<String, String> map = new HashMap<String, String>();
//
//                map.put("u_id", "" + Datastorage.ReadFromPreference("payment_id", Datastorage.STRING_KEY, Selection_Exam_Activity.this));
//                map.put("q_type", code_type);
//                map.put("total_marks", mark);
//                map.put("time", msgCountTime.getText().toString());
//                map.put("w_ans", "" + w_ans);
//                map.put("r_ans", "" + r_ans);
//                map.put("percentage", per);
//                if (per.length() <= 50) {
//                    status = "Good";
//                } else if (per.length() <= 75) {
//                    status = "Better";
//                } else if (per.length() <= 100) {
//                    status = "Best";
//                }
//                map.put("result_type", status);
//                return map;
//            }
//        };
//        result.setRetryPolicy(new DefaultRetryPolicy(
//                60000,
//                DefaultRetryPolicy.DEFAULT_MAX_RETRIES,
//                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
//
//        AppController.getInstance().addToRequestQueue(result);
//    }

    private void ShowResult(){
        dialog = new Dialog(new ContextThemeWrapper(activity, R.style.DialogSlideAnim));
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.showresult_dailog);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setGravity(Gravity.CENTER);
        dialog.getWindow()
                .setLayout(
                        ViewGroup.LayoutParams.FILL_PARENT,
                        ViewGroup.LayoutParams.WRAP_CONTENT
                );

       TextView show_examname, show_time, show_marks, show_wrongans, show_rightans, show_finalresult, show_ok;
       TextView solution_exam;
        show_examname = (TextView) dialog.findViewById(R.id.show_examname);
        show_time = (TextView) dialog.findViewById(R.id.show_time);
        show_marks = (TextView) dialog.findViewById(R.id.show_marks);
        show_wrongans = (TextView) dialog.findViewById(R.id.show_wrongans);
        show_rightans = (TextView) dialog.findViewById(R.id.show_rightans);
        show_finalresult = (TextView) dialog.findViewById(R.id.show_finalresult);
        show_examname.setText(sub);
        show_time.setText(msgCountTime.getText().toString());
        show_marks.setText(mark);
        show_wrongans.setText("" + w_ans);
        show_rightans.setText("" + r_ans);
        show_finalresult.setText("Result  = " + per + "(" + status + ")");
        show_ok = (TextView) dialog.findViewById(R.id.show_ok);

        show_ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                pushFragment(new ExamSubjectFragment(activity, fragmentManager, header, drawer), "Education", true);
            }
        });


        solution_exam = (TextView) dialog.findViewById(R.id.solution_exam);
        solution_exam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                pushFragment(new SolutionFragment(activity, fragmentManager, header, drawer), "Education", true);
//                Intent i = new Intent(Selection_Exam_Activity.this,SolutionActivity.class);
//                startActivity(i);
//                finish();

            }
        });
        dialog.show();
    }

    public void pushFragment(Fragment frag, String tag, Boolean addtobackstack) {
//            FragmentManager fm = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.rl_content, frag, tag);
        if (addtobackstack) {
            transaction.addToBackStack(null);
        }
        transaction.commit();
    }

    private void startCounter() {
        if (!msgCountTime.getText().toString().trim().equals("done!")) {
            countDownTimer = new CountDownTimer(duration * 60 * 1000, 1000) {
                public void onTick(long millisUntilFinished) {
                    msgCountTime.setText(String.format("%d:%d",
                            TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished),
                            TimeUnit.MILLISECONDS.toSeconds(millisUntilFinished) -
                                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(millisUntilFinished))));

                    if (msgCountTime.getText().toString().trim().equalsIgnoreCase("05:0")) {
                        //msgCountTime.setBackgroundResource(R.drawable.round_shape3);
                        msgCountTime.setTextColor(ContextCompat.getColor(activity, R.color.colorAccent));
                        vibrator.vibrate(2000);
                        relativeAlarmMsg.setVisibility(View.VISIBLE);
                        msgAlarmMsg.setText("5 Minutes Remaining!");
                        msgAlarmMsg.startAnimation(mAnim2);
                    } else if (msgCountTime.getText().toString().trim().equalsIgnoreCase("9:54")) {
                        //  msgCountTime.setBackgroundResource(R.drawable.round_shape4);
                        msgCountTime.setTextColor(ContextCompat.getColor(activity, R.color.colorPrimary));
                        vibrator.vibrate(2000);
                        relativeAlarmMsg.setVisibility(View.VISIBLE);
                        msgAlarmMsg.setText("10 Minutes Remaining!");
                        msgAlarmMsg.startAnimation(mAnim2);
                    } else if (msgCountTime.getText().toString().trim().equalsIgnoreCase("9:53")) {
                        relativeAlarmMsg.setVisibility(View.GONE);
                        msgAlarmMsg.clearAnimation();
                    } else if (msgCountTime.getText().toString().trim().equalsIgnoreCase("4:54")) {
                        relativeAlarmMsg.setVisibility(View.GONE);
                        msgAlarmMsg.clearAnimation();
                    }
                }
                public void onFinish() {
                    msgCountTime.setText("done!");
                    countDownTimer.cancel();
                    /*showResult();*/
                    //   uploadResult();
                }
            }.start();
        } else {
            msgCountTime.setText("done!");
            countDownTimer.cancel();
            vibrator.cancel();
        }
    }

//    @Override
//    public boolean onSupportNavigateUp() {
//        onBackPressed();
//        return true;
//    }
//    @Override
//    public void onBackPressed() {
//        super.onBackPressed();
//        finish();
//    }
}