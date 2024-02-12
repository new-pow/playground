package com.example.netty

import io.netty.channel.ChannelInitializer
import io.netty.channel.socket.SocketChannel

class DiscardServerInitializer : ChannelInitializer<SocketChannel>() {

    override fun initChannel(ch: SocketChannel) {
        ch.pipeline().addLast(DiscardServerHandler()) // DiscardServerHandler를 파이프라인에 추가
    }
}
