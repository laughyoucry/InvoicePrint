package com.lemon.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * 常用工具类-文件操作
 *
 * @author plin
 *
 */
public final class FileUtils {

    /**
     * 新建目录
     *
     * @param folderPath String 如 c:/fqf
     * @return boolean
     */
    public static void newFolder(String folderPath) {
        try {
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.mkdir();
            }
        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();
        }
    }

    /**
     * 新建文件
     *
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @param fileContent String 文件内容
     */
    public static void newFile(String filePathAndName, String fileContent) {

        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            if (!myFilePath.exists()) {
                myFilePath.createNewFile();
            }
            FileWriter resultFile = new FileWriter(myFilePath);
            PrintWriter myFile = new PrintWriter(resultFile);
            String strContent = fileContent;
            myFile.println(strContent);
            resultFile.close();

        } catch (Exception e) {
            System.out.println("新建目录操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 删除文件
     *
     * @param filePathAndName String 文件路径及名称 如c:/fqf.txt
     * @return boolean
     */
    public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myDelFile = new File(filePath);
            myDelFile.delete();

        } catch (Exception e) {
            System.out.println("删除文件操作出错");
            e.printStackTrace();

        }
    }

    /**
     * 删除文件夹
     *
     * @param folderPath String 文件夹路径及名称 如c:/fqf
     * @return boolean
     */
    public static void delFolder(String folderPath) {
        try {
            delAllFile(folderPath); // 删除完里面所有内容
            String filePath = folderPath;
            filePath = filePath.toString();
            File myFilePath = new File(filePath);
            myFilePath.delete(); // 删除空文件夹
        } catch (Exception e) {
            System.out.println("删除文件夹操作出错");
            e.printStackTrace();
        }

    }

    /**
     * 删除文件夹里面的所有文件
     *
     * @param path String 文件夹路径 如 c:/fqf
     */
    public static void delAllFile(String path) {
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        if (!file.isDirectory()) {
            return;
        }
        String[] tempList = file.list();
        File temp = null;
        for (int i = 0; i < tempList.length; i++) {
            if (path.endsWith(File.separator)) {
                temp = new File(path + tempList[i]);
            } else {
                temp = new File(path + File.separator + tempList[i]);
            }
            if (temp.isFile()) {
                temp.delete();
            }
            if (temp.isDirectory()) {
                delAllFile(path + "/" + tempList[i]);// 先删除文件夹里面的文件
                delFolder(path + "/" + tempList[i]);// 再删除空文件夹
            }
        }
    }

    /**
     * 复制单个文件
     *
     * @param oldPath String 原文件路径 如：c:/fqf.txt
     * @param newPath String 复制后路径 如：f:/fqf.txt
     * @return boolean
     */
    public static void copyFile(String oldPath, String newPath) {
        InputStream inStream = null;
        FileOutputStream fs = null;
        try {
            int byteread = 0;
            File oldfile = new File(oldPath);
            if (oldfile.exists()) { // 文件存在时
                inStream = new FileInputStream(oldPath); // 读入原文件
                fs = new FileOutputStream(newPath);
                byte[] buffer = new byte[1444];
                while ((byteread = inStream.read(buffer)) != -1) {
                    // bytesum += byteread; //字节数 文件大小
                    fs.write(buffer, 0, byteread);
                }
            }
        } catch (Exception e) {
            System.out.println("复制单个文件操作出错");
            e.printStackTrace();
        } finally {
            try {
                if (fs != null) {
                    fs.close();
                }
                if (inStream != null) {
                    inStream.close();
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    /**
     * 获得文件夹下文件名列表
     *
     * @param path c:/fqf
     * @return
     */
    public static String[] getFileList(String path) {
        File a = new File(path);
        String[] file = a.list();
        if (file == null) {
            return null;
        } else {
            return file;
        }
    }

    /**
     * 复制整个文件夹内容
     *
     * @param oldPath String 原文件路径 如：c:/fqf
     * @param newPath String 复制后路径 如：f:/fqf/ff
     * @return boolean
     */
    public static void copyFolder(String oldPath, String newPath) {

        try {
            (new File(newPath)).mkdirs(); // 如果文件夹不存在 则建立新文件夹
            File a = new File(oldPath);
            String[] file = a.list();
            File temp = null;
            for (int i = 0; i < file.length; i++) {
                if (oldPath.endsWith(File.separator)) {
                    temp = new File(oldPath + file[i]);
                } else {
                    temp = new File(oldPath + File.separator + file[i]);
                }

                if (temp.isFile()) {
                    FileInputStream input = new FileInputStream(temp);
                    FileOutputStream output = new FileOutputStream(newPath + "/" + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);
                    }
                    output.flush();
                    output.close();
                    input.close();
                }
                if (temp.isDirectory()) {// 如果是子文件夹
                    copyFolder(oldPath + "/" + file[i], newPath + "/" + file[i]);
                }
            }
        } catch (Exception e) {
            System.out.println("复制整个文件夹内容操作出错");
            e.printStackTrace();

        }

    }

    /**
     * 移动文件到指定目录
     *
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void moveFile(String oldPath, String newPath) {
        copyFile(oldPath, newPath);
        delFile(oldPath);
    }

    /**
     * 移动文件夹到指定目录
     *
     * @param oldPath String 如：c:/fqf.txt
     * @param newPath String 如：d:/fqf.txt
     */
    public static void moveFolder(String oldPath, String newPath) {

        String[] endWord = oldPath.split("/");
        String oldName;
        oldName = endWord[endWord.length - 1];
        if (oldName == null || oldName.equals("")) {
            oldName = endWord[endWord.length - 2];
        }
        copyFolder(oldPath, newPath + "/" + oldName);
        delFolder(oldPath);
    }

    /**
     * 写入二进制文件
     *
     * @param fileName 文件名
     * @param fileContent 文件内容
     * @param path 存放文件物理路径，路径名称结尾为\ 如：c:\
     * @throws IOException
     */
    public static void writeFile(String fileName, byte[] fileContent, String path) throws IOException {
        FileOutputStream fileoutputstream = new FileOutputStream(path + fileName);
        for (int i = 0; i < fileContent.length; i++) {
            fileoutputstream.write(fileContent[i]);
        }
        fileoutputstream.close();
    }

    /**
     * 写文本文件默认编码为GBK
     *
     * @param fileName
     * @param fileContent
     * @param path
     * @throws IOException
     */
    public static void writeTxtFile(String fileName, byte[] fileContent, String path) throws IOException {

        FileOutputStream fout = new FileOutputStream(path + fileName);
        OutputStreamWriter writer = new OutputStreamWriter(fout, "gb2312");
        //
        // System.out.print(new String(fileContent)+"\r\n");
        // System.out.print(new String(fileContent,"GBK")+"\r\n");
        // System.out.print(new String(fileContent,"gb2312")+"\r\n");
        // System.out.print(new String(fileContent,"UTF-8")+"\r\n");
        //
        // writer.write(new String(fileContent)+"\r\n");
        // writer.write(new String(fileContent,"GBK")+"\r\n");
        // writer.write(new String(fileContent,"gb2312")+"\r\n");
        // writer.write(new String(fileContent,"UTF-8")+"\r\n");
        //
        writer.write(new String(fileContent, "UTF-8"));

        writer.flush();
        writer.close();
        fout.close();

    }

    /*
     * 写文件，并删除老文件.
     */
    public static void writeTxtFile(String fileName, String content, String path) {
        File f = null;
        FileWriter fw = null;
        try {
            f = new File(path + fileName);
            if (f.exists()) {
                f.delete();
                f.createNewFile();
            }
            fw = new FileWriter(f, true);
            fw.write(content);
        } catch (Exception e) {
            try {
                fw.write("写文件触发异常:" + e.getMessage());
            } catch (IOException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        } finally {
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        }
    }

    /**
     * 按指定的格式写文件，并删除老文件
     *
     */
    public static void writeFile(String fullPath, String content, String encoding) {
        Writer writer = null;
        try {
            writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(fullPath), encoding));
            writer.write(content);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("读写流出错...");
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    System.out.println("流关闭出错...");
                }
            }
        }

    }

    /**
     * 根据文件路径得到文件内容
     *
     * @param file_name
     * @return
     */
    public static byte[] readFromFile(String file_name) {
        FileInputStream fin;
        byte[] buf = null;
        try {
            fin = new FileInputStream(file_name);
            buf = new byte[fin.available()];

            fin.read(buf);
            fin.close();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return buf;
    }

    /**
     * 根据时间生成一个文件名称
     *
     * @return
     */
    public static String getFileName() {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        // 生成临时文件名
        return sdf.format(new Date()) + String.valueOf(new Random().nextInt(9999));
    }

    /**
     * 返回文件后缀名
     *
     * @param fileName
     * @return
     */
    public static String getExtension(String fileName) {
        int pos;
        pos = fileName.lastIndexOf(".");
        return fileName.substring(pos + 1).toUpperCase();
    }

    /**
     * 将文件转为字节数组
     *
     * @param filePath
     * @return
     */
    public static byte[] fileToByte(String filePath) {
        BufferedInputStream bis = null;
        byte[] arrByte = null;
        try {
            File file = new File(filePath);
            arrByte = new byte[(int) file.length()];
            bis = new BufferedInputStream(new FileInputStream(file));
            bis.read(arrByte);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bis != null) {
                try {
                    bis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return arrByte;
    }

    /**
     * 二进制写文件
     * 
     * @param filePath
     * @param arrByte
     */
    public static void byteToFile(String filePath, byte[] arrByte) {
        File file = new File(filePath);
        BufferedOutputStream bos = null;
        try {
            File parentFile = file.getParentFile();
            if (!parentFile.exists()) {
                parentFile.mkdirs();
            }
            if (!file.exists()) {
                file.createNewFile();
            }
            bos = new BufferedOutputStream(new FileOutputStream(file));
            bos.write(arrByte);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * 当前程序的相对路径
     * 
     * @return
     */
    public static String getRelationPath(String pathStr) {
        String path = FileUtils.class.getClass().getResource("/").getPath();
        String localFilePath = path.replace("lib/httpclient/", pathStr);
        return localFilePath;
    }

    public static String getRootPath() {
        String rootPath = System.getProperty("user.dir");
        System.out.println("---->" + rootPath);
        return rootPath;
    }

    /**
     * 创建文件夹
     *
     * @param fileDir
     */
    public static void createFileDir(String fileDir) {
        File newFileDir = new File(fileDir);
        if (!newFileDir.exists()) {
            newFileDir.mkdir();
        }
    }

    /**
     * 获取文件夹下所有文件
     *
     * @param filePath
     */
    public static File[] listFiles(String filePath) {
        File file = new File(filePath);
        File[] arrFile = file.listFiles();
        return arrFile;
    }

    /**
     * 获取路径下的所有文件夹
     * 
     * @param filePath
     * @return
     */
    public static List<File> listDirs(String filePath) {
        List<File> rs = new ArrayList<File>();
        File[] files = listFiles(filePath);
        for (File file : files) {
            if (file.isDirectory()) {
                rs.add(file);
            }
        }
        return rs;
    }

    /**
     * 自定义写入出错日志，文件以字期格式存放到根目录log目录下
     *
     */
    /*
     * public static void writeLog(String str) { writeLog(str,Config.getRootPath() + "log/"); } public static void
     * writeLog(String str,String path,String fileName) { File f = null; FileWriter fw = null; try { java.io.File
     * myFilePath = new java.io.File(path); if (!myFilePath.exists()) { myFilePath.mkdir(); } f = new File(path +
     * fileName); if (!f.exists()) { f.createNewFile(); } fw = new FileWriter(f, true);
     * fw.write(DateUtils.getCurrDateTimeStr()+ "------------------------------------------------\r\n"); fw.write(str);
     * fw.write("\r\n-------------------------------------------------------------------\r\n"); } catch (Exception e) {
     * try { fw.write("writeLog写日志文件触发异常:" + e.getMessage()); } catch (IOException e1) { // TODO Auto-generated catch
     * block e1.printStackTrace(); } } finally { if (fw != null) { try { fw.close(); } catch (IOException e1) { // TODO
     * Auto-generated catch block e1.printStackTrace(); } } } } public static void writeLog(String str,String path) {
     * SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd"); String fileName = sdf.format(new Date()) + ".txt";
     * writeLog(str,path,fileName); }
     */
    /**
     * 获取目录下总文件大小
     *
     * @param file
     * @return
     */
    public static long getDirSize(File file) {
        if (file.exists()) {
            if (file.isDirectory()) {
                long size = 0;
                // 是目录递归计算文件总大小
                File[] children = file.listFiles();
                for (File f : children) {
                    size += getDirSize(f);
                }
                return size;
            } else {// 文件，直接返回
                return file.length();
            }
        } else {
            // CmnLogger.log("--->文件不存在，请检查路径！<---", BaseData.getLogPath());
            return 0;
        }
    }

    public static void main(String[] args) {
        // byte[] arrByte = fileToByte("D:\\datafile\\unread\\TRAN110001_1.data");
        // byteToFile("d:\\hello\\a.txt", arrByte);
        // long size = getDirSize(new File("D:\\jdbcdatafile\\2016\\02\\29\\produce"));
        // System.out.println(size);
        // listDirs("d://");
        FileUtils.getRootPath();
    }
}
