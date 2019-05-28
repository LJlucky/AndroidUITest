package utils;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.ho.yaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ConfigUtil {
    private final static Logger Log = Logger.getLogger(ConfigUtil.class);
    public static Map<String, Map<String, String>> test1;
    public static Map<String, String> m;
    static File ymlFile;

    //读取YAML文件
    public static void LoadYaml(String yamlName,String path,String msg) throws FileNotFoundException {
        Log.info("开始加载" + yamlName + msg);
        Yaml yaml = new Yaml();
        String path1 = System.getProperty("user.dir") + path + yamlName;
        ymlFile = new File(path1);
    }

    //读取YAML文件
    public static Map<String, Map<String, String>> getYaml() throws FileNotFoundException {
        test1 = Yaml.loadType(new FileInputStream(ymlFile), HashMap.class);
        if (test1 != null) {
            Log.info("\t-->元素加载成功");
        }
        return test1;
    }



    public static void LoadLogProperties() {
        try {
            Properties props = new Properties();//创建一个系统参数对象
            FileInputStream fis1 = new FileInputStream(System.getProperty("user.dir") + "\\src\\main\\java\\config\\log4j.properties");
            props.load(fis1);//将配置加载到系统参数对象中
            fis1.close();
            PropertyConfigurator.configure(props);//装入log4j配置信息
            Log.info("---》加载log4j.prpperties配置文件成功");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }




}
