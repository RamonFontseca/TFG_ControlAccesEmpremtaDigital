package Controllers;

import Model.CodeUsages;
import Model.UserUsages;
import Singleton.Singleton;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class StadisticsController {
    ArrayList<CodeUsages> codesUsagesList;
    ArrayList<UserUsages> usersUsagesList;

    public StadisticsController()
    {
        InitCodesUsagesList();
        InitUsersUsagesList();
    }

    public ArrayList<CodeUsages> GetCodesUsages()
    {
        return codesUsagesList;
    }

    public ArrayList<UserUsages> GetUsersUsages()
    {
        return usersUsagesList;
    }

    private void InitCodesUsagesList()
    {
        codesUsagesList = new ArrayList<CodeUsages>();
        LoadCodeUsages();
    }

    private void InitUsersUsagesList()
    {
        usersUsagesList = new ArrayList<UserUsages>();
        LoadUsersUsages();
    }

    private void LoadCodeUsages()
    {
        LoadCodeUsagesFromFile();
    }

    private void LoadUsersUsages()
    {
        LoadUsersUsagesFromFile();
    }

    private void LoadCodeUsagesFromFile()
    {
        try {
            File file = new File(Singleton.GetFilesController().stadisticsCodesFilePath);
            Singleton.GetFilesController();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
                var a = data.split(",");
                String part1 = a[0];
                String part2 = a[1];
                CodeUsages codesUsage = new CodeUsages(part1, Integer.parseInt(part2));
                codesUsagesList.add(codesUsage);
            }
            scanner.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void LoadUsersUsagesFromFile()
    {
        try {
            File file = new File(Singleton.GetFilesController().stadisticsUsersFilePath);
            Singleton.GetFilesController();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
                var a = data.split(",");
                String part1 = a[0];
                String part2 = a[1];
                UserUsages userUsages = new UserUsages(part1, Integer.parseInt(part2));
                usersUsagesList.add(userUsages);
            }
            scanner.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
