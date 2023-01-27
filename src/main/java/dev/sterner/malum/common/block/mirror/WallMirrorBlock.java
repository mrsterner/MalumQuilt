package dev.sterner.malum.common.block.mirror;

import com.sammy.lodestone.systems.block.WaterLoggedEntityBlock;
import dev.sterner.malum.common.blockentity.mirror.MirrorBlockEntity;
import dev.sterner.malum.common.registry.MalumBlockEntityRegistry;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.state.StateManager;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import org.jetbrains.annotations.NotNull;

public abstract class WallMirrorBlock<T extends MirrorBlockEntity> extends WaterLoggedEntityBlock<T> {
    public static final DirectionProperty FACING = Properties.FACING;

    public WallMirrorBlock(Settings properties) {
        super(properties);
        this.setDefaultState(this.getDefaultState().with(FACING, Direction.NORTH));
    }

	@Override
	public BlockEntity createBlockEntity(@NotNull BlockPos pos, @NotNull BlockState state) {
		setBlockEntity((BlockEntityType<T>) MalumBlockEntityRegistry.EMITTER_MIRROR);
		return super.createBlockEntity(pos, state);
	}

    @Override
    public void appendProperties(StateManager.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.appendProperties(builder);
    }

    @Override
    public BlockState getPlacementState(ItemPlacementContext ctx) {
        return super.getPlacementState(ctx).with(FACING, ctx.getSide());
    }
}
