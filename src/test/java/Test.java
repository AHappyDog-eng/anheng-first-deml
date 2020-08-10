import com.anheng.firstdemo.utils.IsNAN;
import com.anheng.firstdemo.utils.JwtUtils;

/**
 * @author WN
 * @date 2020/8/10 18:59
 */

public class Test {

    @org.junit.jupiter.api.Test
    public void test() {
        System.out.println("1");
    }

    @org.junit.jupiter.api.Test
    public void test1() {
        String wangning = JwtUtils.createJWT("ad");
        System.out.println(wangning);
        String s = JwtUtils.parseVer(wangning);
        System.out.println(s);
    }

    @org.junit.jupiter.api.Test
    public void test2(){
        String msg = "{\n" +
                "    \"userName\":\"WangNing\",\n" +
                "    \"userPassword\":\"123s.z|4567\"\n" +
                "}";
        boolean b = IsNAN.judgeMsg(msg);
        System.out.println(b);
    }
}
