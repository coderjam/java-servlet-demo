package servlet;

import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;
import java.util.Scanner;

/**
 * web服务器开发团队
 */
public class TomcatService {

    private static HashMap<String, Servlet> servletMap = new HashMap<>();
    public static void main(String[] args) throws Exception {
        // 接收用户请求，根据请求路径调用对应的 Servlet 来完成业务逻辑
        while (true) {
            // 1. 接收用户请求路径
            String url = getUserInputUrl();
            // 2. 根据请求路径找到对应的 Servlet
            Servlet servlet = getServlet(url);
            if(servlet == null){
                System.out.println("输入的url有误，请重新输入：");
                continue;
            }
            // 3. 调用 Servlet，执行业务逻辑
            servlet.service();
        }

    }

    private static String getUserInputUrl() {
        System.out.println("请输入url");
        // 模拟
        Scanner s = new Scanner(System.in);
        String url = s.nextLine();
        return url;
    }

    private static Servlet getServlet(String url) throws Exception {
        if(servletMap.containsKey(url)){
            System.out.println("返回已存在的servlet，不需要重新创建");
            return  servletMap.get(url);
        }
        // 解析配置文件
        FileReader reader = new FileReader("/Users/dengjian/Code/study/Java-servlet-demo/src/servlet/web.properties");
        Properties properties = new Properties();
        properties.load(reader);
        reader.close();
        String className = properties.getProperty(url);
        if (className == null) {
            return null;
        }
        System.out.println("新建servlet");
        // 根据类名称，去处理请求的servlet
        Class clazz = Class.forName(className);
        Object obj = clazz.newInstance();
        Servlet servlet = (Servlet) obj;
        servletMap.put(url, servlet);
        return servlet;
    }
}