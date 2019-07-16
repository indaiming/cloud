package com.provider5552.config;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.api.*;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.RetryNTimes;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.ZooDefs;

import java.util.List;

public class Zookeeper {
    private final static String zkaddr = "127.0.0.1:2182";

    public static void main(String[] args) throws Exception {
        getNodes();
    }

    public static void getNodes() throws Exception {
        CuratorFramework client = CuratorFrameworkFactory
                .newClient(zkaddr, 1000*60, 1000*15, new RetryNTimes(10,5000));
        client.start();//开始连接
        CuratorFrameworkState state = client.getState();//连接状态
        System.out.println(state);
        //CreateMode.EPHEMERAL 临时节点模式
        //CreateMode.PERSISTENT 持久节点模式
        //PERSISTENT_SEQUENTIAL  持久顺序节点模式
        //EPHEMERAL_SEQUENTIAL 临时顺序节点模式
        client.create().withMode(CreateMode.EPHEMERAL).forPath("/jtt");//仅仅创建节点（临时节点）
        client.setData().forPath("/jtt", "jtt111112222".getBytes());//设置值内容

        //路径下的节点集合
        List<String> children = client.getChildren().usingWatcher(new CuratorWatcher() {
            @Override
            public void process(WatchedEvent event) throws Exception {
                System.out.println("监控： " + event.getPath());
            }
        }).forPath("/");
        System.out.println(children);

        String result = client.create().withMode(CreateMode.PERSISTENT).withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE).forPath("/daim1/11", "Data".getBytes());//创建节点，带上节点内容
        System.out.println(result);

        // 设置节点数据
        client.setData().forPath("/daim1/11", "aim1/11111".getBytes());
        client.setData().forPath("/daim1/11", "aim1/11222".getBytes());

        //获取节点值
        String jttStr = new String (client.getData().forPath("/jtt"));
        System.out.println(jttStr);

        // 删除节点
        client.delete().withVersion(-1).forPath("/daim1/11");//删除节点


        client.close();//关闭连接
        System.out.println("操作结束！");
        client.getCuratorListenable().addListener(new CuratorListener()
        {
            @Override
            public void eventReceived(CuratorFramework client, CuratorEvent event) throws Exception
            {
                System.out.println("事件： " + event);
            }
        });
        client.getConnectionStateListenable().addListener(new ConnectionStateListener()
        {
            @Override
            public void stateChanged(CuratorFramework client, ConnectionState newState)
            {
                System.out.println("连接状态事件： " + newState);
            }
        });
        client.getUnhandledErrorListenable().addListener(new UnhandledErrorListener()
        {
            @Override
            public void unhandledError(String message, Throwable e)
            {
                System.out.println("错误事件：" + message);
            }
        });
    }

}
