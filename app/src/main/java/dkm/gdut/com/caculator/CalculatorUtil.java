package dkm.gdut.com.caculator;

import android.util.Log;

import java.util.Stack;

/**
 * 计算器工具类
 */
public class CalculatorUtil {
    private static final String TAG = "CalculatorUtil";

    /** 数字栈：用于存储表达式中的各个数字 */
    private static Stack<Double> numStack = null;
    /** 符号栈：用于存储运算符和括号 */
    private static Stack<Character> operatorStack = null;

    /**
     * 解析并计算四则运算表达式(含括号)，返回计算结果
     *
     * @param numStr
     *            算术表达式(含括号)
     */
    public static double calculate(String numStr) {


        // 检查表达式是否合法
        if (!matchBrackets(numStr)) {
            Log.d(TAG, "错误：算术表达式有误！");
            return 0D;
        }

        numStr=formatStr(numStr);

        // 初始化栈
        numStack = new Stack<>();
        operatorStack = new Stack<>();
        // 用于缓存数字，因为数字可能是多位的
        StringBuffer temp = new StringBuffer();
        // 从表达式的第一个字符开始处理
        for (int i = 0; i < numStr.length(); i++) {
            char ch = numStr.charAt(i); // 获取一个字符
            if (Character.isDigit(ch) || ch == '.'||ch=='π'||ch=='e') { // 若当前字符是数字
                temp.append(ch); // 加入到数字缓存中
            } else { // 非数字的情况
                String tempStr = temp.toString(); // 将数字缓存转为字符串
                if (!tempStr.isEmpty()) {

                    Double num ;
                    if ("π".equals(tempStr)){
                        num=Math.PI;
                    }else if ("e".equals(tempStr)){
                       num=Math.E;
                    }else {
                        num= Double.parseDouble(tempStr); // 将数字字符串转为长整型数
                    }
                    numStack.push(num); // 将数字压栈
                    temp = new StringBuffer(); // 重置数字缓存
                }
                // 判断运算符的优先级，若当前优先级低于栈顶的优先级，则先把计算前面计算出来
                while (!compareLevel(ch) && !operatorStack.empty()) {
                    double b=0;
                    double a=0;
                    // 取出运算符进行相应运算，并把结果压栈进行下一次运算
                    char top=operatorStack.pop();
                    if(top=='*'||top=='/'||top=='+'||top=='-'||top=='^'){
                        b = numStack.pop(); // 出栈，取出数字，后进先出
                        a = numStack.pop();
                    }else {
                        a=numStack.pop();
                        //System.out.println(a);
                    }
                    switch ( top) {
                        case '+':
                            numStack.push(a + b);
                            break;
                        case '-':
                            numStack.push(a - b);
                            break;
                        case '*':
                            numStack.push(a * b);
                            break;
                        case '/':
                            numStack.push(a / b);
                            break;
                        case '^':
                            numStack.push(nFangFast(a,b));
                            break;
                        case 's':
                            //System.out.println(a);
                            //System.out.println(Math.sin(a));
                            numStack.push( Math.sin(a));
                            break;
                        case 'c':
                            numStack.push(Math.cos(a));
                            break;
                        case 't':
                            numStack.push(Math.tan(a));
                            break;
                        case 'n':
                            numStack.push(Math.log(a));
                            break;
                        case 'g':
                            numStack.push(Math.log10(a));
                            break;
                        case '!':
                            if (a==0){
                                numStack.push(1D);
                            }else {
                                double sum=1;
                                for (int j=1;j<=a;j++){
                                    sum*=j;
                                }
                                numStack.push(sum);
                            }
                            break;
                        case '√':
                            numStack.push(Math.sqrt(a));
                            break;

                        default:
                            break;
                    }
                } // while循环结束
                if (ch != '=') {
                    operatorStack.push(ch); // 符号入栈
                    if (ch == ')') { // 去括号
                        operatorStack.pop();
                        operatorStack.pop();
                    }
                }
            }
        } // for end

        return numStack.pop(); // 返回计算结果
    }

    private static String formatStr( String numStr) {
        // 如果算术表达式尾部没有‘=’号，则在尾部添加‘=’，表示结束符
        if (numStr.length() > 1 && !"=".equals(numStr.charAt(numStr.length() - 1) + "")) {
            numStr += "=";
        }
        numStr=numStr.replaceAll("sin","s");
        numStr=numStr.replaceAll("cos","c");
        numStr=numStr.replaceAll("tan","t");
        numStr=numStr.replaceAll("ln","n");
        numStr=numStr.replaceAll("lg","g");

        //System.out.println(numStr);

        return numStr;
    }


    /**
     * 匹配括号
     */
    private static boolean matchBrackets(String numStr) {
        if (numStr == null || numStr.isEmpty()) // 表达式不能为空
            return false;
        Stack<Character> stack = new Stack<>(); // 用来保存括号，检查左右括号是否匹配

        for (int i = 0; i < numStr.length(); i++) {
            char n = numStr.charAt(i);
            // 将左括号压栈，用来给后面的右括号进行匹配
            if ("(".equals(n + "")) {
                stack.push(n);
            }
            if (")".equals(n + "")) { // 匹配括号
                if (stack.isEmpty() || !"(".equals((char) stack.pop() + "")) // 括号是否匹配
                    return false;
            }
        }
        // 可能会有缺少右括号的情况
        return stack.isEmpty();
    }

    /**
     * 比较优先级：如果当前运算符比栈顶元素运算符优先级高则返回true，否则返回false
     */
    private static boolean compareLevel(char symbol) {
        if (operatorStack.empty()) { // 空栈返回ture
            return true;
        }

        // 符号优先级说明（从高到低）:
        // 第1级: (
        // 第2级: * /
        // 第3级: + -
        // 第4级: )

        char top = operatorStack.peek(); // 查看堆栈顶部的对象，注意不是出栈
        if (top == '(') {
            return true;
        }
        // 比较优先级
        switch (symbol) {
            case '(': // 优先级最高
                return true;
            case 's':
            case 'c':
            case 't':
            case 'n':
            case 'g':
            case '!':
            case '^':
            case '√':
                return top == '*' || top == '/' || top == '+' || top == '-';
            case '*': {
                // 优先级比+和-高
                return top == '+' || top == '-';
            }
            case '/': {
                // 优先级比+和-高
                return top == '+' || top == '-';
            }
            case '+':
                return false;
            case '-':
                return false;
            case ')': // 优先级最低
                return false;
            case '=': // 结束符
                return false;
            default:
                break;
        }
        return true;
    }

    //快速求a的b次幂
    private static double nFangFast(double a,double b) {

        double sum=1;
        for (int i=1;i<=b;i++){
            sum*=a;
        }
        return sum;
    }
}