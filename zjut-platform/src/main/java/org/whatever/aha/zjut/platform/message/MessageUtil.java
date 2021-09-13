package org.whatever.aha.zjut.platform.message;

import lombok.RequiredArgsConstructor;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.ReturnType;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.serializer.Jackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.scripting.support.ResourceScriptSource;
import org.springframework.stereotype.Component;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.constant.RedisCacheConstant;
import org.whatever.aha.zjut.base.exception.AppException;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @author Baby_mo
 */
@Component
@RequiredArgsConstructor
public class MessageUtil {
    final RedisTemplate<String, Object> redisTemplate;
    final RedisCacheConstant redisConstant;
    String SHA_CREATE_MSG;
    String SHA_LPOS;


    @PostConstruct
    void init() {
        RedisConnection connection = redisTemplate.getConnectionFactory().getConnection();
        DefaultRedisScript<Object> redisScript1 = new DefaultRedisScript<>();
        redisScript1.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/createMsg.lua")));
        DefaultRedisScript<Integer> redisScript2 = new DefaultRedisScript<>();
        redisScript2.setScriptSource(new ResourceScriptSource(new ClassPathResource("lua/lpos.lua")));
        redisScript2.setResultType(Integer.class);
        SHA_CREATE_MSG = redisScript1.getSha1();
        SHA_LPOS = redisScript2.getSha1();
        connection.scriptLoad(redisScript1.getScriptAsString().getBytes());
        connection.scriptLoad(redisScript2.getScriptAsString().getBytes());
    }

    /**
     * type有三种情况
     * 0 获取用户消息队列
     * 1 获取已发送队列
     * 2 获取未读队列
     */
    public Object[] getMsgIds(int userId, int start, int end, int type) {
        String key;
        switch (type) {
            case 0 : key = redisConstant.getKeyUserMsgQueue(userId);break;
            case 1 : key = redisConstant.getKeyUserMsgSent(userId);break;
            case 2 : key = redisConstant.getKeyUserMsgUnRead(userId);break;
            default: throw new AppException(ErrorCode.ILLEGAL_REQUEST);
        }

        return redisTemplate.opsForList().range(key, start, end).toArray();
    }

    /**
     * 传入msgId数组，pipeline批量获取结果
     **/
    public Object[] getMsgOutlineInRedis(Object[] msgIds) {
        return redisTemplate.executePipelined((RedisCallback<Object>) connection -> {
            connection.openPipeline();
            Arrays.stream(msgIds).forEach(e -> {
                connection.hMGet(redisConstant.getKeyMsg((String) e).getBytes(), "sendTime".getBytes(), "title".getBytes(), "senderName".getBytes(), "readTime".getBytes());
            });
            return null;
        }).toArray();
    }


    public List<Object> convertToOutline(Object[] userMsgIds, Object[] values) {
        List<Object> result = new ArrayList<>();
        for (int i = 0; i < userMsgIds.length; i++) {
            List<Object> value = (List<Object>) values[i];
            result.add(Map.of("msgId", userMsgIds[i], "sendTime", value.get(0), "title", value.get(1), "senderName", value.get(2), "readTime", value.get(3)));
        }
        return result;
    }

    /**
     * 获取消息概况
     */
    public List<Object> getMsgOutline(int userId, int page, int type) {
        int pageSize = 10;
        int start = (page - 1) * pageSize;
        int end = page * pageSize - 1;
        Object[] userMsgIds = getMsgIds(userId, start, end, type);
        Object[] values = getMsgOutlineInRedis(userMsgIds);
        return convertToOutline(userMsgIds, values);
    }

    /**
     * 创建一条点对点消息
     */
    public void createMsg(List<String> keys, Object[] args) {
        redisTemplate.getConnectionFactory().getConnection().evalSha(SHA_CREATE_MSG, ReturnType.fromJavaType(Object.class), 6, keysAndArgs(keys, args));
    }

    /**
     * 判断用户是否有该条消息
     */
    public boolean hasMsg(int userId, String msgId) {
        List<String> keys = Arrays.asList(redisConstant.getKeyUserMsgQueue(userId));
        Object[] args = new Object[]{msgId};
        Long o = redisTemplate.getConnectionFactory().getConnection().evalSha(SHA_LPOS, ReturnType.INTEGER, 1, keysAndArgs(keys, args));
        return o != null;
    }

    public Object getMsgContent(String msgId) {
        String key = redisConstant.getKeyMsg(msgId);
        return redisTemplate.opsForHash().get(key, "content");
    }

    public byte[][] keysAndArgs(List<String> keys, Object[] args) {
        StringRedisSerializer keySerializer = (StringRedisSerializer) redisTemplate.getKeySerializer();
        Jackson2JsonRedisSerializer<Object> argsSerializer = (Jackson2JsonRedisSerializer<Object>) redisTemplate.getValueSerializer();
        final int keySize = keys != null ? keys.size() : 0;
        byte[][] keysAndArgs = new byte[args.length + keySize][];
        int i = 0;
        if (keys != null) {
            for (String key : keys) {
                keysAndArgs[i++] = keySerializer.serialize(key);
            }
        }
        for (Object arg : args) {
            keysAndArgs[i++] = argsSerializer.serialize(arg);
        }
        return keysAndArgs;
    }

    /**
     * 获取队列长度
     */
    public Long getQueueSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

}
