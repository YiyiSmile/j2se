package collection;

import java.io.*;
import java.util.*;

/**
 * @Author Tom
 * @Date 2020/12/5 20:48
 * @Version 1.0
 * @Description
 * 从文件中中读取学生成绩表，然后求每个学生成绩的平均值和总分数。每个学生的考试科目不相同。
 *
 * 采用的用来存储数据的数据结构是HashMap<String,HashMap<String,Integer>>。外层HashMap的key是学生姓名，value是另外一个HashMap，用来保存成绩。
 *
 * 这个用来保存成绩的HashMap，key是考试科目名称，value是考试成绩。
 *
 *  * 测试数据：
 *  * 1 tom 语文 80
 *  * 1 tom 物理 80
 *  * 2 tony 语文 70
 *  * 2 tony 英语 70
 *  * 3 alex 语文 90
 *  * 3 alex 物理 90
 *  * 1 tom 数学 83
 *  *
 *  * 总分数：563
 *  * 平均分：80.428
 *  *
 *  * tom平均分： 81 总分：243
 *  * tony平均分：70 总分：140
 *  * alex 平均分:90 总分:180
 */
public class Interview1 {
    public static void main(String[] args) {
        File file = new File("D:\\Java\\IdeaProjects\\j2se\\resources\\examScore.txt");
        FileReader fr = null;
        BufferedReader br = null;
        try {
            fr = new FileReader(file);
            br = new BufferedReader(fr);
            String s ;
            String[] split;
            HashMap<String, HashMap<String, Integer>> map = new HashMap<>();

            while((s = br.readLine()) != null && s != ""){
                split = s.split("\\s+");
                HashMap<String, Integer> scoreMap = map.get(split[1]);
                if(null == scoreMap){
                    HashMap<String, Integer> map1 = new HashMap<String,Integer>();
                    map1.put(split[2],Integer.valueOf(split[3]));
                    map.put(split[1],map1);


                }else{
                    scoreMap.put(split[2],Integer.parseInt(split[3]));
                }
            }
            for (Map.Entry<String, HashMap<String, Integer>> mapEntry : map.entrySet()) {
                int sum = new ArrayList<Integer>(mapEntry.getValue().values()).stream().mapToInt(n -> n).sum();
//                OptionalDouble average = mapEntry.getValue().values().stream().mapToInt(n -> n).average();
                //another way
                OptionalDouble average = mapEntry.getValue().values().stream().mapToInt(Integer::intValue).average();
                System.out.printf("Student %s: 总成绩-> %d 平均分->%f \n",mapEntry.getKey(),sum,average.getAsDouble());
            }

            //求所有学生的总分数和总平均分

            int sum = map.values()   //Collection<HashMap<String,Integer>>
                    .stream() //Stream<HashMap<String,Integer>>
                    .map(scoreMap -> scoreMap.values()) //Stream<Collection<Integer>>
                    .flatMap(collection -> collection.stream())
                    .mapToInt(Integer::intValue)
                    .sum();
            OptionalDouble average = map.values()   //Collection<HashMap<String,Integer>>
                    .stream() //Stream<HashMap<String,Integer>>
                    .map(scoreMap -> scoreMap.values()) //Stream<Collection<Integer>>
                    .flatMap(collection -> collection.stream())
                    .mapToInt(Integer::intValue)
                    .average();
            System.out.println("所有学生总分数是：" + sum);
            System.out.println("所有学生平均分是：" + average);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(null != fr){
                try {
                    fr.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(null != br){
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
