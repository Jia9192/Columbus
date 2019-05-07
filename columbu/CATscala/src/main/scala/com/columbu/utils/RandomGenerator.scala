package com.galileo.utils

import java.util.List

import org.slf4j.Logger
import org.slf4j.LoggerFactory

class RandomGenerator[T] {
  
  private val logger : Logger = LoggerFactory.getLogger(getClass())
  
  
  def shuffle(list : List[T], time : Long) : List[T] = {
    var headBlockTimeHi : Long = time << 32
    
    for (i <- 0 until list.size) {
      var v : Long = headBlockTimeHi + i * RandomGenerator.RANDOM_GENERATOR_NUMBER
      v = v ^ (v >> 12)
      v = v ^ (v << 25)
      v = v ^ (v >> 27)
      v = v * RandomGenerator.RANDOM_GENERATOR_NUMBER

      var temp : Long = v % (list.size - i)
      var index : Int = (i + temp).asInstanceOf[Int]
      
      if (index < 0 || index >= list.size) {
        logger.warn("index[" + index + "] is out of range[0," + (list.size() - 1) + "],skip");
//        continue;
      }
      var tmp : T = list.get(index)
      list.set(index, list.get(i));
      list.set(i, tmp);
    }
    return list
  }
  
}

object RandomGenerator {
  
  private var RANDOM_GENERATOR_NUMBER : Long = 2685821657736338717L
  
}


