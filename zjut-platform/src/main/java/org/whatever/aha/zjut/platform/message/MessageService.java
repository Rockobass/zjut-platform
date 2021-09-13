package org.whatever.aha.zjut.platform.message;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.base.constant.LocalCacheConstant;
import org.whatever.aha.zjut.base.constant.RedisCacheConstant;

import javax.annotation.PostConstruct;
import java.util.*;

/**
 * @author Baby_mo
 */
@Service
@RequiredArgsConstructor
@CacheConfig(cacheNames = "Message")
public class MessageService {
    final CacheManager caffeineCacheManager;
    final AsyncMethod asyncMethod;
    final RedisCacheConstant redisConstant;
    final LocalCacheConstant cacheConstant;
    final MessageUtil msgUtil;
    Cache localCache;

    @PostConstruct
    void init() {
        localCache = caffeineCacheManager.getCache("Message");
    }


    /**
     * 创建一条点对点消息
     */
    public void createUserMessage(int senderId, int receiverId, String sendTime, String title, String content, String senderName) {
        String mid = UUID.randomUUID().toString().substring(0, 12);
        // 用户消息队列
        String key1 = redisConstant.getKeyUserMsgQueue(receiverId);
        // 消息
        String key2 = redisConstant.getKeyMsg(mid);
        // 用户未读消息
        String key3 = redisConstant.getKeyUserMsgUnRead(receiverId);
        // 用户发送队列
        String key4 = redisConstant.getKeyUserMsgSent(senderId);
        // 用户消息队列长度
        String key5 = redisConstant.getKeyUserMsgQueueCount(receiverId);
        // 用户未读队列长度
        String key6 = redisConstant.getKeyUserMsgUnReadCount(receiverId);

        List<String> keys = Arrays.asList(key1, key2, key3, key4, key5, key6);
        Object[] args = new Object[]{mid, senderId, receiverId, sendTime, title, content, senderName, ""};
        msgUtil.createMsg(keys, args);

        localCache.evict(cacheConstant.getKeyUserMsgQueue(receiverId));
        localCache.evict(cacheConstant.getKeyUserMsgSent(senderId));
        asyncMethod.refreshMsgCountLocalCache(redisConstant.getKeyUserMsgQueueCount(receiverId), 1L);
        asyncMethod.refreshMsgCountLocalCache(redisConstant.getKeyUserMsgUnReadCount(receiverId), 1L);
        asyncMethod.loadMsgQueuePage(receiverId, 1);
    }


    /**
     * @param type 0 接收队列 1 发送队列
     */
    public List<Object> getUserOutlineMessages(int userId, int page, int type) {
        String key;
        switch (type) {
            case 0: key = cacheConstant.getKeyUserMsgQueue(userId);break;
            case 1: key = cacheConstant.getKeyUserMsgSent(userId);break;
            case 2: key = cacheConstant.getKeyUserMsgUnread(userId);break;
            default : return null;
        }

        Map<Integer, List<Object>> cacheMap;
        Cache.ValueWrapper valueWrapper = localCache.get(key);
        if (valueWrapper == null) {
            cacheMap = new HashMap<>();
        } else {
            cacheMap = (Map<Integer, List<Object>>) valueWrapper.get();
        }

        if (cacheMap.containsKey(page)) {
            return cacheMap.get(page);
        }

        List<Object> result = msgUtil.getMsgOutline(userId, page, type);
        cacheMap.put(page, result);
        localCache.put(key, cacheMap);
        return result;
    }

    /**
     * 获取消息内容， 并标记为已读
     *
     * TODO 没有上缓存
     */
    public Object getMsgContent(int userId, String messageId){
        if (!msgUtil.hasMsg(userId, messageId)) {
            return "";
        }
        Object msgContent = msgUtil.getMsgContent(messageId);
        asyncMethod.readMessage(userId, messageId);
        return msgContent;
    }

    /**
     * 获取队列长度
     */
    public Long getQueueSize(String key){
        Cache.ValueWrapper valueWrapper = localCache.get(key);
        if (valueWrapper != null) {
            return (Long) valueWrapper.get();
        } else {
            Long count = msgUtil.getQueueSize(key.substring(0, key.length()-3));
            localCache.put(key, count);
            return count;
        }
    }

}
