package multi_thread.basic.basic.thread_state;

import java.util.concurrent.ForkJoinPool;

/**
 * @Author Tom
 * @Date 2020/6/11 18:27
 * @Version 1.0
 * @Description
 */
public class Test {
    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    public static void main(String[] args) {
        for (int i = 0; i <3 ; i++) {
            for(int j = 3;j>=0;j--){
                if(i==j) break;
                for(int k = 0;i>=0;k++){
                    if(k==j) break;
                    for(int m=3;m>=0;m--){
                        if(k==m) break;
                        System.out.println(i +" " + j + " " + k + " " + m);

                    }

                }
            }
        }
    }
}
