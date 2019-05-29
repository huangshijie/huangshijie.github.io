# Interview

1. static, final, transient关键字的作用

2. 作用域public,protected,private,以及不写时的区别？

3. 写出预期输出

   ```java
    String s1 = "Hello";
    String s2 = "Hello";
    String s3 = "Hel" + "lo";
    String s4 = "Hel" + new String("lo");
    String s5 = new String("Hello");
    String s6 = s5.intern();
    String s7 = "H";
    String s8 = "ello";
    String s9 = s7 + s8;

    System.out.println(s1 == s2);  
    System.out.println(s1 == s3);  
    System.out.println(s1 == s4);
    System.out.println(s1 == s9);
    System.out.println(s4 == s5);
    System.out.println(s1 == s6);
   ```

4. 多线程中run()与start()的区别。

5. 说出一种设计模式，并且简单写下它的实现。

6. servlet的生命周期。

7. Overload 和 Override 的区别。Overloaded 的方法是否可以改变返回值的类型?

8. 接口和抽象类的区别是什么？

9. Given a 2D board and a word, find if the word exists in the grid.

  The word can be constructed from letters of sequentially adjacent cell, where "adjacent" cells are those horizontally or vertically neighboring. The same letter cell may not be used more than once.

  **Example:**

  ```java
    board =
    [
      ['A','B','C','E'],
      ['S','F','C','S'],
      ['A','D','E','E']
    ]

    Given word = "ABCCED", return true.
    Given word = "SEE", return true.
    Given word = "ABCB", return false.
  ```

  ```java
    class Solution {
        public boolean exist(char[][] board, String word) {

        }
    }
  ```