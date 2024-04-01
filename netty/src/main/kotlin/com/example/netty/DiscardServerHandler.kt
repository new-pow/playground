package com.example.netty

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.channel.ChannelInboundHandlerAdapter
import io.netty.util.ReferenceCountUtil

/**
 * Netty에서 생성된 I/O 이벤트를 처리하는 핸들러
 */
class DiscardServerHandler : ChannelInboundHandlerAdapter() {

    override fun channelRead(ctx: ChannelHandlerContext, msg: Any) {
        // 받은 데이터를 로깅하고 버림
        val inBuf = msg as ByteBuf
        try {
            while (inBuf.isReadable) {
                print(inBuf.readByte())
                System.out.flush()
            }
        } finally {
            ReferenceCountUtil.release(msg) // 버퍼를 해제하지 않으면 메모리 누수가 발생할 수 있음. 핸들러에 전달된 데이터는 핸들러가 처리한 후 해제해야 함.
        }
    }

    override fun exceptionCaught(ctx: ChannelHandlerContext?, cause: Throwable?) {
        try {
            cause?.printStackTrace() // 예외는 기록되어야하며
        } finally {
            ctx?.close() // 관련 채널은 닫혀야 한다.
            val byteBuf = cause as ByteBuf
            byteBuf.release()
        }
    }
}
