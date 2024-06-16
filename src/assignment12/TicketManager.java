package assignment12;

import java.util.Scanner;
import java.util.Stack;

class Ticket{
    public String PIB;
    public int flight_number;
    public int price;
    public String appointment;
    public String flight_data;
    public String flight_time;
    public String flight_class;

}
public class TicketManager {
    private Stack<Ticket> stack = new Stack<>();

    //todo
    public static void main(String[] args) {
        //menu
        int information;


        System.out.println("+--------------------------------------------------------------------------------------+");
        System.out.println("|               Доброго дня Вас вітає термінал керування квитками                      |");
        System.out.println("|                  Цей програмний код виконав Tatchuk Nikita                           |");
        System.out.println("|        Програма створена для керуваня квитками, дані зберігаються у стек.            |");
        System.out.println("|                      Ось всі дії які передбачуні програмою:                          |");
        while (true)
        {

            System.out.println("+--------------------------------------------------------------------------------------+");
            System.out.println("| Натисніть 1 | якщо хочете додати квиток                                              |");
            System.out.println("| Натисніть 2 | якщо хочете завантажити квитки з файлу                                 |");
            System.out.println("| Натисніть 3 | якщо хочете видалити квиток                                            |");
            System.out.println("| Натисніть 4 | якщо хочете вивести всю інфомацію о квитках                            |");
            System.out.println("| Натисніть 5 | якщо хочете вивести  всю інфомацію о квитках за датою                  |");
            System.out.println("| Натисніть 6 | якщо хочете змінити у всіх пасажирів клас                              |");
            System.out.println("| Натисніть 7 | якщо хочете оновити дані                                               |");
            System.out.println("| Натисніть 8 | якщо хочете вивести елемента з найменшою ціною за заданим призначенням |");
            System.out.println("| Натисніть 9 | відсортувати по полю ціна                                              |");
            System.out.println("| Натисніть 10| якщо хочете змінити у даного пасажира клас                             |");
            System.out.println("| Натисніть 0 | якщо хочете вийти з теміналу                                           |");
            System.out.println("+--------------------------------------------------------------------------------------+");

            information = getNumber();

            switch (information)
            {
                case 1:
                    addTicket();
                    break;
                case 2:
                    fileRead();
                    break;
                case 3:
                    //Видалення квитка
                    break;
                case 4:
                    printStack();
                    break;
                case 5:
                    printTicketByDate();
                    break;
                case 6:
                    changeAllTicket();
                    break;
                case 7:
                    updateTickets();
                    break;
                case 8:
                    getEntityBySmallPrice();
                    break;
                case 9:
                    sortStackByPrice();
                    break;
                case 10:
                    changeCurrentTicket();
                    break;
                case 0:
                    /*
                    System.out.println("+--------------------------------------------------------------------------------------+");
                    System.out.println("| Чи хочете Ви зберегти всі данні в файл?y/n                                           |");
                    System.out.println("+--------------------------------------------------------------------------------------+");
                    scanf("%s",&data);
                    if(strcmp(data, "y") == 0)
                    {
                        fileWrite();
                    }
                    System.out.println("+--------------------------------------------------------------------------------------+");
                    System.out.println("|                             Програму завершено                                       |");
                    System.out.println("+--------------------------------------------------------------------------------------+");
                    return;*/

                default:
                    System.out.println("+--------------------------------------------------------------------------------------+");
                    System.out.println("|                   Opps, нажаль в нашому списку ще немає такої дії                    |");
                    break;
            }
        }
    }



    private static void changeCurrentTicket() {
    }

    private static void sortStackByPrice() {
    }

    private static void getEntityBySmallPrice() {
    }

    private static void updateTickets() {
    }

    private static void changeAllTicket() {
    }

    private static void printTicketByDate() {
    }

    private static void printStack() {
    }

    private static void fileRead() {
    }
    private static void fileWrite() {
    }

    private static void addTicket(){
        
    }
    private static int getNumber(){
        int number;
        try(Scanner scanner = new Scanner(System.in)){
            number = scanner.nextInt();

        }
        return number;
    }

}

