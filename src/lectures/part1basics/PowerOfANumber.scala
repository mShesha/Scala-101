package lectures.part1basics

object PowerOfANumber extends App {

  try {

    def p(num: Int, power: Int, result: Int = 1): Int = {
      if (power == 0)
        result
      else p(num, power - 1, num * result)
    }

    print(p(3,3))
  }




}
