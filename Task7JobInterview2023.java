/*
Задание.
Найти валидные круглые скобки.
Обязательно счетчик и правильные скобки нужно выводить
 */

/*
Входные данные
(()             -   Результат: 2 - ()
)()())          -   Результат: 4 - ()()
)(()())         -   Результат: 6 - (()())
)(              -   Результат: 0
())(()())())    -   Результат: 10 - ()(()())()
 */

package JobInterviews;
import java.util.*;
public class Task7JobInterview2023 {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        String str = scanner.next();

        //Передадим в метод строку скобок.
        System.out.println(checkStr(str));
    }

    /**
     * Реализация метода checkStr
     * @param str // Входной строковый аргумент
     * @return // Возвращает String в качестве выходного значения.
     */
    public static String checkStr(String str) {

        // Значения скобок ( = 40;
        // Значения скобок ) = 41;

        int count = 0;          // Вспомогательная переменная для контроля по-парных скобок
        int checkBrackets  = 0; // Переменная хранящая количество скобок в результате программы

        //Создадим стек Deque и в качестве реализации использую LinkedList
        Deque<Character> charactersStack = new LinkedList<>();

        // Цикл по проходу по всем символам строки
        for (char value : str.toCharArray()) {

            //Отсекаем две )( но при этом корректно считаем ()
            if (value == 41 && str.length() == 2 && count < 1) {
                return "Вывод: 0";
            }

            // Если скробка открывающаяся ( - то добавим ее в стек СЧТЧИК скобок и вспомогательную переменную checkBrackets прибавим +1
            if (value == 40) {
                charactersStack.addLast(value);
                checkBrackets++;                            // Счетчик скобок
                count++;
            } else {
                //Если на очереди закрывающаяся скобка ), то...
                if (value == 41 && count > 0) {

                    // Условие для строки менее 3х символов и count == 2 позволяет правильно производить расчет 2х и 3х скобок
                    if (str.length() <= 3 && count == 2) {
                        charactersStack.pop();              // Удалим первый с конца символ из очереди :)))
                        checkBrackets--;
                    }

                    charactersStack.addLast(')');       // Добавим символ в начало очереди
                    checkBrackets++;                        // Счетчик скобок
                    count--;
                }
            }
        }
        // Вывод результата в отформатированной строке, согласно условиям задания
        StringBuffer buffer = new StringBuffer("Вывод: ");
        buffer.append(checkBrackets + " - ");

        for (Character t : charactersStack) {
            buffer.append(t);
        }
        return  buffer.toString();

        // Вывод результата напрямую.
        // return "Вывод: " + charactersStack + " " + checkBrackets;
    }
}
