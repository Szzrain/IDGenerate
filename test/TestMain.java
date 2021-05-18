
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;

public class TestMain {
    public static void main(String[] args, BufferedInputStream bis1) {
        File file = new File("D:/IDGenerate/asserts/ccode.json");
        BufferedInputStream bis = bis1;
        FileInputStream  fis= null;
        StringBuilder stringBuilder = new StringBuilder();
        try {
            //第一步 通过文件路径来创建文件实例
            fis = new FileInputStream(file);
          /*把FileInputStream实例 传递到 BufferedInputStream
            目的是能快速读取文件
           */
            bis = new BufferedInputStream(fis);
            /*available检查是不是读到了文件末尾 */
            while( bis.available() > 0 ){
//                System.out.print((char)bis.read());
                stringBuilder.append((char)bis.read());
            }
        }catch(FileNotFoundException fnfe) {
            System.out.println("文件不存在" + fnfe);
        }
        catch(IOException ioe) {
            System.out.println("I/O 错误: " + ioe);
        }
        JSONObject jsonObject = JSON.parseObject(stringBuilder.toString());
        System.out.println(jsonObject.get("CODE"));

    }
}
