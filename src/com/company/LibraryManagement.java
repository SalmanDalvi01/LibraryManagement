package com.company;

import java.util.Scanner;

class LibraryInventory
{
    Scanner sc = new Scanner(System.in);

    // Pause Method
    public void Pause()
    {
        System.out.println(">> Press Any Key To Continue");
        sc.nextLine();
    }

    // Main menu loop for the library
    public void Menu(String [] issueBook,String [] book,int [] issueIndex)
    {
        LibraryTransaction l = new LibraryTransaction();
        int choice = 0;

        // Loop until user chooses to exit
        while(choice != 6)
        {
            System.out.println("=================================");
            System.out.println("          HEAVEN LIBRARY         ");
            System.out.println("=================================");
            System.out.println();
            System.out.println("************* MENU **************");
            System.out.println("1. ADD BOOK");
            System.out.println("2. SHOW AVAILABLE BOOKS");
            System.out.println("3. ISSUE BOOK");
            System.out.println("4. SHOW ISSUED BOOKS");
            System.out.println("5. RETURN BOOK");
            System.out.println("6. EXIT");
            System.out.println("Choice: ");

            choice = sc.nextInt();
            sc.nextLine();

            int starting_index = 0; // Start adding new books from index 5
            int slot_count = 0;

            for (int i = 0; i < book.length; i++)
            {
                if(book[i] == null)
                {
                    starting_index = i;
                    break;
                }
            }

            if(choice == 1)
            {
                // Count how many empty slots are available for new books
                for (int i = starting_index; i < book.length; i++)
                {
                    if(book[i] == null)
                    {
                        slot_count +=1;
                    }
                }
                addBook(book,slot_count); // Call method to add books
            }
            else if (choice == 2)
            {
                showAvailableBook(book); // Display all books currently in the library
            }
            else if(choice == 3)
            {
                l.IssueBook(issueBook,book,issueIndex);
            }
            else if(choice == 4)
            {

                l.issuedBook(issueBook);
            }
            else if(choice == 5)
            {
                l.returnBook(book,issueBook,issueIndex);
            }
            else if(choice == 6)
            {
                System.out.println("Exiting Library... Thank You!");
            }
        }
    }

    // Method to add new books to the library
    public void addBook(String [] book, int slot_count)
    {
        int bookAddCount = 0;

        if(slot_count != 0)
        {
            System.out.println("You Can Add Up To [ " + slot_count + " ] More Books To The Library\n");
        }

        if(slot_count == 0)
        {
            System.out.println("Library Is Full!");
        }
        else
        {
            boolean invalidInput = false;
            int BookAdding = 0;

            // Loop to ensure valid number of books is entered
            while(!invalidInput)
            {
                System.out.println("How Many Book You Want To Add:- ");
                BookAdding = sc.nextInt();
                sc.nextLine();
                bookAddCount = BookAdding;

                if(BookAdding > slot_count)
                {
                    System.out.println("Invalid Input! You Can Only Add Up To [ " + slot_count + " ] Books.Please Enter Again\n");
                }
                else if(BookAdding == 0 || BookAdding < 0)
                {
                    System.out.println(">> At Least One Book Must Be Entered. Adding Zero Books Is Not Allowed!! <<\n");
                }
                else
                {
                    invalidInput = true;
                }
            }

            int count = 0;

            // Loop to take book names from user and store in array
            for (int i = 10; i < book.length; i++)
            {
                if(book[i] != null)
                {
                    continue;
                }
                else
                {
                    System.out.println("Name Of The Book: ");
                    book[i] = sc.nextLine();
                    count += 1;

                    if(count == BookAdding)
                    {
                        break; // Stop after adding the required number of books
                    }
                }
            }
        }

        // Success messages after adding books
        if(bookAddCount == 1)
        {
            System.out.println("Book Added Successfully!");
        }
        else if(bookAddCount > 1)
        {
            System.out.println("Books Added Successfully!");
        }

        Pause();
    }

    // Method to display all available books
    public void showAvailableBook(String [] book)
    {
        String status = "|Status|";
        String book_no = "|No.|";
        String Book_Name = "|Book Names|";
        System.out.println("=================================");
        System.out.println("-------- AVAILABLE BOOKS --------");
        System.out.println();
        System.out.printf(" %-9s  %-29s  %-10s \n", "|No|", "|Book Name|", "|Status|");
        System.out.print("-------------------------------------------------------");


        System.out.println();
        // Loop to print all non-empty books
        for (int i = 0; i < book.length; i++)
        {
            if(book[i] == null || book[i].isEmpty())
            {
                continue;
            }
            else
            {
                if(book[i].contains("(Issued)"))
                {
                    System.out.printf("Book %-3d: %-32s %s\n",
                            (i + 1),
                            book[i].replace("(Issued)", "").trim(),
                            "(Issued)");
                }
                else
                {
                    System.out.printf(
                            "Book %-3d: %-32s %s\n",
                            (i + 1),
                            book[i],
                            "Available"
                    );
                }
            }
        }
        System.out.println("=================================");

        Pause();
    }
}


class LibraryTransaction extends LibraryInventory
{
    public void IssueBook(String [] issueBook,String [] book,int [] issueIndex)
    {
        int no_issueBook = 0;
        int bookNo = 0;
        int slot = 0;
        boolean show = true;

        System.out.println("=================================");
        System.out.println("-------- AVAILABLE BOOKS --------");
        System.out.println();
        System.out.printf(" %-9s  %-29s  %-10s \n", "|No|", "|Book Name|", "|Status|");
        System.out.print("-------------------------------------------------------");
        System.out.println();

        // Loop to print all non-empty books
        for (int i = 0; i < book.length; i++)
        {
            if(book[i] == null || book[i].isEmpty())
            {
                continue;
            }
            else
            {
                if(book[i].contains("(Issued)"))
                {
                    System.out.printf("Book %-3d: %-32s %s\n",
                            (i + 1),
                            book[i].replace("(Issued)", "").trim(),
                            "(Issued)");
                }
                else
                {
                    System.out.printf(
                            "Book %-3d: %-32s %s\n",
                            (i + 1),
                            book[i],
                            "Available"
                    );
                }
            }
        }
        System.out.println("=================================");


        for (int s = 0; s < issueBook.length; s++)
        {
            if(issueBook[s] == null)
            {
                slot += 1;
            }
        }

        if(slot == 0)
        {
            System.out.println("You Cannot Issue Any More Books. The Limit Is Full!");
            show = false;
        }
        else
        {
            if(show)
            {
                System.out.println();
                System.out.println("[ You Can Issue Up To A Maximum Of [ " + slot +  " ] Books At A Time ]\n");
            }
            while(no_issueBook <= 0 || no_issueBook > slot)
            {
                System.out.println(">> How Many Books Do You Want To Issue: ");
                no_issueBook = sc.nextInt();

                if(no_issueBook == 0 || no_issueBook < 0)
                {
                    System.out.println("Invalid! At Least One Book Must Be Issued");
                    System.out.println("=================================");
                }
                else if (no_issueBook > slot)
                {
                    System.out.println("You Cannot Issue More Books Than The Allowed Limit.\n");
                    sc.nextLine();
                }
                else
                {
                    for (int i = 0; i < no_issueBook; i++)
                    {
                        System.out.print("Enter The Number Of The Book To Issue: ");
                        bookNo = sc.nextInt();

                        if(bookNo < 1 || bookNo > book.length)
                        {
                            System.out.println("Invalid Number!");
                            i--;
                            continue;
                        }

                        if(book[bookNo - 1] == null)
                        {
                            System.out.println("No Book Exists At This Number.");
                            i--;
                            continue;
                        }
                        else if ( book[bookNo - 1].contains("(Issued)"))
                        {
                            System.out.println("This Book Is Already Issued!");
                            i--;
                            continue;
                        }

                        for (int j = 0; j < issueBook.length; j++)
                        {
                            if(issueBook[j] == null)
                            {
                                issueBook[j] = book[bookNo - 1];
                                issueIndex[j] = bookNo - 1;

                                System.out.println("The Book Has Been Successfully Issued!");
                                book[bookNo - 1] = book[bookNo - 1] + "   (Issued)";
                                break;
                            }

                        }

                    }
                    sc.nextLine();
                    break;
                }
                System.out.println();
            }
        }
        Pause();

    }


    public void issuedBook(String [] issuedBook)
    {
        boolean anyIssued = false;
        System.out.println("=================================");
        System.out.println("-------- ISSUED BOOKS --------");
        System.out.println();
        for (int i = 0; i < issuedBook.length; i++)
        {
            if(issuedBook[i] == null || issuedBook[i].isEmpty())
            {
                continue;
            }
            else
            {
                System.out.println("Book " + (i+1)+": "+issuedBook[i]);
                anyIssued = true;
            }
        }

        if(!anyIssued)
        {
            System.out.println("------- No Book Issued -------");
        }

        System.out.println("=================================");

        Pause();

    }


    public void returnBook(String [] book,String [] issuedBook,int [] issueIndex)
    {
        boolean anyIssued = false;
        boolean show = true;
        int no_books = 0;

        System.out.println("=================================");
        System.out.println("-------- ISSUED BOOKS --------");
        System.out.println();
        for (int i = 0; i < issuedBook.length; i++)
        {
            if(issuedBook[i] == null || issuedBook[i].isEmpty())
            {
                continue;
            }
            else
            {
                System.out.println("Book " + (i + 1)+": "+issuedBook[i]);
                anyIssued = true;
            }
        }


        if(!anyIssued)
        {
            System.out.println("------- No Book Issued -------");
            show = false;
        }


        System.out.println("=================================");

        if(show)
        {
            System.out.println("Enter How Many Books You Want To Return: ");
            no_books = sc.nextInt();
            sc.nextLine();
        }

        for (int i = 0; i < no_books; i++)
        {
            System.out.println("Please Provide The Number Of The Book You Want To Return: ");
            int temp= sc.nextInt();
            int book_no = temp - 1;

            int originalIndex = issueIndex[book_no];

            book[originalIndex] = book[originalIndex].replace("(Issued)","").trim();
            issuedBook[book_no] = null;
            issueIndex[book_no] = -1;

            System.out.println("Book Returned Successfully: [ " + book[originalIndex] + " ]");
            sc.nextLine();
        }
        Pause();
    }

}

// Main class to run the library program
public class LibraryManagement {
    public static void main(String[] args) {

        String [] book = new String[20];
        String [] issueBook = new String[20];
        int [] issueIndex = new int[20];

        for(int i = 0; i < issueIndex.length; i++)
        {
            issueIndex[i] = -1;
        }


        // Pre-filled initial books in the library
        book[0] = "Atomic Habits";
        book[1] = "The Kite Runner";
        book[2] = "Man’s Search for Meaning";
        book[3] = "The Little Prince";
        book[4] = "Rich Dad Poor Dad";
        book[5] = "To Kill a Mockingbird";
        book[6] = "The Power Of habit";
        book[7] = "Educated";
        book[8] = "The Alchemist";
        book[9] = "Thinking, Fast and Slow";


        LibraryTransaction l = new LibraryTransaction();

        l.Menu(issueBook,book,issueIndex); // Start the menu loop

    }
}