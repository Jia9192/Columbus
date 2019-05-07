package com.galileo.p2p.server

import io.netty.buffer.ByteBuf
import io.netty.channel.ChannelHandlerContext
import io.netty.handler.codec.ByteToMessageDecoder
import io.netty.handler.codec.CorruptedFrameException
import java.util
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class TrxProtobufVarint32FrameDecoder extends ByteToMessageDecoder{

  private val logger: Logger = LoggerFactory.getLogger(getClass)

  private val maxMsgLength: Int = 5 * 1024 * 1024 //5M
  private var channel: Channel = null

  def this(channel: Channel) {
    this()
    this.channel = channel
  }

  @throws[Exception]
  protected def decode(ctx: ChannelHandlerContext, in: ByteBuf, out: util.List[AnyRef]) {
    in.markReaderIndex
    val preIndex: Int = in.readerIndex
    val length: Int = readRawVarint32(in)
    if (length >= maxMsgLength) {
      logger.error("recv a big msg, host : "+ctx.channel.remoteAddress+", msg length is : " + length)
      in.clear
      channel.close()
      return
    }
    if (preIndex == in.readerIndex) return
    if (length < 0) throw new CorruptedFrameException("negative length: " + length)
    if (in.readableBytes < length) in.resetReaderIndex
    else out.add(in.readRetainedSlice(length))
  }

  private def readRawVarint32(buffer: ByteBuf): Int = {
    if (!buffer.isReadable) return 0
    buffer.markReaderIndex
    var tmp: Byte = buffer.readByte
    if (tmp >= 0) tmp
    else {
      var result: Int = tmp & 127
      if (!buffer.isReadable) {
        buffer.resetReaderIndex
        return 0
      }
      tmp = buffer.readByte
      if (tmp >= 0){
        result |= tmp << 7
      } else {
        result |= (tmp & 127) << 7
        if (!buffer.isReadable) {
          buffer.resetReaderIndex
          return 0
        }
        tmp = buffer.readByte
        if (tmp >= 0){
          result |= tmp << 14
        } else {
          result |= (tmp & 127) << 14
          if (!buffer.isReadable) {
            buffer.resetReaderIndex
            return 0
          }
          tmp = buffer.readByte
          if (tmp >= 0){
            result |= tmp << 21
          } else {
            result |= (tmp & 127) << 21
            if (!buffer.isReadable) {
              buffer.resetReaderIndex
              return 0
            }
            tmp = buffer.readByte
            result |= tmp << 28
            if (tmp < 0) throw new CorruptedFrameException("malformed varint.")
          }
        }
      }
      result
    }
  }

}
