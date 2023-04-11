package servlet;

/**
 * 预定房间服务
 */
public class BookRoomServlet  implements Servlet{

    @Override
    public void service(){
        System.out.println("预定房间服务被调用.");
    }
}
