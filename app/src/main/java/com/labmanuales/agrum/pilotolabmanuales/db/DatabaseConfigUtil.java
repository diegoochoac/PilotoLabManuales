package com.labmanuales.agrum.pilotolabmanuales.db;

import com.j256.ormlite.android.apptools.OrmLiteConfigUtil;

import java.io.IOException;
import java.sql.SQLException;

/**
 * Created by diego on 26/08/16.
 */
public class DatabaseConfigUtil extends OrmLiteConfigUtil {

    public static void main(String[] args) throws IOException, SQLException {
        writeConfigFile("ormlite_config.txt");
    }
}