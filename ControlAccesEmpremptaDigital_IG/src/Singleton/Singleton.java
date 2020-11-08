package Singleton;

import Controllers.*;

import java.nio.file.Files;

public class Singleton {

    // PhoneNumbersController
    private static PhoneNumbersController phoneNumbersController;

    public static PhoneNumbersController GetPhoneNumbersController(){
        if (phoneNumbersController == null)
        {
            phoneNumbersController = new PhoneNumbersController();
        }
        return phoneNumbersController;
    }

    // PagesController
    private static PagesController pagesController;
    public static PagesController GetPagesController(){
        if (pagesController == null)
        {
            pagesController = new PagesController();
        }
        return pagesController;
    }

    // LanguagesController
    private static LanguagesController languagesController;
    public static LanguagesController GetLanguagesController(){
        if (languagesController == null)
        {
            languagesController = new LanguagesController();
        }
        return languagesController;
    }

    // NotificationsController
    private static NotificationsController notificationsController;
    public static NotificationsController GetNotificationsController(){
        if (notificationsController == null)
        {
            notificationsController = new NotificationsController();
        }
        return notificationsController;
    }

    // FilesController
    private static FilesController filesController;
    public static FilesController GetFilesController(){
        if (filesController == null)
        {
            filesController = new FilesController();
        }
        return filesController;
    }

}
