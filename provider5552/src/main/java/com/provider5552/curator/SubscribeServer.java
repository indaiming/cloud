package com.provider5552.curator;/**
 * @Author daim
 * @Description //TODO end
 * @Date date
 **/

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryo.io.Input;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.*;
import org.apache.curator.retry.ExponentialBackoffRetry;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

/**
 * @Classname SubscribeServer
 * @Description TODO
 * @Date 2019/7/31
 * @Created by daim
 */
public class SubscribeServer {
    private static DBConfig dbConfig;
    private static CuratorFramework client;
    private static NodeCache nodeCache = null;

    public static void main(String[] args) throws Exception {
        //初始化zkClient
        init();
        //订阅服务
//        subscribeInfo();

        //订阅父路径下的所有子节点
        pathChildrenCacheStart();
//        //不中断监听线程
        Thread.sleep(Integer.MAX_VALUE);
    }

    /**
     * 初始化操作
     */
    public static void init() {
        client = CuratorFrameworkFactory.builder()
                .connectString(ZKConstants.zkAddress)
                .sessionTimeoutMs(ZKConstants.sessionTimeout)
                .retryPolicy(new ExponentialBackoffRetry(1000, 3))
                .build();
        client.start();

    }

    /**
     * 读取本地配置文件到一个DBConfig对象中。
     */
    public static void readConfig() {
        BufferedReader reader = null;//加载文件流
        System.out.println("读取本地数据库信息。。。。。。");
        try {
            reader = new BufferedReader(new FileReader("J:\\cloud\\cloud\\provider5552\\src\\main\\resources\\dbconfig.properties"));
            Properties prop = new Properties();//创建属性操作对象
            prop.load(reader);//加载流
            dbConfig = new DBConfig(prop.getProperty("url"), prop.getProperty("driver"), prop.getProperty("username"), prop.getProperty("password"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("本地数据库配置信息为：" + dbConfig.toString());
    }


    /**
     * 反序列化得到数据库内容
     */
    public static void unSerialize() {
        System.out.println("读取ZooKeeper服务器数据库信息。。。。。。");
        byte[] data = new byte[0];
        try {
            data = client.getData().forPath(ZKConstants.configPath);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Kryo kryo = new Kryo();
        Input input = new Input(data);
        dbConfig = kryo.readObject(input, DBConfig.class);
        input.close();
        System.out.println("ZooKeeper中的数据为：" + dbConfig.toString());
    }


    /**
     * 订阅ZooKeeper中的信息，也就是设置监听,如果ZooKeeper中没有对应的信息的话，就读取本地的数据库信息
     */
    public static void subscribeInfo() {
        nodeCache = new NodeCache(client, ZKConstants.configPath);
        try {
            nodeCache.start(true);
            //首次人工判断读取数据
            if (nodeCache.getCurrentData() != null) {
                if (!(new String(nodeCache.getCurrentData().getData()).equals(""))) {
                    unSerialize();
                } else {
                    readConfig();
                }
            } else {//否则读取本地文件
                readConfig();
            }
            //之后将nodeCache设置为监听对象。一旦ZKConstants.configPath存放的数据和本地的nodeCache不同，则调用nodeChanged()核心方法
            nodeCache.getListenable().addListener(new NodeCacheListener() {
                @Override
                public void nodeChanged() throws Exception {
                    System.out.println(nodeCache.toString());
                    System.out.println("数据库节点信息发生变化，读取新的数据库信息！");
                    unSerialize();//反序列化得到信息
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * 取消订阅
     */
    public static void unSubscribeInfo() {
        if (nodeCache != null) {
            try {
                System.out.println("取消订阅！");
                nodeCache.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    //pathChildrenCache测试 :可以监听子节点数据变化
    private String path = "/abcde";

    public static final Charset CHARSET = Charset.forName("UTF-8");

    public static void pathChildrenCacheStart() throws Exception {
        try{
            //    public static final String parentPath1 = "/ParentPath";
            //绑定订阅的父节点路径
            final PathChildrenCache pathChildrenCache = new PathChildrenCache(client, ZKConstants.parentPath1, true);

            pathChildrenCache.getListenable().addListener(new PathChildrenCacheListener() {
                @Override
                public void childEvent(CuratorFramework curatorFramework,
                                       PathChildrenCacheEvent event) throws Exception {

                    System.out.println("======== catch children change =======");
                    System.out.println("======== event type  "+event.getType()+"=======");
                    //输出更新的对象
                    System.out.println("update event type:" + event.getType() +
                            ",path:" + event.getData().getPath() + ",data:" + new String(event.getData().getData(), CHARSET));

                    //遍历订阅的路径下所有子节点
                    List<ChildData> childDataList = pathChildrenCache.getCurrentData();
                    if (childDataList != null && childDataList.size() > 0) {
                        System.out.println("path all children list:");
                        for (ChildData childData : childDataList) {
                            System.out.println("path:" + childData.getPath() + "," + new String(childData.getData(), CHARSET));
                        }
                    }
                }
            });

            pathChildrenCache.start();  //must call start();

            TimeUnit.MINUTES.sleep(5);

            pathChildrenCache.close();

        }finally {
            if(client!=null)
                client.close();
        }
    }
}
