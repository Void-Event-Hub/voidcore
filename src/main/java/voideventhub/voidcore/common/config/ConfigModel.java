package voideventhub.voidcore.common.config;

import io.wispforest.owo.config.annotation.Config;
import io.wispforest.owo.config.annotation.Modmenu;
import voideventhub.voidcore.common.VoidCore;

@Modmenu(modId = VoidCore.MOD_ID)
@Config(name = VoidCore.MOD_ID, wrapperName = "Config")
public class ConfigModel {

    public String mongodbUsername = "";
    public String mongodbPassword = "";

}
