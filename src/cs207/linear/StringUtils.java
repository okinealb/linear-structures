package cs207.linear;

import java.io.PrintWriter;

/**
 * Assorted utilities for working with strings.
 *
 * @author Samuel A. Rebelsky
 * @author Albert-Kenneth Okine
 */
public class StringUtils {
  // +------------------+--------------------------------------------
  // | Provided methods |
  // +------------------+

  /**
   * Determine whether the parens match in string.
   */
  public static boolean checkMatching(String str) {
    Stack<Character> parens = new LinkedStack<Character>();

    for (char ch : str.toCharArray()) {
        
        if (ch == '(' || ch == '[') {
            try {
                parens.put(ch);
            } catch (Exception e) {
                return false;
            } // try...catch
        } else if (ch == ')' || ch == ']') {
            try {
                char chNew = parens.get();
                if ((ch == ')' && (chNew != '('))
                    || (ch == ']' && chNew != '['))
                {
                    return false;
                } // if (not matching closing parenthesis)
            } catch (Exception e) {
                return false;
            } // try...catch

        } // if (opening or closing parenthesis)
    } // for (each char ch in str)

    return parens.isEmpty(); // check whether all parentheses matched
  } // checkMatching

  /** Provide a nice picture of the matching parens. */
  public static void printPicture(PrintWriter pen, String str) {
    Stack<Integer>indices = new LinkedStack<Integer>();

    pen.println(str);

    for (int i = 0; i < str.length(); i ++) {
        char ch = str.charAt(i);
        
        if (ch == '(' || ch == '[') {
            try {
                indices.put(i);
            } catch (Exception e) {
                // will never execute, we know str is valid
                pen.println(e);
            } // try...catch
        } else if (ch == ')' || ch == ']') {
            try {
                int index = indices.get();
                pen.println(" ".repeat(index)
                            + "+" + "-".repeat(i - index - 1) + "+"
                            + " ".repeat(str.length() - i - 1));
            } catch (Exception e) {
                // will never execute, we know str is valid
                pen.println(e);
            } // try...catch
        } // else if (opening or closing parens.)
    } // for (each index in str)
  } // printPicture(String)

  // +-------------+-------------------------------------------------
  // | Experiments |
  // +-------------+
  /**
   * A quick set of experiments with checkMatching.
   */
  static void checkMatchingExperiments(PrintWriter pen) {
    checkMatchingExperiment(pen, "");
    checkMatchingExperiment(pen, "()");
    checkMatchingExperiment(pen, "(");
    checkMatchingExperiment(pen, ")");
    checkMatchingExperiment(pen, "[]()");
    checkMatchingExperiment(pen, "[()([])]");
    checkMatchingExperiment(pen, "[a(b]c)");
    checkMatchingExperiment(pen, "Hello (there) (world (!!))");
    checkMatchingExperiment(pen, "alphabetical");
    checkMatchingExperiment(pen, "((((((((a))))))))");
    checkMatchingExperiment(pen, "((((((((a)))))]))");
    checkMatchingExperiment(pen, "(([((((((a)))))]))");
    checkMatchingExperiment(pen, "(([((((((a))))))])");
    checkMatchingExperiment(pen, "((((b)(((((a)(c)))(d))))))");
    checkMatchingExperiment(pen, "(oh (boy) (I am having) ((so) much) fun matching (parens))");
    // Feel free to add your own
  } // PrintWriter()

  /**
   * A single experiment with checkMatching.
   */
  static void checkMatchingExperiment(PrintWriter pen, String str) {
    //pen.print("checkMatching(\"" + str + "\") = ");
    pen.flush();
    try {
        //pen.println(checkMatching(str));
        if (checkMatching(str)) printPicture(pen, str);
    } catch (Exception e) {
      pen.println("*** ERROR *** " + e.toString());
    }

  } // checkMatchingExperiment(PrintWriter, String)

  // +------+--------------------------------------------------------
  // | Main |
  // +------+

  /**
   * Run a few experiments.
   */
  public static void main(String[] args) {
    PrintWriter pen = new PrintWriter(System.out, true);
    checkMatchingExperiments(pen);
    pen.close();
  } // main(String[])
} // class StringUtils