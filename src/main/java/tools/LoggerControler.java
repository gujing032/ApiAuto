package tools;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Created by weye on 15/11/6.
 */
public final class LoggerControler {
    // 设置配置文件路径
    private static String path = System.getProperty("user.dir") + File.separator + "src" + File.separator + "main" + File.separator + "resources" + File.separator;
    private static Logger logger = null;
    private static LoggerControler logg = null;

    public static LoggerControler getLogger(Class<?> T) {
        if (logger == null) {
            Properties props = new Properties();

            try {
                String envPaht = path + "log4j.properties";
                InputStream is = new FileInputStream(envPaht);
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }

            PropertyConfigurator.configure(props);
            logger = Logger.getLogger(T);
            logg = new LoggerControler();
        }
        return logg;
    }

    /**
     * 重写logger方法
     */
    public void info(String msg) {
        logger.info(msg);
    }

    public void info(Object msg) {
        logger.info(JsonFormatTool.formatJson(String.valueOf(msg)));
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void error(String msg) {
        logger.error(msg);
    }

    public void error(StringBuilder message) {
        logger.error(message.toString());
    }

    public void info(StringBuilder message) {
        logger.info(message.toString());
    }

    public void warn(StringBuilder message) {
        logger.warn(message.toString());
    }



}
