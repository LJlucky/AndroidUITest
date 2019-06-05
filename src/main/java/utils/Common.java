package utils;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Common {

    //重命名文件
    public static void renameFile(String path, String oldName, String newName) {
        if (!oldName.equals(newName)) {
            File oldFile = new File(path + "/" + oldName);
            File newFile = new File(path + "/" + newName);
            if (newFile.exists()) {
                System.out.println(newName + "已经存在");
            } else {
                oldFile.renameTo(newFile);
            }

        }
    }

    //拷贝文件
    public static boolean copyFile(String file1, String file2) {

        File in = new File(file1);
        File out = new File(file2);
        if (!in.exists()) {
            System.out.println(in.getAbsolutePath() + "源文件路径错误！！！");
            return false;
        }
        /*else{
            System.out.println("源文件路径"+in.getAbsolutePath());
            System.out.println("目标路径"+out.getAbsolutePath());
        }*/
        if (!out.exists()) {
            out.mkdirs();
        }
        //File[] file = in.listFiles();
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            fis = new FileInputStream(in);
            fos = new FileOutputStream(new File(file2 + "\\" + in.getName()));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        int c;
        byte[] b = new byte[1024 * 5];

        try {
            while ((c = fis.read(b)) != -1) {
                fos.write(b, 0, c);
            }

            fis.close();
            fos.flush();
            fos.close();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return false;

    }


    /**
     * 得到现在时间
     *
     * @return 字符串 yyyyMMdd HHmmss
     */
    private static String getStringToday() {
        Date currentTime = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMddHHmmss");
        String dateString = formatter.format(currentTime);
        return dateString;
    }

    /**
     * 复制测试报告并重命名
     */
    public static void copyReport() {
        String objPath = System.getProperty("user.dir");
        String oldPath = objPath + "\\report.html";
        String newPath1 = objPath +  "\\src\\main\\java\\Report";
        String newPath2 = objPath +  "\\src\\main\\java\\Report";
        String oldName = "report.html";
        String newName = "Android自动化_" + getStringToday() + "_Report.html";
        copyFile(oldPath, newPath1);
        renameFile(newPath2, oldName, newName);
    }

}
