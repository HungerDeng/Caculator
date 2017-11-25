package dkm.gdut.com.caculator;


import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private static String text;
    Button factorial,power,sqrt,pi,empty;
    Button sin,leftKH,rightKH,e,delete;
    Button cos,seven,eight,nine,divide;
    Button tan,four,five,six,multi;
    Button ln,one,two,three,minus;
    Button log,zero,dot,equal,plus;

    TextView tvPast,tvCurrent,tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initEvent();
    }

    private void initEvent() {
        factorial.setOnClickListener(this);
        power.setOnClickListener(this);
        sqrt.setOnClickListener(this);
        pi.setOnClickListener(this);
        empty.setOnClickListener(this);

        sin.setOnClickListener(this);
        leftKH.setOnClickListener(this);
        rightKH.setOnClickListener(this);
        e.setOnClickListener(this);
        delete.setOnClickListener(this);

        cos.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        divide.setOnClickListener(this);

        tan.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        multi.setOnClickListener(this);

        ln.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        minus.setOnClickListener(this);

        log.setOnClickListener(this);
        zero.setOnClickListener(this);
        dot.setOnClickListener(this);
        equal.setOnClickListener(this);
        plus.setOnClickListener(this);
    }

    private void initView() {
        factorial= (Button) findViewById(R.id.factorial);
        power= (Button) findViewById(R.id.power);
        sqrt= (Button) findViewById(R.id.sqrt);
        pi= (Button) findViewById(R.id.pi);
        empty= (Button) findViewById(R.id.empty);

        sin= (Button) findViewById(R.id.sin);
        leftKH= (Button) findViewById(R.id.left_parentheses);
        rightKH= (Button) findViewById(R.id.right_parentheses);
        e= (Button) findViewById(R.id.e);
        delete= (Button) findViewById(R.id.delete);

        cos= (Button) findViewById(R.id.cos);
        seven= (Button) findViewById(R.id.seven);
        eight= (Button) findViewById(R.id.eight);
        nine= (Button) findViewById(R.id.nine);
        divide= (Button) findViewById(R.id.divide);

        tan= (Button) findViewById(R.id.tan);
        four= (Button) findViewById(R.id.four);
        five= (Button) findViewById(R.id.five);
        six= (Button) findViewById(R.id.six);
        multi= (Button) findViewById(R.id.multiple);

        ln= (Button) findViewById(R.id.ln);
        one= (Button) findViewById(R.id.one);
        two= (Button) findViewById(R.id.two);
        three= (Button) findViewById(R.id.three);
        minus= (Button) findViewById(R.id.minus);

        log= (Button) findViewById(R.id.log);
        zero= (Button) findViewById(R.id.zero);
        dot= (Button) findViewById(R.id.dot);
        equal= (Button) findViewById(R.id.equal);
        plus= (Button) findViewById(R.id.plus);

        tvPast= (TextView) findViewById(R.id.tvPast);
        tvCurrent= (TextView) findViewById(R.id.tvCurrent);
        tvResult= (TextView) findViewById(R.id.tvResult);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            /*first line*/
            case R.id.factorial:
                tvCurrent.setText(tvCurrent.getText()+"!");
                break;
            case R.id.power:
                tvCurrent.setText(tvCurrent.getText()+"^");
                break;
            case R.id.sqrt:
                setText("√");
                break;
            case R.id.pi:
                setText("π");
                break;
            case R.id.empty:
                tvCurrent.setText("0");
                break;

            /*second line*/
            case R.id.sin:
                setText("sin(");
                break;
            case R.id.left_parentheses:
                setText("(");
                break;
            case R.id.right_parentheses:
                setText(")");
                break;
            case R.id.e:
                setText("e");
                break;
            case R.id.delete:
                //回删
                text= (String) tvCurrent.getText();
                if (text.length()>1){
                    text=text.substring(0,text.length()-2);
                }else {
                    text="0";
                }

                tvCurrent.setText(text);
                break;

            /*third line*/
            case R.id.cos:
                setText("cos(");
                break;
            case R.id.seven:
                setText("7");
                break;
            case R.id.eight:
                setText("8");
                break;
            case R.id.nine:
                setText("9");
                break;
            case R.id.divide:
                setText("/");
                break;

            /*forth line*/
            case R.id.tan:
                setText("tan(");
                break;
            case R.id.four:
                setText("4");
                break;
            case R.id.five:
                setText("5");
                break;
            case R.id.six:
                setText("6");
                break;
            case R.id.multiple:
                setText("*");
                break;

            /*fifth line*/
            case R.id.ln:
                setText("ln(");
                break;
            case R.id.one:
                setText("1");
                break;
            case R.id.two:
                setText("2");
                break;
            case R.id.three:
                setText("3");
                break;
            case R.id.minus:
                setText("-");
                break;

            /*sixth line*/
            case R.id.log:
                setText("lg(");
                break;
            case R.id.zero:
                setText("0");
                break;
            case R.id.dot:
                setText(".");
                break;
            case R.id.equal:
                //计算得出结果
                setText("=");
                tvResult.setText(String.valueOf(CalculatorUtil.calculate((String) tvCurrent.getText())));
                break;
            case R.id.plus:
                setText("+");
                break;
            default:
                break;
        }
    }

    private void setText(String c){
        CharSequence current=tvCurrent.getText();
        if ("0".equals(current)){
            tvCurrent.setText(c);
        }else {
            tvCurrent.setText(tvCurrent.getText()+c);
        }
    }
}


