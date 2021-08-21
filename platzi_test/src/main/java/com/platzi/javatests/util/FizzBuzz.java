package com.platzi.javatests.util;

public class FizzBuzz {

    /*Si el número es divisible por 3, retorna “Fizz”
    Si el número es divisible por 5, retorna “Buzz”
    Si el número es divisible por 3 y por 5, retorna “FizzBuzz”
    En otro caso, retorna el mismo número*/

    public static String fizzBuzz(int n) {
        if (n % 3 == 0 && n % 5 == 0) {
            return "FizzBuzz";
        }
          else if (n % 3 == 0 ) {
            return "Fizz";
        } else if (n % 5 == 0) {
            return "Buzz";
        } else return Integer.toString(n);
    }
}
