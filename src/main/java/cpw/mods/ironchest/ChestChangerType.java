/*******************************************************************************
 * Copyright (c) 2012 cpw. All rights reserved. This program and the accompanying materials are made available under the
 * terms of the GNU Public License v3.0 which accompanies this distribution, and is available at
 * http://www.gnu.org/licenses/gpl.html
 *
 * Contributors: cpw - initial API and implementation
 ******************************************************************************/
package cpw.mods.ironchest;

import static cpw.mods.ironchest.IronChestType.COPPER;
import static cpw.mods.ironchest.IronChestType.CRYSTAL;
import static cpw.mods.ironchest.IronChestType.DIAMOND;
import static cpw.mods.ironchest.IronChestType.GOLD;
import static cpw.mods.ironchest.IronChestType.IRON;
import static cpw.mods.ironchest.IronChestType.OBSIDIAN;
import static cpw.mods.ironchest.IronChestType.STEEL;
import static cpw.mods.ironchest.IronChestType.WOOD;

import net.minecraftforge.common.config.Configuration;

import cpw.mods.fml.common.registry.GameRegistry;

public enum ChestChangerType {

    IRONGOLD(IRON, GOLD, "ironGoldUpgrade", "Iron to Gold Chest Upgrade", "mmm", "msm", "mmm"),
    GOLDDIAMOND(GOLD, DIAMOND, "goldDiamondUpgrade", "Gold to Diamond Chest Upgrade", "GGG", "msm", "GGG"),
    COPPERSTEEL(COPPER, STEEL, "copperSteelUpgrade", "Copper to Steel Chest Upgrade", "mmm", "msm", "mmm"),
    STEELGOLD(STEEL, GOLD, "steelGoldUpgrade", "Steel to Gold Chest Upgrade", "mGm", "GsG", "mGm"),
    COPPERIRON(COPPER, IRON, "copperIronUpgrade", "Copper to Iron Chest Upgrade", "mGm", "GsG", "mGm"),
    DIAMONDCRYSTAL(DIAMOND, CRYSTAL, "diamondCrystalUpgrade", "Diamond to Crystal Chest Upgrade", "GGG", "GOG", "GGG"),
    WOODIRON(WOOD, IRON, "woodIronUpgrade", "Normal chest to Iron Chest Upgrade", "mmm", "msm", "mmm"),
    WOODCOPPER(WOOD, COPPER, "woodCopperUpgrade", "Normal chest to Copper Chest Upgrade", "mmm", "msm", "mmm"),
    DIAMONDOBSIDIAN(DIAMOND, OBSIDIAN, "diamondObsidianUpgrade", "Diamond to Obsidian Chest Upgrade", "mmm", "mGm",
            "mmm");

    private final IronChestType source;
    private final IronChestType target;
    public final String itemName;
    public final String descriptiveName;
    private ItemChestChanger item;
    private final String[] recipe;

    private ChestChangerType(IronChestType source, IronChestType target, String itemName, String descriptiveName,
            String... recipe) {
        this.source = source;
        this.target = target;
        this.itemName = itemName;
        this.descriptiveName = descriptiveName;
        this.recipe = recipe;
    }

    public boolean canUpgrade(IronChestType from) {
        return from == this.source;
    }

    public int getTarget() {
        return this.target.ordinal();
    }

    public ItemChestChanger buildItem(Configuration cfg) {
        item = new ItemChestChanger(this);
        GameRegistry.registerItem(item, itemName);
        return item;
    }

    public void addRecipes() {

    }

    public static void buildItems(Configuration cfg) {
        for (ChestChangerType type : values()) {
            type.buildItem(cfg);
        }
    }

    public static void generateRecipes() {
        for (ChestChangerType item : values()) {
            item.addRecipes();
        }
    }
}
