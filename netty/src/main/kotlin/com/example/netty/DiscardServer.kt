package com.example.netty

import io.netty.bootstrap.ServerBootstrap
import io.netty.channel.ChannelOption
import io.netty.channel.nio.NioEventLoopGroup
import io.netty.channel.socket.nio.NioServerSocketChannel

/**
 * DiscardServer. handler를 가지고 있는 서버
 */
class DiscardServer(val port: Int) {

    fun run() {
        val parentGroup = NioEventLoopGroup() // 이벤트 루프 생성. ParentGroup은 연결을 수락하고 연결된 채널을 자식 이벤트 루프로 전달
        val childGroup = NioEventLoopGroup() // 이벤트 루프 생성. ChildGroup은 연결된 채널의 트래픽을 처리.

        // 이때 사용되는 스레드 수와 생성된 Channel 스레드에 매핑되는 방법은 구현에 따라 달라집니다.
        // EventLoopGroup을 생성할 때 생성자에 인수를 전달하지 않으면 기본값이 사용됩니다.

        try {
            val b = ServerBootstrap() // 서버를 설정하기 위한 부트스트랩을 생성합니다.
            // 설정할 수 있는 것

            b.group(parentGroup, childGroup) // 이벤트 루프 그룹을 설정
                .channel(NioServerSocketChannel::class.java) // 채널의 유형을 설정. 인스턴스화 할 클래스를 제공
                .childHandler(DiscardServerInitializer()) // 채널에 대한 초기화를 설정. 여기서는 채널의 파이프라인을 설정
                .option(ChannelOption.SO_BACKLOG, 128) // 부모 채널의 소켓 옵션을 설정. 여기서는 서버 소켓의 수락 큐 크기를 설정 https://netty.io/4.1/api/io/netty/channel/ChannelOption.html
                .childOption(ChannelOption.SO_KEEPALIVE, true) // 자식 채널의 소켓 옵션을 설정. 여기서는 소켓이 keep-alive인지 설정

            val f = b.bind(port).sync() // 서버를 시작하고 소켓 채널을 바인딩
            f.channel().closeFuture().sync() // 서버 소켓 채널의 closeFuture 를 얻고 완료될 때까지 대기
        } finally {
            childGroup.shutdownGracefully() // 이벤트 루프를 종료
            parentGroup.shutdownGracefully() // 이벤트 루프를 종료
        }
    }

    companion object {
        @JvmStatic
        fun main(args: Array<String>) {
            val port = args.getOrNull(0)?.toIntOrNull() ?: 8080
            DiscardServer(port).run()
        }
    }
}
