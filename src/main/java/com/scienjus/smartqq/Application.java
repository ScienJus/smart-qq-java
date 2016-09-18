package com.scienjus.smartqq;

import com.scienjus.smartqq.callback.MessageCallback;
import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.*;

import java.io.IOException;
import java.util.List;
import java.util.Scanner;

import org.apache.log4j.Logger;

/**
 * @author ScienJus
 * @date 2015/12/18.
 */
public class Application {
	 private static final Logger LOGGER = Logger.getLogger(Application.class);

    public static void main(String[] args) {
        //创建一个新对象时需要扫描二维码登录，并且传一个处理接收到消息的回调，如果你不需要接收消息，可以传null
        SmartQQClient client = new SmartQQClient(new MessageCallback() {
            @Override
            public void onMessage(Message message) {
                System.out.println(message.getContent());
            }

            @Override
            public void onGroupMessage(GroupMessage message) {
                System.out.println(message.getContent());
            }

            @Override
            public void onDiscussMessage(DiscussMessage message) {
                System.out.println(message.getContent());
            }
        });
        //登录成功后便可以编写你自己的业务逻辑了
        boolean sign = true;
        while (sign) {
        	Scanner scanner = new Scanner(System.in);
        	System.out.println("输入命令：1、获取群列表；2、获取群成员详细信息；3、根据发送根据friendId信息给群成员；4、抓取所有好友信息；5、退出程序");
        	int i = scanner.nextInt();
        	switch (i) {
			case 1:
				getGroupList(client);
				break;
			case 2:
				getGroupMemberMes(client);
				break;
			case 3:
				sendMessForGroupMember(client);
				break;
			case 4:
				getFrinedMess(client);
				break;
			case 5:
				LOGGER.info("退出程序");
				sign = false;
			default:
				break;
			}
        	scanner.close();
        }
        
        //使用后调用close方法关闭，你也可以使用try-with-resource创建该对象并自动关闭
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }

	private static void getFrinedMess(SmartQQClient client) {
		// TODO Auto-generated method stub
        List<Category> categories = client.getFriendListWithCategory();
        for (Category category : categories) {
            System.out.println(category.getName());
            for (Friend friend : category.getFriends()) {
                System.out.println(friend);
            }
        }
        
	}

	private static void sendMessForGroupMember(SmartQQClient client) {
		// TODO Auto-generated method stub
		LOGGER.info("根据friendId发送数据到组成员中");
		System.out.println("输入接受对象的friendId");
		Scanner scanner = new Scanner(System.in);
		long friendId = scanner.nextLong();
		client.sendMessageToFriend(friendId, "来自APP的信息！！！");
		LOGGER.info("测试信息已经发送");
		scanner.close();
	}

	private static void getGroupMemberMes(SmartQQClient client) {
		// TODO Auto-generated method stub
		LOGGER.info("获取群组成员信息");
		Scanner scanner = new Scanner(System.in);
		LOGGER.info("输入群的gid");
		long gid = scanner.nextLong();
		GroupInfo groupInfo = client.getGroupInfo(gid);
		for (GroupUser user : groupInfo.getUsers()){
			System.out.println(user);
		}
		LOGGER.info("群组成员拉取完成");
		scanner.close();
 		
	}

	private static void getGroupList(SmartQQClient client) {
		// TODO Auto-generated method stub
		LOGGER.info("获取群列表");
		List<Group> groupList = client.getGroupList();
		for (Group g : groupList) {
			System.out.println(g);
		}
		LOGGER.info("打印完毕");
	}
}
