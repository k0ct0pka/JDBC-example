package util;

import configure.ConnectionsPoolConfig;
import org.yaml.snakeyaml.LoaderOptions;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;
import org.yaml.snakeyaml.inspector.TagInspector;
import org.yaml.snakeyaml.nodes.Tag;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

public class ConfigReader {
    public ConnectionsPoolConfig readDataBaseConfig() {
        LoaderOptions loader = new LoaderOptions();
        TagInspector tagInspector = tag-> tag.getClassName().equals(ConnectionsPoolConfig.class.getName());
        loader.setTagInspector(tagInspector);
        Yaml yaml = new Yaml(new Constructor(ConnectionsPoolConfig.class, loader));
        InputStream inputStream = ConfigReader.class.getClassLoader().getResourceAsStream("database_config.yaml");
        return yaml.load(inputStream);
    }
}
