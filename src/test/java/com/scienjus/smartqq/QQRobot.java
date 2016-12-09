package com.scienjus.smartqq;


import com.scienjus.smartqq.callback.MessageCallback;
import com.scienjus.smartqq.callback.QrCode;
import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.*;
import com.scienjus.smartqq.tuling.Cook;
import com.scienjus.smartqq.tuling.News;
import com.scienjus.smartqq.tuling.TulingApi;
import com.scienjus.smartqq.tuling.TulingResponse;

import java.awt.*;
import java.io.IOException;
import java.util.List;


public class QQRobot {
    public static void main(String[] args) {
        SmartQQClient smartQQClient = new SmartQQClient(new MessageCallback() {

            private QrCodeFrame frame;

            @Override
            public void receiveQrCode(QrCode qrCode) throws IOException{
                if(frame!=null){        //防止二维码失效重新获得二维码的情况
                    frame.setVisible(false);
                    frame.dispose();
                }
                frame = new QrCodeFrame(qrCode.getImage());
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        frame.setVisible(true);
                    }
                });
            }

            @Override
            public void qrFinish() {
                frame.setVisible(false);
            }

            @Override
            public void onMessage(Message message) {
                System.out.println("收到好友消息："+message.getContent());
                handleMessage(message);
            }

            @Override
            public void onGroupMessage(GroupMessage message) {
                System.out.println("收到群消息："+message.getContent());
            }

            @Override
            public void onDiscussMessage(DiscussMessage message) {
                System.out.println("收到讨论组消息："+message.getContent());
            }
        });
        System.out.println("登录成功，获取到好友信息！");
        List<Category> categories = smartQQClient.getFriendListWithCategory();
        for (Category category : categories) {
            System.out.println(category.getName());
            for (Friend friend : category.getFriends()) {
                System.out.println("————" + friend.getNickname());
            }
        }
        while (true);
    }

    private static void handleMessage(Message message){
        String user = String.valueOf(message.getUserId());
        String content = message.getContent();

        try {
            TulingResponse tulingResponse = TulingApi.getTuling(content,user);
            switch (tulingResponse.getType()){
                case Text:
                    sendMessage(message,"[自动回复]:"+tulingResponse.getText());break;
                case Link:
                    sendMessage(message,"[自动回复]:"+tulingResponse.getText());
                    sendMessage(message,tulingResponse.getUrl());break;
                case News:
                    sendMessage(message,"[自动回复]:"+tulingResponse.getText());
                    for(News news:tulingResponse.getNewsList()){
                        sendMessage(message,news.getArticle()+" "+news.getSource()+"\n"+news.getDetailUrl());
                    }
                    break;
                case Cook:
                    sendMessage(message,"[自动回复]:"+tulingResponse.getText());
                    for(Cook cook:tulingResponse.getCookList()){
                        sendMessage(message,cook.getName()+" "+cook.getInfo()+"\n"+cook.getDetailUrl());
                    }
                    break;

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void sendMessage(Message message,String content){
        SmartQQClient client = message.getClient();
        long user = message.getUserId();
        System.out.println("回复好友消息："+content);
        client.sendMessageToFriend(user,content);
    }
}
