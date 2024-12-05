package me.Mastervrunner.BlankCombo;

import java.util.ArrayList;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.entity.Player;

import com.projectkorra.projectkorra.ProjectKorra;
import com.projectkorra.projectkorra.ability.AddonAbility;
import com.projectkorra.projectkorra.ability.AvatarAbility;
import com.projectkorra.projectkorra.ability.ComboAbility;
import com.projectkorra.projectkorra.ability.EarthAbility;
import com.projectkorra.projectkorra.ability.WaterAbility;
import com.projectkorra.projectkorra.ability.util.ComboManager.AbilityInformation;
import com.projectkorra.projectkorra.util.ClickType;

//           ClassName          Element           
public class BlankCombo extends WaterAbility implements AddonAbility, ComboAbility{
	

	//These are the variables for the code
	
	//Cooldown is set to 1000 Milliseconds, or 1 second
	private long cooldown = 1000;
	
	//We are leaving the origin location blank until we use the combo
	private Location origin;

	public BlankCombo(Player player) {
		super(player);
		if (!bPlayer.canBendIgnoreBinds(this) || bPlayer.isOnCooldown(this)) {
			return;
		}
	
		setFields();
		//start(); //This would be to start the combo, but since it is blank, we don't need to
		
		//The player variable is automatically defined by ProjectKorra when you use the combo.
		player.sendMessage("Blank combo!");
		
		String myTest;
		myTest = "Hi, this is a test: ";
		
		if(player.isSneaking()) {
			myTest = myTest + "I am sneaking!";
			//Hi, this is a test: I am sneaking!
		} else {
			myTest = myTest + "I am not sneaking!";
		}
		
		
		
		player.sendMessage(myTest);
		
		
		//Calls the remove function to remove unwanted variables
		remove();
		
		//This adds a cooldown to the combo based on getCooldown()
		bPlayer.addCooldown(this);
		
		//Stops the code from continuing
		return;
	}

	private void setFields() {
		//This is typically to get the config for the ability
		
		//We set the origin location to the player location when we use this combo
		origin = player.getLocation();
	}
	
	@Override
	public void progress() {
		if (!player.isOnline() || player.isDead() || !player.isSneaking()) {
			remove();
			bPlayer.addCooldown(this);
			return;
		}
	
		//This is the progress function which would be called every tick (20 Times per second) assuming the ability was started.
		
	}
	
	@Override
	public long getCooldown() {
		//What is the cooldown for this?
		return cooldown;
	}

	@Override
	public Location getLocation() {
		//This is the location assosiated with your combo. Lets just have it be the player location for now.
		return origin;
	}

	@Override
	public String getName() {
		//What is the name of your combo?
		return "BlankCombo";
	}

	@Override
	public boolean isHarmlessAbility() {
		//Is this combo harmless?
		return false;
	}

	@Override
	public boolean isSneakAbility() {
		//Do you have to use Sneak/Shift to use this combo?
		return true;
	}

	@Override
	public Object createNewComboInstance(Player player) {
		//This tells the code that when you use the combo listed in getCombination() to call the function BlankCombo() above
		return new BlankCombo(player);
	}

	@Override
	public ArrayList<AbilityInformation> getCombination() {
		ArrayList<AbilityInformation> combo = new ArrayList<>();
		
		//To add a combo, you add to the arrayList with the Ability name, and the click type.
		combo.add(new AbilityInformation("FireBlast", ClickType.SHIFT_DOWN));
		
		combo.add(new AbilityInformation("FireBlast", ClickType.SHIFT_UP));
		return combo;
	}

	@Override
	public String getAuthor() {
		//Who made this combo?
		return "Mastervrunner";
	}

	@Override
	public String getVersion() {
		//What is the Version of the combo?
		return ChatColor.DARK_GRAY + "[" + ChatColor.DARK_RED + "v. 1.0.0" + ChatColor.DARK_GRAY + "]";
	}
	
	@Override
	public String getDescription() {
		//What is the description of the combo?
		return "This is a blank combo, the version is: " + getVersion();
	}
	
	@Override
	public String getInstructions() {
		//What are the instructions to use the combo? Note: This does not directly effect how the combo works, it is just to tell the player how to use it.
		return "WaterManipulation (Tap sneak)";
	}
	
	@Override
	public void remove() {
		super.remove();
	}
	
	@Override
	public void load() {
		ProjectKorra.plugin.getServer().getLogger().info(getName() + " " + getVersion() + ChatColor.DARK_RED + " by " + getAuthor() + " has been enabled.");
	}

	@Override
	public void stop() {
		ProjectKorra.plugin.getServer().getLogger().info(getName() + " " + getVersion() + ChatColor.DARK_RED + " by " + getAuthor() + " has been disabled.");
		
		super.remove();
	}

}