import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TestMain {
    public static Map<String,Place> map = new HashMap<>();
    public static void main(String[] args) {
        File file = new File("D:/IDGenerate/asserts/ccode.json");
        StringBuilder stringBuilder = new StringBuilder();
        convertString(stringBuilder,file);
        JSONObject jsonObject = JSON.parseObject(stringBuilder.toString());
        JSONObject names = (JSONObject) jsonObject.get("NAME");
        JSONObject codes = (JSONObject) jsonObject.get("CODE");
        String current = "awa";
        for (int i = 0; i < codes.size(); i++) {
            String name = names.getString(String.valueOf(i));
            String code = codes.getString(String.valueOf(i));
            if (name.contains(current)){
                map.get(current).region.put(name,code);
            }else {
                map.put(name,new Place(code.substring(0,3),new HashMap<>()));
                current = name;
            }
            System.out.println(map);
        }
    }
    public static void convertString(StringBuilder stringBuilder,File file){
        BufferedInputStream bis = null;
        FileInputStream  fis= null;
        try {
            //第一步 通过文件路径来创建文件实例
            fis = new FileInputStream(file);
          /*把FileInputStream实例 传递到 BufferedInputStream
            目的是能快速读取文件           */
            bis = new BufferedInputStream(fis);
            /*available检查是不是读到了文件末尾 */
            while( bis.available() > 0 ){
                stringBuilder.append((char)bis.read());
            }
        }catch(FileNotFoundException fnfe) {
            System.out.println("文件不存在" + fnfe);
        }
        catch(IOException ioe) {
            System.out.println("I/O 错误: " + ioe);
        }
    }
}
