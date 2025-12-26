package raf.graffito.dsw.utils;

public class FolderPreferences {
    private static String defaultFolderPath = null;

    public static String getDefaultFolderPath() {
        return defaultFolderPath;
    }

    public static void setDefaultFolderPath(String folderPath) {
        defaultFolderPath = folderPath;
    }
}
