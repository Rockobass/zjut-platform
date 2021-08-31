package org.whatever.aha.zjut.platform.message;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.dto.AjaxResult;

import java.sql.Timestamp;

/**
 * @author Baby_mo
 */
@RestController
@Api(tags = "消息通知相关接口")
@RequestMapping("/v1/msg")
@RequiredArgsConstructor
public class MessageController {
    final MessageService messageService;

    @ApiOperation(value = "获取用户消息通知", notes = "一页10条")
    @GetMapping("/userReceive")
    public Object getUserMsgList(@RequestParam int page) {
        int userId = StpUtil.getLoginIdAsInt();
        return AjaxResult.SUCCESS(messageService.getUserOutlineMessages(userId, page, 0));
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
        messageService.createUserMessage(senderId, receiverId, new Timestamp(System.currentTimeMillis()), title, content, senderName);
        return AjaxResult.SUCCESS();
    }
}
