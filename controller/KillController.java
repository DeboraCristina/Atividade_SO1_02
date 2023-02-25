package controller;


import javax.swing.*;
import java.io.*;

public class KillController
{
    public KillController()
    {
        super();
    }
    private String os()
    {
        return System.getProperty("os.name");
    }
    public void listaProcessos()
    {
        String cmd_unix = "ps -ef";
        String cmd_win = "TASKLIST /FO TABLE";
        String[] result = new String[0];

        if (is_linux())
            result = callProcess(cmd_unix).split("\n");
        else if (is_windows())
            result = callProcess(cmd_win).split("\n");
        for (String line : result)
            System.out.println(line);
    }
    public void mataPid(int p_id)
    {
        String cmd_unix = "kill -9 " + p_id;
        String cmd_win = "TASKKILL /PID " + p_id;

        if (is_linux())
            callProcess(cmd_unix);
        else if (is_windows())
            callProcess(cmd_win);
    }
    public void mataNome(String p_name)
    {
        String cmd_unix = "pkill -f " + p_name;
        String cmd_win = "TASKKILL /IM " + p_name;

        if (is_linux())
            callProcess(cmd_unix);
        else if (is_windows())
            callProcess(cmd_win);
    }

    private boolean is_linux()
    {
        String systemName = os().toLowerCase();
        return (systemName.contains("linux"));
    }


    private boolean is_windows()
    {
        String systemName = os().toLowerCase();
        return (systemName.contains("windows"));
    }


    private String callProcess(String process)
    {
        try
        {
            Process p = Runtime.getRuntime().exec(process);
            InputStream flow = p.getInputStream();
            InputStreamReader reader = new InputStreamReader(flow);
            BufferedReader buffer = new BufferedReader(reader);

            String line = buffer.readLine();
            StringBuffer lines = new StringBuffer();

            while (line != null)
            {
                lines.append(line);
                lines.append("\n");
                line = buffer.readLine();
            }
            flow.close();
            reader.close();
            buffer.close();
            return (lines.toString());
        }
        catch (Exception e)
        {
            String msg_error = e.getMessage();
            System.err.println(msg_error);
        }
        return ("");
    }
}
