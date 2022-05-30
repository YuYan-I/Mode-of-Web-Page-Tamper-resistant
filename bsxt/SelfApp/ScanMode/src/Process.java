import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * 算出进程列表，再统计出相应进程个数
 * @author syp
 *
 */
public class Process{
    /*
     * 统计进程数量，适应与windows和linux
     * @Author syp
     * @param进程名称
     * 返回值为进程个数
     */
    public static int GetprocessNums(String processName) {

        Runtime runtime = Runtime.getRuntime();
        List<String> tasklist = new ArrayList<>();
        java.lang.Process process=null;
        int count=0; //统计进程数
        try {
            /*
             * 自适应执行查询进程列表命令
             */
                process = runtime.exec("tasklist");
                BufferedReader br = new BufferedReader(new InputStreamReader(process.getInputStream(),"gbk"));
                String s = "";
                while ((s = br.readLine()) != null) {
                    if ("".equals(s)) {
                        continue;
                    }
                    tasklist.add(s+" ");
                }

                // 获取每列最长的长度
                String maxRow = tasklist.get(1) + "";
                String[] maxCol = maxRow.split(" ");
                // 定义映像名称数组
                String[] taskName = new String[tasklist.size()];
                // 定义 PID数组
                String[] taskPid = new String[tasklist.size()];
                // 会话名数组
                String[] taskSessionName = new String[tasklist.size()];
                // 会话#数组
                String[] taskSession = new String[tasklist.size()];
                // 内存使用 数组
                String[] taskNec = new String[tasklist.size()];
                for (int i = 0; i < tasklist.size(); i++) {
                    String data = tasklist.get(i) + "";
                    for (int j = 0; j < maxCol.length; j++) {
                        switch (j) {
                            case 0:
                                //string str1 = convertencodingformat(str, "utf-8", "iso-8859-1");
                                taskName[i]=data.substring(0, maxCol[j].length()+1);
                                data=data.substring(maxCol[j].length()+1);
                                break;
                            case 1:
                                taskPid[i]=data.substring(0, maxCol[j].length()+1);
                                data=data.substring(maxCol[j].length()+1);
                                break;
                            case 2:
                                taskSessionName[i]=data.substring(0, maxCol[j].length()+1);
                                data=data.substring(maxCol[j].length()+1);
                                break;
                            case 3:
                                taskSession[i]=data.substring(0, maxCol[j].length()+1);
                                data=data.substring(maxCol[j].length()+1);
                                break;
                            case 4:
                                taskNec[i]=data;
                                break;
                        }
                    }
                }

                for (int i = 0; i < taskNec.length; i++) {
                    //打印进程列表
                    System.out.println(taskName[i]+" "+taskPid[i]+" "+taskSessionName[i]+" "+taskSession[i]+" "+taskNec[i]);
                    if(taskName[i].contains(processName)){
                        count++;//用于进程计数
                    }
                }



        } catch (Exception e) {
            e.printStackTrace();
        }
        return tasklist.size();

    }


    public static void main(String[] args) throws InterruptedException {
        int count=GetprocessNums("firefox.exe");
        System.out.println(count);
    }

}