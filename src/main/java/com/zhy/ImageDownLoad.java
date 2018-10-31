package com.zhy;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class ImageDownLoad {
    //链接url下载图片
    public  static void downloadPicture(String name, String img) {
        String imageName =  "F:\\img124\\" + name + ".jpg";
        URL url = null;
        InputStream inputStream = null;
        RandomAccessFile randomAccessFile = null;
        int imageNumber = 0;

        try {
            url = new URL(img);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/58.0.3029.19 Safari/537.36");
            connection.setConnectTimeout(5 * 1000);
            int responseCode = connection.getResponseCode();
            if (responseCode >= 200 && responseCode < 300) {
                inputStream = connection.getInputStream();
                int len = 0;
                byte[] data = new byte[4096];
                //用于保存当前进度（具体进度）
                int progress = 0;
                //获取文件长度
                int maxProgress = connection.getContentLength();
                File file = new File(imageName);
                randomAccessFile = new RandomAccessFile(file, "rwd");
                //设置文件大小
                randomAccessFile.setLength(maxProgress);
                //将文件大小分成100分，每一分的大小为unit
                int unit = maxProgress / 100;
                //用于保存当前进度(1~100%)
                int unitProgress = 0;
                System.out.println(imageName);
                while (-1 != (len = inputStream.read(data))) {
                    randomAccessFile.write(data, 0, len);
                    progress += len;//保存当前具体进度
                    int temp = progress / unit; //计算当前百分比进度
                    if (temp >= 1 && temp > unitProgress) {//如果下载过程出现百分比变化
                        unitProgress = temp;//保存当前百分比
                        System.out.println(name + "正在下载中..." + unitProgress + "%");
                    }
                }
                inputStream.close();
                System.out.println("下载完成...");
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (null != randomAccessFile) {
                try {
                    randomAccessFile.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
