package com.net.tools.futurelabnetconnecttools.test;

import com.fasterxml.jackson.databind.node.BigIntegerNode;
import lombok.Data;

/**
 * 类功能描述：</br>
 * 程序猿心中的女朋友
 * 欢迎关注我的公众号：终端研发部，每天分享职场技术，我在这里等你
 * @author yuyahao
 * @version 1.0 </p> 修改时间：</br> 修改备注：</br>
 */
@Data
public class MyNewGrildFriend {
    /**
     * 姓名
     */
    private String name;
    /**
     * 年龄
     */
    private Integer age;


    private static void haveCondition(){
        System.out.println("如果有了女朋友，可以做以：下事情\t");
    }
    private static void haveEat(int dayCount){
        System.out.println(String.format("今天是在一起第 %s 天，我们一起吃吃吃，吃遍天下美食\t",dayCount));
    }
    private static void haveFun(int dayCount){
        System.out.println( String.format("今天是在一起第 %s 天，我们一起每天逛逛逛，逛遍天涯海角\t",dayCount));
    }
    private static void dayDoSomething(){
        System.out.println("白天做些什么，白天一起么么哒\t");
    }
    private static void nighttDoSomething(){
        System.out.println("晚上做些什么，晚上一起啪啪啪\t");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("先new一个女朋友");
        MyNewGrildFriend myNewGrildFriend = new MyNewGrildFriend();
        myNewGrildFriend.setName("翠花");
        myNewGrildFriend.setAge(20);
        System.out.println(myNewGrildFriend.toString());
        int count = 1;
        int  dayCount = 1;
        haveCondition();
        while (true){
            if(count %5 == 1){
                haveEat(dayCount);
            }else   if(count %5 == 2){
                haveFun(dayCount);
            }else   if(count %5 == 3){
                dayDoSomething();
            }else   if(count %5 == 4){
                nighttDoSomething();
            }
            if(count %5 ==  0){
                System.out.println("hi ,baby ，要不要休息一下？");
                Thread.sleep(500);
                System.out.println("别了，咱们继续浪");
                dayCount++;
            }
             Thread.sleep(1000);
            count++;
        }
    }
}
