package com.hoodps.netty;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

public class CustomHandler extends SimpleChannelInboundHandler<HttpObject> {


    @Override
    protected void channelRead0(ChannelHandlerContext ctx, HttpObject msg) throws Exception {
        Channel channel = ctx.channel();
        if(msg instanceof HttpRequest){
            System.out.println(channel.remoteAddress());
            ByteBuf content = Unpooled.copiedBuffer("Hello netty!", CharsetUtil.UTF_8);

            FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_0,
                    HttpResponseStatus.OK,
                    content);

            response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaderNames.CONTENT_LENGTH, content.readableBytes());

            ctx.writeAndFlush(response);

        }
    }
}
