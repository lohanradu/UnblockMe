import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Board {
	final int width;
	final int height;
	Set<Block> blocks = new HashSet<Block>();

	public Board(int w, int h) {
		width = w;
		height = h;
		initBorders();
	}

	private void initBorders() {
		blocks.add(new Block(-1, "h", 1, 0, width));
		blocks.add(new Block(-2, "v", 0, 1, height));
		blocks.add(new Block(-3, "h", 1, height + 1, width));
		// blocks.add(new Block(-4, "v", width + 1, 1, height));
	}

	public void addBlocks(Collection<Block> newBlocks) {
		blocks.addAll(newBlocks);
		updateRightBorder();
	}

	private void updateRightBorder() {
		int gateX = getBlockById(0).x;
		blocks.add(new Block(-4, "v", width + 1, 1, gateX));
		blocks.add(new Block(-5, "v", width + 1, gateX + 1, height - gateX));

	}

	public boolean checkStepValid(Step step) {
		Block block = getBlockById(step.id);
		Block futureBlock = getFutureBlock(block, step.steps);
		for (Block other : blocks) {
			if (!block.equals(other)) {
				if (futureBlock.intersects(other)) {
					return false;
				}
			}
		}
		return true;
	}

	private Block getFutureBlock(Block block, int stepValue) {
		if (block.orientation.equals(Block.HORIZONTAL)) {
			return new Block(block.id, block.orientation, block.x + stepValue,
					block.y, block.length);
		} else {
			return new Block(block.id, block.orientation, block.x, block.y
					+ stepValue, block.length);
		}
	}

	private Block getBlockById(int id) {
		for (Block block : blocks) {
			if (block.id == id) {
				return block;
			}
		}
		throw new RuntimeException("Block not found");
	}

	public List<Integer> getUnmovableBlocks() {
		List<Integer> unmovable = new ArrayList<>();

		for (Block block : blocks) {
			if (block.id >= 0 && blockIsUnmovable(block)) {
				unmovable.add(block.id);
			}
		}

		return unmovable;
	}

	private boolean blockIsUnmovable(Block block) {
		Step step1 = new Step(block.id, -1);
		Step step2 = new Step(block.id, 1);
		if (checkStepValid(step1) || checkStepValid(step2)) {
			return false;
		}
		return true;
	}

	public void makeStep(Step step) {
		Block oldBlock = getBlockById(step.id);
		Block movedBlock = getFutureBlock(oldBlock, step.steps);
		blocks.remove(oldBlock);
		blocks.add(movedBlock);
	}

	public void play() {

	}

	public boolean finished() {
		Block redBlock = getBlockById(0);
		Step step = new Step(0, 1);
		int stepCount = 0;
		while (checkStepValid(step) && stepCount <= redBlock.x) {
			step.steps++;
			stepCount++;
		}

		if (stepCount == width - redBlock.x) {
			return true;
		} else {
			return false;
		}
	}
}
