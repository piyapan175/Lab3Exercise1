package th.ac.tu.siit.its333.lab3exercise1;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.*;
import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {

    int cr = 0;         // Credits
    double gp = 0.0;    // Grade points
    double gpa = 0.0;   // Grade point average

    List<String> listCodes;
    List<Integer> listCredits;
    List<String> listGrades;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listCodes = new ArrayList<String>();
        listCredits = new ArrayList<Integer>();
        listGrades = new ArrayList<String>();

        calculate();

        //Use listCodes.add("ITS333"); to add a new course code
        //Use listCodes.size() to refer to the number of courses in the list
    }
    public void buttonClicked(View v) {
        //Start Activity//
        int id = v.getId();

            switch(id)
            {
                case R.id.button4:
                    Intent j = new Intent(this,CourseListActivity.class);


                    String s = "List of Courses";
                    for (int i = 0 ; i < listCodes.size(); i++)
                    {
                        s += "\n";

                        s += listCodes.get(i) + " ";
                        s += "(" + listCredits.get(i).toString() + " credits)";
                        s += " = " + listGrades.get(i);

                    }

                    j.putExtra("list_of_code",s);




                    startActivity(j);
                    break;
                case R.id.button2:

                    Intent i = new Intent(this,CourseActivity.class);
                    startActivityForResult(i, 23);

                    break;
                case R.id.button:

                     cr = 0;         // Credits
                     gp = 0.0;    // Grade points
                     gpa = 0.0;   // Grade point average

                    listCodes = new ArrayList<String>();
                    listCredits = new ArrayList<Integer>();
                    listGrades = new ArrayList<String>();

                    calculate();

                    break;
            }




    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // Values from child activity
        //TextView CodeResult = (TextView)findViewById(R.id.etCode);
        //TextView CrResult = (TextView)findViewById(R.id.etCR);

        if(requestCode == 23)
        {
            if(resultCode == RESULT_OK)
            {
                String resCode = data.getStringExtra("toAct01");
                int resCredit = data.getIntExtra("toAct02", 0);
                String resGrade = data.getStringExtra("toAct03");

                listCodes.add(resCode);
                listGrades.add(resGrade);
                listCredits.add(resCredit);

                calculate();
            }
            else if (resultCode == RESULT_CANCELED)
            {


            }
        }
    }

    public void calculate()
    {
        double GradePoint = 0 ;
        int i;
        double GPA = 0.0 ;
        int credit = 0 ;
        for(i=0;i<listCredits.size();i++)
        {

            String resGrade = listGrades.get(i);
            if(resGrade.equals("A"))
            {
                GradePoint += 4.0 * listCredits.get(i) ;
            }
            else if(resGrade.equals("B+"))
            {
                GradePoint += 3.5 * listCredits.get(i);
            }
            else if(resGrade.equals("B"))
            {
                GradePoint += 3.0 * listCredits.get(i);
            }
            else if(resGrade.equals("C+"))
            {
                GradePoint += 2.5 * listCredits.get(i);
            }
            else if(resGrade.equals("C"))
            {
                GradePoint += 2.0 * listCredits.get(i);
            }
            else if(resGrade.equals("D+"))
            {
                GradePoint += 1.5 * listCredits.get(i);
            }
            else if(resGrade.equals("D"))
            {
                GradePoint += 1.0 * listCredits.get(i);
            }
            else if(resGrade.equals("F"))
            {
                GradePoint += 0.0 * listCredits.get(i);
            }
            credit += listCredits.get(i);
        }

        GPA = GradePoint/credit;

        TextView tvGP = (TextView)findViewById(R.id.tvGP);
        TextView tvCr = (TextView)findViewById(R.id.tvCR);

        String total = String.valueOf(GPA);

        tvGP.setText(GradePoint+"");
        tvCr.setText(credit+"");

        gpa = GPA;

        TextView tvGPA = (TextView)findViewById(R.id.tvGPA);
        tvGPA.setText(Double.toString(gpa));


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
