package org.whatever.aha.zjut.platform.message;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.constant.RedisCacheConstant;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.base.exception.AppException;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;
import java.util.TimeZone;

/**
 * @author Baby_mo
 *
 * TODO 把消息通知完整内容存到Mongo
 */
@RestController
@Api(tags = "消息通知相关接口")
@RequestMapping("/v1/msg")
@RequiredArgsConstructor
public class MessageController {
    final MessageService messageService;
    final RedisCacheConstant redisConstant;

    @ApiOperation(value = "获取用户消息通知", notes = "一页10条")
    @GetMapping("/userReceive")
    public Object getUserMsgList(@RequestParam int page) {
        int userId = StpUtil.getLoginIdAsInt();
        return AjaxResult.SUCCESS(messageService.getUserOutlineMessages(userId, page, 0));
    }



    @ApiOperation(value = "获取用户未读消息通知", notes = "一页10条")
    @GetMapping("/userUnread")
    public Object getUserUnreadMsgList(@RequestParam int page) {
        int userId = StpUtil.getLoginIdAsInt();
        return AjaxResult.SUCCESS(messageService.getUserOutlineMessages(userId, page, 2));
    }

    @ApiOperation(value = "获取用户已发送消息列表")
    @GetMapping("/userSent")
    public Object getUserMsgSent(@RequestParam int page) {
        int userId = StpUtil.getLoginIdAsInt();
        return AjaxResult.SUCCESS(messageService.getUserOutlineMessages(userId, page, 1));
    }

    @ApiOperation(value = "创建一条消息")
    @PostMapping("/create")
    public Object create(@RequestParam int receiverId, @RequestParam String title, @RequestParam String content, @RequestParam String senderName) {
        int senderId = StpUtil.getLoginIdAsInt();
        SimpleDateFormat s=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String sendTime = s.format(Calendar.getInstance(TimeZone.getTimeZone("UTC")).getTime());
        messageService.createUserMessage(senderId, receiverId, sendTime, title, content, senderName);
        return AjaxResult.SUCCESS();
    }

    @ApiOperation(value = "查看消息", notes = "标记为已读")
    @GetMapping("/{mid}")
    public Object getMsg(@PathVariable String mid) {
        int userId = StpUtil.getLoginIdAsInt();
        Object msgContent = messageService.getMsgContent(userId, mid);
        return AjaxResult.SUCCESS(Map.of("content", msgContent));
    }

    @ApiOperation(value = "查看消息条数")
    @ApiImplicitParam(name = "type", value = "0,1 全部，未读")
    @PostMapping("/count")
    public Object getMessageCount(@RequestParam int type){
        int userId = StpUtil.getLoginIdAsInt();

        String key;
        switch (type) {
            case 0: key = redisConstant.getKeyUserMsgQueueCount(userId);break;
            case 1: key = redisConstant.getKeyUserMsgUnReadCount(userId);break;
            default: throw new AppException(ErrorCode.ILLEGAL_REQUEST);
        }
        return AjaxResult.SUCCESS(Map.of("count", messageService.getQueueSize(key)));
    }
}
