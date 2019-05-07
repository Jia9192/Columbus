package com.galileo.core.service.http.solidity;

import com.google.protobuf.ByteString;
import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.galileo.protos.api.GrpcAPI.BytesMessage;
import com.galileo.utils.ByteArray;
import com.galileo.core.WalletSolidity;
import com.galileo.core.service.http.JsonFormat;
import com.galileo.protos.protos.Protocol.TransactionInfo;


@Component
@Slf4j
public class GetTransactionInfoByIdServlet extends HttpServlet {

  @Autowired
  private WalletSolidity walletSolidity;

  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response) {
    try {
      String input = request.getParameter("value");
      TransactionInfo transInfo = walletSolidity.getTransactionInfoById(ByteString.copyFrom(
          ByteArray.fromHexString(input)));
      if (transInfo == null) {
        response.getWriter().println("{}");
      } else {
        response.getWriter().println(JsonFormat.printToString(transInfo));
      }
    } catch (Exception e) {
      logger.debug("Exception: {}", e.getMessage());
      try {
        response.getWriter().println(e.getMessage());
      } catch (IOException ioe) {
        logger.debug("IOException: {}", ioe.getMessage());
      }
    }
  }

  @Override
  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      String input = request.getReader().lines()
          .collect(Collectors.joining(System.lineSeparator()));
      BytesMessage.Builder build = BytesMessage.newBuilder();
      JsonFormat.merge(input, build);
      TransactionInfo transInfo = walletSolidity.getTransactionInfoById(build.build().getValue());
      if (transInfo == null) {
        response.getWriter().println("{}");
      } else {
        response.getWriter().println(JsonFormat.printToString(transInfo));
      }
    } catch (Exception e) {
      logger.debug("Exception: {}", e.getMessage());
      try {
        response.getWriter().println(e.getMessage());
      } catch (IOException ioe) {
        logger.debug("IOException: {}", ioe.getMessage());
      }
    }
  }
}
