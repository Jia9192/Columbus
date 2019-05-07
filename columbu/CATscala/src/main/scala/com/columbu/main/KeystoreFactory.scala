package com.galileo.main

import com.beust.jcommander.JCommander
import java.io.File
import java.io.IOException
import java.util.Scanner
import org.apache.commons.lang3.StringUtils
import org.slf4j.LoggerFactory
import com.galileo.encrypt.ECKey
import com.galileo.utils.ByteArray
import com.galileo.utils.Utils
import com.galileo.core.Constant
import com.galileo.core.config.args.Args
import com.galileo.db.keystore.CipherException
import com.galileo.db.keystore.Credentials
import com.galileo.db.keystore.WalletUtils
import util.control.Breaks._

class KeystoreFactory {

  private val logger = LoggerFactory.getLogger(this.getClass())
  private val FilePath: String = "Wallet"

  private def priKeyValid(priKey: String): Boolean = {
    if (StringUtils.isEmpty(priKey)) {
      logger.warn("Warning: PrivateKey is empty !!")
      return false
    }
    if (priKey.length != 64) {
      logger.warn("Warning: PrivateKey length need 64 but " + priKey.length + " !!")
      return false
    }
    //Other rule;
    true
  }

  @throws[CipherException]
  @throws[IOException]
  private def genKeystore() {
    val password: String = WalletUtils.inputPassword2Twice
    val eCkey: ECKey = new ECKey(Utils.random)
    val file: File = new File(FilePath)
    if (!file.exists) if (!file.mkdir) throw new IOException("Make directory faild!")
    else if (!file.isDirectory) if (file.delete) if (!file.mkdir) throw new IOException("Make directory faild!")
    else throw new IOException("File is exists and can not delete!")
    val fileName: String = WalletUtils.generateWalletFile(password, eCkey, file, true)
    System.out.println("Gen a keystore its name " + fileName)
    val credentials: Credentials = WalletUtils.loadCredentials(password, new File(file, fileName))
    System.out.println("Your address is " + credentials.getAddress)
  }

  @throws[CipherException]
  @throws[IOException]
  private def importPrivatekey() {
    val in: Scanner = new Scanner(System.in)
    var privateKey: String = null
    System.out.println("Please input private key.")
    while (true) {
      val input: String = in.nextLine.trim
      privateKey = input.split("\\s+")(0)
      if (priKeyValid(privateKey)){
        break()
      }
      System.out.println("Invalid private key, please input again.")
    }
    val password: String = WalletUtils.inputPassword2Twice
    val eCkey: ECKey = ECKey.fromPrivate(ByteArray.fromHexString(privateKey))
    val file: File = new File(FilePath)
    if (!file.exists) if (!file.mkdir) throw new IOException("Make directory faild!")
    else if (!file.isDirectory) if (file.delete) if (!file.mkdir) throw new IOException("Make directory faild!")
    else throw new IOException("File is exists and can not delete!")
    val fileName: String = WalletUtils.generateWalletFile(password, eCkey, file, true)
    System.out.println("Gen a keystore its name " + fileName)
    val credentials: Credentials = WalletUtils.loadCredentials(password, new File(file, fileName))
    System.out.println("Your address is " + credentials.getAddress)
  }

  private def help() {
    System.out.println("You can enter the following command: ")
    System.out.println("GenKeystore")
    System.out.println("ImportPrivatekey")
    System.out.println("Exit or Quit")
    System.out.println("Input any one of then, you will get more tips.")
  }

  def run() {
    val in: Scanner = new Scanner(System.in)
    help()
    while (in.hasNextLine)
      try{
        val cmdLine: String = in.nextLine.trim
        val cmdArray: Array[String] = cmdLine.split("\\s+")
        // split on trim() string will always return at the minimum: [""]
        val cmd: String = cmdArray(0)
        if ("" != cmd) {
          val cmdLowerCase: String = cmd.toLowerCase
          cmdLowerCase match {
            case "help" => {
              help()
              break()
            }
            case "genkeystore" => {
              genKeystore()
              break()
            }
            case "importprivatekey" => {
              importPrivatekey()
              break()
            }
            case "exit" =>
            case "quit" => {
              System.out.println("Exit !!!")
              in.close()
              return
            }
            case _ => {
              System.out.println("Invalid cmd: " + cmd)
              help()
            }
          }
        }
    }catch {
      case e: Exception => {
        logger.error(e.getMessage)
      }
    }
  }

}

object KeystoreFactory {

  def main(args: Array[String]) {
    Args.setParam(args, Constant.TESTNET_CONF)
    val cli: KeystoreFactory = new KeystoreFactory
    JCommander.newBuilder.addObject(cli).build.parse()
    cli.run()
  }

}
