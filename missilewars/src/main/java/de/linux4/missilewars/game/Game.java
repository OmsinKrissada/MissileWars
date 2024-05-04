/*******************************************************************************
 * Copyright (C) 2019 Linux4
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published
 * by the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this program. If not, see <http://www.gnu.org/licenses/>.
 ******************************************************************************/
package de.linux4.missilewars.game;

import java.util.Collection;
import java.util.HashSet;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Color;
import org.bukkit.GameMode;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.attribute.Attribute;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.LeatherArmorMeta;
import org.bukkit.scoreboard.Scoreboard;
import org.bukkit.scoreboard.Team;
import org.bukkit.scoreboard.Team.Option;
import org.bukkit.scoreboard.Team.OptionStatus;

import de.linux4.missilewars.MissileWars;

public class Game {

	public Collection<Location> greenJoin = new HashSet<Location>();
	public Collection<Location> redJoin = new HashSet<Location>();
	public Collection<Player> greenTeam = new HashSet<Player>();
	public Collection<Player> redTeam = new HashSet<Player>();
	private static final String prefix = MissileWars.PREFIX;
	public boolean gameStarted = false;
	public boolean gameStarting = false;
	private Location greenLobbySpawn;
	private Location redLobbySpawn;
	private Location greenSpawn;
	private Location redSpawn;
	private Location lobby;
	private Location specSpawn;
	private Scoreboard board;
	private Team green;
	private Team red;
	private Team none;
	private Team spec;
	private ItemStack greenHelmet;
	private ItemStack greenChestplate;
	private ItemStack greenLeggings;
	private ItemStack greenBoots;
	private ItemStack redHelmet;
	private ItemStack redChestplate;
	private ItemStack redLeggings;
	private ItemStack redBoots;
	private ItemStack bow;
	public boolean gameStopped = false;
	// items
	public ItemStack fireball;
	public ItemStack tomahawk;
	public ItemStack juggernaut;
	public ItemStack shieldBuster;
	public ItemStack guardian;
	public ItemStack lightning;
	public ItemStack shield;
	private static Game instance;
	private World world;

	public Game(World world) {
		instance = this;
		this.world = world;
		board = Bukkit.getScoreboardManager().getNewScoreboard();
		green = board.registerNewTeam("green");
		green.setColor(ChatColor.GREEN);
		green.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
		green.setAllowFriendlyFire(false);
		red = board.registerNewTeam("red");
		red.setColor(ChatColor.RED);
		red.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
		red.setAllowFriendlyFire(false);
		none = board.registerNewTeam("none");
		none.setColor(ChatColor.GRAY);
		none.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.ALWAYS);
		none.setAllowFriendlyFire(false);
		spec = board.registerNewTeam("spec");
		spec.setColor(ChatColor.DARK_GRAY);
		spec.setOption(Option.NAME_TAG_VISIBILITY, OptionStatus.FOR_OWN_TEAM);
		spec.setAllowFriendlyFire(false);
		spec.setCanSeeFriendlyInvisibles(true);
		greenJoin.add(new Location(world, -117, 66, 6));
		greenJoin.add(new Location(world, -117, 66, 7));
		greenJoin.add(new Location(world, -117, 66, 8));
		greenJoin.add(new Location(world, -117, 66, 9));
		redJoin.add(new Location(world, -117, 66, -5));
		redJoin.add(new Location(world, -117, 66, -6));
		redJoin.add(new Location(world, -117, 66, -7));
		redJoin.add(new Location(world, -117, 66, -8));
		greenLobbySpawn = new Location(world, -81, 78, 21, 270F, 0F);
		redLobbySpawn = new Location(world, -81, 78, -20, 270F, 0F);
		greenSpawn = new Location(world, -26, 77, 62, 180F, 0F);
		redSpawn = new Location(world, -26, 77, -61, 360F, 0F);
		lobby = new Location(world, -100, 70, 0, 90F, 0F);
		specSpawn = new Location(world, -27, 74, 0);
		greenHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta greenHelmetMeta = (LeatherArmorMeta) greenHelmet.getItemMeta();
		greenHelmetMeta.setColor(Color.LIME);
		greenHelmetMeta.setUnbreakable(true);
		greenHelmet.setItemMeta(greenHelmetMeta);
		greenChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta greenChestplateMeta = (LeatherArmorMeta) greenChestplate.getItemMeta();
		greenChestplateMeta.setColor(Color.LIME);
		greenChestplateMeta.setUnbreakable(true);
		greenChestplate.setItemMeta(greenChestplateMeta);
		greenLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta greenLeggingsMeta = (LeatherArmorMeta) greenLeggings.getItemMeta();
		greenLeggingsMeta.setColor(Color.LIME);
		greenLeggingsMeta.setUnbreakable(true);
		greenLeggings.setItemMeta(greenLeggingsMeta);
		greenBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta greenBootsMeta = (LeatherArmorMeta) greenBoots.getItemMeta();
		greenBootsMeta.setColor(Color.LIME);
		greenBootsMeta.setUnbreakable(true);
		greenBoots.setItemMeta(greenBootsMeta);
		redHelmet = new ItemStack(Material.LEATHER_HELMET);
		LeatherArmorMeta redHelmetMeta = (LeatherArmorMeta) redHelmet.getItemMeta();
		redHelmetMeta.setColor(Color.RED);
		redHelmetMeta.setUnbreakable(true);
		redHelmet.setItemMeta(redHelmetMeta);
		redChestplate = new ItemStack(Material.LEATHER_CHESTPLATE);
		LeatherArmorMeta redChestplateMeta = (LeatherArmorMeta) redChestplate.getItemMeta();
		redChestplateMeta.setColor(Color.RED);
		redChestplateMeta.setUnbreakable(true);
		redChestplate.setItemMeta(redChestplateMeta);
		redLeggings = new ItemStack(Material.LEATHER_LEGGINGS);
		LeatherArmorMeta redLeggingsMeta = (LeatherArmorMeta) redLeggings.getItemMeta();
		redLeggingsMeta.setColor(Color.RED);
		redLeggingsMeta.setUnbreakable(true);
		redLeggings.setItemMeta(redLeggingsMeta);
		redBoots = new ItemStack(Material.LEATHER_BOOTS);
		LeatherArmorMeta redBootsMeta = (LeatherArmorMeta) redBoots.getItemMeta();
		redBootsMeta.setColor(Color.RED);
		redBootsMeta.setUnbreakable(true);
		redBoots.setItemMeta(redBootsMeta);
		bow = new ItemStack(Material.BOW);
		ItemMeta bowMeta = bow.getItemMeta();
		bowMeta.setDisplayName("§cBow");
		bowMeta.setUnbreakable(true);
		bow.setItemMeta(bowMeta);
		bow.addUnsafeEnchantment(Enchantment.ARROW_FIRE, 1);
		bow.addUnsafeEnchantment(Enchantment.DAMAGE_ALL, 4);
		bow.addUnsafeEnchantment(Enchantment.DURABILITY, 10);
		// items
		fireball = new ItemStack(Material.FIRE_CHARGE, 1);
		ItemMeta fireballMeta = fireball.getItemMeta();
		fireballMeta.setDisplayName("§6Fireball");
		fireball.setItemMeta(fireballMeta);
		tomahawk = new ItemStack(Material.CREEPER_SPAWN_EGG, 1);
		ItemMeta tomahawkMeta = tomahawk.getItemMeta();
		tomahawkMeta.setDisplayName("§2Tomahawk");
		tomahawk.setItemMeta(tomahawkMeta);
		juggernaut = new ItemStack(Material.SHEEP_SPAWN_EGG, 1);
		ItemMeta juggernautMeta = juggernaut.getItemMeta();
		juggernautMeta.setDisplayName("§aJuggernaut");
		juggernaut.setItemMeta(juggernautMeta);
		shieldBuster = new ItemStack(Material.WITCH_SPAWN_EGG, 1);
		ItemMeta shieldBusterMeta = shieldBuster.getItemMeta();
		shieldBusterMeta.setDisplayName("§4Shieldbuster");
		shieldBuster.setItemMeta(shieldBusterMeta);
		guardian = new ItemStack(Material.GUARDIAN_SPAWN_EGG, 1);
		ItemMeta guardianMeta = guardian.getItemMeta();
		guardianMeta.setDisplayName("§aGuardian");
		guardian.setItemMeta(guardianMeta);
		lightning = new ItemStack(Material.OCELOT_SPAWN_EGG, 1);
		ItemMeta lightningMeta = lightning.getItemMeta();
		lightningMeta.setDisplayName("§bLightning");
		lightning.setItemMeta(lightningMeta);
		shield = new ItemStack(Material.SNOWBALL, 1);
		ItemMeta shieldMeta = shield.getItemMeta();
		shieldMeta.setDisplayName("§1Shield");
		shield.setItemMeta(shieldMeta);
	}

	public Collection<Location> getGreenJoin() {
		return greenJoin;
	}

	public Collection<Location> getRedJoin() {
		return redJoin;
	}

	public Scoreboard getScoreboard() {
		return board;
	}

	public World getWorld() {
		return world;
	}

	public void greenAddPlayer(Player player) {
		if (nextTeam() == PlayerTeam.NONE || nextTeam() == PlayerTeam.GREEN) {
			PlayerInventory inv = player.getInventory();
			inv.addItem(bow);
			inv.setHelmet(greenHelmet);
			inv.setChestplate(greenChestplate);
			inv.setLeggings(greenLeggings);
			inv.setBoots(greenBoots);
			spec.removeEntry(player.getName());
			none.removeEntry(player.getName());
			green.addEntry(player.getName());
			greenTeam.add(player);
			player.setDisplayName("§a" + player.getName());
			player.setPlayerListName("§a" + player.getName());
			if (!gameStarted) {
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(greenLobbySpawn);
			} else {
				greenTeleport(player);
			}
			for (Player tmpPlayer : Bukkit.getOnlinePlayers()) {
				tmpPlayer.sendMessage(
						prefix + this.getPlayerPrefix(player) + player.getName() + "§a joined the green team!");
			}
		} else {
			player.sendMessage(prefix + "§cPlease join another team!");
		}
	}

	public void redAddPlayer(Player player) {
		if (nextTeam() == PlayerTeam.NONE || nextTeam() == PlayerTeam.RED) {
			PlayerInventory inv = player.getInventory();
			inv.addItem(bow);
			inv.setHelmet(redHelmet);
			inv.setChestplate(redChestplate);
			inv.setLeggings(redLeggings);
			inv.setBoots(redBoots);
			spec.removeEntry(player.getName());
			none.removeEntry(player.getName());
			red.addEntry(player.getName());
			redTeam.add(player);
			player.setDisplayName("§c" + player.getName());
			player.setPlayerListName("§c" + player.getName());
			if (!gameStarted) {
				player.setGameMode(GameMode.ADVENTURE);
				player.teleport(redLobbySpawn);
			} else {
				redTeleport(player);
			}
			for (Player tmpPlayer : Bukkit.getOnlinePlayers()) {
				tmpPlayer.sendMessage(
						prefix + getPlayerPrefix(player) + player.getName() + "§c joined the red team!");
			}
		} else {
			player.sendMessage(prefix + "§cPlease join another team!");
		}
	}

	public void greenRemovePlayer(Player player) {
		none.addEntry(player.getName());
		if (greenTeam.contains(player)) {
			greenTeam.remove(player);
			green.removeEntry(player.getName());
		}
	}

	public void redRemovePlayer(Player player) {
		none.addEntry(player.getName());
		if (redTeam.contains(player)) {
			redTeam.remove(player);
			red.removeEntry(player.getName());
		}
	}

	public void returnToLobby(Player player) {
		greenRemovePlayer(player);
		redRemovePlayer(player);
		spec.removeEntry(player.getName());
		player.teleport(lobby);
		none.addEntry(player.getName());
		player.setDisplayName("§7" + player.getName());
		player.setPlayerListName("§7" + player.getName());
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().clear();
		player.getInventory().setArmorContents(new ItemStack[4]);
		player.setAllowFlight(false);
		player.setFlying(false);
	}

	public void removeAllTeams(Player player) {
		greenRemovePlayer(player);
		redRemovePlayer(player);
		spec.removeEntry(player.getName());
		none.addEntry(player.getName());
		player.setDisplayName("§7" + player.getName());
		player.setPlayerListName("§7" + player.getName());
		player.setGameMode(GameMode.ADVENTURE);
		player.getInventory().clear();
		player.getInventory().setArmorContents(new ItemStack[4]);
		player.setAllowFlight(false);
		player.setFlying(false);
	}

	public PlayerTeam nextTeam() {
		if (red.getSize() == green.getSize()) {
			return PlayerTeam.NONE;
		} else if (red.getSize() > green.getSize()) {
			return PlayerTeam.GREEN;
		} else if (green.getSize() > red.getSize()) {
			return PlayerTeam.RED;
		} else {
			return PlayerTeam.NONE;
		}
	}

	public void setGameStarted(boolean gameStarted) {
		this.gameStarted = gameStarted;
	}

	public void setGameStarting(boolean gameStarting) {
		this.gameStarting = gameStarting;
	}

	public void greenTeleport(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		player.teleport(greenSpawn);
	}

	public void redTeleport(Player player) {
		player.setGameMode(GameMode.SURVIVAL);
		player.teleport(redSpawn);
	}

	public Location getLobbySpawn() {
		return lobby;
	}

	public Location getGreenSpawn() {
		return greenSpawn;
	}

	public Location getRedSpawn() {
		return redSpawn;
	}

	public Location getSpecSpawn() {
		return specSpawn;
	}

	public void spectate(Player player, boolean teleport) {
		removeAllTeams(player);
		player.setHealth(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
		player.getInventory().clear();
		player.getInventory().setArmorContents(new ItemStack[4]);
		spec.addEntry(player.getName());
		player.setDisplayName("§8" + player.getName());
		player.setPlayerListName("§8" + player.getName());
		if (teleport) {
			player.teleport(specSpawn);
		}
		player.setGameMode(GameMode.SPECTATOR);
	}

	public static Game getGame() {
		return instance;
	}

	public PlayerTeam getPlayerTeam(Player p) {
		if (redTeam.contains(p)) {
			return PlayerTeam.RED;
		} else if (greenTeam.contains(p)) {
			return PlayerTeam.GREEN;
		} else if (spec.hasEntry(p.getName())) {
			return PlayerTeam.SPEC;
		} else {
			return PlayerTeam.NONE;
		}
	}

	public String getPlayerPrefix(Player p) {
		switch (getPlayerTeam(p)) {
			case GREEN:
				return "§a";
			case RED:
				return "§c";
			case SPEC:
				return "§8";
			case NONE:
			default:
				return "§7";
		}
	}

	public enum PlayerTeam {
		RED, GREEN, SPEC, NONE;
	}
}
