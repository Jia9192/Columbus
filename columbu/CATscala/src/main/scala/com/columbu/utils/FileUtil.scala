package com.galileo.utils

import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File
import java.io.FileReader
import java.io.FileWriter
import java.io.IOException
import java.nio.file.FileVisitResult
import java.nio.file.FileVisitor
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.nio.file.attribute.BasicFileAttributes
import java.util.ArrayList
import java.util.Arrays
import java.util.List
import java.util.Objects
import java.util.function.Consumer
import java.util.function.Function
import java.util.stream.Stream

//import lombok.extern.slf4j.Slf4j
import org.slf4j.Logger
import org.slf4j.LoggerFactory

object FileUtil {
  
  private val logger : Logger = LoggerFactory.getLogger(getClass())
  
  @throws[IOException]
  def recursiveList(path : String) : List[String] = {
    val files : List[String] = new ArrayList[String]()
    Files.walkFileTree(Paths.get(path), new FileVisitor[Path]() {
      
      override def preVisitDirectory(dir : Path, attrs : BasicFileAttributes) : FileVisitResult = {
        return FileVisitResult.CONTINUE
      }
      
      override def visitFile(file : Path, attrs : BasicFileAttributes) : FileVisitResult = {
        files.add(file.toString())
        return FileVisitResult.CONTINUE
      }
      
      override def visitFileFailed(file : Path, exc : IOException) : FileVisitResult = {
        return FileVisitResult.CONTINUE
      }
      
      override def postVisitDirectory(dir : Path, exc : IOException) : FileVisitResult = {
        return FileVisitResult.CONTINUE
      }
    })
    return files  
  }
  
  def recursiveDelete(fileName : String) : Boolean = {
    var file : File = new File(fileName)
    if (file.exists()) {
      // check if the file is a directory
      if (file.isDirectory()) {
        // call deletion of file individually
        var mapper : Function[String, String] = new Function[String, String]() {
          override def apply(s : String) : String = {
            return fileName + System.getProperty("file.separator") + s
          }
        }
        var action : Consumer[String]  = new Consumer[String]() {
          override def accept(t : String) : Unit = {
            recursiveDelete(t)
          }
        }
        
        var temp :  Stream[String] = Arrays.stream(Objects.requireNonNull(file.list())).map(mapper)
        temp.forEachOrdered(action)
      }
      file.setWritable(true)
      return file.delete()
    }
    return false
  }
  
  def saveData(filePath : String, data : String, append : Boolean) : Unit = {
    var priFile : File = new File(filePath)
    try {
      priFile.createNewFile()
      try {
        var bw : BufferedWriter = new BufferedWriter(new FileWriter(priFile, append))
        bw.write(data)
        bw.flush()
      }
    } catch {
      case e: IOException => logger.debug(e.getMessage(), e)
    }
  }
  
  def readData(filePath : String, buf : Array[Char]) : Int = {
    var len : Int = 0
    var file : File = new File(filePath)
    try {
      var bufRead : BufferedReader = new BufferedReader(new FileReader(file))
      len = bufRead.read(buf, 0, buf.length)
    } catch {
      case ex: IOException => ex.printStackTrace(); return 0
    }
    return len
  }
  
  /**
   * delete directory.
   */
  def deleteDir(dir : File) : Boolean = {
    if (dir.isDirectory()) {
      var children : Array[String] = dir.list()
      for (i <- 0 until children.length) {
        var success : Boolean = deleteDir(new File(dir, children(i)))
        if (!success) {
          logger.warn("can't delete dir:" + dir);
          return false;
        }
      }
    }
    return dir.delete()
  }
  
}