package erogenousbeef.bigreactors.common.multiblock.block;

import java.util.List;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import erogenousbeef.bigreactors.common.BigReactors;
import net.minecraft.block.BlockContainer;
import net.minecraft.block.material.Material;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.creativetab.CreativeTabs;
import net.minecraft.entity.EnumCreatureType;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.util.IIcon;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;

public class BlockExchangerInteriorPart extends BlockContainer {

	public static final int METADATA_PRIMARY = 0;
	public static final int METADATA_SECONDARY = 1;

	private static final String[] _subBlocks = { "primary", "secondary" };
	private IIcon[] _icons;

	public BlockExchangerInteriorPart(Material material) {
		super(material);
		setStepSound(soundTypeMetal);
		setHardness(2.0f);
		setBlockName("blockExchangerInteriorPart");
		this.setBlockTextureName(BigReactors.TEXTURE_NAME_PREFIX + "blockExchangerPart");
		setCreativeTab(BigReactors.TAB);
	}

	@Override
	public TileEntity createNewTileEntity(World p_149915_1_, int p_149915_2_) {
		return null;
	}
	
	@Override
    public IIcon getIcon(IBlockAccess blockAccess, int x, int y, int z, int side) {
		int metadata = blockAccess.getBlockMetadata(x, y, z);
		if(metadata >= 0 && metadata < _icons.length) {
			return _icons[metadata];
		}
		return blockIcon;
	}

	@Override
	@SideOnly(Side.CLIENT)
	public void registerBlockIcons(IIconRegister par1IconRegister)
	{
		String prefix = BigReactors.TEXTURE_NAME_PREFIX + getUnlocalizedName() + ".";
		_icons = new IIcon[_subBlocks.length];
		for(int i = 0; i < _subBlocks.length; i++) {
			_icons[i] = par1IconRegister.registerIcon(prefix + _subBlocks[i]);
		}
		
		blockIcon = par1IconRegister.registerIcon(prefix + "default");
	}

	@Override
	public boolean isOpaqueCube()
	{
		// TODO: Switch to false once renderer is in
		return true;
	}

	@Override
	public boolean renderAsNormalBlock()
	{
		// TODO: Switch to false once renderer is in
		return true;
	}
	
	@Override
	public int damageDropped(int metadata)
	{
		return metadata;
	}
	
	@Override
	public void getSubBlocks(Item par1, CreativeTabs par2CreativeTabs, List par3List)
	{
		for(int metadata = 0; metadata < _subBlocks.length; metadata++) {
			par3List.add(new ItemStack(this, 1, metadata));
		}
	}

	@Override
    public boolean canCreatureSpawn(EnumCreatureType type, IBlockAccess world, int x, int y, int z)
    {
		return false;
    }

	public ItemStack getItemStack(String name) {
		for(int i = 0; i < _subBlocks.length; i++) {
			if(_subBlocks[i].equals(name)) {
				return new ItemStack(this, 1, i);
			}
		}
		
		throw new IllegalArgumentException("Unable to find Heat Exchanger part with name " + name);
	}
}
