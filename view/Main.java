package view;


import controller.KillController;

import javax.swing.*;


public class Main
{
    public static void main(String[] args)
    {
        KillController k_cont = new KillController();

        boolean end = false;

        do
        {
            String answer = menuOption();
            answer = answer == null? "3":answer;

            try
            {
                int option = Integer.parseInt(answer);

                switch (option)
                {
                    case 1 -> k_cont.listaProcessos();
                    case 2 -> matar();
                    case 3 -> {
                        finalMessage();
                        end = true;
                    }
                    default -> errorMessage();
                }
            }
            catch (Exception e)
            {
                errorMessage();
                System.err.println(e.getMessage());
                end = true;
            }

        } while (!end);
    }

    static void matar()
    {
        KillController k_cont = new KillController();
        String answer;
        String msg;

        msg = "Id process\n[ID or Name]";
        answer = JOptionPane.showInputDialog(null, msg,
                "Activity 02", JOptionPane.QUESTION_MESSAGE);

        try
        {
            int op = Integer.parseInt(answer);
            k_cont.mataPid(op);
        }
        catch (Exception e)
        {
            k_cont.mataNome(answer);
        }
    }

    static String menuOption()
    {
        String menu;
        String msg = "1 - Show Process\n2 - Kill Process\n3 - Exit";
        menu = JOptionPane.showInputDialog(null, msg, "Activity 01",
                JOptionPane.PLAIN_MESSAGE);
        return (menu);
    }


    static void errorMessage()
    {
        JOptionPane.showMessageDialog(null, "Invalid Option",
                "ERROR", JOptionPane.ERROR_MESSAGE);
    }


    static void finalMessage()
    {
        JOptionPane.showMessageDialog(null, "Program Finished",
                "End", JOptionPane.PLAIN_MESSAGE);
    }
}
