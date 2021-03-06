package com.galileo.core.service.http;

import java.io.IOException;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.galileo.core.Wallet;
import com.galileo.protos.protos.Contract.WitnessCreateContract;
import com.galileo.protos.protos.Protocol.Transaction;
import com.galileo.protos.protos.Protocol.Transaction.Contract.ContractType;


@Component
@Slf4j
public class CreateWitnessServlet extends HttpServlet {

  @Autowired
  private Wallet wallet;

  protected void doGet(HttpServletRequest request, HttpServletResponse response) {

  }

  protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    try {
      String contract = request.getReader().lines()
          .collect(Collectors.joining(System.lineSeparator()));
      WitnessCreateContract.Builder build = WitnessCreateContract.newBuilder();
      JsonFormat.merge(contract, build);
      Transaction tx = wallet
          .createTransactionCapsule(build.build(), ContractType.WitnessCreateContract)
          .getInstance();
      response.getWriter().println(Util.printTransaction(tx));
    } catch (Exception e) {
      logger.debug("Exception: {}", e.getMessage());
      try {
        response.getWriter().println(Util.printErrorMsg(e));
      } catch (IOException ioe) {
        logger.debug("IOException: {}", ioe.getMessage());
      }
    }
  }
}