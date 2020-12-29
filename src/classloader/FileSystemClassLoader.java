package classloader;

import java.io.*;

/**
 * @Author Tom
 * @Date 2020/5/30 20:56
 * @Version 1.0
 * @Description
 */
public class FileSystemClassLoader extends ClassLoader{
    //com.bjsxt.test.User   --> d:/myjava/  com/bjsxt/test/User.class
    private String rootDir;

    public FileSystemClassLoader(String rootDir){
        this.rootDir = rootDir;
    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {

        Class<?> c = findLoadedClass(name);

        //应该要先查询有没有加载过这个类。如果已经加载，则直接返回加载好的类。如果没有，则加载新的类。
        if(c!=null){
            return c;
        }else{
            ClassLoader parent = this.getParent();
            try {
                c = parent.loadClass(name);	   //委派给父类加载
            } catch (Exception e) {
//				e.printStackTrace();
            }

            if(c!=null){
                return c;
            }else{
                byte[] classData = getClassData(name);
                if(classData==null){
                    throw new ClassNotFoundException();
                }else{
                    c = defineClass(name, classData, 0,classData.length);

                }
            }

        }

        return c;

    }

    private byte[] getClassData(String classname){   //com.bjsxt.test.User   d:/myjava/  com/bjsxt/test/User.class
        String path = rootDir +"/"+ classname.replace('.', '/')+".class";

//		IOUtils,可以使用它将流中的数据转成字节数组
        InputStream is = null;
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try{
            is  = new FileInputStream(path);

            byte[] buffer = new byte[1024];
            int temp=0;
            while((temp=is.read(buffer))!=-1){
                baos.write(buffer, 0, temp);
            }

            return baos.toByteArray();
        }catch(Exception e){
            e.printStackTrace();
            return null;
        }finally{
            try {
                if(is!=null){
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                if(baos!=null){
                    baos.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) throws Exception {
        ClassLoader loader = FileSystemClassLoader.class.getClassLoader();
        System.out.println(loader);


        ClassLoader loader2 = Thread.currentThread().getContextClassLoader();
        System.out.println(loader2);

        Thread.currentThread().setContextClassLoader(new FileSystemClassLoader("D:\\Java\\IdeaProjects\\DataStructures&Algroithm\\out\\production\\DataStructures&Algroithm"));
        System.out.println(Thread.currentThread().getContextClassLoader());
        System.out.println(Thread.currentThread().getContextClassLoader().getParent());
        File file = new File(":\\Java\\IdeaProjects\\DataStructures&Algroithm\\out\\production\\DataStructures&Algroithm\\indi\\tom\\Algorithm\\recursion\\Queen8");
        file.length();
        Class c = Thread.currentThread().getContextClassLoader().loadClass("indi.tom.Algorithm.recursion.Queen8");
        System.out.println(c);
        System.out.println(c.getClassLoader());

    }
}
