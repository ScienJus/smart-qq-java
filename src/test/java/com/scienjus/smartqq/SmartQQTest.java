package com.scienjus.smartqq;

import static org.junit.Assert.*;

import java.util.List;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.junit.BeforeClass;
import org.junit.Test;

import com.scienjus.smartqq.client.SmartQQClient;
import com.scienjus.smartqq.model.Discuss;
import com.scienjus.smartqq.model.Friend;
import com.scienjus.smartqq.model.Group;
import com.scienjus.smartqq.model.UserInfo;

public class SmartQQTest {

	private static SmartQQClient qqClient;
	
	@BeforeClass
	public static void init() {
		qqClient = new SmartQQClient();
	}
	
	@Test
	public void testGetAccountInfo() {
		UserInfo userInfo = qqClient.getAccountInfo();
		assertNotNull(userInfo);
		System.out.println(ToStringBuilder.reflectionToString(userInfo));
	}
	
	@Test
	public void testGetGroupList() {
		List<Group> groups = qqClient.getGroupList();
		assertNotNull(groups);
		groups.forEach(k -> {
			System.out.println(ToStringBuilder.reflectionToString(k));
		});
	}
	
	@Test
	public void testGetDiscussList() {
		List<Discuss> discusses = qqClient.getDiscussList();
		assertNotNull(discusses);
		discusses.forEach(k -> {
			System.out.println(ToStringBuilder.reflectionToString(k));
		});
	}
	
	@Test
	public void testGetFriendList() {
		List<Friend> friends = qqClient.getFriendList();
		assertNotNull(friends);
		friends.forEach(k -> {
			System.out.println(ToStringBuilder.reflectionToString(k));
		});
	}

}
