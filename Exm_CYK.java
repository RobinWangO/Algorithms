import java.util.ArrayList;
import java.util.List;

public class Exm_CYK {

    private String tmpResult;               //用于存放每次计算的值
    private String fResult = "";            //用于存放最终的值
    public Exm_CYK(){                       //构造函数
    }

    public void cyk(String str){
        char[] arr = new char[str.length()];
        for(int i = 0; i <str.length(); i++){                               // 将输入的字符串转化成字符数组
            arr[i] = str.charAt( i );
        }
        String[][] result = new String[str.length()][str.length()];
        for(int i = 0; i < arr.length; i++){                                // 先把最底层的一行计算出来
            String tmp = "" + arr[i];
            result[0][i] = relate( tmp );
            System.out.print( "\t result[0][" + i + "]: " + result[0][i] );
            //leftStr = "";
        }
        //result[j][i]
        for(int j = 1; j < arr.length; j++){                                // 计算之后的每一项的值
            System.out.print("\n");
            for(int i = 0; i < arr.length - j; i++){
                for(int k = 0; k < j; k++){
                    tmpResult = calculate( result[k][i], result[j-k-1][k+i+1] );
                    fResult += tmpResult;
                }
                if(fResult.equals( "" )){                                   //为空则将其置“--”
                    result[j][i] = "--";
                }else{
                    result[j][i] = fResult;
                }

                System.out.print( "\t result["+ j + "][" + i + "]: " + removeDuplicate( result[j][i] ) );
                fResult = "";
            }
        }
    }

/*  以下面的文法为例写 Rule
 *   S -> AB|BC
 *   A -> BA|a
 *   B -> CC|b
 *   C -> AB|a
 *
 * */

    public String relate(String s){                                         // 文法的规则
        String leftStr = "";
        if(s.equals( "AB" ) || s.equals( "BC" )){
            leftStr += "S";
        }
        if(s.equals( "BA" ) || s.equals( "a" )){
            leftStr += "A";
        }
        if(s.equals( "CC" ) || s.equals( "b" )){
            leftStr += "B";
        }
        if(s.equals( "AB" ) || s.equals( "a" )){
            leftStr += "C";
        }
        return leftStr;
    }

    public String calculate(String a, String b){                            // 计算两个集合的笛卡尔积
        String temp = "";
        for(int i = 0; i < a.length(); i++){
            for(int j = 0; j < b.length(); j++){
                String combi = "" + a.charAt( i ) + b.charAt( j );
                temp += relate( combi );
            }
        }
        return temp;
    }

    public String removeDuplicate(String s){                                // 移除多余的字符
        List<String> data = new ArrayList<String>();
        for (int i = 0; i < s.length(); i++) {
            String tmp = s.substring(i, i + 1);
            if (!data.contains(tmp)) {
                data.add(tmp);
            }
        }
        String re = "";
        for(String tmp : data){
            re += tmp;
        }
        return re;
    }

    public static void main(String[] args){                                 // 主函数
        Exm_CYK test = new Exm_CYK();
        test.cyk( "baababa" );
    }
}
