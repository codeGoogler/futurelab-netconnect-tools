package com.net.tools.futurelabnetconnecttools.test;

import lombok.Data;

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
    private static void haveEat(){
        System.out.println("每天吃吃吃，吃遍天下美食\t");
    }
    private static void haveFun(){
        System.out.println("每天逛逛逛，逛遍天涯海角\t");
    }
    private static void dayDoSomething(){
        System.out.println("白点做些什么，白天一起么么哒\t");
    }
    private static void nighttDoSomething(){
        System.out.println("晚上做些什么，晚上一起啪啪啪\t");
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("先new一个女朋友");
        MyNewGrildFriend myNewGrildFriend = new MyNewGrildFriend();
        myNewGrildFriend.setName("My baby");
        myNewGrildFriend.setAge(20);
        System.out.println(myNewGrildFriend.toString());
        int count = 1;
        haveCondition();
        while (true){
            if(count == 1){
                haveEat();
            }else   if(count == 2){
                haveFun();
            }else   if(count == 3){
                dayDoSomething();
            }else   if(count == 4){
                nighttDoSomething();
            }
            if(count > 4){
                count = 0;
            }
            count++;
             Thread.sleep(1000);
        }
    }
}
