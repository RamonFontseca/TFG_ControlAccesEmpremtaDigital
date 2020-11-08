package Controllers;

import Model.Code;
import Model.Language;
import Model.PhoneNumber;
import Singleton.Singleton;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class LanguagesController {
    private ArrayList<Language> languagesList;

    public LanguagesController()
    {
        languagesList = new ArrayList<>();
        InitData();
    }

    public void InitData()
    {
        LoadLanguages();
    }

    public void LoadLanguages()
    {
        LoadLanguagesFromFile();
    }

    public void LoadLanguagesFromFile()
    {
        try {
            File file = new File(Singleton.GetFilesController().languagesFilePath);
            Singleton.GetFilesController();
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                String data = scanner.nextLine();
                System.out.println(data);
                var a = data.split(",");
                String part1 = a[0];
                String part2 = a[1];
                String part3 = a[2];
                Language lang = new Language(part1, part2, Boolean.parseBoolean(part3));
                languagesList.add(lang);
            }
            scanner.close();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void Update(Language language)
    {
        if (language != null && languagesList.contains(language)){
            int index = languagesList.indexOf(language);
            if (index != -1)
            {
                language.changeState();
                languagesList.set(index, language);
                languagesList.get(index).setEnabledDescription(language.IsEnabled());
            }
        }
    }

    public void UpdateStateFalseLessLanguage(Language language){
        for (Language lang: languagesList) {
            if (!lang.equals(language)){
                lang.setIsEnabled(false);
                lang.setEnabledDescription(false);
            }
        }
    }

    public ArrayList<Language> GetLanguages(){
        return languagesList;
    }

}




