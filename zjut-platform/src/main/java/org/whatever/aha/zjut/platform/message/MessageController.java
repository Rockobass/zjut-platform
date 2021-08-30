package org.whatever.aha.zjut.platform.message;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.dto.AjaxResult;

import java.sql.Timestamp;

@RestController
@Api(tags = "消息通知相关接口")
@RequestMapping("/v1/msg")
public class MessageController {
    @Autowired
    MessageService messageService;

    @ApiOperation(value = "获取用户消息通知", notes = "一页10条")
    @GetMapping("/userList")
    public Object getUserMsgList(@RequestParam int page) {
        int userId = StpUtil.getLoginIdAsInt();
        return AjaxResult.SUCCESS(messageService.getUserMessages(userId, page));
    }

    @ApiOperation(value = "创建一条消息")
    @GetMapping("/create")
    public Object create(@RequestParam int receiverId, @RequestParam String title, @RequestParam String content, @RequestParam String senderName) {
        int senderId = StpUtil.getLoginIdAsInt();
        messageService.createUserMessage(senderId,receiverId, new Timestamp(System.currentTimeMillis()), title, content, senderName);
        return null;
    }
}
